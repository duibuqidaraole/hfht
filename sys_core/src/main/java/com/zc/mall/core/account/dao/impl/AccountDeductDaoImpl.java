package com.zc.mall.core.account.dao.impl;

import com.zc.mall.core.account.dao.AccountDeductDao;
import com.zc.mall.core.account.entity.AccountDeduct;
import com.zc.mall.core.account.model.AccountDeductModel;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.model.jpa.OrderFilter.OrderType;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import com.zc.sys.common.model.jpa.SearchFilter;
import com.zc.sys.common.model.jpa.SearchFilter.Operators;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Repository;

/**
 * 线下扣款
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Repository
public class AccountDeductDaoImpl extends BaseDaoImpl<AccountDeduct> implements AccountDeductDao {

    @Override
    public PageDataList<AccountDeduct> list(AccountDeductModel model) {
        QueryParam param = QueryParam.getInstance();
        if (StringUtil.isNotBlank(model.getSearchName())) {
            SearchFilter orFilter3 = new SearchFilter("user.realName", Operators.LIKE, model.getSearchName().trim());
            SearchFilter orFilter4 = new SearchFilter("user.mobile", Operators.LIKE, model.getSearchName().trim());
            param.addOrFilter(orFilter3, orFilter4);
        } else {
            if (model.getState() != 0) {
                param.addParam("state", model.getState());
            }
            if (model.getType() != 0) {
                param.addParam("type", model.getType());
            }
        }
        param.addOrder(OrderType.ASC, "id");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }

}