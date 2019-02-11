package com.zc.mall.core.common.constant;

import com.zc.sys.common.exception.BusinessException;

/**
 * 常量
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月6日
 */
public final class BaseConstant {

    /** Queue-property=========================================================start **/
    /**
     * 队列消息-属性参数
     **/
    public static final String QUEUE_PROPERTY = "code";
    /** Queue-property=========================================================end **/

    /** Model=========================================================start **/
    /**
     * 表前缀
     **/
    public static final String DB_PREFIX = "zc_";
    /**
     * 模块-s-系统配置
     **/
    public static final String MODEL_S = "s";
    /**
     * 模块-m-核心及管理
     **/
    public static final String MODEL_M = "m";
    /**
     * 模块-u-用户
     **/
    public static final String MODEL_U = "u";
    /**
     * 模块-acc-资金账户
     **/
    public static final String MODEL_Acc = "acc";
    /**
     * 模块-b-业务
     **/
    public static final String MODEL_B = "b";
    /**
     * 模块-xc-宣传
     **/
    public static final String MODEL_Xc = "xc";
    /**
     * 模块-jf-积分
     **/
    public static final String MODEL_Jf = "jf";
    /**
     * 模块-cs-信用分
     **/
    public static final String MODEL_Cs = "cs";
    /**
     * 模块-as-答题
     **/
    public static final String MODEL_As = "as";
    /**
     * 模块-d-票据
     **/
    public static final String MODEL_D = "d";
    /**
     * 模块-g-商城
     **/
    public static final String MODEL_G = "g";
    /**
     * 模块-pt-票据
     **/
    public static final String MODEL_Pt = "pt";
    /** Model=========================================================end **/

    /** Identify-State=========================================================start **/
    /**
     * 通用认证状态-未认证
     **/
    public static final int IDENTIFY_STATE_NO = -1;
    /**
     * 通用认证状态-已认证
     **/
    public static final int IDENTIFY_STATE_YES = 1;
    /**
     * 通用认证状态-认证处理中
     **/
    public static final int IDENTIFY_STATE_WAIT = 2;
    /**
     * 通用认证状态-认证失败
     **/
    public static final int IDENTIFY_STATE_FAIL = -2;
    /** Identify-State=========================================================end **/

    /** Business-State=========================================================start **/
    /**
     * 通用业务处理状态-未处理
     **/
    public static final int BUSINESS_STATE_NO = -1;
    /**
     * 通用业务处理状态-已处理/已认证
     **/
    public static final int BUSINESS_STATE_YES = 1;
    /**
     * 通用业务处理状态-处理中
     **/
    public static final int BUSINESS_STATE_WAIT = 2;
    /**
     * 通用业务处理状态-处理失败
     **/
    public static final int BUSINESS_STATE_FAIL = -2;
    /**
     * 通用业务处理状态-完成
     **/
    public static final int BUSINESS_STATE_FINASH = 5;
    /** Business-State=========================================================end **/

    /** Card-Type=========================================================start **/
    /**
     * 证件类型-身份证
     **/
    public static final int CARD_TYPE_IDENTIFY_CARD = 1;
    /**
     * 证件类型-社会统一信用代码
     **/
    public static final int CARD_TYPE_UNIFIED_SOCIAL_CREDIT_IDENTIFIER = 2;
    /** Card-Type=========================================================end **/

    /** Template-type=========================================================start **/
    /**
     * 资金模版
     **/
    public static final int TEMPLATE_TYPE_ACCOUNT = 1;
    /**
     * 通知信息模版
     **/
    public static final int TEMPLATE_TYPE_MESSAGE = 2;
    /**
     * 操作日志模版
     **/
    public static final int TEMPLATE_TYPE_OPERATOR_LOG = 3;
    /**
     * 积分日志模版
     **/
    public static final int TEMPLATE_TYPE_INTEGRAL = 4;
    /**
     * 协议模版
     **/
    public static final int TEMPLATE_TYPE_PROTOCOL = 5;
    /**
     * 信用分日志模版
     **/
    public static final int TEMPLATE_TYPE_CREDIT_SCORE = 6;
    /** Template-type=========================================================end **/

    /** Info-State=========================================================start **/
    /**
     * 通用信息启用状态-未启用
     **/
    public static final int INFO_STATE_NO = -1;
    /**
     * 通用信息启用状态-已启用
     **/
    public static final int INFO_STATE_YES = 1;
    /** Info-State=========================================================end **/

    /** PeriodUnit=========================================================start **/
    /**
     * 借款期限单位-月
     **/
    public static final int PERIODUNIT_MONTH = 1;
    /**
     * 借款期限单位-天
     **/
    public static final int PERIODUNIT_DAY = 2;
    /** PeriodUnit=========================================================end **/

