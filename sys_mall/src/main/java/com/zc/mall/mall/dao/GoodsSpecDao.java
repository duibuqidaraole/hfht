package com.zc.mall.mall.dao;

import com.zc.mall.mall.entity.GoodsSpec;
import com.zc.mall.mall.model.GoodsSpecModel;
import com.zc.sys.common.dao.BaseDao;
import com.zc.sys.common.model.jpa.PageDataList;

/**
 * 商品规格
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
public interface GoodsSpecDao extends BaseDao<GoodsSpec> {

    PageDataList<GoodsSpec> list(GoodsSpecModel model);

    GoodsSpec findByNo(String no);
}