package com.zc.mall.mall.service;

import com.zc.mall.mall.model.GoodsSpuSpecModel;
import com.zc.sys.common.form.Result;

/**
 * 商品SPU-规格
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
public interface GoodsSpuSpecService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(GoodsSpuSpecModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(GoodsSpuSpecModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(GoodsSpuSpecModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(GoodsSpuSpecModel model);

    /**
     * 未选择列表
     *
     * @param model
     * @return
     */
    Object unCheckList(GoodsSpuSpecModel model);
}