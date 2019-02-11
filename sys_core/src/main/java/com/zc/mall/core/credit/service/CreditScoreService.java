package com.zc.mall.core.credit.service;

import com.zc.mall.core.credit.model.CreditScoreModel;
import com.zc.sys.common.form.Result;

/**
 * 信用分账户
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public interface CreditScoreService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(CreditScoreModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(CreditScoreModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(CreditScoreModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(CreditScoreModel model);

    /**
     * 芝麻信用授权
     *
     * @param model
     * @return
     */
    public Result zmxyAuthorize(CreditScoreModel model);

    /**
     * 更新积分
     *
     * @param model
     * @return
     */
    public void updateCreditScore(CreditScoreModel model);

    /**
     * 后台手动更新信用分
     *
     * @param model
     * @return
     */
    public Result adminHandleCreditScore(CreditScoreModel model);


}