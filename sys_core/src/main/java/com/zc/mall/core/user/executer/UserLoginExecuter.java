package com.zc.mall.core.user.executer;

import com.zc.mall.core.common.constant.BaseConstant.OrderNid;
import com.zc.mall.core.common.executer.BaseExecuter;
import com.zc.mall.core.integral.service.work.IntegralWork;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Component;

/**
 * 用户登录任务
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月13日
 */
@Component
public class UserLoginExecuter extends BaseExecuter {

    private UserModel model;
    /**
     * 推广活动方式-登录
     **/
    public static final int PROMOTION_WAY_LOGIN = 2;

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
//		Object promotionModel = ReflectUtil.invokeConstructorMethod(BaseConstant.PROMOTION_MODEL, PROMOTION_WAY_LOGIN, this.user);
        //发送队列
//		QueueAbstract.send(StringUtil.getSerialNumber(), OrderNid.ORDER_NID_USER_PROMOTION.getNid(), QueueCode.QUEUE_CODE_OTHER.getCode(), promotionModel, this.user);
    }

    @Override
    public void handleIntegral() {
        IntegralWork.instantce().doIntegral(OrderNid.ORDER_NID_USER_LOGIN.getNid(), this.user, StringUtil.isNull(model.getId()));
    }
}
