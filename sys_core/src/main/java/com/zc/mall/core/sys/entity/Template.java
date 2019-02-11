package com.zc.mall.core.sys.entity;

import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.sys.common.entity.LongPKEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 模版配置
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月08日
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = BaseConstant.DB_PREFIX + BaseConstant.MODEL_S + "_template")
public class Template extends LongPKEntity {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;
    /**
     * 名称
     **/
    private String name;
    /**
     * 标识-规则：模块_类型_名称，例：notice_SMS_reg
     **/
    private String nid;
    /**
     * 类型：1-资金模版，2-通知信息模版，3-操作日志模版，4-积分日志模版，5-协议模版
     **/
    private int type;
    /**
     * 子类型：例：1短信，2邮件，3站内信
     **/
    private int typeSub;
    /**
     * 收支类型
     **/
    private int paymentsType;
    /**
     * 模版标题
     **/
    private String title;
    /**
     * 模版内容
     **/
    private String content;
    /**
     * 使用渠道-例：1-短信渠道，2-邮件渠道
     **/
    private int route;
    /**
     * 状态：0未启用，1启用
     **/
    private int state;
    /**
     * 备注
     **/
    private String remark;

    /**
     * 获取【名称】
     **/
    public String getName() {
        return name;
    }

    /**
     * 设置【名称】
     **/
    public void setName(String name) {
        this.name = name;
    }

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
     * 获取【类型：1-资金流水模版，2-通知信息模版，3-操作日志模版，4-积分日志模版，5-协议模版】
     **/
    public int getType() {
        return type;
    }

    /**
     * 设置【类型：1-资金流水模版，2-通知信息模版，3-操作日志模版，4-积分日志模版，5-协议模版】
     **/
    public void setType(int type) {
        this.type = type;
    }

    /**
     * 获取【子类型】
     **/
    public int getTypeSub() {
        return typeSub;
    }

    /**
     * 设置【子类型】
     **/
    public void setTypeSub(int typeSub) {
        this.typeSub = typeSub;
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
     * 获取【状态：0未启用，1启用】
     **/
    public int getState() {
        return state;
    }

    /**
     * 设置【状态：0未启用，1启用】
     **/
    public void setState(int state) {
        this.state = state;
    }

    /**
     * 获取【使用渠道-例：短信渠道，邮件渠道】
     **/
    public int getRoute() {
        return route;
    }

    /**
     * 设置【使用渠道-例：短信渠道，邮件渠道】
     **/
    public void setRoute(int route) {
        this.route = route;
    }

    /**
     * 获取【模版标题】
     **/
    public String getTitle() {
        return title;
    }

    /**
     * 设置【模版标题】
     **/
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取【模版内容】
     **/
    public String getContent() {
        return content;
    }

    /**
     * 设置【模版内容】
     **/
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取【收支类型】
     **/
    public int getPaymentsType() {
        return paymentsType;
    }

    /**
     * 设置【收支类型】
     **/
    public void setPaymentsType(int paymentsType) {
        this.paymentsType = paymentsType;
    }
}