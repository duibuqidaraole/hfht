package com.zc.mall.promotion.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.manage.entity.Operator;
import com.zc.sys.common.entity.LongPKEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 商品分类
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = BaseConstant.DB_PREFIX + BaseConstant.MODEL_Pt + "_vip_coupons")
public class VipCoupons extends LongPKEntity {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;
    /**
     * 会员等级
     **/
    private int grade;
    /**
     * 名称
     **/
    private String name;
    /**
     * 状态
     **/
    private int state;
    /**
     * 内容
     **/
    private String content;
    /**
     * 优惠值
     **/
    private double value;
    /**
     * 价值
     **/
    private double prince;
    /**
     * 有效期类型:天数、截止时间
     **/
    private int validityType;
    /**
     * 有效值期
     **/
    private String validityValue;
    /**
     * 已发放数量
     **/
    private int useQuota;
    /**
     * 开始时间
     **/
    private Date beginTime;
    /**
     * 券no
     **/
    private String prizeNo;

    /**
     * 修改管理员id
     */
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "update_operator_id")
    private Operator updateOperator;

    /**
     * 添加管理员id
     */
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "add_operator_id")
    private Operator addOperator;

    /**
     * 修改时间
     **/
    private Date updateTime;

    /**
     * 添加时间
     **/
    private Date addTime;

    /**
     * 设置【会员等级】
     **/
    public void setGrade(int grade) {
        this.grade = grade;
    }

    /**
     * 获取【会员等级】
     **/
    public int getGrade() {
        return grade;
    }

    /**
     * 设置【名称】
     **/
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取【名称】
     **/
    public String getName() {
        return name;
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
     * 设置【内容】
     **/
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取【内容】
     **/
    public String getContent() {
        return content;
    }

    /**
     * 设置【优惠值】
     **/
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * 获取【优惠值】
     **/
    public double getValue() {
        return value;
    }

    /**
     * 设置【价值】
     **/
    public void setPrince(double prince) {
        this.prince = prince;
    }

    /**
     * 获取【价值】
     **/
    public double getPrince() {
        return prince;
    }

    /**
     * 设置【有效期类型:天数、截止时间】
     **/
    public void setValidityType(int validityType) {
        this.validityType = validityType;
    }

    /**
     * 获取【有效期类型:天数、截止时间】
     **/
    public int getValidityType() {
        return validityType;
    }

    /**
     * 设置【有效值期】
     **/
    public void setValidityValue(String validityValue) {
        this.validityValue = validityValue;
    }

    /**
     * 获取【有效值期】
     **/
    public String getValidityValue() {
        return validityValue;
    }

    /**
     * 设置【已发放数量】
     **/
    public void setUseQuota(int useQuota) {
        this.useQuota = useQuota;
    }

    /**
     * 获取【已发放数量】
     **/
    public int getUseQuota() {
        return useQuota;
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
     * 设置【修改时间】
     **/
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取【修改时间】
     **/
    public Date getUpdateTime() {
        return updateTime;
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

    public Operator getUpdateOperator() {
        return updateOperator;
    }

    public void setUpdateOperator(Operator updateOperator) {
        this.updateOperator = updateOperator;
    }

    public Operator getAddOperator() {
        return addOperator;
    }

    public void setAddOperator(Operator addOperator) {
        this.addOperator = addOperator;
    }

    public String getPrizeNo() {
        return prizeNo;
    }

    public void setPrizeNo(String prizeNo) {
        this.prizeNo = prizeNo;
    }
}