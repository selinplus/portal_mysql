
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>通知发布</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<!--[if lt IE 9]>
<script src="res/js/util/html5.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="res/ext-4.2.1/resources/css/ext-all.css"/>
<script type="text/javascript" src="res/ext-4.2.1/bootstrap.js"></script>
<script type="text/javascript" src="res/ext-4.2.1/locale/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="res/exportToExcel/exportExcel.js"></script>
<script type="text/javascript">
var userName='${userName}';
var vUserDeptId='${userDeptStandId}';
var vUserDeptName='${userDeptStandName}';
var basePath='<%=basePath%>';

</script>
<script type="text/javascript" src="res/js/xzjx/gztz/view/view.js" defer="defer"></script>

  </head>
  <body>
  </body>
</html>
