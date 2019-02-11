package com.zc.mall.mall.service.impl.business.deal;

import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.common.global.Global;
import com.zc.mall.core.sys.constant.ConfigParamConstant;
import com.zc.mall.core.user.dao.UserAddressDao;
import com.zc.mall.core.user.dao.UserDao;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.entity.UserAddress;
import com.zc.mall.mall.constant.OrderInfoLogTypeEnum;
import com.zc.mall.mall.constant.OrderInfoStateEnum;
import com.zc.mall.mall.dao.GoodsSkuDao;
import com.zc.mall.mall.dao.OrderGoodsDao;
import com.zc.mall.mall.dao.OrderInfoDao;
import com.zc.mall.mall.dao.OrderLogisticsDao;
import com.zc.mall.mall.entity.GoodsSku;
import com.zc.mall.mall.entity.OrderGoods;
import com.zc.mall.mall.entity.OrderInfo;
import com.zc.mall.mall.entity.OrderLogistics;
import com.zc.mall.mall.model.OrderInfoModel;
import com.zc.mall.mall.model.OrderLogisticsModel;
import com.zc.mall.mall.service.OrderInfoLogService;
import com.zc.mall.mall.service.impl.GoodsPromotionRecordServiceImpl;
import com.zc.mall.mall.service.impl.business.BaseOrderInfo;
import com.zc.mall.promotion.constant.BasePromotionConstant;
import com.zc.mall.promotion.dao.BonusCouponsRecordDao;
import com.zc.mall.promotion.dao.UserVipCouponsDao;
import com.zc.mall.promotion.entity.BonusCouponsRecord;
import com.zc.mall.promotion.model.BonusCouponsRecordModel;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.util.calculate.BigDecimalUtil;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单添加
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月19日
 */
@Component
public class OrderInfoAdd extends BaseOrderInfo {

    @Resource
    private OrderInfoDao orderInfoDao;
    @Resource
    private UserDao userDao;
    @Resource
    private GoodsSkuDao goodsSkuDao;
    @Resource
    private OrderGoodsDao orderGoodsDao;
    @Resource
    private OrderLogisticsDao orderLogisticsDao;
    @Resource
    private UserAddressDao userAddressDao;
    @Resource
    private UserVipCouponsDao userVipCouponsDao;
    @Resource
    private OrderInfoLogService orderInfoLogService;
    @Resource
    private BonusCouponsRecordDao bonusCouponsRecordDao;
    @Resource
    private GoodsPromotionRecordServiceImpl goodsPromotionRecordServiceImpl;

    private OrderInfo orderInfo;
    /**
     * 商品总价
     **/
    private double amount;
    /**
     * 折扣
     **/
    private double discountAdd;
    /**
     * 红包id
     **/
    private long bonusCouponsRecordId;
    /**
     * 商品计数
     **/
    private int itemCount;
    /**
     * 实际支付金额
     */
    private double amountReal;
    /**
     * vip折扣
     */
    private double vipRate;

    @Override
    public void initGlobalVariable() {
        orderInfo = null;
        amount = 0;
        discountAdd = 0;
        bonusCouponsRecordId = 0;
        itemCount = 0;
        amountReal = 0;
        vipRate = 0;
    }

    @Override
    public void check() {
        if (model.getUser() == null || model.getUser().getId() <= 0) {
            throw new BusinessException("参数错误！");
        }
        User user = userDao.find(model.getUser().getId());
        if (user == null) {
            throw new BusinessException("参数错误！");
        }
        if (model.getOrderGoodsList() == null || model.getOrderGoodsList().size() <= 0) {
            throw new BusinessException("参数错误！");
        }
        if (model.getBonusCouponsRecordModel() != null && model.getBonusCouponsRecordModel().getId() > 0) {
            bonusCouponsRecordId = model.getBonusCouponsRecordModel().getId();
        }
    }

    @Override
    public void init() {
        orderInfo = model.prototype();
        orderInfoDao.save(orderInfo);
        // vip折扣
        vipRate = userVipCouponsDao.getVipValueByUserId(orderInfo.getUser().getId());
        // 物流
        this.initOrderLogistics();
        // 订单商品
        this.initAddOrderGoods();

       /* // 支付金额
        double amountReal = amount;
        // 折扣金额
        amountReal = BigDecimalUtil.sub(amountReal, discountAdd);

        if (vipRate > 0) {
            amountReal = BigDecimalUtil.mul(amountReal, vipRate);
        }*/

        // 优惠券折扣
        double promotionCouponsAmount = 0;
        if (bonusCouponsRecordId > 0) {
            BonusCouponsRecord bonusCouponsRecord = bonusCouponsRecordDao.find(bonusCouponsRecordId);
            if (bonusCouponsRecord == null) {
                throw new BusinessException("红包不存在");
            }
            promotionCouponsAmount = bonusCouponsRecord.getRealAmount();
            double grantSingleInvestAmount = bonusCouponsRecord.getBonusCoupons().getGrantSingleInvestAmount();
            if (amountReal <= grantSingleInvestAmount && grantSingleInvestAmount > 0) {
                throw new BusinessException("红包不符合使用条件");
            }
            amountReal = BigDecimalUtil.sub(amountReal, promotionCouponsAmount);
            if (amountReal <= 0) {
                throw new BusinessException("红包不符合使用条件");
            }
        }
        // 运费
        double freight = Global.getdouble(ConfigParamConstant.SYS_PARAM_FREIGHT);
        if (freight > 0) {
            amountReal = BigDecimalUtil.add(amountReal, freight);
        }
        orderInfo.setAmount(amount);
        orderInfo.setAmountReal(amountReal);
        orderInfo.setFreight(freight);
        orderInfo.setDiscount(discountAdd);
        orderInfo.setIsComment(BaseConstant.BUSINESS_STATE_NO);
        orderInfo.setPromotionCouponsAmount(promotionCouponsAmount);
        orderInfo.setNo(StringUtil.getSerialNumber());
        orderInfo.setItemCount(itemCount);
        orderInfo.setState(OrderInfoStateEnum.ORDER_INFO_STATE_PAY.getOrderInfoState());
        orderInfo.setAddTime(DateUtil.getNow());
    }

