package com.zc.mall.core.manage.dao;

import com.zc.mall.core.manage.entity.OrderTask;
import com.zc.mall.core.manage.model.OrderTaskModel;
import com.zc.sys.common.dao.BaseDao;
import com.zc.sys.common.model.jpa.PageDataList;

/**
 * 模版配置
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public interface OrderTaskDao extends BaseDao<OrderTask> {
    /**
     * 列表
     *
     * @param model
     * @return
     */
    PageDataList<OrderTask> list(OrderTaskModel model);

    /**
     * 根据订单号查询
     *
     * @param orderNo
     * @return
     */
    OrderTask getByOrderNo(String orderNo);
}