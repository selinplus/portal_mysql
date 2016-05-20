package org.ytgs.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.ytgs.security.model.SysAuthorities;
import org.ytgs.security.model.SysRolesAuthorities;

public interface AppDao {

	List<SysAuthorities> getAllAppByRoleId(String roleId);

	void insertRoleApp(SysRolesAuthorities roleAuthority);

	void deleteRoleApp(int id);

	List<Integer> getAppList(String userId);

	void deleteRoleAppByRoleAndAppId(@Param("roleId")String roleId,@Param("appId") String appId);

}
