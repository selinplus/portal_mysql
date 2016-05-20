package org.ytgs.portal.wechat.distributor.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ytgs.portal.wechat.distributor.model.Distributor;
import org.ytgs.portal.wechat.distributor.service.DistributorService;
import org.ytgs.util.Console;

/**
 * 分销商控制类
 * 
 * @author ICE
 * 
 */
@Controller
public class DistributorController {

	@Autowired
	DistributorService disService;

	// 分销商页面
	@RequestMapping(value = "/vendor")
	public String vendor(ModelMap model) {
		Console.output("请求分销商页面");
		return "/wechat_business/vendor";
	}

	/**
	 * 提交分销商信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/vendor/insertVendorInf")
	@ResponseBody
	public Map<String, Object> insertVendorInf(String name, String phone,
			String wechatnum, int parentId, int userId, int level) {		
		Map<String, Object> data = new HashMap<String, Object>();
		Console.output("添加分销商信息");
		try {
			Distributor distributor = new Distributor();
			distributor.setLevel(level);
			distributor.setParentId(parentId);
			distributor.setPhone(phone);
			distributor.setRealName(name);
			distributor.setUserId(userId);
			distributor.setWechatNum(wechatnum);
			disService.insertVendorInf(distributor);
			System.out.println(distributor);
			data.put("success", true);
			data.put("message", "分销商信息录入成功");
		} catch (Exception e) {
			data.put("success", "false");
			data.put("message", "请检查分销商数据");
			e.printStackTrace();
		}
				
		return data;
	}
}
