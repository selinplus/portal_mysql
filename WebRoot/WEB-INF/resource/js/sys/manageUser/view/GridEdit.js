Ext.define('ManageUser.view.GridEdit', {
    extend: 'Ext.window.Window',
    alias : 'widget.gridedit',

    requires: ['Ext.form.Panel'],

    title : '用户维护',
    layout: 'fit',
    autoShow: true,
    width: 380,

    initComponent: function() {
        this.items = [
            {
                xtype: 'form',
                padding: '5 5 0 5',
                border: false,
                style: 'background-color: #fff;',

                items: [
                	{
                        xtype: 'textfield',
                        name : 'userId',
                        width : '80%',
                        allowBlank : false,
                        msgTarget : 'side',
                        fieldLabel: '用户Id'
                    },
                    {
                        xtype: 'textfield',
                        name : 'userAccount',
                        width : '80%',
                        allowBlank : false,
                        msgTarget : 'side',
                        fieldLabel: '登陆账号'
                    },
                    {
                        xtype: 'textfield',
                        name : 'userName',
                        allowBlank : false,
                        width : '80%',
                        fieldLabel: '姓名'
                    },
                    {
                        xtype: 'textfield',
                        name : 'userPassword',
                        width : '80%',
                        allowBlank : false,
                        msgTarget : 'side',
                        fieldLabel: '口令'
                    },
                     {
                        xtype: 'textfield',
                        name : 'userDesc',
                        width : '80%',                    
                        msgTarget : 'side',
                        fieldLabel: '用户描述'
                    },
                     {
                        xtype: 'numberfield',
                        name : 'userMobile',
                        hideTrigger:true,
                        width : '80%',                    
                        msgTarget : 'side',
                        maxLength:11,
                        minLength:11,
                        fieldLabel: '移动电话'
                    },
                    {
                        xtype: 'textfield',
                        name : 'userDept',
                        itemId:'userDeptId',
                        width : '80%',
                        allowBlank : false,
                        msgTarget : 'side',
                        fieldLabel: '部门'
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
