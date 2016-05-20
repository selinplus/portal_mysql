Ext.define('ManageCatagory.view.GridEdit', {
    extend: 'Ext.window.Window',
    alias : 'widget.gridedit',

    requires: ['Ext.form.Panel','ManageCatagory.view.CatagoryTypeComboBox','ManageCatagory.view.CatagoryPubTypeCombo'],

    title : '栏目维护',
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
                        name : 'catagoryName',
                        width : '80%',
                        allowBlank : false,
                        msgTarget : 'side',
                        fieldLabel: '栏目名称'
                    },
                    {
                        xtype: 'catagorytypecombobox',
                        name : 'catagoryType',
                        width : '80%',
                        allowBlank : false,
                        fieldLabel: '栏目类型'
                    },
                    {
                        xtype: 'catagorypubtypecombo',
                        name : 'pubUrl',
                        width : '80%',
                        allowBlank : false,
                        fieldLabel: '发布形式'
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
                        xtype: 'checkbox',
                        name : 'catagoryDesc',
                        width : '80%',
                        allowBlank : false,
                        msgTarget : 'side',
                        fieldLabel: '说明',
                        boxLabel  : '是否为包含图片的专题',
                        inputValue: 'P',
                        uncheckedValue:'null'
                    },  
                    {
                        xtype: 'textfield',
                        name : 'jfXx',
                        width : '80%',
                        allowBlank : false,
                        msgTarget : 'side',
                        value:0,
                        fieldLabel: '默认信息积分'
                    },  
                    {
                        xtype: 'textfield',
                        name : 'jfTp',
                        width : '80%',
                        allowBlank : false,
                        msgTarget : 'side',
                        value:0,
                        fieldLabel: '默认图片积分'
                    },  
                    {
                        xtype: 'checkbox',
                        name : 'bzQy',
                        width : '80%',
                        allowBlank : false,
                        msgTarget : 'side',
                        checked:true,
                        boxLabel  : '是否启用',
                         uncheckedValue:'null'
                    } ,  
                    {
                        xtype: 'checkbox',
                        name : 'bzSh',
                        width : '80%',
                        allowBlank : false,
                        msgTarget : 'side',
                        boxLabel  : '是否审核',
                         uncheckedValue:'null'
                        
                    },  
                    {
                        xtype: 'checkbox',
                        name : 'bzTj',
                        width : '80%',
                        allowBlank : false,
                        msgTarget : 'side',
                        boxLabel  : '是否支持访问统计',
                         uncheckedValue:'null'
                    },  
                    {
                        xtype: 'form',
                        name : 'pageModalForm',
                        width : 300,
                        items:[{xtype: 'filefield',
                        name : 'pageModalFile',
                        width : '80%',
                        msgTarget : 'side',
                        fieldLabel: '栏目页模板',
                        buttonText: '浏览'}],
                        buttons: [{
					        text : '上传',
					        name : 'pageButton'
					    }]
                    },  
                    {
                        xtype: 'form',
                        name : 'artModalForm',
                        width : 300,
                        items:[{xtype: 'filefield',
                        name : 'artModalFile',
                        width : '80%',
                        msgTarget : 'side',
                        fieldLabel: '文章页模板',
                        buttonText: '浏览'}],
                        buttons: [{
					        text: '上传',
					        name: 'artButton'
					    }]
                    }, 
                    {
                        xtype: 'form',
                        name : 'catagoryPicForm',
                        width : 300,
                        items:[{xtype: 'filefield',
                        name : 'catagoryPicFile',
                        width : '80%',
                        msgTarget : 'side',
                        fieldLabel: '栏目图片',
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
