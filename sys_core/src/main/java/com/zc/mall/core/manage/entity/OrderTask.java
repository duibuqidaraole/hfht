package com.zc.mall.core.manage.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.user.entity.User;
import com.zc.sys.common.entity.LongPKEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 订单管理
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = BaseConstant.DB_PREFIX + BaseConstant.MODEL_M + "_order_task")
public class OrderTask extends LongPKEntity {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;
    /**
     * 用户
     */
    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;
    /**
     * 类型
     **/
    private String type;
    /**
     * 关联订单号
     **/
    private String orderNo;
    /**
     * 状态
     **/
    private int state;
    /**
     * 备注
     **/
    private String remark;
    /**
     * 处理结果
     **/
    private String doResult;
    /**
     * 处理时间
     **/
    private Date doTime;
    /**
     * 添加时间
     **/
    private Date addTime;

    public OrderTask() {
        super();
    }

    public OrderTask(User user, String type, String orderNo, int state,
                     String remark, Date addTime) {
        super();
        this.user = user;
        this.type = type;
        this.orderNo = orderNo;
        this.state = state;
        this.remark = remark;
        this.addTime = addTime;
    }

    public OrderTask(String type, String orderNo, int state, String remark,
                     Date addTime) {
        super();
        this.type = type;
        this.orderNo = orderNo;
        this.state = state;
        this.remark = remark;
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
     * 获取【关联订单号】
     **/
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置【关联订单号】
     **/
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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
     * 获取【处理结果】
     **/
    public String getDoResult() {
        return doResult;
    }

    /**
     * 设置【处理结果】
     **/
    public void setDoResult(String doResult) {
        this.doResult = doResult;
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
     * 获取【处理时间】
     **/
    public Date getDoTime() {
        return doTime;
    }

    /**
     * 设置【处理时间】
     **/
    public void setDoTime(Date doTime) {
        this.doTime = doTime;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "OrderTask [orderNo=" + orderNo + ", type=" + type + ", state="
                + state + ", remark=" + remark + ", doResult=" + doResult
                + ", doTime=" + doTime + ", addTime=" + addTime + "]";
    }
}