
package com.zc.mall.promotion.dao.impl;

import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.promotion.dao.PromotionDao;
import com.zc.mall.promotion.entity.Promotion;
import com.zc.mall.promotion.model.PromotionModel;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.model.jpa.OrderFilter.OrderType;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import com.zc.sys.common.model.jpa.SearchFilter;
import com.zc.sys.common.model.jpa.SearchFilter.Operators;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 活动推广
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Repository
public class PromotionDaoImpl extends BaseDaoImpl<Promotion> implements PromotionDao {

    /**
     * 活动推广列表
     *
     * @param model
     * @return
     */
    @Override
    public PageDataList<Promotion> list(PromotionModel model) {
        QueryParam param = QueryParam.getInstance();
        if (StringUtil.isNotBlank(model.getSearchName())) {
            SearchFilter orFilter1 = new SearchFilter("name", Operators.LIKE, model.getSearchName().trim());
            SearchFilter orFilter2 = new SearchFilter("nid", Operators.LIKE, model.getSearchName().trim());
            param.addOrFilter(orFilter1, orFilter2);
        } else {
            if (StringUtil.isNotBlank(model.getName())) {
                param.addParam("name", Operators.LIKE, model.getName());
            }

        }
        if (model.getState() != 0) {
            param.addParam("state", model.getState());
        }
        if (model.getRelationId() > 0) {
            param.addParam("beUser.id", model.getRelationId());
        }
        if (model.getRelationId() == -1) {
            param.addParam("beUser", Operators.EQ, 0);
        }
        if (model.getPromotionState() != 0) {
            assembleState(model.getPromotionState(), param);
        }
        param.addOrder(OrderType.DESC, "addTime");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }

    /**
     * 活动状况(promotionState) 1表示进行中   -2已过期  -1即将开始
     **/
    public void assembleState(int promotionState, QueryParam param) {
        switch (promotionState) {
            case BaseConstant.BUSINESS_STATE_FAIL:
                param.addParam("endTime", Operators.LT, DateUtil.valueOf(DateUtil.dateStr4(DateUtil.getNow())));
                break;
            case BaseConstant.BUSINESS_STATE_NO:
                param.addParam("startTime", Operators.GT, DateUtil.valueOf(DateUtil.dateStr4(DateUtil.getNow())));
                break;
            case BaseConstant.BUSINESS_STATE_YES:
                param.addParam("startTime", Operators.LTE, DateUtil.valueOf(DateUtil.dateStr4(DateUtil.getNow())));
                param.addParam("endTime", Operators.GTE, DateUtil.valueOf(DateUtil.dateStr4(DateUtil.getNow())));
                break;
            default:
                break;
        }

    }

    /**
     * 查询使用中的
     *
     * @param pModel
     * @return
     */
    @Override
    public List<Promotion> findUse(PromotionModel model) {
        QueryParam param = QueryParam.getInstance();
        param.addParam("state", BaseConstant.INFO_STATE_YES);
        param.addParam("way", model.getWay());
        param.addParam("endTime", Operators.GT, DateUtil.getNow());
        return super.findByCriteria(param);
    }

    /**
     * 活动过期处理
     *
     * @return
     */
    @Override
    public int doPromotionOverdue() {
        String jpql = "UPDATE Promotion set state=:updateState WHERE state=:nowState and endTime < NOW()";
        int count = updateByJpql(jpql, new String[]{"updateState", "nowState"},
                new Object[]{BaseConstant.BUSINESS_STATE_FAIL, BaseConstant.BUSINESS_STATE_YES});
        return count;
    }
}