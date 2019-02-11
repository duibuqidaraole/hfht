package com.zc.mall.mall.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zc.mall.core.manage.model.OperatorModel;
import com.zc.mall.core.user.model.UserModel;
import com.zc.mall.mall.entity.OrderGoodsComment;
import com.zc.sys.common.model.jpa.Page;
import org.springframework.beans.BeanUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单评论
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
public class OrderGoodsCommentModel extends OrderGoodsComment {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;

    /**
     * 当前页码
     **/
    private int pageNo;

    /**
     * 多个状态
     **/
    private String states;
    /**
     * 每页数据条数
     **/
    private int pageSize = Page.ROWS;
    /**
     * 条件查询
     **/
    private String searchName;
    /**
     * 订单
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private OrderGoodsModel orderGoodsModel;

    /**
     * 评论人
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UserModel userModel;
    /**
     * 查询用pid可为0
     **/
    private Integer searchPid;

    /**
     * 回复评论
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private OrderGoodsCommentModel replyRecomment;

    /**
     * 回复评论
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private OperatorModel operatorModel;

    /**
     * 实体转换model
     */
    public static List<OrderGoodsCommentModel> instance(List<OrderGoodsComment> orderGoodsCommentList) {
        if (orderGoodsCommentList == null || orderGoodsCommentList.size() <= 0) {
            return null;
        }
        List<OrderGoodsCommentModel> orderGoodsCommentModelList = new ArrayList<OrderGoodsCommentModel>();
        for (OrderGoodsComment orderGoodsComment : orderGoodsCommentList) {
            orderGoodsCommentModelList.add(instance(orderGoodsComment));
        }
        return orderGoodsCommentModelList;
    }

    /**
     * 实体转换model
     */
    public static OrderGoodsCommentModel instance(OrderGoodsComment orderGoodsComment) {
        if (orderGoodsComment == null || orderGoodsComment.getId() <= 0) {
            return null;
        }
        OrderGoodsCommentModel orderGoodsCommentModel = new OrderGoodsCommentModel();
        BeanUtils.copyProperties(orderGoodsComment, orderGoodsCommentModel);
        try {
            orderGoodsCommentModel.setContent(URLDecoder.decode(orderGoodsCommentModel.getContent(), "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return orderGoodsCommentModel;
    }

    /**
     * model转换实体
     */
    public OrderGoodsComment prototype() {
        OrderGoodsComment orderGoodsComment = new OrderGoodsComment();
        BeanUtils.copyProperties(this, orderGoodsComment);
        return orderGoodsComment;
    }

    /**
     * 获取【当前页码】
     **/
    public int getPageNo() {
        return pageNo;
    }

    /**
     * 设置【当前页码】
     **/
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * 获取【每页数据条数】
     **/
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置【每页数据条数】
     **/
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取【条件查询】
     **/
    public String getSearchName() {
        return searchName;
    }

    /**
     * 设置【条件查询】
     **/
    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public OrderGoodsModel getOrderGoodsModel() {
        return orderGoodsModel;
    }

    public void setOrderGoodsModel(OrderGoodsModel orderGoodsModel) {
        this.orderGoodsModel = orderGoodsModel;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public Integer getSearchPid() {
        return searchPid;
    }

    public void setSearchPid(Integer searchPid) {
        this.searchPid = searchPid;
    }

    public OrderGoodsCommentModel getReplyRecomment() {
        return replyRecomment;
    }

    public void setReplyRecomment(OrderGoodsCommentModel replyRecomment) {
        this.replyRecomment = replyRecomment;
    }

    public OperatorModel getOperatorModel() {
        return operatorModel;
    }

    public void setOperatorModel(OperatorModel operatorModel) {
        this.operatorModel = operatorModel;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }
}