package com.zc.mall.core.user.service;

import com.zc.mall.core.user.model.UserAddressModel;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.form.Result;

import java.util.List;

/**
 * 用户地址
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
public interface UserAddressService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(UserAddressModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(UserAddressModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(UserAddressModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(UserAddressModel model);

    /**
     * 添加用户地址
     *
     * @param beUserModel
     */
    void setUserAddress(UserModel beUserModel);

    /**
     * 添加用户地址
     *
     * @param userModelList
     */
    void setUserAddress(List<UserModel> userModelList);
}