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
 * 文章
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = BaseConstant.DB_PREFIX + BaseConstant.MODEL_Xc + "_article")
public class Article extends LongPKEntity {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;
    /**
     * 所属栏目
     **/
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "site_id")
    private Site site;
    /**
     * 简介
     **/
    private String introduction;
    /**
     * 模版标题
     **/
    private String title;
    /**
     * 内容
     **/
    private String content;
    /**
     * 链接
     **/
    private String url;
    /**
     * 排序
     **/
    private int sort;
    /**
     * 热文章
     **/
    private int isHot;
    /**
     * 点击量
     **/
    private int clicks;
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
     * 添加管理员
     **/
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "add_operator_id")
    private Operator operator;
    /**
     * 添加管理员
     **/
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "update_operator_id")
    private Operator updateOperator;
    /**
     * 状态，-1：禁用，1：启用
     **/
    private int state;

    /**
     * 获取【所属栏目】
     **/
    public Site getSite() {
        return site;
    }

    /**
     * 设置【所属栏目】
     **/
    public void setSite(Site site) {
        this.site = site;
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
     * 获取【热文章】
     **/
    public int getIsHot() {
        return isHot;
    }

    /**
     * 设置【热文章】
     **/
    public void setIsHot(int isHot) {
        this.isHot = isHot;
    }

    /**
     * 获取【点击量】
     **/
    public int getClicks() {
        return clicks;
    }

    /**
     * 设置【点击量】
     **/
    public void setClicks(int clicks) {
        this.clicks = clicks;
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
     * 获取【链接】
     **/
    public String getUrl() {
        return url;
    }

    /**
     * 设置【链接】
     **/
    public void setUrl(String url) {
        this.url = url;
    }

    public Operator getUpdateOperator() {
        return updateOperator;
    }

    public void setUpdateOperator(Operator updateOperator) {
        this.updateOperator = updateOperator;
    }
}