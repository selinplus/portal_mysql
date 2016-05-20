package org.ytgs.portal.wechat.product.model;

import java.sql.Timestamp;

/**
 * 商品评价表对应的bean
 * 
 * @author Administrator
 * 
 */
public class ProductEvaluation {
	private Integer id; // '主键',
	private Integer userId; // '用户ID',
	private Integer productId;// '商品ID',
	private Integer stars;// '评价星级',
	private String comment;// '评价内容',
	private Timestamp evaluationTime;// '评价时间',
	private Integer state;// '评价状态，有效与否',

	// getter and setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getStars() {
		return stars;
	}

	public void setStars(Integer stars) {
		this.stars = stars;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Timestamp getEvaluationTime() {
		return evaluationTime;
	}

	public void setEvaluationTime(Timestamp evaluationTime) {
		this.evaluationTime = evaluationTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * 测试使用
	 */
	@Override
	public String toString() {
		return "ProductEvaluation [id=" + id + ", userId=" + userId
				+ ", productId=" + productId + ", stars=" + stars
				+ ", comment=" + comment + ", evaluationTime=" + evaluationTime
				+ ", state=" + state + "]";
	}
}
