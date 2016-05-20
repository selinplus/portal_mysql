
Ext.define('StatisWork.view.StatisWorkGrid', {
			extend : 'Ext.grid.Panel',
			alias : 'widget.statisworkgrid',
			store:'StatisWorkGrid',
			border : 0,			
			columns: 
			{ defaults:{resizable:false},items:
			[
		        { text: '单位',  dataIndex: 'deptName',flex : 3},
		        { text: '工作量', dataIndex: 'count',flex: 1}
		   	]
			}
		})