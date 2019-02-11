
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `zc_acc_account`
-- ----------------------------
DROP TABLE IF EXISTS `zc_acc_account`;
CREATE TABLE `zc_acc_account` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `user_id` int(11) DEFAULT NULL COMMENT '用户',
  `total` decimal(20,2) DEFAULT NULL COMMENT '账户总额',
  `balance` decimal(20,2) DEFAULT NULL COMMENT '可用余额',
  `freeze_amount` decimal(20,2) DEFAULT NULL COMMENT '冻结金额',
  `version` int(11) DEFAULT NULL COMMENT '版本控制',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资金账户表：zc_acc_account';

-- ----------------------------
-- Records of zc_acc_account
-- ----------------------------

-- ----------------------------
-- Table structure for `zc_acc_account_deduct`
-- ----------------------------
DROP TABLE IF EXISTS `zc_acc_account_deduct`;
CREATE TABLE `zc_acc_account_deduct` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `user_id` int(11) DEFAULT NULL COMMENT '用户',
  `amount` decimal(20,2) DEFAULT NULL COMMENT '操作金额',
  `order_no` varchar(64) DEFAULT NULL COMMENT '订单号',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  `add_operator_id` int(11) DEFAULT NULL COMMENT '操作管理员',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_ip` varchar(6646) DEFAULT NULL COMMENT '添加ip',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='线下扣款记录：zc_acc_account_deduct';

-- ----------------------------
-- Records of zc_acc_account_deduct
-- ----------------------------

-- ----------------------------
-- Table structure for `zc_acc_account_log`
-- ----------------------------
DROP TABLE IF EXISTS `zc_acc_account_log`;
CREATE TABLE `zc_acc_account_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `user_id` int(11) DEFAULT NULL COMMENT '用户',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `total` decimal(20,2) DEFAULT NULL COMMENT '账户总额',
  `amount` decimal(20,2) DEFAULT NULL COMMENT '操作金额',
  `balance` decimal(20,2) DEFAULT NULL COMMENT '可用余额',
  `freeze_amount` decimal(20,2) DEFAULT NULL COMMENT '冻结金额',
  `to_user_id` int(11) DEFAULT NULL COMMENT '交易方用户',
  `content` varchar(512) DEFAULT NULL COMMENT '日志内容',
  `payments_type` int(11) DEFAULT NULL COMMENT '收支方式',
  `order_no` varchar(128) DEFAULT NULL COMMENT '关联订单',
  `remark` varchar(64) DEFAULT NULL COMMENT '备注',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资金日志表：zc_acc_account_log';

-- ----------------------------
-- Records of zc_acc_account_log
-- ----------------------------

-- ----------------------------
-- Table structure for `zc_acc_bank_card`
-- ----------------------------
DROP TABLE IF EXISTS `zc_acc_bank_card`;
CREATE TABLE `zc_acc_bank_card` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `user_id` int(11) DEFAULT NULL COMMENT '用户',
  `bank_card_no` varchar(64) DEFAULT NULL COMMENT '银行卡账号',
  `bank_code` varchar(32) DEFAULT NULL COMMENT '所属行编码',
  `bank_name` varchar(32) DEFAULT NULL COMMENT '所属行名称',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `branch` varchar(255) DEFAULT NULL COMMENT '支行',
  `branch_name` varchar(128) DEFAULT NULL COMMENT '支行名称',
  `province` varchar(64) DEFAULT NULL COMMENT '省',
  `city` varchar(64) DEFAULT NULL COMMENT '市',
  `area` varchar(64) DEFAULT NULL COMMENT '区',
  `mobile` varchar(64) DEFAULT NULL COMMENT '预留手机号',
  `real_name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `amount` decimal(20,2) DEFAULT NULL COMMENT '剩余提取金额',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  `auto_deduct` int(11) DEFAULT NULL COMMENT '自动扣款状态',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_ip` varchar(64) DEFAULT NULL COMMENT '添加ip',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银行卡表：zc_acc_bank_card';

-- ----------------------------
-- Records of zc_acc_bank_card
-- ----------------------------

-- ----------------------------
-- Table structure for `zc_acc_recharge`
-- ----------------------------
DROP TABLE IF EXISTS `zc_acc_recharge`;
CREATE TABLE `zc_acc_recharge` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `user_id` int(11) DEFAULT NULL COMMENT '用户',
  `amount` decimal(20,2) DEFAULT NULL COMMENT '操作金额',
  `arrival_amount` decimal(20,2) DEFAULT NULL COMMENT '实际到账金额',
  `type` int(11) DEFAULT NULL COMMENT '充值类型',
  `way` int(11) DEFAULT NULL COMMENT '支付方式',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  `bank_card_no` varchar(64) DEFAULT NULL COMMENT '银行卡账号',
  `bank_code` varchar(32) DEFAULT NULL COMMENT '所属行编码',
  `mobile` varchar(64) DEFAULT NULL COMMENT '预留手机号',
  `real_name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `order_no` varchar(64) DEFAULT NULL COMMENT '订单号',
  `order_no_extra` varchar(64) DEFAULT NULL COMMENT '分步订单号',
  `result_msg` varchar(128) DEFAULT NULL COMMENT '结果描述',
  `route` int(11) DEFAULT NULL COMMENT '渠道',
  `fee` decimal(20,2) DEFAULT NULL COMMENT '手续费',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  `operate_user` varchar(64) DEFAULT NULL COMMENT '操作管理员',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_ip` varchar(64) DEFAULT NULL COMMENT '添加ip',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充值记录表：zc_acc_recharge';

