package com.zc.mall.mall.dao.impl;

import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.sys.service.DictService;
import com.zc.mall.mall.dao.GoodsSkuSpecValueDao;
import com.zc.mall.mall.entity.GoodsSkuSpecValue;
import com.zc.mall.mall.entity.GoodsSpecValue;
import com.zc.mall.mall.model.GoodsSkuModel;
import com.zc.mall.mall.model.GoodsSkuSpecValueModel;
import com.zc.mall.mall.model.GoodsSpecModel;
import com.zc.mall.mall.model.GoodsSpecValueModel;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.model.jpa.OrderFilter;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import com.zc.sys.common.model.jpa.SearchFilter;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 商品sku-规格值
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@Repository
public class GoodsSkuSpecValueDaoImpl extends BaseDaoImpl<GoodsSkuSpecValue> implements GoodsSkuSpecValueDao {
    @Resource
    private DictService dictService;

    @Override
    public PageDataList<GoodsSkuSpecValue> list(GoodsSkuSpecValueModel model) {
        QueryParam param = QueryParam.getInstance();
        if (model.getUpdateOperator() != null && model.getUpdateOperator().getId() > 0) {
            param.addParam("updateOperator.id", model.getUpdateOperator().getId());
        }
        if (model.getAddOperator() != null && model.getAddOperator().getId() > 0) {
            param.addParam("addOperator.id", model.getAddOperator().getId());
        }
        if (model.getGoodsSku() != null) {
            if (model.getGoodsSku().getId() > 0) {
                param.addParam("goodsSku.id", model.getGoodsSku().getId());
            }
            if (StringUtil.isNotBlank(model.getGoodsSku().getNo())) {
                param.addParam("goodsSku.no", model.getGoodsSku().getNo());

            }
            if (StringUtil.isNotBlank(model.getGoodsSku().getName())) {
                param.addParam("goodsSku.name", SearchFilter.Operators.LIKE, model.getGoodsSku().getName());

            }
            if (model.getGoodsSku().getOnSale() != 0) {
                param.addParam("goodsSku.onSale", model.getGoodsSku().getOnSale());
            }
        }


        if (model.getGoodsSpecValue() != null && model.getGoodsSpecValue().getId() > 0) {
            param.addParam("goodsSpecValue.id", model.getGoodsSpecValue().getId());
        }
        if (model.getGoodsSpecValue() != null && model.getGoodsSpecValue().getGoodsSpec() != null && StringUtil.isNotBlank(model.getGoodsSpecValue().getGoodsSpec().getNo())) {
            param.addParam("goodsSpecValue.goodsSpec.no", model.getGoodsSpecValue().getGoodsSpec().getNo());
        }
        if (StringUtil.isNotBlank(model.getCheckSpecValueList())) {
            param.addOrFilter("goodsSpecValue.id", model.getCheckSpecValueList().split(","));
        }
        param.addOrder(OrderFilter.OrderType.DESC, "id");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }

