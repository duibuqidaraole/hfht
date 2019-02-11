package com.zc.mall.mall.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.manage.entity.Operator;
import com.zc.sys.common.entity.LongPKEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 商品sku-规格值
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = BaseConstant.DB_PREFIX + BaseConstant.MODEL_G + "_goods_sku_spec_value")
public class GoodsSkuSpecValue extends LongPKEntity {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;

    /**
     * sku
     **/
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sku_id")
    private GoodsSku goodsSku;

    /**
     * 规格值
     **/
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_spec_value_id")
    private GoodsSpecValue goodsSpecValue;

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

    public GoodsSku getGoodsSku() {
        return goodsSku;
    }

    public void setGoodsSku(GoodsSku goodsSku) {
        this.goodsSku = goodsSku;
    }

    public GoodsSpecValue getGoodsSpecValue() {
        return goodsSpecValue;
    }

    public void setGoodsSpecValue(GoodsSpecValue goodsSpecValue) {
        this.goodsSpecValue = goodsSpecValue;
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