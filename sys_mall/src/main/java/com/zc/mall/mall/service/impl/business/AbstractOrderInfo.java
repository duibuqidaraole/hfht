package com.zc.mall.mall.service.impl.business;

import com.zc.mall.mall.model.OrderInfoModel;

/**
 * 订单业务处理
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月19日
 */
public abstract class AbstractOrderInfo {

    protected OrderInfoModel model;

    public void executer(OrderInfoModel model) {
        this.model = model;
        this.initGlobalVariable();
        this.check();
        this.init();
        this.persist();
        this.log();
        this.handOther();
    }

    /**
     * 全局变量初始化
     * 注：spring注解对象都是单例类
     */
    public abstract void initGlobalVariable();

    /**
     * 校验
     */
    public abstract void check();

    /**
     * 初始化
     */
    public abstract void init();

    /**
     * 持久化
     */
    public abstract void persist();

    /**
     * 日志
     */
    public abstract void log();

    /**
     * 其他操作处理
     */
    public abstract void handOther();

    /**
     * 获取【model】
     **/
    public OrderInfoModel getModel() {
        return model;
    }

    /**
     * 设置【model】
     **/
    public void setModel(OrderInfoModel model) {
        this.model = model;
    }

}
