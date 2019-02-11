package com.zc.mall.core.account.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.user.entity.User;
import com.zc.sys.common.entity.LongPKEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * 资金账户
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = BaseConstant.DB_PREFIX + BaseConstant.MODEL_Acc + "_account")
public class Account extends LongPKEntity {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;
    /**
     * 用户
     */
    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    /**
     * 账户总额
     **/
    private double total;
    /**
     * 可用余额
     **/
    private double balance;
    /**
     * 冻结金额
     **/
    private double freezeAmount;
    /**
     * 版本控制
     **/
    @Version
    private int version;


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
     * 获取【账户总额】
     **/
    public double getTotal() {
        return total;
    }

    /**
     * 设置【账户总额】
     **/
    public void setTotal(double total) {
        this.total = total;
    }

    public double getBalance() {
        return balance;
    }

    /**
     * 设置【可用余额】
     **/
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * 获取【冻结金额】
     **/
    public double getFreezeAmount() {
        return freezeAmount;
    }

    /**
     * 设置【冻结金额】
     **/
    public void setFreezeAmount(double freezeAmount) {
        this.freezeAmount = freezeAmount;
    }

    /**
     * 获取【版本控制】
     **/
    public int getVersion() {
        return version;
    }

    /**
     * 设置【版本控制】
     **/
    public void setVersion(int version) {
        this.version = version;
    }

}