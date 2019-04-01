package com.jjb.cmp.biz.service.content;

import com.jjb.cmp.infrastructure.TmCmpMain;

/**
 * @ClassName ImageMainService
 * @Description TODO
 * @Author smh
 * Date 2018/12/31 15:23
 * Version 1.0
 */
public interface TmCmpMainService {
    TmCmpMain getTmCmpMain(TmCmpMain tmCmpMain);

    void updateTmCmpMain(TmCmpMain tmCmpMain);
}
