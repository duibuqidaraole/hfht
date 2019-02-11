package com.zc.mall.mall.service;

import com.zc.mall.mall.model.OrderGoodsCommentModel;
import com.zc.sys.common.form.Result;

/**
 * 订单评论
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
public interface OrderGoodsCommentService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(OrderGoodsCommentModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(OrderGoodsCommentModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(OrderGoodsCommentModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(OrderGoodsCommentModel model);

    /**
     * 评论权限查询
     *
     * @param model
     * @return
     */
    boolean isComment(OrderGoodsCommentModel model);

    /**
     * 自动默认评价
     */
    void autoDefaultComment();

    /**
     * 删除评论
     *
     * @param model
     * @return
     */
    Object deleteComment(OrderGoodsCommentModel model);

    Object passComment(OrderGoodsCommentModel model);
}