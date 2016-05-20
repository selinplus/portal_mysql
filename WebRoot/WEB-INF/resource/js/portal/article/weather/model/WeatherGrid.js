
Ext.define('weather.model.WeatherGrid', {
	extend: 'Ext.data.Model',
	fields: [
	    { name : 'id'	  },
        { name : 'content'   },
        { name : 'source'   },
        { name : 'weatherTime'   },
        { name : 'weatherId'   },
        { name : 'createTime'   },
        { name : 'week'   },
        { name : 'title'   }       
        
	 ]
  }
);