package org.ytgs.portal.wechat.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.ytgs.portal.wechat.login.dao.LoginDao;
import org.ytgs.portal.wechat.login.model.LoginUser;

@Service
public class LoginService {

	@Autowired
	LoginDao loginDao;

	public LoginUser getLoginUserInfo(int disId) {
		LoginUser user = new LoginUser();		
		user = loginDao.getLoginUserInfo(disId);
		return user;
	}
	
	
}
