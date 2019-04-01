package com.jjb.cmp.dto;

import java.io.Serializable;

/**
 * @ClassName T40000
 * Company jydata-tech
 * @Description TODO
 * Author smh
 * Date 2019/3/22 10:51
 * Version 1.0
 */
public class T40000req implements Serializable{
    private static  final long serialVersionUID=1L;
    private String org ;
    private String id_type;
    private String id_no;
    private String name;
    private String sys_id;
    private String operator_id;
    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
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

    public String getOperator_id() {
        return operator_id;
    }

    public void setOperator_id(String operator_id) {
        this.operator_id = operator_id;
    }


}
