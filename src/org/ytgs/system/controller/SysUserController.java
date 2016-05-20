package org.ytgs.system.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ytgs.security.CustomUserDetails;
import org.ytgs.security.model.SysResources;
import org.ytgs.security.model.SysUsers;
import org.ytgs.security.service.SecurityDaoService;
import org.ytgs.system.service.SystemDaoService;

import org.ytgs.system.tree.SysDeptTree;
import org.ytgs.system.tree.SysModulesTree;
import org.ytgs.util.FillBeanProperyAsNUllForMybatis;
import org.ytgs.util.Md5Encoder;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.json.simple.JSONArray;

@Controller
public class SysUserController {

	@Autowired
	public SecurityDaoService securityDaoService;
	@Autowired
	public SysUsers sysUsers;
	@Autowired
	public SysDeptTree jsonTree;
	public ObjectMapper om = new ObjectMapper();
		
	/*@RequestMapping(value = "/admin/sys/manageUserQuery")
	@ResponseBody
	public String getSysUsersByUserId(String userDept,int page,int limit) throws IOException {
		
		List<SysUsers> sysUsersList =null;
		sysUsersList = securityDaoService.getAllUsers(userDept,page,limit);
		int totalCount=securityDaoService.getAllUsersCount(userDept);
			
		String jsonString ="{ \"totalCount\":"+totalCount+", \"records\":[";
		for(SysUsers sysUser:sysUsersList){
			jsonString+="{\"userId\":\""+sysUser.getUserId()+"\",";
			jsonString+="\"userAccount\":\""+sysUser.getUserAccount()+"\",";
			jsonString+="\"userName\":\""+sysUser.getUserName()+"\",";
			jsonString+="\"userPassword\":\""+sysUser.getUserPassword()+"\",";
			jsonString+="\"userDesc\":\""+sysUser.getUserDesc()+"\",";
			
			jsonString+="\"issys\":\""+sysUser.getIssys()+"\",";
			jsonString+="\"userDept\":\""+sysUser.getUserDept()+"\",";
			jsonString+="\"userDuty\":\""+sysUser.getUserDuty()+"\",";
			jsonString+="\"userMobile\":\""+sysUser.getUserMobile()+"\",";
			jsonString+="\"userOrder\":\""+sysUser.getUserOrder()+"\",";
			jsonString+="\"subSystem\":\""+sysUser.getSubSystem()+"\"},";
			
		}
		if(sysUsersList.size()!=0)
		{jsonString=",".equals(jsonString.substring(jsonString.length()-1))?jsonString.substring(0, jsonString.length()-1):jsonString;}
		jsonString+="]}";
		return jsonString;
	}*/
	@RequestMapping(value = "/admin/sys/manageUserQuery")
	@ResponseBody
	public String getSysUsersByUserId(SysUsers sysUsers) throws IOException {
		
		List<SysUsers> sysUsersList =null;
		FillBeanProperyAsNUllForMybatis.fillNull(sysUsers);
		sysUsersList = securityDaoService.getAllUsers(sysUsers);
		int totalCount=securityDaoService.getAllUsersCount(sysUsers);
			
		String jsonString ="{ \"totalCount\":"+totalCount+", \"records\":[";
		for(SysUsers sysUser:sysUsersList){
			jsonString+="{\"userId\":\""+sysUser.getUserId()+"\",";
			jsonString+="\"userAccount\":\""+sysUser.getUserAccount()+"\",";
			jsonString+="\"userName\":\""+sysUser.getUserName()+"\",";
			jsonString+="\"userPassword\":\""+sysUser.getUserPassword()+"\",";
			jsonString+="\"userDesc\":\""+sysUser.getUserDesc()+"\",";
			
			jsonString+="\"issys\":\""+sysUser.getIssys()+"\",";
			jsonString+="\"userDept\":\""+sysUser.getUserDept()+"\",";
			jsonString+="\"userDuty\":\""+sysUser.getUserDuty()+"\",";
			jsonString+="\"userMobile\":\""+sysUser.getUserMobile()+"\",";
			jsonString+="\"userOrder\":\""+sysUser.getUserOrder()+"\",";
			jsonString+="\"userDesktop\":\""+sysUser.getUserDesktop()+"\",";
			jsonString+="\"subSystem\":\""+sysUser.getSubSystem()+"\"},";
			
		}
		if(sysUsersList.size()!=0)
		{jsonString=",".equals(jsonString.substring(jsonString.length()-1))?jsonString.substring(0, jsonString.length()-1):jsonString;}
		jsonString+="]}";
		return jsonString;
	}
	@RequestMapping(value = "/admin/sys/manageUserDelete")
	@ResponseBody
	public Map<String, String> deleteUser(@RequestBody SysUsers sysUsers, ModelMap model) {
		Map<String, String> imsg = new HashMap<String, String>();
		try {
			securityDaoService.deleteUsers(sysUsers.getUserId());
			imsg.put("success", "true");
		} catch (Exception e) {
			imsg.put("success", "false");
			e.printStackTrace();
		}
		
		
		return imsg;
	}
	

	@RequestMapping(value = "/admin/sys/manageUserSave")
	@ResponseBody
	public Map<String, Object> saveUser(
			@RequestBody SysUsers sysUsers, ModelMap model) {
		FillBeanProperyAsNUllForMybatis.fillNull(sysUsers);
		SysUsers tempUser=sysUsers;
		Map<String, Object> imsg = new HashMap<String, Object>();
		try {
			int selectString=securityDaoService.selectUsers(sysUsers.getUserId()).size();
			if(0 < selectString){				
				securityDaoService.updateUsers(sysUsers);
			}else{
				securityDaoService.insertUsers(sysUsers);
				tempUser.setId(sysUsers.getId());
			}
			
			imsg.put("success", "true");
			imsg.put("records", tempUser);
			
		} catch (Exception e) {
			imsg.put("success", "false");
			e.printStackTrace();
		}

		return imsg;
	}
	@RequestMapping(value = "/admin/sys/UserDeparmentQuery")
	@ResponseBody
	public String moduleTree(@RequestParam("id") String id)
			throws JsonProcessingException, IOException {
		jsonTree.setPID(id);
		String jsonString = jsonTree.getTreeJSONString();
		return jsonString;
	}
	@RequestMapping(value = "/admin/sys/desktop/modifyPwd")
	@ResponseBody
	public Map<String,Object> modifyPwd(String newpass1){
		Map<String,Object> iMap =new HashMap<String,Object>();
		SysUsers user=(SysUsers)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SysUsers sysUsers = new SysUsers();
		sysUsers.setUserAccount(user.getUserAccount());
		String md5Pass=Md5Encoder.md5(newpass1);
		sysUsers.setUserPassword(newpass1);
		user.setUserPassword(md5Pass);
		try {
			securityDaoService.updateUser(sysUsers);
			iMap.put("success", true);
		} catch (Exception e) {
			iMap.put("success", false);
			e.printStackTrace();
		}
		return iMap;
	}
	
}
