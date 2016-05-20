package org.ytgs.zgjx.model;

public class DmHy {
    private String id;

    private String hyDm;

    private String hyMc;

    private String parentDm;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getHyDm() {
        return hyDm;
    }

    public void setHyDm(String hyDm) {
        this.hyDm = hyDm == null ? null : hyDm.trim();
    }

    public String getHyMc() {
        return hyMc;
    }

    public void setHyMc(String hyMc) {
        this.hyMc = hyMc == null ? null : hyMc.trim();
    }

    public String getParentDm() {
        return parentDm;
    }

    public void setParentDm(String parentDm) {
        this.parentDm = parentDm == null ? null : parentDm.trim();
    }
}