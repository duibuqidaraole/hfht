package com.zc.mall.core.user.dao;

import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.entity.UserRelation;
import com.zc.mall.core.user.model.UserRelationModel;
import com.zc.sys.common.dao.BaseDao;
import com.zc.sys.common.model.jpa.PageDataList;

import java.util.List;

/**
 * 用户关系
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2018年03月15日
 */
public interface UserRelationDao extends BaseDao<UserRelation> {
    /**
     * 列表
     *
     * @param model
     * @return
     */
    PageDataList<UserRelation> list(UserRelationModel model);

    /**
     * 通过用户和商家查询用户关系存不存在
     *
     * @param model
     * @return UserRelation
     */
    UserRelation findUserRelation(UserRelationModel model);

    /**
     * 通过商家id和用户与商家之间的关系Type查询用户列表
     *
     * @param model
     * @return UserRelation
     */
    List<UserRelation> findUserByToUserIdAndType(UserRelationModel model);

    /**
     * 统计商家旗下今日注册人数
     *
     * @param model
     * @return UserRelation
     */
    List<UserRelation> countUserNumber(UserRelationModel model);

    /**
     * 查询此用户邀请的人
     *
     * @param id
     * @return
     */
    PageDataList<UserRelation> findUserListByToUserId(long id);

    /**
     * 查询邀请人
     *
     * @param id
     * @return
     */
    User findToUserByUserId(long id);
}