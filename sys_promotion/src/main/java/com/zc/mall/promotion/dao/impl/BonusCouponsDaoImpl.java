
package com.zc.mall.promotion.dao.impl;

import com.zc.mall.promotion.dao.BonusCouponsDao;
import com.zc.mall.promotion.entity.BonusCoupons;
import com.zc.mall.promotion.model.BonusCouponsModel;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.model.jpa.OrderFilter.OrderType;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import com.zc.sys.common.model.jpa.SearchFilter;
import com.zc.sys.common.model.jpa.SearchFilter.Operators;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Repository;

/**
 * 红包
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年12月20日
 */
@Repository
public class BonusCouponsDaoImpl extends BaseDaoImpl<BonusCoupons> implements BonusCouponsDao {
    /**
     * 红包列表
     *
     * @author zp
     * @version 2.0.0.0
     * @since 2017年12月20日
     */
    @Override
    public PageDataList<BonusCoupons> list(BonusCouponsModel model) {
        QueryParam param = QueryParam.getInstance();
        if (StringUtil.isNotBlank(model.getSearchName())) {
            SearchFilter orFilter1 = new SearchFilter("name", Operators.LIKE, model.getSearchName().trim());
            SearchFilter orFilter2 = new SearchFilter("couponsNo", Operators.LIKE, model.getSearchName().trim());
            param.addOrFilter(orFilter1, orFilter2);
        }
        if (model.getState() != 0) {
            param.addParam("state", model.getState());
        }
        if (model.getType() != 0) {
            param.addParam("type", model.getType());
        }
        if (StringUtil.isNotBlank(model.getName())) {
            param.addParam("name", Operators.LIKE, model.getName().trim());
        }
        if (model.getRelationId() > 0) {
            param.addParam("beUser.id", model.getRelationId());
        }
        param.addOrder(OrderType.ASC, "id");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }

    @Override
    public BonusCoupons getByCouponsNo(String couponsNo) {
        return super.findObjByProperty("couponsNo", couponsNo);
    }

    @Override
    public int findBonusCouponsBalance(long id) {
        BonusCoupons bonusCoupons = super.find(id);
        return bonusCoupons.getQuota() - bonusCoupons.getUseQuota();
    }

    @Override
    public BonusCoupons findbyCouponNo(String no) {
        QueryParam param = QueryParam.getInstance();
        param.addParam("couponsNo", no);
        return super.findByCriteriaForUnique(param);
    }

}