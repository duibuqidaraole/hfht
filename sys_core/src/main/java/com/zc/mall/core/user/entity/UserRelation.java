package com.zc.mall.core.user.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.sys.common.entity.LongPKEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户关系
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2018年03月15日
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = BaseConstant.DB_PREFIX + BaseConstant.MODEL_U + "_user_relation")
public class
UserRelation extends LongPKEntity {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;
    /**
     * 关注者/借款人
     */
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private User user;
    /**
     * 被关注者/商家
     */
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "be_user_id")
    private User beUser;
    /**
     * 类型:1.关注商家,2.借款人-商家-
     **/
    private int type;
    /**
     * 状态
     **/
    private int state;
    /**
     * 添加时间
     **/
    private Date addTime;

    /**
     * 获取【关注者借款人】
     **/
    public User getUser() {
        return user;
    }

    /**
     * 设置【关注者借款人】
     **/
    public void setUser(User user) {
        this.user = user;
    }


    /**
     * 获取【被关注者商家】
     **/
    public User getBeUser() {
        return beUser;
    }


    /**
     * 设置【被关注者商家】
     **/
    public void setBeUser(User beUser) {
        this.beUser = beUser;
    }


    /**
     * 获取【类型:1.关注商家2.借款人-商家-】
     **/
    public int getType() {
        return type;
    }


    /**
     * 设置【类型:1.关注商家2.借款人-商家-】
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