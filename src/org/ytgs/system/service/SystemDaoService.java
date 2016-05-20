package org.ytgs.system.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.ytgs.security.dao.SysAuthoritiesDao;
import org.ytgs.security.dao.SysAuthoritiesResourcesDao;
import org.ytgs.security.dao.SysRolesAuthoritiesDao;
import org.ytgs.security.dao.SysRolesDao;
import org.ytgs.security.dao.SysUsersDao;
import org.ytgs.security.dto.SysMenuResourceDto;
import org.ytgs.security.model.SysResources;
import org.ytgs.security.model.SysRoles;
import org.ytgs.security.model.SysRolesAuthorities;
import org.ytgs.security.model.SysUsers;
import org.ytgs.system.dao.SysDeptDao;
import org.ytgs.system.dao.SysMenusDao;
import org.ytgs.system.dao.SysModulesDao;
import org.ytgs.system.dao.SysResourcesDao;
import org.ytgs.system.dao.SysUserShortCutsDao;
import org.ytgs.system.dto.SysAuthorityResourceDto;
import org.ytgs.system.model.SysDept;
import org.ytgs.system.model.SysMenus;
import org.ytgs.system.model.SysModules;
import org.ytgs.util.TreeNode;
@Service
public class SystemDaoService {

	@Autowired
	public SysMenusDao sysMenusDao;
	@Autowired
	public SysUsersDao sysUsersDao;
	@Autowired
	public SysModulesDao sysModulesDao;
	@Autowired
	public SysResourcesDao sysResourcesDao;
	@Autowired
	public SysAuthoritiesDao sysAuthoritiesDao;
	@Autowired
	public SysAuthoritiesResourcesDao sysAuthoritiesResourcesDao;
	@Autowired
	public SysRolesAuthoritiesDao sysRolesAuthoritiesDao;
	@Autowired
	public SysRolesDao sysRolesDao;
	@Autowired
	public SysUserShortCutsDao sysUserShortCutsDao;

	@Autowired
	public SysDeptDao sysDeptDao;
	public Map<Integer, SysMenuResourceDto> imap = new HashMap<Integer, SysMenuResourceDto>(
			0);

	/**
	 * 通过parentId获取它的儿子，不包括孙子及后代
	 * 
	 * @param parentId
	 * @return
	 */
	public List<SysMenus> getChildByParentId(int parentId) {
		return sysMenusDao.getChildByParentId(parentId);
	}

	/**
	 * 获取所有父亲节点的代码
	 * 
	 * @return
	 */
	public List<Integer> getParentId() {
		return sysMenusDao.getParentId();
	}

	/**
	 * 根据权限名称获取菜单值对象SysMenuResourceDto的以菜单ID为key的map，
	 * 
	 * @param authName
	 */
	public void getUserMenusByAuth(String authName) {

		List<SysMenuResourceDto> menuResourceList = sysMenusDao
				.getUserMenuResourcesString(authName);
		for (SysMenuResourceDto smr : menuResourceList) {
			imap.put(smr.getMenuId(), smr);
		}
	}

	/**
	 * 获取每个子节点的到根的所有节点路径
	 */
	public void getUserMenuBranch() {
		// 值引用怎么搞？？？
		Object[] iKeySet = null;
		iKeySet = imap.keySet().toArray();
		for (int i = 0; i < iKeySet.length; i++) {
			int menuId =  (Integer) iKeySet[i];
			List<SysMenuResourceDto> menuResourceList = sysMenusDao
					.getMenuBranchById(menuId);
			for (SysMenuResourceDto smr : menuResourceList) {
				if (!imap.containsKey(smr.getMenuId())) {
					imap.put(smr.getMenuId(), smr);
				}
			}
		}
	}

	/**
	 * 填充所有权限对应的资源及父的imap对象
	 */
	@SuppressWarnings("unchecked")
	public void setAuthMenuMap() {
		UserDetails userDetails = (SysUsers) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		Collection<GrantedAuthority> auths = (Collection<GrantedAuthority>) userDetails
				.getAuthorities();
		imap=new HashMap<Integer, SysMenuResourceDto>(0);//重置imap
		for (GrantedAuthority au : auths) {
			this.getUserMenusByAuth(au.getAuthority());
		}
		this.getUserMenuBranch();

	}

