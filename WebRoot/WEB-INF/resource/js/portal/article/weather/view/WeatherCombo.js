Ext.define('Weather.view.WeatherCombo',{
        extend :'Ext.form.field.ComboBox',
        alias:'widget.weathercombo',
        fieldLabel: '天气情况',
        displayField: 'weatherType',
        valueField: 'weatherId',
        width: 500,
        labelWidth: 130,
        store: 'WeatherCombo'
})
