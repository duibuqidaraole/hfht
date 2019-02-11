
package com.zc.mall.core.account.service;

import com.zc.mall.core.account.model.BankCardModel;
import com.zc.sys.common.form.Result;

/**
 * 银行卡
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public interface BankCardService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(BankCardModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(BankCardModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(BankCardModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(BankCardModel model);

    /**
     * 根据用户ID获取BankCard
     *
     * @param model
     * @return
     */
    public Result getByBankCardUserId(BankCardModel model);

    /**
     * 绑卡请求
     *
     * @param model
     * @return
     */
    public Result bindBCRequest(BankCardModel model);

    /**
     * 绑卡处理
     *
     * @param model
     * @return
     */
    public Result bindBCDeal(BankCardModel model);

    /**
     * 换绑卡请求
     *
     * @param model
     * @return
     */
    public Result changeBankCardRequest(BankCardModel model);

    /**
     * 换绑卡处理
     *
     * @param model
     * @return
     */
    public Result changeBankCardDeal(BankCardModel model);

    /**
     * 解绑卡处理
     *
     * @param model
     * @return
     */
    public Result unBindBCDeal(BankCardModel model);

}