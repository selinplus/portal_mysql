
Ext.define('ManageCard.view.ArticleGrid', {
			extend : 'Ext.grid.Panel',
			alias : 'widget.articlegrid',
			store:'ArticleGrid',
			border : 0,
			dockedItems: [{
		        xtype: 'pagingtoolbar',
		        store: 'ArticleGrid',  
		        dock: 'bottom',
		        displayInfo: true
		    }],
			columns: 
			{ defaults:{resizable:false},items:
			[
		        { text: '标题',  dataIndex: 'title',flex : 3,
		          renderer: function(value,metaData,record,rowIndex,colIndex,store,view) {
	        	  var thisUrl = store.getAt(rowIndex).get('htmlUrl');
	        	  var bzZd = store.getAt(rowIndex).get('bzZd');
	        	  if(bzZd=='on'){
	        	  	return Ext.String.format('顶<a href="{0}" target="_blank">{1}</a>',thisUrl, value);
	        	  }else{
	                return Ext.String.format('<a href="{0}" target="_blank">{1}</a>',thisUrl, value);
	        	  }
	        	} 
		        },
		        { text: '来源', dataIndex: 'source',flex: 1},
		        { text: '发布时间', dataIndex: 'createTime' , flex: 1 }
		   	]
			}
		})