package org.ytgs.portal.common.model;

import org.springframework.stereotype.Component;

@Component
public class Catagory {
	private int id;
	private int parentId;
	private String catagoryName;
	private String catagoryType;
	private String catagoryUrl;
	private String pubType;
	private String pubTime; 
	private String updateTime;
	private String catagoryDesc;
	private String jfXx;
	private String jfTp;
	private String bzQy;
	private String bzSh; 
	private String bzTj; 
	private String pageModal;
	private String articleModal;
	private String deptId;
	private String deptName;
	private String topicPic;
	private String deptDesc;
	private String catagoryPic;	
	private String pubUrl;		
	public String getCatagoryPic() {
		return catagoryPic;
	}
	public void setCatagoryPic(String catagoryPic) {
		this.catagoryPic = catagoryPic;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getCatagoryName() {
		return catagoryName;
	}
	public void setCatagoryName(String catagoryName) {
		this.catagoryName = catagoryName;
	}
	public String getCatagoryType() {
		return catagoryType;
	}
	public void setCatagoryType(String catagoryType) {
		this.catagoryType = catagoryType;
	}
	public String getCatagoryUrl() {
		return catagoryUrl;
	}
	public void setCatagoryUrl(String catagoryUrl) {
		this.catagoryUrl = catagoryUrl;
	}
	public String getPubType() {
		return pubType;
	}
	public void setPubType(String pubType) {
		this.pubType = pubType;
	}
	public String getPubTime() {
		return pubTime;
	}
	public void setPubTime(String pubTime) {
		this.pubTime = pubTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getCatagoryDesc() {
		return catagoryDesc;
	}
	public void setCatagoryDesc(String catagoryDesc) {
		this.catagoryDesc = catagoryDesc;
	}
	public String getJfXx() {
		return jfXx;
	}
	public void setJfXx(String jfXx) {
		this.jfXx = jfXx;
	}
	public String getJfTp() {
		return jfTp;
	}
	public void setJfTp(String jfTp) {
		this.jfTp = jfTp;
	}
	public String getBzQy() {
		return bzQy;
	}
	public void setBzQy(String bzQy) {
		this.bzQy = bzQy;
	}
	public String getBzSh() {
		return bzSh;
	}
	public void setBzSh(String bzSh) {
		this.bzSh = bzSh;
	}
	public String getBzTj() {
		return bzTj;
	}
	public void setBzTj(String bzTj) {
		this.bzTj = bzTj;
	}
	public String getPageModal() {
		return pageModal;
	}
	public void setPageModal(String pageModal) {
		this.pageModal = pageModal;
	}
	public String getArticleModal() {
		return articleModal;
	}
	public void setArticleModal(String articleModal) {
		this.articleModal = articleModal;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getTopicPic() {
		return topicPic;
	}
	public void setTopicPic(String topicPic) {
		this.topicPic = topicPic;
	}
	public String getDeptDesc() {
		return deptDesc;
	}
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}
	public String getPubUrl() {
		return pubUrl;
	}
	public void setPubUrl(String pubUrl) {
		this.pubUrl = pubUrl;
	}
	
	
}
