Ext.define('StatisWork.view.Viewport', {
    extend: 'Ext.container.Viewport',
    layout: {
               type: 'fit',
               align: 'stretch'
             },
    border : 0,
    requires: [
        'StatisWork.view.StatisWorkGrid'
    ],    
    initComponent: function() {
        this.items = { 
            layout: {
                type: 'hbox',
                align: 'stretch'
            },
            items: 		
			 [{
            	xtype: 'panel',
            	flex : 1,
            	id : 'ipanel',
            	dockedItems: [{
			        xtype: 'toolbar',
			        dock: 'top',
			        id : 'itbar',
	                items : [{
						        xtype: 'datefield',						     
						        fieldLabel: '开始日期',						       
						        name: 'fromDate',
						        id:'fromDate',
						        format:'Y-m-d',
						        value: new Date()  // defaults to today
						    }
						     ,{
						        xtype: 'datefield',						       
						        fieldLabel: '截止日期',						      
						        name: 'toDate',
						        id:'toDate',
						         format:'Y-m-d',
						        value: new Date()  // defaults to today
						    },
	                	
	                		{	xtype : 'button',
								text : '查询',
								action: 'query'
							}]
		    	  }],
		    	layout: {
	                type: 'fit',
	                align: 'stretch'
                },
            	items:[{
                xtype: 'statisworkgrid',                
                id:'statiswork-grid'
            	}]
             }]
        };
        
        this.callParent();
    }
});