package com.zc.mall.core.user.dao;

import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.dao.BaseDao;
import com.zc.sys.common.model.jpa.PageDataList;

/**
 * 用户
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public interface UserDao extends BaseDao<User> {
    /**
     * 列表
     *
     * @param model
     * @return
     */
    PageDataList<User> list(UserModel model);

    /**
     * 根据手机号查询用户
     *
     * @param mobile
     * @return
     */
    User findByMobile(String mobile);

    /**
     * 查询所有注册用户
     *
     * @return
     */
    Long findUserNumber();

    /**
     * 查询今日所有注册用户
     *
     * @return
     */
    Long findUserNumberByToday();

    /**
     * 根据openId查询用户
     *
     * @param openId
     * @return
     */
    User findByOpenId(String openId);
}