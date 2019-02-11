package com.zc.mall.mall.dao.impl;

import com.zc.mall.mall.dao.GoodsCategoryDao;
import com.zc.mall.mall.entity.GoodsCategory;
import com.zc.mall.mall.model.GoodsCategoryModel;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.model.jpa.OrderFilter;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Repository;

/**
 * 商品分类
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@Repository
public class GoodsCategoryDaoImpl extends BaseDaoImpl<GoodsCategory> implements GoodsCategoryDao {

    /**
     * 查询商品分类列表
     *
     * @param model
     * @return
     */
    @Override
    public PageDataList<GoodsCategory> list(GoodsCategoryModel model) {
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

        param.addOrder(OrderFilter.OrderType.DESC, "addTime");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }
}