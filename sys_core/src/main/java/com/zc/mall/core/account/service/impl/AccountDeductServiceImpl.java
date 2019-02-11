package com.zc.mall.core.account.service.impl;

import com.zc.mall.core.account.dao.AccountDeductDao;
import com.zc.mall.core.account.entity.AccountDeduct;
import com.zc.mall.core.account.executer.DeductDealExecuter;
import com.zc.mall.core.account.model.AccountDeductModel;
import com.zc.mall.core.account.service.AccountDeductService;
import com.zc.mall.core.common.constant.BaseConstant.OrderNid;
import com.zc.mall.core.common.constant.BaseConstant.QueueCode;
import com.zc.mall.core.common.executer.Executer;
import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.common.queue.work.QueueAbstract;
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
 * 线下扣款
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Service
public class AccountDeductServiceImpl implements AccountDeductService {

    @Resource
    private AccountDeductDao accountDeductDao;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    public Result list(AccountDeductModel model) {
        PageDataList<AccountDeduct> pageDataList = accountDeductDao.list(model);
        PageDataList<AccountDeductModel> pageDataList_ = new PageDataList<AccountDeductModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<AccountDeductModel> list = new ArrayList<AccountDeductModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (AccountDeduct accountDeduct : pageDataList.getList()) {
                AccountDeductModel model_ = AccountDeductModel.instance(accountDeduct);
                model_.setUserModel(UserModel.instance(accountDeduct.getUser()));
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
    public Result add(AccountDeductModel model) {

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
    public Result update(AccountDeductModel model) {

        return null;
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getById(AccountDeductModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        AccountDeduct accountDeduct = accountDeductDao.find(model.getId());
        AccountDeductModel model_ = AccountDeductModel.instance(accountDeduct);
        model_.setUserModel(UserModel.instance(accountDeduct.getUser()));
        // model_=UserModel.instance(UserInfoModel.instance(user.getUserInfo()).getUser());
        return Result.success().setData(model_);
    }

    /**
     * 线下资金变更请求
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result deductRequest(AccountDeductModel model) {
        // 扣款请求校验
        model.checkDeductRequest();
        // 扣款请求初始化
        model.initDeductRequest();
        AccountDeduct accountDeduct = model.prototype();
        AccountDeduct accountDeductEntiy = accountDeductDao.save(accountDeduct);

        // 资金变更队列
        QueueAbstract.send(StringUtil.getSerialNumber(), OrderNid.ORDER_NID_ACCOUNT_DEDUCT.getNid(),
                QueueCode.QUEUE_CODE_OTHER.getCode(), AccountDeductModel.instance(accountDeductEntiy), accountDeductEntiy.getUser());
        return Result.success("线下资金变更处理中");
    }

    /**
     * 线下资金变更处理
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result deductDeal(AccountDeductModel model) {
        // 扣款处理校验
        AccountDeduct accountDeduct = model.checkDeductDeal();
        // 扣款处理初始化
        model.initDeductDeal(accountDeduct);
        accountDeductDao.update(accountDeduct);
        // 扣款任务
        Executer deductDealExecuter = BeanUtil.getBean(DeductDealExecuter.class);
        deductDealExecuter.execute(AccountDeductModel.instance(accountDeduct));
        return Result.success("线下资金变更处理完成");
    }

}