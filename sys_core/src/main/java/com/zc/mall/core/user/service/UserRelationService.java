package com.zc.mall.core.user.service;

import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.entity.UserRelation;
import com.zc.mall.core.user.model.UserModel;
import com.zc.mall.core.user.model.UserRelationModel;
import com.zc.sys.common.form.Result;

import java.util.List;

/**
 * 用户关系
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2018年03月15日
 */
public interface UserRelationService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(UserRelationModel model);

    /**
     * 关注商家
     *
     * @param model
     * @return
     */
    public Result add(UserRelationModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(UserRelationModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(UserRelationModel model);

    /**
     * 取消关注商家
     *
     * @param model
     * @return
     */
    public Result deleteById(UserRelationModel model);

    /**
     * 添加商家旗下借款人
     *
     * @param model
     * @return
     */
    public Result addBorrowUserInRelation(UserRelationModel model);

    /**
     * 通过商家Id和用户Id查询商家用户关系表
     *
     * @param model
     * @return
     */
    public Result findUserRelationByUserIdAndToBeUserId(UserRelationModel model);

    /**
     * 通过商家id和用户与商家之间的关系Type查询用户列表
     *
     * @param model
     * @return UserRelation
     */
    public Result findUserByToUserIdAndType(UserRelationModel model);

    /**
     * 统计商家旗下今日注册人数
     *
     * @param model
     * @return UserRelation
     */
    List<UserRelation> countUserNumber(UserRelationModel model);

    /**
     * 注册邀请
     *
     * @param user
     * @param inviteUser
     */
    void regInvite(User user, User inviteUser);

    Object simpleList(UserRelationModel model);

    /**
     * 统计用户及分销奖励
     *
     * @param model
     * @return
     */
    Object userInfoAboutAccount(UserRelationModel model);

    /**
     * 根据用户id查询邀请的人的列表
     *
     * @param id
     * @return
     */
    List<UserModel> findUserListByToUserId(long id);

    /**
     * 根据用户id获取邀请人
     *
     * @param id
     * @return
     */
    User findToUserByUserId(long id);

    /**
     * 添加用户邀请人数
     *
     * @param list
     */
    void setInviteUserCount(List<UserModel> list);
}