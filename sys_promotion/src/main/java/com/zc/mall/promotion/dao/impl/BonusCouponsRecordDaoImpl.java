
package com.zc.mall.promotion.dao.impl;

import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.promotion.constant.BasePromotionConstant;
import com.zc.mall.promotion.dao.BonusCouponsRecordDao;
import com.zc.mall.promotion.entity.BonusCouponsRecord;
import com.zc.mall.promotion.model.BonusCouponsRecordModel;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.model.jpa.OrderFilter.OrderType;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import com.zc.sys.common.model.jpa.SearchFilter;
import com.zc.sys.common.model.jpa.SearchFilter.Operators;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * 红包发放记录
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年12月20日
 */
@Repository
public class BonusCouponsRecordDaoImpl extends BaseDaoImpl<BonusCouponsRecord> implements BonusCouponsRecordDao {
    /**
     * 红包发放记录列表
     *
     * @author zp
     * @version 2.0.0.0
     * @since 2017年12月20日
     */
    @Override
    public PageDataList<BonusCouponsRecord> list(BonusCouponsRecordModel model) {
        QueryParam param = QueryParam.getInstance();
        if (StringUtil.isNotBlank(model.getSearchName())) {
            SearchFilter orFilter1 = new SearchFilter("user.realName", Operators.EQ, model.getSearchName());
            SearchFilter orFilter2 = new SearchFilter("user.mobile", Operators.EQ, model.getSearchName());
            SearchFilter orFilter3 = new SearchFilter("bonusCoupons.name", Operators.EQ, model.getSearchName());
            param.addOrFilter(orFilter1, orFilter2, orFilter3);
        }
        if (model.getUseSingleInvestAmount() > 0) {
            param.addParam("bonusCoupons.quota", Operators.LTE, model.getUseSingleInvestAmount());
        }

        if (model.getUser() != null) {
            if (model.getUser().getId() > 0) {
                param.addParam("user.id", model.getUser().getId());
            }
            if (StringUtil.isNotBlank(model.getUser().getOpenId())) {
                param.addParam("user.openId", model.getUser().getOpenId());
            }
        }
        //未使用
        if (model.getState() == BaseConstant.BUSINESS_STATE_NO) {
            param.addParam("state", model.getState());
            param.addParam("endTime", Operators.GTE, DateUtil.getNow());
        }
        //已使用
        if (model.getState() == BaseConstant.BUSINESS_STATE_YES) {
            param.addParam("state", model.getState());
        }
        //已过期
        if (model.getState() == BaseConstant.BUSINESS_STATE_FAIL) {
            param.addParam("state", BaseConstant.BUSINESS_STATE_NO);
            param.addParam("endTime", Operators.LT, DateUtil.getNow());
        }
        if (model.getBonusCoupons() != null && model.getBonusCoupons().getType() != 0) {
            param.addParam("bonusCoupons.type", model.getBonusCoupons().getType());
        }
        if (model.getBonusCoupons() != null && model.getBonusCoupons().getId() > 0) {
            param.addParam("bonusCoupons.id", model.getBonusCoupons().getId());
        }

        if (StringUtil.isNotBlank(model.getBounsIds())) {
            param.addOrFilter("bonusCoupons.id", model.getBounsIds().split(","));
        }
        if (model.getUseType() != 0) {
            param.addParam("useType", model.getUseType());
        }
        if (model.getRelationId() > 0) {
            param.addParam("beUser.id", model.getRelationId());
        }
        if (!StringUtil.isBlank(model.getAddTimeStartTime()) && !StringUtil.isBlank(model.getAddTimeEndTime())) {
            //查询今日
            if (DateUtil.getDayStartTime(DateUtil.getTime(DateUtil.valueOf(model.getAddTimeStartTime()))).equals(DateUtil.getDayStartTime(DateUtil.getTime(DateUtil.valueOf(model.getAddTimeEndTime()))))) {
                param.addParam("addTime", Operators.GT, DateUtil.getDayStartTime(DateUtil.getTime(DateUtil.valueOf(model.getAddTimeStartTime()))));
                param.addParam("addTime", Operators.LTE, DateUtil.getDayEndTime(DateUtil.getTime(DateUtil.valueOf(model.getAddTimeEndTime()))));
            } else {
                param.addParam("addTime", Operators.GT, DateUtil.valueOf(model.getAddTimeStartTime()));
                param.addParam("addTime", Operators.LTE, DateUtil.valueOf(model.getAddTimeEndTime()));
            }

        }
        if (model.getLessAmount() != null && model.getLessAmount() > 0) {
            param.addParam("bonusCoupons.useSingleInvestAmount", Operators.LTE, model.getLessAmount());
        }
        param.addOrder(OrderType.DESC, "id");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }

