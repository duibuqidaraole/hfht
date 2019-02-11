package com.zc.mall.core.manage.model;

import com.zc.mall.core.manage.entity.RoleMenu;
import com.zc.mall.core.sys.model.MenuModel;
import com.zc.sys.common.model.jpa.Page;
import org.springframework.beans.BeanUtils;

/**
 * 角色-菜单
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public class RoleMenuModel extends RoleMenu {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;

    /**
     * 当前页码
     **/
    private int pageNo;
    /**
     * 每页数据条数
     **/
    private int pageSize = Page.ROWS;
    /**
     * 条件查询
     **/
    private String searchName;
    /**
     * 状态：0-未启用；1-启用
     **/
    private int state;
    /**
     * 菜单Model
     **/
    private MenuModel menuModel;
    /**
     * 角色Model
     **/
    private RoleModel roleModel;
    /**
     * 角色Id
     **/
    private long roleId;

    /**
     * 实体转换model
     */
    public static RoleMenuModel instance(RoleMenu roleMenu) {
        if (roleMenu == null || roleMenu.getId() <= 0) {
            return null;
        }
        RoleMenuModel roleMenuModel = new RoleMenuModel();
        BeanUtils.copyProperties(roleMenu, roleMenuModel);
        return roleMenuModel;
    }

    /**
     * model转换实体
     */
    public RoleMenu prototype() {
        RoleMenu roleMenu = new RoleMenu();
        BeanUtils.copyProperties(this, roleMenu);
        return roleMenu;
    }

    /**
     * 获取【当前页码】
     **/
    public int getPageNo() {
        return pageNo;
    }

    /**
     * 设置【当前页码】
     **/
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * 获取【每页数据条数】
     **/
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置【每页数据条数】
     **/
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取【searchName】
     **/
    public String getSearchName() {
        return searchName;
    }

    /**
     * 设置【searchName】
     **/
    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    /**
     * 获取【menuModel】
     **/
    public MenuModel getMenuModel() {
        return menuModel;
    }

    /**
     * 设置【menuModel】
     **/
    public void setMenuModel(MenuModel menuModel) {
        this.menuModel = menuModel;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public RoleModel getRoleModel() {
        return roleModel;
    }

    public void setRoleModel(RoleModel roleModel) {
        this.roleModel = roleModel;
    }

}