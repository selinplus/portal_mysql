Ext.define ('Ytgs.nsrxx.SsyhNsrsbhField',
              {extend : 'Ext.form.field.Text',
               alias : 'widget.ssyhnsrsbhfield',
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
								    url: 'admin/zgjx/sdsgl/tz/ssyh/getNsrmcSsyhMx',
								    params: {
								        nsrsbh:thisObj.getValue(),
								        nd:me.up('form').down('numberfield[name=nd]').value
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
												me.up('form').down('numberfield[name=qwndjzkdme1]').setValue(text.qwndjzkdme1);
												me.up('form').down('numberfield[name=qwndjzkdme2]').setValue(text.qwndjzkdme2);
												me.up('form').down('numberfield[name=qwndjzkdme3]').setValue(text.qwndjzkdme3);
												me.up('form').down('numberfield[name=qwndjzkdme4]').setValue(text.qwndjzkdme4);
												me.up('form').down('numberfield[name=qwndjzkdme5]').setValue(text.qwndjzkdme5);
												me.up('form').down('numberfield[name=qwndjzkdmeHj]').setValue(text.qwndjzkdmeHj);
												me.up('form').down('numberfield[name=snjzkdktze]').setValue(text.snjzkdktze);
												me.up('form').down('numberfield[name=oldQwndjzkdme1]').setValue(text.qwndjzkdme1);
												me.up('form').down('numberfield[name=oldQwndjzkdme2]').setValue(text.qwndjzkdme2);
												me.up('form').down('numberfield[name=oldQwndjzkdme3]').setValue(text.qwndjzkdme3);
												me.up('form').down('numberfield[name=oldQwndjzkdme4]').setValue(text.qwndjzkdme4);
												me.up('form').down('numberfield[name=oldQwndjzkdme5]').setValue(text.qwndjzkdme5);
												me.up('form').down('numberfield[name=oldQwndjzkdmeHj]').setValue(text.qwndjzkdmeHj);
												me.up('form').down('numberfield[name=oldSnjzkdktze]').setValue(text.snjzkdktze);
												
												me.up('form').down('textfield[name=jmqq]').setValue(me.up('form').down('numberfield[name=nd]').value+"-01-01");
												me.up('form').down('textfield[name=jmqz]').setValue(me.up('form').down('numberfield[name=nd]').value+"-12-31");
/*
													me.up('form').down('numberfield[name=qwndjzkdme1]').setValue('0');
													me.up('form').down('numberfield[name=qwndjzkdme2]').setValue('0');
													me.up('form').down('numberfield[name=qwndjzkdme3]').setValue('0');
													me.up('form').down('numberfield[name=qwndjzkdme4]').setValue('0');
													me.up('form').down('numberfield[name=qwndjzkdme5]').setValue('0');
													me.up('form').down('numberfield[name=snjzkdktze]').setValue('0');
													me.up('form').down('numberfield[name=qwndjzkdme1]').setReadOnly(false);
													me.up('form').down('numberfield[name=qwndjzkdme2]').setReadOnly(false);
													me.up('form').down('numberfield[name=qwndjzkdme3]').setReadOnly(false);
													me.up('form').down('numberfield[name=qwndjzkdme4]').setReadOnly(false);
													me.up('form').down('numberfield[name=qwndjzkdme5]').setReadOnly(false);
													me.up('form').down('numberfield[name=snjzkdktze]').setReadOnly(false);
*/
									        	
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