    /** Repayment-Way=========================================================start **/
    /**
     * 还款方式-一次性还本付息
     **/
    public static final int REPAYMENT_WAY_ONETIME = 1;
    /**
     * 还款方式-等额本息（按月分期还款）
     **/
    public static final int REPAYMENT_WAY_INSTALLMENT = 2;
    /**
     * 还款方式-每期还息到期还本
     **/
    public static final int REPAYMENT_WAY_PERIOD_INTEREST = 3;

    /** Repayment-Way=========================================================end **/

    /** update-Way=========================================================start **/
    /**
     * 修改密码方式-设置
     **/
    public static final int UPDATE_PWD_WAY_SET = 1;
    /**
     * 修改密码方式-正常
     **/
    public static final int UPDATE_PWD_WAY_UPDATE = 2;
    /**
     * 修改密码方式-忘记
     **/
    public static final int UPDATE_PWD_WAY_FORGET = 3;
    /** update-Way=========================================================end **/

    /** NoticeMessage-type=========================================================start **/
    /**
     * 通知消息类型-站内信
     **/
    public static final int NOTICEMESSAGE_TYPE_MESSAGE = 1;
    /**
     * 通知消息类型-短信
     **/
    public static final int NOTICEMESSAGE_TYPE_SMS = 2;
    /**
     * 通知消息类型-邮件
     **/
    public static final int NOTICEMESSAGE_TYPE_EMAIL = 3;
    /**
     * 通知消息类型-推送
     **/
    public static final int NOTICEMESSAGE_TYPE_APPPUSH = 4;
    /** NoticeMessage-type=========================================================end **/

    /** handle-SMS-type=========================================================start **/
    /**
     * 短信发送渠道
     **/
    public static final String SMS_ROUTE = "SMSRoute";
    /**
     * 主动短信类型-注册
     **/
    public static final int HANDLE_SMS_TYPE_REG = 1;
    /**
     * 主动短信类型-登录密码
     **/
    public static final int HANDLE_SMS_TYPE_PWD = 2;
    /**
     * 主动短信类型-交易密码
     **/
    public static final int HANDLE_SMS_TYPE_PAYPWD = 3;
    /**
     * 主动短信类型-修改登录密码
     **/
    public static final int HANDLE_SMS_TYPE_UPDATE_PWD = 4;
    /**
     * 主动短信类型-修改注册手机号码
     **/
    public static final int HANDLE_SMS_TYPE_UPDATE_MOBILE = 5;
    /** handle-SMS-type=========================================================end **/

/** login-type=========================================================start **/
    /**
     * 密码登录
     **/
    public static final int LOGIN_BY_PWD = 1;
    /**
     * 手机验证码登陆
     **/
    public static final int LOGIN_BY_MOBILECODE = 2;
/** login-type=========================================================end **/

    /** validity-type=========================================================start **/
    /**
     * 有效期类型-天数
     **/
    public static final int VALIDITY_TYPE_DAY = 1;
    /**
     * 有效期类型-时间
     **/
    public static final int VALIDITY_TYPE_TIME = 2;
    /** validity-type=========================================================end **/

    /** amount-type=========================================================start **/
    /**
     * 红包金额类型-金额
     **/
    public static final int AMOUNT_TYPE_MONEY = 1;
    /**
     * 红包金额类型-比例
     **/
    public static final int AMOUNT_TYPE_PROPORTION = 2;
    /** amount-type=========================================================end **/

    /** user-type=========================================================start **/
    /**
     * 用户-类型-普通用户
     **/
    public static final int USER_TYPE_GENERAL = 1;
    /**
     * 用户-类型-借款人
     **/
    public static final int USER_TYPE_BORROWER = 2;
    /**
     * 用户-类型-担保人
     **/
    public static final int USER_TYPE_GUARANTEE = 3;
    /**
     * 用户-类型-商家
     **/
    public static final int USER_TYPE_BUSINESS = 4;
    /** user-type=========================================================end **/

    /** user-nature=========================================================start **/
    /**
     * 用户-类别-普通用户
     **/
    public static final int USER_NATURE_GENERAL = 1;
    /**
     * 用户-类型-企业用户
     **/
    public static final int USER_NATURE_ARTIF = 2;
    /** user-type=========================================================end **/

    /** user-grade-integral=========================================================start **/
    /**
     * 用户-积分-等级-初级
     **/
    public static final int USER_GRADE_INTEGRAL_PRIMARY = 1;
    /**
     * 用户-积分-等级-初级-上限
     **/
    public static final int USER_GRADE_INTEGRAL_PRIMARY_LIMIT = 1000;

