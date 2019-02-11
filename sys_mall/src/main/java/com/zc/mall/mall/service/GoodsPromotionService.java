package com.zc.mall.mall.service;

import com.zc.mall.mall.model.GoodsPromotionModel;
import com.zc.sys.common.form.Result;

/**
 * 商品活动
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年12月24日
 */
public interface GoodsPromotionService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(GoodsPromotionModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(GoodsPromotionModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(GoodsPromotionModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(GoodsPromotionModel model);

    /**
     * 无分页列表
     * @param model
     * @return
     */
    Object checkList(GoodsPromotionModel model);

}