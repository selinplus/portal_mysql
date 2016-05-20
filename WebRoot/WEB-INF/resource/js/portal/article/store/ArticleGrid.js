Ext.define('ManageArticle.store.ArticleGrid',{
    extend: 'Ext.data.Store',
	requires: 'ManageArticle.model.ArticleGrid',
	model: 'ManageArticle.model.ArticleGrid',
	autoSync : true,
	pageSize : 20,
    proxy : {
         type: 'ajax',
         api: {
	    	create  : 'admin/portal/managerArticle/manageArticleSave',
	    	read    : 'admin/portal/managerArticle/manageArticleQuery',
	    	update  : 'admin/portal/managerArticle/manageArticleUpdate',
		    destroy : 'admin/portal/managerArticle/manageArticleDelete'
		},
         reader: {
             type : 'json',
             root : 'records',
    		 totalProperty   : 'totalCount',
    		 idProperty      : 'id'
         },
         writer: {
             type : 'json',
    		 idProperty      : 'id'
         }
     }
})
