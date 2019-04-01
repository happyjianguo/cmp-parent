package com.jjb.cmp.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName TmCmpContentDto
 * @Description TODO  内容清单模型
 * @Author smh
 * Date 2018/12/31 11:39
 * Version 1.0
 */
public class TmCmpContentDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;  //客户姓名
    private String idType;//证件类型
    private String idNo;       //客户证件号
    private String batchNo; //批次号
    private String consSysId; //消费系统Id
    private String supType;//大类型
    private String supTypeDesc;//大类型描述
    private String subType;//小类型
    private String subTypeDesc;//小类型描述
    private String branch;//上传操作的机构网点
    private String contFmt;//内容格式
    private Integer contSort;//内容排序
    private String contRelPath;
    private String contAbsPath;
    private String contStatus;//内容状态
    private Date updateDate; //维护时间
    private String updateUser; // 维护人
    private String formatDate;//格式话之后的时间
    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }


    public String getFormatDate() {
        return formatDate;
    }

    public void setFormatDate(String formatDate) {
        this.formatDate = formatDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getConsSysId() {
        return consSysId;
    }

    public void setConsSysId(String consSysId) {
        this.consSysId = consSysId;
    }

    public String getSupType() {
        return supType;
    }

    public void setSupType(String supType) {
        this.supType = supType;
    }

    public String getSupTypeDesc() {
        return supTypeDesc;
    }

    public void setSupTypeDesc(String supTypeDesc) {
        this.supTypeDesc = supTypeDesc;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getSubTypeDesc() {
        return subTypeDesc;
    }

    public void setSubTypeDesc(String subTypeDesc) {
        this.subTypeDesc = subTypeDesc;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getContFmt() {
        return contFmt;
    }

    public void setContFmt(String contFmt) {
        this.contFmt = contFmt;
    }

    public Integer getContSort() {
        return contSort;
    }

    public void setContSort(Integer contSort) {
        this.contSort = contSort;
    }

    public String getContRelPath() {
        return contRelPath;
    }

    public void setContRelPath(String contRelPath) {
        this.contRelPath = contRelPath;
    }

    public String getContAbsPath() {
        return contAbsPath;
    }

    public void setContAbsPath(String contAbsPath) {
        this.contAbsPath = contAbsPath;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
    public String getContStatus() {
        return contStatus;
    }

    public void setContStatus(String contStatus) {
        this.contStatus = contStatus;
    }

}
