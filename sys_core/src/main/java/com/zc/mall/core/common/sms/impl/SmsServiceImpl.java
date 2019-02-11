package com.zc.mall.core.common.sms.impl;

import com.alibaba.fastjson.JSONObject;
import com.zc.mall.core.common.sms.SmsService;
import org.springframework.stereotype.Service;

/**
 * 短信发送
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月13日
 */
@Service("testSmsService")
public class SmsServiceImpl implements SmsService {

    @Override
    public JSONObject sendSms(String mobile, String content) {
        JSONObject json = new JSONObject();
        json.put("resultCode", "0000");
        json.put("result", "测试-发送成功");
        return json;
    }

}
