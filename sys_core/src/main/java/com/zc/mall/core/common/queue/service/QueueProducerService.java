package com.zc.mall.core.common.queue.service;

import com.zc.mall.core.common.queue.pojo.QueueModel;

import javax.jms.Destination;

/**
 * 队列生产者
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年7月26日
 */
public interface QueueProducerService {

    /**
     * 发送消息
     *
     * @param model
     */
    void send(QueueModel model);

    /**
     * 发送消息
     *
     * @param destination
     * @param model
     */
    void send(Destination destination, final QueueModel model);

}
