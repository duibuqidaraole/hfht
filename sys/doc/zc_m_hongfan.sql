SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `zc_u_user_address`
-- ----------------------------
DROP TABLE IF EXISTS `zc_u_user_address`;
CREATE TABLE `zc_u_user_address` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `user_id` int(11) DEFAULT NULL COMMENT '用户',
  `sex` int(2) DEFAULT NULL COMMENT '性别',
  `name` VARCHAR(128) DEFAULT NULL COMMENT '收件人',
  `mobile` VARCHAR(32) DEFAULT NULL COMMENT '手机号',
  `post_code` VARCHAR(32) DEFAULT NULL COMMENT '邮编',
  `province` VARCHAR(32) DEFAULT NULL COMMENT '省',
  `city` VARCHAR(32) DEFAULT NULL COMMENT '市',
  `area` VARCHAR(32) DEFAULT NULL COMMENT '区',
  `address` VARCHAR(32) DEFAULT NULL COMMENT '地址',
  `state` INT(2) DEFAULT NULL COMMENT '状态',
  `add_time` DATETIME DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户地址表：zc_u_user_address';

-- ----------------------------
-- Records of zc_u_user_address
-- ----------------------------


-- ----------------------------
-- Table structure for `zc_g_goods_category`
-- ----------------------------
DROP TABLE IF EXISTS `zc_g_goods_category`;
CREATE TABLE `zc_g_goods_category` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `name` VARCHAR(128) DEFAULT NULL COMMENT '姓名',
  `nid` VARCHAR(32) DEFAULT NULL COMMENT '标识',
  `update_operator_id` int(11) DEFAULT NULL COMMENT '修改管理员id',
  `update_time` DATETIME DEFAULT NULL COMMENT '修改时间',
  `add_operator_id` int(11) DEFAULT NULL COMMENT '添加管理员id',
  `add_time` DATETIME DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品分类表：zc_g_goods_category';

-- ----------------------------
-- Records of zc_g_goods_category
-- ----------------------------


-- ----------------------------
-- Table structure for `zc_g_goods_brand`
-- ----------------------------
DROP TABLE IF EXISTS `zc_g_goods_brand`;
CREATE TABLE `zc_g_goods_brand` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `name` VARCHAR(128) DEFAULT NULL COMMENT '姓名',
  `nid` VARCHAR(32) DEFAULT NULL COMMENT '标识',
  `update_operator_id` int(11) DEFAULT NULL COMMENT '修改管理员id',
  `update_time` DATETIME DEFAULT NULL COMMENT '修改时间',
  `add_operator_id` int(11) DEFAULT NULL COMMENT '添加管理员id',
  `add_time` DATETIME DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品品牌表：zc_g_goods_brand';

-- ----------------------------
-- Records of zc_g_goods_brand
-- ----------------------------


-- ----------------------------
-- Table structure for `zc_g_goods_spu`
-- ----------------------------
DROP TABLE IF EXISTS `zc_g_goods_spu`;
CREATE TABLE `zc_g_goods_spu` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `no` VARCHAR(64) DEFAULT NULL COMMENT '商品唯一编号',
  `name` VARCHAR(128) DEFAULT NULL COMMENT '名称',
  `show` VARCHAR(128) DEFAULT NULL COMMENT '搜索展示',
  `less_amount` DECIMAL(20,2) DEFAULT 0 COMMENT '最低金额',
  `less_amount_with_out_discount` DECIMAL(20,2) DEFAULT 0 COMMENT '最低金额(未减优惠)',
  `most_amount` DECIMAL(20,2) DEFAULT 0 COMMENT '最高金额',
  `most_amount_with_out_discount` DECIMAL(20,2) DEFAULT 0 COMMENT '最高金额(未减优惠)',
  `goods_category_id` int(11) DEFAULT NULL COMMENT '分类id',
  `goods_brand_id` int(11) DEFAULT NULL COMMENT '品牌id',
  `update_operator_id` int(11) DEFAULT NULL COMMENT '修改管理员id',
  `update_time` DATETIME DEFAULT NULL COMMENT '修改时间',
  `add_operator_id` int(11) DEFAULT NULL COMMENT '添加管理员id',
  `add_time` DATETIME DEFAULT NULL COMMENT '添加时间',
  `recommend_date` DATETIME DEFAULT NULL COMMENT '推荐时间',
  `recommend_site` VARCHAR(128) DEFAULT NULL COMMENT '推荐位置',
  `recommend_ranking` int(3) DEFAULT 0 COMMENT '推荐位置排序',
  `goodsSkuCount` int(11) DEFAULT 0 COMMENT '上架商品数目',
  `is_vip_prince` int(2) DEFAULT 0 COMMENT '是否使用vip折扣',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品SPU表：zc_g_goods_spu';

