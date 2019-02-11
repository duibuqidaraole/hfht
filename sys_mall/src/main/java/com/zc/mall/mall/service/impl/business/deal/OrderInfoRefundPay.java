package com.zc.mall.mall.service.impl.business.deal;

import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.manage.dao.OperatorDao;
import com.zc.mall.core.manage.entity.Operator;
import com.zc.mall.mall.constant.OrderInfoLogTypeEnum;
import com.zc.mall.mall.constant.OrderInfoStateEnum;
import com.zc.mall.mall.dao.OrderInfoDao;
import com.zc.mall.mall.entity.OrderInfo;
import com.zc.mall.mall.executer.OrderInfoRefundPayExecuter;
import com.zc.mall.mall.model.OrderInfoModel;
import com.zc.mall.mall.service.OrderInfoLogService;
import com.zc.mall.mall.service.impl.business.BaseOrderInfo;
import com.zc.mall.promotion.constant.BasePromotionConstant;
import com.zc.mall.promotion.model.BonusCouponsRecordModel;
import com.zc.mall.promotion.service.BonusCouponsRecordService;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 退款支付
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月20日
 */
@Component
public class OrderInfoRefundPay extends BaseOrderInfo {

    @Resource
    private OrderInfoDao orderInfoDao;
    @Resource
    private BonusCouponsRecordService bonusCouponsRecordService;
    @Resource
    private OrderInfoLogService orderInfoLogService;
    @Resource
    private OperatorDao operatorDao;

    private OrderInfo orderInfo;
    private Operator operator;

    @Override
    public void initGlobalVariable() {
        orderInfo = null;
        operator = null;
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
        if (orderInfo.getState() != OrderInfoStateEnum.ORDER_INFO_STATE_REFUND_PAY.getOrderInfoState()) {
            throw new BusinessException("订单状态错误！");
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
        orderInfo.setState(OrderInfoStateEnum.ORDER_INFO_STATE_REFUND_COMPLETE.getOrderInfoState());
        orderInfo.setUpdateTime(DateUtil.getNow());
    }

    @Override
    public void persist() {
        orderInfoDao.update(orderInfo);
    }

    @Override
    public void log() {
        orderInfoLogService.add(OrderInfoLogTypeEnum.ORDER_INFO_TYPE_REFUND_PAY.getOrderInfoLogType(),
                orderInfo, operator, StringUtil.isNull(model.getRemark()));
    }

    @Override
    public void handOther() {
        //返还红包
        BonusCouponsRecordModel.cancelUseBonusCoupons(orderInfo.getUser().getId(), BasePromotionConstant.COUPONS_USETYPE_DEDUCITION, orderInfo.getId() + "");
        // 退款任务
        if (orderInfo.getBalancePay() > 0) {
            OrderInfoRefundPayExecuter orderInfoRefundPayExecuter = BeanUtil.getBean(OrderInfoRefundPayExecuter.class);
            OrderInfoModel orderInfoModel = OrderInfoModel.instance(orderInfo);
            orderInfoRefundPayExecuter.execute(orderInfoModel, orderInfoModel.getBalancePay(), orderInfoModel.getUser());
        }
    }

}
