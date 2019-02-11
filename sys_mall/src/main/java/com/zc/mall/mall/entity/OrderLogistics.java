package com.zc.mall.mall.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.sys.common.entity.LongPKEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 物流
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = BaseConstant.DB_PREFIX + BaseConstant.MODEL_G + "_order_logistics")
public class OrderLogistics extends LongPKEntity {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     **/
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderInfo orderInfo;
    /**
     * 物流公司编号
     **/
    private String logisticsCompanyNo;
    /**
     * 快递编号
     **/
    private String postNo;
    /**
     * 发货时间
     **/
    private Date sendTime;
    /**
     * 收货时间
     **/
    private Date receiveTime;
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
     * 设置【物流公司编号】
     **/
    public void setLogisticsCompanyNo(String logisticsCompanyNo) {
        this.logisticsCompanyNo = logisticsCompanyNo;
    }

    /**
     * 获取【物流公司编号】
     **/
    public String getLogisticsCompanyNo() {
        return logisticsCompanyNo;
    }

    /**
     * 设置【快递编号】
     **/
    public void setPostNo(String postNo) {
        this.postNo = postNo;
    }

    /**
     * 获取【快递编号】
     **/
    public String getPostNo() {
        return postNo;
    }

    /**
     * 设置【发货时间】
     **/
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * 获取【发货时间】
     **/
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * 设置【收货时间】
     **/
    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    /**
     * 获取【收货时间】
     **/
    public Date getReceiveTime() {
        return receiveTime;
    }

    /**
     * 获取【订单id】
     **/
    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    /**
     * 设置【订单id】
     **/
    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}