package org.ytgs.portal.common.service;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ytgs.portal.common.model.Catagory;
import org.ytgs.portal.common.model.Content;
import org.ytgs.util.ConfigInfo;
import org.ytgs.util.FreeMarkerCfg;

import freemarker.template.Configuration;
import freemarker.template.Template;
@Service
public class TopicOutPutHtmlService {

	@Autowired
	private PortalPublishService portalPublishService;
	@Autowired
	private ConfigInfo configInfo;
	public int parentId;
	public String catagoryName;
	/**
	 * 生成静态主题页HTML文件
	 * 
	 * @param realPhyTplPath   ：真实物理模板根路径，例如：C:\Tomcat 6.0\webapps\portal\WEB-INF\resource\repository\portal\templates\
	 * @param catagoryId       ：专题ID
	 * @param realPhyHtmlFile  ：真实物理HTML文件，例如：C:\Tomcat 6.0\webapps\portal\WEB-INF\resource\repository\portal\col\col123\index.html
	 * @param listContent      ：文章对象列表
	 * @throws Exception
	 */
	public synchronized void topicHtmlGenerate(String realPhyTplPath, Catagory catagory,
			String realPhyHtmlFile, String baseUrlPath) throws Exception {
		
		Configuration cfg = FreeMarkerCfg.getCfg(realPhyTplPath);
		//Catagory catagory = portalPublishService.getTemplateById(catagoryId);
		String templateFile = catagory.getPageModal();
		String catagoryName = catagory.getCatagoryName();
		Template temp = cfg.getTemplate(templateFile, "UTF-8");//解决模板汉字乱码问题
		/* 创建数据模型 */
		Map<String, Object> root = new HashMap<String, Object>();	
		root.put("catagoryId", catagory.getId());
		root.put("catagoryName", catagoryName);
		root.put("baseUrlPath", baseUrlPath);
		root.put("colPath",catagory.getCatagoryUrl());
		root.put("ldzlUrl",getLdzlUrl(baseUrlPath,catagory.getId()));
		root.put("dzyjUrl",getDzyjUrl(baseUrlPath,catagory.getId()));
		root.put("dhhmUrl",getDhhmUrl(baseUrlPath,catagory.getId()));
		root.put("dzmpUrl",getDzmpUrl(baseUrlPath,catagory.getId()));
		int indexId=Integer.parseInt(configInfo.getIndexCatagoryInfo().getProperty("indexId"));
		if(indexId==catagory.getId())
		{
			indexTopic(root,baseUrlPath);
		}
		else {
			commonTopic( root,catagory.getId(),baseUrlPath);
		}
		
		/* 将模板和数据模型合并 */
		FileOutputStream fout = new FileOutputStream(realPhyHtmlFile);
		Writer out = new OutputStreamWriter(fout, "UTF-8");//解决数据编码问题
		temp.process(root, out);
		out.flush();
		fout.close();
		out.close();
	}
	private String getLdzlUrl(String baseUrlPath, int id) {
		String ldzlId=configInfo.getQxHomeLdzlId().getProperty(String.valueOf(id));
		String url="#";
		if(ldzlId !=null){
			String colUrl=portalPublishService.getColPathById(Integer.parseInt(ldzlId));
			url=baseUrlPath+"/res/repository/portal/col/"+colUrl+"/index.html";
		}
		return url;
	}
	private String getDzyjUrl(String baseUrlPath,int id) {
		return null;
	}
	private String getDhhmUrl(String baseUrlPath,int id) {
		String url=baseUrlPath+"/res/repository/portal/col/col20140423101635_131/index.html";
		return url;
	}
	private String getDzmpUrl(String baseUrlPath,int id) {
		String dzmpId = configInfo.getQxHomeDzmpId().getProperty(String.valueOf(id));
		String url="#";
		if(dzmpId!=null){
			String colUrl=portalPublishService.getColPathById(Integer.parseInt(dzmpId));
			url=baseUrlPath+"/res/repository/portal/col/"+colUrl+"/index.html";
		}
		return url;
	}
	/**
	 * 首页专题生成静态页面
	 * @param root
	 * @param catagory
	 * @param baseUrlPath
	 */
	private void indexTopic(Map<String, Object> root, String baseUrlPath) {
		String picNewIdString= configInfo.getIndexCatagoryInfo().getProperty("picNewId");
		String[] idStrArray = picNewIdString.split(",");
		int[] idArray = new int[idStrArray.length];
		for(int j=0;j<idStrArray.length;j++){
			idArray[j]=Integer.parseInt(idStrArray[j]);
		}
		//栏目url 前缀：		
	   String colPrefix=baseUrlPath+configInfo.getHtmlUrlForCol();
	   //首页Id
	   int indexId=Integer.parseInt(configInfo.getIndexCatagoryInfo().getProperty("indexId"));
		//装配图片新闻数据
		int picNewsTopNum=Integer.parseInt(configInfo.getIndexCatagoryInfo().getProperty("picNewsTopNum"));//从配置文件获取显示的条数
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("idArray", idArray);
		paramMap.put("picNewsTopNum", picNewsTopNum);
		List<Content> picNews = portalPublishService.getPicNewsByTime(paramMap);
		root.put("picNews",picNews );
		
		//装配Banner图片数据
		int bannerId=Integer.parseInt(configInfo.getIndexCatagoryInfo().getProperty("bannerId"));
		int bannerTopNum=Integer.parseInt(configInfo.getIndexCatagoryInfo().getProperty("bannerTopNum"));
		List<Content> picBanners = portalPublishService.getContentsByCatagoryId(bannerId,bannerTopNum);	
		for(Content content:picBanners){
			if(content.getHtmlUrl().indexOf("http:")==-1){
				content.setHtmlUrl(baseUrlPath+"/"+content.getHtmlUrl());
			}
		}
		root.put("picBanners",picBanners);
		//装配市局图片新闻数据(仅一条数据)		
		int sjxwId=Integer.parseInt(configInfo.getIndexCatagoryInfo().getProperty("sjxwId"));
		Content topSjxwPic = portalPublishService.getPicNewByCatagoryId(sjxwId);
		root.put("topSjxwPic",topSjxwPic );
		////装配市局新闻数据
		int sjxwTopNum=Integer.parseInt(configInfo.getIndexCatagoryInfo().getProperty("sjxwTopNum"));//从配置文件获取显示的条数
		
		 //移出重复的头条记录
		List<Content> sjxwList=new ArrayList<Content>() ;
		if(topSjxwPic!=null)
		{sjxwList = portalPublishService.getContentsByCatagoryId(sjxwId,sjxwTopNum+1);
		for(Content content:sjxwList)
			{ if (content.getId()==topSjxwPic.getId())
			    {
				sjxwList.remove(content);
			     break;}
			}
		if(sjxwList.size()>sjxwTopNum)
			sjxwList.remove(sjxwTopNum);
		}
		else
		 sjxwList = portalPublishService.getContentsByCatagoryId(sjxwId,sjxwTopNum);
		//sjxwList.remove(topSjxwPic);		
		root.put("sjxwList",sjxwList );
		//装配市局新闻栏目url
		Catagory sjxwCatagory=portalPublishService.getCatagoryById(sjxwId);
		sjxwCatagory.setCatagoryUrl(colPrefix+sjxwCatagory.getCatagoryUrl()+"/index.html");
		root.put("sjxwCatagory",sjxwCatagory );
	    //装配县局图片新闻数据(仅一条数据)		
		int xjxwId=Integer.parseInt(configInfo.getIndexCatagoryInfo().getProperty("xjxwId"));
		Content topXjxwPic = portalPublishService.getPicNewByCatagoryId(xjxwId);
		root.put("topXjxwPic",topXjxwPic );
		//装配县局新闻数据
		//int xjxwTopNum=Integer.parseInt(configInfo.getIndexCatagoryInfo().getProperty("sjxwTopNum"));//从配置文件获取显示的条数
		 //移出重复的头条记录
		List<Content> xjxwList=new ArrayList<Content>() ;
		if(topXjxwPic!=null)
		{xjxwList = portalPublishService.getContentsByCatagoryId(xjxwId,sjxwTopNum+1);		
		for(Content content:xjxwList)
		{ 
			if (content.getId()==topXjxwPic.getId())
		    {
				xjxwList.remove(content);
		     break;}
		}
	  if(xjxwList.size()>sjxwTopNum)
		  xjxwList.remove(sjxwTopNum);}
		else
		 xjxwList = portalPublishService.getContentsByCatagoryId(sjxwId,sjxwTopNum);
		
		root.put("xjxwList",xjxwList );
		//装配县局新闻栏目url
		Catagory xjxwCatagory=portalPublishService.getCatagoryById(xjxwId);
		xjxwCatagory.setCatagoryUrl(colPrefix+xjxwCatagory.getCatagoryUrl()+"/index.html");
		root.put("xjxwCatagory",xjxwCatagory );
		//装配行政发文数据
		int xzfwId=Integer.parseInt(configInfo.getIndexCatagoryInfo().getProperty("xzfwId"));
		int xzfwTopNum=Integer.parseInt(configInfo.getIndexCatagoryInfo().getProperty("xzfwTopNum"));//从配置文件获取显示的条数
		List<Content> xzfwList = portalPublishService.getContentsByCatagoryId(xzfwId,xzfwTopNum);
		root.put("xzfwList",xzfwList );
		//装配行政发文栏目url
		Catagory xzfwCatagory=portalPublishService.getCatagoryById(xzfwId);
		xzfwCatagory.setCatagoryUrl(colPrefix+xzfwCatagory.getCatagoryUrl()+"/index.html");
		root.put("xzfwCatagory",xzfwCatagory );
		//装配专题图片
		//int topicTopNum=Integer.parseInt(configInfo.getIndexCatagoryInfo().getProperty("topicTopNum"));
		List<Catagory> topicListTmp = portalPublishService.getTopicPicCatagory(configInfo.getTopicRoot());
		List<Catagory> topicList=new ArrayList<Catagory>();
		for(Catagory topic:topicListTmp )
		{ topic.setCatagoryUrl(httpConvert(topic.getCatagoryUrl(),"","res/repository/portal/col"));
			topicList.add(topic);
		}
		root.put("topicList",topicList );
		//装配国税情况
		int gsqkId=Integer.parseInt(configInfo.getIndexCatagoryInfo().getProperty("gsqkId"));
		int gsqkTopNum=Integer.parseInt(configInfo.getIndexCatagoryInfo().getProperty("gsqkTopNum"));//从配置文件获取显示的条数
		List<Content> gsqkList = portalPublishService.getContentsByCatagoryId(gsqkId,gsqkTopNum);
		root.put("gsqkList",gsqkList );
		//装配国税情况栏目url
		Catagory gsqkCatagory=portalPublishService.getCatagoryById(gsqkId);
		gsqkCatagory.setCatagoryUrl(colPrefix+gsqkCatagory.getCatagoryUrl()+"/index.html");
		root.put("gsqkCatagory",gsqkCatagory );
		//装配国税动态
		int gsdtId=Integer.parseInt(configInfo.getIndexCatagoryInfo().getProperty("gsdtId"));
		//int gsdtTopNum=Integer.parseInt(configInfo.getIndexCatagoryInfo().getProperty("gsqkTopNum"));//从配置文件获取显示的条数
		List<Content> gsdtList = portalPublishService.getContentsByCatagoryId(gsdtId,gsqkTopNum);
		root.put("gsdtList",gsdtList );
		//装配国税动态栏目url
		Catagory gsdtCatagory=portalPublishService.getCatagoryById(gsdtId);
		gsdtCatagory.setCatagoryUrl(colPrefix+gsdtCatagory.getCatagoryUrl()+"/index.html");
		root.put("gsdtCatagory",gsdtCatagory );
		//装配税收调研
		int ssdyId=Integer.parseInt(configInfo.getIndexCatagoryInfo().getProperty("ssdyId"));
		//int gsdtTopNum=Integer.parseInt(configInfo.getIndexCatagoryInfo().getProperty("gsqkTopNum"));//从配置文件获取显示的条数
		List<Content> ssdyList = portalPublishService.getContentsByCatagoryId(ssdyId,gsqkTopNum);
		root.put("ssdyList",ssdyList );
		//装配税收调研栏目url
		Catagory ssdyCatagory=portalPublishService.getCatagoryById(ssdyId);
		ssdyCatagory.setCatagoryUrl(colPrefix+ssdyCatagory.getCatagoryUrl()+"/index.html");
		root.put("ssdyCatagory",ssdyCatagory );		
		//装配计划总结
		int jhzjId=Integer.parseInt(configInfo.getIndexCatagoryInfo().getProperty("jhzjId"));
		//int gsdtTopNum=Integer.parseInt(configInfo.getIndexCatagoryInfo().getProperty("gsqkTopNum"));//从配置文件获取显示的条数
		List<Content> jhzjList = portalPublishService.getContentsByCatagoryId(jhzjId,gsqkTopNum);
		root.put("jhzjList",jhzjList );
		//装配计划总结url
		Catagory jhzjCatagory=portalPublishService.getCatagoryById(jhzjId);
		jhzjCatagory.setCatagoryUrl(colPrefix+jhzjCatagory.getCatagoryUrl()+"/index.html");
		root.put("jhzjCatagory",jhzjCatagory );	
		//装配税收材料
		int ssclId=Integer.parseInt(configInfo.getIndexCatagoryInfo().getProperty("ssclId"));
		//int gsdtTopNum=Integer.parseInt(configInfo.getIndexCatagoryInfo().getProperty("gsqkTopNum"));//从配置文件获取显示的条数
		List<Content> ssclList = portalPublishService.getContentsByCatagoryId(ssclId,gsqkTopNum);
		root.put("ssclList",ssclList );
		//装配税收材料栏目url
		Catagory ssclCatagory=portalPublishService.getCatagoryById(ssclId);
		ssclCatagory.setCatagoryUrl(colPrefix+ssclCatagory.getCatagoryUrl()+"/index.html");
		root.put("ssclCatagory",ssclCatagory );
		//装配媒体报道
		int mtbdId=Integer.parseInt(configInfo.getIndexCatagoryInfo().getProperty("mtbdId"));
		//int gsdtTopNum=Integer.parseInt(configInfo.getIndexCatagoryInfo().getProperty("gsqkTopNum"));//从配置文件获取显示的条数
		List<Content> mtbdList = portalPublishService.getContentsByCatagoryId(mtbdId,gsqkTopNum);
		root.put("mtbdList",mtbdList );
		//装配媒体报道栏目url
		Catagory mtbdCatagory=portalPublishService.getCatagoryById(mtbdId);
		mtbdCatagory.setCatagoryUrl(colPrefix+mtbdCatagory.getCatagoryUrl()+"/index.html");
		root.put("mtbdCatagory",mtbdCatagory );
		//时政与新闻图片
		int szxwId=Integer.parseInt(configInfo.getIndexCatagoryInfo().getProperty("szxwId"));
		Catagory szxw = portalPublishService.getPicCatagory(szxwId);		
		root.put("szxw",szxw );
		  //时政与新闻列表		
		int szxwTopNum=Integer.parseInt(configInfo.getIndexCatagoryInfo().getProperty("szxwTopNum"));//从配置文件获取显示的条数
		List<Content> szxwList = portalPublishService.getContentsByCatagoryId(szxwId,szxwTopNum);
		root.put("szxwList",szxwList );	
		//装配时政与新闻栏目url
		Catagory szxwCatagory=portalPublishService.getCatagoryById(szxwId);
		szxwCatagory.setCatagoryUrl(colPrefix+szxwCatagory.getCatagoryUrl()+"/index.html");
		root.put("szxwCatagory",szxwCatagory );
		//家庭与生活图片
		int jtshId=Integer.parseInt(configInfo.getIndexCatagoryInfo().getProperty("jtshId"));
		Catagory jtsh = portalPublishService.getPicCatagory(jtshId);		
		root.put("jtsh",jtsh );
		  //家庭与生活列表		
		//int szxwTopNum=Integer.parseInt(configInfo.getIndexCatagoryInfo().getProperty("szxwTopNum"));//从配置文件获取显示的条数
		List<Content> jtshList = portalPublishService.getContentsByCatagoryId(jtshId,szxwTopNum);
		root.put("jtshList",jtshList );	
		//家庭与生活栏目url
		Catagory jtshCatagory=portalPublishService.getCatagoryById(jtshId);
		jtshCatagory.setCatagoryUrl(colPrefix+jtshCatagory.getCatagoryUrl()+"/index.html");
		root.put("jtshCatagory",jtshCatagory );
		//创作与欣赏图片
		int czxsId=Integer.parseInt(configInfo.getIndexCatagoryInfo().getProperty("czxsId"));
		Catagory czxs = portalPublishService.getPicCatagory(czxsId);		
		root.put("czxs",czxs );
		  //创作与欣赏列表		
		//int szxwTopNum=Integer.parseInt(configInfo.getIndexCatagoryInfo().getProperty("szxwTopNum"));//从配置文件获取显示的条数
		List<Content> czxsList = portalPublishService.getContentsByCatagoryId(czxsId,szxwTopNum);
		root.put("czxsList",czxsList );	
		//装配创作与欣赏栏目url
		Catagory czxsCatagory=portalPublishService.getCatagoryById(czxsId);
		czxsCatagory.setCatagoryUrl(colPrefix+czxsCatagory.getCatagoryUrl()+"/index.html");
		root.put("czxsCatagory",czxsCatagory );
		//税收与文化图片
		int sswhId=Integer.parseInt(configInfo.getIndexCatagoryInfo().getProperty("sswhId"));
		Catagory sswh = portalPublishService.getPicCatagory(sswhId);		
		root.put("sswh",sswh );
		//税收与文化列表		
		//int szxwTopNum=Integer.parseInt(configInfo.getIndexCatagoryInfo().getProperty("szxwTopNum"));//从配置文件获取显示的条数
		List<Content> sswhList  = portalPublishService.getContentsByCatagoryId(sswhId,szxwTopNum);
		root.put("sswhList",sswhList);	
		//装配税收与文化栏目url
		Catagory sswhCatagory=portalPublishService.getCatagoryById(sswhId);
		sswhCatagory.setCatagoryUrl(colPrefix+sswhCatagory.getCatagoryUrl()+"/index.html");
		root.put("sswhCatagory",sswhCatagory );
	    //新闻视角
		int xwsjId=Integer.parseInt(configInfo.getIndexCatagoryInfo().getProperty("xwsjId"));	
		Catagory xwsjCatagory = portalPublishService.getPicCatagory(xwsjId);		
		root.put("rightBottomPic",xwsjCatagory.getCatagoryPic() );	
		root.put("xwsjUrl",colPrefix+xwsjCatagory.getCatagoryUrl()+"/index.html");	
        //税收收入情况		
					
		int gssrId=Integer.parseInt(configInfo.getIndexCatagoryInfo().getProperty("gssrId"));
		Content topGssrPic = portalPublishService.getSssrByCatagoryId(gssrId);
		String[] ssArray = topGssrPic.getContent().split(",");
		if(ssArray.length>1){
			topGssrPic.setBzPl(ssArray[0]);
			topGssrPic.setBzSh(ssArray[1]);
			root.put("topGssrPic",topGssrPic );
		}
		
	}
	
