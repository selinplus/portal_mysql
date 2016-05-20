package org.ytgs.common.tree;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TreeService {

	@Autowired
	public TreeDao treeDao;
	/**
	 * 通过parentId获取它的儿子，不包括孙子及后代
	 * 
	 * @param parentId
	 * @return
	 */
	public List<TreeModel> getChildByParentId(String tabName,int parentId) {
		return treeDao.getChildByParentId(tabName,parentId);
	}
	public List<TreeModel> getChildWithHerfByParentId(String tabName,int parentId) {
		return treeDao.getChildWithHerfByParentId(tabName,parentId);
	}
	public List<Integer> getParentId(String tabName) {
		return treeDao.getParentId(tabName);
	}
	public List<TreeModel> getChildWithHerfByTableNameParentId(String tabName,int parentId) {
		return treeDao.getChildWithHerfByTableNameParentId(tabName,parentId);
	}
	public List<Integer> getParentIdByTableName(String tabName) {
		return treeDao.getParentIdByTableName(tabName);
	}

}
