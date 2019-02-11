package com.zc.mall.promotion.service;

import com.zc.mall.promotion.model.PromotionPrizeRecordModel;
import com.zc.sys.common.form.Result;

/**
 * 活动推广奖励记录
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public interface PromotionPrizeRecordService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(PromotionPrizeRecordModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(PromotionPrizeRecordModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(PromotionPrizeRecordModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(PromotionPrizeRecordModel model);

}