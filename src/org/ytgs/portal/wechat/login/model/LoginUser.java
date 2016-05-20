package org.ytgs.portal.wechat.login.model;

/**
 * 微信登录帐号 + 分销商登记信息
 * @author ICE
 *
 */
public class LoginUser {

	public int id;//用户编号
	
	public String wechatPubNum;//微信公众号
	
	public String wechatNum;//微信号码
	
	public String nickName;//昵称
	
	public String image;//图片流
	
	public String imgPath;//用户图片路径
	
	public int userState;//用户状态
	
	public int disId;//分销商编号
	
	public int parentId;//上级介绍人编号
	
	public String phone;//电话号码
	
	public String appWechatNum;//申请公众号
	
	public String realName;//真实姓名
	
	public int level;//商铺级别
	
	public String disCreateTime;//分销商创建时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWechatPubNum() {
		return wechatPubNum;
	}

	public void setWechatPubNum(String wechatPubNum) {
		this.wechatPubNum = wechatPubNum;
	}

	public String getWechatNum() {
		return wechatNum;
	}

	public void setWechatNum(String wechatNum) {
		this.wechatNum = wechatNum;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public int getUserState() {
		return userState;
	}

	public void setUserState(int userState) {
		this.userState = userState;
	}

	public int getDisId() {
		return disId;
	}

	public void setDisId(int disId) {
		this.disId = disId;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAppWechatNum() {
		return appWechatNum;
	}

	public void setAppWechatNum(String appWechatNum) {
		this.appWechatNum = appWechatNum;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getDisCreateTime() {
		return disCreateTime;
	}

	public void setDisCreateTime(String disCreateTime) {
		this.disCreateTime = disCreateTime;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "LoginUser [id=" + id + ", wechatPubNum=" + wechatPubNum
				+ ", wechatNum=" + wechatNum + ", nickName=" + nickName
				+ ", image=" + image + ", imgPath=" + imgPath + ", userState="
				+ userState + ", disId=" + disId + ", parentId=" + parentId
				+ ", phone=" + phone + ", appWechatNum=" + appWechatNum
				+ ", realName=" + realName + ", level=" + level
				+ ", disCreateTime=" + disCreateTime + "]";
	}
	
}
