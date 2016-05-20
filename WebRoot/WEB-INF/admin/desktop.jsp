<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>烟台国税办公平台</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="res/js/sys/desktop/desktop/css/desktop.css" />
<link href="res/wallpaper/css/base.css" rel="stylesheet">
<!--[if lt IE 7]>
<link href="res/wallpaper/css/ie6base.css" rel="stylesheet">
<![endif]-->
<!--[if gt IE 7]>
<link href="res/wallpaper/css/base.css" rel="stylesheet">
<![endif]-->
<link href="res/wallpaper/css/index.css" rel="stylesheet">
<link type="text/css"
	href="res/wallpaper/css/datePicker.css" rel="stylesheet" />
<!-- GC -->

<!-- <x-compile> -->
<!-- <x-bootstrap>-->
<script type="text/javascript">
var userName='${userName}';
var userAccount='${userAccount}';
var deptName='${deptName}';
var basePath='<%=basePath%>';
var ols='${onlineUsers}';
var sysDate='${sysDate}';
</script>
<script type="text/javascript"
	src="res/js/sys/desktop/shared/include-ext.js"></script>

<script type="text/javascript"
	src="res/ext-4.2.1/locale/ext-lang-zh_CN.js"></script>


<script src="res/wallpaper/js/jquery-1.10.2.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="res/wallpaper/js/jquery.datePicker-min.js"></script>


<script type="text/javascript"
	src="res/wallpaper/js/html5.js"></script>


