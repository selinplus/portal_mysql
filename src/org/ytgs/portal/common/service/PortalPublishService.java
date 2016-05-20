package org.ytgs.portal.common.service;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ytgs.portal.common.dao.PortalCatagoryDao;
import org.ytgs.portal.common.dao.PortalContentDao;
import org.ytgs.portal.common.model.Catagory;
import org.ytgs.portal.common.model.Content;

@Service
public class PortalPublishService {
	@Autowired 
	PortalContentDao portalContentDao; 
	@Autowired 
	PortalCatagoryDao portalCatagoryDao;
	public void addContent(Content content)
	{
		 portalContentDao.insertContent(content);
	}
	public void removeConent(int id)
	{
		portalContentDao.deleteContent(id);
		
	}
	public void editContent(Content content)
	{
		portalContentDao.updateContent(content);
	}
	public Content getContentById(int id)
	{
		return portalContentDao.getContentById(id);
	}
	public Catagory getTemplateById(int id)
	{
		return portalCatagoryDao.getTemplateByCatagroyId(id);
	}
	public List<Content> getArtListByCatagoryId(int id)
	{
		return portalContentDao.getArtListByCatagoryId(id);
	}
	public String getColPathById(int id)
	{
		return portalCatagoryDao.getColPathById(id);
	}
	public int getArtCountByCatagoryId(int id)
	{
		return portalContentDao.getArtCountByCatagoryId(id);
	}
	public List<Content> getArtPageListByCatagoryIdPageNum(int catagoryId,int page,int limit )
	{
		return portalContentDao.getArtPageListByCatagoryIdPageNum(catagoryId, page, limit);
	}
	public void setBzZd(int catagoryId) {
		portalContentDao.setBzZd(catagoryId);
		
	}
	public Catagory getTopicPathById(int catagoryId) {
		return portalCatagoryDao.getTopicPathById(catagoryId);
	}
	public List<Catagory> getSubIdListByTopicId(int catagoryId) {
		
		return portalCatagoryDao.getSubIdListByTopicId(catagoryId);
	}
	public List<Content> getTopArtListByCatagoryId(int subId,int topicTopNum) {
		return portalContentDao.getTopArtListByCatagoryId(subId,topicTopNum);
	}
	public List<Content> getTopPicListByCatagoryId(int subId, int topicTopNum) {
		return portalContentDao.getTopPicListByCatagoryId(subId,topicTopNum);
	}
	public List<Catagory> getNavigateByCatagoryId(int catagoryId){
		return portalCatagoryDao.getNavigateByCatagoryId(catagoryId);
	}
	public int getColIdByContentId(int id) {
		return portalContentDao.getColIdByContentId(id);
	}
	public List<Catagory>getCatagoryListByIdArray(int[] idArray){
		return portalCatagoryDao.getCatagoryListByIdArray(idArray);
	}
	public List<Content> getPicNewsByTime(Map<String, Object>paramMap ) {
		return portalContentDao.getPicNewsByTime(paramMap);
	}
	public Content getPicNewByCatagoryId(int catagoryId) {
		
		return portalContentDao.getPicNewByCatagoryId(catagoryId);
	}
	public List<Content> getPicNewsByCatagoryId(int catagoryId,int topNum) {
		
		return portalContentDao.getPicNewsByCatagoryId(catagoryId, topNum);
	}
	public Content getSssrByCatagoryId(int catagoryId) {
		
		return portalContentDao.getSssrByCatagoryId(catagoryId);
	}
	public List<Content> getContentsByCatagoryId(int catagroyId, int contentTopNum) {
		
		return portalContentDao.getContentsByCatagoryId(catagroyId,contentTopNum);
	}
	public List<Catagory> getTopicCatagory(int topicTopNum,int indexId) {
		
		return portalCatagoryDao.getTopicCatagory(topicTopNum,indexId);
	}
	public List<Catagory> getTopicPicCatagory(int catagoryId) {
		
		return portalCatagoryDao.getTopicPicCatagory(catagoryId);
	}
	public Catagory getPicCatagory(int szxwId) {
		
		return portalCatagoryDao.getTemplateByCatagroyId(szxwId);
	}
	public Catagory getCatagoryById(int sjxwId) {
		
		return portalCatagoryDao.getTemplateByCatagroyId(sjxwId);
	}
	public List<Catagory> getCardCataroyByCatagroyId(int id)
	{
		return portalCatagoryDao.getCardCataroyByCatagroyId(id);
	}
	public List<Content> getArtPageListByKeyWordPageNum(String keyWord,int page,int limit )
	{
		return portalContentDao.getArtPageListByKeyWordPageNum(keyWord, page, limit);
	}
	public int getTotalCountByKeyWord(String keyWord)
	{
		return portalContentDao.getTotalCountByKeyWord(keyWord);
	}
	public Content getElecCardHtmlUrlByTitleAndTopicId(String name, int elecId) {
		return portalContentDao.getElecCardHtmlUrlByTitleAndTopicId(name,elecId);
	}
	public void updateContent(Content content) {
		portalContentDao.updateContent(content);
		
	}
	
}
