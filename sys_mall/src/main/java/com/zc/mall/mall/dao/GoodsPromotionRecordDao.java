package com.zc.mall.mall.dao;

import com.zc.mall.mall.entity.GoodsPromotionRecord;
import com.zc.mall.mall.model.GoodsPromotionRecordModel;
import com.zc.sys.common.dao.BaseDao;
import com.zc.sys.common.model.jpa.PageDataList;

/**
 * 商品活动记录
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年12月24日
 */
public interface GoodsPromotionRecordDao extends BaseDao<GoodsPromotionRecord> {

    /**
     * 列表
     * @param model
     * @return
     */
    PageDataList<GoodsPromotionRecord> list(GoodsPromotionRecordModel model);

    /**
     * 根据商品获取活动
     * @param id
     * @return
     */
    GoodsPromotionRecord findBySku(long id);
}