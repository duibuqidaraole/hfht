package com.zc.mall.mall.dao;

import com.zc.mall.mall.entity.GoodsSpecValue;
import com.zc.mall.mall.model.GoodsSpecValueModel;
import com.zc.sys.common.dao.BaseDao;
import com.zc.sys.common.model.jpa.PageDataList;

import java.util.List;

/**
 * 商品规格值
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
public interface GoodsSpecValueDao extends BaseDao<GoodsSpecValue> {

    PageDataList<GoodsSpecValue> list(GoodsSpecValueModel model);

    /**
     * 根据规格id查询规格值
     *
     * @param id
     * @return
     */
    List<GoodsSpecValue> findByGoodsSpecId(long id);

}