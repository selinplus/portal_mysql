Ext.define('ManageArticle.view.Viewport', {
    extend: 'Ext.container.Viewport',
    layout: {
               type: 'fit',
               align: 'stretch'
             },
    border : 0,
    requires: [
        'ManageArticle.view.CatagoryTree',
        'ManageArticle.view.ArticleGrid',
        'ManageArticle.view.GridEdit'

    ],    
    initComponent: function() {
        this.items = { 
            layout: {
                type: 'hbox',
                align: 'stretch'
            },
            items: 		
			 [{
                width: 250,
                xtype: 'catagorytree',
                id: 'catagory-tree'
            }, {
            	xtype : 'splitter',
            	border: 1,
            	width : 5,
				collapsible : true,
				collapseTarget : 'prev'
            },
            	{
            	xtype: 'panel',
            	flex : 1,
            	id : 'ipanel',
            	dockedItems: [{
			        xtype: 'toolbar',
			        dock: 'top',
			        id : 'itbar',
	                items : [{	xtype : 'button',
								text : '增加',
								action: 'add'
							}, {
								xtype : 'button',
								text : '修改',
								id:'modify',
								action: 'modify'
							}, {
								xtype : 'button',
								text : '删除',
								action: 'delete'
							}]
		    	  }],
		    	layout: {
	                type: 'fit',
	                align: 'stretch'
                },
            	items:[{
                xtype: 'articlegrid',                
                id:'article-grid'
            	}]
             }]
        };
        
        this.callParent();
    }
});