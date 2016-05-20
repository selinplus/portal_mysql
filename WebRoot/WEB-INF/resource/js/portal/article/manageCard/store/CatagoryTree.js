Ext.define('ManageCard.store.CatagoryTree',{
    extend: 'Ext.data.TreeStore',
	requires: 'ManageCard.model.CatagoryTree',
	model: 'ManageCard.model.CatagoryTree',
    proxy: {
         type: 'ajax',
         url: 'admin/portal/manageArticle/catagoryTree',
         extraParams :{id:'1'}, 
         reader: {
             type: 'json',
             root: 'menu'
         }
     },
    root: {
        text: '栏目',
        id:  '1',
        expanded: false
    }
})