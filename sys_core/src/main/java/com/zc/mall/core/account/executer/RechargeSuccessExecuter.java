package com.zc.mall.core.account.executer;

import com.zc.mall.core.account.constant.AccountConstant.AccountStatisticsType;
import com.zc.mall.core.account.entity.AccountLog;
import com.zc.mall.core.account.model.AccountLogModel;
import com.zc.mall.core.account.model.AccountModel;
import com.zc.mall.core.account.model.RechargeModel;
import com.zc.mall.core.account.service.work.AccountWork;
import com.zc.mall.core.common.constant.BaseConstant.OrderNid;
import com.zc.mall.core.common.executer.BaseExecuter;
import com.zc.mall.core.integral.service.work.IntegralWork;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Component;

/**
 * 银行卡充值成功处理任务
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月14日
 */
@Component
public class RechargeSuccessExecuter extends BaseExecuter {
    private RechargeModel model;

    @Override
    public void init() {
        super.init();
        if (!(this.obj instanceof RechargeModel)) {
            throw new BusinessException("实例不存在");
        }
        this.model = (RechargeModel) this.obj;
        this.user = model.getUser();
        this.money = model.getAmount();
        this.account = user.getAccount();
    }

    @Override
    public void addAccountLog() {
        AccountLog log = new AccountLogModel().instanceAccountLog(account, user, OrderNid.ORDER_NID_ACCOUNT_RECHARGE.getNid(), null, money, model.getOrderNo(), null);
        accountLogDao.save(log);
    }

    @Override
    public void handleAccount() {
        accountService.updateAccount(
                new AccountModel(account.getId(), money, money, 0));
    }

    @Override
    public void handleIntegral() {
        IntegralWork.instantce().doIntegral(OrderNid.ORDER_NID_ACCOUNT_RECHARGE.getNid(), this.user, StringUtil.isNull(model.getId()));
    }

    @Override
    public void doAccountStatistics() {
        AccountWork.updateAccountStatistics(user, AccountStatisticsType.ACCOUNT_STATISTICS_RECHARGE_TOTAL.getType(), money);
    }
}
