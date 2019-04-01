package com.jjb.cmp.dto;

/**
 * @ClassName PicList
 * Company jydata-tech
 * @Description TODO
 * Author smh
 * Date 2019/3/22 11:05
 * Version 1.0
 */
public class PicList {
    private String supType;
    private String supTypeDesc;
    private String subType;
    private String subTypeDesc;
    private String uploadTime;
    private String uploadSysId;
    private String imageContSort;//该批次内容序号

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

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getUploadSysId() {
        return uploadSysId;
    }

    public void setUploadSysId(String uploadSysId) {
        this.uploadSysId = uploadSysId;
    }

    public String getImageContSort() {
        return imageContSort;
    }

    public void setImageContSort(String imageContSort) {
        this.imageContSort = imageContSort;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getContAbsPath() {
        return contAbsPath;
    }

    public void setContAbsPath(String contAbsPath) {
        this.contAbsPath = contAbsPath;
    }

    public String getImageContFmt() {
        return imageContFmt;
    }

    public void setImageContFmt(String imageContFmt) {
        this.imageContFmt = imageContFmt;
    }

    private String branchCode;
    private String contAbsPath;//上传的相对路径
    private String imageContFmt;//内容格式  如 .jpg


}
