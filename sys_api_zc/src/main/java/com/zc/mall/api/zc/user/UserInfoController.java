package com.zc.mall.api.zc.user;

import com.zc.mall.core.common.web.BaseController;
import com.zc.mall.core.user.model.UserInfoModel;
import com.zc.mall.core.user.service.UserInfoService;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户信息
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@RestController
@RequestMapping("/u/userinfo")
public class UserInfoController extends BaseController<UserInfoModel> {

    @Resource
    UserInfoService userInfoService;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(UserInfoModel model) throws BusinessException {
        return userInfoService.list(model);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(UserInfoModel model) throws BusinessException {
        return userInfoService.add(model);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(UserInfoModel model) throws BusinessException {
        return userInfoService.update(model);
    }

    /**
     * 修改商家用户信息
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateRelationUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public Object updateRelationUserInfo(UserInfoModel model) throws BusinessException {
        return userInfoService.updateRelationUserInfo(model);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(UserInfoModel model) throws BusinessException {
        return userInfoService.getById(model);
    }

    /**
     * 通过Id删除
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteById(UserInfoModel model) throws BusinessException {
        return userInfoService.deleteById(model);
    }

    /**
     * 通过用户Id获取用户信息
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getUserInfoByUserId", method = RequestMethod.POST)
    @ResponseBody
    public Object getUserInfoByUserId(UserInfoModel model) throws BusinessException {
        return userInfoService.getUserInfoByUserId(model);
    }


}