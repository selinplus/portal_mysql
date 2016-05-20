Ext.define('weather.store.WeatherGrid',{
    extend: 'Ext.data.Store',
	requires: 'weather.model.WeatherGrid',
	model: 'weather.model.WeatherGrid',
	autoSync : true,
	pageSize : 20,
    proxy : {
         type: 'ajax',
         api: {
	    	create  : 'admin/portal/article/weatherSave',
	    	read    : 'admin/portal/article/weather',
		    destroy : 'admin/portal/weather/weatherDelete'
		},
         reader: {
             type : 'json',
             root : 'records',
    		 totalProperty   : 'totalCount',
    		 idProperty      : 'id'
         },
         writer: {
             type : 'json',
    		 idProperty      : 'id'
         }
     }
})
