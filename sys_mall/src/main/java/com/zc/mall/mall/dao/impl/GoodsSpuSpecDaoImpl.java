package com.zc.mall.mall.dao.impl;

import com.zc.mall.mall.dao.GoodsSpecDao;
import com.zc.mall.mall.dao.GoodsSpuSpecDao;
import com.zc.mall.mall.entity.GoodsSpec;
import com.zc.mall.mall.entity.GoodsSpuSpec;
import com.zc.mall.mall.model.GoodsSpuSpecModel;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.model.jpa.OrderFilter;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品SPU-规格
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@Repository
public class GoodsSpuSpecDaoImpl extends BaseDaoImpl<GoodsSpuSpec> implements GoodsSpuSpecDao {

    @Resource
    private GoodsSpecDao goodsSpecDao;

    @Override
    public PageDataList<GoodsSpuSpec> list(GoodsSpuSpecModel model) {
        QueryParam param = QueryParam.getInstance();

        if (model.getUpdateOperator() != null && model.getUpdateOperator().getId() > 0) {
            param.addParam("updateOperator.id", model.getUpdateOperator().getId());
        }
        if (model.getAddOperator() != null && model.getAddOperator().getId() > 0) {
            param.addParam("addOperator.id", model.getAddOperator().getId());
        }

        if (model.getGoodsSpec() != null && model.getGoodsSpec().getId() > 0) {
            param.addParam("goodsSpec.id", model.getGoodsSpec().getId());
        }
        if (model.getGoodsSpec() != null && model.getGoodsSpec().getType() > 0) {
            param.addParam("goodsSpec.type", model.getGoodsSpec().getType());
        }
        if (model.getGoodsSpu() != null && model.getGoodsSpu().getId() > 0) {
            param.addParam("goodsSpu.id", model.getGoodsSpu().getId());
        }

        param.addOrder(OrderFilter.OrderType.DESC, "id");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }

    /**
     * 查询是否有重复信息
     *
     * @param model
     * @return true 有重复
     */
    @Override
    public boolean checkSpecAndSpu(GoodsSpuSpecModel model) {
        if (model.getGoodsSpu() == null || model.getGoodsSpu().getId() <= 0 || model.getGoodsSpec() == null || model.getGoodsSpec().getId() <= 0) {
            throw new BusinessException("查询条件不足");
        }
        QueryParam queryParam = QueryParam.getInstance();
        queryParam.addParam("goodsSpu.id", model.getGoodsSpu().getId());
        queryParam.addParam("goodsSpec.id", model.getGoodsSpec().getId());
        List<GoodsSpuSpec> list = super.findByCriteria(queryParam);
        if (list != null && list.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 未选择列表
     *
     * @param model
     * @return
     */
    @Override
    public List<GoodsSpec> unCheckList(GoodsSpuSpecModel model) {
        QueryParam param = QueryParam.getInstance();
        List<GoodsSpec> listAll = goodsSpecDao.findByCriteria(param);
        if (model.getGoodsSpu() != null && model.getGoodsSpu().getId() > 0) {
            param.addParam("goodsSpu.id", model.getGoodsSpu().getId());
        }
        //获取规格列表
        List<GoodsSpuSpec> listChecked = super.findByCriteria(param);
        //获取规格id列表
        ArrayList<Long> listCheckedSpecId = new ArrayList<>();
        for (GoodsSpuSpec goodsSpuSpec : listChecked) {
            listCheckedSpecId.add(goodsSpuSpec.getGoodsSpec().getId());
        }
        ArrayList<GoodsSpec> goodsSpecArrayList = new ArrayList<>();
        for (GoodsSpec goodsSpec : listAll) {
            if (!listCheckedSpecId.contains(goodsSpec.getId())) {
                goodsSpecArrayList.add(goodsSpec);
            }
        }
        return goodsSpecArrayList;
    }
}