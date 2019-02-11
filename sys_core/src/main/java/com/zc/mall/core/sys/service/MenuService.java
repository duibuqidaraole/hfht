package com.zc.mall.core.sys.service;

import com.zc.mall.core.sys.model.MenuModel;
import com.zc.sys.common.form.Result;

/**
 * 菜单
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public interface MenuService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(MenuModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(MenuModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(MenuModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(MenuModel model);

}