    @Override
    public void persist() {
        orderInfoDao.update(orderInfo);
    }

    @Override
    public void log() {
        orderInfoLogService.add(OrderInfoLogTypeEnum.ORDER_INFO_TYPE_ADD.getOrderInfoLogType(),
                orderInfo, orderInfo.getUser(), "添加订单：" + orderInfo.getNo());
    }

    @Override
    public void handOther() {
        // 优惠券处理
        this.initPromotionCoupons();
        model = OrderInfoModel.instance(orderInfo);
    }

    /**
     * 物流
     */
    private void initOrderLogistics() {
        OrderLogisticsModel orderLogisticsModel = new OrderLogisticsModel();
        UserAddress userAddress = userAddressDao.find(model.getUserAddressModel().getId());
        if (userAddress == null) {
            throw new BusinessException("请传入地址信息");
        }
        orderLogisticsModel.setAddress(userAddress.getAddress());
        orderLogisticsModel.setProvince(userAddress.getProvince());
        orderLogisticsModel.setCity(userAddress.getCity());
        orderLogisticsModel.setArea(userAddress.getArea());
        orderLogisticsModel.setName(userAddress.getName());
        orderLogisticsModel.setMobile(userAddress.getMobile());
        orderLogisticsModel.setPostCode(userAddress.getPostCode());
        orderLogisticsModel.setOrderInfo(orderInfo);
        OrderLogistics save = orderLogisticsDao.save(orderLogisticsModel.prototype());
        orderInfo.setOrderLogistics(save);
    }

    /**
     * 添加初始化订单商品
     */
    private void initAddOrderGoods() {
        List<OrderGoods> list = new ArrayList<OrderGoods>();
        for (OrderGoods orderGoods : model.getOrderGoodsList()) {
            // 活动买赠计数
            int promotionCount = 0;
            // 校验订单商品
            GoodsSku goodsSku = this.checkAddOrderGoods(orderGoods);
            //初始化活动
            promotionCount = goodsPromotionRecordServiceImpl.initGoodsPromotion(orderGoods, promotionCount);
            // 更新库存
            orderGoods.setGoodsSku(goodsSku);
            goodsSkuDao.updateGoodsSkuStock(goodsSku.getId(), -orderGoods.getNumber() - promotionCount);
            // 初始化参数
            orderGoods.setOrderInfo(orderInfo);
            orderGoods.setGoodsName(orderGoods.getGoodsSku().getName());
            orderGoods.setGiftNumber(promotionCount);

            double price = BigDecimalUtil.mul(orderGoods.getGoodsSku().getPrince(), orderGoods.getNumber());
            double discount = BigDecimalUtil.mul(orderGoods.getGoodsSku().getDiscount(), orderGoods.getNumber());
            orderGoods.setPrince(price);
            orderGoods.setDiscount(discount);
            // 总价
            amount = BigDecimalUtil.add(orderGoods.getPrince(), amount);
            price = BigDecimalUtil.sub(price, orderGoods.getDiscount());
            if (vipRate > 0 && goodsSku.getGoodsSpu().getIsVipPrince() == BaseConstant.BUSINESS_STATE_YES){
                price=BigDecimalUtil.mul(price, vipRate);
                orderGoods.setVipRate(vipRate);
            }
            amountReal = BigDecimalUtil.add(price, amountReal);
            // 总折扣
            discountAdd = BigDecimalUtil.add(orderGoods.getDiscount(), discountAdd);
            //计数
            itemCount = orderGoods.getNumber() + itemCount + promotionCount;
            list.add(orderGoods);
        }
        model.setOrderGoodsList(list);
        orderGoodsDao.save(model.getOrderGoodsList());
    }


    /**
     * 校验订单商品
     *
     * @param orderGoods
     */
    private GoodsSku checkAddOrderGoods(OrderGoods orderGoods) {
        if (orderGoods.getGoodsSku() == null || orderGoods.getGoodsSku().getId() <= 0) {
            throw new BusinessException("参数错误！");
        }
        GoodsSku goodsSku = goodsSkuDao.find(orderGoods.getGoodsSku().getId());
        if (goodsSku == null) {
            throw new BusinessException("参数错误！");
        }
        if (orderGoods.getNumber() <= 0) {
            throw new BusinessException("参数错误！");
        }
        if (goodsSku.getOnSale() != BaseConstant.BUSINESS_STATE_YES) {
            throw new BusinessException("goods_not_on_sale");
        }
        if (goodsSku.getStock() < orderGoods.getNumber()) {
            throw new BusinessException("insufficient_inventory");
        }
        return goodsSku;

    }

    /**
     * 优惠券处理
     */
    private void initPromotionCoupons() {
        if (bonusCouponsRecordId > 0) {
            BonusCouponsRecordModel.useBonusCoupons(bonusCouponsRecordId, BasePromotionConstant.COUPONS_USETYPE_DEDUCITION, orderInfo.getId() + "", model.getRemark());
        }
    }
}
