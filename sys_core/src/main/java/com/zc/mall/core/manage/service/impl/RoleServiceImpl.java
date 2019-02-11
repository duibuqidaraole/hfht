package com.zc.mall.core.manage.service.impl;

import com.zc.mall.core.manage.dao.OperatorDao;
import com.zc.mall.core.manage.dao.RoleDao;
import com.zc.mall.core.manage.dao.RoleMenuDao;
import com.zc.mall.core.manage.entity.Operator;
import com.zc.mall.core.manage.entity.Role;
import com.zc.mall.core.manage.entity.RoleMenu;
import com.zc.mall.core.manage.model.OperatorModel;
import com.zc.mall.core.manage.model.RoleMenuModel;
import com.zc.mall.core.manage.model.RoleModel;
import com.zc.mall.core.manage.service.RoleService;
import com.zc.mall.core.sys.dao.MenuDao;
import com.zc.mall.core.sys.entity.Menu;
import com.zc.mall.core.sys.model.MenuModel;
import com.zc.mall.core.user.model.UserInfoModel;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;
    @Resource
    private RoleMenuDao roleMenuDao;
    @Resource
    private MenuDao menuDao;
    @Resource
    private OperatorDao operatorDao;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result list(RoleModel model) {
        PageDataList<Role> pageDataList = roleDao.list(model);
        PageDataList<RoleModel> pageDataList_ = new PageDataList<RoleModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<RoleModel> list = new ArrayList<RoleModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (Role role : pageDataList.getList()) {
                RoleModel model_ = RoleModel.instance(role);
                model_.setOperatorModel(OperatorModel.instance(role
                        .getOperator()));
                model_.setBeUserModel(UserModel.instance(role.getBeUser()));
                if (role.getBeUser() != null) {
                    model_.setBeUserInfoModel(UserInfoModel.instance(role
                            .getBeUser().getUserInfo()));
                }
                list.add(model_);
            }
        }

        pageDataList_.setList(list);

        return Result.success().setData(pageDataList_);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result add(RoleModel model) {
        String menuIdStr = model.getMenuIdStr();
        if (StringUtil.isBlank(menuIdStr)) {
            return Result.error("请选择角色对应的菜单！");
        }
        String[] menuIdArr = menuIdStr.split(",");
        Role role = model.prototype();
        Role r = roleDao.save(role);

        for (int i = 0; i < menuIdArr.length; i++) {
            long menuId = Long.parseLong(menuIdArr[i]);
            Menu menu = menuDao.find(menuId);
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setMenu(menu);
            roleMenu.setRole(r);
            roleMenuDao.save(roleMenu);
        }
        roleDao.clear();
        return Result.success().setData(role);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result update(RoleModel model) {
        String menuIdStr = model.getMenuIdStr();
        if (StringUtil.isBlank(menuIdStr)) {
            return Result.error("请选择角色对应的菜单！");
        }
        String[] menuIdArr = menuIdStr.split(",");
        Role role = roleDao.find(model.getId());
        model.setUpdateParam(role);// 设置基本参数
        Role r = roleDao.update(role);// 更新
        roleMenuDao.deleteByRoleId(role.getId());// 删除原菜单
        // 保存新菜单
        for (int i = 0; i < menuIdArr.length; i++) {
            long menuId = Long.parseLong(menuIdArr[i]);
            Menu menu = menuDao.find(menuId);
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setMenu(menu);
            roleMenu.setRole(r);
            roleMenuDao.save(roleMenu);
        }
        return Result.success().setData(model.prototype());
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result getById(RoleModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        Role role = roleDao.find(model.getId());
        List<RoleMenu> list = new ArrayList<RoleMenu>();
        for (RoleMenu menu : role.getRoleMenus()) {
            RoleMenuModel model_ = RoleMenuModel.instance(menu);
            if (menu.getMenu() != null) {
                model_.setMenuModel(MenuModel.instance(menu.getMenu()));
            }
            list.add(model_);
        }
        role.setRoleMenus(list);
        RoleModel model_ = RoleModel.instance(role);
        Operator operator = role.getOperator();
        model_.setOperatorModel(OperatorModel.instance(operator));
        model_.setOperatorModel(OperatorModel.instance(role.getOperator()));
        model_.setBeUserModel(UserModel.instance(role.getBeUser()));
        if (role.getBeUser() != null) {
            model_.setBeUserInfoModel(UserInfoModel.instance(role.getBeUser()
                    .getUserInfo()));
        }
        return Result.success().setData(model_);
    }

}