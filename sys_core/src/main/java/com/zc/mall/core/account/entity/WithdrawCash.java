package com.zc.mall.core.account.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.user.entity.User;
import com.zc.sys.common.entity.LongPKEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 提现
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = BaseConstant.DB_PREFIX + BaseConstant.MODEL_Acc + "_withdraw_cash")
public class WithdrawCash extends LongPKEntity {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;
    /**
     * 用户
     */
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    /**
     * 操作金额
     **/
    private double amount;
    /**
     * 实际到账金额
     **/
    private double arrivalAmount;
    /**
     * 手续费
     **/
    private double fee;
    /**
     * 银行卡号
     **/
    private String bankCardNo;
    /**
     * 订单号
     **/
    private String orderNo;
//	/** 分步订单号 **/
//	private String orderNoExtra;
    /**
     * 所属银行编码
     **/
    private String bankCode;
    /**
     * 预留手机号
     **/
    private String mobile;
    /**
     * 姓名
     **/
    private String realName;
    /**
     * 结果描述
     **/
    private String resultMsg;
    /**
     * 渠道
     **/
    private int route;
    /**
     * 状态
     **/
    private int state;
    /**
     * 备注
     **/
    private String remark;
    /**
     * 操作管理员
     **/
    private String operateUser;
    /**
     * 添加时间
     **/
    private Date addTime;
    /**
     * 添加ip
     **/
    private String addIp;

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
     * 获取【操作金额】
     **/
    public double getAmount() {
        return amount;
    }

    /**
     * 设置【操作金额】
     **/
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * 获取【实际到账金额 】
     **/
    public double getArrivalAmount() {
        return arrivalAmount;
    }

    /**
     * 设置【实际到账金额 】
     **/
    public void setArrivalAmount(double arrivalAmount) {
        this.arrivalAmount = arrivalAmount;
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

    /**
     * 获取【银行卡号】
     **/
    public String getBankCardNo() {
        return bankCardNo;
    }

    /**
     * 设置【银行卡号】
     **/
    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    /**
     * 获取【所属银行编码】
     **/
    public String getBankCode() {
        return bankCode;
    }

    /**
     * 设置【所属银行编码】
     **/
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    /**
     * 获取【预留手机号】
     **/
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置【预留手机号】
     **/
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取【姓名】
     **/
    public String getRealName() {
        return realName;
    }

    /**
     * 设置【姓名】
     **/
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 获取【结果描述】
     **/
    public String getResultMsg() {
        return resultMsg;
    }

    /**
     * 设置【结果描述】
     **/
    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    /**
     * 获取【手续费 】
     **/
    public double getFee() {
        return fee;
    }

    /**
     * 设置【手续费 】
     **/
    public void setFee(double fee) {
        this.fee = fee;
    }

    /**
     * 获取【渠道】
     **/
    public int getRoute() {
        return route;
    }

    /**
     * 设置【渠道】
     **/
    public void setRoute(int route) {
        this.route = route;
    }

    /**
     * 获取【备注】
     **/
    public String getRemark() {
        return remark;
    }

    /**
     * 设置【备注】
     **/
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取【操作管理员】
     **/
    public String getOperateUser() {
        return operateUser;
    }

    /**
     * 设置【操作管理员】
     **/
    public void setOperateUser(String operateUser) {
        this.operateUser = operateUser;
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
     * 获取【添加ip】
     **/
    public String getAddIp() {
        return addIp;
    }

    /**
     * 设置【添加ip】
     **/
    public void setAddIp(String addIp) {
        this.addIp = addIp;
    }

    /**
     * 获取【订单号】
     **/
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置【订单号】
     **/
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

//	/** 获取【分步订单号】 **/
//	public String getOrderNoExtra() {
//		return orderNoExtra;
//	}
//	
//	/** 设置【分步订单号】 **/
//	public void setOrderNoExtra(String orderNoExtra) {
//		this.orderNoExtra = orderNoExtra;
//	}
//	

}