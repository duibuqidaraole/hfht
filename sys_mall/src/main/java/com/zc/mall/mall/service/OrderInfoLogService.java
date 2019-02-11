package com.zc.mall.mall.service;

import com.zc.mall.core.manage.entity.Operator;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.mall.entity.OrderInfo;
import com.zc.mall.mall.entity.OrderInfoLog;
import com.zc.mall.mall.model.OrderInfoLogModel;
import com.zc.sys.common.form.Result;

import java.util.List;

/**
 * 订单日志
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年12月05日
 */
public interface OrderInfoLogService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(OrderInfoLogModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(OrderInfoLogModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(int type, OrderInfo orderInfo, User user, Operator operator,
                      String content);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(int type, OrderInfo orderInfo, User user, String content);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(int type, OrderInfo orderInfo, Operator operator, String content);

    /**
     * 日志查询
     *
     * @param type
     * @param orderId
     * @param operatorId
     * @param userId
     * @return
     */
    public List<OrderInfoLog> findList(int type, long orderId, int operatorId,
                                       int userId);

    /**
     * 日志查询
     *
     * @param type
     * @param orderId
     * @return
     */
    public List<OrderInfoLog> findList(int type, long orderId);

}