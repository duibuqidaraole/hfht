package com.zc.mall.mall.service.impl.business.deal;

import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.user.dao.UserDao;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.mall.constant.OrderInfoLogTypeEnum;
import com.zc.mall.mall.constant.OrderInfoStateEnum;
import com.zc.mall.mall.dao.OrderInfoDao;
import com.zc.mall.mall.entity.OrderInfo;
import com.zc.mall.mall.executer.OrderInfoPayExecuter;
import com.zc.mall.mall.model.OrderInfoModel;
import com.zc.mall.mall.service.OrderInfoLogService;
import com.zc.mall.mall.service.impl.business.BaseOrderInfo;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 订单支付
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月20日
 */
@Component
public class OrderInfoPay extends BaseOrderInfo {

    @Resource
    private OrderInfoDao orderInfoDao;
    @Resource
    private UserDao userDao;
    @Resource
    private OrderInfoLogService orderInfoLogService;

    private OrderInfo orderInfo;

    @Override
    public void initGlobalVariable() {
        orderInfo = null;
    }

    @Override
    public void check() {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误！");
        }
        orderInfo = orderInfoDao.find(model.getId());
        if (orderInfo == null) {
            throw new BusinessException("订单不存在！");
        }
        if (orderInfo.getState() != OrderInfoStateEnum.ORDER_INFO_STATE_PAY.getOrderInfoState()) {
            throw new BusinessException("订单状态错误！");
        }
        if (model.getUser() == null || model.getUser().getId() <= 0) {
            throw new BusinessException("参数错误！");
        }
        User user = userDao.find(model.getUser().getId());
        if (user == null) {
            throw new BusinessException("参数错误！");
        }
        if (user.getId() != orderInfo.getUser().getId()) {
            throw new BusinessException("参数错误！");
        }
    }

    @Override
    public void init() {
        orderInfo.setBeforeState(orderInfo.getState());
        orderInfo.setState(OrderInfoStateEnum.ORDER_INFO_STATE_SEND.getOrderInfoState());
        orderInfo.setUpdateTime(DateUtil.getNow());
    }

    @Override
    public void persist() {
        orderInfoDao.update(orderInfo);
    }

    @Override
    public void log() {
        orderInfoLogService.add(OrderInfoLogTypeEnum.ORDER_INFO_TYPE_PAY.getOrderInfoLogType(),
                orderInfo, orderInfo.getUser(), "订单支付：" + orderInfo.getNo() + ";" + StringUtil.isNull(model.getRemark()));
    }

    @Override
    public void handOther() {
        // 订单任务
        OrderInfoPayExecuter orderInfoPayExecuter = BeanUtil.getBean(OrderInfoPayExecuter.class);
        OrderInfoModel orderInfoModel = OrderInfoModel.instance(orderInfo);
        orderInfoPayExecuter.execute(orderInfoModel, orderInfoModel.getAmountReal(), orderInfoModel.getUser());
    }
}
