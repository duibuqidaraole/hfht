package com.zc.mall.api.zc.goods;

import com.zc.mall.core.common.web.BaseController;
import com.zc.mall.mall.model.GoodsSkuSpecValueModel;
import com.zc.mall.mall.service.GoodsSkuSpecValueService;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 商品sku-规格值
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@RestController
@RequestMapping("/g/goodsskuspecvalue")
public class GoodsSkuSpecValueController extends BaseController<GoodsSkuSpecValueModel> {

    @Resource
    GoodsSkuSpecValueService goodsSkuSpecValueService;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(GoodsSkuSpecValueModel model) throws BusinessException {
        return goodsSkuSpecValueService.list(model);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(GoodsSkuSpecValueModel model) throws BusinessException {
        return goodsSkuSpecValueService.add(model);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(GoodsSkuSpecValueModel model) throws BusinessException {
        return goodsSkuSpecValueService.update(model);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(GoodsSkuSpecValueModel model) throws BusinessException {
        return goodsSkuSpecValueService.getById(model);
    }

    /**
     * 根据spu获取
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getBySpuId", method = RequestMethod.POST)
    @ResponseBody
    public Object getBySpuId(GoodsSkuSpecValueModel model) throws BusinessException {
        return goodsSkuSpecValueService.getBySpuId(model);
    }

    /**
     * 获取有库存的规格
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getOnSalesList", method = RequestMethod.POST)
    @ResponseBody
    public Object getOnSalesList(GoodsSkuSpecValueModel model) throws BusinessException {
        return goodsSkuSpecValueService.getOnSalesList(model);
    }

    /**
     * 一步添加商品
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/addSkuAllIn", method = RequestMethod.POST)
    @ResponseBody
    public Object addSkuAllIn(GoodsSkuSpecValueModel model) throws BusinessException {
        return goodsSkuSpecValueService.addSkuAllIn(model);
    }

    /**
     * 一步修改商品
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateSkuAllIn", method = RequestMethod.POST)
    @ResponseBody
    public Object updateSkuAllIn(GoodsSkuSpecValueModel model) throws BusinessException {
        return goodsSkuSpecValueService.updateSkuAllIn(model);
    }

}