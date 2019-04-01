package com.jjb.cmp.biz.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.jjb.cmp.biz.dao.TmCmpSeqDao;
import com.jjb.cmp.infrastructure.TmCmpSeq;
import com.jjb.unicorn.base.dao.impl.AbstractBaseDao;
import com.jjb.unicorn.facility.exception.ProcessException;
import com.jjb.unicorn.facility.util.CollectionUtils;
import com.jjb.unicorn.facility.util.StringUtils;

/**
 * @ClassName TmCmpSeqDaoImpl
 * Company jydata-tech
 * @Description TODO
 * Author smh
 * Date 2019/3/25 15:14
 * Version 1.0
 */
@Repository("tmCmpSeqDao")
public class TmCmpSeqDaoImpl extends AbstractBaseDao<TmCmpSeq> implements  TmCmpSeqDao{
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public TmCmpSeq getTmCmpSeq(TmCmpSeq tmCmpSeq) {
        if (tmCmpSeq == null) {
            logger.info("请求参数异常");
            throw new ProcessException("请求参数异常");
        }
        List<TmCmpSeq> tmCmpSeqList = queryForList(tmCmpSeq);
        if (CollectionUtils.isNotEmpty(tmCmpSeqList) && tmCmpSeqList.size() > 0) {
            return tmCmpSeqList.get(0);
        }
        return null;
    }

    @Override
    public String getSeqNo(String org) {
        TmCmpSeq tmCmpSeq = new TmCmpSeq();
        tmCmpSeq.setOrg(org);
        save(tmCmpSeq);
        String seqNo = StringUtils.valueOf(tmCmpSeq.getSeq());
        return seqNo;
    }

}
