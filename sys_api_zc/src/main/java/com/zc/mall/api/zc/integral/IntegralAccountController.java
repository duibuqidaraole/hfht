package com.zc.mall.api.zc.integral;

import com.zc.mall.core.common.web.BaseController;
import com.zc.mall.core.integral.model.IntegralAccountModel;
import com.zc.mall.core.integral.service.IntegralAccountService;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 积分账户
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@RestController
@RequestMapping("/jf/integralAccount")
public class IntegralAccountController extends BaseController<IntegralAccountModel> {

    @Resource
    IntegralAccountService integralAccountService;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(IntegralAccountModel model) throws BusinessException {
        return integralAccountService.list(model);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(IntegralAccountModel model) throws BusinessException {
        return integralAccountService.add(model);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(IntegralAccountModel model) throws BusinessException {
        return integralAccountService.update(model);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(IntegralAccountModel model) throws BusinessException {
        return integralAccountService.getById(model);
    }

    /**
     * 通过用户Id获取
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getByUserId", method = RequestMethod.POST)
    @ResponseBody
    public Object getByUserId(IntegralAccountModel model) throws BusinessException {
        return integralAccountService.getByUserId(model);
    }


}