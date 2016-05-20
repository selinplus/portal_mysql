package org.ytgs.xzjx.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ytgs.util.DepartUtil;
import org.ytgs.xzjx.dao.XzjxGztzMapper;
import org.ytgs.xzjx.model.XzjxGztz;

@Service
public class XzjxGztzService {
	
	@Autowired
	XzjxGztzMapper xzjxGztzMapper;

	public void addxzjxgztz(XzjxGztz xzjxGztz) {
		
		xzjxGztzMapper.insertSelective(xzjxGztz);
		
	}

	public int getXzjxGztzMxCount(XzjxGztz xzjxGztz) {
		return xzjxGztzMapper.selectGztzMxCount(xzjxGztz);
	}

	public List<XzjxGztz> getXzjxGztzMxByPage(XzjxGztz xzjxGztz) {
		 
		return xzjxGztzMapper.selectGztzMxByPage(xzjxGztz);
	}

	public XzjxGztz getContentById(String id) {
		return xzjxGztzMapper.selectByPrimaryKey(id);
	}

	public void removeXzjxGztzMx(String id) {

		xzjxGztzMapper.deleteByPrimaryKey(id);
	}

	public void updateGztzMx(XzjxGztz xzjxGztz) {
		 
		xzjxGztzMapper.updateByPrimaryKey(xzjxGztz);
		
	}

	public int getXzjxGztzqueryMxCount(XzjxGztz xzjxGztz) {
	
		return xzjxGztzMapper.selectGztzMxCount(xzjxGztz);
	}

	public List<XzjxGztz> getXzjxGztzqueryMxByPage(XzjxGztz xzjxGztz) {

		return xzjxGztzMapper.selectGztzMxByPageall(xzjxGztz);
	}

	public XzjxGztz getdeptinfo(String fbrDeptDm) {
		return xzjxGztzMapper.getdeptinfo(fbrDeptDm);
	}

	public List<XzjxGztz> getXzjxGztzviewMxByPage(XzjxGztz xzjxGztz) {
		return xzjxGztzMapper.selectGztzMxviewByPage(xzjxGztz);
	}

	public int getXzjxGztzviewMxCount(XzjxGztz xzjxGztz) {
		return xzjxGztzMapper.selectGztzviewMxCount(xzjxGztz);
	}
	/**
	 * 通知查询查询人机构信息获取
	 * @param 
	 * @return
	 * @throws Exception
	 */
    //	通知查询查询人机构信息获取2014.11.26 ywh
public void selectdeptinfo (XzjxGztz xzjxGztz){
		XzjxGztz xzjxGztzdept=this.getdeptinfo(xzjxGztz.getFbrDeptDm());
		if("13706000000".equals(xzjxGztzdept.getFbrDeptParent()))
		{
			xzjxGztz.setDeptcxdj("ds");//地市级通知查询
			xzjxGztz.setFbrDeptParent(xzjxGztzdept.getFbrDeptParent());
		}
		else
		{
			if(("0000".equals(xzjxGztzdept.getFbrDeptParent().substring(7)))&&("N".equals(xzjxGztzdept.getFbrDeptSx())))
			{
				xzjxGztz.setDeptcxdj("qx");//区县级通知查询
				xzjxGztz.setFbrDeptParent(xzjxGztzdept.getFbrDeptParent());	
				
			}
			else {
				if ("Y".equals(xzjxGztzdept.getFbrDeptSx()))
				{
					xzjxGztz.setDeptcxdj("gl");//区县级管理分局通知查询，暂发现只存在于莱山区
					xzjxGztz.setFbrDeptParent(xzjxGztzdept.getFbrDeptParent());	
				}
				else
				{
				xzjxGztz.setDeptcxdj("fj");//分局级通知查询
				xzjxGztz.setFbrDeptParent(xzjxGztzdept.getFbrDeptParent());	
				}
			}
		}
		
		
	}
	public void prepareCondition(XzjxGztz xzjxGztz) {
		if (!"".equals(xzjxGztz.getXxbt())
				&& xzjxGztz.getXxbt() != null) {
			xzjxGztz.setXxbt("%" + xzjxGztz.getXxbt() + "%");
		}
		if (!"".equals(xzjxGztz.getContent())
				&& xzjxGztz.getContent() != null) {
			xzjxGztz.setContent("%" + xzjxGztz.getContent() + "%");
		}
		if (!"".equals(xzjxGztz.getFbrDeptDm())
				&& xzjxGztz.getFbrDeptDm() != null) {
			xzjxGztz.setFbrDeptDm(DepartUtil.swjgDmStr(xzjxGztz
					.getFbrDeptDm()));
		}	
		if ("".equals(xzjxGztz.getXxbt())) {
			xzjxGztz.setXxbt(null);
		}
		if ("".equals(xzjxGztz.getContent())) {
			xzjxGztz.setContent(null);
		}
		if ("".equals(xzjxGztz.getFbrDeptDm())) {
			xzjxGztz.setFbrDeptDm(null);
		}
		if ("".equals(xzjxGztz.getKsrq())) {
			xzjxGztz.setKsrq(null);
		}
		if ("".equals(xzjxGztz.getJzrq())) {
			xzjxGztz.setJzrq(null);
		}
	}
	/**
	 * 通知发布机构信息补充
	 * @param 
	 * @return
	 * @throws Exception
	 */
    //	通知发布机构信息补充2014.11.26 ywh
	public void insertdeptinfo (XzjxGztz xzjxGztz){
		XzjxGztz xzjxGztzdept=this.getdeptinfo(xzjxGztz.getFbrDeptDm());
		xzjxGztz.setFbrDeptMc(xzjxGztzdept.getFbrDeptMc());
		xzjxGztz.setFbrDeptParent(xzjxGztzdept.getFbrDeptParent());
		xzjxGztz.setFbrDeptSx(xzjxGztzdept.getFbrDeptSx());
		
		
	}
	public List<XzjxGztz> getNoticeListByPage(XzjxGztz xzjxGztz) {
		return xzjxGztzMapper.selectNoticeListByPage(xzjxGztz);
	}

	public int getNoticeListCount(XzjxGztz xzjxGztz) {
		return xzjxGztzMapper.selectNoticeListCount(xzjxGztz);
	}
}