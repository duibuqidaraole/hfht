package com.zc.mall.core.common.pay.wx;

import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.common.constant.BaseConstant.OrderNid;
import com.zc.mall.core.common.global.Global;
import com.zc.mall.core.common.queue.work.QueueAbstract;
import com.zc.mall.core.sys.constant.ConfigParamConstant;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.util.http.RequestUtil;
import com.zc.sys.common.util.reflect.ReflectUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信支付
 * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1
 *
 * @author zp
 */
public class WxPayClient {

    private static Logger log = LoggerFactory.getLogger(WxPayClient.class);

    /**
     * 统一下单-h5支付
     *
     * @return
     * @throws Exception
     */
    public static Map<String, String> unifiedorder(String openid, String no, String body, double amount) throws Exception {
        WXPayConfig wxPayConfig = new MyWXPayConfig();
        WXPay wxPay = new WXPay(wxPayConfig);
        Map<String, String> reqData = new HashMap<String, String>();
        // openid
        reqData.put("openid", openid);
        // 商品描述
        reqData.put("body", body);
        // 商户订单号
        reqData.put("out_trade_no", no);
        // 标价金额
        reqData.put("total_fee", StringUtil.moneyToCents(amount));
        // 终端IP
        reqData.put("spbill_create_ip", RequestUtil.getClientIp());
        // 通知地址
        reqData.put("notify_url", Global.getValue(ConfigParamConstant.SYS_PARAM_WEB_URL) +
                Global.getValue(ConfigParamConstant.SYS_PARAM_WX_PAY_NOTIFY));
        // 交易类型
        reqData.put("trade_type", "JSAPI");

        Map<String, String> respData = wxPay.unifiedOrder(reqData);
        log.info("统一下单返回参数：" + respData.toString());
        String result_code = respData.get("result_code");
        String return_msg = respData.get("return_msg");
        if (!"SUCCESS".equals(result_code) && !"OK".equals(return_msg)) {
            throw new BusinessException(return_msg);
        }
        String prepay_id = respData.get("prepay_id");
        Map<String, String> reqH5Data = new HashMap<String, String>();
        wxPay.fillH5Data(reqH5Data, prepay_id);
        log.info("H5请求参数：" + reqH5Data.toString());

        //支付信息
        payInfo(no, body, amount);
        return reqH5Data;
    }

    private static void payInfo(String no, String body, double amount) {
        Object orderPayHistoryModel = ReflectUtil.invokeConstructorMethod(BaseConstant.ORDERPAYHISTORY_MODEL, no, body, amount);
        //发送队列
        QueueAbstract.send(StringUtil.getSerialNumber(), OrderNid.ORDER_NID_ORDER_INFO_PAY_HISTORY.getNid(), BaseConstant.QueueCode.QUEUE_CODE_OTHER.getCode(), orderPayHistoryModel, null);
    }

}
