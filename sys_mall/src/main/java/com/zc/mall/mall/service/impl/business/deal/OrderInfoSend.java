package com.zc.mall.mall.service.impl.business.deal;

import com.zc.mall.core.manage.dao.OperatorDao;
import com.zc.mall.core.manage.entity.Operator;
import com.zc.mall.core.user.dao.UserDao;
import com.zc.mall.mall.constant.OrderInfoLogTypeEnum;
import com.zc.mall.mall.constant.OrderInfoStateEnum;
import com.zc.mall.mall.dao.OrderInfoDao;
import com.zc.mall.mall.dao.OrderLogisticsDao;
import com.zc.mall.mall.entity.OrderInfo;
import com.zc.mall.mall.entity.OrderLogistics;
import com.zc.mall.mall.service.OrderInfoLogService;
import com.zc.mall.mall.service.impl.business.BaseOrderInfo;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 发货
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月20日
 */
@Component
public class OrderInfoSend extends BaseOrderInfo {

    @Resource
    private OrderInfoDao orderInfoDao;
    @Resource
    private UserDao userDao;
    @Resource
    private OrderLogisticsDao orderLogisticsDao;
    @Resource
    private OrderInfoLogService orderInfoLogService;
    @Resource
    private OperatorDao operatorDao;

    private OrderInfo orderInfo;
    private Operator operator;

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
        if (orderInfo.getState() != OrderInfoStateEnum.ORDER_INFO_STATE_SEND.getOrderInfoState()) {
            throw new BusinessException("订单状态错误！");
        }
        if (model.getOrderLogisticsModel() == null) {
            throw new BusinessException("参数错误！");
        }
        if (StringUtil.isBlank(model.getOrderLogisticsModel().getPostNo())) {
            throw new BusinessException("请填写快递单号！");
        }
        if (StringUtil.isBlank(model.getOrderLogisticsModel().getLogisticsCompanyNo())) {
            throw new BusinessException("请填写物流公司！");
        }
        if (model.getUpdateOperator() == null || model.getUpdateOperator().getId() <= 0) {
            throw new BusinessException("参数错误！");
        }
        operator = operatorDao.find(model.getUpdateOperator().getId());
        if (operator == null) {
            throw new BusinessException("参数错误！");
        }
    }

    @Override
    public void init() {
        orderInfo.setBeforeState(orderInfo.getState());
        orderInfo.setState(OrderInfoStateEnum.ORDER_INFO_STATE_RECEIVE.getOrderInfoState());
        orderInfo.setUpdateTime(DateUtil.getNow());
    }

    @Override
    public void persist() {
        orderInfoDao.update(orderInfo);
    }

    @Override
    public void log() {
        orderInfoLogService.add(OrderInfoLogTypeEnum.ORDER_INFO_TYPE_SEND.getOrderInfoLogType(),
                orderInfo, operator, StringUtil.isNull(model.getRemark()));
    }

    @Override
    public void handOther() {
        // 物流
        initOrderLogistics();
    }

    /**
     * 物流处理
     */
    private void initOrderLogistics() {
        OrderLogistics orderLogistics = orderLogisticsDao.findByOrderInfoId(orderInfo.getId());
        orderLogistics.setPostNo(model.getOrderLogisticsModel().getPostNo());
        orderLogistics.setLogisticsCompanyNo(model.getOrderLogisticsModel().getLogisticsCompanyNo());
        orderLogistics.setSendTime(DateUtil.getNow());
        orderLogisticsDao.update(orderLogistics);
    }
}
