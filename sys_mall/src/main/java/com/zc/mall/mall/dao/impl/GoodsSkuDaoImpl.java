package com.zc.mall.mall.dao.impl;

import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.common.global.Global;
import com.zc.mall.core.sys.entity.Dict;
import com.zc.mall.mall.dao.GoodsSkuDao;
import com.zc.mall.mall.entity.GoodsSku;
import com.zc.mall.mall.model.GoodsSkuModel;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.model.jpa.OrderFilter;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import com.zc.sys.common.model.jpa.SearchFilter;
import com.zc.sys.common.util.validate.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@Repository
public class GoodsSkuDaoImpl extends BaseDaoImpl<GoodsSku> implements GoodsSkuDao {

    private static Logger log = LoggerFactory.getLogger(GoodsSkuDaoImpl.class);

    @Override
    public PageDataList<GoodsSku> list(GoodsSkuModel model) {
        QueryParam param = QueryParam.getInstance();
        if (StringUtil.isNotBlank(model.getSearchName())) {
            SearchFilter orFilter1 = new SearchFilter("goodsSpu.showTab", SearchFilter.Operators.LIKE, model.getSearchName());
            SearchFilter orFilter2 = new SearchFilter("goodsSpu.name", SearchFilter.Operators.LIKE, model.getSearchName());
            SearchFilter orFilter3 = new SearchFilter("goodsSpu.recommendSite", SearchFilter.Operators.LIKE, model.getSearchName());
            SearchFilter orFilter4 = new SearchFilter("name", SearchFilter.Operators.LIKE, model.getSearchName());
            param.addOrFilter(orFilter1, orFilter2, orFilter3, orFilter4);
        }

        if (StringUtil.isNotBlank(model.getName())) {
            param.addParam("name", SearchFilter.Operators.LIKE, model.getName());
        }
        if (model.getGoodsSpuModel() != null) {
            if (StringUtil.isNotBlank(model.getGoodsSpuModel().getShowTab())) {
                param.addParam("goodsSpu.showTab", SearchFilter.Operators.LIKE, model.getGoodsSpuModel().getShowTab());
            }
            if (StringUtil.isNotBlank(model.getGoodsSpuModel().getName())) {
                param.addParam("goodsSpu.name", SearchFilter.Operators.LIKE, model.getGoodsSpuModel().getName());
            }

            if (StringUtil.isNotBlank(model.getGoodsSpuModel().getRecommendSite())) {
                param.addParam("goodsSpu.recommendSite", SearchFilter.Operators.LIKE, model.getGoodsSpuModel().getRecommendSite());
            }

            if (StringUtil.isNotBlank(model.getGoodsSpuModel().getRecommendSiteList())){
                param.addOrFilter("goodsSpu.recommendSite", model.getGoodsSpuModel().getRecommendSiteList().split(","));
            }
        }

        if (model.getStock() < 0) {
            param.addParam("stock", SearchFilter.Operators.LT, 1);
        }

        if (StringUtil.isNotBlank(model.getNo())) {
            param.addParam("no", model.getNo());
        }

        if (StringUtil.isNotBlank(model.getState())) {
            param.addParam("state", model.getState());
        }

        if (model.getPrince() > 0) {
            param.addParam("prince", model.getPrince());
        }

        if (model.getGoodsSpu() != null && model.getGoodsSpu().getId() > 0) {
            param.addParam("goodsSpu.id", model.getGoodsSpu().getId());
        }
        if (model.getIsRecommend() != null && model.getIsRecommend() > 0) {
            param.addParam("recommendTime", SearchFilter.Operators.NOTEQ, null);
            param.addOrder(OrderFilter.OrderType.DESC, "recommendTime");
        }

        if (model.getUpdateOperator() != null && model.getUpdateOperator().getId() > 0) {
            param.addParam("updateOperator.id", model.getUpdateOperator().getId());
        }
        if (model.getAddOperator() != null && model.getAddOperator().getId() > 0) {
            param.addParam("addOperator.id", model.getAddOperator().getId());
        }
        if (model.getOnSale() != 0) {
            param.addParam("onSale", model.getOnSale());
        }

        param.addOrder(OrderFilter.OrderType.DESC, "addTime");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }

