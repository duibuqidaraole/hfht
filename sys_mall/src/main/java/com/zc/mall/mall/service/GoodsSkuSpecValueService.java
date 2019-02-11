package com.zc.mall.mall.service;

import com.zc.mall.mall.model.GoodsSkuSpecValueModel;
import com.zc.sys.common.form.Result;

/**
 * 商品sku-规格值
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
public interface GoodsSkuSpecValueService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(GoodsSkuSpecValueModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(GoodsSkuSpecValueModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(GoodsSkuSpecValueModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(GoodsSkuSpecValueModel model);

    /**
     * 根据spu获取
     *
     * @param model
     * @return
     */
    Object getBySpuId(GoodsSkuSpecValueModel model);

    /**
     * 获取有库存的商品规格
     *
     * @param model
     * @return
     */
    Object getOnSalesList(GoodsSkuSpecValueModel model);

    /**
     * 一步添加商品
     *
     * @param model
     * @return
     */
    Object addSkuAllIn(GoodsSkuSpecValueModel model);

    /**
     * 一步修改商品
     *
     * @param model
     * @return
     */
    Object updateSkuAllIn(GoodsSkuSpecValueModel model);
}