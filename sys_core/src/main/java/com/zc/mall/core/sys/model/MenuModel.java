package com.zc.mall.core.sys.model;

import com.zc.mall.core.sys.entity.Menu;
import com.zc.sys.common.model.jpa.Page;
import org.springframework.beans.BeanUtils;

/**
 * 菜单
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public class MenuModel extends Menu {
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
     * 实体转换model
     */
    public static MenuModel instance(Menu menu) {
        if (menu == null || menu.getId() <= 0) {
            return null;
        }
        MenuModel menuModel = new MenuModel();
        BeanUtils.copyProperties(menu, menuModel);
        return menuModel;
    }

    /**
     * model转换实体
     */
    public Menu prototype() {
        Menu menu = new Menu();
        BeanUtils.copyProperties(this, menu);
        return menu;
    }

    /**
     * 设置修改基本参数
     *
     * @param menu
     */
    public void setUpdateParam(Menu menu) {
        menu.setName(this.getName());
        menu.setParentId(this.getParentId());
        menu.setHref(this.getHref());
        menu.setSort(this.getSort());
        menu.setRemark(this.getRemark());
        menu.setState(this.getState());
        menu.setType(this.getType());
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

}