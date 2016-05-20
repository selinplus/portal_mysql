package org.ytgs.portal.common.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.codehaus.jackson.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ytgs.common.tree.TreeNode;
import org.ytgs.common.tree.TreeUtil;
import org.ytgs.portal.common.model.Catagory;
import org.ytgs.portal.common.model.Content;
import org.ytgs.portal.common.model.Weather;
import org.ytgs.portal.common.service.ArtColHtmlOutputService;
import org.ytgs.portal.common.service.PortalPublishService;
import org.ytgs.portal.common.service.TopicOutPutHtmlService;
import org.ytgs.security.CustomUserDetails;
import org.ytgs.util.ConfigInfo;
import org.ytgs.util.GenerateFileName;
@Controller
public class PortalContentController {
	@Autowired
	ConfigInfo configInfo;
	@Autowired
	PortalPublishService portalPublishService;
	@Autowired
	TopicOutPutHtmlService topicOutPutHtmlService;
	@Autowired
	ArtColHtmlOutputService artColHtmlOutputService;
	@Autowired
	TreeUtil treeUtil;

	@RequestMapping(value = "/admin/portal/common/contentPublish")
	public String noticePublish(int artId,ModelMap model) {
		if(artId>0){
			Content content=portalPublishService.getContentById(artId);
			model.put("content", content);
		}
		model.put("artId", artId);
		return "/admin/portal/common/contentPublish";
	}

	@RequestMapping(value = "/home")
	public String homePage(ModelMap model) {
		return "page/redirect";
	}

	@RequestMapping(value = "/portal/colQuery")
	@ResponseBody
	public String getColQuery(@RequestParam("catagoryId") int catagoryId,
			int totalCount, int page, int limit, HttpServletRequest request) {
		String baseUrlPath = request.getScheme() + "://"
				+ request.getServerName() + request.getContextPath();
		if (request.getServerPort() != 80) {
			baseUrlPath = request.getScheme() + "://" + request.getServerName()
					+ ":" + request.getServerPort() + request.getContextPath();
		}
		List<Content> artList = null;
		artList = portalPublishService.getArtPageListByCatagoryIdPageNum(
				catagoryId, limit*(page-1), limit);
		totalCount=portalPublishService.getArtCountByCatagoryId(catagoryId);
		String jsonString = "{ \"totalCount\":" + totalCount
				+ ", \"records\":[";
		for (Content content : artList) {
			jsonString += "{\"title\":\"" + content.getTitle() + "\",";
			jsonString += "\"createTime\":\"" + content.getCreateTime() + "\",";
			jsonString += "\"id\":\"" + content.getId() + "\",";
			jsonString += "\"topicPic\":\"" + content.getTopicPic() + "\",";
			jsonString += "\"bzZd\":\"" + content.getBzZd() + "\",";
			jsonString += "\"htmlUrl\":\""
					+ httpConvert(content.getHtmlUrl(), baseUrlPath) + "\"},";
		}
		jsonString = artList.size() != 0 ? jsonString.substring(0,
				jsonString.length() - 1) : jsonString;
		jsonString += "]}";
		return jsonString;
	}

	

	

