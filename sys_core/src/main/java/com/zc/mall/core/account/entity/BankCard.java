package com.zc.mall.core.account.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.user.entity.User;
import com.zc.sys.common.entity.LongPKEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 银行卡
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = BaseConstant.DB_PREFIX + BaseConstant.MODEL_Acc + "_bank_card")
public class BankCard extends LongPKEntity {
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
     * 银行卡号
     **/
    private String bankCardNo;
    /**
     * 所属银行编码
     **/
    private String bankCode;
    /**
     * 所属银行名称
     **/
    private String bankName;
    /**
     * 类型
     **/
    private int type;
    /**
     * 支行编码
     **/
    private String branch;
    /**
     * 支行名称
     **/
    private String branchName;
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
     * 预留手机号
     **/
    private String mobile;
    /**
     * 预留姓名
     **/
    private String realName;
    /**
     * 剩余提取金额
     **/
    private double amount;
    /**
     * 状态
     **/
    private int state;
    /**
     * 自动扣款状态
     **/
    private int autoDeduct;
    /**
     * 备注
     **/
    private String remark;
    /**
     * 添加时间
     **/
    private Date addTime;
    /**
     * 添加ip
     **/
    private String addIp;

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
     * 获取【银行卡号】
     **/
    public String getBankCardNo() {
        return bankCardNo;
    }

    /**
     * 设置【银行卡号】
     **/
    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    /**
     * 获取【所属银行编码】
     **/
    public String getBankCode() {
        return bankCode;
    }

    /**
     * 设置【所属银行编码】
     **/
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    /**
     * 获取【所属银行名称】
     **/
    public String getBankName() {
        return bankName;
    }

    /**
     * 设置【所属银行名称】
     **/
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * 获取【支行编码】
     **/
    public String getBranch() {
        return branch;
    }

    /**
     * 设置【支行编码】
     **/
    public void setBranch(String branch) {
        this.branch = branch;
    }

    /**
     * 获取【支行名称】
     **/
    public String getBranchName() {
        return branchName;
    }

    /**
     * 设置【支行名称】
     **/
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    /**
     * 获取【省】
     **/
    public String getProvince() {
        return province;
    }

    /**
     * 设置【省】
     **/
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取【市】
     **/
    public String getCity() {
        return city;
    }

    /**
     * 设置【市】
     **/
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取【区】
     **/
    public String getArea() {
        return area;
    }

    /**
     * 设置【区】
     **/
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * 获取【预留手机号】
     **/
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置【预留手机号】
     **/
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取【剩余提取金额】
     **/
    public double getAmount() {
        return amount;
    }

    /**
     * 设置【剩余提取金额】
     **/
    public void setAmount(double amount) {
        this.amount = amount;
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
     * 获取【添加ip】
     **/
    public String getAddIp() {
        return addIp;
    }

    /**
     * 设置【添加ip】
     **/
    public void setAddIp(String addIp) {
        this.addIp = addIp;
    }

    /**
     * 获取【自动扣款状态】
     **/
    public int getAutoDeduct() {
        return autoDeduct;
    }

    /**
     * 设置【自动扣款状态】
     **/
    public void setAutoDeduct(int autoDeduct) {
        this.autoDeduct = autoDeduct;
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
     * 获取【预留姓名】
     **/
    public String getRealName() {
        return realName;
    }

    /**
     * 设置【预留姓名】
     **/
    public void setRealName(String realName) {
        this.realName = realName;
    }

}