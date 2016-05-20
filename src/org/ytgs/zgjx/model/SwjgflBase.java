package org.ytgs.zgjx.model;

public class SwjgflBase {
	private String id         ;
	private String swjgDm     ;
	private String swjgMc      ;
	private int nd     ;
	private int yf;
	private String sjSwjgDm ;
	private String sjSwjgMc ;
	private String hzcdDm     ;
	private String hzcdMc ;
	private String cls;
	private boolean expandable;
	private boolean expanded = false;
	private int limit;
    private int page;
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public boolean isExpanded() {
		return expanded;
	}
	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}
	public boolean isExpandable() {
		return expandable;
	}
	public void setExpandable(boolean expandable) {
		this.expandable = expandable;
	}
	public String getCls() {
		return cls;
	}
	public void setCls(String cls) {
		this.cls = cls;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSwjgDm() {
		return swjgDm;
	}
	public void setSwjgDm(String swjgDm) {
		this.swjgDm = swjgDm;
	}
	public String getSwjgMc() {
		return swjgMc;
	}
	public void setSwjgMc(String swjgMc) {
		this.swjgMc = swjgMc;
	}
	public int getNd() {
		return nd;
	}
	public void setNd(int nd) {
		this.nd = nd;
	}
	public int getYf() {
		return yf;
	}
	public void setYf(int yf) {
		this.yf = yf;
	}
	public String getSjSwjgDm() {
		return sjSwjgDm;
	}
	public void setSjSwjgDm(String sjSwjgDm) {
		this.sjSwjgDm = sjSwjgDm;
	}
	public String getSjSwjgMc() {
		return sjSwjgMc;
	}
	public void setSjSwjgMc(String sjSwjgMc) {
		this.sjSwjgMc = sjSwjgMc;
	}
	public String getHzcdDm() {
		return hzcdDm;
	}
	public void setHzcdDm(String hzcdDm) {
		this.hzcdDm = hzcdDm;
	}
	public String getHzcdMc() {
		return hzcdMc;
	}
	public void setHzcdMc(String hzcdMc) {
		this.hzcdMc = hzcdMc;
	}
	
	
}
