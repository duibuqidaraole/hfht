package com.zc.mall.core.common.pay.recharge;

import com.alibaba.fastjson.JSONObject;
import com.zc.mall.core.common.global.Global;
import com.zc.mall.core.common.pay.constant.PayConstant;
import com.zc.mall.core.sys.constant.ConfigParamConstant;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.util.encrypt.MD5Util;
import com.zc.sys.common.util.log.LogUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * 富友支付
 *
 * @author zp
 */
@SuppressWarnings("restriction")
@Service("fuioupayGateWayService")
public class FuioupayGateWayService extends AbstractRecharge {

    private String mchnt_cd;// 商户号
    private String webUrl;// 网站url
    private String mchnt_key;// 32位的商户密钥
    private String url;// 请求url
    private String pcUrl;// 请求url
    private String private_key;// 秘钥

    @Override
    public void init() {
        this.mchnt_cd = Global.getValue(PayConstant.RECHARGE_WAY_FUIOU_MCHNT_CD);
        this.webUrl = Global.getValue(ConfigParamConstant.SYS_PARAM_WEB_URL);
        this.mchnt_key = Global.getValue(PayConstant.RECHARGE_WAY_FUIOU_MCHNT_KEY);
        this.url = Global.getValue(PayConstant.RECHARGE_WAY_FUIOU_URL);
        this.private_key = Global.getValue(PayConstant.RECHARGE_WAY_FUIOU_PRIVATE_KEY);
        this.pcUrl = Global.getValue(PayConstant.RECHARGE_WAY_FUIOU_PC_URL);
    }

    @Override
    public JSONObject createGateWay(double money, String bankCode, String orderNo) {
        String order_id = orderNo;
        String order_amt = StringUtil.moneyToCents(money);
        String order_pay_type = "B2C";
        String page_notify_url = this.webUrl + "/node/fuioupay/gateWay/rechargeCallBack";
        String back_notify_url = this.webUrl + "/node/fuioupay/gateWay/rechargeCallBack";
        String order_valid_time = "10m";
        String iss_ins_cd = convertBank(bankCode);
        String goods_name = "";
        String goods_display_url = "";
        String rem = "";
        String ver = "1.0.1";
        String signDataStr = this.mchnt_cd + "|" + order_id + "|" + order_amt + "|" + order_pay_type + "|" +
                page_notify_url + "|" + back_notify_url + "|" + order_valid_time + "|" +
                iss_ins_cd + "|" + goods_name + "|" + goods_display_url + "|"
                + rem + "|" + ver + "|" + this.mchnt_key;
        String md5 = MD5Util.MD5(signDataStr);
        JSONObject json = new JSONObject();
        json.put("md5", md5);
        json.put("mchnt_cd", this.mchnt_cd);
        json.put("order_id", order_id);
        json.put("order_amt", order_amt);
        json.put("order_pay_type", order_pay_type);
        json.put("page_notify_url", page_notify_url);
        json.put("back_notify_url", back_notify_url);
        json.put("order_valid_time", order_valid_time);
        json.put("iss_ins_cd", iss_ins_cd);
        json.put("goods_name", goods_name);
        json.put("goods_display_url", goods_display_url);
        json.put("rem", rem);
        json.put("ver", ver);
        json.put("url", this.url);
        return json;
    }

    @Override
    public boolean callGateWay(HttpServletRequest request) {
        String order_id = request.getParameter("order_id");
        String order_amt = request.getParameter("order_amt");
        String order_date = request.getParameter("order_date");
        String order_st = request.getParameter("order_st");
        String order_pay_code = request.getParameter("order_pay_code");
        String order_pay_error = request.getParameter("order_pay_error");
        String fy_ssn = request.getParameter("fy_ssn");
        String resv1 = request.getParameter("resv1");
        String md5 = request.getParameter("md5");
        String signDataStr = this.mchnt_cd + "|" +
                order_id + "|" + order_date + "|" + order_amt + "|" + order_st + "|" + order_pay_code + "|" +
                order_pay_error + "|" + resv1 + "|" +
                fy_ssn + "|" + this.mchnt_key;
        LogUtil.info("=======================signDataStr===================" + signDataStr);
        if (!MD5Util.MD5(signDataStr).equals(md5)) {
            throw new BusinessException("验签未通过");
        }
        if (!"0000".equals(order_pay_code)) {
            throw new BusinessException(order_pay_error);
        } else {
            return true;
        }
    }

    @Override
    public JSONObject createPcAuth(double money, String orderNo, String bankCardNo, String userId, String cardNo, String realName) {
        JSONObject args = new JSONObject();
        try {
            String order_id = orderNo;
            String order_amt = StringUtil.moneyToCents(money);
            String user_id = userId;
            String card_no = bankCardNo;
            String cert_type = "0";
            String cert_no = cardNo;
            String user_type = "1";
            String page_notify_url = this.webUrl + "/node/fuioupay/gateWay/rechargeCallBack";//this.webUrl + "/account/result/" + orderNo;
            String back_notify_url = this.webUrl + "/call/fyGateWay";
            String cardholder_name = realName;
            String pri_key = this.private_key;
            String signDataStr = this.mchnt_cd + "|" + user_id + "|" + order_id + "|" + order_amt + "|" +
                    card_no + "|" + cardholder_name + "|" + cert_type + "|" + cert_no + "|" +
                    page_notify_url + "|" + back_notify_url;
            byte[] bytesKey = (new BASE64Decoder()).decodeBuffer(pri_key.trim());
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(bytesKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
            Signature signature = Signature.getInstance("MD5WithRSA");
            signature.initSign(priKey);
            signature.update(signDataStr.getBytes("GBK"));
            String RSA = (new BASE64Encoder()).encodeBuffer(signature.sign());
            args.put("RSA", RSA);
            args.put("mchnt_cd", mchnt_cd);
            args.put("order_id", order_id);
            args.put("order_amt", order_amt);
            args.put("user_type", user_type);
            args.put("card_no", card_no);
            args.put("page_notify_url", page_notify_url);
            args.put("back_notify_url", back_notify_url);
            args.put("cert_type", cert_type);
            args.put("cert_no", cert_no);
            args.put("cardholder_name", cardholder_name);
            args.put("user_id", user_id);
            args.put("url", this.pcUrl);
        } catch (Exception e) {
            throw new BusinessException("接口请求错误");
        }
        return args;
    }

    @Override
    public String convertBank(String bankCode) {
        JSONObject json = new JSONObject();
        json.put("ICBC", "0801020000");
        json.put("CCB", "0801050000");
        json.put("CMBC", "0803050000");
        json.put("PSBC", "0801000000");
        json.put("CEB", "0803030000");
        json.put("HXB", "0803040000");
        json.put("CMB", "0803080000");
        json.put("BOC", "0801040000");
        json.put("BCM", "0803010000");
        json.put("SPDB", "0803100000");
        json.put("CIB", "0803090000");
        json.put("CITIC", "0803020000");
        json.put("BOB", "0804031000");
        json.put("CGB", "0803060000");
        json.put("PAB", "0804105840");
        json.put("ABC", "0801030000");
        json.put("fuioupay", "0000000000");
        return json.getString(bankCode);
    }

}