	@RequestMapping(value = "/admin/portal/common/saveContent")
	public String saveContent(@Valid Content content,
			@RequestParam("catagoryId") int catagoryId, ModelMap model,
			HttpServletRequest request) throws UnsupportedEncodingException,
			ParseException {  
		// 应用服务器物理根目录，例如：C:\Tomcat 6.0\webapps\portal\
		String basePhyPath = request.getSession().getServletContext()
				.getRealPath("/");
		// 应用服务器根目录，例如：http:/portal
		String baseUrlPath = request.getScheme() + "://"
				+ request.getServerName() + request.getContextPath();
		if (request.getServerPort() != 80) {
			baseUrlPath = request.getScheme() + "://" + request.getServerName()
					+ ":" + request.getServerPort() + request.getContextPath();
		}
		// 文件保存目录路径
		String savePath = basePhyPath + configInfo.getRealPhyPathForArt();
		// 文件保存目录URL
		String saveUrl = configInfo.getHtmlUrlForArt();
		// 创建文件夹
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		String rightListFilePre = basePhyPath + configInfo.getRightListPath();
		String fileName = GenerateFileName.generateFileName(savePath, ".html");
		content.setCatagoryId(catagoryId);
		content.setCreateTime(ymd);
		String realPhyTplPath = basePhyPath + configInfo.getRealPhyPathForTpl();
		String colPathPrefix = basePhyPath + configInfo.getRealPhyPathForCol();
		String colPathSuffix = portalPublishService.getColPathById(catagoryId);
		// 保存文章到数据表(先写数据表，再生成静态页面)
		if ("on".equals(content.getBzZd())) {
			portalPublishService.setBzZd(catagoryId);
		}
		String urlName = saveUrl
				+ fileName.substring(fileName.lastIndexOf('/') + 1);
		if("".equals(content.getHtmlUrl())){
			content.setHtmlUrl(urlName);
		}
		SimpleDateFormat displaySdf = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		if (!"".equals(content.getPublishTime()))
			content.setDisplayTime(displaySdf.parse(content.getPublishTime()));
		else {
			content.setDisplayTime(new Date());
		}
		content.setTitle(htmlspecialchars(content.getTitle()));// 转义特殊符号
		if(content.getIntroduction()==null){
			content.setIntroduction("");
		}
		int artId=Integer.parseInt(request.getParameter("artId"));
		Content oldContent = null;
		if(artId>0){
			content.setId(artId);
			oldContent = portalPublishService.getContentById(artId);
			portalPublishService.updateContent(content);
			//deleteHtmlFile(basePhyPath,configInfo.getRealPhyPathForArt(),oldContent.getHtmlUrl());
		}else{
			portalPublishService.addContent(content);
		}
		// 获取插入对象的id
		int id = content.getId();
		String rightIdStr = "";

		// 生成静态侧面栏目列表文章页面
		Catagory catagory = portalPublishService.getTopicPathById(catagoryId);// 获取当前栏目所属专题信息
		if (catagory != null) {
			try {
				rightIdStr = configInfo.getRightCatagoryIdArrayForIndexPage()
						.getProperty(String.valueOf(catagory.getId()));
				artColHtmlOutputService.rightListHtmlGenerate(realPhyTplPath,
						catagoryId, rightListFilePre, baseUrlPath,
						colPathSuffix, rightIdStr);
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("msg", "侧栏模板不存在或者路径不正确！");
				model.addAttribute("error", e.getMessage());
				portalPublishService.removeConent(id);
				return "/admin/portal/common/contentPublishFail";
			}
		}
		// 生成静态文章页面
		try {
			artColHtmlOutputService.artHtmlGenerate(realPhyTplPath, catagoryId,
					fileName, baseUrlPath, content, colPathSuffix, rightIdStr);
			if(oldContent!=null){//修改成功生成新页面时删除原静态页面
				deleteHtmlFile(basePhyPath,configInfo.getRealPhyPathForArt(),oldContent.getHtmlUrl());
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(oldContent==null){//生成静态页面失败时，如果是新增，则删除数据库记录。
				portalPublishService.removeConent(id);
			}
			model.addAttribute("msg", "文章模板不存在或者路径不正确！");
			model.addAttribute("error", e.getMessage());
			return "/admin/portal/common/contentPublishFail";
		}

		// 生成栏目页面

		fileName = colPathPrefix + colPathSuffix + "/index.html";
		try {
			artColHtmlOutputService.colHtmlGenerate(realPhyTplPath, catagoryId,
					fileName, baseUrlPath, colPathSuffix,
					configInfo.getPageSize(), rightIdStr);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "栏目模板不存在或者路径不正确！");
			model.addAttribute("error", e.getMessage());
			portalPublishService.removeConent(id);
			return "/admin/portal/common/contentPublishFail";
		}
		// 生成专题页面
		if (catagory != null) {
			String topicPathSuffix = catagory.getCatagoryUrl();
			// 专题ID
			catagoryId = catagory.getId();
			// 专题路径
			fileName = colPathPrefix + topicPathSuffix + "/index.html";
			try {
				topicOutPutHtmlService.topicHtmlGenerate(realPhyTplPath,
						catagory, fileName, baseUrlPath);
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("msg", "专题模板不存在或者路径不正确！");
				model.addAttribute("error", e.getMessage());
				portalPublishService.removeConent(id);
				return "/admin/portal/common/contentPublishFail";
			}
		}
		return "/admin/portal/common/contentPublishSuccess";

	}

	private void deleteHtmlFile(String basePhyPath, String realPhyPathForArt,
			String oldFile) {
		String filename = basePhyPath+realPhyPathForArt+oldFile.substring(oldFile.indexOf("art")+4);
		File iFile = new File(filename);
		if(iFile.exists()){
			iFile.delete();
		}
	}


