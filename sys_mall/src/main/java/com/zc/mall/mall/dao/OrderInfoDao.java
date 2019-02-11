package com.zc.mall.mall.dao;

import com.zc.mall.mall.entity.OrderInfo;
import com.zc.mall.mall.model.OrderInfoModel;
import com.zc.sys.common.dao.BaseDao;
import com.zc.sys.common.model.jpa.PageDataList;

import java.util.List;

/**
 * 订单信息表
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
public interface OrderInfoDao extends BaseDao<OrderInfo> {

    /**
     * 查询退款处理超时
     *
     * @return
     */
    List<OrderInfo> listTimeOutRefundUser();

    /**
     * 查询收货超时
     *
     * @return
     */
    List<OrderInfo> listTimeOutReceive();

    PageDataList<OrderInfo> list(OrderInfoModel model);

    /**
     * 订单支付超时
     *
     * @return
     */
    List<OrderInfo> findTimeOutPay();

    /**
     * 未评价订单
     *
     * @return
     */
    List<OrderInfo> findWaitComment();

    /**
     * 根据用户状态获取订单数目
     *
     * @param id
     * @param state
     * @return
     */
    int getCountByState(long id, int state, int isComment);

    int getCountByState(long id, int state, int beforeState, int isComment);

    /**
     * 根据orderNo获取订单
     *
     * @param model
     * @return
     */
    OrderInfo getByOrderNo(OrderInfoModel model);
}