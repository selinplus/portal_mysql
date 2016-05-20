Ext.define('ManageCatagory.store.DepartTree',{
    extend: 'Ext.data.TreeStore',
	requires: 'ManageCatagory.model.DepartTree',
	model: 'ManageCatagory.model.DepartTree',
    proxy: {
         type: 'ajax',
         url: 'admin/sys/UserDeparmentQuery',
         extraParams :{id:'13706000000'}, 
         reader: {
             type: 'json',
             root: 'records'
         }
     },
    root: {
        text: '龙之韵科技平台',
        id:  '13706000000',
        expanded: false
    }
})