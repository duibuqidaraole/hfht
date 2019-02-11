
package com.zc.mall.core.user.service.impl;

import com.zc.mall.core.account.dao.BankCardDao;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.integral.dao.IntegralAccountDao;
import com.zc.mall.core.integral.entity.IntegralAccount;
import com.zc.mall.core.manage.model.OrderTaskModel;
import com.zc.mall.core.user.dao.UserIdentifyDao;
import com.zc.mall.core.user.dao.UserInfoDao;
import com.zc.mall.core.user.entity.UserIdentify;
import com.zc.mall.core.user.entity.UserInfo;
import com.zc.mall.core.user.model.UserIdentifyModel;
import com.zc.mall.core.user.model.UserInfoModel;
import com.zc.mall.core.user.model.UserModel;
import com.zc.mall.core.user.service.UserInfoService;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户信息
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoDao userInfoDao;
    @Resource
    private UserIdentifyDao userIdentifyDao;
    @Resource
    private IntegralAccountDao integralAccountDao;
    @Resource
    private BankCardDao bankCardDao;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    public Result list(UserInfoModel model) {
        PageDataList<UserInfo> pageDataList = userInfoDao.list(model);
        PageDataList<UserInfoModel> pageDataList_ = new PageDataList<UserInfoModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<UserInfoModel> list = new ArrayList<UserInfoModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (UserInfo userInfo : pageDataList.getList()) {
                UserInfoModel model_ = UserInfoModel.instance(userInfo);
                model_.setUserModel(UserModel.instance(userInfo.getUser()));
                model_.setInviteUserModel(UserModel.instance(userInfo.getInviteUser()));
                list.add(model_);
            }
        }
        pageDataList_.setList(list);
        return Result.success().setData(pageDataList_);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result add(UserInfoModel model) {
        return null;
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result update(UserInfoModel model) {

        return null;
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result getById(UserInfoModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        UserInfo userInfo = userInfoDao.find(model.getId());
        UserInfoModel model_ = UserInfoModel.instance(userInfo);

        if (userInfo != null) {
            UserModel userModel = UserModel.instance(userInfo.getUser());
            IntegralAccount integralAccount = integralAccountDao.findByUserId(userModel.getId());
            double totalIntegral = integralAccount.getTotalIntegral();
            model_.setUserTotleIntegralAcount(totalIntegral);
            userModel.setUserIdentifyModel(UserIdentifyModel.instance(userInfo.getUser().getUserIdentify()));
//			QueryParam qp = QueryParam.getInstance();
//			qp.addParam("userId",model.getId());
//			BankCard bankCard = bankCardDao.findByCriteriaForUnique(qp);
//			model_.setBankCardModel(BankCardModel.instance(bankCard));
            model_.setUserModel(userModel);
            model_.setInviteUserModel(UserModel.instance(userInfo.getInviteUser()));
        }
        return Result.success().setData(model_);
    }

    /**
     * 通过用户Id获取用户信息
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result getUserInfoByUserId(UserInfoModel model) {
        if (model.getUserId() <= 0) {
            return Result.error("参数错误！");
        }
        UserInfo userInfo = userInfoDao.findObjByProperty("user.id", model.getUserId());
        UserIdentify userIdentify = userIdentifyDao.findObjByProperty("user.id", model.getUserId());
        UserInfoModel model_ = UserInfoModel.instance(userInfo);
        if (userInfo.getUser() != null) {
            model_.setUserModel(UserModel.instance(userInfo.getUser()));
        }
        model_.setUserIdentifyModel(UserIdentifyModel.instance(userIdentify));
        model_.setInviteUserModel(UserModel.instance(userInfo.getInviteUser()));
        return Result.success().setData(model_);
    }

    /**
     * 修改用户信息
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result updateInfo(UserInfoModel model) {
        userInfoDao.update(model.prototype());
        // 订单处理
        OrderTaskModel.dealOrder(model.getOrderTask() != null ? model.getOrderTask().getOrderNo() : "",
                BaseConstant.BUSINESS_STATE_YES, "修改用户信息处理成功");
        return Result.success();
    }

    /**
     * 根据id删除
     *
     * @param model userId
     * @return
     */
    @Override
    @Transactional
    public Result deleteById(UserInfoModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        userInfoDao.delete(model.getId());
        return Result.success();
    }

    /**
     * 修改商家用户信息
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result updateRelationUserInfo(UserInfoModel model) {
        if (model.getUserId() <= 0) {
            return Result.error("参数错误！");
        }
        UserInfo userInfo = userInfoDao.findObjByProperty("user.id", model.getUserId());
        model.setRelationUserInfoParam(userInfo);
        userInfoDao.update(model.prototype());
        model.setUserModel(UserModel.instance(userInfo.getUser()));
        return Result.success();
    }

}