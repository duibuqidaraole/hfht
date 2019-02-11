package com.zc.mall.mall.constant;

/**
 * 活动类型
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月19日
 */
public enum GoodsPromotionTypeEnum {
    /** ------------------------活动类型--------------------------START  **/
    /**
     * 买赠
     **/
    GOODS_PROMOTION_TYPE_BUY_SEND(1),

    /** ------------------------活动类型--------------------------END  **/
    ;

    /**
     * 活动类型
     **/
    int goodsPromotionType;

    private GoodsPromotionTypeEnum(int goodsPromotionType) {
        this.goodsPromotionType = goodsPromotionType;
    }

    /**
     * 获取【活动类型】
     **/
    public int getGoodsPromotionType() {
        return goodsPromotionType;
    }

    /**
     * 设置【活动类型】
     **/
    public void setGoodsPromotionType(int goodsPromotionType) {
        this.goodsPromotionType = goodsPromotionType;
    }

}
