package org.ytgs.xzjx.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.ytgs.security.CustomUserDetails;
import org.ytgs.util.ConfigInfo;
import org.ytgs.util.DepartUtil;
import org.ytgs.xzjx.model.XzjxGztz;
import org.ytgs.xzjx.service.XzjxGztzService;



@Controller
public class XzjxGztzController {
	
	@Autowired
	ConfigInfo configInfo;
	
	@Autowired
	XzjxGztzService xzjxGztzService;
	
	@RequestMapping(value = "admin/xzjx/gztz/gztzeditPublish")
	public String gztzPublish(ModelMap model) {
		return "admin/xzjx/gztz/gztzeditPublish";
	}	
	@RequestMapping(value = "admin/xzjx/gztz/gztzviewhtml")
	public String gztzview(String id,ModelMap model) {
			XzjxGztz xzjxGztz=xzjxGztzService.getContentById(id);
			model.put("xzjxGztz", xzjxGztz);
		    model.put("id", id);
		return "admin/xzjx/gztz/gztzviewhtml";
	}

	
//	/**
//	 * 增加
//	 * 保存生产数据，同时保存历史数据
//	 * @param 
//	 * @return
//	 * @throws Exception
//	 */
	//此网页模式通知发布保存2014.11.25 ywh
//	@RequestMapping(value = "admin/xzjx/gztz/saveGztz")
//	@ResponseBody
//	public String saveContent(@Valid XzjxGztz xzjxGztz,
//			ModelMap model,HttpServletRequest request) throws UnsupportedEncodingException,
//			ParseException {  
//		// 应用服务器物理根目录，例如：C:\Tomcat 6.0\webapps\portal\
//		String basePhyPath = request.getSession().getServletContext()
//				.getRealPath("/");
//		// 应用服务器根目录，例如：http:/portal
//		String baseUrlPath = request.getScheme() + "://"
//				+ request.getServerName() + request.getContextPath();
//		if (request.getServerPort() != 80) {
//			baseUrlPath = request.getScheme() + "://" + request.getServerName()
//					+ ":" + request.getServerPort() + request.getContextPath();
//		}
//		// 文件保存目录路径
//		String savePath = basePhyPath + configInfo.getRealPhyPathForArt();
//				// 创建文件夹
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String ymd = sdf.format(new Date());
//		savePath += ymd + "/";
//		File dirFile = new File(savePath);
//		if (!dirFile.exists()) {
//			dirFile.mkdirs();
//		}
//		xzjxGztz.setXxbt((htmlspecialchars(xzjxGztz.getXxbt())));// 转义特殊符号
//		xzjxGztzService.addxzjxgztz(xzjxGztz);
//		return ymd;
//		}
	/**
	 * 明细查询（有分页功能）
	 * @param sdstzBzssrMx
	 * @return
	 * @throws Exception
	 */
//	通知明细查询已使用2014.1127 ywh
	@RequestMapping(value = "admin/xzjx/gztz/gztzquery")
	@ResponseBody
	public Map<String, Object> gztzQuery(XzjxGztz xzjxGztz)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<XzjxGztz> resultList = new ArrayList<XzjxGztz>();
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		xzjxGztz.setFbrDm(user.getUserAccount());
		xzjxGztzService.prepareCondition(xzjxGztz);// 预处理查询条件，，处理空条件
		try {
			long count=xzjxGztzService.getXzjxGztzMxCount(xzjxGztz);
			if (count<=10)
			{
			resultMap.put("totalCount",count);
			}//符合条件的总记录条数，用于前台分页
			else {
				resultMap.put("totalCount",10);
			}
			resultList = xzjxGztzService.getXzjxGztzMxByPage(xzjxGztz);
			resultMap.put("records", resultList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultMap;
	}
	
	
	/**
	 * 通知发布附件上传
	 * @param 
	 * @return
	 * @throws Exception
	 */
    //	通知发布附件上传2014.11.26 ywh
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "admin/xzjx/gztz/uploadGztzAttachmentFile")
	public void uploadGztzAttachmentFile(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(value = "attachment") MultipartFile... files)
			throws FileUploadException, IOException {
		// 文件保存目录路径
		String savePath = request.getSession().getServletContext()
				.getRealPath("/")
				+ "/WEB-INF/resource/repository/gztz/";

		// 最大文件大小
		long maxSize = 100000000;
		response.setContentType("text/html; charset=UTF-8");
		if (!ServletFileUpload.isMultipartContent(request)) {
			response.setContentType("text/html");
			response.getWriter().write(getError("请选择文件。"));
		}
		//文件名后缀名称	

		// 检查目录
		File uploadDir = new File(savePath);
		if (!uploadDir.isDirectory()) {
			response.setContentType("text/html");
			response.getWriter().write(getError("上传目录不存在。"));
		}
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			response.setContentType("text/html");
			response.getWriter().write(getError("上传目录没有写权限。"));
		}
		// 获取用户id,用id为名称创建目录
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		String dirName = user.getUserAccount();
		String tzpath="/WEB-INF/resource/repository/gztz/"+dirName + "/";

