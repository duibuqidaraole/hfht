package com.zc.mall.mall.model;

import com.zc.mall.core.common.constant.BaseConstant.OrderNid;
import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.manage.entity.OrderTask;
import com.zc.mall.mall.entity.OrderPayHistory;
import com.zc.mall.mall.service.OrderPayHistoryService;
import com.zc.sys.common.model.jpa.Page;
import org.springframework.beans.BeanUtils;

/**
 * 支付记录
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
public class OrderPayHistoryModel extends OrderPayHistory {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;

    /**
     * 订单任务信息
     **/
    private OrderTask orderTask;

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

    public OrderPayHistoryModel() {

    }

    public OrderPayHistoryModel(String no, String body, Double amount) {
        this.setAmountReal(amount);
        this.setPaymentTradeNo(no);
        this.setRemark(body);
    }

    /**
     * 实体转换model
     */
    public static OrderPayHistoryModel instance(OrderPayHistory orderPayHistory) {
        if (orderPayHistory == null || orderPayHistory.getId() <= 0) {
            return null;
        }
        OrderPayHistoryModel orderPayHistoryModel = new OrderPayHistoryModel();
        BeanUtils.copyProperties(orderPayHistory, orderPayHistoryModel);
        return orderPayHistoryModel;
    }

    /**
     * model转换实体
     */
    public OrderPayHistory prototype() {
        OrderPayHistory orderPayHistory = new OrderPayHistory();
        BeanUtils.copyProperties(this, orderPayHistory);
        return orderPayHistory;
    }

    /**
     * 队列处理
     */
    /**
     * 队列
     */
    public void doQueue() {
        String type = this.getOrderTask().getType();
        OrderPayHistoryService orderPayHistoryService = BeanUtil.getBean(OrderPayHistoryService.class);
        if (OrderNid.ORDER_NID_ORDER_INFO_PAY_HISTORY.getNid().equals(type)) {
            orderPayHistoryService.add(this);
        }
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
     * 获取【订单任务信息】
     **/
    public OrderTask getOrderTask() {
        return orderTask;
    }

    /**
     * 设置【订单任务信息】
     **/
    public void setOrderTask(OrderTask orderTask) {
        this.orderTask = orderTask;
    }
}