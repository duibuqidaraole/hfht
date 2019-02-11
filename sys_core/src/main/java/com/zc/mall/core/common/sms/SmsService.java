package com.zc.mall.core.common.sms;

import com.alibaba.fastjson.JSONObject;


/**
 * 短信发送
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月13日
 */
public interface SmsService {

    public JSONObject sendSms(String mobile, String content) throws Exception;

}
