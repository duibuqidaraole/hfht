package com.zc.mall.promotion.way.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.promotion.dao.PromotionPrizeConfigDao;
import com.zc.mall.promotion.dao.PromotionPrizeRecordDao;
import com.zc.mall.promotion.entity.PromotionPrizeConfig;
import com.zc.mall.promotion.entity.PromotionPrizeRecord;
import com.zc.mall.promotion.model.PromotionModel;
import com.zc.mall.promotion.model.PromotionPrizeRecordModel;
import com.zc.mall.promotion.way.PromotionWayAbstract;

/**
 * 活动推广奖励处理-完成订单
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月27日
 */
@Service("promotionWayOrderFinish")
public class PromotionWayOrderFinish extends PromotionWayAbstract {

    @Resource
    private PromotionPrizeRecordDao promotionPrizeRecordDao;
    @Resource
    private PromotionPrizeConfigDao promotionPrizeConfigDao;

    /**
     * 推广处理
     *
     * @param model promotionPrizeConfig
     */
    @Transactional
    @Override
    public void doPromotionPrize(PromotionModel model) {
        //todo
        model.setId(7);
        List<PromotionPrizeConfig> ppcList = promotionPrizeConfigDao.findByProperty("promotion.id", model.getId());
        //循环查找所有奖励配置
        for (PromotionPrizeConfig promotionPrizeConfig : ppcList) {
            if (promotionPrizeConfig.getState() == BaseConstant.INFO_STATE_YES) {
                PromotionPrizeRecordModel prizeRecordModel = new PromotionPrizeRecordModel();
                prizeRecordModel.initAdd(model, promotionPrizeConfig);//初始化信息
                PromotionPrizeRecord prizeRecord = prizeRecordModel.prototype();
                promotionPrizeRecordDao.save(prizeRecord);//保存记录
                prizeRecordModel.setPromotionModel(model);
                try {
                    this.trfGiveOut(prizeRecordModel);//发放
                } catch (Exception e) {
                    prizeRecord.setRemark(e.getMessage());
                    prizeRecord.setState(BaseConstant.BUSINESS_STATE_FAIL);
                }
            }
        }

    }

}
