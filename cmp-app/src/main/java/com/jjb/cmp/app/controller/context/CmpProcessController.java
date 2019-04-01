package com.jjb.cmp.app.controller.context;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jjb.acl.infrastructure.TmAclDict;
import com.jjb.cmp.app.controller.fastdfs.FastDFSClient;
import com.jjb.cmp.app.controller.fastdfs.FileCheck;
import com.jjb.cmp.biz.cache.controller.CmpCacheContext;
import com.jjb.cmp.biz.service.content.TmCmpContentService;
import com.jjb.cmp.biz.service.content.TmCmpMainService;
import com.jjb.cmp.biz.util.LogPrintUtils;
import com.jjb.cmp.dto.TmCmpContentDto;
import com.jjb.cmp.infrastructure.TmCmpContent;
import com.jjb.cmp.infrastructure.TmCmpMain;
import com.jjb.fastdfs.client.FastDFSException;
import com.jjb.unicorn.facility.context.OrganizationContextHolder;
import com.jjb.unicorn.facility.exception.ProcessException;
import com.jjb.unicorn.facility.model.Json;
import com.jjb.unicorn.facility.model.Page;
import com.jjb.unicorn.facility.model.Query;
import com.jjb.unicorn.facility.util.StringUtils;
import com.jjb.unicorn.web.controller.BaseController;

/**
 * @ClassName CmpProcessController
 * @Description 打开影像调阅窗口
 * @Author smh
 * Date 2018/12/31 11:47
 * Version 1.0
 */
@Controller
@RequestMapping("/cmp_")
public class CmpProcessController extends CommonController {
    public static final String  sqlId ="com.jjb.acl.infrastructure.mapping.TmAclDictMapper.selectAll";
    private static final String symbolss="0123456789";
    private  static  final Random randoms=new SecureRandom();
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private TmCmpMainService tmCmpMainService;
    @Autowired
    private TmCmpContentService tmCmpContentService;
    @Autowired
    private CmpCacheContext cmpCacheContext;
    /**
     * @Author smh
     * @Description TODO 跳转到影像平台,显示存在的所有类型的有效影像
     * @Date 2018/12/28 14:56
     */
    @RequestMapping("/showContent")
    public String showContent() {
        String remark=null;
        remark="已有批次号; "+"创建于: ";
        String batchNo = getPara("batchNo");
        TmCmpMain tmCmpMain = new TmCmpMain();
        TmCmpMain cmpMain = new TmCmpMain();
        TmCmpContentDto conDto = new TmCmpContentDto();
        conDto.setBatchNo(batchNo);
        conDto.setContStatus("A");
        List<TmCmpContentDto> list = null;
        LinkedHashMap<String, LinkedHashMap<String, List<TmCmpContentDto>>> allContextMap = new LinkedHashMap<>();
        try {
            //取到cmpMain 页面显示姓名和身份证号,批次号使用
            if (StringUtils.isNotEmpty(batchNo)) {
                tmCmpMain.setBatchNo(batchNo);
            }
            cmpMain = tmCmpMainService.getTmCmpMain(tmCmpMain);
            if (cmpMain == null) {
                logger.info("cmpMain为空,未查询到相关的信息");
                throw new ProcessException("cmpMain为空");
            }
            //查询有效的
            list = tmCmpContentService.quyContentByParam(conDto);
            if (list != null) {
                for (TmCmpContentDto dto : list) {
                    if (dto == null || StringUtils.isEmpty(dto.getBatchNo())) {
                        continue;
                    }
                    LinkedHashMap<String, List<TmCmpContentDto>> batchMap = new LinkedHashMap<>();
                    List<TmCmpContentDto> l1 = new ArrayList<>();
                    //如果存在大批次号大
                    if (allContextMap.get(dto.getBatchNo()) != null && allContextMap.get(dto.getBatchNo()).size() != 0) {
                        batchMap = allContextMap.get(dto.getBatchNo());
                        if (batchMap.get(dto.getSubTypeDesc()) != null && batchMap.get(dto.getSubTypeDesc()).size() > 0) {
                            l1 = batchMap.get(dto.getSubTypeDesc());
                            l1.add(dto);
                        } else {
                            l1 = new ArrayList<>();
                            l1.add(dto);
                        }
                        batchMap.put(dto.getSubTypeDesc(), l1);
                        allContextMap.put(dto.getBatchNo(), batchMap);
                    } else {//如果不存在大map中
                        l1 = new ArrayList<>();
                        l1.add(dto);
                        batchMap.put(dto.getSubTypeDesc(), l1);
                        allContextMap.put(dto.getBatchNo(), batchMap);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("内容信息调阅异常", e);
//            return exceptionPageUtils.doExcepiton(e.getMessage(),batchNo);
        }
        setAttr("allContextList", list);
        setAttr("allContextMap", allContextMap);

        //取到cmpMain 页面显示姓名和身份证号,批次号使用
        setAttr("tmCmpMain", cmpMain);
        setAttr("remark",remark);
        return "/contextView.ftl";
    }

    /**
     * 跳转到上传页面
     *
     * @return
     */
    @RequestMapping("/uploadPage")
    public String uploadPage() {
       return commonUploadPage();
    }

    /**
     * 影像列表展示查询
     *
     * @return
     */
    @RequestMapping("/queryImageList")
    @ResponseBody
    public Page<TmCmpContentDto> queryImageList() {
       return commmonQueryImageList();
    }

    /**
     * @return
     */
    @RequestMapping("/loadingPage")
    public String loadingPage() {
        return "index/index.ftl";
    }

    /**
     * @Author smh
     * @Description TODO 上传或者更新当前影像
     * @Date 2018/12/28 14:56
     */
    @Transactional
    @RequestMapping("uploadImage")
    @ResponseBody
    //用于区分是跟新还是上传,传一个标志,update更新upload为上传
    public Json uploadImage(String sgin) {
        return  commonUploadImage(sgin);
    }

    /**
     * 生成随机的4位数
     * @return
     */
    private Integer getContSort() {
        char[] nonceChars = new char[4];
        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = symbolss.charAt(randoms.nextInt(symbolss.length()));
        }
        Integer contSort = Integer.valueOf(new String(nonceChars));
        return contSort;
    }






}


