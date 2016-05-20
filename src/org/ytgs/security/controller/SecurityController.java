package org.ytgs.security.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.ytgs.security.CustomUserDetails;
import org.ytgs.security.service.SecurityDaoService;
import org.ytgs.system.model.SysDeparts;
import org.ytgs.system.model.SysDept;
@Controller
public class SecurityController {

	@Autowired
    public SecurityDaoService securityDaoService;
    @RequestMapping(value = "/admin/login")  
    public String login(ModelMap model) { 
    	System.out.println("测试");
    	return "/admin/login";  
    }  
    @RequestMapping(value = "/admin/logfail")  
    public String logfail(ModelMap model) {  
    	return "/admin/logfail";  
    }  
    
    @RequestMapping(value = "/admin/sessionTimeout")  
    public String sessionTimeout(ModelMap model) {  
    	return "/admin/sessionTimeout";  
    }
	
	@RequestMapping(value = "/admin/index")  
    public String mainSpace(ModelMap model){  
		try {
			CustomUserDetails user=(CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String deptId=user.getUserDept();
			SysDept userDeparts = securityDaoService.getDeparts(deptId);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			securityDaoService.updateUserStatus(user.getUserId(),"YES");
			String ols = securityDaoService.onlineUsers();
			model.put("userName", user.getUserName());
			model.put("userAccount", user.getUserAccount());
			model.put("deptName", userDeparts.getDeptName());
			model.put("deptId", userDeparts.getDeptId());
			model.put("sysDate", sdf.format(new Date()));
			model.put("onlineUsers", ols);
			return "/admin/index";
		} catch (Exception e) {
			return "/admin/login";
		}  
    } 
	
	@RequestMapping(value = "/sys/desktop")  
    public String desktop(ModelMap model){  		
		try {
			CustomUserDetails user=(CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String deptId=user.getUserDept();
			SysDept userDeparts = securityDaoService.getDeparts(deptId);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			securityDaoService.updateUserStatus(user.getUserId(),"YES");
			String ols = securityDaoService.onlineUsers();
			model.put("userName", user.getUserName());
			model.put("userAccount", user.getUserAccount());
			model.put("deptName", userDeparts.getDeptName());
			model.put("deptId", userDeparts.getDeptId());
			model.put("sysDate", sdf.format(new Date()));
			model.put("onlineUsers", ols);
			return "/sys/desktop";
		} catch (Exception e) {
			return "/admin/login";
		}  
    } 
	
	@RequestMapping(value = "/sys/logout")  
	public String logout(ModelMap model,HttpServletRequest request){  		
		try {
			CustomUserDetails user=(CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			securityDaoService.updateUserStatus(user.getUserId(),"NO");
			String baseUrlPath = request.getScheme() + "://"
					+ request.getServerName() + request.getContextPath();
			if (request.getServerPort() != 80) {
				baseUrlPath = request.getScheme() + "://" + request.getServerName()
						+ ":" + request.getServerPort() + request.getContextPath();
			}
			return "redirect:"+baseUrlPath+"/j_spring_security_logout";
		} catch (Exception e) {
			
			return "/admin/login";
		}  
	} 
	
}
