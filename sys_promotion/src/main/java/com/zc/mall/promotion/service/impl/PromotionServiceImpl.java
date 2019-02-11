package com.zc.mall.promotion.service.impl;

import com.zc.mall.core.manage.model.OperatorModel;
import com.zc.mall.core.user.model.UserInfoModel;
import com.zc.mall.core.user.model.UserModel;
import com.zc.mall.promotion.constant.BasePromotionConstant.PromotionWay;
import com.zc.mall.promotion.dao.PromotionDao;
import com.zc.mall.promotion.entity.Promotion;
import com.zc.mall.promotion.model.PromotionModel;
import com.zc.mall.promotion.service.PromotionService;
import com.zc.mall.promotion.way.PromotionWayAbstract;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 活动推广
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Service("promotionService")
public class PromotionServiceImpl implements PromotionService {

    @Resource
    private PromotionDao promotionDao;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result list(PromotionModel model) {
        PageDataList<Promotion> pageDataList = promotionDao.list(model);
        PageDataList<PromotionModel> pageDataList_ = new PageDataList<PromotionModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<PromotionModel> list = new ArrayList<PromotionModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (Promotion promotion : pageDataList.getList()) {
                PromotionModel model_ = PromotionModel.instance(promotion);
                model_.setOperatorModel(OperatorModel.instance(promotion.getOperator()));
                if (promotion.getBeUser() != null && promotion.getBeUser().getId() > 0) {
                    model_.setBeUserModel(UserModel.instance(promotion.getBeUser()));
                    model_.setBeUserInfoModel(UserInfoModel.instance(promotion.getBeUser().getUserInfo()));
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
    public Result add(PromotionModel model) {
        model.validAdd();//校验添加参数
        model.initAdd();//初始化添加
        Promotion promotion = model.prototype();
        promotionDao.save(promotion);
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
    public Result update(PromotionModel model) {
        model.validUpdate();//校验修改参数
        Promotion promotion = promotionDao.find(model.getId());
        model.initUpdate(promotion);//初始化修改
        promotionDao.update(promotion);
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
    public Result getById(PromotionModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误");
        }
        Promotion promotion = promotionDao.find(model.getId());
        PromotionModel model_ = PromotionModel.instance(promotion);
        model_.setOperatorModel(OperatorModel.instance(promotion.getOperator()));
        model_.setBeUserModel(UserModel.instance(promotion.getBeUser()));
        if (promotion.getBeUser() != null) {
            model_.setBeUserInfoModel(UserInfoModel.instance(promotion.getBeUser().getUserInfo()));
        }
        return Result.success().setData(model_);
    }

    /**
     * 活动处理
     *
     * @param model
     */
    @Override
    @Transactional
    public void handlePromotion(PromotionModel model) {
        model.validParam();//参数校验
        model.initParam();//初始化参数
        List<Promotion> usePromotionList = promotionDao.findUse(model);
        if (usePromotionList.size() <= 0) {
            throw new BusinessException("活动未配置-1");
        }

        //循环查询到的活动list
        for (Promotion promotion : usePromotionList) {
            for (PromotionWay promotionWay : PromotionWay.values()) {
                if (promotionWay.getIndex() == promotion.getWay()) {
                    model.setId(promotion.getId());
                    PromotionWayAbstract promotionWayAbstract = PromotionWayAbstract.instances(promotionWay.getName());
                    promotionWayAbstract.executer(model);
                    break;
                }
            }
        }
    }

    /**
     * 活动过期处理
     *
     * @return
     */
    @Override
    public Result doPromotionOverdue() {
        promotionDao.doPromotionOverdue();
        return Result.success();
    }


}