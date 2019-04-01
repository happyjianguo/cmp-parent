package com.jjb.cmp.app.controller.context;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import com.jjb.acl.infrastructure.TmAclDict;
import com.jjb.cmp.biz.service.param.CmpTableService;
import com.jjb.unicorn.facility.model.Json;
import com.jjb.unicorn.facility.model.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.jjb.cmp.biz.service.content.TmCmpContentService;
import com.jjb.cmp.biz.service.content.TmCmpMainService;
import com.jjb.cmp.dto.TmCmpContentDto;
import com.jjb.cmp.infrastructure.TmCmpMain;
import com.jjb.unicorn.facility.exception.ProcessException;
import com.jjb.unicorn.facility.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName RestFulController RESTFul风格接口
 * @Description 外部系统通过免密登录打开本系统页面</br>打开内容调阅、内容上传
 * @Author smh Date 2019/2/21 16:50 Version 1.0
 */
@Controller
@RequestMapping("/assets/cmp_")
public class CmpAssetsController extends CommonController {
	private static final String symbols = "0123456789";
	private static final Random random = new SecureRandom();
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private TmCmpMainService tmCmpMainService;
	@Autowired
	private TmCmpContentService tmCmpContentService;
	@Autowired
	private CmpTableService cmpTableService;

	/**
	 * @Author smh
	 * @Description 对外提供免密跳转，用于影像内容调阅查看
	 * @Date 2019/2/21 16:58
	 */
	@RequestMapping(value = "/content/{batchNo}/{sysId}/{userNo}/{branchCode}", method = RequestMethod.GET)
	public String get(@PathVariable("batchNo") String batchNo, @PathVariable("sysId") String systemId,
					  @PathVariable("userNo") String userNo, @PathVariable("branchCode") String branchCode) {
		long start = System.currentTimeMillis();
		showContent(batchNo, userNo, branchCode, systemId);
		return "/contextView.ftl";
	}

	/**
	 * 生成一个新的批次号
	 * 
	 * @return
	 */
/*	private String getNewBatchNo() {
		String sysType = "IMG";
		java.text.DateFormat format = new java.text.SimpleDateFormat("yyyyMMddHHmmSS");
		String dateNo = format.format(new Date());
		String zeroAppno = "0";
		char[] nonceChars = new char[6];
		for (int index = 0; index < nonceChars.length; ++index) {
			nonceChars[index] = symbols.charAt(random.nextInt(symbols.length()));
		}
		String randomNo = new String(nonceChars);
		String newBatchNo = sysType + dateNo + zeroAppno + randomNo;
		return newBatchNo;
	}*/

	/**
	 * 通过batchNo得到相应的数据
	 * 
	 * @param batchNo
	 */
	private void showContent(String batchNo, String userNo, String branchCode, String systemId) {
		TmCmpMain tmCmpMain = new TmCmpMain();
		TmCmpMain cmpMain = new TmCmpMain();
		TmCmpContentDto conDto = new TmCmpContentDto();
		conDto.setBatchNo(batchNo);
		conDto.setContStatus("A");
		List<TmCmpContentDto> list = null;
		LinkedHashMap<String, LinkedHashMap<String, List<TmCmpContentDto>>> allContextMap = new LinkedHashMap<>();
		try {
			// 取到cmpMain 页面显示姓名和身份证号,批次号使用
			if (StringUtils.isNotEmpty(batchNo)) {
				tmCmpMain.setBatchNo(batchNo);
			}
			cmpMain = tmCmpMainService.getTmCmpMain(tmCmpMain);
			if (cmpMain == null) {
				logger.info("cmpMain为空,未查询到相关的信息");
				throw new ProcessException("cmpMain为空");
			}
			// 查询有效的
			list = tmCmpContentService.quyContentByParam(conDto);
			if (list != null) {
				for (TmCmpContentDto dto : list) {
					if (dto == null || StringUtils.isEmpty(dto.getBatchNo())) {
						continue;
					}
					LinkedHashMap<String, List<TmCmpContentDto>> batchMap = new LinkedHashMap<>();
					List<TmCmpContentDto> l1 = new ArrayList<>();
					// 如果存在大批次号大
					if (allContextMap.get(dto.getBatchNo()) != null
							&& allContextMap.get(dto.getBatchNo()).size() != 0) {
						batchMap = allContextMap.get(dto.getBatchNo());
						if (batchMap.get(dto.getSubTypeDesc()) != null
								&& batchMap.get(dto.getSubTypeDesc()).size() > 0) {
							l1 = batchMap.get(dto.getSubTypeDesc());
							l1.add(dto);
						} else {
							l1 = new ArrayList<>();
							l1.add(dto);
						}
						batchMap.put(dto.getSubTypeDesc(), l1);
						allContextMap.put(dto.getBatchNo(), batchMap);
					} else {// 如果不存在大map中
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
		// 取到cmpMain 页面显示姓名和身份证号,批次号使用
		setAttr("tmCmpMain", cmpMain);
		setAttr("userNo", userNo);
		setAttr("branchCode", branchCode);
		setAttr("systemId", systemId);
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
	 * 获取表信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping({"/getTableByBigType"})
	public Json getTable() {
		Json j = Json.newSuccess();
		String type = getPara("type");
		String _PARENT_KEY=getPara("_PARENT_KEY");
		String _PARENT_VALUE = getPara("_PARENT_VALUE");
		try {
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("type", type);
			parameter.put(_PARENT_KEY,_PARENT_VALUE);
			List<TmAclDict>   list = cmpTableService.getTable(sqlId,parameter);
			j.setObj(list);
		} catch (Exception var10) {
			j.setFail("获取表信息失败,表,参数");
		}
		return j;
	}
}
