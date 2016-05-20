Ext.define('Sys.controller.MenuController', {
    extend: 'Ext.app.Controller',

    refs: [{
        ref: 'menuTreeView',
        selector: 'menutreeview'
    },
    {
        ref: 'contentCardView',
        selector: 'contentcardview'
    }
    ],

    stores: ['SysTree'],
    
    init: function() {    	
      this.control({
      'viewport panel toolbar button[action=btLogout]' : {
						click : function(){location.href=basePath+'j_spring_security_logout';}
					},
        'menutreeview': {
                beforeitemclick: this.onBeforeItemClick,
                itemclick : this.onClickTree
            }
       })
    },
    
    onItemExpand:  function(node,optd){
			var store = this.getSysTreeStore();					     
			var mProxy=store.getProxy();
		    mProxy.setExtraParam("id",node.get("id"));
	} ,	
	onBeforeItemClick: function(viewtree, record, item, index, e, eOpts ){
		
		 e.stopEvent();
	},
	
	onClickTree: function(viewtree, record, item, index, e, eOpts ){
		var content = this.getContentCardView();
		
	    if(!record.isLeaf()){
	    	return false;
	    }
	    if(content.getChildByElement(record.get("id"))){
	    	content.setActiveTab(record.get("id"));
	    	return false;
	    }
	    content.el.mask('加载中...');
		var tab = Ext.create('Ext.panel.Panel', {
		    title : record.get("text"),	    
			closable : true,
			id : record.get("id"),
			layout: {
                    type: 'fit',
                    align: 'stretch'
                },
		    html: '',
		    lisnteners:{show:function(){content.el.unmask();}}
		});
		tab.html='<iframe onload="Ext.getCmp(\'sysTabPanel\').el.unmask();" id="frame'+record.get("id")+'" src="'+record.get("href")+'" width=100% height="100%" frameBorder="0"></iframe>';
		content.add(tab).show();
		content.setActiveTab(tab);
	}
});