-- ----------------------------
-- Records of zc_acc_recharge
-- ----------------------------

-- ----------------------------
-- Table structure for `zc_acc_withdraw_cash`
-- ----------------------------
DROP TABLE IF EXISTS `zc_acc_withdraw_cash`;
CREATE TABLE `zc_acc_withdraw_cash` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `user_id` int(11) DEFAULT NULL COMMENT '用户',
  `amount` decimal(20,2) DEFAULT NULL COMMENT '操作金额',
  `arrival_amount` decimal(20,2) DEFAULT NULL COMMENT '实际到账金额',
  `fee` decimal(20,2) DEFAULT NULL COMMENT '手续费',
  `bank_card_no` varchar(64) DEFAULT NULL COMMENT '银行卡账号',
  `bank_code` varchar(32) DEFAULT NULL COMMENT '所属行编码',
  `mobile` varchar(64) DEFAULT NULL COMMENT '预留手机号',
  `real_name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `result_msg` varchar(128) DEFAULT NULL COMMENT '结果描述',
  `route` int(11) DEFAULT NULL COMMENT '渠道',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  `operate_user` varchar(64) DEFAULT NULL COMMENT '操作管理员',
  `order_no` varchar(64) DEFAULT NULL COMMENT '订单号',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_ip` varchar(64) DEFAULT NULL COMMENT '添加ip',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='提现记录表：zc_acc_withdraw_cash';

-- ----------------------------
-- Records of zc_acc_withdraw_cash
-- ----------------------------

DROP TABLE IF EXISTS `zc_acc_account_statistics`;
CREATE TABLE `zc_acc_account_statistics` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `name` varchar(32) DEFAULT NULL COMMENT '名称',
  `amount` decimal(20,2) DEFAULT NULL COMMENT '统计金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT=' 账户统计表：zc_acc_account_statistics';

-- ----------------------------
-- Table structure for `zc_cs_credit_score`
-- ----------------------------
DROP TABLE IF EXISTS `zc_cs_credit_score`;
CREATE TABLE `zc_cs_credit_score` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `user_id` int(11) DEFAULT NULL COMMENT '用户',
  `balance_score` decimal(20,2) DEFAULT NULL COMMENT '信用总评分',
  `sys_score` decimal(20,2) DEFAULT NULL COMMENT '系统评分',
  `zmxy_score` decimal(20,2) DEFAULT NULL COMMENT '芝麻信用分(未授权：-1)',
  `version` int(11) DEFAULT NULL COMMENT '版本控制',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='信用评分账户表：zc_cs_credit_score';

-- ----------------------------
-- Records of zc_cs_credit_score
-- ----------------------------

-- ----------------------------
-- Table structure for `zc_cs_credit_score_log`
-- ----------------------------
DROP TABLE IF EXISTS `zc_cs_credit_score_log`;
CREATE TABLE `zc_cs_credit_score_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `user_id` int(11) DEFAULT NULL COMMENT '用户',
  `score` decimal(20,2) DEFAULT NULL COMMENT '操作信用分',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `balance_score` decimal(20,2) DEFAULT NULL COMMENT '总信用分',
  `content` varchar(512) DEFAULT NULL COMMENT '日志内容',
  `payment_type` int(11) DEFAULT NULL COMMENT '收支方式',
  `order_no` varchar(128) DEFAULT NULL COMMENT '关联订单',
  `operator` varchar(64) DEFAULT NULL COMMENT '操作用户',
  `remark` varchar(64) DEFAULT NULL COMMENT '备注',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='信用评分日志表：zc_cs_credit_score_log';

-- ----------------------------
-- Records of zc_cs_credit_score_log
-- ----------------------------

-- ----------------------------
-- Table structure for `zc_jf_integral_account`
-- ----------------------------
DROP TABLE IF EXISTS `zc_jf_integral_account`;
CREATE TABLE `zc_jf_integral_account` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `user_id` int(11) DEFAULT NULL COMMENT '用户',
  `total_integral` decimal(20,2) DEFAULT NULL COMMENT '积分总额',
  `balance_integral` decimal(20,2) DEFAULT NULL COMMENT '可用积分',
  `expense_integral` decimal(20,2) DEFAULT NULL COMMENT '消费积分',
  `freeze_integral` decimal(20,2) DEFAULT NULL COMMENT '冻结积分',
  `grade_integral` int(11) DEFAULT NULL COMMENT '积分等级',
  `version` int(11) DEFAULT NULL COMMENT '版本控制',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分账户表：zc_jf_integral_account';

-- ----------------------------
-- Records of zc_jf_integral_account
-- ----------------------------

-- ----------------------------
-- Table structure for `zc_jf_integral_log`
-- ----------------------------
DROP TABLE IF EXISTS `zc_jf_integral_log`;
CREATE TABLE `zc_jf_integral_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `user_id` int(11) DEFAULT NULL COMMENT '用户',
  `integral` decimal(20,2) DEFAULT NULL COMMENT '操作积分',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `total_integral` decimal(20,2) DEFAULT NULL COMMENT '积分总额',
  `balance_integral` decimal(20,2) DEFAULT NULL COMMENT '可用积分',
  `expense_integral` decimal(20,2) DEFAULT NULL COMMENT '消费积分',
  `freeze_integral` decimal(20,2) DEFAULT NULL COMMENT '冻结积分',
  `to_user_id` int(11) DEFAULT NULL COMMENT '交易方用户',
  `content` varchar(512) DEFAULT NULL COMMENT '日志内容',
  `payments_type` int(11) DEFAULT NULL COMMENT '收支方式',
  `order_no` varchar(128) DEFAULT NULL COMMENT '关联订单',
  `remark` varchar(64) DEFAULT NULL COMMENT '备注',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分日志：zc_jf_integral_log';

