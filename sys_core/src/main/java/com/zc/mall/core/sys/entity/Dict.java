package com.zc.mall.core.sys.entity;

import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.sys.common.entity.LongPKNoneEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 数据字典（自定义主键） 主键规则：按照标识分类，id区间例如：[100,199),[200,299)，以此类推
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月6日
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = BaseConstant.DB_PREFIX + BaseConstant.MODEL_S + "_dict")
public class Dict extends LongPKNoneEntity {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;
    /**
     * 状态，0：禁用，1：启用
     **/
    private int state;
    /**
     * 排序
     **/
    private int sort;
    /**
     * 标识
     **/
    private String nid;
    /**
     * 标识名
     **/
    private String nidName;
    /**
     * 名称
     **/
    private String name;
    /**
     * 值
     **/
    private String value;
    /**
     * 备注
     **/
    private String remark;

    /**
     * 获取【状态，0：禁用，1：启用】
     **/
    public int getState() {
        return state;
    }

    /**
     * 设置【状态，0：禁用，1：启用】
     **/
    public void setState(int state) {
        this.state = state;
    }

    /**
     * 获取【排序】
     **/
    public int getSort() {
        return sort;
    }

    /**
     * 设置【排序】
     **/
    public void setSort(int sort) {
        this.sort = sort;
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
     * 获取【标识名】
     **/
    public String getNidName() {
        return nidName;
    }

    /**
     * 设置【标识名】
     **/
    public void setNidName(String nidName) {
        this.nidName = nidName;
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
