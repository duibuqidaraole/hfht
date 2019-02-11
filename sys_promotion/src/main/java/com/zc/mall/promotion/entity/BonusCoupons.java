package com.zc.mall.promotion.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.manage.entity.Operator;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.promotion.constant.BasePromotionConstant;
import com.zc.sys.common.entity.LongPKEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 红包
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年12月20日
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = BaseConstant.DB_PREFIX + BasePromotionConstant.MODEL_Pt + "_bonus_coupons")
public class BonusCoupons extends LongPKEntity {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;
    /**
     * 券编号
     **/
    private String couponsNo;
    /**
     * 券名称
     **/
    private String name;
    /**
     * 券面金额
     **/
    private double amount;
    /**
     * 返利方式 1:金额 2:比例
     **/
    private int amountType;
    /**
     * 类型
     **/
    private int type;
    /**
     * 添加管理员
     **/
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "add_operator_id")
    private Operator operator;
    /**
     * 关联商家用户
     **/
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "be_user_id")
    private User beUser;
    /**
     * 状态
     **/
    private int state;
    /**
     * 有效期类型 天数 截止时间
     **/
    private int validityType;
    /**
     * 有效期值
     **/
    private String validityValue;
    /**
     * 发放开始时间
     **/
    private Date startTime;
    /**
     * 发放结束时间
     **/
    private Date endTime;
    /**
     * 配额
     **/
    private int quota;
    /**
     * 已发放数量
     **/
    private int useQuota;
    /**
     * 发放限制单笔投资额度
     **/
    private double grantSingleInvestAmount;
    /**
     * 发放限制累计投资额度
     **/
    private double grantTotalInvestAmount;
    /**
     * 使用限制最小投资额度
     **/
    private double useSingleInvestAmount;
    /**
     * 简介
     **/
    private String summary;
    /**
     * 备注
     **/
    private String remark;
    /**
     * 添加时间
     **/
    private Date addTime;
    /**
     * 用户发放数量限制 用于新人红包发放
     **/
    private int quotaOfUser;

    /**
     * 获取【券编号】
     **/
    public String getCouponsNo() {
        return couponsNo;
    }

    /**
     * 设置【券编号】
     **/
    public void setCouponsNo(String couponsNo) {
        this.couponsNo = couponsNo;
    }

    /**
     * 获取【券名称】
     **/
    public String getName() {
        return name;
    }

    /**
     * 设置【券名称】
     **/
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取【券面金额】
     **/
    public double getAmount() {
        return amount;
    }

    /**
     * 设置【券面金额】
     **/
    public void setAmount(double amount) {
        this.amount = amount;
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
     * 获取【有效期类型天数截止时间】
     **/
    public int getValidityType() {
        return validityType;
    }

    /**
     * 设置【有效期类型天数截止时间】
     **/
    public void setValidityType(int validityType) {
        this.validityType = validityType;
    }

    /**
     * 获取【有效期值】
     **/
    public String getValidityValue() {
        return validityValue;
    }

    /**
     * 设置【有效期值】
     **/
    public void setValidityValue(String validityValue) {
        this.validityValue = validityValue;
    }

    /**
     * 获取【发放开始时间】
     **/
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置【发放开始时间】
     **/
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取【发放结束时间】
     **/
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置【发放结束时间】
     **/
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取【配额】
     **/
    public int getQuota() {
        return quota;
    }

    /**
     * 设置【配额】
     **/
    public void setQuota(int quota) {
        this.quota = quota;
    }

    /**
     * 获取【已发放数量】
     **/
    public int getUseQuota() {
        return useQuota;
    }

    /**
     * 设置【已发放数量】
     **/
    public void setUseQuota(int useQuota) {
        this.useQuota = useQuota;
    }

    /**
     * 获取【发放限制单笔投资额度】
     **/
    public double getGrantSingleInvestAmount() {
        return grantSingleInvestAmount;
    }

    /**
     * 设置【发放限制单笔投资额度】
     **/
    public void setGrantSingleInvestAmount(double grantSingleInvestAmount) {
        this.grantSingleInvestAmount = grantSingleInvestAmount;
    }

    /**
     * 获取【发放限制累计投资额度】
     **/
    public double getGrantTotalInvestAmount() {
        return grantTotalInvestAmount;
    }

    /**
     * 设置【发放限制累计投资额度】
     **/
    public void setGrantTotalInvestAmount(double grantTotalInvestAmount) {
        this.grantTotalInvestAmount = grantTotalInvestAmount;
    }

    /**
     * 获取【使用限制最小投资额度】
     **/
    public double getUseSingleInvestAmount() {
        return useSingleInvestAmount;
    }

    /**
     * 设置【使用限制最小投资额度】
     **/
    public void setUseSingleInvestAmount(double useSingleInvestAmount) {
        this.useSingleInvestAmount = useSingleInvestAmount;
    }

    /**
     * 获取【简介】
     **/
    public String getSummary() {
        return summary;
    }

    /**
     * 设置【简介】
     **/
    public void setSummary(String summary) {
        this.summary = summary;
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


    public User getBeUser() {
        return beUser;
    }

    public void setBeUser(User beUser) {
        this.beUser = beUser;
    }

    public int getAmountType() {
        return amountType;
    }

    public void setAmountType(int amountType) {
        this.amountType = amountType;
    }

    public int getQuotaOfUser() {
        return quotaOfUser;
    }

    public void setQuotaOfUser(int quotaOfUser) {
        this.quotaOfUser = quotaOfUser;
    }
}