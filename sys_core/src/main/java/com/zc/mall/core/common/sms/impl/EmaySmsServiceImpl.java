package com.zc.mall.core.common.sms.impl;

import com.alibaba.fastjson.JSONObject;
import com.zc.mall.core.common.global.Global;
import com.zc.mall.core.common.sms.SmsService;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.encrypt.MD5Util;
import com.zc.sys.common.util.http.UtilHttp;
import com.zc.sys.common.util.log.LogUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;

/**
 * 短信发送
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月13日
 */
@Service("emaySmsService")
public class EmaySmsServiceImpl implements SmsService {

    /**
     * 密码
     **/
    public static final String EMAY_SMS_PASSWORD = "emay_sms_password";
    /**
     * APPID
     **/
    public static final String EMAY_SMS_APPID = "emay_sms_appid";
    /**
     * sign信息
     **/
    public static final String EMAY_SMS_SIGN_INFO = "emay_sms_sign_info";


    @Override
    public JSONObject sendSms(String mobile, String content) throws Exception {
        String password = Global.getValue(EMAY_SMS_PASSWORD);
        String appId = Global.getValue(EMAY_SMS_APPID);
        String signInfo = Global.getValue(EMAY_SMS_SIGN_INFO);
        if (StringUtil.isBlank(password) || StringUtil.isBlank(appId) || StringUtil.isBlank(signInfo)) {
            LogUtil.info("短信发送失败，配置信息有误");
            return null;
        }
        content = signInfo + content;
        String timestamp = DateUtil.dateStr3(DateUtil.getNow());
        String secretKey = MD5Util.MD5(appId + password + timestamp);
        String param = "appId=" + appId + "&sign=" + secretKey + "&mobiles=" + mobile + "&content=" + URLEncoder.encode(content, "UTF-8") + "&timestamp=" + timestamp;
        String resultStr = UtilHttp.sendPost("http://bjmtn.b2m.cn/simpleinter/sendSMS", param);
        LogUtil.info("亿美短信发送结果：" + resultStr);
        JSONObject json = JSONObject.parseObject(resultStr);
        String retCode = json.getString("code");
        String result = "";
        String resultCode = "";
        if (retCode.equals("SUCCESS")) {
            resultCode = "0000";
            result = "发送成功";
        } else {
            resultCode = retCode;
            result = "发送失败";
        }
        json.put("resultCode", resultCode);
        json.put("result", result);
        return json;
    }

}
