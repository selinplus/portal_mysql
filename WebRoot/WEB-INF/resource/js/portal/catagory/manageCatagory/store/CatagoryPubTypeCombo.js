


Ext.define('ManageCatagory.store.CatagoryPubTypeCombo',{
      extend:'Ext.data.Store',
      autoDestroy: false,
      //model: 'ManageCatagory.model.CatagoryTypeComboBox',
      fields:['pubType','pubUrl'],
      data: [ {"pubType":"通用","pubUrl":"admin/portal/common/contentPublish"}, 
      	      {"pubType":"天气预报","pubUrl":"admin/portal/article/weather"}, 
      	      {"pubType":"电子名片","pubUrl":"admin/portal/common/cardPublish"}
      	    ],
      queryMode: 'local'
})


