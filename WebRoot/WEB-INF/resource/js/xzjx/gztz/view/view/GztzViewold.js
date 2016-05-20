Ext.define('Gztzview.view.GztzView', {
    extend: 'Ext.window.Window',
    alias : 'widget.gztzview',
    id:'gztzview',
    title : '通知内容',	    
	closable : true,
	floating : true,
	width:'100%',
	height:'80%',
	id : 'gztzviewPage',
	layout: {
            type: 'fit',
            align: 'stretch'
        },
     html: '',
    mask : '加载中...'
});
