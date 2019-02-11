package com.zc.mall.core.common.queue.pojo;

import com.zc.mall.core.manage.model.OrderTaskModel;

import java.io.Serializable;

public class QueueModel implements Serializable {
    /**
     * 序列化
     **/
    private static final long serialVersionUID = 1L;

    /**
     * 监听代码
     **/
    private String code;

    /**
     * 订单信息
     **/
    private OrderTaskModel orderTaskModel;

    /**
     * 处理实体
     **/
    private Object obj;

    /**
     * 处理类路径
     **/
    private String name;

    /**
     * 是否处理
     **/
    private boolean delState;

    public QueueModel() {
        super();
    }

    public QueueModel(String code, OrderTaskModel orderTaskModel, Object obj, String name) {
        super();
        this.code = code;
        this.orderTaskModel = orderTaskModel;
        this.obj = obj;
        this.name = name;
    }


    /**
     * 获取【处理实体】
     **/
    public Object getObj() {
        return obj;
    }

    /**
     * 设置【处理实体】
     **/
    public void setObj(Object obj) {
        this.obj = obj;
    }

    /**
     * 获取【订单信息】
     **/
    public OrderTaskModel getOrderTaskModel() {
        return orderTaskModel;
    }

    /**
     * 设置【订单信息】
     **/
    public void setOrderTaskModel(OrderTaskModel orderTaskModel) {
        this.orderTaskModel = orderTaskModel;
    }

    /**
     * 获取【监听代码】
     **/
    public String getCode() {
        return code;
    }

    /**
     * 设置【监听代码】
     **/
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取【处理类路径】
     **/
    public String getName() {
        return name;
    }

    /**
     * 设置【处理类路径】
     **/
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取【是否处理】
     **/
    public boolean getDelState() {
        return delState;
    }

    /**
     * 设置【是否处理】
     **/
    public void setDelState(boolean delState) {
        this.delState = delState;
    }


}
