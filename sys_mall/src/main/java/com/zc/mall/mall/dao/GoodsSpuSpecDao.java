package com.zc.mall.mall.dao;

import com.zc.mall.mall.entity.GoodsSpec;
import com.zc.mall.mall.entity.GoodsSpuSpec;
import com.zc.mall.mall.model.GoodsSpuSpecModel;
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
public interface GoodsSpuSpecDao extends BaseDao<GoodsSpuSpec> {

    PageDataList<GoodsSpuSpec> list(GoodsSpuSpecModel model);

    /**
     * 查询是否有重复信息
     *
     * @param model
     * @return true 有重复
     */
    boolean checkSpecAndSpu(GoodsSpuSpecModel model);

    /**
     * 未选择列表
     *
     * @param model
     * @return
     */
    List<GoodsSpec> unCheckList(GoodsSpuSpecModel model);
}