package com.zc.mall.mall.dao.impl;

import com.zc.mall.mall.dao.GoodsPromotionRecordDao;
import com.zc.mall.mall.entity.GoodsPromotionRecord;
import com.zc.mall.mall.model.GoodsPromotionRecordModel;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.model.jpa.OrderFilter;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import com.zc.sys.common.model.jpa.SearchFilter;
import com.zc.sys.common.util.date.DateUtil;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品活动记录
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年12月24日
 */
@Repository
public class GoodsPromotionRecordDaoImpl extends BaseDaoImpl<GoodsPromotionRecord> implements GoodsPromotionRecordDao {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    public PageDataList<GoodsPromotionRecord> list(GoodsPromotionRecordModel model) {
        QueryParam queryParam = QueryParam.getInstance();
        if (model.getGoodsSku()!=null){
            if (model.getGoodsSku().getId()>0){
                queryParam.addParam("goodsSku.id",model.getGoodsSku().getId());
            }
        }
        if (model.getGoodsPromotion()!=null&&model.getGoodsPromotion().getId()>0){
            queryParam.addParam("promotion.id",model.getGoodsPromotion().getId());
        }
        queryParam.addPage(model.getPageNo(),model.getPageSize());
        queryParam.addOrder(OrderFilter.OrderType.DESC,"id");
        return super.findPageList(queryParam);
    }

    /**
     * 根据商品获取活动
     *
     * @param id
     * @return
     */
    @Override
    public GoodsPromotionRecord findBySku(long id) {
        QueryParam queryParam = QueryParam.getInstance();
        queryParam.addParam("goodsSku.id",id);
        queryParam.addParam("endTime", SearchFilter.Operators.GT, DateUtil.getNow());
        List<GoodsPromotionRecord> goodsPromotionRecordList = super.findByCriteria(queryParam);
        if (goodsPromotionRecordList==null||goodsPromotionRecordList.size()<=0){
            return null;
        }

        return goodsPromotionRecordList.get(0);
    }
}