package com.zc.mall.promotion.executer;

import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.common.executer.BaseExecuter;
import com.zc.mall.promotion.model.VipCouponsModel;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.stereotype.Component;

/**
 * vip推广活动任务
 *
 * @author zy
 * @version 2.0.0.0
 * @since 2018年11月13日
 */
@Component
public class VipCouponsExecuter extends BaseExecuter {
    private VipCouponsModel model;

    @Override
    public void init() {
        super.init();
        if (!(this.obj instanceof VipCouponsModel)) {
            throw new BusinessException("实例不存在");
        }
        this.model = (VipCouponsModel) this.obj;
        this.account = accountService.findByUser(this.user.getId());
    }


    @Override
    public void handleAccount() {
		/*if (account.getBalance()<model.getPrince()){

		}
		if (model.getState()==BaseConstant.BUSINESS_STATE_YES){
			this.money=0;
		}
		*/
        model.setState(BaseConstant.BUSINESS_STATE_YES);
        model.setUseQuota(model.getUseQuota() - 1);
		/*accountService.updateAccount(
				new AccountModel(account.getId(), 0, 0-this.money,0));*/
    }
}
