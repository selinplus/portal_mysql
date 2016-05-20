


Ext.define('ManageCatagory.store.CatagoryTypeComboBox',{
      extend:'Ext.data.Store',
      autoDestroy: false,
      //model: 'ManageCatagory.model.CatagoryTypeComboBox',
      fields:['CatagoryType','CatagoryTypeText'],
      data: [ {"CatagoryType":"S","CatagoryTypeText":"单信息"}, 
      	      {"CatagoryType":"M","CatagoryTypeText":"多信息"}, 
      	      {"CatagoryType":"F","CatagoryTypeText":"栏目夹"}, 
      	      {"CatagoryType":"C","CatagoryTypeText":"专题"}],
      queryMode: 'local'
})


