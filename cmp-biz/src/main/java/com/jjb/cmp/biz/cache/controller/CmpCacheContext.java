package com.jjb.cmp.biz.cache.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jjb.acl.access.service.AccessDictService;
import com.jjb.acl.infrastructure.TmAclDict;

/**
 * 缓存处理
 * 
 * @author hp
 *
 */
@Component
public class CmpCacheContext implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private AccessDictService accessDictService;

	/**
	 * 通过字典类型查询业务字典列表
	 * 
	 * @param type
	 * @return
	 */
	public List<TmAclDict> getAclDictByType(String type) {
		return accessDictService.getByType(type);
	}

	/**
	 * 通过业务字典代码查询单条业务字典数据
	 * 
	 * @param type
	 * @return
	 */
	public TmAclDict getAclDictByCode(String type, String code) {
		return accessDictService.get(type, code);
	}

	/**
	 * 刷新系统全部缓存
	 * 
	 * @see com.jjb.acl.cache.InnerCache#refresh()
	 */
	public void refresh() {
		accessDictService.initAclDictParm();
	}
}
