package com.zc.mall.promotion.dao.impl;

import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.promotion.dao.UserVipCouponsDao;
import com.zc.mall.promotion.entity.UserVipCoupons;
import com.zc.mall.promotion.entity.VipCoupons;
import com.zc.mall.promotion.model.UserVipCouponsModel;
import com.zc.mall.promotion.model.VipCouponsModel;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.model.jpa.OrderFilter;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import com.zc.sys.common.model.jpa.SearchFilter;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户vip会员卡
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月15日
 */
@Repository
public class UserVipCouponsDaoImpl extends BaseDaoImpl<UserVipCoupons> implements UserVipCouponsDao {

    /**
     * 通过用户id获取vip卡
     *
     * @param id
     * @return
     */
    @Override
    public List<UserVipCoupons> getByUserId(long id) {
        QueryParam queryParam = QueryParam.getInstance();
        queryParam.addParam("user.id", id);
        return super.findByCriteria(queryParam);
    }

    @Override
    public PageDataList<UserVipCoupons> list(UserVipCouponsModel model) {
        QueryParam queryParam = QueryParam.getInstance();
        if (StringUtil.isNotBlank(model.getSearchName())) {
            SearchFilter orFilter1 = new SearchFilter("user.userName", SearchFilter.Operators.LIKE, model.getSearchName().trim());
            SearchFilter orFilter2 = new SearchFilter("vipCoupons.name", SearchFilter.Operators.LIKE, model.getSearchName().trim());
            queryParam.addOrFilter(orFilter1, orFilter2);
        }
        if (model.getUser() != null && model.getUser().getId() > 0) {
            queryParam.addParam("user.id", model.getUser().getId());
        }
        if (model.getVipCoupons() != null && model.getVipCoupons().getId() > 0) {
            queryParam.addParam("vipCoupons.id", model.getVipCoupons().getId());
        }
        queryParam.addOrder(OrderFilter.OrderType.DESC, "id");
        queryParam.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(queryParam);
    }

    /**
     * 校验改用户是否拥有此vip
     *
     * @param model
     * @return
     */
    @Override
    public PageDataList<UserVipCoupons> checkInUsed(UserVipCouponsModel model) {
        QueryParam queryParam = QueryParam.getInstance();
        if (model.getUser() != null && model.getUser().getId() >= 0) {
            queryParam.addParam("user.id", model.getUser().getId());
        }
        if (model.getVipCoupons() != null && model.getVipCoupons().getId() > 0) {
            queryParam.addParam("vipCoupons.id", model.getVipCoupons().getId());
        }
        queryParam.addParam("state", BaseConstant.BUSINESS_STATE_YES);
        return super.findPageList(queryParam);
    }

    /**
     * 根据用户获取最高vip
     *
     * @param userId
     * @return
     */
    @Override
    @Transactional
    public VipCouponsModel giveByUserId(long userId) {
        QueryParam queryParam = QueryParam.getInstance();
        queryParam.addParam("user.id", userId);
        queryParam.addParam("state", BaseConstant.BUSINESS_STATE_YES);
        List<UserVipCoupons> userVipCouponsList = super.findByCriteria(queryParam);
        if (userVipCouponsList == null || userVipCouponsList.size() <= 0) {
            return null;
        }
        VipCoupons vipCoupons = null;
        for (UserVipCoupons userVipCoupons : userVipCouponsList) {
            if (vipCoupons == null || userVipCoupons.getVipCoupons().getGrade() > vipCoupons.getGrade()) {
                vipCoupons = userVipCoupons.getVipCoupons();
            }
        }
        return VipCouponsModel.instance(vipCoupons);
    }

    /**
     * 获取vip折扣值
     *
     * @param id
     * @return
     */
    @Override
    public double getVipValueByUserId(long id) {
        QueryParam queryParam = QueryParam.getInstance();
        queryParam.addParam("user.id", id);
        queryParam.addParam("state", BaseConstant.BUSINESS_STATE_YES);
        List<UserVipCoupons> userVipCouponsList = super.findByCriteria(queryParam);
        if (userVipCouponsList == null || userVipCouponsList.size() <= 0) {
            return 0;
        }
        VipCoupons vipCoupons = null;
        for (UserVipCoupons userVipCoupons : userVipCouponsList) {
            if (vipCoupons == null || userVipCoupons.getVipCoupons().getGrade() > vipCoupons.getGrade()) {
                vipCoupons = userVipCoupons.getVipCoupons();
            }
        }
        return vipCoupons.getValue();
    }
}