-- ----------------------------
-- Records of zc_jf_integral_log
-- ----------------------------

-- ----------------------------
-- Table structure for `zc_m_app_upload_record`
-- ----------------------------
DROP TABLE IF EXISTS `zc_m_app_upload_record`;
CREATE TABLE `zc_m_app_upload_record` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `version_id` varchar(8) DEFAULT NULL COMMENT '版本id',
  `version_no` varchar(32) DEFAULT NULL COMMENT '版本号',
  `content` varchar(128) DEFAULT NULL COMMENT '内容',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `url` varchar(128) DEFAULT NULL COMMENT '下载链接',
  `operator_id` int(11) DEFAULT NULL COMMENT '操作管理员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='安卓包上传记录：zc_m_app_upload_record';

-- ----------------------------
-- Records of zc_m_app_upload_record
-- ----------------------------

-- ----------------------------
-- Table structure for `zc_m_log`
-- ----------------------------
DROP TABLE IF EXISTS `zc_m_log`;
CREATE TABLE `zc_m_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `operator_id` int(11) DEFAULT NULL COMMENT '管理员id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `type` varchar(16) DEFAULT NULL COMMENT '类型',
  `content` varchar(512) DEFAULT NULL COMMENT '操作内容',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_ip` varchar(64) DEFAULT NULL COMMENT '添加ip',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统操作日志表：zc_m_log';

-- ----------------------------
-- Records of zc_m_log
-- ----------------------------

-- ----------------------------
-- Table structure for `zc_m_notice_message`
-- ----------------------------
DROP TABLE IF EXISTS `zc_m_notice_message`;
CREATE TABLE `zc_m_notice_message` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `nid` varchar(32) DEFAULT NULL COMMENT '标识',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `send_user_id` int(11) DEFAULT NULL COMMENT '发送用户',
  `receive_user_id` int(11) DEFAULT NULL COMMENT '接收用户',
  `operator_id` int(11) DEFAULT NULL COMMENT '管理员',
  `state` int(11) DEFAULT NULL COMMENT '标题',
  `title` varchar(128) DEFAULT NULL COMMENT '标题',
  `content` varchar(512) DEFAULT NULL COMMENT '内容',
  `result` varchar(64) DEFAULT NULL COMMENT '发送结果描述',
  `result_code` varchar(32) DEFAULT NULL COMMENT '发送结果代码',
  `receive_addr` varchar(64) DEFAULT NULL COMMENT '接收地址',
  `code` varchar(64) DEFAULT NULL COMMENT '验证码',
  `order_no` varchar(64) DEFAULT NULL COMMENT '订单号',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通知消息：zc_m_notice_message';

-- ----------------------------
-- Records of zc_m_notice_message
-- ----------------------------

-- ----------------------------
-- Table structure for `zc_m_operator`
-- ----------------------------
DROP TABLE IF EXISTS `zc_m_operator`;
CREATE TABLE `zc_m_operator` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `user_name` varchar(64) DEFAULT NULL COMMENT '用户名',
  `pwd` varchar(128) DEFAULT NULL COMMENT '登录密码',
  `pay_pwd` varchar(128) DEFAULT NULL COMMENT '交易密码',
  `mobile` varchar(64) DEFAULT NULL COMMENT '手机号',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `state` int(11) DEFAULT '0' COMMENT '状态',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `login_ip` varchar(64) DEFAULT NULL COMMENT '最后登录ip',
  `login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `add_manager` varchar(32) DEFAULT NULL COMMENT '添加用户',
  `update_manager` varchar(32) DEFAULT NULL COMMENT '修改用户',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  `pid` int(11) DEFAULT NULL COMMENT '父id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户关联',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='管理员表：zc_m_operator';

-- ----------------------------
-- Records of zc_m_operator
-- ----------------------------

-- ----------------------------
-- Table structure for `zc_m_order_task`
-- ----------------------------
DROP TABLE IF EXISTS `zc_m_order_task`;
CREATE TABLE `zc_m_order_task` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `type` varchar(128) DEFAULT NULL COMMENT '类型',
  `user_id` int(11) DEFAULT NULL COMMENT 'FK',
  `order_no` varchar(128) DEFAULT NULL COMMENT '关联订单号',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  `do_result` varchar(128) DEFAULT NULL COMMENT '处理结果',
  `do_time` datetime DEFAULT NULL COMMENT '处理时间',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单管理：zc_m_order_task';

-- ----------------------------
-- Records of zc_m_order_task
-- ----------------------------

-- ----------------------------
-- Table structure for `zc_m_role`
-- ----------------------------
DROP TABLE IF EXISTS `zc_m_role`;
CREATE TABLE `zc_m_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `name` varchar(64) DEFAULT NULL COMMENT '角色名称',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  `add_operator_id` int(11) DEFAULT NULL COMMENT '添加管理员',
  `be_user_id` int(11) DEFAULT NULL COMMENT '关联商家用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表：zc_m_role';

