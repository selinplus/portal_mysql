Ext.define('ManageCard.view.GridEdit', {
    extend: 'Ext.form.Panel',
    alias : 'widget.gridedit',

   // requires: ['Ext.form.Panel'],

    title : '电子名片编辑',	    
	closable : true,
	floating : true,
	width:650,
	height:340,
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
                handler: this.close
            }
        ];

        this.callParent(arguments);
    }
});
