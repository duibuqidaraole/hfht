package com.zc.mall.core.common.pay.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zc.mall.core.common.global.Global;
import com.zc.mall.core.common.pay.constant.PayConstant;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.encrypt.MD5Util;
import com.zc.sys.common.util.http.UtilHttp;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Service;

/**
 * 羿顿认证
 *
 * @author zp
 */
@Service("yeadunService")
public class YeadunService extends AbstractRealName {

    @Override
    public JSONObject realNameInvok(String realName, String cardNo, String bankCardNo, String mobile) throws Exception {
        String merchCode = Global.getValue(PayConstant.YEADUN_CONFIG_MERCH_CODE);
        String merchPrivate = "";// 私有域
        String merchKey = Global.getValue(PayConstant.YEADUN_CONFIG_MERCH_MERCH_KEY);
//		merchCode = "M100000123";
//		merchKey = "339687d0-cfeb-4366-aa1b-098dd2157484";
        String orderId = System.currentTimeMillis() + cardNo;
        String jsonStr = "{\"certName\":\"" + realName + "\",\"certId\":\"" + cardNo + "\",\"cardId\":\"" + bankCardNo + "\",\"mp\":\"" + mobile + "\"}";
        String tranTime = DateUtil.dateStr3(DateUtil.getNow());
        System.err.println("羿顿四要素认证请求报文:" + jsonStr);
        String sign = MD5Util.md5(merchCode + orderId + "P100007" + jsonStr + merchPrivate + tranTime + merchKey).toLowerCase();
        String param = "merchCode=" + merchCode + "&orderId=" + orderId + "&productCode=P100007&jsonStr=" + jsonStr + "&merchPrivate=" +
                merchPrivate + "&tranTime=" + tranTime + "&sign=" + sign;
        String result = UtilHttp.sendPost("http://119.23.105.236/api/", param);
        JSONObject json = JSON.parseObject(result);
        System.err.println("羿顿四要素认证响应报文：" + json.toJSONString());
        String retCode = json.getString("retCode");
        if (!"0000".equals(retCode)) {
            throw new BusinessException("接口请求失败");
        }
        JSONObject message = json.getJSONObject("retInfo").getJSONObject("message");
        String msg = "";
        String code = message.getString("identify_code");
        ;
        if ("01".equals(StringUtil.isBlank(code))) {
            msg = "认证成功";
            code = "0000";
        } else {
            msg = message.getString("identify_msg");
        }
        JSONObject res = new JSONObject();
        res.put(PayConstant.REALNAME_CODE, code);
        res.put(PayConstant.REALNAME_MSG, msg);
        return res;
    }

}
