var noticeJson;
var onlines;
Ext.Ajax.request({
	url : baseUrlPath+'/portal/index/noticeQuery',
	success : function(response) {
		noticeJson = Ext.decode(response.responseText);
		var notice=' <ul>';
		var json = noticeJson.records;
		onlines = noticeJson.onlineUsers;
		for (var i = 0; i < json.length; i++) {
			notice+='<li><a target="_blank" href="http://76.68.16.98/YTGS_QYGL/XXGG/nwxx_mx.aspx?lm=tz_gztz&id='+ json[i].id + '">' + json[i].title + ' </a> </li>';
		}
		notice+='</ul>';
		Ext.get('noticeDiv').setHTML(notice);
		//Ext.get('online').setHTML('在线人数：'+onlines+'人');
	}
});