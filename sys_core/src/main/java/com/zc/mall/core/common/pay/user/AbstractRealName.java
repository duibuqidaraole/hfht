package com.zc.mall.core.common.pay.user;

import com.alibaba.fastjson.JSONObject;
import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.common.global.Global;
import com.zc.mall.core.common.pay.constant.PayConstant;
import com.zc.sys.common.cache.RedisCacheUtil;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.util.log.LogUtil;
import com.zc.sys.common.util.validate.StringUtil;

/**
 * 实名认证
 *
 * @author zp
 */
public abstract class AbstractRealName {

    /**
     * 实名
     *
     * @param realName   姓名
     * @param cardNo     身份证
     * @param bankCardNo 银行卡号
     * @param mobile     银行卡预留手机号
     * @return
     */
    public boolean doRealName(String realName, String cardNo, String bankCardNo, String mobile) {
        RedisCacheUtil cache = BeanUtil.getBean(RedisCacheUtil.class);
        String key = PayConstant.REALNAME_ERROR_INFO + realName + cardNo + bankCardNo + mobile;
        String errorInfo = cache.getCache(key, String.class);
        if (!StringUtil.isBlank(errorInfo)) {
            LogUtil.info("实名重复认证信息：" + key + "：" + errorInfo);
            throw new BusinessException(errorInfo);
        }
        String msg = "";
        String code = "";
        try {
            JSONObject result = realNameInvok(realName, cardNo, bankCardNo, mobile);
            msg = result.getString(PayConstant.REALNAME_MSG);
            code = result.getString(PayConstant.REALNAME_CODE);
            if (PayConstant.REALNAME_SUCCESS_CODE.equals(code)) {
                return true;
            } else {
                throw new BusinessException(msg);
            }
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        } finally {
            cache.setCode(key, msg, 60 * 60 * 1);
        }
    }

    public static AbstractRealName getRealNameWay() {
        String realNameWayStr = Global.getValue(PayConstant.REALNAME_WAY);
        AbstractRealName realNameWay = BeanUtil.getBean(realNameWayStr + "Service");
        if (realNameWay == null) {
            throw new BusinessException("实名通道未配置....");
        }
        return realNameWay;
    }

    /**
     * 实名调用
     *
     * @param realName   姓名
     * @param cardNo     身份证
     * @param bankCardNo 银行卡号
     * @param mobile     银行卡预留手机号
     * @return
     */
    public abstract JSONObject realNameInvok(String realName, String cardNo, String bankCardNo, String mobile) throws Exception;
}
