package org.ytgs.portal.statiswork.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ytgs.portal.statiswork.dao.PortalStatisWorkDao;
import org.ytgs.portal.statiswork.model.TotalContent;
@Component
public class CatalogService {
@Autowired
PortalStatisWorkDao portalContentDao;

public String getDeptDl(String id)
{
	return  portalContentDao.getDeptDl(id);
 }
public List<TotalContent> getYtsj(String ssqQ,String ssqZ)
{
	return  portalContentDao.getYtsj(ssqQ,ssqZ);
 }

public List<TotalContent> getXsqj(String deptID,String ssqQ,String ssqZ)
{
	return  portalContentDao.getXsqj(deptID, ssqQ,ssqZ);
 }
}
