
Ext.application({
    name: 'ManageCard',
    autoCreateViewport: true,
    models: ['CatagoryTree','ArticleGrid','CardGrid'], 
    appFolder:'res/js/portal/article/manageCard',   
    stores: ['CatagoryTree','ArticleGrid'],
    controllers: ['ArticleController']
});