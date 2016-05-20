package org.ytgs.portal.wechat.product.model;

import java.sql.Timestamp;
import java.util.List;

/**
 * 对应product_info表的一条记录--bean
 * 
 * @author Administrator
 * 
 */
public class ProductInfo {
	private Integer id; // 主键
	private String item; // 商品名称
	private String desc; // 商品描述
	private String co; // 原产地
	private String overpack;// 包装类型
	private String varity;// 品种
	private String deliveryMode;// 配送方式
	private String deliveryRange;// 配送范围
	private Double kg;// 商品重量
	private String storageMode; // 储藏方式
	private String remarks; // 备注
	private Short state;// 商品状态
	private Double originPrice;// 原价
	private Double commonDiscount;// 普通折扣
	private Double commonPrice;// 普通价格
	private Double memberDiscount; // 会员折扣
	private Double memberPrice; // 会员价格
	private String auditId; // 处理人ID
	private Timestamp auditTime; // 处理时间

	private List<ProductStock> productStocks;// 该商品对应的库存记录

	// 为了更方便sql语句的组合

	private Integer type;// 所有商品列表按照新品1、销量升序2降序3、价格升序4降序5排序

	// getter and setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOverpack() {
		return overpack;
	}

	public void setOverpack(String overpack) {
		this.overpack = overpack;
	}

	public String getVarity() {
		return varity;
	}

	public void setVarity(String varity) {
		this.varity = varity;
	}

	public String getDeliveryMode() {
		return deliveryMode;
	}

	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}

	public String getDeliveryRange() {
		return deliveryRange;
	}

	public void setDeliveryRange(String deliveryRange) {
		this.deliveryRange = deliveryRange;
	}

	public Double getKg() {
		return kg;
	}

	public void setKg(Double kg) {
		this.kg = kg;
	}

	public String getStorageMode() {
		return storageMode;
	}

	public void setStorageMode(String storageMode) {
		this.storageMode = storageMode;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Double getOriginPrice() {
		return originPrice;
	}

	public void setOriginPrice(Double originPrice) {
		this.originPrice = originPrice;
	}

	public Double getCommonDiscount() {
		return commonDiscount;
	}

	public void setCommonDiscount(Double commonDiscount) {
		this.commonDiscount = commonDiscount;
	}

	public Double getCommonPrice() {
		return commonPrice;
	}

	public void setCommonPrice(Double commonPrice) {
		this.commonPrice = commonPrice;
	}

	public Double getMemberDiscount() {
		return memberDiscount;
	}

	public void setMemberDiscount(Double memberDiscount) {
		this.memberDiscount = memberDiscount;
	}

	public Double getMemberPrice() {
		return memberPrice;
	}

	public void setMemberPrice(Double memberPrice) {
		this.memberPrice = memberPrice;
	}

	public String getAuditId() {
		return auditId;
	}

	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}

	public Timestamp getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Timestamp auditTime) {
		this.auditTime = auditTime;
	}

	public String getCo() {
		return co;
	}

	public void setCo(String co) {
		this.co = co;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public List<ProductStock> getProductStocks() {
		return productStocks;
	}

	public void setProductStocks(List<ProductStock> productStocks) {
		this.productStocks = productStocks;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 测试使用
	 */
	@Override
	public String toString() {
		return "ProductInfo [id=" + id + ", item=" + item + ", desc=" + desc
				+ ", co=" + co + ", overpack=" + overpack + ", varity="
				+ varity + ", deliveryMode=" + deliveryMode
				+ ", deliveryRange=" + deliveryRange + ", kg=" + kg
				+ ", storageMode=" + storageMode + ", remarks=" + remarks
				+ ", state=" + state + ", originPrice=" + originPrice
				+ ", commonDiscount=" + commonDiscount + ", commonPrice="
				+ commonPrice + ", memberDiscount=" + memberDiscount
				+ ", memberPrice=" + memberPrice + ", auditId=" + auditId
				+ ", auditTime=" + auditTime + ", productStocks="
				+ productStocks + ", type=" + type + "]";
	}

}
