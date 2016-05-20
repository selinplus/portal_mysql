Ext.define('Weather.store.WeatherCombo',{
      extend:'Ext.data.Store',
      autoDestroy: false,      
      fields:['weatherType','weatherId'],
      data: [ {"weatherType":"晴","weatherId":"qing.png"}, 
      	      {"weatherType":"阴","weatherId":"yin.png"}, 
      	      {"weatherType":"雾","weatherId":"wu.png"},
      	      {"weatherType":"雨","weatherId":"yu.png"},
      	      {"weatherType":"雪","weatherId":"xue.png"}
      	    ],
      queryMode: 'local'
})


