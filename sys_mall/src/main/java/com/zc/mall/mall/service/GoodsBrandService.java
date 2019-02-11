package com.zc.mall.mall.service;

import com.zc.mall.mall.model.GoodsBrandModel;
import com.zc.sys.common.form.Result;

/**
 * 商品品牌表
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
public interface GoodsBrandService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(GoodsBrandModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(GoodsBrandModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(GoodsBrandModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(GoodsBrandModel model);

}