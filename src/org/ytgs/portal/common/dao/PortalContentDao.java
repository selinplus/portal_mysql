package org.ytgs.portal.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.ytgs.portal.common.model.Content;

public interface PortalContentDao {
public void insertContent(Content content);
public void deleteContent(int id);
public void updateContent(Content content);
public Content getContentById(int id);
public List<Content> getArtListByCatagoryId(int id);
public int getArtCountByCatagoryId(int id);
public List<Content> getArtPageListByCatagoryIdPageNum(@Param("catagoryId") int catagoryId,@Param("page") int page,@Param("limit") int limit );
public List<Content> getTopArtListByCatagoryId(@Param("subId") int subId,@Param("topicTopNum") int topicTopNum); 
public void setBzZd(int catagoryId);
public List<Content> getTopPicListByCatagoryId(@Param("subId")int subId,@Param("topicTopNum") int topicTopNum);
public int getColIdByContentId(int id);
//public List<Content> getPicNewsByTime(@Param("idArray")int[] idArray,@Param("picNewsTopNum")int picNewsTopNum);
public List<Content> getPicNewsByTime(Map<String,Object> params);
public Content getPicNewByCatagoryId(int catagoryId);
public List<Content> getPicNewsByCatagoryId(@Param("catagoryId")int catagoryId,@Param("topNum")int topNum);
public Content getSssrByCatagoryId(int catagoryId);
public List<Content> getContentsByCatagoryId(@Param("catagoryId")int catagoryId, @Param("contentTopNum")int contentTopNum);
//龙口王维纲站内搜索 start
public List<Content> getArtPageListByKeyWordPageNum(@Param("keyWord") String keyWord,@Param("page") int page,@Param("limit") int limit );
public int getTotalCountByKeyWord(@Param("keyWord") String keyWord);
//龙口王维纲站内搜索 end
public Content getElecCardHtmlUrlByTitleAndTopicId(@Param("name")String name, @Param("id")int elecId);

}
