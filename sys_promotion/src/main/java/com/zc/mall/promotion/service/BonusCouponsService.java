package com.zc.mall.promotion.service;

import com.zc.mall.promotion.model.BonusCouponsModel;
import com.zc.sys.common.form.Result;

/**
 * 红包
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年12月20日
 */
public interface BonusCouponsService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(BonusCouponsModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(BonusCouponsModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(BonusCouponsModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(BonusCouponsModel model);

}