package com.zc.mall.core.integral.dao;

import com.zc.mall.core.integral.entity.IntegralAccount;
import com.zc.mall.core.integral.model.IntegralAccountModel;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.dao.BaseDao;
import com.zc.sys.common.exception.VersionControlException;
import com.zc.sys.common.model.jpa.PageDataList;

/**
 * 积分账户
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public interface IntegralAccountDao extends BaseDao<IntegralAccount> {

    /**
     * 积分账户列表
     *
     * @param model
     * @return
     */
    public PageDataList<IntegralAccount> list(IntegralAccountModel model);

    /**
     * 通过用户Id查询积分账户
     *
     * @param userId
     * @return
     */
    public IntegralAccount findByUserId(long userId);

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
    public void updateIntegral(long id, double totalIntegral, double balanceIntegral,
                               double expenseIntegral, double freezeIntegral, int version) throws VersionControlException;

    /**
     * jpql查询实体
     *
     * @param id
     * @return
     */
    public IntegralAccount findByIdUseJpql(long id);

    /**
     * 添加用户积分
     *
     * @param model
     */
    void setUserIntegral(UserModel model);
}