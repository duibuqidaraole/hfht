package com.zc.mall.core.account.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zc.mall.core.account.dao.UserSelfHelpAccountLogDao;
import com.zc.mall.core.account.entity.UserSelfHelpAccountLog;
import com.zc.mall.core.account.model.UserSelfHelpAccountLogModel;
import com.zc.mall.core.account.service.UserSelfHelpAccountLogService;
import com.zc.mall.core.user.dao.UserDao;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户自助记账
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2018年08月05日
 */
@Service
public class UserSelfHelpAccountLogServiceImpl implements UserSelfHelpAccountLogService {

    @Resource
    private UserSelfHelpAccountLogDao userSelfHelpAccountLogDao;
    @Resource
    private UserDao userDao;
    @PersistenceContext
    protected EntityManager em;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    public Result list(UserSelfHelpAccountLogModel model) {
        PageDataList<UserSelfHelpAccountLog> pageDataList = userSelfHelpAccountLogDao.list(model);
        PageDataList<UserSelfHelpAccountLogModel> pageDataList_ = new PageDataList<UserSelfHelpAccountLogModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<UserSelfHelpAccountLogModel> list = new ArrayList<UserSelfHelpAccountLogModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (UserSelfHelpAccountLog userSelfHelpAccountLog : pageDataList.getList()) {
                UserSelfHelpAccountLogModel model_ = UserSelfHelpAccountLogModel.instance(userSelfHelpAccountLog);
                model_.setUserModel(UserModel.instance(userSelfHelpAccountLog.getUser()));
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
    public Result add(UserSelfHelpAccountLogModel model) {
        // 添加校验
        this.checkAdd(model);
        // 添加初始化
        this.initAdd(model);
        userSelfHelpAccountLogDao.save(model.prototype());
        return Result.success();
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result update(UserSelfHelpAccountLogModel model) {
        // 修改校验
        this.checkUpdate(model);
        // 修改初始化
        this.initUpdate(model);
        userSelfHelpAccountLogDao.update(model.getUserSelfHelpAccountLog());
        return Result.success();
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getById(UserSelfHelpAccountLogModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误");
        }
        UserSelfHelpAccountLog bonusCoupons = userSelfHelpAccountLogDao.find(model.getId());
        UserSelfHelpAccountLogModel model_ = UserSelfHelpAccountLogModel.instance(bonusCoupons);
        model_.setUserModel(UserModel.instance(bonusCoupons.getUser()));
        return Result.success().setData(model_);
    }

    @Override
    public Object sum(UserSelfHelpAccountLogModel model) {
        QueryParam param = QueryParam.getInstance();
        if (!StringUtil.isBlank(model.getUserId()) && model.getUserId() > 0) {
            param.addParam("user.id", model.getUserId());
        }
        double sumAll = 0;
        double sumZ = 0;
        double sumF = 0;
        List<UserSelfHelpAccountLog> list = userSelfHelpAccountLogDao.findByCriteria(param);
        for (UserSelfHelpAccountLog userSelfHelpAccountLog : list) {
            sumAll += userSelfHelpAccountLog.getAmount();
            if (userSelfHelpAccountLog.getAmount() > 0) {
                sumZ += userSelfHelpAccountLog.getAmount();
            } else {
                sumF += userSelfHelpAccountLog.getAmount();
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sumAll", sumAll);
        jsonObject.put("sumZ", sumZ);
        jsonObject.put("sumF", sumF);
        return Result.success().setData(jsonObject);

    }


    /**
     * 添加校验
     *
     * @param model
     */
    private void checkAdd(UserSelfHelpAccountLogModel model) {
        if (model.getUserId() <= 0) {
            throw new BusinessException("参数错误");
        }
        User user = userDao.find(model.getUserId());
        if (user == null) {
            throw new BusinessException("参数错误");
        }
        model.setUser(user);
    }

    /**
     * 添加初始化
     *
     * @param model
     */
    private void initAdd(UserSelfHelpAccountLogModel model) {
        model.setAddTime(DateUtil.getNow());
    }

    /**
     * 修改校验
     *
     * @param model
     */
    private void checkUpdate(UserSelfHelpAccountLogModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误");
        }
    }

    /**
     * 修改初始化
     *
     * @param model
     */
    private void initUpdate(UserSelfHelpAccountLogModel model) {
        UserSelfHelpAccountLog userSelfHelpAccountLog = userSelfHelpAccountLogDao.find(model.getId());
        userSelfHelpAccountLog.setName(model.getName());
        userSelfHelpAccountLog.setContent(model.getContent());
        userSelfHelpAccountLog.setType(model.getType());
        userSelfHelpAccountLog.setPaymentsType(model.getPaymentsType());
        userSelfHelpAccountLog.setAmount(model.getAmount());
        userSelfHelpAccountLog.setInterest(model.getInterest());
        userSelfHelpAccountLog.setRemark(model.getRemark());
        model.setUserSelfHelpAccountLog(userSelfHelpAccountLog);
    }
}