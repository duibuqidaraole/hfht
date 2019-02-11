package com.zc.mall.mall.service.impl;

import com.zc.mall.mall.dao.OrderCartDao;
import com.zc.mall.mall.entity.OrderCart;
import com.zc.mall.mall.model.OrderCartModel;
import com.zc.mall.mall.service.OrderCartService;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.form.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 购物车
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@Service
public class OrderCartServiceImpl implements OrderCartService {

    @Resource
    private OrderCartDao orderCartDao;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    public Result list(OrderCartModel model) {

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
    public Result add(OrderCartModel model) {
        this.checkAdd(model);
        this.initAdd(model);
        orderCartDao.save(model.prototype());
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
    public Result update(OrderCartModel model) {
        OrderCart orderCart = this.checkUpdate(model);
        this.initUpdate(model, orderCart);
        orderCartDao.save(model.prototype());
        return Result.success();
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getById(OrderCartModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        OrderCart orderCart = orderCartDao.find(model.getId());
        if (orderCart == null) {
            return Result.error("参数错误！");
        }
        return Result.success().setData(OrderCartModel.instance(orderCart));
    }

    /**
     * 校验
     *
     * @param model
     */
    private void checkAdd(OrderCartModel model) {

    }

    /**
     * 初始化
     *
     * @param model
     */
    private void initAdd(OrderCartModel model) {

    }

    /**
     * 校验
     *
     * @param model
     * @return
     */
    private OrderCart checkUpdate(OrderCartModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误！");
        }
        OrderCart orderCart = orderCartDao.find(model.getId());
        if (orderCart == null) {
            throw new BusinessException("参数错误！");
        }
        return orderCart;
    }

    /**
     * 初始化
     *
     * @param model
     */
    private void initUpdate(OrderCartModel model, OrderCart orderCart) {

    }

}