package com.zc.mall.core.account.executer;

import com.zc.mall.core.account.model.BankCardModel;
import com.zc.mall.core.common.executer.BaseExecuter;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.stereotype.Component;

/**
 * 用户换绑银行卡任务
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月14日
 */
@Component
public class ChangeBankCardRequestExecuter extends BaseExecuter {

    private BankCardModel model;

    @Override
    public void init() {
        if (!(this.obj instanceof BankCardModel)) {
            throw new BusinessException("实例不存在");
        }
        this.model = (BankCardModel) this.obj;
        this.user = model.getUser();
    }

    @Override
    public void handleInterface() {
//		Object pay = ReflectUtil.invokeMethod(FuiouPayMethod.WEBCHANGECARD.getClassName(), FuiouPayMethod.WEBCHANGECARD.getMethodName(), 
//											  user.getMobile());
//		model.setPayObj(pay);
    }

}
