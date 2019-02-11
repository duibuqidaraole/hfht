package com.zc.mall.mall.service;

import com.zc.mall.mall.model.OrderCartModel;
import com.zc.sys.common.form.Result;

/**
 * 购物车
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
public interface OrderCartService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(OrderCartModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(OrderCartModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(OrderCartModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(OrderCartModel model);

}