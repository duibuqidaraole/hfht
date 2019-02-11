
package com.zc.mall.core.account.service.impl;

import com.zc.mall.core.account.dao.AccountDao;
import com.zc.mall.core.account.dao.BankCardDao;
import com.zc.mall.core.account.dao.RechargeDao;
import com.zc.mall.core.account.dao.WithdrawCashDao;
import com.zc.mall.core.account.entity.WithdrawCash;
import com.zc.mall.core.account.executer.WithdrawCashRequestExecuter;
import com.zc.mall.core.account.executer.WithdrawFailExecuter;
import com.zc.mall.core.account.executer.WithdrawSuccessExecuter;
import com.zc.mall.core.account.model.WithdrawCashModel;
import com.zc.mall.core.account.service.WithdrawCashService;
import com.zc.mall.core.common.constant.BaseConstant.OrderNid;
import com.zc.mall.core.common.constant.BaseConstant.QueueCode;
import com.zc.mall.core.common.executer.Executer;
import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.common.queue.work.QueueAbstract;
import com.zc.mall.core.manage.dao.OrderTaskDao;
import com.zc.mall.core.user.dao.UserDao;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 提现
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Service
public class WithdrawCashServiceImpl implements WithdrawCashService {

    @Resource
    private WithdrawCashDao withdrawCashDao;
    @Resource
    private RechargeDao rechargeDao;
    @Resource
    private OrderTaskDao orderTaskDao;
    @Resource
    private BankCardDao bankCardDao;
    @Resource
    private UserDao userDao;
    @Resource
    private AccountDao accountDao;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    public Result list(WithdrawCashModel model) {
        PageDataList<WithdrawCash> pageDataList = withdrawCashDao.list(model);
        PageDataList<WithdrawCashModel> pageDataList_ = new PageDataList<WithdrawCashModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<WithdrawCashModel> list = new ArrayList<WithdrawCashModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (WithdrawCash withdrawCash : pageDataList.getList()) {
                WithdrawCashModel model_ = WithdrawCashModel.instance(withdrawCash);
                model_.setUserModel(UserModel.instance(withdrawCash.getUser()));
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
    public Result add(WithdrawCashModel model) {

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
    public Result update(WithdrawCashModel model) {

        return null;
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getById(WithdrawCashModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        WithdrawCash withdrawCash = withdrawCashDao.find(model.getId());
        WithdrawCashModel model_ = WithdrawCashModel.instance(withdrawCash);
        model_.setUserModel(UserModel.instance(withdrawCash.getUser()));
        return Result.success().setData(model_);
    }

    /**
     * 提现请求
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result withdrawReq(WithdrawCashModel model) {
        model.checkWithdrawCash();// 校验参数
        model.initWithdraw();//初始化
        WithdrawCash WithdrawCash = model.prototype();
        withdrawCashDao.save(WithdrawCash);
        //银行卡提现任务
        Executer withdrawSuccessExecuter = BeanUtil.getBean(WithdrawCashRequestExecuter.class);
        withdrawSuccessExecuter.execute(model);
        return Result.success("提现申请处理中...请稍后！");
    }

    /**
     * 提现失败处理
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result withdrawFailDeal(WithdrawCashModel model) {
        WithdrawCash withdrawCash = withdrawCashDao.find(model.getId());
        model.initWithdrawCashFailDeal(withdrawCash);// 处理
        withdrawCashDao.update(withdrawCash);
        //银行卡提现任务
        Executer withdrawSuccessExecuter = BeanUtil.getBean(WithdrawFailExecuter.class);
        withdrawSuccessExecuter.execute(model);
        return Result.success("提现失败");
    }

    /**
     * 提现成功处理
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result withdrawSuccessfulDeal(WithdrawCashModel model) {
        WithdrawCash withdrawCash = withdrawCashDao.find(model.getId());
        model.initWithdrawCashSuccessDeal(model);// 处理
        withdrawCashDao.update(withdrawCash);
        //银行卡提现任务
        Executer withdrawSuccessExecuter = BeanUtil.getBean(WithdrawSuccessExecuter.class);
        withdrawSuccessExecuter.execute(model);
        return Result.success("提现成功");
    }

    /**
     * 提现审核处理
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result auditWithdrawDeal(WithdrawCashModel model) {
        model.checkAuditWithdraw();
        WithdrawCash withdrawCash = withdrawCashDao.find(model.getId());
        model.initAuditWithdraw(withdrawCash);
        model.setUser(withdrawCash.getUser());
        model.setUserId(withdrawCash.getUser().getId());
        QueueAbstract.send(StringUtil.getSerialNumber(), OrderNid.ORDER_NID_ACCOUNT_WITHDRAW_CASH.getNid(), QueueCode.QUEUE_CODE_OTHER.getCode(), model, model.getUser());
        return Result.success("---提现审核处理中---");
    }

}