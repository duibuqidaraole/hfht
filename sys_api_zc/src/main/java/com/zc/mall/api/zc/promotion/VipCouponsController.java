package com.zc.mall.api.zc.promotion;

import com.zc.mall.core.common.web.BaseController;
import com.zc.mall.promotion.model.VipCouponsModel;
import com.zc.mall.promotion.service.VipCouponsService;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.form.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * vip会员卡
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@RestController
@RequestMapping("/pt/vipcoupons")
public class VipCouponsController extends BaseController<VipCouponsModel> {

    @Resource
    VipCouponsService vipCouponsService;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(VipCouponsModel model) throws BusinessException {
        return vipCouponsService.list(model);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(VipCouponsModel model) throws BusinessException {
        return vipCouponsService.add(model);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(VipCouponsModel model) throws BusinessException {
        return vipCouponsService.update(model);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(VipCouponsModel model) throws BusinessException {
        return Result.success().setData(vipCouponsService.getById(model));
    }
}