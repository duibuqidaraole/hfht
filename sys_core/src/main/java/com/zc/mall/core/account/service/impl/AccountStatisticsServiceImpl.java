package com.zc.mall.core.account.service.impl;

import com.zc.mall.core.account.constant.AccountConstant;
import com.zc.mall.core.account.dao.AccountStatisticsDao;
import com.zc.mall.core.account.entity.AccountStatistics;
import com.zc.mall.core.account.model.AccountStatisticsModel;
import com.zc.mall.core.account.service.AccountStatisticsService;
import com.zc.mall.core.user.dao.UserRelationDao;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.entity.UserRelation;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import com.zc.sys.common.util.calculate.BigDecimalUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 账户统计
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年12月20日
 */
@Service
public class AccountStatisticsServiceImpl implements AccountStatisticsService {

    @Resource
    private AccountStatisticsDao accountStatisticsDao;
    @Resource
    private UserRelationDao userRelationDao;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    public Result list(AccountStatisticsModel model) {
        PageDataList<AccountStatistics> pageDataList = accountStatisticsDao.list(model);
        PageDataList<AccountStatisticsModel> pageDataList_ = new PageDataList<AccountStatisticsModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<AccountStatisticsModel> list = new ArrayList<AccountStatisticsModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (AccountStatistics accountStatistics : pageDataList.getList()) {
                AccountStatisticsModel model_ = AccountStatisticsModel.instance(accountStatistics);
                model_.setUserModel(UserModel.instance(accountStatistics.getUser()));
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
    public Result add(AccountStatisticsModel model) {

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
    public Result update(AccountStatisticsModel model) {

        return null;
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getById(AccountStatisticsModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        AccountStatistics accountStatistics = accountStatisticsDao.find(model.getId());
        AccountStatisticsModel model_ = AccountStatisticsModel.instance(accountStatistics);
        model_.setUserModel(UserModel.instance(accountStatistics.getUser()));
        return Result.success().setData(model_);
    }

    /**
     * 统计更新
     *
     * @param user   用户
     * @param type   类型
     * @param amount 金额
     */
    @Override
    public void updateStatistics(User user, int type, double amount) {
        AccountStatistics accountStatistics = accountStatisticsDao.getAccountStatistics(user, type);
        if (accountStatistics == null) {
            accountStatistics = new AccountStatistics(user, type, 0);
        }
        accountStatistics.setAmount(BigDecimalUtil.add(accountStatistics.getAmount(), amount));
        accountStatisticsDao.merge(accountStatistics);
    }

    @Override
    public Result getStatisticsByUserAndType(AccountStatisticsModel model) {
        QueryParam param = QueryParam.getInstance();
        param.addParam("user.id", model.getUserId());
        param.addParam("type", model.getType());
        return Result.success().setData(accountStatisticsDao.findByCriteriaForUnique(param));
    }

    /**
     * 获取用户总余额
     *
     * @param userModel
     * @return
     */
    @Override
    public UserModel setAccountStatistics(UserModel userModel) {
        return null;
    }

    /**
     * 添加用户消费金额
     *
     * @param list
     */
    @Override
    public void setUserPayInfo(List<UserModel> list) {
        for (UserModel userModel : list) {
            setUserPayInfo(userModel);
        }
    }

    /**
     * 添加用户消费金额
     *
     * @param userModel
     */
    @Override
    public void setUserPayInfo(UserModel userModel) {
        Double InviteUserTotalPay = 0D;
        AccountStatistics accountStatisticsAmount = accountStatisticsDao.findByUserId(userModel, AccountConstant.AccountStatisticsType.ACCOUNT_STATISTICS_ORDER_INFO_TOTAL.getType());
        AccountStatistics accountStatisticsCount = accountStatisticsDao.findByUserId(userModel, AccountConstant.AccountStatisticsType.ACCOUNT_STATISTICS_ORDER_INFO_COUNT.getType());
        userModel.setTotalPay(accountStatisticsAmount != null ? accountStatisticsAmount.getAmount() : 0D);
        userModel.setTotalCount(accountStatisticsCount != null ? accountStatisticsCount.getAmount() : 0D);
        PageDataList<UserRelation> inviteUserModelList = userRelationDao.findUserListByToUserId(userModel.getId());
        for (UserRelation userRelation : inviteUserModelList.getList()) {
            UserModel userModel1 = new UserModel();
            userModel1.setId(userRelation.getUser().getId());
            AccountStatistics accountStatisticsAmount1 = accountStatisticsDao.findByUserId(userModel1, AccountConstant.AccountStatisticsType.ACCOUNT_STATISTICS_ORDER_INFO_TOTAL.getType());
            InviteUserTotalPay += accountStatisticsAmount1 != null ? accountStatisticsAmount1.getAmount() : 0D;
        }
        userModel.setInviteUserTotalPay(InviteUserTotalPay);

    }
}