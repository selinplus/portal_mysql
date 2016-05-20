Ext.define('StatisWork.controller.StatisWorkController', {
	extend : 'Ext.app.Controller',

	refs : [{
				ref : 'statisworkGrid',
				selector : 'statisworkgrid'
			}],
	stores : [ 'StatisWorkGrid'],
	init : function() {
		this.control({				
					'viewport panel toolbar button[action=query]' : {
						click : this.query
					}
				})
	},
	query : function() {
		var fromDate=Ext.getCmp('fromDate').value;
		var toDate=Ext.getCmp(('toDate')).value;
	 	fromDate=Ext.Date.format(fromDate,'Y-m-d');
	 	toDate=Ext.Date.format(toDate,'Y-m-d');
		
	 	this.getStatisWorkGridStore().getProxy().setExtraParam('sssqQ',fromDate);
	    this.getStatisWorkGridStore().getProxy().setExtraParam('sssqZ',toDate);
	    
		this.getStatisWorkGridStore().load();
		/*Ext.Ajax.request({
					    url: 'admin/portal/statisWork/statisQuery',
					    params: {
					        sssqQ:fromDate,
					        sssqZ:toDate
					    },
					    success: function(response){
					        var text = response.responseText;
					        alert(text);
					        // process server response here
					    }
});*/

	}
});
