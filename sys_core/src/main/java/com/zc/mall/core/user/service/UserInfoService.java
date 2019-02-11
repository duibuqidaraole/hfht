
package com.zc.mall.core.user.service;

import com.zc.mall.core.user.model.UserInfoModel;
import com.zc.sys.common.form.Result;

/**
 * 用户信息
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public interface UserInfoService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(UserInfoModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(UserInfoModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(UserInfoModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(UserInfoModel model);

    /**
     * 通过用户Id获取用户信息
     *
     * @param model
     * @return
     */
    public Result getUserInfoByUserId(UserInfoModel model);

    /**
     * 修改用户信息
     *
     * @param model
     * @return
     */
    public Result updateInfo(UserInfoModel model);

    /**
     * 修改商家用户信息
     *
     * @param model
     * @return
     */
    public Result updateRelationUserInfo(UserInfoModel model);

    /**
     * 根据id删除
     *
     * @param userId
     * @return
     */
    public Result deleteById(UserInfoModel model);

}