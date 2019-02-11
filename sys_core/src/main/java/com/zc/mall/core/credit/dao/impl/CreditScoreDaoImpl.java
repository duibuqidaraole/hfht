package com.zc.mall.core.credit.dao.impl;

import com.zc.mall.core.credit.dao.CreditScoreDao;
import com.zc.mall.core.credit.entity.CreditScore;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.exception.VersionControlException;
import org.springframework.stereotype.Repository;

/**
 * 信用分账户
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Repository
public class CreditScoreDaoImpl extends BaseDaoImpl<CreditScore> implements CreditScoreDao {

    /**
     * 更新积分信息
     *
     * @param id
     * @param balanceScore
     * @param sysScore
     * @param version
     * @throws VersionControlException
     */
    @Override
    public void updateCreditScore(long id, double balanceScore, double sysScore, int version) throws VersionControlException {
        if (balanceScore == 0 && sysScore == 0) {
            return;
        }
        StringBuffer jpql = new StringBuffer();
        jpql.append(" update CreditScore c SET c.balanceScore = c.balanceScore+:balanceScore, c.sysScore = c.sysScore+:sysScore, c.version = c.version + 1 ");
        jpql.append(" WHERE c.id=:id ");
        jpql.append(" AND round(c.balanceScore+:balanceScore)>=0");
        jpql.append(" AND round(c.sysScore+:sysScore)>=0");
        jpql.append(" AND c.version=:version");
        int count = updateByJpql(jpql.toString(), new String[]{"balanceScore", "sysScore", "id", "version"},
                new Object[]{balanceScore, sysScore, id, version});
        if (count != 1) {
            throw new VersionControlException("用户信用值更新有误");
        }
        this.flush();
    }

    /**
     * 通过用户查询积分账户
     *
     * @param userId
     * @return
     */
    @Override
    public CreditScore findByUser(long userId) {
        return this.findObjByProperty("user.id", userId);
    }

}