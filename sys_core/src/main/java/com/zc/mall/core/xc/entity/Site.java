package com.zc.mall.core.xc.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.manage.entity.Operator;
import com.zc.sys.common.entity.LongPKEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 栏目
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = BaseConstant.DB_PREFIX + BaseConstant.MODEL_Xc + "_site")
public class Site extends LongPKEntity {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;
    /**
     * 名称
     **/
    private String name;
    /**
     * 添加管理员
     **/
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "update_operator_id")
    private Operator operator;
    /**
     * 标识
     **/
    private String nid;
    /**
     * 父id
     **/
    private long pid;
    /**
     * 状态，-1：禁用，1：启用
     **/
    private int state;
    /**
     * 类型
     **/
    private int type;
    /**
     * 跳转链接
     **/
    private String url;
    /**
     * 排序
     **/
    private int sort;
    /**
     * 简介
     **/
    private String introduction;
    /**
     * 内容
     **/
    private String content;
    /**
     * 图片地址
     **/
    private String picPath;
    /**
     * 最后修改时间
     **/
    private Date updateTime;
    /**
     * 最后修改ip
     **/
    private String updateIp;

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
     * 获取【父id】
     **/
    public long getPid() {
        return pid;
    }

    /**
     * 设置【父id】
     **/
    public void setPid(long pid) {
        this.pid = pid;
    }

    /**
     * 获取【状态，-1：禁用，1：启用】
     **/
    public int getState() {
        return state;
    }

    /**
     * 设置【状态，-1：禁用，1：启用】
     **/
    public void setState(int state) {
        this.state = state;
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
     * 获取【跳转链接】
     **/
    public String getUrl() {
        return url;
    }

    /**
     * 设置【跳转链接】
     **/
    public void setUrl(String url) {
        this.url = url;
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
     * 获取【简介】
     **/
    public String getIntroduction() {
        return introduction;
    }

    /**
     * 设置【简介】
     **/
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
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
     * 获取【图片地址】
     **/
    public String getPicPath() {
        return picPath;
    }

    /**
     * 设置【图片地址】
     **/
    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    /**
     * 获取【最后修改时间】
     **/
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置【最后修改时间】
     **/
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取【最后修改ip】
     **/
    public String getUpdateIp() {
        return updateIp;
    }

    /**
     * 设置【最后修改ip】
     **/
    public void setUpdateIp(String updateIp) {
        this.updateIp = updateIp;
    }

    /**
     * 获取【添加管理员】
     **/
    public Operator getOperator() {
        return operator;
    }

    /**
     * 设置【添加管理员】
     **/
    public void setOperator(Operator operator) {
        this.operator = operator;
    }

}