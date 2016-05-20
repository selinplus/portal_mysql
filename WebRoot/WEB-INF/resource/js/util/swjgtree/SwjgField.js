Ext.define ('Ytgs.swjgtree.SwjgField',
              {extend : 'Ext.form.Panel',
               alias : 'widget.swjgfield',
               requires: ['Ytgs.swjgtree.SwjgTree'],
               layout:'hbox',
               items:[{
		                    xtype: 'textfield',                        
		                    name : 'deptName',
		                    width:400,
		                    labelWidth:65,
		                    labelAlign : 'right',
		                    allowBlank : false,
		                    msgTarget : 'side',
		                    readOnly:true,
		                    fieldLabel: '税务机关'
	                    },{
		                    xtype: 'textfield',
		                    name : 'deptId',
		                    allowBlank : false,
		                    hidden:true
	                    },                	 
		        	 	{  xtype:'button',
		        	 	   text:'...',
		        	 	   name:'deptButton',
		        	 	   handler:function(){
		            	 	   	var edit = Ext.create('Ext.window.Window',{		            	 	   	
								height: 400,
								width: 400,
								name : 'popWindow',
								layout: 'fit',
								items:{ xtype: 'departtree'}});
							    edit.show();
		        	 	    }
        	 	        }
                  ],
                  getSwjgMc:function(){ 
                  	var me=this                  
                  	return  me.items.items[0].getValue();
                  },
                  getSwjgDm:function(){
                  	var me=this                  
                  	return  me.items.items[1].getValue();
                  }
                  
                }	 	
     )
