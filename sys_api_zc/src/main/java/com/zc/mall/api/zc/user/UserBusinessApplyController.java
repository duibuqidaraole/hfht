package com.zc.mall.api.zc.user;

import com.zc.mall.core.user.model.UserBusinessApplyModel;
import com.zc.mall.core.user.service.UserBusinessApplyService;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 商家申请信息
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@RestController
@RequestMapping("/u/userBusinessApply")
public class UserBusinessApplyController {
    @Resource
    UserBusinessApplyService userBusinessApplyService;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(UserBusinessApplyModel model) throws BusinessException {
        return userBusinessApplyService.list(model);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(UserBusinessApplyModel model) throws BusinessException {
        return userBusinessApplyService.add(model);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(UserBusinessApplyModel model) throws BusinessException {
        return userBusinessApplyService.update(model);
    }

    /**
     * 去除商家申请
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/delRegRequest", method = RequestMethod.POST)
    @ResponseBody
    public Object delRegRequest(UserBusinessApplyModel model) throws BusinessException {
        return userBusinessApplyService.delRegRequest(model);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(UserBusinessApplyModel model) throws BusinessException {
        return userBusinessApplyService.getById(model);
    }
}
