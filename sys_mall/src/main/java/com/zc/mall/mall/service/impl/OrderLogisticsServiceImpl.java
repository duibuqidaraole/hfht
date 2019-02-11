package com.zc.mall.mall.service.impl;

import com.zc.mall.mall.dao.OrderLogisticsDao;
import com.zc.mall.mall.entity.OrderLogistics;
import com.zc.mall.mall.model.OrderLogisticsModel;
import com.zc.mall.mall.service.OrderLogisticsService;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.form.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 物流
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@Service
public class OrderLogisticsServiceImpl implements OrderLogisticsService {

    @Resource
    private OrderLogisticsDao orderLogisticsDao;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    public Result list(OrderLogisticsModel model) {

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
    public Result add(OrderLogisticsModel model) {
        this.checkAdd(model);
        this.initAdd(model);
        orderLogisticsDao.save(model.prototype());
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
    public Result update(OrderLogisticsModel model) {
        OrderLogistics orderLogistics = this.checkUpdate(model);
        this.initUpdate(model, orderLogistics);
        orderLogisticsDao.update(orderLogistics);
        return Result.success();
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getById(OrderLogisticsModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        OrderLogistics orderLogistics = orderLogisticsDao.find(model.getId());
        if (orderLogistics == null) {
            return Result.error("参数错误！");
        }
        return Result.success().setData(OrderLogisticsModel.instance(orderLogistics));
    }

    /**
     * 校验
     *
     * @param model
     */
    private void checkAdd(OrderLogisticsModel model) {

    }

    /**
     * 初始化
     *
     * @param model
     */
    private void initAdd(OrderLogisticsModel model) {

    }

    /**
     * 校验
     *
     * @param model
     * @return
     */
    private OrderLogistics checkUpdate(OrderLogisticsModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误！");
        }
        OrderLogistics orderLogistics = orderLogisticsDao.find(model.getId());
        if (orderLogistics == null) {
            throw new BusinessException("参数错误！");
        }
        return orderLogistics;
    }

    /**
     * 初始化
     *
     * @param model
     */
    private void initUpdate(OrderLogisticsModel model, OrderLogistics orderLogistics) {

    }

}