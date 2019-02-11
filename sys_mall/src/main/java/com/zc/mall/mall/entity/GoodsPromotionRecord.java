package com.zc.mall.mall.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.manage.entity.Operator;
import com.zc.mall.promotion.entity.Promotion;
import com.zc.sys.common.entity.LongPKEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 商品活动记录
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年12月24日
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = BaseConstant.DB_PREFIX + BaseConstant.MODEL_G + "_goods_promotion_record")
public class GoodsPromotionRecord extends LongPKEntity {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;

    /**
     * 商品活动id
     */
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_id")
    private GoodsPromotion goodsPromotion;
    /**
     * skuId
     */
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_sku_id")
    private GoodsSku goodsSku;
    /**
     * 活动开始时间
     **/
    private Date beginTime;
    /**
     * 活动结束时间
     **/
    private Date endTime;
    /**
     * 修改管理员id
     */
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "update_operator_id")
    private Operator updateOperator;

    /**
     * 添加管理员id
     */
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "add_operator_id")
    private Operator addOperator;
    /**
     * 修改时间
     **/
    private Date updateTime;
    /**
     * 添加时间
     **/
    private Date addTime;

    public GoodsSku getGoodsSku() {
        return goodsSku;
    }

    public void setGoodsSku(GoodsSku goodsSku) {
        this.goodsSku = goodsSku;
    }

    /**
     * 设置【活动开始时间】
     **/
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * 获取【活动开始时间】
     **/
    public Date getBeginTime() {
        return beginTime;
    }

    /**
     * 设置【活动结束时间】
     **/
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取【活动结束时间】
     **/
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置【修改时间】
     **/
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取【修改时间】
     **/
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置【添加时间】
     **/
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取【添加时间】
     **/
    public Date getAddTime() {
        return addTime;
    }

    public GoodsPromotion getGoodsPromotion() {
        return goodsPromotion;
    }

    public void setGoodsPromotion(GoodsPromotion goodsPromotion) {
        this.goodsPromotion = goodsPromotion;
    }

    public Operator getUpdateOperator() {
        return updateOperator;
    }

    public void setUpdateOperator(Operator updateOperator) {
        this.updateOperator = updateOperator;
    }

    public Operator getAddOperator() {
        return addOperator;
    }

    public void setAddOperator(Operator addOperator) {
        this.addOperator = addOperator;
    }
}