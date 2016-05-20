-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.10 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 portal 的数据库结构
DROP DATABASE IF EXISTS `portal`;
CREATE DATABASE IF NOT EXISTS `portal` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `portal`;


-- 导出  表 portal.portal_catagory 结构
DROP TABLE IF EXISTS `portal_catagory`;
CREATE TABLE IF NOT EXISTS `portal_catagory` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PARENT_ID` int(11) DEFAULT NULL,
  `CATAGORY_NAME` varchar(50) DEFAULT NULL COMMENT '��Ŀ����',
  `CATAGORY_URL` varchar(200) DEFAULT NULL COMMENT '���ӵ�ַ',
  `CATAGORY_TYPE` varchar(1) DEFAULT NULL COMMENT '��Ŀ���ͣ�����Ϣ��S��������Ϣ��M����ר�⣨C��',
  `PUB_TYPE` varchar(50) DEFAULT NULL COMMENT '������ʽ��ͨ�á�����Ԥ����������Ƭ',
  `PUB_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '����ʱ��',
  `UPDATE_TIME` datetime DEFAULT NULL,
  `CATAGORY_DESC` varchar(300) DEFAULT NULL COMMENT '˵�� : P���������ͼƬ��Ŀ',
  `JF_XX` decimal(8,0) DEFAULT '1' COMMENT 'Ĭ����Ϣ����',
  `JF_TP` decimal(8,0) DEFAULT '0' COMMENT 'Ĭ��ͼƬ����',
  `BZ_QY` varchar(4) DEFAULT NULL COMMENT '�Ƿ�����',
  `BZ_SH` varchar(4) DEFAULT NULL COMMENT '�Ƿ����',
  `BZ_TJ` varchar(4) DEFAULT NULL COMMENT '�Ƿ�֧�ַ���ͳ��',
  `PAGE_MODAL` varchar(100) DEFAULT NULL COMMENT '��Ŀҳģ��',
  `ARTICLE_MODAL` varchar(100) DEFAULT NULL COMMENT '����ҳģ��',
  `DEPT_ID` varchar(20) DEFAULT NULL COMMENT '�������ID',
  `DEPT_NAME` varchar(100) DEFAULT NULL COMMENT '�����������',
  `CATAGORY_PIC` varchar(200) DEFAULT NULL COMMENT '��ҳ��ר������ͼƬ',
  `PUB_URL` varchar(100) DEFAULT NULL COMMENT '����URL',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='��Ŀ';

