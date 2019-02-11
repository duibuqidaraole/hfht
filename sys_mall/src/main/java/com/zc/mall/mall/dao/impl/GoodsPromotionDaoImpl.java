package com.zc.mall.mall.dao.impl;

import com.zc.mall.mall.dao.GoodsPromotionDao;
import com.zc.mall.mall.entity.GoodsPromotion;
import com.zc.mall.mall.model.GoodsPromotionModel;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import org.springframework.stereotype.Repository;

/**
 * 商品活动
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年12月24日
 */
@Repository
public class GoodsPromotionDaoImpl extends BaseDaoImpl<GoodsPromotion> implements GoodsPromotionDao {

    @Override
    public PageDataList<GoodsPromotion> list(GoodsPromotionModel model) {
        QueryParam queryParam = QueryParam.getInstance();
        if (model.getState()!=0){
            queryParam.addParam("state",model.getState());
        }
        if (model.getType()>0){
            queryParam.addParam("type",model.getType());
        }
        if (model.getPageSize()!=0){
            queryParam.addPage(model.getPageNo(), model.getPageSize());
        }
        return super.findPageList(queryParam);
    }
}