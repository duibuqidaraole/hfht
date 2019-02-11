package com.zc.mall.promotion.dao.impl;

import com.zc.mall.promotion.dao.PromotionPrizeRecordDao;
import com.zc.mall.promotion.entity.PromotionPrizeRecord;
import com.zc.mall.promotion.model.PromotionPrizeRecordModel;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.model.jpa.OrderFilter.OrderType;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import com.zc.sys.common.model.jpa.SearchFilter;
import com.zc.sys.common.model.jpa.SearchFilter.Operators;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Repository;

/**
 * 活动推广奖励记录
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Repository
public class PromotionPrizeRecordDaoImpl extends BaseDaoImpl<PromotionPrizeRecord> implements PromotionPrizeRecordDao {

    /**
     * 活动推广奖励记录列表
     *
     * @param model
     * @return
     */
    @Override
    public PageDataList<PromotionPrizeRecord> list(PromotionPrizeRecordModel model) {
        QueryParam param = QueryParam.getInstance();
        if (StringUtil.isNotBlank(model.getSearchName())) {
            SearchFilter orFilter1 = new SearchFilter("name", Operators.LIKE, model.getSearchName().trim());
            SearchFilter orFilter2 = new SearchFilter("nid", Operators.LIKE, model.getSearchName().trim());
            param.addOrFilter(orFilter1, orFilter2);
        }
        if (model.getPromotionPrizeConfig() != null && model.getPromotionPrizeConfig().getId() > 0) {
            param.addParam("promotionPrizeConfig.id", model.getPromotionPrizeConfig().getId());
        }
        if (model.getState() != 0) {
            param.addParam("state", model.getState());
        }
        if (model.getRelationId() > 0) {
            param.addParam("beUser.id", model.getRelationId());
        }
        param.addOrder(OrderType.ASC, "id");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }

}