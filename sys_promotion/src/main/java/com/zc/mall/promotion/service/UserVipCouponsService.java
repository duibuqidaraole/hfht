package com.zc.mall.promotion.service;

import com.zc.mall.core.user.model.UserModel;
import com.zc.mall.promotion.entity.PromotionPrizeRecord;
import com.zc.mall.promotion.entity.UserVipCoupons;
import com.zc.mall.promotion.model.UserVipCouponsModel;
import com.zc.mall.promotion.model.VipCouponsModel;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.form.Result;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户vip会员卡
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月15日
 */
public interface UserVipCouponsService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(UserVipCouponsModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(UserVipCouponsModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(UserVipCouponsModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(UserVipCouponsModel model);

    /**
     * 通过用户id获取vip卡
     *
     * @param id
     * @return
     */
    List<UserVipCoupons> getByUserId(long id);

    /**
     * 发放vip
     *
     * @param model
     * @return
     */
    Result giveOutRequest(UserVipCouponsModel model);

    /**
     * 发放vip
     *
     * @param prizeRecord
     * @return
     */
    Result giveOutRequest(PromotionPrizeRecord prizeRecord);

    @Transactional(noRollbackFor = BusinessException.class)
    Result giveOut(UserVipCouponsModel model);

    /**
     * 根据用户获取最高的vip
     *
     * @param userId
     * @return
     */
    VipCouponsModel giveByUserId(long userId);

    /**
     * 添加用户等级信息
     *
     * @param list
     */
    void setUserGrade(List<UserModel> list);

    /**
     * 添加用户等级信息
     *
     * @param model
     */
    void setUserGrade(UserModel model);

    /**
     * 红包支付
     *
     * @param model
     * @return
     */
    Object giveOutPay(UserVipCouponsModel model);
}