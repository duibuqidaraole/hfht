package com.zc.mall.mall.dao.impl;

import com.zc.mall.mall.dao.GoodsBrandDao;
import com.zc.mall.mall.entity.GoodsBrand;
import com.zc.mall.mall.model.GoodsBrandModel;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.model.jpa.OrderFilter;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Repository;

/**
 * 商品品牌表
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@Repository
public class GoodsBrandDaoImpl extends BaseDaoImpl<GoodsBrand> implements GoodsBrandDao {

    /**
     * 商品品牌搜索
     *
     * @param model
     * @return
     */
    @Override
    public PageDataList<GoodsBrand> list(GoodsBrandModel model) {
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

        param.addOrder(OrderFilter.OrderType.ASC, "id");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }


}