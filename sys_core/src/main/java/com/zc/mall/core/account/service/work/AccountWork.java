package com.zc.mall.core.account.service.work;

import com.zc.mall.core.account.service.AccountStatisticsService;
import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.user.entity.User;

/**
 * 账户
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2018年03月29日
 */
public class AccountWork {

    public static AccountWork initAccountWork() {
        return new AccountWork();
    }

    /**
     * 统计更新
     *
     * @param user   用户
     * @param type   类型
     * @param amount 金额
     */
    public static void updateAccountStatistics(User user, int type, double amount) {
        AccountStatisticsService accountStatisticsService = BeanUtil.getBean(AccountStatisticsService.class);
        accountStatisticsService.updateStatistics(user, type, amount);
    }
}
