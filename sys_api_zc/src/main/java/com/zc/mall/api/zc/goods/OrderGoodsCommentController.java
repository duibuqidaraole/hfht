package com.zc.mall.api.zc.goods;

import com.zc.mall.core.common.web.BaseController;
import com.zc.mall.mall.model.OrderGoodsCommentModel;
import com.zc.mall.mall.service.OrderGoodsCommentService;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.form.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 订单评论
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@RestController
@RequestMapping("/g/ordergoodscomment")
public class OrderGoodsCommentController extends BaseController<OrderGoodsCommentModel> {

    @Resource
    OrderGoodsCommentService orderGoodsCommentService;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(OrderGoodsCommentModel model) throws BusinessException {
        return orderGoodsCommentService.list(model);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(OrderGoodsCommentModel model) throws BusinessException {
        return orderGoodsCommentService.add(model);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(OrderGoodsCommentModel model) throws BusinessException {
        return orderGoodsCommentService.update(model);
    }

    /**
     * 审核
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/passComment", method = RequestMethod.POST)
    @ResponseBody
    public Object passComment(OrderGoodsCommentModel model) throws BusinessException {
        return orderGoodsCommentService.passComment(model);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(OrderGoodsCommentModel model) throws BusinessException {
        return orderGoodsCommentService.getById(model);
    }

    /**
     * 评论权限查询
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/isComment", method = RequestMethod.POST)
    @ResponseBody
    public Object isComment(OrderGoodsCommentModel model) throws BusinessException {
        return Result.success().setData(orderGoodsCommentService.isComment(model));
    }

    /**
     * 删除评论
     *
     * @param model
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/deleteComment", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteComment(OrderGoodsCommentModel model) throws BusinessException {
        return orderGoodsCommentService.deleteComment(model);
    }
}