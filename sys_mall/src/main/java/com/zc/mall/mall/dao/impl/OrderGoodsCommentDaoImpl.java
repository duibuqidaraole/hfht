package com.zc.mall.mall.dao.impl;

import com.zc.mall.mall.constant.OrderInfoStateEnum;
import com.zc.mall.mall.dao.OrderGoodsCommentDao;
import com.zc.mall.mall.entity.OrderGoodsComment;
import com.zc.mall.mall.model.OrderGoodsCommentModel;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.model.jpa.OrderFilter;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import com.zc.sys.common.model.jpa.SearchFilter.Operators;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单评论
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@Repository
public class OrderGoodsCommentDaoImpl extends BaseDaoImpl<OrderGoodsComment> implements OrderGoodsCommentDao {


    /**
     * 查询已经评价、回复次数
     *
     * @param model
     * @return
     */
    @Override
    public int countByModel(OrderGoodsCommentModel model) {
        QueryParam param = QueryParam.getInstance();
        if (model.getOrderGoods() != null && model.getOrderGoods().getId() > 0) {
            param.addParam("orderGoods.id", model.getOrderGoods().getId());
            if (model.getOrderGoods().getOrderInfo() != null && model.getOrderGoods().getOrderInfo().getId() > 0) {
                param.addParam("orderGoods.orderInfo.id", model.getOrderGoods().getOrderInfo().getId());
            }
        }
        if (model.getType() > 0) {
            param.addParam("type", model.getType());
        }
        if (model.getOperator() != null && model.getOperator().getId() > 0) {
            param.addParam("operator.id", Operators.GT, 0);
        }
        param.addParam("orderGoods.orderInfo.state", OrderInfoStateEnum.ORDER_INFO_STATE_COMPLETE.getOrderInfoState());
        return countByCriteria(param);
    }

    @Override
    public PageDataList<OrderGoodsComment> list(OrderGoodsCommentModel model) {
        if (model == null) {
            return null;
        }
        QueryParam queryParam = QueryParam.getInstance();
        if (model.getUser() != null && model.getUser().getId() > 0) {
            queryParam.addParam("user.id", model.getUser().getId());
        }
        if (model.getOrderGoods() != null) {
            if (model.getOrderGoods().getId() > 0) {
                queryParam.addParam("orderGoods.id", model.getOrderGoods().getId());
            }
            if (model.getOrderGoods().getGoodsSku() != null && model.getOrderGoods().getGoodsSku().getId() > 0) {
                queryParam.addParam("orderGoods.goodsSku.id", model.getOrderGoods().getGoodsSku().getId());
            }
            if (model.getOrderGoods().getGoodsSku() != null && StringUtil.isNotBlank(model.getOrderGoods().getGoodsSku().getName())) {
                queryParam.addParam("orderGoods.goodsSku.name",Operators.LIKE, model.getOrderGoods().getGoodsSku().getName());
            }
        }
        if (model.getSearchPid() != null) {
            queryParam.addParam("pid", model.getSearchPid());
        }
        if (model.getState() != 0) {
            queryParam.addParam("state", model.getState());
        }
        if (!StringUtil.isBlank(model.getStates())){
            queryParam.addOrFilter("state",model.getStates().split(","));
        }
        queryParam.addOrder(OrderFilter.OrderType.DESC, "id");
        queryParam.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(queryParam);
    }

    /**
     * 根据商品获得评论
     *
     * @param id
     * @return
     */
    @Override
    public List<OrderGoodsComment> findByOrderGoods(long id) {
        QueryParam queryParam = QueryParam.getInstance();
        queryParam.addParam("orderGoods.id", id);
        return super.findByCriteria(queryParam);
    }

    /**
     * 获取评论回复
     *
     * @param model_
     * @return
     */
    @Override
    public OrderGoodsCommentModel getReplyComment(OrderGoodsCommentModel model_) {
        QueryParam queryParam = QueryParam.getInstance();
        queryParam.addParam("pid", model_.getId());
        OrderGoodsComment goodsComment = super.findByCriteriaForUnique(queryParam);
        return goodsComment == null ? null : OrderGoodsCommentModel.instance(goodsComment);
    }

    /**
     * 获取订单评论列表
     *
     * @param id
     * @return
     */
    @Override
    public List<OrderGoodsCommentModel> getOrderInfoCommentList(long id) {
        QueryParam queryParam = QueryParam.getInstance();
        queryParam.addParam("orderGoods.orderInfo.id", id);
        queryParam.addParam("pid", 0);
        List<OrderGoodsComment> orderGoodsCommentList = super.findByCriteria(queryParam);
        ArrayList<OrderGoodsCommentModel> orderGoodsCommentModels = new ArrayList<>();
        for (OrderGoodsComment orderGoodsComment : orderGoodsCommentList) {
            OrderGoodsCommentModel orderGoodsCommentModel = OrderGoodsCommentModel.instance(orderGoodsComment);
            orderGoodsCommentModel.setReplyRecomment(getReplyComment(orderGoodsCommentModel));
            orderGoodsCommentModels.add(orderGoodsCommentModel);
        }
        return orderGoodsCommentModels;
    }

}