-- ----------------------------
-- Records of zc_g_goods_spu
-- ----------------------------


-- ----------------------------
-- Table structure for `zc_g_goods_spec`
-- ----------------------------
DROP TABLE IF EXISTS `zc_g_goods_spec`;
CREATE TABLE `zc_g_goods_spec` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `no` VARCHAR(64) DEFAULT NULL COMMENT '商品唯一编号',
  `name` VARCHAR(128) DEFAULT NULL COMMENT '名称',
  `update_operator_id` int(11) DEFAULT NULL COMMENT '修改管理员id',
  `update_time` DATETIME DEFAULT NULL COMMENT '修改时间',
  `add_operator_id` int(11) DEFAULT NULL COMMENT '添加管理员id',
  `add_time` DATETIME DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品规格表：zc_g_goods_spec';

-- ----------------------------
-- Records of zc_g_goods_spec
-- ----------------------------


-- ----------------------------
-- Table structure for `zc_g_goods_spec_value`
-- ----------------------------
DROP TABLE IF EXISTS `zc_g_goods_spec_value`;
CREATE TABLE `zc_g_goods_spec_value` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `goods_spec_id` int(11) DEFAULT NULL COMMENT '规格id',
  `value` VARCHAR(128) DEFAULT NULL COMMENT '规格值',
  `update_operator_id` int(11) DEFAULT NULL COMMENT '修改管理员id',
  `update_time` DATETIME DEFAULT NULL COMMENT '修改时间',
  `add_operator_id` int(11) DEFAULT NULL COMMENT '添加管理员id',
  `add_time` DATETIME DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品规格值表：zc_g_goods_spec_value';

-- ----------------------------
-- Records of zc_g_goods_spec
-- ----------------------------


-- ----------------------------
-- Table structure for `zc_g_goods_spu_spec`
-- ----------------------------
DROP TABLE IF EXISTS `zc_g_goods_spu_spec`;
CREATE TABLE `zc_g_goods_spu_spec` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `goods_spu_id` int(11) DEFAULT NULL COMMENT 'spu_id',
  `goods_spec_id` int(11) DEFAULT NULL COMMENT 'spec_id',
  `update_operator_id` int(11) DEFAULT NULL COMMENT '修改管理员id',
  `update_time` DATETIME DEFAULT NULL COMMENT '修改时间',
  `add_operator_id` int(11) DEFAULT NULL COMMENT '添加管理员id',
  `add_time` DATETIME DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品SPU-规格表：zc_g_goods_spu_spec';

-- ----------------------------
-- Records of zc_g_goods_spu_spec
-- ----------------------------


-- ----------------------------
-- Table structure for `zc_g_goods_spu_spec`
-- ----------------------------
DROP TABLE IF EXISTS `zc_g_goods_sku`;
CREATE TABLE `zc_g_goods_sku` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `no` VARCHAR(64) DEFAULT NULL COMMENT 'sku编号，唯一',
  `name` VARCHAR(128) DEFAULT NULL COMMENT '名称',
  `description` VARCHAR(128) DEFAULT NULL COMMENT '描述',
  `prince` DECIMAL(20,2) DEFAULT NULL COMMENT '售价',
  `discount` DECIMAL(20,2) DEFAULT NULL COMMENT '折扣',
  `stock` int(11) DEFAULT NULL COMMENT '库存',
  `state` int(2) DEFAULT NULL COMMENT '状态',
  `goods_spu_id` int(11) DEFAULT NULL COMMENT 'spu_id',
  `img` VARCHAR(128) DEFAULT NULL COMMENT '图片地址',
  `pc_img` VARCHAR(128) DEFAULT NULL COMMENT 'pc图片地址',
  `summary` VARCHAR(128) DEFAULT NULL COMMENT '简介',
  `recommend_time` DATETIME DEFAULT NULL COMMENT '推荐时间',
  `sales_volume` int(11) DEFAULT NULL COMMENT '销量',
  `update_operator_id` int(11) DEFAULT NULL COMMENT '修改管理员id',
  `update_time` DATETIME DEFAULT NULL COMMENT '修改时间',
  `add_operator_id` int(11) DEFAULT NULL COMMENT '添加管理员id',
  `add_time` DATETIME DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品SKU表：zc_g_goods_sku';

