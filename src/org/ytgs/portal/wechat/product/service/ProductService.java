package org.ytgs.portal.wechat.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ytgs.portal.wechat.product.dao.ProductDao;
import org.ytgs.portal.wechat.product.dao.ProductEvaluationDao;
import org.ytgs.portal.wechat.product.model.ProductEvaluation;
import org.ytgs.portal.wechat.product.model.ProductInfo;

/**
 * 商品模块的服务层
 * 
 * @author Administrator
 * 
 */

@Service
public class ProductService {
	// 商品模块的dao类
	@Autowired
	ProductDao productDao;
	@Autowired
	// 商品评论的dao
	ProductEvaluationDao productEvaluationDao;

	/**
	 * 查询所有商品的信息并返回
	 * 
	 * @return
	 */
	public List<ProductInfo> queryAll() {
		return productDao.selectAll();
	}

	/**
	 * 查询首页显示的热门商品的信息
	 * 
	 * @return
	 */
	public List<ProductInfo> queryIndexProducts() {
		return productDao.selectIndexProducts();
	}

	/**
	 * 查询符合条件的商品记录
	 * 
	 * @return
	 */
	public List<ProductInfo> queryProducts(ProductInfo pi) {
		return productDao.selectProducts(pi);
	}

	/**
	 * 根据id查询商品的基本信息
	 * 
	 * @param pi
	 * @return
	 */
	public ProductInfo queryProductById(ProductInfo pi) {
		return productDao.selectProductById(pi);
	}

	/**
	 * 按照商品id查询第page页的评论
	 * 
	 * @param pi
	 * @param page
	 * @return
	 */
	public List<ProductEvaluation> queryEvaluationByPage(ProductInfo pi,
			Integer page) {
		return productEvaluationDao.selectEvaluationByPage(pi, page);
	}
}
