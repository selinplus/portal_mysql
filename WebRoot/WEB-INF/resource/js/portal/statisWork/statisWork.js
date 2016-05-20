
Ext.application({
    name: 'StatisWork',
    autoCreateViewport: true,
    models: ['StatisWorkGrid'], 
    appFolder:'res/js/portal/statisWork',   
    stores: ['StatisWorkGrid'],
    controllers: ['StatisWorkController']
});