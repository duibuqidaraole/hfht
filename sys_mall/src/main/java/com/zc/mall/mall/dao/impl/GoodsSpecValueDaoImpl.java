package com.zc.mall.mall.dao.impl;

import com.zc.mall.mall.dao.GoodsSkuSpecValueDao;
import com.zc.mall.mall.dao.GoodsSpecValueDao;
import com.zc.mall.mall.entity.GoodsSpecValue;
import com.zc.mall.mall.model.GoodsSpecValueModel;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.model.jpa.OrderFilter;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品规格值
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@Repository
public class GoodsSpecValueDaoImpl extends BaseDaoImpl<GoodsSpecValue> implements GoodsSpecValueDao {

    @Resource
    private GoodsSkuSpecValueDao goodsSkuSpecValueDao;

    @Override
    public PageDataList<GoodsSpecValue> list(GoodsSpecValueModel model) {
        QueryParam param = QueryParam.getInstance();
        if (StringUtil.isNotBlank(model.getValue())) {
            param.addParam("value", model.getValue());
        }
        if (model.getUpdateOperator() != null && model.getUpdateOperator().getId() > 0) {
            param.addParam("updateOperator.id", model.getUpdateOperator().getId());
        }
        if (model.getAddOperator() != null && model.getAddOperator().getId() > 0) {
            param.addParam("addOperator.id", model.getAddOperator().getId());
        }
        if (model.getGoodsSpec() != null && model.getGoodsSpec().getId() > 0) {
            param.addParam("goodsSpec.id", model.getGoodsSpec().getId());
        }

        if (model.getGoodsSpec() != null && StringUtil.isNotBlank(model.getGoodsSpec().getNo())) {
            param.addParam("goodsSpec.no", model.getGoodsSpec().getNo());
        }

        param.addOrder(OrderFilter.OrderType.DESC, "addTime");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }

    /**
     * 根据规格id查询规格值
     *
     * @param id
     * @return
     */
    @Override
    public List<GoodsSpecValue> findByGoodsSpecId(long id) {
        QueryParam queryParam = QueryParam.getInstance();
        queryParam.addParam("goodsSpec.id", id);
        return super.findByCriteria(queryParam);
    }

}