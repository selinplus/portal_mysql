package org.ytgs.portal.common.service;

import freemarker.template.*;

import java.util.*;
import java.io.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ytgs.portal.common.model.Catagory;
import org.ytgs.portal.common.model.Content;
import org.ytgs.portal.common.service.PortalPublishService;
import org.ytgs.util.ConfigInfo;
import org.ytgs.util.FreeMarkerCfg;

@Component
public class ArtColHtmlOutputService {
	@Autowired
	private PortalPublishService portalPublishService;
	@Autowired
	private ConfigInfo configInfo;
	
	public int parentId;
	public String catagoryName;
	public int topicId;
	
	/**
	 * 生成静态右侧栏目文章列表页HTML包含文件
	 * @param realPhyTplPath   ：真实物理模板根路径，例如：C:\Tomcat 6.0\webapps\portal\WEB-INF\resource\repository\portal\templates\
	 * @param catagoryId       ：栏目ID
	 * @param realPhyHtmlFile  ：真实物理HTML文件，例如：C:\Tomcat 6.0\webapps\portal\WEB-INF\resource\repository\portal\art\20140227\2e693cc5-b28e-43f8-9166-81ad3b270e58.html
	 * @param content          ：文章对象
	 * @throws Exception
	 */
 
	public synchronized void  rightListHtmlGenerate(String realPhyTplPath, int catagoryId,
			String realPhyHtmlFile,String baseUrlPath,String colPath,String rightIdStr) throws Exception {
		Configuration cfg = FreeMarkerCfg.getCfg(realPhyTplPath);
		Catagory catagory= portalPublishService.getTopicPathById(catagoryId);
		topicId = catagory.getId();
		String templateFile = catagory.getArticleModal();
		if(templateFile==null||"null".equals(templateFile))
			return;
		Template temp = cfg.getTemplate(templateFile, "UTF-8");//解决模板汉字乱码问题
		/* 创建数据模型 */
		Map<String, Object> root = new HashMap<String, Object>();
		
		root.put("catagoryId", catagoryId);		
		rightList(root,catagoryId,baseUrlPath,rightIdStr);
		/* 将模板和数据模型合并 */
		FileOutputStream fout = new FileOutputStream(realPhyHtmlFile+topicId+".inc");
		Writer out = new OutputStreamWriter(fout, "UTF-8");//解决数据编码问题
		temp.process(root, out);
		out.flush();
		fout.close();
		out.close();
	}
	
	/**
	 * 生成静态文章页HTML文件
	 * @param realPhyTplPath   ：真实物理模板根路径，例如：C:\Tomcat 6.0\webapps\portal\WEB-INF\resource\repository\portal\templates\
	 * @param catagoryId       ：栏目ID
	 * @param realPhyHtmlFile  ：真实物理HTML文件，例如：C:\Tomcat 6.0\webapps\portal\WEB-INF\resource\repository\portal\art\20140227\2e693cc5-b28e-43f8-9166-81ad3b270e58.html
	 * @param content          ：文章对象
	 * @throws Exception
	 */
	public void artHtmlGenerate(String realPhyTplPath, int catagoryId,
			String realPhyHtmlFile,String baseUrlPath, Content content,String colPath,String rightIdStr) throws Exception {
		Configuration cfg = FreeMarkerCfg.getCfg(realPhyTplPath);
		String templateFile = portalPublishService.getTemplateById(catagoryId)
				.getArticleModal();
		Template temp = cfg.getTemplate(templateFile, "UTF-8");//解决模板汉字乱码问题
		/* 创建数据模型 */
		if (!"".equals(content.getPublishTime()))
			content.setCreateTime(content.getPublishTime());
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("navigate", this.getNavigateString(catagoryId));//导航条
		root.put("catagoryName", catagoryName);
		root.put("catagoryId", catagoryId);
		root.put("topicId", topicId);
		root.put("baseUrlPath",baseUrlPath);
		root.put("content", content);
		root.put("colPath", colPath);
		//最近更新文章
		String recntNum=configInfo.getRecentContents().getProperty(String.valueOf(catagoryId));
		if(recntNum!=null){
			root.put("recentContents",portalPublishService.getTopArtListByCatagoryId(catagoryId, Integer.parseInt(recntNum)));
		}
		
		//rightList(root,catagoryId,baseUrlPath,rightIdStr);
		/* 将模板和数据模型合并 */
		FileOutputStream fout = new FileOutputStream(realPhyHtmlFile);
		Writer out = new OutputStreamWriter(fout, "UTF-8");//解决数据编码问题
		temp.process(root, out);
		out.flush();
		fout.close();
		out.close();
	}

