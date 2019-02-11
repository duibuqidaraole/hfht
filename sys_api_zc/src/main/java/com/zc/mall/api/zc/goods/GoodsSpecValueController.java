package com.zc.mall.api.zc.goods;

import com.zc.mall.core.common.web.BaseController;
import com.zc.mall.mall.model.GoodsSpecValueModel;
import com.zc.mall.mall.service.GoodsSpecValueService;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 商品规格值
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@RestController
@RequestMapping("/g/goodsspecvalue")
public class GoodsSpecValueController extends BaseController<GoodsSpecValueModel> {

    @Resource
    GoodsSpecValueService goodsSpecValueService;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(GoodsSpecValueModel model) throws BusinessException {
        return goodsSpecValueService.list(model);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(GoodsSpecValueModel model) throws BusinessException {
        return goodsSpecValueService.add(model);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(GoodsSpecValueModel model) throws BusinessException {
        return goodsSpecValueService.update(model);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(GoodsSpecValueModel model) throws BusinessException {
        return goodsSpecValueService.getById(model);
    }
}