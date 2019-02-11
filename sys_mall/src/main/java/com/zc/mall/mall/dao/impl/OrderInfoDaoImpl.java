package com.zc.mall.mall.dao.impl;

import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.mall.constant.OrderInfoStateEnum;
import com.zc.mall.mall.dao.OrderInfoDao;
import com.zc.mall.mall.entity.OrderInfo;
import com.zc.mall.mall.model.OrderInfoModel;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.model.jpa.OrderFilter;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import com.zc.sys.common.model.jpa.SearchFilter;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单信息表
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@Repository
public class OrderInfoDaoImpl extends BaseDaoImpl<OrderInfo> implements OrderInfoDao {

    @Override
    public List<OrderInfo> listTimeOutReceive() {
        QueryParam param = QueryParam.getInstance();
        param.addParam("state", OrderInfoStateEnum.ORDER_INFO_STATE_RECEIVE.getOrderInfoState());
        return super.findByCriteria(param);
    }

    @Override
    public PageDataList<OrderInfo> list(OrderInfoModel model) {
        QueryParam param = QueryParam.getInstance();
        if (StringUtil.isNotBlank(model.getSearchName())) {
            SearchFilter orFilter1 = new SearchFilter("no", SearchFilter.Operators.LIKE, model.getSearchName());
            SearchFilter orFilter3 = new SearchFilter("user.userName", SearchFilter.Operators.LIKE, model.getSearchName());
            param.addOrFilter(orFilter1, orFilter3);
        }
        if (model.getState() != 0) {
            param.addParam("state", model.getState());
        }

        if (model.getUser() != null && model.getUser().getId() > 0) {
            param.addParam("user.id", model.getUser().getId());
        }

        if (model.getIsComment() != 0) {
            param.addParam("isComment", model.getIsComment());
        }
        if (!StringUtil.isBlank(model.getStateList())) {
            param.addOrFilter("state", model.getStateList().split(","));
        }

        if (!StringUtil.isBlank(model.getSearchState())) {
            if (model.getSearchState() == OrderInfoStateEnum.ORDER_INFO_STATE_SEND.getOrderInfoState()) {
                SearchFilter orFilter1 = new SearchFilter("state", SearchFilter.Operators.EQ, OrderInfoStateEnum.ORDER_INFO_STATE_SEND.getOrderInfoState());
                SearchFilter orFilter2 = new SearchFilter("beforeState", SearchFilter.Operators.EQ, OrderInfoStateEnum.ORDER_INFO_STATE_SEND.getOrderInfoState(),
                        "state", SearchFilter.Operators.EQ, OrderInfoStateEnum.ORDER_INFO_STATE_REFUND.getOrderInfoState());
                param.addOrAndFilter(orFilter1, orFilter2);
            }
            if (model.getSearchState() == OrderInfoStateEnum.ORDER_INFO_STATE_RECEIVE.getOrderInfoState()) {
                SearchFilter orFilter1 = new SearchFilter("state", SearchFilter.Operators.EQ, OrderInfoStateEnum.ORDER_INFO_STATE_RECEIVE.getOrderInfoState());
                SearchFilter orFilter2 = new SearchFilter("beforeState", SearchFilter.Operators.EQ, OrderInfoStateEnum.ORDER_INFO_STATE_RECEIVE.getOrderInfoState(),
                        "state", SearchFilter.Operators.EQ, OrderInfoStateEnum.ORDER_INFO_STATE_REFUND.getOrderInfoState());
                param.addOrAndFilter(orFilter1, orFilter2);
            }
        }
        param.addOrder(OrderFilter.OrderType.DESC, "id");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }

    /**
     * 订单支付超时
     *
     * @return
     */
    @Override
    public List<OrderInfo> findTimeOutPay() {
        QueryParam param = QueryParam.getInstance();
        param.addParam("state", OrderInfoStateEnum.ORDER_INFO_STATE_PAY.getOrderInfoState());
        return super.findByCriteria(param);
    }

    @Override
    public List<OrderInfo> findWaitComment() {
        QueryParam param = QueryParam.getInstance();
        param.addParam("state", OrderInfoStateEnum.ORDER_INFO_STATE_COMPLETE.getOrderInfoState());
        param.addParam("isComment", BaseConstant.INFO_STATE_NO);
        return super.findByCriteria(param);
    }

    /**
     * 根据用户状态获取订单数目
     *
     * @param userId
     * @param state
     * @return
     */
    @Override
    public int getCountByState(long userId, int state, int isComment) {
        QueryParam param = QueryParam.getInstance();
        param.addParam("state", state);
        param.addParam("user.id", userId);
        if (isComment != 0) {
            param.addParam("isComment", isComment);
        }
        return super.countByCriteria(param);
    }

    /**
     * 根据用户状态获取订单数目
     *
     * @param userId
     * @param state
     * @return
     */
    @Override
    public int getCountByState(long userId, int state, int beforeState, int isComment) {
        QueryParam param = QueryParam.getInstance();
        param.addParam("state", state);
        param.addParam("user.id", userId);
        param.addParam("beforeState", beforeState);
        if (isComment != 0) {
            param.addParam("isComment", isComment);
        }
        return super.countByCriteria(param);
    }

    /**
     * 根据orderNo获取订单
     *
     * @param model
     * @return
     */
    @Override
    public OrderInfo getByOrderNo(OrderInfoModel model) {
        QueryParam param = QueryParam.getInstance();
        param.addParam("no", model.getNo());
        return super.findByCriteriaForUnique(param);
    }

    /**
     * 查询退款处理超时
     *
     * @return
     */
    @Override
    public List<OrderInfo> listTimeOutRefundUser() {
        QueryParam param = QueryParam.getInstance();
        param.addParam("state", OrderInfoStateEnum.ORDER_INFO_STATE_REFUND.getOrderInfoState());
        return super.findByCriteria(param);
    }

}