package org.ytgs.xzjx.dao;

import java.util.List;

import org.ytgs.xzjx.model.XzjxGztz;

public interface XzjxGztzMapper {
	void deleteByPrimaryKey(String id);

    int insert(XzjxGztz record);

    int insertSelective(XzjxGztz record);

    XzjxGztz selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(XzjxGztz record);

    int updateByPrimaryKey(XzjxGztz record);

	int selectGztzMxCount(XzjxGztz xzjxGztz);

	List<XzjxGztz> selectGztzMxByPage(XzjxGztz xzjxGztz);

	List<XzjxGztz> selectGztzMxByPageall(XzjxGztz xzjxGztz);

	XzjxGztz getdeptinfo(String id);

	List<XzjxGztz> selectGztzMxviewByPage(XzjxGztz xzjxGztz);

	int selectGztzviewMxCount(XzjxGztz xzjxGztz);
	int selectNoticeListCount(XzjxGztz xzjxGztz);

	List<XzjxGztz> selectNoticeListByPage(XzjxGztz xzjxGztz);
}