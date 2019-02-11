package com.zc.mall.core.integral.service;

import com.zc.mall.core.integral.model.IntegralAccountModel;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.form.Result;

import java.util.List;

/**
 * 积分账户
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public interface IntegralAccountService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(IntegralAccountModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(IntegralAccountModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(IntegralAccountModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(IntegralAccountModel model);

    /**
     * 通过用户Id获取
     *
     * @param model
     * @return
     */
    public Result getByUserId(IntegralAccountModel model);

    /**
     * 更新积分
     *
     * @param model
     */
    public void updateIntegral(IntegralAccountModel model);

    /**
     * 添加用户积分
     *
     * @param beUserModel
     */
    void setUserIntegral(UserModel beUserModel);

    /**
     * 添加用户积分
     *
     * @param userModelList
     */
    void setUserIntegral(List<UserModel> userModelList);
}