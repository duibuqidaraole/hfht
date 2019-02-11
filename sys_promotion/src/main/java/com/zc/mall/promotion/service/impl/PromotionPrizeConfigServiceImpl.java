package com.zc.mall.promotion.service.impl;

import com.zc.mall.core.manage.model.OperatorModel;
import com.zc.mall.core.user.model.UserInfoModel;
import com.zc.mall.core.user.model.UserModel;
import com.zc.mall.promotion.dao.PromotionPrizeConfigDao;
import com.zc.mall.promotion.entity.PromotionPrizeConfig;
import com.zc.mall.promotion.model.PromotionModel;
import com.zc.mall.promotion.model.PromotionPrizeConfigModel;
import com.zc.mall.promotion.service.PromotionPrizeConfigService;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 活动推广奖励配置
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Service
public class PromotionPrizeConfigServiceImpl implements PromotionPrizeConfigService {

    @Resource
    private PromotionPrizeConfigDao promotionPrizeConfigDao;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result list(PromotionPrizeConfigModel model) {
        PageDataList<PromotionPrizeConfig> pageDataList = promotionPrizeConfigDao.list(model);
        PageDataList<PromotionPrizeConfigModel> pageDataList_ = new PageDataList<PromotionPrizeConfigModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<PromotionPrizeConfigModel> list = new ArrayList<PromotionPrizeConfigModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (PromotionPrizeConfig promotionPrizeConfig : pageDataList.getList()) {
                PromotionPrizeConfigModel model_ = PromotionPrizeConfigModel.instance(promotionPrizeConfig);
                model_.setPromtionModel(PromotionModel.instance(promotionPrizeConfig.getPromotion()));
                model_.setOperatorModel(OperatorModel.instance(promotionPrizeConfig.getOperator()));
                model_.setBeUserModel(UserModel.instance(promotionPrizeConfig.getBeUser()));
                if (promotionPrizeConfig.getBeUser() != null) {
                    model_.setBeUserInfoModel(UserInfoModel.instance(promotionPrizeConfig.getBeUser().getUserInfo()));
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
    public Result add(PromotionPrizeConfigModel model) {
        model.validAdd();// 校验添加参数
        model.initAdd();// 初始化添加
        PromotionPrizeConfig promotionPrizeConfig = model.prototype();
        promotionPrizeConfigDao.save(promotionPrizeConfig);
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
    public Result update(PromotionPrizeConfigModel model) {
        model.validUpdate();// 校验修改参数
        PromotionPrizeConfig promotionPrizeConfig = model.updateParams();// 初始化修改
        promotionPrizeConfigDao.update(promotionPrizeConfig);
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
    public Result getById(PromotionPrizeConfigModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误");
        }
        PromotionPrizeConfigModel model_ = null;
        PromotionPrizeConfig promotionPrizeConfig = promotionPrizeConfigDao.find(model.getId());
        model_ = PromotionPrizeConfigModel.instance(promotionPrizeConfig);
        model_.setPromtionModel(PromotionModel.instance(promotionPrizeConfig.getPromotion()));
        model_.setOperatorModel(OperatorModel.instance(promotionPrizeConfig.getOperator()));
        model_.setBeUserModel(UserModel.instance(promotionPrizeConfig.getBeUser()));
        if (promotionPrizeConfig.getBeUser() != null) {
            model_.setBeUserInfoModel(UserInfoModel.instance(promotionPrizeConfig.getBeUser().getUserInfo()));
        }
        return Result.success().setData(model_);
    }

}