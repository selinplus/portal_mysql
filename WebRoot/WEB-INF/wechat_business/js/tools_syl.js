/**
 * Created by Administrator on 15-11-25.
 */
function creatHtmlElementByJquery(tag,classNames,content){
    var obj =null;
    //元素
    if(tag=='img'){
        //单标签
        obj = $('<'+tag+'>');
    }else{
        //双标签
        obj = $('<'+tag+'></'+tag+'>');
    }
    //classs属性
    if(classNames!=null&&classNames.length>0){
        var arr = classNames.split(',');
        for(var i = 0 ; i < arr.length;i++){
            obj.addClass(arr[i]);
        }
    }
    //文本内容
    if(content!=null){
        obj.text(content);
    }
    return obj;
}