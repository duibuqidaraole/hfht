package com.zc.mall.core.user.service;

import com.zc.mall.core.user.model.UserBusinessApplyModel;
import com.zc.sys.common.form.Result;

/**
 * 商家申请信息
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public interface UserBusinessApplyService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(UserBusinessApplyModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(UserBusinessApplyModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(UserBusinessApplyModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(UserBusinessApplyModel model);

    /**
     * 商家申请
     *
     * @param model
     * @return
     */
    public Result delRegRequest(UserBusinessApplyModel model);
}
