package com.zc.mall.promotion.dao;

import com.zc.mall.promotion.entity.PromotionPrizeConfig;
import com.zc.mall.promotion.model.PromotionPrizeConfigModel;
import com.zc.sys.common.dao.BaseDao;
import com.zc.sys.common.model.jpa.PageDataList;

/**
 * 活动推广奖励配置
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public interface PromotionPrizeConfigDao extends BaseDao<PromotionPrizeConfig> {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    PageDataList<PromotionPrizeConfig> list(PromotionPrizeConfigModel model);

}