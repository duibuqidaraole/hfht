package com.zc.mall.core.account.dao;

import com.zc.mall.core.account.entity.AccountStatistics;
import com.zc.mall.core.account.model.AccountStatisticsModel;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.dao.BaseDao;
import com.zc.sys.common.model.jpa.PageDataList;

/**
 * 账户统计表
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年12月20日
 */
public interface AccountStatisticsDao extends BaseDao<AccountStatistics> {
    /**
     * 账户统计列表
     *
     * @param model
     * @return
     */
    PageDataList<AccountStatistics> list(AccountStatisticsModel model);

    /**
     * 查询账户
     *
     * @param user 用户
     * @param type 类型
     * @return
     */
    AccountStatistics getAccountStatistics(User user, int type);

    /**
     * 根据用户获取消费总结
     *
     * @param userModel
     * @return
     */
    AccountStatistics findByUserId(UserModel userModel, int type);
}