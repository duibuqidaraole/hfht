package com.zc.mall.core.credit.model.zmxy;

import com.antgroup.zmxy.openplatform.api.DefaultZhimaClient;
import com.antgroup.zmxy.openplatform.api.ZhimaApiException;
import com.antgroup.zmxy.openplatform.api.request.ZhimaAuthInfoAuthorizeRequest;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.util.log.LogUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 芝麻信用授权
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月24日
 */
public class ZhimaAuthInfoAuthorize {
    //芝麻开放平台地址
    private String gatewayUrl = "https://zmopenapi.zmxy.com.cn/openapi.do";
    //商户应用 Id
    private String appId = "***";
    //商户 RSA 私钥
    private String privateKey = "***";
    //芝麻 RSA 公钥
    private String zhimaPublicKey = "***";

    public void testZhimaAuthInfoAuthorize() {
        ZhimaAuthInfoAuthorizeRequest req = new ZhimaAuthInfoAuthorizeRequest();
        req.setChannel("apppc");
        req.setPlatform("zmop");
        req.setIdentityType("2");// 必要参数 
        req.setIdentityParam("{\"name\":\"张三\",\"certType\":\"IDENTITY_CARD\",\"certNo\":\"330100xxxxxxxxxxxx\"}");// 必要参数 
        req.setBizParams("{\"auth_code\":\"M_H5\",\"channelType\":\"app\",\"state\":\"商户自定义\"}");// 
        DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, appId, privateKey, zhimaPublicKey);
        try {
            String url = client.generatePageRedirectInvokeUrl(req);
            System.out.println(url);
        } catch (ZhimaApiException e) {
            e.printStackTrace();
        }
    }

    public void getResult() {
        String params = "Srnlsy8O0SRZ1wYkFBQQPwLh66o0tscIsL7tAEQz%2Fk%2BNW7QeMSKzDFNmh2B%2Fbaq6GnBEXkQTj4PZqLYBQjBvlCRkgRkSx4g%2FP4lJ6JW2UjAQWTN0djSi28NDyQQSP772YZiLtcw%2Fp%2FHmDb2Uhr24Jp2gPxfdzV9gGDl0uY1gNmYxqfG9tsqlNhnL%2FqTZU4KB%2FHt%2Fz3vaogxAZzo3YiB%2Fj47t97%2FUkoNzk2bWvtV12d275dwgYg31MVMKOUc%2BG%2ByRQsZMsDBKoZfhqFoADnuD0i0a%2FguFZcppxxZKCVIpveT29JtkHFXfYC8F2jZaRH5zXQa1odi%2BRv%2Bt%2BF08O1NrnA%3D%3D";
        //从回调URL中获取params参数，此处为示例值
        String sign = "BvAYHmZ29xkuIJoNPomGJCUWUDuSufHOPTTlXpzYtY4e3i9mL6QpwPUDuK0d0F9yPxaHBAVq8YEveNoBPnsryFPE1rL3810a5pcHb8WZb02RVqCbc5j5La3SBdHY1fZRUlQYKPY0KwkipkTZmOXZAHIPU3hgynt1m%2BsAscxonIk%3D";
        //从回调URL中获取sign参数，此处为示例值
        //判断串中是否有%，有则需要decode
        if (params.indexOf("%") != -1) {
            try {
                params = URLDecoder.decode(params, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                LogUtil.info(e.getMessage());
                throw new BusinessException("系统异常");
            }
        }
        if (sign.indexOf("%") != -1) {
            try {
                sign = URLDecoder.decode(sign, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                LogUtil.info(e.getMessage());
                throw new BusinessException("系统异常");
            }
        }
        DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, appId, privateKey, zhimaPublicKey);
        try {
            String result = client.decryptAndVerifySign(params, sign);
            System.out.println(result);
        } catch (ZhimaApiException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ZhimaAuthInfoAuthorize result = new ZhimaAuthInfoAuthorize();
        result.testZhimaAuthInfoAuthorize();
    }
}
