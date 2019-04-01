package com.jjb.cmp.infrastructure;

import com.jjb.unicorn.facility.util.DataTypeUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 内容清单
 * @author jjb
 */
public class TmCmpContent implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String batchNo;

    private String consSysId;

    private String supType;

    private String supTypeDesc;

    private String subType;

    private String subTypeDesc;

    private String branch;

    private String contFmt;

    private Integer contSort;

    private String contRelPath;

    private String contAbsPath;

    private String contStatus;

    private Date updateDate;

    private String updateUser;

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
     * <p>内容大类型描述</p>
     */
    public String getSupTypeDesc() {
        return supTypeDesc;
    }

    /**
     * <p>内容大类型描述</p>
     */
    public void setSupTypeDesc(String supTypeDesc) {
        this.supTypeDesc = supTypeDesc;
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
     * <p>内容子类型描述</p>
     */
    public String getSubTypeDesc() {
        return subTypeDesc;
    }

    /**
     * <p>内容子类型描述</p>
     */
    public void setSubTypeDesc(String subTypeDesc) {
        this.subTypeDesc = subTypeDesc;
    }

    /**
     * <p>内容所属网点</p>
     */
    public String getBranch() {
        return branch;
    }

    /**
     * <p>内容所属网点</p>
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }

    /**
     * <p>内容格式</p>
     */
    public String getContFmt() {
        return contFmt;
    }

    /**
     * <p>内容格式</p>
     */
    public void setContFmt(String contFmt) {
        this.contFmt = contFmt;
    }

    /**
     * <p>内容排序编号</p>
     */
    public Integer getContSort() {
        return contSort;
    }

    /**
     * <p>内容排序编号</p>
     */
    public void setContSort(Integer contSort) {
        this.contSort = contSort;
    }

    /**
     * <p>内容存放相对路径</p>
     */
    public String getContRelPath() {
        return contRelPath;
    }

    /**
     * <p>内容存放相对路径</p>
     */
    public void setContRelPath(String contRelPath) {
        this.contRelPath = contRelPath;
    }

    /**
     * <p>内容存放绝对路径</p>
     */
    public String getContAbsPath() {
        return contAbsPath;
    }

    /**
     * <p>内容存放绝对路径</p>
     */
    public void setContAbsPath(String contAbsPath) {
        this.contAbsPath = contAbsPath;
    }

    /**
     * <p>内容状态</p>
     */
    public String getContStatus() {
        return contStatus;
    }

    /**
     * <p>内容状态</p>
     */
    public void setContStatus(String contStatus) {
        this.contStatus = contStatus;
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
        map.put("batchNo", batchNo);
        map.put("consSysId", consSysId);
        map.put("supType", supType);
        map.put("supTypeDesc", supTypeDesc);
        map.put("subType", subType);
        map.put("subTypeDesc", subTypeDesc);
        map.put("branch", branch);
        map.put("contFmt", contFmt);
        map.put("contSort", contSort);
        map.put("contRelPath", contRelPath);
        map.put("contAbsPath", contAbsPath);
        map.put("contStatus", contStatus);
        map.put("updateDate", updateDate);
        map.put("updateUser", updateUser);
        map.put("jpaVersion", jpaVersion);
        return map;
    }

    public void updateFromMap(Map<String, Serializable> map) {
        if (map.containsKey("id")) this.setId(DataTypeUtils.getIntegerValue(map.get("id")));
        if (map.containsKey("batchNo")) this.setBatchNo(DataTypeUtils.getStringValue(map.get("batchNo")));
        if (map.containsKey("consSysId")) this.setConsSysId(DataTypeUtils.getStringValue(map.get("consSysId")));
        if (map.containsKey("supType")) this.setSupType(DataTypeUtils.getStringValue(map.get("supType")));
        if (map.containsKey("supTypeDesc")) this.setSupTypeDesc(DataTypeUtils.getStringValue(map.get("supTypeDesc")));
        if (map.containsKey("subType")) this.setSubType(DataTypeUtils.getStringValue(map.get("subType")));
        if (map.containsKey("subTypeDesc")) this.setSubTypeDesc(DataTypeUtils.getStringValue(map.get("subTypeDesc")));
        if (map.containsKey("branch")) this.setBranch(DataTypeUtils.getStringValue(map.get("branch")));
        if (map.containsKey("contFmt")) this.setContFmt(DataTypeUtils.getStringValue(map.get("contFmt")));
        if (map.containsKey("contSort")) this.setContSort(DataTypeUtils.getIntegerValue(map.get("contSort")));
        if (map.containsKey("contRelPath")) this.setContRelPath(DataTypeUtils.getStringValue(map.get("contRelPath")));
        if (map.containsKey("contAbsPath")) this.setContAbsPath(DataTypeUtils.getStringValue(map.get("contAbsPath")));
        if (map.containsKey("contStatus")) this.setContStatus(DataTypeUtils.getStringValue(map.get("contStatus")));
        if (map.containsKey("updateDate")) this.setUpdateDate(DataTypeUtils.getDateValue(map.get("updateDate")));
        if (map.containsKey("updateUser")) this.setUpdateUser(DataTypeUtils.getStringValue(map.get("updateUser")));
        if (map.containsKey("jpaVersion")) this.setJpaVersion(DataTypeUtils.getIntegerValue(map.get("jpaVersion")));
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode())+"["+
        "serialVersionUID="+serialVersionUID+
        ", id="+id+
        "id="+id+
        ", batchNo="+batchNo+
        "batchNo="+batchNo+
        ", consSysId="+consSysId+
        "consSysId="+consSysId+
        ", supType="+supType+
        "supType="+supType+
        ", supTypeDesc="+supTypeDesc+
        "supTypeDesc="+supTypeDesc+
        ", subType="+subType+
        "subType="+subType+
        ", subTypeDesc="+subTypeDesc+
        "subTypeDesc="+subTypeDesc+
        ", branch="+branch+
        "branch="+branch+
        ", contFmt="+contFmt+
        "contFmt="+contFmt+
        ", contSort="+contSort+
        "contSort="+contSort+
        ", contRelPath="+contRelPath+
        "contRelPath="+contRelPath+
        ", contAbsPath="+contAbsPath+
        "contAbsPath="+contAbsPath+
        ", contStatus="+contStatus+
        "contStatus="+contStatus+
        ", updateDate="+updateDate+
        "updateDate="+updateDate+
        ", updateUser="+updateUser+
        "updateUser="+updateUser+
        ", jpaVersion="+jpaVersion+
        "jpaVersion="+jpaVersion+
        "]";
    }

    public void fillDefaultValues() {
        if (batchNo == null) batchNo = "";
        if (consSysId == null) consSysId = "";
        if (supType == null) supType = "";
        if (supTypeDesc == null) supTypeDesc = "";
        if (subType == null) subType = "";
        if (subTypeDesc == null) subTypeDesc = "";
        if (branch == null) branch = "";
        if (contFmt == null) contFmt = "";
        if (contSort == null) contSort = 0;
        if (contRelPath == null) contRelPath = "";
        if (contAbsPath == null) contAbsPath = "";
        if (contStatus == null) contStatus = "";
        if (updateDate == null) updateDate = new Date();
        if (updateUser == null) updateUser = "";
        if (jpaVersion == null) jpaVersion = 0;
    }
}