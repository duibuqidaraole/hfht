package com.zc.mall.mall.constant;

/**
 * 订单日志类型
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月19日
 */
public enum OrderInfoLogTypeEnum {
    /** ------------------------订单日志类型--------------------------START  **/
    /**
     * 添加
     **/
    ORDER_INFO_TYPE_ADD(1),
    /**
     * 支付请求
     **/
    ORDER_INFO_TYPE_PAY_REQUEST(2),
    /**
     * 支付
     **/
    ORDER_INFO_TYPE_PAY(3),
    /**
     * 发货
     **/
    ORDER_INFO_TYPE_SEND(4),
    /**
     * 收货
     **/
    ORDER_INFO_TYPE_RECEIVE(5),
    /**
     * 用户申请退款
     **/
    ORDER_INFO_TYPE_USER_REFUND(6),
    /**
     * 退款处理
     **/
    ORDER_INFO_TYPE_REFUND_DEAL(7),
    /**
     * 退款支付
     **/
    ORDER_INFO_TYPE_REFUND_PAY(8),
    /**
     * 关闭订单
     **/
    ORDER_INFO_TYPE_CLOSE(9),;
    /** ------------------------订单日志类型--------------------------END  **/
    /**
     * 订单状态
     **/
    int orderInfoLogType;

    private OrderInfoLogTypeEnum(int orderInfoLogType) {
        this.orderInfoLogType = orderInfoLogType;
    }

    /**
     * 获取【订单状态】
     **/
    public int getOrderInfoLogType() {
        return orderInfoLogType;
    }

    /**
     * 设置【订单状态】
     **/
    public void setOrderInfoLogType(int orderInfoLogType) {
        this.orderInfoLogType = orderInfoLogType;
    }

    public static OrderInfoLogTypeEnum getByState(int value) {
        for (OrderInfoLogTypeEnum orderInfoLogType : values()) {
            if (orderInfoLogType.orderInfoLogType == value) {
                return orderInfoLogType;
            }
        }
        return null;
    }
}
