package com.zc.mall.core.user.dao.impl;

import com.zc.mall.core.user.dao.UserBusinessApplyDao;
import com.zc.mall.core.user.entity.UserBusinessApply;
import com.zc.mall.core.user.model.UserBusinessApplyModel;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.model.jpa.OrderFilter.OrderType;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import com.zc.sys.common.model.jpa.SearchFilter;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Repository;

/**
 * 商家申请信息
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Repository
public class UserBusinessApplyDaoImpl extends BaseDaoImpl<UserBusinessApply> implements UserBusinessApplyDao {
    @Override
    public PageDataList<UserBusinessApply> list(UserBusinessApplyModel model) {
        QueryParam param = QueryParam.getInstance();
        if (StringUtil.isNotBlank(model.getSearchName())) {
            SearchFilter orFilter2 = new SearchFilter("mobile", model.getSearchName().trim());
            param.addOrFilter(orFilter2);
        }
        if (model.getState() > 0) {
            param.addParam("state", model.getState());
        }
        param.addOrder(OrderType.DESC, "addTime");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }


}
