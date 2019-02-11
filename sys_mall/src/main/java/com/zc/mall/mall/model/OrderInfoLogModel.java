package com.zc.mall.mall.model;

import com.zc.mall.mall.constant.OrderInfoLogTypeEnum;
import com.zc.mall.mall.entity.OrderInfoLog;
import com.zc.sys.common.model.jpa.Page;
import org.springframework.beans.BeanUtils;

/**
 * 订单日志
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年12月05日
 */
public class OrderInfoLogModel extends OrderInfoLog {
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
    public static OrderInfoLogModel instance(OrderInfoLog orderInfoLog) {
        if (orderInfoLog == null || orderInfoLog.getId() <= 0) {
            return null;
        }
        OrderInfoLogModel orderInfoLogModel = new OrderInfoLogModel();
        BeanUtils.copyProperties(orderInfoLog, orderInfoLogModel);
        return orderInfoLogModel;
    }

    /**
     * model转换实体
     */
    public OrderInfoLog prototype() {
        OrderInfoLog orderInfoLog = new OrderInfoLog();
        BeanUtils.copyProperties(this, orderInfoLog);
        return orderInfoLog;
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

    public static void initOrderInfoModelByType(OrderInfoModel orderInfoModel, OrderInfoLog orderInfoLog) {
        switch (OrderInfoLogTypeEnum.getByState(orderInfoLog.getType())) {
            //支付时间
            case ORDER_INFO_TYPE_PAY_REQUEST:
                orderInfoModel.setPaymentTime(orderInfoLog.getAddTime());
                break;
            //付款时间
            case ORDER_INFO_TYPE_PAY:
                orderInfoModel.setPayTime(orderInfoLog.getAddTime());
                break;
            //退款处理
            case ORDER_INFO_TYPE_USER_REFUND:
                orderInfoModel.setRefundTime(orderInfoLog.getAddTime());
                orderInfoModel.setRefundExplanation(orderInfoLog.getContent());
                break;
            //发货时间
            case ORDER_INFO_TYPE_SEND:
                orderInfoModel.setSendTime(orderInfoLog.getAddTime());
                break;
            //关闭时间
            case ORDER_INFO_TYPE_CLOSE:
                orderInfoModel.setCloseTime(orderInfoLog.getAddTime());
                break;
            default:
                break;
        }
    }
}