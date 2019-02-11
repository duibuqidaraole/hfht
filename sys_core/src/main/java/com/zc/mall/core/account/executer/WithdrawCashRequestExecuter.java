package com.zc.mall.core.account.executer;

import com.zc.mall.core.account.constant.AccountConstant.AccountStatisticsType;
import com.zc.mall.core.account.entity.AccountLog;
import com.zc.mall.core.account.model.AccountLogModel;
import com.zc.mall.core.account.model.AccountModel;
import com.zc.mall.core.account.model.WithdrawCashModel;
import com.zc.mall.core.account.service.work.AccountWork;
import com.zc.mall.core.common.constant.BaseConstant.OrderNid;
import com.zc.mall.core.common.executer.BaseExecuter;
import com.zc.mall.core.integral.service.work.IntegralWork;
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
public class WithdrawCashRequestExecuter extends BaseExecuter {

    private WithdrawCashModel model;

    @Override
    public void init() {
        super.init();
        if (!(this.obj instanceof WithdrawCashModel)) {
            throw new BusinessException("实例不存在");
        }
        this.model = (WithdrawCashModel) this.obj;
        this.user = model.getUser();
        this.money = model.getAmount();
        this.account = user.getAccount();
    }

    @Override
    public void addAccountLog() {
        AccountLog log = new AccountLogModel().instanceAccountLog(account, user, OrderNid.ORDER_NID_ACCOUNT_WITHDRAW_CASH.getNid(), null, money, model.getOrderNo(), null);
        accountLogDao.save(log);
    }

    @Override
    public void handleAccount() {
        accountService.updateAccount(
                new AccountModel(account.getId(), 0, -money, money));
    }

    @Override
    public void handleIntegral() {
        IntegralWork.instantce().doIntegral(OrderNid.ORDER_NID_ACCOUNT_WITHDRAW_CASH.getNid(), this.user, StringUtil.isNull(model.getId()));
    }

    @Override
    public void doAccountStatistics() {
        AccountWork.updateAccountStatistics(user, AccountStatisticsType.ACCOUNT_STATISTICS_WITHDRAW_CASH_TOTAL.getType(), this.money);
    }
}
	

