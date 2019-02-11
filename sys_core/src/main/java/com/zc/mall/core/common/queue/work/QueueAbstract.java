package com.zc.mall.core.common.queue.work;

import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.common.constant.BaseConstant.QueueCode;
import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.common.queue.pojo.QueueModel;
import com.zc.mall.core.common.queue.service.QueueProducerService;
import com.zc.mall.core.manage.entity.OrderTask;
import com.zc.mall.core.manage.model.OrderTaskModel;
import com.zc.mall.core.manage.service.OrderTaskService;
import com.zc.mall.core.user.entity.User;
import com.zc.sys.common.util.log.LogUtil;

/**
 * 队列消息处理
 *
 * @author zp
 */
public abstract class QueueAbstract {

    private static QueueProducerService queueProducerService;
    private static OrderTaskService orderTaskService;

    /**
     * 发送消息
     *
     * @param orderNo  请求订单
     * @param orderNid 订单标识
     * @param code     队列标识
     * @param obj      实体
     * @param user     操作用户
     */
    public static void send(String orderNo, String orderNid, String code, Object obj, User user) {
        queueProducerService = BeanUtil.getBean(QueueProducerService.class);
        orderTaskService = BeanUtil.getBean(OrderTaskService.class);
        OrderTask orderTask = orderTaskService.add(user, orderNid, orderNo);
        QueueModel model = new QueueModel(code, OrderTaskModel.instance(orderTask), obj, QueueCode.getName(code));
        queueProducerService.send(model);
    }

    /**
     * 接收消息
     *
     * @param model
     */
    public void receive(QueueModel model) {
        orderTaskService = BeanUtil.getBean(OrderTaskService.class);
        OrderTask orderTask = (OrderTask) orderTaskService.findByOrderNo(model.getOrderTaskModel().getOrderNo());
        if (orderTask == null || orderTask.getState() != BaseConstant.BUSINESS_STATE_WAIT) {
            LogUtil.info("订单号+" + orderTask.getOrderNo() + "不存在，或者处理状态有误");
            return;
        }
        receive(model, orderTask);
        if (!model.getDelState()) {
            LogUtil.info("订单号+" + orderTask.getOrderNo() + "无队列处理配置项..........");
        }
    }

    /**
     * 接收消息
     *
     * @param model
     * @param orderTask
     */
    public abstract void receive(QueueModel model, OrderTask orderTask);

}
