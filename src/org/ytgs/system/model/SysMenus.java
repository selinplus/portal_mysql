package org.ytgs.system.model;

import org.springframework.stereotype.Component;

@SuppressWarnings("serial")
@Component
public class SysMenus implements java.io.Serializable{

	private int menuId;
	private int parentId;
	private String groupId;
	private String authorityId;
	private String display;
	private String shortcut;
	private String description;
	private String dispindex;
	private String iconuri;
	private String text;//对应前台ExtJS 树的'text'属性
	private int id;
	private boolean leaf;//对应前台ExtJS树的'leaf'属性
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getAuthorityId() {
		return authorityId;
	}
	public void setAuthorityId(String authorityId) {
		this.authorityId = authorityId;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public String getShortcut() {
		return shortcut;
	}
	public void setShortcut(String shortcut) {
		this.shortcut = shortcut;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDispindex() {
		return dispindex;
	}
	public void setDispindex(String dispindex) {
		this.dispindex = dispindex;
	}
	public String getIconuri() {
		return iconuri;
	}
	public void setIconuri(String iconuri) {
		this.iconuri = iconuri;
	}
}