	/**
	 * 生成静态栏目页HTML文件
	 * 
	 * @param realPhyTplPath   ：真实物理模板根路径，例如：C:\Tomcat 6.0\webapps\portal\WEB-INF\resource\repository\portal\templates\
	 * @param catagoryId       ：栏目ID
	 * @param realPhyHtmlFile  ：真实物理HTML文件，例如：C:\Tomcat 6.0\webapps\portal\WEB-INF\resource\repository\portal\col\col123\index.html
	 * @param listContent      ：文章对象列表
	 * @throws Exception
	 */
	public void colHtmlGenerate(String realPhyTplPath, int catagoryId,
			String realPhyHtmlFile, String baseUrlPath,String colPath,int pageSize,String rightIdStr) throws Exception {
		
		Configuration cfg = FreeMarkerCfg.getCfg(realPhyTplPath);
		Catagory catagory = portalPublishService.getTemplateById(catagoryId);
		String templateFile = catagory.getPageModal();
		String catagoryName = catagory.getCatagoryName();
		Template temp = cfg.getTemplate(templateFile, "UTF-8");//解决模板汉字乱码问题
		/* 创建数据模型 */
		Map<String, Object> root = new HashMap<String, Object>();
		int totalCount = portalPublishService.getArtCountByCatagoryId(catagoryId);
		root.put("navigate", this.getNavigateString(catagoryId));//导航条
		root.put("catagoryId", catagoryId);
		root.put("catagoryName", catagoryName);
		root.put("topicId", topicId);
		root.put("totalCount", totalCount);
		root.put("pageSize", pageSize);
		String storeUrl=configInfo.getSpecialCatagoryStoreUrl().getProperty(String.valueOf(catagoryId));
		if(storeUrl==null){
			root.put("storeUrl",baseUrlPath+"/portal/colQuery");
		}else{
			root.put("storeUrl",baseUrlPath+storeUrl);	
		}
		root.put("baseUrlPath",baseUrlPath);
		root.put("colPath", colPath);
		
		//rightList(root,catagoryId,baseUrlPath,rightIdStr);
		/* 将模板和数据模型合并 */
		FileOutputStream fout = new FileOutputStream(realPhyHtmlFile);
		Writer out = new OutputStreamWriter(fout, "UTF-8");//解决数据编码问题
		temp.process(root, out);
		out.flush();
		fout.close();
		out.close();
	}
	//生成导航条
	public String getNavigateString(int catagoryId){
		List<Catagory> catatoryList = portalPublishService.getNavigateByCatagoryId(catagoryId);
		String result="";
		for(Catagory catagory:catatoryList){
			if(catagory.getId()==catagoryId){
				parentId=catagory.getParentId();
				catagoryName=catagory.getCatagoryName();
			}
			result=catagory.getCatagoryName()+">"+result;
		}
		result=configInfo.getUnitName()+result.substring(result.indexOf('>'));
		result=result.substring(0, result.length()-1);
		return result;
	}
	/**
	 * 生成主题侧栏信息
	 * @param root
	 * @param catagoryId
	 * @param baseUrlPath
	 * @param rightIdStr 侧面列表栏目ID字符串，由XML配置获得，‘,’间隔
	 */
	private void rightList( Map<String, Object> root,int catagoryId,String baseUrlPath,String rightIdStr) {		
		List<Catagory> subCatagoryList=new ArrayList<Catagory>();
		if(rightIdStr==null ){
			subCatagoryList = portalPublishService.getSubIdListByTopicId(topicId);
		}else{
			String[] idStrArray = rightIdStr.split(",");
			int[] idArray = new int[idStrArray.length];
			for(int j=0;j<idStrArray.length;j++){
				idArray[j]=Integer.parseInt(idStrArray[j]);
			}
			subCatagoryList = portalPublishService.getCatagoryListByIdArray(idArray);
		}
		List<Map<String, Object>> colList =new ArrayList<Map<String, Object>>();
		for(Catagory subCatagory :subCatagoryList){
			String customTopNum=configInfo.getCustomTopNum().getProperty(String.valueOf(subCatagory.getId()));
			int topNum =customTopNum==null?configInfo.getTopicTopNum():Integer.parseInt(customTopNum);
			String picId=configInfo.getRightPicId().getProperty(String.valueOf(subCatagory.getId()));
			List<Content> tmpContents =portalPublishService.getTopArtListByCatagoryId(subCatagory.getId(),topNum);
			if(picId!=null){
				tmpContents=portalPublishService.getPicNewsByCatagoryId(subCatagory.getId(),topNum);
			}
			for(Content tmpContent :tmpContents){
				tmpContent.setHtmlUrl(httpConvert(tmpContent.getHtmlUrl(), baseUrlPath));
				tmpContent.setTopicPic(httpConvert(tmpContent.getTopicPic(), baseUrlPath));
			}
			Map<String, Object> colMap =new HashMap<String, Object>();
			colMap.put("catagoryId",subCatagory.getId());
			colMap.put("catagoryName",subCatagory.getCatagoryName());
			colMap.put("catagoryDesc",subCatagory.getCatagoryDesc());
			colMap.put("colMoreUrl",httpConvert(configInfo.getHtmlUrlForCol()+subCatagory.getCatagoryUrl()+"/index.html",baseUrlPath));
			colMap.put("content", tmpContents);
			colList.add(colMap);
		}
		root.put("colList",colList);
		root.put("baseUrlPath", baseUrlPath);
	}
	
