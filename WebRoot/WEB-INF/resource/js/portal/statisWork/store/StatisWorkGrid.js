Ext.define('StatisWork.store.StatisWorkGrid',{
    extend: 'Ext.data.Store',
	requires: 'StatisWork.model.StatisWorkGrid',
	model: 'StatisWork.model.StatisWorkGrid',
	autoSync : true,
    proxy : {
         type: 'ajax',
         //url : 'admin/sys/manageResourceQuery',
         api: {
	    	read    : 'admin/portal/statisWork/statisQuery'
		},
         reader: {
             type : 'json',
             root : 'records',
    		 totalProperty   : 'totalCount',
    		 idProperty      : 'statisworkId'
         }
     }
})
