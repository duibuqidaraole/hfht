package com.zc.mall.queue.listener;

import com.zc.mall.core.common.queue.pojo.QueueModel;
import com.zc.mall.core.common.queue.work.QueueAbstract;
import com.zc.sys.common.util.log.LogUtil;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * 处理队列消息监听
 * P2P项目队列处理
 * @author zp
 * @version 2.0.0.0
 * @since 2018年01月09日
 */
public class P2PConsumerMessageListener implements MessageListener{
    @Override
	public void onMessage(Message message) {
        try {
        	ObjectMessage objectMessage = (ObjectMessage) message;
        	QueueModel model = (QueueModel)objectMessage.getObject();
            LogUtil.info("===============【P2P项目监听队列处理】接收消息：" + model.getOrderTaskModel().toString());
            QueueAbstract queue = (QueueAbstract) Class.forName(model.getName()).newInstance();
            queue.receive(model);
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (Exception e){
        	LogUtil.info(e.getMessage());
        	e.printStackTrace();
        }
    }
	
}
