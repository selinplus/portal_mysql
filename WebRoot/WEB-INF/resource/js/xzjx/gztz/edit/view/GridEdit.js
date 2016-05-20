Ext.define('Gztz.view.GridEdit', {
			extend : 'Ext.window.Window',
			alias : 'widget.gridedit',

			requires : ['Ext.form.Panel'],

			title : '通知发布',
			layout : 'fit',
			maximizable : true,
			minimizable : true,

			initComponent : function() {
				this.items = [{
							xtype : 'form',
							id : 'gztzForm',
							padding : '5 5 0 5',
							border : false,
							style : 'background-color: #fff;',

							items : [{
										xtype : 'textfield',
										name : 'xxbt',
										id : 'xxbtfb',
										width : '100%',
										height : 20,
										allowBlank : false,
										msgTarget : 'side',
										labelAlign : 'right',									
										fieldLabel : '通知标题'
									}, {
										xtype : 'htmleditor',
										name : 'content',
										width : '100%',
										height : 300,
										enableColors : false,
										enableAlignments : false,
										allowBlank : false,
										msgTarget : 'side',
										fieldLabel : ''
									}, {
										xtype : 'form',
										width : '100%',
										height : '10%',
										layout : 'hbox',
										border : false,
										items : [{
													xtype : 'filefield',
													name : 'attachment',
													width : '80%',
													msgTarget : 'side',
													fieldLabel : '附件',
													buttonText : '浏览'
												}, {
													xtype : 'button',
													text : '上传',
													name : 'fileButton'
												}, {
													xtype : 'button',
													text : '重新上传附件',
													name : 'filecxButton',
													id : 'filecxButton',
													hidden : true
												}, {
													xtype : 'label',
													id : 'showAttachment'
												}]

									}, {
										xtype : 'textfield',
										name : 'attachname',
										id : 'attachname',
										width : '100%',
										readOnly : true,
										hidden : true
									}, {
										xtype : 'textfield',
										name : 'attchnameupload',
										id : 'attchnameupload',
										width : '100%',
										readOnly : true,
										hidden : true
									}, {
										xtype : 'textfield',
										name : 'savepath',
										id : 'savepath',
										width : '100%',
										readOnly : true,
										hidden : true
									}, {
										xtype : 'textfield',
										name : 'displayAttachName',
										id : 'displayAttachName',
										width : '100%',
										fieldStyle : {
											borderStyle : 'none'
										},
										border : false,// 取消边框
										readOnly : true
									}]
						}];

				this.buttons = [{
							text : '保存',
							action : 'save'
						},

						{
							text : '取消',
							scope : this,
							handler : this.close
						}];

				this.callParent(arguments);
			}
		});
