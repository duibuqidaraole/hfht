package com.zc.mall.mall.service;

import com.zc.mall.mall.model.GoodsCategoryModel;
import com.zc.sys.common.form.Result;

/**
 * 商品分类
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
public interface GoodsCategoryService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(GoodsCategoryModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(GoodsCategoryModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(GoodsCategoryModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(GoodsCategoryModel model);

}