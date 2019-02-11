package com.zc.mall.core.manage.service;

import com.zc.mall.core.manage.model.RoleModel;
import com.zc.sys.common.form.Result;

/**
 * 角色
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public interface RoleService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(RoleModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(RoleModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(RoleModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(RoleModel model);

}