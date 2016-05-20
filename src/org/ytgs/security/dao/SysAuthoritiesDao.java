package org.ytgs.security.dao;



import java.util.List;

import org.ytgs.security.model.SysAuthorities;
import org.ytgs.security.model.SysRolesAuthorities;
import org.ytgs.system.dto.SysAuthorityResourceDto;


public interface SysAuthoritiesDao  {

	public String selectSysAuthorities(String id);
	public void insertSysAuthorities(SysAuthorityResourceDto sysAuthorityResourceDto);
	public List<String> selectSysAuthoritiesNames();
	public void updateSysAuthorities(SysAuthorityResourceDto sysAuthorityResourceDto);
	public void deleteSysAuthorities(String authorityId);
	public List<SysAuthorityResourceDto> selectSysAuthoritiesByModule(String moduleId);
	public List<SysAuthorities> getAllAuthority(String roleId);	
}
