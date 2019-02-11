package com.zc.mall.core.sys.entity;

import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.sys.common.entity.LongPKEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 系统参数配置
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月6日
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = BaseConstant.DB_PREFIX + BaseConstant.MODEL_S + "_config")
public class Config extends LongPKEntity {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;
    /**
     * 名称
     **/
    private String name;
    /**
     * 标识
     **/
    private String nid;
    /**
     * 值
     **/
    private String value;
    /**
     * 状态，0：未启用，1：启用
     **/
    private int state;
    /**
     * 配置分类
     **/
    private int type;
    /**
     * 备注
     **/
    private String remark;

    /**
     * 获取【状态，0：未启用，1：启用】
     **/
    public int getState() {
        return state;
    }

    /**
     * 设置【状态，0：未启用，1：启用】
     **/
    public void setState(int state) {
        this.state = state;
    }

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
     * 获取【值】
     **/
    public String getValue() {
        return value;
    }

    /**
     * 设置【值】
     **/
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取【类型，1：基础参数;2：rule规则nid】
     **/
    public int getType() {
        return type;
    }

    /**
     * 设置【类型，1：基础参数;2：rule规则nid】
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
}
