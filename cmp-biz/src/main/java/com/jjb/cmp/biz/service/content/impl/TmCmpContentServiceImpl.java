package com.jjb.cmp.biz.service.content.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jjb.cmp.biz.dao.TmCmpContentDao;
import com.jjb.cmp.biz.dao.TmCmpContentDtoDao;
import com.jjb.cmp.biz.service.content.TmCmpContentService;
import com.jjb.cmp.dto.TmCmpContentDto;
import com.jjb.cmp.infrastructure.TmCmpContent;
import com.jjb.unicorn.facility.model.Page;

/**
 * @ClassName ImageContentServiceImpl
 * @Description TODO
 * @Author smh Date 2018/12/31 11:57 Version 1.0
 */
@Service("tmCmpContentService")
public class TmCmpContentServiceImpl implements TmCmpContentService {
	@Autowired
	private TmCmpContentDao tmCmpContentDao;
	@Autowired
	private TmCmpContentDtoDao tmCmpContentDtoDao;

	@Override
	public Page<TmCmpContentDto> queryImageList(Page<TmCmpContentDto> page) {
		page = tmCmpContentDtoDao.queryImageList(page);
		return page;
	}

	/**
	 * 根据条件查询内容清单
	 *
	 * @param contentDto
	 * @return
	 */
	@Override
	public List<TmCmpContentDto> quyContentByParam(TmCmpContentDto contentDto) {
		return tmCmpContentDtoDao.quyContentByParam(contentDto);
	}

	@Override
	public List<TmCmpContent> getTmCmpContentByBatchNo(String batchNo) {
		return tmCmpContentDao.getTmCmpContentByBatchNo(batchNo);
	}

	@Override
	public TmCmpContent getTmCmpContent(TmCmpContent tmCmpContent) {
		return tmCmpContentDao.getTmCmpContent(tmCmpContent);
	}

	@Override
	public void updateTmCmpContent(TmCmpContent tmCmpContent) {
		tmCmpContentDao.updateTmCmpContent(tmCmpContent);
	}

	@Override
	public void saveTmCmpContent(TmCmpContent tmCmpContent) {
		tmCmpContentDao.saveTmCmpContent(tmCmpContent);
	}

	@Override
	public List<TmCmpContent> getTmCmpContents(TmCmpContent tmCmpContent) {
		return tmCmpContentDao.getTmCmpContents(tmCmpContent);
	}

}
