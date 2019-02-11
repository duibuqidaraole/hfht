package com.zc.mall.mall.service.impl.business.deal;

import com.zc.mall.core.user.dao.UserDao;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.mall.constant.OrderInfoLogTypeEnum;
import com.zc.mall.mall.constant.OrderInfoStateEnum;
import com.zc.mall.mall.dao.OrderInfoDao;
import com.zc.mall.mall.entity.OrderInfo;
import com.zc.mall.mall.entity.OrderInfoLog;
import com.zc.mall.mall.service.OrderInfoLogService;
import com.zc.mall.mall.service.impl.business.BaseOrderInfo;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户退款
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月20日
 */
@Component
public class OrderInfoRefundUser extends BaseOrderInfo {

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
        if (orderInfo.getState() != OrderInfoStateEnum.ORDER_INFO_STATE_RECEIVE.getOrderInfoState() &&
                orderInfo.getState() != OrderInfoStateEnum.ORDER_INFO_STATE_SEND.getOrderInfoState() &&
                orderInfo.getState() != OrderInfoStateEnum.ORDER_INFO_STATE_COMPLETE.getOrderInfoState()
                ) {
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
        if (model.getRefundReason() == null) {
            throw new BusinessException("请输入退款原因！");
        }
        List<OrderInfoLog> list = orderInfoLogService.findList(OrderInfoLogTypeEnum.ORDER_INFO_TYPE_USER_REFUND.getOrderInfoLogType(), model.getId());
        if (list != null && list.size() > 0) {
            throw new BusinessException("不能多次退货");
        }
    }

    @Override
    public void init() {

        orderInfo.setBeforeState(orderInfo.getState());
        orderInfo.setState(OrderInfoStateEnum.ORDER_INFO_STATE_REFUND.getOrderInfoState());
        orderInfo.setUpdateTime(DateUtil.getNow());
    }

    @Override
    public void persist() {
        orderInfoDao.update(orderInfo);
    }

    @Override
    public void log() {
        String remark = model.getRefundReason() + model.getRefundExplanation();
        orderInfoLogService.add(OrderInfoLogTypeEnum.ORDER_INFO_TYPE_USER_REFUND.getOrderInfoLogType(),
                orderInfo, orderInfo.getUser(), "申请退款：" + orderInfo.getNo() + ";" + StringUtil.isNull(remark));
    }

    @Override
    public void handOther() {

    }
}
