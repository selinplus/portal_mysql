
Ext.application({
    name: 'ManageArticle',
    autoCreateViewport: true,
    models: ['CatagoryTree','ArticleGrid'], 
    appFolder:'res/js/portal/article',   
    stores: ['CatagoryTree','ArticleGrid'],
    controllers: ['ArticleController']
});