package com.zc.mall.mall.service;

import com.zc.mall.mall.model.OrderInfoModel;
import com.zc.sys.common.form.Result;

/**
 * 订单信息表
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
public interface OrderInfoService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(OrderInfoModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(OrderInfoModel model);

    /**
     * 支付请求
     *
     * @param model
     * @return
     */
    public Result payRequest(OrderInfoModel model);

    /**
     * 支付
     *
     * @param model
     * @return
     */
    public Result pay(OrderInfoModel model);

    /**
     * 发货
     *
     * @param model
     * @return
     */
    public Result send(OrderInfoModel model);

    /**
     * 收货
     *
     * @param model
     * @return
     */
    public Result receive(OrderInfoModel model);

    /**
     * 用户退款
     *
     * @param model
     * @return
     */
    public Result refundUser(OrderInfoModel model);

    /**
     * 退款处理
     *
     * @param model
     * @return
     */
    public Result refundDeal(OrderInfoModel model);

    /**
     * 退款支付
     *
     * @param model
     * @return
     */
    public Result refundPay(OrderInfoModel model);

    /**
     * 超时自动收货
     *
     * @param model
     * @return
     */
    public Result autoReceive();

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(OrderInfoModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(OrderInfoModel model);

    /**
     * 关闭订单
     *
     * @param model
     * @return
     */
    public Result close(OrderInfoModel model);

    /**
     * 自动关闭订单
     *
     * @return
     */
    public Result autoClose();

    /**
     * 根据用户id获取各个状态下的订单数目（有做内部处理，多个状态对应一个数目）
     *
     * @param id
     * @return
     */
    Object getCountByState(long id);

    /**
     * 根据orderNo查询订单
     *
     * @param model
     * @return
     */
    Object getByOrderNo(OrderInfoModel model);

    /**
     * 自动处理用户退款
     */
    public void autoDealRefundUser();
}