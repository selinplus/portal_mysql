
Ext.define('ManageCatagory.model.CatagoryGrid', {
	extend: 'Ext.data.Model',
	//idProperty:'',
	fields: [
	    {name:'id'},
	    { name : 'parentId'	  },
        { name : 'catagoryName'   },
        { name : 'catagoryType'   },
        { name : 'catagoryUrl'   },       
        { name : 'catagoryDesc'   },
        { name : 'jfXx'   },
        { name : 'jfTp'   },
        { name : 'bzQy'   },
        { name : 'bzSh'   },
        { name : 'bzTj'   },
        { name : 'pageModal'   },
        { name : 'articleModal'   },
        { name : 'deptId'   },
        { name : 'catagoryPic'   },
        { name : 'deptName'   } ,
        { name : 'pubType'   } ,
        { name : 'pubUrl'   } 
	 ]
  }
);