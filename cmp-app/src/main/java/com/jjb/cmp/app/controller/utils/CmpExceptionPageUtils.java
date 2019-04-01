package com.jjb.cmp.app.controller.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jjb.unicorn.web.controller.BaseController;

@Service
public class CmpExceptionPageUtils extends BaseController{
	
	private final static Logger logger = LoggerFactory.getLogger(CmpExceptionPageUtils.class);
	
	/**
	 *  执行错误界面的方法,返回错误页面，展示对应的错误信息
	 *  
	 *  @param String errMessage  错误原因
	 *
	 *  @return String 错误页面地址
	 */
	
	public String doExcepiton(String errMessage){
		
		logger.info("ExceptionPage----系统错误，系统跳转错误页面:" + errMessage);
		
		this.setAttr("imageErrMessage", "ERR MESSAGE:"+errMessage);
		
		return "/common/error/excepitonPageErr.ftl";
	
	}
	
}
