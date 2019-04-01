package com.jjb.cmp.infrastructure;

import com.jjb.unicorn.facility.util.DataTypeUtils;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 内容平台批次序号
 * @author jjb
 */
public class TmCmpSeq implements Serializable {
    private static final long serialVersionUID = 1L;

    private String org;

    private Integer seq;

    private Integer jpaVersion;

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
     * <p>自增序号</p>
     */
    public Integer getSeq() {
        return seq;
    }

    /**
     * <p>自增序号</p>
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
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
        map.put("org", org);
        map.put("seq", seq);
        map.put("jpaVersion", jpaVersion);
        return map;
    }

    public void updateFromMap(Map<String, Serializable> map) {
        if (map.containsKey("org")) this.setOrg(DataTypeUtils.getStringValue(map.get("org")));
        if (map.containsKey("seq")) this.setSeq(DataTypeUtils.getIntegerValue(map.get("seq")));
        if (map.containsKey("jpaVersion")) this.setJpaVersion(DataTypeUtils.getIntegerValue(map.get("jpaVersion")));
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode())+"["+
        "serialVersionUID="+serialVersionUID+
        ", org="+org+
        "org="+org+
        ", seq="+seq+
        "seq="+seq+
        ", jpaVersion="+jpaVersion+
        "jpaVersion="+jpaVersion+
        "]";
    }

    public void fillDefaultValues() {
        if (org == null) org = "";
        if (jpaVersion == null) jpaVersion = 0;
    }
}