	/**
	 * 转换url添加http://XX.XX.XX.XX/(portal)
	 * @param source
	 * @param baseUrl
	 * @return
	 */
	public String httpConvert(String source,String baseUrl){
		if(source==null)
			return source;
		return source.indexOf("http:")>=0?source:baseUrl+source;
	}
	/**
	 * 生成静态电子名片栏目页HTML文件
	 * 
	 * @param realPhyTplPath   ：真实物理模板根路径，例如：C:\Tomcat 6.0\webapps\portal\WEB-INF\resource\repository\portal\templates\
	 * @param catagoryId       ：栏目ID
	 * @param realPhyHtmlFile  ：真实物理HTML文件，例如：C:\Tomcat 6.0\webapps\portal\WEB-INF\resource\repository\portal\col\col123\index.html
	 * @param listContent      ：文章对象列表
	 * @throws Exception
	 */
	public void cardcolHtmlGenerate(String realPhyTplPath, int catagoryId,
			String realPhyHtmlFile, String baseUrlPath,String colPath,int pageSize,String rightIdStr) throws Exception {
		
		Configuration cfg = FreeMarkerCfg.getCfg(realPhyTplPath);
		Catagory catagory = portalPublishService.getTemplateById(catagoryId);
		String templateFile = catagory.getPageModal();
		String catagoryName = catagory.getCatagoryName();
		Template temp = cfg.getTemplate(templateFile, "UTF-8");//解决模板汉字乱码问题
		/* 创建数据模型 */
		Map<String, Object> root = new HashMap<String, Object>();
		int totalCount = portalPublishService.getArtCountByCatagoryId(catagoryId);
		root.put("navigate", this.getNavigateString(catagoryId));//导航条
		root.put("catagoryId", catagoryId);
		root.put("catagoryName", catagoryName);
		root.put("topicId", topicId);
		root.put("totalCount", totalCount);
		root.put("pageSize", pageSize);
		root.put("storeUrl",baseUrlPath+"/portal/colQuery");
		root.put("baseUrlPath",baseUrlPath);
		root.put("colPath", colPath);
		cardList(root,catagoryId,baseUrlPath,rightIdStr);
		/* 将模板和数据模型合并 */
		FileOutputStream fout = new FileOutputStream(realPhyHtmlFile);
		Writer out = new OutputStreamWriter(fout, "UTF-8");//解决数据编码问题
		temp.process(root, out);
		out.flush();
		fout.close();
		out.close();
	}
	/**
	 * 生成主题侧栏信息
	 * @param root
	 * @param catagoryId
	 * @param baseUrlPath
	 * @param rightIdStr 侧面列表栏目ID字符串，由XML配置获得，‘,’间隔
	 */
	private void cardList( Map<String, Object> root,int catagoryId,String baseUrlPath,String rightIdStr) {		
		List<Catagory> subCatagoryList=new ArrayList<Catagory>();
		if((rightIdStr==null) ||"".equals(rightIdStr) ){
			subCatagoryList = portalPublishService.getSubIdListByTopicId(topicId);
		}else{
			String[] idStrArray = rightIdStr.split(",");
			int[] idArray = new int[idStrArray.length];
			for(int j=0;j<idStrArray.length;j++){
				idArray[j]=Integer.parseInt(idStrArray[j]);
			}
			subCatagoryList = portalPublishService.getCatagoryListByIdArray(idArray);
		}
		List<Map<String, Object>> colList =new ArrayList<Map<String, Object>>();
		for(Catagory subCatagory :subCatagoryList){
			String customTopNum=configInfo.getCustomTopNum().getProperty(String.valueOf(subCatagory.getId()));
			int topNum =customTopNum==null?configInfo.getTopicTopNum():Integer.parseInt(customTopNum);
			List<Content> tmpContents =portalPublishService.getTopArtListByCatagoryId(subCatagory.getId(),topNum);
			for(Content tmpContent :tmpContents){
				tmpContent.setHtmlUrl(httpConvert(tmpContent.getHtmlUrl(), baseUrlPath));
				tmpContent.setTopicPic(httpConvert(tmpContent.getTopicPic(), baseUrlPath));
			}
			Map<String, Object> colMap =new HashMap<String, Object>();
			colMap.put("catagoryId",subCatagory.getId());
			colMap.put("catagoryName",subCatagory.getCatagoryName());
			colMap.put("catagoryDesc",subCatagory.getCatagoryDesc());
			colMap.put("colMoreUrl",httpConvert(configInfo.getHtmlUrlForCol()+subCatagory.getCatagoryUrl()+"/index.html",baseUrlPath));
			colMap.put("content", tmpContents);
			colList.add(colMap);
		}
		root.put("colList",colList);
		root.put("baseUrlPath", baseUrlPath);
	}
	
}
