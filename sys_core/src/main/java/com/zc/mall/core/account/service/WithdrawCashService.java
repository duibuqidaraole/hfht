
package com.zc.mall.core.account.service;

import com.zc.mall.core.account.model.WithdrawCashModel;
import com.zc.sys.common.form.Result;

/**
 * 提现
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public interface WithdrawCashService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(WithdrawCashModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(WithdrawCashModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(WithdrawCashModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(WithdrawCashModel model);

    /**
     * 提现请求
     *
     * @param model
     * @return
     */
    public Result withdrawReq(WithdrawCashModel model);

    /**
     * 提现成功处理
     *
     * @param model
     * @return
     */
    public Result withdrawSuccessfulDeal(WithdrawCashModel model);

    /**
     * 提现失败处理
     *
     * @param model
     * @return
     */
    public Result withdrawFailDeal(WithdrawCashModel model);

    /**
     * 提现审核处理
     *
     * @param model
     * @return
     */
    public Result auditWithdrawDeal(WithdrawCashModel model);
}