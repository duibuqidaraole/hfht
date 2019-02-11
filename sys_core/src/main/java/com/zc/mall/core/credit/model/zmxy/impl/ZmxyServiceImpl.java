package com.zc.mall.core.credit.model.zmxy.impl;

import com.antgroup.zmxy.openplatform.api.DefaultZhimaClient;
import com.antgroup.zmxy.openplatform.api.ZhimaApiException;
import com.antgroup.zmxy.openplatform.api.request.ZhimaAuthInfoAuthorizeRequest;
import com.antgroup.zmxy.openplatform.api.request.ZhimaCreditScoreGetRequest;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCreditScoreGetResponse;
import com.zc.mall.core.credit.model.zmxy.ZmxyService;

/**
 * 芝麻信用接口
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月24日
 */
public class ZmxyServiceImpl implements ZmxyService {

    // 芝麻开放平台地址
    private String gatewayUrl = "https://zmopenapi.zmxy.com.cn/openapi.do";
    // 商户应用 Id
    private String appId = "***";
    // 商户 RSA 私钥
    private String privateKey = "***";
    // 芝麻 RSA 公钥
    private String zhimaPublicKey = "***";

    /**
     * 授权
     */
    @Override
    public void zhimaAuthInfoAuthorize() {
        ZhimaAuthInfoAuthorizeRequest req = new ZhimaAuthInfoAuthorizeRequest();
        req.setChannel("apppc");
        req.setPlatform("zmop");
        req.setIdentityType("2");// 必要参数
        req.setIdentityParam("{\"name\":\"张三\",\"certType\":\"IDENTITY_CARD\",\"certNo\":\"330100xxxxxxxxxxxx\"}");// 必要参数
        req.setBizParams("{\"auth_code\":\"M_H5\",\"channelType\":\"app\",\"state\":\"商户自定义\"}");//
        DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, appId,
                privateKey, zhimaPublicKey);
        try {
            String url = client.generatePageRedirectInvokeUrl(req);
            System.out.println(url);
        } catch (ZhimaApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取芝麻信用分
     */
    @Override
    public void zhimaCreditScoreGet() {
        ZhimaCreditScoreGetRequest req = new ZhimaCreditScoreGetRequest();
        req.setChannel("apppc");
        req.setPlatform("zmop");
        req.setTransactionId("201512100936588040000000465158");// 必要参数
        req.setProductCode("w1010100100000000001");// 必要参数
        req.setOpenId("268810000007909449496");// 必要参数
        DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, appId,
                privateKey, zhimaPublicKey);
        try {
            ZhimaCreditScoreGetResponse response = (ZhimaCreditScoreGetResponse) client
                    .execute(req);
            System.out.println(response.isSuccess());
            System.out.println(response.getErrorCode());
            System.out.println(response.getErrorMessage());
        } catch (ZhimaApiException e) {
            e.printStackTrace();
        }
    }

}
