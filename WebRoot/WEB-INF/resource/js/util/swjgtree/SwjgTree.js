
var vModel= Ext.define('Swjg',{
	         extend: 'Ext.data.Model',
			 fields: [
			    { name: 'id', type: 'string' },
			    { name: 'text', type: 'string' }
			]
		});

var vStore=Ext.create('Ext.data.TreeStore',{		  	
			model: vModel,
		    proxy: {
		         type: 'ajax',
		         url: 'admin/zgjx/sdsgl/hsqj/deparmentQuery',
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
});
Ext.define('Ytgs.swjgtree.SwjgTree', {
			extend : 'Ext.tree.Panel',
			alias : 'widget.departtree',
			store:vStore,
			border : 0, 
			listeners:{
						  beforeitemexpand : function(node, optd) {		
								var mProxy = vStore.getProxy();
								mProxy.setExtraParam("id", node.get("id"));	
						   },
						beforeitemclick : function(depttree, record, item, index, e, eOpts) {
							e.stopEvent();
						},
						itemdblclick:function(depttree, record, item, index, e, eOpts) {
						    depttree.toggleOnDblClick=false;
						},
						beforeitemdblclick: function( depttree, record, item, index, e, eOpts ){
							var selNode=this.getSelectionModel().getSelection()[0];			
							if (!selNode){
								Ext.Msg.alert('请选择部门!');
							return false;
							}else{			   
							   var popWin =Ext.ComponentQuery.query('window[name=popWindow]')[0];
							   Ext.ComponentQuery.query('swjgfield>textfield[name=deptName]')[0].setValue(selNode.get("text"));
							    Ext.ComponentQuery.query('swjgfield>textfield[name=deptId]')[0].setValue(selNode.get("id"));
							   popWin.close();
							}	
						}
			}			
})