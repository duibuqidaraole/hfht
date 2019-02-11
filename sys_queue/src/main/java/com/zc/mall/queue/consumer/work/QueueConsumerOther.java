package com.zc.mall.queue.consumer.work;

import com.zc.mall.core.account.model.AccountDeductModel;
import com.zc.mall.core.account.model.BankCardModel;
import com.zc.mall.core.account.model.RechargeModel;
import com.zc.mall.core.account.model.WithdrawCashModel;
import com.zc.mall.core.common.model.CommonModel;
import com.zc.mall.core.common.queue.pojo.QueueModel;
import com.zc.mall.core.common.queue.work.QueueAbstract;
import com.zc.mall.core.manage.entity.OrderTask;
import com.zc.mall.core.user.model.UserIdentifyModel;
import com.zc.mall.core.user.model.UserInfoModel;
import com.zc.mall.core.user.model.UserModel;
import com.zc.mall.mall.model.OrderPayHistoryModel;
import com.zc.mall.promotion.model.BonusCouponsRecordModel;
import com.zc.mall.promotion.model.UserVipCouponsModel;

/**
 * 综合
 * @author zp
 *
 */
public class QueueConsumerOther extends QueueAbstract {

	@Override
	public void receive(QueueModel model, OrderTask orderTask) {
		//认证
		if(model.getObj() instanceof UserIdentifyModel){
			UserIdentifyModel uiModel = (UserIdentifyModel)model.getObj();
			uiModel.setOrderTask(orderTask);
			uiModel.doQueue();
			model.setDelState(true);
			return;
		}
		//认证
		if(model.getObj() instanceof CommonModel){
			CommonModel cModel = (CommonModel)model.getObj();
			cModel.setOrderTask(orderTask);
			cModel.doQueue();
			model.setDelState(true);
			return;
		}
		// 修改用户信息
		if(model.getObj() instanceof UserInfoModel){
			UserInfoModel uiModel = (UserInfoModel)model.getObj();
			uiModel.setOrderTask(orderTask);
			uiModel.doQueue();
			model.setDelState(true);
			return;
		}
		//修改手机号
		if(model.getObj() instanceof UserModel){
			UserModel userModel = (UserModel)model.getObj();
			userModel.setOrderTask(orderTask);
			userModel.doQueue();
			model.setDelState(true);
			return;
		}
		//绑卡
		if(model.getObj() instanceof BankCardModel){
			BankCardModel bcModel = (BankCardModel)model.getObj();
			bcModel.setOrderTask(orderTask);
			bcModel.doQueue();
			model.setDelState(true);
			return;
		}
		//充值
		if(model.getObj() instanceof RechargeModel){
			RechargeModel rModel = (RechargeModel)model.getObj();
			rModel.setOrderTask(orderTask);
			rModel.doQueue();
			model.setDelState(true);
			return;
		}
		//提现
		if(model.getObj() instanceof WithdrawCashModel){
			WithdrawCashModel rModel = (WithdrawCashModel)model.getObj();
			rModel.setOrderTask(orderTask);
			rModel.doQueue();
			model.setDelState(true);
			return;
		}
		//线下资金变更
		if(model.getObj() instanceof AccountDeductModel){
			AccountDeductModel dModel = (AccountDeductModel)model.getObj();
			dModel.setOrderTask(orderTask);
			dModel.doQueue();
			model.setDelState(true);
			return;
		}

		//推广活动处理
		if(model.getObj() instanceof BonusCouponsRecordModel){
			BonusCouponsRecordModel bonusCouponsRecordModel = ((BonusCouponsRecordModel)model.getObj());
			bonusCouponsRecordModel.setOrderTask(orderTask);
			bonusCouponsRecordModel.doQueue();
			model.setDelState(true);
			return;
		}

		//vip发放处理
		if(model.getObj() instanceof UserVipCouponsModel){
			UserVipCouponsModel userVipCouponsModel = ((UserVipCouponsModel)model.getObj());
			userVipCouponsModel.setOrderTask(orderTask);
			userVipCouponsModel.doQueue();
			model.setDelState(true);
			return;
		}
		// 订单支付记录
		if(model.getObj() instanceof OrderPayHistoryModel){
		    OrderPayHistoryModel orderPayHistoryModel = ((OrderPayHistoryModel)model.getObj());
		    orderPayHistoryModel.setOrderTask(orderTask);
		    orderPayHistoryModel.doQueue();
		    model.setDelState(true);
		    return;
		}
	}

}
