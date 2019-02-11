package com.zc.mall.core.user.service.impl;

import com.zc.mall.core.account.service.AccountStatisticsService;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.user.dao.UserInfoDao;
import com.zc.mall.core.user.dao.UserRelationDao;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.entity.UserRelation;
import com.zc.mall.core.user.model.UserInfoModel;
import com.zc.mall.core.user.model.UserModel;
import com.zc.mall.core.user.model.UserRelationModel;
import com.zc.mall.core.user.service.UserRelationService;
import com.zc.mall.core.user.service.UserService;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.util.date.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户关系
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2018年03月15日
 */
@Service
public class UserRelationServiceImpl implements UserRelationService {

    @Resource
    private UserRelationDao userRelationDao;
    @Resource
    private UserInfoDao userInfoDao;
    @Resource
    private AccountStatisticsService accountStatisticsService;
    @Resource
    private UserService userService;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    public Result list(UserRelationModel model) {
        PageDataList<UserRelation> pageDataList = userRelationDao.list(model);
        PageDataList<UserRelationModel> pageDataList_ = new PageDataList<UserRelationModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<UserRelationModel> list = new ArrayList<UserRelationModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (UserRelation userRelation : pageDataList.getList()) {
                UserRelationModel model_ = UserRelationModel.instance(userRelation);
                if (userRelation.getBeUser() != null) {
                    UserModel userBeModel_ = UserModel.instance(userRelation.getBeUser());
                    if (userRelation.getUser() != null) {
                        UserModel userModel_ = UserModel.instance(userRelation.getUser());
                        userModel_.setInfoModel(UserInfoModel
                                .instance(userInfoDao.findObjByProperty("user.id", userRelation.getUser().getId())));
                        model_.setUserModel(userModel_);
                    }
                    if (userRelation.getBeUser() != null) {
                        userBeModel_.setInfoModel(UserInfoModel
                                .instance(userInfoDao.findObjByProperty("user.id", userRelation.getBeUser().getId())));
                        model_.setBeUserModel(userBeModel_);
                    }
                }
                list.add(model_);
            }
        }
        pageDataList_.setList(list);

