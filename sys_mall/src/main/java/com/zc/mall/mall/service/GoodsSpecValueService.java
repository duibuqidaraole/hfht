package com.zc.mall.mall.service;

import com.zc.mall.mall.model.GoodsSpecValueModel;
import com.zc.sys.common.form.Result;

/**
 * 商品规格值
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
public interface GoodsSpecValueService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(GoodsSpecValueModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(GoodsSpecValueModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(GoodsSpecValueModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(GoodsSpecValueModel model);

}