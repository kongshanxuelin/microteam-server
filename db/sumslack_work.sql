/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.242
Source Server Version : 50513
Source Host           : 192.168.1.242:3306
Source Database       : sumslack_work

Target Server Type    : MYSQL
Target Server Version : 50513
File Encoding         : 65001

Date: 2017-06-16 16:19:57
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `m_work_anno`
-- ----------------------------
DROP TABLE IF EXISTS `m_work_anno`;
CREATE TABLE `m_work_anno` (
  `ID` varchar(32) NOT NULL DEFAULT '',
  `title` varchar(512) DEFAULT NULL,
  `content` text,
  `create_uid` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_uid` varchar(32) DEFAULT NULL,
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `company_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_work_anno
-- ----------------------------
INSERT INTO m_work_anno VALUES ('l7vq2k7fuo', '阿道夫', '阿斯顿发', '1145', '2017-04-27 17:07:50', '1145', '2017-04-27 17:05:12', '71');

-- ----------------------------
-- Table structure for `m_work_company_tmpl`
-- ----------------------------
DROP TABLE IF EXISTS `m_work_company_tmpl`;
CREATE TABLE `m_work_company_tmpl` (
  `id` varchar(32) NOT NULL,
  `company_id` int(11) DEFAULT NULL,
  `tmpl_id` varchar(32) DEFAULT NULL,
  `tmpl_cateid` varchar(512) DEFAULT NULL,
  `sts` char(1) DEFAULT NULL,
  `ord_` int(11) DEFAULT NULL,
  `create_uid` varchar(64) DEFAULT NULL,
  `modify_uid` varchar(64) DEFAULT NULL,
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_work_company_tmpl
-- ----------------------------
INSERT INTO m_work_company_tmpl VALUES ('1', '71', '1', 'task', '1', null, null, null, '2017-04-19 14:29:29', null);

-- ----------------------------
-- Table structure for `m_work_process`
-- ----------------------------
DROP TABLE IF EXISTS `m_work_process`;
CREATE TABLE `m_work_process` (
  `id` varchar(32) NOT NULL,
  `title` varchar(512) DEFAULT NULL,
  `content` text,
  `delflag` char(1) DEFAULT NULL,
  `create_uid` varchar(64) DEFAULT NULL,
  `modify_uid` varchar(64) DEFAULT NULL,
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ord_` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `tmpl_id` varchar(32) DEFAULT NULL,
  `company_id` varchar(32) DEFAULT NULL,
  `audit_level` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_work_process
-- ----------------------------
INSERT INTO m_work_process VALUES ('1', '请假流程', null, '0', null, null, '2017-04-26 10:57:00', null, null, '2', '55', '3');
INSERT INTO m_work_process VALUES ('2', '加班流程', null, '0', null, null, '2017-05-16 17:44:15', null, null, '3', '55', '2');
INSERT INTO m_work_process VALUES ('3', '购书申请', null, '0', null, null, '2017-05-16 17:52:08', null, null, 'lhfhxlqi9s', '55', '2');

-- ----------------------------
-- Table structure for `m_work_process_audit_define`
-- ----------------------------
DROP TABLE IF EXISTS `m_work_process_audit_define`;
CREATE TABLE `m_work_process_audit_define` (
  `id` varchar(32) NOT NULL,
  `title` varchar(32) DEFAULT NULL,
  `delflag` char(1) DEFAULT NULL,
  `audit_level` int(11) DEFAULT NULL,
  `create_uid` varchar(64) DEFAULT NULL,
  `modify_uid` varchar(64) DEFAULT NULL,
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime DEFAULT NULL,
  `audit_level_rel` varchar(32) DEFAULT NULL,
  `process_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_work_process_audit_define
-- ----------------------------
INSERT INTO m_work_process_audit_define VALUES ('a', '上级审批', '0', '1', '54', '54', '2017-05-15 15:57:26', null, 'OR', '1');
INSERT INTO m_work_process_audit_define VALUES ('b', 'CTO审批', '0', '2', '54', '54', '2017-05-15 15:57:26', null, 'AND', '1');
INSERT INTO m_work_process_audit_define VALUES ('c', 'HR审批', '0', '3', '54', '54', '2017-05-15 15:57:33', null, 'AND', '1');
INSERT INTO m_work_process_audit_define VALUES ('lhcb6myakg', 'team leader审批', '0', '1', '1145', '1145', '2017-05-16 13:34:49', '2017-05-16 13:38:04', 'OR', '2');
INSERT INTO m_work_process_audit_define VALUES ('lhcbhqs8hs', '人事备案', '0', '2', '1145', '1145', '2017-05-16 13:35:30', '2017-05-16 13:38:45', 'OR', '2');
INSERT INTO m_work_process_audit_define VALUES ('lhfj2bxwxs', 'Team Leader审批', '0', '1', '1145', '1145', '2017-05-16 17:51:24', '2017-05-16 17:54:40', 'OR', '3');
INSERT INTO m_work_process_audit_define VALUES ('lhfjd75fcw', '购书人审批', '0', '2', '1145', '1145', '2017-05-16 17:52:04', '2017-05-16 17:55:20', 'OR', '3');

-- ----------------------------
-- Table structure for `m_work_process_audit_define_uid`
-- ----------------------------
DROP TABLE IF EXISTS `m_work_process_audit_define_uid`;
CREATE TABLE `m_work_process_audit_define_uid` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `audit_id` varchar(32) DEFAULT NULL,
  `company_id` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_uid` varchar(32) DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modify_uid` varchar(32) DEFAULT NULL,
  `audit_uid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_work_process_audit_define_uid
-- ----------------------------
INSERT INTO m_work_process_audit_define_uid VALUES ('1', 'a', '55', '2017-04-26 10:03:04', '1145', '2017-05-15 15:57:53', '1145', '54');
INSERT INTO m_work_process_audit_define_uid VALUES ('2', 'b', '55', '2017-04-26 10:03:04', '1145', '2017-05-15 15:57:54', '1145', '54');
INSERT INTO m_work_process_audit_define_uid VALUES ('3', 'c', '55', '2017-04-26 10:03:04', '1145', '2017-05-15 15:57:54', '1145', '54');
INSERT INTO m_work_process_audit_define_uid VALUES ('lhcb6myzuo', 'lhcb6myakg', '72', '2017-05-16 13:38:04', '1145', '2017-05-16 13:34:49', '1145', '54');
INSERT INTO m_work_process_audit_define_uid VALUES ('lhcbhqsl4w', 'lhcbhqs8hs', '72', '2017-05-16 13:38:45', '1145', '2017-05-16 13:35:30', '1145', '54');
INSERT INTO m_work_process_audit_define_uid VALUES ('lhfj2bym80', 'lhfj2bxwxs', '72', '2017-05-16 17:54:40', '1145', '2017-05-16 17:51:24', '1145', '54');
INSERT INTO m_work_process_audit_define_uid VALUES ('lhfjd764n4', 'lhfjd75fcw', '72', '2017-05-16 17:55:20', '1145', '2017-05-16 17:52:04', '1145', '54');

-- ----------------------------
-- Table structure for `m_work_process_instance`
-- ----------------------------
DROP TABLE IF EXISTS `m_work_process_instance`;
CREATE TABLE `m_work_process_instance` (
  `id` varchar(32) NOT NULL,
  `process_define_id` varchar(32) DEFAULT NULL,
  `delflag` char(1) DEFAULT NULL,
  `create_uid` varchar(64) DEFAULT NULL,
  `modify_uid` varchar(64) DEFAULT NULL,
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime DEFAULT NULL,
  `sts` varchar(64) DEFAULT NULL,
  `audit_level` int(11) DEFAULT NULL,
  `tmpl_id_id` varchar(32) DEFAULT NULL,
  `isfinish` char(1) DEFAULT 'F',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_work_process_instance
-- ----------------------------
INSERT INTO m_work_process_instance VALUES ('llfkkck9a8', '1', '0', '54', '54', '2017-05-24 17:13:27', '2017-05-24 17:17:07', 'F', '4', 'llfkkck9a9', 'F');
INSERT INTO m_work_process_instance VALUES ('llfmius3r4', '1', '0', '54', '54', '2017-05-24 17:17:30', '2017-05-24 17:21:27', 'I', '1', 'llfmius3r5', 'F');
INSERT INTO m_work_process_instance VALUES ('llfnb84l4w', '2', '0', '54', '54', '2017-05-24 17:19:15', '2017-05-24 17:23:12', 'I', '1', 'llfnb84l4x', 'F');
INSERT INTO m_work_process_instance VALUES ('llfndxi9z4', '3', '0', '54', '54', '2017-05-24 17:19:25', '2017-05-24 17:23:22', 'I', '1', 'llfndxi9z5', 'F');
INSERT INTO m_work_process_instance VALUES ('llxx7rn6kg', '1', '0', '54', '54', '2017-05-25 17:37:10', '2017-05-25 17:40:01', 'F', '4', 'llxx7rn6kh', 'F');
INSERT INTO m_work_process_instance VALUES ('llxz1n2fwg', '1', '0', '54', '54', '2017-05-25 17:39:28', '2017-05-25 17:44:04', 'I', '1', 'llxz1n2fwh', 'F');
INSERT INTO m_work_process_instance VALUES ('llxzj18u80', '2', '0', '54', '54', '2017-05-25 17:40:40', '2017-05-25 17:45:09', 'F', '3', 'llxzj18u81', 'F');

-- ----------------------------
-- Table structure for `m_work_process_instance_tasks`
-- ----------------------------
DROP TABLE IF EXISTS `m_work_process_instance_tasks`;
CREATE TABLE `m_work_process_instance_tasks` (
  `id` varchar(32) NOT NULL,
  `process_define_id` varchar(32) DEFAULT NULL,
  `delflag` char(1) DEFAULT NULL,
  `create_uid` varchar(64) DEFAULT NULL,
  `modify_uid` varchar(64) DEFAULT NULL,
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime DEFAULT NULL,
  `process_instance_id` varchar(32) DEFAULT NULL,
  `audit_uid` varchar(64) DEFAULT NULL,
  `audit_sts` char(1) DEFAULT NULL,
  `audit_memo` text,
  `audit_define_id` varchar(32) DEFAULT NULL,
  `audit_level` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_work_process_instance_tasks
-- ----------------------------
INSERT INTO m_work_process_instance_tasks VALUES ('llfkms15vk', '1', '0', '54', '54', '2017-05-24 17:13:19', '2017-05-24 17:17:16', 'llfkkck9a8', '54', '1', '', 'a', '1');
INSERT INTO m_work_process_instance_tasks VALUES ('llfko14lq8', '1', '0', '54', '54', '2017-05-24 17:13:24', '2017-05-24 17:17:21', 'llfkkck9a8', '54', '1', '', 'b', '2');
INSERT INTO m_work_process_instance_tasks VALUES ('llfkotj75s', '1', '0', '54', '54', '2017-05-24 17:13:27', '2017-05-24 17:17:24', 'llfkkck9a8', '54', '1', '', 'c', '3');
INSERT INTO m_work_process_instance_tasks VALUES ('llxxuk08ow', '1', '0', '54', '54', '2017-05-25 17:36:49', '2017-05-25 17:41:25', 'llxx7rn6kg', '54', '1', '', 'a', '1');
INSERT INTO m_work_process_instance_tasks VALUES ('llxxxtk54w', '1', '0', '54', '54', '2017-05-25 17:37:01', '2017-05-25 17:41:37', 'llxx7rn6kg', '54', '1', '', 'b', '2');
INSERT INTO m_work_process_instance_tasks VALUES ('llxy072tc0', '1', '0', '54', '54', '2017-05-25 17:37:10', '2017-05-25 17:41:46', 'llxx7rn6kg', '54', '1', '', 'c', '3');
INSERT INTO m_work_process_instance_tasks VALUES ('llxzkdxuyo', '2', '0', '54', '54', '2017-05-25 17:40:37', '2017-05-25 17:45:14', 'llxzj18u80', '54', '1', '', 'lhcb6myakg', '1');
INSERT INTO m_work_process_instance_tasks VALUES ('llxzl2hqtc', '2', '0', '54', '54', '2017-05-25 17:40:40', '2017-05-25 17:45:16', 'llxzj18u80', '54', '1', '', 'lhcbhqs8hs', '2');

-- ----------------------------
-- Table structure for `m_work_projects`
-- ----------------------------
DROP TABLE IF EXISTS `m_work_projects`;
CREATE TABLE `m_work_projects` (
  `id` varchar(32) NOT NULL,
  `title` varchar(512) DEFAULT NULL,
  `content` text,
  `delflag` char(1) DEFAULT NULL,
  `create_uid` varchar(64) DEFAULT NULL,
  `modify_uid` varchar(64) DEFAULT NULL,
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ord_` int(11) DEFAULT NULL,
  `avator_url` varchar(128) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `owner_uid` varchar(64) DEFAULT NULL,
  `company_id` varchar(64) DEFAULT NULL,
  `sts` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_work_projects
-- ----------------------------
INSERT INTO m_work_projects VALUES ('1', '项目1', '项目1描述', '0', '1145', '1145', '2017-04-19 14:14:08', '1', null, '2017-04-19 14:15:59', '2017-04-19 14:16:02', '2017-04-26 14:16:04', '1145', '71', '0');

-- ----------------------------
-- Table structure for `m_work_project_tasks`
-- ----------------------------
DROP TABLE IF EXISTS `m_work_project_tasks`;
CREATE TABLE `m_work_project_tasks` (
  `id` varchar(32) NOT NULL,
  `project_id` varchar(32) DEFAULT NULL,
  `tmpl_id` varchar(32) DEFAULT NULL,
  `tmpl_id_id` varchar(32) DEFAULT NULL,
  `sts` char(1) DEFAULT NULL,
  `ord_` int(11) DEFAULT NULL,
  `content` text,
  `admin_uid` varchar(64) DEFAULT NULL,
  `create_uid` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_uid` varchar(64) DEFAULT NULL,
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `task_auditer` varchar(64) DEFAULT NULL,
  `time_start` datetime DEFAULT NULL,
  `time_end` datetime DEFAULT NULL,
  `task_joiner` text,
  `delflag` char(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_work_project_tasks
-- ----------------------------
INSERT INTO m_work_project_tasks VALUES ('llezlg0glc', 'lhvxsn981s', '1', 'llezlg0t8g', '1', '1', '1111', '1977', '54', '2017-05-24 16:30:41', '54', '2017-05-24 16:27:09', '1900', null, '2017-05-24 00:00:00', '1209', '0');

-- ----------------------------
-- Table structure for `m_work_project_tasks_atta`
-- ----------------------------
DROP TABLE IF EXISTS `m_work_project_tasks_atta`;
CREATE TABLE `m_work_project_tasks_atta` (
  `id` varchar(32) NOT NULL,
  `task_id` varchar(32) DEFAULT NULL,
  `ord_` int(11) DEFAULT NULL,
  `create_uid` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_uid` varchar(64) DEFAULT NULL,
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `atta_type` varchar(512) DEFAULT NULL,
  `atta_path` varchar(512) DEFAULT NULL,
  `atta_spath` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_work_project_tasks_atta
-- ----------------------------

-- ----------------------------
-- Table structure for `m_work_project_tasks_comments`
-- ----------------------------
DROP TABLE IF EXISTS `m_work_project_tasks_comments`;
CREATE TABLE `m_work_project_tasks_comments` (
  `id` varchar(32) NOT NULL,
  `task_id` varchar(32) DEFAULT NULL,
  `content` text,
  `create_uid` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_uid` varchar(64) DEFAULT NULL,
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `stype` char(1) DEFAULT NULL COMMENT '语音/图片/普通文本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_work_project_tasks_comments
-- ----------------------------

-- ----------------------------
-- Table structure for `m_work_project_users`
-- ----------------------------
DROP TABLE IF EXISTS `m_work_project_users`;
CREATE TABLE `m_work_project_users` (
  `id` varchar(32) NOT NULL,
  `project_id` varchar(32) DEFAULT NULL,
  `role_id` varchar(32) DEFAULT NULL,
  `uid` varchar(64) DEFAULT NULL,
  `create_uid` varchar(64) DEFAULT NULL,
  `modify_uid` varchar(64) DEFAULT NULL,
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_work_project_users
-- ----------------------------

-- ----------------------------
-- Table structure for `m_work_signin`
-- ----------------------------
DROP TABLE IF EXISTS `m_work_signin`;
CREATE TABLE `m_work_signin` (
  `id` varchar(32) NOT NULL,
  `company_id` varchar(32) DEFAULT NULL,
  `workon_time` time DEFAULT NULL,
  `workoff_time` time DEFAULT NULL,
  `create_uid` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_uid` varchar(64) DEFAULT NULL,
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `work_week` varchar(512) DEFAULT NULL,
  `let` decimal(15,10) DEFAULT NULL,
  `lot` decimal(15,10) DEFAULT NULL,
  `error_scope` decimal(6,0) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_work_signin
-- ----------------------------

-- ----------------------------
-- Table structure for `m_work_signin_record`
-- ----------------------------
DROP TABLE IF EXISTS `m_work_signin_record`;
CREATE TABLE `m_work_signin_record` (
  `id` varchar(32) NOT NULL,
  `company_id` varchar(32) DEFAULT NULL,
  `uid` varchar(32) DEFAULT NULL,
  `create_uid` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_uid` varchar(64) DEFAULT NULL,
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `direct_` varchar(512) DEFAULT NULL,
  `ip` varchar(512) DEFAULT NULL,
  `info` text,
  `signin_settings_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_work_signin_record
-- ----------------------------

-- ----------------------------
-- Table structure for `m_work_tmpl`
-- ----------------------------
DROP TABLE IF EXISTS `m_work_tmpl`;
CREATE TABLE `m_work_tmpl` (
  `id` varchar(32) NOT NULL,
  `tmpl_cateid` varchar(32) DEFAULT NULL,
  `title` varchar(512) DEFAULT NULL,
  `tmpl_content` text,
  `sts` char(1) DEFAULT NULL,
  `ord_` int(11) DEFAULT NULL,
  `create_uid` varchar(64) DEFAULT NULL,
  `modify_uid` varchar(64) DEFAULT NULL,
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime DEFAULT NULL,
  `delflag` char(1) DEFAULT '0',
  `pid` varchar(32) DEFAULT NULL,
  `desc_header` text,
  `desc_footer` text,
  `form_js` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `m_work_tmpl_category`
-- ----------------------------
DROP TABLE IF EXISTS `m_work_tmpl_category`;
CREATE TABLE `m_work_tmpl_category` (
  `id` varchar(32) NOT NULL,
  `title` varchar(512) DEFAULT NULL,
  `content` text,
  `sts` char(1) DEFAULT NULL,
  `ord_` int(11) DEFAULT NULL,
  `create_uid` varchar(64) DEFAULT NULL,
  `modify_uid` varchar(64) DEFAULT NULL,
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime DEFAULT NULL,
  `parent_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_work_tmpl_category
-- ----------------------------
INSERT INTO m_work_tmpl_category VALUES ('crm', 'CRM模板', null, '1', null, null, null, '2017-04-19 15:56:33', null, null);
INSERT INTO m_work_tmpl_category VALUES ('log', '日志模板', null, '1', null, null, null, '2017-05-22 11:45:31', null, null);
INSERT INTO m_work_tmpl_category VALUES ('process', '流程模板', null, '1', null, null, null, '2017-04-19 14:27:25', null, null);
INSERT INTO m_work_tmpl_category VALUES ('task', '任务模板', null, '1', null, null, null, '2017-04-19 14:27:08', null, null);

-- ----------------------------
-- Table structure for `m_work_tmpl_data_scope`
-- ----------------------------
DROP TABLE IF EXISTS `m_work_tmpl_data_scope`;
CREATE TABLE `m_work_tmpl_data_scope` (
  `id` varchar(255) NOT NULL DEFAULT '',
  `tmpl_id` varchar(255) DEFAULT NULL,
  `uid` varchar(32) DEFAULT NULL,
  `uid_view` varchar(32) DEFAULT NULL,
  `scope` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_work_tmpl_data_scope
-- ----------------------------
INSERT INTO m_work_tmpl_data_scope VALUES ('1', 'lowwr18kqo', '1803', null, 'all');

-- ----------------------------
-- Table structure for `m_work_tmpl_fields`
-- ----------------------------
DROP TABLE IF EXISTS `m_work_tmpl_fields`;
CREATE TABLE `m_work_tmpl_fields` (
  `id` varchar(32) NOT NULL,
  `tmpl_id` varchar(32) DEFAULT NULL,
  `title` varchar(512) DEFAULT NULL,
  `label_suf` varchar(512) DEFAULT NULL,
  `sts` char(1) DEFAULT NULL,
  `ord_` int(11) DEFAULT NULL,
  `create_uid` varchar(64) DEFAULT NULL,
  `modify_uid` varchar(64) DEFAULT NULL,
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime DEFAULT NULL,
  `ui_component` varchar(512) DEFAULT NULL,
  `ui_defaultvalue` text,
  `ui_maxlen` int(11) DEFAULT NULL,
  `ui_minlen` int(11) DEFAULT NULL,
  `ui_isreq` char(1) DEFAULT NULL,
  `ui_other` text,
  `ui_showlist` char(1) DEFAULT '0',
  `ui_attrs` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `m_work_tmpl_fields_value`
-- ----------------------------
DROP TABLE IF EXISTS `m_work_tmpl_fields_value`;
CREATE TABLE `m_work_tmpl_fields_value` (
  `id` varchar(32) NOT NULL,
  `tmpl_id` varchar(32) DEFAULT NULL,
  `field_id` varchar(32) DEFAULT NULL,
  `field_value` text,
  `delflag` char(1) DEFAULT NULL,
  `tmpl_id_id` varchar(32) DEFAULT NULL,
  `field_value_display` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `m_work_tmpl_records`
-- ----------------------------
DROP TABLE IF EXISTS `m_work_tmpl_records`;
CREATE TABLE `m_work_tmpl_records` (
  `id` varchar(32) NOT NULL,
  `tmpl_id` varchar(32) DEFAULT NULL,
  `tmpl_cateid` varchar(512) DEFAULT NULL,
  `deflag` char(1) DEFAULT NULL,
  `create_uid` varchar(64) DEFAULT NULL,
  `modify_uid` varchar(64) DEFAULT NULL,
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime DEFAULT NULL,
  `ip` varchar(512) DEFAULT NULL,
  `browser` text,
  `rec_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `teams`
-- ----------------------------
DROP TABLE IF EXISTS `teams`;
CREATE TABLE `teams` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `title` varchar(512) DEFAULT NULL,
  `avatar` varchar(512) DEFAULT NULL,
  `create_uid` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `delflag` char(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teams
-- ----------------------------
INSERT INTO teams VALUES ('l8dhft9ngg', '测试222', null, 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', '2017-04-28 16:43:36', '0');
INSERT INTO teams VALUES ('l8dhm9ahhc', '啛啛喳喳333', null, 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', '2017-04-28 16:44:00', '0');
INSERT INTO teams VALUES ('l8diqjiby8', 'sdf', null, '1145', '2017-04-28 16:46:29', '0');

-- ----------------------------
-- Table structure for `teams_user`
-- ----------------------------
DROP TABLE IF EXISTS `teams_user`;
CREATE TABLE `teams_user` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `team_id` varchar(32) DEFAULT NULL,
  `role` varchar(32) DEFAULT NULL,
  `uid` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teams_user
-- ----------------------------
INSERT INTO teams_user VALUES ('l8dhftacqo', 'l8dhft9ngg', 'owner', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI');
INSERT INTO teams_user VALUES ('l8dhm9b6rk', 'l8dhm9ahhc', 'owner', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI');
INSERT INTO teams_user VALUES ('l8diqjiolc', 'l8diqjiby8', 'owner', '1145');

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `uid` varchar(64) NOT NULL DEFAULT '',
  `username` varchar(64) DEFAULT NULL,
  `nick` varchar(512) DEFAULT NULL,
  `source` char(1) DEFAULT NULL,
  `company_id` varchar(32) DEFAULT NULL,
  `company_name` varchar(512) DEFAULT NULL,
  `token` varchar(32) DEFAULT NULL,
  `pwd` varchar(32) DEFAULT NULL,
  `avatar` varchar(1024) DEFAULT NULL,
  `dept` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO users VALUES ('52', 'test1', 'Miyoki', 's', '71', null, 'luz4elb18g', '123456', '', null);
INSERT INTO users VALUES ('53', 'test2', 'i心跳', 's', '71', null, 'lvg75yuww0', '123456', '', null);
-- ----------------------------
-- Table structure for `users_data_purview`
-- ----------------------------
DROP TABLE IF EXISTS `users_data_purview`;
CREATE TABLE `users_data_purview` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `uid` varchar(64) DEFAULT NULL,
  `touid` varchar(64) DEFAULT NULL,
  `isview` char(1) DEFAULT NULL,
  `isedit` char(1) DEFAULT NULL,
  `isremove` char(1) DEFAULT NULL,
  `tmplid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users_data_purview
-- ----------------------------
INSERT INTO users_data_purview VALUES ('lvgenbdp8g', '56', '53', 'Y', 'Y', 'N', 'luya1t258g');
INSERT INTO users_data_purview VALUES ('lvgfcs0rnk', '56', '54', 'Y', 'Y', 'N', 'luya1t258g');
INSERT INTO users_data_purview VALUES ('lvggp9fy80', '56', '55', 'Y', 'Y', 'N', 'luya1t258g');
INSERT INTO users_data_purview VALUES ('lvggs9v5s0', '55', '53', 'Y', 'Y', 'N', 'luya1t258g');

-- ----------------------------
-- Table structure for `users_tmpl_purview`
-- ----------------------------
DROP TABLE IF EXISTS `users_tmpl_purview`;
CREATE TABLE `users_tmpl_purview` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `uid` varchar(64) DEFAULT NULL,
  `tmpl_id` varchar(32) DEFAULT NULL,
  `isedit` char(1) DEFAULT 'N',
  `isview` char(1) DEFAULT 'Y',
  `isremove` char(1) DEFAULT 'N',
  `isowner` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

