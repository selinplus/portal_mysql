package org.ytgs.util.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.ytgs.util.BCSFileService;
import org.ytgs.util.ConfigInfo;

@Controller
public class UploadFileController {
	@Autowired
	private ConfigInfo configInfo;

	@RequestMapping(value = "/admin/util/uploadFile")
	public String uploadFile(ModelMap model) {
		return "/admin/util/uploadFile";
	}

	@RequestMapping(value = "/admin/util/fileManagerJson")
	public String fileManagerJson(ModelMap model) {
		return "/admin/util/fileManagerJson";
	}
/**
 * 文章发布中的图片上传
 * @param request
 * @param response
 * @param model
 * @param files
 * @return
 * @throws FileUploadException
 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/admin/util/uploadJson")
	public String uploadJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(value = "imgFile") MultipartFile... files)
			throws FileUploadException {
		String url="";
		
		// 最大文件大小
		long maxSize = 20000000;
		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
		response.setContentType("text/html; charset=UTF-8");
		if (!ServletFileUpload.isMultipartContent(request)) {
			return getError("请选择文件。");
		}
		String dirName = request.getParameter("dir");
		if (dirName == null) {
			dirName = "image";
		}
		for (MultipartFile f : files) {
			String fileName = f.getOriginalFilename();
			long fileSize = f.getSize();
			if (!f.isEmpty()) {
				// 检查文件大小
				if (fileSize > maxSize) {
					return getError("上传文件大小超过限制。");
				}
				// 检查扩展名
				String fileExt = fileName.substring(
						fileName.lastIndexOf(".") + 1).toLowerCase();
				if (!Arrays.<String> asList(extMap.get(dirName).split(","))
						.contains(fileExt)) {
					return getError("上传文件扩展名是不允许的扩展名。\n只允许"
							+ extMap.get(dirName) + "格式。");
				}
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = df.format(new Date()) + "_"+ new Random().nextInt(1000) + "." + fileExt;
				try {
					File uploadedFile = File.createTempFile("java-sdk-", ".txt");
					uploadedFile.deleteOnExit();					
					f.transferTo(uploadedFile);
					BCSFileService.object="/portal/media/"+newFileName;
					url=BCSFileService.transferTOBCS(uploadedFile);
				} catch (Exception e) {
					return getError("上传文件失败。");
				}

				JSONObject obj = new JSONObject();
				obj.put("error", 0);
				obj.put("url", url);
				return obj.toJSONString();
			}
		}

		return "/admin/util/uploadJson";
	}

	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/admin/util/uploadTopicPicJson")
	public String uploadTopicPicJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(value = "tPic") MultipartFile... files)
			throws FileUploadException {
		return uploadJson(request,response,model,files);
	}

	@SuppressWarnings("unchecked")
	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}

	
}
