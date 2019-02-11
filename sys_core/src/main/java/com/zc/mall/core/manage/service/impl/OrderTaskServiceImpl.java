package com.zc.mall.core.manage.service.impl;

import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.manage.dao.OrderTaskDao;
import com.zc.mall.core.manage.entity.OrderTask;
import com.zc.mall.core.manage.model.OrderTaskModel;
import com.zc.mall.core.manage.service.OrderTaskService;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单任务
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Service
public class OrderTaskServiceImpl implements OrderTaskService {

    @Resource
    private OrderTaskDao orderTaskDao;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    public Result list(OrderTaskModel model) {
        PageDataList<OrderTask> pageDataList = orderTaskDao.list(model);
        PageDataList<OrderTaskModel> pageDataList_ = new PageDataList<OrderTaskModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<OrderTaskModel> list = new ArrayList<OrderTaskModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (OrderTask orderTask : pageDataList.getList()) {
                OrderTaskModel model_ = OrderTaskModel.instance(orderTask);
                model_.setUserModel(UserModel.instance(orderTask.getUser()));
                list.add(model_);
            }
        }
        pageDataList_.setList(list);
        return Result.success().setData(pageDataList_);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public OrderTask add(User user, String type, String orderNo) {
        OrderTask orderTaskLoan = new OrderTask(user, type,
                StringUtil.getSerialNumber(), BaseConstant.BUSINESS_STATE_WAIT,
                "", DateUtil.getNow());
        orderTaskDao.save(orderTaskLoan);
        return orderTaskLoan;
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void update(OrderTask orderTask, int state, String result) {
        orderTask.setDoTime(DateUtil.getNow());
        orderTask.setDoResult(result);
        orderTask.setState(state);
        orderTaskDao.update(orderTask);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getById(OrderTaskModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误");
        }
        return Result.success().setData(orderTaskDao.find(model.getId()));
    }

    /**
     * 根据orderNo查找
     *
     * @param model
     * @return
     */
    @Override
    public OrderTask findByOrderNo(String orderNo) {
        return orderTaskDao.findObjByProperty("orderNo", orderNo);
    }

    /**
     * 处理订单
     *
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void dealOrder(String orderNo, int state, String result) {
        OrderTask orderTask = orderTaskDao.getByOrderNo(orderNo);
        if (orderTask == null) {
            throw new BusinessException("订单信息不存在");
        }
        orderTask.setState(state);
        orderTask.setRemark(result);
        orderTask.setDoTime(DateUtil.getNow());
        orderTaskDao.update(orderTask);
    }

}