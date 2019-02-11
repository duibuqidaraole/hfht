package com.zc.mall.api.zc.goods;

import com.zc.mall.core.common.web.BaseController;
import com.zc.mall.mall.model.GoodsCategoryModel;
import com.zc.mall.mall.service.GoodsCategoryService;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 商品分类
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@RestController
@RequestMapping("/g/goodscategory")
public class GoodsCategoryController extends BaseController<GoodsCategoryModel> {

    @Resource
    GoodsCategoryService goodsCategoryService;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(GoodsCategoryModel model) throws BusinessException {
        return goodsCategoryService.list(model);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(GoodsCategoryModel model) throws BusinessException {
        return goodsCategoryService.add(model);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(GoodsCategoryModel model) throws BusinessException {
        return goodsCategoryService.update(model);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(GoodsCategoryModel model) throws BusinessException {
        return goodsCategoryService.getById(model);
    }
}