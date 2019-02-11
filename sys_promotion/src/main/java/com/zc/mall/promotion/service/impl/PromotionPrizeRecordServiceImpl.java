package com.zc.mall.promotion.service.impl;

import com.zc.mall.core.manage.model.OperatorModel;
import com.zc.mall.core.user.model.UserModel;
import com.zc.mall.promotion.dao.PromotionPrizeRecordDao;
import com.zc.mall.promotion.entity.PromotionPrizeRecord;
import com.zc.mall.promotion.model.PromotionModel;
import com.zc.mall.promotion.model.PromotionPrizeConfigModel;
import com.zc.mall.promotion.model.PromotionPrizeRecordModel;
import com.zc.mall.promotion.service.PromotionPrizeRecordService;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 活动推广奖励记录
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Service
public class PromotionPrizeRecordServiceImpl implements PromotionPrizeRecordService {

    @Resource
    private PromotionPrizeRecordDao promotionPrizeRecordDao;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result list(PromotionPrizeRecordModel model) {
        PageDataList<PromotionPrizeRecord> pageDataList = promotionPrizeRecordDao.list(model);
        PageDataList<PromotionPrizeRecordModel> pageDataList_ = new PageDataList<PromotionPrizeRecordModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<PromotionPrizeRecordModel> list = new ArrayList<PromotionPrizeRecordModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (PromotionPrizeRecord promotionPrizeRecord : pageDataList.getList()) {
                PromotionPrizeRecordModel model_ = PromotionPrizeRecordModel.instance(promotionPrizeRecord);
                model_.setOperatorModel(OperatorModel.instance(promotionPrizeRecord.getOperator()));
                model_.setPromtionModel(PromotionModel.instance(promotionPrizeRecord.getPromotion()));
                model_.setPromtionPrizeConfigModel(PromotionPrizeConfigModel.instance(promotionPrizeRecord.getPromotionPrizeConfig()));
                model_.setUserModel(UserModel.instance(promotionPrizeRecord.getUser()));
                if (promotionPrizeRecord.getOperator() != null) {
                    model.setOperatorModel(OperatorModel.instance(promotionPrizeRecord.getOperator()));
                }
                if (promotionPrizeRecord.getBeUser() != null) {
                    model_.setBeUserModel(UserModel.instance(promotionPrizeRecord.getBeUser()));
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
    public Result add(PromotionPrizeRecordModel model) {
        return Result.success();
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @Override
    public Result update(PromotionPrizeRecordModel model) {

        return null;
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result getById(PromotionPrizeRecordModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误");
        }
        PromotionPrizeRecord promotionPrizeRecord = promotionPrizeRecordDao.find(model.getId());
        PromotionPrizeRecordModel model_ = PromotionPrizeRecordModel.instance(promotionPrizeRecord);
        model_.setPromtionModel(PromotionModel.instance(promotionPrizeRecord.getPromotion()));
        model_.setPromtionPrizeConfigModel(PromotionPrizeConfigModel.instance(promotionPrizeRecord.getPromotionPrizeConfig()));
        model_.setUserModel(UserModel.instance(promotionPrizeRecord.getUser()));
        if (promotionPrizeRecord.getOperator() != null) {
            model.setOperatorModel(OperatorModel.instance(promotionPrizeRecord.getOperator()));
        }
        if (promotionPrizeRecord.getBeUser() != null) {
            model_.setBeUserModel(UserModel.instance(promotionPrizeRecord.getBeUser()));
        }
        return Result.success().setData(model_);
    }

}