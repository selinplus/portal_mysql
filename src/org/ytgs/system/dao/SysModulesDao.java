package org.ytgs.system.dao;

import java.util.List;

import org.ytgs.system.model.SysModules;

public interface SysModulesDao {

	public List<SysModules> getChildByParentId(String parentId);
	public List<String> getParentId();
	public void deleteNode(String moduleId);
	public void insertNode(SysModules sysModules);
	public void updateNode(SysModules sysModules);
}
