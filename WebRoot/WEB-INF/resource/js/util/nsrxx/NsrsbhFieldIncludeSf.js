Ext.define('Ytgs.nsrxx.NsrsbhFieldIncludeSf', {
	extend : 'Ext.form.field.Text',
	alias : 'widget.nsrsbhfieldincludesf',
	name : 'nsrsbh',
	allowBlank : false,
	msgTarget : 'side',
	fieldLabel : '纳税人识别号',
	enableKeyEvents : true,
	activeError : '',
	listeners : {
		keypress : function(me, e, eOpts) {
			if (e.getKey() == 13) {
				me.up('form').el.mask();
				this.getNsrmcQuery(me, e, eOpts);
			}
		}
	},
	getNsrmcQuery : function(thisObj, eventObj, eOpts) {
		var me = this;
		Ext.Ajax.request({
			url : 'admin/zgjx/getNsrxxIncludeSf',
			params : {
				nsrsbh : thisObj.getValue()
			},
			callback : function(options, success, response) {
				var text = Ext.decode(response.responseText);
				if (text.success) {
					var text = Ext.decode(response.responseText);
					if (me.up('form')) {
						if (me.up('form').down('textfield[name=nsrmc]')) {
							me.up('form').down('textfield[name=nsrmc]')
									.setValue(text.nsrmc);
						}
						if (me.up('form').down('textfield[name=zgswskfjDm]')) {
							me.up('form').down('textfield[name=zgswskfjDm]')
									.setValue(text.zgswskfjDm);
						}
						if (me.up('form').down('textfield[name=swjgDm]')) {
							me.up('form').down('textfield[name=swjgDm]')
									.setValue(text.zgswskfjDm);
						}
						if (me.up('form').down('textfield[name=zgswskfjMc]')) {
							me.up('form').down('textfield[name=zgswskfjMc]')
									.setValue(text.zgswskfjMc);
						}
						if (me.up('form').down('textfield[name=swjgMc]')) {
							me.up('form').down('textfield[name=swjgMc]')
									.setValue(text.zgswskfjMc);
						}
						if (me.up('form').down('textfield[name=ssglyMc]')) {
							me.up('form').down('textfield[name=ssglyMc]')
									.setValue(text.ssglyMc);
						}
						if (me.up('form').down('textfield[name=zgswgyMc]')) {
							me.up('form').down('textfield[name=zgswgyMc]')
									.setValue(text.ssglyMc);
						}
						if (me.up('form').down('textfield[name=ssglyDm]')) {
							me.up('form').down('textfield[name=ssglyDm]')
									.setValue(text.ssglyDm);
						}
						if (me.up('form').down('textfield[name=zgswgyDm]')) {
							me.up('form').down('textfield[name=zgswgyDm]')
									.setValue(text.ssglyDm);
						}
						if (me.up('form').down('textfield[name=dimHy]')) {
							me.up('form').down('textfield[name=dimHy]')
									.setValue(text.dimHy);
						}
						if (me.up('form').down('textfield[name=dimHyMc]')) {
							me.up('form').down('textfield[name=dimHyMc]')
									.setValue(text.dimHyMc);
						}
						if (me.up('form').down('textfield[name=sfHyZzs]')) {
							me.up('form').down('textfield[name=sfHyZzs]')
									.setValue(text.sfHyZzs);
						}
						if (me.up('form').down('textfield[name=sfHySds]')) {
							me.up('form').down('textfield[name=sfHySds]')
									.setValue(text.sfHySds);
						}
						if (me.up('form').down('textfield[name=sftsczZzsSds]')) {
							if (text.dimSzZzs != null
									&& text.dimSzSdsQy != null) {
								me.up('form')
										.down('textfield[name=sftsczZzsSds]')
										.setValue('是');
							} else {
								me.up('form')
										.down('textfield[name=sftsczZzsSds]')
										.setValue('否');
							}
						}

						if (me.up('form').down('textfield[name=dimZzslx]')) {
							me.up('form').down('textfield[name=dimZzslx]')
									.setValue(text.dimZzslx);
						}
						if (me.up('form').down('textfield[name=dimZzslxMc]')) {
							me.up('form').down('textfield[name=dimZzslxMc]')
									.setValue(text.dimZzslxMc);
						}
					}
				} else {
					me.markInvalid('没有该纳税人信息');
					me.focus();
				}
				me.up('form').el.unmask();
			}
		});
	}
})
