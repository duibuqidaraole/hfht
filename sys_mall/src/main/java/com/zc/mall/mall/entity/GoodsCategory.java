package com.zc.mall.mall.entity;

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
@Table(name = BaseConstant.DB_PREFIX + BaseConstant.MODEL_G + "_goods_category")
public class GoodsCategory extends LongPKEntity {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;

    /**
     * 姓名
     **/
    private String name;
    /**
     * 标识
     **/
    private String nid;

    /**
     * 修改管理员id
     */
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "update_operator_id")
    private Operator updateOperator;
    /**
     * 修改时间
     **/
    private Date updateTime;
    /**
     * 添加管理员id
     */
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "add_operator_id")
    private Operator addOperator;
    /**
     * 添加时间
     **/
    private Date addTime;

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

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }
}