    /**
     * 用户-积分-等级-中级
     **/
    public static final int USER_GRADE_INTEGRAL_MIDDLE_LEVEL = 2;
    /**
     * 用户-积分-等级-中级 -上限
     **/
    public static final int USER_GRADE_INTEGRAL_MIDDLE_LEVEL_LIMIT = 2000;

    /**
     * 用户-积分-等级-高级
     **/
    public static final int USER_GRADE_INTEGRAL_HIGH_RANKING = 3;
    /**
     * 用户-积分-等级-高级 -上限
     **/
    public static final int USER_GRADE_INTEGRAL_HIGH_RANKING_LIMIT = 5000;

    /**
     * 用户-积分-等级-尊享
     **/
    public static final int USER_GRADE_INTEGRAL_EXCLUSIVE = 4;
    /** user-grade-integral=========================================================end **/


    /** userRelation-type=========================================================start **/
    /**
     * 用户-类别-关注商家
     **/
    public static final int USERRELATION_TYPE_GENERAL = 1;
    /**
     * 用户-类型-借款人-商家
     **/
    public static final int USERRELATION_TYPE_ARTIF = 2;
    /**
     * 用户-类型-邀请
     **/
    public static final int USERRELATION_TYPE_INVITE = 3;
    /** userRelation-type=========================================================end **/

    /** payment-type=========================================================start **/
    /**
     * 收支类型-不变
     **/
    public static final int PAYMENTS_TYPE_NOCHANGE = -1;
    /**
     * 收支类型-收入
     **/
    public static final int PAYMENTS_TYPE_INCOME = 1;
    /**
     * 收支类型-支出
     **/
    public static final int PAYMENTS_TYPE_EXPENDITURES = 2;
    /** payment-Type=========================================================end **/

    /** spec-type=========================================================start **/
    /**
     * 显示规格
     **/
    public static final int SPEC_TYPE_SHOW = -1;
    /**
     * 选择规格
     **/
    public static final int SPEC_TYPE_CHOOSE = 1;
    /** spec-type=========================================================end **/


    /**
     * order-nid=========================================================start
     **/
    public static enum OrderNid {
        ORDER_NID_USER_LOGIN("user_login", "用户登录"),
        ORDER_NID_USER_REALNAME("user_realname", "实名"),
        ORDER_NID_USER_INFO_UPDATE("user_info_update", "修改用户信息"),
        ORDER_NID_USER_MODIFY_MOBILE("modify_mobile", "修改手机号"),
        ORDER_NID_USER_BINDBC("user_bindbc", "绑卡"),
        ORDER_NID_USER_UNBINDBC("user_unBindbc", "解绑卡"),
        ORDER_NID_ACCOUNT_RECHARGE("account_recharge", "充值"),
        ORDER_NID_ACCOUNT_WITHDRAW_CASH("account_withdraw_cash", "提现"),
        ORDER_NID_ACCOUNT_WITHDRAW_CASHFAIL("account_withdraw_cashFail", "提现失败"),
        ORDER_NID_ACCOUNT_WITHDRAW_CASHSUCCESS("account_withdraw_cashSuccess", "提现成功"),
        ORDER_NID_ACCOUNT_DEDUCT("account_deduct", "线下资金变更"),
        ORDER_NID_ACCOUNT_DEDUCT_Add("account_deduct_add", "线下资金变更"),
        ORDER_NID_ACCOUNT_DEDUCT_SUBTRACT("account_deduct_add_subtract", "线下资金变更"),
        ORDER_NID_MESSAGE_SEND("message_send", "发送消息"),
        ORDER_NID_USER_PROMOTION("user_promotion", "推广"),
        ORDER_NID_USER_PROMOTION_GIVE_OUT_BONUS_COUPONS("user_promotion_give_out_bonus_coupons", "推广-发放红包"),
        ORDER_NID_USER_PROMOTION_GIVE_OUT_USER_VIP_COUPONS("user_promotion_give_out_user_vip_coupon", "推广-发放vip红包"),
        ORDER_NID_PROJECT_AUDIT("project_audit", "项目审核"),
        ORDER_NID_PROJECT_TENDER("project_tender", "项目投资"),
        ORDER_NID_PROJECT_TENDER_FAIL("project_tender_fail", "项目投资失败"),
        ORDER_NID_PROJECT_LOAN("project_loan", "项目放款"),
        ORDER_NID_PROJECT_REPAYMENT("project_repayment", "还款"),
        ORDER_NID_TENDER_CREATE_PROTOCOL("tender_create_protocol", "创建投资协议"),
        ORDER_NID_ADMIN_HANDLE_CREDIT_SCORE("admin_handle_credit_score", "后台手动更新信用分"),
        ORDER_NID_ORDER_INFO_PAY("order_info_pay", "订单支付"),
        ORDER_NID_ORDER_INFO_REFUND_PAY("order_info_refund_pay", "订单退款支付"),
        ORDER_NID_ORDER_INFO_PAY_HISTORY("order_info_pay_history", "订单支付记录"),
        ORDER_NID_ORDER_INFO_RECEIVE("order_info_receive", "订单收货完成"),;
        /**
         * 标识
         **/
        private String nid;
        /**
         * 名称
         **/
        private String name;

