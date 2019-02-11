package com.zc.mall.core.user.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.sys.common.entity.LongPKEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户地址
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = BaseConstant.DB_PREFIX + BaseConstant.MODEL_U + "_user_address")
public class UserAddress extends LongPKEntity {
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
     * 姓名
     **/
    private String name;
    /**
     * 手机号
     **/
    private String mobile;
    /**
     * 邮编
     **/
    private String postCode;
    /**
     * 省
     **/
    private String province;
    /**
     * 市
     **/
    private String city;
    /**
     * 区
     **/
    private String area;
    /**
     * 地址
     **/
    private String address;
    /**
     * 状态 1默认 0非默认 -1删除
     **/
    private int state;
    /**
     * 添加时间
     **/
    private Date addTime;
    /**
     * 性别
     **/
    private int sex;

    /**
     * 设置【姓名】
     **/
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取【姓名】
     **/
    public String getName() {
        return name;
    }

    /**
     * 设置【手机号】
     **/
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取【手机号】
     **/
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置【邮编】
     **/
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    /**
     * 获取【邮编】
     **/
    public String getPostCode() {
        return postCode;
    }

    /**
     * 设置【省】
     **/
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取【省】
     **/
    public String getProvince() {
        return province;
    }

    /**
     * 设置【市】
     **/
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取【市】
     **/
    public String getCity() {
        return city;
    }

    /**
     * 设置【区】
     **/
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * 获取【区】
     **/
    public String getArea() {
        return area;
    }

    /**
     * 设置【地址】
     **/
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取【地址】
     **/
    public String getAddress() {
        return address;
    }

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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}