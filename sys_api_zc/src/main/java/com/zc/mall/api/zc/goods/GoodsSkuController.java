package com.zc.mall.api.zc.goods;

import com.zc.mall.core.common.web.BaseController;
import com.zc.mall.mall.model.GoodsSkuModel;
import com.zc.mall.mall.service.GoodsSkuService;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 商品SKU
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@RestController
@RequestMapping("/g/goodssku")
public class GoodsSkuController extends BaseController<GoodsSkuModel> {

    @Resource
    GoodsSkuService goodsSkuService;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(GoodsSkuModel model) throws BusinessException {
        return goodsSkuService.list(model);
    }

    /**
     * 前台列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/listForReception", method = RequestMethod.POST)
    @ResponseBody
    public Object listForReception(GoodsSkuModel model) throws BusinessException {
        return goodsSkuService.listForReception(model);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(GoodsSkuModel model) throws BusinessException {
        return goodsSkuService.add(model);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(GoodsSkuModel model) throws BusinessException {
        return goodsSkuService.update(model);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(GoodsSkuModel model) throws BusinessException {
        return goodsSkuService.getById(model);
    }

    /**
     * 获取热门商品
     *
     * @return
     */
    @RequestMapping(value = "/getHot", method = RequestMethod.POST)
    @ResponseBody
    public Object getHot() throws BusinessException {
        return goodsSkuService.getHot();
    }

    /**
     * 根据商品的spu和规格值获取sku
     *
     * @return
     */
    @RequestMapping(value = "/getChooseSku", method = RequestMethod.POST)
    @ResponseBody
    public Object getChooseSku(GoodsSkuModel model) throws BusinessException {
        return goodsSkuService.getChooseSku(model);
    }


}