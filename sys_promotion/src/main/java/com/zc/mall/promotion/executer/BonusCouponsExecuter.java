package com.zc.mall.promotion.executer;

import com.zc.mall.core.account.entity.AccountLog;
import com.zc.mall.core.account.model.AccountLogModel;
import com.zc.mall.core.account.model.AccountModel;
import com.zc.mall.core.common.executer.BaseExecuter;
import com.zc.mall.core.common.global.Global;
import com.zc.mall.promotion.constant.BasePromotionConstant;
import com.zc.mall.promotion.model.BonusCouponsModel;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Component;

/**
 * 推广活动任务
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月13日
 */
@Component
public class BonusCouponsExecuter extends BaseExecuter {
    private BonusCouponsModel model;

    @Override
    public void init() {
        super.init();
        if (!(this.obj instanceof BonusCouponsModel)) {
            throw new BusinessException("实例不存在");
        }
        this.model = (BonusCouponsModel) this.obj;
        this.account = accountService.findByUser(this.user.getId());
    }

    @Override
    public void addAccountLog() {
        Global.setTransfer("money", money);
        AccountLog log = new AccountLogModel().instanceAccountLog(account, user, BasePromotionConstant.BONUS_COUPONS_ACCOUNT_LOG_TYPE_CASH,
                null, this.money, StringUtil.getSerialNumber(), null);
        accountLogDao.save(log);
    }

    @Override
    public void handleAccount() {
        accountService.updateAccount(
                new AccountModel(account.getId(), this.money, this.money, 0));
    }
}
