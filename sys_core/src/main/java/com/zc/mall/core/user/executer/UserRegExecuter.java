package com.zc.mall.core.user.executer;

import com.zc.mall.core.account.constant.AccountConstant.AccountStatisticsType;
import com.zc.mall.core.account.service.work.AccountWork;
import com.zc.mall.core.common.executer.BaseExecuter;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.stereotype.Component;

/**
 * 用户注册任务
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月13日
 */
@Component
public class UserRegExecuter extends BaseExecuter {
    private UserModel model;
    /**
     * 推广活动方式-注册
     **/
    public static final int PROMOTION_WAY_REG = 1;

    @Override
    public void init() {
        if (!(this.obj instanceof UserModel)) {
            throw new BusinessException("实例不存在");
        }
        this.model = (UserModel) this.obj;
        this.user = this.model.prototype();
    }

    @Override
    public void handlePromotion() {
//		Object promotionModel = ReflectUtil.invokeConstructorMethod(BaseConstant.PROMOTION_MODEL, PROMOTION_WAY_REG, this.user);
        //发送队列
//		QueueAbstract.send(StringUtil.getSerialNumber(), OrderNid.ORDER_NID_USER_PROMOTION.getNid(), QueueCode.QUEUE_CODE_OTHER.getCode(), promotionModel, this.user);
    }

    @Override
    public void doAccountStatistics() {
        AccountWork.updateAccountStatistics(null, AccountStatisticsType.USER_RESIGER.getType(), 1);
    }
}
