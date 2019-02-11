package com.zc.mall.mall.service.impl.business.deal;

import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.user.dao.UserDao;
import com.zc.mall.core.user.dao.UserRelationDao;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.mall.constant.OrderInfoLogTypeEnum;
import com.zc.mall.mall.constant.OrderInfoStateEnum;
import com.zc.mall.mall.dao.OrderInfoDao;
import com.zc.mall.mall.dao.OrderLogisticsDao;
import com.zc.mall.mall.entity.OrderInfo;
import com.zc.mall.mall.entity.OrderLogistics;
import com.zc.mall.mall.executer.OrderInfoReceiveExecuter;
import com.zc.mall.mall.model.OrderInfoModel;
import com.zc.mall.mall.service.OrderInfoLogService;
import com.zc.mall.mall.service.impl.business.BaseOrderInfo;
import com.zc.mall.promotion.model.PromotionModel;
import com.zc.mall.promotion.model.UserVipCouponsModel;
import com.zc.mall.promotion.model.VipCouponsModel;
import com.zc.mall.promotion.service.UserVipCouponsService;
import com.zc.mall.promotion.service.VipCouponsService;
import com.zc.mall.promotion.way.impl.PromotionWayFirstPay;
import com.zc.mall.promotion.way.impl.PromotionWayOrderFinish;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.util.calculate.BigDecimalUtil;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 收货
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月20日
 */
@Component
public class OrderInfoReceive extends BaseOrderInfo {

    @Resource
    private OrderInfoDao orderInfoDao;
    @Resource
    private UserDao userDao;
    @Resource
    private OrderLogisticsDao orderLogisticsDao;
    @Resource
    private UserRelationDao userRelationDao;
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
                orderInfo.getState() != OrderInfoStateEnum.ORDER_INFO_STATE_REFUND.getOrderInfoState() &&
                orderInfo.getState() != OrderInfoStateEnum.ORDER_INFO_STATE_REFUND_PAY.getOrderInfoState()) {
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
        orderInfo.setState(OrderInfoStateEnum.ORDER_INFO_STATE_COMPLETE.getOrderInfoState());
        orderInfo.setRemark(model.getRemark());
        orderInfo.setUpdateTime(DateUtil.getNow());
    }

    @Override
    public void persist() {
        orderInfoDao.update(orderInfo);
    }

    @Override
    public void log() {
        orderInfoLogService.add(OrderInfoLogTypeEnum.ORDER_INFO_TYPE_RECEIVE.getOrderInfoLogType(),
                orderInfo, orderInfo.getUser(), "确认收货：" + orderInfo.getNo() + ";" + StringUtil.isNull(model.getRemark()));
    }

    @Override
    public void handOther() {
        // 物流
        initOrderLogistics();
        //分销处理
        initAccount();
        OrderInfoReceiveExecuter orderInfoReceiveExecuter = BeanUtil.getBean(OrderInfoReceiveExecuter.class);
        orderInfoReceiveExecuter.execute(OrderInfoModel.instance(orderInfo), orderInfo.getAmountReal(), orderInfo.getUser());
    }

    /**
     * 物流处理
     */
    private void initOrderLogistics() {
        OrderLogistics orderLogistics = orderLogisticsDao.findByOrderInfoId(orderInfo.getId());
        orderLogistics.setReceiveTime(DateUtil.getNow());
        orderLogisticsDao.update(orderLogistics);
    }

    /**
     * 分销处理
     */
    private void initAccount() {
        //上级分销奖励
        User toUser = userRelationDao.findToUserByUserId(orderInfo.getUser().getId());
        if (toUser != null) {
            PromotionModel promotionModel = new PromotionModel();
            promotionModel.setRealAmount(BigDecimalUtil.mul(orderInfo.getAmountReal(), 0.15));
            promotionModel.setVipAmount(orderInfo.getAmountReal());
            promotionModel.setUser(toUser);
            PromotionWayOrderFinish promotionWayOrderFinish = BeanUtil.getBean(PromotionWayOrderFinish.class);
            promotionWayOrderFinish.executer(promotionModel);
            /*//注册奖励
            OrderInfoModel orderInfoModel = new OrderInfoModel();
            orderInfoModel.setState(OrderInfoStateEnum.ORDER_INFO_STATE_COMPLETE.getOrderInfoState());
            orderInfoModel.setUser(orderInfo.getUser());
            List<OrderInfo> list = orderInfoDao.list(orderInfoModel).getList();
            if (list.size() == 1) {
                //发放红包奖励
                PromotionModel promotionModel1 = new PromotionModel();
                promotionModel1.setUser(toUser);
                PromotionWayFirstPay promotionWayFirstPay = BeanUtil.getBean(PromotionWayFirstPay.class);
                promotionWayFirstPay.executer(promotionModel1);
                //发放超级vip
                VipCouponsModel vipCouponsModel = vipCouponsService.getById(2L);
                UserVipCouponsModel userVipCouponsModel = new UserVipCouponsModel();
                userVipCouponsModel.setVipCoupons(vipCouponsModel);
                userVipCouponsModel.setUser(orderInfo.getUser());
                userVipCouponsService.giveOutRequest(userVipCouponsModel);

            }*/

            //上上级分销奖励
            User toUser1 = userRelationDao.findToUserByUserId(toUser.getId());
            if (toUser1 != null) {
                PromotionModel promotionModel1 = new PromotionModel();
                promotionModel1.setRealAmount(BigDecimalUtil.mul(orderInfo.getAmountReal(), 0.05));
                promotionModel1.setVipAmount(orderInfo.getAmountReal());
                promotionModel1.setUser(toUser1);
                PromotionWayOrderFinish promotionWayOrderFinish1 = BeanUtil.getBean(PromotionWayOrderFinish.class);
                promotionWayOrderFinish1.executer(promotionModel1);
            }
        }
    }
}
