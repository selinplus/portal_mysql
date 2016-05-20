package org.ytgs.zgjx.model;

import java.math.BigDecimal;

public class DmHyAvgsf {
    private String id;

    private String hyId;

    private BigDecimal avgsfZzs;

    private BigDecimal avgsfSds;

    private String deptDm;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getHyId() {
        return hyId;
    }

    public void setHyId(String hyId) {
        this.hyId = hyId == null ? null : hyId.trim();
    }

    public BigDecimal getAvgsfZzs() {
        return avgsfZzs;
    }

    public void setAvgsfZzs(BigDecimal avgsfZzs) {
        this.avgsfZzs = avgsfZzs;
    }

    public BigDecimal getAvgsfSds() {
        return avgsfSds;
    }

    public void setAvgsfSds(BigDecimal avgsfSds) {
        this.avgsfSds = avgsfSds;
    }

    public String getDeptDm() {
        return deptDm;
    }

    public void setDeptDm(String deptDm) {
        this.deptDm = deptDm == null ? null : deptDm.trim();
    }
}