Ext.define('ManageCatagory.controller.CatagoryController', {
	extend : 'Ext.app.Controller',

	refs : [{
				ref : 'moduleTree',
				selector : 'moduletree'
			}, {
				ref : 'catagoryGrid',
				selector : 'catagorygrid'
			}, {
				ref : 'departTree',
				selector : 'departtree'
			}, {
				ref : 'gridEdit',
				selector : 'gridedit'
			}],

	stores : ['ModuleTree', 'CatagoryGrid','CatagoryTypeComboBox','DepartTree','CatagoryPubTypeCombo'],

	init : function() {
		var me=this;
		this.getCatagoryGridStore().on('write', this.onGridWrite, me, {
            buffer: 1
        });
		this.control({

					'moduletree' : {
						beforeitemexpand : this.onItemExpand,
						beforeitemclick : this.onBeforeItemClick,
						itemclick : this.onClickTree
					},
					
					'viewport panel toolbar button[action=add]' : {
						click : this.addGrid
					},
					'viewport panel toolbar button[action=edit]' : {
						click : this.editGrid
					},
					'viewport panel toolbar button[action=upModel]' : {
						click : this.upModel
					},
					'viewport panel toolbar button[action=delete]' : {
						click : this.deleteGrid
					},
					'gridedit button[action=save]' : {
						click : this.saveGrid
					},
					'gridedit button[name=pageButton]' : {
						click : this.pageModelUploadHandler
					},
					'gridedit button[name=artButton]' : {
						click : this.artModelUploadHandler
					},
					'gridedit button[name=deptButton]' : {
						click : this.deptPopupTreeHandler
					},
					'gridedit button[name=catagoryPicButton]' : {
						click : this.catagoryPicUploadHandler
					},
					'departtree' : {
						beforeitemexpand : this.onDeptItemExpand,
						beforeitemclick : this.onDeptBeforeItemClick,
						itemdblclick : this.onDbClickTree,
						beforeitemdblclick : this.onDeptBeforeItemDblClick
					}
				})
	},
	
	addGrid : function() {
		var store = this.getModuleTreeStore();
		var selNode=this.getModuleTree().getSelectionModel().getSelection()[0];
		var mProxy = store.getProxy();
		var moduleId = store.getProxy().extraParams.id;
		//Ext.Msg.alert(selNode.get("parentId"));
		//var thisNode = store.getNodeById(selNode.get("parentId"));
		//Ext.Msg.alert(thisNode.get("parentId"));
		//Ext.Msg.alert(moduleId);

		  if(selNode){
		  			//新增栏目需判断是否超过三级
			if(selNode.get("parentId")){
				var thisNode = store.getNodeById(selNode.get("parentId"));
				if (thisNode.get("parentId")){
			   		if (thisNode.get("parentId")=="0000"){
			       		Ext.Msg.alert('最多可以增加到三级栏目!');
			       	    return false;
			   		}
				}
			}
		    var edit = Ext.create('ManageCatagory.view.GridEdit');
		    edit.down('form[name=pageModalForm]').setVisible(false);
		    edit.down('form[name=artModalForm]').setVisible(false);
		    edit.show();
			}
			 else
			{
				Ext.Msg.alert('请选择具体栏目!');
			}

	},
	upModel : function() {
		var iGrid = this.getCatagoryGrid();
		var irecord = iGrid.getSelectionModel().getSelection()[0];
		if (irecord) {
			var selNode=this.getModuleTree().getSelectionModel().getSelection()[0];
			if(selNode){
		    	var edit = Ext.create('ManageCatagory.view.GridEdit');
		    	
				edit.show();
				edit.down('form').loadRecord(irecord);
				//edit.down('form').down('textfield').setDisabled(true);
			}
			 else
			{
				Ext.Msg.alert('请到左边树中选择要修改的栏目!');
				return false;
			}
			
		} else {
			Ext.Msg.alert('请选择要编辑的记录!');
		}

	},
	editGrid : function() {
		
		var iGrid = this.getCatagoryGrid();
		var irecord = iGrid.getSelectionModel().getSelection()[0];
		if (irecord) {
			var selNode=this.getModuleTree().getSelectionModel().getSelection()[0];
			if(selNode){
		    	var edit = Ext.create('ManageCatagory.view.GridEdit');
				edit.show();
				edit.down('form').loadRecord(irecord);
				//edit.down('form').down('textfield').setDisabled(true);
			}
			 else
			{
				Ext.Msg.alert('请到左边树中选择要修改的栏目!');
				return false;
			}
			
		} else {
			Ext.Msg.alert('请选择要编辑的记录!');
		}
	},
	deleteGrid : function() {
		var iGridStore = this.getCatagoryGridStore();
		var iGrid = this.getCatagoryGrid();
		var irecord = iGrid.getSelectionModel().getSelection()[0];
		if (irecord) {
			var selNode=this.getModuleTree().getSelectionModel().getSelection()[0];
			var store = this.getModuleTreeStore();
			if (!selNode){
				Ext.Msg.alert('请到左边树中选择要删除的栏目!');
				return false;
			}
			var thisNode = store.getNodeById(irecord.get("id"));
			if(!thisNode.isLeaf()){//非叶子结点不能删除
				Ext.Msg.alert('不能删除非空点!');
				return false;
			};
			Ext.Msg.confirm('栏目管理','是否确认删除？',function(choice){
			if (choice=='yes') {			
				
					iGridStore.remove(irecord);				
				
			}
			});
		}else{
				Ext.Msg.alert('请选择要删除的记录!');
		};
	},
	saveGrid : function(button) {
		var win = button.up('window'), form = win.down('form'), record = form
				.getRecord(), values = form.getValues();
		var selNode;
		if(form.isValid()){
			var index=this.getCatagoryPubTypeComboStore().find('pubUrl',values.pubUrl);
		    values.pubType=this.getCatagoryPubTypeComboStore().getAt(index).get('pubType');
			if (record) {
				record.set(values);
			} else {
				
				selNode=this.getModuleTree().getSelectionModel().getSelection()[0];
			 	values.parentId=selNode.getId();
			 	//values.pubType='立即发布';
				var tmpModel = this.getCatagoryGridStore().insert(0, values);
				
			}
			win.close();
		}else{
			Ext.Msg.alert('项目录入非法，请检查!');
			
		}

	},
	onGridWrite : function(istore, operation, eOpts) {
			var store = this.getModuleTreeStore();
			var mProxy = store.getProxy();
			var operation = new Ext.data.Operation({
			    action: 'read'
			});
				
			
			var selNode=this.getModuleTree().getSelectionModel().getSelection()[0];
			if (!selNode){
				var iGrid = this.getCatagoryGrid();
				var irecord = iGrid.getSelectionModel().getSelection()[0];
				if (irecord){
					selNode = store.getNodeById(irecord.get("id"));
					
				}
			}
			//给id赋值为选中节点的父节点id，若无父节点，则为当前选中节点id。
			mProxy.setExtraParam("id",selNode.get("parentId")||selNode.getId());
			mProxy.read(operation);
			//获取选中节点的父节点，若无父节点，则为当前选中节点
			var parentNode = store.getNodeById(selNode.get("parentId")||selNode.getId());
			var thisNode = store.getNodeById(selNode.get("id"));
			//若某节点只有一个子项，删除时需将其设置为叶子节点，并将其图标改为叶子。
			if(parentNode.firstChild===parentNode.lastChild){
				//若某节点的第一个子节点和最后一个节点一样，说明该节点只有一个子节点。
				parentNode.set("cls","file");
				parentNode.set("leaf",true);
			}
			store.reload({node:parentNode});//重新RELOAD父节点，自动更新修改过的节点。
			
			//thisNode.expand();
	},
	onItemExpand : function(node, optd) {
		var store = this.getModuleTreeStore();
		var mProxy = store.getProxy();
		mProxy.setExtraParam("id", node.get("id"));

	},
	onBeforeItemClick : function(viewtree, record, item, index, e, eOpts) {

		e.stopEvent();
	},

	onClickTree : function(viewtree, record, item, index, e, eOpts) {
		
		if (record.isLeaf()) {
			var store = this.getCatagoryGridStore();
			var mProxy = store.getProxy();
			//清空store
			store.loadData([],false);
			mProxy.setExtraParam("id", record.get("id"));
			//后台调用leaf参数,根据leaf调用不同的方法。true 为叶子节点。只显示出本节点的记录。
			mProxy.setExtraParam("leaf", "true");
			store.load();
		}
		else
		{
			var store = this.getCatagoryGridStore();
			var mProxy = store.getProxy();
			//清空store
			store.loadData([],false);
			mProxy.setExtraParam("id", record.get("id"));
			//后台调用leaf参数,根据leaf调用不同的方法。false 为非叶子节点。显示出本节点下的所有节点记录。
			mProxy.setExtraParam("leaf", "false");
			store.load();
		}
	},
	pageModelUploadHandler: function(oButton) {
					            var form = oButton.up('form').getForm();
					            var uniForm =oButton.up('form').up('form');
					            
					            if(form.isValid()){
					                form.submit({
					                    url: 'admin/util/uploadPageModelFile',
					                    params:{'colName':uniForm.down('textfield[name=catagoryUrl]').getValue()=="null"?"":uniForm.down('textfield[name=catagoryUrl]').getValue()},
					                    waitMsg: '上传栏目模板...',
				                    success: function(fp, o) {
					                       Ext.Msg.alert("上传成功!");
					                       uniForm.down('textfield[name=catagoryUrl]').setValue(o.result.catagoryUrl);					                      
					                       uniForm.down('textfield[name=pageModal]').setValue(o.result.pageModal);
					                    },
					                    failure: function(fp, o) {
					                       Ext.Msg.alert("上传失败，请检查模板文件!");
					                    }
					                });
					            }
					        },
	artModelUploadHandler: function(oButton) {
					            var form = oButton.up('form').getForm();
					            var uniForm =oButton.up('form').up('form');
					            if(form.isValid()){
					                form.submit({
					                    url: 'admin/util/uploadArtModelFile',
					                    params:{'colName':uniForm.down('textfield[name=catagoryUrl]').getValue()=="null"?"":uniForm.down('textfield[name=catagoryUrl]').getValue()},
					                    waitMsg: '上传文章模板...',
					                    success: function(fp, o) {
											Ext.Msg.alert("上传成功!");
					                    	uniForm.down('textfield[name=catagoryUrl]').setValue(o.result.catagoryUrl);
					                        uniForm.down('textfield[name=articleModal]').setValue(o.result.articleModal);
					                    },
					                    failure: function(fp, o) {
					                       Ext.Msg.alert("上传失败，请检查模板文件!");
					                    }
					                });
					            }
					        },
catagoryPicUploadHandler: function(oButton) {
				            var form = oButton.up('form').getForm();
				            var uniForm =oButton.up('form').up('form');
				            if(form.isValid()){
				                form.submit({
				                    url: 'admin/portal/catagory/uploadCatagoryPicJson',
				                    params:{'colName':uniForm.down('textfield[name=catagoryUrl]').getValue()=="null"?"":uniForm.down('textfield[name=catagoryUrl]').getValue()},
				                    waitMsg: '上传栏目图片...',
				                    success: function(fp, o) {
										Ext.Msg.alert("上传成功!");
				                    	uniForm.down('textfield[name=catagoryPic]').setValue(o.result.url);
				                    },
				                    failure: function(fp, o) {
				                       Ext.Msg.alert("上传失败，请检查文件!");
				                    }
				                });
				            }
				        },
  deptPopupTreeHandler: function(oText){
		var edit = Ext.create('Ext.window.Window',{
		height: 400,
		width: 400,
		id : 'popWindow',
		layout: 'fit',
		items:{ xtype: 'departtree'}});
	    edit.show();
  },
  onDeptItemExpand : function(node, optd) {
		var store = this.getDepartTreeStore();
		var mProxy = store.getProxy();
		mProxy.setExtraParam("id", node.get("id"));

	},
	onDeptBeforeItemClick : function(depttree, record, item, index, e, eOpts) {

		e.stopEvent();
	},
	onDeptBeforeItemDblClick:function(depttree, record, item, index, e, eOpts) {
	    depttree.toggleOnDblClick=false;
	},
    onDbClickTree: function( depttree, record, item, index, e, eOpts ){
    	
    	
    	var selNode=this.getDepartTree().getSelectionModel().getSelection()[0];
			var store = this.getDepartTreeStore();
			if (!selNode){
				Ext.Msg.alert('请选择部门!');
				return false;
			}else{
			   var uniForm =Ext.getCmp('pForm');
			   var popWin =Ext.getCmp('popWindow');
			   uniForm.down('textfield[name=deptName]').setValue(selNode.get("text"));
			   uniForm.down('textfield[name=deptId]').setValue(selNode.get("id"));
			   popWin.close();
			}
		
		
    }
});
