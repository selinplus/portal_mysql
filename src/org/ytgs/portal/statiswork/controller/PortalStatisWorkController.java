package org.ytgs.portal.statiswork.controller;


import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ytgs.common.tree.TreeUtil;
import org.ytgs.portal.common.model.Catagory;
import org.ytgs.portal.common.model.Content;
import org.ytgs.portal.common.service.ArtColHtmlOutputService;
import org.ytgs.portal.common.service.PortalPublishService;
import org.ytgs.portal.common.service.TopicOutPutHtmlService;
import org.ytgs.portal.statiswork.model.TotalContent;
import org.ytgs.portal.statiswork.service.CatalogService;
import org.ytgs.util.ConfigInfo;
import org.ytgs.util.GenerateFileName;
import org.ytgs.util.MyUser;

@Controller
public class PortalStatisWorkController {
	@Autowired
	CatalogService catalogService;
	@Autowired
	MyUser myUser;

	@RequestMapping(value = "/admin/portal/statisWork/statisWork")
	public String noticePublish(ModelMap model) {
		return "/admin/portal/statisWork/statisWork";
	}
	@RequestMapping(value = "admin/portal/statisWork/statisQuery")
	@ResponseBody
	public String statisQuery(String sssqQ, String sssqZ, String deptID) throws JsonProcessingException,
			IOException {
		
		List<TotalContent> totalList = null;
		String swjgBz=myUser.getDeptBz();
		if ("ytsj".equals(swjgBz))
		{totalList=catalogService.getYtsj(sssqQ, sssqZ);}
		if("xsqj".equals(swjgBz))
			{totalList=catalogService.getXsqj(deptID, sssqQ, sssqZ);}
		String jsonString =  "{\"records\":[";
		for (TotalContent content : totalList) {
			jsonString += "{\"deptName\":\"" + content.getDeptName() + "\",";
			jsonString += "\"count\":\"" + content.getCount() + "\"},";
			
		}
		jsonString = totalList.size() != 0 ? jsonString.substring(0,
				jsonString.length() - 1) : jsonString;
		jsonString += "]}";
		return jsonString;
	}
	
	
}
