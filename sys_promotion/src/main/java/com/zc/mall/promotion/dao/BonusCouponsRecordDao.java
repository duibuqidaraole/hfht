package com.zc.mall.promotion.dao;

import com.zc.mall.core.user.entity.User;
import com.zc.mall.promotion.entity.BonusCouponsRecord;
import com.zc.mall.promotion.model.BonusCouponsRecordModel;
import com.zc.sys.common.dao.BaseDao;
import com.zc.sys.common.model.jpa.PageDataList;

import java.util.List;

/**
 * 红包发放记录
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年12月20日
 */
public interface BonusCouponsRecordDao extends BaseDao<BonusCouponsRecord> {
    /**
     * 列表
     *
     * @param model
     * @return
     */
    PageDataList<BonusCouponsRecord> list(BonusCouponsRecordModel model);

    /**
     * 根据model查询所有
     *
     * @param bonusCouponsRecord
     * @return
     */
    List<BonusCouponsRecord> findByCriteria(BonusCouponsRecordModel model);

    /**
     * 查询使用的红包
     *
     * @param useType
     * @param useId
     * @return
     */
    BonusCouponsRecord getByUse(int useType, String useId, int state);

    /**
     * 查询使用的红包金额
     *
     * @param useType
     * @param useId
     * @return
     */
    double getByUseAmount(int useType, String useId, int state);

    /**
     * 红包记录过期处理
     *
     * @return
     */
    int doBonusRecordOverdue();

    /**
     * 通过使用id查询红包金额
     *
     * @return
     */
    double getByUseAmount(String useId);

    /**
     * 校验用户是否发放过新人礼包
     *
     * @param user
     */
    void checkUserNewcomer(User user,String bonusIds);
}