        private OrderNid(String nid, String name) {
            this.nid = nid;
            this.name = name;
        }

        /**
         * 获取【标识】
         **/
        public String getNid() {
            return nid;
        }

        /**
         * 设置【标识】
         **/
        public void setNid(String nid) {
            this.nid = nid;
        }

        /**
         * 获取【名称】
         **/
        public String getName() {
            return name;
        }

        /**
         * 设置【名称】
         **/
        public void setName(String name) {
            this.name = name;
        }
    }
    /** order-nid=========================================================start **/

    /**
     * QueueCode=========================================================start
     **/
    public static enum QueueCode {
        QUEUE_CODE_OTHER("queue_code_other", "com.zc.mall.queue.consumer.work.QueueConsumerOther"),
        QUEUE_CODE_MESSAGE("queue_code_message", "com.zc.mall.queue.consumer.work.QueueConsumerMessage");
        /**
         * 标识
         **/
        private String code;
        /**
         * 地址名称
         **/
        private String name;

        private QueueCode(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public static String getName(String code) {
            for (QueueCode q : QueueCode.values()) {
                if (code.equals(q.getCode())) {
                    return q.getName();
                }
            }
            throw new BusinessException("枚举不存在");
        }

        /**
         * 获取【标识】
         **/
        public String getCode() {
            return code;
        }

        /**
         * 设置【标识】
         **/
        public void setCode(String code) {
            this.code = code;
        }

        /**
         * 获取【地址名称】
         **/
        public String getName() {
            return name;
        }

        /**
         * 设置【地址名称】
         **/
        public void setName(String name) {
            this.name = name;
        }
    }
    /** QueueCode=========================================================end **/

    /** FuiouPayMethod=========================================================start **/
//	public static enum FuiouPayMethod{
//		WEBREG("com.zc.sys.fuioupay.util.FuiouPayUtil","webReg","注册-网页版"),
//		WEBARTIFREG("com.zc.sys.fuioupay.util.FuiouPayUtil","webArtifReg","注册-企业法人-网页版"),
//		WEBQUICKRECHARGE("com.zc.sys.fuioupay.util.FuiouPayUtil","webQuickRecharge","快速充值-网页版"),
//		WEBWITHDRAWCASH("com.zc.sys.fuioupay.util.FuiouPayUtil","webQuickWithdrawCash","快速提现-网页版"),
//		WEBLOGIN("com.zc.sys.fuioupay.util.FuiouPayUtil","webLogin","登录-网页版"),
//		WEBMODIFYMOBILE("com.zc.sys.fuioupay.util.FuiouPayUtil","webModifyMobile","修改手机号-网页版"),
//		WEBCHANGECARD("com.zc.sys.fuioupay.util.FuiouPayUtil","webChangeCard","绑卡-换绑卡")
//		;
//		/** 类名 **/
//		private String className;
//		/** 方法名 **/
//		private String methodName;
//		/** 名称 **/
//		private String name;
//		private FuiouPayMethod(String className, String methodName, String name) {
//			this.className = className;
//			this.methodName = methodName;
//			this.name = name;
//		}
//		/** 获取【类名】 **/
//		public String getClassName() {
//			return className;
//		}
//		/** 设置【类名】 **/
//		public void setClassName(String className) {
//			this.className = className;
//		}
//		/** 获取【方法名】 **/
//		public String getMethodName() {
//			return methodName;
//		}
//		/** 设置【方法名】 **/
//		public void setMethodName(String methodName) {
//			this.methodName = methodName;
//		}
//		/** 获取【名称】 **/
//		public String getName() {
//			return name;
//		}
//		/** 设置【名称】 **/
//		public void setName(String name) {
//			this.name = name;
//		}
//	}
    /** FuiouPayMethod=========================================================end **/

    /**
     * promotion_model
     **/
    public static final String PROMOTION_MODEL = "com.zc.mall.promotion.model.PromotionModel";
    public static final String ORDERPAYHISTORY_MODEL = "com.zc.mall.mall.model.OrderPayHistoryModel";
}
