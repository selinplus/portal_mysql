package org.ytgs.system.dao;

import java.util.List;

import org.ytgs.security.dto.SysMenuResourceDto;
import org.ytgs.system.model.SysMenus;
import org.ytgs.util.TreeNode;

public interface SysUserShortCutsDao {

	public List<TreeNode> getUserShortCuts(String userAccount);
}
