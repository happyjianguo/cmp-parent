package com.jjb.cmp.biz.service.content;

import com.jjb.cmp.infrastructure.TmCmpSeq;

/**
  *@ClassName ImageSeqService
  *Company jydata-tech
  *@Description TODO
  *Author smh
  *Date 2019/3/25 15:10
  *Version 1.0
  */
public interface TmCmpSeqService {
    public TmCmpSeq getTmCmpSeq(TmCmpSeq tmCmpSeq);
    public String getSeqNo(String org);
}
