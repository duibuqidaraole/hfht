package com.zc.mall.core.account.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.manage.entity.Operator;
import com.zc.mall.core.user.entity.User;
import com.zc.sys.common.entity.LongPKEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 线下扣款
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = BaseConstant.DB_PREFIX + BaseConstant.MODEL_Acc + "_account_deduct")
public class AccountDeduct extends LongPKEntity {
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
     * 订单号
     **/
    private String orderNo;
    /**
     * 状态
     **/
    private int state;
    /**
     * 类型
     **/
    private int type;
    /**
     * 备注
     **/
    private String remark;
    /**
     * 添加管理员
     **/
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "add_operator_id")
    private Operator operator;
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
     * 获取【类型】
     **/
    public int getType() {
        return type;
    }

    /**
     * 设置【类型】
     **/
    public void setType(int type) {
        this.type = type;
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
     * 获取【添加管理员】
     **/
    public Operator getOperator() {
        return operator;
    }

    /**
     * 设置【添加管理员】
     **/
    public void setOperator(Operator operator) {
        this.operator = operator;
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


}