Ext.define('Gztz.view.GridEdit', {
    extend: 'Ext.window.Window',
    alias : 'widget.gridedit',
    id:'publishEdit',

   // requires: ['Ext.form.Panel'],

    title : '通知发布',	    
	closable : true,
	floating : true,
	width:'100%',
	height:'80%',
	id : 'gztzPage',
	layout: {
            type: 'fit',
            align: 'stretch'
        },
    html: '',
    mask : '加载中...',

    initComponent: function() {    	
        this.buttons = [
                       
            {
                text: '保存',
                action: 'save'
            },
            {
                text: '关闭',
                scope: this,
                action:'close'
                //handler: this.close
            }
        ];

        this.callParent(arguments);
    }
});