    /**
     * 根据model查询所有
     *
     * @param model
     * @return
     */
    @Override
    public List<BonusCouponsRecord> findByCriteria(BonusCouponsRecordModel model) {
        QueryParam param = QueryParam.getInstance();
        if (StringUtil.isNotBlank(model.getSearchName())) {
            SearchFilter orFilter1 = new SearchFilter("name", Operators.LIKE, model.getSearchName().trim());
            SearchFilter orFilter2 = new SearchFilter("nid", Operators.LIKE, model.getSearchName().trim());
            param.addOrFilter(orFilter1, orFilter2);
        } else {
            if (model.getId() > 0) {
                param.addParam("id", model.getId());
            }
            if (model.getUser() != null && model.getUser().getId() > 0) {
                param.addParam("user.id", model.getUser().getId());
            }
        }
        if (model.getUseType() > 0) {
            param.addParam("useType", model.getUseType());
        }
        if (!StringUtil.isBlank(model.getUseId())) {
            param.addParam("useId", model.getUseId());
        }
        if (model.getState() != 0) {
            param.addParam("state", model.getState());
        }
        return super.findByCriteria(param);
    }

    /**
     * 查询使用的红包
     *
     * @param useType 使用类型
     * @param useId   关联id
     * @return
     */
    @Override
    public BonusCouponsRecord getByUse(int useType, String useId, int state) {
        List<BonusCouponsRecord> list = this.findByCriteria(new BonusCouponsRecordModel(useType, useId, state));
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 查询使用的红包金额
     *
     * @param useType
     * @param useId
     * @return
     */
    @Override
    public double getByUseAmount(int useType, String useId, int state) {
        BonusCouponsRecord bonusCouponsRecord = this.getByUse(useType, useId, state);
        if (bonusCouponsRecord == null) {
            return 0;
        }
        return bonusCouponsRecord.getBonusCoupons().getAmount();
    }

    /**
     * 通过使用id查询红包金额
     *
     * @return
     */
    @Override
    public double getByUseAmount(String useId) {
        return this.getByUseAmount(BasePromotionConstant.BONUS_COUPONS_TYPE_CASH, useId, BasePromotionConstant.BONUS_COUPONS_TYPE_DEDUCT);
    }

    /**
     * 校验用户是否发放过新人礼包
     *
     * @param user
     */
    @Override
    public void checkUserNewcomer(User user,String bonusIds) {
        QueryParam queryParam = QueryParam.getInstance();
        queryParam.addParam("user.id", user.getId());
        List<BonusCouponsRecord> bonusCouponsRecords = super.findByCriteria(queryParam);
        for (BonusCouponsRecord bonusCouponsRecord : bonusCouponsRecords) {
            if (Arrays.asList(bonusIds.split(",")).contains(bonusCouponsRecord.getBonusCoupons().getCouponsNo())){
                throw new BusinessException("用户已发送新人奖励");
            }
        }
    }

    /**
     * 红包记录过期处理
     *
     * @return
     */
    @Override
    public int doBonusRecordOverdue() {
        String jpql = "UPDATE BonusCouponsRecord set state=:updateState WHERE state=:nowState and endTime < NOW()";
        int count = updateByJpql(jpql, new String[]{"updateState", "nowState"}, new Object[]{BaseConstant.BUSINESS_STATE_FAIL, BaseConstant.BUSINESS_STATE_NO});
        return count;
    }

}