package com.jjb.cmp.biz.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jjb.cmp.biz.dao.TmCmpContentDtoDao;
import com.jjb.cmp.dto.TmCmpContentDto;
import com.jjb.unicorn.base.dao.impl.AbstractBaseDao;
import com.jjb.unicorn.facility.model.Page;
import com.jjb.unicorn.facility.model.Query;
import com.jjb.unicorn.facility.util.StringUtils;

/**
 * @ClassName TmCmpContentDtoDaoImpl
 * @Description TODO
 * @Author smh
 * Date 2018/12/31 15:28
 * Version 1.0
 */
@Repository("tmCmpContentDtoDao")
public class TmCmpContentDtoDaoImpl extends AbstractBaseDao<TmCmpContentDto> implements TmCmpContentDtoDao {
    public static final String selectAll = "com.jjb.cmp.biz.TmCmpContentDtoMapper.selectAll";//查询影像信息列表
    public static final String quyContentByParam = "com.jjb.cmp.biz.TmCmpContentDtoMapper.quyContentByParam";//查询影像信息列表

    @Override
    public Page<TmCmpContentDto> queryImageList(Page<TmCmpContentDto> page) {
        if (null == page.getQuery()) {
            page.setQuery(new Query());
        }
        Page<TmCmpContentDto> p = queryForPageList(selectAll, page.getQuery(), page);
        return p;
    }

    /**
     * 根据条件查询内容清单
     *
     * @param contentDto
     * @return
     */
    @Override
    public List<TmCmpContentDto> quyContentByParam(TmCmpContentDto contentDto) {
        List<TmCmpContentDto> list = null;
        if (contentDto != null) {
            list = queryForList(quyContentByParam, contentDto);
            if (list.size() > 0 && list != null) {
                for (TmCmpContentDto dto : list) {
                    if (dto != null && StringUtils.isNotEmpty(String.valueOf(dto.getUpdateDate()))) {
                        Date date = dto.getUpdateDate();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        String formatDate = dateFormat.format(date);
                        dto.setFormatDate(formatDate);
                    }
                }
            }
        }
        return list;
    }

}

