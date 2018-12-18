/*
 Navicat Premium Data Transfer

 Source Server         : www.sumslack.com
 Source Server Type    : MySQL
 Source Server Version : 50628
 Source Host           : www.sumslack.com
 Source Database       : sumslack_work

 Target Server Type    : MySQL
 Target Server Version : 50628
 File Encoding         : utf-8

 Date: 12/18/2018 15:29:06 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `ai_tencent_libs`
-- ----------------------------
DROP TABLE IF EXISTS `ai_tencent_libs`;
CREATE TABLE `ai_tencent_libs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `t` varchar(32) DEFAULT NULL,
  `code` varchar(32) DEFAULT NULL,
  `label` varchar(512) DEFAULT NULL,
  `label2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1445 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `bond`
-- ----------------------------
DROP TABLE IF EXISTS `bond`;
CREATE TABLE `bond` (
  `bondid` varchar(32) DEFAULT NULL,
  `bondkey` varchar(64) NOT NULL,
  `shortname` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`bondkey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `c_learning`
-- ----------------------------
DROP TABLE IF EXISTS `c_learning`;
CREATE TABLE `c_learning` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `t` text,
  `dt` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `yw` text,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `c_learning_rate`
-- ----------------------------
DROP TABLE IF EXISTS `c_learning_rate`;
CREATE TABLE `c_learning_rate` (
  `id` int(11) NOT NULL,
  `num_e` bigint(255) DEFAULT NULL,
  `num_t` bigint(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `m_act`
-- ----------------------------
DROP TABLE IF EXISTS `m_act`;
CREATE TABLE `m_act` (
  `id` varchar(32) NOT NULL,
  `title` varchar(512) DEFAULT NULL,
  `d` text,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `freq_num` int(11) DEFAULT NULL,
  `freq_unit` varchar(32) DEFAULT NULL,
  `is_alarm` char(1) DEFAULT NULL,
  `modifytime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `teamid` varchar(32) DEFAULT NULL,
  `alarm_before_num` int(11) DEFAULT NULL,
  `alarm_before_unit` varchar(32) DEFAULT NULL,
  `cur_start_date` datetime DEFAULT NULL,
  `cur_end_date` datetime DEFAULT NULL,
  `act_time` time DEFAULT NULL,
  `act_addr` varchar(512) DEFAULT NULL,
  `sts` char(1) DEFAULT NULL,
  `min_num` int(11) DEFAULT NULL,
  `cur_sts` char(1) DEFAULT NULL,
  `uid` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `m_act_users`
-- ----------------------------
DROP TABLE IF EXISTS `m_act_users`;
CREATE TABLE `m_act_users` (
  `id` varchar(32) NOT NULL,
  `uid` varchar(64) DEFAULT NULL,
  `act_id` varchar(32) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `modifytime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `sts` char(1) DEFAULT NULL,
  `form_id` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `m_docs`
-- ----------------------------
DROP TABLE IF EXISTS `m_docs`;
CREATE TABLE `m_docs` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `title` varchar(512) DEFAULT NULL,
  `pid` varchar(32) DEFAULT NULL,
  `isfile` char(1) DEFAULT NULL,
  `_ord` int(11) DEFAULT NULL,
  `icon` varchar(512) DEFAULT NULL,
  `teamid` varchar(32) DEFAULT NULL,
  `create_uid` varchar(32) DEFAULT NULL,
  `scope` char(1) DEFAULT NULL,
  `fileid` varchar(32) DEFAULT NULL,
  `content` text,
  `cate` char(1) DEFAULT NULL COMMENT '1:内部文件夹 2：共享文件夹 3：我的文件夹',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `m_docs_share_rel`
-- ----------------------------
DROP TABLE IF EXISTS `m_docs_share_rel`;
CREATE TABLE `m_docs_share_rel` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `doc_id` varchar(32) DEFAULT NULL,
  `share_scope` char(1) DEFAULT NULL,
  `share_scope_id` varchar(32) DEFAULT NULL,
  `_ops` int(11) DEFAULT NULL COMMENT '第一位：可管理 第二位：可查看/上传 第三位：可查看',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `m_logs`
-- ----------------------------
DROP TABLE IF EXISTS `m_logs`;
CREATE TABLE `m_logs` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `uid` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `title` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `params` text COLLATE utf8_unicode_ci,
  `dt` datetime DEFAULT NULL,
  `teamid` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `istop` int(1) DEFAULT NULL,
  `addr` text COLLATE utf8_unicode_ci,
  `scope` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `stype` char(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Table structure for `m_logs_mapping`
-- ----------------------------
DROP TABLE IF EXISTS `m_logs_mapping`;
CREATE TABLE `m_logs_mapping` (
  `uri` varchar(128) NOT NULL DEFAULT '',
  `title` varchar(128) DEFAULT NULL,
  `isshow_team` char(1) DEFAULT NULL,
  PRIMARY KEY (`uri`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `m_quan_comments`
-- ----------------------------
DROP TABLE IF EXISTS `m_quan_comments`;
CREATE TABLE `m_quan_comments` (
  `id` varchar(32) NOT NULL,
  `fid` varchar(32) DEFAULT NULL,
  `comment_content` text COMMENT 'T：文本消息\r\n            P：图片消息',
  `create_time` datetime DEFAULT NULL,
  `create_uid` varchar(32) DEFAULT NULL,
  `ip` varchar(512) DEFAULT NULL,
  `reply_uid` varchar(32) DEFAULT NULL,
  `delflag` char(1) DEFAULT '0',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_nick` varchar(64) DEFAULT NULL,
  `reply_nick` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `m_quan_like`
-- ----------------------------
DROP TABLE IF EXISTS `m_quan_like`;
CREATE TABLE `m_quan_like` (
  `id` varchar(32) NOT NULL,
  `uid` varchar(32) DEFAULT NULL,
  `fid` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_uid` varchar(32) DEFAULT NULL,
  `ip` varchar(1024) DEFAULT NULL,
  `platform_id` varchar(8) DEFAULT NULL,
  `delflag` char(1) DEFAULT '0',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `nick` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `m_webstat_projects`
-- ----------------------------
DROP TABLE IF EXISTS `m_webstat_projects`;
CREATE TABLE `m_webstat_projects` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `prj_id` varchar(32) DEFAULT NULL,
  `prj_title` varchar(512) DEFAULT NULL,
  `dsname` varchar(512) DEFAULT NULL,
  `teamid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `m_work_anno`
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
--  Table structure for `m_work_company_tmpl`
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
--  Table structure for `m_work_note`
-- ----------------------------
DROP TABLE IF EXISTS `m_work_note`;
CREATE TABLE `m_work_note` (
  `id` varchar(32) NOT NULL,
  `company_id` varchar(32) DEFAULT NULL,
  `title` varchar(2048) DEFAULT NULL,
  `create_uid` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_uid` varchar(64) DEFAULT NULL,
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `content` text,
  `project_name` varchar(512) DEFAULT NULL,
  `starttime` time DEFAULT NULL,
  `endtime` time DEFAULT NULL,
  `project_id` varchar(32) DEFAULT NULL,
  `workdate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `m_work_process`
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
--  Table structure for `m_work_process_audit_define`
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
--  Table structure for `m_work_process_audit_define_uid`
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
--  Table structure for `m_work_process_instance`
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
--  Table structure for `m_work_process_instance_tasks`
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
--  Table structure for `m_work_project_tasks`
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
--  Table structure for `m_work_project_tasks_atta`
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
--  Table structure for `m_work_project_tasks_comments`
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
--  Table structure for `m_work_project_users`
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
--  Table structure for `m_work_projects`
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
--  Table structure for `m_work_projects_zhang`
-- ----------------------------
DROP TABLE IF EXISTS `m_work_projects_zhang`;
CREATE TABLE `m_work_projects_zhang` (
  `id` varchar(32) NOT NULL,
  `prj_id` varchar(32) DEFAULT NULL,
  `d` tinyint(2) DEFAULT NULL,
  `cate_id` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `dt` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `je` decimal(12,2) DEFAULT NULL,
  `fzr` varchar(32) DEFAULT NULL,
  `joiner` text,
  `sts` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `m_work_projects_zhang_cate`
-- ----------------------------
DROP TABLE IF EXISTS `m_work_projects_zhang_cate`;
CREATE TABLE `m_work_projects_zhang_cate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `dt` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `d` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `m_work_signin`
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
--  Table structure for `m_work_signin_record`
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
--  Table structure for `m_work_tmpl`
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
  `view_page` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `m_work_tmpl_category`
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
--  Table structure for `m_work_tmpl_data_scope`
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
--  Table structure for `m_work_tmpl_fields`
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
--  Table structure for `m_work_tmpl_fields_value`
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
  `dt` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `m_work_tmpl_records`
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
  `source` char(1) DEFAULT 'w',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `teams`
-- ----------------------------
DROP TABLE IF EXISTS `teams`;
CREATE TABLE `teams` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `title` varchar(512) DEFAULT NULL,
  `avatar` varchar(512) DEFAULT NULL,
  `create_uid` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `delflag` char(1) DEFAULT '0',
  `pid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `teams_user`
-- ----------------------------
DROP TABLE IF EXISTS `teams_user`;
CREATE TABLE `teams_user` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `team_id` varchar(32) DEFAULT NULL,
  `role` varchar(32) DEFAULT NULL,
  `uid` varchar(64) DEFAULT NULL,
  `team_tid` varchar(32) DEFAULT NULL,
  `modifytime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `uploads`
-- ----------------------------
DROP TABLE IF EXISTS `uploads`;
CREATE TABLE `uploads` (
  `id` varchar(32) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `path` varchar(1024) DEFAULT NULL,
  `spath` varchar(1024) DEFAULT NULL,
  `_ext` varchar(32) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `uploads_rel`
-- ----------------------------
DROP TABLE IF EXISTS `uploads_rel`;
CREATE TABLE `uploads_rel` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `objType` varchar(32) DEFAULT NULL,
  `objId` varchar(32) DEFAULT NULL,
  `uploadId` varchar(32) DEFAULT NULL,
  `dt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `uid` varchar(64) NOT NULL DEFAULT '',
  `username` varchar(64) DEFAULT NULL,
  `nick` varchar(512) DEFAULT NULL,
  `source` char(1) DEFAULT NULL,
  `company_id` varchar(32) DEFAULT NULL,
  `company_name` varchar(1024) DEFAULT NULL,
  `token` varchar(32) DEFAULT NULL,
  `pwd` varchar(32) DEFAULT NULL,
  `avatar` varchar(4000) DEFAULT NULL,
  `dept` varchar(512) DEFAULT NULL,
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `py` varchar(256) DEFAULT NULL,
  `session_key` varchar(256) DEFAULT NULL,
  `tel` varchar(64) DEFAULT NULL,
  `truename` varchar(256) DEFAULT NULL,
  `addr` varchar(1024) DEFAULT NULL,
  `sumslack_uid` int(11) DEFAULT NULL,
  `sign` text,
  `dept_id` varchar(32) DEFAULT NULL,
  `dept_name` varchar(512) DEFAULT NULL,
  `email` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `users_data_purview`
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
--  Table structure for `users_tmpl_purview`
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

SET FOREIGN_KEY_CHECKS = 1;
