Ext.define('Weather.controller.WeatherController', {
	extend : 'Ext.app.Controller',
	refs : [ {
				ref : 'gridEdit',
				selector : 'gridedit'
			}],

	init : function() {
		this.control({	
			     'gridedit form' : {
						beforeaction : this.onBeforeaction
					}			
					
				}
	)
	},
	onBeforeaction:function(form){
	if(!form.isValid()){
	document.getElementById("checkFlag").value="1";
	return false;
	}
	
	}	
});
