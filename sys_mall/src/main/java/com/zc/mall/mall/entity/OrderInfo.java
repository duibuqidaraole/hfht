package com.zc.mall.mall.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.manage.entity.Operator;
import com.zc.mall.core.user.entity.User;
import com.zc.sys.common.entity.LongPKEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 订单信息表
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = BaseConstant.DB_PREFIX + BaseConstant.MODEL_G + "_order_info")
public class OrderInfo extends LongPKEntity {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;

    /**
     * 订单编号
     **/
    private String no;
    /**
     * 用户
     */
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    /**
     * 商品计数
     **/
    private int itemCount;
    /**
     * 支付方式
     **/
    private int payment;

    /**
     * 支付订单号
     **/
    private String paymentTradeNo;
    /**
     * 商品总额
     **/
    private double amount;
    /**
     * 运费
     **/
    private double freight;

    /**
     * 商品折扣
     **/
    private double discount;
    /**
     * 活动券抵扣金额
     **/
    private double promotionCouponsAmount;
    /**
     * 余额支付
     **/
    private double balancePay;
    /**
     * 实际付款
     **/
    private double amountReal;
    /**
     * 状态
     **/
    private int state;
    /**
     * 是否评论完1评论完成-1未评论完成
     **/
    private int isComment;
    /**
     * 前一状态
     **/
    private int beforeState;
    /**
     * 物流
     **/
    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_logistics_id")
    private OrderLogistics orderLogistics;
    /**
     * 修改管理员
     **/
    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "update_operator_id")
    private Operator updateOperator;
    /**
     * 添加时间
     **/
    private Date addTime;
    /**
     * 更新时间
     **/
    private Date updateTime;
    /**
     * 备注
     **/
    private String remark;


    @JsonBackReference
    @OneToMany(mappedBy = "orderInfo")
    private List<OrderGoods> orderGoodsList;

    public List<OrderGoods> getOrderGoodsList() {
        return orderGoodsList;
    }

    public void setOrderGoodsList(List<OrderGoods> orderGoodsList) {
        this.orderGoodsList = orderGoodsList;
    }

    /**
     * 设置【订单编号】
     **/
    public void setNo(String no) {
        this.no = no;
    }

    /**
     * 获取【订单编号】
     **/
    public String getNo() {
        return no;
    }

    /**
     * 设置【商品计数】
     **/
    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    /**
     * 获取【商品计数】
     **/
    public int getItemCount() {
        return itemCount;
    }

    /**
     * 设置【支付方式】
     **/
    public void setPayment(int payment) {
        this.payment = payment;
    }

    /**
     * 获取【支付方式】
     **/
    public int getPayment() {
        return payment;
    }

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
     * 设置【运费】
     **/
    public void setFreight(double freight) {
        this.freight = freight;
    }

    /**
     * 获取【运费】
     **/
    public double getFreight() {
        return freight;
    }

    /**
     * 设置【实际付款】
     **/
    public void setAmountReal(double amountReal) {
        this.amountReal = amountReal;
    }

    /**
     * 获取【实际付款】
     **/
    public double getAmountReal() {
        return amountReal;
    }

    /**
     * 设置【状态】
     **/
    public void setState(int state) {
        this.state = state;
    }

    /**
     * 获取【状态】
     **/
    public int getState() {
        return state;
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
     * 获取【用户】
     **/
    public User getUser() {
        return user;
    }

    /**
     * 设置【用户】
     **/
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * 获取【物流】
     **/
    public OrderLogistics getOrderLogistics() {
        return orderLogistics;
    }

    /**
     * 设置【物流】
     **/
    public void setOrderLogistics(OrderLogistics orderLogistics) {
        this.orderLogistics = orderLogistics;
    }

    /**
     * 获取【修改管理员】
     **/
    public Operator getUpdateOperator() {
        return updateOperator;
    }

    /**
     * 设置【修改管理员】
     **/
    public void setUpdateOperator(Operator updateOperator) {
        this.updateOperator = updateOperator;
    }

    /**
     * 获取【更新时间】
     **/
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置【更新时间】
     **/
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取【活动券抵扣金额】
     **/
    public double getPromotionCouponsAmount() {
        return promotionCouponsAmount;
    }

    /**
     * 设置【活动券抵扣金额】
     **/
    public void setPromotionCouponsAmount(double promotionCouponsAmount) {
        this.promotionCouponsAmount = promotionCouponsAmount;
    }

    /**
     * 获取【余额支付金额】
     **/
    public double getBalancePay() {
        return balancePay;
    }

    /**
     * 设置【余额支付金额】
     **/
    public void setBalancePay(double balancePay) {
        this.balancePay = balancePay;
    }

    /**
     * 获取【前一状态】
     **/
    public int getBeforeState() {
        return beforeState;
    }

    /**
     * 设置【前一状态】
     **/
    public void setBeforeState(int beforeState) {
        this.beforeState = beforeState;
    }

    public int getIsComment() {
        return isComment;
    }

    public void setIsComment(int isComment) {
        this.isComment = isComment;
    }

    /**
     * 获取【商品折扣】
     **/
    public double getDiscount() {
        return discount;
    }

    /**
     * 设置【商品折扣】
     **/
    public void setDiscount(double discount) {
        this.discount = discount;
    }

}