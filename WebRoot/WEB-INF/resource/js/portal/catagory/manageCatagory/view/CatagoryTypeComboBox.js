Ext.define('ManageCatagory.view.CatagoryTypeComboBox',{
        extend :'Ext.form.field.ComboBox',
        alias:'widget.catagorytypecombobox',
        fieldLabel: '栏目类型',
        displayField: 'CatagoryTypeText',
        valueField: 'CatagoryType',
        width: 500,
        labelWidth: 130,
        store: 'CatagoryTypeComboBox'
})
