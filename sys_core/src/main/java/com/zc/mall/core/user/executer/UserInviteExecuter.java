package com.zc.mall.core.user.executer;

import com.zc.mall.core.common.executer.BaseExecuter;
import com.zc.mall.core.user.dao.UserDao;
import com.zc.mall.core.user.dao.UserRelationDao;
import com.zc.mall.core.user.entity.UserRelation;
import com.zc.mall.core.user.model.UserInfoModel;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 用户邀请任务
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月13日
 */
@Component
public class UserInviteExecuter extends BaseExecuter {
    private UserInfoModel model;
    private UserRelation userRelation;
    @Resource
    private UserRelationDao userRelationDao;
    @Resource
    private UserDao userDao;

    @Override
    public void init() {
        if (!(this.obj instanceof UserInfoModel)) {
            throw new BusinessException("实例不存在");
        }
    }

    @Override
    public void handlePromotion() {

    }

}
