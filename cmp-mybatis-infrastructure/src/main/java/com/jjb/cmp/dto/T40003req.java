package com.jjb.cmp.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName T40003req
 * Company jydata-tech
 * @Description TODO
 * Author smh
 * Date 2019/3/21 15:01
 * Version 1.0
 */
public class T40003req implements Serializable {
    private static  final long serialVersionUID=1L;
    private String org ;
    private String branch_code;
    private String id_type;
    private String id_no;
    private String name;
    private String sys_id;
    private String image_no;
    private List<String> sys_array=new ArrayList<String>();
    private List<String> type_array=new ArrayList<String>();
    private String operator_id;

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getBranch_code() {
        return branch_code;
    }

    public void setBranch_code(String branch_code) {
        this.branch_code = branch_code;
    }

    public String getId_type() {
        return id_type;
    }

    public void setId_type(String id_type) {
        this.id_type = id_type;
    }

    public String getId_no() {
        return id_no;
    }

    public void setId_no(String id_no) {
        this.id_no = id_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<String> getSys_array() {
        return sys_array;
    }

    public void setSys_array(List<String> sys_array) {
        this.sys_array = sys_array;
    }

    public List<String> getType_array() {
        return type_array;
    }

    public void setType_array(List<String> type_array) {
        this.type_array = type_array;
    }

    public String getOperator_id() {
        return operator_id;
    }

    public void setOperator_id(String operator_id) {
        this.operator_id = operator_id;
    }

}
