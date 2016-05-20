package org.ytgs.security.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.ytgs.security.model.SysRoles;

public interface SysRolesDao {
public SysRoles selectSysRoles(int roleId);
public void   insertSysRoles(SysRoles  sysRoles );
public void   updateSysRoles(SysRoles  sysRoles );
public void  deleteSysRoles(String roleId);
public List<SysRoles> selectAllRolesByUserId(String userId);
public List<SysRoles> selectAllRoles(@Param("page") int page,@Param("limit") int limit);
public String selectRoleById(String roleName);
public int selectAllRolesCount();	
}
