package org.ytgs.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ytgs.common.dao.AppDao;
import org.ytgs.security.dao.SysAuthoritiesDao;
import org.ytgs.security.dao.SysAuthoritiesResourcesDao;
import org.ytgs.security.dao.SysRolesAuthoritiesDao;
import org.ytgs.security.dao.SysRolesDao;
import org.ytgs.security.dao.SysUsersDao;
import org.ytgs.security.dao.SysUsersRolesDao;
import org.ytgs.security.model.SysAuthorities;
import org.ytgs.security.model.SysResources;
import org.ytgs.security.model.SysRoles;
import org.ytgs.security.model.SysRolesAuthorities;
import org.ytgs.security.model.SysUsers;
import org.ytgs.security.model.SysUsersRoles;
import org.ytgs.system.dao.SysDepartsDao;
import org.ytgs.system.dao.SysDeptDao;
import org.ytgs.system.dao.SysResourcesDao;
import org.ytgs.system.model.SysDeparts;
import org.ytgs.system.model.SysDept;

@Service
public class SecurityDaoService {

	@Autowired
	public SysAuthoritiesDao sysAuthoritiesDao;
	@Autowired
	public SysAuthoritiesResourcesDao sysAuthoritiesResourcesDao;
	@Autowired
	public SysResourcesDao sysResourcesDao;
	@Autowired
	public SysRolesAuthoritiesDao sysRolesAuthoritiesDao;	
	@Autowired
	public SysUsersDao sysUsersDao;
	@Autowired
	public SysDepartsDao sysDepartsDao;
	@Autowired
	public SysDeptDao sysDeptDao;
	@Autowired
	public SysRolesDao sysRolesDao;
	@Autowired
	public SysUsersRolesDao sysUsersRolesDao;
	@Autowired
	public AppDao appDao;
	/**
	 * 获取全部权限名
	 * @return
	 */
	public List<String> getSysAuthoritiesNames(){
		return sysAuthoritiesDao.selectSysAuthoritiesNames();
	}
	/**
	 * 根据账号获取用户对象
	 * @param userName
	 * @return
	 */
	public SysUsers findByUserAccount(String userName){
		return sysUsersDao.findByUserAccount( userName );
	}
	/**
	 * 获取用户账号为'userName'的全部权限名
	 * @param userName
	 * @return
	 */
	public List<String> loadUserAuthorities(String userName){
		return sysUsersDao.loadUserAuthorities(userName);
	}
	/**
	 * 获取权限名为auth的资源url
	 * @param auth
	 * @return
	 */
	public List<String> getSysResourcesString(String auth){
		return sysAuthoritiesResourcesDao.getSysResourcesString(auth);
	}	

	//用户基本信息操作
	public List<SysUsers> selectUsers(String userId){
		return sysUsersDao.selectUsers(userId);
	}
	
	public void deleteUsers(String userId){
		sysUsersDao.deleteUsers(userId);
	}
	
	public void insertUsers(SysUsers sysUsers){
		sysUsersDao.insertUsers(sysUsers);
	}
	public void updateUsers(SysUsers sysUsers){
		sysUsersDao.updateUsers(sysUsers);
	}
	public List<SysUsers> getAllUsers(SysUsers sysUsers)
	{
		sysUsers.setPage(sysUsers.getLimit()*(sysUsers.getPage()-1));
		return sysUsersDao.selectAllUsers(sysUsers);
	}
	//全部角色检出，SYS_ROLES表
		public List<SysRoles> getAllRoles(String userId){
			return sysRolesDao.selectAllRolesByUserId(userId);
		}
		
		//按用户ID检出用户角色SYS_USERS_ROLES和SYS_ROLES表
		public List<SysUsersRoles> getUsersRolesByUserId(String userId){
			
			return sysUsersRolesDao.selectSysUsersRolesByUserId(userId);
		}
		
		public void insertUsersRoles(SysUsersRoles sysUsersRoles){
			sysUsersRolesDao.insertSysUsersRoles(sysUsersRoles);
		}
		
		public void deleteUsersRoles(int id){
			sysUsersRolesDao.deleteSysUsersRoles(id);
		}
		
		public String isExistUserRole(){
			return "y";
		}
	//部门树
		public List<SysDeparts> getDepartChildByParentId(String parentId){
			return sysDepartsDao.getChildByParentId(parentId);
		}
		public SysDept getDeparts(String deptId){
			return sysDeptDao.getDeparts(deptId);
		}
		public List<String> getDepartParentId(){
			return sysDepartsDao.getParentId();
		}
		public List<SysAuthorities> getAllAuthority(String roleId) {
			
			return sysAuthoritiesDao.getAllAuthority(roleId);
		}
		public List<SysRolesAuthorities> getRoleAuthorityByroleId(String roleId) {
			
			return sysRolesAuthoritiesDao.getRoleAuthorityByroleId(roleId);
		}
		public void deleteRoleAuthority(int id) {
			
			sysRolesAuthoritiesDao.deleteRoleAuthority(id);
		}
		public void insertRoleAuthority(SysRolesAuthorities roleAuthority) {
			sysRolesAuthoritiesDao.insertSysRolesAuthorities(roleAuthority);
			
		}
		public List<SysAuthorities> getAllApp(String roleId) {
			return appDao.getAllAppByRoleId(roleId);
		}
		public void insertRoleApp(SysRolesAuthorities roleAuthority) {
			appDao.insertRoleApp(roleAuthority);
		}
		public void deleteRoleApp(int id) {
			appDao.deleteRoleApp(id);
		}
		public List<Integer> getAppList(String userId) {
			return appDao.getAppList(userId);
		}
		public void deleteRoleAppByRoleAndAppId(String roleId,String appId ) {
			
			appDao.deleteRoleAppByRoleAndAppId(roleId,appId);
		}
		public void updateUserStatus(String userId,String status) {
			
			sysUsersDao.updateUserStatus(userId,status);
		}
		public void updateUser(SysUsers users) {
			
			sysUsersDao.updateSysUser(users);
		}
		public String onlineUsers() {
			
			return sysUsersDao.onlineUsers();
		}
		public int getAllUsersCount(SysUsers sysUsers) {
			return  sysUsersDao.selectAllUsersCount(sysUsers);
		}
		
		
}
