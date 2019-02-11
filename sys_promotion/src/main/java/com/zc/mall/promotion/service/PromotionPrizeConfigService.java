package com.zc.mall.promotion.service;

import com.zc.mall.promotion.model.PromotionPrizeConfigModel;
import com.zc.sys.common.form.Result;

/**
 * 活动推广奖励配置
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public interface PromotionPrizeConfigService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(PromotionPrizeConfigModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(PromotionPrizeConfigModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(PromotionPrizeConfigModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(PromotionPrizeConfigModel model);

}