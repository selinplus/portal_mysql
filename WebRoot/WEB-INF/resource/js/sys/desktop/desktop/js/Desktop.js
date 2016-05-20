/*
 * ! Ext JS Library 4.0 Copyright(c) 2006-2011 Sencha Inc. licensing@sencha.com
 * http://www.sencha.com/license
 */

/**
 * @class Ext.ux.desktop.Desktop
 * @extends Ext.panel.Panel
 *          <p>
 *          This class manages the wallpaper, shortcuts and taskbar.
 *          </p>
 */
Ext.define('Ext.ux.desktop.Desktop', {
	extend : 'Ext.panel.Panel',

	alias : 'widget.desktop',

	uses : ['Ext.util.MixedCollection', 'Ext.menu.Menu',
			'Ext.view.View', // dataview
			'Ext.window.Window',

			'Ext.ux.desktop.TaskBar', 'Ext.ux.desktop.Wallpaper'],

	activeWindowCls : 'ux-desktop-active-win',
	inactiveWindowCls : 'ux-desktop-inactive-win',
	lastActiveWindow : null,

	border : false,
	html : '&#160;',
	layout : 'fit',

	xTickSize : 1,
	yTickSize : 1,
    datePicker:'',
	app : null,

	/**
	 * @cfg {Array|Store} shortcuts The items to add to the DataView. This can
	 *      be a {@link Ext.data.Store Store} or a simple array. Items should
	 *      minimally provide the fields in the
	 *      {@link Ext.ux.desktop.ShorcutModel ShortcutModel}.
	 */
	shortcuts : null,

	/**
	 * @cfg {String} shortcutItemSelector This property is passed to the
	 *      DataView for the desktop to select shortcut items. If the
	 *      {@link #shortcutTpl} is modified, this will probably need to be
	 *      modified as well.
	 */
	shortcutItemSelector : 'div.ux-desktop-shortcut',

	/**
	 * @cfg {String} shortcutTpl This XTemplate is used to render items in the
	 *      DataView. If this is changed, the {@link shortcutItemSelect} will
	 *      probably also need to changed.
	 * 
	 * shortcutTpl: [ '<tpl for=".">', '<div class="ux-desktop-shortcut"
	 * id="{name}-shortcut">', '<div class="ux-desktop-shortcut-icon
	 * {iconCls}">', '<img src="',Ext.BLANK_IMAGE_URL,'" title="{name}">', '</div>', '<span
	 * class="ux-desktop-shortcut-text">{name}</span>', '</div>', '</tpl>', '<div
	 * class="x-clear"></div>' ],
	 */
	shortcutTpl : [
			'<tpl for=".">',
			'<div class="x-clear"></div>',
			' <div class="container content">                                                                                                                                                             ',
			' 	<div class="aside-left">                                                                                                                                                                    ',
			'    	<div class="model-base">                                                                                                                                                                ',
			'        	<div class="model-ver-title"><i>工作通知</i></div>                                                                                                                                      ',
			'            <div class="model-panel">                                                                                                                                                            ',
			'            	<div class="search">                                                                                                                                                                ',
			'                	<input name="search-put" type="input" id="searchContent" class="base-form search-input"><input type="button" onclick="noticeSearch()" name="" value="" class="base-form serch-btn"><a href="javascript:refreshNotice()" class="base-form refresh-btn"></a>',
			'                </div>                                                                                                                                                                           ',
			'            	<ul id="notice-ul" class="notice-item">    ' ,
			'                    <tpl for="noticeList">',
			'					  {.}',
			'<tpl if="this.isToday(fbsjquery)">',
			'                    <li class="{[xindex % 2 === 0 ? "even" : "liodd"]}"><span class="xxbt"><a title="{xxbt}" href="javascript:noticePage(\'{id}\')" style="color:red;">{xxbt}</a></span><span class="fbrDeptMc">{fbrDeptMc}</span></li>                                                                                                        ',
			'<tpl else>',
			'                    <li class="{[xindex % 2 === 0 ? "even" : "liodd"]}"><span class="xxbt"><a title="{xxbt}" href="javascript:noticePage(\'{id}\')">{xxbt}</a></span><span class="fbrDeptMc">{fbrDeptMc}</span></li>                                                                                                        ',
			'</tpl>',
			'                    </tpl>',			'                                                                                                             ',
			'                </ul>                                                                                                                                                                            ',
			'                <p class="more"><a href="javascript:noticeMore()" class="more-btn">more</a></p>                                                                                                                         ',
			'            </div>                                                                                                                                                                               ',
			'            <div class="clearfix"></div>                                                                                                                                                         ',
			'        </div>                                                                                                                                                                               ',
			'        <div class="clear-height40"></div>                                                                                                                                                       ',
			'        <div id="leftbottom" class="model-base" >                                                                                                                                                         ',
			'        	<div class="aside-left-corner"  style="border-radius:8px;">                                                                                                                                  ',
			'                <div class="model-base">                                                                                                                                                     ',
			'                    <div class="model-zqrl-title"><span class="dbrq-l"></span>日历<span class="dbrq-r"></span></div>                                                                             ',
			'                    <div class="datepicker_old"></div>                                                                                                                                               ',
			'                                                                                                                                                                                                 ',
			'                </div>                                                                                                                                                                       ',
			'            </div>                                                                                                                                                                             ',
			'            <div class="aside-right-corner"  style="border-radius:8px;">                                                                                                                              ',
			'                <div class="model-base" style=" padding-bottom:1px;border-radius:8px 8px 2px 2px">                                                                                           ',
			'                    <div class="model-cross-title pos-item"><i>待办事宜<a href="javascript:refreshTodo()" class="base-form refresh-btn"></a></i></div>                                                                   ',
			'            	<ul id="todo-ul" class="todo-item">    ' ,
			'                    <tpl for="todoList">',
			'					  {.}',
			'                    <li><span class="todo"><a title="{title}" href="javascript:todoPage(\'{uri}\',\'{baseId}\',\'{registerId}\',\'{currentNode}\',\'{currentNodeMc}\',\'{senderNodeDm}\',\'{senderNodeMc}\',\'{forwardOrBack}\')">{title}</a></span></li>                                                                                                        ',
			'                    </tpl>',			'                                                                                                             ',
			'                </ul>                                                                                                                                                                            ',
			'                    <div class="clear-height10"></div>                                                                                                                                           ',
			'                    <p class="more"><a href="javascript:todoMore()" class="more-btn">more</a></p>                                                                                                                     ',
			'                    <div class="clearfix"></div>                                                                                                                                                 ',
			'                </div>                                                                                                                                                                       ',
			'            </div>                                                                                                                                                                             ',
			'        </div>                                                                                                                                                                               ',
			'    </div>                                                                                                                                                                                     ',
			'                                                                                                                                                                                                 ',
			'    <div class="aside-right">                                                                                                                                                                  ',
			'    	<div class="model-base mail-div">                                                                                                                                   ',
			'        	<div class="model-cross-title"><i>新邮件<a href="javascript:refreshMail()"  class="base-form refresh-btn"></a></i></div>                                                                                                                                      ',
			'            <ul id="mail-ul" class="email-item">                                                                                                                                                              ',
			'               <tpl for="mailList">',
			'					  {.}',
			'                <li><a title="{subject}" href="admin/mail/mailInfo?messageNumber={messageNumber}&active={active}&desktop=yes" target="_blank">{subject}</a><span class="nameFrom">{nameFrom}</span><span class="time">{sentDate}</span></li>                                                                              ',
			'                </tpl>',   
			'           </ul>                                                                                                                                                                                ',
			'            <p class="more"><a href="{basePath}admin/mail/index?type=recbox" target="_blank" class="more-btn">more</a></p>                                                                                                                             ',
			'            <div class="clearfix"></div>                                                                                                                                                         ',
			'        </div>                                                                                                                                                                               ',
			'        <div class="clear-height40"></div>                                                                                                                                                       ',
			'        <ul class="icon-item">                                                                                                                                                                   ',
			'            <li><a href="{basePath}admin/mail/newMail" target="_blank"><img src="res/js/sys/desktop/desktop/wallimages/icon1.png"><div class="mailinfo">写邮件</div></a></li>                                                                                                   ',
			'            <li><a href="{basePath}admin/mail/index?type=recbox" target="_blank"><img src="res/js/sys/desktop/desktop/wallimages/icon2.png"><div class="mailinfo">收件箱</div></a></li>                                                                                                   ',
			'            <li><a href="{basePath}admin/mail/index?type=sentbox" target="_blank"><img src="res/js/sys/desktop/desktop/wallimages/icon3.png"><div class="mailinfo">发件箱</div></a></li>                                                                                                   ',
			'            <li><a href="{basePath}admin/mail/index?type=lesebox" target="_blank"><img src="res/js/sys/desktop/desktop/wallimages/icon4.png"><div class="mailinfo">垃圾箱</div></a></li>                                                                                                   ',
			'        </ul>                                                                                                                                                                                    ',
			'        <div class="clear-height40"></div>                                                                                                                                                       ',
			'        <div class="model-base" style=" padding-bottom:5px;">                                                                                                                                ',
			'        	<div class="model-cross-title"><i>快速链接</i></div>                                                                                                                                    ',
			'            <ul class="link-item">                                                                                                                                                               ',
			'                <li><a href="http://76.12.120.16/odps/" target="_blank"><img src="res/js/sys/desktop/desktop/wallimages/link1.png"><div class="linkinfo">税务综合办公系统</div></a></li>                                                                                             ',
			'                <li><a href="http://sso.sdgs.tax.cn/sso/" target="_blank"><img src="res/js/sys/desktop/desktop/wallimages/link1.png"><div class="linkinfo">金三税收系统</div></a></li>                                                                                             ',
			'                <li><a  href="http://76.16.16.88/" target="_blank"><img src="res/js/sys/desktop/desktop/wallimages/link2.png"><div class="linkinfo">省局内网</div></a></li> ',
			' <li><a  href="http://76.68.16.156/home" target="_blank"><img src="res/js/sys/desktop/desktop/wallimages/link2.png"><div class="linkinfo">市局内网</div></a></li>                                                                                            ',
			'                <li><a  href="http://76.68.16.229/" target="_blank"><img src="res/js/sys/desktop/desktop/wallimages/link3.png"><div class="linkinfo">网盘</div></a></li>                                                                                                 ',
			'                                                                                                              ',
			'                <li><a  href="http://76.68.16.99:158" target="_blank"><img src="res/js/sys/desktop/desktop/wallimages/link5.png"><div class="linkinfo">税企邮箱</div></a></li>                                                                                             ',
			'            </ul>                                                                                                                                                                                ',
			'            <div class="clearfix"></div>                                                                                                                                                         ',
			'        </div>                                                                                                                                                                               ',
			'    </div>                                                                                                                                                                                     ',
			' </div>                                                                                                                                                                                   ',
			
			'</tpl>',
			'<div class="x-clear"></div>',{isToday:function(value){if(value==sysDate){ return true;}else{ return false;}}}],

	/**
	 * @cfg {Object} taskbarConfig The config object for the TaskBar.
	 */
	taskbarConfig : null,

	windowMenu : null,
	initShortcut : function() {
		var btnHeight = 64;
		var btnWidth = 64;
		var btnPadding = 30;
		var col = {
			index : 1,
			x : btnPadding
		};
		var row = {
			index : 1,
			y : btnPadding
		};
		var bottom;
		var numberOfItems = 0;
		var taskBarHeight = Ext.query(".ux-taskbar")[0].clientHeight + 40;
		var bodyHeight = Ext.getBody().getHeight() - taskBarHeight;
		var items = Ext.query(".ux-desktop-shortcut");

		for (var i = 0, len = items.length; i < len; i++) {
			numberOfItems += 1;
			bottom = row.y + btnHeight;
			if (((bodyHeight < bottom) ? true : false)
					&& bottom > (btnHeight + btnPadding)) {
				numberOfItems = 0;
				col = {
					index : col.index++,
					x : col.x + btnWidth + btnPadding
				};
				row = {
					index : 1,
					y : btnPadding
				};
			}
			Ext.fly(items[i]).setXY([col.x, row.y]);
			row.index++;
			row.y = row.y + btnHeight + btnPadding;
		}
		$(".datepicker").datePicker({
								inline : true,
								selectMultiple : false
							});
	},
	initComponent : function() {
		var me = this;

		me.windowMenu = new Ext.menu.Menu(me.createWindowMenu());

		me.bbar = me.taskbar = new Ext.ux.desktop.TaskBar(me.taskbarConfig);
		me.taskbar.windowMenu = me.windowMenu;

		me.windows = new Ext.util.MixedCollection();
        //暂时屏蔽桌面右键事件
		//me.contextMenu = new Ext.menu.Menu(me.createDesktopMenu());

		me.items = [{
					xtype : 'wallpaper',
					id : me.id + '_wallpaper'
				}, me.createDataView()];

		me.callParent();

		// me.shortcutsView = me.items.getAt(1);
		// me.shortcutsView.on('itemclick', me.onShortcutItemClick, me);

		var wallpaper = me.wallpaper;
		me.wallpaper = me.items.getAt(0);
		if (wallpaper) {
			me.setWallpaper(wallpaper, me.wallpaperStretch);
		}
	},

	afterRender : function() {
		var me = this;
		me.callParent();
		me.el.on('contextmenu', me.onDesktopMenu, me);
			
		// Ext.Function.defer(me.initShortcut,1);
	},

	// ------------------------------------------------------
	// Overrideable configuration creation methods

	createDataView : function() {
		var me = this;
		return {
			xtype : 'dataview',
			overItemCls : 'x-view-over',
			trackOver : true,
			itemSelector : me.shortcutItemSelector,
			store : me.shortcuts,
			style : {
				position : 'absolute'
			},
			x : 0,
			y : 0,
			// listeners:{
			// resize:me.initShortcut
			// },
			tpl : new Ext.XTemplate(me.shortcutTpl)
		};
	},
	createDesktopMenu : function() {
		var me = this, ret = {
			items : me.contextMenuItems || []
		};

		if (ret.items.length) {
			ret.items.push('-');
		}

		ret.items.push({
					text : 'Tile',
					handler : me.tileWindows,
					scope : me,
					minWindows : 1
				}, {
					text : 'Cascade',
					handler : me.cascadeWindows,
					scope : me,
					minWindows : 1
				})

		return ret;
	},

	createWindowMenu : function() {
		var me = this;
		return {
			defaultAlign : 'br-tr',
			items : [{
						text : '恢复',
						handler : me.onWindowMenuRestore,
						scope : me
					}, {
						text : '最小化',
						handler : me.onWindowMenuMinimize,
						scope : me
					}, {
						text : '最大化',
						handler : me.onWindowMenuMaximize,
						scope : me
					}, '-', {
						text : '关闭',
						handler : me.onWindowMenuClose,
						scope : me
					}],
			listeners : {
				beforeshow : me.onWindowMenuBeforeShow,
				hide : me.onWindowMenuHide,
				scope : me
			}
		};
	},

	// ------------------------------------------------------
	// Event handler methods

	onDesktopMenu : function(e) {
		/*
		var me = this, menu = me.contextMenu;
		e.stopEvent();
		if (!menu.rendered) {
			menu.on('beforeshow', me.onDesktopMenuBeforeShow, me);
		}
		menu.showAt(e.getXY());
		menu.doConstrain();
		*/
	},

	onDesktopMenuBeforeShow : function(menu) {
		var me = this, count = me.windows.getCount();

		menu.items.each(function(item) {
					var min = item.minWindows || 0;
					item.setDisabled(count < min);
				});
	},

	onShortcutItemClick : function(dataView, record) {// 更改由原来调用getModule方法改为调用getLeafModule 20140509
		var me = this, module = me.app.getLeafModule(record.data.module), win = module
				&& module.createWindow();

		if (win) {
			me.restoreWindow(win);
		}
	},

	onWindowClose : function(win) {
		var me = this;
		me.windows.remove(win);
		me.taskbar.removeTaskButton(win.taskButton);
		me.updateActiveWindow();
	},

	// ------------------------------------------------------
	// Window context menu handlers

	onWindowMenuBeforeShow : function(menu) {
		var items = menu.items.items, win = menu.theWin;
		items[0].setDisabled(win.maximized !== true && win.hidden !== true); // Restore
		items[1].setDisabled(win.minimized === true); // Minimize
		items[2].setDisabled(win.maximized === true || win.hidden === true); // Maximize
	},

	onWindowMenuClose : function() {
		var me = this, win = me.windowMenu.theWin;

		win.close();
	},

	onWindowMenuHide : function(menu) {
		Ext.defer(function() {
					menu.theWin = null;
				}, 1);
	},

	onWindowMenuMaximize : function() {
		var me = this, win = me.windowMenu.theWin;

		win.maximize();
		win.toFront();
	},

	onWindowMenuMinimize : function() {
		var me = this, win = me.windowMenu.theWin;

		win.minimize();
	},

	onWindowMenuRestore : function() {
		var me = this, win = me.windowMenu.theWin;

		me.restoreWindow(win);
	},

	// ------------------------------------------------------
	// Dynamic (re)configuration methods

	getWallpaper : function() {
		return this.wallpaper.wallpaper;
	},

	setTickSize : function(xTickSize, yTickSize) {
		var me = this, xt = me.xTickSize = xTickSize, yt = me.yTickSize = (arguments.length > 1)
				? yTickSize
				: xt;

		me.windows.each(function(win) {
					var dd = win.dd, resizer = win.resizer;
					dd.xTickSize = xt;
					dd.yTickSize = yt;
					resizer.widthIncrement = xt;
					resizer.heightIncrement = yt;
				});
	},

	setWallpaper : function(wallpaper, stretch) {
		this.wallpaper.setWallpaper(wallpaper, stretch);
		return this;
	},

	// ------------------------------------------------------
	// Window management methods

	cascadeWindows : function() {
		var x = 0, y = 0, zmgr = this.getDesktopZIndexManager();

		zmgr.eachBottomUp(function(win) {
					if (win.isWindow && win.isVisible() && !win.maximized) {
						win.setPosition(x, y);
						x += 20;
						y += 20;
					}
				});
	},

	createWindow : function(config, cls) {
		var me = this, win, cfg = Ext.applyIf(config || {}, {
					stateful : false,
					isWindow : true,
					constrainHeader : true,
					minimizable : true,
					maximizable : true
				});

		cls = cls || Ext.window.Window;
		win = me.add(new cls(cfg));

		me.windows.add(win);

		win.taskButton = me.taskbar.addTaskButton(win);
		win.animateTarget = win.taskButton.el;

		win.on({
					activate : me.updateActiveWindow,
					beforeshow : me.updateActiveWindow,
					deactivate : me.updateActiveWindow,
					minimize : me.minimizeWindow,
					destroy : me.onWindowClose,
					scope : me
				});

		win.on({
					boxready : function() {
						win.dd.xTickSize = me.xTickSize;
						win.dd.yTickSize = me.yTickSize;

						if (win.resizer) {
							win.resizer.widthIncrement = me.xTickSize;
							win.resizer.heightIncrement = me.yTickSize;
						}
					},
					single : true
				});

		// replace normal window close w/fadeOut animation:
		win.doClose = function() {
			win.doClose = Ext.emptyFn; // dblclick can call again...
			win.el.disableShadow();
			win.el.fadeOut({
						listeners : {
							afteranimate : function() {
								win.destroy();
							}
						}
					});
		};

		return win;
	},

	getActiveWindow : function() {
		var win = null, zmgr = this.getDesktopZIndexManager();

		if (zmgr) {
			// We cannot rely on activate/deactive because that fires against
			// non-Window
			// components in the stack.

			zmgr.eachTopDown(function(comp) {
						if (comp.isWindow && !comp.hidden) {
							win = comp;
							return false;
						}
						return true;
					});
		}

		return win;
	},

	getDesktopZIndexManager : function() {
		var windows = this.windows;
		// TODO - there has to be a better way to get this...
		return (windows.getCount() && windows.getAt(0).zIndexManager) || null;
	},

	getWindow : function(id) {
		return this.windows.get(id);
	},

	minimizeWindow : function(win) {
		win.minimized = true;
		win.hide();
	},

	restoreWindow : function(win) {
		if (win.isVisible()) {
			win.restore();
			win.toFront();
		} else {
			win.show();
		}
		return win;
	},

	tileWindows : function() {
		var me = this, availWidth = me.body.getWidth(true);
		var x = me.xTickSize, y = me.yTickSize, nextY = y;

		me.windows.each(function(win) {
					if (win.isVisible() && !win.maximized) {
						var w = win.el.getWidth();

						// Wrap to next row if we are not at the line start and
						// this Window will
						// go off the end
						if (x > me.xTickSize && x + w > availWidth) {
							x = me.xTickSize;
							y = nextY;
						}

						win.setPosition(x, y);
						x += w + me.xTickSize;
						nextY = Math.max(nextY, y + win.el.getHeight()
										+ me.yTickSize);
					}
				});
	},

	updateActiveWindow : function() {
		var me = this, activeWindow = me.getActiveWindow(), last = me.lastActiveWindow;
		if (activeWindow === last) {
			return;
		}

		if (last) {
			if (last.el.dom) {
				last.addCls(me.inactiveWindowCls);
				last.removeCls(me.activeWindowCls);
			}
			last.active = false;
		}

		me.lastActiveWindow = activeWindow;

		if (activeWindow) {
			activeWindow.addCls(me.activeWindowCls);
			activeWindow.removeCls(me.inactiveWindowCls);
			activeWindow.minimized = false;
			activeWindow.active = true;
		}

		me.taskbar.setActiveButton(activeWindow && activeWindow.taskButton);
	}
});
