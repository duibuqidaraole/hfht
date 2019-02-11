package com.zc.mall.core.common.pay.user;

import com.alibaba.fastjson.JSONObject;
import com.zc.mall.core.common.pay.constant.PayConstant;
import org.springframework.stereotype.Service;

/**
 * 测试实名
 *
 * @author zp
 */
@Service("testRealNameService")
public class TestRealNameService extends AbstractRealName {

    @Override
    public JSONObject realNameInvok(String realName, String cardNo, String bankCardNo, String mobile) throws Exception {
        JSONObject res = new JSONObject();
        res.put(PayConstant.REALNAME_CODE, PayConstant.REALNAME_SUCCESS_CODE);
        res.put(PayConstant.REALNAME_MSG, "SUCCESS");
        return res;
    }

}
