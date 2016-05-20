package org.ytgs.portal.wechat.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.ytgs.portal.wechat.login.model.LoginUser;
import org.ytgs.portal.wechat.login.service.LoginService;
import org.ytgs.util.Console;

/**
 * 微信登录获取用户信息 封装Session
 * @author ICE
 *
 */
@Controller
public class LoginController {

	@Autowired
	LoginService loginService;
	
	/**
	 * 跳转到首页的连接
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String home(ModelMap model,HttpServletRequest request) {
		Console.output("请求首页" + request.getParameter("disId"));
		String disId = request.getParameter("disId");
		LoginUser user = new LoginUser();
		
		user = loginService.getLoginUserInfo(Integer.parseInt(disId));		
		System.out.println(user);
		
		//封装分销商Session信息
		HttpSession session = request.getSession();	
		session.setAttribute("disUser", user);
		
		return "/wechat_business/index";
	}
	
}
