package com.zc.mall.mall.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.sys.common.entity.LongPKEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

/**
 * 订单商品
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = BaseConstant.DB_PREFIX + BaseConstant.MODEL_G + "_order_goods")
public class OrderGoods extends LongPKEntity {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     **/
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderInfo orderInfo;
    /**
     * sku
     **/
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sku_id")
    private GoodsSku goodsSku;

    /**
     * 活动id
     **/
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_promotion_record_id")
    private GoodsPromotionRecord goodsPromotionRecord;
    /**
     * 商品名称
     **/
    private String goodsName;
    /**
     * 商品数量
     **/
    private int number;
    /**
     * 商品数量
     **/
    private int giftNumber;
    /**
     * 价格
     **/
    private double prince;
    /**
     * 折扣
     **/
    private double discount;
    /**
     * vip折扣
     **/
    private double vipRate;
    /**
     * 备注
     **/
    private String remark;
    @JsonBackReference
    @OneToMany(mappedBy = "orderGoods")
    private List<OrderGoodsComment> orderGoodsCommentList;

    public List<OrderGoodsComment> getOrderGoodsCommentList() {
        return orderGoodsCommentList;
    }

    public void setOrderGoodsCommentList(List<OrderGoodsComment> orderGoodsCommentList) {
        this.orderGoodsCommentList = orderGoodsCommentList;
    }

    /**
     * 获取【订单id】
     **/
    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    /**
     * 设置【订单id】
     **/
    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    /**
     * 获取【sku】
     **/
    public GoodsSku getGoodsSku() {
        return goodsSku;
    }

    /**
     * 设置【sku】
     **/
    public void setGoodsSku(GoodsSku goodsSku) {
        this.goodsSku = goodsSku;
    }

    /**
     * 设置【商品名称】
     **/
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * 获取【商品名称】
     **/
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 设置【商品数量】
     **/
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * 获取【商品数量】
     **/
    public int getNumber() {
        return number;
    }

    /**
     * 设置【价格】
     **/
    public void setPrince(double prince) {
        this.prince = prince;
    }

    /**
     * 获取【价格】
     **/
    public double getPrince() {
        return prince;
    }

    /**
     * 设置【备注】
     **/
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取【备注】
     **/
    public String getRemark() {
        return remark;
    }

    /**
     * 获取【折扣】
     **/
    public double getDiscount() {
        return discount;
    }

    /**
     * 设置【折扣】
     **/
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public GoodsPromotionRecord getGoodsPromotionRecord() {
        return goodsPromotionRecord;
    }

    public void setGoodsPromotionRecord(GoodsPromotionRecord goodsPromotionRecord) {
        this.goodsPromotionRecord = goodsPromotionRecord;
    }

    public int getGiftNumber() {
        return giftNumber;
    }

    public void setGiftNumber(int giftNumber) {
        this.giftNumber = giftNumber;
    }

    public double getVipRate() {
        return vipRate;
    }

    public void setVipRate(double vipRate) {
        this.vipRate = vipRate;
    }
}