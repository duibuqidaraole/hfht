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
 * 红包发放记录
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年12月20日
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = BaseConstant.DB_PREFIX + BasePromotionConstant.MODEL_Pt + "_bonus_coupons_record")
public class BonusCouponsRecord extends LongPKEntity {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;

    /**
     * 用户
     **/
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * 红包
     **/
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bonus_coupons_id")
    private BonusCoupons bonusCoupons;

    /**
     * 发放管理员
     **/
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operator_id")
    private Operator operator;
    /**
     * 状态
     **/
    private int state;
    /**
     * 使用时间
     **/
    private Date useTime;
    /**
     * 使用类型
     **/
    private int useType;
    /**
     * 使用关联id
     **/
    private String useId;
    /**
     * 发放开始时间
     **/
    private Date startTime;
    /**
     * 发放结束时间
     **/
    private Date endTime;
    /**
     * 备注
     **/
    private String remark;
    /**
     * 添加时间
     **/
    private Date addTime;
    /**
     * 关联商家用户
     **/
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "be_user_id")
    private User beUser;
    /**
     * 最终的金额
     **/
    private double realAmount;
    /**
     * 最终的金额 返利方式 1:金额 2:比例
     **/
    private int realAmountType;
    /**
     * vip红包 最下级使用金额
     **/
    private double vipAmount;


    /**
     * 获取【user】
     **/
    public User getUser() {
        return user;
    }

    /**
     * 设置【user】
     **/
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * 获取【红包】
     **/
    public BonusCoupons getBonusCoupons() {
        return bonusCoupons;
    }

    /**
     * 设置【红包】
     **/
    public void setBonusCoupons(BonusCoupons bonusCoupons) {
        this.bonusCoupons = bonusCoupons;
    }

    /**
     * 获取【发放管理员】
     **/
    public Operator getOperator() {
        return operator;
    }

    /**
     * 设置【发放管理员】
     **/
    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    /**
     * 获取【state】
     **/
    public int getState() {
        return state;
    }

    /**
     * 设置【state】
     **/
    public void setState(int state) {
        this.state = state;
    }

    /**
     * 获取【useTime】
     **/
    public Date getUseTime() {
        return useTime;
    }

    /**
     * 设置【useTime】
     **/
    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    /**
     * 获取【startTime】
     **/
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置【startTime】
     **/
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取【endTime】
     **/
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置【endTime】
     **/
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取【remark】
     **/
    public String getRemark() {
        return remark;
    }

    /**
     * 设置【remark】
     **/
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取【addTime】
     **/
    public Date getAddTime() {
        return addTime;
    }

    /**
     * 设置【addTime】
     **/
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取【使用类型】
     **/
    public int getUseType() {
        return useType;
    }

    /**
     * 设置【使用类型】
     **/
    public void setUseType(int useType) {
        this.useType = useType;
    }

    /**
     * 获取【使用关联id】
     **/
    public String getUseId() {
        return useId;
    }

    /**
     * 设置【使用关联id】
     **/
    public void setUseId(String useId) {
        this.useId = useId;
    }

    public User getBeUser() {
        return beUser;
    }

    public void setBeUser(User beUser) {
        this.beUser = beUser;
    }

    public double getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(double realAmount) {
        this.realAmount = realAmount;
    }

    public double getVipAmount() {
        return vipAmount;
    }

    public void setVipAmount(double vipAmount) {
        this.vipAmount = vipAmount;
    }

    public int getRealAmountType() {
        return realAmountType;
    }

    public void setRealAmountType(int realAmountType) {
        this.realAmountType = realAmountType;
    }
}