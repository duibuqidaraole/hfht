package com.zc.mall.mall.dao;

import com.zc.mall.mall.entity.OrderGoodsComment;
import com.zc.mall.mall.model.OrderGoodsCommentModel;
import com.zc.sys.common.dao.BaseDao;
import com.zc.sys.common.model.jpa.PageDataList;

import java.util.List;

/**
 * 订单评论
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
public interface OrderGoodsCommentDao extends BaseDao<OrderGoodsComment> {

    /**
     * 查询已经评价、回复次数
     *
     * @param model
     * @return
     */
    int countByModel(OrderGoodsCommentModel model);

    PageDataList<OrderGoodsComment> list(OrderGoodsCommentModel model);

    /**
     * 根据商品获得评论
     *
     * @param id
     * @return
     */
    List<OrderGoodsComment> findByOrderGoods(long id);

    /**
     * 获取评论回复
     *
     * @param model_
     * @return
     */
    OrderGoodsCommentModel getReplyComment(OrderGoodsCommentModel model_);

    /**
     * 获取订单评论列表
     *
     * @param id
     * @return
     */
    List<OrderGoodsCommentModel> getOrderInfoCommentList(long id);
}