package org.ytgs.util;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.ytgs.portal.statiswork.service.CatalogService;
import org.ytgs.security.CustomUserDetails;

@Component
public class MyUser {
@Autowired
CatalogService catalogService;
	public  CustomUserDetails getUser(){
		
		CustomUserDetails user=(CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}
public  String  getDeptBz() {
	CustomUserDetails userDetails=getUser();
	String deptId= userDetails.getUserDept();	
	String deptDl=catalogService.getDeptDl(deptId);
	String  deptBz="xsqj";
	if("0000".equals(deptId.substring(7)) && "N".equals(deptDl))
	{   
		
		deptBz= "ytsj";
	}
	if("000000".equals(deptId.substring(5)))
	{
		deptBz= "ytsj";
	}
	if("00000000000".equals(deptId))
	{
		deptBz= "ytsj";
	}
	return deptBz;
	
}
}
