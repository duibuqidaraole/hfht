package com.zc.mall.core.common.service;

import com.zc.mall.core.common.model.CommonModel;
import com.zc.sys.common.form.Result;

/**
 * 公共接口
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月15日
 */
public interface CommonService {

    /**
     * 生成唯一请求标识
     *
     * @param model
     * @return
     */
    Result getToken();

    /**
     * 校验token
     *
     * @param token
     * @return
     */
    void checkToken(String token);

    /**
     * 发送短信验证码
     *
     * @param model
     * @return
     */
    Result getMobileCode(CommonModel model);

    /**
     * 校验短信验证码
     *
     * @param mobile
     * @param code
     * @return
     */
    void checkMobileCode(String mobile, String code, int handleSmsType);

    /**
     * 利息计算器
     *
     * @param model
     * @return
     */
    CommonModel calculator(CommonModel model);

    /**
     * 协议下载
     *
     * @param model
     * @return
     */
    Result downloadProtocol(CommonModel model);

    /**
     * 生成协议
     *
     * @param model
     * @return
     */
    Result createProtocol(CommonModel model);

}
