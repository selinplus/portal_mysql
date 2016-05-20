Ext.define('ManageCatagory.view.CatagoryPubTypeCombo',{
        extend :'Ext.form.field.ComboBox',
        alias:'widget.catagorypubtypecombo',
        fieldLabel: '发布形式',
        displayField: 'pubType',
        valueField: 'pubUrl',
        width: 500,
        labelWidth: 130,
        store: 'CatagoryPubTypeCombo'
})
