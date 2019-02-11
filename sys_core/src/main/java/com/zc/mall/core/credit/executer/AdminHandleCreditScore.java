package com.zc.mall.core.credit.executer;

import com.zc.mall.core.common.constant.BaseConstant.OrderNid;
import com.zc.mall.core.common.executer.BaseExecuter;
import com.zc.mall.core.credit.model.CreditScoreModel;
import com.zc.mall.core.credit.service.work.CreditScoreWork;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 后台手动更新信用分
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月14日
 */
@Component
public class AdminHandleCreditScore extends BaseExecuter {

    private CreditScoreModel model;
    @Resource
    private CreditScoreWork creditScoreWork;

    @Override
    public void init() {
        if (!(this.obj instanceof CreditScoreModel)) {
            throw new BusinessException("实例不存在");
        }
        this.model = (CreditScoreModel) this.obj;
    }

    @Override
    public void handleCredit() {
        creditScoreWork.doCreditScore(OrderNid.ORDER_NID_ADMIN_HANDLE_CREDIT_SCORE.getNid(), user, "",
                money, model.getOperatorModel().prototype(), model.getRemark(), model);
    }
}
