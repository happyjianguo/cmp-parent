package com.jjb.cmp.infrastructure;

import com.jjb.unicorn.facility.util.DataTypeUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 内容管理主表信息
 * @author jjb
 */
public class TmCmpMain implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String org;

    private String name;

    private String idNo;

    private String idType;

    private String batchNo;

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
     * <p>客户姓名</p>
     */
    public String getName() {
        return name;
    }

    /**
     * <p>客户姓名</p>
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * <p>客户证件</p>
     */
    public String getIdNo() {
        return idNo;
    }

    /**
     * <p>客户证件</p>
     */
    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    /**
     * <p>证件类型</p>
     */
    public String getIdType() {
        return idType;
    }

    /**
     * <p>证件类型</p>
     */
    public void setIdType(String idType) {
        this.idType = idType;
    }

    /**
     * <p>内容批次号</p>
     */
    public String getBatchNo() {
        return batchNo;
    }

    /**
     * <p>内容批次号</p>
     */
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
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
        map.put("name", name);
        map.put("idNo", idNo);
        map.put("idType", idType);
        map.put("batchNo", batchNo);
        map.put("updateUser", updateUser);
        map.put("updateDate", updateDate);
        map.put("jpaVersion", jpaVersion);
        return map;
    }

    public void updateFromMap(Map<String, Serializable> map) {
        if (map.containsKey("id")) this.setId(DataTypeUtils.getIntegerValue(map.get("id")));
        if (map.containsKey("org")) this.setOrg(DataTypeUtils.getStringValue(map.get("org")));
        if (map.containsKey("name")) this.setName(DataTypeUtils.getStringValue(map.get("name")));
        if (map.containsKey("idNo")) this.setIdNo(DataTypeUtils.getStringValue(map.get("idNo")));
        if (map.containsKey("idType")) this.setIdType(DataTypeUtils.getStringValue(map.get("idType")));
        if (map.containsKey("batchNo")) this.setBatchNo(DataTypeUtils.getStringValue(map.get("batchNo")));
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
        ", name="+name+
        "name="+name+
        ", idNo="+idNo+
        "idNo="+idNo+
        ", idType="+idType+
        "idType="+idType+
        ", batchNo="+batchNo+
        "batchNo="+batchNo+
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
        if (name == null) name = "";
        if (idNo == null) idNo = "";
        if (idType == null) idType = "";
        if (batchNo == null) batchNo = "";
        if (updateUser == null) updateUser = "";
        if (updateDate == null) updateDate = new Date();
        if (jpaVersion == null) jpaVersion = 0;
    }
}