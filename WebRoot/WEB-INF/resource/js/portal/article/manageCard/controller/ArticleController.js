Ext.define('ManageCard.controller.ArticleController', {
	extend : 'Ext.app.Controller',

	refs : [{
				ref : 'catagoryTree',
				selector : 'catagorytree'
			}, {
				ref : 'articleGrid',
				selector : 'articlegrid'
			}, {
				ref : 'gridEdit',
				selector : 'gridedit'
			}],

	stores : ['CatagoryTree', 'ArticleGrid'],

	init : function() {
		this.control({

					'catagorytree' : {
						beforeitemexpand : this.onItemExpand,
						beforeitemclick : this.onBeforeItemClick,
						itemclick : this.onClickTree
					},
					'viewport panel toolbar button[action=add]' : {
						click : this.addGrid
					},
					'viewport panel toolbar button[action=delete]' : {
						click : this.deleteGrid
					},
					'gridedit button[action=save]' : {
						click : this.saveGrid
					}
				})
	},
	addGrid : function() {
		var store = this.getArticleGridStore();
		var selNode=this.getCatagoryTree().getSelectionModel().getSelection()[0];
		var mProxy = store.getProxy();
		var catagoryId = store.getProxy().extraParams.id;
		if(catagoryId){
		  if(catagoryId==selNode.getId()){
		  var pal = Ext.create('ManageCard.view.GridEdit');		  
		  pal.html='<iframe id="cataframe" name="cataframe" src="admin/portal/common/cardPublish?catagoryId='+catagoryId+'" width=100% height=100% frameBorder="0"></iframe>';
		  pal.show();
		  }else{
		  	Ext.Msg.alert('请选择具体栏目!');
		  }
		}else{
			Ext.Msg.alert('请选择具体栏目!');
		}
	},
	deleteGrid : function() {
		var iGridStore = this.getArticleGridStore();
		var iGrid = this.getArticleGrid();
		var irecord = iGrid.getSelectionModel().getSelection()[0];
		Ext.Msg.confirm('资源','是否确认删除？',function(choice){
		if (choice=='yes') {			
			if (irecord) {
				iGridStore.remove(irecord);
			} else {
				Ext.Msg.alert('请选择要删除的记录!');
			}
		}
		});
		
	},
	saveGrid : function(button) {
		var ipan= button.up('form');
		var contForm=window.frames["cataframe"].document.getElementById("sbutton");
		var selNode=this.getCatagoryTree().getSelectionModel().getSelection()[0];
		//ipan.body.mask('保存中...');
		contForm.click();
		var checkFlag = window.frames["cataframe"].document.getElementById("checkFlag").value;
		if(checkFlag=="0"){
			button.setVisible(false);
		}
		var store = this.getArticleGridStore();
		var mProxy = store.getProxy();
		//清空store
		store.loadData([],false);
		mProxy.setExtraParam("id", selNode.get("id"));
		store.load();
	},
	onItemExpand : function(node, optd) {
		var store = this.getCatagoryTreeStore();
		var mProxy = store.getProxy();
		mProxy.setExtraParam("id", node.get("id"));

	},
	onBeforeItemClick : function(viewtree, record, item, index, e, eOpts) {

		e.stopEvent();
	},

	onClickTree : function(viewtree, record, item, index, e, eOpts) {
		if (record.isLeaf()) {
			var store = this.getArticleGridStore();
			var mProxy = store.getProxy();
			//清空store
			store.loadData([],false);
			mProxy.setExtraParam("id", record.get("id"));
			store.load();
		}
	}

});
