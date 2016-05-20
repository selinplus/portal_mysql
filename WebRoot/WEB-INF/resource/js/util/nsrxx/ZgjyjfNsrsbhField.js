Ext.define ('Ytgs.nsrxx.ZgjyjfNsrsbhField',
              {extend : 'Ext.form.field.Text',
               alias : 'widget.zgjyjfnsrsbhfield',
                name : 'nsrsbh',	                        
                allowBlank : false,
                msgTarget : 'side',
                fieldLabel: '纳税人识别号',
                enableKeyEvents:true,
                activeError :'',
                listeners:{	                        	
                      keypress:function(me, e, eOpts){
							if(e.getKey()==13){
								me.up('form').el.mask();
								this.getNsrmcQuery(me, e, eOpts);
							}
						}
					                         
                 } ,
	              getNsrmcQuery:function(thisObj, eventObj, eOpts){
	              	            var me =this;	              	           
								Ext.Ajax.request({
								    url: 'admin/zgjx/sdsgl/tz/zgjyjf/getNsrmcZgjyjfMx',
								    params: {
								        nsrsbh:thisObj.getValue(),
								        kcnd:me.up('form').down('numberfield[name=kcnd]').value
								    },
								    callback:function(options,success,response ){
								    	var text = Ext.decode(response.responseText);
								    	if(text.success){
									        var text = Ext.decode(response.responseText);
									        if(me.up('form')){
									         	if(me.up('form').down('textfield[name=nsrmc]')){
									            	me.up('form').down('textfield[name=nsrmc]').setValue(text.nsrmc);
									       		}
									       		if(me.up('form').down('textfield[name=zgswskfjDm]')){
									       	 		me.up('form').down('textfield[name=zgswskfjDm]').setValue(text.zgswskfjDm);
									       		}
									       		if(me.up('form').down('textfield[name=zgswskfjMc]')){
									       	 		me.up('form').down('textfield[name=zgswskfjMc]').setValue(text.zgswskfjMc);
									       		}
											    if(me.up('form').down('textfield[name=ssglyMc]')){
									       	  		me.up('form').down('textfield[name=ssglyMc]').setValue(text.ssglyMc);
									       		}
												if (text.jtswkcye2008nqfsq!=null){
													me.up('form').down('numberfield[name=jtswkcye2008nqfsq]').setValue(text.jtswkcye2008nqfsq);
													me.up('form').down('numberfield[name=sndljye]').setValue(text.sndljye);

												}else{
													me.up('form').down('numberfield[name=jtswkcye2008nqfsq]').setValue('0');
													me.up('form').down('numberfield[name=sndljye]').setValue('0');
													me.up('form').down('numberfield[name=jtswkcye2008nqfsq]').setReadOnly(false);
													me.up('form').down('numberfield[name=sndljye]').setReadOnly(false);				
												}
									        	
									        }
									    }else{
								    		 me.markInvalid('没有该纳税人信息');
								    		 me.focus();
								    		
								    	}
								    	me.up('form').el.unmask();
								    }
						         });
		              }
                  
                }	 	
     )
