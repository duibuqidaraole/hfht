package com.zc.mall.core.user.model;

import com.zc.mall.core.user.entity.UserAddress;
import com.zc.sys.common.model.jpa.Page;
import org.springframework.beans.BeanUtils;

/**
 * 用户地址
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
public class UserAddressModel extends UserAddress {
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
     * 多个状态查询
     **/
    private String states;

    /**
     * 实体转换model
     */
    public static UserAddressModel instance(UserAddress userAddress) {
        if (userAddress == null || userAddress.getId() <= 0) {
            return null;
        }
        UserAddressModel userAddressModel = new UserAddressModel();
        BeanUtils.copyProperties(userAddress, userAddressModel);
        return userAddressModel;
    }

    /**
     * model转换实体
     */
    public UserAddress prototype() {
        UserAddress userAddress = new UserAddress();
        BeanUtils.copyProperties(this, userAddress);
        return userAddress;
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

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }
}