package org.ytgs.security.dao;

import java.util.List;

import org.ytgs.security.model.SysAuthoritiesResources;
import org.ytgs.system.dto.SysAuthorityResourceDto;

public interface SysAuthoritiesResourcesDao {

	public List<String> getSysResourcesString(String auth);
	public  void deleteSysAuthoritiesResources(String authorityId);
	public void insertSysAuthoritiesResources(SysAuthorityResourceDto sysAuthorityResourceDto);
}
