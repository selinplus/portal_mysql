package org.ytgs.system.dao;

import java.util.List;

import org.ytgs.system.model.SysDeparts;
import org.ytgs.system.model.SysDept;

public interface SysDeptDao  {

	public List<SysDept> getDeptChildByParentId(String parentId);
	public List<String> getDeptParentId();
	public List<String> getDeptId();
	public void deleteSysDept(String id);
	public void insertSysDept(SysDept sysDept);
	public void updateSysDept(SysDept sysDept);
	public String selectDeptById(String deptId);
	public SysDept getDeparts(String deptId);	

}
