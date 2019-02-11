package com.zc.mall.mall.dao.impl;

import com.zc.mall.mall.dao.GoodsSpecDao;
import com.zc.mall.mall.entity.GoodsSpec;
import com.zc.mall.mall.model.GoodsSpecModel;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.model.jpa.OrderFilter;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Repository;

/**
 * 商品规格
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@Repository
public class GoodsSpecDaoImpl extends BaseDaoImpl<GoodsSpec> implements GoodsSpecDao {

    @Override
    public PageDataList<GoodsSpec> list(GoodsSpecModel model) {
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
        if (StringUtil.isNotBlank(model.getNo())) {
            param.addParam("no", model.getNo());
        }
        param.addOrder(OrderFilter.OrderType.DESC, "addTime");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }

    @Override
    public GoodsSpec findByNo(String no) {
        QueryParam param = QueryParam.getInstance();
        param.addParam("no", no);
        return super.findByCriteriaForUnique(param);
    }
}