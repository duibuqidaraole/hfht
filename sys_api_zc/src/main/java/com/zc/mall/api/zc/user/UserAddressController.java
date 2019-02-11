package com.zc.mall.api.zc.user;

import com.zc.mall.core.common.web.BaseController;
import com.zc.mall.core.user.model.UserAddressModel;
import com.zc.mall.core.user.service.UserAddressService;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户地址
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@RestController
@RequestMapping("/u/useraddress")
public class UserAddressController extends BaseController<UserAddressModel> {

    @Resource
    UserAddressService userAddressService;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(UserAddressModel model) throws BusinessException {
        return userAddressService.list(model);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(UserAddressModel model) throws BusinessException {
        return userAddressService.add(model);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(UserAddressModel model) throws BusinessException {
        return userAddressService.update(model);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(UserAddressModel model) throws BusinessException {
        return userAddressService.getById(model);
    }
}