	private void commonTopic( Map<String, Object> root,int catagoryId,String baseUrlPath) {		
		Catagory catagory = portalPublishService.getTopicPathById(catagoryId);
		String rightIdStr= configInfo.getRightCatagoryIdArrayForIndexPage().getProperty(String.valueOf(catagory.getId()));
		List<Catagory> subCatagoryList = portalPublishService.getSubIdListByTopicId(catagoryId);
		//判断是否存在右侧边栏配置信息---开始
		if(rightIdStr != null){
			String[] idStrArray = rightIdStr.split(",");
			int[] idArray = new int[idStrArray.length];
			for(int j=0;j<idStrArray.length;j++){
				idArray[j]=Integer.parseInt(idStrArray[j]);}
		    List<Catagory> subRightCatagoryList=portalPublishService.getCatagoryListByIdArray(idArray);
		     for(Catagory subCatagory :subRightCatagoryList){
			     root.put("col"+String.valueOf(subCatagory.getId())+"Url", baseUrlPath+configInfo.getHtmlUrlForCol()+subCatagory.getCatagoryUrl()+"/index.html");
			     String customTopNum=configInfo.getCustomTopNum().getProperty(String.valueOf(subCatagory.getId()));
				 int topNum =customTopNum==null?configInfo.getTopicTopNum():Integer.parseInt(customTopNum);
			     root.put("col"+String.valueOf(subCatagory.getId()), portalPublishService.getTopArtListByCatagoryId(subCatagory.getId(),topNum));
		     }
		}	
	//判断是否存在右侧边栏配置信息---结束
		//List<Catagory> subCatagoryList = portalPublishService.getSubIdListByTopicId(catagoryId);
		for(Catagory subCatagory :subCatagoryList){
			//更多
			root.put("col"+String.valueOf(subCatagory.getId())+"Url", baseUrlPath+configInfo.getHtmlUrlForCol()+subCatagory.getCatagoryUrl()+"/index.html");
			String customTopNum=configInfo.getCustomTopNum().getProperty(String.valueOf(subCatagory.getId()));
			int topNum =customTopNum==null?configInfo.getTopicTopNum():Integer.parseInt(customTopNum);
			//图片
			if("P".equals(subCatagory.getCatagoryDesc())){
				
				List<Content> tmpContents =portalPublishService.getTopPicListByCatagoryId(subCatagory.getId(),topNum);
				for(Content tmpContent :tmpContents){
					tmpContent.setTopicPic(httpConvert(tmpContent.getTopicPic(), baseUrlPath));
				}
				root.put("pic"+String.valueOf(subCatagory.getId()),tmpContents );
			}
			root.put("col"+String.valueOf(subCatagory.getId()), portalPublishService.getTopArtListByCatagoryId(subCatagory.getId(),topNum));
		}
		//专题首页图片连接装配
//		String customTopNum=configInfo.getCustomTopNum().getProperty(String.valueOf(catagoryId));
//		int topNum =customTopNum==null?configInfo.getTopicTopNum():Integer.parseInt(customTopNum);
		List<Catagory> topicListTmp = portalPublishService.getTopicPicCatagory(catagoryId);
		List<Catagory> topicList=new ArrayList<Catagory>();
		for(Catagory topic:topicListTmp )
		{ topic.setCatagoryUrl(httpConvert(topic.getCatagoryUrl(),"","res/repository/portal/col"));
			topicList.add(topic);
		}
		root.put("topicList",topicList);
		root.put("navigate", this.getNavigateString(catagoryId));
	}
	//生成导航条
	public String getNavigateString(int catagoryId){
		String result="";
		try {
			List<Catagory> catatoryList = portalPublishService.getNavigateByCatagoryId(catagoryId);
			for(Catagory catagory:catatoryList){
				if(catagory.getId()==catagoryId){
					parentId=catagory.getParentId();
					catagoryName=catagory.getCatagoryName();
				}
				result=catagory.getCatagoryName()+">"+result;
			}
			result=configInfo.getUnitName()+result.substring(result.indexOf('>'));
			result=result.substring(0, result.length()-1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public String httpConvert(String source,String baseUrl){
		return source.indexOf("http:")>=0?source:baseUrl+source;
	}
	public String httpConvert(String source,String baseUrl,String suffix){
		if(source==null){
			return "";
		}else{
			return source.indexOf("http:")>=0?source:baseUrl+suffix+"/"+source+"/index.html";
		}
	}
}
