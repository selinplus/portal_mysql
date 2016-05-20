
Ext.application({
    name: 'ManageCatagory',
    autoCreateViewport: true,
    models: ['ModuleTree','CatagoryGrid','CatagoryTypeComboBox'], 
    appFolder:'res/js/portal/catagory/manageCatagory',   
    stores: ['ModuleTree','CatagoryGrid','CatagoryTypeComboBox'],
    controllers: ['CatagoryController']
});