-- ----------------------------
-- Records of zc_g_goods_sku
-- ----------------------------


-- ----------------------------
-- Table structure for `zc_g_goods_sku_spec_value`
-- ----------------------------
DROP TABLE IF EXISTS `zc_g_goods_sku_spec_value`;
CREATE TABLE `zc_g_goods_sku_spec_value` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `sku_id` INT(11) DEFAULT NULL COMMENT 'sku的Id',
  `goods_spec_value_id` INT(11) DEFAULT NULL COMMENT '规格值id',
  `update_operator_id` int(11) DEFAULT NULL COMMENT '修改管理员id',
  `update_time` DATETIME DEFAULT NULL COMMENT '修改时间',
  `add_operator_id` int(11) DEFAULT NULL COMMENT '添加管理员id',
  `add_time` DATETIME DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品sku-规格值表：zc_g_goods_sku_spec_value';

-- ----------------------------
-- Records of zc_g_goods_sku_spec_value
-- ----------------------------


-- ----------------------------
-- Table structure for `zc_g_order_cart`
-- ----------------------------
DROP TABLE IF EXISTS `zc_g_order_cart`;
CREATE TABLE `zc_g_order_cart` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `sku_id` INT(11) DEFAULT NULL COMMENT 'sku的Id',
  `user_id` INT(11) DEFAULT NULL COMMENT '用户id',
  `number` int(11) DEFAULT NULL COMMENT '数量',
  `prince` DECIMAL(20,2) DEFAULT NULL COMMENT '价格',
  `state` int(2) DEFAULT NULL COMMENT '状态',
  `add_time` DATETIME DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='购物车表：zc_g_order_cart';

-- ----------------------------
-- Records of zc_g_order_cart
-- ----------------------------


-- ----------------------------
-- Table structure for `zc_g_order_info`
-- ----------------------------
DROP TABLE IF EXISTS `zc_g_order_info`;
CREATE TABLE `zc_g_order_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `no` VARCHAR(64) DEFAULT NULL COMMENT '订单编号',
  `user_id` INT(11) DEFAULT NULL COMMENT '用户id',
  `update_operator_id` INT(11) DEFAULT NULL COMMENT '更新管理员id',
  `item_count` INT(11) DEFAULT NULL COMMENT '商品计数',
  `payment` int(2) DEFAULT NULL COMMENT '支付方式',
  `payment_trade_no` VARCHAR(64) DEFAULT NULL COMMENT '支付订单号',
  `amount` DECIMAL(20,2) DEFAULT NULL COMMENT '商品总额',
  `freight` DECIMAL(20,2) DEFAULT NULL COMMENT '运费',
  `vip_rate` DECIMAL(20,2) DEFAULT NULL COMMENT 'vip折扣',
  `promotion_coupons_amount` DECIMAL(20,2) DEFAULT NULL COMMENT '活动券抵扣金额',
  `balance_pay` DECIMAL(20,2) DEFAULT NULL COMMENT '余额支付',
  `amount_real` DECIMAL(20,2) DEFAULT NULL COMMENT '实际付款',
  `state` int(2) DEFAULT NULL COMMENT '状态',
  `before_state` int(2) DEFAULT NULL COMMENT '前一状态',
  `order_logistics_id` int(11) DEFAULT NULL COMMENT '物流id',
  `add_time` DATETIME DEFAULT NULL COMMENT '添加时间',
  `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
  `remark` VARCHAR(128) DEFAULT NULL COMMENT '备注',

  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单信息表：zc_g_order_info';

-- ----------------------------
-- Records of zc_g_order_info
-- ----------------------------

