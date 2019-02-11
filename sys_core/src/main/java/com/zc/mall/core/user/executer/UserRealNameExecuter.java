package com.zc.mall.core.user.executer;

import com.zc.mall.core.common.executer.BaseExecuter;
import com.zc.mall.core.common.queue.service.QueueProducerService;
import com.zc.mall.core.manage.dao.OrderTaskDao;
import com.zc.mall.core.user.model.UserIdentifyModel;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 用户实名成功任务
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月13日
 */
@Component
public class UserRealNameExecuter extends BaseExecuter {

    @Resource
    private QueueProducerService queueProducerService;
    @Resource
    private OrderTaskDao orderTaskDao;
    private UserIdentifyModel model;

    @Override
    public void init() {
        if (!(this.obj instanceof UserIdentifyModel)) {
            throw new BusinessException("实例不存在");
        }
        this.model = (UserIdentifyModel) this.obj;
        this.user = model.getUser();
    }

    @Override
    public void sendMessage() {
//		try {
//			NoticeMessageModel message = new NoticeMessageModel();
//		} catch (Exception e) {
//			LogUtil.info("Message消息发送失败" + e.getMessage());
//		}
    }

    @Override
    public void sendSMS() {
//		try {
//			OrderTask orderTask = new OrderTask(model.getUser(), "sendSMS", StringUtil.getSerialNumber(), 2, "", DateUtil.getNow());
//			orderTaskDao.save(orderTask);
//			OrderTaskModel orderTaskModel =OrderTaskModel.instance(orderTask);
//			NoticeMessageModel sms = new NoticeMessageModel(2, null, model.getUser(), null, orderTask.getOrderNo(), orderTaskModel);
////			queueProducerService.send(new QueueModel("sendSMS",orderTaskModel, sms));
//		} catch (Exception e) {
//			LogUtil.info("SMS消息发送失败" + e.getMessage());
//		}
    }

    @Override
    public void handleInterface() {
		/*FuiouPayReg pay = FuiouPayUtil.reg(StringUtil.getSerialNumber(), 
						 this.user.getRealName(), 
						 "0", 
						 this.user.getCardNo(), 
						 this.user.getMobile(), 
						 this.user.getEmail(), 
						 "6875", 
						 "0103", 
						 "中国农业银行", 
						 this.user.getRealName(), 
						 "6228480311111111226", 
						 "97b149a269795ef98a7e31b66d1f105e", 
						 "97b149a269795ef98a7e31b66d1f105e", 
						 "");
		
		int state = BaseConstant.BUSINESS_STATE_NO;
		String remark = "实名成功";
		if(pay.getResp_code().equals(FuiouPay.resp_code_0000)){
			state = BaseConstant.BUSINESS_STATE_YES;
			remark = pay.getResp_desc();
		}
		model.setState(state);
		model.setRemark(remark);*/
    }
}
