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
 * 商品规格
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = BaseConstant.DB_PREFIX + BaseConstant.MODEL_G + "_goods_spec")
public class GoodsSpec extends LongPKEntity {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;

    /**
     * 商品唯一编号
     **/
    private String no;
    /**
     * 名称
     **/
    private String name;
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
     * 添加时间
     **/
    private int type;

    /**
     * 设置【商品唯一编号】
     **/
    public void setNo(String no) {
        this.no = no;
    }

    /**
     * 获取【商品唯一编号】
     **/
    public String getNo() {
        return no;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}