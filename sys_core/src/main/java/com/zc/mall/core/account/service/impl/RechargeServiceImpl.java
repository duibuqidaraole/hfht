package com.zc.mall.core.account.service.impl;

import com.zc.mall.core.account.dao.AccountDao;
import com.zc.mall.core.account.dao.BankCardDao;
import com.zc.mall.core.account.dao.RechargeDao;
import com.zc.mall.core.account.entity.Recharge;
import com.zc.mall.core.account.executer.RechargeRequestExecuter;
import com.zc.mall.core.account.executer.RechargeSuccessExecuter;
import com.zc.mall.core.account.model.RechargeModel;
import com.zc.mall.core.account.service.RechargeService;
import com.zc.mall.core.common.executer.Executer;
import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.manage.dao.OrderTaskDao;
import com.zc.mall.core.user.dao.UserDao;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 充值
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Service
public class RechargeServiceImpl implements RechargeService {

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
    public Result list(RechargeModel model) {
        PageDataList<Recharge> pageDataList = rechargeDao.list(model);
        PageDataList<RechargeModel> pageDataList_ = new PageDataList<RechargeModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<RechargeModel> list = new ArrayList<RechargeModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (Recharge recharge : pageDataList.getList()) {
                RechargeModel model_ = RechargeModel.instance(recharge);
                model_.setUserModel(UserModel.instance(recharge.getUser()));
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
    public Result add(RechargeModel model) {

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
    public Result update(RechargeModel model) {
        return null;
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getById(RechargeModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        Recharge recharge = rechargeDao.find(model.getId());
        RechargeModel model_ = RechargeModel.instance(recharge);
        model_.setUserModel(UserModel.instance(recharge.getUser()));
        return Result.success().setData(model_);
    }

    /**
     * 充值请求
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result rechargeReq(RechargeModel model) {
        model.checkRecharge();// 校验参数
        model.initRecharge();//初始化
        Recharge recharge = model.prototype();
        rechargeDao.save(recharge);
        //实名请求任务
        RechargeModel rModel = RechargeModel.instance(recharge);
        Executer rechargeRequestExecuter = BeanUtil.getBean(RechargeRequestExecuter.class);
        rechargeRequestExecuter.execute(rModel);
        return Result.success("处理中...请稍后！").setData(rModel.getPayRecharge());
    }

//	private void doRechargeQueue(Recharge recharge){
//		//充值发送队列
//		QueueAbstract.send(StringUtil.getSerialNumber(), OrderNid.ORDER_NID_ACCOUNT_RECHARGE.getNid(), QueueCode.QUEUE_CODE_OTHER.getCode(), RechargeModel.instance(recharge), recharge.getUser());
//	}

    /**
     * 充值处理
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result rechargeDeal(RechargeModel model) {
        Recharge recharge = rechargeDao.find(model.getId());
        model.checkRechargeDeal(recharge);// 处理校验
        model.initRechargeDeal(recharge);// 处理
        rechargeDao.update(recharge);
        //银行卡充值任务
        Executer rechargeExecuter = BeanUtil.getBean(RechargeSuccessExecuter.class);
        rechargeExecuter.execute(RechargeModel.instance(recharge));
        return Result.success("充值成功");
    }

}