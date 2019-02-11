package com.zc.mall.mall.dao;

import com.zc.mall.mall.entity.GoodsSpu;
import com.zc.mall.mall.model.GoodsSpuModel;
import com.zc.sys.common.dao.BaseDao;
import com.zc.sys.common.model.jpa.PageDataList;

import java.util.List;

/**
 * 商品SPU
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
public interface GoodsSpuDao extends BaseDao<GoodsSpu> {

    PageDataList<GoodsSpu> list(GoodsSpuModel model);

    /**
     * 获取商品列表
     *
     * @param showTab
     * @return
     */
    List<GoodsSpu> getSpuListByShowTab(String showTab);
}