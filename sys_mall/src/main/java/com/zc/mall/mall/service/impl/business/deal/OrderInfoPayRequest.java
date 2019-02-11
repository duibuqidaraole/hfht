package com.zc.mall.mall.service.impl.business.deal;

import com.zc.mall.core.account.entity.Account;
import com.zc.mall.core.common.pay.wx.WxPayClient;
import com.zc.mall.core.user.dao.UserDao;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.mall.constant.OrderInfoLogTypeEnum;
import com.zc.mall.mall.constant.OrderInfoStateEnum;
import com.zc.mall.mall.dao.OrderInfoDao;
import com.zc.mall.mall.dao.OrderPayHistoryDao;
import com.zc.mall.mall.entity.OrderInfo;
import com.zc.mall.mall.service.OrderInfoLogService;
import com.zc.mall.mall.service.impl.business.BaseOrderInfo;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.util.calculate.BigDecimalUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 订单支付请求
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月20日
 */
@Component
public class OrderInfoPayRequest extends BaseOrderInfo {

    @Resource
    private OrderInfoDao orderInfoDao;
    @Resource
    private UserDao userDao;
    @Resource
    private OrderPayHistoryDao orderPayHistoryDao;
    @Resource
    private OrderInfoLogService orderInfoLogService;

    private OrderInfo orderInfo;
    private User user;

    @Override
    public void initGlobalVariable() {
        orderInfo = null;
        user = null;
    }

    @Override
    public void check() {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误！");
        }
        if (model.getPayment() <= 0) {
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
        user = userDao.find(model.getUser().getId());
        if (user == null) {
            throw new BusinessException("参数错误！");
        }
        if (user.getId() != orderInfo.getUser().getId()) {
            throw new BusinessException("参数错误！");
        }
        Account account = user.getAccount();
        if (model.getBalancePay() < 0 || account.getBalance() < model.getBalancePay()) {
            throw new BusinessException("参数错误！");
        }
    }

    @Override
    public void init() {
        orderInfo.setPayment(model.getPayment());
        orderInfo.setPaymentTradeNo(StringUtil.getSerialNumber());
        orderInfo.setBalancePay(model.getBalancePay());
    }

    @Override
    public void persist() {
        orderInfoDao.update(orderInfo);
    }

    @Override
    public void log() {
        orderInfoLogService.add(OrderInfoLogTypeEnum.ORDER_INFO_TYPE_PAY_REQUEST.getOrderInfoLogType(),
                orderInfo, orderInfo.getUser(), "订单支付请求：" + orderInfo.getNo() + ";" + StringUtil.isNull(model.getRemark()));
    }

    @Override
    public void handOther() {
        // 微信请求
        wxRequest();
    }

    private void wxRequest() {
        double wxPayAmount = BigDecimalUtil.sub(orderInfo.getAmountReal(), orderInfo.getBalancePay());
        if (wxPayAmount <= 0) {
            return;
        }
        try {
            //微信请求
            Map<String, String> payData = WxPayClient.unifiedorder(user.getOpenId(),
                    orderInfo.getPaymentTradeNo(), "弘梵商品订单-" + orderInfo.getNo(), wxPayAmount);
            model.setPayData(payData);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
