package com.zc.mall.core.credit.model.zmxy;

/**
 * 芝麻信用接口
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月24日
 */
public interface ZmxyService {

    /**
     * 授权
     */
    void zhimaAuthInfoAuthorize();

    /**
     * 获取芝麻信用分
     */
    void zhimaCreditScoreGet();
}
