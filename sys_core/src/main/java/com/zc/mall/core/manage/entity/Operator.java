package com.zc.mall.core.manage.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.user.entity.User;
import com.zc.sys.common.entity.LongPKEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 管理员
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月7日
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = BaseConstant.DB_PREFIX + BaseConstant.MODEL_M + "_operator")
public class Operator extends LongPKEntity {

    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;

    /**
     * 姓名
     **/
    private String name;
    /**
     * 用户名
     **/
    private String userName;
    /**
     * 登录密码
     **/
    private String pwd;
    /**
     * 交易密码
     **/
    private String payPwd;
    /**
     * 父id
     **/
    private int pid;
    /**
     * 用户关联
     **/
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    /**
     * 手机
     **/
    private String mobile;
    /**
     * 邮箱
     **/
    private String email;
    /**
     * 状态：1-正常, -1-禁用
     **/
    private int state;
    /**
     * 角色主键
     **/
    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;
    /**
     * 添加时间
     **/
    private Date addTime;
    /**
     * 最后登录IP
     **/
    private String loginIp;
    /**
     * 最后登录时间
     **/
    private Date loginTime;
    /**
     * 添加者
     **/
    private String addManager;
    /**
     * 修改时间
     **/
    private Date updateTime;
    /**
     * 修改者
     **/
    private String updateManager;
    /**
     * 备注
     **/
    private String remark;

    /**
     * 获取【姓名】
     **/
    public String getName() {
        return name;
    }

    /**
     * 设置【姓名】
     **/
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取【用户名】
     **/
    public String getUserName() {
        return userName;
    }

    /**
     * 设置【用户名】
     **/
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取【登录密码】
     **/
    public String getPwd() {
        return pwd;
    }

    /**
     * 设置【登录密码】
     **/
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * 获取【交易密码】
     **/
    public String getPayPwd() {
        return payPwd;
    }

    /**
     * 设置【交易密码】
     **/
    public void setPayPwd(String payPwd) {
        this.payPwd = payPwd;
    }

    /**
     * 获取【手机】
     **/
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置【手机】
     **/
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

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
     * 获取【状态：1-正常-1-禁用】
     **/
    public int getState() {
        return state;
    }

    /**
     * 设置【状态：1-正常-1-禁用】
     **/
    public void setState(int state) {
        this.state = state;
    }

    /**
     * 获取【角色主键】
     **/
    public Role getRole() {
        return role;
    }

    /**
     * 设置【角色主键】
     **/
    public void setRole(Role role) {
        this.role = role;
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
     * 获取【最后登录IP】
     **/
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * 设置【最后登录IP】
     **/
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    /**
     * 获取【最后登录时间】
     **/
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 设置【最后登录时间】
     **/
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * 获取【添加者】
     **/
    public String getAddManager() {
        return addManager;
    }

    /**
     * 设置【添加者】
     **/
    public void setAddManager(String addManager) {
        this.addManager = addManager;
    }

    /**
     * 获取【修改时间】
     **/
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置【修改时间】
     **/
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取【修改者】
     **/
    public String getUpdateManager() {
        return updateManager;
    }

    /**
     * 设置【修改者】
     **/
    public void setUpdateManager(String updateManager) {
        this.updateManager = updateManager;
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
     * 获取【父id】
     **/
    public int getPid() {
        return pid;
    }

    /**
     * 设置【父id】
     **/
    public void setPid(int pid) {
        this.pid = pid;
    }

    /**
     * 获取【用户关联】
     **/
    public User getUser() {
        return user;
    }

    /**
     * 设置【用户关联】
     **/
    public void setUser(User user) {
        this.user = user;
    }

}
