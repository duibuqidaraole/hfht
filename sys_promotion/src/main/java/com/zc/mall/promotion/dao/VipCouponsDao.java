package com.zc.mall.promotion.dao;

import com.zc.mall.promotion.entity.VipCoupons;
import com.zc.mall.promotion.model.VipCouponsModel;
import com.zc.sys.common.dao.BaseDao;
import com.zc.sys.common.model.jpa.PageDataList;

/**
 * 商品分类
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
public interface VipCouponsDao extends BaseDao<VipCoupons> {
    /**
     * 根据prizeNo查询
     *
     * @param prizeNo
     * @return
     */
    VipCoupons findByPrizeNo(String prizeNo);

    PageDataList<VipCoupons> list(VipCouponsModel model);
}