INSERT INTO `zc_m_role` VALUES ('1', '超级管理员', '1', '超级管理员', '1', null);

-- ----------------------------
-- Records of zc_m_role
-- ----------------------------

-- ----------------------------
-- Table structure for `zc_m_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `zc_m_role_menu`;
CREATE TABLE `zc_m_role_menu` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-菜单表：zc_m_role_menu';


INSERT INTO `zc_m_role_menu` VALUES ('1', '1', '3');
INSERT INTO `zc_m_role_menu` VALUES ('2', '1', '4');
INSERT INTO `zc_m_role_menu` VALUES ('3', '1', '5');
INSERT INTO `zc_m_role_menu` VALUES ('4', '1', '6');
INSERT INTO `zc_m_role_menu` VALUES ('5', '1', '7');
INSERT INTO `zc_m_role_menu` VALUES ('6', '1', '8');
INSERT INTO `zc_m_role_menu` VALUES ('7', '1', '9');
INSERT INTO `zc_m_role_menu` VALUES ('8', '1', '10');
INSERT INTO `zc_m_role_menu` VALUES ('9', '1', '75');
INSERT INTO `zc_m_role_menu` VALUES ('10', '1', '78');
INSERT INTO `zc_m_role_menu` VALUES ('11', '1', '77');
INSERT INTO `zc_m_role_menu` VALUES ('12', '1', '1');
INSERT INTO `zc_m_role_menu` VALUES ('13', '1', '2');
INSERT INTO `zc_m_role_menu` VALUES ('14', '1', '76');
INSERT INTO `zc_m_role_menu` VALUES ('15', '1', '79');
INSERT INTO `zc_m_role_menu` VALUES ('16', '1', '80');
INSERT INTO `zc_m_role_menu` VALUES ('17', '1', '81');
INSERT INTO `zc_m_role_menu` VALUES ('18', '1', '74');


-- ----------------------------
-- Records of zc_m_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for `zc_s_config`
-- ----------------------------
DROP TABLE IF EXISTS `zc_s_config`;
CREATE TABLE `zc_s_config` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `nid` varchar(64) DEFAULT NULL COMMENT '标识',
  `value` varchar(256) DEFAULT NULL COMMENT '值',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  `type` int(11) DEFAULT NULL COMMENT '分类',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统参数表：zc_s_config';

-- ----------------------------
-- Records of zc_s_config
-- ----------------------------

-- ----------------------------
-- Table structure for `zc_s_dict`
-- ----------------------------
DROP TABLE IF EXISTS `zc_s_dict`;
CREATE TABLE `zc_s_dict` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `nid` varchar(64) DEFAULT NULL COMMENT '标识',
  `nid_name` varchar(64) DEFAULT NULL COMMENT '标识名',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `value` varchar(256) DEFAULT NULL COMMENT '值',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典表：zc_s_dict';

-- ----------------------------
-- Records of zc_s_dict
-- ----------------------------

-- ----------------------------
-- Table structure for `zc_s_menu`
-- ----------------------------
DROP TABLE IF EXISTS `zc_s_menu`;
CREATE TABLE `zc_s_menu` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `name` varchar(64) DEFAULT NULL COMMENT '菜单名',
  `parent_id` int(11) DEFAULT NULL COMMENT '父级id',
  `href` varchar(128) DEFAULT NULL COMMENT '链接地址',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表：zc_s_menu';


INSERT INTO `zc_s_menu` VALUES ('1', '系统管理', '0', '', '1', '1', '1', null);
INSERT INTO `zc_s_menu` VALUES ('2', '宣传管理', '0', '', '1', '1', '1', null);
INSERT INTO `zc_s_menu` VALUES ('3', '角色管理', '1', '/supervisors/roles', '1', '1', '1', null);
INSERT INTO `zc_s_menu` VALUES ('4', '菜单表', '1', '/supervisors/menu', '1', '1', '1', null);
INSERT INTO `zc_s_menu` VALUES ('5', '系统参数设置', '1', 'supervisors/config', '1', '0', '1', '');
INSERT INTO `zc_s_menu` VALUES ('6', '数据字典', '1', '/system/dict', '1', '1', '1', null);
INSERT INTO `zc_s_menu` VALUES ('7', '模版管理', '1', 'supervisors/template', '1', '0', '1', '');
INSERT INTO `zc_s_menu` VALUES ('8', '管理员管理 ', '1', '/supervisors/list', '1', '1', '1', null);
INSERT INTO `zc_s_menu` VALUES ('9', '文章管理', '2', '/content/articleList', '1', '0', '1', '');
INSERT INTO `zc_s_menu` VALUES ('10', '栏目管理', '2', '/content/siteList', '1', '0', '1', '');
INSERT INTO `zc_s_menu` VALUES ('74', '票据管理', '0', '', '1', '0', '1', '');
INSERT INTO `zc_s_menu` VALUES ('75', '票据列表', '74', '/bills/list', '1', '0', '1', '');
INSERT INTO `zc_s_menu` VALUES ('76', '用户管理', '0', null, '1', '0', '1', null);
INSERT INTO `zc_s_menu` VALUES ('77', '会员列表', '76', '/user/list', '1', '0', '1', null);
INSERT INTO `zc_s_menu` VALUES ('78', '协商中订单', '74', '/bills/waitingList', '1', '0', '1', 'd');
INSERT INTO `zc_s_menu` VALUES ('79', '交易中订单', '74', '/bills/tradingList', '1', '0', '1', 'd');
INSERT INTO `zc_s_menu` VALUES ('80', '审核未通过', '74', '/bills/auditFailedList', '1', '0', '1', 'd');
INSERT INTO `zc_s_menu` VALUES ('81', '关闭订单', '74', '/bills/timeOutList', '1', '0', '1', 'd');

-- ----------------------------
-- Records of zc_s_menu
-- ----------------------------

-- ----------------------------
-- Table structure for `zc_s_template`
-- ----------------------------
DROP TABLE IF EXISTS `zc_s_template`;
CREATE TABLE `zc_s_template` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `name` varchar(32) DEFAULT NULL COMMENT '名称',
  `nid` varchar(128) DEFAULT NULL COMMENT '标识',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `type_sub` varchar(32) DEFAULT NULL COMMENT '子类型',
  `payments_type` varchar(32) DEFAULT NULL COMMENT '子类型',
  `title` varchar(512) DEFAULT NULL COMMENT '模版标题',
  `content` varchar(512) DEFAULT NULL COMMENT '模版内容',
  `route` int(32) DEFAULT NULL COMMENT '渠道',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模版表：zc_s_template';

-- ----------------------------
-- Records of zc_s_template
-- ----------------------------

-- ----------------------------
-- Table structure for `zc_u_user`
-- ----------------------------
DROP TABLE IF EXISTS `zc_u_user`;
CREATE TABLE `zc_u_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `user_name` varchar(64) DEFAULT NULL COMMENT '用户名',
  `open_id` varchar(64) DEFAULT NULL COMMENT '微信特征码',
  `pwd` varchar(128) DEFAULT NULL COMMENT '登录密码',
  `pay_pwd` varchar(128) DEFAULT NULL COMMENT '交易密码',
  `real_name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(64) DEFAULT NULL COMMENT '手机号',
  `card_no` varchar(64) DEFAULT NULL COMMENT '证件号码',
  `add_time` datetime DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表：zc_u_user';



-- ----------------------------
-- Records of zc_u_user
-- ----------------------------

-- ----------------------------
-- Table structure for `zc_u_user_business_apply`
-- ----------------------------
DROP TABLE IF EXISTS `zc_u_user_business_apply`;
CREATE TABLE `zc_u_user_business_apply` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `company_name` varchar(128) DEFAULT NULL COMMENT '公司名称',
  `user_name` varchar(128) DEFAULT NULL COMMENT '联系人',
  `content` varchar(256) DEFAULT NULL COMMENT '内容说明',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(64) DEFAULT NULL COMMENT '电话',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `do_time` datetime DEFAULT NULL COMMENT '处理时间',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商家申请表：zc_u_user_business_apply';

-- ----------------------------
-- Records of zc_u_user_business_apply
-- ----------------------------

-- ----------------------------
-- Table structure for `zc_u_user_identify`
-- ----------------------------
DROP TABLE IF EXISTS `zc_u_user_identify`;
CREATE TABLE `zc_u_user_identify` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `user_id` int(11) DEFAULT NULL COMMENT 'FK',
  `real_name_state` int(11) DEFAULT NULL COMMENT '实名认证状态(OCR、活体)',
  `email_state` int(11) DEFAULT NULL COMMENT '邮箱认证状态',
  `mobile_state` int(11) DEFAULT NULL COMMENT '手机号认证状态',
  `bind_card_num` int(11) DEFAULT NULL COMMENT '绑卡数量',
  `real_name_count` int(11) DEFAULT NULL COMMENT '实名认证次数',
  `card_img_state` int(11) DEFAULT NULL COMMENT '身份证图片认证状态',
  `octopus_state` int(11) DEFAULT NULL COMMENT '数据魔盒-运营商-数据认证状态',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  `login_fail_count` int(11) DEFAULT NULL COMMENT '登录失败次数',
  `e_sign_state` int(11) DEFAULT NULL COMMENT '是否创建e签宝印章',
  `is_recommend` int(11) DEFAULT NULL COMMENT '是否推荐',
  `cloud_moulage_state` int(11) DEFAULT NULL COMMENT '云合同印章创建状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户认证表：zc_u_user_identify';

-- ----------------------------
-- Records of zc_u_user_identify
-- ----------------------------

DROP TABLE IF EXISTS `zc_u_user_info`;
CREATE TABLE `zc_u_user_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `user_id` int(11) DEFAULT NULL COMMENT 'FK',
  `invite_user_id` int(11) DEFAULT NULL COMMENT '邀请用户id',
  `invite_code` varchar(32) DEFAULT NULL COMMENT '邀请码',
  `type` int(11) DEFAULT NULL COMMENT '用户类型',
  `user_nature` int(11) DEFAULT NULL COMMENT '用户类别',
  `card_type` int(11) DEFAULT NULL COMMENT '证件类型',
  `route` int(11) DEFAULT NULL COMMENT '注册渠道',
  `channel` varchar(32) DEFAULT NULL COMMENT '推广途径',
  `sex` int(11) DEFAULT '0' COMMENT '性别',
  `head_img` varchar(128) DEFAULT NULL COMMENT '头像',
  `e_sign_account_id` varchar(64) DEFAULT NULL COMMENT 'E签宝账户唯一标识',
  `e_sign_seal_data` TEXT DEFAULT NULL COMMENT 'E签宝电子签章数据',
  `cloud_contract_id` varchar(64) DEFAULT NULL COMMENT '云合同id',
  `zmxy_open_id` varchar(500) DEFAULT NULL COMMENT '芝麻信用授权标识',
  `elec_acct` varchar(64) DEFAULT NULL COMMENT '民泰电子账号',
  `ad_identifier` varchar(100) DEFAULT NULL COMMENT '设备标识符',
  `card_fg` varchar(100) DEFAULT NULL COMMENT '身份证国徽面',
  `card_bg` varchar(100) DEFAULT NULL COMMENT '身份证头像面',
  `login_lock_remark` varchar(128) DEFAULT NULL COMMENT '锁定备注',
  `province` varchar(32) DEFAULT NULL COMMENT '省',
  `city` varchar(32) DEFAULT NULL COMMENT '市',
  `area` varchar(32) DEFAULT NULL COMMENT '区',
  `address` varchar(128) DEFAULT NULL COMMENT '居住地址',
  `company_name` varchar(128) DEFAULT NULL COMMENT '公司名称',
  `company_type` int(11) DEFAULT NULL COMMENT '公司类型',
  `company_card_no` varchar(64) DEFAULT NULL COMMENT '公司证件号(企业征信代码)',
  `company_des` varchar(512) DEFAULT NULL COMMENT '公司描述',
  `legal_name` varchar(64) DEFAULT NULL COMMENT '企业法人姓名',
  `legal_card_no` varchar(128) DEFAULT NULL COMMENT '企业法人证件号',
  `add_ip` varchar(128) DEFAULT NULL COMMENT '注册ip',
  `company_project_des` varchar(512) DEFAULT NULL COMMENT '公司产品介绍',
  `company_project_type` varchar(64) DEFAULT NULL COMMENT '公司产品类型',
  `company_logo` varchar(128) DEFAULT NULL COMMENT 'logo图片地址',
  `company_registered_capital` varchar(128) DEFAULT NULL COMMENT '注册资本',
  `company_real_registered_capital` varchar(128) DEFAULT NULL COMMENT '实缴注册资本',
  `company_business_address` varchar(128) DEFAULT NULL COMMENT '公司经营地址',
  `company_business_period` varchar(32) DEFAULT NULL COMMENT '公司经营期限',
  `company_business_state` varchar(32) DEFAULT NULL COMMENT '公司经营状态',
  `company_telephone` varchar(32) DEFAULT NULL COMMENT '公司电话',
  `company_recommend_dec` varchar(512) DEFAULT NULL COMMENT '公司推荐信息',
  `company_business_license_pic` text DEFAULT NULL COMMENT '营业执照图片',
  `company_rules_pic` text DEFAULT NULL COMMENT '公司章程',
  `company_other_pic` text DEFAULT NULL COMMENT '公司其他图片',
  `company_cash_rule` varchar(128) DEFAULT NULL COMMENT '提现规则',
  `company_safety` varchar(256) DEFAULT NULL COMMENT '公司安全保障',
  `company_safety_type` varchar(64) DEFAULT NULL COMMENT '公司安全保障方式',
  `company_add_time` datetime DEFAULT NULL COMMENT '公司成立时间',
  `company_rate_most` varchar(32) DEFAULT NULL COMMENT '最高年化',
  `company_rate_lowest` varchar(32) DEFAULT NULL COMMENT '最低年化',
  `company_period_most` varchar(32) DEFAULT NULL COMMENT '最大期限',
  `company_period_lowest` varchar(32) DEFAULT NULL COMMENT '最小期限', 
  `cloud_moulage_id` varchar(256) DEFAULT NULL COMMENT '云合同签章id',
  `company_logo_pic` varchar(128) DEFAULT NULL COMMENT 'logo图片地址',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表：zc_u_user_info';

