Ext.define('ManageCatagory.store.CatagoryGrid',{
    extend: 'Ext.data.Store',
	requires: 'ManageCatagory.model.CatagoryGrid',
	model: 'ManageCatagory.model.CatagoryGrid',
	autoSync : true,
    proxy : {
         type: 'ajax',
         //url : 'admin/sys/manageCatagoryQuery',
         api: {
	    	create  : 'admin/portal/manageCatagorySave',
	    	read    : 'admin/portal/manageCatagoryQuery',
		    update  : 'admin/portal/manageCatagorySave',
		    destroy : 'admin/portal/manageCatagoryDelete'
		},
         reader: {
             type : 'json',
             root : 'records',
    		 totalProperty   : 'totalCount'
    		 
         },
         writer: {
             type : 'json'
    		
         }
     }
})
