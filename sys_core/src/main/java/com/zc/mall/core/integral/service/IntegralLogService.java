package com.zc.mall.core.integral.service;

import com.zc.mall.core.integral.model.IntegralLogModel;
import com.zc.sys.common.form.Result;

/**
 * 积分日志
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public interface IntegralLogService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(IntegralLogModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public void add(IntegralLogModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(IntegralLogModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(IntegralLogModel model);

    /**
     * 通过用户用户Id获取
     *
     * @param model
     * @return
     */
    public Result getByUserId(IntegralLogModel model);

}