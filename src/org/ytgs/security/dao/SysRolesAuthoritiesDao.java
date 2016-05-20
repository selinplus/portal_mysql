package org.ytgs.security.dao;

import java.util.List;

import org.ytgs.security.model.SysRolesAuthorities;

public interface SysRolesAuthoritiesDao  {

	public SysRolesAuthorities selectSysRolesAuthorities(int id );
	public void deleteSysRolesAuthorities(int id );
	public void insertSysRolesAuthorities(SysRolesAuthorities  sysRolesAuthorities );
	public void updateSysRolesAuthorities(SysRolesAuthorities  sysRolesAuthorities );
	public List<SysRolesAuthorities> getRoleAuthorityByroleId(String roleId);
	public void deleteRoleAuthority(int id);
	
}
