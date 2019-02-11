package com.zc.mall.core.account.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zc.mall.core.account.constant.AccountConstant.AccountStatisticsType;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.user.entity.User;
import com.zc.sys.common.entity.LongPKEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * 账户统计
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年12月20日
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = BaseConstant.DB_PREFIX + BaseConstant.MODEL_Acc + "_account_statistics")
public class AccountStatistics extends LongPKEntity {
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
     * 类型
     **/
    private int type;
    /**
     * 名称
     **/
    private String name;
    /**
     * 统计金额
     **/
    private double amount;

    public AccountStatistics() {

    }

    public AccountStatistics(User user, int type, double amount) {
        super();
        this.user = user;
        this.type = type;
        this.name = AccountStatisticsType.getName(type);
        this.amount = amount;
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
     * 获取【名称】
     **/
    public String getName() {
        return name;
    }

    /**
     * 设置【名称】
     **/
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取【统计金额】
     **/
    public double getAmount() {
        return amount;
    }

    /**
     * 设置【统计金额】
     **/
    public void setAmount(double amount) {
        this.amount = amount;
    }
}