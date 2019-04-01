package com.jjb.cmp.biz.service.content.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jjb.cmp.biz.dao.TmCmpMainDao;
import com.jjb.cmp.biz.service.content.TmCmpMainService;
import com.jjb.cmp.infrastructure.TmCmpMain;

/**
 * @ClassName ImageMainServiceImpl
 * @Description TODO
 * @Author smh
 * Date 2018/12/31 15:24
 * Version 1.0
 */
@Service("tmCmpMainService")
public class TmCmpMainServiceImpl implements TmCmpMainService{
    @Autowired
    private  TmCmpMainDao tmCmpMainDao;
    @Override
    public TmCmpMain getTmCmpMain(TmCmpMain tmCmpMain) {
        return tmCmpMainDao.getTmCmpMain(tmCmpMain);
    }

    @Override
    public void updateTmCmpMain(TmCmpMain tmCmpMain) {
        tmCmpMainDao.updateTmCmpMain(tmCmpMain);
    }
}
