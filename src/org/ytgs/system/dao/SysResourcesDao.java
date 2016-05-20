package org.ytgs.system.dao;

import java.util.List;

import org.ytgs.security.model.SysResources;

public interface SysResourcesDao  {

	public List<SysResources> selectSysResourcesByModuleId(String id);	
	public void deleteSysResources(String id);
	public void insertSysResources(SysResources sysResources);
	public void updateSysResources(SysResources sysResources);
	public String selectSysResourcesById(String resourceId);

}
