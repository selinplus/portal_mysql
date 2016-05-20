
Ext.define('ManageCatagory.view.CatagoryGrid', {
			extend : 'Ext.grid.Panel',
			alias : 'widget.catagorygrid',
			store:'CatagoryGrid',
			viewConfig : {enableTextSelection:true},//可选择
			border : 0,
			dockedItems: [{
		        xtype: 'pagingtoolbar',
		        store: 'CatagoryGrid',  
		        dock: 'bottom',
		        displayInfo: true
		    }],
			columns: 
			{ defaults:{resizable:false},items:
			[
		        { text: '栏目ID', dataIndex: 'id',flex: 2},
		        { text: '栏目名称', dataIndex: 'catagoryName',flex: 3},
		        { text: '栏目类型', dataIndex: 'catagoryType',flex: 2},
		        { text: '栏目URL', dataIndex: 'catagoryUrl' , flex: 6 },
		        { text: '说明', dataIndex: 'catagoryDesc' , flex: 2 },
		        { text: '是否启用', dataIndex: 'bzQy' , flex: 2 },
		        { text: '是否审核', dataIndex: 'bzSh' , flex: 2 },
		        { text: '栏目页模板', dataIndex: 'pageModal' , flex: 6 },
		        { text: '文章页模板', dataIndex: 'articleModal' , flex: 6 },
		        { text: '栏目图片', dataIndex: 'catagoryPic' , flex: 6 },
		        { text: '所属部门', dataIndex: 'deptName' , flex: 6 },
		        { text: '发布形式', dataIndex: 'pubType' , flex: 2 },
		        { text: '发布URL', dataIndex: 'pubUrl' , flex: 6 }
		   	]
			}
		})