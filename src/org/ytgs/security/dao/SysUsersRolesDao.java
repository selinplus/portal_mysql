package org.ytgs.security.dao;

import java.util.List;

import org.ytgs.security.model.SysUsersRoles;

public interface SysUsersRolesDao  {
 public SysUsersRoles selectSysUsersRoles(int id);
 public void  deleteSysUsersRoles(int id);
 public void insertSysUsersRoles(SysUsersRoles sysUsersRoles);
 public void updateSysUsersRoles(SysUsersRoles sysUsersRoles);
 
 public List<SysUsersRoles> selectSysUsersRolesByUserId(String userId);
	
}
