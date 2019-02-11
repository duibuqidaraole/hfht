package com.zc.mall.mall.service.impl.business.deal;

import com.zc.mall.core.manage.dao.OperatorDao;
import com.zc.mall.core.manage.entity.Operator;
import com.zc.mall.core.user.dao.UserDao;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.mall.constant.OrderInfoLogTypeEnum;
import com.zc.mall.mall.constant.OrderInfoStateEnum;
import com.zc.mall.mall.dao.GoodsPromotionRecordDao;
import com.zc.mall.mall.dao.GoodsSkuDao;
import com.zc.mall.mall.dao.OrderInfoDao;
import com.zc.mall.mall.entity.GoodsPromotion;
import com.zc.mall.mall.entity.GoodsPromotionRecord;
import com.zc.mall.mall.entity.OrderGoods;
import com.zc.mall.mall.entity.OrderInfo;
import com.zc.mall.mall.service.OrderInfoLogService;
import com.zc.mall.mall.service.impl.GoodsPromotionRecordServiceImpl;
import com.zc.mall.mall.service.impl.business.BaseOrderInfo;
import com.zc.mall.promotion.constant.BasePromotionConstant;
import com.zc.mall.promotion.model.BonusCouponsRecordModel;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 关闭订单
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月20日
 */
@Component
public class OrderInfoClose extends BaseOrderInfo {

    @Resource
    private OrderInfoDao orderInfoDao;
    @Resource
    private UserDao userDao;
    @Resource
    private OrderInfoLogService orderInfoLogService;
    @Resource
    private OperatorDao operatorDao;
    @Resource
    private GoodsSkuDao goodsSkuDao;
    @Resource
    private GoodsPromotionRecordServiceImpl goodsPromotionRecordService;


    private OrderInfo orderInfo;
    private User user;
    private Operator operator;

    @Override
    public void initGlobalVariable() {
        orderInfo = null;
        user = null;
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
        if (orderInfo.getState() != OrderInfoStateEnum.ORDER_INFO_STATE_PAY.getOrderInfoState()) {
            throw new BusinessException("订单状态错误！");
        }
        if (model.getUser() != null) {
            if (model.getUser().getId() <= 0) {
                throw new BusinessException("参数错误！");
            }
            user = userDao.find(model.getUser().getId());
            if (user == null) {
                throw new BusinessException("参数错误！");
            }
            if (user.getId() != orderInfo.getUser().getId()) {
                throw new BusinessException("参数错误！");
            }
        }
        if (model.getUpdateOperator() != null) {
            if (model.getUpdateOperator().getId() <= 0) {
                throw new BusinessException("参数错误！");
            }
            operator = operatorDao.find(model.getUpdateOperator().getId());
            if (operator == null) {
                throw new BusinessException("参数错误！");
            }
        }
    }

    @Override
    public void init() {
        orderInfo.setBeforeState(orderInfo.getState());
        orderInfo.setState(OrderInfoStateEnum.ORDER_INFO_STATE_CLOSE.getOrderInfoState());
        orderInfo.setUpdateTime(DateUtil.getNow());
    }

    @Override
    public void persist() {
        //更新库存
        for (OrderGoods orderGoods : orderInfo.getOrderGoodsList()) {
            int proCount=0;
            proCount=goodsPromotionRecordService.initGoodsPromotion(orderGoods,proCount);
            goodsSkuDao.updateGoodsSkuStock(orderGoods.getGoodsSku().getId(), orderGoods.getNumber()+proCount);
        }
        orderInfoDao.update(orderInfo);
    }

    @Override
    public void log() {
        orderInfoLogService.add(OrderInfoLogTypeEnum.ORDER_INFO_TYPE_CLOSE.getOrderInfoLogType(),
                orderInfo, user, operator, "关闭订单：" + orderInfo.getNo() + ";" + StringUtil.isNull(model.getRemark()));
    }

    @Override
    public void handOther() {
        //返还红包
        BonusCouponsRecordModel.cancelUseBonusCoupons(orderInfo.getUser().getId(), BasePromotionConstant.COUPONS_USETYPE_DEDUCITION, orderInfo.getId() + "");
    }
}
