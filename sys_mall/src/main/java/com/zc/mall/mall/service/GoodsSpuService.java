package com.zc.mall.mall.service;

import com.zc.mall.mall.model.GoodsSpuModel;
import com.zc.sys.common.form.Result;

/**
 * 商品SPU
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
public interface GoodsSpuService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(GoodsSpuModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(GoodsSpuModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(GoodsSpuModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(GoodsSpuModel model);

}