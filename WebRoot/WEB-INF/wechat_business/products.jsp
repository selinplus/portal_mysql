<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!-- 全部商品 -->
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
        //改变布局样式
        function changeLayout(obj){
            var btn = $(obj);
            btn.children().toggleClass('fa-table');
            btn.children().toggleClass('fa-list');
            var divObj = $('#product-thumb');
            divObj.toggleClass('product-thumb-list');
            divObj.toggleClass('product-thumb-table');
        }
        //查询按照参数查找商品信息
        //新品1，销量升序2降序3，价格升序4降序5
        function queryProducts(obj,type){
            if(obj!=null){
                var btn = $(obj);
                if(btn.hasClass('active')){
                }
                //修正各个按钮的选中状态
                var btns = btn.siblings('button');
                var i = 0;
                if(btns!=null){
                    for(i = 0; i <  btns.length; i++){
                     $(btns[i]).removeClass('active');
                    }
                }
                btn.toggleClass('active');
                //修正价格或者销量的下拉图标
                var carets = btn.children('i');
                if(carets!=null){
                    for(i = 0; i < carets.length;i++){
                        var tmp = $(carets[i]);
                        tmp.toggleClass('fa-caret-down');
                        tmp.toggleClass('fa-caret-up');
                        if(tmp.hasClass('fa-caret-down')){
                            type = type+1;
                        }
                    }
                }
            }
            $.ajax({
            type:'POST',
            data:{
                type:type
            },
            url:path+'/products/query/valid',
            success:function(result){
            var parent = $('#product-thumb');
            //清空原来的元素
            parent.empty();
            //返回结果--成功
            if(result.success){
            if(result.data!=null){
            for(var i = 0; i < result.data.length;i++){
            var product = result.data[i];
            var child = $('<div></div>');
            //缩略图
            var obj1 = $('<img>');
            obj1.attr('src','res_wechat/images/products/'+product.id+'/thunmb.jpg');
            child.append(obj1);
            //价格\名称等信息
            obj1 = $('<div></div>');
            //名称  带有跳转连接
            var obj2 =creatHtmlElementByJquery('a',null,product.item);
            obj2.attr('href',path+'/product/id/'+product.id);
            obj1.append($('<span></span>').append(obj2));
            //价格
            var obj3 = $('<span></span>').append(creatHtmlElementByJquery('i','fa fa-rmb fa-lg',product.commonPrice));
            var obj4 = $('<p></p>').append(creatHtmlElementByJquery('i','fa fa-rmb',product.originPrice));
            //obj2 = $('<div></div>').append(creatHtmlElementByJquery('span',null,product.commonPrice)).append(creatHtmlElementByJquery('p',null,product.originPrice));
            obj2 = $('<div></div>').append(obj3).append(obj4);
            obj1.append(obj2);
            //折扣和销量
            var salesVolume=0;
            if(product.productStocks!=null&&product.productStocks.length>0){
            salesVolume = product.productStocks[0].salesVolume;
            }
            obj2 = $('<div></div>').append(creatHtmlElementByJquery('span',null,product.commonDiscount+'折')).append('销量：'+salesVolume);
            obj1.append(obj2);
            child.append(obj1);
            parent.append(child);
            }
            }
            }else{
            //返回结果--失败
            alert('服务器繁忙，稍后重试');
            }
            //结束的空间
            // parent.append(creatHtmlElementByJquery('span','bottom-space',' '));
            },
            error:function(){
            alert('服务器繁忙，稍后重试');
            },
            dataType:'json'
            });
        }

        $(document).ready(function(){
            queryProducts(null,1);
        });
    </script>
    <title>商品列表</title>
</head>
<!-- 默认使用小号字体 -->
<body class="small body-white">
<!-- 分类查询按钮 -->
<div class="type-search-btn-group">
    <div class="btn-group">
        <button class="btn btn-default btn-sm active" onclick="queryProducts(this,1);">新品</button>
        <button class="btn btn-default btn-sm" onclick="queryProducts(this,2);">销量&nbsp;<i class="fa fa-caret-down"></i></button>
        <button class="btn btn-default btn-sm" onclick="queryProducts(this,4);">价格&nbsp;<i class="fa fa-caret-up"></i></button>
        <button class="btn btn-default btn-sm" onclick="changeLayout(this);"><i class="fa fa-list"></i></button>
    </div>
</div>
<!-- 全部商品展示页面 -->
<div id="product-thumb" class="product-thumb-list">
</div>
<!-- 底部的导航栏 -->
<div class="navbar navbar-default navbar-fixed-bottom" role="navigation">
    <div class="navbar-collapse">
        <ul class="nav navbar-nav navbar-nav-bottom">
            <li>
                <a href="./index.html">
                    <span class="fa fa-home fa-2x">
                    </span>
                    <span>首页</span>
                </a>
            </li>
            <li  class="active">
                <a href="#">
                    <span class="fa fa-apple fa-2x">
                    </span>
                    <span>商品</span>
                </a>
            </li>
            <li>
                <a href="./shoppingCart.html">
                    <span class="fa fa-shopping-cart fa-2x">
                    </span>
                    <span>购物车</span>
                </a>
            </li>
            <li>
                <a href="./me.html">
                    <span class="fa fa-user fa-2x">
                    </span>
                    <span>我</span>
                </a>
            </li>
        </ul>
    </div>
</div>
</body>
</html>
