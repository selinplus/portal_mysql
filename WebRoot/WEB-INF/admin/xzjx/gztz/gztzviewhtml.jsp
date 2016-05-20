<%@page import="java.lang.reflect.Array"%>
<%@page import="org.ytgs.xzjx.model.XzjxGztz"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	 
	XzjxGztz tz=request.getAttribute("xzjxGztz") != null ? (XzjxGztz)request.getAttribute("xzjxGztz") : null;
	
	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<base href="<%=basePath%>">

<title>工作通知</title>
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
</script>
</head>
<body>
	<div class="divcenter clist" style="">
	<div class="cleft cbackground" >
    	<ul class="xzjxGztz" >
        	<li style="height:50px;"><h1>${xzjxGztz.xxbt}</h1></li>
            <li style="height:50px;text-align:center"><span>发布时间:</span><span>${xzjxGztz.fbsj}</span> <span>发布人:</span><span>${xzjxGztz.fbrMc}</span></span></li>
            <li style="border-top:#999 dashed 1px;">${xzjxGztz.content}</li>
            <li style="height:50px;text-align:center"><span>附件:</span><span>Attachnamelist</span></li> 
        </ul>
    </div>
    </div>
</body>
</html>