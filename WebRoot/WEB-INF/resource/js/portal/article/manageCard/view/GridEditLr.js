Ext.define('ManageCard.view.GridEdit', {
    extend: 'Ext.window.Window',
    alias : 'widget.gridedit',

    requires: ['Ext.form.Panel'],

    title : '电子名片维护',
    layout: 'fit',
    autoShow: true,
    width: 380,

    initComponent: function() {
        this.items = [
            {
                xtype: 'form',
                padding: '5 5 0 5',
                border: false,
                id : 'pForm',
                style: 'background-color: #fff;',

                items: [
                    {
                        xtype: 'textfield',
                        name : 'name',
                        width : '80%',
                        allowBlank : false,
                        msgTarget : 'side',
                        fieldLabel: '姓名'
                    },
                    {
                        xtype: 'textfield',
                        name : 'catagoryUrl',
                        id : 'catagoryUrl',
                        width : '80%',
                        msgTarget : 'side',
                        hidden : true
                    },  
                    {
                        xtype: 'textfield',
                        name : 'articleModal',
                        id : 'articleModal',
                        width : '80%',
                        hidden : true
                    },  
                    {
                        xtype: 'textfield',
                        name : 'pageModal',
                        id : 'pageModal',
                        width : '80%',
                        hidden : true
                    },  
                    {
                        xtype: 'textfield',
                        name : 'catagoryPic',
                        id : 'catagoryPic',
                        width : '80%',
                        hidden : true
                    },  
                    {
                        xtype: 'textfield',
                        name : 'szdw',
                        width : '80%',
                        allowBlank : false,
                        msgTarget : 'side',
                        fieldLabel: '所在单位'
                    },  
                    {
                        xtype: 'textfield',
                        name : 'xrzw',
                        width : '80%',
                        allowBlank : false,
                        msgTarget : 'side',
                        fieldLabel: '现任职务'
                    },  
                    {
                        xtype: 'textfield',
                        name : 'zzfg',
                        width : '80%',
                        allowBlank : false,
                        msgTarget : 'side',
                        fieldLabel: '职责分工'
                    },  
                    {
                        xtype: 'textfield',
                        name : 'bgdh',
                        width : '80%',
                        allowBlank : false,
                        msgTarget : 'side',
                        fieldLabel: '办公电话'
                    },
                    {
                        xtype: 'form',
                        name : 'catagoryPicForm',
                        width : 300,
                        items:[{xtype: 'filefield',
                        name : 'catagoryPicFile',
                        width : '80%',
                        msgTarget : 'side',
                        fieldLabel: '照片',
                        buttonText: '浏览'}],
                        buttons: [{
					        text: '上传',
					        name: 'catagoryPicButton'
					    }]
                    }, 
                    {
                        xtype: 'form',
                        name : 'popupDeptForm',
                        width : 300,
                        items:[{xtype: 'textfield',
                        name : 'deptName',
                        width : '80%',
                        allowBlank : false,
                        msgTarget : 'side',
                        readOnly:true,
                        fieldLabel: '所属部门'},
                        {xtype: 'textfield',
                        name : 'deptId',
                        width : '80%',
                        allowBlank : false,
                        hidden:true
                        }
                        ],
                        buttons: [{
					        text: '选择部门',
					        name: 'deptButton'
					    }]
                    }                                                        
                ]
            }
        ];

        this.buttons = [
            {
                text: '保存',
                action: 'save'
            },
            {
                text: '取消',
                scope: this,
                handler: this.close
            }
        ];

        this.callParent(arguments);
    }
});
