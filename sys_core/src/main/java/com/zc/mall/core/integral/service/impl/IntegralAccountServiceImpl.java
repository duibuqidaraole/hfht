package com.zc.mall.core.integral.service.impl;

import com.zc.mall.core.common.global.Global;
import com.zc.mall.core.integral.dao.IntegralAccountDao;
import com.zc.mall.core.integral.entity.IntegralAccount;
import com.zc.mall.core.integral.model.IntegralAccountModel;
import com.zc.mall.core.integral.service.IntegralAccountService;
import com.zc.mall.core.sys.constant.ConfigParamConstant;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.exception.VersionControlException;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.util.log.LogUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 积分账户
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Service
public class IntegralAccountServiceImpl implements IntegralAccountService {

    @Resource
    private IntegralAccountDao integralAccountDao;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result list(IntegralAccountModel model) {
        PageDataList<IntegralAccount> pageDataList = integralAccountDao.list(model);
        PageDataList<IntegralAccountModel> pageDataList_ = new PageDataList<IntegralAccountModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<IntegralAccountModel> list = new ArrayList<IntegralAccountModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (IntegralAccount integralAccount : pageDataList.getList()) {
                IntegralAccountModel model_ = IntegralAccountModel.instance(integralAccount);
                model_.setUserModel(UserModel.instance(integralAccount.getUser()));
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
    public Result add(IntegralAccountModel model) {

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
    public Result update(IntegralAccountModel model) {

        return null;
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getById(IntegralAccountModel model) {
        IntegralAccount integralAccount = null;
        if (model.getUserId() > 0) {
            integralAccount = integralAccountDao.findByUserId(model.getUserId());
        } else {
            if (model.getId() <= 0) {
                return Result.error("参数错误！");
            }
            integralAccount = integralAccountDao.find(model.getId());
        }
        IntegralAccountModel model_ = IntegralAccountModel.instance(integralAccount);
        model_.setUserModel(UserModel.instance(integralAccount.getUser()));
        return Result.success().setData(model_);
    }

    /**
     * 更新积分
     *
     * @param model
     */
    @Override
    @Transactional
    public void updateIntegral(IntegralAccountModel model) {
        int currUpdateIntegralTimes = Global.getTransfer("currUpdateIntegralTimes", Integer.class);
        IntegralAccount integralAccount = integralAccountDao.findByUserId(model.getUser().getId());
        if (integralAccount == null) {
            throw new BusinessException("更新账户不存在");
        }
        currUpdateIntegralTimes++;
        try {
            integralAccountDao.updateIntegral(integralAccount.getId(), model.getTotalIntegral(),
                    model.getBalanceIntegral(), model.getExpenseIntegral(), model.getFreezeIntegral(),
                    integralAccount.getVersion());
        } catch (VersionControlException e) {
            LogUtil.info("资金更新失败信息:" + model.toString() + "------当前执行次数:" + currUpdateIntegralTimes);
            int versionControlTimes = Global.getInt(ConfigParamConstant.SYS_PARAM_VERSION_CONTROL_TIMES);// 版本控制次数
            if (currUpdateIntegralTimes < versionControlTimes) {
                Global.setTransfer("currUpdateIntegralTimes", currUpdateIntegralTimes);
                updateIntegral(model);
            }
            throw new BusinessException("账户更新异常");
        }
    }

    /**
     * 添加用户积分
     *
     * @param model
     */
    @Override
    public void setUserIntegral(UserModel model) {
        integralAccountDao.setUserIntegral(model);
    }

    /**
     * 添加用户积分
     *
     * @param userModelList
     */
    @Override
    public void setUserIntegral(List<UserModel> userModelList) {
        for (UserModel userModel : userModelList) {
            integralAccountDao.setUserIntegral(userModel);
        }
    }

    /**
     * 通过用户Id获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getByUserId(IntegralAccountModel model) {
        if (model.getUserId() <= 0) {
            return Result.error("参数错误！");
        }
        IntegralAccount integralAccount = integralAccountDao.findByUserId(model.getUserId());
        IntegralAccountModel model_ = IntegralAccountModel.instance(integralAccount);
        model_.setUserModel(UserModel.instance(integralAccount.getUser()));
        return Result.success().setData(model_);
    }

}