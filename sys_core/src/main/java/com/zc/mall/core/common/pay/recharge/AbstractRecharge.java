package com.zc.mall.core.common.pay.recharge;

import com.alibaba.fastjson.JSONObject;
import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.common.global.Global;
import com.zc.mall.core.common.pay.constant.PayConstant;
import com.zc.sys.common.exception.BusinessException;

import javax.servlet.http.HttpServletRequest;

/**
 * 充值
 *
 * @author zp
 */
public abstract class AbstractRecharge {

    /**
     * 获取类
     *
     * @return
     */
    public static AbstractRecharge getRechargeWay() {
        String rechargeWayStr = Global.getValue(PayConstant.RECHARGE_WAY);
        AbstractRecharge rechargeWay = BeanUtil.getBean(rechargeWayStr + "Service");
        if (rechargeWay == null) {
            throw new BusinessException("充值通道未配置....");
        }
        rechargeWay.init();
        return rechargeWay;
    }

    /**
     * 表单提交
     *
     * @param money      金额
     * @param bankCardNo 银行卡
     * @param orderNo    订单
     * @return
     */
    public abstract JSONObject createGateWay(double money, String bankCardNo, String orderNo);

    /**
     * 回调
     *
     * @param params
     * @return
     */
    public abstract boolean callGateWay(HttpServletRequest request);

    /**
     * 银行卡转换
     *
     * @param bank
     * @return
     */
    public abstract String convertBank(String bankCardNo);

    /**
     * pc快捷
     *
     * @param money      金额
     * @param orderNo    订单
     * @param bankCardNo 银行卡
     * @param userId     用户id
     * @param cardNo     身份证
     * @param realName   姓名
     * @return
     */
    public abstract JSONObject createPcAuth(double money, String orderNo, String bankCardNo, String userId, String cardNo, String realName);

    /**
     * 初始化
     */
    public abstract void init();
}
