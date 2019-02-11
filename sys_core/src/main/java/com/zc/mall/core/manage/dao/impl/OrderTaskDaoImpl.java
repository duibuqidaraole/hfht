package com.zc.mall.core.manage.dao.impl;

import com.zc.mall.core.manage.dao.OrderTaskDao;
import com.zc.mall.core.manage.entity.OrderTask;
import com.zc.mall.core.manage.model.OrderTaskModel;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.model.jpa.OrderFilter.OrderType;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import com.zc.sys.common.model.jpa.SearchFilter;
import com.zc.sys.common.model.jpa.SearchFilter.Operators;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Repository;

/**
 * 模版配置
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Repository
public class OrderTaskDaoImpl extends BaseDaoImpl<OrderTask> implements OrderTaskDao {

    @Override
    public PageDataList<OrderTask> list(OrderTaskModel model) {
        QueryParam param = QueryParam.getInstance();
        if (StringUtil.isNotBlank(model.getSearchName())) {
            SearchFilter orFilter2 = new SearchFilter("name", Operators.LIKE,
                    model.getSearchName().trim());
            param.addOrFilter(orFilter2);
        }
        param.addOrder(OrderType.ASC, "id");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }

    /**
     * 根据订单号查询
     *
     * @param orderNo
     * @return
     */
    @Override
    public OrderTask getByOrderNo(String orderNo) {
        return this.findObjByProperty("orderNo", orderNo);
    }

}