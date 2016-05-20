
Ext.define('ManageAuthority.model.AuthorityGrid', {
	extend: 'Ext.data.Model',
	fields: [
	    { name : 'authorityId'	  },
        { name : 'authorityName'   },
        { name : 'authorityDesc'   },
        { name : 'resourceId'   },
        { name : 'resourceString'   },
        { name : 'module' }        
	 ],
	 idProperty:'authorityId'
  }
);