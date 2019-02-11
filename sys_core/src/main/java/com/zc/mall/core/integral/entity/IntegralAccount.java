package com.zc.mall.core.integral.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.user.entity.User;
import com.zc.sys.common.entity.LongPKEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * 积分账户
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = BaseConstant.DB_PREFIX + BaseConstant.MODEL_Jf + "_integral_account")
public class IntegralAccount extends LongPKEntity {
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
     * 积分总额
     **/
    private double totalIntegral;
    /**
     * 可用积分
     **/
    private double balanceIntegral;
    /**
     * 消费积分
     **/
    private double expenseIntegral;
    /**
     * 冻结积分
     **/
    private double freezeIntegral;
    /**
     * 积分等级
     **/
    private int gradeIntegral;
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
     * 获取【积分总额】
     **/
    public double getTotalIntegral() {
        return totalIntegral;
    }

    /**
     * 设置【积分总额】
     **/
    public void setTotalIntegral(double totalIntegral) {
        this.totalIntegral = totalIntegral;
    }

    /**
     * 获取【可用积分】
     **/
    public double getBalanceIntegral() {
        return balanceIntegral;
    }

    /**
     * 设置【可用积分】
     **/
    public void setBalanceIntegral(double balanceIntegral) {
        this.balanceIntegral = balanceIntegral;
    }

    /**
     * 获取【消费积分】
     **/
    public double getExpenseIntegral() {
        return expenseIntegral;
    }

    /**
     * 设置【消费积分】
     **/
    public void setExpenseIntegral(double expenseIntegral) {
        this.expenseIntegral = expenseIntegral;
    }

    /**
     * 获取【冻结积分】
     **/
    public double getFreezeIntegral() {
        return freezeIntegral;
    }

    /**
     * 设置【冻结积分】
     **/
    public void setFreezeIntegral(double freezeIntegral) {
        this.freezeIntegral = freezeIntegral;
    }

    /**
     * 获取【积分等级】
     **/
    public int getGradeIntegral() {
        return gradeIntegral;
    }

    /**
     * 设置【积分等级】
     **/
    public void setGradeIntegral(int gradeIntegral) {
        this.gradeIntegral = gradeIntegral;
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