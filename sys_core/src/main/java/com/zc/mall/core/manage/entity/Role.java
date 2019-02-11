package com.zc.mall.core.manage.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.user.entity.User;
import com.zc.sys.common.entity.LongPKEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

/**
 * 角色
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月7日
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = BaseConstant.DB_PREFIX + BaseConstant.MODEL_M + "_role")
public class Role extends LongPKEntity {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;
    /**
     * 角色名
     **/
    private String name;
    /**
     * 添加用户id
     **/
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "add_operator_id")
    private Operator operator;
    /**
     * 关联商家用户
     **/
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "be_user_id")
    private User beUser;
    /**
     * 状态：0-未启用；1-启用
     **/
    private int state;
    /**
     * 备注
     **/
    private String remark;
    /**
     * 获取权限
     **/
    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    private List<RoleMenu> roleMenus;

    /**
     * 获取【角色名】
     **/
    public String getName() {
        return name;
    }

    /**
     * 设置【角色名】
     **/
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取【状态：0-未启用；1-启用】
     **/
    public int getState() {
        return state;
    }

    /**
     * 设置【状态：0-未启用；1-启用】
     **/
    public void setState(int state) {
        this.state = state;
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
     * 获取【获取权限】
     **/
    public List<RoleMenu> getRoleMenus() {
        return roleMenus;
    }

    /**
     * 设置【获取权限】
     **/
    public void setRoleMenus(List<RoleMenu> roleMenus) {
        this.roleMenus = roleMenus;
    }

    /**
     * 获取【添加用户id】
     **/
    public Operator getOperator() {
        return operator;
    }

    /**
     * 设置【添加用户id】
     **/
    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public User getBeUser() {
        return beUser;
    }

    public void setBeUser(User beUser) {
        this.beUser = beUser;
    }

}
