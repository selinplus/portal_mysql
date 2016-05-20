package org.ytgs.security.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.ytgs.security.model.SysUsers;
public interface SysUsersDao {

	public List<String> loadUserAuthorities(String userName);

	public SysUsers selectSysUsers(String userId);

	public void insertSysUsers(SysUsers users);

	public SysUsers findByUserAccount(String userAccount);

	public void deleteSysUsers(int userId);

	public SysUsers selectSysUsers(int userId);

	public void updateSysUser(SysUsers users);

	public List<SysUsers> selectUsers(String userId);

	public void deleteUsers(String userId);

	public void insertUsers(SysUsers sysUsers);

	public void updateUsers(SysUsers sysUsers);

	public void updateUserStatus(@Param("userId") String userId,
			@Param("status") String status);

	public String onlineUsers();

	public List<SysUsers> selectAllUsers(SysUsers sysUsers);

	public SysUsers getUserByAccount(String userAccount);

	public int selectAllUsersCount(SysUsers sysUsers);
}