		// 创建文件夹
		savePath += dirName + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		for (MultipartFile f : files) {
			String fileName = f.getOriginalFilename();//
			String filenameupload=fileName;
			long fileSize = f.getSize();
			if (!f.isEmpty()) {
				// 检查文件大小
				if (fileSize > maxSize) {
					response.setContentType("text/html");
					response.getWriter().write(getError("上传文件大小超过限制。"));
				}

				// 文件保存原文件名+时间保存
				try {
					File uploadedFile = new File(savePath, fileName);
					f.transferTo(uploadedFile);
				} catch (Exception e) {
					response.setContentType("text/html");
					response.getWriter().write(getError("上传文件失败。"));
				}

				JSONObject obj = new JSONObject();
				obj.put("error", 0);
				obj.put("success", true);
				obj.put("attchname", fileName);
				obj.put("attchnameupload", filenameupload);
				obj.put("displayAttachName", f.getOriginalFilename());
				obj.put("path", tzpath);
				response.setContentType("text/html");
				response.getWriter().write(obj.toJSONString());
			}
		}

		
	}
	@SuppressWarnings("unchecked")
	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}
	
	/**
	 * 增加
	 * 保存生产数据，同时保存历史数据
	 * @param 
	 * @return
	 * @throws Exception
	 */
//	此网页模式通知发布保存（暂时保留不使用）2014.11.25 ywh
	@RequestMapping(value = "admin/xzjx/gztz/saveGztz")
	@ResponseBody
	public Map<String, Object> gztzSave(@RequestBody XzjxGztz xzjxGztz)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String clrq = sdf.format(cal.getTime());
		xzjxGztz.setFbrDeptDm(user.getUserDept());
		xzjxGztz.setFbrDm(user.getUserAccount());
		xzjxGztz.setFbrMc(user.getUserName());
		xzjxGztzService.insertdeptinfo (xzjxGztz);
		try {
			xzjxGztz.setFbsj(clrq);
			xzjxGztzService.addxzjxgztz(xzjxGztz);
//			addOperteLog(xzjxGztz, user, "ADD");//所有操作均需保存到操作历史记录表中
			resultMap.put("success", true);
			resultMap.put("records", xzjxGztz);//添加后返回带ID的对象给前台
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
		}
		return resultMap;
	}
	
	/**
	 * 明细查询（无分页功能）
	 * @param sdstzBzssrMx
	 * @return
	 * @throws Exception
	 */
