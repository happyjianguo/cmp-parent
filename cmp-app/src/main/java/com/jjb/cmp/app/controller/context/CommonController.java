package com.jjb.cmp.app.controller.context;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import java.util.Date;

/**
 * @ClassName CommonController
 * Company jydata-tech
 * @Description TODO   影像操作的公共方法  用于本系统操作与免密登录的操作
 * Author smh
 * Date 2019/3/28 16:07
 * Version 1.0
 */
public class CommonController extends BaseController{
    public static final String  sqlId ="com.jjb.acl.infrastructure.mapping.TmAclDictMapper.selectAll";
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private TmCmpMainService tmCmpMainService;
    @Autowired
    private TmCmpContentService tmCmpContentService;
    @Autowired
    private CmpCacheContext cmpCacheContext;

    /**
     * 跳转到上传页面的公共方法
     * @return
     */
    public String commonUploadPage() {
        String batchNo = getPara("batchNo");
        String userNo = getPara("userNo");
        String systemId = getPara("systemId");
        String branchCode = getPara("branchCode");
        TmCmpMain tmCmpMain = new TmCmpMain();
        String page="";
        try {
            if (StringUtils.isNotEmpty(batchNo)) {
                tmCmpMain.setBatchNo(batchNo);
            }else{
                logger.error("未获取到影像批次号");
                throw new ProcessException("未获取到影像批次号");
            }
            TmCmpMain cmpMain = new TmCmpMain();
            if (tmCmpMain != null) {
                cmpMain = tmCmpMainService.getTmCmpMain(tmCmpMain);
            }else {
                logger.error("为空,未查询到相关的信息");
                throw new ProcessException("cmpMain为空");
            }
            if (StringUtils.isBlank(userNo)&&StringUtils.isBlank(systemId)&&StringUtils.isBlank(branchCode)){
                page="/imageUpload.ftl";
            }else
            {
                page ="/imageAssetsUpload.ftl";
            }
            //取到cmpMain 页面显示使用
            setAttr("tmCmpMain", cmpMain);
            setAttr("userNo", userNo);
            setAttr("systemId", systemId);
            setAttr("branchCode", branchCode);

        } catch (Exception e) {
            logger.error("跳转上传页面失败", e);
            //返回一个错误页面
            //pagePath = exceptionPageUtils.doExcepiton(e.getMessage(),batchNo);
        }
        return page;
    }

    /**
     * 影像列表展示查询
     *
     * @return
     */
    public Page<TmCmpContentDto> commmonQueryImageList() {
        Long start = System.currentTimeMillis();
        logger.info("====>开始执行影像列表展示查询" + LogPrintUtils.printCommonStartLog(start, null));
        Page<TmCmpContentDto> page = getPage(TmCmpContentDto.class);
        try {
            page = tmCmpContentService.queryImageList(page);
        } catch (Exception e) {
            throw new ProcessException("影像列表展示查询异常[" + e.getMessage() + "]");
        }
        logger.info("====>结束执行影像列表展示查询" + LogPrintUtils.printCommonEndLog(start, null));
        return page;
    }

    /**
     *
     * @param sgin  用于区分是否是免密上传  upload  assetsUpload
     * @return
     */
    public Json commonUploadImage(String sgin) {
        Json json = Json.newSuccess();
        //获取参数,这里用待定用Query
        Query query = getQuery();
        String batchNo = StringUtils.valueOf(query.get("batchNo"));
        String userNo=StringUtils.valueOf(query.get("userNo"));  //操作人
        String systemId=StringUtils.valueOf(query.get("systemId"));
        String branchCode=StringUtils.valueOf(query.get("branchCode"));
        try {
            //必验证的参数  batchNo
            if (StringUtils.isEmpty(batchNo)) {
                throw new ProcessException("未获取到有效批次号");
            }
            //免密登录上传时  需要验证的参数  userNo systemId branchCode
            if (StringUtils.equals(sgin,"assetsUpload")){
                if (StringUtils.isBlank(userNo)||StringUtils.isBlank(systemId)||StringUtils.isBlank(branchCode)){
                    throw new ProcessException("未获取到有效参数（免密登录上传）");
                }
            }
        } catch (Exception e) {
            logger.error("影像上传失败", e);
            json.setFail("影像上传失败," + e.getMessage());
            json.setS(false);
        }
        int num = 0;
        //开始上传所有的影像
        while (StringUtils.isNotEmpty(String.valueOf(query.get("supType" + num))) &&
                StringUtils.isNotEmpty(String.valueOf(query.get("subType" + num)))) {

            String supType = StringUtils.valueOf(query.get("supType" + num));
            String subType = StringUtils.valueOf(query.get("subType" + num));

            MultipartFile file = getFile("fileName" + num);
            try {
                if (file.isEmpty()) {
                    throw new ProcessException("上传的文件不存在");
                }
                //上传时的操作
                    Boolean isImage = FileCheck.checkImage(file.getOriginalFilename());
                    String url = "";
                    if (isImage) {
                        url = uploadFileWithMultipart(file);
                        if (StringUtils.isBlank(url)){
                        throw new ProcessException("上传文件未返回地址");
                        }
                    } else {
                        throw new ProcessException("请上传图片格式的文件");
                    }
                        save(batchNo, supType, subType, url,userNo,systemId,branchCode);
            } catch (Exception e) {
                logger.error("影像上传失败", e);
                json.setFail("影像上传失败," + e.getMessage());
                json.setS(false);
            }
            num++;
        }
        return json;
    }


