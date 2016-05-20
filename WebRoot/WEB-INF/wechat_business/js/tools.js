/**
 * Created by Administrator on 15-10-29.
 */
function cus_popover(id,title,content,placement,className){
    var obj = $('#'+id);
    cus_popover_2(obj,title,content,placement,className);
}
//显示一个提示框 --并自动消失
function cus_popover_2(obj,title,content,placement,className){
    obj. popover ({
        title: title,
        content:content,
        placement: placement,
        template:'<div class="popover">' +
            '<div class=""></div>' +
            '<strong><div class="popover-title text-center"></div></strong>' +
            '<div class="popover-content small '+className+'" style="text-indent:0px;"></div>' +
            '</div>'
    });
    //显示登陆错误的提示框
    obj. popover('show');
    //关闭登陆错误的提示框
    setTimeout(function(){
        obj. popover('destroy');
    },2000);
}
//日期格式化
function dateformat(d,pattern){
    var str = '';
    if(pattern=='yyyy-MM-dd HH:mm:ss'){
        str =  d.getFullYear()+'-';
        if( d.getMonth()<9){
            str =  str + '0';
        }
        str = str +  (d.getMonth()+1)+'-';
        if( d.getDate()<10){
            str =  str + '0';
        }
        str = str +  d.getDate()+' ';
        if( d.getHours()<10){
            str =  str + '0';
        }
        str = str +  d.getHours()+':';
        if( d.getMinutes()<10){
            str =  str + '0';
        }
        str = str +  d.getMinutes()+':';
        if( d.getSeconds()<10){
            str =  str + '0';
        }
        str = str +  d.getSeconds();
    }
    return str;
}

//根据id将元素总是位于屏幕底部
//注意：该id云元素应该具有样式：style="position:fixed;height:XXpx"
function setEleScreenBottom(id){
    var obj = $('#'+id);
    obj.css('top',$(window).height()-obj.height());
    obj.css('left',($(window).width()-obj.width())/2);
}

//根据id将元素置于窗口的正中央,建议元素css样式包含了绝对定位和固定大小
function setEleHVCenter(id){
    var obj = $('#'+id);
    obj.css('top',($(window).height()-obj.height())/2);
    obj.css('left',($(window).width()-obj.width())/2);
}

//根据id和是否可见isVisible将元素设为可见或者隐藏
function setEleVisible(isVisible,id){
    var obj = $('#'+id);
    if(isVisible){
        if(obj.hasClass('hidden')){
            obj.toggleClass('hidden');
        }
    }else{
        if(!obj.hasClass('hidden')){
            obj.toggleClass('hidden');
        }
    }
}