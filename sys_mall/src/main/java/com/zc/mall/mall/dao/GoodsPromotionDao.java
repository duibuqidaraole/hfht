package com.zc.mall.mall.dao;

import com.zc.mall.mall.entity.GoodsPromotion;
import com.zc.mall.mall.model.GoodsPromotionModel;
import com.zc.sys.common.dao.BaseDao;
import com.zc.sys.common.model.jpa.PageDataList;

/**
 * 商品活动
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年12月24日
 */
public interface GoodsPromotionDao extends BaseDao<GoodsPromotion> {

    PageDataList<GoodsPromotion> list(GoodsPromotionModel model);
}