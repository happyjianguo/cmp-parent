package com.jjb.cmp.app.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jjb.cmp.biz.service.content.TmCmpContentService;
import com.jjb.cmp.infrastructure.TmCmpContent;
import com.jjb.unicorn.facility.context.OrganizationContextHolder;
import com.jjb.unicorn.facility.model.Json;
import com.jjb.unicorn.facility.model.Query;
import com.jjb.unicorn.web.controller.BaseController;

@Controller
@RequestMapping("/assets/cmp")
public class Cmp_Controller extends BaseController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private TmCmpContentService tmCmpContentService;

	/**
	 * 删除
	 *
	 * @return
	 */
	@Transactional
	@RequestMapping("deleteImage")
	@ResponseBody
	public Json updateImage() {
		Json json = Json.newSuccess();
		// 获取参数,这里用待定用Query
		Query query = null;
		try {
			// 删除时的操作,先使原来的变为无效
			TmCmpContent tmCmpContent = new TmCmpContent();
			// 获取当前影像的BatchNo,supType,subType
			tmCmpContent.setBatchNo("");
			tmCmpContent.setSubType("");
			tmCmpContent.setSupType("");
			TmCmpContent cmpContent = tmCmpContentService.getTmCmpContent(tmCmpContent);
			cmpContent.setUpdateDate(new Date());
			// 怎么取维护人,后续补充
			cmpContent.setUpdateUser(OrganizationContextHolder.getUserNo());
			// 改变当前影像的状态
			cmpContent.setContStatus("B");
			// 跟新内容清单表
			tmCmpContentService.updateTmCmpContent(cmpContent);
		} catch (Exception e) {
			logger.error("影像删除失败", e);
			json.setFail("影像删除失败");
			json.setS(false);
		}
		return json;
	}

}
