package com.zc.mall.mall.service;

import com.zc.mall.mall.model.OrderPayHistoryModel;
import com.zc.sys.common.form.Result;

/**
 * 支付记录
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
public interface OrderPayHistoryService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(OrderPayHistoryModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(OrderPayHistoryModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(OrderPayHistoryModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(OrderPayHistoryModel model);

}