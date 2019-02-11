package com.zc.mall.queue.consumer.work;

import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.common.queue.pojo.QueueModel;
import com.zc.mall.core.common.queue.work.QueueAbstract;
import com.zc.mall.core.manage.entity.OrderTask;
import com.zc.mall.core.manage.model.NoticeMessageModel;
import com.zc.mall.core.manage.model.OrderTaskModel;
import com.zc.mall.core.manage.service.NoticeMessageService;

/**
 * 消息
 * @author zp
 *
 */
public class QueueConsumerMessage extends QueueAbstract {

	@Override
	public void receive(QueueModel model, OrderTask orderTask) {
		//消息处理
		if(model.getObj() instanceof NoticeMessageModel){
			NoticeMessageService noticeMessageService = BeanUtil.getBean(NoticeMessageService.class);
			NoticeMessageModel nModel = (NoticeMessageModel)model.getObj();
			nModel.setOrderTaskModel(OrderTaskModel.instance(orderTask));
			noticeMessageService.send(nModel);
			model.setDelState(true);
			return;
		}
	}

}
