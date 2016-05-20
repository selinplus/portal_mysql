Ext.define('Weather.view.GridEdit', {
    extend: 'Ext.window.Window',
    alias : 'widget.gridedit',

    requires: ['Ext.form.Panel','Weather.view.WeatherCombo'],

    title : '天气预报维护',   
    layout: {
            type: 'fit',
            align: 'stretch'
        },
    autoShow: true,
    width: '100%',
    height:'100%',

    initComponent: function() {
        this.items = [
            {
                xtype: 'form',
                padding: '5 5 0 5',
                border: false,
                id : 'pForm',
                standardSubmit:true,                			   
                url:'admin/portal/article/weatherSave',                
                style: 'background-color: #fff;',
                items: [
                    {
                        xtype: 'textfield',
                        name : 'title',
                        width : '80%',
                        allowBlank : false,
                        msgTarget : 'side',
                        fieldLabel: '标题'
                    },                     
                    {
                        xtype: 'textfield',
                        name : 'source',
                        width : '80%',
                        allowBlank : false,
                        msgTarget : 'side',
                        fieldLabel: '来源'
                    },  
                    {
                        xtype: 'datefield',						       
				        fieldLabel: '预报日期',						      
				        name: 'weatherTime',
				        id:'toDate',
				        format:'Y-m-d',
				        value: new Date()  // defaults to today                        
                    },  
                    {
                        xtype: 'weathercombo',
                        name : 'weatherId',
                        width : '80%',
                        allowBlank : false,
                        msgTarget : 'side',
                        fieldLabel: '天气预报'
                    },  
                    {
                        xtype: 'textareafield',
                        name : 'content',
                        width : '80%',
                        grow      : true,
                        rows:8,
                        cols:40,
                        allowBlank : false,
                        msgTarget : 'side',
                        fieldLabel: '天气预报描述'
                    },                                                 
                    {
                        xtype: 'hiddenfield',
                        id : 'checkFlag',
                        width : '80%',
                        value:'0',
                        allowBlank : false,
                        msgTarget : 'side'
                    }                                               
                ]
            }
        ];

        this.buttons = [
            {
                text: '保存',
                hidden:true,
                id:'sbutton',
                handler: function() {
                this.up('window').down('form').getForm().submit({headers:{accept:'text/html'}})
                
            }
            }
        ];

        this.callParent(arguments);
    }
});
