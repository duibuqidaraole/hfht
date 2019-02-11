package com.zc.mall.core.user.service;

import com.zc.mall.core.account.model.BankCardModel;
import com.zc.mall.core.user.model.UserIdentifyModel;
import com.zc.sys.common.form.Result;

/**
 * 用户认证
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public interface UserIdentifyService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(UserIdentifyModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(UserIdentifyModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(UserIdentifyModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(UserIdentifyModel model);

    /**
     * 通过用户ID获取
     *
     * @param model
     * @return
     */
    public Result getByUserId(UserIdentifyModel model);

    /**
     * 实名请求
     *
     * @param model
     * @return
     */
    public Object realNameRequest(UserIdentifyModel model);

    /**
     * 实名处理
     *
     * @param model
     * @return
     */
    public Object realNameDeal(UserIdentifyModel model);

    /**
     * 数据魔盒-运营商-数据认证请求
     *
     * @param model
     * @return
     */
    public Object octopusRequest(UserIdentifyModel model);

    /**
     * 数据魔盒-运营商-数据认证处理
     *
     * @param model
     * @return
     */
    public Object octopusDeal(UserIdentifyModel model);


    /**
     * 实名+绑卡请求
     *
     * @param model
     * @return
     */
    public Object realNameAndBindCardRequest(UserIdentifyModel model, BankCardModel bcModel);

    /**
     * 法人实名请求
     *
     * @param model
     * @return
     */
    public Object realNameArtifRequest(UserIdentifyModel model);

    /**
     * 实名测试
     *
     * @param model
     * @return
     */
    Object realNameTest(UserIdentifyModel model);
}