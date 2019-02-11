package com.zc.mall.mall.service.impl;

import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.mall.dao.OrderPayHistoryDao;
import com.zc.mall.mall.entity.OrderPayHistory;
import com.zc.mall.mall.model.OrderPayHistoryModel;
import com.zc.mall.mall.service.OrderPayHistoryService;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.util.date.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 支付记录
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@Service
public class OrderPayHistoryServiceImpl implements OrderPayHistoryService {

    @Resource
    private OrderPayHistoryDao orderPayHistoryDao;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    public Result list(OrderPayHistoryModel model) {

        return null;
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result add(OrderPayHistoryModel model) {
        this.checkAdd(model);
        this.initAdd(model);
        orderPayHistoryDao.save(model.prototype());
        return Result.success();
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result update(OrderPayHistoryModel model) {
//        OrderPayHistory orderPayHistory = this.checkUpdate(model);
//        this.initUpdate(model, orderPayHistory);
//        orderPayHistoryDao.save(model.prototype());
        return Result.success();
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getById(OrderPayHistoryModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        OrderPayHistory orderPayHistory = orderPayHistoryDao.find(model.getId());
        if (orderPayHistory == null) {
            return Result.error("参数错误！");
        }
        return Result.success().setData(OrderPayHistoryModel.instance(orderPayHistory));
    }

    /**
     * 校验
     *
     * @param model
     */
    private void checkAdd(OrderPayHistoryModel model) {

    }

    /**
     * 初始化
     *
     * @param model
     */
    private void initAdd(OrderPayHistoryModel model) {
        model.setState(BaseConstant.BUSINESS_STATE_WAIT);
        model.setAddTime(DateUtil.getNow());
    }

    /**
     * 校验
     *
     * @param model
     * @return
     */
    private OrderPayHistory checkUpdate(OrderPayHistoryModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误！");
        }
        OrderPayHistory orderPayHistory = orderPayHistoryDao.find(model.getId());
        if (orderPayHistory == null) {
            throw new BusinessException("参数错误！");
        }
        return orderPayHistory;
    }

    /**
     * 初始化
     *
     * @param model
     */
    private void initUpdate(OrderPayHistoryModel model, OrderPayHistory orderPayHistory) {

    }

}