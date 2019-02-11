package com.zc.mall.core.manage.service;

import com.zc.mall.core.manage.model.OperatorModel;
import com.zc.sys.common.form.Result;

/**
 * 管理员
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public interface OperatorService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(OperatorModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(OperatorModel model);

    /**
     * 添加后台商家
     *
     * @param model
     * @return
     */
    public Result addRelation(OperatorModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(OperatorModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(OperatorModel model);

    /**
     * 登录
     *
     * @param model
     * @return
     */
    public Result signIn(OperatorModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getByUserId(OperatorModel model);

}