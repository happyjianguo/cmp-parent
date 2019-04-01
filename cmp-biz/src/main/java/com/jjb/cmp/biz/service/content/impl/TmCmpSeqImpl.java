package com.jjb.cmp.biz.service.content.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jjb.cmp.biz.dao.TmCmpSeqDao;
import com.jjb.cmp.biz.service.content.TmCmpSeqService;
import com.jjb.cmp.infrastructure.TmCmpSeq;

/**
 * @ClassName ImageSeqServiceImpl
 * Company jydata-tech
 * @Description TODO
 * Author smh
 * Date 2019/3/25 15:10
 * Version 1.0
 */
@Service("tmCmpSeqService")
public class TmCmpSeqImpl implements TmCmpSeqService{
    @Autowired
    private TmCmpSeqDao tmCmpSeqDao;
    @Override
    public TmCmpSeq getTmCmpSeq(TmCmpSeq tmCmpSeq) {
       return tmCmpSeqDao.getTmCmpSeq(tmCmpSeq);
    }

    @Override
    public String getSeqNo(String org) {
        String seqNo = tmCmpSeqDao.getSeqNo(org);
        return seqNo;
    }
}
