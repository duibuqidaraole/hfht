package com.zc.mall.promotion.constant;

/**
 * 常量
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月6日
 */
public final class BasePromotionConstant {

    /** DB=========================================================start **/
    /**
     * 表模块-pt-活动推广
     **/
    public static final String MODEL_Pt = "pt";
    /** DB=========================================================end **/

    /** Promotion-Way==============================================start **/
    /**
     * 推广活动方式
     **/
    public enum PromotionWay {
        // 注册活动
        PROMOTION_WAY_REG("promotionWayReg", 1),
        // 登录活动
        PROMOTION_WAY_LOGIN("promotionWayLogin", 2),
        // 抽奖活动
        PROMOTION_WAY_DRAW("promotionWayDraw", 3),
        // 最后满标放款活动
        PROMOTION_WAY_FULL_LAST_LOAN("promotionWayFullLastLoan", 4);
        private String name;
        private int index;

        private PromotionWay(String name, int index) {
            this.name = name;
            this.index = index;
        }

        public int getIndex() {
            return this.index;
        }

        public String getName() {
            return this.name;
        }
    }
    /** Promotion-Way===============================================end **/

    /** PromotionPrize-type==============================================start **/
    /**
     * 推广活动奖励类型-免息券
     **/
    public static final int PROMOTIONPRIZE_TYPE_INTEREST_FREE_NOTE = 1;
    /**
     * 推广活动奖励类型-红包
     **/
    public static final int PROMOTIONPRIZE_TYPE_BONUS_COUPONS = 2;
    /**
     * 推广活动奖励类型-加息券
     **/
    public static final int PROMOTIONPRIZE_TYPE_INTEREST_RATE_COUPONS = 3;
    /**
     * 推广活动奖励类型-体验券
     **/
    public static final int PROMOTIONPRIZE_TYPE_EXPERIENCE_COUPONS = 4;
    /**
     * 推广活动奖励类型-升级超级vip
     **/
    public static final int PROMOTIONPRIZE_TYPE_VIP = 5;
    /**
     * 推广活动奖励类型-vip红包
     **/
    public static final int PROMOTIONPRIZE_TYPE_VIP_BONUS_COUPONS = 6;


    /** PromotionPrize-type===============================================end **/

    /** Coupons-useType===============================================start **/
    /**
     * 券使用类型-投资
     **/
    public static final int COUPONS_USETYPE_TENDER = 1;
    /**
     * 券使用类型-兑换
     **/
    public static final int COUPONS_USETYPE_CONVERT = 2;
    /**
     * 券使用类型-抵扣
     **/
    public static final int COUPONS_USETYPE_DEDUCITION = 3;
    /** Coupons-useType===============================================end **/

    /** BonusCoupons-type===============================================start **/
    /**
     * 红包类型-抵扣红包
     **/
    public static final int BONUS_COUPONS_TYPE_DEDUCT = 1;
    /**
     * 红包类型-现金红包
     **/
    public static final int BONUS_COUPONS_TYPE_CASH = 2;
    /** BonusCoupons-type===============================================end **/

/** BonusCoupons-account-log-type===============================================start **/
    /**
     * 红包类型-抵扣红包
     **/
    public static final String BONUS_COUPONS_ACCOUNT_LOG_TYPE_DEDUCT = "user_promotion_give_out_bonus_coupons_deduct";
    /**
     * 红包类型-现金红包
     **/
    public static final String BONUS_COUPONS_ACCOUNT_LOG_TYPE_CASH = "user_promotion_give_out_bonus_coupons_cash";
    /** BonusCoupons-account-log-type===============================================end **/


    /** InterestRateCoupons-type===============================================start **/
    /**
     * 加息券类型-投资加息
     **/
    public static final int INTEREST_RATE_COUPONS_TYPE_TENDER = 1;
    /** InterestRateCoupons-type===============================================end **/
}
