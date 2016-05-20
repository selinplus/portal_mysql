Ext.define('ManageCatagory.store.ModuleTree',{
    extend: 'Ext.data.TreeStore',
	requires: 'ManageCatagory.model.ModuleTree',
	model: 'ManageCatagory.model.ModuleTree',
    proxy: {
         type: 'ajax',
         url: 'admin/portal/manageArticle/catagoryTree',//'portal/moduleTree',
         extraParams :{id:'1'}, 
         reader: {
             type: 'json'
         }
     },
    root: {
        text: '栏目',
        id:  '1',
        expanded: false
    }
})