        return Result.success().setData(pageDataList_);
    }


    @Override
    public Object simpleList(UserRelationModel model) {
        PageDataList<UserRelation> pageDataList = userRelationDao.list(model);
        PageDataList<UserModel> pageDataList_ = new PageDataList<UserModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<UserModel> list = new ArrayList<UserModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (UserRelation userRelation : pageDataList.getList()) {
                if (userRelation.getBeUser() != null) {
                    if (userRelation.getBeUser() != null) {
                        list.add(UserModel.instance(userRelation.getBeUser()));
                    }
                }

            }
        }
        pageDataList_.setList(list);

        return Result.success().setData(pageDataList_);
    }

    /**
     * 统计用户及分销奖励
     *
     * @param model
     * @return
     */
    @Override
    public Object userInfoAboutAccount(UserRelationModel model) {
        User beUser = model.getBeUser();
        //获取用户信息
        UserModel beUserModel = userService.getById(UserModel.instance(beUser));
        //获取用户邀请的人的列表
        List<UserRelation> list = userRelationDao.findUserListByToUserId(beUser.getId()).getList();
        List<UserModel> userModels = new ArrayList<>();
        for (UserRelation userRelation : list) {
            UserModel userModel = userService.getById(UserModel.instance(userRelation.getUser()));
            userModels.add(accountStatisticsService.setAccountStatistics(userModel));
        }
        return null;
    }

    /**
     * 根据用户id查询邀请的人的列表
     *
     * @param id
     * @return
     */
    @Override
    public List<UserModel> findUserListByToUserId(long id) {
        ArrayList<UserModel> userModels = new ArrayList<>();
        for (UserRelation userRelation : userRelationDao.findUserListByToUserId(id).getList()) {
            UserModel userModel = UserModel.instance(userRelation.getUser());
            userModel.setInfoModel(UserInfoModel.instance(userModel.getUserInfo()));
            userModels.add(userModel);
        }
        return userModels;
    }

    /**
     * 根据用户id获取邀请人
     *
     * @param id
     * @return
     */
    @Override
    public User findToUserByUserId(long id) {
        userRelationDao.findToUserByUserId(id);
        return userRelationDao.findToUserByUserId(id);
    }

    /**
     * 添加用户邀请人数
     *
     * @param list
     */
    @Override
    public void setInviteUserCount(List<UserModel> list) {
        for (UserModel userModel : list) {
            setInviteUserCount(userModel);
        }
    }

    public void setInviteUserCount(UserModel userModel) {
        userModel.setInviteUserCount(findUserListByToUserId(userModel.getId()).size());
    }

    /**
     * 关注商家
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result add(UserRelationModel model) {
        model.checkParam();
        model.addInit();
        UserRelation userRelation = model.prototype();
        userRelationDao.save(userRelation);
        return Result.success().setData(userRelation);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result update(UserRelationModel model) {
        model.checkParam();
        model.setUpdateParam();// 设置修改基本参数
        UserRelation userRelation = model.prototype();
        userRelationDao.update(userRelation);
        return Result.success();
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result getById(UserRelationModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        UserRelation userRelation = userRelationDao.find(model.getId());
        UserModel userModel_ = UserModel.instance(userRelation.getUser());
        UserModel userBeModel_ = UserModel.instance(userRelation.getBeUser());
        if (userRelation.getUser() != null) {
            if (userRelation.getUser().getUserInfo() != null) {
                userModel_.setInfoModel(UserInfoModel.instance(userRelation.getUser().getUserInfo()));
            }
            model.setBeUserModel(userModel_);
        }
        if (userRelation.getUser() != null) {

            model.setUserModel(userBeModel_);
        }

        return Result.success().setData(model);
    }

    /**
     * 取消关注商家
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result deleteById(UserRelationModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        userRelationDao.delete(model.getId());
        return Result.success();
    }

    /**
     * 通过商家Id和用户Id查询商家用户关系表
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result findUserRelationByUserIdAndToBeUserId(UserRelationModel model) {
        UserRelation userRelationEntiy = userRelationDao.findUserRelation(model);// 检测用户的唯一性
        return Result.success().setData(userRelationEntiy);
    }

    /**
     * 添加商家旗下借款人
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result addBorrowUserInRelation(UserRelationModel model) {
        model.addUserInRelationInit();
        UserRelation userRelation = model.prototype();
        userRelationDao.save(userRelation);
        return Result.success().setData(userRelation);
    }

    /**
     * 通过商家id和用户与商家之间的关系Type查询用户列表
     *
     * @param model
     * @return UserRelation
     */
    @Override
    @Transactional
    public Result findUserByToUserIdAndType(UserRelationModel model) {
        if (model.getBeUserId() <= 0) {
            return Result.error("参数错误！");
        }
        return Result.success().setData(userRelationDao.findUserByToUserIdAndType(model));
    }

    /**
     * 统计商家旗下今日注册人数
     *
     * @param model
     * @return UserRelation
     */
    @Override
    public List<UserRelation> countUserNumber(UserRelationModel model) {
        return userRelationDao.countUserNumber(model);
    }

    /**
     * 注册邀请
     *
     * @param user
     * @param inviteUser
     */
    @Override
    public void regInvite(User user, User inviteUser) {
        if (inviteUser == null) {
            return;
        }
        UserRelation userRelation = new UserRelation();
        userRelation.setAddTime(DateUtil.getNow());
        userRelation.setBeUser(inviteUser);
        userRelation.setState(BaseConstant.IDENTIFY_STATE_YES);
        userRelation.setType(BaseConstant.USERRELATION_TYPE_INVITE);
        userRelation.setUser(user);
        userRelationDao.save(userRelation);
    }

}