    /**
     * 检查重复性
     *
     * @param model
     * @return
     */
    @Override
    public boolean checkSkuAndSpecValue(GoodsSkuSpecValueModel model) {
        if (model.getGoodsSku() == null || model.getGoodsSku().getId() <= 0 || model.getGoodsSpecValue() == null || model.getGoodsSpecValue().getId() <= 0) {
            throw new BusinessException("查询条件不足");
        }
        QueryParam queryParam = QueryParam.getInstance();
        queryParam.addParam("goodsSpecValue.goodsSpec.type", BaseConstant.BUSINESS_STATE_YES);
        queryParam.addParam("goodsSku.id", model.getGoodsSku().getId());
        queryParam.addParam("goodsSpecValue.id", model.getGoodsSpecValue().getId());
        List<GoodsSkuSpecValue> list = super.findByCriteria(queryParam);
        if (list != null && list.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 根据skuId查询
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public List<GoodsSkuSpecValueModel> findBySku(long id) {
        QueryParam queryParam = QueryParam.getInstance();
        queryParam.addParam("goodsSku.id", id);
        List<GoodsSkuSpecValue> goodsSkuSpecValues = super.findByCriteria(queryParam);
        ArrayList<GoodsSkuSpecValueModel> GoodsSkuSpecValueModels = new ArrayList<>();
        setGoodsSkuSpecValueModel(goodsSkuSpecValues, GoodsSkuSpecValueModels);
        return GoodsSkuSpecValueModels;
    }

    /**
     * 根据skuId查询
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public GoodsSkuSpecValue findBySku(long id,String specNo) {
        QueryParam queryParam = QueryParam.getInstance();
        queryParam.addParam("goodsSku.id", id);
        queryParam.addParam("goodsSpecValue.goodsSpec.no",specNo);
        return super.findByCriteriaForUnique(queryParam);
    }

    /**
     * 根据skuId查询
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public List<GoodsSkuSpecValueModel> findBySku(long id, int specType) {
        QueryParam queryParam = QueryParam.getInstance();
        queryParam.addParam("goodsSku.id", id);
        queryParam.addParam("goodsSpecValue.goodsSpec.type", specType);
        List<GoodsSkuSpecValue> goodsSkuSpecValues = super.findByCriteria(queryParam);
        List<GoodsSkuSpecValueModel> GoodsSkuSpecValueModels = new ArrayList<>();
        setGoodsSkuSpecValueModel(goodsSkuSpecValues, GoodsSkuSpecValueModels);
        return GoodsSkuSpecValueModels;
    }

    private void setGoodsSkuSpecValueModel(List<GoodsSkuSpecValue> goodsSkuSpecValues, List<GoodsSkuSpecValueModel> GoodsSkuSpecValueModels) {
        for (GoodsSkuSpecValue goodsSkuSpecValue : goodsSkuSpecValues) {
            GoodsSkuSpecValueModel goodsSkuSpecValueModel = GoodsSkuSpecValueModel.instance(goodsSkuSpecValue);
            GoodsSpecValueModel goodsSpecValueModel = GoodsSpecValueModel.instance(goodsSkuSpecValueModel.getGoodsSpecValue());
            goodsSpecValueModel.setGoodsSpecModel(GoodsSpecModel.instance(goodsSpecValueModel.getGoodsSpec()));
            goodsSkuSpecValueModel.setGoodsSpecValueModel(goodsSpecValueModel);
            goodsSkuSpecValueModel.setGoodsSkuModel(GoodsSkuModel.instance(goodsSkuSpecValueModel.getGoodsSku()));
            GoodsSkuSpecValueModels.add(goodsSkuSpecValueModel);
        }
    }

    @Override
    public String getShowSpec(long id) {
        QueryParam queryParam = QueryParam.getInstance();
        queryParam.addParam("goodsSpecValue.goodsSpec.no", "spec_1_capacity_1812010048954322");
        queryParam.addParam("goodsSku.id", id);
        GoodsSkuSpecValue skuSpecValue = super.findByCriteriaForUnique(queryParam);
        return skuSpecValue == null ? "" : skuSpecValue.getGoodsSpecValue().getValue();
    }

    /**
     * 根据规格值列表获取商品list
     *
     * @param specValueList
     * @return
     */
    @Override
    public List<Long> findBySpecValueList(List<Long> specValueList) {
        List<Long> goodsSkus = new ArrayList<>();
        for (Long specValueId : specValueList) {
            //根据specValue获取skuSpecValue
            List<Long> specValue = findBySpecValue(specValueId);

            if (goodsSkus == null || goodsSkus.size() <= 0) {
                goodsSkus.addAll(specValue);
            }
            goodsSkus.retainAll(specValue);
            if (goodsSkus == null || goodsSkus.size() <= 0) {
                return null;
            }
        }
        return goodsSkus;
    }

    /**
     * 根据商品spu获取列表
     *
     * @param id
     * @return
     */
    @Override
    public List<GoodsSpecValue> findBySpuId(long id, int onSale) {
        ArrayList<GoodsSpecValue> goodsSpecValues = new ArrayList<>();
        QueryParam queryParam = QueryParam.getInstance();
        queryParam.addParam("goodsSku.goodsSpu.id", id);
        if (onSale != 0) {
            queryParam.addParam("goodsSku.onSale", onSale);
        }
        List<GoodsSkuSpecValue> list = super.findByCriteria(queryParam);
        for (GoodsSkuSpecValue goodsSkuSpecValue : list) {
            goodsSpecValues.add(GoodsSpecValueModel.instance(goodsSkuSpecValue.getGoodsSpecValue()));
        }
        return goodsSpecValues;
    }

    /**
     * 根据商品id获取可选参数值
     *
     * @param id
     * @return
     */
    @Override
    public String getSkuChooseSpecValueBySkuId(long id) {
        String result = "";
        QueryParam queryParam = QueryParam.getInstance();
        queryParam.addParam("goodsSku.id", id);
        queryParam.addParam("goodsSpecValue.goodsSpec.type", BaseConstant.BUSINESS_STATE_YES);
        List<GoodsSkuSpecValue> goodsSkuSpecValueList = super.findByCriteria(queryParam);
        for (int i = 0; i < goodsSkuSpecValueList.size(); i++) {
            result += goodsSkuSpecValueList.get(i).getGoodsSpecValue().getValue() + (i == goodsSkuSpecValueList.size() - 1 ? "" : ",");
        }
        return result;
    }

    /**
     * 根据specValue获取skuSpecValue
     *
     * @param specValueId
     */
    private List<Long> findBySpecValue(Long specValueId) {
        QueryParam queryParam = QueryParam.getInstance();
        queryParam.addParam("goodsSpecValue.id", specValueId);
        List<GoodsSkuSpecValue> goodsSkuSpecValueList = super.findByCriteria(queryParam);
        Set<Long> goodsSkus = new HashSet<>();
        for (GoodsSkuSpecValue goodsSkuSpecValue : goodsSkuSpecValueList) {
            goodsSkus.add(goodsSkuSpecValue.getGoodsSku().getId());
        }
        return new ArrayList<>(goodsSkus);
    }
}