<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String htmlData = request.getParameter("content") != null ? request
			.getParameter("content") : "";
	String catagoryId =request.getParameter("catagoryId");
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
				width : 888,
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
<body style="margin-top:20px;margin-left:20px" >
	<form name="picForm" id="picForm" enctype="multipart/form-data" method ="post">
	                    <font>照片              ：</font> <input type="file" name="tPic" id ="tPic" > <input id="upbutton" type="button" value="上传" onclick="uploadPic();">
	 </form>
	<form id="content_form" name="content_form" action="admin/portal/common/saveCard"
		method="post" onsubmit="return checkForm()">
			 <font>姓名              ：</font> <input type="text" size="20" name="title" id="titleId" 
				value="${content.title}"><font color=red>*</font>
				<br>
			 <font>所在单位： </font><input type="text" size="40"name="source" id="sourceId" 
				value="${content.source}"><font color=red>*</font>
			<br>
			 <font>现任职务： </font><input type="text" size="20" name="dzmpxrzw" id="dzmpxrzwId" 
				value="${content.dzmpxrzw}"><font color=red>*</font>
				<input type="hidden" value="<%=catagoryId %>" name="catagoryId">
				<input type="hidden" value="0" id="checkFlag" name="checkFlag">
				<input type="hidden" id="topicPic" name="topicPic">
				<br>
				 <font>办公电话： </font><input type="text" size="20" name="dzmpbgdh" id="dzmpbgdhId"
				value="${content.dzmpbgdh}"><font color=red>*</font>
				<br>
				<br>
			职责分工：
			 <textarea name="dzmpzzfg" id="dzmpzzfgId" style="width:600px;height:100px;" value="${content.dzmpzzfg}"></textarea>
		     <div style="display:none" ><textarea name="content" style="display:none" id="contentId"></textarea>
			<input type="submit" id="sbutton"  name="button" type="hidden" >
			</div>
	</form>
</body>
</html>