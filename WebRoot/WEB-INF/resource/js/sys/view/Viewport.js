Ext.define('Sys.view.Viewport', {
    extend: 'Ext.container.Viewport',
    layout: 'border',
    id:'viewport',
    
    requires: [
        'Sys.view.MenuTreeView',
        'Sys.view.ContentCardView'
    ],    
    initComponent: function() {
        this.items = [
	{
		region: 'north',     // position for region
		xtype: 'panel',
		height: 100,
		split: false,         // enable resizing
		margins: '0 0 0 0',
	    dockedItems: [{
	        xtype: 'toolbar',
	        dock: 'top',
	        id:'3434',	       
	        items: [{
		        xtype:'component',
		        width:'80%'
	        },{
		        xtype:'label',	        
		        text:'当前用户：'
	        },{
		        xtype:'label',	        
		        text:userName
	        },{
		        xtype:'component',
		        width:20
	        },{
	        	
	            text: '注销',
	            action:'btLogout',
	            id:'btLogout'
	        },{
	         text: '修改密码' 
	        }]
	    }],
		items:[{
		    xtype:'component',
		    html:'<div><center><h1>龙之韵科技云发布 平台</h1></center></div><div>--服务于微信公众号、企业产品发布、微商发布管理</div>'
	        }]
        },
	{
	region: 'west',     // position for region
        xtype: 'panel',
        split: true,         // enable resizing
        margins: '0 5 5 5',
        layout:'fit',
        collapsible: true,
	items:{
		width: 250,
		xtype: 'menutreeview',
		id: 'west-region',
		margins : '0 0 0 0',		
		layout: {
		    type: 'vbox',
		    align: 'stretch'
		}
	      }
	}, 
	{
	 region: 'center',     // position for region
         xtype: 'panel',
         split: false,         // enable resizing
         margins: '0 0 0 0',
         layout:'fit',
         
	 items:[{
                xtype: 'contentcardview',
                flex : 1,
                layout: {
                    type: 'vbox',
                    align: 'stretch'
                },
                items: {
	            title: 'Default Tab',
	            html: '欢迎使用新政务绩效办公平台'
	           }
            }]
        }
        ]
        this.callParent();
    }
});