package com.zc.mall.mall.dao;

import com.zc.mall.mall.entity.GoodsCategory;
import com.zc.mall.mall.model.GoodsCategoryModel;
import com.zc.sys.common.dao.BaseDao;
import com.zc.sys.common.model.jpa.PageDataList;

/**
 * 商品分类
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
public interface GoodsCategoryDao extends BaseDao<GoodsCategory> {
    /**
     * 查询商品分类列表
     *
     * @param model
     * @return
     */
    PageDataList<GoodsCategory> list(GoodsCategoryModel model);
}