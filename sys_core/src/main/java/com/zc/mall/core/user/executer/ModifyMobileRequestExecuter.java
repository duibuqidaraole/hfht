package com.zc.mall.core.user.executer;

import com.zc.mall.core.common.executer.BaseExecuter;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.stereotype.Component;

/**
 * 修改手机号
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2018年02月11日
 */
@Component
public class ModifyMobileRequestExecuter extends BaseExecuter {

    private UserModel model;

    @Override
    public void init() {
        if (!(this.obj instanceof UserModel)) {
            throw new BusinessException("实例不存在");
        }
        this.model = (UserModel) this.obj;
    }

    @Override
    public void handleInterface() {

    }
}
