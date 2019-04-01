package com.jjb.cmp.biz.dao;

import com.jjb.cmp.infrastructure.TmCmpSeq;
import com.jjb.unicorn.base.dao.BaseDao;

/**
  *@ClassName TmCmpSeqDao
  *Company jydata-tech
  *@Description TODO
  *Author smh
  *Date 2019/3/25 15:12
  *Version 1.0
  */
public interface TmCmpSeqDao extends BaseDao<TmCmpSeq> {
    public TmCmpSeq getTmCmpSeq(TmCmpSeq tmCmpSeq);

    public String getSeqNo(String org);
}
