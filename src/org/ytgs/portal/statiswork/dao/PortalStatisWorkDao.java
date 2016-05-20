package org.ytgs.portal.statiswork.dao;

import java.util.List;



import org.apache.ibatis.annotations.Param;
import org.ytgs.portal.common.model.Content;
import org.ytgs.portal.statiswork.model.TotalContent;

public interface PortalStatisWorkDao {
public void insertContent(Content content);
public void deleteContent(int id);
public void updateContent(Content content);
public Content getContentById(int id);
public Content getStatiswordContent(int id);
public String getDeptDl(String id);
public List<TotalContent>getYtsj( @Param("sssq_q" ) String sssqQ,@Param("sssq_z") String sssqZ);
public List<TotalContent>getXsqj(@Param("sssq_q" ) String sssqQ,@Param("sssq_z") String sssqZ,@Param("deptID" ) String deptId);
public List<Content> getArtListByCatagoryId(int id);
public int getArtCountByCatagoryId(int id);
public List<Content> getArtPageListByCatagoryIdPageNum(@Param("catagoryId") int catagoryId,@Param("page") int page,@Param("limit") int limit );
public List<Content> getTopArtListByCatagoryId(@Param("subId") int subId,@Param("topicTopNum") int topicTopNum); 
public void setBzZd(int catagoryId);
public List<Content> getTopPicListByCatagoryId(@Param("subId")int subId,@Param("topicTopNum") int topicTopNum);
public int getColIdByContentId(int id);
}
