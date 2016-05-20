package org.ytgs.portal.wechat.distributor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ytgs.portal.wechat.distributor.dao.DistributorDao;
import org.ytgs.portal.wechat.distributor.model.Distributor;

/**
 * 
 * 分销商服务类
 * @author ICE
 *
 */
@Service
public class DistributorService {

	@Autowired
	DistributorDao disDao;
	
	
	/**
	 * 插入分销商信息
	 * @param wechatnum
	 */
	public void insertVendorInf(Distributor dis){
		disDao.insertVendorInf(dis);
	}
}
