package com.zc.mall.api.zc.goods;

import com.zc.mall.core.common.web.BaseController;
import com.zc.mall.mall.model.GoodsPromotionRecordModel;
import com.zc.mall.mall.service.GoodsPromotionRecordService;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 商品活动记录
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年12月24日
 */
@RestController
@RequestMapping("/g/goodspromotionrecord")
public class GoodsPromotionRecordController extends BaseController<GoodsPromotionRecordModel> {

    @Resource
    GoodsPromotionRecordService GoodsPromotionRecordService;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(GoodsPromotionRecordModel model) throws BusinessException {
        return GoodsPromotionRecordService.list(model);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(GoodsPromotionRecordModel model) throws BusinessException {
        return GoodsPromotionRecordService.add(model);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(GoodsPromotionRecordModel model) throws BusinessException {
        return GoodsPromotionRecordService.update(model);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(GoodsPromotionRecordModel model) throws BusinessException {
        return GoodsPromotionRecordService.getById(model);
    }

    /**
     * 删除
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object delete(GoodsPromotionRecordModel model) throws BusinessException {
        return GoodsPromotionRecordService.delete(model);
    }
}