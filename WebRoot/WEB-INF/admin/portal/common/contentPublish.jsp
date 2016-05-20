<%@page import="org.ytgs.portal.common.model.Content"%>
<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
//	String htmlData = (Content)request.getParameter("content") != null ? request.getParameter("content") : "";
	 
	Content ic=request.getAttribute("content") != null ? (Content)request.getAttribute("content") : null;
	String htmlData="";
	if(ic!=null){
		htmlData =ic.getContent();
	}
	String catagoryId =request.getParameter("catagoryId");
	String artId =request.getParameter("artId");
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
	Ext.onReady(function(){
		
		Ext.Loader.setConfig({
    		enabled:true,
    		disableCaching:false,
    		paths:{
    			'Go':'res/ext-4.2.1/datatime/'
    		}
    	});
		Ext.require('Go.form.field.DateTime');
    	Ext.onReady(function(){
    		Ext.create('Go.form.field.DateTime',{
                renderTo:Ext.get('distimeDiv'),
    			fieldLabel:'日期选择器',    			
    			format:'Y-m-d H:i:s',
    			name: 'publishTime',
                width:300
    		});
    	});
	});
	function uploadPic(){
		Ext.Ajax.request({
		    url: 'admin/util/uploadTopicPicJson',
		    method:'POST',
		    form:'picForm',
		    isUpload : true,
		    success: function(response){
		    	
		    	var result=response.responseText||response.responseXML.body.innerText;		    	
		    	var obj = Ext.decode(result);
		        if(obj.error=='0'){
		        	Ext.Msg.alert('上传成功!');
		        	//Ext.get('topicPic').value=obj.url;
		        	document.getElementById('topicPic').value=obj.url;
		        	document.getElementById('upbutton').style.cssText="visibility:hidden;";
		        }else{
		        	Ext.Msg.alert(obj.message);
		        }
		    }
		});
	};
	function checkForm(){
		var varTitle=document.getElementById("titleId");
		var varSource=document.getElementById("sourceId");		
		if(varTitle.value==null||varTitle.value==""){
			Ext.Msg.alert("标题内容不能为空，请输入标题！");
			document.getElementById("checkFlag").value=1;
			return false;
		};
		if(varSource.value==null||varSource.value==""){
			Ext.Msg.alert("来源不能为空，请输入来源!");
			document.getElementById("checkFlag").value=1;
			return false;
		};	
		document.getElementById("checkFlag").value=0;
	    Ext.getBody().mask("保存中...");
		
	};
</script>
</head>
<body>
	<form id="content_form" name="content_form" action="admin/portal/common/saveContent"
		method="post" onsubmit="return checkForm()">
			标题： <input type="text" size="80" name="title" id="titleId"
				value="${content.title}"><font color=red>*</font>
			来源： <input type="text" width="20" name="source" id="sourceId"	value="${content.source}"><font color=red>*</font><p>
			摘要： <input type="text" size="80" name="introduction" id="introduction"	value="${content.introduction}"><p>
			引用URL： <input type="text" size="80" name="htmlUrl" id="htmlUrl"	value=""><p>
			
				<input type="hidden" value="<%=catagoryId %>" name="catagoryId">
				<input type="hidden" value="${artId}" name="artId">
				<input type="hidden" value="0" id="checkFlag" name="checkFlag">
				<input type="hidden" id="topicPic" name="topicPic">
		<textarea name="content"
			style="visibility:hidden;"><%=htmlspecialchars(htmlData)%></textarea>
			是否置顶:<input type="checkbox" name="bzZd">
			是否设置主题图片:<input type="checkbox" name="bzTopicPic">
			<div id="distimeDiv" style="height:4px;"></div>
		<input type="submit" id="sbutton"  name="button" style="visibility:hidden;">
	</form>
	 <form name="picForm" id="picForm" enctype="multipart/form-data" method ="post">
	   主题图片:<input type="file" name="tPic" id ="tPic" ><input id="upbutton" type="button" value="上传" onclick="uploadPic();">
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