<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!-- 称为供应商页面 -->
<!DOCTYPE html>
<html>
<head lang="en">
	<base href="<%=basePath%>">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="wechat/css/__main.css" rel="stylesheet">
    <link href="wechat/css/bootstrapValidator.min.css" rel="stylesheet">

    <script src="wechat/js/jquery-1.11.3.min.js"></script>
    <script src="wechat/js/bootstrap.min.js"></script>
    <script src="wechat/js/bootstrapValidator.min.js"></script>
    <script src="wechat/js/tools.js"></script>
    
    <script type="text/javascript">
    	var path = '<%=path%>';	
    	function insertVendorInf(){
    		var name = document.getElementById("name").value;
    		var phone = document.getElementById("phone").value;
    		var wechatnum = document.getElementById("wechatnum").value;
    		
    		if(name==""){
    			cus_popover('name',"提示：","用户名不能为空","top","line-alone form-group");
    			return false;
    		}else if(phone==""){
    			cus_popover('name',"提示：","电话号码不能为空","top","line-alone form-group");
    			return false;
    		}else if(wechatnum==""){
    			cus_popover('name',"提示：","微信号不能为空","top","line-alone form-group");
    			return false;
    		}

			
			/* document.getElementById("formid").submit(); */
			$.ajax({
				url:path+'/vendor/insertVendorInf',
				data:{
					parentId:3,
					userId:4,
					name:name,
					phone:phone,
					wechatnum:wechatnum,
					level:1
				},
				dataType:"json",
				type:"post",
				success:function(result){
					if(result.success){
						alert(result.message);
					}
				},
				error:function(result){
					if(result.success){
						alert(result.message);
					}
				}
			});
    	}
    </script>

    <title>申请分销商</title>
</head>
<!-- 默认使用小号字体 -->
<body class="small body-gray">
<!-- 页头 -->
<div class="header clearfix">
    <img src="wechat/images/header.jpg" class="header-bg-lg">
</div>
<!--申请分销商 -->
<div class="panel-body">
    <span>欢迎加入
        <span class="text-primary font-lg"><strong>公司名称</strong></span>
        ,请填写申请信息!
    </span>
    <span class="line-alone">你的邀请人：
        <span class="text-primary font-lg"><strong>邀请人</strong></span>
    </span>
<!--     <span class="color-primary line-alone">请耐心等待总部核实!</span> -->
    <!-- 空行 -->
    <span class="line-alone">&nbsp;</span>
    <!-- 分销商信息 -->
    <form role="form" id="formid" name="personForm">
<!--         <div class="form-group">
            <label class="label control-label color-gray">邀请人信息</label>
            <input class="form-control input line-alone" type="text" placeholder="请输入邀请人姓名">
        </div> -->
        <div class="form-group">
            <label class="label control-label color-gray">个人信息</label>
            <input id = "name" class="form-control input line-alone" type="text" placeholder="请输入个人真实姓名，用于佣金结算">
            <input id = "phone" class="form-control input line-alone" type="text" placeholder="请填写手机号码方便联系">
            <input id = "wechatnum" class="form-control input line-alone" type="text" placeholder="请输入个人微信号">
        </div>
        <button class="form-control btn btn-danger" type="button" onclick = "insertVendorInf()">成为分销商</button>
    </form>

    <!-- 分销商特权 -->
    <span class="line-alone">&nbsp;</span>
    <span class="text-muted line-alone">分销商特权</span>
    <div class="vendor-rights">
        <div>
                <span class="fa-stack fa-2x">
                    <i class="fa fa-circle fa-stack-2x color-success"></i>
                    <i class="fa fa-hospital-o fa-stack-1x color-white"></i>
                 </span>
            <div>
                独立微店<br>
                <span class="text-muted">拥有自己的微店及推广二维码</span>
            </div>
        </div>
        <hr>
        <div>
                <span class="fa-stack fa-2x">
                    <i class="fa fa-circle fa-stack-2x color-warning"></i>
                    <i class="fa fa-rmb fa-stack-1x color-white"></i>
                 </span>
            <div>
                销售拿佣金<br>
                <span class="text-muted">微店卖出商品您可以获得佣金</span>
            </div>
        </div>
        <hr>
        <div>
            <p class="text-muted">
                分销商的商品销售统一由厂家直接收款、直接发货并提供售后服务，分销佣金由厂家统一设置
            </p>
        </div>
    </div>
</div>

<!-- 页脚 -->
<div class="footer">Copyright&nbsp;&copy;&nbsp;2015</div>
<!-- 底部的导航栏 -->
<div class="navbar navbar-default navbar-fixed-bottom" role="navigation">
    <div class="navbar-collapse">
        <ul class="nav navbar-nav navbar-nav-bottom">
            <li>
                <a href="./index.html">
                    <span class="fa fa-home fa-2x"></span>
                    <span>首页</span>
                </a>
            </li>
            <li class="active">
                <a href="#">
                    <span class="fa fa-sitemap fa-2x"></span>
                    <span>分销商</span>
                </a>
            </li>
            <li>
                <a href="./shoppingCart.html">
                    <span class="fa fa-shopping-cart fa-2x"></span>
                    <span>购物车</span>
                </a>
            </li>
            <li>
                <a href="./me.html">
                    <span class="fa fa-user fa-2x"></span>
                    <span>我</span>
                </a>
            </li>
        </ul>
    </div>
</div>
</body>
</html>