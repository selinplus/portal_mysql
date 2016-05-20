Ext.define('Gztzview.store.GztzviewGrid', {
			extend : 'Ext.data.Store',
			requires : 'Gztzview.model.GztzviewGrid',
			model : 'Gztzview.model.GztzviewGrid',
			autoSync : false,
			proxy : {
				type : 'ajax',
				api : {
					read : 'admin/xzjx/gztz/gztzviewquery'
				},
				reader : {
					type : 'json',
					root : 'records',
					totalProperty : 'totalCount'//后台需返回，记录总数，分页用
				},
				writer : {
					type : 'json',
					idProperty : 'id'
				}
			}
		})
