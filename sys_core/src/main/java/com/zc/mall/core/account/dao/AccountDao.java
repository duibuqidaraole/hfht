package com.zc.mall.core.account.dao;

import com.zc.mall.core.account.entity.Account;
import com.zc.mall.core.account.model.AccountModel;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.dao.BaseDao;
import com.zc.sys.common.exception.VersionControlException;
import com.zc.sys.common.model.jpa.PageDataList;

/**
 * 资金账户
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public interface AccountDao extends BaseDao<Account> {
    /**
     * 列表
     *
     * @param model
     * @return
     */
    PageDataList<Account> list(AccountModel model);

    /**
     * 根据userId查询
     *
     * @param userId
     * @return
     */
    Account findByUser(long userId);

    /**
     * 更新账户资金
     *
     * @param id
     * @param total
     * @param balance
     * @param freezeAmount
     * @param version
     * @return
     * @throws VersionControlException
     */
    void updateAccount(long id, double total, double balance, double freezeAmount, int version) throws VersionControlException;

    /**
     * jpql查询实体
     *
     * @param id
     * @return
     */
    Account findByIdUseJpql(long id);

    /**
     * 添加用户资金信息
     *
     * @param beUserModel
     */
    void setUserAccount(UserModel beUserModel);
}