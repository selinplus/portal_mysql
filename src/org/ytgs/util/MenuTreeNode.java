package org.ytgs.util;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MenuTreeNode implements Serializable{

	private int id;            //ID 
    private String text;          //�ڵ���ʾ 
    private String cls;           //ͼ�� 
    private boolean leaf;         //�Ƿ�Ҷ�� 
    private String href="";          //���� 
    private String hrefTarget="";    //����ָ�� 
    private boolean expandable;   //�Ƿ�չ�� 
    private String description;
    private String authorityId;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthorityId() {
		return authorityId;
	}
	public void setAuthorityId(String authorityId) {
		this.authorityId = authorityId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getCls() {
		return cls;
	}
	public void setCls(String cls) {
		this.cls = cls;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getHrefTarget() {
		return hrefTarget;
	}
	public void setHrefTarget(String hrefTarget) {
		this.hrefTarget = hrefTarget;
	}
	public boolean isExpandable() {
		return expandable;
	}
	public void setExpandable(boolean expandable) {
		this.expandable = expandable;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
    
}
