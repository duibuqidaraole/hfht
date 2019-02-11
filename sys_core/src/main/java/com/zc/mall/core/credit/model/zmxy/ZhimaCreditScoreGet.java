package com.zc.mall.core.credit.model.zmxy;

import com.antgroup.zmxy.openplatform.api.DefaultZhimaClient;
import com.antgroup.zmxy.openplatform.api.ZhimaApiException;
import com.antgroup.zmxy.openplatform.api.request.ZhimaCreditScoreGetRequest;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCreditScoreGetResponse;

/**
 * 获取芝麻信用分
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月24日
 */
public class ZhimaCreditScoreGet {
    //芝麻开放平台地址
    private String gatewayUrl = "https://zmopenapi.zmxy.com.cn/openapi.do";
    //商户应用 Id
    private String appId = "***";
    //商户 RSA 私钥
    private String privateKey = "***";
    //芝麻 RSA 公钥
    private String zhimaPublicKey = "***";

    public void testZhimaCreditScoreGet() {
        ZhimaCreditScoreGetRequest req = new ZhimaCreditScoreGetRequest();
        req.setChannel("apppc");
        req.setPlatform("zmop");
        req.setTransactionId("201512100936588040000000465158");// 必要参数 
        req.setProductCode("w1010100100000000001");// 必要参数 
        req.setOpenId("268810000007909449496");// 必要参数 
        DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, appId, privateKey, zhimaPublicKey);
        try {
            ZhimaCreditScoreGetResponse response = (ZhimaCreditScoreGetResponse) client.execute(req);
            System.out.println(response.isSuccess());
            System.out.println(response.getErrorCode());
            System.out.println(response.getErrorMessage());
        } catch (ZhimaApiException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ZhimaCreditScoreGet result = new ZhimaCreditScoreGet();
        result.testZhimaCreditScoreGet();
    }
}
