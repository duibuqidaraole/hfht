package com.zc.mall.core.user.dao;

import com.zc.mall.core.user.entity.UserBusinessApply;
import com.zc.mall.core.user.model.UserBusinessApplyModel;
import com.zc.sys.common.dao.BaseDao;
import com.zc.sys.common.model.jpa.PageDataList;

public interface UserBusinessApplyDao extends BaseDao<UserBusinessApply> {
    /**
     * 列表
     *
     * @param model
     * @return
     */
    PageDataList<UserBusinessApply> list(UserBusinessApplyModel model);
}
