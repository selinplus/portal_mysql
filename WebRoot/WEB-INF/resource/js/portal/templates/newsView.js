Ext.onReady(function() {

	var iStore=Ext.create('Ext.data.Store',{
	  	fields: [
	 		    { name : 'id'},
	 	        { name : 'title'},
	 	        { name : 'createTime'},
	 	        { name : 'bzZd'},
	 	        { name : 'introduce'},
	 	        { name : 'topicPic'},
	 	        { name : 'htmlUrl'} 
	 		 ],
	  	autoLoad : true,
	  	pageSize:vPageSize,
	    proxy : {
	        type: 'ajax',
	        async:false,
	        url : vStoreUrl,
			extraParams: {catagoryId : vCatagoryId,totalCount:vTotalCount},
	        reader: {
	            type : 'json',
	            root : 'records',
	   		 totalProperty   : 'totalCount',
	   		 idProperty      : 'id'
	        }
	    }
	    
	});
	var iPanel=Ext.create('Ext.panel.Panel', {
		store:iStore,
		border : 0,
		renderTo: Ext.get('pageInfo'),
		hideHeaders:true,
		dockedItems: [{
	        xtype: 'pagingtoolbar',
	        store: iStore,  
	        dock: 'bottom',
	        displayInfo: true
	    }],
		html:''
	});
	var me=this;
	iStore.on('load', genHtml,me, {
                 buffer: 1
             });        
	function genHtml(){
		var htmlStr="<ul>";
		for(var k=0;k<=iStore.pageSize;k++){
			var record=iStore.getAt(k);
			if(record){
				htmlStr+="<li>";
	            htmlStr+="    	<div class=\"module\">";
	            htmlStr+="      <a href=\""+record.get('htmlUrl')+"\" target=_blank><img src=\""+baseUrlPath+record.get('topicPic')+"\" width=\"216\" height=\"140\" /></a>";
	            htmlStr+="      	<a href=\""+record.get('htmlUrl')+"\" class=\"left\" >"+record.get('title')+"</a>";                        
	            htmlStr+="          </div>"  ;
	            htmlStr+="         </li>";
			}
		}
		htmlStr+="</ul>";
		document.getElementById('newsViewList').innerHTML=htmlStr;
	}
	
});