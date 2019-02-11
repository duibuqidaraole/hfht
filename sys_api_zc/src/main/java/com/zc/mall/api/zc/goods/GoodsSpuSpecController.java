package com.zc.mall.api.zc.goods;

import com.zc.mall.core.common.web.BaseController;
import com.zc.mall.mall.model.GoodsSpuSpecModel;
import com.zc.mall.mall.service.GoodsSpuSpecService;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 商品SPU-规格
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@RestController
@RequestMapping("/g/goodsspuspec")
public class GoodsSpuSpecController extends BaseController<GoodsSpuSpecModel> {

    @Resource
    GoodsSpuSpecService goodsSpuSpecService;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(GoodsSpuSpecModel model) throws BusinessException {
        return goodsSpuSpecService.list(model);
    }

    /**
     * 未选择列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/unCheckList", method = RequestMethod.POST)
    @ResponseBody
    public Object unCheckList(GoodsSpuSpecModel model) throws BusinessException {
        return goodsSpuSpecService.unCheckList(model);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(GoodsSpuSpecModel model) throws BusinessException {
        return goodsSpuSpecService.add(model);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(GoodsSpuSpecModel model) throws BusinessException {
        return goodsSpuSpecService.update(model);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(GoodsSpuSpecModel model) throws BusinessException {
        return goodsSpuSpecService.getById(model);
    }
}