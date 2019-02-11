package com.zc.mall.core.common.protocol.sign;

import com.zc.mall.core.user.model.UserModel;

/**
 * 协议签名接口
 *
 * @author zp
 */
public interface ProtocolSign {

    /**
     * 执行
     */
    String executer(UserModel tenderUserModel, UserModel borrowUserModel, long tenderId, String path, String name);

    /**
     * 下载
     */
    void downloadContract(String contractId);
}
