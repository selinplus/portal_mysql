
Ext.define('ManageArticle.model.ArticleGrid', {
	extend: 'Ext.data.Model',
	fields: [
	    { name : 'id'	  },
        { name : 'title'   },
        { name : 'createTime'   },
        { name : 'source'   },
        { name : 'bzZd'   },
        { name : 'htmlUrl'   }
	 ]
  }
);