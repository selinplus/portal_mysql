package org.ytgs.portal.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.ytgs.portal.common.model.Catagory;

public interface PortalCatagoryDao {

public Catagory getTemplateByCatagroyId(int id);
public String  getColPathById(int id);

public Catagory getTopicPathById(int catagoryId);
public List<Catagory> getSubIdListByTopicId(int catagoryId); 
public List<Catagory> getNavigateByCatagoryId(int catagoryId); 
public List<Catagory> getCardCataroyByCatagroyId(int catagoryId);
public List<Catagory> getCatagoryListByIdArray(int[] idArray);
public List<Catagory> getTopicCatagory(@Param("topicTopNum")int topicTopNum,@Param("indexId")int indexId);
public List<Catagory> getTopicPicCatagory(@Param("catagoryId")int catagoryId);

public List<Catagory> selectCatagory();
public void   insertCatagory(Catagory  Catagorys );
public void   updateCatagory(Catagory  Catagorys );
public void  deleteCatagory(int id);
public List<Catagory> getCatagoryById(int id);
public List<Catagory> getCatagoryByParentId(int id);
public List<Catagory> getModuleChildByParentId(int ParentId);
public List<String> getParentId();
}
