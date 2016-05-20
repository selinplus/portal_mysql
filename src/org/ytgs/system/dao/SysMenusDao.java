package org.ytgs.system.dao;

import java.util.List;

import org.ytgs.security.dto.SysMenuResourceDto;
import org.ytgs.system.model.SysMenus;
import org.ytgs.util.TreeNode;

public interface SysMenusDao {

	public List<SysMenuResourceDto> getUserMenuResourcesString(String auth);
	public List<SysMenuResourceDto> getMenuBranchById(int menuId);
	public List<SysMenus> getChildByParentId(int parentId);
	public List<Integer> getParentId();
	public String getMenuResource(int menuId);
	public void insertNode(SysMenus sysMenus);
	public void deleteNode(int menuId);
	public void updateNode(SysMenus sysMenus);
	public  List<Integer> getMenuLeafByUserAccount(String userAccount);
	public List<SysMenuResourceDto> getMenusByUserAccount(String userAccount);
	public List<TreeNode> getChildrenByParentId(int parentId);
}
