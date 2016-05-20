Ext.define('ManageCard.store.ArticleGrid',{
    extend: 'Ext.data.Store',
	requires: 'ManageCard.model.ArticleGrid',
	model: 'ManageCard.model.ArticleGrid',
	autoSync : true,
	pageSize : 20,
    proxy : {
         type: 'ajax',
         api: {
	    	create  : 'admin/portal/managerArticle/manageArticleSave',
	    	read    : 'admin/portal/managerArticle/manageArticleQuery',
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
