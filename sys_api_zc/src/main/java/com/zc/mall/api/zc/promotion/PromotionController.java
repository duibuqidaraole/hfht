package com.zc.mall.api.zc.promotion;

import com.zc.mall.core.common.web.BaseController;
import com.zc.mall.promotion.model.PromotionModel;
import com.zc.mall.promotion.service.PromotionService;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 活动推广
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@RestController
@RequestMapping("/pt/promotion")
public class PromotionController extends BaseController<PromotionModel> {

    @Resource
    PromotionService promotionService;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(PromotionModel model) throws BusinessException {
        return promotionService.list(model);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(PromotionModel model) throws BusinessException {
        return promotionService.add(model);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(PromotionModel model) throws BusinessException {
        return promotionService.update(model);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(PromotionModel model) throws BusinessException {
        return promotionService.getById(model);
    }
}