package com.zc.mall.mall.service;

import com.zc.mall.mall.entity.GoodsSku;
import com.zc.mall.mall.model.GoodsSkuModel;
import com.zc.sys.common.form.Result;

import java.util.List;

/**
 * 商品SKU
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
public interface GoodsSkuService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(GoodsSkuModel model);

    /**
     * 前台列表
     *
     * @param model
     * @return
     */
    public Result listForReception(GoodsSkuModel model);


    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(GoodsSkuModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(GoodsSkuModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(GoodsSkuModel model);

    /**
     * 获取热门商品
     *
     * @return
     */
    Object getHot();

    /**
     * 根据商品的规格值和spu获取sku
     *
     * @return
     */
    Object getChooseSku(GoodsSkuModel model);

    /**
     * 根据spuId获取商品列表
     *
     * @param goodsSpuId
     * @return
     */
    List<GoodsSku> findBySpuId(Long goodsSpuId);
}