-- 正在导出表  portal.portal_catagory 的数据：~3 rows (大约)
DELETE FROM `portal_catagory`;
/*!40000 ALTER TABLE `portal_catagory` DISABLE KEYS */;
INSERT INTO `portal_catagory` (`ID`, `PARENT_ID`, `CATAGORY_NAME`, `CATAGORY_URL`, `CATAGORY_TYPE`, `PUB_TYPE`, `PUB_TIME`, `UPDATE_TIME`, `CATAGORY_DESC`, `JF_XX`, `JF_TP`, `BZ_QY`, `BZ_SH`, `BZ_TJ`, `PAGE_MODAL`, `ARTICLE_MODAL`, `DEPT_ID`, `DEPT_NAME`, `CATAGORY_PIC`, `PUB_URL`) VALUES
	(1, NULL, '栏目', NULL, 'R', NULL, '2015-02-06 12:33:42', '2015-02-05 16:32:52', NULL, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(2, 1, '专题', 'col20150206153625_999', 'C', '通用', '2015-02-09 12:23:23', '2015-02-06 15:36:35', 'P', 0, 0, 'on', 'null', 'null', 'qzlxjy.html', 'rightList', '13706000000', '龙之韵科技平台', '', 'admin/portal/common/contentPublish'),
	(6, 2, '微信之公众账号', 'col20150206153533_670', 'M', '通用', '2015-02-09 12:22:14', '2015-02-06 15:35:43', 'P', 0, 0, 'on', 'null', 'null', 'qzlxjyList.html', 'qzlxjySingle.html', '13706000000', '龙之韵科技平台', '', 'admin/portal/common/contentPublish');
/*!40000 ALTER TABLE `portal_catagory` ENABLE KEYS */;


-- 导出  表 portal.portal_content 结构
DROP TABLE IF EXISTS `portal_content`;
CREATE TABLE IF NOT EXISTS `portal_content` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(300) NOT NULL COMMENT '����',
  `CONTENT` text COMMENT '����',
  `AUTHOR` varchar(20) DEFAULT NULL COMMENT '����',
  `SOURCE` varchar(100) DEFAULT NULL COMMENT '��Դ',
  `KEYWORDS` varchar(500) DEFAULT NULL COMMENT '�ؼ���',
  `INTRODUCTION` varchar(800) DEFAULT NULL COMMENT 'ժҪ',
  `SUB_TITLE` varchar(100) DEFAULT NULL COMMENT '������',
  `EYEBROW_HEAD` varchar(100) DEFAULT NULL COMMENT '����',
  `LINK_TITLE` varchar(100) DEFAULT NULL COMMENT '���ӱ���',
  `LINK_URL` varchar(100) DEFAULT NULL COMMENT '������ַ',
  `YXRQ_Q` datetime DEFAULT NULL,
  `YXRQ_Z` datetime DEFAULT NULL,
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `DISPLAY_TIME` datetime NOT NULL,
  `BZ_DSFB` varchar(3) DEFAULT NULL COMMENT '�Ƿ�ʱ��������on����null',
  `PUBLISH_TIME` datetime DEFAULT NULL,
  `BZ_PL` varchar(3) DEFAULT NULL COMMENT '�Ƿ����ۣ���on����null',
  `BZ_SH` varchar(3) DEFAULT NULL COMMENT '�Ƿ���ˣ���on����null',
  `BZ_ZD` varchar(3) DEFAULT NULL COMMENT '�Ƿ��ö�����on����null',
  `TOP_STOP_TIME` datetime DEFAULT NULL,
  `CATAGORY_ID` decimal(20,0) DEFAULT NULL COMMENT '��Ϣ����',
  `CONTENT_PAGING` decimal(8,0) DEFAULT '0' COMMENT '���ݷ�ҳ����Ϣ����������ʱ��Ϊ��ҳ��ʾ���˴�����ÿҳ����������HTML�����������ã�',
  `ARTICLE_SCORE` decimal(8,0) DEFAULT '1' COMMENT '���·�ֵ',
  `PICTURE_SCORE` decimal(8,0) DEFAULT '0' COMMENT 'ͼƬ��ֵ',
  `FB_STATUS` varchar(20) DEFAULT NULL COMMENT '����״̬��δ����WFB���ѷ���YFB���ѳ���YCG��������FBZ�����޸�YXG',
  `BZ_HSZ` varchar(3) DEFAULT NULL COMMENT '�Ƿ�������վ����Y����N',
  `DISPLAY_ORDER` decimal(8,0) DEFAULT '0' COMMENT '��ʾ˳��',
  `LRR_DM` varchar(20) DEFAULT NULL COMMENT '¼���˴���',
  `LRR_MC` varchar(50) DEFAULT NULL COMMENT '¼��������',
  `LRR_DEPT_DM` varchar(20) DEFAULT NULL COMMENT '¼���˲��Ŵ���',
  `LRR_DEPT_MC` varchar(20) DEFAULT NULL COMMENT '¼���˲�������',
  `SH_STATUS` varchar(20) DEFAULT NULL COMMENT '���״̬�������BSH�������DSH�����ͨ��SHTG����˲�ͨ��SHBTG',
  `SHR_DM` varchar(20) DEFAULT NULL COMMENT '����˴���',
  `SHR_MC` varchar(20) DEFAULT NULL COMMENT '���������',
  `SHR_DEPT_DM` varchar(20) DEFAULT NULL COMMENT '����˲��Ŵ���',
  `SHR_DEPT_MC` varchar(20) DEFAULT NULL COMMENT '����˲�������',
  `SH_TIME` datetime DEFAULT NULL,
  `HTML_URL` varchar(200) DEFAULT NULL COMMENT '��̬��ַ',
  `TOPIC_PIC` varchar(100) DEFAULT NULL COMMENT '����ͼƬ��ַ',
  `BZ_TOPICPIC` varchar(3) DEFAULT NULL COMMENT '�Ƿ���������ͼƬ:��on����null',
  `DEPT_DESC` varchar(100) DEFAULT NULL COMMENT '���ż��',
  PRIMARY KEY (`ID`),
  KEY `INDX_PORTAL_CONTENT_CATAGORYID` (`CATAGORY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='����';

-- 正在导出表  portal.portal_content 的数据：~5 rows (大约)
DELETE FROM `portal_content`;
/*!40000 ALTER TABLE `portal_content` DISABLE KEYS */;
INSERT INTO `portal_content` (`ID`, `TITLE`, `CONTENT`, `AUTHOR`, `SOURCE`, `KEYWORDS`, `INTRODUCTION`, `SUB_TITLE`, `EYEBROW_HEAD`, `LINK_TITLE`, `LINK_URL`, `YXRQ_Q`, `YXRQ_Z`, `CREATE_TIME`, `DISPLAY_TIME`, `BZ_DSFB`, `PUBLISH_TIME`, `BZ_PL`, `BZ_SH`, `BZ_ZD`, `TOP_STOP_TIME`, `CATAGORY_ID`, `CONTENT_PAGING`, `ARTICLE_SCORE`, `PICTURE_SCORE`, `FB_STATUS`, `BZ_HSZ`, `DISPLAY_ORDER`, `LRR_DM`, `LRR_MC`, `LRR_DEPT_DM`, `LRR_DEPT_MC`, `SH_STATUS`, `SHR_DM`, `SHR_MC`, `SHR_DEPT_DM`, `SHR_DEPT_MC`, `SH_TIME`, `HTML_URL`, `TOPIC_PIC`, `BZ_TOPICPIC`, `DEPT_DESC`) VALUES
	(2, 'eweww', 'weeww', NULL, 'weer', NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, '2015-02-06 15:45:43', '2015-02-06 15:45:43', NULL, NULL, NULL, NULL, NULL, NULL, 6, 0, 1, 0, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '/res/repository/portal/art/2015-02-06/1609cf23-4051-438d-b5fe-7b076702cf85.html', '', NULL, NULL),
	(3, 'www', '<p>\r\n	<img src="http://bcs.duapp.com/lytecmaster%2Fimage%2F20150206212456_748.jpg?sign=MBO%3A5tVf8BOFP4duWdpG9YB4Bqr0%3A1AotHXGEvW3wFc6acSSTBkiW%2Be4%3D" width="600" height="400" alt="" />\r\n</p>\r\n<p>\r\n	<br />\r\n</p>\r\n<p>\r\n	<br />\r\n</p>\r\n<p>\r\n	rfggerre\r\n</p>\r\n<p>\r\n	<br />\r\n</p>\r\n<p>\r\n	<br />\r\n</p>\r\n<p>\r\n	<img src="http://bcs.duapp.com/lytecmaster%2Fimage%2F20150206212532_944.jpg?sign=MBO%3A5tVf8BOFP4duWdpG9YB4Bqr0%3A1nxKE3xA7%2B1Fz2qAkH5OzxZlw9A%3D" width="500" height="281" alt="" />\r\n</p>', NULL, 'eeee', NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, '2015-02-06 21:26:19', '2015-02-06 21:26:19', NULL, NULL, NULL, NULL, NULL, NULL, 6, 0, 1, 0, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '/res/repository/portal/art/2015-02-06/18dd4d56-3013-42fa-b9e6-065df038bb87.html', '', NULL, NULL),
	(10, '1', '1', NULL, '1', NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, '2015-02-09 12:55:24', '2015-02-09 12:55:24', NULL, NULL, NULL, NULL, NULL, NULL, 6, 0, 1, 0, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '/res/repository/portal/art/2015-02-09/fa90c818-d66b-428c-acc6-2defc22726c0.html', '', NULL, NULL),
	(11, 'e', 'e', NULL, 'e', NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, '2015-02-09 13:13:19', '2015-02-09 13:13:19', NULL, NULL, NULL, NULL, NULL, NULL, 6, 0, 1, 0, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '/res/repository/portal/art/2015-02-09/db12a0b5-75cd-475c-8a7c-5713786d1376.html', '', NULL, NULL),
	(12, 'dfdgdf', 'gfdfddd', NULL, 'fgdg', NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, '2015-02-09 13:18:34', '2015-02-09 13:18:34', NULL, NULL, NULL, NULL, NULL, NULL, 6, 0, 1, 0, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '/res/repository/portal/art/2015-02-09/cbc4b4a1-e0a5-48fd-a7dd-5d9436591daf.html', '', NULL, NULL);
/*!40000 ALTER TABLE `portal_content` ENABLE KEYS */;


-- 导出  表 portal.sys_authorities 结构
DROP TABLE IF EXISTS `sys_authorities`;
CREATE TABLE IF NOT EXISTS `sys_authorities` (
  `AUTHORITY_ID` varchar(200) NOT NULL,
  `AUTHORITY_NAME` varchar(500) DEFAULT NULL,
  `AUTHORITY_DESC` varchar(500) DEFAULT NULL,
  `ENABLED` char(1) DEFAULT 'Y',
  `ISSYS` char(1) DEFAULT 'N',
  `MODULE` int(11) DEFAULT NULL,
  PRIMARY KEY (`AUTHORITY_ID`),
  KEY `MODULE` (`MODULE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='SYS_AUTHORITIES';

-- 正在导出表  portal.sys_authorities 的数据：~15 rows (大约)
DELETE FROM `sys_authorities`;
/*!40000 ALTER TABLE `sys_authorities` DISABLE KEYS */;
INSERT INTO `sys_authorities` (`AUTHORITY_ID`, `AUTHORITY_NAME`, `AUTHORITY_DESC`, `ENABLED`, `ISSYS`, `MODULE`) VALUES
	('AUTH_INFO_MANAGE', 'AUTH_INFO_MANAGE', '内网信息管理', 'Y', 'N', 6),
	('AUTH_LOGIN', 'AUTH_LOGIN', '登录系统', 'Y', 'N', NULL),
	('AUTH_MANAGEUSER', 'AUTH_MANAGEUSER', '用户管理', 'Y', 'N', 4),
	('AUTH_PORTAL_CATAGORY', 'AUTH_PORTAL_CATAGORY', '栏目管理', 'Y', 'N', 9),
	('AUTH_PORTAL_COMMON_CONTENT', 'AUTH_PORTAL_COMMON_CONTENT', '信息维护', 'Y', 'N', 6),
	('AUTH_PORTAL_COMMON_CONTENT_CARD', 'AUTH_PORTAL_COMMON_CONTENT_CARD', '电子名片维护', 'Y', 'N', 12),
	('AUTH_PORTAL_STATISWORK', 'AUTH_PORTAL_STATISWORK', '工作量统计', 'Y', 'N', 11),
	('AUTH_SYS_AUTH', 'AUTH_SYS_AUTH', '权限管理', 'Y', 'N', 3),
	('AUTH_SYS_DEPT', 'AUTH_SYS_DEPT', '部门维护', 'Y', 'N', 10),
	('AUTH_SYS_MENU', 'AUTH_SYS_MENU', '菜单管理', 'Y', 'N', 3),
	('AUTH_SYS_MODULE', 'AUTH_SYS_MODULE', '模块管理', 'Y', 'N', 3),
	('AUTH_SYS_RESOURCE', 'AUTH_SYS_RESOURCE', '资源管理', 'Y', 'Y', 3),
	('AUTH_SYS_ROLE', 'AUTH_SYS_ROLE', '角色管理', 'Y', 'N', 3),
	('AUTH_TZTG_TZGL', 'AUTH_TZTG_TZGL', '通知通告_通知管理', 'Y', 'N', 8),
	('AUTH_USER', 'AUTH_USER', 'AUTH_USER', 'Y', 'N', NULL);
/*!40000 ALTER TABLE `sys_authorities` ENABLE KEYS */;


-- 导出  表 portal.sys_authorities_resources 结构
DROP TABLE IF EXISTS `sys_authorities_resources`;
CREATE TABLE IF NOT EXISTS `sys_authorities_resources` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `AUTHORITY_ID` varchar(200) DEFAULT NULL,
  `RESOURCE_ID` varchar(200) DEFAULT NULL,
  `ENABLED` char(1) DEFAULT 'Y',
  PRIMARY KEY (`ID`),
  KEY `AUTHORITY_ID` (`AUTHORITY_ID`),
  KEY `RESOURCE_ID` (`RESOURCE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=341 DEFAULT CHARSET=utf8 COMMENT='SYS_AUTHORITIES_RESOURCES';

-- 正在导出表  portal.sys_authorities_resources 的数据：~12 rows (大约)
DELETE FROM `sys_authorities_resources`;
/*!40000 ALTER TABLE `sys_authorities_resources` DISABLE KEYS */;
INSERT INTO `sys_authorities_resources` (`ID`, `AUTHORITY_ID`, `RESOURCE_ID`, `ENABLED`) VALUES
	(13, 'AUTH_SYS_AUTH', 'sys_auth', 'Y'),
	(14, 'AUTH_SYS_RESOURCE', 'sys_resource', 'Y'),
	(15, 'AUTH_SYS_ROLE', 'sys_role', 'Y'),
	(16, 'AUTH_SYS_MODULE', 'sys_module', 'Y'),
	(24, 'AUTH_SYS_MENU', 'sys_menu', 'Y'),
	(100, 'AUTH_PORTAL_COMMON_CONTENT', 'portal_manageArticle', 'Y'),
	(121, 'AUTH_PORTAL_CATAGORY', 'portal_catagory', 'Y'),
	(122, 'AUTH_SYS_DEPT', 'sys_dept', 'Y'),
	(123, 'AUTH_MANAGEUSER', 'sys_manageUser', 'Y'),
	(180, 'AUTH_PORTAL_STATISWORK', 'portal_statiswork', 'Y'),
	(200, 'AUTH_PORTAL_COMMON_CONTENT_CARD', 'portal_manageCard', 'Y'),
	(340, 'AUTH_TZTG_TZGL', 'TZTG_TZGL', 'Y');
/*!40000 ALTER TABLE `sys_authorities_resources` ENABLE KEYS */;


-- 导出  表 portal.sys_dept 结构
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE IF NOT EXISTS `sys_dept` (
  `ID` varchar(11) NOT NULL COMMENT '����ID',
  `PARENT_ID` varchar(11) DEFAULT NULL COMMENT '���ڵ�ID',
  `DEPT_NAME` varchar(200) DEFAULT NULL COMMENT '��������',
  `DEPTSTANDALONE` char(1) DEFAULT NULL COMMENT '�������ǲ���',
  `DEPT_DESC` varchar(100) DEFAULT NULL COMMENT '��������',
  `DEPTSORT` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='SYS_DEPT';

-- 正在导出表  portal.sys_dept 的数据：~3 rows (大约)
DELETE FROM `sys_dept`;
/*!40000 ALTER TABLE `sys_dept` DISABLE KEYS */;
INSERT INTO `sys_dept` (`ID`, `PARENT_ID`, `DEPT_NAME`, `DEPTSTANDALONE`, `DEPT_DESC`, `DEPTSORT`) VALUES
	('13706000000', '13700000000', '公司（单位）', 'Y', '公司（单位）', '0'),
	('13706120000', '13706000000', 'muping', 'Y', '', ''),
	('13706120100', '13706120000', 'shuidao', 'N', '', '');
/*!40000 ALTER TABLE `sys_dept` ENABLE KEYS */;


-- 导出  表 portal.sys_menus 结构
DROP TABLE IF EXISTS `sys_menus`;
CREATE TABLE IF NOT EXISTS `sys_menus` (
  `MENU_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PARENT_ID` int(11) DEFAULT NULL,
  `GROUP_ID` varchar(11) DEFAULT NULL,
  `AUTHORITY_ID` varchar(200) DEFAULT NULL,
  `DISPLAY` varchar(100) DEFAULT NULL,
  `SHORTCUT` varchar(20) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `DISPINDEX` varchar(11) NOT NULL DEFAULT '0',
  `ICONURI` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`MENU_ID`),
  KEY `AUTHORITY_ID` (`AUTHORITY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='SYS_MENUS';

-- 正在导出表  portal.sys_menus 的数据：~17 rows (大约)
DELETE FROM `sys_menus`;
/*!40000 ALTER TABLE `sys_menus` DISABLE KEYS */;
INSERT INTO `sys_menus` (`MENU_ID`, `PARENT_ID`, `GROUP_ID`, `AUTHORITY_ID`, `DISPLAY`, `SHORTCUT`, `DESCRIPTION`, `DISPINDEX`, `ICONURI`) VALUES
	(1, 0, NULL, NULL, '菜单', NULL, NULL, '0', NULL),
	(2, 1, NULL, NULL, '系统管理', NULL, '系统管理', '0', NULL),
	(3, 2, '1', 'AUTH_SYS_RESOURCE', '资源管理', NULL, '资源管理', '0', NULL),
	(4, 2, '1', 'AUTH_SYS_AUTH', '权限管理', NULL, '权限管理', '0', NULL),
	(5, 2, '1', 'AUTH_SYS_ROLE', '角色管理', NULL, '角色管理', '0', NULL),
	(6, 2, '1', 'AUTH_SYS_MODULE', '模块管理', NULL, '模块管理', '0', NULL),
	(7, 2, '1', 'AUTH_SYS_MENU', '菜单管理', NULL, '菜单管理', '0', NULL),
	(8, 1, NULL, NULL, '内网管理', NULL, '内网管理', '0', NULL),
	(9, 8, NULL, 'AUTH_PORTAL_COMMON_CONTENT', '信息维护', NULL, '信息维护', '0', NULL),
	(10, 8, NULL, 'AUTH_PORTAL_CATAGORY', '栏目管理', NULL, '栏目管理', '0', NULL),
	(11, 2, NULL, 'AUTH_SYS_DEPT', '部门管理', NULL, '部门管理', '0', NULL),
	(12, 2, NULL, 'AUTH_MANAGEUSER', '用户管理', NULL, '用户管理', '0', NULL),
	(13, 8, NULL, 'AUTH_PORTAL_STATISWORK', '工作量统计', NULL, '工作量统计', '0', NULL),
	(14, 8, NULL, 'AUTH_PORTAL_COMMON_CONTENT_CARD', '电子名片维护', NULL, '电子名片维护', '0', NULL),
	(15, 1, NULL, NULL, '通讯系统', NULL, '信息通讯', '0', NULL),
	(16, 1, NULL, NULL, '通知通告', NULL, NULL, '0', NULL),
	(17, 16, NULL, 'AUTH_TZTG_TZGL', '通知管理', NULL, '通知管理', '0', NULL);
/*!40000 ALTER TABLE `sys_menus` ENABLE KEYS */;


-- 导出  表 portal.sys_modules 结构
DROP TABLE IF EXISTS `sys_modules`;
CREATE TABLE IF NOT EXISTS `sys_modules` (
  `MODULE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PARENT_ID` int(11) NOT NULL,
  `MODULE_NAME` varchar(100) DEFAULT NULL,
  `DISPINDEX` varchar(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`MODULE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='SYS_MODULES';

-- 正在导出表  portal.sys_modules 的数据：~14 rows (大约)
DELETE FROM `sys_modules`;
/*!40000 ALTER TABLE `sys_modules` DISABLE KEYS */;
INSERT INTO `sys_modules` (`MODULE_ID`, `PARENT_ID`, `MODULE_NAME`, `DISPINDEX`) VALUES
	(1, 0, '模块管理', '0'),
	(2, 1, '系统管理', '1'),
	(3, 2, '系统维护', '0'),
	(4, 2, '用户管理', '0'),
	(5, 1, '内网管理', '0'),
	(6, 5, '信息维护', '0'),
	(7, 5, '区县栏目', '0'),
	(8, 1, '通知通告', '0'),
	(9, 5, '栏目管理', '0'),
	(10, 2, '部门管理', '0'),
	(11, 5, '工作量统计', '0'),
	(12, 5, '电子名片维护', '0'),
	(13, 1, '通讯系统', '0'),
	(14, 13, '邮件系统', '0');
/*!40000 ALTER TABLE `sys_modules` ENABLE KEYS */;


-- 导出  表 portal.sys_resources 结构
DROP TABLE IF EXISTS `sys_resources`;
CREATE TABLE IF NOT EXISTS `sys_resources` (
  `RESOURCE_ID` varchar(200) NOT NULL,
  `RESOURCE_NAME` varchar(500) DEFAULT NULL,
  `RESOURCE_DESC` varchar(500) DEFAULT NULL,
  `RESOURCE_TYPE` varchar(40) DEFAULT NULL,
  `RESOURCE_STRING` varchar(500) DEFAULT NULL,
  `PRIORITY` char(1) DEFAULT NULL,
  `ENABLED` char(1) DEFAULT 'Y',
  `ISSYS` char(1) DEFAULT 'N',
  `MODULE` int(11) DEFAULT NULL,
  PRIMARY KEY (`RESOURCE_ID`),
  KEY `MODULE` (`MODULE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='SYS_RESOURCES';

-- 正在导出表  portal.sys_resources 的数据：~12 rows (大约)
DELETE FROM `sys_resources`;
/*!40000 ALTER TABLE `sys_resources` DISABLE KEYS */;
INSERT INTO `sys_resources` (`RESOURCE_ID`, `RESOURCE_NAME`, `RESOURCE_DESC`, `RESOURCE_TYPE`, `RESOURCE_STRING`, `PRIORITY`, `ENABLED`, `ISSYS`, `MODULE`) VALUES
	('portal_catagory', 'portal_catagory', '栏目管理', NULL, '/admin/portal/catagory/manageCatagory', NULL, 'Y', 'N', 9),
	('portal_manageArticle', 'portal_manageArticle', '信息维护', 'url', '/admin/portal/article/manageArticle', NULL, 'Y', 'N', 6),
	('portal_manageCard', 'portal_manageCard', '电子名片维护', 'url', '/admin/portal/article/manageCard', NULL, 'Y', 'N', 12),
	('portal_statiswork', 'portal_statiswork', '工作量统计', NULL, '/admin/portal/statisWork/statisWork', NULL, 'Y', 'N', 11),
	('sys_auth', 'sys_auth', '权限管理', 'url', '/admin/sys/manageAuthority', NULL, 'Y', 'Y', 3),
	('sys_dept', 'sys_dept', '部门管理', NULL, '/admin/sys/manageDept', NULL, 'Y', 'N', 10),
	('sys_manageUser', 'sys_manageUser', '用户管理', NULL, '/admin/sys/manageUser', NULL, 'Y', 'N', 4),
	('sys_menu', 'sys_menu', '菜单管理', NULL, '/admin/sys/manageMenu', NULL, 'Y', 'N', 3),
	('sys_module', 'sys_module', '模块管理', 'url', '/admin/sys/manageModules', NULL, 'Y', 'N', 3),
	('sys_resource', 'sys_resource', '资源管理', 'url', '/admin/sys/manageResources', NULL, 'Y', 'Y', 3),
	('sys_role', 'sys_role', '角色管理', 'url', '/admin/sys/manageRole', NULL, 'Y', 'Y', 3),
	('TZTG_TZGL', 'TZTG_TZGL', '通知通告_通知管理', NULL, '/admin/xzjx/gztz/gztz', NULL, 'Y', 'N', 8);
/*!40000 ALTER TABLE `sys_resources` ENABLE KEYS */;


-- 导出  表 portal.sys_roles 结构
DROP TABLE IF EXISTS `sys_roles`;
CREATE TABLE IF NOT EXISTS `sys_roles` (
  `ROLE_ID` varchar(200) NOT NULL,
  `ROLE_NAME` varchar(500) DEFAULT NULL,
  `ROLE_DESC` varchar(500) DEFAULT NULL,
  `ENABLED` char(1) DEFAULT 'Y',
  `ISSYS` char(1) DEFAULT 'N',
  `MODULE` varchar(4) DEFAULT NULL,
  `ID` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='SYS_ROLES';

-- 正在导出表  portal.sys_roles 的数据：~3 rows (大约)
DELETE FROM `sys_roles`;
/*!40000 ALTER TABLE `sys_roles` DISABLE KEYS */;
INSERT INTO `sys_roles` (`ROLE_ID`, `ROLE_NAME`, `ROLE_DESC`, `ENABLED`, `ISSYS`, `MODULE`, `ID`) VALUES
	('ROLE_ADMIN_USER', 'ROLE_ADMIN_USER', '用户管理', 'Y', 'N', NULL, NULL),
	('ROLE_LOGIN', 'ROLE_LOGIN', '登录', 'Y', 'N', '01', NULL),
	('ROLE_PLATFORMADMIN1', 'ROLE_PLATFORMADMIN1', '管理员', 'Y', 'N', '01', NULL);
/*!40000 ALTER TABLE `sys_roles` ENABLE KEYS */;


-- 导出  表 portal.sys_roles_authorities 结构
DROP TABLE IF EXISTS `sys_roles_authorities`;
CREATE TABLE IF NOT EXISTS `sys_roles_authorities` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_ID` varchar(200) DEFAULT NULL,
  `AUTHORITY_ID` varchar(200) DEFAULT NULL,
  `ENABLED` char(1) DEFAULT 'Y',
  PRIMARY KEY (`ID`),
  KEY `ROLE_ID` (`ROLE_ID`),
  KEY `AUTHORITY_ID` (`AUTHORITY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=586 DEFAULT CHARSET=utf8 COMMENT='SYS_ROLES_AUTHORITIES';

-- 正在导出表  portal.sys_roles_authorities 的数据：~17 rows (大约)
DELETE FROM `sys_roles_authorities`;
/*!40000 ALTER TABLE `sys_roles_authorities` DISABLE KEYS */;
INSERT INTO `sys_roles_authorities` (`ID`, `ROLE_ID`, `AUTHORITY_ID`, `ENABLED`) VALUES
	(222, 'ROLE_LOGIN', 'AUTH_LOGIN', 'Y'),
	(570, 'ROLE_ADMIN_USER', 'AUTH_MANAGEUSER', 'Y'),
	(571, 'ROLE_PLATFORMADMIN1', 'AUTH_LOGIN', 'Y'),
	(572, 'ROLE_PLATFORMADMIN1', 'AUTH_USER', 'Y'),
	(573, 'ROLE_PLATFORMADMIN1', 'AUTH_SYS_AUTH', 'Y'),
	(574, 'ROLE_PLATFORMADMIN1', 'AUTH_SYS_MENU', 'Y'),
	(575, 'ROLE_PLATFORMADMIN1', 'AUTH_SYS_MODULE', 'Y'),
	(576, 'ROLE_PLATFORMADMIN1', 'AUTH_SYS_RESOURCE', 'Y'),
	(577, 'ROLE_PLATFORMADMIN1', 'AUTH_SYS_ROLE', 'Y'),
	(578, 'ROLE_PLATFORMADMIN1', 'AUTH_MANAGEUSER', 'Y'),
	(579, 'ROLE_PLATFORMADMIN1', 'AUTH_PORTAL_COMMON_CONTENT', 'Y'),
	(580, 'ROLE_PLATFORMADMIN1', 'AUTH_INFO_MANAGE', 'Y'),
	(581, 'ROLE_PLATFORMADMIN1', 'AUTH_TZTG_TZGL', 'Y'),
	(582, 'ROLE_PLATFORMADMIN1', 'AUTH_PORTAL_CATAGORY', 'Y'),
	(583, 'ROLE_PLATFORMADMIN1', 'AUTH_SYS_DEPT', 'Y'),
	(584, 'ROLE_PLATFORMADMIN1', 'AUTH_PORTAL_STATISWORK', 'Y'),
	(585, 'ROLE_PLATFORMADMIN1', 'AUTH_PORTAL_COMMON_CONTENT_CARD', 'Y');
/*!40000 ALTER TABLE `sys_roles_authorities` ENABLE KEYS */;


-- 导出  表 portal.sys_role_app 结构
DROP TABLE IF EXISTS `sys_role_app`;
CREATE TABLE IF NOT EXISTS `sys_role_app` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `APP_ID` int(11) NOT NULL COMMENT 'Ӧ',
  `APP_TABLE` varchar(50) DEFAULT NULL COMMENT 'ĳ������Ӧ�õı���',
  `ROLE_ID` varchar(100) NOT NULL COMMENT '��Ӧ��ɫ���id',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='SYS_ROLE_APP';

-- 正在导出表  portal.sys_role_app 的数据：~3 rows (大约)
DELETE FROM `sys_role_app`;
/*!40000 ALTER TABLE `sys_role_app` DISABLE KEYS */;
INSERT INTO `sys_role_app` (`ID`, `APP_ID`, `APP_TABLE`, `ROLE_ID`) VALUES
	(1, 5, NULL, 'ROLE_PLATFORMADMIN1'),
	(2, 2, NULL, 'ROLE_PLATFORMADMIN1'),
	(3, 6, NULL, 'ROLE_PLATFORMADMIN1');
/*!40000 ALTER TABLE `sys_role_app` ENABLE KEYS */;


-- 导出  表 portal.sys_users 结构
DROP TABLE IF EXISTS `sys_users`;
CREATE TABLE IF NOT EXISTS `sys_users` (
  `USER_ID` varchar(32) DEFAULT NULL,
  `USER_ACCOUNT` varchar(30) NOT NULL,
  `USER_NAME` varchar(40) DEFAULT NULL,
  `USER_PASSWORD` varchar(100) DEFAULT NULL,
  `USER_DESC` varchar(100) DEFAULT NULL,
  `ENABLED` char(1) DEFAULT 'Y',
  `ISSYS` char(1) DEFAULT 'N',
  `USER_DEPT` varchar(20) DEFAULT NULL,
  `USER_DUTY` varchar(10) DEFAULT NULL,
  `SUB_SYSTEM` varchar(30) DEFAULT NULL,
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ISONLINE` varchar(30) DEFAULT 'NO' COMMENT '�Ƿ����ߣ�YES��NO',
  `LAST_LOGIN_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '����¼ʱ��',
  `LAST_LOGIN_IP` varchar(20) DEFAULT NULL COMMENT '����¼IP��ַ',
  `USER_MOBILE` varchar(20) DEFAULT NULL COMMENT '�ֻ�����',
  `USER_DESKTOP` varchar(500) DEFAULT NULL COMMENT '����',
  `USER_ORDER` decimal(8,0) DEFAULT NULL COMMENT '����',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USER_ACCOUNT` (`USER_ACCOUNT`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='SYS_USERS';

-- 正在导出表  portal.sys_users 的数据：~3 rows (大约)
DELETE FROM `sys_users`;
/*!40000 ALTER TABLE `sys_users` DISABLE KEYS */;
INSERT INTO `sys_users` (`USER_ID`, `USER_ACCOUNT`, `USER_NAME`, `USER_PASSWORD`, `USER_DESC`, `ENABLED`, `ISSYS`, `USER_DEPT`, `USER_DUTY`, `SUB_SYSTEM`, `ID`, `ISONLINE`, `LAST_LOGIN_TIME`, `LAST_LOGIN_IP`, `USER_MOBILE`, `USER_DESKTOP`, `USER_ORDER`) VALUES
	('admin', 'admin', '系统管理员', '202cb962ac59075b964b07152d234b70', '系统管理员', 'Y', 'N', '13706000000', '', '', 1, 'YES', '2016-05-14 11:26:10', '', '', '', 0),
	('13706120101', 'shuidao', '测试', '202cb962ac59075b964b07152d234b70', NULL, '0', NULL, '13706120100', NULL, NULL, 2, 'NO', '2015-02-06 14:57:27', NULL, NULL, NULL, NULL),
	('13706120001', 'mpadmin', '牟平国联元', '202cb962ac59075b964b07152d234b70', NULL, '0', NULL, '13706120000', NULL, NULL, 3, 'YES', '2015-02-06 15:07:16', NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `sys_users` ENABLE KEYS */;


-- 导出  表 portal.sys_users_roles 结构
DROP TABLE IF EXISTS `sys_users_roles`;
CREATE TABLE IF NOT EXISTS `sys_users_roles` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` varchar(32) DEFAULT NULL,
  `ROLE_ID` varchar(200) DEFAULT NULL,
  `ENABLED` char(1) DEFAULT 'Y',
  PRIMARY KEY (`ID`),
  KEY `ROLE_ID` (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='SYS_USERS_ROLES';

-- 正在导出表  portal.sys_users_roles 的数据：~5 rows (大约)
DELETE FROM `sys_users_roles`;
/*!40000 ALTER TABLE `sys_users_roles` DISABLE KEYS */;
INSERT INTO `sys_users_roles` (`ID`, `USER_ID`, `ROLE_ID`, `ENABLED`) VALUES
	(1, 'admin', 'ROLE_LOGIN', 'Y'),
	(2, 'admin', 'ROLE_PLATFORMADMIN1', 'Y'),
	(3, '13706120001', 'ROLE_PLATFORMADMIN1', 'Y'),
	(4, '13706120001', 'ROLE_LOGIN', 'Y'),
	(5, '13706120001', 'ROLE_ADMIN_USER', 'Y');
/*!40000 ALTER TABLE `sys_users_roles` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
