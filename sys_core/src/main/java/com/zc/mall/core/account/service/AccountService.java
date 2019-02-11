package com.zc.mall.core.account.service;

import com.zc.mall.core.account.entity.Account;
import com.zc.mall.core.account.model.AccountModel;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.form.Result;

import java.util.List;

/**
 * 资金账户
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public interface AccountService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(AccountModel model);

    /**
     * 添加（注册）
     *
     * @param model
     * @return
     */
    public Result add(AccountModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public void updateAccount(AccountModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(AccountModel model);

    /**
     * 根据userId查询
     *
     * @param userId
     * @return
     */
    public Account findByUser(long userId);

    /**
     * 添加用户资金信息
     *
     * @param beUserModel
     */
    void setUserAccount(UserModel beUserModel);

    /**
     * 添加用户资金信息
     *
     * @param userModelList
     */
    void setUserAccount(List<UserModel> userModelList);
}