package com.jjb.cmp.dto;

import java.io.Serializable;

/**
 * @ClassName T400010req
 * Company jydata-tech
 * @Description TODO
 * Author smh
 * Date 2019/3/26 11:59
 * Version 1.0
 */
public class T400010req implements Serializable{
    private static  final long serialVersionUID=1L;
    private String sup_type;
    private String sub_type;
    private String branch_code;
    private String upload_sys_id;
    private String format;
    private String content; //经过base64编码的图片的数据

    public String getSup_type() {
        return sup_type;
    }

    public void setSup_type(String sup_type) {
        this.sup_type = sup_type;
    }

    public String getSub_type() {
        return sub_type;
    }

    public void setSub_type(String sub_type) {
        this.sub_type = sub_type;
    }

    public String getBranch_code() {
        return branch_code;
    }

    public void setBranch_code(String branch_code) {
        this.branch_code = branch_code;
    }

    public String getUpload_sys_id() {
        return upload_sys_id;
    }

    public void setUpload_sys_id(String upload_sys_id) {
        this.upload_sys_id = upload_sys_id;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