    @Override
    public PageDataList<GoodsSku> listForReception(GoodsSkuModel model) {
        QueryParam param = QueryParam.getInstance();
        if (StringUtil.isNotBlank(model.getName())) {
            param.addParam("name", model.getName());
        }

        if (StringUtil.isNotBlank(model.getNo())) {
            param.addParam("no", model.getNo());
        }

        if (model.getPrince() > 0) {
            param.addParam("prince", model.getPrince());
        }

        param.addParam("onSale", BaseConstant.BUSINESS_STATE_YES);

        if (model.getGoodsSpu() != null && model.getGoodsSpu().getId() > 0) {
            param.addParam("goodsSpu.id", model.getGoodsSpu().getId());
        }
        if (model.getUpdateOperator() != null && model.getUpdateOperator().getId() > 0) {
            param.addParam("updateOperator.id", model.getUpdateOperator().getId());
        }
        if (model.getAddOperator() != null && model.getAddOperator().getId() > 0) {
            param.addParam("addOperator.id", model.getAddOperator().getId());
        }

        if (model.getIsRecommend() != null && model.getIsRecommend() > 0) {
            param.addParam("recommendTime", SearchFilter.Operators.NOTEQ, null);
            param.addOrder(OrderFilter.OrderType.DESC, "recommendTime");
        }
        param.addOrder(OrderFilter.OrderType.DESC, "addTime");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }

    /**
     * 更新库存
     *
     * @param goodsSkuId
     * @param number
     */
    @Override
    @Transactional
    public synchronized void updateGoodsSkuStock(long goodsSkuId, int number) {
        String jpql = " UPDATE GoodsSku sku set sku.stock = sku.stock +:number,sku.salesVolume = sku.salesVolume -:number where sku.id =:goodsSkuId and (sku.stock +:number >= 0)";
        int count = updateByJpql(jpql, new String[]{"goodsSkuId", "number"},
                new Object[]{goodsSkuId, number});
        log.info("更新商品sku库存和销量---更新条数：" + count);
        if (count <= 0) {
            throw new BusinessException("库存更新失败");
        }
    }

    /**
     * 根据no获取商品
     *
     * @param no
     * @return
     */
    @Override
    public GoodsSku findByNo(String no) {
        QueryParam queryParam = QueryParam.getInstance();
        queryParam.addParam("no", no);
        return super.findByCriteriaForUnique(queryParam);
    }

    /**
     * 根据spu获取商品列表
     *
     * @param id
     * @return
     */
    @Override
    public List<GoodsSku> findBySpu(long id) {
        QueryParam queryParam = QueryParam.getInstance();
        queryParam.addParam("goodsSpu.id", id);
        queryParam.addOrder(OrderFilter.OrderType.DESC, "onSale");
        queryParam.addOrder(OrderFilter.OrderType.DESC, "stock");
        queryParam.addOrder(OrderFilter.OrderType.ASC, "prince");
        queryParam.addOrder(OrderFilter.OrderType.DESC, "discount");
        return super.findByCriteria(queryParam);
    }

    /**
     * 获取热门
     *
     * @return
     */
    @Override
    public List<GoodsSkuModel> getHot() {
        ArrayList<GoodsSkuModel> goodsSkuModels = new ArrayList<>();
        List<Dict> dictList = Global.getDictByNid("show_in_tab");
        for (Dict dict : dictList) {
            List<GoodsSku> goodsSkuList = findByShowInTab(dict.getValue());
            for (GoodsSku goodsSku : goodsSkuList) {
                if (goodsSku.getOnSale() > 0 && goodsSku.getStock() > 0) {
                    goodsSkuModels.add(GoodsSkuModel.instance(goodsSku));
                    break;
                }
            }
        }
        return goodsSkuModels;

    }

    /**
     * 根据展示获取推荐商品
     *
     * @param value
     * @return
     */
    private List<GoodsSku> findByShowInTab(String value) {
        QueryParam queryParam = QueryParam.getInstance();
        queryParam.addParam("goodsSpu.showTab", value);
        return super.findByCriteria(queryParam);
    }
}