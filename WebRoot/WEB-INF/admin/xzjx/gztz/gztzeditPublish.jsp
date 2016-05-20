<%@page import="org.ytgs.xzjx.model.XzjxGztz"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	 
	XzjxGztz ic=request.getAttribute("xzjxGztz") != null ? (XzjxGztz)request.getAttribute("xzjxGztz") : null;
	String htmlData="";
	if(ic!=null){
		htmlData =ic.getContent();
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<base href="<%=basePath%>">

<title>Content</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" type="text/css" href="res/ext-4.2.1/resources/css/ext-all.css"/>
<script type="text/javascript" src="res/ext-4.2.1/bootstrap.js"></script>
<script type="text/javascript" src="res/ext-4.2.1/locale/ext-lang-zh_CN.js"></script>

<link rel="stylesheet"
	href="res/kindeditor-4.1.7/themes/default/default.css" />
<link rel="stylesheet"
	href="res/kindeditor-4.1.7/plugins/code/prettify.css" />
<script charset="utf-8" src="res/kindeditor-4.1.7/kindeditor.js"></script>
<script charset="utf-8" src="res/kindeditor-4.1.7/lang/zh_CN.js"></script>
<script charset="utf-8"
	src="res/kindeditor-4.1.7/plugins/code/prettify.js"></script>
<script>
    
	KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="content"]', {
				uploadJson : 'admin/util/uploadJson',
				fileManagerJson : 'admin/util/fileManagerJson',
				allowFileManager : false,
				width : '99%',
				height: 400
			});
			prettyPrint();
		});
	function uploadFile(file,upbutton){
		Ext.Ajax.request({
		    url: '/admin/util/uploadGztsAttachmentFile',
		    method:'POST',
		    form:'picForm',
		    isUpload : true,
		    success: function(response){
		    	
		    	var result=response.responseText||response.responseXML.body.innerText;		    	
		    	var obj = Ext.decode(result);
		        if(obj.error=='0'){
		        	Ext.Msg.alert('上传成功!');
		        	//Ext.get('topicPic').value=obj.url;
		        	document.getElementById(file).value=obj.url;
		        	document.getElementById(upbutton).style.cssText="visibility:hidden;";
		        }else{
		        	Ext.Msg.alert(obj.message);
		        }
		    }
		});
	};
	function checkForm(){
		var varTitle=document.getElementById("xxbtId");	
		if(varTitle.value==null||varTitle.value==""){
			Ext.Msg.alert("标题内容不能为空，请输入标题！");
			document.getElementById("checkFlag").value=1;
			return false;
		};
		document.getElementById("checkFlag").value=0;
	    Ext.getBody().mask("保存中...");
		
	};
</script>
</head>
<body>
	<form id="xzjxGztz_form" name="xzjxGztz_form" action="admin/xzjx/gztz/saveGztz"
		method="post" onsubmit="return checkForm()">
			标题： <input type="text" size="80" name="xxbt" id="xxbtId"
				value="${xzjxGztz.xxbt}"><font color=red>*</font>
				<input type="hidden" value="0" id="checkFlag" name="checkFlag">
		<textarea name="content"
		style="visibility:hidden;"><%=htmlspecialchars(htmlData)%></textarea>
		<input type="submit" id="sbutton"  name="button" style="visibility:hidden;">
	</form>
	 <form name="fileForm" id="fileForm" enctype="multipart/form-data" method ="post">
	  附件1:<input type="file" name="file1" id ="file1" ><input id="upbutton1" type="button" value="上传" onclick="uploadFile(file1,upbutton1);">
	 <br>
	  附件2:<input type="file" name="file2" id ="file2" ><input id="upbutton2" type="button" value="上传" onclick="uploadFile(file2,upbutton2);">
	 <br> 
	  附件3:<input type="file" name="file3" id ="file3" ><input id="upbutton3" type="button" value="上传" onclick="uploadFile(file3,upbutton3);">
	 <br> 
	  附件4:<input type="file" name="file4" id ="file4" ><input id="upbutton4" type="button" value="上传" onclick="uploadFile(file4,upbutton4);">
	 </form>
</body>
</html>
<%!private String htmlspecialchars(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}%>