package com.zc.mall.promotion.dao;

import com.zc.mall.promotion.entity.BonusCoupons;
import com.zc.mall.promotion.model.BonusCouponsModel;
import com.zc.sys.common.dao.BaseDao;
import com.zc.sys.common.model.jpa.PageDataList;

/**
 * 红包
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年12月20日
 */
public interface BonusCouponsDao extends BaseDao<BonusCoupons> {
    /**
     * 列表
     *
     * @param model
     * @return
     */
    PageDataList<BonusCoupons> list(BonusCouponsModel model);

    /**
     * 根据券No查询
     *
     * @param couponsNo
     * @return
     */
    BonusCoupons getByCouponsNo(String couponsNo);

    /**
     * 查询红包剩余量
     *
     * @param id
     * @return
     */
    int findBonusCouponsBalance(long id);

    BonusCoupons findbyCouponNo(String no);
}