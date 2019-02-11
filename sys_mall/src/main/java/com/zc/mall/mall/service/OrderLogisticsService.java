package com.zc.mall.mall.service;

import com.zc.mall.mall.model.OrderLogisticsModel;
import com.zc.sys.common.form.Result;

/**
 * 物流信息
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
public interface OrderLogisticsService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(OrderLogisticsModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(OrderLogisticsModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(OrderLogisticsModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(OrderLogisticsModel model);

}