	/**
	 * 文章删除时调用，重新生成各静态页面
	 * 
	 * @param catagoryId
	 * @param model
	 * @param request
	 * @return
	 */
	private String doGenIndexFile(int catagoryId, ModelMap model,
			HttpServletRequest request) {
		// 应用服务器物理根目录，例如：C:\Tomcat 6.0\webapps\portal\
		String basePhyPath = request.getSession().getServletContext()
				.getRealPath("/");
		// 应用服务器根目录，例如：/portal
		String baseUrlPath = request.getScheme() + "://"
				+ request.getServerName() + request.getContextPath();
		if (request.getServerPort() != 80) {
			baseUrlPath = request.getScheme() + "://" + request.getServerName()
					+ ":" + request.getServerPort() + request.getContextPath();
		}
		// 文件保存目录路径
		String savePath = basePhyPath + configInfo.getRealPhyPathForArt();
		// 文件保存目录URL
		// String saveUrl = baseUrlPath + configInfo.getHtmlUrlForArt();
		// 创建文件夹
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		// saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		String rightListFilePre = basePhyPath + configInfo.getRightListPath();
		String fileName = GenerateFileName.generateFileName(savePath, ".html");
		String realPhyTplPath = basePhyPath + configInfo.getRealPhyPathForTpl();
		String colPathPrefix = basePhyPath + configInfo.getRealPhyPathForCol();
		String colPathSuffix = portalPublishService.getColPathById(catagoryId);
		Catagory catagory = portalPublishService.getTopicPathById(catagoryId);// 获取当前栏目所属专题信息
		String rightIdStr = "";
		if (catagory != null) {
			// 生成静态侧面栏目列表文章页面
			try {
				rightIdStr = configInfo.getRightCatagoryIdArrayForIndexPage()
						.getProperty(String.valueOf(catagory.getId()));
				artColHtmlOutputService.rightListHtmlGenerate(realPhyTplPath,
						catagoryId, rightListFilePre, baseUrlPath,
						colPathSuffix, rightIdStr);
			} catch (Exception e) {
				e.printStackTrace();

				model.addAttribute("msg", "模板不存在或者路径不正确！");
				model.addAttribute("error", e.getMessage());
				return "error";
			}
		}

		// 生成栏目页面

		fileName = colPathPrefix + colPathSuffix + "/index.html";
		try {
			artColHtmlOutputService.colHtmlGenerate(realPhyTplPath, catagoryId,
					fileName, baseUrlPath, colPathSuffix,
					configInfo.getPageSize(), rightIdStr);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "模板不存在或者路径不正确！");
			model.addAttribute("error", e.getMessage());
			return "error";
		}
		// 生成专题页面
		if (catagory != null) {
			String topicPathSuffix = catagory.getCatagoryUrl();
			// 专题ID
			catagoryId = catagory.getId();
			// 专题路径
			fileName = colPathPrefix + topicPathSuffix + "/index.html";
			try {
				topicOutPutHtmlService.topicHtmlGenerate(realPhyTplPath,
						catagory, fileName, baseUrlPath);
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("msg", "模板不存在或者路径不正确！");
				model.addAttribute("error", e.getMessage());
				return "error";
			}
		}
		return "success";
	}

	@RequestMapping(value = "/admin/portal/manageArticle/catagoryTree")
	@ResponseBody
	public List<TreeNode> catagoryTree(@RequestParam("id") int id,
			ModelMap model) throws JsonProcessingException, IOException {
		treeUtil.setParentId(id);
		treeUtil.setTabName("PORTAL_CATAGORY");
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		List<TreeNode> treeNodeList = treeUtil
				.getTreeNodeList(user.getUserId());
		return treeNodeList;
	}

	@RequestMapping(value = "/admin/portal/managerArticle/manageArticleQuery")
	@ResponseBody
	public String manageArticleQuery(@RequestParam("id") int id, int page,
			int limit, ModelMap model, HttpServletRequest request)
			throws JsonProcessingException, IOException {
		List<Content> artList = null;
		List<Weather> weatherList = null;

		String baseUrlPath = request.getScheme() + "://"
				+ request.getServerName() + request.getContextPath();
		if (request.getServerPort() != 80) {
			baseUrlPath = request.getScheme() + "://" + request.getServerName()
					+ ":" + request.getServerPort() + request.getContextPath();
		}
		// 从配置文件获取天气预报Id，加分支区别于普通发布
		int tqybId = Integer.parseInt(configInfo.getIndexCatagoryInfo().getProperty(
				"tqybId"));
		String jsonString = "";
		int totalCount = 0;
	 
		totalCount = portalPublishService.getArtCountByCatagoryId(id);
		artList = portalPublishService.getArtPageListByCatagoryIdPageNum(
				id, limit*(page-1), limit);
		jsonString = "{ \"totalCount\":" + totalCount + ", \"records\":[";
		for (Content content : artList) {
			jsonString += "{\"title\":\"" + content.getTitle() + "\",";
			jsonString += "\"createTime\":\"" + content.getCreateTime()
					+ "\",";
			jsonString += "\"id\":\"" + content.getId() + "\",";
			jsonString += "\"bzZd\":\"" + content.getBzZd() + "\",";
			jsonString += "\"source\":\"" + content.getSource() + "\",";
			jsonString += "\"htmlUrl\":\""
					+ httpConvert(content.getHtmlUrl(), baseUrlPath)
					+ "\"},";
		}
		jsonString = artList.size() != 0 ? jsonString.substring(0,
				jsonString.length() - 1) : jsonString;
		jsonString += "]}";
		
		return jsonString;
	}

	@RequestMapping(value = "/admin/portal/managerArticle/manageArticleDelete")
	@ResponseBody
	public Map<String, String> manageArticleDelete(
			@RequestBody Content content, ModelMap model,
			HttpServletRequest request) {
		Map<String, String> imsg = new HashMap<String, String>();
		String basePhyPath = request.getSession().getServletContext().getRealPath("/");
		String realPhyPathForArt = configInfo.getRealPhyPathForArt();
		try {
			int catagoryId = portalPublishService.getColIdByContentId(content
					.getId());
			portalPublishService.removeConent(content.getId());
			deleteHtmlFile(basePhyPath, realPhyPathForArt, content.getHtmlUrl());
			String iresult = this.doGenIndexFile(catagoryId, model, request);
			if ("success".equals(iresult)) {
				imsg.put("success", "true");
			} else {
				imsg.put("success", "false");
			}
		} catch (Exception e) {
			imsg.put("success", "false");
			e.printStackTrace();
		}
		return imsg;
	}
	

	public String httpConvert(String source, String baseUrl) {
		return source.indexOf("http:") >= 0 ? source : baseUrl + source;
	}

	public String htmlspecialchars(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}

	// 电子名片handler
	@RequestMapping(value = "/admin/portal/common/cardPublish")
	public String cardPublish(ModelMap model) {
		return "/admin/portal/common/cardPublish";
	}

	// 电子名片显示栏目列表
	@RequestMapping(value = "/portal/cardQuery")
	@ResponseBody
	public String getcardQuery(@RequestParam("catagoryId") int catagoryId,
			HttpServletRequest request) {
		String baseUrlPath = request.getScheme() + "://"
				+ request.getServerName() + request.getContextPath();
		if (request.getServerPort() != 80) {
			baseUrlPath = request.getScheme() + "://" + request.getServerName()
					+ ":" + request.getServerPort() + request.getContextPath();
		}
		List<Catagory> cataList = null;
		cataList = portalPublishService.getCardCataroyByCatagroyId(catagoryId);
		String jsonString = "{ \"cardcata\":[";
		for (Catagory catagory : cataList) {
			jsonString += "{\"id\":\"" + catagory.getId() + "\",";
			jsonString += "\"name\":\"" + catagory.getCatagoryName() + "\",";
			if (catagory.getCatagoryUrl() != null) {
				jsonString += "\"cataUrl\":\"" + baseUrlPath
						+ "/res/repository/portal/col/"
						+ catagory.getCatagoryUrl() + "/index.html\"},";
			} else {
				jsonString += "\"cataUrl\":\"无数据\"},";

			}
		}
		jsonString = cataList.size() != 0 ? jsonString.substring(0,
				jsonString.length() - 1) : jsonString;
		jsonString += "]";
		List<Content> artList = null;
		artList = portalPublishService.getContentsByCatagoryId(catagoryId, 100);
		jsonString += ",\"content\":[";
		for (Content content : artList) {
			jsonString += "{\"title\":\"" + content.getTitle() + "\",";
			jsonString += "\"picurl\":\""
					+ httpConvert(content.getTopicPic(), baseUrlPath) + "\",";
			jsonString += "\"id\":\"" + content.getId() + "\",";
			jsonString += "\"htmlUrl\":\""
					+ httpConvert(content.getHtmlUrl(), baseUrlPath) + "\"},";
		}
		jsonString = artList.size() != 0 ? jsonString.substring(0,
				jsonString.length() - 1) : jsonString;
		jsonString += "]}";
		return jsonString;
	}

	// 电子名片生成静态页面
	@RequestMapping(value = "/admin/portal/common/saveCard")
	public String saveCard(@Valid Content content,
			@RequestParam("catagoryId") int catagoryId, ModelMap model,
			HttpServletRequest request) throws UnsupportedEncodingException,
			ParseException {

		// 应用服务器物理根目录，例如：C:\Tomcat 6.0\webapps\portal\
		String basePhyPath = request.getSession().getServletContext()
				.getRealPath("/");
		// 应用服务器根目录，例如：http:/portal
		String baseUrlPath = request.getScheme() + "://"
				+ request.getServerName() + request.getContextPath();
		if (request.getServerPort() != 80) {
			baseUrlPath = request.getScheme() + "://" + request.getServerName()
					+ ":" + request.getServerPort() + request.getContextPath();
		}
		// 文件保存目录路径
		String savePath = basePhyPath + configInfo.getRealPhyPathForArt();
		// 文件保存目录URL
		String saveUrl = configInfo.getHtmlUrlForArt();
		// 创建文件夹
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		
		String fileName = GenerateFileName.generateFileName(savePath, ".html");
		content.setCatagoryId(catagoryId);
		content.setCreateTime(ymd);
		String realPhyTplPath = basePhyPath + configInfo.getRealPhyPathForTpl();
		String colPathPrefix = basePhyPath + configInfo.getRealPhyPathForCol();
		String colPathSuffix = portalPublishService.getColPathById(catagoryId);
		// 保存文章到数据表(先写数据表，再生成静态页面)
		if ("on".equals(content.getBzZd())) {
			portalPublishService.setBzZd(catagoryId);
		}
		String urlName = saveUrl
				+ fileName.substring(fileName.lastIndexOf('/') + 1);
		content.setHtmlUrl(urlName);

		// SimpleDateFormat displaySdf= new
		// SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		content.setDisplayTime(new Date());
		content.setTitle(htmlspecialchars(content.getTitle()));// 转义特殊符号
		portalPublishService.addContent(content);
		// 获取插入对象的id
		int id = content.getId();
		String rightIdStr = "";

		try {
			artColHtmlOutputService.artHtmlGenerate(realPhyTplPath, catagoryId,
					fileName, baseUrlPath, content, colPathSuffix, rightIdStr);
		} catch (Exception e) {
			e.printStackTrace();

			model.addAttribute("msg", "文章模板不存在或者路径不正确！");
			model.addAttribute("error", e.getMessage());
			portalPublishService.removeConent(id);
			return "/admin/portal/common/contentPublishFail";
		}

		// 生成栏目页面

		fileName = colPathPrefix + colPathSuffix + "/index.html";
		try {
			artColHtmlOutputService.cardcolHtmlGenerate(realPhyTplPath,
					catagoryId, fileName, baseUrlPath, colPathSuffix,
					configInfo.getPageSize(), rightIdStr);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "栏目模板不存在或者路径不正确！");
			model.addAttribute("error", e.getMessage());
			portalPublishService.removeConent(id);
			return "/admin/portal/common/contentPublishFail";
		}
		
		return "/admin/portal/common/contentPublishSuccess";

	}

	@RequestMapping(value = "/admin/portal/article/weatherSave")
	public String saveWeather(@Valid Weather weather, ModelMap model,
			HttpServletRequest request) throws UnsupportedEncodingException,
			ParseException {
		// 应用服务器物理根目录，例如：C:\Tomcat 6.0\webapps\portal\
		String basePhyPath = request.getSession().getServletContext().getRealPath("/");
		// 应用服务器根目录，例如：http:/portal
		String baseUrlPath = request.getScheme() + "://"+ request.getServerName() + request.getContextPath();
		if (request.getServerPort() != 80) {
			baseUrlPath = request.getScheme() + "://" + request.getServerName()	+ ":" + request.getServerPort() + request.getContextPath();
		}
		// 创建文件夹		
		String realPhyTplPath = basePhyPath + configInfo.getRealPhyPathForTpl();
		String colPathPrefix = basePhyPath + configInfo.getRealPhyPathForCol();
		Catagory catagory = portalPublishService.getTopicPathById(Integer.parseInt(configInfo.getIndexCatagoryInfo().getProperty("tqybId")));// 获取当前栏目所属专题信息
		try {
			// 生成专题页面
			if (catagory != null) {
				String topicPathSuffix = catagory.getCatagoryUrl();
				String fileName = colPathPrefix + topicPathSuffix + "/index.html";
				try {
					topicOutPutHtmlService.topicHtmlGenerate(realPhyTplPath,
							catagory, fileName, baseUrlPath);
				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("msg", "模板不存在或者路径不正确！");
					model.addAttribute("error", e.getMessage());
					return "error";
				}
			}
		} catch (Exception e) {
			return "/admin/portal/common/contentPublishFail";
		}
		return "/admin/portal/common/contentPublishSuccess";

	}

	@RequestMapping(value = "admin/portal/article/weather")
	public String indexWeather() {

		return "/admin/portal/article/weather";

	}
	
	//龙口王维纲站内搜索 start
	@RequestMapping(value = "/portal/searchKeyWord")
	@ResponseBody
	public String searchKeyWord(@RequestParam("keyWord") String keyWord ,int page,
			int limit, HttpServletRequest request) throws UnsupportedEncodingException {
		String baseUrlPath=request.getScheme()+"://"+request.getServerName()+ request.getContextPath();
		if (request.getServerPort()!=80){	 
			baseUrlPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ request.getContextPath();
		}
		//将前台页面传来的关键字转换为UTF-8,解决汉字乱字符问题。
		//String keyWord2=URLDecoder.decode(keyWord,"UTF-8");
		//String keyWord2=new String(keyWord.getBytes("iso-8859-1"),"utf-8");
		int totalCount=portalPublishService.getTotalCountByKeyWord(keyWord);
		List<Content> artList = null;
		artList = portalPublishService.getArtPageListByKeyWordPageNum(
				keyWord, page, limit);
		String jsonString = "{ \"totalCount\":" + totalCount
				+ ", \"records\":[";
		for (Content content : artList) {
			jsonString += "{\"title\":\"" + content.getTitle() + "\",";
			jsonString += "\"createTime\":\"" + content.getCreateTime() + "\",";
			jsonString += "\"id\":\"" + content.getId() + "\",";
			jsonString += "\"bzZd\":\"" + content.getBzZd() + "\",";
			jsonString += "\"htmlUrl\":\"" + httpConvert(content.getHtmlUrl(),baseUrlPath) + "\"},";
		}
		jsonString = artList.size() != 0 ? jsonString.substring(0,
				jsonString.length() - 1) : jsonString;
		jsonString += "]}";
		return jsonString;
	}
	@RequestMapping(value = "/portal/eleCard/priCard")
	@ResponseBody
	public Map<String, String> priCard(@RequestParam("name") String name ,@RequestParam("elecId") int elecId ,HttpServletRequest request) throws UnsupportedEncodingException {
		String baseUrlPath=request.getScheme()+"://"+request.getServerName()+ request.getContextPath();
		if (request.getServerPort()!=80){	 
			baseUrlPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ request.getContextPath();
		}
		Map<String, String> result = new HashMap<String, String>();
		name=URLDecoder.decode(name,"UTF-8");
		Content content=portalPublishService.getElecCardHtmlUrlByTitleAndTopicId(name,elecId);
		result.put("success", "success");
		result.put("htmlUrl", baseUrlPath+content.getHtmlUrl());
		return result;		
	}
	@RequestMapping(value = "/portal/searchIndex")
	
	public String searchIndex(@RequestParam("keyWord") String keyWord ,
			ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {
		String baseUrlPath=request.getScheme()+"://"+request.getServerName()+ request.getContextPath();
		if (request.getServerPort()!=80){	 
			baseUrlPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ request.getContextPath();
		}
		//将前台页面传来的关键字转换为UTF-8,解决汉字乱字符问题。
		keyWord=URLDecoder.decode(keyWord,"UTF-8");
		model.put("baseUrlPath", baseUrlPath);
		model.put("storeUrl", baseUrlPath+"/portal/searchKeyWord");
		model.put("keyWord", keyWord);
		return "/admin/portal/search";
	}
	//龙口王维纲站内搜索 end
}
