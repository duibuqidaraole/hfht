package com.zc.mall.core.user.executer;

import com.zc.mall.core.common.executer.BaseExecuter;
import com.zc.mall.core.common.pay.user.AbstractRealName;
import com.zc.mall.core.user.model.UserIdentifyModel;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.stereotype.Component;

/**
 * 用户实名请求
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月13日
 */
@Component
public class UserRealNameRequestExecuter extends BaseExecuter {

    private UserIdentifyModel model;

    @Override
    public void init() {
        if (!(this.obj instanceof UserIdentifyModel)) {
            throw new BusinessException("实例不存在");
        }
        this.model = (UserIdentifyModel) this.obj;
        this.user = model.getUser();
    }

    @Override
    public void handleInterface() {

        AbstractRealName.getRealNameWay().doRealName(model.getRealName(), model.getCardNo(),
                model.getBankCardNo(), model.getMobile());
    }
}
