<%@page import="org.ytgs.portal.wechat.login.model.LoginUser"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	LoginUser loginUser = request.getSession().getAttribute("disUser")!=null?(LoginUser)request.getSession().getAttribute("disUser"):null;
%>

<!-- 首页 -->
<!DOCTYPE html>
<html>
<head lang="en">
<base href="<%=basePath%>">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="res_wechat/css/__main.css" rel="stylesheet">
<link href="res_wechat/css/bootstrapValidator.min.css" rel="stylesheet">

<script src="res_wechat/js/jquery-1.11.3.min.js"></script>
<script src="res_wechat/js/bootstrap.min.js"></script>
<script src="res_wechat/js/bootstrapValidator.min.js"></script>
<script src="res_wechat/js/tools_syl.js"></script>
<script>
	var path = '<%=path%>';
	var disUser = '<%=loginUser%>'
	
	$(document).ready(function(){
		alert(disUser);
	
	 $.ajax({
        type:'POST',
        url:path+'/products/query/index',
        success:function(result){
        //返回结果--成功
            if(result.success){
                if(result.data!=null){
                var parent = $('#productsDisplay');
                for(var i = 0; i < result.data.length;i++){
                    var product = result.data[i];
                    var child = $('<div></div>');
                        //背景图片
                        var obj = $('<img>');
                        obj.attr('src','res_wechat/images/products/'+product.id+'/index.jpg');
                        child.append(obj);
                        obj = $('<div></div>');
                        //描述信息    ;
                        var obj2 = null;
                        obj.append(creatHtmlElementByJquery('span',null,product.desc));
                        //商品的价格信息
                        obj2=$('<div></div>');
                        var obj3 = creatHtmlElementByJquery('span','original','原价');
                        obj3.append(creatHtmlElementByJquery('i','fa fa-rmb',null));
                        obj3.append(product.originPrice);
                        obj2.append(obj3);
                        obj3 = $('<span></span>');
                        obj3.addClass('discount');
                        var obj6 =$('<span></span>');
                        obj6.append(creatHtmlElementByJquery('i','fa fa-rmb',null));
                        obj3.append(obj6);
                        obj3.append(product.commonPrice);
                        obj2.append(obj3);
                        obj.append(obj2);
                        //购买按钮
                        var obj5 = creatHtmlElementByJquery('div','btn-once-buy',null);
                        obj2 = $('<a></a>');
                        obj2.attr('href',path+'/products/query?id='+product.id);
                        obj2.append(creatHtmlElementByJquery('span',null,'立即'));
                        obj2.append(creatHtmlElementByJquery('span',null,'购买'));
                        obj5.append(obj2);
                        obj.append(obj5);
                        //
                        child.append(obj);
                        parent.append(child);
                }
                }
            }else{
            //返回结果--失败
                alert('服务器繁忙，稍后重试');
            }
        },
        error:function(){
            alert('服务器繁忙，稍后重试');
        },
        dataType:'json'
        });
        });
</script>
<title>首页</title>
</head>
<!-- 默认使用小号字体 -->
<body class="small body-gray">
	<!-- 页头 -->
	<div class="header clearfix">
		<img src="res_wechat/images/header.jpg" class="header-bg-lg"> <img
			src="res_wechat/images/test.jpg" class="header-bg-sm"> <span
			class="text-muted">共有6个宝贝</span>
	</div>
	<!-- 导航栏 -->
	<div class="navbar navbar-default navbar-inverse" role="navigation">
		<div class="navbar-collapse">
			<ul class="nav navbar-nav navbar-nav-top">
				<li><a href="<%=path%>/products"> <span
						class="fa-stack fa-2x"> <i
							class="fa fa-circle-thin fa-stack-2x"></i> <i
							class="fa fa-stack-1x">果</i> </span> <span> 鲜果园</span> </a></li>
				<li><a href="./shoppingCart.html"> <span
						class="fa-stack fa-2x"> <i
							class="fa fa-circle-thin fa-stack-2x"></i> <i
							class="fa fa-stack-1x">购</i> </span> <span> 购物车</span> </a></li>
				<li><a href="./commission.html"> <span
						class="fa-stack fa-2x"> <i
							class="fa fa-circle-thin fa-stack-2x"></i> <i
							class="fa fa-stack-1x">金</i> </span> <span> 佣金机制</span> </a></li>
			</ul>
		</div>
	</div>
	<!-- 搜索框 -->
	<div class="container search_bg">
		<div class="row">
			<div class="col-xs-12">
				<div class="search_group">
					<span class="fa fa-search"></span> <input type="text"
						class="input-sm" placeholder="商品搜索:请输入商品关键词">
					<button type="button" class="btn btn-primary btn-sm">搜索</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 图片展示 -->
	<div id="productsDisplay" class="display"></div>
	<!-- 页脚 -->
	<div class="footer">Copyright&nbsp;&copy;&nbsp;2015</div>
	<!-- 底部的导航栏 -->
	<div class="navbar navbar-default navbar-fixed-bottom"
		role="navigation">
		<div class="navbar-collapse">
			<ul class="nav navbar-nav navbar-nav-bottom">
				<li class="active"><a href="#"> <span
						class="fa fa-home fa-2x"></span> <span>首页</span> </a></li>
				<li><a href="./vendor.html"> <span
						class="fa fa-sitemap fa-2x"></span> <span>分销商</span> </a></li>
				<li><a href="./shoppingCart.html"> <span
						class="fa fa-shopping-cart fa-2x"></span> <span>购物车</span> </a></li>
				<li><a href="./me.html"> <span class="fa fa-user fa-2x"></span>
						<span>我</span> </a></li>
			</ul>
		</div>
	</div>
</body>
</html>
