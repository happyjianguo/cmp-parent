package com.jjb.cmp.app.controller.context;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jjb.acl.biz.dao.TmAclDictDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jjb.acl.infrastructure.TmAclDict;
import com.jjb.cmp.app.controller.fastdfs.FastDFSClient;
import com.jjb.cmp.biz.cache.controller.CmpCacheContext;
import com.jjb.cmp.biz.dao.TmCmpMainDao;
import com.jjb.cmp.biz.service.content.TmCmpContentService;
import com.jjb.cmp.biz.service.content.TmCmpMainService;
import com.jjb.cmp.biz.service.content.TmCmpSeqService;
import com.jjb.cmp.biz.util.Base64UtilMultipart;
import com.jjb.cmp.dto.T40000req;
import com.jjb.cmp.dto.T400010req;
import com.jjb.cmp.dto.T40001req;
import com.jjb.cmp.infrastructure.TmCmpContent;
import com.jjb.cmp.infrastructure.TmCmpMain;
import com.jjb.fastdfs.client.FastDFSException;
import com.jjb.unicorn.facility.exception.ProcessException;
import com.jjb.unicorn.facility.util.StringUtils;
import com.jjb.unicorn.web.controller.BaseController;

/**
 * @ClassName ImageActionController Company jydata-tech
 * @Description 外部系统通过restful 进行内容的上传、查询、批次号获取等操作 
 * @Author shiminghong Date 2019/3/21 15:39 Version 1.0
 */
@RestController
@Configuration
public class CmpRestfulController extends CommonController {
	@Autowired
	private TmCmpMainDao tmCmpMainDao;
	@Autowired
	private TmCmpSeqService tmCmpSeqService;
	@Autowired
	private TmCmpMainService tmCmpMainService;
	@Autowired
	private TmCmpContentService tmCmpContentService;
	@Autowired
	private CmpCacheContext cmpCacheContext;
	@Autowired
	private TmAclDictDao tmAclDictDao;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * T40000 40000-影像批次号申请
	 * 
	 * @param request
	 * @param response
	 * @param t40000req
	 * @return
	 */
	@RequestMapping(value = "/assets/api/v1/img/id", method = RequestMethod.POST)
	public JSON T40000(HttpServletRequest request, HttpServletResponse response, T40000req t40000req) {
		logger.info("T40000-影像批次号申请");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("RET_CODE", "S");
		jsonObject.put("RET_MSG", "响应成功");
		String newBatchNo = "";
		try {
			String org = t40000req.getOrg();
			String idType = t40000req.getId_type();
			String idNo = t40000req.getId_no();
			String name = t40000req.getName();
			String sysId = t40000req.getSys_id();
			String operateId = t40000req.getOperator_id();
			if (StringUtils.isEmpty(org) || StringUtils.isEmpty(idType) || StringUtils.isEmpty(idNo)
					|| StringUtils.isEmpty(name) || StringUtils.isEmpty(sysId) || StringUtils.isEmpty(operateId)) {
				throw new Exception("未获取到有效参数");
			}
			TmCmpMain tmCmpMain = new TmCmpMain();
			tmCmpMain.setOrg(org);
			tmCmpMain.setIdType(idType);
			tmCmpMain.setIdNo(idNo);
			tmCmpMain.setName(name);
			TmCmpMain cmpMain = tmCmpMainDao.getTmCmpMain(tmCmpMain);
			if (cmpMain != null && StringUtils.isNotBlank(cmpMain.getBatchNo())) {
				throw new Exception("该客户批次号已存在,已有批次号为" + cmpMain.getBatchNo());
			}
			// 开始获取新的批次号
			// TODO Auto-generated method stub
			String sysType = "IMG";
			java.text.DateFormat format = new java.text.SimpleDateFormat("yyyyMMddHH");
			String dateNo = format.format(new Date());
			String zeroAppno = "0";
			String seqNo = tmCmpSeqService.getSeqNo(org);
			// 设置八位数
			if (8 == seqNo.length()) {
				newBatchNo = sysType + dateNo + zeroAppno;
			} else {
				for (int i = 1; i < (8 - seqNo.length()); i++) {
					zeroAppno = "0" + zeroAppno;
				}
			}
			newBatchNo = dateNo + zeroAppno + seqNo;
			tmCmpMain.setBatchNo(newBatchNo);
			tmCmpMain.setUpdateDate(new Date());
			tmCmpMain.setUpdateUser(operateId);
			tmCmpMainDao.saveTmCmpMain(tmCmpMain);
		} catch (Exception e) {
			logger.error("获取批次号异常", e);
			jsonObject.put("RET_CODE", "F");
			jsonObject.put("RET_MSG", "响应失败:" + e.getMessage());
		}
		jsonObject.put("IMAGE_NO", newBatchNo);
		return jsonObject;
	}

