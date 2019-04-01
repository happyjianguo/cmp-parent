package com.jjb.cmp.biz.service.param.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jjb.cmp.biz.cache.controller.CmpCacheContext;
import com.jjb.cmp.biz.service.param.ParameterRefreshService;

@Service("parameterRefreshService")
public class ParameterRefreshServiceImpl implements ParameterRefreshService{

//	@Resource
//	private RabbitTemplate ecmsParameterRefreshExchange;
	@Autowired
	private CmpCacheContext cmpCacheContext;
	/**
	 * 初始化所有的参数缓存
	 */
	@Override
	@Transactional
	public void refreshParam(){
//		ecmsParameterRefreshExchange.convertAndSend(new Object());
		cmpCacheContext.refresh();
	}
}
