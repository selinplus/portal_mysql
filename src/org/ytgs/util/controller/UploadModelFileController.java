package org.ytgs.util.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;
import org.ytgs.util.ConfigInfo;
import org.ytgs.util.ZipUtils;

@Controller
public class UploadModelFileController {
    @Autowired
    private ConfigInfo configInfo;
	
    @SuppressWarnings("unchecked")    
	@RequestMapping(value = "/admin/util/uploadPageModelFile")  
    public void uploadPageModelFile(HttpServletRequest request,HttpServletResponse response,ModelMap model,@RequestParam(value="colName") String colName,@RequestParam(value="pageModalFile") MultipartFile... files) throws Exception {  
    
    	//文件保存目录路径
    	String savePath = request.getSession().getServletContext().getRealPath("/") + configInfo.getRealPhyPathForTpl();
    	String saveColPath = request.getSession().getServletContext().getRealPath("/") + configInfo.getRealPhyPathForCol();
    	
    	//文件保存目录URL
    	String baseUrlPath=request.getScheme()+"://"+request.getServerName()+ request.getContextPath();
		if (request.getServerPort()!=80)
		{	 baseUrlPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ request.getContextPath();}
    	     String saveUrl  = baseUrlPath + configInfo.getHtmlUrlForImage();

    	//定义允许上传的文件扩展名
    	HashMap<String, String> extMap = new HashMap<String, String>();
    	extMap.put("image", "gif,jpg,jpeg,png,bmp");
    	extMap.put("flash", "swf,flv");
    	extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
    	extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

    	//最大文件大小
    	long maxSize = 2000000;

    	response.setContentType("text/html; charset=UTF-8");

    	if(!ServletFileUpload.isMultipartContent(request)){
    		response.getWriter().write(getError("请选择文件。"));
    		return ;
    	}
    	//检查目录
    	File uploadDir = new File(savePath);
    	File uploadColDir = new File(saveColPath);
    	if(!uploadDir.isDirectory()){
    		response.getWriter().write(getError("上传目录不存在。"));
    		return ;
    	}
    	if(!uploadColDir.isDirectory()){
    		response.getWriter().write(getError("上传目录不存在。"));
    		return ;
    	}
    	//检查目录写权限
    	if(!uploadDir.canWrite()){
    		response.getWriter().write(getError("上传目录没有写权限。"));
    		return ;
    	}
    	if(!uploadColDir.canWrite()){
    		response.getWriter().write(getError("上传目录没有写权限。"));
    	}

    	String dirName = request.getParameter("dir");
    	if (dirName == null) {
    		dirName = "file";
    	}
    	if(!extMap.containsKey(dirName)){
    		response.getWriter().write(getError("目录名不正确。"));
    		return ;
    	}
    	//创建文件夹
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
    	if ("".equals(colName)||colName == null){
    		colName="col"+df.format(new Date()) + "_" +new Random().nextInt(1000);
    	}
    	saveColPath += colName + "/";
    	
    	File saveDirFile = new File(saveColPath);
    	if (!saveDirFile.exists()) {
    		saveDirFile.mkdirs();
    	}
    	for(MultipartFile f:files){
    		String fileName = f.getOriginalFilename();
    		long fileSize =f.getSize();
    		if (!f.isEmpty()) {
    			//检查文件大小
    			if(fileSize > maxSize){
    				response.getWriter().write(getError("上传文件大小超过限制。"));
    				return ;
    			}
    			//检查扩展名
    			String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    			if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
    				response.getWriter().write(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
    				return;
    			}


    			String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
    			try{
    				File uploadedFile = new File(savePath, newFileName);
    				f.transferTo(uploadedFile);
    			}catch(Exception e){
    				response.getWriter().write(getError("上传文件失败。"));
    				return;
    			}
    			ZipUtils.decompress(savePath+newFileName, saveColPath);
    			File newfile=new File(saveColPath+"/index.html");
                if(!newfile.exists()){//若在该目录下没有index.html，则报错
                  response.getWriter().write(getError("上传文件失败。"));
                  return;
                }
    			String newModelFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + ".html" ;
    			copyFile(saveColPath+"index.html",savePath+newModelFileName);
    			JSONObject obj = new JSONObject();
    			obj.put("success", true);
    			obj.put("error", 0);
    			obj.put("url", saveUrl + newFileName);
    			obj.put("catagoryUrl", colName);
    			obj.put("pageModal", newModelFileName);
    			response.getWriter().write(obj.toJSONString());
    			return ;
    		}
    	}
    	
    	response.getWriter().write(getError("上传文件失败。"));
    }  
   
    

    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin/util/uploadArtModelFile")  
    public void uploadArtModelFile(HttpServletRequest request,HttpServletResponse response,ModelMap model,@RequestParam(value="colName") String colName,@RequestParam(value="artModalFile") MultipartFile... files) throws Exception {  
    	
    	//文件保存目录路径
    	String savePath = request.getSession().getServletContext().getRealPath("/") + configInfo.getRealPhyPathForTpl();
    	String saveColPath = request.getSession().getServletContext().getRealPath("/") + configInfo.getRealPhyPathForCol();
    	
    	//文件保存目录URL
    	String baseUrlPath=request.getScheme()+"://"+request.getServerName()+ request.getContextPath();
		if (request.getServerPort()!=80)
		{	 baseUrlPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ request.getContextPath();}
    	     String saveUrl  = baseUrlPath + configInfo.getHtmlUrlForImage();

    	//定义允许上传的文件扩展名
    	HashMap<String, String> extMap = new HashMap<String, String>();
    	extMap.put("image", "gif,jpg,jpeg,png,bmp");
    	extMap.put("flash", "swf,flv");
    	extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
    	extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

    	//最大文件大小
    	long maxSize = 100000000;

    	response.setContentType("text/html; charset=UTF-8");

    	if(!ServletFileUpload.isMultipartContent(request)){
    		response.getWriter().write(getError("请选择文件。"));
    		return ;
    	}
    	//检查目录
    	File uploadDir = new File(savePath);
    	File uploadColDir = new File(saveColPath);
    	if(!uploadDir.isDirectory()){
    		response.getWriter().write(getError("上传目录不存在。"));
    	}
    	if(!uploadColDir.isDirectory()){
    		response.getWriter().write(getError("上传目录不存在。"));
    		return ;
    	}
    	//检查目录写权限
    	if(!uploadDir.canWrite()){
    		response.getWriter().write(getError("上传目录没有写权限。"));
    		return ;
    	}
    	if(!uploadColDir.canWrite()){
    		response.getWriter().write(getError("上传目录没有写权限。"));
    		return ;
    	}

    	String dirName = request.getParameter("dir");
    	if (dirName == null) {
    		dirName = "file";
    	}
    	if(!extMap.containsKey(dirName)){
    		response.getWriter().write(getError("目录名不正确。"));
    		return ;
    	}
    	//创建文件夹
    	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		
    	if ("".equals(colName)||colName == null){
    		colName="col"+df.format(new Date()) + "_" +new Random().nextInt(1000);
    	}
    	saveColPath += colName + "/";
    	
    	File saveDirFile = new File(saveColPath);
    	if (!saveDirFile.exists()) {
    		saveDirFile.mkdirs();
    	}
    	for(MultipartFile f:files){
    		String fileName = f.getOriginalFilename();
    		long fileSize =f.getSize();
    		if (!f.isEmpty()) {
    			//检查文件大小
    			if(fileSize > maxSize){
    				response.getWriter().write(getError("上传文件大小超过限制。"));
    				return ;
    			}
    			//检查扩展名
    			String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    			if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
    				response.getWriter().write(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
    				return ;
    			}

    			String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
    			try{
    				File uploadedFile = new File(savePath, newFileName);
    				f.transferTo(uploadedFile);
    			}catch(Exception e){
    				response.getWriter().write(getError("上传文件失败。"));
    				return ;
    			}
    			ZipUtils.decompress(savePath+newFileName, saveColPath);
    			File newfile=new File(saveColPath+"/index.html");
                if(!newfile.exists()){//若在该目录下没有index.html，则报错
                  response.getWriter().write(getError("上传文件失败。"));
                  return ;
                }
    			String newModelFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + ".html" ;
    			copyFile(saveColPath+"index.html",savePath+newModelFileName);
    			JSONObject obj = new JSONObject();
    			obj.put("success", true);
    			obj.put("error", 0);
    			obj.put("url", saveUrl + newFileName);
    			obj.put("catagoryUrl", colName);
    			obj.put("articleModal", newModelFileName);
    			response.getWriter().write(obj.toJSONString());
    			return ;
    		}
    	}
    	
    	response.getWriter().write(getError("上传文件失败。"));
    }  
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/admin/portal/catagory/uploadCatagoryPicJson")  
    public void uploadTopicPicJson(HttpServletRequest request,HttpServletResponse response,ModelMap model,@RequestParam(value="catagoryPicFile") MultipartFile... files) throws FileUploadException, IOException {  
    	
    	/**
    	 * KindEditor JSP
    	 * 
    	 * 
    	 */

    	//文件保存目录路径
    	String savePath = request.getSession().getServletContext().getRealPath("/") + configInfo.getRealPhyPathForImage();

    	//文件保存目录URL
    	String saveUrl  = configInfo.getHtmlUrlForImage();

    	//定义允许上传的文件扩展名
    	HashMap<String, String> extMap = new HashMap<String, String>();
    	extMap.put("image", "gif,jpg,jpeg,png,bmp");
    	extMap.put("flash", "swf,flv");
    	extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
    	extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

    	//最大文件大小
    	long maxSize = 10000000;

    	response.setContentType("text/html; charset=UTF-8");

    	if(!ServletFileUpload.isMultipartContent(request)){
    		response.getWriter().write(getError("请选择文件。"));
    		return ;
    	}
    	//检查目录
    	File uploadDir = new File(savePath);
    	if(!uploadDir.isDirectory()){
    		response.getWriter().write(getError("上传目录不存在。"));
    		return ;
    	}
    	//检查目录写权限
    	if(!uploadDir.canWrite()){
    		response.getWriter().write(getError("上传目录没有写权限。"));
    		return ;
    	}

    	String dirName = request.getParameter("dir");
    	if (dirName == null) {
    		dirName = "image";
    	}
    	if(!extMap.containsKey(dirName)){
    		response.getWriter().write(getError("目录名不正确。"));
    		return ;
    	}
    	//创建文件夹
    	savePath += dirName + "/";
    	saveUrl += dirName + "/";
    	File saveDirFile = new File(savePath);
    	if (!saveDirFile.exists()) {
    		saveDirFile.mkdirs();
    	}
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
    	String ymd = sdf.format(new Date());
    	savePath += ymd + "/";
    	saveUrl += ymd + "/";
    	File dirFile = new File(savePath);
    	if (!dirFile.exists()) {
    		dirFile.mkdirs();
    	}
    	for(MultipartFile f:files){
    		String fileName = f.getOriginalFilename();
    		long fileSize =f.getSize();
    		if (!f.isEmpty()) {
    			//检查文件大小
    			if(fileSize > maxSize){
    				response.getWriter().write(getError("上传文件大小超过限制。"));
    				return ;
    			}
    			//检查扩展名
    			String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    			if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
    				response.getWriter().write(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
    				return ;
    			}

    			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
    			String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
    			try{
    				File uploadedFile = new File(savePath, newFileName);
    				f.transferTo(uploadedFile);
    			}catch(Exception e){
    				response.getWriter().write(getError("上传文件失败。"));
    				return ;
    			}

    			JSONObject obj = new JSONObject();
    			obj.put("error", 0);
    			obj.put("success",true);
    			obj.put("url", saveUrl + newFileName);
    			response.getWriter().write(obj.toJSONString());
    		}
    	}
    	
    }  
    @SuppressWarnings("unchecked")
	private String getError(String message) {
    	JSONObject obj = new JSONObject();
    	obj.put("error", 1);
    	obj.put("success", false);
    	obj.put("failure", 1);
    	obj.put("message", message);
    	return obj.toJSONString();
    }
    
    public void copyFile(String src,String dest) throws IOException{
        FileInputStream in=new FileInputStream(src);
        File file=new File(dest);
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file);
        int c;
        byte buffer[]=new byte[1024];
        while((c=in.read(buffer))!=-1){
            for(int i=0;i<c;i++)
                out.write(buffer[i]);        
        }
        in.close();
        out.close();
    }
}
