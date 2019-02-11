package com.zc.mall.mall.constant;

/**
 * 订单状态
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月19日
 */
public enum OrderInfoStateEnum {
    /** ------------------------订单流程状态--------------------------START  **/
    /**
     * 待支付
     **/
    ORDER_INFO_STATE_PAY(1),
    /**
     * 待发货
     **/
    ORDER_INFO_STATE_SEND(5),
    /**
     * 待收货
     **/
    ORDER_INFO_STATE_RECEIVE(10),
    /**
     * 待退款处理
     **/
    ORDER_INFO_STATE_REFUND(11),
    /**
     * 待退款支付
     **/
    ORDER_INFO_STATE_REFUND_PAY(12),
    /**
     * 确认收货/完成交易
     **/
    ORDER_INFO_STATE_COMPLETE(15),
    /**
     * 退款完成
     **/
    ORDER_INFO_STATE_REFUND_COMPLETE(16),
    /**
     * 关闭订单
     **/
    ORDER_INFO_STATE_CLOSE(-15),
    /** ------------------------订单流程状态--------------------------END  **/
    ;

    /**
     * 订单状态
     **/
    int orderInfoState;

    private OrderInfoStateEnum(int orderInfoState) {
        this.orderInfoState = orderInfoState;
    }

    /**
     * 获取【订单状态】
     **/
    public int getOrderInfoState() {
        return orderInfoState;
    }

    /**
     * 设置【订单状态】
     **/
    public void setOrderInfoState(int orderInfoState) {
        this.orderInfoState = orderInfoState;
    }

    public static OrderInfoStateEnum getByState(int value) {
        for (OrderInfoStateEnum orderInfoState : values()) {
            if (orderInfoState.orderInfoState == value) {
                return orderInfoState;
            }
        }
        return null;
    }
}
