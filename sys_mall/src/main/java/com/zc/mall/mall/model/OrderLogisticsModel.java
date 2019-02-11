package com.zc.mall.mall.model;

import com.zc.mall.mall.entity.OrderLogistics;
import com.zc.sys.common.model.jpa.Page;
import org.springframework.beans.BeanUtils;

/**
 * 物流
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
public class OrderLogisticsModel extends OrderLogistics {
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
    public static OrderLogisticsModel instance(OrderLogistics orderLogistics) {
        if (orderLogistics == null || orderLogistics.getId() <= 0) {
            return null;
        }
        OrderLogisticsModel orderLogisticsModel = new OrderLogisticsModel();
        BeanUtils.copyProperties(orderLogistics, orderLogisticsModel);
        return orderLogisticsModel;
    }

    /**
     * model转换实体
     */
    public OrderLogistics prototype() {
        OrderLogistics orderLogistics = new OrderLogistics();
        BeanUtils.copyProperties(this, orderLogistics);
        return orderLogistics;
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