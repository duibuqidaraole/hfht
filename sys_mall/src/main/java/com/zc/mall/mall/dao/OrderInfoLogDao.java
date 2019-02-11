package com.zc.mall.mall.dao;

import com.zc.mall.mall.entity.OrderInfoLog;
import com.zc.sys.common.dao.BaseDao;

import java.util.List;

/**
 * 订单日志
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年12月05日
 */
public interface OrderInfoLogDao extends BaseDao<OrderInfoLog> {

    /**
     * 查询日志
     *
     * @param type
     * @param orderId
     * @param operatorId
     * @param userId
     * @return
     */
    public List<OrderInfoLog> findList(int type, long orderId, int operatorId, int userId);

    /**
     * 日志查询
     *
     * @param type
     * @param orderId
     * @return
     */
    public OrderInfoLog findOrderInfoLog(int type, long orderId);

}