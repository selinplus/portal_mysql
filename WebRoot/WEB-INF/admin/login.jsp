
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String error_msg = request.getParameter("error_msg");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css" href="res/ext-4.2.1/resources/css/ext-all.css" />
<link rel="stylesheet" type="text/css" href="res/ext-4.2.1/example.css" />
<link rel="stylesheet" type="text/css" href="res/ext-4.2.1/example.css" />
<style>
body {
	margin: 0;
	padding: 0;
	position: relative;
}
.header{
	height: 30%;
}
.footer{
	height: 30%;
}
.middle{
	background: #666;
	width: 100%;
	height:40%;
	text-align: center;
}
.login-form{
	padding:30px 0;
}
.container{
	margin: 0 auto;
	height: 100%;
}
.username {
	width: 100px;
	text-shadow: #000;
	font-size: 18px;
	border-bottom-color: gray;
	border-bottom-width: 1px;
	border-left-style: none;
	border-right-style: none;
	border-top-style: none;
}
.span-username{
	font-size: 18px;
	color:white;
	text-shadow: gray;
}
.span-password{
	font-size: 18px;
	color:white;
	text-shadow: gray;
}
.password{
	width: 100px;
	font-size: 18px;
	border-bottom-color: gray;
	border-bottom-width: 1px;
	border-left-style: none;
	border-right-style: none;
	border-top-style: none;
}
</style>
<script type="text/javascript" src="res/ext-4.2.1/bootstrap.js"></script>
<script type="text/javascript" src="res/ext-4.2.1/locale/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="res/ext-4.2.1/examples.js"></script>

<title>龙之韵信息管理平台</title>
</head>
<body>
	
		<div class="header"></div>		
		<div class="middle">
			<div class="login-form">
				<form name="loginform" action="j_spring_security_check" method="POST"
					id="iform">
					<span class="span-username">用户名</span>
					<input id="username" class="username" type='text' name="j_username" value="">
					<span class="span-password">密码</span>
					<input id="pwd" class="password" type='password' name='j_password' value="">

					<span><input type="button" width="101" height="46" value="登录" alt=""
						onclick="submitForm();" style="cursor: pointer;"></span>
				</form>
			</div>
		</div>
		<div class="footer"></div>
</body>
<script type="text/javascript">
	window.onload = function() {
		var error_msg = '${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}';
		document.forms[0].j_username.focus();
		if (error_msg.length > 2)
			Ext.example
					.msg('警告:',
							'${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}');
	}
	function submitForm() {
		document.getElementById('iform').submit();
	}
	document.onkeydown = function(event) {
		var e = event || window.event || arguments.caller.arguments[0];
		if (e && e.keyCode == 13) {
			submitForm();
		}
	}
</script>
</html>
