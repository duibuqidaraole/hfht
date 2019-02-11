package com.zc.mall.core.manage.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.sys.entity.Menu;
import com.zc.sys.common.entity.LongPKEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * 角色菜单
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月7日
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = BaseConstant.DB_PREFIX + BaseConstant.MODEL_M + "_role_menu")
public class RoleMenu extends LongPKEntity {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    /**
     * 菜单主键
     */
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    /**
     * 获取【role】
     */
    public Role getRole() {
        return role;
    }

    /**
     * 设置【role】
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * 获取【菜单主键】
     */
    public Menu getMenu() {
        return menu;
    }

    /**
     * 设置【菜单主键】
     */
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

}
