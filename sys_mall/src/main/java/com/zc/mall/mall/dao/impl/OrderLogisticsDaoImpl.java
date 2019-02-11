package com.zc.mall.mall.dao.impl;

import com.zc.mall.mall.dao.OrderLogisticsDao;
import com.zc.mall.mall.entity.OrderLogistics;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * 物流信息
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@Repository
public class OrderLogisticsDaoImpl extends BaseDaoImpl<OrderLogistics> implements OrderLogisticsDao {

    @Override
    public OrderLogistics findByOrderInfoId(long orderInfoId) {
        return findObjByProperty("orderInfo.id", orderInfoId);
    }

}