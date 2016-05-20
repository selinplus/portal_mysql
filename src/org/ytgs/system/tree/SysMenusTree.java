package org.ytgs.system.tree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ytgs.system.model.SysMenus;
import org.ytgs.system.service.SystemDaoService;
import org.ytgs.util.MenuTreeNode;
import org.ytgs.util.TreeNode;

@Component
public class SysMenusTree {

	private int parentId;
	@Autowired
	private SystemDaoService systemDaoService;
	private static ObjectMapper objectMapper = new ObjectMapper();

	public ArrayList<TreeNode> getJSONString() throws JsonGenerationException,
			JsonMappingException, IOException {

		ArrayList<TreeNode> treeNodeArray = null;

		List<SysMenus> childList = systemDaoService
				.getChildByParentId(parentId);

		List<Integer> parentIdList = systemDaoService.getParentId();

		treeNodeArray = new ArrayList<TreeNode>();
		systemDaoService.setAuthMenuMap();
		for (SysMenus sysMenus : childList) {
			if (systemDaoService.imap.containsKey(sysMenus.getMenuId())) {
				TreeNode treeNode = new TreeNode();
				treeNode.setId(sysMenus.getMenuId());
				treeNode.setText(sysMenus.getDisplay());
				treeNode.setDescription(sysMenus.getDescription());

				if (parentIdList.contains(sysMenus.getMenuId())) 
				{
					treeNode.setCls("folder");
					treeNode.setLeaf(false);
					treeNode.setExpandable(true);
				} else 
				{
					treeNode.setHref("admin/menuTreeNodeRedirect?id="
							+ sysMenus.getMenuId());
					treeNode.setCls("file");
					treeNode.setLeaf(true);
					treeNode.setExpandable(false);
				}
				treeNodeArray.add(treeNode);
			}
		}

		return treeNodeArray;

	}

	public int getPID() {
		return parentId;
	}

	public void setPID(int pid) {
		parentId = pid;
	}

	public ArrayList<MenuTreeNode> getFullJSONString()
			throws JsonGenerationException, JsonMappingException, IOException {

		ArrayList<MenuTreeNode> treeNodeArray = null;

		List<SysMenus> childList = systemDaoService
				.getChildByParentId(parentId);

		List<Integer> parentIdList = systemDaoService.getParentId();

		treeNodeArray = new ArrayList<MenuTreeNode>();
		systemDaoService.setAuthMenuMap();
		for (SysMenus sysMenus : childList) {

			MenuTreeNode treeNode = new MenuTreeNode();
			treeNode.setId(sysMenus.getMenuId());
			treeNode.setText(sysMenus.getDisplay());
			treeNode.setDescription(sysMenus.getDescription());

			if (parentIdList.contains(sysMenus.getMenuId())) // ���ڵ�
			{
				treeNode.setCls("folder");
				treeNode.setLeaf(false);
				treeNode.setExpandable(true);
			} else // �ӽڵ�
			{
				treeNode.setHref("admin/menuTreeNodeRedirect?id="
						+ sysMenus.getMenuId());
				treeNode.setAuthorityId(sysMenus.getAuthorityId());
				treeNode.setCls("file");
				treeNode.setLeaf(true);
				treeNode.setExpandable(false);
			}
			treeNodeArray.add(treeNode);

		}

		return treeNodeArray;

	}
}