DROP TABLE IF EXISTS `zc_g_order_info_log`;
CREATE TABLE `zc_g_order_info_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `type` INT(2) DEFAULT NULL COMMENT '类型',
  `order_info_id` INT(11) DEFAULT NULL COMMENT '订单id',
  `user_id` INT(11) DEFAULT NULL COMMENT '用户id',
  `operator_id` INT(11) DEFAULT NULL COMMENT '管理员id',
  `content` VARCHAR(128) DEFAULT NULL COMMENT '内容',
  `add_time` DATETIME DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单信息表';

-- ----------------------------
-- Table structure for `zc_g_order_goods`
-- ----------------------------
DROP TABLE IF EXISTS `zc_g_order_goods`;
CREATE TABLE `zc_g_order_goods` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `order_id` INT(11) DEFAULT NULL COMMENT '订单id',
  `sku_id` INT(11) DEFAULT NULL COMMENT 'sku的Id',
  `goods_promotion_record_id` INT(11) DEFAULT NULL COMMENT '活动记录',
  `goods_name` VARCHAR(128) DEFAULT NULL COMMENT '商品名称',
  `number` int(11) DEFAULT NULL COMMENT '商品数量',
  `prince` DECIMAL(20,2) DEFAULT NULL COMMENT '价格',
  `discount` DECIMAL(20,2) DEFAULT NULL COMMENT '折扣',
  `remark` VARCHAR(128) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单商品表：zc_g_order_goods';

-- ----------------------------
-- Records of zc_g_order_goods
-- ----------------------------


-- ----------------------------
-- Table structure for `zc_g_order_logistics`
-- ----------------------------
DROP TABLE IF EXISTS `zc_g_order_logistics`;
CREATE TABLE `zc_g_order_logistics` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `name` VARCHAR(64) DEFAULT NULL COMMENT '收件人',
  `mobile` VARCHAR(32) DEFAULT NULL COMMENT '手机号',
  `post_code` VARCHAR(32) DEFAULT NULL COMMENT '邮编',
  `province` VARCHAR(32) DEFAULT NULL COMMENT '省',
  `city` VARCHAR(32) DEFAULT NULL COMMENT '市',
  `area` VARCHAR(32) DEFAULT NULL COMMENT '区',
  `address` VARCHAR(255) DEFAULT NULL COMMENT '地址',
  `order_id` INT(11) DEFAULT NULL COMMENT '订单id',
  `user_address_id` INT(11) DEFAULT NULL COMMENT '收货地址id',
  `logistics_company_no` VARCHAR(32) DEFAULT NULL COMMENT '物流公司编号',
  `post_no` VARCHAR(128) DEFAULT NULL COMMENT '快递编号',
  `send_time` DATETIME DEFAULT NULL COMMENT '发货时间',
  `receive_time` DATETIME DEFAULT NULL COMMENT '收货时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物流表：zc_g_order_logistics';

-- ----------------------------
-- Records of zc_g_ order_logistics
-- ----------------------------


-- ----------------------------
-- Table structure for `zc_g_order_pay_history`
-- ----------------------------
DROP TABLE IF EXISTS `zc_g_order_pay_history`;
CREATE TABLE `zc_g_order_pay_history` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `payment_trade_no` VARCHAR(128) DEFAULT NULL COMMENT '支付订单号',
  `order_id` INT(11) DEFAULT NULL COMMENT '订单id',
  `amount` DECIMAL(20,2) DEFAULT NULL COMMENT '商品总额',
  `amount_real` DECIMAL(20,2) DEFAULT NULL COMMENT '实际支付金额',
  `request_json` VARCHAR(512) DEFAULT NULL COMMENT '请求参数',
  `response_json` VARCHAR(512) DEFAULT NULL COMMENT '响应参数',
  `remark` VARCHAR(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付记录表：zc_g_order_pay_history';

-- ----------------------------
-- Records of zc_g_order_pay_history
-- ----------------------------



-- ----------------------------
-- Table structure for `zc_g_order_goods_comment`
-- ----------------------------
DROP TABLE IF EXISTS `zc_g_order_goods_comment`;
CREATE TABLE `zc_g_order_goods_comment` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `pid` INT(11) DEFAULT NULL COMMENT 'pid',
  `type` INT(2) DEFAULT NULL COMMENT '类型',
  `order_goods_id` INT(11) DEFAULT NULL COMMENT '订单商品id',
  `user_id` INT(11) DEFAULT NULL COMMENT '用户id',
  `operator_id` INT(11) DEFAULT NULL COMMENT '管理员id',
  `state` INT(2) DEFAULT NULL COMMENT '状态2用户评论未审核，-1用户评论删除 -2用户评论审核未通过 1用户评论通过未评论 5用户评论已评论',
  `content` VARCHAR(512) DEFAULT NULL COMMENT '内容',
  `grade` INT(3) DEFAULT NULL COMMENT '评分',
  `img_pic` VARCHAR(512) DEFAULT NULL COMMENT '图片',
  `add_time` DATETIME DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单评论：zc_g_order_goods_comment';

-- ----------------------------
-- Records of zc_g_order_goods_comment
-- ----------------------------

-- ----------------------------
-- Table structure for `zc_g_goods_promotion`
-- ----------------------------
DROP TABLE IF EXISTS `zc_g_goods_promotion`;
CREATE TABLE `zc_g_goods_promotion` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `name` VARCHAR(512) DEFAULT NULL COMMENT '活动名称',
  `type` INT(3) DEFAULT 0 COMMENT '类型',
  `state` INT(3) DEFAULT 0 COMMENT '状态',
  `value` DECIMAL(20,2) DEFAULT 0 COMMENT '值',
  `value_Append` DECIMAL(20,2) DEFAULT 0 COMMENT '附加值',
  `update_operator_id` int(11) DEFAULT NULL COMMENT '修改管理员id',
  `update_time` DATETIME DEFAULT NULL COMMENT '修改时间',
  `add_operator_id` int(11) DEFAULT NULL COMMENT '添加管理员id',
  `add_time` DATETIME DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单评论：zc_g_goods_promotion';

-- ----------------------------
-- Records of zc_g_goods_promotion
-- ----------------------------

-- ----------------------------
-- Table structure for `zc_g_goods_promotion_record`
-- ----------------------------
DROP TABLE IF EXISTS `zc_g_goods_promotion_record`;
CREATE TABLE `zc_g_goods_promotion_record` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `promotion_id` INT(11) DEFAULT NULL COMMENT '商品活动id',
  `goods_sku_id` INT(11) DEFAULT NULL COMMENT 'sku_id',
  `begin_time` DATETIME DEFAULT NULL COMMENT '活动开始时间',
  `end_time` DATETIME DEFAULT NULL COMMENT '活动结束时间',
  `update_operator_id` int(11) DEFAULT NULL COMMENT '修改管理员id',
  `update_time` DATETIME DEFAULT NULL COMMENT '修改时间',
  `add_operator_id` int(11) DEFAULT NULL COMMENT '添加管理员id',
  `add_time` DATETIME DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单评论：zc_g_goods_promotion_record';

-- ----------------------------
-- Records of zc_g_goods_promotion_record
-- ----------------------------

-- ----------------------------
-- Table structure for `zc_pt_vip_coupons`
-- ----------------------------
DROP TABLE IF EXISTS `zc_pt_vip_coupons`;
CREATE TABLE `zc_pt_vip_coupons` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `grade` INT(2) DEFAULT NULL COMMENT '会员等级',
  `name` VARCHAR(64) DEFAULT NULL COMMENT '名称',
  `state` INT(2) DEFAULT NULL COMMENT '状态',
  `content` VARCHAR(512) DEFAULT NULL COMMENT '内容',
  `value` DECIMAL(20,2) DEFAULT NULL COMMENT '优惠值',
  `prince` DECIMAL(20,2) DEFAULT NULL COMMENT '价值',
  `validity_type` INT(2) DEFAULT NULL COMMENT '有效期类型:天数、截止时间',
  `validity_value` VARCHAR(32) DEFAULT NULL COMMENT '有效值期',
  `use_quota` INT(2) DEFAULT NULL COMMENT '已发放数量',
  `begin_time` DATETIME DEFAULT NULL COMMENT '开始时间',
  `update_operator_id` int(11) DEFAULT NULL COMMENT '修改管理员id',
  `update_time` DATETIME DEFAULT NULL COMMENT '修改时间',
  `add_operator_id` int(11) DEFAULT NULL COMMENT '添加管理员id',
  `add_time` DATETIME DEFAULT NULL COMMENT '添加时间',
  `prize_no` varchar(64) DEFAULT NULL COMMENT '编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='vip会员卡表：zc_pt_vip_coupons';

-- ----------------------------
-- Records of zc_pt_vip_coupons
-- ----------------------------



-- ----------------------------
-- Table structure for `zc_pt_user_vip_coupons`
-- ----------------------------
DROP TABLE IF EXISTS `zc_pt_user_vip_coupons`;
CREATE TABLE `zc_pt_user_vip_coupons` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `user_id` INT(11) DEFAULT NULL COMMENT '用户id',
  `vip_coupons_id` INT(11) DEFAULT NULL COMMENT '会员卡id',
  `state` INT(2) DEFAULT NULL COMMENT '状态',
  `begin_time` DATETIME DEFAULT NULL COMMENT '开始时间',
  `end_time` DATETIME DEFAULT NULL COMMENT '结束时间',
  `add_time` DATETIME DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户vip会员卡表：zc_pt_user_vip_coupons';

-- ----------------------------
-- Records of zc_pt_user_vip_coupons
-- ----------------------------