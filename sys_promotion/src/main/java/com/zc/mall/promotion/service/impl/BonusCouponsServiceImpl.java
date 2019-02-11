package com.zc.mall.promotion.service.impl;

import com.zc.mall.core.manage.model.OperatorModel;
import com.zc.mall.core.user.dao.UserInfoDao;
import com.zc.mall.core.user.model.UserInfoModel;
import com.zc.mall.core.user.model.UserModel;
import com.zc.mall.promotion.dao.BonusCouponsDao;
import com.zc.mall.promotion.entity.BonusCoupons;
import com.zc.mall.promotion.model.BonusCouponsModel;
import com.zc.mall.promotion.service.BonusCouponsService;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 红包
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年12月20日
 */
@Service
public class BonusCouponsServiceImpl implements BonusCouponsService {

    @Resource
    private BonusCouponsDao bonusCouponsDao;
    @Resource
    private UserInfoDao userInfoDao;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result list(BonusCouponsModel model) {
        PageDataList<BonusCoupons> pageDataList = bonusCouponsDao.list(model);
        PageDataList<BonusCouponsModel> pageDataList_ = new PageDataList<BonusCouponsModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<BonusCouponsModel> list = new ArrayList<BonusCouponsModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (BonusCoupons bonusCoupons : pageDataList.getList()) {
                BonusCouponsModel model_ = BonusCouponsModel.instance(bonusCoupons);
                model_.setOperatorModel(OperatorModel.instance(bonusCoupons.getOperator()));
                model_.setBeUserModel(UserModel.instance(bonusCoupons.getBeUser()));
                if (bonusCoupons.getBeUser() != null) {
                    model_.setBeUserInfoModel(UserInfoModel.instance(bonusCoupons.getBeUser().getUserInfo()));
                }
                list.add(model_);
            }
        }
        pageDataList_.setList(list);
        return Result.success().setData(pageDataList_);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result add(BonusCouponsModel model) {
        model.validParam();
        model.init();
        bonusCouponsDao.save(model.prototype());
        return Result.success();
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result update(BonusCouponsModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误");
        }
        BonusCoupons bonusCoupons = bonusCouponsDao.find(model.getId());
        model.setParamsUpdate(bonusCoupons);
        bonusCouponsDao.update(bonusCoupons);
        return Result.success();
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result getById(BonusCouponsModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误");
        }
        BonusCoupons bonusCoupons = bonusCouponsDao.find(model.getId());
        BonusCouponsModel model_ = BonusCouponsModel.instance(bonusCoupons);
        model_.setOperatorModel(OperatorModel.instance(bonusCoupons.getOperator()));
        model_.setBeUserModel(UserModel.instance(bonusCoupons.getBeUser()));
        if (bonusCoupons.getBeUser() != null) {
            model_.setBeUserInfoModel(UserInfoModel.instance(bonusCoupons.getBeUser().getUserInfo()));
        }
        return Result.success().setData(model_);
    }

}