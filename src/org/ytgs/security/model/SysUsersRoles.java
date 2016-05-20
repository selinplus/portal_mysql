package org.ytgs.security.model;

import org.springframework.stereotype.Component;

// Generated 2011-3-23 11:09:37 by Hibernate Tools 3.2.2.GA

/**
 * PubUsersRoles generated by hbm2java
 */
@SuppressWarnings("serial")
@Component
public class SysUsersRoles implements java.io.Serializable {
    public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	private String roleName;
	private long id;
	private String userId;
	private String roleId;
	private String roleDesc;	
	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public SysUsers getPubUsers() {
		return pubUsers;
	}

	public void setPubUsers(SysUsers pubUsers) {
		this.pubUsers = pubUsers;
	}

	public SysRoles getPubRoles() {
		return pubRoles;
	}

	public void setPubRoles(SysRoles pubRoles) {
		this.pubRoles = pubRoles;
	}

	private SysUsers pubUsers;
	private SysRoles pubRoles;
	private Boolean enabled;

	public SysUsersRoles() {
	}

	public SysUsersRoles(long id) {
		this.id = id;
	}

	public SysUsersRoles(long id, SysUsers pubUsers, SysRoles pubRoles,
			Boolean enabled) {
		this.id = id;
		this.pubUsers = pubUsers;
		this.pubRoles = pubRoles;
		this.enabled = enabled;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public SysUsers getSysUsers() {
		return this.pubUsers;
	}

	public void setSysUsers(SysUsers pubUsers) {
		this.pubUsers = pubUsers;
	}

	public SysRoles getSysRoles() {
		return this.pubRoles;
	}

	public void setSysRoles(SysRoles pubRoles) {
		this.pubRoles = pubRoles;
	}

	public boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}