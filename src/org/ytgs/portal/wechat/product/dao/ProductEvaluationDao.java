package org.ytgs.portal.wechat.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.ytgs.portal.wechat.product.model.ProductEvaluation;
import org.ytgs.portal.wechat.product.model.ProductInfo;

/**
 * 商品评论的dao
 * 
 * @author Administrator
 * 
 */
public interface ProductEvaluationDao {
	/**
	 * 按照商品id查询第page页的评论
	 * 
	 * @param pi
	 * @param page
	 * @return
	 */
	public List<ProductEvaluation> selectEvaluationByPage(
			@Param("pi") ProductInfo pi, @Param("page") Integer page);
}
