package com.zc.mall.core.user.dao;

import com.zc.mall.core.user.entity.UserAddress;
import com.zc.mall.core.user.model.UserAddressModel;
import com.zc.sys.common.dao.BaseDao;
import com.zc.sys.common.model.jpa.PageDataList;

/**
 * 用户地址
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
public interface UserAddressDao extends BaseDao<UserAddress> {
    /**
     * 查询用户地址列表
     *
     * @param model
     * @return
     */
    PageDataList<UserAddress> list(UserAddressModel model);

    void updateDefault();

}