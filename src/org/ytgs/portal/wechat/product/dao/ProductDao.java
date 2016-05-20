package org.ytgs.portal.wechat.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.ytgs.portal.wechat.product.model.ProductEvaluation;
import org.ytgs.portal.wechat.product.model.ProductInfo;

/**
 * 商品模块的dao层
 * 
 * @author Administrator
 * 
 */
public interface ProductDao {
	/**
	 * 查询所有商品的信息
	 * 
	 * @return
	 */
	public List<ProductInfo> selectAll();

	/**
	 * 查询首页需要的商品的信息
	 * 
	 * @return
	 */
	public List<ProductInfo> selectIndexProducts();

	/**
	 * 查询满足条件的商品信息
	 * 
	 * @param pi
	 * @return
	 */
	public List<ProductInfo> selectProducts(ProductInfo pi);

	/**
	 * 根据id查询商品的基本信息
	 * 
	 * @param pi
	 * @return
	 */
	public ProductInfo selectProductById(ProductInfo pi);

}
