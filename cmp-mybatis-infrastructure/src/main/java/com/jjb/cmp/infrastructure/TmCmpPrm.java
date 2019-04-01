package com.jjb.cmp.infrastructure;

import com.jjb.unicorn.facility.util.DataTypeUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 内容管理参数表
 * @author jjb
 */
public class TmCmpPrm implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String org;

    private String consSysId;

    private String supType;

    private String subType;

    private String updateUser;

    private Date updateDate;

    private Integer jpaVersion;

    /**
     * <p>主键</p>
     */
    public Integer getId() {
        return id;
    }

    /**
     * <p>主键</p>
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * <p>行机构号</p>
     */
    public String getOrg() {
        return org;
    }

    /**
     * <p>行机构号</p>
     */
    public void setOrg(String org) {
        this.org = org;
    }

    /**
     * <p>消费系统ID</p>
     */
    public String getConsSysId() {
        return consSysId;
    }

    /**
     * <p>消费系统ID</p>
     */
    public void setConsSysId(String consSysId) {
        this.consSysId = consSysId;
    }

    /**
     * <p>内容大类型</p>
     */
    public String getSupType() {
        return supType;
    }

    /**
     * <p>内容大类型</p>
     */
    public void setSupType(String supType) {
        this.supType = supType;
    }

    /**
     * <p>内容子类型</p>
     */
    public String getSubType() {
        return subType;
    }

    /**
     * <p>内容子类型</p>
     */
    public void setSubType(String subType) {
        this.subType = subType;
    }

    /**
     * <p>维护人</p>
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * <p>维护人</p>
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * <p>维护时间</p>
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * <p>维护时间</p>
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * <p>JPA版本</p>
     */
    public Integer getJpaVersion() {
        return jpaVersion;
    }

    /**
     * <p>JPA版本</p>
     */
    public void setJpaVersion(Integer jpaVersion) {
        this.jpaVersion = jpaVersion;
    }

    public Map<String, Serializable> convertToMap() {
        HashMap<String, Serializable> map = new HashMap<String, Serializable>();
        map.put("id", id);
        map.put("org", org);
        map.put("consSysId", consSysId);
        map.put("supType", supType);
        map.put("subType", subType);
        map.put("updateUser", updateUser);
        map.put("updateDate", updateDate);
        map.put("jpaVersion", jpaVersion);
        return map;
    }

    public void updateFromMap(Map<String, Serializable> map) {
        if (map.containsKey("id")) this.setId(DataTypeUtils.getIntegerValue(map.get("id")));
        if (map.containsKey("org")) this.setOrg(DataTypeUtils.getStringValue(map.get("org")));
        if (map.containsKey("consSysId")) this.setConsSysId(DataTypeUtils.getStringValue(map.get("consSysId")));
        if (map.containsKey("supType")) this.setSupType(DataTypeUtils.getStringValue(map.get("supType")));
        if (map.containsKey("subType")) this.setSubType(DataTypeUtils.getStringValue(map.get("subType")));
        if (map.containsKey("updateUser")) this.setUpdateUser(DataTypeUtils.getStringValue(map.get("updateUser")));
        if (map.containsKey("updateDate")) this.setUpdateDate(DataTypeUtils.getDateValue(map.get("updateDate")));
        if (map.containsKey("jpaVersion")) this.setJpaVersion(DataTypeUtils.getIntegerValue(map.get("jpaVersion")));
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode())+"["+
        "serialVersionUID="+serialVersionUID+
        ", id="+id+
        "id="+id+
        ", org="+org+
        "org="+org+
        ", consSysId="+consSysId+
        "consSysId="+consSysId+
        ", supType="+supType+
        "supType="+supType+
        ", subType="+subType+
        "subType="+subType+
        ", updateUser="+updateUser+
        "updateUser="+updateUser+
        ", updateDate="+updateDate+
        "updateDate="+updateDate+
        ", jpaVersion="+jpaVersion+
        "jpaVersion="+jpaVersion+
        "]";
    }

    public void fillDefaultValues() {
        if (org == null) org = "";
        if (consSysId == null) consSysId = "";
        if (supType == null) supType = "";
        if (subType == null) subType = "";
        if (updateUser == null) updateUser = "";
        if (updateDate == null) updateDate = new Date();
        if (jpaVersion == null) jpaVersion = 0;
    }
}