	/**
	 * 通过parentId获取它的儿子，不包括孙子及后代
	 * 
	 * @param parentId
	 * @return
	 */
	public List<SysModules> getModuleChildByParentId(String parentId) {
		return sysModulesDao.getChildByParentId(parentId);
	}

	/**
	 * 获取所有父亲节点的代码
	 * 
	 * @return
	 */
	public List<String> getModuleParentId() {
		return sysModulesDao.getParentId();
	}

	/**
	 * 根据menuId获取url
	 * 
	 * @param menuId
	 * @return
	 */
	public String getMenuResource(int menuId) {
		return sysMenusDao.getMenuResource(menuId);
	}

	/**
	 * 删除模块节点
	 * 
	 * @param moduleId
	 */
	public void deleteModuleNode(String moduleId) {
		sysModulesDao.deleteNode(moduleId);
	}

	/**
	 * 增加模块节点
	 * 
	 * @param sysModules
	 */
	public void insertModuleNode(SysModules sysModules) {
		sysModulesDao.insertNode(sysModules);
	}

	/**
	 * 修改模块节点
	 * 
	 * @param sysModules
	 */
	public void updateModuleNode(SysModules sysModules) {
		sysModulesDao.updateNode(sysModules);
	}

	/**
	 * 根据moduleId获取资源列表
	 * 
	 * @param moudleId
	 * @return
	 */
	public List<SysResources> getSysResourcesByModuleId(String moudleId) {
		return sysResourcesDao.selectSysResourcesByModuleId(moudleId);
	}

	/**
	 * 删除资源节点
	 * 
	 * @param resourceId
	 */
	public void deleteResource(String resourceId) {
		sysResourcesDao.deleteSysResources(resourceId);
	}

	/**
	 * 增加资源节点
	 * 
	 * @param sysResources
	 */
	public void insertResource(SysResources sysResources) {
		sysResourcesDao.insertSysResources(sysResources);
	}

	/**
	 * 修改资源节点
	 * 
	 * @param sysResources
	 */
	public void updateResource(SysResources sysResources) {
		sysResourcesDao.updateSysResources(sysResources);
	}

	/**
	 * 是否存在该resources
	 * 
	 * @param resourceId
	 * @return
	 */
	public String isExistResource(String resourceId) {
		return sysResourcesDao.selectSysResourcesById(resourceId);
	}

	/**
	 * 增加权限记录
	 * 
	 * @param sysAuthorities
	 */
	public void addAuthority(SysAuthorityResourceDto sysAuthorityResourceDto) {
		sysAuthoritiesDao.insertSysAuthorities(sysAuthorityResourceDto);
	}

	/**
	 * 修改权限信息
	 * 
	 * @param sysAuthorities
	 */
	public void editAuthority(SysAuthorityResourceDto sysAuthorityResourceDto) {
		sysAuthoritiesDao.updateSysAuthorities(sysAuthorityResourceDto);
	}

	/**
	 * 删除权限
	 * 
	 * @param authorityId
	 */
	public void removeAuthority(String authorityId) {
		sysAuthoritiesDao.deleteSysAuthorities(authorityId);
	}

	/**
	 * 通过moduleId获取权限及资源信息
	 * 
	 * @param moduleId
	 * @return
	 */
	public List<SysAuthorityResourceDto> getSysAuthoritiesByModule(
			String moduleId) {
		return sysAuthoritiesDao.selectSysAuthoritiesByModule(moduleId);
	}

	/**
	 * 删除资源权限关联表中记录
	 * 
	 * @param authorityId
	 */
	public void removeSysAuthoritiesResources(String authorityId) {
		sysAuthoritiesResourcesDao.deleteSysAuthoritiesResources(authorityId);
	}

	/**
	 * 增加权限资源关联表记录
	 * 
	 * @param sysAuthorityResourceDto
	 */
	public void addSysAuthoritiesResources(
			SysAuthorityResourceDto sysAuthorityResourceDto) {
		sysAuthoritiesResourcesDao
				.insertSysAuthoritiesResources(sysAuthorityResourceDto);
	}

	public String isExistAuthority(String authorityId) {
		return sysAuthoritiesDao.selectSysAuthorities(authorityId);
	}

