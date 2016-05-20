package org.ytgs.portal.wechat.login.dao;

import org.ytgs.portal.wechat.login.model.LoginUser;

public interface LoginDao {

	LoginUser getLoginUserInfo(int disId);
}
