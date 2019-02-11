package com.zc.mall.mall.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.sys.common.entity.LongPKEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 支付记录
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = BaseConstant.DB_PREFIX + BaseConstant.MODEL_G + "_order_pay_history")
public class OrderPayHistory extends LongPKEntity {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;

    /**
     * 支付订单号
     **/
    private String paymentTradeNo;
    /**
     * 状态
     **/
    private int state;
    /**
     * 订单
     **/
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderInfo orderInfo;
    /**
     * 商品总额
     **/
    private double amount;
    /**
     * 实际支付金额
     **/
    private double amountReal;
    /**
     * 请求参数
     **/
    private String requestJson;
    /**
     * 响应参数
     **/
    private String responseJson;
    /**
     * 备注
     **/
    private String remark;
    /**
     * 添加时间
     **/
    private Date addTime;

    /**
     * 设置【支付订单号】
     **/
    public void setPaymentTradeNo(String paymentTradeNo) {
        this.paymentTradeNo = paymentTradeNo;
    }

    /**
     * 获取【支付订单号】
     **/
    public String getPaymentTradeNo() {
        return paymentTradeNo;
    }

    /**
     * 获取【订单】
     **/
    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    /**
     * 设置【订单】
     **/
    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    /**
     * 设置【商品总额】
     **/
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * 获取【商品总额】
     **/
    public double getAmount() {
        return amount;
    }

    /**
     * 设置【实际支付金额】
     **/
    public void setAmountReal(double amountReal) {
        this.amountReal = amountReal;
    }

    /**
     * 获取【实际支付金额】
     **/
    public double getAmountReal() {
        return amountReal;
    }

    /**
     * 设置【请求参数】
     **/
    public void setRequestJson(String requestJson) {
        this.requestJson = requestJson;
    }

    /**
     * 获取【请求参数】
     **/
    public String getRequestJson() {
        return requestJson;
    }

    /**
     * 设置【响应参数】
     **/
    public void setResponseJson(String responseJson) {
        this.responseJson = responseJson;
    }

    /**
     * 获取【响应参数】
     **/
    public String getResponseJson() {
        return responseJson;
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
     * 获取【添加时间】
     **/
    public Date getAddTime() {
        return addTime;
    }

    /**
     * 设置【添加时间】
     **/
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取【状态】
     **/
    public int getState() {
        return state;
    }

    /**
     * 设置【状态】
     **/
    public void setState(int state) {
        this.state = state;
    }
}