	/**
	 * 增加菜单项
	 * 
	 * @param sysMenus
	 */
	public void addMenuNode(SysMenus sysMenus) {
		sysMenusDao.insertNode(sysMenus);
	}

	/**
	 * 删除菜单项
	 * 
	 * @param menuId
	 */
	public void removeMenuNode(int menuId) {
		sysMenusDao.deleteNode(menuId);
	}

	/**
	 * 修改菜单项
	 * 
	 * @param sysMenus
	 */
	public void editMenuNode(SysMenus sysMenus) {
		sysMenusDao.updateNode(sysMenus);
	}
	/**
	 * 根据账号获取所有菜单的叶子节点
	 * @param userAccount
	 * @return
	 */
   public List<Integer> getMenuLeafByUserAccount(String userAccount)
   {
       return sysMenusDao.getMenuLeafByUserAccount(userAccount);
   }
   public List<TreeNode> getUserShortCuts(String userAccount){
	   return sysUserShortCutsDao.getUserShortCuts(userAccount);
   }
   /**
    * 更具菜单id获取其所有祖先节点
    * @param menuId
    * @return
    */
   public List<SysMenuResourceDto> getMenuBranchById(int menuId)
   {
	   return sysMenusDao.getMenuBranchById(menuId);
   }
   public List<TreeNode> getChildrenByParentId(int parentId)
   {
	   return sysMenusDao.getChildrenByParentId(parentId);
   }
   public List<TreeNode> getChildrenByParentId(int parentId,List<TreeNode> list)
   {
	   return sysMenusDao.getChildrenByParentId(parentId);
   }
	/**
	 * 插入角色权限
	 * 
	 * @param sysRolesAuthorities
	 */
	public void addSysRolesAuthorities(SysRolesAuthorities sysRolesAuthorities) {
		sysRolesAuthoritiesDao.insertSysRolesAuthorities(sysRolesAuthorities);
	}
	public List<SysRoles> getAllRoles(int page ,int limit)
	{
		return sysRolesDao.selectAllRoles(limit*(page-1),limit);
	}

	public String isExistRole(String roleName) {
		return sysRolesDao.selectRoleById(roleName);
	}

	public void insertRole(SysRoles sysRole) {
		sysRolesDao.insertSysRoles(sysRole);
		
	}

	public void updateRole(SysRoles sysRole) {
		sysRolesDao.updateSysRoles(sysRole);
	}

	public void delRole(String roleId) {
		sysRolesDao.deleteSysRoles(roleId);
	}
	/** 部门维护 增删改
	 * 
	 */
	/**
	 * 通过parentId获取它的儿子，不包括孙子及后代
	 * 
	 * @param parentId
	 * @return
	 */
	public List<SysDept> getDeptChildByParentId(String parentId) {
		return sysDeptDao.getDeptChildByParentId(parentId);
	}

	/**
	 * 获取所有父亲节点的代码
	 * 
	 * @return
	 */
	public List<String> getDeptParentId() {
		return sysDeptDao.getDeptParentId();
	}
	/**
	 * 删除部门
	 * 
	 * @return
	 */
	public void removeDeptNode(String id){
	   sysDeptDao.deleteSysDept(id);
	}
	/**
	 * 增加部门
	 * 
	 * @return
	 */
	public void addDeptNode(SysDept sysDept){
	   sysDeptDao.insertSysDept(sysDept);
	}
     /**
      * 编辑部门
      * 
      * @return
      */
	public void editDeptNode(SysDept sysDept){
		 sysDeptDao.updateSysDept(sysDept);
		 }
	/**
	 * 获取所有部门Id的代码
	 * 
	 * @return
	 */
	public List<String> getDeptId() {
		return sysDeptDao.getDeptId();
	}
	/**
	 * 校验部门Id的代码
	 * 
	 * @return
	 */
	public String isExistDept(String deptId) {
		return sysDeptDao.selectDeptById(deptId);
	}

	public List<SysMenuResourceDto> getMenusByUserAccount(String userAccount) {
		return sysMenusDao.getMenusByUserAccount(userAccount);
	}

	public SysUsers getUserByAccount(String userAccount) {
		return sysUsersDao.getUserByAccount(userAccount);
		
	}

	public int getAllRolesCount() {
		return sysRolesDao.selectAllRolesCount();
	}
	
}
