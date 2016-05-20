Ext.define('Weather.view.Viewport', {
    extend: 'Ext.container.Viewport',
    layout: {
               type: 'fit',
               align: 'stretch'
             },
    border : 0,
    requires: [        
        'Weather.view.GridEdit'

    ],    
    initComponent: function() {
        this.items = { 
            
            items: 		
			 [ {
            	xtype : 'gridedit'           	
				
            }]
        };
        
        this.callParent();
    }
});