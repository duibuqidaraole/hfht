package com.zc.mall.promotion.dao.impl;

import com.zc.mall.promotion.dao.VipCouponsDao;
import com.zc.mall.promotion.entity.VipCoupons;
import com.zc.mall.promotion.model.VipCouponsModel;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.model.jpa.OrderFilter;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import com.zc.sys.common.model.jpa.SearchFilter;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Repository;

/**
 * 商品分类
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@Repository
public class VipCouponsDaoImpl extends BaseDaoImpl<VipCoupons> implements VipCouponsDao {
    /**
     * 根据prizeNo查询
     *
     * @param prizeNo
     * @return
     */
    @Override
    public VipCoupons findByPrizeNo(String prizeNo) {
        QueryParam queryParam = QueryParam.getInstance();
        queryParam.addParam("prizeNo", prizeNo);
        return super.findByCriteriaForUnique(queryParam);
    }

    @Override
    public PageDataList<VipCoupons> list(VipCouponsModel model) {
        QueryParam param = QueryParam.getInstance();
        if (StringUtil.isNotBlank(model.getSearchName())) {
            SearchFilter orFilter1 = new SearchFilter("name", SearchFilter.Operators.LIKE, model.getSearchName().trim());
            SearchFilter orFilter2 = new SearchFilter("content", SearchFilter.Operators.LIKE, model.getSearchName().trim());
            param.addOrFilter(orFilter1, orFilter2);
        }
        if (model.getState() != 0) {
            param.addParam("state", model.getState());
        }
        param.addOrder(OrderFilter.OrderType.ASC, "id");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }


}