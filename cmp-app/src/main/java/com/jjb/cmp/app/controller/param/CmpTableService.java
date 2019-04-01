package com.jjb.cmp.biz.service.param;

import com.jjb.acl.biz.dao.TmAclDictDao;
import com.jjb.acl.infrastructure.TmAclDict;
import com.jjb.unicorn.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CmpTableService
 * @Description TODO
 * @Author smh
 * Date 2019/2/18 17:35
 * Version 1.0
 */
@Service
public class CmpTableService extends BaseService {
    @Autowired
    private TmAclDictDao tmAclDictDao;
    public CmpTableService(){

    }
    public List getTable (String sqlId, Map<String,Object> map){
        return  super.queryForList(sqlId,map);
    }
    public TmAclDict getCodeName(String type, String code){
        return   tmAclDictDao.getDictByType(type,code);
    }
}