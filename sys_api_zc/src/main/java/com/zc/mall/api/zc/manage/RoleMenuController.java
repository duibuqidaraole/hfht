package com.zc.mall.api.zc.manage;

import com.zc.mall.core.common.web.BaseController;
import com.zc.mall.core.manage.model.RoleMenuModel;
import com.zc.mall.core.manage.service.RoleMenuService;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 角色-菜单
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@RestController
@RequestMapping("/m/roleMenu")
public class RoleMenuController extends BaseController<RoleMenuModel> {

    @Resource
    RoleMenuService roleMenuService;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(RoleMenuModel model) throws BusinessException {
        return roleMenuService.list(model);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(RoleMenuModel model) throws BusinessException {
        return roleMenuService.add(model);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(RoleMenuModel model) throws BusinessException {
        return roleMenuService.update(model);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(RoleMenuModel model) throws BusinessException {
        return roleMenuService.getById(model);
    }
}