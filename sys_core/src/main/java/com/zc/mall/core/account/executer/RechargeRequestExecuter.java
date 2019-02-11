package com.zc.mall.core.account.executer;

import com.zc.mall.core.account.constant.AccountConstant;
import com.zc.mall.core.account.model.RechargeModel;
import com.zc.mall.core.common.executer.BaseExecuter;
import com.zc.mall.core.common.pay.recharge.AbstractRecharge;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Component;

/**
 * 充值请求任务
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月14日
 */
@Component
public class RechargeRequestExecuter extends BaseExecuter {

    private RechargeModel model;

    @Override
    public void init() {
        if (!(this.obj instanceof RechargeModel)) {
            throw new BusinessException("实例不存在");
        }
        this.model = (RechargeModel) this.obj;
        this.user = model.getUser();
        this.money = model.getAmount();
    }

    @Override
    public void handleInterface() {
//		Object pay = ReflectUtil.invokeMethod(FuiouPayMethod.WEBQUICKRECHARGE.getClassName(), FuiouPayMethod.WEBQUICKRECHARGE.getMethodName(), 
//											  model.getOrderNo(),
//											  user.getMobile(),
//											  StringUtil.moneyToCents(money));
        Object pay = null;
        switch (model.getWay()) {
            case AccountConstant.ACCOUNT_RECHARGE_WAY_ONLINE_BANK:// 网银
                pay = AbstractRecharge.getRechargeWay().createGateWay(money, model.getBankCode(), model.getOrderNo());
                break;
            case AccountConstant.ACCOUNT_RECHARGE_WAY_PC_QUICK:// pc快捷
                pay = AbstractRecharge.getRechargeWay().createPcAuth(money, model.getOrderNo(), model.getBankCardNo(),
                        StringUtil.isNull(this.user.getId()), this.user.getCardNo(), this.user.getRealName());
                break;

            default:
                break;

        }
        model.setPayRecharge(pay);
    }

}
