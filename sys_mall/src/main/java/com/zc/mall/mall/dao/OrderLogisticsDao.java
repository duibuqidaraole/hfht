package com.zc.mall.mall.dao;

import com.zc.mall.mall.entity.OrderLogistics;
import com.zc.sys.common.dao.BaseDao;

/**
 * 物流信息
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
public interface OrderLogisticsDao extends BaseDao<OrderLogistics> {

    /**
     * 根据订单查询物流信息
     *
     * @param orderInfoId
     * @return
     */
    OrderLogistics findByOrderInfoId(long orderInfoId);

}