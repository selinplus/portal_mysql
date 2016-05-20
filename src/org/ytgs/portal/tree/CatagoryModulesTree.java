package org.ytgs.portal.tree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ytgs.portal.common.model.Catagory;
import org.ytgs.portal.service.CatagoryDaoService;
import org.ytgs.util.CheckTreeNode;
import org.ytgs.util.TreeNode;

@Component
public class CatagoryModulesTree {

	private String parentId;
	@Autowired
	private CatagoryDaoService CatagoryDaoService;
	private static ObjectMapper objectMapper = new ObjectMapper();

	public String getTreeJSONString() throws JsonGenerationException,
			JsonMappingException, IOException {
		ArrayList<TreeNode> treeNodeArray = null;
		List<Catagory> childList = CatagoryDaoService.getModuleChildByParentId(Integer.parseInt(parentId));
		List<String> parentIdList = CatagoryDaoService.getModuleParentId();
		treeNodeArray = new ArrayList<TreeNode>();
		for (Catagory catagory : childList) {
			TreeNode treeNode = new TreeNode();
			treeNode.setId(catagory.getId());
			treeNode.setText(catagory.getCatagoryName());
			treeNode.setDescription(catagory.getCatagoryName());
			if (parentIdList.contains(catagory.getId())) {
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
		List<Catagory> childList = CatagoryDaoService
				.getModuleChildByParentId(Integer.parseInt(parentId));
		List<String> parentIdList = CatagoryDaoService.getModuleParentId();
		treeNodeArray = new ArrayList<CheckTreeNode>();
		for (Catagory catagory : childList) {
			CheckTreeNode treeNode = new CheckTreeNode();
			treeNode.setId(String.valueOf(catagory.getId()));
			treeNode.setText(catagory.getCatagoryName());
			treeNode.setDescription(catagory.getCatagoryName());
			if (parentIdList.contains(catagory.getId())) {
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
