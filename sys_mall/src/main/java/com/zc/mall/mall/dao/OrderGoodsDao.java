package com.zc.mall.mall.dao;

import com.zc.mall.mall.entity.OrderGoods;
import com.zc.mall.mall.model.OrderGoodsModel;
import com.zc.sys.common.dao.BaseDao;
import com.zc.sys.common.model.jpa.PageDataList;

import java.util.List;

/**
 * 订单商品
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
public interface OrderGoodsDao extends BaseDao<OrderGoods> {

    /**
     * 查询订单包含商品
     *
     * @param orderId
     * @return
     */
    List<OrderGoods> listByOrder(long orderId);

    /**
     * 查询
     *
     * @param model
     * @return
     */
    PageDataList<OrderGoods> list(OrderGoodsModel model);
}