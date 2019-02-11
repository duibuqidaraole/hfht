package com.zc.mall.mall.service;

import com.zc.mall.mall.model.GoodsPromotionRecordModel;
import com.zc.sys.common.form.Result;

/**
 * 商品活动记录
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年12月24日
 */
public interface GoodsPromotionRecordService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(GoodsPromotionRecordModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(GoodsPromotionRecordModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(GoodsPromotionRecordModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(GoodsPromotionRecordModel model);
    /**
     * 删除活动记录
     * @param model
     * @return
     */
    Object delete(GoodsPromotionRecordModel model);
}