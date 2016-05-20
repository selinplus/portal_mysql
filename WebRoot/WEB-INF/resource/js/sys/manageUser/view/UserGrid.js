Ext.define('ManageUser.view.UserGrid', {
			extend : 'Ext.grid.Panel',
			alias : 'widget.usergrid',
			store : 'UserGrid',
			viewConfig : {
				enableTextSelection : true
			},// 可选择
			border : 0,
			dockedItems : [{
						xtype : 'pagingtoolbar',
						store : 'UserGrid',
						dock : 'bottom',
						displayInfo : true
					}],
			columns : {
				defaults : {
					resizable : false
				},
				items : [{
							text : '序号',
							dataIndex : 'xh',
							width : 50,
							xtype : 'rownumberer'
						}, {
							text : '用户ID',
							dataIndex : 'userId',
							fles : 1
						}, {
							text : '登陆账号',
							dataIndex : 'userAccount',
							flex : 1
						}, {
							text : '姓名',
							dataIndex : 'userName',
							flex : 3
						}, {
							text : '用户描述',
							dataIndex : 'userDesc',
							flex : 2
						}, {
							text : '移动电话',
							dataIndex : 'userMobile',
							flex : 2,
							renderer : function(value) {
								if (value == 'null') {
									return '';
								}
								return value;
							}
						}, {
							text : '部门',
							dataIndex : 'userDesktop',
							flex : 3
						}]
			}
		})