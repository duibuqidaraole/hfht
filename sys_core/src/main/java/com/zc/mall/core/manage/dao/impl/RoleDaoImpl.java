package com.zc.mall.core.manage.dao.impl;

import com.zc.mall.core.manage.dao.RoleDao;
import com.zc.mall.core.manage.entity.Role;
import com.zc.mall.core.manage.model.RoleModel;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.model.jpa.OrderFilter.OrderType;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import com.zc.sys.common.model.jpa.SearchFilter;
import com.zc.sys.common.model.jpa.SearchFilter.Operators;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Repository;

/**
 * 角色
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    public PageDataList<Role> list(RoleModel model) {
        QueryParam param = QueryParam.getInstance();
        if (StringUtil.isNotBlank(model.getSearchName())) {
            SearchFilter orFilter1 = new SearchFilter("operator.name", Operators.LIKE, model.getSearchName().trim());
            SearchFilter orFilter2 = new SearchFilter("name", Operators.LIKE, model.getSearchName().trim());

            param.addOrFilter(orFilter1, orFilter2);
        } else {
            if (StringUtil.isNotBlank(model.getName())) {
                param.addParam("name", Operators.LIKE, model.getName().trim());
            }
        }
        if (model.getState() != 0) {
            param.addParam("state", model.getState());
        }
        param.addOrder(OrderType.ASC, "id");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }

}