package com.zc.mall.core.credit.dao;

import com.zc.mall.core.credit.entity.CreditScore;
import com.zc.sys.common.dao.BaseDao;
import com.zc.sys.common.exception.VersionControlException;

/**
 * 信用分账户
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public interface CreditScoreDao extends BaseDao<CreditScore> {

    /**
     * 更新积分信息
     *
     * @param id
     * @param balanceScore
     * @param sysScore
     * @param version
     * @throws VersionControlException
     */
    void updateCreditScore(long id, double balanceScore, double sysScore, int version) throws VersionControlException;

    /**
     * 通过用户查询积分账户
     *
     * @param userId
     * @return
     */
    CreditScore findByUser(long userId);

}