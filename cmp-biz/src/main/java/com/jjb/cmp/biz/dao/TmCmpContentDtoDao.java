package com.jjb.cmp.biz.dao;

import java.util.List;

import com.jjb.cmp.dto.TmCmpContentDto;
import com.jjb.unicorn.base.dao.BaseDao;
import com.jjb.unicorn.facility.model.Page;

/**
 * @ClassName TmCmpContentDtoDao
 * @Description TODO
 * @Author smh
 * Date 2018/12/31 15:26
 * Version 1.0
 */
public interface TmCmpContentDtoDao extends BaseDao<TmCmpContentDto>{
    /**
     * 查询影像展示页面
     * @param page
     * @return
     */
    Page<TmCmpContentDto> queryImageList(Page<TmCmpContentDto> page);

    /**
     * 根据条件查询内容清单
     * @param contentDto
     * @return
     */
    List<TmCmpContentDto> quyContentByParam(TmCmpContentDto contentDto);

}
