package com.zc.mall.core.manage.dao;

import com.zc.mall.core.manage.entity.Role;
import com.zc.mall.core.manage.model.RoleModel;
import com.zc.sys.common.dao.BaseDao;
import com.zc.sys.common.model.jpa.PageDataList;

/**
 * 角色
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public interface RoleDao extends BaseDao<Role> {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    PageDataList<Role> list(RoleModel model);

}