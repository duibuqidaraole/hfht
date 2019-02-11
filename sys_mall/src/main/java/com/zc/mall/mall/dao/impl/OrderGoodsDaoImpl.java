package com.zc.mall.mall.dao.impl;

import com.zc.mall.mall.dao.OrderGoodsDao;
import com.zc.mall.mall.entity.OrderGoods;
import com.zc.mall.mall.model.OrderGoodsModel;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.model.jpa.OrderFilter;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单商品
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@Repository
public class OrderGoodsDaoImpl extends BaseDaoImpl<OrderGoods> implements OrderGoodsDao {

    /**
     * 查询订单包含商品
     *
     * @param orderId
     * @return
     */
    @Override
    public List<OrderGoods> listByOrder(long orderId) {
        return findByProperty("orderInfo.id", orderId);
    }

    /**
     * 查询
     *
     * @param model
     * @return
     */
    @Override
    public PageDataList<OrderGoods> list(OrderGoodsModel model) {
        QueryParam param = QueryParam.getInstance();
        if (model == null) {
            return super.findPageList(param);
        }
        if (model.getGoodsSku() != null && model.getGoodsSku().getId() > 0) {
            param.addParam("goodsSku.id", model.getGoodsSku().getId());
        }
        if (model.getOrderInfo() != null && model.getOrderInfo().getId() > 0) {
            param.addParam("orderInfo.id", model.getOrderInfo().getId());
        }
        param.addOrder(OrderFilter.OrderType.ASC, "id");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }

}