package com.zc.mall.core.account.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zc.mall.core.account.dao.AccountLogDao;
import com.zc.mall.core.account.entity.AccountLog;
import com.zc.mall.core.account.model.AccountLogModel;
import com.zc.mall.core.account.service.AccountLogService;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.entity.UserRelation;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 资金日志
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Service
public class AccountLogServiceImpl implements AccountLogService {

    @Resource
    private AccountLogDao accountLogDao;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result list(AccountLogModel model) {
        PageDataList<AccountLog> pageDataList = accountLogDao.list(model);
        PageDataList<AccountLogModel> pageDataList_ = new PageDataList<AccountLogModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<AccountLogModel> list = new ArrayList<AccountLogModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (AccountLog accountLog : pageDataList.getList()) {
                AccountLogModel model_ = AccountLogModel.instance(accountLog);
                model_.setUserModel(UserModel.instance(accountLog.getUser()));
                model_.setToUserModel(UserModel.instance(accountLog.getToUser()));
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
    public Result add(AccountLogModel model) {

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
    public Result update(AccountLogModel model) {

        return null;
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getById(AccountLogModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        AccountLog accountLog = accountLogDao.find(model.getId());
        AccountLogModel model_ = AccountLogModel.instance(accountLog);
        model_.setUserModel(UserModel.instance(accountLog.getUser()));
        model_.setToUserModel(UserModel.instance(accountLog.getToUser()));
        return Result.success().setData(model_);
    }

    /**
     * 获取用户总结
     *
     * @param list
     * @param beUser
     * @return
     */
    @Override
    public Object userInfoAboutAccount(List<UserRelation> list, User beUser) {
        JSONObject result = new JSONObject();
        //添加用户邀请人个数
        result.put("inviteUserCount", list.size());

        return null;
    }

}