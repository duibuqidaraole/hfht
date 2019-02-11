package com.zc.mall.api.zc.promotion;

import com.zc.mall.core.common.web.BaseController;
import com.zc.mall.promotion.model.BonusCouponsModel;
import com.zc.mall.promotion.service.BonusCouponsService;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 红包
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年12月20日
 */
@RestController
@RequestMapping("/pt/bonuscoupons")
public class BonusCouponsController extends BaseController<BonusCouponsModel> {

    @Resource
    BonusCouponsService bonusCouponsService;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(BonusCouponsModel model) throws BusinessException {
        return bonusCouponsService.list(model);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(BonusCouponsModel model) throws BusinessException {
        return bonusCouponsService.add(model);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(BonusCouponsModel model) throws BusinessException {
        return bonusCouponsService.update(model);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(BonusCouponsModel model) throws BusinessException {
        return bonusCouponsService.getById(model);
    }
}