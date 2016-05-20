package org.ytgs.portal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ytgs.portal.common.dao.PortalCatagoryDao;
import org.ytgs.portal.common.model.Catagory;

@Service
public class CatagoryDaoService {
	@Autowired
	PortalCatagoryDao portalCatagoryDao; 
	public void addCatagory(Catagory catagory)
	{
		portalCatagoryDao.insertCatagory(catagory);
	}
	public void removeCatagory(int id)
	{
		portalCatagoryDao.deleteCatagory(id);
		
	}
	public void editCatagory(Catagory catagory)
	{
		portalCatagoryDao.updateCatagory(catagory);
	}
	public List<Catagory> getCatagoryById(int id)
	{
		return portalCatagoryDao.getCatagoryById(id);
	}
	public List<Catagory> getCatagoryByParentId(int id)
	{
		return portalCatagoryDao.getCatagoryByParentId(id);
	}	
	public List<Catagory> selectCatagory()
	{
		return portalCatagoryDao.selectCatagory();
	}
   public List<Catagory> getModuleChildByParentId(int parentId)
   {
	   return portalCatagoryDao.getModuleChildByParentId(parentId);
   }
   public List<String> getModuleParentId()
   {
	   return portalCatagoryDao.getParentId();
   }
}
