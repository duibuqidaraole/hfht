package com.zc.mall.promotion.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.manage.entity.Operator;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.promotion.constant.BasePromotionConstant;
import com.zc.sys.common.entity.LongPKEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;

/**
 * 活动推广
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = BaseConstant.DB_PREFIX + BasePromotionConstant.MODEL_Pt + "_promotion")
public class Promotion extends LongPKEntity {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;
    /**
     * 标题
     **/
    private String name;
    /**
     * 推广方式
     **/
    private int way;
    /**
     * 推广内容
     **/
    private String content;
    /**
     * 摘要
     **/
    private String summary;
    /**
     * 活动图片地址
     **/
    private String pic;
    /**
     * 活动链接
     **/
    private String url;
    /**
     * 状态
     **/
    private int state;
    /**
     * 开始时间
     **/
    private Date startTime;
    /**
     * 结束时间
     **/
    private Date endTime;
    /**
     * 备注
     **/
    private String remark;
    /**
     * 添加时间
     **/
    private Date addTime;
    /**
     * 关联商家用户
     **/
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "be_user_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private User beUser;
    /**
     * 添加管理员
     **/
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "add_operator_id")
    private Operator operator;

    /**
     * 获取【标题】
     **/
    public String getName() {
        return name;
    }

    /**
     * 设置【标题】
     **/
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取【推广方式】
     **/
    public int getWay() {
        return way;
    }

    /**
     * 设置【推广方式】
     **/
    public void setWay(int way) {
        this.way = way;
    }

    /**
     * 获取【摘要】
     **/
    public String getSummary() {
        return summary;
    }

    /**
     * 设置【摘要】
     **/
    public void setSummary(String summary) {
        this.summary = summary;
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
     * 获取【开始时间】
     **/
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置【开始时间】
     **/
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取【结束时间】
     **/
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置【结束时间】
     **/
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    /**
     * 获取【推广内容】
     **/
    public String getContent() {
        return content;
    }

    /**
     * 设置【推广内容】
     **/
    public void setContent(String content) {
        this.content = content;
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
     * 获取【活动图片地址】
     **/
    public String getPic() {
        return pic;
    }

    /**
     * 设置【活动图片地址】
     **/
    public void setPic(String pic) {
        this.pic = pic;
    }

    /**
     * 获取【活动链接】
     **/
    public String getUrl() {
        return url;
    }

    /**
     * 设置【活动链接】
     **/
    public void setUrl(String url) {
        this.url = url;
    }

    public User getBeUser() {
        return beUser;
    }

    public void setBeUser(User beUser) {
        this.beUser = beUser;
    }

}