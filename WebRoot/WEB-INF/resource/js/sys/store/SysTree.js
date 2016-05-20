
Ext.define('Sys.store.SysTree', {
	extend: 'Ext.data.TreeStore',
	requires: 'Sys.model.TreeNode',
	model: 'Sys.model.TreeNode',
    proxy: {
         type: 'ajax',
         async:false,
         url: 'admin/desktop/startTree',
         extraParams :{id:1}, 
         reader: {
             type: 'json'
         }
     },
    root: {
        text: '目录',
        id:  1,
        expanded: true
    },
    autoLoad:true
});