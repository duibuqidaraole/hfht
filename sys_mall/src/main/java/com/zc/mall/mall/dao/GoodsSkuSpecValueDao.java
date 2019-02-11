package com.zc.mall.mall.dao;

import com.zc.mall.mall.entity.GoodsSkuSpecValue;
import com.zc.mall.mall.entity.GoodsSpecValue;
import com.zc.mall.mall.model.GoodsSkuSpecValueModel;
import com.zc.sys.common.dao.BaseDao;
import com.zc.sys.common.model.jpa.PageDataList;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品sku-规格值
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
public interface GoodsSkuSpecValueDao extends BaseDao<GoodsSkuSpecValue> {

    PageDataList<GoodsSkuSpecValue> list(GoodsSkuSpecValueModel model);

    /**
     * 检查重复性
     *
     * @param model
     * @return
     */
    boolean checkSkuAndSpecValue(GoodsSkuSpecValueModel model);

    /**
     * 根据skuId查询
     *
     * @param id
     * @return
     */
    List<GoodsSkuSpecValueModel> findBySku(long id);

    @Transactional
    GoodsSkuSpecValue findBySku(long id, String specNo);

    List<GoodsSkuSpecValueModel> findBySku(long id, int specType);

    /**
     * 获取展示
     *
     * @param id
     * @return
     */
    String getShowSpec(long id);

    /**
     * 根据规格值列表获取商品list
     *
     * @param specValueList
     * @return
     */
    List<Long> findBySpecValueList(List<Long> specValueList);

    /**
     * 根据商品spu获取列表
     *
     * @param id
     * @param onSale
     * @return
     */
    List<GoodsSpecValue> findBySpuId(long id, int onSale);

    /**
     * 根据商品id获取可选参数值
     *
     * @param id
     * @return
     */
    String getSkuChooseSpecValueBySkuId(long id);
}