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
 * 通知消息
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月15日
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = BaseConstant.DB_PREFIX + BaseConstant.MODEL_M + "_notice_message")
public class NoticeMessage extends LongPKEntity {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;

    /**
     * 标识
     **/
    private String nid;
    /**
     * 类型1-message;2-SMS;3-email;4-appPush
     **/
    private int type;
    /**
     * 发送用户
     **/
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "send_user_id")
    private User sendUser;
    /**
     * 接收用户
     **/
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receive_user_id")
    private User receiveUser;
    /**
     * 管理员
     **/
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operator_id")
    private Operator operator;
    /**
     * 状态 0:未发送成功 1：发送成功 5： 已读
     **/
    private int state;
    /**
     * 标题
     **/
    private String title;
    /**
     * 内容
     **/
    private String content;
    /**
     * 发送结果描述
     **/
    private String result;
    /**
     * 发用结果代码
     **/
    private String resultCode;
    /**
     * 接收地址
     **/
    private String receiveAddr;
    /**
     * 验证码
     **/
    private String code;
    /**
     * 订单号
     **/
    private String orderNo;
    /**
     * 备注
     **/
    private String remark;
    /**
     * 添加时间
     **/
    private Date addTime;

    /**
     * 获取【标识】
     **/
    public String getNid() {
        return nid;
    }

    /**
     * 设置【标识】
     **/
    public void setNid(String nid) {
        this.nid = nid;
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
     * 获取【发送用户】
     **/
    public User getSendUser() {
        return sendUser;
    }

    /**
     * 设置【发送用户】
     **/
    public void setSendUser(User sendUser) {
        this.sendUser = sendUser;
    }

    /**
     * 获取【接收用户】
     **/
    public User getReceiveUser() {
        return receiveUser;
    }

    /**
     * 设置【接收用户】
     **/
    public void setReceiveUser(User receiveUser) {
        this.receiveUser = receiveUser;
    }

    /**
     * 获取【管理员】
     **/
    public Operator getOperator() {
        return operator;
    }

    /**
     * 设置【管理员】
     **/
    public void setOperator(Operator operator) {
        this.operator = operator;
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
     * 获取【标题】
     **/
    public String getTitle() {
        return title;
    }

    /**
     * 设置【标题】
     **/
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取【内容】
     **/
    public String getContent() {
        return content;
    }

    /**
     * 设置【内容】
     **/
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取【发送结果描述】
     **/
    public String getResult() {
        return result;
    }

    /**
     * 设置【发送结果描述】
     **/
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * 获取【发用结果代码】
     **/
    public String getResultCode() {
        return resultCode;
    }

    /**
     * 设置【发用结果代码】
     **/
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * 获取【接收地址】
     **/
    public String getReceiveAddr() {
        return receiveAddr;
    }

    /**
     * 设置【接收地址】
     **/
    public void setReceiveAddr(String receiveAddr) {
        this.receiveAddr = receiveAddr;
    }

    /**
     * 获取【验证码】
     **/
    public String getCode() {
        return code;
    }

    /**
     * 设置【验证码】
     **/
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取【订单号】
     **/
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置【订单号】
     **/
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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
}