package com.zc.mall.core.manage.model;

import com.zc.mall.core.manage.entity.Role;
import com.zc.mall.core.user.model.UserInfoModel;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.model.jpa.Page;
import org.springframework.beans.BeanUtils;

/**
 * 管理员角色
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public class RoleModel extends Role {
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
     * Model
     **/
    private String searchName;
    /**
     * 添加管理员Id
     **/
    private long operatorId;
    /**
     * Model
     **/
    private OperatorModel operatorModel;
    /**
     * 商家关联用户Model
     **/
    private UserModel beUserModel;
    /**
     * 商家关联用户信息Model
     **/
    private UserInfoModel beUserInfoModel;
    /**
     * 用户Id
     **/
    private long userId;
    /**
     * 角色菜单
     **/
    private String menuIdStr;

    /**
     * 实体转换model
     */
    public static RoleModel instance(Role role) {
        if (role == null || role.getId() <= 0) {
            return null;
        }
        RoleModel roleModel = new RoleModel();
        BeanUtils.copyProperties(role, roleModel);
        return roleModel;
    }

    /**
     * model转换实体
     */
    public Role prototype() {
        Role role = new Role();
        BeanUtils.copyProperties(this, role);
        return role;
    }

    /**
     * 设置管理员角色修改基本参数
     *
     * @param role
     */
    public void setUpdateParam(Role role) {
        role.setName(this.getName());
        role.setRemark(this.getRemark());
        role.setState(this.getState());
        role.setBeUser(role.getBeUser());
        role.setOperator(role.getOperator());
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
     * 获取【条件查询】
     **/
    public String getSearchName() {
        return searchName;
    }

    /**
     * 设置【条件查询】
     **/
    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    /**
     * 获取【角色菜单】
     **/
    public String getMenuIdStr() {
        return menuIdStr;
    }

    /**
     * 设置【角色菜单】
     **/
    public void setMenuIdStr(String menuIdStr) {
        this.menuIdStr = menuIdStr;
    }

    /**
     * 获取【Model】
     **/
    public OperatorModel getOperatorModel() {
        return operatorModel;
    }

    /**
     * 设置【Model】
     **/
    public void setOperatorModel(OperatorModel operatorModel) {
        this.operatorModel = operatorModel;
    }

    public long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(long operatorId) {
        this.operatorId = operatorId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public UserModel getBeUserModel() {
        return beUserModel;
    }

    public void setBeUserModel(UserModel beUserModel) {
        this.beUserModel = beUserModel;
    }

    public UserInfoModel getBeUserInfoModel() {
        return beUserInfoModel;
    }

    public void setBeUserInfoModel(UserInfoModel beUserInfoModel) {
        this.beUserInfoModel = beUserInfoModel;
    }

}