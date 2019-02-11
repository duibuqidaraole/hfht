package com.zc.mall.core.common.pay.wx;

import com.zc.mall.core.common.global.Global;
import com.zc.mall.core.sys.constant.ConfigParamConstant;

import java.io.InputStream;

public class MyWXPayConfig extends WXPayConfig {

    @Override
    String getAppID() {
//        return "wx09bf2f5eab115b6b";
        return Global.getValue(ConfigParamConstant.SYS_PARAM_WX_APP_ID);
    }

    @Override
    String getMchID() {
//        return "1517403931";
        return Global.getValue(ConfigParamConstant.SYS_PARAM_WX_MCH_ID);
    }

    @Override
    String getKey() {
//        return "JBIHI7J7KRWCGTRRKEQSGJDHJU2XIQ2R";
        return Global.getValue(ConfigParamConstant.SYS_PARAM_WX_KEY);
    }

    @Override
    InputStream getCertStream() {
        return null;
    }

    @Override
    IWXPayDomain getWXPayDomain() {
        return new IWXPayDomain() {

            @Override
            public void report(String domain, long elapsedTimeMillis, Exception ex) {

            }

            @Override
            public DomainInfo getDomain(WXPayConfig config) {
//                return new DomainInfo("api.mch.weixin.qq.com", true);
                return new DomainInfo(Global.getValue(ConfigParamConstant.SYS_PARAM_WX_API), true);
            }
        };
    }

}
