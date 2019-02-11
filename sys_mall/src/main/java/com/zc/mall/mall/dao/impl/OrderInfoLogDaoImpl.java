package com.zc.mall.mall.dao.impl;

import com.zc.mall.mall.dao.OrderInfoLogDao;
import com.zc.mall.mall.entity.OrderInfoLog;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.model.jpa.OrderFilter;
import com.zc.sys.common.model.jpa.QueryParam;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单日志
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年12月05日
 */
@Repository
public class OrderInfoLogDaoImpl extends BaseDaoImpl<OrderInfoLog> implements OrderInfoLogDao {

    /**
     * 查询日志
     *
     * @param type
     * @param orderId
     * @param operatorId
     * @param userId
     * @return
     */
    @Override
    public List<OrderInfoLog> findList(int type, long orderId, int operatorId, int userId) {
        QueryParam param = QueryParam.getInstance();
        if (type > 0) {
            param.addParam("type", type);
        }
        if (orderId > 0) {
            param.addParam("orderInfo.id", orderId);
        }
        if (operatorId > 0) {
            param.addParam("operator.id", operatorId);
        }
        if (userId > 0) {
            param.addParam("user.id", userId);
        }
        param.addOrder(OrderFilter.OrderType.DESC, "id");
        return findByCriteria(param);
    }

    /**
     * 日志查询
     *
     * @param type
     * @param orderId
     * @return
     */
    @Override
    public OrderInfoLog findOrderInfoLog(int type, long orderId) {
        List<OrderInfoLog> list = this.findList(type, orderId, 0, 0);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

}