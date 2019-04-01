package com.jjb.cmp.biz.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.jjb.cmp.biz.dao.TmCmpMainDao;
import com.jjb.cmp.infrastructure.TmCmpMain;
import com.jjb.unicorn.base.dao.impl.AbstractBaseDao;
import com.jjb.unicorn.facility.exception.ProcessException;
import com.jjb.unicorn.facility.util.CollectionUtils;

/**
 * @ClassName TmCmpMainDaoImpl
 * @Description TODO
 * @Author smh
 * Date 2018/12/27 20:06
 * Version 1.0
 */
@Repository("tmCmpMainDao")
public class TmCmpMainDaoImpl extends AbstractBaseDao<TmCmpMain> implements TmCmpMainDao {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取内容管理主表信息
     *
     * @param tmCmpMain
     * @return
     */
    @Override
    public TmCmpMain getTmCmpMain(TmCmpMain tmCmpMain) {
        if (tmCmpMain == null) {
            logger.info("请求参数异常");
            throw new ProcessException("请求参数异常");
        }
        List<TmCmpMain> cmpMainList = queryForList(tmCmpMain);
        if (CollectionUtils.isNotEmpty(cmpMainList) && cmpMainList.size() > 0) {
            return cmpMainList.get(0);
        }
        return null;
    }

    @Override
    public void updateTmCmpMain(TmCmpMain tmCmpMain) {
        update(tmCmpMain);
    }

    @Override
    public void saveTmCmpMain(TmCmpMain tmCmpMain) {
        save(tmCmpMain);
    }
}
