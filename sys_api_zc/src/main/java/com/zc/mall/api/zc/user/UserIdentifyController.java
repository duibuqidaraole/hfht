package com.zc.mall.api.zc.user;

import com.zc.mall.core.common.web.BaseController;
import com.zc.mall.core.user.model.UserIdentifyModel;
import com.zc.mall.core.user.service.UserIdentifyService;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户认证
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@RestController
@RequestMapping("/u/useridentify")
public class UserIdentifyController extends BaseController<UserIdentifyModel> {

    @Resource
    UserIdentifyService userIdentifyService;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(UserIdentifyModel model) throws BusinessException {
        return userIdentifyService.list(model);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(UserIdentifyModel model) throws BusinessException {
        return userIdentifyService.add(model);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(UserIdentifyModel model) throws BusinessException {
        return userIdentifyService.update(model);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(UserIdentifyModel model) throws BusinessException {
        return userIdentifyService.getById(model);
    }

    /**
     * 通过用户ID获取
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getByUserId", method = RequestMethod.POST)
    @ResponseBody
    public Object getByUserId(UserIdentifyModel model) throws BusinessException {
        return userIdentifyService.getByUserId(model);
    }

    /**
     * 实名认证
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/realName", method = RequestMethod.POST)
    @ResponseBody
    public Object realName(UserIdentifyModel model) throws BusinessException {
        return userIdentifyService.realNameRequest(model);
    }

    /**
     * 实名认证-法人
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/realNameArtif", method = RequestMethod.POST)
    @ResponseBody
    public Object realNameArtif(UserIdentifyModel model) throws BusinessException {
        return userIdentifyService.realNameArtifRequest(model);
    }

    /**
     * 实名认证处理
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/realNameDeal", method = RequestMethod.POST)
    @ResponseBody
    public Object realNameDeal(UserIdentifyModel model) throws BusinessException {
        return userIdentifyService.realNameDeal(model);
    }

    /**
     * 像素聚合实名认证
     *
     * @param model
     * @return
     */
    //@RequestMapping(value = "/realName", method = RequestMethod.POST)
    @ResponseBody
    public Object realNameTest(UserIdentifyModel model) throws BusinessException {
        return userIdentifyService.realNameTest(model);
    }


}