package com.zc.mall.core.manage.dao.impl;

import com.zc.mall.core.manage.dao.RoleMenuDao;
import com.zc.mall.core.manage.entity.RoleMenu;
import com.zc.mall.core.manage.model.RoleMenuModel;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.model.jpa.OrderFilter;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * 角色-菜单
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Repository
public class RoleMenuDaoImpl extends BaseDaoImpl<RoleMenu> implements RoleMenuDao {

    /**
     * 删除角色菜单关联表信息（物理删除）
     *
     * @param roleId 角色ID
     */
    @Override
    public void deleteByRoleId(long roleId) {
        Query query = em.createNativeQuery(
                "delete from zc_m_role_menu where role_id=?1").setParameter(1,
                roleId);
        query.executeUpdate();
    }

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    public PageDataList<RoleMenu> list(RoleMenuModel model) {
        QueryParam param = QueryParam.getInstance();
        if (model.getState() != 0) {
            param.addParam("menu.state", model.getState());
        }
        if (model.getRoleId() > 0) {
            param.addParam("role.id", model.getRoleId());
        }
        param.addOrder(OrderFilter.OrderType.ASC, "id");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }

    @Override
    public List<RoleMenu> getMenuListByRoleId(RoleMenuModel model) {
        QueryParam param = QueryParam.getInstance();
        if (model.getRoleId() > 0) {
            param.addParam("role.id", model.getRoleId());
        }
        if (model.getState() != 0) {
            param.addParam("menu.state", model.getState());
        }
        return this.findByCriteria(param);
    }
}