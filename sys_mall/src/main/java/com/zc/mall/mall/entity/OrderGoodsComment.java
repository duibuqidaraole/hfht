package com.zc.mall.mall.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.manage.entity.Operator;
import com.zc.mall.core.user.entity.User;
import com.zc.sys.common.entity.LongPKEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 订单评论
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = BaseConstant.DB_PREFIX + BaseConstant.MODEL_G + "_order_goods_comment")
public class OrderGoodsComment extends LongPKEntity {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;

    /**
     * pid
     **/
    private int pid;
    /**
     * 类型
     **/
    private int type;
    /**
     * 订单商品
     */
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_goods_id")
    private OrderGoods orderGoods;
    /**
     * 用户
     */
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    /**
     * 管理员
     **/
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operator_id")
    private Operator operator;

    /**
     * 状态 (2用户评论未审核，-1用户评论删除 -2用户评论审核未通过 1用户评论通过未评论 5用户评论已评论)
     **/
    private int state;
    /**
     * 内容
     **/
    private String content;
    /**
     * 评分
     **/
    private int grade;
    /**
     * 图片
     **/
    private String imgPic;
    /**
     * 添加时间
     **/
    private Date addTime;

    /**
     * 设置【pid】
     **/
    public void setPid(int pid) {
        this.pid = pid;
    }

    /**
     * 获取【pid】
     **/
    public int getPid() {
        return pid;
    }

    /**
     * 设置【类型】
     **/
    public void setType(int type) {
        this.type = type;
    }

    /**
     * 获取【类型】
     **/
    public int getType() {
        return type;
    }

    /**
     * 设置【状态】
     **/
    public void setState(int state) {
        this.state = state;
    }

    /**
     * 获取【状态】
     **/
    public int getState() {
        return state;
    }

    /**
     * 设置【内容】
     **/
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取【内容】
     **/
    public String getContent() {
        return content;
    }

    /**
     * 设置【评分】
     **/
    public void setGrade(int grade) {
        this.grade = grade;
    }

    /**
     * 获取【评分】
     **/
    public int getGrade() {
        return grade;
    }

    /**
     * 设置【图片】
     **/
    public void setImgPic(String imgPic) {
        this.imgPic = imgPic;
    }

    /**
     * 获取【图片】
     **/
    public String getImgPic() {
        return imgPic;
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

    /**
     * 获取【订单商品】
     **/
    public OrderGoods getOrderGoods() {
        return orderGoods;
    }

    /**
     * 设置【订单商品】
     **/
    public void setOrderGoods(OrderGoods orderGoods) {
        this.orderGoods = orderGoods;
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

}