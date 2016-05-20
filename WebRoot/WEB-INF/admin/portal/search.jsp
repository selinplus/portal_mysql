<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<link rel="stylesheet" type="text/css" href="${baseUrlPath}/res/ext-4.2.1/resources/css/ext-all.css" />




<style>
.gridInner
{ font-family: "宋体";
  font-size: 12px;
   text-decoration: none;color: black;
   overflow:hidden;text-overflow:ellipsis;white-space:nowrap;
}
</style>
<script type="text/javascript" src="${baseUrlPath}/res/ext-4.2.1/bootstrap.js"></script>
<script type="text/javascript" src="${baseUrlPath}/res/ext-4.2.1/locale/ext-lang-zh_CN.js"></script>
<script type="text/javascript">	
    var vKeyWord="${keyWord}";
	var vStoreUrl="${storeUrl}";    //Ajax后台获取数据的URL

</script>
<script type="text/javascript" src="${baseUrlPath}/res/js/portal/templates/keyword.js"></script>
<!--[if lt IE 9]>
<script src="js/html5.js"></script>
<![endif]-->
<title>站内搜索</title>
    <link rel="stylesheet" type="text/css" href="${baseUrlPath}/res/repository/portal/col/home/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="${baseUrlPath}/res/repository/portal/col/home/css/backgroud.css"/>
<script language="javascript">
function hiddenThis(){
	var vCatagoryId=83;
	var vDiv="div"+vCatagoryId;
	var vUl="ul"+vCatagoryId;
	document.getElementById(vDiv).style.cssText="display:none;";
	document.getElementById(vUl).style.cssText="display:none;";
}
 </script>
</head>

<body>
<div class="divcenter header background">
    <div class="headerl">
        <div class="logo"></div>
    </div>
    <div class="headerr">
        <div class="biaoyu"></div>
        <div class="searchbar">
            <script type="text/javascript" src="${baseUrlPath}/res/repository/portal/common/weather.inc"></script>
            <form name="searForm" id="searchForm" method="post" action="" class="search">
                <input type="text" name="keyword" id="keyword" class="text" />
                <input type="button" name="btn" id="btn" class="btn" />
				
            </form>
        </div>
    </div>

</div>
<div class="divcenter nav">
    <script type="text/javascript" src="${baseUrlPath}/res/repository/portal/common/indexHead.inc"></script>
</div>
<div class="divcenter breadbackground bread fontcolor2" style="height:35px;">
	<span>
     <div id="nav1" class="navl">搜索关键字：${keyWord}</div> 
    </span>
</div>

<div class="divcenter clist" style="">
	<div class="cleft cbackground" >
    	<div id="artList" name="artList"></div>
    </div>
    <div class="cright cbackground">
    	<script type="text/javascript" src="${baseUrlPath}/res/repository/portal/common/54.inc"></script>
    </div>
</div>
<div style="height: 100px; clear: both;"></div>
<div class="divall footer background">
    <div class="divcenter" style="position: relative;">
    <p>龙之韵科技 版权所有</p>
    <a href="#" class="gotop"></a>
    </div>
</div>
<div style="height: 500px; clear: both;"></div>
</body>
</html>
