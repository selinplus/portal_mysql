package org.ytgs.portal.wechat.distributor.model;

/**
 * 分销商登记表
 * @author ICE
 *
 */
public class Distributor {

	@Override
	public String toString() {
		return "Distributor [id=" + id + ", userId=" + userId + ", parentId="
				+ parentId + ", phone=" + phone + ", wechatNum=" + wechatNum
				+ ", realName=" + realName + ", disState=" + disState
				+ ", createTime=" + createTime + ", level=" + level + "]";
	}

	private int id;//分销中心编号
	
	private int userId;//登记用户编码
	
	private int parentId;//申请介绍人编号
	
	private String phone;//登记人电话
	
	private String wechatNum;//微信号码
	
	private String realName;//昵称
	
	private int disState;//用户当前状态
	
	private String createTime;//创建时间
	
	private int level;//分销商等级

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getWechatNum() {
		return wechatNum;
	}

	public void setWechatNum(String wechatNum) {
		this.wechatNum = wechatNum;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public int getDisState() {
		return disState;
	}

	public void setDisState(int disState) {
		this.disState = disState;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	
}