<!-- </x-bootstrap> -->
<script type="text/javascript" defer="defer">
	Ext.Loader.setPath({
            'Ext.ux.desktop': 'res/js/sys/desktop/desktop/js',          
    		'Sys.store.SysTree':'res/js/sys/store/SysTree.js',
    		'Sys.model.TreeNode':'res/js/sys/model/TreeNode.js',
             MyDesktop: 'res/js/sys/desktop/desktop'
        });

     Ext.require('MyDesktop.App');
     Ext.require('Sys.store.SysTree');
     Ext.require('Sys.model.TreeNode');
    
     var myDesktopApp;
     var dateInterval=setInterval(function(icnt){ 
   	   //if(window.screen.width<1050){
   		 //$("#leftbottom").css("display","none");
   	   //}
 	   if(!$(".datepicker").html()){
    	$(".datepicker").datePicker({
		inline : true,
		selectMultiple : false
	    });
 	   }else{
 		   clearInterval(dateInterval);
 	   }
 	   },150);
     Ext.onReady(function () {
    	 
    	 
     	 treeStore = new Sys.store.SysTree();
     	 var func=function(){
     	 myDesktopApp = new MyDesktop.App();
     	
     	 };
     	 var me=this;
     	 treeStore.on('load', func,me, {
              buffer: 1
          });        	
         
     });
     
     function force_logout(){
     	Ext.Ajax.request({
	url : basePath + 'j_spring_security_logout'
});
     }
     //邮件刷新
     function refreshMail(){   
    	 Ext.get("mail-ul").el.mask('加载中...');
    	 Ext.Ajax.request({
             url : 'admin/mail/unreadQuery',
             success : function(response, opts) {
            	 var html="";
                     var obj = Ext.decode(response.responseText);
                     for( i=0;i<obj.length;i++){
                    	 html+='<li><a title="'+obj[i].subject+'" href="admin/mail/mailInfo?messageNumber='+obj[i].messageNumber+'&active='+obj[i].active+'" target="_blank">'+obj[i].subject+'</a><span class="nameFrom">'+obj[i].nameFrom+'</span><span class="time">'+obj[i].sentDate+'</span></li>';
                     }
                     $(".email-item").html(html);
                     Ext.get("mail-ul").el.unmask();
             }
     });
     }
     //通知刷新
     function refreshNotice(){ 
    	 Ext.get("notice-ul").el.mask('加载中...');
    	 Ext.Ajax.request({
             url : 'admin/xzjx/gztz/gztzquery',
             success : function(response, opts) {
            	 var html="";
                     var obj = Ext.decode(response.responseText).records;
                     for( i=0;i<Math.min(obj.length,8);i++){
                    	 var temp=(i%2===0)?"even":"liodd";
                    	 if(obj[i].fbsjquery==sysDate){
                    	 	html+=  '<li class='+temp+'><span class="xxbt"><a style="color:red;" title="'+obj[i].xxbt+'" href="javascript:noticePage(\''+obj[i].id+'\')">'+obj[i].xxbt+'</a></span><span class="fbrDeptMc">'+obj[i].fbrDeptMc+'</span></li> '  ;
                    	 }else{
                     	 	html+=  '<li class='+temp+'><span class="xxbt"><a title="'+obj[i].xxbt+'" href="javascript:noticePage(\''+obj[i].id+'\')">'+obj[i].xxbt+'</a></span><span class="fbrDeptMc">'+obj[i].fbrDeptMc+'</span></li> '  ;
                    	 }
                     }
                     $(".notice-item").html(html);
                     Ext.get("notice-ul").el.unmask();
             }
     });
     }
     //待办事项刷新
     function refreshTodo(){   
    	 Ext.get("todo-ul").el.mask('加载中...');
    	 Ext.Ajax.request({
             url : 'admin/workflow/getTodoList',
             params:{page:1,limit:5,userAccount:userAccount},
             success : function(response, opts) {
            	 var html="";
                     var obj = Ext.decode(response.responseText).records;
                     for( i=0;i<obj.length;i++){
                    	 html+=  '<li><span class="todo"><a title="'+obj[i].title+'" href="javascript:todoPage(\''+obj[i].uri+'\',\''+obj[i].baseId+'\',\''+obj[i].registerId+'\',\''+obj[i].currentNode+'\',\''+obj[i].currentNodeMc+'\',\''+obj[i].senderNodeDm+'\',\''+obj[i].senderNodeMc+'\',\''+obj[i].forwardOrBack+'\')">'+obj[i].title+'</a></span></li>  ' ;
                     }
                     $(".todo-item").html(html);
                     Ext.get("todo-ul").el.unmask();
             }
     });
     }
    //通知浏览 
     function noticePage(id){
 		var url=basePath+"admin/desktop/noticePage?id="+id;
 		window.open(url,"_blank");
 		var imenu = new MyDesktop.BogusModule();
		imenu.app = appme;
		imenu.wndId = child.get('id');
		imenu.text = child.get('text');
		imenu.id = 'bogus' + child.get('id');
		imenu.customHtml = '<iframe  id="frame'
				+ child.get("id")
				+ '" src="'
				+ child.get("href")
				+ '" width=100% height="100%" frameBorder="0"></iframe>';
 		
 	} 
    //待办事宜浏览 
     function todoPage(uri,baseId,registerId,currentNode,currentNodeMc,senderNodeDm,senderNodeMc,forwardOrBack){
    	 var url=basePath+uri+"?baseId="+baseId+"&registerId="+registerId+
    			 "&currentNode="+currentNode+"&currentNodeMc="+currentNodeMc+
    			 "&senderNodeDm="+senderNodeDm+"&senderNodeMc="+senderNodeMc+
    			 "&forwardOrBack="+forwardOrBack;
 		window.open(encodeURI(url),"_blank");
 	} 
    //通知搜索
     function noticeSearch(){
    	var searchContent=document.getElementById('searchContent').value;
 		//var url=basePath+"admin/xzjx/gztz/gztzquery?xxbt="+searchContent;
 		var url=basePath+"admin/desktop/noticeQuery?searchContent="+searchContent;
 		window.open(url,"_blank");
 	} 
    //待办事宜more
     function todoMore(){
    	
 		var url=basePath+"admin/desktop/todoMore";
 		window.open(url,"_blank");
 	} 
    //通知more
     function noticeMore(){
    	
 		var url=basePath+"admin/desktop/noticeMore";
 		window.open(url,"_blank");
 	} 
</script>
<!-- </x-compile> -->

</head>

<body onunload="force_logout();">

</body>
<script type="text/javascript" defer="defer">
	
</script>
</html>
