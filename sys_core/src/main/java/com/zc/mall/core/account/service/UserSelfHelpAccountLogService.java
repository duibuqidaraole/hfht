package com.zc.mall.core.account.service;

import com.zc.mall.core.account.model.UserSelfHelpAccountLogModel;
import com.zc.sys.common.form.Result;

/**
 * 用户自助记账
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2018年08月05日
 */
public interface UserSelfHelpAccountLogService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(UserSelfHelpAccountLogModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(UserSelfHelpAccountLogModel model) throws Exception;

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(UserSelfHelpAccountLogModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(UserSelfHelpAccountLogModel model);

    Object sum(UserSelfHelpAccountLogModel model);
}