//	通知明细查询已使用2014.1127 ywh
	@RequestMapping(value = "admin/xzjx/gztz/gztzviewquery")
	@ResponseBody
	public Map<String, Object> gztzviewQuery(XzjxGztz xzjxGztz)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<XzjxGztz> resultList = new ArrayList<XzjxGztz>();
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		xzjxGztz.setFbrDeptDm(user.getUserDept());
		xzjxGztz.setFbrDm(user.getUserAccount());
		xzjxGztz.setFbrMc(user.getUserName());
		xzjxGztzService.selectdeptinfo (xzjxGztz);
		xzjxGztzService.prepareCondition(xzjxGztz);// 预处理查询条件，，处理空条件
		try {
			long count=xzjxGztzService.getXzjxGztzviewMxCount(xzjxGztz);
			if (count<=10)
			{
			resultMap.put("totalCount",count);
			}//符合条件的总记录条数，用于前台分页
			else {
				resultMap.put("totalCount",10);
			}//符合条件的总记录条数，用于前台分页
			resultList = xzjxGztzService.getXzjxGztzviewMxByPage(xzjxGztz);
			resultMap.put("records", resultList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultMap;
	}
	/**
	 * 明细查询（有分页功能）
	 * @param sdstzBzssrMx
	 * @return
	 * @throws Exception
	 */
//	通知明细查询已使用2014.1127 ywh
	@RequestMapping(value = "admin/xzjx/gztz/gztzviewqueryquery")
	@ResponseBody
	public Map<String, Object> gztzviewqueryQuery(XzjxGztz xzjxGztz)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<XzjxGztz> resultList = new ArrayList<XzjxGztz>();
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		xzjxGztz.setFbrDeptDm(user.getUserDept());
		xzjxGztz.setFbrDm(user.getUserAccount());
		xzjxGztz.setFbrMc(user.getUserName());
		xzjxGztzService.selectdeptinfo (xzjxGztz);
		xzjxGztzService.prepareCondition(xzjxGztz);// 预处理查询条件，，处理空条件
		try {
			long count=xzjxGztzService.getXzjxGztzviewMxCount(xzjxGztz);
			resultMap.put("totalCount",count);
			//符合条件的总记录条数，用于前台分页
			xzjxGztz.setPage(xzjxGztz.getLimit()*(xzjxGztz.getPage()-1));
			resultList = xzjxGztzService.getXzjxGztzqueryMxByPage(xzjxGztz);
			resultMap.put("records", resultList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultMap;
	}
	/**
	 * 通知附件处理
	 * @param sdstzBzssrMx
	 * @return
	 * @throws Exception
	 */
   //	通知附件下载已使用2014.1127 ywh
	@RequestMapping(value = "admin/xzjx/gztz/downloadAttachmentFile/{dirName}/{fileName}.{fileExt}")
	@ResponseBody
	public FileSystemResource downloadgztzAttachmentFile(
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model, @PathVariable("fileName") String filName,
			@PathVariable("fileExt") String ext,@PathVariable("dirName") String dirName )
			throws UnsupportedEncodingException {
		// 获取用户id,用id为名称创建目录
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		String realFile =request.getSession().getServletContext()
				.getRealPath("/")
				+ "/WEB-INF/resource/repository/gztz/"
				+ dirName
				+ "/"
				+ filName+ "." + ext;

		return new FileSystemResource(new File(realFile));
	}
	 /** 删除多条记录
	 * @RequestBody：表明请求映射数据体，装载前台返回的数据体到对象列表中
	 * @param sdstzBzssrMxList
	 * @return
	 * @throws Exception
	 */
	//删除多条新增多缴税款 ywh  20140815
	@RequestMapping(value = "admin/xzjx/gztz/gztzDeleteList")
	@ResponseBody
	public Map<String, Object> gztzDeleteList(
			@RequestBody List<XzjxGztz> xzjxGztzMxList) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			for (XzjxGztz xzjxGztz : xzjxGztzMxList) {

				xzjxGztzService.removeXzjxGztzMx(xzjxGztz.getId());
//				  addOperteLog(xzjxGztz, user, "DELETE");//所有操作均需保存到操作历史记录表中
				}
			resultMap.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);

		}
		return resultMap;
	}
	/**
	 * 删除一条记录
	 * @RequestBody：表明请求映射数据体，装载前台返回的数据体到对象中
	 * @param 
	 * @return
	 * @throws Exception
	 */
	//删除一条记录  20141202
	@RequestMapping(value = "admin/xzjx/gztz/gztzDelete")
	@ResponseBody
	public Map<String, Object> gztzDelete(@RequestBody XzjxGztz xzjxGztz)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			xzjxGztzService.removeXzjxGztzMx(xzjxGztz.getId());
//				addOperteLog(sdstzBzssrMx, user, "DELETE");//所有操作均需保存到操作历史记录表中
				resultMap.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);

		}
		return resultMap;

	}
	/**
	 * 更新
	 * @param sdstzBzssrMx
	 * @return
	 * @throws Exception
	 */
	//更新通知 ywh  20141202
	@RequestMapping(value = "admin/xzjx/gztz/gztzupdate")
	@ResponseBody
	public Map<String, Object> bzssrUpdate(@RequestBody XzjxGztz xzjxGztz)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			xzjxGztzService.updateGztzMx(xzjxGztz);
//			addOperteLog(xzjxGztz, user, "UPDATE");//所有操作均需保存到操作历史记录表中
			resultMap.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);

		}
		return resultMap;

	}
	
	
}

