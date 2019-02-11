package com.zc.mall.core.common.sms.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zc.mall.core.common.global.Global;
import com.zc.mall.core.common.sms.SmsService;
import com.zc.sys.common.util.encrypt.MD5Util;
import com.zc.sys.common.util.log.LogUtil;
import com.zc.sys.common.util.validate.StringUtil;

/**
 * 发财鱼短信发送
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月13日
 */
@Service("faCaiYuSmsService")
public class FaCaiYuSmsServiceImpl implements SmsService {

    /** 用户名 **/
//    public static final String FACAIYU_SMS_USER_NAME = "hfmy";
    /** 密码 **/
//    public static final String FACAIYU_SMS_PASSWORD = "8CwE0kBt";
    /** key **/
//    public static final String FACAIYU_SMS_KEY = "vvPWZBqzbgjAOye6";
    /** key **/
//    public static final String FACAIYU_SMS_SIGN_INFO = "【弘梵】";
    
    /** 用户名 **/
    public static final String FACAIYU_SMS_USER_NAME = "facaiyu_sms_user_name";
    /** 密码 **/
    public static final String FACAIYU_SMS_PASSWORD = "facaiyu_sms_password";
    /** key **/
    public static final String FACAIYU_SMS_KEY = "facaiyu_sms_key";
    /** key **/
    public static final String FACAIYU_SMS_SIGN_INFO = "facaiyu_sms_sign_info";


    @Override
    public JSONObject sendSms(String mobile, String content) throws Exception {
        if (StringUtil.isBlank(mobile) || StringUtil.isBlank(content)) {
            LogUtil.info("短信发送失败，信息不能为空");
            return null;
        }
        String userName = Global.getValue(FACAIYU_SMS_USER_NAME);
        String password = Global.getValue(FACAIYU_SMS_PASSWORD);
        String key = Global.getValue(FACAIYU_SMS_KEY);
        String signInfo = Global.getValue(FACAIYU_SMS_SIGN_INFO);
        
        
        String tradeNo = StringUtil.getSerialNumber();
        
        JSONObject signParam = new JSONObject();
        signParam.put("tradeNo", tradeNo);
        signParam.put("userName", userName);
        signParam.put("userPassword", password);
        signParam.put("phones", mobile);
        signParam.put("content", content);
        signParam.put("etnumber", "");
        
        String sign = encrypt(signParam.toJSONString(), key);
        
        signParam.put("sign", sign);
        signParam.put("userPassword", MD5Util.md5(FACAIYU_SMS_PASSWORD));
        String resultStr = sendPost("http://120.26.125.243:8585/sms/openCard", signParam.toJSONString());
        LogUtil.info("发财鱼短信发送结果：" + resultStr);
        JSONObject json = JSONObject.parseObject(resultStr);
        String retCode = json.getString("status");
        String result = "";
        String resultCode = "";
        if (retCode.equals("1")) {
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
    
    
    /**
     * 加密 　　
     * 
     * @param content
     *            需要加密的内容 　　
     * @param password
     *            加密密码 　　
     * @return 　　
     */
    private static String encrypt(String content, String password) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            int blockSize = cipher.getBlockSize();
            byte[] dataBytes = content.getBytes();
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength
                        + (blockSize - (plaintextLength % blockSize));
            }
            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

            SecretKeySpec keyspec = new SecretKeySpec(password.getBytes(),
                    "AES");
            IvParameterSpec ivspec = new IvParameterSpec(password.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);

            return parseByte2HexStr(encrypted);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    /**
     * 将二进制转换成16进制 　　
     * 
     * @param buf
     *            　　
     * @return 　　
     */

    private static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }
    
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "application/json");
            conn.setRequestProperty("Content-type", "application/json; charset=utf-8");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(1000 * 30);
            conn.setReadTimeout(1000 * 30);
            conn.setUseCaches(false);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        FaCaiYuSmsServiceImpl d = new FaCaiYuSmsServiceImpl();
        try {
            d.sendSms("", "");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
