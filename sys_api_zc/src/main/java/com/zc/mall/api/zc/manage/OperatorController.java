package com.zc.mall.api.zc.manage;

import com.zc.mall.core.common.web.BaseController;
import com.zc.mall.core.manage.model.OperatorModel;
import com.zc.mall.core.manage.service.OperatorService;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 管理员
 *
 * @author zy
 * @version 2.0.0.0
 * @since 2018年9月06日
 */
@RestController
@RequestMapping("/m/operator")
public class OperatorController extends BaseController<OperatorModel> {

    @Resource
    OperatorService operatorService;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(OperatorModel model) throws BusinessException {
        return operatorService.list(model);
    }

    /**
     * 添加后台商家
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/addRelation", method = RequestMethod.POST)
    @ResponseBody
    public Object addRelation(OperatorModel model) throws BusinessException {
        return operatorService.addRelation(model);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(OperatorModel model) throws BusinessException {
        return operatorService.add(model);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(OperatorModel model) throws BusinessException {
        return operatorService.update(model);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(OperatorModel model) throws BusinessException {
        return operatorService.getById(model);
    }

    /**
     * 登录
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    @ResponseBody
    public Object signIn(OperatorModel model) throws BusinessException {
        return operatorService.signIn(model);
    }

    /**
     * 通过用户Id查询管理员信息
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getByUserId", method = RequestMethod.POST)
    @ResponseBody
    public Object getByUserId(OperatorModel model) throws BusinessException {
        return operatorService.getByUserId(model);
    }
}