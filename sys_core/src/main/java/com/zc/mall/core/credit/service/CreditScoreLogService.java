package com.zc.mall.core.credit.service;

import com.zc.mall.core.credit.entity.CreditScoreLog;
import com.zc.mall.core.credit.model.CreditScoreLogModel;
import com.zc.sys.common.form.Result;

/**
 * 信用分账户
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public interface CreditScoreLogService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(CreditScoreLogModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public CreditScoreLog add(CreditScoreLogModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(CreditScoreLogModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(CreditScoreLogModel model);

}