    /**
     * 开始上传文件
     *
     * @return
     */
    public String uploadFileWithMultipart(MultipartFile file) throws FastDFSException {
        FastDFSClient fastDFSClient = new FastDFSClient();
        String url = fastDFSClient.uploadFileWithMultipart(file);
        return url;
    }

    /**
     * 成功上传之后保存记录到数据库
     *
     * @param
     */
    public void save(String batchNo, String supType, String subType, String url,String userNo,String systemId,String branchCode) {
        //更新内容管理主表信息
        TmCmpMain tmCmpMain = new TmCmpMain();
        tmCmpMain.setBatchNo(batchNo);
        TmCmpMain cmpMain = tmCmpMainService.getTmCmpMain(tmCmpMain);
        if (StringUtils.isEmpty(userNo)){
            cmpMain.setUpdateUser(OrganizationContextHolder.getUserNo());
        }else{
            cmpMain.setUpdateUser(userNo);
        }
        cmpMain.setUpdateDate(new Date());
        tmCmpMainService.updateTmCmpMain(cmpMain);
        //更新内容清单
        String supTypeDesc = "";
        String subTypeDesc = "";
        TmAclDict tmAclDict=cmpCacheContext.getAclDictByCode("fileBigType",supType);
        if (tmAclDict!=null){
            supTypeDesc=tmAclDict.getCodeName();
        }
        TmAclDict tmAclDictt=cmpCacheContext.getAclDictByCode("fileSmallType",subType);
        if (tmAclDict!=null){
            subTypeDesc=tmAclDictt.getCodeName();
        }
        if (StringUtils.isEmpty(supTypeDesc)||StringUtils.isEmpty(subTypeDesc)){
            throw new ProcessException("未获取到有效的参数");
        }
        TmCmpContent tmCmpContent = new TmCmpContent();
        tmCmpContent.setBatchNo(batchNo);
        if (StringUtils.isEmpty(systemId)){
            tmCmpContent.setConsSysId("CMP");
        }else{
            tmCmpContent.setConsSysId(systemId);
        }
        tmCmpContent.setSupType(supType);
        tmCmpContent.setSupTypeDesc(supTypeDesc);
        tmCmpContent.setSubType(subType);
        tmCmpContent.setSubTypeDesc(subTypeDesc);
        if (StringUtils.isEmpty(branchCode)){
            tmCmpContent.setBranch(OrganizationContextHolder.getBranchCode());
        }else{
            tmCmpContent.setBranch(branchCode);
        }
        if (StringUtils.isEmpty(userNo)){
            tmCmpContent.setUpdateUser(OrganizationContextHolder.getUserNo());
        }else{
            tmCmpContent.setUpdateUser(userNo);
        }
        tmCmpContent.setContFmt("内容格式");
        //生成一个随机的8位数的编号
  /*      Integer contSort =getContSort();
        tmCmpContent.setContSort(contSort);//这里也需要一个唯一的编号*/
        tmCmpContent.setContRelPath("http://10.109.3.205:80/");
        tmCmpContent.setContAbsPath(url);
        tmCmpContent.setContStatus("A");
        tmCmpContent.setUpdateDate(new Date());
        tmCmpContentService.saveTmCmpContent(tmCmpContent);

    }
}
