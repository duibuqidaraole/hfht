package com.zc.mall.core.credit.entity;

import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.manage.entity.Operator;
import com.zc.mall.core.user.entity.User;
import com.zc.sys.common.entity.LongPKEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 信用分账户
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = BaseConstant.DB_PREFIX + BaseConstant.MODEL_Cs + "_credit_score_log")
public class CreditScoreLog extends LongPKEntity {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;

    /**
     * 用户
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;
    /**
     * 操作信用分
     **/
    private double score;
    /**
     * 类型
     **/
    private String type;
    /**
     * 名称
     **/
    private String name;
    /**
     * 信用分
     **/
    private double balanceScore;
    /**
     * 内容
     **/
    private String content;
    /**
     * 收支方式
     **/
    private int paymentType;
    /**
     * 关联订单
     **/
    private String orderNo;
    /**
     * 操作管理员
     **/
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "operator_id")
    private Operator operator;
    /**
     * 备注
     **/
    private String remark;
    /**
     * 添加时间
     **/
    private Date addTime;

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
     * 获取【操作信用分】
     **/
    public double getScore() {
        return score;
    }

    /**
     * 设置【操作信用分】
     **/
    public void setScore(double score) {
        this.score = score;
    }

    /**
     * 获取【类型】
     **/
    public String getType() {
        return type;
    }

    /**
     * 设置【类型】
     **/
    public void setType(String type) {
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
     * 获取【信用分】
     **/
    public double getBalanceScore() {
        return balanceScore;
    }

    /**
     * 设置【信用分】
     **/
    public void setBalanceScore(double balanceScore) {
        this.balanceScore = balanceScore;
    }

    /**
     * 获取【内容】
     **/
    public String getContent() {
        return content;
    }

    /**
     * 设置【内容】
     **/
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取【收支方式】
     **/
    public int getPaymentType() {
        return paymentType;
    }

    /**
     * 设置【收支方式】
     **/
    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * 获取【关联订单】
     **/
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置【关联订单】
     **/
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取【操作管理员】
     **/
    public Operator getOperator() {
        return operator;
    }

    /**
     * 设置【操作管理员】
     **/
    public void setOperator(Operator operator) {
        this.operator = operator;
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
}