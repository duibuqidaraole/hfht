package com.zc.mall.mall.dao;

import com.zc.mall.mall.entity.GoodsSku;
import com.zc.mall.mall.model.GoodsSkuModel;
import com.zc.sys.common.dao.BaseDao;
import com.zc.sys.common.model.jpa.PageDataList;

import java.util.List;

/**
 * 商品分类
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
public interface GoodsSkuDao extends BaseDao<GoodsSku> {

    PageDataList<GoodsSku> list(GoodsSkuModel model);

    PageDataList<GoodsSku> listForReception(GoodsSkuModel model);

    /**
     * 更新库存
     *
     * @param goodsSkuId
     * @param number
     */
    void updateGoodsSkuStock(long goodsSkuId, int number);

    /**
     * 根据no获取商品
     *
     * @param no
     * @return
     */
    GoodsSku findByNo(String no);

    /**
     * 根据spu获取商品列表
     *
     * @param id
     * @return
     */
    List<GoodsSku> findBySpu(long id);

    /**
     * 获取热门
     *
     * @return
     */
    List<GoodsSkuModel> getHot();
}