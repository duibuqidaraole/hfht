package com.zc.mall.mall.service;

import com.zc.mall.mall.model.GoodsSpecModel;
import com.zc.sys.common.form.Result;

/**
 * 商品规格
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
public interface GoodsSpecService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(GoodsSpecModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(GoodsSpecModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(GoodsSpecModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(GoodsSpecModel model);

}