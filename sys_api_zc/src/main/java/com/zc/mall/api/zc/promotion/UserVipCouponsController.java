package com.zc.mall.api.zc.promotion;

import com.zc.mall.core.common.web.BaseController;
import com.zc.mall.promotion.model.UserVipCouponsModel;
import com.zc.mall.promotion.service.UserVipCouponsService;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.form.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户vip会员卡
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月15日
 */
@RestController
@RequestMapping("/pt/uservipcoupons")
public class UserVipCouponsController extends BaseController<UserVipCouponsModel> {

    @Resource
    UserVipCouponsService userVipCouponsService;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(UserVipCouponsModel model) throws BusinessException {
        return userVipCouponsService.list(model);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(UserVipCouponsModel model) throws BusinessException {
        return userVipCouponsService.add(model);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(UserVipCouponsModel model) throws BusinessException {
        return userVipCouponsService.update(model);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(UserVipCouponsModel model) throws BusinessException {
        return userVipCouponsService.getById(model);
    }

    /**
     * 发放
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/giveOutVipRequest", method = RequestMethod.POST)
    @ResponseBody
    public Object giveOutVipRequest(UserVipCouponsModel model) throws BusinessException {
        return userVipCouponsService.giveOutRequest(model);
    }

    /**
     * 发放支付
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/giveOutPay", method = RequestMethod.POST)
    @ResponseBody
    public Object giveOutPay(UserVipCouponsModel model) throws BusinessException {
        return userVipCouponsService.giveOutPay(model);
    }

    /**
     * 根据用户id获取最高vip
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/giveByUserId", method = RequestMethod.POST)
    @ResponseBody
    public Object giveByUserId(UserVipCouponsModel model) throws BusinessException {
        return Result.success().setData(userVipCouponsService.giveByUserId(model.getUser().getId()));
    }

}