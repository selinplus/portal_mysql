package org.ytgs.system.tree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ytgs.system.model.SysModules;
import org.ytgs.system.service.SystemDaoService;
import org.ytgs.util.CheckTreeNode;
import org.ytgs.util.TreeDeptNode;
import org.ytgs.util.TreeNode;
import org.ytgs.zgjx.model.DmHy;
@Component
public class SysModulesTree {

	private String parentId;
	@Autowired
	private SystemDaoService systemDaoService;
	
	private static ObjectMapper objectMapper = new ObjectMapper();

	public String getTreeJSONString() throws JsonGenerationException,
			JsonMappingException, IOException {
		ArrayList<TreeDeptNode> treeNodeArray = null;
		List<SysModules> childList = systemDaoService
				.getModuleChildByParentId(parentId);
		List<String> parentIdList = systemDaoService.getModuleParentId();
		treeNodeArray = new ArrayList<TreeDeptNode>();
		for (SysModules sysModules : childList) {
			TreeDeptNode treeNode = new TreeDeptNode();
			treeNode.setId(sysModules.getModuleId());
			treeNode.setText(sysModules.getModuleName());
			treeNode.setDescription(sysModules.getModuleName());
			if (parentIdList.contains(sysModules.getModuleId())) {
				treeNode.setCls("folder");
				treeNode.setLeaf(false);
				treeNode.setExpandable(true);
			} else {
				treeNode.setCls("file");
				treeNode.setLeaf(true);
				treeNode.setExpandable(false);
			}
			treeNodeArray.add(treeNode);

		}
		return objectMapper.writeValueAsString(treeNodeArray);
	}
	public String getCheckTreeJSONString() throws JsonGenerationException,
	JsonMappingException, IOException {
		ArrayList<CheckTreeNode> treeNodeArray = null;
		List<SysModules> childList = systemDaoService
				.getModuleChildByParentId(parentId);
		List<String> parentIdList = systemDaoService.getModuleParentId();
		treeNodeArray = new ArrayList<CheckTreeNode>();
		for (SysModules sysModules : childList) {
			CheckTreeNode treeNode = new CheckTreeNode();
			treeNode.setId(sysModules.getModuleId());
			treeNode.setText(sysModules.getModuleName());
			treeNode.setDescription(sysModules.getModuleName());
			if (parentIdList.contains(sysModules.getModuleId())) {
				treeNode.setCls("folder");
				treeNode.setLeaf(false);
				treeNode.setExpandable(true);
			} else {
				treeNode.setCls("file");
				treeNode.setLeaf(true);
				treeNode.setExpandable(false);
			}
			treeNodeArray.add(treeNode);
			
		}
		return objectMapper.writeValueAsString(treeNodeArray);
	}

	public String getPID() {
		return parentId;
	}

	public void setPID(String pid) {
		parentId = pid;
	}
	
}
