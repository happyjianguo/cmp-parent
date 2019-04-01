package com.jjb.cmp.biz.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.jjb.cmp.biz.dao.TmCmpContentDao;
import com.jjb.cmp.infrastructure.TmCmpContent;
import com.jjb.unicorn.base.dao.impl.AbstractBaseDao;
import com.jjb.unicorn.facility.util.CollectionUtils;

/**
 * @ClassName TmCmpContentDaoImpl
 * @Description TODO
 * @Author smh
 * Date 2018/12/27 20:34
 * Version 1.0
 */
@Repository("tmCmpContentDao")
public class TmCmpContentDaoImpl extends AbstractBaseDao<TmCmpContent> implements TmCmpContentDao {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<TmCmpContent> getTmCmpContentByBatchNo(String batchNo) {
        TmCmpContent tmCmpContent = new TmCmpContent();
        tmCmpContent.setBatchNo(batchNo);
        //查询的是有效的影像资料
/*
        tmCmpContent.setContStatus("valid");
*/
        List<TmCmpContent> tmCmpContentList = queryForList(tmCmpContent);
        if (CollectionUtils.isNotEmpty(tmCmpContentList)) {
            return tmCmpContentList;
        }
        return null;
    }

    @Override
    public TmCmpContent getTmCmpContent(TmCmpContent tmCmpContent) {
        List<TmCmpContent> tmCmpContentList = queryForList(tmCmpContent);
        if (CollectionUtils.isNotEmpty(tmCmpContentList)) {
            return tmCmpContentList.get(0);
        }
        return null;
    }

    @Override
    public void updateTmCmpContent(TmCmpContent tmCmpContent) {
        update(tmCmpContent);
    }

    @Override
    public void saveTmCmpContent(TmCmpContent tmCmpContent) {
        save(tmCmpContent);
    }

    @Override
    public List<TmCmpContent> getTmCmpContents(TmCmpContent tmCmpContent) {
        List<TmCmpContent> tmCmpContentList = queryForList(tmCmpContent);
        if (CollectionUtils.isNotEmpty(tmCmpContentList)) {
            return tmCmpContentList;
        }
        return null;
    }


}
