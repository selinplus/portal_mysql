package org.ytgs.portal.wechat.product.model;

import java.sql.Timestamp;

/**
 * 商品库存记录的bean对象
 * 
 * @author Administrator
 * 
 */
public class ProductStock {
	private Integer id; // '主键ID',
	private Integer productId; // '商品ID',
	private Integer originQuantity; // '原来商品数量',
	private Integer drawQuantity;// '改变的商品数量，可为负值',
	private Integer residualQuantity;// '剩余商品数量',
	private Integer stockQuantity; // '商品总的进货量',
	private Integer salesVolume;// '商品总的出货量,销量',
	private Integer auditId;// '处理人ID',
	private Timestamp auditTime;// '处理时间',
	private String auditDesc;// '处理人描述',

	// getter and setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getOriginQuantity() {
		return originQuantity;
	}

	public void setOriginQuantity(Integer originQuantity) {
		this.originQuantity = originQuantity;
	}

	public Integer getDrawQuantity() {
		return drawQuantity;
	}

	public void setDrawQuantity(Integer drawQuantity) {
		this.drawQuantity = drawQuantity;
	}

	public Integer getResidualQuantity() {
		return residualQuantity;
	}

	public void setResidualQuantity(Integer residualQuantity) {
		this.residualQuantity = residualQuantity;
	}

	public Integer getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public Integer getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(Integer salesVolume) {
		this.salesVolume = salesVolume;
	}

	public Integer getAuditId() {
		return auditId;
	}

	public void setAuditId(Integer auditId) {
		this.auditId = auditId;
	}

	public Timestamp getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Timestamp auditTime) {
		this.auditTime = auditTime;
	}

	public String getAuditDesc() {
		return auditDesc;
	}

	public void setAuditDesc(String auditDesc) {
		this.auditDesc = auditDesc;
	}

	/**
	 * 测试用
	 */
	@Override
	public String toString() {
		return "ProductStock [id=" + id + ", productId=" + productId
				+ ", originQuantity=" + originQuantity + ", drawQuantity="
				+ drawQuantity + ", residualQuantity=" + residualQuantity
				+ ", stockQuantity=" + stockQuantity + ", salesVolume="
				+ salesVolume + ", auditId=" + auditId + ", auditTime="
				+ auditTime + ", auditDesc=" + auditDesc + "]";
	}

}
