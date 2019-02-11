package com.zc.mall.mall.executer;

import com.zc.mall.core.account.constant.AccountConstant.AccountStatisticsType;
import com.zc.mall.core.account.service.work.AccountWork;
import com.zc.mall.core.common.constant.BaseConstant.OrderNid;
import com.zc.mall.core.common.executer.BaseExecuter;
import com.zc.mall.core.common.global.Global;
import com.zc.mall.core.integral.service.work.IntegralWork;
import com.zc.mall.core.sys.constant.ConfigParamConstant;
import com.zc.mall.mall.model.OrderInfoModel;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.util.calculate.BigDecimalUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Component;

/**
 * 确认收货任务
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月14日
 */
@Component
public class OrderInfoReceiveExecuter extends BaseExecuter {
    private OrderInfoModel model;

    @Override
    public void init() {
        super.init();
        if (!(this.obj instanceof OrderInfoModel)) {
            throw new BusinessException("实例不存在");
        }
        this.model = (OrderInfoModel) this.obj;
    }

    @Override
    public void addAccountLog() {

    }

    @Override
    public void handleAccount() {

    }

    @Override
    public void handleIntegral() {
        // 积分为整数
        int integral = (int) BigDecimalUtil.mul(money, Global.getdouble(ConfigParamConstant.SYS_PARAM_JF_ORDER_INFO_RECEIVE));
        if (integral > 0) {
            Global.setTransfer("integral", integral);
            IntegralWork.instantce().doIntegral(OrderNid.ORDER_NID_ORDER_INFO_RECEIVE.getNid(), this.user, StringUtil.isNull(model.getId()), integral);
        }
    }

    @Override
    public void doAccountStatistics() {
        AccountWork.updateAccountStatistics(user, AccountStatisticsType.ACCOUNT_STATISTICS_ORDER_INFO_TOTAL.getType(), money);
        AccountWork.updateAccountStatistics(user, AccountStatisticsType.ACCOUNT_STATISTICS_ORDER_INFO_COUNT.getType(), 1);
    }
}
