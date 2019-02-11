package com.zc.mall.api.zc.goods;

import com.zc.mall.core.common.web.BaseController;
import com.zc.mall.mall.model.OrderGoodsModel;
import com.zc.mall.mall.service.OrderGoodsService;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 订单商品
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@RestController
@RequestMapping("/g/ordergoods")
public class OrderGoodsController extends BaseController<OrderGoodsModel> {

    @Resource
    OrderGoodsService orderGoodsService;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(OrderGoodsModel model) throws BusinessException {
        return orderGoodsService.list(model);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(OrderGoodsModel model) throws BusinessException {
        return orderGoodsService.add(model);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(OrderGoodsModel model) throws BusinessException {
        return orderGoodsService.update(model);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(OrderGoodsModel model) throws BusinessException {
        return orderGoodsService.getById(model);
    }
}