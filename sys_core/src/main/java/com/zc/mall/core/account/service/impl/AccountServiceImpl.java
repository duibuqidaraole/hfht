package com.zc.mall.core.account.service.impl;

import com.zc.mall.core.account.dao.AccountDao;
import com.zc.mall.core.account.entity.Account;
import com.zc.mall.core.account.model.AccountModel;
import com.zc.mall.core.account.service.AccountService;
import com.zc.mall.core.common.global.Global;
import com.zc.mall.core.sys.constant.ConfigParamConstant;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.exception.VersionControlException;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.util.calculate.BigDecimalUtil;
import com.zc.sys.common.util.log.LogUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 资金账户
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountDao accountDao;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    public Result list(AccountModel model) {
        PageDataList<Account> pageDataList = accountDao.list(model);
        PageDataList<AccountModel> pageDataList_ = new PageDataList<AccountModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<AccountModel> list = new ArrayList<AccountModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (Account account : pageDataList.getList()) {
                AccountModel model_ = AccountModel.instance(account);
                model_.setUserModel(UserModel.instance(account.getUser()));
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
    public Result add(AccountModel model) {
        Account account = accountDao.find(1l);
        account.setBalance(BigDecimalUtil.add(account.getBalance(), 1d));
        accountDao.update(account);
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
    public void updateAccount(AccountModel model) {
        int currUpdateAccountTimes = Global.getTransfer("currUpdateAccountTimes", Integer.class);
        Account account = accountDao.find(model.getId());
        if (account == null) {
            throw new BusinessException("更新账户不存在");
        }
        currUpdateAccountTimes++;
        try {
            accountDao.updateAccount(account.getId(), model.getTotal(), model.getBalance(), model.getFreezeAmount(), account.getVersion());
        } catch (VersionControlException e) {
            LogUtil.info("资金更新失败信息:" + model.toString() + "------当前执行次数:" + currUpdateAccountTimes);
            int versionControlTimes = Global.getInt(ConfigParamConstant.SYS_PARAM_VERSION_CONTROL_TIMES);// 版本控制次数
            if (currUpdateAccountTimes < versionControlTimes) {
                Global.setTransfer("currUpdateAccountTimes", currUpdateAccountTimes);
                updateAccount(model);
            }
            throw new BusinessException("账户更新异常");
        }
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getById(AccountModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        Account account = accountDao.find(model.getId());
        AccountModel model_ = AccountModel.instance(account);
        model_.setUserModel(UserModel.instance(account.getUser()));
        return Result.success().setData(model_);
    }

    /**
     * 根据userId查询
     *
     * @param userId
     * @return
     */
    @Override
    public Account findByUser(long userId) {
        return accountDao.findByUser(userId);
    }

    /**
     * 添加用户资金信息
     *
     * @param beUserModel
     */
    @Override
    public void setUserAccount(UserModel beUserModel) {
        accountDao.setUserAccount(beUserModel);
    }

    /**
     * 添加用户资金信息
     *
     * @param userModelList
     */
    @Override
    public void setUserAccount(List<UserModel> userModelList) {
        for (UserModel userModel : userModelList) {
            accountDao.setUserAccount(userModel);
        }
    }

}