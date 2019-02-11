package com.zc.mall.promotion.dao;

import com.zc.mall.promotion.entity.UserVipCoupons;
import com.zc.mall.promotion.model.UserVipCouponsModel;
import com.zc.mall.promotion.model.VipCouponsModel;
import com.zc.sys.common.dao.BaseDao;
import com.zc.sys.common.model.jpa.PageDataList;

import java.util.List;

/**
 * 用户vip会员卡
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月15日
 */
public interface UserVipCouponsDao extends BaseDao<UserVipCoupons> {
    /**
     * 通过用户id获取vip卡
     *
     * @param id
     * @return
     */
    List<UserVipCoupons> getByUserId(long id);

    PageDataList<UserVipCoupons> list(UserVipCouponsModel model);

    PageDataList<UserVipCoupons> checkInUsed(UserVipCouponsModel model);

    /**
     * 根据用户获取最高vip
     *
     * @param userId
     * @return
     */
    VipCouponsModel giveByUserId(long userId);

    /**
     * 获取vip折扣值
     *
     * @param id
     * @return
     */
    double getVipValueByUserId(long id);
}