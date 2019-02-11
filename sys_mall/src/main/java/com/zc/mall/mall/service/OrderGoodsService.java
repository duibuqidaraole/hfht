package com.zc.mall.mall.service;

import com.zc.mall.mall.model.OrderGoodsModel;
import com.zc.sys.common.form.Result;

/**
 * 订单商品
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
public interface OrderGoodsService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(OrderGoodsModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(OrderGoodsModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(OrderGoodsModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(OrderGoodsModel model);

}