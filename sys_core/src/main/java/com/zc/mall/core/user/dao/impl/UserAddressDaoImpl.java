package com.zc.mall.core.user.dao.impl;

import com.zc.mall.core.user.dao.UserAddressDao;
import com.zc.mall.core.user.entity.UserAddress;
import com.zc.mall.core.user.model.UserAddressModel;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.model.jpa.OrderFilter;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import com.zc.sys.common.model.jpa.SearchFilter;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Repository;

/**
 * 用户地址
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@Repository
public class UserAddressDaoImpl extends BaseDaoImpl<UserAddress> implements UserAddressDao {

    /**
     * 查询用户地址列表
     *
     * @param model
     * @return
     */
    @Override
    public PageDataList<UserAddress> list(UserAddressModel model) {
        QueryParam param = QueryParam.getInstance();
        if (StringUtil.isNotBlank(model.getSearchName())) {
            SearchFilter orFilter1 = new SearchFilter("name", StringUtil.isNull(model.getSearchName()));
            SearchFilter orFilter2 = new SearchFilter("mobile", StringUtil.isNull(model.getSearchName()));
            param.addOrFilter(orFilter1, orFilter2);
        }
        if (StringUtil.isNotBlank(model.getAddress())) {
            param.addParam("address", model.getAddress());
        }
        if (StringUtil.isNotBlank(model.getArea())) {
            param.addParam("area", model.getArea());
        }
        if (StringUtil.isNotBlank(model.getCity())) {
            param.addParam("city", model.getCity());
        }
        if (StringUtil.isNotBlank(model.getMobile())) {
            param.addParam("mobile", model.getMobile());
        }
        if (StringUtil.isNotBlank(model.getPostCode())) {
            param.addParam("postCode", model.getPostCode());
        }
        if (StringUtil.isNotBlank(model.getProvince())) {
            param.addParam("province", model.getProvince());
        }
        if (model.getState() != 0) {
            param.addParam("state", model.getState());
        }
        if (StringUtil.isNotBlank(model.getStates())) {
            param.addOrFilter("state", model.getStates().split(","));
        }
        if (StringUtil.isNotBlank(model.getName())) {
            param.addParam("name", model.getName());
        }
        if (model.getUser() != null && model.getUser().getId() > 0) {
            param.addParam("user.id", model.getUser().getId());
        }

        param.addOrder(OrderFilter.OrderType.DESC, "state");
        param.addOrder(OrderFilter.OrderType.DESC, "addTime");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }

    @Override
    public void updateDefault() {
        QueryParam param = QueryParam.getInstance();
        param.addParam("state", 1);
        UserAddress userAddress = super.findByCriteriaForUnique(param);
        if (userAddress != null) {
            userAddress.setState(0);
            super.update(userAddress);
        }
    }
}