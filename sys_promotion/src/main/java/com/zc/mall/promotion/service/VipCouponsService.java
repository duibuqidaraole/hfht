package com.zc.mall.promotion.service;

import com.zc.mall.promotion.model.VipCouponsModel;
import com.zc.sys.common.form.Result;

/**
 * 商品分类
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
public interface VipCouponsService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(VipCouponsModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(VipCouponsModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(VipCouponsModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public VipCouponsModel getById(VipCouponsModel model);

    /**
     * 获取
     *
     * @param id
     * @return
     */
    public VipCouponsModel getById(long id);

}