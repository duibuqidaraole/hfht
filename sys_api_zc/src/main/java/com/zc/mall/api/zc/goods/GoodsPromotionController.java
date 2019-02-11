package com.zc.mall.api.zc.goods;

import com.zc.mall.core.common.web.BaseController;
import com.zc.mall.mall.model.GoodsPromotionModel;
import com.zc.mall.mall.service.GoodsPromotionService;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 商品活动
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年12月24日
 */
@RestController
@RequestMapping("/g/goodspromotion")
public class GoodsPromotionController extends BaseController<GoodsPromotionModel> {

    @Resource
    GoodsPromotionService GoodsPromotionService;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(GoodsPromotionModel model) throws BusinessException {
        return GoodsPromotionService.list(model);
    }

    /**
     * 无分页列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/checkList", method = RequestMethod.POST)
    @ResponseBody
    public Object checkList(GoodsPromotionModel model) throws BusinessException {
        return GoodsPromotionService.checkList(model);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(GoodsPromotionModel model) throws BusinessException {
        return GoodsPromotionService.add(model);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(GoodsPromotionModel model) throws BusinessException {
        return GoodsPromotionService.update(model);
    }



    /**
     * 获取
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(GoodsPromotionModel model) throws BusinessException {
        return GoodsPromotionService.getById(model);
    }
}