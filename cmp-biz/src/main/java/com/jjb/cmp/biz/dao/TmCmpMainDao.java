package com.jjb.cmp.biz.dao;

import com.jjb.cmp.infrastructure.TmCmpMain;
import com.jjb.unicorn.base.dao.BaseDao;

/**
 * @ClassName TmCmpMainDao
 * @Description TODO
 * @Author smh
 * Date 2018/12/27 17:58
 * Version 1.0
 */
public interface TmCmpMainDao extends BaseDao<TmCmpMain> {
    /**
     * 获取内容管理主表信息
     * @param tmCmpMain
     * @return
     */
    public  TmCmpMain getTmCmpMain(TmCmpMain tmCmpMain);

    public void updateTmCmpMain(TmCmpMain tmCmpMain);

    public void saveTmCmpMain(TmCmpMain tmCmpMain);

}
