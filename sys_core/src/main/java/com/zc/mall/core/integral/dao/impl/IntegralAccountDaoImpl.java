package com.zc.mall.core.integral.dao.impl;

import com.zc.mall.core.integral.dao.IntegralAccountDao;
import com.zc.mall.core.integral.entity.IntegralAccount;
import com.zc.mall.core.integral.model.IntegralAccountModel;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.exception.VersionControlException;
import com.zc.sys.common.model.jpa.OrderFilter.OrderType;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import com.zc.sys.common.model.jpa.SearchFilter;
import com.zc.sys.common.model.jpa.SearchFilter.Operators;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 积分账户
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Repository
public class IntegralAccountDaoImpl extends BaseDaoImpl<IntegralAccount> implements IntegralAccountDao {

    @Override
    public PageDataList<IntegralAccount> list(IntegralAccountModel model) {
        QueryParam param = QueryParam.getInstance();
        if (StringUtil.isNotBlank(model.getSearchName())) {
            SearchFilter orFilter3 = new SearchFilter("user.realName", Operators.LIKE, model.getSearchName().trim());
            SearchFilter orFilter4 = new SearchFilter("user.mobile", Operators.LIKE, model.getSearchName().trim());
            param.addOrFilter(orFilter3, orFilter4);
        }
        if (model.getUserId() > 0) {
            param.addParam("user.id", model.getUserId());
        }
        param.addOrder(OrderType.ASC, "id");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }

    /**
     * 通过用户Id查询积分账户
     *
     * @param userId
     * @return
     */
    @Override
    public IntegralAccount findByUserId(long userId) {
        return this.findObjByProperty("user.id", userId);
    }

    /**
     * 更新积分
     *
     * @param id
     * @param totalIntegral   积分总额
     * @param balanceIntegral 积分余额
     * @param expenseIntegral 消费积分
     * @param freezeIntegral  冻结积分
     * @param version         版本
     * @throws VersionControlException
     */
    @Override
    @Transactional
    public void updateIntegral(long id, double totalIntegral, double balanceIntegral, double expenseIntegral,
                               double freezeIntegral, int version) throws VersionControlException {
        StringBuffer jpql = new StringBuffer();
        jpql.append(" update IntegralAccount iAcct SET iAcct.totalIntegral=iAcct.totalIntegral+:totalIntegral, "
                + " iAcct.balanceIntegral=iAcct.balanceIntegral+:balanceIntegral, iAcct.expenseIntegral=iAcct.expenseIntegral+:expenseIntegral, "
                + " iAcct.freezeIntegral=iAcct.freezeIntegral+:freezeIntegral, iAcct.version=iAcct.version+1 ");
        jpql.append(" WHERE iAcct.id=:id ");
        jpql.append(" AND round(iAcct.totalIntegral+:totalIntegral)>=0");
        jpql.append(" AND round(iAcct.balanceIntegral+:balanceIntegral)>=0");
        jpql.append(" AND round(iAcct.expenseIntegral+:expenseIntegral)>=0");
        jpql.append(" AND round(iAcct.freezeIntegral+:freezeIntegral)>=0");
        jpql.append(" AND iAcct.version=:version");
        int count = updateByJpql(jpql.toString(),
                new String[]{"id", "totalIntegral", "balanceIntegral", "expenseIntegral", "freezeIntegral", "version"},
                new Object[]{id, totalIntegral, balanceIntegral, expenseIntegral, freezeIntegral, version});
        if (count != 1) {
            throw new VersionControlException("用户积分更新有误");
        }
        IntegralAccount integralAccount = findByIdUseJpql(id);
        em.refresh(integralAccount);
    }

    /**
     * jpql查询实体
     *
     * @param id
     * @return
     */
    @Override
    public IntegralAccount findByIdUseJpql(long id) {
        StringBuffer jpql = new StringBuffer();
        jpql.append(" from IntegralAccount iAcct where iAcct.id = :id ");
        return this.findByJpql(jpql.toString(), new String[]{"id"}, new Object[]{id});
    }

    /**
     * 添加用户积分
     *
     * @param model
     */
    @Override
    public void setUserIntegral(UserModel model) {
        QueryParam param = QueryParam.getInstance();
        param.addParam("user.id", model.getId());
        model.setIntegralAccountModel(IntegralAccountModel.instance(super.findByCriteriaForUnique(param)));
    }

}