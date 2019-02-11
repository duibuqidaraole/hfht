package com.zc.mall.core.user.entity;

import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.sys.common.entity.LongPKEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 用户
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = BaseConstant.DB_PREFIX + BaseConstant.MODEL_U + "_user_business_apply")
public class UserBusinessApply extends LongPKEntity {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;
    /**
     * 公司名称
     **/
    private String companyName;
    /**
     * 联系人
     **/
    private String userName;
    /**
     * 内容说明
     **/
    private String content;
    /**
     * 状态
     **/
    private int state;
    /**
     * 邮箱
     **/
    private String email;
    /**
     * 电话
     **/
    private String mobile;
    /**
     * 备注
     **/
    private String remark;
    /**
     * 添加时间
     **/
    private Date addTime;
    /**
     * 处理时间
     **/
    private Date doTime;

    /**
     * 获取【邮箱】
     **/
    public String getEmail() {
        return email;
    }

    /**
     * 设置【邮箱】
     **/
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取【电话】
     **/
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置【电话】
     **/
    public void setMobile(String mobile) {
        this.mobile = mobile;
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
     * 获取【公司名称】
     **/
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置【公司名称】
     **/
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 获取【联系人】
     **/
    public String getUserName() {
        return userName;
    }

    /**
     * 设置【联系人】
     **/
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取【内容说明】
     **/
    public String getContent() {
        return content;
    }

    /**
     * 设置【内容说明】
     **/
    public void setContent(String content) {
        this.content = content;
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
     * 获取【处理时间】
     **/
    public Date getDoTime() {
        return doTime;
    }

    /**
     * 设置【处理时间】
     **/
    public void setDoTime(Date doTime) {
        this.doTime = doTime;
    }


}
