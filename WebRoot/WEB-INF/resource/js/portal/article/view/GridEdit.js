Ext.define('ManageArticle.view.GridEdit', {
    extend: 'Ext.window.Window',
    alias : 'widget.gridedit',
    id:'publishEdit',

   // requires: ['Ext.form.Panel'],

    title : '文章编辑',	    
	closable : true,
	floating : true,
	width:'100%',
	height:'100%',
	id : 'articlePage',
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
