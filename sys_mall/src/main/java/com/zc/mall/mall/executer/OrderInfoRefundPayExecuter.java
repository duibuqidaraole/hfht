package com.zc.mall.mall.executer;

import com.zc.mall.core.account.entity.AccountLog;
import com.zc.mall.core.account.model.AccountLogModel;
import com.zc.mall.core.account.model.AccountModel;
import com.zc.mall.core.common.constant.BaseConstant.OrderNid;
import com.zc.mall.core.common.executer.BaseExecuter;
import com.zc.mall.core.common.global.Global;
import com.zc.mall.mall.model.OrderInfoModel;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.stereotype.Component;

/**
 * 订单退款支付
 *
 * @author zp
 * @version 1.0
 * @since 2018年12月10日
 */
@Component
public class OrderInfoRefundPayExecuter extends BaseExecuter {

    private OrderInfoModel model;
    // 余额支付
    private double balancePay;

    @Override
    public void init() {
        super.init();
        if (!(this.obj instanceof OrderInfoModel)) {
            throw new BusinessException("实例不存在");
        }
        this.model = (OrderInfoModel) this.obj;
        this.balancePay = this.money;
        this.account = model.getUser().getAccount();
        Global.setTransfer("model", model);
    }

    @Override
    public void addAccountLog() {
        AccountLog log = new AccountLogModel().instanceAccountLog(account, user, OrderNid.ORDER_NID_ORDER_INFO_REFUND_PAY.getNid(), null, balancePay, model.getNo(), null);
        accountLogDao.save(log);
    }

    @Override
    public void handleAccount() {
        accountService.updateAccount(
                new AccountModel(account.getId(), balancePay, balancePay, 0));
    }

}
