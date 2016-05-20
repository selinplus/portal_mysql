<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String item = (String)request.getAttribute("item");
    String productId = (Integer)request.getAttribute("id")+"";
%>
    <!-- 商品详情 -->
<!DOCTYPE html>
    <html>
        <base href="<%=basePath%>">
        <head lang="en">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link href="res_wechat/css/__main.css" rel="stylesheet">
        <link href="res_wechat/css/bootstrapValidator.min.css" rel="stylesheet">

        <script src="res_wechat/js/jquery-1.11.3.min.js"></script>
        <script src="res_wechat/js/bootstrap.min.js"></script>
        <script src="res_wechat/js/bootstrapValidator.min.js"></script>
        <script src="res_wechat/js/tools_syl.js"></script>
        <script>
            //点击标签页的时候
            function changeTabs(type){
                if(type==2){
                    setProductInfo();
                }else if(type==3){
                    setEvaluation();
                }
            }
            //加载商品参数
            function setProductInfo(){
                var parent = $('#second');
                parent.empty();
                $.ajax({
                type:'POST',
                url:'products/query/id/<%=productId%>',
                success:function(result){
                //返回结果--成功
                if(result.success){
                if(result.data!=null){
                    var product = result.data;
                    var child = creatHtmlElementByJquery('div','container,product-detail-list',null);
                    parent.append(child);
                    var tmp = creatHtmlElementByJquery('div','row',null);
                    child.append(tmp);
                    var t = creatHtmlElementByJquery('table','table',null);
                    tmp.append(t);
                    //商品名称
                    t.append($('<tr></tr>').append(creatHtmlElementByJquery('td','col-xs-4,text-muted,font-lg','商品名称'))
                    .append(creatHtmlElementByJquery('td','col-xs-8',product.item)));
                    //剩余信息<i class="fa fa-rmb color-primary">100.00</i>
                    var obj = creatHtmlElementByJquery('i','fa,fa-rmb,color-primary',product.commonPrice);
                    t.append($('<tr></tr>').append(creatHtmlElementByJquery('td','text-muted,font-lg','商品价格'))
                    .append(creatHtmlElementByJquery('td','col-xs-8',null).append(obj)));
                    t.append($('<tr></tr>').append(creatHtmlElementByJquery('td','text-muted,font-lg','原产地'))
                    .append(creatHtmlElementByJquery('td','col-xs-8',product.co)));
                    t.append($('<tr></tr>').append(creatHtmlElementByJquery('td','text-muted,font-lg','包装类型'))
                    .append(creatHtmlElementByJquery('td','col-xs-8',product.overpack)));
                    t.append($('<tr></tr>').append(creatHtmlElementByJquery('td','text-muted,font-lg','品种'))
                    .append(creatHtmlElementByJquery('td','col-xs-8',product.varity)));
                    t.append($('<tr></tr>').append(creatHtmlElementByJquery('td','text-muted,font-lg','配送方式'))
                    .append(creatHtmlElementByJquery('td','col-xs-8',product.deliveryMode)));
                    t.append($('<tr></tr>').append(creatHtmlElementByJquery('td','text-muted,font-lg','配送范围'))
                    .append(creatHtmlElementByJquery('td','col-xs-8',product.deliveryRange)));
                    t.append($('<tr></tr>').append(creatHtmlElementByJquery('td','text-muted,font-lg','商品重量'))
                    .append(creatHtmlElementByJquery('td','col-xs-8','约'+product.kg+'kg')));
                    t.append($('<tr></tr>').append(creatHtmlElementByJquery('td','text-muted,font-lg','储藏方式'))
                    .append(creatHtmlElementByJquery('td','col-xs-8',product.storageMode)));
                    t.append($('<tr></tr>').append(creatHtmlElementByJquery('td','text-muted,font-lg','备注'))
                    .append(creatHtmlElementByJquery('td','col-xs-8',product.remarks)));
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
            }
            //加载用户评论
            function setEvaluation(){

            }
        </script>

        <title><%=item%></title>
        </head>
        <!-- 默认使用小号字体 -->
        <body class="small">
        <!-- 轮播详情图片 -->
        <div id="carousel1" class="carousel slide">
        <ol class="carousel-indicators">
        <li data-target="#carousel1" data-slide-to="0" class="active"></li>
        <li data-target="#carousel1" data-slide-to="1"></li>
        <li data-target="#carousel1" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner">
        <div class="item active">
        <img src="res_wechat/images/products/<%=productId%>/detail1.jpg">
        </div>
        <div class="item">
        <img src="res_wechat/images/products/<%=productId%>/detail2.jpg">
        </div>
        <div class="item">
        <img src="res_wechat/images/products/<%=productId%>/detail3.jpg">
        </div>
        </div>
        <a class="carousel-control left" href="#carousel1" data-slide="prev">&lsaquo;</a>
        <a class="carousel-control right" href="#carousel1" data-slide="next">&rsaquo;</a>
        </div>
        <ul id="myTab" class="nav nav-tabs">
        <li class="active"><a href="#first" data-toggle="tab">图片详情</a></li>
        <li><a href="#second" data-toggle="tab" onclick="changeTabs(2);">商品参数</a></li>
        <li><a href="#third" data-toggle="tab" onclick="changeTabs(3);">用户评价</a></li>
        </ul>
        <div class="tab-content">
        <div id="first" class="tab-pane in active fade">
        暂无
        </div>
        <div id="second" class="tab-pane fade">
        <!-- 商品详细信息 -->
        </div>
        <!-- 显示用户的评价 -->
        <div id="third" class="tab-pane fade">
        <ul class="media-list">
        <li class="media">
        <a href="#" class="pull-left">
        <img class="media-object" src="./images/tes2t.jpg">
        </a>
        <div class="media-body">
        <span class="media-heading">用户名</span>
        <span class="media-heading pull-right color-primary">
        <i class="fa fa-star"></i>
        <i class="fa fa-star-o"></i>
        <i class="fa fa-star-o"></i>
        <i class="fa fa-star-o"></i>
        <i class="fa fa-star-o"></i>
        </span>
        <p>评价内容</p>
        <span class="media-bottom text-muted">评价时间</span>
        </div>
        </li>
        <li class="media">
        <a href="#" class="pull-left">
        <img class="media-object" src="./images/tes2t.jpg">
        </a>
        <div class="media-body">
        <span class="media-heading">用户名</span>
        <span class="media-heading pull-right">评价星级</span>
        <p>评价内容</p>
        <span class="media-bottom text-muted">评价时间</span>
        </div>
        </li>
        </ul>
        </div>
        </div>


        <!-- 底部的导航栏 -->
        <div class="navbar navbar-default navbar-fixed-bottom" role="navigation">
        <div class="navbar-collapse">
        <ul class="nav navbar-nav navbar-nav-bottom">
        <li>
        <a href="./index.html">
        <span class="fa fa-home fa-2x">
        </span>
        <span>首页</span>,
        </a>
        </li>
        <li>
        <a href="./vendor.html">
        <span class="fa fa-sitemap fa-2x">
        </span>
        <span>分销商</span>
        </a>
        </li>
        <li>
        <a href="#">
        <span class="fa fa-cart-plus fa-2x">
        </span>
        <span class="">加入购物车</span>
        </a>
        </li>
        <li>
        <a href="#">
        <span class="fa fa-money fa-2x">
        </span>
        <span>立即购买</span>
        </a>
        </li>
        </ul>
        </div>
        </div>
    </body>
</html>