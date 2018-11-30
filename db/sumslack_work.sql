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
INSERT INTO m_work_projects VALUES ('l4sphid05c', 'sss33', 'ssss', '0', '1145', '1145', '2017-04-21 15:41:41', '1', null, '2017-04-21 13:38:02', '2017-04-05 00:00:00', '2017-08-01 00:00:00', '1145', '71', '0');
INSERT INTO m_work_projects VALUES ('l6qjgr1kao', '11122', '33333', '1', '1145', '1145', '2017-04-25 10:23:02', '1', null, '2017-04-25 10:24:50', '2017-04-01 00:00:00', '2017-08-01 00:00:00', '1145', '71', '0');
INSERT INTO m_work_projects VALUES ('l6y6rwwmww', 'asdfasdf是的33', 'asdf阿斯顿', '1', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', '2017-05-09 16:41:02', '1', null, '2017-04-25 20:34:28', null, '2017-04-25 00:00:00', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', '55', '0');
INSERT INTO m_work_projects VALUES ('l7x85hdvy8', '测试项3', 'asdfa sd ', '1', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', '2017-05-09 16:40:59', '1', null, '2017-04-27 19:07:35', null, '2017-04-27 00:00:00', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', '55', '0');
INSERT INTO m_work_projects VALUES ('l7xka687wg', '多人参与项目22', 'asdf ', '0', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', '2017-04-27 20:14:42', '1', null, '2017-04-27 19:34:27', null, '2017-04-29 00:00:00', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', '55', '0');
INSERT INTO m_work_projects VALUES ('l7xltv9c00', '22', '22222', '0', '54', '54', '2017-05-10 16:59:35', '1', null, '2017-04-27 19:37:52', null, null, '54', '55', '2');
INSERT INTO m_work_projects VALUES ('l88tb4xkw0', '啦啦啦', '啦啦啦', '0', '54', '54', '2017-05-02 17:48:46', '1', null, '2017-04-28 10:31:19', null, '2017-04-28 00:00:00', '54', '55', '0');
INSERT INTO m_work_projects VALUES ('lbtbk5h1q8', '撒旦发22', '阿斯顿发( ⊙ o ⊙ )啊！444', '0', '54', '54', '2017-05-10 16:59:34', '1', null, '2017-05-05 13:15:24', null, null, '54', '55', '2');
INSERT INTO m_work_projects VALUES ('lbwo0daby8', '来咯哦哦哦', '快乐的叫我女', '0', '54', '54', '2017-05-10 16:59:32', '1', null, '2017-05-05 17:42:06', null, '2017-05-05 00:00:00', '54', '55', '2');
INSERT INTO m_work_projects VALUES ('lbwufi9ssg', '测试项目', '哈哈哈啊', '0', '54', '54', '2017-05-10 16:59:31', '1', null, '2017-05-05 17:56:19', null, '2017-10-05 00:00:00', '54', '55', '2');
INSERT INTO m_work_projects VALUES ('lbwv8y51c0', '粑粑', '', '0', '54', '54', '2017-05-10 16:59:29', '1', null, '2017-05-05 17:58:08', null, '2017-05-05 00:00:00', '54', '55', '2');
INSERT INTO m_work_projects VALUES ('lbwvi1vif4', '咖啡', '', '0', '54', '54', '2017-05-09 15:35:34', '1', null, '2017-05-05 17:58:42', null, '2017-05-05 00:00:00', '54', '55', '1');
INSERT INTO m_work_projects VALUES ('lbwwl8hmgw', '咯么', '', '1', '54', '54', '2017-05-09 15:21:16', '1', null, '2017-05-05 18:01:06', null, '2017-05-05 00:00:00', '54', '55', '0');
INSERT INTO m_work_projects VALUES ('lbwwxscc1s', '公共婆婆', '', '1', '54', '54', '2017-05-09 15:21:10', '1', null, '2017-05-05 18:01:53', null, '2017-05-05 00:00:00', '54', '55', '0');
INSERT INTO m_work_projects VALUES ('lbwx4bvl6o', '公共你哦', '', '0', '54', '54', '2017-05-05 17:59:23', '1', null, '2017-05-05 18:02:17', null, '2017-05-05 00:00:00', '54', '55', '0');
INSERT INTO m_work_projects VALUES ('ld9m7glxc0', '来咯', '通道', '0', '54', '54', '2017-05-09 15:35:32', '1', null, '2017-05-08 10:44:12', null, '2017-05-08 00:00:00', '54', '55', '1');
INSERT INTO m_work_projects VALUES ('ldbz972pds', '测试1', '测试内容1', '0', '52', '52', '2017-05-09 15:35:30', '1', null, '2017-05-08 13:52:32', null, '2017-05-08 00:00:00', '52', '55', '2');
INSERT INTO m_work_projects VALUES ('ldvurm0dts', 'asdf ', 'asdf', '0', '1145', '1145', '2017-05-09 16:13:56', '1', null, '2017-05-09 16:16:55', '2017-04-01 00:00:00', '2017-08-01 00:00:00', '1145', '72', '0');
INSERT INTO m_work_projects VALUES ('ldw0h2mb5s', '测试后', '婆婆说', '0', '54', '54', '2017-05-10 16:59:27', '1', null, '2017-05-09 16:29:33', null, '2017-05-09 00:00:00', '54', '71', '1');
INSERT INTO m_work_projects VALUES ('ldw18dsbuo', '老婆婆婆', '末日婆婆', '1', '54', '54', '2017-05-19 15:43:51', '1', null, '2017-05-09 16:31:14', null, '2017-05-09 00:00:00', '54', '71', '0');
INSERT INTO m_work_projects VALUES ('ldw1b243cw', '老婆婆婆', '末日婆婆', '0', '54', '54', '2017-05-19 15:43:48', '1', null, '2017-05-09 16:31:24', null, '2017-05-09 00:00:00', '54', '71', '1');
INSERT INTO m_work_projects VALUES ('ldw1thc7wg', '闽人', '破破破', '0', '54', '54', '2017-05-09 16:29:33', '1', null, '2017-05-09 16:32:32', null, '2017-05-09 00:00:00', '54', '71', '0');
INSERT INTO m_work_projects VALUES ('ldw4n53q4g', 'opopos', '破破破', '0', '54', '54', '2017-05-10 16:59:39', '1', null, '2017-05-09 16:38:47', null, '2017-05-09 00:00:00', '54', '71', '2');
INSERT INTO m_work_projects VALUES ('lewmxk2pz4', '1', 'fggh', '0', '52', '52', '2017-05-11 17:05:55', '1', null, '2017-05-11 17:09:04', null, '2017-05-11 00:00:00', '52', '71', '0');
INSERT INTO m_work_projects VALUES ('lewn8vfev4', '热的', '德国环境', '0', '52', '52', '2017-05-11 17:06:36', '1', null, '2017-05-11 17:09:46', null, '2017-05-23 00:00:00', '52', '71', '0');
INSERT INTO m_work_projects VALUES ('lewp5ize2o', '粑粑11111', '啦啦啦', '1', '54', '54', '2017-05-17 15:35:52', '1', null, '2017-05-11 17:13:59', null, '2017-05-11 00:00:00', '54', '71', '0');
INSERT INTO m_work_projects VALUES ('lewsnpan0g', '他咯哦', '啦啦吧', '0', '54', '54', '2017-05-11 17:18:35', '1', null, '2017-05-11 17:21:45', null, '2017-05-11 00:00:00', '54', '71', '0');
INSERT INTO m_work_projects VALUES ('lhc30cpclc', '闽你', '匿名', '0', '54', '54', '2017-05-16 13:16:43', '1', null, '2017-05-16 13:19:58', null, '2017-05-16 00:00:00', '54', '71', '0');
INSERT INTO m_work_projects VALUES ('lhucz81iio', '啦啊擦', '啦啦啦', '1', '54', '54', '2017-05-17 13:34:29', '1', null, '2017-05-17 13:37:02', null, '2017-05-17 00:00:00', '54', '71', '0');
INSERT INTO m_work_projects VALUES ('lhud0wm96o', '啦啊擦', '啦啦啦', '1', '54', '54', '2017-05-17 13:34:26', '1', null, '2017-05-17 13:37:02', null, '2017-05-17 00:00:00', '54', '71', '0');
INSERT INTO m_work_projects VALUES ('lhudm0188w', '爸比妈咪', '啦啦啊', '1', '54', '54', '2017-05-17 15:31:48', '1', null, '2017-05-17 13:38:20', null, '2017-05-17 00:00:00', '54', '71', '0');
INSERT INTO m_work_projects VALUES ('lhvu1vt69s', '哥哥更', '在线内而', '0', '52', '52', '2017-05-17 15:31:06', '1', null, '2017-05-17 15:34:27', null, '2017-05-17 00:00:00', '52', '71', '0');
INSERT INTO m_work_projects VALUES ('lhvuh9wt1c', '送哦', '破后悔', '0', '54', '54', '2017-05-17 15:32:03', '1', null, '2017-05-17 15:35:24', null, '2017-05-17 00:00:00', '54', '71', '0');
INSERT INTO m_work_projects VALUES ('lhvvzj46bk', '粑粑', '啦啦啊', '1', '54', '54', '2017-05-17 15:35:46', '1', null, '2017-05-17 15:38:44', null, '2017-05-17 00:00:00', '54', '71', '0');
INSERT INTO m_work_projects VALUES ('lhvw3cy5fk', '粑粑', '啦啦啊', '1', '54', '54', '2017-05-17 15:35:49', '1', null, '2017-05-17 15:38:59', null, '2017-05-17 00:00:00', '54', '71', '0');
INSERT INTO m_work_projects VALUES ('lhvwotge0w', '发广告', '骨灰盒', '0', '52', '52', '2017-05-17 15:36:57', '1', null, '2017-05-17 15:40:18', null, '2017-05-17 00:00:00', '52', '71', '0');
INSERT INTO m_work_projects VALUES ('lhvxsn981s', 'lll的', '啦啦啦', '0', '54', '54', '2017-05-18 17:57:37', '1', null, '2017-05-17 15:42:45', null, '2017-05-17 00:00:00', '54', '71', '0');
INSERT INTO m_work_projects VALUES ('lhw6r7knpc', '哈哈哈', '骨灰盒', '0', '52', '52', '2017-05-17 15:59:14', '1', null, '2017-05-17 16:02:35', null, '2017-05-17 00:00:00', '52', '71', '0');
INSERT INTO m_work_projects VALUES ('lhwb0xhon4', '方法', '哈哈', '0', '52', '52', '2017-05-17 16:08:41', '1', null, '2017-05-17 16:12:03', null, '2017-05-17 00:00:00', '52', '71', '0');
INSERT INTO m_work_projects VALUES ('lhwb1vlczk', '方法', '哈哈', '0', '52', '52', '2017-05-17 16:08:45', '1', null, '2017-05-17 16:12:06', null, '2017-05-17 00:00:00', '52', '71', '0');
INSERT INTO m_work_projects VALUES ('lhwb20xssg', '方法', '哈哈', '0', '52', '52', '2017-05-17 16:08:45', '1', null, '2017-05-17 16:12:07', null, '2017-05-17 00:00:00', '52', '71', '0');
INSERT INTO m_work_projects VALUES ('lhwb22tkow', '方法', '哈哈', '0', '52', '52', '2017-05-17 16:08:46', '1', null, '2017-05-17 16:12:07', null, '2017-05-17 00:00:00', '52', '71', '0');
INSERT INTO m_work_projects VALUES ('lhwb24ozy8', '方法', '哈哈', '0', '52', '52', '2017-05-17 16:08:46', '1', null, '2017-05-17 16:12:07', null, '2017-05-17 00:00:00', '52', '71', '0');
INSERT INTO m_work_projects VALUES ('lhwb26dedc', '方法', '哈哈', '0', '52', '52', '2017-05-17 16:08:46', '1', null, '2017-05-17 16:12:07', null, '2017-05-17 00:00:00', '52', '71', '0');
INSERT INTO m_work_projects VALUES ('lhwb2c5af4', '方法', '哈哈', '0', '52', '52', '2017-05-17 16:08:47', '1', null, '2017-05-17 16:12:08', null, '2017-05-17 00:00:00', '52', '71', '0');
INSERT INTO m_work_projects VALUES ('lhwb2c6ozk', '方法', '哈哈', '0', '52', '52', '2017-05-17 16:08:47', '1', null, '2017-05-17 16:12:08', null, '2017-05-17 00:00:00', '52', '71', '0');
INSERT INTO m_work_projects VALUES ('lhwb37o9a8', '方法', '哈哈', '0', '52', '52', '2017-05-17 16:08:50', '1', null, '2017-05-17 16:12:11', null, '2017-05-17 00:00:00', '52', '71', '0');
INSERT INTO m_work_projects VALUES ('lhwb3akjy8', '方法', '哈哈', '0', '52', '52', '2017-05-17 16:08:50', '1', null, '2017-05-17 16:12:11', null, '2017-05-17 00:00:00', '52', '71', '0');
INSERT INTO m_work_projects VALUES ('lhwb427eo0', '方法', '哈哈', '0', '52', '52', '2017-05-17 16:08:53', '1', null, '2017-05-17 16:12:14', null, '2017-05-17 00:00:00', '52', '71', '0');
INSERT INTO m_work_projects VALUES ('lhwcb13b40', '个v', '发广告', '0', '52', '52', '2017-05-17 16:11:32', '1', null, '2017-05-17 16:14:53', null, '2017-05-17 00:00:00', '52', '71', '0');
INSERT INTO m_work_projects VALUES ('lhwebs6bk0', '刚刚好', '刚刚好', '0', '52', '52', '2017-05-17 16:16:00', '1', null, '2017-05-17 16:19:21', null, '2017-05-17 00:00:00', '52', '71', '0');
INSERT INTO m_work_projects VALUES ('lhwjp5p6v4', '方法天', '方法过', '0', '52', '52', '2017-05-17 16:27:54', '1', null, '2017-05-17 16:31:15', null, '2017-05-17 00:00:00', '52', '71', '0');
INSERT INTO m_work_projects VALUES ('lhwkwsjwn4', '方法天', '方法过', '0', '52', '52', '2017-05-17 16:30:35', '1', null, '2017-05-17 16:33:56', null, '2017-05-17 00:00:00', '52', '71', '0');
INSERT INTO m_work_projects VALUES ('lhwluzhji8', '哥哥更', '推广会', '0', '52', '52', '2017-05-17 16:32:41', '1', null, '2017-05-17 16:36:02', null, '2017-05-17 00:00:00', '52', '71', '0');
INSERT INTO m_work_projects VALUES ('lhxa1tsu0w', '发广告', '发广告', '0', '52', '52', '2017-05-17 17:26:15', '1', null, '2017-05-17 17:29:36', null, '2017-05-17 00:00:00', '52', '71', '0');
INSERT INTO m_work_projects VALUES ('lhxk3eeh34', '发广告', '哥哥更', '0', '52', '52', '2017-05-17 17:48:29', '1', null, '2017-05-17 17:51:50', null, '2017-05-17 00:00:00', '52', '71', '0');
INSERT INTO m_work_projects VALUES ('lhxkj8i0ow', '刚刚好', '哈哈哈', '0', '52', '52', '2017-05-17 17:49:27', '1', null, '2017-05-17 17:52:49', null, '2017-05-17 00:00:00', '52', '71', '0');
INSERT INTO m_work_projects VALUES ('liw2gztp8g', '测试', '婆婆你', '1', '54', '54', '2017-05-19 15:43:45', '1', null, '2017-05-19 15:42:53', null, '2017-05-19 00:00:00', '54', '71', '0');
INSERT INTO m_work_projects VALUES ('liw4ce4mbk', '啦啦啦', '啦啦', '1', '54', '54', '2017-05-19 15:43:42', '1', null, '2017-05-19 15:47:02', null, '2017-05-19 00:00:00', '54', '71', '0');
INSERT INTO m_work_projects VALUES ('liw51ms2yo', '来咯哦111', 'lll12', '1', '54', '54', '2017-05-19 15:46:40', '1', null, '2017-05-19 15:48:35', null, '2017-05-19 00:00:00', '54', '71', '0');
INSERT INTO m_work_projects VALUES ('liw5vkk268', '啦册', '健健康康', '1', '54', '54', '2017-05-19 15:48:01', '1', null, '2017-05-19 15:50:25', null, '2017-05-19 00:00:00', '54', '71', '0');
INSERT INTO m_work_projects VALUES ('liwc6m8kjk', '111', '哈哈哈哈', '1', '54', '54', '2017-05-19 16:01:06', '1', null, '2017-05-19 16:04:23', null, '2017-05-19 00:00:00', '54', '71', '0');
INSERT INTO m_work_projects VALUES ('liwhhk9tz4', '111', '啦啦啦', '0', '54', '54', '2017-05-19 16:12:37', '1', null, '2017-05-19 16:16:08', null, '2017-05-19 00:00:00', '54', '71', '0');
INSERT INTO m_work_projects VALUES ('lmbi8hv11c', '1112', '333', '0', '1145', '1145', '2017-05-26 11:38:12', '1', null, '2017-05-26 11:42:53', '2017-04-01 00:00:00', '2017-08-01 00:00:00', '1145', '72', '0');
INSERT INTO m_work_projects VALUES ('lmbi9vaqkg', '22222', '333', '0', '1145', '1145', '2017-05-26 11:38:17', '1', null, '2017-05-26 11:42:58', '2017-04-01 00:00:00', '2017-08-01 00:00:00', '1145', '72', '0');
INSERT INTO m_work_projects VALUES ('lmbiagbi0w', '33333', '333', '0', '1145', '1145', '2017-05-26 11:38:20', '1', null, '2017-05-26 11:43:00', '2017-04-01 00:00:00', '2017-08-01 00:00:00', '1145', '72', '0');
INSERT INTO m_work_projects VALUES ('lmbiaypg5c', '4444', '333', '0', '1145', '1145', '2017-05-26 11:38:21', '1', null, '2017-05-26 11:43:02', '2017-04-01 00:00:00', '2017-08-01 00:00:00', '1145', '72', '0');
INSERT INTO m_work_projects VALUES ('lmbibg9728', '5555', '333', '0', '1145', '1145', '2017-05-26 11:38:23', '1', null, '2017-05-26 11:43:03', '2017-04-01 00:00:00', '2017-08-01 00:00:00', '1145', '72', '0');
INSERT INTO m_work_projects VALUES ('lmbibya5fk', '6666', '333', '0', '1145', '1145', '2017-05-26 11:38:25', '1', null, '2017-05-26 11:43:05', '2017-04-01 00:00:00', '2017-08-01 00:00:00', '1145', '72', '0');
INSERT INTO m_work_projects VALUES ('lmbicm5szk', '7777', '333', '0', '1145', '1145', '2017-05-26 11:38:27', '1', null, '2017-05-26 11:43:08', '2017-04-01 00:00:00', '2017-08-01 00:00:00', '1145', '72', '0');
INSERT INTO m_work_projects VALUES ('lmbid47ta8', '8888', '333', '0', '1145', '1145', '2017-05-26 11:38:29', '1', null, '2017-05-26 11:43:10', '2017-04-01 00:00:00', '2017-08-01 00:00:00', '1145', '72', '0');
INSERT INTO m_work_projects VALUES ('lmbidlsyrk', '9999', '333', '0', '1145', '1145', '2017-05-26 11:38:31', '1', null, '2017-05-26 11:43:11', '2017-04-01 00:00:00', '2017-08-01 00:00:00', '1145', '72', '0');
INSERT INTO m_work_projects VALUES ('lmbied6osg', 'aaaa', '333', '0', '1145', '1145', '2017-05-26 11:38:34', '1', null, '2017-05-26 11:43:14', '2017-04-01 00:00:00', '2017-08-01 00:00:00', '1145', '72', '0');
INSERT INTO m_work_projects VALUES ('lmbietn3sw', 'bbbb', '333', '0', '1145', '1145', '2017-05-26 11:38:36', '1', null, '2017-05-26 11:43:16', '2017-04-01 00:00:00', '2017-08-01 00:00:00', '1145', '72', '0');
INSERT INTO m_work_projects VALUES ('lmbif8zhmo', 'cccc', '333', '0', '1145', '1145', '2017-05-26 11:38:37', '1', null, '2017-05-26 11:43:18', '2017-04-01 00:00:00', '2017-08-01 00:00:00', '1145', '72', '0');
INSERT INTO m_work_projects VALUES ('lmbifpl69s', 'dddd', '333', '0', '1145', '1145', '2017-05-26 11:38:39', '1', null, '2017-05-26 11:43:19', '2017-04-01 00:00:00', '2017-08-01 00:00:00', '1145', '72', '0');
INSERT INTO m_work_projects VALUES ('lmbig5g64g', 'eeee', '333', '0', '1145', '1145', '2017-05-26 11:38:41', '1', null, '2017-05-26 11:43:21', '2017-04-01 00:00:00', '2017-08-01 00:00:00', '1145', '72', '0');
INSERT INTO m_work_projects VALUES ('lmbiglwxs0', 'ffff', '333', '0', '1145', '1145', '2017-05-26 11:38:42', '1', null, '2017-05-26 11:43:23', '2017-04-01 00:00:00', '2017-08-01 00:00:00', '1145', '72', '0');
INSERT INTO m_work_projects VALUES ('lmbih2lfk0', 'gggg', '333', '0', '1145', '1145', '2017-05-26 11:38:44', '1', null, '2017-05-26 11:43:24', '2017-04-01 00:00:00', '2017-08-01 00:00:00', '1145', '72', '0');
INSERT INTO m_work_projects VALUES ('lmbihoqku8', 'hhhh', '333', '0', '1145', '1145', '2017-05-26 11:38:46', '1', null, '2017-05-26 11:43:26', '2017-04-01 00:00:00', '2017-08-01 00:00:00', '1145', '72', '0');
INSERT INTO m_work_projects VALUES ('lmbiicvpq8', 'iiii', '333', '0', '1145', '1145', '2017-05-26 11:38:49', '1', null, '2017-05-26 11:43:29', '2017-04-01 00:00:00', '2017-08-01 00:00:00', '1145', '72', '0');
INSERT INTO m_work_projects VALUES ('lmbij2msjk', 'jjjjj', '333', '0', '1145', '1145', '2017-05-26 11:38:51', '1', null, '2017-05-26 11:43:32', '2017-04-01 00:00:00', '2017-08-01 00:00:00', '1145', '72', '0');
INSERT INTO m_work_projects VALUES ('lmbijlul8g', 'kkkkk', '333', '0', '1145', '1145', '2017-05-26 11:38:53', '1', null, '2017-05-26 11:43:34', '2017-04-01 00:00:00', '2017-08-01 00:00:00', '1145', '72', '0');
INSERT INTO m_work_projects VALUES ('lmbik9k9vk', 'lllll', '333', '0', '1145', '1145', '2017-05-26 11:38:56', '1', null, '2017-05-26 11:43:36', '2017-04-01 00:00:00', '2017-08-01 00:00:00', '1145', '72', '0');
INSERT INTO m_work_projects VALUES ('lmbikx9lvk', 'mmmm', '333', '0', '1145', '1145', '2017-05-26 11:38:58', '1', null, '2017-05-26 11:43:38', '2017-04-01 00:00:00', '2017-08-01 00:00:00', '1145', '72', '0');
INSERT INTO m_work_projects VALUES ('lmbilj7dog', 'nnnn', '333', '0', '1145', '1145', '2017-05-26 11:39:00', '1', null, '2017-05-26 11:43:41', '2017-04-01 00:00:00', '2017-08-01 00:00:00', '1145', '72', '0');
INSERT INTO m_work_projects VALUES ('lmbim7mp6o', 'ooooo', '333', '0', '1145', '1145', '2017-05-26 11:39:03', '1', null, '2017-05-26 11:43:43', '2017-04-01 00:00:00', '2017-08-01 00:00:00', '1145', '72', '0');
INSERT INTO m_work_projects VALUES ('lmbin09dds', 'ppppp', '333', '0', '1145', '1145', '2017-05-26 11:39:06', '1', null, '2017-05-26 11:43:46', '2017-04-01 00:00:00', '2017-08-01 00:00:00', '1145', '72', '0');
INSERT INTO m_work_projects VALUES ('lmbini7v9c', 'qqqq2', '333', '0', '1145', '1145', '2017-05-26 17:00:41', '1', null, '2017-05-26 11:43:48', '2017-04-01 00:00:00', '2017-08-01 00:00:00', '1145', '72', '0');
INSERT INTO m_work_projects VALUES ('lru6aj7sow', '哦穷', '婆婆哦说', '0', '54', '54', '2017-06-06 11:34:47', '1', null, '2017-06-06 11:39:57', null, '2017-06-06 00:00:00', '54', '71', '0');

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
INSERT INTO m_work_project_tasks VALUES ('llg8ed77r4', 'liwhhk9tz4', '1', 'llg8ed77r5', '1', '1', '566888', '1900', '54', '2017-05-24 18:09:54', '54', '2017-05-24 18:05:56', '1209', null, '2017-05-24 00:00:00', '1977,1983', '0');
INSERT INTO m_work_project_tasks VALUES ('llw4c9log0', 'liwhhk9tz4', '1', 'llw4ca8i68', '1', '1', '测试', '1983', '54', '2017-05-25 15:16:22', '54', '2017-05-25 15:12:20', '1983', null, '2017-05-25 00:00:00', '54,2050,59', '0');
INSERT INTO m_work_project_tasks VALUES ('llw4pdi1a8', 'liwhhk9tz4', '1', 'llw4pdidxc', '1', '1', '测试', '1977', '54', '2017-05-25 15:17:10', '54', '2017-05-25 15:13:09', '1209', null, '2017-05-25 00:00:00', '1977,54', '0');
INSERT INTO m_work_project_tasks VALUES ('llw701zrpc', 'lhxkj8i0ow', '1', 'llw70204cg', '1', '1', '哈哈哈', '52', '52', '2017-05-25 15:22:15', '52', '2017-05-25 15:18:14', '52', null, '2017-05-25 00:00:00', '52,54', '0');
INSERT INTO m_work_project_tasks VALUES ('llw73t9dz4', 'lhxkj8i0ow', '1', 'llw73t9qm8', '1', '1', '哈哈哈', '52', '52', '2017-05-25 15:22:29', '52', '2017-05-25 15:18:28', '52', null, '2017-05-25 00:00:00', '52,54', '0');
INSERT INTO m_work_project_tasks VALUES ('llw7464kqo', 'lhxkj8i0ow', '1', 'llw7468sg0', '1', '1', '哈哈哈', '52', '52', '2017-05-25 15:22:31', '52', '2017-05-25 15:18:29', '52', null, '2017-05-25 00:00:00', '52,54', '0');
INSERT INTO m_work_project_tasks VALUES ('llw754ly4g', 'lhxkj8i0ow', '1', 'llw754ly4h', '1', '1', '哈哈哈', '52', '52', '2017-05-25 15:22:34', '52', '2017-05-25 15:18:32', '52', null, '2017-05-25 00:00:00', '52,54', '0');
INSERT INTO m_work_project_tasks VALUES ('llw7giax34', 'lhxkj8i0ow', '1', 'llw7gib9q8', '1', '1', '哈哈哈', '52', '52', '2017-05-25 15:23:16', '52', '2017-05-25 15:19:14', '52', null, '2017-05-25 00:00:00', '52,54', '0');
INSERT INTO m_work_project_tasks VALUES ('llw7ka82dc', 'lhxkj8i0ow', '1', 'llw7kab85c', '1', '1', '哈哈哈', '52', '52', '2017-05-25 15:23:30', '52', '2017-05-25 15:19:28', '52', null, '2017-05-25 00:00:00', '52,54', '0');
INSERT INTO m_work_project_tasks VALUES ('llw7kkzr40', 'lhxkj8i0ow', '1', 'llw7kl2k8w', '1', '1', '哈哈哈', '52', '52', '2017-05-25 15:23:31', '52', '2017-05-25 15:19:30', '52', null, '2017-05-25 00:00:00', '52,54', '0');
INSERT INTO m_work_project_tasks VALUES ('llw7lgbp4w', 'lhxkj8i0ow', '1', 'llw7lgfk74', '1', '1', '哈哈哈', '52', '52', '2017-05-25 15:23:34', '52', '2017-05-25 15:19:33', '52', null, '2017-05-25 00:00:00', '52,54', '0');
INSERT INTO m_work_project_tasks VALUES ('llw7n7a4g0', 'lhxkj8i0ow', '1', 'llw7n7ckxs', '1', '1', '哈哈哈', '52', '52', '2017-05-25 15:23:41', '52', '2017-05-25 15:19:39', '52', null, '2017-05-25 00:00:00', '52,54', '0');
INSERT INTO m_work_project_tasks VALUES ('llw7nhxlhc', 'lhxkj8i0ow', '1', 'llw7ni1t6o', '1', '1', '哈哈哈', '52', '52', '2017-05-25 15:23:42', '52', '2017-05-25 15:19:40', '52', null, '2017-05-25 00:00:00', '52,54', '0');
INSERT INTO m_work_project_tasks VALUES ('llw94ywow0', 'lhxkj8i0ow', '1', 'llw94ywow1', '1', '1', '你好', '1931', '52', '2017-05-25 15:26:59', '52', '2017-05-25 15:22:58', '1931', null, '2017-05-25 00:00:00', '54,52', '0');
INSERT INTO m_work_project_tasks VALUES ('llwlbbft34', 'liwhhk9tz4', '1', 'llwlvulerk', '1', '1', '11111584', '1900', '54', '2017-05-25 15:55:13', '54', '2017-05-25 15:50:37', '1209', null, '2017-05-25 00:00:00', '54,2050', '0');
INSERT INTO m_work_project_tasks VALUES ('llwlmo5nuo', 'liwhhk9tz4', '1', 'llwlmo60hs', '1', '1', '5555', '1983', '54', '2017-05-25 15:54:39', '54', '2017-05-25 15:50:03', '1209', null, '2017-05-25 00:00:00', '54,2050', '0');
INSERT INTO m_work_project_tasks VALUES ('llwmsqo1kw', 'liwhhk9tz4', '1', 'llwmsqoe80', '2', '1', '6666', '1983', '54', '2017-05-25 15:57:14', '54', '2017-05-25 17:17:40', '56', null, '2017-05-25 00:00:00', '54,1980', '0');
INSERT INTO m_work_project_tasks VALUES ('llwybfk9hc', 'liwhhk9tz4', '1', 'llwybfkm4g', '1', '1', '拉力赛', '1931', '52', '2017-05-25 16:22:45', '52', '2017-05-26 13:30:05', '1931', null, '2017-05-25 00:00:00', '52', '0');
INSERT INTO m_work_project_tasks VALUES ('llxpgyswzk', 'liwhhk9tz4', '1', 'llxpgyt9mo', '2', '1', 'ceshirenwu', '1983', '54', '2017-05-25 17:22:52', '54', '2017-05-25 17:18:31', '1858', null, '2017-05-25 00:00:00', '54,2050', '0');
INSERT INTO m_work_project_tasks VALUES ('lmcicmx728', 'liwhhk9tz4', '1', 'lmcicmxjpc', '1', '1', '刚刚好', '1931', '52', '2017-05-26 13:02:51', '52', '2017-05-26 13:30:04', '1931', null, '2017-05-26 00:00:00', '52', '0');
INSERT INTO m_work_project_tasks VALUES ('lmcr2ypjb4', 'liwhhk9tz4', '1', 'lmcr2ypvy8', '1', '1', '1111', '54', '1145', '2017-05-26 13:22:11', '1145', '2017-05-26 13:29:42', '54', null, '2017-05-06 00:00:00', '54', '0');
INSERT INTO m_work_project_tasks VALUES ('lmcr4xakn4', 'liwhhk9tz4', '1', 'lmcr4xaxa8', '1', '1', '22222', '54', '1145', '2017-05-26 13:22:18', '1145', '2017-05-26 13:29:43', '54', null, '2017-05-06 00:00:00', '54', '0');
INSERT INTO m_work_project_tasks VALUES ('lmcr5dz2f4', 'liwhhk9tz4', '1', 'lmcr5dzf28', '1', '1', '3333', '54', '1145', '2017-05-26 13:22:20', '1145', '2017-05-26 13:29:43', '54', null, '2017-05-06 00:00:00', '54', '0');
INSERT INTO m_work_project_tasks VALUES ('lmcr5t2o74', 'liwhhk9tz4', '1', 'lmcr5t2o75', '1', '1', '4444', '54', '1145', '2017-05-26 13:22:21', '1145', '2017-05-26 13:29:44', '54', null, '2017-05-06 00:00:00', '54', '0');
INSERT INTO m_work_project_tasks VALUES ('lmcr67m9ds', 'liwhhk9tz4', '1', 'lmcr67mm0w', '1', '1', '5555', '54', '1145', '2017-05-26 13:22:23', '1145', '2017-05-26 13:29:44', '54', null, '2017-05-06 00:00:00', '54', '0');
INSERT INTO m_work_project_tasks VALUES ('lmcr6mt0xs', 'liwhhk9tz4', '1', 'lmcr6mtdkw', '1', '1', '6666', '54', '1145', '2017-05-26 13:22:24', '1145', '2017-05-26 13:29:44', '54', null, '2017-05-06 00:00:00', '54', '0');
INSERT INTO m_work_project_tasks VALUES ('lmcr72cfls', 'liwhhk9tz4', '1', 'lmcr72cflt', '1', '1', '7777', '54', '1145', '2017-05-26 13:22:26', '1145', '2017-05-26 13:29:45', '54', null, '2017-05-06 00:00:00', '54', '0');
INSERT INTO m_work_project_tasks VALUES ('lmcr7fql1c', 'liwhhk9tz4', '1', 'lmcr7fqxog', '1', '1', '8888', '54', '1145', '2017-05-26 13:22:27', '1145', '2017-05-26 13:29:45', '54', null, '2017-05-06 00:00:00', '54', '0');
INSERT INTO m_work_project_tasks VALUES ('lmcr8dg7pc', 'liwhhk9tz4', '1', 'lmcr8dgkcg', '1', '1', '9999', '54', '1145', '2017-05-26 13:22:31', '1145', '2017-05-26 13:29:45', '54', null, '2017-05-06 00:00:00', '54', '0');
INSERT INTO m_work_project_tasks VALUES ('lmcr8y4c1s', 'liwhhk9tz4', '1', 'lmcr8y4c1t', '1', '1', 'aaaa', '54', '1145', '2017-05-26 13:22:33', '1145', '2017-05-26 13:29:45', '54', null, '2017-05-06 00:00:00', '54', '0');
INSERT INTO m_work_project_tasks VALUES ('lmcr9e1fr4', 'liwhhk9tz4', '1', 'lmcr9e1se8', '1', '1', 'bbbbb', '54', '1145', '2017-05-26 13:22:35', '1145', '2017-05-26 13:29:46', '54', null, '2017-05-06 00:00:00', '54', '0');
INSERT INTO m_work_project_tasks VALUES ('lmcr9wyp6o', 'liwhhk9tz4', '1', 'lmcr9wyp6p', '1', '1', 'cccc', '54', '1145', '2017-05-26 13:22:37', '1145', '2017-05-26 13:29:46', '54', null, '2017-05-06 00:00:00', '54', '0');
INSERT INTO m_work_project_tasks VALUES ('lmcradi9z4', 'liwhhk9tz4', '1', 'lmcradi9z5', '1', '1', 'ddddd', '54', '1145', '2017-05-26 13:22:38', '1145', '2017-05-26 13:29:46', '54', null, '2017-05-06 00:00:00', '54', '0');
INSERT INTO m_work_project_tasks VALUES ('lmcrasxgxs', 'liwhhk9tz4', '1', 'lmcrasxgxt', '1', '1', 'eeee', '54', '1145', '2017-05-26 13:22:40', '1145', '2017-05-26 13:29:47', '54', null, '2017-05-06 00:00:00', '54', '0');
INSERT INTO m_work_project_tasks VALUES ('lmfkkqp3i8', 'lmbini7v9c', '1', 'lmfkkqpg5c', '1', '1', '匿名哦', '1977', '54', '2017-05-26 17:06:55', '54', '2017-05-26 17:02:14', '', null, '2017-05-26 00:00:00', '1983', '0');
INSERT INTO m_work_project_tasks VALUES ('lmfllv7r40', 'lmbini7v9c', '1', 'lmfllv83r4', '1', '1', '467646，', '1977', '54', '2017-05-26 17:09:12', '54', '2017-05-26 17:04:31', '1983', null, '2017-05-26 00:00:00', '1900,1983', '0');
INSERT INTO m_work_project_tasks VALUES ('lru6gunxmo', 'lru6aj7sow', '1', 'lru6gvj6rk', '2', '1', '婆婆您', '1983', '54', '2017-06-06 11:40:20', '54', '2017-06-06 11:35:26', '1977', null, '2017-06-06 00:00:00', '1983,1209', '0');
INSERT INTO m_work_project_tasks VALUES ('lru6qjq22o', 'lru6aj7sow', '1', 'lru6qjqeps', '1', '1', '破功哦', '1977', '54', '2017-06-06 11:40:56', '54', '2017-06-06 11:35:46', '56', null, '2017-06-06 00:00:00', '1983', '0');
INSERT INTO m_work_project_tasks VALUES ('lvza0ixhc0', '1', '1', 'lvza0j92io', '1', '1', '哈撒上', '1906', '1145', '2017-06-14 17:45:54', '1145', '2017-06-14 17:39:54', '1909', null, '2017-06-14 00:00:00', '1909,1867', '0');

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
INSERT INTO m_work_project_tasks_comments VALUES ('lewgdtqadc', 'leeyz9ktmo', '啦啦啦', '54', '2017-05-11 16:54:34', '54', '2017-05-11 16:51:25', 't');
INSERT INTO m_work_project_tasks_comments VALUES ('lewgrz0nwg', 'leeyz9ktmo', '啦啦啊', '54', '2017-05-11 16:55:26', '54', '2017-05-11 16:52:17', 't');
INSERT INTO m_work_project_tasks_comments VALUES ('lewlg47nr4', 'leeyz9ktmo', '还有殖民', '54', '2017-05-11 17:05:47', '54', '2017-05-11 17:02:37', 't');
INSERT INTO m_work_project_tasks_comments VALUES ('lewtdfdzi8', 'lewt6q016o', '啦啊擦', '54', '2017-05-11 17:23:20', '54', '2017-05-11 17:20:10', 't');
INSERT INTO m_work_project_tasks_comments VALUES ('lewtevi9kw', 'lewt6q016o', '啦啊擦1222', '54', '2017-05-11 17:23:25', '54', '2017-05-11 17:20:16', 't');
INSERT INTO m_work_project_tasks_comments VALUES ('lex3agto8w', 'leeyz9ktmo', '来来咯哦1222', '54', '2017-05-11 17:45:17', '54', '2017-05-11 17:42:08', 't');
INSERT INTO m_work_project_tasks_comments VALUES ('lex3fzrpc0', 'leeyz9ktmo', '啊擦的', '54', '2017-05-11 17:45:38', '54', '2017-05-11 17:42:28', 't');
INSERT INTO m_work_project_tasks_comments VALUES ('lex6m1qlfk', 'leeyz9ktmo', '122222', '54', '2017-05-11 17:52:39', '54', '2017-05-11 17:49:29', 't');
INSERT INTO m_work_project_tasks_comments VALUES ('lex6uey8zk', 'leeyz9ktmo', '455588', '54', '2017-05-11 17:53:09', '54', '2017-05-11 17:50:00', 't');
INSERT INTO m_work_project_tasks_comments VALUES ('lfega22mf4', 'lewt6q016o', '粑粑', '54', '2017-05-12 16:49:13', '54', '2017-05-12 16:45:59', 't');
INSERT INTO m_work_project_tasks_comments VALUES ('liwmnq9xj4', 'liwlm9i7ls', '111', '54', '2017-05-19 16:27:35', '54', '2017-05-19 16:24:04', 't');
INSERT INTO m_work_project_tasks_comments VALUES ('liwmpspog0', 'liwlm9i7ls', '222', '54', '2017-05-19 16:27:43', '54', '2017-05-19 16:24:11', 't');
INSERT INTO m_work_project_tasks_comments VALUES ('liwntuheyo', 'liwlm9i7ls', '333', '54', '2017-05-19 16:30:10', '54', '2017-05-19 16:26:39', 't');
INSERT INTO m_work_project_tasks_comments VALUES ('liwnxj4x6o', 'liwlm9i7ls', '444', '54', '2017-05-19 16:30:24', '54', '2017-05-19 16:26:53', 't');
INSERT INTO m_work_project_tasks_comments VALUES ('liwqltau4g', 'liwlm9i7ls', '555', '54', '2017-05-19 16:36:19', '54', '2017-05-19 16:32:48', 't');
INSERT INTO m_work_project_tasks_comments VALUES ('liwrg16mf4', 'liwm01rs3k', '11', '54', '2017-05-19 16:38:11', '54', '2017-05-19 16:34:39', 't');
INSERT INTO m_work_project_tasks_comments VALUES ('liwrhlo83k', 'liwm01rs3k', '22', '54', '2017-05-19 16:38:17', '54', '2017-05-19 16:34:45', 't');
INSERT INTO m_work_project_tasks_comments VALUES ('liwrruw7i8', 'liwm01rs3k', '333', '54', '2017-05-19 16:38:55', '54', '2017-05-19 16:35:23', 't');
INSERT INTO m_work_project_tasks_comments VALUES ('lkwx4n3fgg', 'lewmn21a80', 'Rom 破坏', '54', '2017-05-23 16:30:20', '54', '2017-05-23 16:26:28', 't');
INSERT INTO m_work_project_tasks_comments VALUES ('llwubv52ps', 'llg8ed77r4', '22236', '54', '2017-05-25 16:13:55', '54', '2017-05-25 16:09:19', 't');
INSERT INTO m_work_project_tasks_comments VALUES ('llwusd3klc', 'llwmsqo1kw', '235', '54', '2017-05-25 16:14:56', '54', '2017-05-25 16:10:20', 't');
INSERT INTO m_work_project_tasks_comments VALUES ('llxj0luqrk', 'llwybfk9hc', '你好', '52', '2017-05-25 17:08:35', '52', '2017-05-25 17:03:58', 't');
INSERT INTO m_work_project_tasks_comments VALUES ('llxmqumnsw', 'llwmsqo1kw', 'kdk', '54', '2017-05-25 17:16:50', '54', '2017-05-25 17:12:14', 't');
INSERT INTO m_work_project_tasks_comments VALUES ('llxmt6hzi8', 'llwmsqo1kw', 'gkunx', '54', '2017-05-25 17:16:59', '54', '2017-05-25 17:12:22', 't');
INSERT INTO m_work_project_tasks_comments VALUES ('lotxycwi68', 'lmcr7fql1c', '共鸣', '54', '2017-05-31 11:52:08', '54', '2017-05-31 11:47:02', 't');
INSERT INTO m_work_project_tasks_comments VALUES ('lru6wjdzi8', 'lru6qjq22o', 'going', '54', '2017-06-06 11:41:18', '54', '2017-06-06 11:36:08', 't');
INSERT INTO m_work_project_tasks_comments VALUES ('lru6yaxtz4', 'lru6qjq22o', 'ins 模式', '54', '2017-06-06 11:41:24', '54', '2017-06-06 11:36:15', 't');
INSERT INTO m_work_project_tasks_comments VALUES ('lsgvgytslc', 'llw701zrpc', '高规格', '52', '2017-06-07 17:49:27', '52', '2017-06-07 17:44:12', 't');
INSERT INTO m_work_project_tasks_comments VALUES ('lwcr2as4jk', 'llwmsqo1kw', '兔兔了', '1145', '2017-06-15 11:39:57', '1145', '2017-06-15 11:33:54', 't');

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
INSERT INTO m_work_project_users VALUES ('1', '1', 'owner', '1145', '1145', '1145', '2017-04-21 13:06:43');
INSERT INTO m_work_project_users VALUES ('l4tw76om4g', 'l4sphid05c', 'owner', '1145', '1145', '1145', '2017-04-21 15:10:31');
INSERT INTO m_work_project_users VALUES ('l4tw76po1s', 'l4sphid05c', 'member', '3', '1145', '1145', '2017-04-21 15:49:18');
INSERT INTO m_work_project_users VALUES ('l6qjgrykn4', 'l6qjgr1kao', 'owner', '1145', '1145', '1145', '2017-04-25 10:22:25');
INSERT INTO m_work_project_users VALUES ('l6qjgryxa8', 'l6qjgr1kao', 'member', '2', '1145', '1145', '2017-04-25 10:22:25');
INSERT INTO m_work_project_users VALUES ('l7y3nlb3ls', 'l7xka687wg', 'owner', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', '2017-04-27 20:14:42');
INSERT INTO m_work_project_users VALUES ('l7y3nlbg8w', 'l7xka687wg', 'member', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', '2017-04-27 20:14:42');
INSERT INTO m_work_project_users VALUES ('l7y3ooncow', 'l7x85hdvy8', 'owner', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', '2017-04-27 20:14:46');
INSERT INTO m_work_project_users VALUES ('l7y3ooncox', 'l7x85hdvy8', 'member', 'test', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', '2017-04-27 20:14:46');
INSERT INTO m_work_project_users VALUES ('l7y3oonpc0', 'l7x85hdvy8', 'member', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', '2017-04-27 20:14:46');
INSERT INTO m_work_project_users VALUES ('l7y3oonpc1', 'l7x85hdvy8', 'member', '54', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', '2017-05-02 17:53:27');
INSERT INTO m_work_project_users VALUES ('l7y3pvdgjk', 'l6y6rwwmww', 'owner', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', '2017-04-27 20:14:51');
INSERT INTO m_work_project_users VALUES ('l7y3pvdt6o', 'l6y6rwwmww', 'member', '54', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', '2017-05-02 17:53:26');
INSERT INTO m_work_project_users VALUES ('l7y3pvdt6p', 'l6y6rwwmww', 'member', 'test', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', '2017-04-27 20:14:51');
INSERT INTO m_work_project_users VALUES ('l7y4ex6eio', 'l7xltv9c00', 'owner', '54', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', '2017-05-02 17:53:28');
INSERT INTO m_work_project_users VALUES ('l7y4ex6r5s', 'l7xltv9c00', 'member', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', '2017-04-27 20:16:23');
INSERT INTO m_work_project_users VALUES ('l7y4ex6r5t', 'l7xltv9c00', 'member', 'test4', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', '2017-04-27 20:16:23');
INSERT INTO m_work_project_users VALUES ('l7y4ex73sw', 'l7xltv9c00', 'member', 'test5', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', '2017-04-27 20:16:23');
INSERT INTO m_work_project_users VALUES ('l88tb54ydc', 'l88tb4xkw0', 'owner', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', '2017-04-28 10:28:37');
INSERT INTO m_work_project_users VALUES ('l88tb55nnk', 'l88tb4xkw0', 'member', 'test', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', '2017-04-28 10:28:37');
INSERT INTO m_work_project_users VALUES ('l88tb560ao', 'l88tb4xkw0', 'member', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', '2017-04-28 10:28:37');
INSERT INTO m_work_project_users VALUES ('lbr4zqhx4w', 'lbr4zqcav4', 'owner', '54', '54', '54', '2017-05-05 10:18:33');
INSERT INTO m_work_project_users VALUES ('lbrajznhmo', 'lbrajzikn4', 'owner', '54', '54', '54', '2017-05-05 10:30:52');
INSERT INTO m_work_project_users VALUES ('lbrk9j03cw', 'lbrk9iu4g0', 'owner', '54', '54', '54', '2017-05-05 10:52:22');
INSERT INTO m_work_project_users VALUES ('lbs0g6wbgg', 'lbs0g6uww0', 'owner', '54', '54', '54', '2017-05-05 11:28:12');
INSERT INTO m_work_project_users VALUES ('lbs3v6fqww', 'lbs3v6c8hs', 'owner', '54', '54', '54', '2017-05-05 11:35:46');
INSERT INTO m_work_project_users VALUES ('lbsdfy46io', 'lbsdfxxuyo', 'owner', '54', '54', '54', '2017-05-05 11:56:58');
INSERT INTO m_work_project_users VALUES ('lbt5htubr4', 'lbt5htk54w', 'owner', '54', '54', '54', '2017-05-05 12:59:05');
INSERT INTO m_work_project_users VALUES ('lbt5x2obuo', 'lbt5x2mxa8', 'owner', '54', '54', '54', '2017-05-05 13:00:01');
INSERT INTO m_work_project_users VALUES ('lbt6hw191c', 'lbt6hvzugw', 'owner', '54', '54', '54', '2017-05-05 13:01:18');
INSERT INTO m_work_project_users VALUES ('lbt9wpn668', 'lbt9wpghz4', 'owner', '54', '54', '54', '2017-05-05 13:08:51');
INSERT INTO m_work_project_users VALUES ('lbtbk7xji8', 'lbtbk5h1q8', 'owner', '54', '54', '54', '2017-05-05 13:12:31');
INSERT INTO m_work_project_users VALUES ('lbtbk7y8sg', 'lbtbk5h1q8', 'member', '54', '54', '54', '2017-05-05 13:12:31');
INSERT INTO m_work_project_users VALUES ('lbvbd6nim8', 'lbvbd6l24g', 'owner', '54', '54', '54', '2017-05-05 15:51:30');
INSERT INTO m_work_project_users VALUES ('lbvbd6nv9c', 'lbvbd6l24g', 'member', '53', '54', '54', '2017-05-05 15:51:30');
INSERT INTO m_work_project_users VALUES ('lbvbd6nv9d', 'lbvbd6l24g', 'member', '52', '54', '54', '2017-05-05 15:51:30');
INSERT INTO m_work_project_users VALUES ('lbvbd6o7wg', 'lbvbd6l24g', 'member', '58', '54', '54', '2017-05-05 15:51:30');
INSERT INTO m_work_project_users VALUES ('lbvqgys4jk', 'lbvqgyqpz4', 'owner', '54', '54', '54', '2017-05-05 16:24:57');
INSERT INTO m_work_project_users VALUES ('lbvstgaqdc', 'lbvstga134', 'owner', '54', '54', '54', '2017-05-05 16:30:09');
INSERT INTO m_work_project_users VALUES ('lbvx2x9qm8', 'lbvx2x91c0', 'owner', '54', '54', '54', '2017-05-05 16:39:35');
INSERT INTO m_work_project_users VALUES ('lbwcaowcn4', 'lbwcaovaps', 'owner', '54', '54', '54', '2017-05-05 17:13:16');
INSERT INTO m_work_project_users VALUES ('lbwefkclj4', 'lbwefkbw8w', 'owner', '54', '54', '54', '2017-05-05 17:18:00');
INSERT INTO m_work_project_users VALUES ('lbweqe3fnk', 'lbweqe2dq8', 'owner', '54', '54', '54', '2017-05-05 17:18:40');
INSERT INTO m_work_project_users VALUES ('lbwgwcxou8', 'lbwgwco7i8', 'owner', '52', '52', '52', '2017-05-05 17:23:28');
INSERT INTO m_work_project_users VALUES ('lbwgyxfg1s', 'lbwgyxee4g', 'owner', '52', '52', '52', '2017-05-05 17:23:37');
INSERT INTO m_work_project_users VALUES ('lbwh5ibv28', 'lbwh5ia3uo', 'owner', '52', '52', '52', '2017-05-05 17:24:02');
INSERT INTO m_work_project_users VALUES ('lbwh9ubcw0', 'lbwh9u9ybk', 'owner', '52', '52', '52', '2017-05-05 17:24:18');
INSERT INTO m_work_project_users VALUES ('lbwhdmgxkw', 'lbwhdmfj0g', 'owner', '52', '52', '52', '2017-05-05 17:24:32');
INSERT INTO m_work_project_users VALUES ('lbwhdmhmv4', 'lbwhdmfj0g', 'member', '56', '52', '52', '2017-05-05 17:24:32');
INSERT INTO m_work_project_users VALUES ('lbwhi75o1s', 'lbwhi749hc', 'owner', '52', '52', '52', '2017-05-05 17:24:48');
INSERT INTO m_work_project_users VALUES ('lbwhi76dc0', 'lbwhi749hc', 'member', '56', '52', '52', '2017-05-05 17:24:48');
INSERT INTO m_work_project_users VALUES ('lbwiqwn01s', 'lbwiqwllhc', 'owner', '52', '52', '52', '2017-05-05 17:27:33');
INSERT INTO m_work_project_users VALUES ('lbwiqwncow', 'lbwiqwllhc', 'member', '2016', '52', '52', '2017-05-05 17:27:33');
INSERT INTO m_work_project_users VALUES ('lbwiqwnpc0', 'lbwiqwllhc', 'member', '54', '52', '52', '2017-05-05 17:27:33');
INSERT INTO m_work_project_users VALUES ('lbwlioo0e8', 'lbwliom96o', 'owner', '52', '52', '52', '2017-05-05 17:33:42');
INSERT INTO m_work_project_users VALUES ('lbwlj85n28', 'lbwlj84l4w', 'owner', '52', '52', '52', '2017-05-05 17:33:44');
INSERT INTO m_work_project_users VALUES ('lbwljtgxs0', 'lbwljtfj7k', 'owner', '52', '52', '52', '2017-05-05 17:33:46');
INSERT INTO m_work_project_users VALUES ('lbwljv4a9s', 'lbwljv3kzk', 'owner', '52', '52', '52', '2017-05-05 17:33:46');
INSERT INTO m_work_project_users VALUES ('lbwljwo4cg', 'lbwljwnrpc', 'owner', '52', '52', '52', '2017-05-05 17:33:46');
INSERT INTO m_work_project_users VALUES ('lbwljy8npc', 'lbwljy7yf4', 'owner', '52', '52', '52', '2017-05-05 17:33:46');
INSERT INTO m_work_project_users VALUES ('lbwljztjpc', 'lbwljzsuf4', 'owner', '52', '52', '52', '2017-05-05 17:33:46');
INSERT INTO m_work_project_users VALUES ('lbwlxbaeps', 'lbwlxb905c', 'owner', '54', '54', '54', '2017-05-05 17:34:36');
INSERT INTO m_work_project_users VALUES ('lbwm6yjx1c', 'lbwm6yj7r4', 'owner', '54', '54', '54', '2017-05-05 17:35:11');
INSERT INTO m_work_project_users VALUES ('lbwmo2iakg', 'lbwmo2gw00', 'owner', '54', '54', '54', '2017-05-05 17:36:14');
INSERT INTO m_work_project_users VALUES ('lbwnqsg9hc', 'lbwnqsei9s', 'owner', '54', '54', '54', '2017-05-05 17:38:37');
INSERT INTO m_work_project_users VALUES ('lbwnqw5csg', 'lbwnqw4ni8', 'owner', '54', '54', '54', '2017-05-05 17:38:38');
INSERT INTO m_work_project_users VALUES ('lbwnqxo4xs', 'lbwnqxnfnk', 'owner', '54', '54', '54', '2017-05-05 17:38:38');
INSERT INTO m_work_project_users VALUES ('lbwo0dbdvk', 'lbwo0daby8', 'owner', '54', '54', '54', '2017-05-05 17:39:13');
INSERT INTO m_work_project_users VALUES ('lbwo0dbqio', 'lbwo0daby8', 'member', '55', '54', '54', '2017-05-05 17:39:13');
INSERT INTO m_work_project_users VALUES ('lbwo0dc35s', 'lbwo0daby8', 'member', '53', '54', '54', '2017-05-05 17:39:13');
INSERT INTO m_work_project_users VALUES ('lbwufig4cg', 'lbwufi9ssg', 'owner', '54', '54', '54', '2017-05-05 17:53:26');
INSERT INTO m_work_project_users VALUES ('lbwufigtmo', 'lbwufi9ssg', 'member', '53', '54', '54', '2017-05-05 17:53:26');
INSERT INTO m_work_project_users VALUES ('lbwufigtmp', 'lbwufi9ssg', 'member', '58', '54', '54', '2017-05-05 17:53:26');
INSERT INTO m_work_project_users VALUES ('lbwv8y6fwg', 'lbwv8y51c0', 'owner', '54', '54', '54', '2017-05-05 17:55:14');
INSERT INTO m_work_project_users VALUES ('lbwvi1wkcg', 'lbwvi1vif4', 'owner', '54', '54', '54', '2017-05-05 17:55:48');
INSERT INTO m_work_project_users VALUES ('lbwwl8ioe8', 'lbwwl8hmgw', 'owner', '54', '54', '54', '2017-05-05 17:58:12');
INSERT INTO m_work_project_users VALUES ('lbwwxsddz4', 'lbwwxscc1s', 'owner', '54', '54', '54', '2017-05-05 17:58:59');
INSERT INTO m_work_project_users VALUES ('lbwx4bwagw', 'lbwx4bvl6o', 'owner', '54', '54', '54', '2017-05-05 17:59:23');
INSERT INTO m_work_project_users VALUES ('ld9m7gpse8', 'ld9m7glxc0', 'owner', '54', '54', '54', '2017-05-08 10:41:20');
INSERT INTO m_work_project_users VALUES ('ld9m7gq51c', 'ld9m7glxc0', 'member', '59', '54', '54', '2017-05-08 10:41:20');
INSERT INTO m_work_project_users VALUES ('ld9m7gqhog', 'ld9m7glxc0', 'member', '55', '54', '54', '2017-05-08 10:41:20');
INSERT INTO m_work_project_users VALUES ('ldbz9743y8', 'ldbz972pds', 'owner', '52', '52', '52', '2017-05-08 13:49:39');
INSERT INTO m_work_project_users VALUES ('ldbz974t8g', 'ldbz972pds', 'member', '54', '52', '52', '2017-05-08 13:49:39');
INSERT INTO m_work_project_users VALUES ('ldbz974t8h', 'ldbz972pds', 'member', '55', '52', '52', '2017-05-08 13:49:39');
INSERT INTO m_work_project_users VALUES ('ldbzn75wqo', 'ldbzn74utc', 'owner', '52', '52', '52', '2017-05-08 13:50:31');
INSERT INTO m_work_project_users VALUES ('ldvurm7eo0', 'ldvurm0dts', 'owner', '1145', '1145', '1145', '2017-05-09 16:13:56');
INSERT INTO m_work_project_users VALUES ('ldvurm7rb4', 'ldvurm0dts', 'member', '1145', '1145', '1145', '2017-05-09 16:13:56');
INSERT INTO m_work_project_users VALUES ('ldw0h2p4ao', 'ldw0h2mb5s', 'owner', '54', '54', '54', '2017-05-09 16:26:34');
INSERT INTO m_work_project_users VALUES ('ldw0h2ptkw', 'ldw0h2mb5s', 'member', '59', '54', '54', '2017-05-09 16:26:34');
INSERT INTO m_work_project_users VALUES ('ldw0h2q680', 'ldw0h2mb5s', 'member', '53', '54', '54', '2017-05-09 16:26:34');
INSERT INTO m_work_project_users VALUES ('ldw18dtqf4', 'ldw18dsbuo', 'owner', '54', '54', '54', '2017-05-09 16:28:15');
INSERT INTO m_work_project_users VALUES ('ldw18du328', 'ldw18dsbuo', 'member', '58', '54', '54', '2017-05-09 16:28:15');
INSERT INTO m_work_project_users VALUES ('ldw18duscg', 'ldw18dsbuo', 'member', '52', '54', '54', '2017-05-09 16:28:15');
INSERT INTO m_work_project_users VALUES ('ldw1b24g00', 'ldw1b243cw', 'owner', '54', '54', '54', '2017-05-09 16:28:25');
INSERT INTO m_work_project_users VALUES ('ldw1b24sn4', 'ldw1b243cw', 'member', '58', '54', '54', '2017-05-09 16:28:25');
INSERT INTO m_work_project_users VALUES ('ldw1b255a8', 'ldw1b243cw', 'member', '52', '54', '54', '2017-05-09 16:28:25');
INSERT INTO m_work_project_users VALUES ('ldw1thcx6o', 'ldw1thc7wg', 'owner', '54', '54', '54', '2017-05-09 16:29:33');
INSERT INTO m_work_project_users VALUES ('ldw1thd9ts', 'ldw1thc7wg', 'member', '58', '54', '54', '2017-05-09 16:29:33');
INSERT INTO m_work_project_users VALUES ('ldw1thdmgw', 'ldw1thc7wg', 'member', '53', '54', '54', '2017-05-09 16:29:33');
INSERT INTO m_work_project_users VALUES ('ldw1thdz40', 'ldw1thc7wg', 'member', '59', '54', '54', '2017-05-09 16:29:33');
INSERT INTO m_work_project_users VALUES ('ldw4n55tz4', 'ldw4n53q4g', 'owner', '55', '54', '54', '2017-05-09 16:35:48');
INSERT INTO m_work_project_users VALUES ('ldw4n566m8', 'ldw4n53q4g', 'member', '52', '54', '54', '2017-05-09 16:35:48');
INSERT INTO m_work_project_users VALUES ('ldw4n566m9', 'ldw4n53q4g', 'member', '54', '54', '54', '2017-05-09 16:38:15');
INSERT INTO m_work_project_users VALUES ('ldw4n56j9c', 'ldw4n53q4g', 'member', '57', '54', '54', '2017-05-09 16:35:48');
INSERT INTO m_work_project_users VALUES ('lewmxkwx6o', 'lewmxk2pz4', 'owner', '1931', '52', '52', '2017-05-11 17:05:55');
INSERT INTO m_work_project_users VALUES ('lewmxkx9ts', 'lewmxk2pz4', 'member', '1892', '52', '52', '2017-05-11 17:05:55');
INSERT INTO m_work_project_users VALUES ('lewn8vg45c', 'lewn8vfev4', 'owner', '1931', '52', '52', '2017-05-11 17:06:36');
INSERT INTO m_work_project_users VALUES ('lewn8vg45d', 'lewn8vfev4', 'member', '1906', '52', '52', '2017-05-11 17:06:36');
INSERT INTO m_work_project_users VALUES ('lewn8vggsg', 'lewn8vfev4', 'member', '1892', '52', '52', '2017-05-11 17:06:36');
INSERT INTO m_work_project_users VALUES ('lewn8vggsh', 'lewn8vfev4', 'member', '1867', '52', '52', '2017-05-11 17:06:36');
INSERT INTO m_work_project_users VALUES ('lewp5j03cw', 'lewp5ize2o', 'owner', '54', '54', '54', '2017-05-11 17:10:50');
INSERT INTO m_work_project_users VALUES ('lewp5j0g00', 'lewp5ize2o', 'member', '1209', '54', '54', '2017-05-11 17:10:50');
INSERT INTO m_work_project_users VALUES ('lewp5j0sn4', 'lewp5ize2o', 'member', '1900', '54', '54', '2017-05-11 17:10:50');
INSERT INTO m_work_project_users VALUES ('lewp5j0sn5', 'lewp5ize2o', 'member', '1977', '54', '54', '2017-05-11 17:10:50');
INSERT INTO m_work_project_users VALUES ('lewp5j0sn6', 'lewp5ize2o', 'member', '1983', '54', '54', '2017-05-11 17:10:50');
INSERT INTO m_work_project_users VALUES ('lewsnpbcao', 'lewsnpan0g', 'owner', '54', '54', '54', '2017-05-11 17:18:35');
INSERT INTO m_work_project_users VALUES ('lewsnpbcap', 'lewsnpan0g', 'member', '1983', '54', '54', '2017-05-11 17:18:35');
INSERT INTO m_work_project_users VALUES ('lewsnpboxs', 'lewsnpan0g', 'member', '1977', '54', '54', '2017-05-11 17:18:35');
INSERT INTO m_work_project_users VALUES ('lhc30cw0sg', 'lhc30cpclc', 'owner', '1977', '54', '54', '2017-05-16 13:16:43');
INSERT INTO m_work_project_users VALUES ('lhc30cwq2o', 'lhc30cpclc', 'member', '1209', '54', '54', '2017-05-16 13:16:43');
INSERT INTO m_work_project_users VALUES ('lhc30cx2ps', 'lhc30cpclc', 'member', '1983', '54', '54', '2017-05-16 13:16:43');
INSERT INTO m_work_project_users VALUES ('lhud0qq5fk', 'lhucz81iio', 'owner', '1984', '54', '54', '2017-05-17 13:33:41');
INSERT INTO m_work_project_users VALUES ('lhud0wqgw0', 'lhud0wm96o', 'owner', '1984', '54', '54', '2017-05-17 13:33:42');
INSERT INTO m_work_project_users VALUES ('lhud13kzk0', 'lhud0wm96o', 'member', '1219', '54', '54', '2017-05-17 13:33:42');
INSERT INTO m_work_project_users VALUES ('lhud13lou8', 'lhucz81iio', 'member', '1219', '54', '54', '2017-05-17 13:33:42');
INSERT INTO m_work_project_users VALUES ('lhud13me4g', 'lhud0wm96o', 'member', '1980', '54', '54', '2017-05-17 13:33:42');
INSERT INTO m_work_project_users VALUES ('lhud13n3eo', 'lhucz81iio', 'member', '1980', '54', '54', '2017-05-17 13:33:42');
INSERT INTO m_work_project_users VALUES ('lhud13ng1s', 'lhud0wm96o', 'member', '54', '54', '54', '2017-05-17 13:33:42');
INSERT INTO m_work_project_users VALUES ('lhud13ohz4', 'lhud0wm96o', 'member', '2050', '54', '54', '2017-05-17 13:33:42');
INSERT INTO m_work_project_users VALUES ('lhud13p79c', 'lhud0wm96o', 'member', '59', '54', '54', '2017-05-17 13:33:42');
INSERT INTO m_work_project_users VALUES ('lhud13pwjk', 'lhud0wm96o', 'member', '1981', '54', '54', '2017-05-17 13:33:42');
INSERT INTO m_work_project_users VALUES ('lhud13qlts', 'lhud0wm96o', 'member', '1207', '54', '54', '2017-05-17 13:33:42');
INSERT INTO m_work_project_users VALUES ('lhud15qy2o', 'lhucz81iio', 'member', '54', '54', '54', '2017-05-17 13:33:43');
INSERT INTO m_work_project_users VALUES ('lhud15s000', 'lhucz81iio', 'member', '2050', '54', '54', '2017-05-17 13:33:43');
INSERT INTO m_work_project_users VALUES ('lhud15spa8', 'lhucz81iio', 'member', '59', '54', '54', '2017-05-17 13:33:43');
INSERT INTO m_work_project_users VALUES ('lhud15tekg', 'lhucz81iio', 'member', '1981', '54', '54', '2017-05-17 13:33:43');
INSERT INTO m_work_project_users VALUES ('lhud15u3uo', 'lhucz81iio', 'member', '1207', '54', '54', '2017-05-17 13:33:43');
INSERT INTO m_work_project_users VALUES ('lhudm02a68', 'lhudm0188w', 'owner', '54', '54', '54', '2017-05-17 13:34:59');
INSERT INTO m_work_project_users VALUES ('lhudqk58u8', 'lhudm0188w', 'member', '1977', '54', '54', '2017-05-17 13:35:16');
INSERT INTO m_work_project_users VALUES ('lhudrawuf4', 'lhudm0188w', 'member', '1983', '54', '54', '2017-05-17 13:35:19');
INSERT INTO m_work_project_users VALUES ('lhvu1vuxhc', 'lhvu1vt69s', 'owner', '1931', '52', '52', '2017-05-17 15:31:06');
INSERT INTO m_work_project_users VALUES ('lhvu1vxdz4', 'lhvu1vt69s', 'member', '1867', '52', '52', '2017-05-17 15:31:06');
INSERT INTO m_work_project_users VALUES ('lhvu1vysjk', 'lhvu1vt69s', 'member', '1864', '52', '52', '2017-05-17 15:31:06');
INSERT INTO m_work_project_users VALUES ('lhvuh9xuyo', 'lhvuh9wt1c', 'owner', '54', '54', '54', '2017-05-17 15:32:03');
INSERT INTO m_work_project_users VALUES ('lhvuh9z9j4', 'lhvuh9wt1c', 'member', '1209', '54', '54', '2017-05-17 15:32:03');
INSERT INTO m_work_project_users VALUES ('lhvuha0bgg', 'lhvuh9wt1c', 'member', '1983', '54', '54', '2017-05-17 15:32:03');
INSERT INTO m_work_project_users VALUES ('lhvvzj588w', 'lhvvzj46bk', 'owner', '54', '54', '54', '2017-05-17 15:35:23');
INSERT INTO m_work_project_users VALUES ('lhvvzj6zgg', 'lhvvzj46bk', 'member', '1209', '54', '54', '2017-05-17 15:35:23');
INSERT INTO m_work_project_users VALUES ('lhvvzj81ds', 'lhvvzj46bk', 'member', '1983', '54', '54', '2017-05-17 15:35:23');
INSERT INTO m_work_project_users VALUES ('lhvw3cz7cw', 'lhvw3cy5fk', 'owner', '54', '54', '54', '2017-05-17 15:35:38');
INSERT INTO m_work_project_users VALUES ('lhvw3d0lxc', 'lhvw3cy5fk', 'member', '1209', '54', '54', '2017-05-17 15:35:38');
INSERT INTO m_work_project_users VALUES ('lhvw3d1nuo', 'lhvw3cy5fk', 'member', '1983', '54', '54', '2017-05-17 15:35:38');
INSERT INTO m_work_project_users VALUES ('lhvwothfy8', 'lhvwotge0w', 'owner', '52', '52', '52', '2017-05-17 15:36:57');
INSERT INTO m_work_project_users VALUES ('lhvwotjjsw', 'lhvwotge0w', 'member', '58', '52', '52', '2017-05-17 15:36:57');
INSERT INTO m_work_project_users VALUES ('lhvwotkydc', 'lhvwotge0w', 'member', '55', '52', '52', '2017-05-17 15:36:57');
INSERT INTO m_work_project_users VALUES ('lhvxsna9z4', 'lhvxsn981s', 'owner', '54', '54', '54', '2017-05-17 15:39:24');
INSERT INTO m_work_project_users VALUES ('lhvxsndfr4', 'lhvxsn981s', 'member', '1209', '54', '54', '2017-05-17 15:39:24');
INSERT INTO m_work_project_users VALUES ('lhvxsnehog', 'lhvxsn981s', 'member', '1059', '54', '54', '2017-05-17 15:39:24');
INSERT INTO m_work_project_users VALUES ('lhw6r7lczk', 'lhw6r7knpc', 'owner', '1931', '52', '52', '2017-05-17 15:59:14');
INSERT INTO m_work_project_users VALUES ('lhw6r7meww', 'lhw6r7knpc', 'member', '1892', '52', '52', '2017-05-17 15:59:14');
INSERT INTO m_work_project_users VALUES ('lhwb0xidxc', 'lhwb0xhon4', 'owner', '1906', '52', '52', '2017-05-17 16:08:41');
INSERT INTO m_work_project_users VALUES ('lhwb0xk54w', 'lhwb0xhon4', 'member', '1934', '52', '52', '2017-05-17 16:08:41');
INSERT INTO m_work_project_users VALUES ('lhwb1vmeww', 'lhwb1vlczk', 'owner', '1906', '52', '52', '2017-05-17 16:08:45');
INSERT INTO m_work_project_users VALUES ('lhwb1vngu8', 'lhwb1vlczk', 'member', '1934', '52', '52', '2017-05-17 16:08:45');
INSERT INTO m_work_project_users VALUES ('lhwb20yups', 'lhwb20xssg', 'owner', '1906', '52', '52', '2017-05-17 16:08:45');
INSERT INTO m_work_project_users VALUES ('lhwb20zwn4', 'lhwb20xssg', 'member', '1934', '52', '52', '2017-05-17 16:08:45');
INSERT INTO m_work_project_users VALUES ('lhwb22u9z4', 'lhwb22tkow', 'owner', '1906', '52', '52', '2017-05-17 16:08:46');
INSERT INTO m_work_project_users VALUES ('lhwb22umm8', 'lhwb22tkow', 'member', '1934', '52', '52', '2017-05-17 16:08:46');
INSERT INTO m_work_project_users VALUES ('lhwb24pp8g', 'lhwb24ozy8', 'owner', '1906', '52', '52', '2017-05-17 16:08:46');
INSERT INTO m_work_project_users VALUES ('lhwb24qr5s', 'lhwb24ozy8', 'member', '1934', '52', '52', '2017-05-17 16:08:46');
INSERT INTO m_work_project_users VALUES ('lhwb26dr0g', 'lhwb26dedc', 'owner', '1906', '52', '52', '2017-05-17 16:08:46');
INSERT INTO m_work_project_users VALUES ('lhwb26egao', 'lhwb26dedc', 'member', '1934', '52', '52', '2017-05-17 16:08:46');
INSERT INTO m_work_project_users VALUES ('lhwb2c5zpc', 'lhwb2c5af4', 'owner', '1906', '52', '52', '2017-05-17 16:08:47');
INSERT INTO m_work_project_users VALUES ('lhwb2ca7eo', 'lhwb2c5af4', 'member', '1934', '52', '52', '2017-05-17 16:08:47');
INSERT INTO m_work_project_users VALUES ('lhwb2ccb9c', 'lhwb2c6ozk', 'owner', '1906', '52', '52', '2017-05-17 16:08:47');
INSERT INTO m_work_project_users VALUES ('lhwb2cdpts', 'lhwb2c6ozk', 'member', '1934', '52', '52', '2017-05-17 16:08:47');
INSERT INTO m_work_project_users VALUES ('lhwb37r2f4', 'lhwb37o9a8', 'owner', '1906', '52', '52', '2017-05-17 16:08:50');
INSERT INTO m_work_project_users VALUES ('lhwb37s4cg', 'lhwb37o9a8', 'member', '1934', '52', '52', '2017-05-17 16:08:50');
INSERT INTO m_work_project_users VALUES ('lhwb3akwlc', 'lhwb3akjy8', 'owner', '1906', '52', '52', '2017-05-17 16:08:50');
INSERT INTO m_work_project_users VALUES ('lhwb3alyio', 'lhwb3akjy8', 'member', '1934', '52', '52', '2017-05-17 16:08:50');
INSERT INTO m_work_project_users VALUES ('lhwb4283y8', 'lhwb427eo0', 'owner', '1906', '52', '52', '2017-05-17 16:08:53');
INSERT INTO m_work_project_users VALUES ('lhwb4295vk', 'lhwb427eo0', 'member', '1934', '52', '52', '2017-05-17 16:08:53');
INSERT INTO m_work_project_users VALUES ('lhwcb13nr4', 'lhwcb13b40', 'owner', '1892', '52', '52', '2017-05-17 16:11:32');
INSERT INTO m_work_project_users VALUES ('lhwcb152bk', 'lhwcb13b40', 'member', '1867', '52', '52', '2017-05-17 16:11:32');
INSERT INTO m_work_project_users VALUES ('lhwebs7dhc', 'lhwebs6bk0', 'owner', '1867', '52', '52', '2017-05-17 16:16:00');
INSERT INTO m_work_project_users VALUES ('lhwebs8s1s', 'lhwebs6bk0', 'member', '1892', '52', '52', '2017-05-17 16:16:00');
INSERT INTO m_work_project_users VALUES ('lhwjp5qy2o', 'lhwjp5p6v4', 'owner', '1867', '52', '52', '2017-05-17 16:27:54');
INSERT INTO m_work_project_users VALUES ('lhwjp5scn4', 'lhwjp5p6v4', 'member', '1892', '52', '52', '2017-05-17 16:27:54');
INSERT INTO m_work_project_users VALUES ('lhwkwskykg', 'lhwkwsjwn4', 'owner', '1867', '52', '52', '2017-05-17 16:30:35');
INSERT INTO m_work_project_users VALUES ('lhwkwslnuo', 'lhwkwsjwn4', 'member', '1892', '52', '52', '2017-05-17 16:30:35');
INSERT INTO m_work_project_users VALUES ('lhwluzilfk', 'lhwluzhji8', 'owner', '1892', '52', '52', '2017-05-17 16:32:41');
INSERT INTO m_work_project_users VALUES ('lhwluzo7pc', 'lhwluzhji8', 'member', '1867', '52', '52', '2017-05-17 16:32:41');
INSERT INTO m_work_project_users VALUES ('lhxa1ttvy8', 'lhxa1tsu0w', 'owner', '1931', '52', '52', '2017-05-17 17:26:15');
INSERT INTO m_work_project_users VALUES ('lhxa1tuxvk', 'lhxa1tsu0w', 'member', '1892', '52', '52', '2017-05-17 17:26:15');
INSERT INTO m_work_project_users VALUES ('lhxk3efvnk', 'lhxk3eeh34', 'owner', '1906', '52', '52', '2017-05-17 17:48:29');
INSERT INTO m_work_project_users VALUES ('lhxk3eha80', 'lhxk3eeh34', 'member', '1867', '52', '52', '2017-05-17 17:48:29');
INSERT INTO m_work_project_users VALUES ('lhxkj8ipz4', 'lhxkj8i0ow', 'owner', '52', '52', '52', '2017-05-17 17:49:27');
INSERT INTO m_work_project_users VALUES ('lhxkj8jrwg', 'lhxkj8i0ow', 'member', '1892', '52', '52', '2017-05-17 17:49:27');
INSERT INTO m_work_project_users VALUES ('lhxkj8kh6o', 'lhxkj8i0ow', 'member', '1867', '52', '52', '2017-05-17 17:49:27');
INSERT INTO m_work_project_users VALUES ('lhxkj8l6gw', 'lhxkj8i0ow', 'member', '1906', '52', '52', '2017-05-17 17:49:27');
INSERT INTO m_work_project_users VALUES ('liw2gzur5s', 'liw2gztp8g', 'owner', '54', '54', '54', '2017-05-19 15:39:22');
INSERT INTO m_work_project_users VALUES ('liw2gzwidc', 'liw2gztp8g', 'member', '1209', '54', '54', '2017-05-19 15:39:22');
INSERT INTO m_work_project_users VALUES ('liw2gzxkao', 'liw2gztp8g', 'member', '1219', '54', '54', '2017-05-19 15:39:22');
INSERT INTO m_work_project_users VALUES ('liw2gzy9kw', 'liw2gztp8g', 'member', '1900', '54', '54', '2017-05-19 15:39:22');
INSERT INTO m_work_project_users VALUES ('liw4ce5bls', 'liw4ce4mbk', 'owner', '54', '54', '54', '2017-05-19 15:43:30');
INSERT INTO m_work_project_users VALUES ('liw4ce6dj4', 'liw4ce4mbk', 'member', '1209', '54', '54', '2017-05-19 15:43:30');
INSERT INTO m_work_project_users VALUES ('liw4ce7fgg', 'liw4ce4mbk', 'member', '1983', '54', '54', '2017-05-19 15:43:30');
INSERT INTO m_work_project_users VALUES ('liw4ce84qo', 'liw4ce4mbk', 'member', '1977', '54', '54', '2017-05-19 15:43:30');
INSERT INTO m_work_project_users VALUES ('liw51mt4w0', 'liw51ms2yo', 'owner', '54', '54', '54', '2017-05-19 15:45:04');
INSERT INTO m_work_project_users VALUES ('liw51mu6tc', 'liw51ms2yo', 'member', '1209', '54', '54', '2017-05-19 15:45:04');
INSERT INTO m_work_project_users VALUES ('liw51muw3k', 'liw51ms2yo', 'member', '1219', '54', '54', '2017-05-19 15:45:04');
INSERT INTO m_work_project_users VALUES ('liw5vklgqo', 'liw5vkk268', 'owner', '54', '54', '54', '2017-05-19 15:46:54');
INSERT INTO m_work_project_users VALUES ('liw5vkmio0', 'liw5vkk268', 'member', '1209', '54', '54', '2017-05-19 15:46:54');
INSERT INTO m_work_project_users VALUES ('liw5vkn7y8', 'liw5vkk268', 'member', '1983', '54', '54', '2017-05-19 15:46:54');
INSERT INTO m_work_project_users VALUES ('liwc6m99ts', 'liwc6m8kjk', 'owner', '54', '54', '54', '2017-05-19 16:00:52');
INSERT INTO m_work_project_users VALUES ('liwc6mb11c', 'liwc6m8kjk', 'member', '1209', '54', '54', '2017-05-19 16:00:52');
INSERT INTO m_work_project_users VALUES ('liwc6mbqbk', 'liwc6m8kjk', 'member', '1219', '54', '54', '2017-05-19 16:00:52');
INSERT INTO m_work_project_users VALUES ('liwhhka6m8', 'liwhhk9tz4', 'owner', '54', '54', '54', '2017-05-19 16:12:37');
INSERT INTO m_work_project_users VALUES ('liwhhkb8jk', 'liwhhk9tz4', 'member', '1209', '54', '54', '2017-05-19 16:12:37');
INSERT INTO m_work_project_users VALUES ('liwhhkbl6o', 'liwhhk9tz4', 'member', '1900', '54', '54', '2017-05-19 16:12:37');
INSERT INTO m_work_project_users VALUES ('liwhhkcagw', 'liwhhk9tz4', 'member', '1219', '54', '54', '2017-05-19 16:12:37');
INSERT INTO m_work_project_users VALUES ('lmbi8hws8w', 'lmbi8hv11c', 'owner', '54', '1145', '1145', '2017-05-26 11:38:12');
INSERT INTO m_work_project_users VALUES ('lmbi9vb37k', 'lmbi9vaqkg', 'owner', '54', '1145', '1145', '2017-05-26 11:38:17');
INSERT INTO m_work_project_users VALUES ('lmbiagc7b4', 'lmbiagbi0w', 'owner', '54', '1145', '1145', '2017-05-26 11:38:20');
INSERT INTO m_work_project_users VALUES ('lmbiayq5fk', 'lmbiaypg5c', 'owner', '54', '1145', '1145', '2017-05-26 11:38:21');
INSERT INTO m_work_project_users VALUES ('lmbibg9wcg', 'lmbibg9728', 'owner', '54', '1145', '1145', '2017-05-26 11:38:23');
INSERT INTO m_work_project_users VALUES ('lmbibyaups', 'lmbibya5fk', 'owner', '54', '1145', '1145', '2017-05-26 11:38:25');
INSERT INTO m_work_project_users VALUES ('lmbicm6i9s', 'lmbicm5szk', 'owner', '54', '1145', '1145', '2017-05-26 11:38:27');
INSERT INTO m_work_project_users VALUES ('lmbid48ikg', 'lmbid47ta8', 'owner', '54', '1145', '1145', '2017-05-26 11:38:29');
INSERT INTO m_work_project_users VALUES ('lmbidlto1s', 'lmbidlsyrk', 'owner', '54', '1145', '1145', '2017-05-26 11:38:31');
INSERT INTO m_work_project_users VALUES ('lmbied7e2o', 'lmbied6osg', 'owner', '54', '1145', '1145', '2017-05-26 11:38:34');
INSERT INTO m_work_project_users VALUES ('lmbieto5q8', 'lmbietn3sw', 'owner', '54', '1145', '1145', '2017-05-26 11:38:36');
INSERT INTO m_work_project_users VALUES ('lmbif906ww', 'lmbif8zhmo', 'owner', '54', '1145', '1145', '2017-05-26 11:38:37');
INSERT INTO m_work_project_users VALUES ('lmbifplvk0', 'lmbifpl69s', 'owner', '54', '1145', '1145', '2017-05-26 11:38:39');
INSERT INTO m_work_project_users VALUES ('lmbig5girk', 'lmbig5g64g', 'owner', '54', '1145', '1145', '2017-05-26 11:38:41');
INSERT INTO m_work_project_users VALUES ('lmbiglxn28', 'lmbiglwxs0', 'owner', '54', '1145', '1145', '2017-05-26 11:38:42');
INSERT INTO m_work_project_users VALUES ('lmbih2mhhc', 'lmbih2lfk0', 'owner', '54', '1145', '1145', '2017-05-26 11:38:44');
INSERT INTO m_work_project_users VALUES ('lmbihora4g', 'lmbihoqku8', 'owner', '54', '1145', '1145', '2017-05-26 11:38:46');
INSERT INTO m_work_project_users VALUES ('lmbiicwf0g', 'lmbiicvpq8', 'owner', '54', '1145', '1145', '2017-05-26 11:38:49');
INSERT INTO m_work_project_users VALUES ('lmbij2nhts', 'lmbij2msjk', 'owner', '54', '1145', '1145', '2017-05-26 11:38:51');
INSERT INTO m_work_project_users VALUES ('lmbijlvn5s', 'lmbijlul8g', 'owner', '54', '1145', '1145', '2017-05-26 11:38:53');
INSERT INTO m_work_project_users VALUES ('lmbik9kz5s', 'lmbik9k9vk', 'owner', '54', '1145', '1145', '2017-05-26 11:38:56');
INSERT INTO m_work_project_users VALUES ('lmbikxab5s', 'lmbikx9lvk', 'owner', '54', '1145', '1145', '2017-05-26 11:38:58');
INSERT INTO m_work_project_users VALUES ('lmbiljn6kg', 'lmbilj7dog', 'owner', '54', '1145', '1145', '2017-05-26 11:39:00');
INSERT INTO m_work_project_users VALUES ('lmbim7negw', 'lmbim7mp6o', 'owner', '54', '1145', '1145', '2017-05-26 11:39:03');
INSERT INTO m_work_project_users VALUES ('lmbin0a2o0', 'lmbin09dds', 'owner', '54', '1145', '1145', '2017-05-26 11:39:06');
INSERT INTO m_work_project_users VALUES ('lmbini8kjk', 'lmbini7v9c', 'owner', '54', '1145', '1145', '2017-05-26 11:39:08');
INSERT INTO m_work_project_users VALUES ('lru6ajalts', 'lru6aj7sow', 'owner', '54', '54', '54', '2017-06-06 11:34:47');
INSERT INTO m_work_project_users VALUES ('lru6ajaygw', 'lru6aj7sow', 'member', '1900', '54', '54', '2017-06-06 11:34:47');
INSERT INTO m_work_project_users VALUES ('lru6ajbb40', 'lru6aj7sow', 'member', '1977', '54', '54', '2017-06-06 11:34:47');

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
INSERT INTO m_work_signin VALUES ('l7utuzzg8w', '71', '09:30:00', '18:00:00', '1145', '2017-04-27 15:56:47', '1145', '2017-05-10 15:30:27', '1,2,3,4,5', '29.8166458900', '121.5366684800', '100', '2017-01-01 00:00:00', '2017-12-01 00:00:00');
INSERT INTO m_work_signin VALUES ('le8fd3ylfk', '72', '09:00:00', '18:00:00', '1145', '2017-05-10 08:59:07', '1145', '2017-05-10 15:42:57', '1,2,3,4,5', '29.8166458900', '121.5366684800', '100', '2017-01-01 00:00:00', '2017-12-01 00:00:00');

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
INSERT INTO m_work_signin_record VALUES ('2131', '71', '52', '52', '2017-05-10 08:55:17', '52', '2017-05-10 16:26:55', '0', '192.168.1.96', null, 'l7utuzzg8w');
INSERT INTO m_work_signin_record VALUES ('lednb4wpa8', '71', '54', '54', '2017-05-10 08:55:17', '54', '2017-05-10 16:26:56', '0', '192.168.1.96', '', 'l7utuzzg8w');
INSERT INTO m_work_signin_record VALUES ('ledncj4i68', '71', '54', '54', '2017-05-10 15:55:23', '54', '2017-05-10 16:26:56', '1', '192.168.1.96', '', 'l7utuzzg8w');
INSERT INTO m_work_signin_record VALUES ('leektqi1vk', '71', '52', '52', '2017-05-10 17:09:31', '52', '2017-05-10 17:06:27', '1', '192.168.1.96', '', 'l7utuzzg8w');
INSERT INTO m_work_signin_record VALUES ('lf9cfhsrnk', '71', '54', '54', '2017-05-12 10:02:07', '54', '2017-05-12 09:58:54', '0', '192.168.1.96', '', 'l7utuzzg8w');
INSERT INTO m_work_signin_record VALUES ('lf9cogod1c', '71', '54', '54', '2017-05-12 10:02:40', '54', '2017-05-12 09:59:28', '1', '192.168.1.96', '', 'l7utuzzg8w');
INSERT INTO m_work_signin_record VALUES ('lfenhdrs3k', '71', '52', '52', '2017-05-12 17:05:10', '52', '2017-05-12 17:01:56', '0', '192.168.1.77', '', 'l7utuzzg8w');
INSERT INTO m_work_signin_record VALUES ('lfenk037cw', '71', '52', '52', '2017-05-12 17:05:20', '52', '2017-05-12 17:02:06', '1', '192.168.1.77', '', 'l7utuzzg8w');
INSERT INTO m_work_signin_record VALUES ('lhuvrzrfuo', '71', '52', '52', '2017-05-17 14:18:34', '52', '2017-05-17 14:15:13', '0', '192.168.1.96', '', 'l7utuzzg8w');
INSERT INTO m_work_signin_record VALUES ('lidpwgeyv4', '71', '54', '54', '2017-05-18 15:20:09', '54', '2017-05-18 15:16:43', '0', '192.168.1.77', '', 'l7utuzzg8w');
INSERT INTO m_work_signin_record VALUES ('lidpxpnbpc', '71', '54', '54', '2017-05-18 15:20:14', '54', '2017-05-18 15:16:48', '1', '192.168.1.77', '', 'l7utuzzg8w');
INSERT INTO m_work_signin_record VALUES ('liwth2yigw', '71', '54', '54', '2017-05-19 16:42:41', '54', '2017-05-19 16:39:09', '0', '192.168.1.96', '', 'l7utuzzg8w');
INSERT INTO m_work_signin_record VALUES ('liwthwwxz4', '71', '54', '54', '2017-05-19 16:42:44', '54', '2017-05-19 16:39:12', '1', '192.168.1.96', '', 'l7utuzzg8w');
INSERT INTO m_work_signin_record VALUES ('llf87sx9ts', '71', '52', '52', '2017-05-24 16:49:46', '52', '2017-05-24 16:45:50', '0', '192.168.1.96', '', 'l7utuzzg8w');
INSERT INTO m_work_signin_record VALUES ('llf8abh5a8', '71', '52', '52', '2017-05-24 16:49:56', '52', '2017-05-24 16:45:59', '1', '192.168.1.96', '', 'l7utuzzg8w');
INSERT INTO m_work_signin_record VALUES ('llfwvs505c', '71', '54', '54', '2017-05-24 17:44:24', '54', '2017-05-24 17:40:26', '0', '192.168.1.154', '', 'l7utuzzg8w');
INSERT INTO m_work_signin_record VALUES ('llfwwmfdhc', '71', '54', '54', '2017-05-24 17:44:27', '54', '2017-05-24 17:40:30', '1', '192.168.1.154', '', 'l7utuzzg8w');

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
-- Records of m_work_tmpl
-- ----------------------------
INSERT INTO m_work_tmpl VALUES ('1', 'task', '普通任务', null, '1', '1', 'sys', null, '2017-04-21 17:11:45', null, '0', null, null, null, null);
INSERT INTO m_work_tmpl VALUES ('2', 'process', '请假申请单', null, '1', '1', 'sys', null, '2017-04-21 17:11:46', null, '0', null, null, null, null);
INSERT INTO m_work_tmpl VALUES ('3', 'process', '加班申请单', null, '1', '2', 'sys', null, '2017-04-21 17:11:46', null, '0', null, null, null, null);
INSERT INTO m_work_tmpl VALUES ('4', 'crm', '机构信息', null, '1', '1', 'sys', null, '2017-04-21 17:11:46', null, '0', null, null, null, null);
INSERT INTO m_work_tmpl VALUES ('5', 'crm', '机构联系人资料', null, '1', '1', 'sys', null, '2017-04-21 17:11:47', null, '0', null, null, null, null);
INSERT INTO m_work_tmpl VALUES ('l6cea81pmo', '', '普通任2222', '普通任务描述35', '1', '1', 'sys', null, '2017-06-01 17:53:25', null, '0', null, null, null, null);
INSERT INTO m_work_tmpl VALUES ('lhfhxlqi9s', 'process', '购书申请单', '', '1', '1', 'sys', null, '2017-05-16 17:54:59', null, '0', null, null, null, null);
INSERT INTO m_work_tmpl VALUES ('lkbcyn5beo', 'log', '工作日志', '', '1', '1', 'sys', null, '2017-05-22 11:47:52', null, '0', null, null, null, null);
INSERT INTO m_work_tmpl VALUES ('lkswz4ta0w', 'log', '周报', '', '1', '1', 'sys', null, '2017-05-23 11:07:17', null, '0', null, null, null, null);
INSERT INTO m_work_tmpl VALUES ('lksxt5oxds', 'log', '月报', '', '1', '1', 'sys', null, '2017-05-23 11:09:08', null, '0', null, null, null, null);
INSERT INTO m_work_tmpl VALUES ('lkwg456fb4', 'log', '拜访记录', '', '1', '1', 'sys', null, '2017-05-23 15:48:48', null, '0', null, null, null, null);
INSERT INTO m_work_tmpl VALUES ('lkwhkzos8w', 'log', '客户投诉', '', '1', '1', 'sys', null, '2017-05-23 15:52:03', null, '0', null, null, null, null);
INSERT INTO m_work_tmpl VALUES ('lowwr18kqo', 'crm', '合同表', '', '1', '1', '1145', null, '2017-06-16 14:24:04', null, '0', null, '', '', '');
INSERT INTO m_work_tmpl VALUES ('lpgqyn78xs', 'task', 'test', 'sdfsdfsdf', '1', '1', '1145', null, '2017-06-09 17:08:37', null, '1', null, null, null, null);
INSERT INTO m_work_tmpl VALUES ('lpxrpwjy80', '', 'aaaa', 'bbbb', '1', '1', '1145', null, '2017-06-09 15:33:05', null, '1', null, null, null, null);
INSERT INTO m_work_tmpl VALUES ('ltfeq951j4', '', '销售明细', '', '1', '1', '1145', null, '2017-06-16 13:34:39', null, '0', 'lowwr18kqo', '', '', '$(\"[name=ltfeqa0nb4],[name=ltgiyse2gw],[name=ltfeq9zy0w],[name=ltfeqa0ao0]\").bind(\"keypress\",function(){\r\n	var _yearStaPrice = $(\"[name=ltfeqa0nb4]\").val();\r\n        var _price = $(\"[name=ltgiyse2gw]\").val();\r\n	$(\"[name=ltgiysdpts]\").val(Math.floor(parseInt(_price)/parseInt(_yearStaPrice),2));\r\n\r\n        var je = $(\"[name=ltgiysef40]\");\r\n        var years = $(\"[name=ltfeq9zy0w]\").val();\r\n        var usernum = $(\"[name=ltfeqa0ao0]\").val();\r\n        if(years!=\"\"){\r\n	   je.val(Math.floor(parseInt(years)*parseInt(_yearStaPrice),2));\r\n        }else{\r\n	   je.val(Math.floor(parseInt(usernum)*parseInt(_yearStaPrice),2));	\r\n	}\r\n        \r\n        \r\n});');
INSERT INTO m_work_tmpl VALUES ('ltfshwfh1c', 'process', '22', '', '1', '1', '1145', null, '2017-06-09 16:07:56', null, '1', 'lpgqyn78xs', null, null, null);
INSERT INTO m_work_tmpl VALUES ('luxjbhvegw', '', '调查表', '', '1', '1', '1803', null, '2017-06-12 15:51:43', null, '0', '', null, '<span style=\"color:red;font-weight:bold;\">注意：</span>打开<span style=\"color:green\"> 微信->扫一扫 </span>扫描以下二维码打开本小程序体验\r\n<img src=\"http://www.xcx.la/uploads/20170414/8b2e38cbc65cb395504dbe10506fb387.jpg\" border=0 width=\"150\" height=\"150\" />', null);
INSERT INTO m_work_tmpl VALUES ('luya1t258g', 'process', 'test', 'test', '1', '1', '1803', null, '2017-06-16 13:16:00', null, '0', '', 't3', 'test', 'console.log(1);');

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
-- Records of m_work_tmpl_fields
-- ----------------------------
INSERT INTO m_work_tmpl_fields VALUES ('1', '1', '工期', '天', '1', '1', null, null, '2017-05-23 17:20:48', null, 'text', '1', null, null, null, null, '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('11', 'lkwg456fb4', '客户分级', null, '1', '30', null, null, '2017-05-24 17:50:26', null, 'select', '小型客户', null, null, null, '[\"小型客户\",\"中型客户\",\"大型客户\",\"VIP客户\"]', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('12', '4', '客户地区', null, '1', '40', null, null, '2017-04-19 16:04:12', null, 'select', null, null, null, null, null, '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('14', '4', '客户地址', null, '1', '50', null, null, '2017-04-19 16:04:54', null, 'text', null, null, null, null, null, '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('15', 'lkwg456fb4', '销售意向', null, '1', '60', null, null, '2017-05-24 17:50:26', null, 'select', '初步沟通', null, null, null, '[\"初步沟通\",\"见面拜访\",\"确定意向\",\"正式客户\",\"商务洽谈\",\"签约成功\",\"停滞客户\",\"流失客户\"]', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('16', '4', '客户网址', null, '1', '70', null, null, '2017-04-19 16:06:46', null, 'text', null, '512', null, null, null, '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('17', '4', '备注', null, '1', '80', null, null, '2017-04-19 16:06:39', null, 'textarea', null, '1024', null, null, null, '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('18', '5', '姓名', null, '1', '10', null, null, '2017-04-19 16:09:23', null, 'text', null, '32', null, 'Y', null, '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('19', '5', '部门', null, '1', '20', null, null, '2017-04-19 16:07:47', null, 'text', null, '512', null, null, null, '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('2', '2', '请假原因', null, '1', '1', null, null, '2017-04-19 16:09:07', null, 'textarea', null, null, null, 'Y', null, '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('20', '5', '职务', null, '1', '30', null, null, '2017-04-19 16:07:49', null, 'text', null, '512', null, null, null, '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('21', '5', '手机', null, '1', '40', null, null, '2017-05-23 17:20:48', null, 'text', null, '512', null, null, null, '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('22', '5', '座机', null, '1', '50', null, null, '2017-04-19 16:08:51', null, 'text', null, '512', null, null, null, '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('23', '5', '传真', null, '1', '60', null, null, '2017-04-24 10:08:09', null, 'text', null, '512', null, null, null, '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('24', '5', 'QQ', null, '1', '70', null, null, '2017-05-23 17:20:48', null, 'text', null, '512', null, null, null, '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('25', '5', '邮箱', null, '1', '80', null, null, '2017-04-19 16:07:52', null, 'text', null, '512', null, null, null, '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('26', '5', '地址', null, '1', '90', null, null, '2017-04-19 16:07:53', null, 'text', null, '512', null, null, null, '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('27', '5', '性别', null, '1', '100', null, null, '2017-04-19 16:08:12', null, 'select', null, null, null, null, '[\"男\",\"女\"]', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('28', '5', '备注', null, '1', '110', null, null, '2017-04-19 16:07:38', null, 'textarea', null, '1024', null, null, null, '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('3', '2', '开始日期', null, '1', '2', null, null, '2017-05-24 17:10:40', null, 'date', '2017-01-04', null, null, null, null, '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('4', '2', '结束日期', null, '1', '3', null, null, '2017-05-24 17:10:39', null, 'date', '2017-03-04', null, null, null, null, '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('5', '2', '请假类别', null, '1', '4', null, null, '2017-04-26 14:42:36', null, 'select', '病假', null, null, 'Y', '[\"事假\",\"病假\",\"婚假\",\"陪产假\",\"产假\",\"其他\"]', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('6', '3', '任务描述', null, '1', '1', null, null, '2017-04-19 15:23:32', null, 'textarea', null, null, null, null, null, '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('7', '3', '类型', null, '1', '2', null, null, '2017-04-19 16:09:16', null, 'select', '晚上', null, null, 'Y', '[\"晚上\",\"周末\",\"法定节假日\"]', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('8', '3', '小时数', null, '1', '3', null, null, '2017-04-19 16:09:15', null, 'text', '3', null, null, 'Y', null, '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('9', '4', '客户名称', null, '1', '10', null, null, '2017-04-19 16:09:18', null, 'text', null, null, null, 'Y', null, '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('l6cea82rk0', 'l6cea81pmo', '预计工期', '天2', '1', '1', '1145', '1145', '2017-06-01 17:56:38', '2017-06-01 18:01:50', 'text', '1', '512', '0', 'Y', '', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('l6cea83474', 'l6cea81pmo', '其他描述', '1', '1', '2', '1145', '1145', '2017-06-01 17:56:38', '2017-06-01 18:01:50', 'textarea', '', '200', '0', 'Y', '', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('lhfgvjkbuo', 'lhfgvji800', '', '', '1', '1', '1145', '1145', '2017-05-16 17:46:33', '2017-05-16 17:49:49', 'text', '', '512', '0', 'N', '', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('lhfhxlr7k0', 'lhfhxlqi9s', '申请理由', '', '1', '1', '1145', '1145', '2017-05-16 17:48:53', '2017-05-16 17:52:10', 'textarea', '', '512', '0', 'Y', '', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('lhfhxlrk74', 'lhfhxlqi9s', '书名', '', '1', '2', '1145', '1145', '2017-05-16 17:48:53', '2017-05-16 17:52:10', 'textarea', '', '512', '0', 'Y', '', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('lhfhxlrwu8', 'lhfhxlqi9s', '购书网址', '', '1', '3', '1145', '1145', '2017-05-16 17:48:53', '2017-05-16 17:52:10', 'textarea', '', '512', '0', 'N', '', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('lkbcyn6dc0', 'lkbcyn5beo', '项目', '', '1', '1', '1145', '1145', '2017-05-22 15:31:09', '2017-05-22 12:00:13', 'select', '', '512', '0', 'N', 'selectproject', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('lkbgu9c5j4', 'lkbcyn5beo', '标题', '', '1', '2', '1145', '1145', '2017-05-22 11:56:28', '2017-05-22 12:00:13', 'text', '', '1024', '0', 'N', '', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('lkbgu9ci68', 'lkbcyn5beo', '描述', '', '1', '3', '1145', '1145', '2017-05-22 11:56:28', '2017-05-22 12:00:13', 'textarea', '', '2048', '0', 'N', '', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('lkbgu9cutc', 'lkbcyn5beo', '开始时间', '', '1', '4', '1145', '1145', '2017-05-22 15:20:50', '2017-05-22 12:00:13', 'time', '09:00', '1024', '0', 'N', '', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('lkbgu9d7gg', 'lkbcyn5beo', '结束时间', '', '1', '5', '1145', '1145', '2017-05-22 15:20:51', '2017-05-22 12:00:13', 'time', '18:00', '1024', '0', 'N', '', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('lkbgu9dk3k', 'lkbcyn5beo', '发送给', '', '1', '6', '1145', '1145', '2017-05-22 15:24:36', '2017-05-22 12:00:13', 'select', '', '1024', '0', 'N', 'selectmuser', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('lkswz4uby8', 'lkswz4ta0w', '本周完成工作', '', '1', '1', '1145', '1145', '2017-05-23 11:08:55', '2017-05-23 11:12:46', 'textarea', '', '2048', '0', 'N', '', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('lksxpr8e0w', 'lkswz4ta0w', '下周工作计划', '', '1', '2', '1145', '1145', '2017-05-23 11:08:55', '2017-05-23 11:12:46', 'textarea', '', '2048', '0', 'N', '', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('lksxpr8qo0', 'lkswz4ta0w', '需协调与帮助', '', '1', '3', '1145', '1145', '2017-05-23 11:08:55', '2017-05-23 11:12:46', 'textarea', '', '2048', '0', 'N', '', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('lksxpr93b4', 'lkswz4ta0w', '发送给谁', '', '1', '4', '1145', '1145', '2017-05-23 15:37:09', '2017-05-23 11:12:46', 'select', '', '512', '0', 'N', 'selectmuser', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('lksxt5pmo0', 'lksxt5oxds', '本月工作内容', '', '1', '1', '1145', '1145', '2017-05-23 11:10:20', '2017-05-23 11:14:10', 'textarea', '', '2048', '0', 'N', '', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('lksycn0cu8', 'lksxt5oxds', '下月工作计划', '', '1', '2', '1145', '1145', '2017-05-23 11:10:20', '2017-05-23 11:14:10', 'textarea', '', '2048', '0', 'N', '', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('lksycn0phc', 'lksxt5oxds', '需帮助与支持', '', '1', '3', '1145', '1145', '2017-05-23 11:10:20', '2017-05-23 11:14:10', 'textarea', '', '2048', '0', 'N', '', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('lksycn124g', 'lksxt5oxds', '发送给谁', '', '1', '4', '1145', '1145', '2017-05-23 15:37:08', '2017-05-23 11:14:10', 'select', '', '512', '0', 'N', 'selectmuser', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('lkwg4574lc', 'lkwg456fb4', '姓名', '', '1', '1', '1145', '1145', '2017-05-23 15:51:00', '2017-05-23 15:54:52', 'text', '', '512', '0', 'N', '', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('lkwh4113b4', 'lkwg456fb4', '拜访方式', '', '1', '2', '1145', '1145', '2017-05-24 17:52:51', '2017-05-23 15:54:52', 'select', '当面拜访', '512', '0', 'N', '[\"当面拜访\",\"电话拜访\",\"聊天软件拜访\",\"其他\"]', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('lkwh411fy8', 'lkwg456fb4', '主要事宜', '', '1', '3', '1145', '1145', '2017-05-23 15:51:00', '2017-05-23 15:54:52', 'textarea', '', '2048', '0', 'N', '', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('lkwh411fy9', 'lkwg456fb4', '拜访结果', '', '1', '4', '1145', '1145', '2017-05-23 15:51:00', '2017-05-23 15:54:52', 'textarea', '', '2048', '0', 'N', '', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('lkwh411slc', 'lkwg456fb4', '发送给谁', '', '1', '5', '1145', '1145', '2017-05-23 15:51:00', '2017-05-23 15:54:52', 'select', '', '512', '0', 'N', 'selectmuser', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('lkwhkzphj4', 'lkwhkzos8w', '投诉日期', '', '1', '1', '1145', '1145', '2017-05-23 15:53:41', '2017-05-23 15:57:33', 'date', '', '512', '0', 'N', '', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('lkwibkyghs', 'lkwhkzos8w', '客户名称', '', '1', '2', '1145', '1145', '2017-05-23 15:53:41', '2017-05-23 15:57:33', 'text', '', '512', '0', 'N', '', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('lkwibkyght', 'lkwhkzos8w', '投诉内容', '', '1', '3', '1145', '1145', '2017-05-23 15:53:41', '2017-05-23 15:57:33', 'text', '', '512', '0', 'N', '', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('lkwibkyt4w', 'lkwhkzos8w', '处理情况', '', '1', '4', '1145', '1145', '2017-05-23 15:53:41', '2017-05-23 15:57:33', 'text', '', '512', '0', 'N', '', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('lkwibkyt4x', 'lkwhkzos8w', '详细备注', '', '1', '5', '1145', '1145', '2017-05-23 15:53:41', '2017-05-23 15:57:33', 'text', '', '2048', '0', 'N', '', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('lkwibkz5s0', 'lkwhkzos8w', '发送给谁', '', '1', '6', '1145', '1145', '2017-05-23 15:53:41', '2017-05-23 15:57:33', 'select', '', '512', '0', 'N', 'selectmuser', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('lowwr1aby8', 'lowwr18kqo', '预计签约时间', '', '1', '1', '1803', '1803', '2017-06-16 14:24:04', '2017-06-16 14:30:13', 'date', '', '512', '0', 'Y', '', '1', '');
INSERT INTO m_work_tmpl_fields VALUES ('lox1qk9t6o', 'lowwr18kqo', '客户名称', '', '1', '3', '1803', '1803', '2017-06-16 14:24:04', '2017-06-16 14:30:13', 'text', '', '512', '0', 'Y', '', '1', '');
INSERT INTO m_work_tmpl_fields VALUES ('lox1qka5ts', 'lowwr18kqo', '客户联络人', '', '1', '4', '1803', '1803', '2017-06-16 14:24:04', '2017-06-16 14:30:13', 'text', '', '512', '0', 'Y', '', '1', '');
INSERT INTO m_work_tmpl_fields VALUES ('lox1qka5tt', 'lowwr18kqo', '币种', '', '1', '8', '1803', '1803', '2017-06-16 14:24:04', '2017-06-16 14:30:13', 'radio', '人民币', '512', '0', 'Y', '人民币,美元,港币,其他', '1', '');
INSERT INTO m_work_tmpl_fields VALUES ('lox1qkbx1c', 'lowwr18kqo', '合同总金额原币', '', '1', '9', '1803', '1803', '2017-06-16 15:51:25', '2017-06-16 14:30:13', 'number', 'sql:select sum(field_value) num from m_work_tmpl_fields_value where tmpl_id_id in (select id from m_work_tmpl_records where rec_id=:rec_id:) and field_id=\'ltgiysef40\'', '12', '0', 'N', '', '1', 'readonly=\"true\"');
INSERT INTO m_work_tmpl_fields VALUES ('lox1qkcmbk', 'lowwr18kqo', '合同总金额本币 ', '', '1', '10', '1803', '1803', '2017-06-16 14:24:04', '2017-06-16 14:30:13', 'number', '0', '12', '0', 'N', '', '0', '');
INSERT INTO m_work_tmpl_fields VALUES ('lox1qki8lc', 'lowwr18kqo', '所处状态', '', '1', '11', '1803', '1803', '2017-06-16 14:24:04', '2017-06-16 14:30:13', 'select', '', '64', '0', 'N', '已签约,合同审核流程中,商务洽谈,意向', '0', '');
INSERT INTO m_work_tmpl_fields VALUES ('lox1qkil8g', 'lowwr18kqo', '目前进展情况', '', '1', '12', '1803', '1803', '2017-06-16 14:24:04', '2017-06-16 14:30:13', 'text', '', '512', '0', 'N', '', '1', '');
INSERT INTO m_work_tmpl_fields VALUES ('lpglrju5ts', 'l6cea81pmo', '撒打发沙发', '1', '1', '3', '1145', '1145', '2017-06-01 17:56:38', '2017-06-01 18:01:50', 'text', '', '512', '2', 'N', '', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('lpgqyn7y80', 'lpgqyn78xs', '单行测试', '', '1', '1', '1145', '1145', '2017-06-02 16:29:31', '2017-06-02 16:34:48', 'text', '', '4', '1', 'Y', '', '1', null);
INSERT INTO m_work_tmpl_fields VALUES ('lpgqyn8ni8', 'lpgqyn78xs', '多行测试', '', '1', '2', '1145', '1145', '2017-06-02 16:29:31', '2017-06-02 16:34:48', 'textarea', '', '10', '1', 'Y', '', '1', null);
INSERT INTO m_work_tmpl_fields VALUES ('lpu17o8mio', 'lpgqyn78xs', '下拉测试', '', '1', '3', '1145', '1145', '2017-06-02 16:29:31', '2017-06-02 16:34:48', 'select', '', '512', '0', 'Y', '测1,测2,ce3', '1', null);
INSERT INTO m_work_tmpl_fields VALUES ('lpu17o9og0', 'lpgqyn78xs', '单选测试', '', '1', '4', '1145', '1145', '2017-06-02 16:29:31', '2017-06-02 16:34:48', 'radio', '', '512', '0', 'Y', 'a,b,c', '1', null);
INSERT INTO m_work_tmpl_fields VALUES ('lpu17oa134', 'lpgqyn78xs', '多选测试', '', '1', '5', '1145', '1145', '2017-06-02 16:29:31', '2017-06-02 16:34:48', 'checkbox', '', '512', '0', 'N', 'e,f,g,h', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('lpu17oadq8', 'lpgqyn78xs', '日期测试', '', '1', '6', '1145', '1145', '2017-06-02 16:29:31', '2017-06-02 16:34:48', 'date', '', '512', '0', 'N', '', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('lpxrqm9mgw', 'lpxrpwjy80', '1', '', '1', '1', '1145', '1145', '2017-06-02 16:41:44', '2017-06-02 16:47:01', 'text', '', '3', '1', 'Y', '', '1', null);
INSERT INTO m_work_tmpl_fields VALUES ('lpxrqmabr4', 'lpxrpwjy80', '2', '', '1', '2', '1145', '1145', '2017-06-02 16:41:44', '2017-06-02 16:47:01', 'text', '', '7', '1', 'Y', '', '1', null);
INSERT INTO m_work_tmpl_fields VALUES ('lpxrqmaoe8', 'lpxrpwjy80', '3', '', '1', '3', '1145', '1145', '2017-06-02 16:41:44', '2017-06-02 16:47:01', 'text', '', '4', '1', 'Y', '', '1', null);
INSERT INTO m_work_tmpl_fields VALUES ('ltfeq9xhj4', 'ltfeq951j4', '一级成本中心', '', '1', '1', '1803', '1803', '2017-06-16 13:47:03', '2017-06-16 13:53:12', 'select', '', '256', '1', 'Y', 'Data,智能接口,IP(智能信息+),IDB,DCM,IAS,其他', '1', '');
INSERT INTO m_work_tmpl_fields VALUES ('ltfeq9y6tc', 'ltfeq951j4', '产品二级分类', '', '1', '2', '1803', '1803', '2017-06-16 13:47:03', '2017-06-16 13:53:12', 'select', '', '256', '1', 'Y', 'Broker,ETF,智能接口,Excel插件,国债期货,QDP,CDH+,CMDS,交易接口,解析,开发,IRS,FX,FXO,Bond,PC,MM,NCD,CRM,DCS,旗舰店,PC,市场活动,数据,广告,普通帐户,镜像帐户,新华社,中国银行', '1', '');
INSERT INTO m_work_tmpl_fields VALUES ('ltfeq9yjgg', 'ltfeq951j4', '产品三级分类', '', '1', '3', '1803', '1803', '2017-06-16 13:47:03', '2017-06-16 13:53:12', 'select', '', '256', '1', 'Y', 'TP,IoPV,交易接口,CMDS,一期,二期,线下,线上', '1', '');
INSERT INTO m_work_tmpl_fields VALUES ('ltfeq9yw3k', 'ltfeq951j4', '资产类别', '', '1', '4', '1803', '1803', '2017-06-16 13:47:03', '2017-06-16 13:53:12', 'select', '', '256', '1', 'N', '债券行情,债券基本信息,国债期货,利率,外汇,外汇衍生品,同业货币,ABS', '1', '');
INSERT INTO m_work_tmpl_fields VALUES ('ltfeq9z8qo', 'ltfeq951j4', '数据来源', '', '1', '5', '1803', '1803', '2017-06-16 13:47:03', '2017-06-16 13:53:12', 'select', '', '256', '1', 'N', '国利,中诚宝捷思,国际,天津信唐,平安利顺,中诚信,中央国债,中金所,上交所,深交所,Nasdaq,NYSE,Activ,NYMEX,巨灵,宁波', '0', '');
INSERT INTO m_work_tmpl_fields VALUES ('ltfeq9zlds', 'ltfeq951j4', '数据供应商', '', '1', '6', '1803', '1803', '2017-06-16 13:47:03', '2017-06-16 13:53:12', 'text', '', '1024', '1', 'N', '', '0', '');
INSERT INTO m_work_tmpl_fields VALUES ('ltfeq9zy0w', 'ltfeq951j4', '年数', '', '1', '7', '1803', '1803', '2017-06-16 13:47:03', '2017-06-16 13:53:12', 'number', '', '256', '1', 'N', '', '1', '');
INSERT INTO m_work_tmpl_fields VALUES ('ltfeqa0ao0', 'ltfeq951j4', '.用户数量（如按用户收费）', '', '1', '8', '1803', '1803', '2017-06-16 13:47:03', '2017-06-16 13:53:12', 'text', '', '256', '1', 'N', '', '1', '');
INSERT INTO m_work_tmpl_fields VALUES ('ltfeqa0nb4', 'ltfeq951j4', '年度标准价', '', '1', '9', '1803', '1803', '2017-06-16 13:47:03', '2017-06-16 13:53:12', 'number', '', '256', '1', 'N', '', '0', '');
INSERT INTO m_work_tmpl_fields VALUES ('ltfg76hxxc', 'ltfg76gw00', '币种', '', '1', '1', '1145', '1145', '2017-06-09 15:40:17', '2017-06-09 15:45:42', 'select', '', '256', '1', 'Y', '人民币,美元,港币', '1', null);
INSERT INTO m_work_tmpl_fields VALUES ('ltfg76iakg', 'ltfg76gw00', '一级成本中心', '', '1', '2', '1145', '1145', '2017-06-09 15:40:17', '2017-06-09 15:45:42', 'select', '', '256', '1', 'Y', '1,2,3', '1', null);
INSERT INTO m_work_tmpl_fields VALUES ('ltfg76in7k', 'ltfg76gw00', '产品二级分类', '', '1', '3', '1145', '1145', '2017-06-09 15:40:17', '2017-06-09 15:45:42', 'select', '', '256', '1', 'Y', '1,2,3', '1', null);
INSERT INTO m_work_tmpl_fields VALUES ('ltfg76izuo', 'ltfg76gw00', '产品三级分类', '', '1', '4', '1145', '1145', '2017-06-09 15:40:17', '2017-06-09 15:45:42', 'select', '', '256', '1', 'Y', '1,2,3', '1', null);
INSERT INTO m_work_tmpl_fields VALUES ('ltfg76jchs', 'ltfg76gw00', '资产类别', '', '1', '5', '1145', '1145', '2017-06-09 15:40:17', '2017-06-09 15:45:42', 'select', '', '256', '1', 'N', '1,2,3', '1', null);
INSERT INTO m_work_tmpl_fields VALUES ('ltfg76jp4w', 'ltfg76gw00', '数据来源', '', '1', '6', '1145', '1145', '2017-06-09 15:40:17', '2017-06-09 15:45:42', 'text', '', '256', '1', 'N', '', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('ltfg76k1s0', 'ltfg76gw00', '数据供应商', '', '1', '7', '1145', '1145', '2017-06-09 15:40:17', '2017-06-09 15:45:42', 'text', '', '256', '1', 'N', '1,2,3', '0', null);
INSERT INTO m_work_tmpl_fields VALUES ('ltfg76k1s1', 'ltfg76gw00', '年数', '', '1', '8', '1145', '1145', '2017-06-09 15:40:17', '2017-06-09 15:45:42', 'number', '', '12', '1', 'N', '', '1', null);
INSERT INTO m_work_tmpl_fields VALUES ('ltfg76kef4', 'ltfg76gw00', '金额', '', '1', '9', '1145', '1145', '2017-06-09 15:40:17', '2017-06-09 15:45:42', 'number', '', '12', '1', 'N', '', '1', null);
INSERT INTO m_work_tmpl_fields VALUES ('ltfg76kr28', 'ltfg76gw00', '税金承担方', '', '1', '10', '1145', '1145', '2017-06-09 15:40:17', '2017-06-09 15:45:42', 'text', '', '256', '1', 'N', '', '1', null);
INSERT INTO m_work_tmpl_fields VALUES ('ltfshxdjb4', 'ltfshwfh1c', 'a', '', '1', '1', '1145', '1145', '2017-06-09 16:07:31', '2017-06-09 16:12:56', 'text', '', '256', '1', 'Y', '', '1', null);
INSERT INTO m_work_tmpl_fields VALUES ('ltfshxel8g', 'ltfshwfh1c', 'aa', '', '1', '2', '1145', '1145', '2017-06-09 16:07:31', '2017-06-09 16:12:56', 'text', '', '256', '1', 'Y', '', '1', null);
INSERT INTO m_work_tmpl_fields VALUES ('ltgc93rx8g', 'lowwr18kqo', '客户手机/电话', '', '1', '5', '1803', '1803', '2017-06-16 14:24:04', '2017-06-16 14:30:13', 'text', '', '512', '1', 'N', '', '1', '');
INSERT INTO m_work_tmpl_fields VALUES ('ltgclg2txc', 'lowwr18kqo', '客户经理', '', '1', '6', '1803', '1803', '2017-06-16 14:24:04', '2017-06-16 14:30:13', 'text', '', '256', '1', 'N', '', '1', '');
INSERT INTO m_work_tmpl_fields VALUES ('ltgclg2txd', 'lowwr18kqo', '销售专员', '', '1', '7', '1803', '1803', '2017-06-16 14:24:04', '2017-06-16 14:30:13', 'text', '', '256', '1', 'N', '', '1', '');
INSERT INTO m_work_tmpl_fields VALUES ('ltgd1b8av4', 'lowwr18kqo', '销售合同编号', '', '1', '2', '1803', '1803', '2017-06-16 14:24:04', '2017-06-16 14:30:13', 'text', '', '256', '1', 'N', '', '1', '');
INSERT INTO m_work_tmpl_fields VALUES ('ltgfh5bo5c', 'lowwr18kqo', '是否为以前年度续约', '', '1', '13', '1803', '1803', '2017-06-16 14:24:04', '2017-06-16 14:30:13', 'select', '', '256', '1', 'N', '是,否', '1', '');
INSERT INTO m_work_tmpl_fields VALUES ('ltgiysdpts', 'ltfeq951j4', '折扣率', '', '1', '10', '1803', '1803', '2017-06-16 13:47:03', '2017-06-16 13:53:12', 'number', '', '256', '1', 'N', '', '0', 'readonly=\"true\"');
INSERT INTO m_work_tmpl_fields VALUES ('ltgiyse2gw', 'ltfeq951j4', '实际单价', '', '1', '11', '1803', '1803', '2017-06-16 13:47:03', '2017-06-16 13:53:12', 'number', '', '256', '1', 'N', '', '0', '');
INSERT INTO m_work_tmpl_fields VALUES ('ltgiysef40', 'ltfeq951j4', '金额（金额为年数*实际单价或者用户数量*实际单价）', '', '1', '12', '1803', '1803', '2017-06-16 13:47:03', '2017-06-16 13:53:12', 'number', '', '256', '1', 'N', '', '0', 'readonly=\"true\"');
INSERT INTO m_work_tmpl_fields VALUES ('ltgiysef41', 'ltfeq951j4', '税金承担方', '', '1', '13', '1803', '1803', '2017-06-16 13:47:03', '2017-06-16 13:53:12', 'select', '', '256', '1', 'N', '森浦,客户', '1', '');
INSERT INTO m_work_tmpl_fields VALUES ('luxjbhx5og', 'luxjbhvegw', '你最喜欢的功能？', '', '1', '1', '1803', '1803', '2017-06-12 15:46:01', '2017-06-12 15:51:41', 'checkbox', '', '256', '1', 'Y', '日历,事件提醒,记账,备忘录,名片,其他', '1', null);
INSERT INTO m_work_tmpl_fields VALUES ('luxjbhxuyo', 'luxjbhvegw', '你希望有的功能？', '', '1', '2', '1803', '1803', '2017-06-12 15:46:01', '2017-06-12 15:51:41', 'radio', '', '256', '1', 'Y', '搜索功能,电子名片,其他', '1', null);
INSERT INTO m_work_tmpl_fields VALUES ('luxjbhxuyp', 'luxjbhvegw', '你还需要什么功能？', '', '1', '3', '1803', '1803', '2017-06-12 15:46:01', '2017-06-12 15:51:41', 'textarea', '', '2048', '1', 'Y', '', '1', null);
INSERT INTO m_work_tmpl_fields VALUES ('luxjbhy7ls', 'luxjbhvegw', '最需要的分享功能？', '', '1', '4', '1803', '1803', '2017-06-12 15:46:01', '2017-06-12 15:51:41', 'select', '', '256', '1', 'Y', '记事记账,电子名片,备忘录事件', '1', null);
INSERT INTO m_work_tmpl_fields VALUES ('luxjbhyk8w', 'luxjbhvegw', '你对界面风格满意么？', '', '1', '5', '1803', '1803', '2017-06-12 15:46:01', '2017-06-12 15:51:41', 'radio', '', '256', '1', 'Y', '非常满意,还满意,不大满意,无法接受', '1', null);
INSERT INTO m_work_tmpl_fields VALUES ('luxjbhyk8x', 'luxjbhvegw', '你对本小程序交互方式满意么？', '', '1', '6', '1803', '1803', '2017-06-12 15:46:01', '2017-06-12 15:51:41', 'radio', '', '256', '1', 'Y', '非常满意,还满意,不大满意,无法接受', '1', null);
INSERT INTO m_work_tmpl_fields VALUES ('luxjbhyww0', 'luxjbhvegw', '你最不能接受的交互方式', '', '1', '7', '1803', '1803', '2017-06-12 15:46:01', '2017-06-12 15:51:41', 'text', '', '1024', '1', 'Y', '', '1', null);
INSERT INTO m_work_tmpl_fields VALUES ('luya1t6cxs', 'luya1t258g', 'test', '', '1', '1', '1803', '1803', '2017-06-16 13:45:14', '2017-06-16 13:51:23', 'text', '', '256', '1', 'Y', '', '1', 'readonly=\"true\"');

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
-- Records of m_work_tmpl_fields_value
-- ----------------------------
INSERT INTO m_work_tmpl_fields_value VALUES ('lrfpgrpm9s', 'lkbcyn5beo', 'lkbcyn6dc0', 'ppppp', '0', 'lrfpgr2fwg', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lrfpgrpyww', 'lkbcyn5beo', 'lkbgu9c5j4', '婆4444', '0', 'lrfpgr2fwg', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lrfpgrqbk0', 'lkbcyn5beo', 'lkbgu9ci68', '婆婆222', '0', 'lrfpgr2fwg', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lrfpgrqo74', 'lkbcyn5beo', 'lkbgu9cutc', '16:26', '0', 'lrfpgr2fwg', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lrfpgrr0u8', 'lkbcyn5beo', 'lkbgu9d7gg', '18:00', '0', 'lrfpgr2fwg', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lrfpgrrdhc', 'lkbcyn5beo', 'lkbgu9dk3k', '1983,1977', '0', 'lrfpgr2fwg', '肖楚凡,陈范斌');
INSERT INTO m_work_tmpl_fields_value VALUES ('lrfrvdt88w', 'lpgqyn78xs', 'lpgqyn7y80', 'asdf', '0', 'lrfrvdpd6o', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lrfrvdtkw0', 'lpgqyn78xs', 'lpgqyn8ni8', 'asdf3333', '0', 'lrfrvdpd6o', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lrfrvdtkw1', 'lpgqyn78xs', 'lpu17o8mio', 'ce3', '0', 'lrfrvdpd6o', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lrfrvdtxj4', 'lpgqyn78xs', 'lpu17o9og0', 'b', '0', 'lrfrvdpd6o', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lrfrvdua68', 'lpgqyn78xs', 'lpu17oa134', 'f,g', '0', 'lrfrvdpd6o', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lrfrvduzgg', 'lpgqyn78xs', 'lpu17oadq8', '2017-06-28', '0', 'lrfrvdpd6o', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lru6h6don4', '1', '1', '1', '0', 'lru6gvj6rk', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lru6qjtx4w', '1', '1', '1', '0', 'lru6qjqeps', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltf6bt2z9c', 'lowwr18kqo', 'lowwr1aby8', '2017-06-07', '0', 'ltf6bspmv4', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltf6bt3bwg', 'lowwr18kqo', 'lox1qk9t6o', '1', '0', 'ltf6bspmv4', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltf6bt3bwh', 'lowwr18kqo', 'lox1qka5ts', '2', '0', 'ltf6bspmv4', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltf6bt3ojk', 'lowwr18kqo', 'lox1qka5tt', '人民币', '0', 'ltf6bspmv4', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltf6bt416o', 'lowwr18kqo', 'lox1qkbx1c', '0', '0', 'ltf6bspmv4', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltf6bt4dts', 'lowwr18kqo', 'lox1qkcmbk', '0', '0', 'ltf6bspmv4', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltf6bt4dtt', 'lowwr18kqo', 'lox1qkcyyo', '0', '0', 'ltf6bspmv4', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltf6bt4qgw', 'lowwr18kqo', 'lox1qkdo8w', '0', '0', 'ltf6bspmv4', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltf6bt4qgx', 'lowwr18kqo', 'lox1qke0w0', '0', '0', 'ltf6bspmv4', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltf6bt5340', 'lowwr18kqo', 'lox1qkf2tc', 'IP（智能信息+）', '0', 'ltf6bspmv4', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltf6bt5fr4', 'lowwr18kqo', 'lox1qkffgg', 'ETF', '0', 'ltf6bspmv4', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltf6bt5fr5', 'lowwr18kqo', 'lox1qkffgh', 'IoPV', '0', 'ltf6bspmv4', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltf6bt5se8', 'lowwr18kqo', 'lox1qkfs3k', '债券基本信息', '0', 'ltf6bspmv4', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltf6bt651c', 'lowwr18kqo', 'lox1qkfs3l', '中诚宝捷思', '0', 'ltf6bspmv4', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltf6bt651d', 'lowwr18kqo', 'lox1qkgu0w', '1', '0', 'ltf6bspmv4', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltf6bt6hog', 'lowwr18kqo', 'lox1qkgu0x', '', '0', 'ltf6bspmv4', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltf6bt6hoh', 'lowwr18kqo', 'lox1qkhjb4', '', '0', 'ltf6bspmv4', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltf6bt6ubk', 'lowwr18kqo', 'lox1qkhjb5', '', '0', 'ltf6bspmv4', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltf6bt6ubl', 'lowwr18kqo', 'lox1qkhvy8', '客户', '0', 'ltf6bspmv4', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltf6bt76yo', 'lowwr18kqo', 'lox1qki8lc', '合同审核流程中', '0', 'ltf6bspmv4', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltf6bt7jls', 'lowwr18kqo', 'lox1qki8ld', '否', '0', 'ltf6bspmv4', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltf6bt7jlt', 'lowwr18kqo', 'lox1qkil8g', '', '0', 'ltf6bspmv4', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg0wwxxj4', 'ltfeq951j4', 'ltfeq9wfls', '美元', '0', 'ltg0wwh2ps', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg0wwymtc', 'ltfeq951j4', 'ltfeq9xhj4', '1', '0', 'ltg0wwh2ps', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg0wwyzgg', 'ltfeq951j4', 'ltfeq9y6tc', '2', '0', 'ltg0wwh2ps', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg0wwzc3k', 'ltfeq951j4', 'ltfeq9yjgg', '3', '0', 'ltg0wwh2ps', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg0wx01ds', 'ltfeq951j4', 'ltfeq9yw3k', '1', '0', 'ltg0wwh2ps', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg0wx01dt', 'ltfeq951j4', 'ltfeq9z8qo', 'a', '0', 'ltg0wwh2ps', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg0wx0e0w', 'ltfeq951j4', 'ltfeq9zlds', 'b', '0', 'ltg0wwh2ps', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg0wx0qo0', 'ltfeq951j4', 'ltfeq9zy0w', '2', '0', 'ltg0wwh2ps', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg0wx13b4', 'ltfeq951j4', 'ltfeqa0ao0', '3', '0', 'ltg0wwh2ps', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg0wx13b5', 'ltfeq951j4', 'ltfeqa0nb4', '32', '0', 'ltg0wwh2ps', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg58viebk', 'ltfeq951j4', 'ltfeq9wfls', '美元', '0', 'ltg58v6ghs', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg58vj3ls', 'ltfeq951j4', 'ltfeq9xhj4', '1', '0', 'ltg58v6ghs', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg58vjg8w', 'ltfeq951j4', 'ltfeq9y6tc', '2', '0', 'ltg58v6ghs', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg58vjsw0', 'ltfeq951j4', 'ltfeq9yjgg', '2', '0', 'ltg58v6ghs', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg58vk5j4', 'ltfeq951j4', 'ltfeq9yw3k', '2', '0', 'ltg58v6ghs', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg58vki68', 'ltfeq951j4', 'ltfeq9z8qo', '1', '0', 'ltg58v6ghs', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg58vki69', 'ltfeq951j4', 'ltfeq9zlds', '2', '0', 'ltg58v6ghs', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg58vkutc', 'ltfeq951j4', 'ltfeq9zy0w', '3', '0', 'ltg58v6ghs', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg58vl7gg', 'ltfeq951j4', 'ltfeqa0ao0', '4', '0', 'ltg58v6ghs', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg58vlk3k', 'ltfeq951j4', 'ltfeqa0nb4', '5', '0', 'ltg58v6ghs', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg7xm1khs', 'lowwr18kqo', 'lowwr1aby8', '2017-06-22', '0', 'ltg7xlz400', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg7xm1kht', 'lowwr18kqo', 'lox1qk9t6o', 'bbbb', '0', 'ltg7xlz400', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg7xm1x4w', 'lowwr18kqo', 'lox1qka5ts', '22', '0', 'ltg7xlz400', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg7xm29s0', 'lowwr18kqo', 'lox1qka5tt', '美元', '0', 'ltg7xlz400', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg7xm29s1', 'lowwr18kqo', 'lox1qkbx1c', '0', '0', 'ltg7xlz400', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg7xm2z28', 'lowwr18kqo', 'lox1qkcmbk', '0', '0', 'ltg7xlz400', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg7xm3bpc', 'lowwr18kqo', 'lox1qkcyyo', '0', '0', 'ltg7xlz400', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg7xm3ocg', 'lowwr18kqo', 'lox1qkdo8w', '0', '0', 'ltg7xlz400', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg7xm3och', 'lowwr18kqo', 'lox1qke0w0', '0', '0', 'ltg7xlz400', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg7xm40zk', 'lowwr18kqo', 'lox1qkf2tc', null, '0', 'ltg7xlz400', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg7xm4q9s', 'lowwr18kqo', 'lox1qkffgg', null, '0', 'ltg7xlz400', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg7xm52ww', 'lowwr18kqo', 'lox1qkffgh', null, '0', 'ltg7xlz400', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg7xm52wx', 'lowwr18kqo', 'lox1qkfs3k', null, '0', 'ltg7xlz400', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg7xm5fk0', 'lowwr18kqo', 'lox1qkfs3l', null, '0', 'ltg7xlz400', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg7xm5fk1', 'lowwr18kqo', 'lox1qkgu0w', '1', '0', 'ltg7xlz400', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg7xm5s74', 'lowwr18kqo', 'lox1qkgu0x', '', '0', 'ltg7xlz400', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg7xm64u8', 'lowwr18kqo', 'lox1qkhjb4', '', '0', 'ltg7xlz400', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg7xm64u9', 'lowwr18kqo', 'lox1qkhjb5', '', '0', 'ltg7xlz400', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg7xm6hhc', 'lowwr18kqo', 'lox1qkhvy8', null, '0', 'ltg7xlz400', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg7xm6u4g', 'lowwr18kqo', 'lox1qki8lc', null, '0', 'ltg7xlz400', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg7xm6u4h', 'lowwr18kqo', 'lox1qki8ld', null, '0', 'ltg7xlz400', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg7xm76rk', 'lowwr18kqo', 'lox1qkil8g', '', '0', 'ltg7xlz400', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg9lnoav4', 'ltfeq951j4', 'ltfeq9wfls', '美元', '0', 'ltg9lnlhq8', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg9lnoni8', 'ltfeq951j4', 'ltfeq9xhj4', '2', '0', 'ltg9lnlhq8', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg9lnp05c', 'ltfeq951j4', 'ltfeq9y6tc', '2', '0', 'ltg9lnlhq8', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg9lnpcsg', 'ltfeq951j4', 'ltfeq9yjgg', '2', '0', 'ltg9lnlhq8', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg9lnppfk', 'ltfeq951j4', 'ltfeq9yw3k', '1', '0', 'ltg9lnlhq8', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg9lnq22o', 'ltfeq951j4', 'ltfeq9z8qo', 'bccff', '0', 'ltg9lnlhq8', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg9lnqeps', 'ltfeq951j4', 'ltfeq9zlds', 'asdf3w33', '0', 'ltg9lnlhq8', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg9lnqrcw', 'ltfeq951j4', 'ltfeq9zy0w', '2', '0', 'ltg9lnlhq8', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg9lnr400', 'ltfeq951j4', 'ltfeqa0ao0', '', '0', 'ltg9lnlhq8', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltg9lnrgn4', 'ltfeq951j4', 'ltfeqa0nb4', '', '0', 'ltg9lnlhq8', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltga29ecjk', 'ltfeq951j4', 'ltfeq9wfls', '港币', '0', 'ltga29bjeo', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltga29ep6o', 'ltfeq951j4', 'ltfeq9xhj4', '2', '0', 'ltga29bjeo', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltga29f1ts', 'ltfeq951j4', 'ltfeq9y6tc', '2', '0', 'ltga29bjeo', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltga29f1tt', 'ltfeq951j4', 'ltfeq9yjgg', '1', '0', 'ltga29bjeo', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltga29fegw', 'ltfeq951j4', 'ltfeq9yw3k', '1', '0', 'ltga29bjeo', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltga29fr40', 'ltfeq951j4', 'ltfeq9z8qo', 'bewr', '0', 'ltga29bjeo', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltga29g3r4', 'ltfeq951j4', 'ltfeq9zlds', '', '0', 'ltga29bjeo', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltga29g3r5', 'ltfeq951j4', 'ltfeq9zy0w', '', '0', 'ltga29bjeo', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltga29gge8', 'ltfeq951j4', 'ltfeqa0ao0', '', '0', 'ltga29bjeo', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltga29gge9', 'ltfeq951j4', 'ltfeqa0nb4', '', '0', 'ltga29bjeo', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltggqpd7gg', 'lowwr18kqo', 'lowwr1aby8', '2017-06-07', '0', 'ltggqpaqyo', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltggqpdk3k', 'lowwr18kqo', 'ltgd1b8av4', 'aaaa', '0', 'ltggqpaqyo', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltggqpdwqo', 'lowwr18kqo', 'lox1qk9t6o', 'dd', '0', 'ltggqpaqyo', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltggqpdwqp', 'lowwr18kqo', 'lox1qka5ts', 'ff', '0', 'ltggqpaqyo', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltggqpe9ds', 'lowwr18kqo', 'ltgc93rx8g', '2342', '0', 'ltggqpaqyo', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltggqpem0w', 'lowwr18kqo', 'ltgclg2txc', 'adf', '0', 'ltggqpaqyo', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltggqpem0x', 'lowwr18kqo', 'ltgclg2txd', 'sdf', '0', 'ltggqpaqyo', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltggqpeyo0', 'lowwr18kqo', 'lox1qka5tt', '人民币', '0', 'ltggqpaqyo', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltggqpeyo1', 'lowwr18kqo', 'lox1qkbx1c', '0', '0', 'ltggqpaqyo', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltggqpfbb4', 'lowwr18kqo', 'lox1qkcmbk', '0', '0', 'ltggqpaqyo', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltggqpfny8', 'lowwr18kqo', 'lox1qki8lc', '已签约', '0', 'ltggqpaqyo', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltggqpfny9', 'lowwr18kqo', 'lox1qkil8g', 'dd', '0', 'ltggqpaqyo', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltggqpg0lc', 'lowwr18kqo', 'ltgfh5bo5c', '是', '0', 'ltggqpaqyo', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltggv36dc0', 'lowwr18kqo', 'lowwr1aby8', '2017-06-22', '0', 'ltggv34m4g', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltggv36pz4', 'lowwr18kqo', 'ltgd1b8av4', 'bbb', '0', 'ltggv34m4g', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltggv372m8', 'lowwr18kqo', 'lox1qk9t6o', 'adf', '0', 'ltggv34m4g', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltggv372m9', 'lowwr18kqo', 'lox1qka5ts', 'asdf', '0', 'ltggv34m4g', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltggv37f9c', 'lowwr18kqo', 'ltgc93rx8g', 'adsf', '0', 'ltggv34m4g', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltggv37rwg', 'lowwr18kqo', 'ltgclg2txc', 'asdf', '0', 'ltggv34m4g', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltggv37rwh', 'lowwr18kqo', 'ltgclg2txd', 'asdf', '0', 'ltggv34m4g', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltggv384jk', 'lowwr18kqo', 'lox1qka5tt', '人民币', '0', 'ltggv34m4g', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltggv38h6o', 'lowwr18kqo', 'lox1qkbx1c', '12', '0', 'ltggv34m4g', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltggv38h6p', 'lowwr18kqo', 'lox1qkcmbk', '123', '0', 'ltggv34m4g', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltggv38tts', 'lowwr18kqo', 'lox1qki8lc', '合同审核流程中', '0', 'ltggv34m4g', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltggv38ttt', 'lowwr18kqo', 'lox1qkil8g', '', '0', 'ltggv34m4g', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltggv396gw', 'lowwr18kqo', 'ltgfh5bo5c', '是', '0', 'ltggv34m4g', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjd6yr5s', 'lowwr18kqo', 'lowwr1aby8', '2017-06-07', '0', 'ltgjd6wao0', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjd6yr5t', 'lowwr18kqo', 'ltgd1b8av4', 'aaaa', '0', 'ltgjd6wao0', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjd6z3sw', 'lowwr18kqo', 'lox1qk9t6o', 'aaaa', '0', 'ltgjd6wao0', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjd6z3sx', 'lowwr18kqo', 'lox1qka5ts', 'aaaaa', '0', 'ltgjd6wao0', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjd6zgg0', 'lowwr18kqo', 'ltgc93rx8g', '', '0', 'ltgjd6wao0', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjd6zgg1', 'lowwr18kqo', 'ltgclg2txc', '', '0', 'ltgjd6wao0', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjd6zt34', 'lowwr18kqo', 'ltgclg2txd', '', '0', 'ltgjd6wao0', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjd6zt35', 'lowwr18kqo', 'lox1qka5tt', null, '0', 'ltgjd6wao0', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjd705q8', 'lowwr18kqo', 'lox1qkbx1c', '2', '0', 'ltgjd6wao0', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjd705q9', 'lowwr18kqo', 'lox1qkcmbk', '333355', '0', 'ltgjd6wao0', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjd70idc', 'lowwr18kqo', 'lox1qki8lc', '合同审核流程中', '0', 'ltgjd6wao0', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjd70idd', 'lowwr18kqo', 'lox1qkil8g', '', '0', 'ltgjd6wao0', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjd70v0g', 'lowwr18kqo', 'ltgfh5bo5c', '否', '0', 'ltgjd6wao0', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjewdce8', 'lowwr18kqo', 'lowwr1aby8', '2017-06-22', '0', 'ltgjewaj9c', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjewdp1c', 'lowwr18kqo', 'ltgd1b8av4', 'bbb', '0', 'ltgjewaj9c', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjewdp1d', 'lowwr18kqo', 'lox1qk9t6o', 'bbb', '0', 'ltgjewaj9c', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjewe1og', 'lowwr18kqo', 'lox1qka5ts', 'bbbb', '0', 'ltgjewaj9c', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjeweebk', 'lowwr18kqo', 'ltgc93rx8g', '', '0', 'ltgjewaj9c', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjeweqyo', 'lowwr18kqo', 'ltgclg2txc', '', '0', 'ltgjewaj9c', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjewf3ls', 'lowwr18kqo', 'ltgclg2txd', '', '0', 'ltgjewaj9c', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjewfg8w', 'lowwr18kqo', 'lox1qka5tt', null, '0', 'ltgjewaj9c', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjewfg8x', 'lowwr18kqo', 'lox1qkbx1c', '0', '0', 'ltgjewaj9c', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjewfg8y', 'lowwr18kqo', 'lox1qkcmbk', '0', '0', 'ltgjewaj9c', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjewfsw0', 'lowwr18kqo', 'lox1qki8lc', null, '0', 'ltgjewaj9c', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjewfsw1', 'lowwr18kqo', 'lox1qkil8g', '', '0', 'ltgjewaj9c', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjewg5j4', 'lowwr18kqo', 'ltgfh5bo5c', null, '0', 'ltgjewaj9c', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjjcd62o', 'ltfeq951j4', 'ltfeq9xhj4', '智能接口', '0', 'ltgjjcbev4', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjjcd62p', 'ltfeq951j4', 'ltfeq9y6tc', 'ETF', '0', 'ltgjjcbev4', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjjcdips', 'ltfeq951j4', 'ltfeq9yjgg', 'IoPV', '0', 'ltgjjcbev4', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjjcdvcw', 'ltfeq951j4', 'ltfeq9yw3k', '债券基本信息', '0', 'ltgjjcbev4', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjjcdvcx', 'ltfeq951j4', 'ltfeq9z8qo', '天津信唐', '0', 'ltgjjcbev4', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjjce800', 'ltfeq951j4', 'ltfeq9zlds', 'ss', '0', 'ltgjjcbev4', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjjce801', 'ltfeq951j4', 'ltfeq9zy0w', '222', '0', 'ltgjjcbev4', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjjcekn4', 'ltfeq951j4', 'ltfeqa0ao0', 'aa', '0', 'ltgjjcbev4', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjjcekn5', 'ltfeq951j4', 'ltfeqa0nb4', '-1', '0', 'ltgjjcbev4', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjjcexa8', 'ltfeq951j4', 'ltgiysdpts', '', '0', 'ltgjjcbev4', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjjcexa9', 'ltfeq951j4', 'ltgiyse2gw', '', '0', 'ltgjjcbev4', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjjcf9xc', 'ltfeq951j4', 'ltgiysef40', '', '0', 'ltgjjcbev4', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjjcf9xd', 'ltfeq951j4', 'ltgiysef41', '客户', '0', 'ltgjjcbev4', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjn4ywao', 'ltfeq951j4', 'ltfeq9xhj4', '智能接口', '0', 'ltgjn4x534', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjn4z8xs', 'ltfeq951j4', 'ltfeq9y6tc', '智能接口', '0', 'ltgjn4x534', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjn4zlkw', 'ltfeq951j4', 'ltfeq9yjgg', 'CMDS', '0', 'ltgjn4x534', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjn4zlkx', 'ltfeq951j4', 'ltfeq9yw3k', '国债期货', '0', 'ltgjn4x534', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjn4zy80', 'ltfeq951j4', 'ltfeq9z8qo', '中诚宝捷思', '0', 'ltgjn4x534', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjn50av4', 'ltfeq951j4', 'ltfeq9zlds', '33333', '0', 'ltgjn4x534', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjn50av5', 'ltfeq951j4', 'ltfeq9zy0w', '22', '0', 'ltgjn4x534', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjn50ni8', 'ltfeq951j4', 'ltfeqa0ao0', 'dd', '0', 'ltgjn4x534', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjn50ni9', 'ltfeq951j4', 'ltfeqa0nb4', '', '0', 'ltgjn4x534', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjn5105c', 'ltfeq951j4', 'ltgiysdpts', '', '0', 'ltgjn4x534', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjn51csg', 'ltfeq951j4', 'ltgiyse2gw', '', '0', 'ltgjn4x534', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjn51pfk', 'ltfeq951j4', 'ltgiysef40', '', '0', 'ltgjn4x534', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgjn51pfl', 'ltfeq951j4', 'ltgiysef41', null, '0', 'ltgjn4x534', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgo0os074', 'lowwr18kqo', 'lowwr1aby8', '2017-06-07', '0', 'ltgo0od98g', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgo0oscu8', 'lowwr18kqo', 'ltgd1b8av4', 'cccc', '0', 'ltgo0od98g', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgo0ot24g', 'lowwr18kqo', 'lox1qk9t6o', 'dfdf', '0', 'ltgo0od98g', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgo0oterk', 'lowwr18kqo', 'lox1qka5ts', 'asdf', '0', 'ltgo0od98g', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgo0otreo', 'lowwr18kqo', 'ltgc93rx8g', 'asdf', '0', 'ltgo0od98g', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgo0ougow', 'lowwr18kqo', 'ltgclg2txc', 'asdf', '0', 'ltgo0od98g', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgo0ougox', 'lowwr18kqo', 'ltgclg2txd', 'df', '0', 'ltgo0od98g', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgo0outc0', 'lowwr18kqo', 'lox1qka5tt', null, '0', 'ltgo0od98g', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgo0ovim8', 'lowwr18kqo', 'lox1qkbx1c', '0', '0', 'ltgo0od98g', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgo0ovv9c', 'lowwr18kqo', 'lox1qkcmbk', '0', '0', 'ltgo0od98g', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgo0ovv9d', 'lowwr18kqo', 'lox1qki8lc', null, '0', 'ltgo0od98g', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgo0ow7wg', 'lowwr18kqo', 'lox1qkil8g', '', '0', 'ltgo0od98g', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgo0owkjk', 'lowwr18kqo', 'ltgfh5bo5c', null, '0', 'ltgo0od98g', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgoazy800', 'lowwr18kqo', 'lowwr1aby8', '2017-06-07', '0', 'ltgoazwgsg', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgoazykn4', 'lowwr18kqo', 'ltgd1b8av4', 'eee', '0', 'ltgoazwgsg', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgoazykn5', 'lowwr18kqo', 'lox1qk9t6o', 'eee', '0', 'ltgoazwgsg', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgoazyxa8', 'lowwr18kqo', 'lox1qka5ts', 'eee', '0', 'ltgoazwgsg', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgoazz9xc', 'lowwr18kqo', 'ltgc93rx8g', '', '0', 'ltgoazwgsg', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgoazz9xd', 'lowwr18kqo', 'ltgclg2txc', '', '0', 'ltgoazwgsg', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgoazzmkg', 'lowwr18kqo', 'ltgclg2txd', '', '0', 'ltgoazwgsg', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgoazzz7k', 'lowwr18kqo', 'lox1qka5tt', null, '0', 'ltgoazwgsg', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgob00buo', 'lowwr18kqo', 'lox1qkbx1c', '0', '0', 'ltgoazwgsg', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgob00bup', 'lowwr18kqo', 'lox1qkcmbk', '0', '0', 'ltgoazwgsg', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgob00ohs', 'lowwr18kqo', 'lox1qki8lc', null, '0', 'ltgoazwgsg', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgob0114w', 'lowwr18kqo', 'lox1qkil8g', '', '0', 'ltgoazwgsg', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('ltgob0114x', 'lowwr18kqo', 'ltgfh5bo5c', null, '0', 'ltgoazwgsg', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('luuqkhlmv4', 'ltfeq951j4', 'ltfeq9xhj4', 'IP(智能信息+)', '0', 'luuqkhaebk', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('luuqkhmosg', 'ltfeq951j4', 'ltfeq9y6tc', '智能接口', '0', 'luuqkhaebk', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('luuqkhn1fk', 'ltfeq951j4', 'ltfeq9yjgg', 'CMDS', '0', 'luuqkhaebk', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('luuqkhne2o', 'ltfeq951j4', 'ltfeq9yw3k', '国债期货', '0', 'luuqkhaebk', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('luuqkhne2p', 'ltfeq951j4', 'ltfeq9z8qo', '天津信唐', '0', 'luuqkhaebk', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('luuqkho3cw', 'ltfeq951j4', 'ltfeq9zlds', '33', '0', 'luuqkhaebk', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('luuqkho3cx', 'ltfeq951j4', 'ltfeq9zy0w', '4', '0', 'luuqkhaebk', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('luuqkhosn4', 'ltfeq951j4', 'ltfeqa0ao0', '222', '0', 'luuqkhaebk', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('luuqkhp5a8', 'ltfeq951j4', 'ltfeqa0nb4', '', '0', 'luuqkhaebk', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('luuqkhphxc', 'ltfeq951j4', 'ltgiysdpts', '', '0', 'luuqkhaebk', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('luuqkhq77k', 'ltfeq951j4', 'ltgiyse2gw', '', '0', 'luuqkhaebk', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('luuqkhq77l', 'ltfeq951j4', 'ltgiysef40', '', '0', 'luuqkhaebk', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('luuqkhqwhs', 'ltfeq951j4', 'ltgiysef41', null, '0', 'luuqkhaebk', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('luweqr9uyo', 'ltfeq951j4', 'ltfeq9xhj4', 'DCM', '0', 'luweqr6cjk', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('luweqra7ls', 'ltfeq951j4', 'ltfeq9y6tc', null, '0', 'luweqr6cjk', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('luweqrak8w', 'ltfeq951j4', 'ltfeq9yjgg', null, '0', 'luweqr6cjk', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('luweqraww0', 'ltfeq951j4', 'ltfeq9yw3k', null, '0', 'luweqr6cjk', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('luweqrb9j4', 'ltfeq951j4', 'ltfeq9z8qo', null, '0', 'luweqr6cjk', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('luweqrb9j5', 'ltfeq951j4', 'ltfeq9zlds', '', '0', 'luweqr6cjk', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('luweqrbm68', 'ltfeq951j4', 'ltfeq9zy0w', '', '0', 'luweqr6cjk', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('luweqrbm69', 'ltfeq951j4', 'ltfeqa0ao0', '', '0', 'luweqr6cjk', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('luweqrbytc', 'ltfeq951j4', 'ltfeqa0nb4', '', '0', 'luweqr6cjk', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('luweqrbytd', 'ltfeq951j4', 'ltgiysdpts', '', '0', 'luweqr6cjk', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('luweqrbyte', 'ltfeq951j4', 'ltgiyse2gw', '', '0', 'luweqr6cjk', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('luweqrcbgg', 'ltfeq951j4', 'ltgiysef40', '', '0', 'luweqr6cjk', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('luweqrco3k', 'ltfeq951j4', 'ltgiysef41', null, '0', 'luweqr6cjk', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lvg7jabf9c', 'luya1t258g', 'luya1t6cxs', 'test21bbbb', '0', 'lvg7in9yww', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lvg7molzpc', 'luya1t258g', 'luya1t6cxs', 'test33333', '0', 'lvg7mojvuo', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lvg7paomww', 'luya1t258g', 'luya1t6cxs', 'test244444', '0', 'lvg7pam6f4', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lvg821064g', 'luya1t258g', 'luya1t6cxs', 'test2345345', '0', 'lvg820yeww', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lvg84r7pj4', 'luya1t258g', 'luya1t6cxs', 'test2aaaaaaa', '0', 'lvg84r4we8', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lvg896nv9c', 'luya1t258g', 'luya1t6cxs', 'test41111', '0', 'lvg896m41s', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lvg8aj8bnk', 'luya1t258g', 'luya1t6cxs', 'test4aaaaaa', '0', 'lvg8aj6kg0', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lvg8drhvcw', 'luya1t258g', 'luya1t6cxs', 'test5aaaa', '0', 'lvg8drfri8', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lvg8exrtog', 'luya1t258g', 'luya1t6cxs', 'test5bbbb', '0', 'lvg8exq2gw', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lvza1893wg', '1', '1', '1', '0', 'lvza0j92io', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lww0kyaayo', 'luya1t258g', 'luya1t6cxs', 'dddd33', '0', 'lww0kwod1c', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lww5fvoqo0', 'lowwr18kqo', 'lowwr1aby8', '2017-06-07', '0', 'lww5fvma68', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lww5fvp3b4', 'lowwr18kqo', 'ltgd1b8av4', 'sd', '0', 'lww5fvma68', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lww5fvpfy8', 'lowwr18kqo', 'lox1qk9t6o', '2', '0', 'lww5fvma68', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lww5fvpfy9', 'lowwr18kqo', 'lox1qka5ts', '222', '0', 'lww5fvma68', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lww5fvpslc', 'lowwr18kqo', 'ltgc93rx8g', '', '0', 'lww5fvma68', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lww5fvq58g', 'lowwr18kqo', 'ltgclg2txc', '', '0', 'lww5fvma68', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lww5fvq58h', 'lowwr18kqo', 'ltgclg2txd', '', '0', 'lww5fvma68', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lww5fvqhvk', 'lowwr18kqo', 'lox1qka5tt', '美元', '0', 'lww5fvma68', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lww5fvquio', 'lowwr18kqo', 'lox1qkbx1c', '0', '0', 'lww5fvma68', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lww5fvquip', 'lowwr18kqo', 'lox1qkcmbk', '0', '0', 'lww5fvma68', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lww5fvr75s', 'lowwr18kqo', 'lox1qki8lc', null, '0', 'lww5fvma68', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lww5fvs934', 'lowwr18kqo', 'lox1qkil8g', '', '0', 'lww5fvma68', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lww5fvslq8', 'lowwr18kqo', 'ltgfh5bo5c', null, '0', 'lww5fvma68', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwhq2n94w', 'ltfeq951j4', 'ltfeq9xhj4', null, '0', 'lwwhq1m134', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwhq2nyf4', 'ltfeq951j4', 'ltfeq9y6tc', null, '0', 'lwwhq1m134', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwhq2nyf5', 'ltfeq951j4', 'ltfeq9yjgg', null, '0', 'lwwhq1m134', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwhq2ob28', 'ltfeq951j4', 'ltfeq9yw3k', null, '0', 'lwwhq1m134', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwhq2onpc', 'ltfeq951j4', 'ltfeq9z8qo', null, '0', 'lwwhq1m134', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwhq2onpd', 'ltfeq951j4', 'ltfeq9zlds', '213', '0', 'lwwhq1m134', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwhq2p0cg', 'ltfeq951j4', 'ltfeq9zy0w', '123', '0', 'lwwhq1m134', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwhq2pczk', 'ltfeq951j4', 'ltfeqa0ao0', '123', '0', 'lwwhq1m134', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwhq2pczl', 'ltfeq951j4', 'ltfeqa0nb4', '123', '0', 'lwwhq1m134', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwhq2ppmo', 'ltfeq951j4', 'ltgiysdpts', '10', '0', 'lwwhq1m134', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwhq2ppmp', 'ltfeq951j4', 'ltgiyse2gw', '12322', '0', 'lwwhq1m134', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwhq2q29s', 'ltfeq951j4', 'ltgiysef40', '15129', '0', 'lwwhq1m134', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwhq2q29t', 'ltfeq951j4', 'ltgiysef41', null, '0', 'lwwhq1m134', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwwhae6tc', 'ltfeq951j4', 'ltfeq9xhj4', 'IP(智能信息+)', '0', 'lwwwhaaoe8', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwwhaejgg', 'ltfeq951j4', 'ltfeq9y6tc', 'ETF', '0', 'lwwwhaaoe8', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwwhaew3k', 'ltfeq951j4', 'ltfeq9yjgg', 'IoPV', '0', 'lwwwhaaoe8', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwwhaew3l', 'ltfeq951j4', 'ltfeq9yw3k', '债券基本信息', '0', 'lwwwhaaoe8', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwwhaflds', 'ltfeq951j4', 'ltfeq9z8qo', '中诚宝捷思', '0', 'lwwwhaaoe8', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwwhafy0w', 'ltfeq951j4', 'ltfeq9zlds', '23', '0', 'lwwwhaaoe8', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwwhagao0', 'ltfeq951j4', 'ltfeq9zy0w', '123', '0', 'lwwwhaaoe8', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwwhagnb4', 'ltfeq951j4', 'ltfeqa0ao0', '23', '0', 'lwwwhaaoe8', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwwhagnb5', 'ltfeq951j4', 'ltfeqa0nb4', '12', '0', 'lwwwhaaoe8', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwwhagzy8', 'ltfeq951j4', 'ltgiysdpts', '1', '0', 'lwwwhaaoe8', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwwhahclc', 'ltfeq951j4', 'ltgiyse2gw', '123', '0', 'lwwwhaaoe8', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwwhahp8g', 'ltfeq951j4', 'ltgiysef40', '1476', '0', 'lwwwhaaoe8', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwwhai1vk', 'ltfeq951j4', 'ltgiysef41', null, '0', 'lwwwhaaoe8', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwxsgrp4w', 'lowwr18kqo', 'lowwr1aby8', '2017-06-21', '0', 'lwwxsgqakg', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwxsgs1s0', 'lowwr18kqo', 'ltgd1b8av4', 'sdaf', '0', 'lwwxsgqakg', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwxsgs1s1', 'lowwr18kqo', 'lox1qk9t6o', '123', '0', 'lwwxsgqakg', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwxsgsef4', 'lowwr18kqo', 'lox1qka5ts', '123123', '0', 'lwwxsgqakg', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwxsgsef5', 'lowwr18kqo', 'ltgc93rx8g', '', '0', 'lwwxsgqakg', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwxsgsr28', 'lowwr18kqo', 'ltgclg2txc', '', '0', 'lwwxsgqakg', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwxsgsr29', 'lowwr18kqo', 'ltgclg2txd', '', '0', 'lwwxsgqakg', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwxsgt3pc', 'lowwr18kqo', 'lox1qka5tt', null, '0', 'lwwxsgqakg', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwxsgt3pd', 'lowwr18kqo', 'lox1qkbx1c', '', '0', 'lwwxsgqakg', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwxsgtgcg', 'lowwr18kqo', 'lox1qkcmbk', '0', '0', 'lwwxsgqakg', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwxsgtgch', 'lowwr18kqo', 'lox1qki8lc', '商务洽谈', '0', 'lwwxsgqakg', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwxsgtszk', 'lowwr18kqo', 'lox1qkil8g', '', '0', 'lwwxsgqakg', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwxsgtszl', 'lowwr18kqo', 'ltgfh5bo5c', '否', '0', 'lwwxsgqakg', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwy1576yo', 'ltfeq951j4', 'ltfeq9xhj4', '智能接口', '0', 'lwwy15416o', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwy1576yp', 'ltfeq951j4', 'ltfeq9y6tc', 'ETF', '0', 'lwwy15416o', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwy157jls', 'ltfeq951j4', 'ltfeq9yjgg', 'IoPV', '0', 'lwwy15416o', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwy157jlt', 'ltfeq951j4', 'ltfeq9yw3k', '债券基本信息', '0', 'lwwy15416o', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwy157w8w', 'ltfeq951j4', 'ltfeq9z8qo', '中诚宝捷思', '0', 'lwwy15416o', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwy1588w0', 'ltfeq951j4', 'ltfeq9zlds', '', '0', 'lwwy15416o', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwy1588w1', 'ltfeq951j4', 'ltfeq9zy0w', '13', '0', 'lwwy15416o', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwy158lj4', 'ltfeq951j4', 'ltfeqa0ao0', '23', '0', 'lwwy15416o', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwy158lj5', 'ltfeq951j4', 'ltfeqa0nb4', '12', '0', 'lwwy15416o', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwy158y68', 'ltfeq951j4', 'ltgiysdpts', '102', '0', 'lwwy15416o', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwy158y69', 'ltfeq951j4', 'ltgiyse2gw', '1234', '0', 'lwwy15416o', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwy159atc', 'ltfeq951j4', 'ltgiysef40', '12', '0', 'lwwy15416o', null);
INSERT INTO m_work_tmpl_fields_value VALUES ('lwwy159atd', 'ltfeq951j4', 'ltgiysef41', null, '0', 'lwwy15416o', null);

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
-- Records of m_work_tmpl_records
-- ----------------------------
INSERT INTO m_work_tmpl_records VALUES ('lrfpgr2fwg', 'lkbcyn5beo', null, '0', '54', '54', '2017-06-05 16:21:34', '2017-06-05 16:26:39', '192.168.1.154', '{\"agent\":{\"browser\":\"UNKNOWN\",\"id\":16843022,\"operatingSystem\":\"UNKNOWN\"},\"os\":\"UNKNOWN\",\"browser\":\"UNKNOWN\",\"ua\":\"iPhone9,1(iOS/10.3.2) Sumslack(Sumslack/1.0.0) Weex/0.12.0 750x1334\"}', null);
INSERT INTO m_work_tmpl_records VALUES ('lrfrvdpd6o', 'lpgqyn78xs', null, '0', '1145', '1145', '2017-06-05 16:26:54', '2017-06-05 16:31:59', '0:0:0:0:0:0:0:1', '{\"agent\":{\"browser\":\"CHROME\",\"browserVersion\":{\"majorVersion\":\"58\",\"minorVersion\":\"0\",\"version\":\"58.0.3029.110\"},\"id\":34934529,\"operatingSystem\":\"WINDOWS_7\"},\"os\":\"WINDOWS_7\",\"browser\":\"CHROME\",\"ua\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36\"}', null);
INSERT INTO m_work_tmpl_records VALUES ('lru6gvj6rk', '1', null, '0', '54', '54', '2017-06-06 11:35:12', '2017-06-06 11:40:21', null, null, null);
INSERT INTO m_work_tmpl_records VALUES ('lru6qjqeps', '1', null, '0', '54', '54', '2017-06-06 11:35:46', '2017-06-06 11:40:56', null, null, null);
INSERT INTO m_work_tmpl_records VALUES ('ltg58v6ghs', 'ltfeq951j4', null, '0', '1145', '1145', '2017-06-09 16:35:44', '2017-06-09 16:41:10', '0:0:0:0:0:0:0:1', '{\"agent\":{\"browser\":\"CHROME\",\"browserVersion\":{\"majorVersion\":\"58\",\"minorVersion\":\"0\",\"version\":\"58.0.3029.110\"},\"id\":34934529,\"operatingSystem\":\"WINDOWS_7\"},\"os\":\"WINDOWS_7\",\"browser\":\"CHROME\",\"ua\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36\"}', 'ltf6bspmv4');
INSERT INTO m_work_tmpl_records VALUES ('ltg9lnlhq8', 'ltfeq951j4', null, '0', '1145', '1145', '2017-06-09 16:45:23', '2017-06-09 16:50:48', '0:0:0:0:0:0:0:1', '{\"agent\":{\"browser\":\"CHROME\",\"browserVersion\":{\"majorVersion\":\"58\",\"minorVersion\":\"0\",\"version\":\"58.0.3029.110\"},\"id\":34934529,\"operatingSystem\":\"WINDOWS_7\"},\"os\":\"WINDOWS_7\",\"browser\":\"CHROME\",\"ua\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36\"}', 'ltg7xlz400');
INSERT INTO m_work_tmpl_records VALUES ('ltga29bjeo', 'ltfeq951j4', null, '0', '1145', '1145', '2017-06-09 16:46:24', '2017-06-09 16:51:50', '0:0:0:0:0:0:0:1', '{\"agent\":{\"browser\":\"CHROME\",\"browserVersion\":{\"majorVersion\":\"58\",\"minorVersion\":\"0\",\"version\":\"58.0.3029.110\"},\"id\":34934529,\"operatingSystem\":\"WINDOWS_7\"},\"os\":\"WINDOWS_7\",\"browser\":\"CHROME\",\"ua\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36\"}', 'ltg7xlz400');
INSERT INTO m_work_tmpl_records VALUES ('ltgjd6wao0', 'lowwr18kqo', null, '0', '1145', '1145', '2017-06-09 17:07:00', '2017-06-09 17:12:26', '0:0:0:0:0:0:0:1', '{\"agent\":{\"browser\":\"CHROME\",\"browserVersion\":{\"majorVersion\":\"58\",\"minorVersion\":\"0\",\"version\":\"58.0.3029.110\"},\"id\":34934529,\"operatingSystem\":\"WINDOWS_7\"},\"os\":\"WINDOWS_7\",\"browser\":\"CHROME\",\"ua\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36\"}', '');
INSERT INTO m_work_tmpl_records VALUES ('ltgjewaj9c', 'lowwr18kqo', null, '0', '1145', '1145', '2017-06-09 17:07:06', '2017-06-09 17:12:32', '0:0:0:0:0:0:0:1', '{\"agent\":{\"browser\":\"CHROME\",\"browserVersion\":{\"majorVersion\":\"58\",\"minorVersion\":\"0\",\"version\":\"58.0.3029.110\"},\"id\":34934529,\"operatingSystem\":\"WINDOWS_7\"},\"os\":\"WINDOWS_7\",\"browser\":\"CHROME\",\"ua\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36\"}', '');
INSERT INTO m_work_tmpl_records VALUES ('ltgjjcbev4', 'ltfeq951j4', null, '0', '1145', '1145', '2017-06-09 17:07:23', '2017-06-09 17:12:49', '0:0:0:0:0:0:0:1', '{\"agent\":{\"browser\":\"CHROME\",\"browserVersion\":{\"majorVersion\":\"58\",\"minorVersion\":\"0\",\"version\":\"58.0.3029.110\"},\"id\":34934529,\"operatingSystem\":\"WINDOWS_7\"},\"os\":\"WINDOWS_7\",\"browser\":\"CHROME\",\"ua\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36\"}', 'ltgjd6wao0');
INSERT INTO m_work_tmpl_records VALUES ('ltgjn4x534', 'ltfeq951j4', null, '0', '1145', '1145', '2017-06-09 17:07:37', '2017-06-09 17:13:03', '0:0:0:0:0:0:0:1', '{\"agent\":{\"browser\":\"CHROME\",\"browserVersion\":{\"majorVersion\":\"58\",\"minorVersion\":\"0\",\"version\":\"58.0.3029.110\"},\"id\":34934529,\"operatingSystem\":\"WINDOWS_7\"},\"os\":\"WINDOWS_7\",\"browser\":\"CHROME\",\"ua\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36\"}', 'ltgjd6wao0');
INSERT INTO m_work_tmpl_records VALUES ('ltgo0od98g', 'lowwr18kqo', null, '0', '1145', '1145', '2017-06-09 17:17:18', '2017-06-09 17:22:44', '0:0:0:0:0:0:0:1', '{\"agent\":{\"browser\":\"CHROME\",\"browserVersion\":{\"majorVersion\":\"58\",\"minorVersion\":\"0\",\"version\":\"58.0.3029.110\"},\"id\":34934529,\"operatingSystem\":\"WINDOWS_7\"},\"os\":\"WINDOWS_7\",\"browser\":\"CHROME\",\"ua\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36\"}', '');
INSERT INTO m_work_tmpl_records VALUES ('ltgoazwgsg', 'lowwr18kqo', null, '0', '1186', '1186', '2017-06-09 17:17:56', '2017-06-09 17:23:22', '0:0:0:0:0:0:0:1', '{\"agent\":{\"browser\":\"CHROME\",\"browserVersion\":{\"majorVersion\":\"58\",\"minorVersion\":\"0\",\"version\":\"58.0.3029.110\"},\"id\":34934529,\"operatingSystem\":\"WINDOWS_7\"},\"os\":\"WINDOWS_7\",\"browser\":\"CHROME\",\"ua\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36\"}', '');
INSERT INTO m_work_tmpl_records VALUES ('lvg7in9yww', 'luya1t258g', null, '0', '53', '55', '2017-06-13 17:03:49', '2017-06-13 16:25:48', '0:0:0:0:0:0:0:1', '{\"agent\":{\"browser\":\"CHROME\",\"browserVersion\":{\"majorVersion\":\"58\",\"minorVersion\":\"0\",\"version\":\"58.0.3029.110\"},\"id\":34934529,\"operatingSystem\":\"WINDOWS_7\"},\"os\":\"WINDOWS_7\",\"browser\":\"CHROME\",\"ua\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36\"}', '');
INSERT INTO m_work_tmpl_records VALUES ('lvg7pam6f4', 'luya1t258g', null, '0', '53', '53', '2017-06-13 16:20:16', '2017-06-13 16:26:10', '0:0:0:0:0:0:0:1', '{\"agent\":{\"browser\":\"CHROME\",\"browserVersion\":{\"majorVersion\":\"58\",\"minorVersion\":\"0\",\"version\":\"58.0.3029.110\"},\"id\":34934529,\"operatingSystem\":\"WINDOWS_7\"},\"os\":\"WINDOWS_7\",\"browser\":\"CHROME\",\"ua\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36\"}', '');
INSERT INTO m_work_tmpl_records VALUES ('lvg820yeww', 'luya1t258g', null, '0', '53', '53', '2017-06-13 16:21:03', '2017-06-13 16:26:57', '0:0:0:0:0:0:0:1', '{\"agent\":{\"browser\":\"CHROME\",\"browserVersion\":{\"majorVersion\":\"58\",\"minorVersion\":\"0\",\"version\":\"58.0.3029.110\"},\"id\":34934529,\"operatingSystem\":\"WINDOWS_7\"},\"os\":\"WINDOWS_7\",\"browser\":\"CHROME\",\"ua\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36\"}', '');
INSERT INTO m_work_tmpl_records VALUES ('lvg84r4we8', 'luya1t258g', null, '0', '53', '53', '2017-06-13 16:21:13', '2017-06-13 16:27:07', '0:0:0:0:0:0:0:1', '{\"agent\":{\"browser\":\"CHROME\",\"browserVersion\":{\"majorVersion\":\"58\",\"minorVersion\":\"0\",\"version\":\"58.0.3029.110\"},\"id\":34934529,\"operatingSystem\":\"WINDOWS_7\"},\"os\":\"WINDOWS_7\",\"browser\":\"CHROME\",\"ua\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36\"}', '');
INSERT INTO m_work_tmpl_records VALUES ('lvg8aj6kg0', 'luya1t258g', null, '0', '55', '55', '2017-06-13 16:21:34', '2017-06-13 16:27:28', '0:0:0:0:0:0:0:1', '{\"agent\":{\"browser\":\"CHROME\",\"browserVersion\":{\"majorVersion\":\"58\",\"minorVersion\":\"0\",\"version\":\"58.0.3029.110\"},\"id\":34934529,\"operatingSystem\":\"WINDOWS_7\"},\"os\":\"WINDOWS_7\",\"browser\":\"CHROME\",\"ua\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36\"}', '');
INSERT INTO m_work_tmpl_records VALUES ('lvg8drfri8', 'luya1t258g', null, '0', '56', '56', '2017-06-13 16:21:46', '2017-06-13 16:27:40', '0:0:0:0:0:0:0:1', '{\"agent\":{\"browser\":\"CHROME\",\"browserVersion\":{\"majorVersion\":\"58\",\"minorVersion\":\"0\",\"version\":\"58.0.3029.110\"},\"id\":34934529,\"operatingSystem\":\"WINDOWS_7\"},\"os\":\"WINDOWS_7\",\"browser\":\"CHROME\",\"ua\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36\"}', '');
INSERT INTO m_work_tmpl_records VALUES ('lvg8exq2gw', 'luya1t258g', null, '0', '56', '56', '2017-06-13 16:21:50', '2017-06-13 16:27:45', '0:0:0:0:0:0:0:1', '{\"agent\":{\"browser\":\"CHROME\",\"browserVersion\":{\"majorVersion\":\"58\",\"minorVersion\":\"0\",\"version\":\"58.0.3029.110\"},\"id\":34934529,\"operatingSystem\":\"WINDOWS_7\"},\"os\":\"WINDOWS_7\",\"browser\":\"CHROME\",\"ua\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36\"}', '');
INSERT INTO m_work_tmpl_records VALUES ('lvza0j92io', '1', null, '0', '1145', '1145', '2017-06-14 17:39:56', '2017-06-14 17:45:56', null, null, null);
INSERT INTO m_work_tmpl_records VALUES ('lww0kwod1c', 'luya1t258g', null, '0', '1803', '1803', '2017-06-16 13:09:29', '2017-06-16 13:15:38', '0:0:0:0:0:0:0:1', '{\"agent\":{\"browser\":\"CHROME\",\"browserVersion\":{\"majorVersion\":\"58\",\"minorVersion\":\"0\",\"version\":\"58.0.3029.110\"},\"id\":34934529,\"operatingSystem\":\"WINDOWS_7\"},\"os\":\"WINDOWS_7\",\"browser\":\"CHROME\",\"ua\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36\"}', '');
INSERT INTO m_work_tmpl_records VALUES ('lww5fvma68', 'lowwr18kqo', null, '0', '1803', '1803', '2017-06-16 13:20:15', '2017-06-16 13:26:24', '0:0:0:0:0:0:0:1', '{\"agent\":{\"browser\":\"CHROME\",\"browserVersion\":{\"majorVersion\":\"58\",\"minorVersion\":\"0\",\"version\":\"58.0.3029.110\"},\"id\":34934529,\"operatingSystem\":\"WINDOWS_7\"},\"os\":\"WINDOWS_7\",\"browser\":\"CHROME\",\"ua\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36\"}', '');
INSERT INTO m_work_tmpl_records VALUES ('lwwhq1m134', 'ltfeq951j4', null, '0', '1803', '1803', '2017-06-16 13:47:27', '2017-06-16 13:53:36', '0:0:0:0:0:0:0:1', '{\"agent\":{\"browser\":\"CHROME\",\"browserVersion\":{\"majorVersion\":\"58\",\"minorVersion\":\"0\",\"version\":\"58.0.3029.110\"},\"id\":34934529,\"operatingSystem\":\"WINDOWS_7\"},\"os\":\"WINDOWS_7\",\"browser\":\"CHROME\",\"ua\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36\"}', 'lww5fvma68');
INSERT INTO m_work_tmpl_records VALUES ('lwwwhaaoe8', 'ltfeq951j4', null, '0', '1803', '1803', '2017-06-16 14:20:07', '2017-06-16 14:26:16', '0:0:0:0:0:0:0:1', '{\"agent\":{\"browser\":\"CHROME\",\"browserVersion\":{\"majorVersion\":\"58\",\"minorVersion\":\"0\",\"version\":\"58.0.3029.110\"},\"id\":34934529,\"operatingSystem\":\"WINDOWS_7\"},\"os\":\"WINDOWS_7\",\"browser\":\"CHROME\",\"ua\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36\"}', 'lww5fvma68');
INSERT INTO m_work_tmpl_records VALUES ('lwwxsgqakg', 'lowwr18kqo', null, '0', '1803', '1803', '2017-06-16 14:23:01', '2017-06-16 14:29:10', '0:0:0:0:0:0:0:1', '{\"agent\":{\"browser\":\"CHROME\",\"browserVersion\":{\"majorVersion\":\"58\",\"minorVersion\":\"0\",\"version\":\"58.0.3029.110\"},\"id\":34934529,\"operatingSystem\":\"WINDOWS_7\"},\"os\":\"WINDOWS_7\",\"browser\":\"CHROME\",\"ua\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36\"}', '');
INSERT INTO m_work_tmpl_records VALUES ('lwwy15416o', 'ltfeq951j4', null, '0', '1803', '1803', '2017-06-16 14:23:33', '2017-06-16 14:29:42', '0:0:0:0:0:0:0:1', '{\"agent\":{\"browser\":\"CHROME\",\"browserVersion\":{\"majorVersion\":\"58\",\"minorVersion\":\"0\",\"version\":\"58.0.3029.110\"},\"id\":34934529,\"operatingSystem\":\"WINDOWS_7\"},\"os\":\"WINDOWS_7\",\"browser\":\"CHROME\",\"ua\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36\"}', 'lwwxsgqakg');

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
INSERT INTO users VALUES ('1057', 'g-41a6197125a5a1468409831175', 'g-41a6197125a5a1468409831175', 's', '71', null, null, '123456', '', null);
INSERT INTO users VALUES ('1058', 'g-f8518b94e7aaa1468409997261', 'g-f8518b94e7aaa1468409997261', 's', '71', null, null, '123456', '', null);
INSERT INTO users VALUES ('1059', 'g-25f6f9318fd761468410071257', 'g-25f6f9318fd761468410071257', 's', '71', null, null, '123456', '', null);
INSERT INTO users VALUES ('1060', 'g-662feb55a75601468417071308', 'g-662feb55a75601468417071308', 's', '71', null, null, '123456', '', null);
INSERT INTO users VALUES ('1061', 'g-b443bb94934531468593421505', 'g-b443bb94934531468593421505', 's', '71', null, null, '123456', '', null);
INSERT INTO users VALUES ('1064', 'test3s', 'test3s', 's', '71', null, null, '123456', '', null);
INSERT INTO users VALUES ('1145', 'alex.liu@sumscope.com', '柳磊', 's', '72', null, 'luzdco8c8w', '123456', ' ', null);
INSERT INTO users VALUES ('1170', 'shaoping.liu@sumscope.com', '少平', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1171', 'kedong.li@sumscope.com', '李科栋', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1184', 'alex.liu3@sumscope.com', '柳磊', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1186', 'alex.liu4@sumscope.com', '柳磊', 's', '72', null, 'ltgq5awjcw', '123456', '', null);
INSERT INTO users VALUES ('1207', 'kedong1', '科栋1', 's', '0', null, null, '123456', '', null);
INSERT INTO users VALUES ('1208', 'kedong2', '科栋2', 's', '0', null, null, '123456', '', null);
INSERT INTO users VALUES ('1209', 'kedong3', '科栋3', 's', '0', null, null, '123456', '', null);
INSERT INTO users VALUES ('1210', 'kedong4', '科栋4', 's', '0', null, null, '123456', '', null);
INSERT INTO users VALUES ('1211', 'kedong5', '科栋5', 's', '0', null, null, '123456', '', null);
INSERT INTO users VALUES ('1219', 'xxx@xxx.com', '测试x', 's', '0', null, null, '123456', '', null);
INSERT INTO users VALUES ('1803', 'admin@sumscopecom', '管理员', 's', '0', null, 'lwy1wacw74', '123456', '', null);
INSERT INTO users VALUES ('1807', 'quan.chen@sumscope.com', '陈铨', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1810', 'xingguo.tong@sumscope.com', '童兴国', 's', '72', null, null, '123456', ' ', null);
INSERT INTO users VALUES ('1812', 'huameng.jia@sumscope.com', '贾华孟2', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1813', 'jianwen.jia@sumscope.com', '贾建文', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1814', 'fan.zhang@sumscope.com', '张凡', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1815', 'yudan.chen@sumscope.com', '陈雨旦', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1816', 'kiki.zhong@sumscope.com', '仲棋', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1817', 'ye.liu@sumscope.com', '刘业', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1818', 'jiawei.tu@sumscope.com', '涂佳伟', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1819', 'weihai.cui@sumscope.com', '崔卫海', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1820', 'monica.shi@sumscope.com', '史未', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1822', 'zenggui.shen@sumscope.com', '沈增贵', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1823', 'xingjian.zhu@sumscope.com', '朱兴建', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1824', 'qianzi.wang@sumscope.com', '王倩子', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1825', 'chunchao.chen@sumscope.com', '陈春潮', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1826', 'baolong.hou@sumscope.com', '候宝龙', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1827', 'kedong.li@sumscope.com', '李科栋', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1828', 'xiaohua.zhu@sumscope.com', '祝小华', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1829', 'zeke.guo@sumscope.com', '郭泽羽', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1830', 'guomiao.xu@sumscope.com', '徐国淼', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1831', 'ting.miao@sumscope.com', '缪婷', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1832', 'hui.jiang@sumscope.com', '江慧', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1833', 'taiyan.luo@sumscope.com', '罗太燕', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1834', 'jialu.sun@sumscope.com', '孙嘉璐', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1835', 'lara.xu@sumscope.com', '许海琳', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1836', 'yu.bian@sumscope.com', '卞宇', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1837', 'yibo.sun@sumscope.com', '孙轶博', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1838', 'binson.qian@sumscope.com', '钱彬浩', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1839', 'zhe.wang@sumscope.com', '王喆', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1840', 'sally.yang@sumscope.com', '杨爽', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1841', 'mingtao.chen@sumscope.com', '陈明涛', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1842', 'arno.fang@sumscope.com', '方丹俊', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1843', 'holly.li@sumscope.com', '李航', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1844', 'zhongxin.chen@sumscope.com', '陈忠新', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1845', 'yinfei.li@sumscope.com', '李寅飞', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1846', 'jiawang.xie@sumscope.com', '谢家旺', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1847', 'xin.zhou@sumscope.com', '周欣', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1848', 'weiqin.luo@sumscope.com', '罗伟芹', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1849', 'yefan.wang@sumscope.com', '王叶凡', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1850', 'elaine.xin@sumscope.com', '辛茜', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1851', 'haoming.he@sumscope.com', '何浩铭', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1852', 'lei.xie@sumscope.com', '谢磊', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1853', 'mengyang.sun@sumscope.com   ', '孙孟阳', 's', '0', null, null, '123456', '', null);
INSERT INTO users VALUES ('1854', 'wenxia.wu@sumscope.com', '吴文霞', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1855', 'lina.zhang@sumscope.com', '张丽娜', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1856', 'rong.huang@sumscope.com', '黄荣', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1857', 'huiqin.wang@sumscope.com', '王慧琴', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1858', 'zhongjie.qiu@sumscope.com ', '裘中杰', 's', '0', null, null, '123456', '', null);
INSERT INTO users VALUES ('1859', 'jian.xu@sumscope.com ', '许健', 's', '0', null, null, '123456', '', null);
INSERT INTO users VALUES ('1860', 'he.kong@sumscope.com', '孔贺', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1861', 'yue.shi@sumscope.com', '史岳', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1862', 'wangyu.ye@sumscope.com ', '叶汪宇', 's', '0', null, null, '123456', '', null);
INSERT INTO users VALUES ('1863', 'jingfei.guo@sumscope.com', '郭靖菲', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1864', 'yuliang.li@sumscope.com', '李俞良', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1865', 'jiao.zhang@sumscope.com', '张矫矫', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1866', 'xiaofang.lv@sumscope.com', '吕晓芳', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1867', 'shuang.wang@sumscope.com', '王爽', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1868', 'huaijie.chen@sumscope.com', '陈怀结 ', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1869', 'zhaokun.zhang@sumscope.com', '张照坤', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1870', 'ji.wang@sumscope.com', '汪骥', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1871', 'qikai.yu@sumscope.com', '俞祺恺', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1872', 'liangbin.liu@sumscope.com', '刘良斌', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1873', 'zushun.zhan@sumscope.com', '詹祖顺', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1874', 'xiaofeng.yuan@sumscope.com', '袁晓丰', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1875', 'bo.liu@sumscope.com', '刘波', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1876', 'caiqin.yu@sumscope.com', '余彩琴', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1877', 'qin.dong@sumscope.com', '董芹', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1878', 'jolly.jiang@sumscope.com', '江力', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1879', 'vixen.xu@sumscope.com', '徐翌晹', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1880', 'shakira.su@sumscope.com', '苏慧慧', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1881', 'givins.zhu@sumscope.com', '朱慕舜', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1882', 'james.xu@sumscope.com', '徐建明', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1883', 'daniel.zhang@sumscope.com', '张浩', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1884', 'tony.li@sumscope.com', '李建', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1885', 'scofined.qi@sumscope.com', '柒成林', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1886', 'bo.cai@sumscope.com', '蔡波', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1887', 'dongsheng.wang@sumscope.com', '王东升', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1888', 'xy.wang@sumscope.com', '王兴岳', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1889', 'alvin.hu@sumscope.com', '胡荻斯', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1890', 'yifu.ding@sumscope.com', '丁一夫', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1891', 'hao.ding@sumscope.com', '丁昊', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1892', 'teresa.fei@sumscope.com', '费霏', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1893', 'jean.huang@sumscope.com', '黄健', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1894', 'shangyi.xin@sumscope.com', '辛尚艺', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1895', 'yachao.li@sumscope.com', '李亚超', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1896', 'yingxiu.ouyang@sumscope.com', '欧阳应秀', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1897', 'cc.liu@sumscope.com', '刘畅', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1898', 'julio.chen@sumscope.com', '陈晶', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1899', 'minmin.liu@sumscope.com', '柳珉敏', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1900', 'wenshuai.li@sumscope.com ', '李文帅', 's', '0', null, null, '123456', '', null);
INSERT INTO users VALUES ('1901', 'peng.fang@sumscope.com', '方鹏', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1902', 'rita.cang@sumscope.com', '仓瑲乂', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1903', 'jia.huang@sumscope.com', '黄佳', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1904', 'kaiting.lin@sumscope.com', '林楷庭', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1905', 'wei.lin@sumscope.com', '林炜', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1906', 'daren.li@sumscope.com', '李达人', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1907', 'fuwen.lu@sumscope.com', '卢福文', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1908', 'eric.zhu@sumscope.com', '朱行超', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1909', 'qi.sun@sumscope.com', '孙奇', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1910', 'chunbin.chen@sumscope.com', '陈春斌', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1911', 'jian.sun@sumscope.com  ', '孙健', 's', '0', null, null, '123456', '', null);
INSERT INTO users VALUES ('1912', 'wenye.wang@sumscope.com', '王文业', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1913', 'biaobiao.yang@sumscope.com', '杨彪彪', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1914', 'yifang.li@sumscope.com', '黎宜芳', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1915', 'nina.zhang@sumscope.com', '章雅青', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1916', 'zhenghong.zhou@sumscope.com', '周政宏', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1917', 'xuejian.sun@sumscope.com', '孙雪健', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1918', 'yinan.zhang@sumscope.com', '张义男', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1919', 'xinglong.shi@sumscope.com', '石兴龙', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1920', 'li.li@sumscope.com', '李力', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1921', 'pengfei.chen@sumscope.com', '陈鹏飞', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1922', 'xing.peng@sumscope.com', '彭兴珺', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1923', 'alger.lu@sumscope.com', '陆忠良', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1924', 'simon.zhang@sumscope.com', '张廷然', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1925', 'devin.zhang@sumscope.com', '张大润', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1926', 'ken.cai@sumscope.com', '蔡剑', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1927', 'guoxi.zhang@sumscope.com', '张国玺', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1928', 'wei.liu@sumscope.com', '刘微', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1929', 'bingquan.dong@sumscope.com', '董炳权', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1930', 'jesen.shen@sumscope.com', '沈盛杰', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1931', 'richard.yan@sumscope.com', '严列挺', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1932', 'fan.hou@sumscope.com', '侯凡', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1933', 'na.gao@sumscope.com', '高娜', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1934', 'xinxin.sun@sumscope.com', '孙鑫鑫', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1935', 'zhiqin.li@sumscope.com', '李志勤', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1936', 'chunlai.zhong@sumscope.com', '钟春来', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1937', 'danfei.pan@sumscope.com', '潘丹斐', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1938', 'li.wang@sumscope.com', '王莉', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1939', 'derek.zhao@sumscope.com', '赵银龙', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1940', 'xiaobo.hu@sumscope.com', '胡晓波', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1941', 'lu.wan@sumscope.com', '万璐', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1942', 'lulu.tong@sumscope.com', '童露露', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1943', 'qingsong.sheng@sumscope.com', '盛青松', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1944', 'feifei.li@sumscope.com', '李菲菲', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1945', 'qing.fang@sumscope.com', '方青', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1946', 'yulong.kong@sumscope.com', '孔玉龙', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1947', 'jun.wang@sumscope.com', '王俊', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1948', 'na.lv@sumscope.com', '吕娜', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1949', 'winnie.bian@sumscope.com', '卞雯', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1950', 'bin.wang@sumscope.com', '王斌', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1951', 'wei.mao@sumscope.com', '毛伟', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1952', 'jinjiang.liu@sumscope.com', '刘锦江', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1953', 'zhiwei.wang@sumscope.com', '王志伟', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1954', 'yanlin.wang@sumscope.com', '王彦麟', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1955', 'chunhong.huang@sumscope.com', '黄春泓', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1956', 'yinfei.bao@sumscope.com ', '鲍寅飞', 's', '0', null, null, '123456', '', null);
INSERT INTO users VALUES ('1957', 'yang.yu@sumscope.com', '于洋', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1958', 'nan.li@sumscope.com', '李楠', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1959', 'zhidian.she@sumscope.com', '佘志典', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1960', 'lin.wang@sumscope.com', '王林', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1961', 'xingdong.zhai@sumscope.com', '翟兴东', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1962', 'mandy.jiang@sumscope.com', '姜婧琛', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1963', 'echo.yang@sumscope.com', '杨逸舟', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1964', 'amanda.zhou@sumscope.com', '周梦丹', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1965', 'shenggang.huang@sumscope.com', '黄盛刚', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1966', 'shaoxu.wang@sumscope.com', '王绍旭', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1967', 'zhaoan.tan@sumscope.com', '谭兆安', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1968', 'joanna.chen@sumscope.com', '陈蓉', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1969', 'jessica.lv@sumscope.com', '吕博', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1970', 'kristina.liu@sumscope.com', '刘陆滢', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1971', 'shuwei.lin@sumscope.com', '林蜀玮', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1972', 'min.zhou@sumscope.com', '周敏', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1973', 'yang.liu@sumscope.com', '刘洋', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1974', 'dongze.wang@sumscope.com', '王东泽', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1975', 'yan.liu@sumscope.com', '刘岩', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1976', 'jingyi.peng@sumscope.com', '彭景一', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1977', 'fanbin.chen@sumscope.com ', '陈范斌', 's', '0', null, null, '123456', '', null);
INSERT INTO users VALUES ('1978', 'fang.zhang@sumscope.com', '张芳', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1979', 'cheng.han@sumscope.com', '韩澄', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1980', 'jianming.zhang@sumscope.com  ', '张建明', 's', '0', null, null, '123456', '', null);
INSERT INTO users VALUES ('1981', 'haowen.zhang@sumscope.com ', '张浩文', 's', '0', null, null, '123456', '', null);
INSERT INTO users VALUES ('1982', 'linlin.an@sumscope.com  ', '安琳琳', 's', '0', null, null, '123456', '', null);
INSERT INTO users VALUES ('1983', 'chufan.xiao@sumscope.com ', '肖楚凡', 's', '0', null, null, '123456', '', null);
INSERT INTO users VALUES ('1984', 'fangxia.shangguan@sumscope.com ', '上官芳霞', 's', '0', null, null, '123456', '', null);
INSERT INTO users VALUES ('1985', 'yingzheng.shi@sumscope.com', '时嬴政', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1986', 'yajie.wang@sumscope.com', '王亚杰', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1987', 'qiumin.chen@sumscope.com', '陈秋敏', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1988', 'weiming.yi@sumscope.com', '易伟鸣', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1989', 'guohui.li@sumscope.com', '李国慧', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1990', 'vivian.xu@sumscope.com', '许可', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1991', 'kun.liu@sumscope.com', '刘琨', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1992', 'hongbin.li@sumscope.com', '李宏斌', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1993', 'lai.wei@sumscope.com', '卫徕', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1994', 'lanfei.shi@sumscope.com', '石兰飞', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1995', 'zhiyang.li@sumscope.com', '李志洋', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1996', 'chengzhang.wang@sumscope.com', '王成章', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1997', 'raylan.cai@sumscope.com', '蔡学范', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1998', 'weifeng.chen@sumscope.com', '陈伟凤', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('1999', 'dong.hu@sumscope.com', '胡冬', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('2000', 'jie.wu@sumscope.com', '吴杰', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('2001', 'larry.chen@sumscope.com', '陈冠龙', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('2002', 'smith.chen@sumscope.com', '陈林玉', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('2003', 'titi.liu@sumscope.com', '刘婷', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('2004', 'mengyi.he@sumscope.com', '贺梦怡', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('2005', 'bo.chen@sumscope.com', '陈博', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('2006', 'lie.qian@sumscope.com', '钱列', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('2007', 'jianxin.du@sumscope.com', '杜建新', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('2008', 'chi.hao@sumscope.com', '郝驰', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('2009', 'juejing.zhang@sumscope.com', '张珏晶', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('2010', 'yi.li@sumscope.com', '李毅', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('2011', 'junjie.zhao@sumscope.com', '赵俊杰', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('2012', 'jiamei.bai@sumscope.com ', '白佳玫', 's', '0', null, null, '123456', '', null);
INSERT INTO users VALUES ('2013', 'yi.he@sumscope.com', '何伊', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('2014', 'ke.li@sumscope.com', '李轲', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('2015', 'huaqin.zhao@sumscope.com', '赵华琴', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('2016', 'fan.bai@sumscope.com', '白凡', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('2017', 'Richard.li@sumscope.com', '李广瑜', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('2018', 'guannan.pang@sumscope.com', '庞冠楠', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('2019', 'hongdao.xie@sumscope.com', '谢宏道', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('2020', 'guoying.feng@sumscope.com', '冯国营', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('2021', 'sky.zhang@sumscope.com', '张朋飞', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('2022', 'steve.han@sumscope.com', '韩红军', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('2023', 'samuel.liu@sumscope.com', '刘士奇', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('2024', 'qiaosheng.wang@sumscope.com', '汪乔升', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('2025', 'baoge.li@sumscope.com', '李宝鸽', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('2026', 'chanjuan.qi@sumscope.com', '齐婵娟', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('2027', 'ruyi.yang@sumscope.com', '杨如意', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('2028', 'haitao.gu@sumscope.com', '顾海涛', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('2029', 'jiannan.niu@sumscope.com', '牛剑南', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('2030', '5548@qq.com', '艾丝凡', 's', '0', null, null, '123456', '', null);
INSERT INTO users VALUES ('2049', '55481@qq.com', 'Next', 's', '72', null, null, '123456', '', null);
INSERT INTO users VALUES ('2050', '64292447@qq.com', '崆峒', 's', '71', null, null, '123456', '', null);
INSERT INTO users VALUES ('2051', 'yanlieting@hotmail.com', '何厚铧', 's', '71', null, null, '123456', '', null);
INSERT INTO users VALUES ('52', 'test1', 'Miyoki', 's', '71', null, 'luz4elb18g', '123456', '', null);
INSERT INTO users VALUES ('53', 'test2', 'i心跳', 's', '71', null, 'lvg75yuww0', '123456', '', null);
INSERT INTO users VALUES ('54', 'test3', '丝丝丝', 's', '71', null, null, '123456', 'http://192.168.1.151:8700/g0/000/000/1484127154019235_140661452691_0x0.png', null);
INSERT INTO users VALUES ('55', 'test4', '夜彌紗', 's', '71', null, 'lvghd9v85c', '123456', '', null);
INSERT INTO users VALUES ('56', 'test5', '极度丨达人', 's', '71', null, 'lvgv694ow0', '123456', '', null);
INSERT INTO users VALUES ('57', 'test6', 'Claire_', 's', '71', null, null, '123456', '', null);
INSERT INTO users VALUES ('58', 'test7', 'LoveJuan玄xxxxxxxxxxxxxxxxxxx', 's', '71', null, null, '123456', '', null);
INSERT INTO users VALUES ('59', 'test8', '华哥和小美', 's', '71', null, null, '123456', '', null);
INSERT INTO users VALUES ('opCDs0H1T08wjdQ-Xi9z2ALgvefI', 'opCDs0H1T08wjdQ-Xi9z2ALgvefI', '空山雪林', 'w', null, null, 'lrxzxxgfls', null, 'http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTI5snx6fqLSTF77lOeibgcNCF7d4gVeIyp6rRFTPmtNe8BpADvrd8l03CyvtUIibDgCN4Klgaiad4Czw/0', null);

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

-- ----------------------------
-- Records of users_tmpl_purview
-- ----------------------------
INSERT INTO users_tmpl_purview VALUES ('aaa', '1803', 'lowwr18kqo', 'Y', 'Y', 'Y', 'Y');
INSERT INTO users_tmpl_purview VALUES ('luya1taxa8', '1803', 'luya1t258g', 'Y', 'Y', 'Y', 'Y');
INSERT INTO users_tmpl_purview VALUES ('luz0n2g7i8', '55', 'luya1t258g', 'Y', 'Y', 'Y', 'N');
INSERT INTO users_tmpl_purview VALUES ('luz1ixzdhc', '56', 'luya1t258g', 'Y', 'Y', 'Y', 'N');
INSERT INTO users_tmpl_purview VALUES ('lvfyhdga2o', '53', 'luya1t258g', 'Y', 'Y', 'Y', 'N');
INSERT INTO users_tmpl_purview VALUES ('lvg6ykwdfk', '54', 'luya1t258g', 'Y', 'Y', 'Y', 'N');
