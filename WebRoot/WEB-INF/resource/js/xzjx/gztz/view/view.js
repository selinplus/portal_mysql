/**
 * 引入Ytgs.nsrxx.NsrsbhField，
 * 输入纳税人识别号后台查询出该纳税人信息（纳税人名称、主管税务机关、税收管理员）
 * 如需使用，则页面中应该有name=nsrmc，name=zgswskfjDm，name=zgswskfjMc，name=ssglyMc
 */
Ext.Loader.setConfig({
			enabled : true,
			disableCaching : false,
			paths : {
				'Ytgs' : 'res/js/util/'
			}
		});
Ext.require('Ytgs.nsrxx.NsrsbhField');
Ext.application({
			name : 'Gztzview',
			autoCreateViewport : true,
			models : ['GztzviewGrid'],
			appFolder : 'res/js/xzjx/gztz/view',
			stores : ['GztzviewGrid'],
			controllers : ['GztzviewController']
		});