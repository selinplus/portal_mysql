package org.ytgs.system.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ytgs.portal.common.service.PortalPublishService;
import org.ytgs.security.CustomUserDetails;
import org.ytgs.security.service.SecurityDaoService;
import org.ytgs.system.model.SysDept;
import org.ytgs.system.model.SysDesktop;
import org.ytgs.system.service.SystemDaoService;
import org.ytgs.xzjx.model.XzjxGztz;
import org.ytgs.xzjx.service.XzjxGztzService;

@Controller
public class SysDesktopController {

	@Autowired
	public SystemDaoService systemDaoService;
	@Autowired
	PortalPublishService portalPublishService;
	
	@Autowired
	XzjxGztzService xzjxGztzService;
	@Autowired
	public SecurityDaoService securityDaoService;
	@RequestMapping(value = "admin/deskNotice")
	public String deskNotice(ModelMap model) {
		return "/admin/deskNotice";
	}

	@RequestMapping(value = "admin/zqrl")
	public String zqrl(ModelMap model) {
		return "/admin/zqrl";
	}

	@RequestMapping(value = "admin/xzjx/gztz/gztz")
	public String gztz(ModelMap model) {
		return "/admin/xzjx/gztz/gztz";
	}

	@RequestMapping(value = "/admin/desktop/shortcuts")
	@ResponseBody
	public Map<String, Object> getDeskShortCuts(
			@RequestParam("userAccount") String userAccount,
			HttpServletRequest request) throws JsonProcessingException,
			IOException {
		// List<Notice> artList = null;
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		XzjxGztz xzjxGztz = new XzjxGztz();
		xzjxGztz.setFbrDeptDm(user.getUserDept());
		xzjxGztz.setFbrDm(user.getUserAccount());
		xzjxGztz.setFbrMc(user.getUserName());
		xzjxGztzService.selectdeptinfo(xzjxGztz);
		xzjxGztzService.prepareCondition(xzjxGztz);// 预处理查询条件，，处理空条件
		List<XzjxGztz> artList = new ArrayList<XzjxGztz>();
		
		SysDesktop sysDesktop = new SysDesktop();
		// 桌面通知准备                           

		artList = xzjxGztzService.getXzjxGztzviewMxByPage(xzjxGztz);
		sysDesktop.setNoticeList(artList);
		
		
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		sysDesktop.setBasePath(basePath);
		Map<String, Object> imMap = new HashMap<String, Object>();
		imMap.put("success", "true");
		imMap.put("shortcuts", sysDesktop);
		return imMap;
	}

	@RequestMapping(value = "admin/desktop/noticePage")
	public String noticePage(ModelMap model, String id) {
		XzjxGztz xzjxGztz = xzjxGztzService.getContentById(id);
		model.put("xzjxGztz", xzjxGztz);
		return "/admin/desktop/noticePage";
	}

	@RequestMapping(value = "admin/desktop/noticeQuery")
	public String noticeQuery(ModelMap model) {

		return "/admin/desktop/noticeQuery";
	}

	// 通知明细查询已使用2014.1127 ywh
	@RequestMapping(value = "admin/desktop/noticeListQuery")
	@ResponseBody
	public Map<String, Object> noticeListQuery(XzjxGztz xzjxGztz,
			String searchContent) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<XzjxGztz> resultList = new ArrayList<XzjxGztz>();
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		xzjxGztz.setXxbt(searchContent);
		xzjxGztz.setFbrDm(user.getUserAccount());
		xzjxGztzService.prepareCondition(xzjxGztz);// 预处理查询条件，，处理空条件
		try {
			int count = xzjxGztzService.getNoticeListCount(xzjxGztz);
			resultMap.put("totalCount", count);
			xzjxGztz.setPage(xzjxGztz.getLimit()*(xzjxGztz.getPage()-1));
			resultList = xzjxGztzService.getNoticeListByPage(xzjxGztz);
			resultMap.put("records", resultList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}
	@RequestMapping(value = "admin/desktop/todoMore")
	public String todoMore(ModelMap model) {
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		String deptId = user.getUserDept();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SysDept userDeparts = securityDaoService.getDeparts(deptId);
		model.put("userName", user.getUserName());//用户名
		model.put("userAccount", user.getUserAccount());
		model.put("userDeptId", user.getUserDept());
		model.put("userDeptName", userDeparts.getDeptName());
		model.put("sysDate", sdf.format(new Date()));
		return "/admin/workflow/todo";
	}
	@RequestMapping(value = "admin/desktop/noticeMore")
	public String noticeMore(ModelMap model) {
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		String deptId = user.getUserDept();
		String deptStandId = deptId.substring(0, 7) + "0000";
		SysDept userStandDeparts = securityDaoService.getDeparts(deptStandId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SysDept userDeparts = securityDaoService.getDeparts(deptId);
		model.put("userName", user.getUserName());//用户名
		model.put("userAccount", user.getUserAccount());
		model.put("userDeptId", user.getUserDept());
		model.put("userDeptName", userDeparts.getDeptName());
		model.put("sysDate", sdf.format(new Date()));
		model.put("userDeptStandId", userStandDeparts.getId());
		model.put("userDeptStandName", userStandDeparts.getDeptName());
		return "/admin/xzjx/gztz/view";
	}

}
