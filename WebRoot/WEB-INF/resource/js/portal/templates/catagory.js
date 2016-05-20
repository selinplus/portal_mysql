Ext.onReady(function() {

	var iStore=Ext.create('Ext.data.Store',{
	  	fields: [
	 		    { name : 'id'	  },
	 	        { name : 'title'   },
	 	        { name : 'createTime'   },
	 	        { name : 'bzZd'   },
	 	        { name : 'htmlUrl'   } 
	 		 ],
	  	autoLoad : true,
	  	pageSize:vPageSize,
	    proxy : {
	        type: 'ajax',
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
	var iGrid=Ext.create('Ext.grid.Panel', {
		store:iStore,
		border : 0,
		renderTo: Ext.get(vCatagoryId),
		//hideHeaders:true,
		rowLines: false,
		dockedItems: [{
	        xtype: 'pagingtoolbar',
	        store: iStore,  
	        dock: 'bottom',
	        displayInfo: true
	    }],
		columns: 
		{ defaults:{resizable:false},items:
		[
	        { text: '标题', dataIndex: 'title',flex : 5,
	          renderer: function(value,metaData,record,rowIndex,colIndex,store,view) {
	        	  var thisUrl = store.getAt(rowIndex).get('htmlUrl');
	        	  var bzZd = store.getAt(rowIndex).get('bzZd');
	        	  if(bzZd=='on'){
	        	  	return Ext.String.format('<a href="{0}" target="_blank" class="gridInner">{1}</a>',thisUrl, value);
	        	  }else{
	                return Ext.String.format('<a href="{0}" target="_blank" class="gridInner">{1}</a>',thisUrl, value);
	        	  }
	        	} 
	        },
	        { text: '发布时间', dataIndex: 'createTime',flex: 2}		        
	   	]
		}
	});			
});