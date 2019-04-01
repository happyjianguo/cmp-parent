package com.jjb.cmp.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName T40001req
 * Company jydata-tech
 * @Description TODO
 * Author smh
 * Date 2019/3/22 11:00
 * Version 1.0
 */
public class T40001req  implements Serializable{
    private static  final long serialVersionUID=1L;
    private String org ;
    private String sys_id;
    private String image_no;
    private String operator_id;
    private String images_list;
    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getSys_id() {
        return sys_id;
    }

    public void setSys_id(String sys_id) {
        this.sys_id = sys_id;
    }

    public String getImage_no() {
        return image_no;
    }

    public void setImage_no(String image_no) {
        this.image_no = image_no;
    }

    public String getOperator_id() {
        return operator_id;
    }

    public void setOperator_id(String operator_id) {
        this.operator_id = operator_id;
    }

    public String getImages_list() {
        return images_list;
    }

    public void setImages_list(String images_list) {
        this.images_list = images_list;
    }



}
