package com.zc.mall.core.common.protocol;


/**
 * 协议接口
 *
 * @author zp
 */
public interface ProtocolBean {

    /**
     * 初始化模板
     *
     * @return
     */
    void initTemplate();

    /**
     * 执行
     */
    void executer(String nid, Object model);

    /**
     * 初始化参数
     */
    void initData();

    /**
     * 协议名称
     */
    void initName();

    /**
     * 创建模板
     */
    void buildProtocol();

    /**
     * 签章签名
     */
    void sign();

    /**
     * 协议生成之后操作
     */
    void afterHandle();

    /**
     * 下载
     */
    void downloadContract(String contractId);
}
