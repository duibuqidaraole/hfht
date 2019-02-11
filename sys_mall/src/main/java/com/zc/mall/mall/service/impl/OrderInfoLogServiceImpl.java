package com.zc.mall.mall.service.impl;

import com.zc.mall.core.manage.entity.Operator;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.mall.dao.OrderInfoLogDao;
import com.zc.mall.mall.entity.OrderInfo;
import com.zc.mall.mall.entity.OrderInfoLog;
import com.zc.mall.mall.model.OrderInfoLogModel;
import com.zc.mall.mall.service.OrderInfoLogService;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.util.date.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单日志
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年12月05日
 */
@Service
public class OrderInfoLogServiceImpl implements OrderInfoLogService {

    @Resource
    private OrderInfoLogDao orderInfoLogDao;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    public Result list(OrderInfoLogModel model) {

        return null;
    }

    /**
     * 日志查询
     *
     * @param type
     * @param orderId
     * @return
     */
    @Override
    public List<OrderInfoLog> findList(int type, long orderId) {
        return orderInfoLogDao.findList(type, orderId, 0, 0);
    }

    /**
     * 日志查询
     *
     * @param type
     * @param orderId
     * @param operatorId
     * @param userId
     * @return
     */
    @Override
    public List<OrderInfoLog> findList(int type, long orderId, int operatorId, int userId) {
        return orderInfoLogDao.findList(type, orderId, operatorId, userId);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result add(int type, OrderInfo orderInfo, Operator operator, String content) {
        return add(type, orderInfo, null, operator, content);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result add(int type, OrderInfo orderInfo, User user, String content) {
        return add(type, orderInfo, user, null, content);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result add(int type, OrderInfo orderInfo, User user, Operator operator, String content) {
        OrderInfoLogModel model = new OrderInfoLogModel();
        model.setType(type);
        model.setOrderInfo(orderInfo);
        model.setUser(user);
        model.setOperator(operator);
        model.setContent(content);
        model.setAddTime(DateUtil.getNow());
        return add(model);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result add(OrderInfoLogModel model) {
        this.checkAdd(model);
        this.initAdd(model);
        orderInfoLogDao.save(model.prototype());
        return Result.success();
    }

    /**
     * 校验
     *
     * @param model
     */
    private void checkAdd(OrderInfoLogModel model) {

    }

    /**
     * 初始化
     *
     * @param model
     */
    private void initAdd(OrderInfoLogModel model) {

    }

}