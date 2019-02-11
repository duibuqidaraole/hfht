package com.zc.mall.mall.service.impl.business.deal;

import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.manage.dao.OperatorDao;
import com.zc.mall.core.manage.entity.Operator;
import com.zc.mall.mall.constant.OrderInfoLogTypeEnum;
import com.zc.mall.mall.constant.OrderInfoStateEnum;
import com.zc.mall.mall.dao.GoodsSkuDao;
import com.zc.mall.mall.dao.OrderGoodsDao;
import com.zc.mall.mall.dao.OrderInfoDao;
import com.zc.mall.mall.entity.OrderGoods;
import com.zc.mall.mall.entity.OrderInfo;
import com.zc.mall.mall.service.OrderInfoLogService;
import com.zc.mall.mall.service.impl.GoodsPromotionRecordServiceImpl;
import com.zc.mall.mall.service.impl.business.BaseOrderInfo;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 退款处理
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月20日
 */
@Component
public class OrderInfoRefundDeal extends BaseOrderInfo {

    @Resource
    private OrderInfoDao orderInfoDao;
    @Resource
    private OperatorDao operatorDao;
    @Resource
    private OrderGoodsDao orderGoodsDao;
    @Resource
    private GoodsSkuDao goodsSkuDao;
    @Resource
    private OrderInfoLogService orderInfoLogService;
    @Resource
    private GoodsPromotionRecordServiceImpl goodsPromotionRecordService;

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
        if (orderInfo.getState() != OrderInfoStateEnum.ORDER_INFO_STATE_REFUND.getOrderInfoState()) {
            throw new BusinessException("订单状态错误！");
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
        int state = 0;
        switch (model.getState()) {
            // 同意
            case BaseConstant.BUSINESS_STATE_YES:
                state = OrderInfoStateEnum.ORDER_INFO_STATE_REFUND_PAY.getOrderInfoState();
                break;
            // 拒绝
            case BaseConstant.BUSINESS_STATE_NO:
                state = orderInfo.getBeforeState();
                break;
            default:
                throw new BusinessException("参数错误！");
        }
        orderInfo.setBeforeState(orderInfo.getState());
        orderInfo.setState(state);
        orderInfo.setUpdateOperator(operator);
        orderInfo.setUpdateTime(DateUtil.getNow());
    }

    @Override
    public void persist() {
        orderInfoDao.update(orderInfo);
    }

    @Override
    public void log() {
        orderInfoLogService.add(OrderInfoLogTypeEnum.ORDER_INFO_TYPE_REFUND_DEAL.getOrderInfoLogType(),
                orderInfo, operator, StringUtil.isNull(model.getRemark()));
    }

    @Override
    public void handOther() {
        // 处理商品
        this.initGoodsSku();
    }

    /**
     * 处理商品
     */
    private void initGoodsSku() {
        List<OrderGoods> orderGoodsList = orderGoodsDao.listByOrder(orderInfo.getId());
        for (OrderGoods orderGoods : orderGoodsList) {
            int proCount=0;
            proCount=goodsPromotionRecordService.initGoodsPromotion(orderGoods,proCount);
            goodsSkuDao.updateGoodsSkuStock(orderGoods.getGoodsSku().getId(), orderGoods.getNumber()+proCount);
        }
    }
}