DROP TABLE IF EXISTS `zc_u_user_relation`;
CREATE TABLE `zc_u_user_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  `type` int(11) DEFAULT NULL COMMENT '类型:借款人-商家;关注商家',
  `user_id` int(11) DEFAULT NULL COMMENT '关注者/借款人',
  `be_user_id` int(11) DEFAULT NULL COMMENT '被关注者/商家',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户关系表：zc_u_user_relation';

-- ----------------------------
-- Table structure for `zc_xc_article`
-- ----------------------------
DROP TABLE IF EXISTS `zc_xc_article`;
CREATE TABLE `zc_xc_article` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `site_id` int(11) DEFAULT NULL COMMENT '所属栏目',
  `title` varchar(128) DEFAULT NULL COMMENT '标题',
  `introduction` varchar(512) DEFAULT NULL COMMENT '简介',
  `content` text COMMENT '内容',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `is_hot` int(11) DEFAULT NULL COMMENT '热文章',
  `clicks` int(11) DEFAULT NULL COMMENT '点击量',
  `pic_path` varchar(512) DEFAULT NULL COMMENT '图片地址',
  `url` varchar(64) DEFAULT NULL COMMENT '链接',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `update_ip` varchar(64) DEFAULT NULL COMMENT '最后修改ip',
  `update_operator_id` int(11) DEFAULT NULL COMMENT '添加管理员',
  `add_operator_id` int(11) DEFAULT NULL COMMENT '添加管理员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章表：zc_xc_article';

-- ----------------------------
-- Records of zc_xc_article
-- ----------------------------

-- ----------------------------
-- Table structure for `zc_xc_site`
-- ----------------------------
DROP TABLE IF EXISTS `zc_xc_site`;
CREATE TABLE `zc_xc_site` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `nid` varchar(64) DEFAULT NULL COMMENT '标识',
  `pid` int(11) DEFAULT NULL COMMENT '父id',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `url` varchar(512) DEFAULT NULL COMMENT '跳转链接',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `introduction` varchar(512) DEFAULT NULL COMMENT '简介',
  `content` text COMMENT '内容',
  `pic_path` varchar(512) DEFAULT NULL COMMENT '图片地址',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `update_ip` varchar(64) DEFAULT NULL COMMENT '最后修改ip',
  `operate_user` varchar(64) DEFAULT NULL COMMENT '最后操作管理员',
  `add_operator_id` int(11) DEFAULT NULL COMMENT '添加管理员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='栏目表：zc_xc_site';

-- ----------------------------
-- Table structure for zc_pt_bonus_coupons
-- ----------------------------
DROP TABLE IF EXISTS `zc_pt_bonus_coupons`;
CREATE TABLE `zc_pt_bonus_coupons` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `coupons_no` varchar(64) DEFAULT NULL COMMENT '券编号',
  `name` varchar(64) DEFAULT NULL COMMENT '券名称',
  `amount` decimal(20,2) DEFAULT NULL COMMENT '券面金额',
  `amount_type` int(11) DEFAULT NULL COMMENT '券面类型',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  `validity_type` int(11) DEFAULT NULL COMMENT '有效期类型 天数 截止时间',
  `validity_value` varchar(32) DEFAULT NULL COMMENT '有效期值',
  `start_time` datetime DEFAULT NULL COMMENT '发放开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '发放结束时间',
  `quota` int(11) DEFAULT NULL COMMENT '配额',
  `use_quota` int(11) DEFAULT NULL COMMENT '已发放数量',
  `quota_of_user` int(11) DEFAULT 0 COMMENT '用户发放限制',
  `grant_single_invest_amount` decimal(20,2) DEFAULT NULL COMMENT '发放限制单笔投资额度',
  `grant_total_invest_amount` decimal(20,2) DEFAULT NULL COMMENT '发放限制累计投资额度',
  `use_single_invest_amount` decimal(20,2) DEFAULT NULL COMMENT '使用限制最小投资额度',
  `summary` varchar(128) DEFAULT NULL COMMENT '简介',
  `remark` varchar(64) DEFAULT NULL COMMENT '备注',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `be_user_id` int(11) DEFAULT NULL COMMENT '商家id',
  `add_operator_id` int(11) DEFAULT NULL COMMENT '添加管理员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='红包表：zc_pt_bonus_coupons';

-- ----------------------------
-- Records of zc_pt_bonus_coupons
-- ----------------------------
-- ----------------------------
-- Table structure for zc_pt_bonus_coupons_record
-- ----------------------------
DROP TABLE IF EXISTS `zc_pt_bonus_coupons_record`;
CREATE TABLE `zc_pt_bonus_coupons_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `use_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `bonus_coupons_id` int(11) DEFAULT NULL COMMENT '红包id',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  `operator_id` int(11) DEFAULT NULL COMMENT '发放管理员：0-系统发放',
  `use_time` datetime DEFAULT NULL COMMENT '使用时间',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `use_type` int(11) DEFAULT NULL COMMENT '使用类型',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `remark` varchar(64) DEFAULT NULL COMMENT '备注',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `user_id` int(11) DEFAULT NULL COMMENT 'ID',
  `be_user_id` int(11) DEFAULT NULL COMMENT '商家id',
  `real_amount` decimal(20,2) DEFAULT NULL COMMENT '最终金额',
  `real_amount_type` int(2) DEFAULT 1 COMMENT '最终金额返利方式1:金额 2:比例',
  `vip_amount` decimal(20,2) DEFAULT NULL COMMENT 'vip红包 最下级使用金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8 COMMENT='红包发放记录表：zc_pt_bonus_coupons_record';

-- ----------------------------
-- Records of zc_pt_bonus_coupons_record
-- ----------------------------

-- ----------------------------
-- Table structure for zc_pt_promotion
-- ----------------------------
DROP TABLE IF EXISTS `zc_pt_promotion`;
CREATE TABLE `zc_pt_promotion` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `name` varchar(128) DEFAULT NULL COMMENT '活动推广标题',
  `way` int(11) DEFAULT NULL COMMENT '推广方式登录、注册、充值、提现、抽奖、续投、现金借款、邀请',
  `content` text COMMENT '推广内容',
  `summary` varchar(512) DEFAULT NULL COMMENT '摘要',
  `pic` varchar(256) DEFAULT NULL,
  `url` varchar(256) DEFAULT NULL,
  `state` int(11) DEFAULT NULL COMMENT '状态',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `remark` varchar(64) DEFAULT NULL COMMENT '备注',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_operator_id` int(11) DEFAULT NULL,
  `be_user_id` int(11) DEFAULT NULL COMMENT '商家id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='活动推广表：zc_pt_promotion';

