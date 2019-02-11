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
 * 活动推广奖励记录
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = BaseConstant.DB_PREFIX + BasePromotionConstant.MODEL_Pt + "_promotion_prize_record")
public class PromotionPrizeRecord extends LongPKEntity {
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
     * 活动推广奖励配置id
     **/
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prize_config_id")
    private PromotionPrizeConfig promotionPrizeConfig;
    /**
     * 活动推广id
     **/
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;
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
     * 活动条件记录值
     **/
    private String value;
    /**
     * 类型
     **/
    private int type;
    /**
     * 备注
     **/
    private String remark;
    /**
     * 添加时间
     **/
    private Date addTime;
    /**
     * 发放管理员
     **/
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operator_id")
    private Operator operator;

    /**
     * 获取【活动推广奖励配置id】
     **/
    public PromotionPrizeConfig getPromotionPrizeConfig() {
        return promotionPrizeConfig;
    }

    /**
     * 设置【活动推广奖励配置id】
     **/
    public void setPromotionPrizeConfig(PromotionPrizeConfig promotionPrizeConfig) {
        this.promotionPrizeConfig = promotionPrizeConfig;
    }

    /**
     * 获取【活动推广id】
     **/
    public Promotion getPromotion() {
        return promotion;
    }

    /**
     * 设置【活动推广id】
     **/
    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
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
     * 获取【活动条件记录值】
     **/
    public String getValue() {
        return value;
    }

    /**
     * 设置【活动条件记录值】
     **/
    public void setValue(String value) {
        this.value = value;
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


    public User getBeUser() {
        return beUser;
    }

    public void setBeUser(User beUser) {
        this.beUser = beUser;
    }

}