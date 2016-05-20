package org.ytgs.system.dao;

import java.util.List;

import org.ytgs.system.model.SysDeparts;

public interface SysDepartsDao {

	public List<SysDeparts> getChildByParentId(String parentId);
	public List<String> getParentId();
}