	/**
	 * T40001 影像新增\补录
	 * 
	 * @param request
	 * @param response
	 * @param t40001req
	 * @return
	 */
	@RequestMapping(value = "/assets/api/v1/img/upload", method = RequestMethod.POST)
	public JSON T40001(HttpServletRequest request, HttpServletResponse response, T40001req t40001req) {
		logger.info("T40001-影像新增/补录");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("RET_CODE", "S");
		jsonObject.put("RET_MSG", "响应成功");
		String image_no = "";
		try {
			String org = t40001req.getOrg();
			String sys_id = t40001req.getSys_id();
			image_no = t40001req.getImage_no();// 批次号
			String operator_id = t40001req.getOperator_id();// 操作人
			JSONArray jsonArray= JSON.parseArray(t40001req.getImages_list());
			if (StringUtils.isEmpty(org) || StringUtils.isEmpty(sys_id) || StringUtils.isEmpty(image_no)
					|| StringUtils.isEmpty(operator_id)||!(jsonArray.size()>0)) {
				throw new Exception("未获取到有效参数");
			}
			for (int i =0;i<jsonArray.size();i++){
				JSONObject jobject = jsonArray.getJSONObject(i);
				String sup_type = StringUtils.valueOf(jobject.get("sup_type"));
				String sub_type =StringUtils.valueOf(jobject.get("sub_type"));
				String branch_code = StringUtils.valueOf(jobject.get("branch_code"));
				String upload_sys_id = StringUtils.valueOf(jobject.get("upload_sys_id"));
				String format = StringUtils.valueOf(jobject.get("format"));
				String content = StringUtils.valueOf(jobject.get("content"));
				if (StringUtils.isEmpty(sup_type) || StringUtils.isEmpty(sub_type)
						|| StringUtils.isEmpty(upload_sys_id) || StringUtils.isEmpty(branch_code)
						|| StringUtils.isEmpty(format) || StringUtils.isEmpty(content)) {
					throw new Exception("未获取到有效参数(T400010req)");
				}
				// 开始上传
				MultipartFile file = Base64UtilMultipart.base64ToMultipart(content);
				if (file.isEmpty()) {
					throw new ProcessException("上传的文件不存在");
				}
				String url = uploadFileWithMultipart(file);
				if (!StringUtils.isEmpty(url)) {
					// 更新内容管理主表信息
					TmCmpMain tmCmpMain = new TmCmpMain();
					tmCmpMain.setBatchNo(image_no);
					TmCmpMain cmpMain = tmCmpMainService.getTmCmpMain(tmCmpMain);
					cmpMain.setUpdateUser(operator_id);
					cmpMain.setUpdateDate(new Date());
					tmCmpMainService.updateTmCmpMain(cmpMain);
					// 更新内容清单
					String supTypeDesc = "";
					String subTypeDesc = "";
					TmAclDict tmAclDict = tmAclDictDao.getDictByType("fileBigType", sup_type);
					if (tmAclDict != null) {
						supTypeDesc = tmAclDict.getCodeName();
					}
					TmAclDict tmAclDictt = tmAclDictDao.getDictByType("fileSmallType", sub_type);
					if (tmAclDict != null) {
						subTypeDesc = tmAclDictt.getCodeName();
					}
					if (StringUtils.isEmpty(supTypeDesc) || StringUtils.isEmpty(subTypeDesc)) {
						throw new ProcessException("未获取到有效的参数(sup_type,sub_type)");
					}
					TmCmpContent tmCmpContent = new TmCmpContent();
					tmCmpContent.setBatchNo(image_no);
					tmCmpContent.setConsSysId(sys_id);
					tmCmpContent.setSupType(sup_type);
					tmCmpContent.setSupTypeDesc(supTypeDesc);
					tmCmpContent.setSubType(sub_type);
					tmCmpContent.setSubTypeDesc(subTypeDesc);
					tmCmpContent.setBranch(branch_code);
					tmCmpContent.setUpdateUser(operator_id);
					tmCmpContent.setContFmt(format);
					// 生成一个随机的8位数的编号
					// Integer contSort =getContSort();
					// tmCmpContent.setContSort(contSort);//这里也需要一个唯一的编号
					tmCmpContent.setContRelPath("http://10.109.3.205:80/");
					tmCmpContent.setContAbsPath(url);
					tmCmpContent.setContStatus("A");
					tmCmpContent.setUpdateDate(new Date());
					tmCmpContentService.saveTmCmpContent(tmCmpContent);
				}else if (StringUtils.isBlank(url)){
					throw  new Exception("上传失败，未获取到返回值");
				}
			}
		} catch (Exception e) {
			logger.error("上传失败"+e);
			jsonObject.put("RET_CODE", "F");
			jsonObject.put("RET_MSG", "响应失败:" + e.getMessage());
		}
		jsonObject.put("IMAGE _NO", image_no);
		return jsonObject;
	}

}
