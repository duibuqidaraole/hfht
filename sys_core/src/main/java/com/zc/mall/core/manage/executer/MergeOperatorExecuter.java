package com.zc.mall.core.manage.executer;

import com.zc.mall.core.common.executer.BaseExecuter;
import com.zc.mall.core.manage.model.OperatorModel;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.stereotype.Component;

/**
 * 合并管理员任务类
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Component
public class MergeOperatorExecuter extends BaseExecuter {

    private OperatorModel model;

    @Override
    public void init() {
        if (this.obj instanceof OperatorModel) {
            this.model = (OperatorModel) this.obj;
        } else {
            throw new BusinessException("实例化model不存在");
        }
    }

    @Override
    public void addOperateLog() {

    }

}
