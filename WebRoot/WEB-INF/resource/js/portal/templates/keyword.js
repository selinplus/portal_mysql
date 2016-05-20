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
	  	proxy : {
	        type: 'ajax',
	        url : vStoreUrl,
			extraParams: {'keyWord' : vKeyWord},
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
		renderTo: Ext.get('artList'), //将结果列表放到页面定义的DIV artList。
		hideHeaders:true,
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
	        	  return Ext.String.format('<a href="{0}" target="_blank" class="gridInner">{1}</a>',thisUrl, value);
	        	} 
	        },
	        { text: '发布时间', dataIndex: 'createTime',flex: 2}		        
	   	]
		}
	});			
});