-- ----------------------------
-- Records of zc_pt_promotion
-- ----------------------------

-- ----------------------------
-- Table structure for zc_pt_promotion_prize_config
-- ----------------------------
DROP TABLE IF EXISTS `zc_pt_promotion_prize_config`;
CREATE TABLE `zc_pt_promotion_prize_config` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `promotion_id` int(11) DEFAULT NULL COMMENT '推广id',
  `type` int(11) DEFAULT NULL COMMENT '奖品类型',
  `prize_no` varchar(64) DEFAULT NULL COMMENT '对应编号',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_operator_id` int(11) DEFAULT NULL,
  `be_user_id` int(11) DEFAULT NULL COMMENT '商家id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='活动推广奖励配置：zc_pt_promotion_prize_config';


-- ----------------------------
-- Table structure for zc_pt_promotion_prize_record
-- ----------------------------
DROP TABLE IF EXISTS `zc_pt_promotion_prize_record`;
CREATE TABLE `zc_pt_promotion_prize_record` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `user_id` int(11) DEFAULT NULL COMMENT '用户',
  `prize_config_id` int(11) DEFAULT NULL COMMENT '推广配置id',
  `promotion_id` int(11) DEFAULT NULL COMMENT '推广id',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  `value` varchar(128) DEFAULT NULL COMMENT '活动条件记录值',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `operator_id` int(11) DEFAULT NULL COMMENT '管理员',
  `remark` varchar(64) DEFAULT NULL COMMENT '备注',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `be_user_id` int(11) DEFAULT NULL COMMENT '商家id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1039 DEFAULT CHARSET=utf8 COMMENT='活动推广奖励记录表：zc_pt_promotion_prize_record';

-- ----------------------------
-- Records of zc_pt_promotion_prize_record
-- ----------------------------




-- ----------------------------
-- init
-- ----------------------------
/*INSERT INTO `zc_mall`.`zc_u_user` ( `user_name`, `pwd`, `pay_pwd`, `real_name`, `email`, `mobile`, `card_no`, `add_time`) VALUES ( 'admin', NULL, NULL, NULL, NULL, NULL, NULL, DATE(NOW()));
INSERT INTO `zc_mall`.`zc_u_user_info` ( `user_id`, `invite_user_id`, `invite_code`, `type`, `user_nature`, `card_type`, `route`, `channel`, `sex`, `head_img`, `e_sign_account_id`, `e_sign_seal_data`, `cloud_contract_id`, `zmxy_open_id`, `elec_acct`, `ad_identifier`, `card_fg`, `card_bg`, `login_lock_remark`, `province`, `city`, `area`, `address`, `company_name`, `company_type`, `company_card_no`, `company_des`, `legal_name`, `legal_card_no`, `add_ip`, `company_project_des`, `company_project_type`, `company_logo`, `company_registered_capital`, `company_real_registered_capital`, `company_business_address`, `company_business_period`, `company_business_state`, `company_telephone`, `company_recommend_dec`, `company_business_license_pic`, `company_rules_pic`, `company_other_pic`, `company_cash_rule`, `company_safety`, `company_safety_type`, `company_add_time`, `company_rate_most`, `company_rate_lowest`, `company_period_most`, `company_period_lowest`, `cloud_moulage_id`, `company_logo_pic`, `remark`) VALUES ( '1', NULL, '0', '1', '1', '0', '1', '0', '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '浙江', '杭州', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `zc_mall`.`zc_u_user_relation` ( `state`, `type`, `user_id`, `be_user_id`, `add_time`) VALUES ( '1', '1', '1', '1', DATE(NOW()));
INSERT INTO `zc_mall`.`zc_u_user_identify` ( `user_id`, `real_name_state`, `email_state`, `mobile_state`, `bind_card_num`, `real_name_count`, `card_img_state`, `octopus_state`, `state`, `login_fail_count`, `e_sign_state`, `is_recommend`, `cloud_moulage_state`) VALUES ( '1', '-1', '-1', '1', '0', '0', '-1', '-1', '1', '0', '-1', '-1', '-1');
INSERT INTO `zc_mall`.`zc_acc_account` ( `user_id`, `total`, `balance`, `freeze_amount`, `version`) VALUES ( '1', '0.00', '0.00', '0.00', '1');
INSERT INTO `zc_mall`.`zc_jf_integral_account` ( `user_id`, `total_integral`, `balance_integral`, `expense_integral`, `freeze_integral`, `grade_integral`, `version`) VALUES ( '1', '0.00', '0.00', '0.00', '0.00', '0', '0');
INSERT INTO `zc_mall`.`zc_cs_credit_score` ( `user_id`, `balance_score`, `sys_score`, `zmxy_score`, `version`) VALUES ( '1', '0.00', '0.00', '-1.00', '0');*/

INSERT INTO `zc_mall`.`zc_m_operator` ( `name`, `user_name`, `pwd`, `pay_pwd`, `mobile`, `email`, `state`, `role_id`, `add_time`, `login_ip`, `login_time`, `add_manager`, `update_manager`, `update_time`, `remark`, `pid`, `user_id`) VALUES ( 'admin', 'admin', '0192023a7bbd73250516f069df18b500', 'e10adc3949ba59abbe56e057f20f883e', '18233563702', NULL, '1', '1',DATE(NOW()), '172.16.13.2', NULL , NULL, NULL, NULL, NULL, '0', NULL);


-- ----------------------------
-- init
-- ----------------------------