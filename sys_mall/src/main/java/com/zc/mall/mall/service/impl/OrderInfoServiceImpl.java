package com.zc.mall.mall.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.user.model.UserModel;
import com.zc.mall.mall.constant.OrderInfoLogTypeEnum;
import com.zc.mall.mall.constant.OrderInfoStateEnum;
import com.zc.mall.mall.dao.GoodsSkuSpecValueDao;
import com.zc.mall.mall.dao.OrderGoodsCommentDao;
import com.zc.mall.mall.dao.OrderInfoDao;
import com.zc.mall.mall.dao.OrderInfoLogDao;
import com.zc.mall.mall.entity.OrderGoodsComment;
import com.zc.mall.mall.entity.OrderInfo;
import com.zc.mall.mall.entity.OrderInfoLog;
import com.zc.mall.mall.model.*;
import com.zc.mall.mall.service.OrderInfoService;
import com.zc.mall.mall.service.impl.business.deal.*;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单信息表
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Resource
    private OrderInfoDao orderInfoDao;
    @Resource
    private OrderInfoLogDao orderInfoLogDao;
    @Resource
    private OrderGoodsCommentDao orderGoodsCommentDao;
    @Resource
    private GoodsSkuSpecValueDao goodsSkuSpecValueDao;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result list(OrderInfoModel model) {
        PageDataList<OrderInfo> pageDataList = orderInfoDao.list(model);
        PageDataList<OrderInfoModel> pageDataList_ = new PageDataList<>();
        pageDataList_.setPage(pageDataList.getPage());
        List<OrderInfoModel> list = new ArrayList<>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (OrderInfo orderInfo : pageDataList.getList()) {
                OrderInfoModel model_ = OrderInfoModel.instance(orderInfo);
                model_.setUserModel(UserModel.instance(orderInfo.getUser()));
                model_.setOrderLogisticsModel(OrderLogisticsModel.instance(orderInfo.getOrderLogistics()));
                List<OrderGoodsModel> orderGoodsModelList = OrderGoodsModel.instance(orderInfo.getOrderGoodsList(), true);
                if (orderGoodsModelList != null) {
                    for (OrderGoodsModel orderGoodsModel : orderGoodsModelList) {
                        orderGoodsModel.setChooseSpecVale(goodsSkuSpecValueDao.getSkuChooseSpecValueBySkuId(orderGoodsModel.getGoodsSku().getId()));
                        GoodsPromotionRecordModel goodsPromotionRecordModel = GoodsPromotionRecordModel.instance(orderGoodsModel.getGoodsPromotionRecord());
                        if (goodsPromotionRecordModel!=null){
                            goodsPromotionRecordModel.setGoodsPromotionModel(GoodsPromotionModel.instance(goodsPromotionRecordModel.getGoodsPromotion()));
                        }
                        GoodsSkuModel goodsSkuModel = orderGoodsModel.getGoodsSkuModel();
                        goodsSkuModel.setGoodsPromotionRecordModel(goodsPromotionRecordModel);
                        orderGoodsModel.setGoodsSkuModel(goodsSkuModel);
                    }
                }
                model_.setOrderGoodsModelList(orderGoodsModelList);
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
    @Transactional
    public Result add(OrderInfoModel model) {
        OrderInfoAdd orderInfoAdd = BeanUtil.getBean(OrderInfoAdd.class);
        orderInfoAdd.executer(model);
        return Result.success().setData(orderInfoAdd.getModel());
    }

    /**
     * 支付请求
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result payRequest(OrderInfoModel model) {
        OrderInfoPayRequest orderInfoPayRequest = BeanUtil.getBean(OrderInfoPayRequest.class);
        orderInfoPayRequest.executer(model);
        return Result.success().setData(model);
    }

    /**
     * 支付
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result pay(OrderInfoModel model) {
        OrderInfoPay orderInfoPay = BeanUtil.getBean(OrderInfoPay.class);
        orderInfoPay.executer(model);
        return Result.success();
    }

    /**
     * 发货
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result send(OrderInfoModel model) {
        OrderInfoSend orderInfoSend = BeanUtil.getBean(OrderInfoSend.class);
        orderInfoSend.executer(model);
        return Result.success();
    }

    /**
     * 收货
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result receive(OrderInfoModel model) {
        OrderInfoReceive orderInfoReceive = BeanUtil.getBean(OrderInfoReceive.class);
        orderInfoReceive.executer(model);
        return Result.success();
    }

    /**
     * 用户退款
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result refundUser(OrderInfoModel model) {
        OrderInfoRefundUser orderInfoRefundUser = BeanUtil.getBean(OrderInfoRefundUser.class);
        orderInfoRefundUser.executer(model);
        return Result.success();
    }

    /**
     * 自动处理用户退款
     */
    @Override
    @Transactional
    public void autoDealRefundUser() {
        List<OrderInfo> list = orderInfoDao.listTimeOutRefundUser();
        for (OrderInfo orderInfo : list) {
            OrderInfoLog orderInfoLog = orderInfoLogDao.findOrderInfoLog(OrderInfoLogTypeEnum.ORDER_INFO_TYPE_USER_REFUND.getOrderInfoLogType(), orderInfo.getId());
            if (orderInfoLog == null) {
                continue;
            }
            int betweenDays = DateUtil.daysBetween(DateUtil.getDayStartTime(orderInfoLog.getAddTime().getTime() / 1000), DateUtil.getDayStartTime(0));
            if (betweenDays > 5) {
                OrderInfoModel model = OrderInfoModel.instance(orderInfo);
                model.setRemark("超时自动处理用户退款");
                model.setState(BaseConstant.BUSINESS_STATE_YES);
                refundDeal(model);
            }
        }
    }

    /**
     * 退款处理
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result refundDeal(OrderInfoModel model) {
        OrderInfoRefundDeal orderInfoRefundDeal = BeanUtil.getBean(OrderInfoRefundDeal.class);
        orderInfoRefundDeal.executer(model);
        return Result.success();
    }

    /**
     * 退款支付
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result refundPay(OrderInfoModel model) {
        OrderInfoRefundPay orderInfoRefundPay = BeanUtil.getBean(OrderInfoRefundPay.class);
        orderInfoRefundPay.executer(model);
        return Result.success();
    }

    /**
     * 超时自动收货
     *
     * @return
     */
    @Override
    @Transactional
    public Result autoReceive() {
        List<OrderInfo> list = orderInfoDao.listTimeOutReceive();
        for (OrderInfo orderInfo : list) {
            OrderInfoLog orderInfoLog = orderInfoLogDao.findOrderInfoLog(OrderInfoLogTypeEnum.ORDER_INFO_TYPE_SEND.getOrderInfoLogType(), orderInfo.getId());
            if (orderInfoLog == null) {
                continue;
            }
            int betweenDays = DateUtil.daysBetween(DateUtil.getDayStartTime(orderInfoLog.getAddTime().getTime() / 1000), DateUtil.getDayStartTime(0));
            if (betweenDays > 15) {
                OrderInfoModel model = OrderInfoModel.instance(orderInfo);
                model.setRemark("超时自动收货");
                this.receive(model);
            }
        }
        return Result.success();
    }

    /**
     * 关闭订单
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result close(OrderInfoModel model) {
        OrderInfoClose orderInfoClose = BeanUtil.getBean(OrderInfoClose.class);
        orderInfoClose.executer(model);
        return Result.success();
    }

    /**
     * 自动关闭订单
     *
     * @return
     */
    @Override
    @Transactional
    public Result autoClose() {
        List<OrderInfo> list = orderInfoDao.findTimeOutPay();
        for (OrderInfo orderInfo : list) {
            OrderInfoLog orderInfoLog = orderInfoLogDao.findOrderInfoLog(OrderInfoLogTypeEnum.ORDER_INFO_TYPE_ADD.getOrderInfoLogType(), orderInfo.getId());
            if (orderInfoLog == null) {
                continue;
            }
            double betweenDays = DateUtil.msBetween(DateUtil.rollMinute(orderInfoLog.getAddTime(), 15), DateUtil.getNow());
            if (betweenDays < 0) {
                OrderInfoModel model = OrderInfoModel.instance(orderInfo);
                model.setRemark("支付超时");
                this.close(model);
            }
        }
        return Result.success();
    }

    /**
     * 根据用户id获取各个状态下的订单数目（有做内部处理，多个状态对应一个数目）
     *
     * @param id
     * @return
     */
    @Override
    public Object getCountByState(long id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("waitPay", orderInfoDao.getCountByState(id, OrderInfoStateEnum.ORDER_INFO_STATE_PAY.getOrderInfoState(), 0));
        //+ orderInfoDao.getCountByState(id, OrderInfoStateEnum.ORDER_INFO_STATE_REFUND.getOrderInfoState(), OrderInfoStateEnum.ORDER_INFO_STATE_SEND.getOrderInfoState(), 0)
        jsonObject.put("waitSend", orderInfoDao.getCountByState(id, OrderInfoStateEnum.ORDER_INFO_STATE_SEND.getOrderInfoState(), 0));
        //+ orderInfoDao.getCountByState(id, OrderInfoStateEnum.ORDER_INFO_STATE_REFUND.getOrderInfoState(), OrderInfoStateEnum.ORDER_INFO_STATE_RECEIVE.getOrderInfoState(), 0)
        jsonObject.put("waitReceive", orderInfoDao.getCountByState(id, OrderInfoStateEnum.ORDER_INFO_STATE_RECEIVE.getOrderInfoState(), 0) );
        jsonObject.put("waitRecommend", orderInfoDao.getCountByState(id, OrderInfoStateEnum.ORDER_INFO_STATE_COMPLETE.getOrderInfoState(), BaseConstant.BUSINESS_STATE_NO));
        // 11 12 16
        jsonObject.put("afterSale",orderInfoDao.getCountByState(id, OrderInfoStateEnum.ORDER_INFO_STATE_REFUND.getOrderInfoState(), 0) + orderInfoDao.getCountByState(id, OrderInfoStateEnum.ORDER_INFO_STATE_REFUND_PAY.getOrderInfoState(), 0) + orderInfoDao.getCountByState(id, OrderInfoStateEnum.ORDER_INFO_STATE_REFUND_COMPLETE.getOrderInfoState(), 0));
        return jsonObject;
    }

    /**
     * 根据orderNo查询订单
     *
     * @param model
     * @return
     */
    @Override
    public Object getByOrderNo(OrderInfoModel model) {
        if (model == null || StringUtil.isBlank(model.getNo())) {
            throw new BusinessException("请输入订单编号");
        }

        OrderInfo orderInfo = orderInfoDao.getByOrderNo(model);
        return Result.success().setData(OrderInfoModel.instance(orderInfo));
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result update(OrderInfoModel model) {
//        OrderInfo orderInfo = this.checkUpdate(model);
//        this.initUpdate(model, orderInfo);
//        orderInfoDao.update(model.prototype());
        return Result.success();
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result getById(OrderInfoModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        OrderInfo orderInfo = orderInfoDao.find(model.getId());
        if (orderInfo == null) {
            return Result.error("参数错误！");
        }
        OrderInfoModel orderInfoModel = OrderInfoModel.instance(orderInfo);
        OrderGoodsModel orderGoods1 = new OrderGoodsModel();
        orderGoods1.setOrderInfo(orderInfo);
        List<OrderGoodsModel> orderGoodsModelList = OrderGoodsModel.instance(orderInfo.getOrderGoodsList(), true);
        for (OrderGoodsModel orderGoodsModel : orderGoodsModelList) {
            List<OrderGoodsComment> orderGoodsCommentList = orderGoodsCommentDao.findByOrderGoods(orderGoodsModel.getId());
            ArrayList<OrderGoodsCommentModel> goodsCommentModelArrayList = new ArrayList<>();
            for (OrderGoodsComment orderGoodsComment : orderGoodsCommentList) {
                goodsCommentModelArrayList.add(OrderGoodsCommentModel.instance(orderGoodsComment));
            }
            orderGoodsModel.setOrderGoodsCommentModelList(goodsCommentModelArrayList);
            orderGoodsModel.setGoodsSkuModel(GoodsSkuModel.instance(orderGoodsModel.getGoodsSku()));
            orderGoodsModel.setChooseSpecVale(goodsSkuSpecValueDao.getSkuChooseSpecValueBySkuId(orderGoodsModel.getGoodsSku().getId()));
            GoodsPromotionRecordModel goodsPromotionRecordModel = GoodsPromotionRecordModel.instance(orderGoodsModel.getGoodsPromotionRecord());
            if (goodsPromotionRecordModel!=null){
                goodsPromotionRecordModel.setGoodsPromotionModel(GoodsPromotionModel.instance(goodsPromotionRecordModel.getGoodsPromotion()));
            }
            GoodsSkuModel goodsSkuModel = orderGoodsModel.getGoodsSkuModel();
            goodsSkuModel.setGoodsPromotionRecordModel(goodsPromotionRecordModel);
            orderGoodsModel.setGoodsSkuModel(goodsSkuModel);
        }
        orderInfoModel.setOrderGoodsModelList(orderGoodsModelList);
        orderInfoModel.setOrderLogisticsModel(OrderLogisticsModel.instance(orderInfoModel.getOrderLogistics()));
        orderInfoModel.setUserModel(UserModel.instance(orderInfo.getUser()));
        orderInfoModel.setOrderGoodsCommentModelList(orderGoodsCommentDao.getOrderInfoCommentList(orderInfoModel.getId()));
        ArrayList<OrderInfoLogModel> orderInfoLogModels = new ArrayList<>();
        for (OrderInfoLog orderInfoLog : orderInfoLogDao.findList(0, orderInfoModel.getId(), 0, 0)) {
            orderInfoLogModels.add(OrderInfoLogModel.instance(orderInfoLog));
        }
        orderInfoModel.setOrderInfoLogModelList(orderInfoLogModels);
        return Result.success().setData(orderInfoModel);
    }

    /**
     * 校验
     *
     * @param model
     * @return
     */
    private OrderInfo checkUpdate(OrderInfoModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误！");
        }
        OrderInfo orderInfo = orderInfoDao.find(model.getId());
        if (orderInfo == null) {
            throw new BusinessException("参数错误！");
        }
        return orderInfo;
    }

    /**
     * 初始化
     *
     * @param model
     */
    private void initUpdate(OrderInfoModel model, OrderInfo orderInfo) {

    }

}