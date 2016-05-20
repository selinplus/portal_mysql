package org.ytgs.portal.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ytgs.portal.service.CatagoryDaoService; 
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.ytgs.portal.tree.CatagoryModulesTree;
import org.ytgs.portal.common.model.Catagory;
import org.ytgs.security.model.SysRolesAuthorities;
import org.ytgs.security.service.SecurityDaoService;

@Controller
public class CatagoryController {

	@Autowired
	public CatagoryDaoService catagoryDaoService;
	@Autowired
	public CatagoryModulesTree jsonTree;	
	@Autowired
	public Catagory Catagory;
	@Autowired
	SecurityDaoService securityDaoService;
	public ObjectMapper om = new ObjectMapper();
		
	@RequestMapping(value = "/admin/portal/manageCatagoryQuery")
	@ResponseBody
	public String getCatagory(int id,String leaf,ModelMap model) throws IOException {
		
		List<Catagory> CatagoryList =null;
		
		CatagoryList=catagoryDaoService.getCatagoryById(id);
					
		String jsonString ="{ \"totalCount\":"+CatagoryList.size()+", \"records\":[";
		for(Catagory catagory:CatagoryList){
			jsonString+="{\"id\":\""+catagory.getId()+"\",";
			jsonString+="\"parentId\":\""+catagory.getParentId()+"\",";
			jsonString+="\"catagoryName\":\""+catagory.getCatagoryName()+"\",";
			jsonString+="\"catagoryType\":\""+catagory.getCatagoryType()+"\",";
			jsonString+="\"catagoryUrl\":\""+catagory.getCatagoryUrl()+"\",";
			jsonString+="\"pubType\":\""+catagory.getPubType()+"\",";
			jsonString+="\"pubUrl\":\""+catagory.getPubUrl()+"\",";
			jsonString+="\"pubTime\":\""+catagory.getPubTime()+"\",";
			jsonString+="\"updateTime\":\""+catagory.getUpdateTime()+"\",";
			jsonString+="\"catagoryDesc\":\""+catagory.getCatagoryDesc()+"\",";
			jsonString+="\"jfXx\":\""+catagory.getJfXx()+"\",";
			jsonString+="\"jfTp\":\""+catagory.getJfTp()+"\",";
			jsonString+="\"bzQy\":\""+catagory.getBzQy()+"\",";
			jsonString+="\"bzTj\":\""+catagory.getBzTj()+"\",";
			jsonString+="\"bzSh\":\""+catagory.getBzSh()+"\",";
			jsonString+="\"pageModal\":\""+catagory.getPageModal()+"\",";
			jsonString+="\"articleModal\":\""+catagory.getArticleModal()+"\",";
			jsonString+="\"deptId\":\""+catagory.getDeptId()+"\",";
			jsonString+="\"catagoryPic\":\""+catagory.getCatagoryPic()+"\",";
			jsonString+="\"deptName\":\""+catagory.getDeptName()+"\"},";
			
		}
		jsonString=",".equals(jsonString.substring(jsonString.length()-1))?jsonString.substring(0, jsonString.length()-1):jsonString;
		jsonString+="]}";
		return jsonString;
	}
	@RequestMapping(value = "/admin/portal/manageCatagoryDelete")
	@ResponseBody
	public Map<String, String> deleteCatagory(@RequestBody Catagory catagory, ModelMap model) {
		Map<String, String> imsg = new HashMap<String, String>();
		try {
			catagoryDaoService.removeCatagory(catagory.getId());
			//同时删除系统管理员角色中的对应的权限
			
			securityDaoService.deleteRoleAppByRoleAndAppId("ROLE_PLATFORMADMIN1",String.valueOf(catagory.getId()));
			imsg.put("success", "true");
		} catch (Exception e) {
			imsg.put("success", "false");
			e.printStackTrace();
		}
		
		
		return imsg;
	}
	

	@RequestMapping(value = "/admin/portal/manageCatagorySave")
	@ResponseBody
	public Map<String, Object> saveCatagory(
			@RequestBody Catagory catagory, ModelMap model) {
		Map<String, Object> imsg = new HashMap<String, Object>();
		try {
			int selectString=catagoryDaoService.getCatagoryById(catagory.getId()).size();
			//catagory为新增无id
			Catagory tmpCatagory=catagory;

			if(selectString!=0){
				catagoryDaoService.editCatagory(catagory);
			}else{
				catagoryDaoService.addCatagory(catagory);
				//增加栏目同时添加到管理员角色表中
				SysRolesAuthorities roleAuthority=new SysRolesAuthorities();
				roleAuthority.setRoleId("ROLE_PLATFORMADMIN1");
				roleAuthority.setAuthorityDesc(catagory.getCatagoryName());
				roleAuthority.setAuthorityId(String.valueOf( catagory.getId()));
				securityDaoService.insertRoleApp(roleAuthority);
				//新增后当前catagory里在catagory.xml里定义只返回id,tmpCatagory.setId后tmpCatagory为完整记录。
				tmpCatagory.setId(catagory.getId());
			}
			imsg.put("success", "true");
			imsg.put("records", tmpCatagory);
		} catch (Exception e) {
			imsg.put("success", "false");
			e.printStackTrace();
		}

		return imsg;
	}
	
	@RequestMapping(value = "/portal/moduleTree")
	@ResponseBody
	public String moduleTree(@RequestParam("id") String id, ModelMap model)
			throws JsonProcessingException, IOException {
		jsonTree.setPID(id);
		String jsonString = jsonTree.getTreeJSONString();
		return jsonString;
	}
	
}

