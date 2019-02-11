package com.zc.mall.promotion.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.user.entity.User;
import com.zc.sys.common.entity.LongPKEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户vip会员卡
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月15日
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = BaseConstant.DB_PREFIX + BaseConstant.MODEL_Pt + "_user_vip_coupons")
public class UserVipCoupons extends LongPKEntity {
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
     * vip卡
     */
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vip_coupons_id")
    private VipCoupons vipCoupons;

    /**
     * 状态
     **/
    private int state;
    /**
     * 开始时间
     **/
    private Date beginTime;
    /**
     * 结束时间
     **/
    private Date endTime;
    /**
     * 添加时间
     **/
    private Date addTime;

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
     * 设置【开始时间】
     **/
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * 获取【开始时间】
     **/
    public Date getBeginTime() {
        return beginTime;
    }

    /**
     * 设置【结束时间】
     **/
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取【结束时间】
     **/
    public Date getEndTime() {
        return endTime;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public VipCoupons getVipCoupons() {
        return vipCoupons;
    }

    public void setVipCoupons(VipCoupons vipCoupons) {
        this.vipCoupons = vipCoupons;
    }
}