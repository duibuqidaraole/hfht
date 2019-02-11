package com.zc.mall.core.account.executer;

import com.zc.mall.core.account.entity.AccountLog;
import com.zc.mall.core.account.model.AccountDeductModel;
import com.zc.mall.core.account.model.AccountLogModel;
import com.zc.mall.core.account.model.AccountModel;
import com.zc.mall.core.common.constant.BaseConstant.OrderNid;
import com.zc.mall.core.common.executer.BaseExecuter;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.stereotype.Component;

/**
 * 资金变更任务
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2018年07月11日
 */
@Component
public class DeductDealExecuter extends BaseExecuter {

    private AccountDeductModel model;

    @Override
    public void init() {
        super.init();
        if (!(this.obj instanceof AccountDeductModel)) {
            throw new BusinessException("实例不存在");
        }
        this.model = (AccountDeductModel) this.obj;
        this.user = model.getUser();
        this.money = model.getAmount();
        this.account = model.getUser().getAccount();
    }

    @Override
    public void addAccountLog() {
        String nid = OrderNid.ORDER_NID_ACCOUNT_DEDUCT_Add.getNid();
        if (this.money < 0) {
            nid = OrderNid.ORDER_NID_ACCOUNT_DEDUCT_SUBTRACT.getNid();
        }
        AccountLog log = new AccountLogModel().instanceAccountLog(account, user, nid, null, Math.abs(money), model.getOrderNo(), null);
        accountLogDao.save(log);
    }

    @Override
    public void handleAccount() {
        accountService.updateAccount(
                new AccountModel(account.getId(), money, money, 0));
    }

}
