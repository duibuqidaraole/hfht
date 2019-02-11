package com.zc.mall.mall.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.user.model.UserAddressModel;
import com.zc.mall.core.user.model.UserModel;
import com.zc.mall.mall.entity.OrderInfo;
import com.zc.mall.mall.entity.OrderInfoLog;
import com.zc.mall.mall.service.OrderInfoLogService;
import com.zc.mall.promotion.model.BonusCouponsRecordModel;
import com.zc.sys.common.model.jpa.Page;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 订单信息表
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
public class OrderInfoModel extends OrderInfo {

    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;

    /**
     * 物流
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private OrderLogisticsModel orderLogisticsModel;
    /**
     * 用户地址
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UserAddressModel userAddressModel;
    /**
     * 支付参数
     **/
    private Map<String, String> payData;
    /**
     * 红包
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BonusCouponsRecordModel bonusCouponsRecordModel;
    /**
     * 当前页码
     **/
    private int pageNo;
    /**
     * 每页数据条数
     **/
    private int pageSize = Page.ROWS;
    /**
     * 条件查询
     **/
    private String searchName;
    /**
     * 查询多个state
     **/
    private String stateList;
    private List<OrderInfoLogModel> orderInfoLogModelList;

    /**
     * 用户
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UserModel userModel;

    /**
     * 订单商品ModelList
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<OrderGoodsModel> orderGoodsModelList;

    /**
     * 订单商品评论ModelList
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<OrderGoodsCommentModel> orderGoodsCommentModelList;

    /**
     * 为了前台查询用的状态
     **/
    private Integer searchState;

    /**
     * 退款原因
     **/
    private String refundReason;
    /**
     * 退款说明
     **/
    private String refundExplanation;
    /**
     * 支付时间
     **/
    private Date paymentTime;
    /**
     * 付款时间
     **/
    private Date payTime;
    /**
     * 退款时间
     **/
    private Date refundTime;
    /**
     * 发货时间
     **/
    private Date sendTime;
    /**
     * 订单关闭时间
     **/
    private Date closeTime;

    /**
     * 实体转换model
     */
    public static OrderInfoModel instance(OrderInfo orderInfo) {
        if (orderInfo == null || orderInfo.getId() <= 0) {
            return null;
        }
        OrderInfoModel orderInfoModel = new OrderInfoModel();
        BeanUtils.copyProperties(orderInfo, orderInfoModel);
        //初始化订单额外参数
        initOrderInfoModelTime(orderInfoModel);
        return orderInfoModel;
    }

    /**
     * 初始化参数
     *
     * @param orderInfoModel
     */
    public static void initOrderInfoModelTime(OrderInfoModel orderInfoModel) {
        //初始化支付时间
        OrderInfoLogService orderInfoLogService = BeanUtil.getBean(OrderInfoLogService.class);
        List<OrderInfoLog> orderInfoLogList = orderInfoLogService.findList(0, orderInfoModel.getId());
        for (OrderInfoLog orderInfoLog : orderInfoLogList) {
            OrderInfoLogModel.initOrderInfoModelByType(orderInfoModel, orderInfoLog);
        }
    }


    /**
     * model转换实体
     */
    public OrderInfo prototype() {
        OrderInfo orderInfo = new OrderInfo();
        BeanUtils.copyProperties(this, orderInfo);
        return orderInfo;
    }

    /**
     * 获取【当前页码】
     **/
    public int getPageNo() {
        return pageNo;
    }

    /**
     * 设置【当前页码】
     **/
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * 获取【每页数据条数】
     **/
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置【每页数据条数】
     **/
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取【条件查询】
     **/
    public String getSearchName() {
        return searchName;
    }

    /**
     * 设置【条件查询】
     **/
    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    /**
     * 获取【物流】
     **/
    public OrderLogisticsModel getOrderLogisticsModel() {
        return orderLogisticsModel;
    }

    /**
     * 设置【物流】
     **/
    public void setOrderLogisticsModel(OrderLogisticsModel orderLogisticsModel) {
        this.orderLogisticsModel = orderLogisticsModel;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    /**
     * 获取【支付参数】
     **/
    public Map<String, String> getPayData() {
        return payData;
    }

    /**
     * 设置【支付参数】
     **/
    public void setPayData(Map<String, String> payData) {
        this.payData = payData;
    }

    public UserAddressModel getUserAddressModel() {
        return userAddressModel;
    }

    public void setUserAddressModel(UserAddressModel userAddressModel) {
        this.userAddressModel = userAddressModel;
    }

    public BonusCouponsRecordModel getBonusCouponsRecordModel() {
        return bonusCouponsRecordModel;
    }

    public void setBonusCouponsRecordModel(BonusCouponsRecordModel bonusCouponsRecordModel) {
        this.bonusCouponsRecordModel = bonusCouponsRecordModel;
    }

    public List<OrderGoodsModel> getOrderGoodsModelList() {
        return orderGoodsModelList;
    }

    public void setOrderGoodsModelList(List<OrderGoodsModel> orderGoodsModelList) {
        this.orderGoodsModelList = orderGoodsModelList;
    }

    public String getStateList() {
        return stateList;
    }

    public void setStateList(String stateList) {
        this.stateList = stateList;
    }

    public List<OrderInfoLogModel> getOrderInfoLogModelList() {
        return orderInfoLogModelList;
    }

    public void setOrderInfoLogModelList(List<OrderInfoLogModel> orderInfoLogModelList) {
        this.orderInfoLogModelList = orderInfoLogModelList;
    }

    public List<OrderGoodsCommentModel> getOrderGoodsCommentModelList() {
        return orderGoodsCommentModelList;
    }

    public void setOrderGoodsCommentModelList(List<OrderGoodsCommentModel> orderGoodsCommentModelList) {
        this.orderGoodsCommentModelList = orderGoodsCommentModelList;
    }

    public Integer getSearchState() {
        return searchState;
    }

    public void setSearchState(Integer searchState) {
        this.searchState = searchState;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    public String getRefundExplanation() {
        return refundExplanation;
    }

    public void setRefundExplanation(String refundExplanation) {
        this.refundExplanation = refundExplanation;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }
}