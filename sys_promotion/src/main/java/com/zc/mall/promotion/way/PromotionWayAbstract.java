package com.zc.mall.promotion.way;

import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.promotion.constant.BasePromotionConstant;
import com.zc.mall.promotion.model.BonusCouponsRecordModel;
import com.zc.mall.promotion.model.PromotionModel;
import com.zc.mall.promotion.model.PromotionPrizeRecordModel;
import com.zc.mall.promotion.service.UserVipCouponsService;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.exception.NoRollBackException;
import com.zc.sys.common.util.log.LogUtil;
import org.springframework.transaction.annotation.Transactional;

/**
 * 活动推广奖励处理
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月27日
 */
public abstract class PromotionWayAbstract {

    /**
     * 实例化
     */
    public static PromotionWayAbstract instances(String way) {
        try {
            return BeanUtil.getBean(way);
        } catch (Exception e) {
            LogUtil.info(e.getMessage());
            throw new BusinessException("活动未配置-2");
        }
    }

    /**
     * 执行
     *
     * @param model
     */
    @Transactional
    public void executer(PromotionModel model) {
        this.doPromotionPrize(model);
    }

    /**
     * 推广处理
     *
     * @param model
     * @param model
     */
    protected abstract void doPromotionPrize(PromotionModel model);

    /**
     * 发放
     *
     * @param prizeRecord
     * @throws NoRollBackException
     */
    @Transactional
    protected void trfGiveOut(PromotionPrizeRecordModel prizeRecord) throws NoRollBackException {
        switch (prizeRecord.getPromotionPrizeConfig().getType()) {
            // 红包
            case BasePromotionConstant.PROMOTIONPRIZE_TYPE_BONUS_COUPONS:
                BonusCouponsRecordModel.giveOutRequest(prizeRecord);
                break;
            //会员vip
            case BasePromotionConstant.PROMOTIONPRIZE_TYPE_VIP:
                UserVipCouponsService userVipCouponsService = BeanUtil.getBean(UserVipCouponsService.class);
                userVipCouponsService.giveOutRequest(prizeRecord);
            default:
                LogUtil.info("奖励配置不存在--" + prizeRecord.getPromotionPrizeConfig().getType());
                break;
        }
    }

}
