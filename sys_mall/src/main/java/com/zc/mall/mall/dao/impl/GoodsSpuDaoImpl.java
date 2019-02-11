package com.zc.mall.mall.dao.impl;

import com.zc.mall.mall.dao.GoodsSpuDao;
import com.zc.mall.mall.entity.GoodsSpu;
import com.zc.mall.mall.model.GoodsSpuModel;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.model.jpa.OrderFilter;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import com.zc.sys.common.model.jpa.SearchFilter;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品SPU
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@Repository
public class GoodsSpuDaoImpl extends BaseDaoImpl<GoodsSpu> implements GoodsSpuDao {

    @Override
    public PageDataList<GoodsSpu> list(GoodsSpuModel model) {
        QueryParam param = QueryParam.getInstance();
        if (StringUtil.isNotBlank(model.getName())) {
            param.addParam("name", model.getName());
        }
        if (model.getUpdateOperator() != null && model.getUpdateOperator().getId() > 0) {
            param.addParam("updateOperator.id", model.getUpdateOperator().getId());
        }
        if (model.getAddOperator() != null && model.getAddOperator().getId() > 0) {
            param.addParam("addOperator.id", model.getAddOperator().getId());
        }
        if (model.getGoodsBrand() != null && model.getGoodsBrand().getId() > 0) {
            param.addParam("goodsBrand.id", model.getGoodsBrand().getId());
        }
        if (StringUtil.isNotBlank(model.getShowTab())) {
            param.addParam("showTab",SearchFilter.Operators.LIKE, model.getShowTab());
        }
        if (model.getGoodsSkuCount() > 0) {
            param.addParam("goodsSkuCount",  SearchFilter.Operators.GT, 0);
        }
        if (StringUtil.isNotBlank(model.getRecommendSite())) {
            param.addParam("recommendSite", SearchFilter.Operators.LIKE,model.getRecommendSite());
            param.addOrder(OrderFilter.OrderType.ASC, "recommendRanking");
            param.addOrder(OrderFilter.OrderType.DESC, "recommendDate");
        }
        if (model.getGoodsCategory() != null && model.getGoodsCategory().getId() > 0) {
            param.addParam("goodsCategory.id", model.getGoodsCategory().getId());
        }
        if (StringUtil.isNotBlank(model.getNo())) {
            param.addParam("no", model.getNo());
        }
        param.addOrder(OrderFilter.OrderType.DESC, "addTime");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }

    /**
     * 获取商品列表
     *
     * @param showTab
     * @return
     */
    @Override
    public List<GoodsSpu> getSpuListByShowTab(String showTab) {
        QueryParam param = QueryParam.getInstance();
        param.addParam("showTab", showTab);
        return super.findByCriteria(param);
    }

}