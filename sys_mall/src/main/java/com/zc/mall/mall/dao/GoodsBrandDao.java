package com.zc.mall.mall.dao;

import com.zc.mall.mall.entity.GoodsBrand;
import com.zc.mall.mall.model.GoodsBrandModel;
import com.zc.sys.common.dao.BaseDao;
import com.zc.sys.common.model.jpa.PageDataList;

/**
 * 商品品牌表
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
public interface GoodsBrandDao extends BaseDao<GoodsBrand> {
    /**
     * 商品品牌搜索
     *
     * @param model
     * @return
     */
    PageDataList<GoodsBrand> list(GoodsBrandModel model);
}