package org.ytgs.portal.wechat.product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ytgs.portal.wechat.product.model.ProductEvaluation;
import org.ytgs.portal.wechat.product.model.ProductInfo;
import org.ytgs.portal.wechat.product.service.ProductService;
import org.ytgs.util.Console;

/**
 * 商品模块的控制器
 * 
 * @author Administrator
 * 
 */
@Controller
public class ProductController {
	// 商品模块的服务类
	@Autowired
	ProductService productService;

	/**
	 * 跳转到所有商品页面的连接
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/products")
	public String products() {
		Console.output("请求所有商品页面");
		return "/wechat_business/products";
	}

	/**
	 * 根据商品id跳转到具体商品的页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/product/id/{value}")
	public String products(@PathVariable Integer value,
			HttpServletRequest request) {
		Console.output("某个商品:" + value);
		try {
			// 商品的参数
			ProductInfo pi = new ProductInfo();
			Short state = 1;
			pi.setState(state);
			pi.setId(value);
			// 查询
			pi = productService.queryProductById(pi);
			Console.output(pi);
			// 商品名称以及id绑定
			request.setAttribute("item", pi.getItem());
			request.setAttribute("id", pi.getId());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("item", "查询出错");
			request.setAttribute("id", -1);
		}
		return "/wechat_business/productDetail";
	}

	/**
	 * 查询首页的有效商品信息--基本信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/products/query/index")
	@ResponseBody
	public Map<String, Object> queryIndexProducts() {
		Console.output("queryIndexProducts");
		Map<String, Object> map = null;
		try {
			map = new HashMap<String, Object>();
			List<ProductInfo> lists = productService.queryIndexProducts();
			Console.output(lists);
			map.put("data", lists);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
		return map;
	}

	/**
	 * 查询所有有效的商品记录--包含商品的基本信息以及存货信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/products/query/valid")
	@ResponseBody
	public Map<String, Object> queryValidProducts(String type) {
		Console.output("queryValidProducts");
		Map<String, Object> map = null;
		try {
			// 商品的参数
			ProductInfo pi = new ProductInfo();
			Short state = 1;
			pi.setState(state);
			pi.setType(Integer.parseInt(type));
			map = new HashMap<String, Object>();
			// 查询
			List<ProductInfo> lists = productService.queryProducts(pi);
			Console.output(lists);
			map.put("data", lists);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
		return map;
	}

	/**
	 * 商品详细参数页面，查询商品的信息
	 * 
	 * @param value
	 * @return
	 */
	@RequestMapping(value = "/products/query/id/{value}")
	@ResponseBody
	public Map<String, Object> queryProductById(@PathVariable Integer value,
			HttpServletRequest request) {
		Console.output("queryProductById");
		Map<String, Object> map = null;
		try {
			// 商品的参数
			ProductInfo pi = new ProductInfo();
			Short state = 1;
			pi.setState(state);
			pi.setId(value);
			map = new HashMap<String, Object>();
			// 查询
			pi = productService.queryProductById(pi);
			Console.output(pi);
			map.put("data", pi);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
		return map;
	}

	/**
	 * 按照商品id查询第page页的评论
	 * 
	 * @param productId
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/evaluation/query/{productId}/{page}")
	@ResponseBody
	public Map<String, Object> queryEvaluationByPage(
			@PathVariable Integer productId, @PathVariable Integer page) {
		Console.output("queryEvaluationByPage");
		Map<String, Object> map = null;
		try {
			// 商品的参数
			ProductInfo pi = new ProductInfo();
			Short state = 1;
			pi.setState(state);
			pi.setId(productId);
			map = new HashMap<String, Object>();
			// 查询
			List<ProductEvaluation> lists = productService.queryEvaluationByPage(pi,page);
			Console.output(lists);
			map.put("data", lists);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
		return map;
	}

}
