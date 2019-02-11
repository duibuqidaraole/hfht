package com.zc.mall.core.account.service;

import com.zc.mall.core.account.model.AccountStatisticsModel;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.form.Result;

import java.util.List;

/**
 * 账户统计
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年12月20日
 */
public interface AccountStatisticsService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(AccountStatisticsModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(AccountStatisticsModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(AccountStatisticsModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(AccountStatisticsModel model);

    /**
     * 统计更新
     *
     * @param user   用户
     * @param type   类型
     * @param amount 金额
     */
    void updateStatistics(User user, int type, double amount);

    /**
     * 统计更新
     *
     * @param user   用户
     * @param type   类型
     * @param amount 金额
     */
    public Result getStatisticsByUserAndType(AccountStatisticsModel model);

    /**
     * 获取用户总余额
     *
     * @param userModel
     * @return
     */
    UserModel setAccountStatistics(UserModel userModel);

    /**
     * 添加用户消费金额
     *
     * @param list
     */
    void setUserPayInfo(List<UserModel> list);

    /**
     * 添加用户消费金额
     *
     * @param model
     */
    void setUserPayInfo(UserModel model);
}