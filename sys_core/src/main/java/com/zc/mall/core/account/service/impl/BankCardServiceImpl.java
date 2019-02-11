
package com.zc.mall.core.account.service.impl;

import com.zc.mall.core.account.dao.BankCardDao;
import com.zc.mall.core.account.entity.BankCard;
import com.zc.mall.core.account.executer.ChangeBankCardRequestExecuter;
import com.zc.mall.core.account.executer.UserBindBCExecuter;
import com.zc.mall.core.account.executer.UserUnBindBCExecuter;
import com.zc.mall.core.account.model.BankCardModel;
import com.zc.mall.core.account.service.BankCardService;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.common.constant.BaseConstant.OrderNid;
import com.zc.mall.core.common.constant.BaseConstant.QueueCode;
import com.zc.mall.core.common.executer.Executer;
import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.common.queue.work.QueueAbstract;
import com.zc.mall.core.manage.dao.OrderTaskDao;
import com.zc.mall.core.manage.model.OrderTaskModel;
import com.zc.mall.core.user.dao.UserDao;
import com.zc.mall.core.user.dao.UserIdentifyDao;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.entity.UserIdentify;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 银行卡
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Service
public class BankCardServiceImpl implements BankCardService {

    @Resource
    private BankCardDao bankCardDao;
    @Resource
    private OrderTaskDao orderTaskDao;
    @Resource
    private UserIdentifyDao userIdentifyDao;
    @Resource
    private UserDao userDao;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    public Result list(BankCardModel model) {
        PageDataList<BankCard> pageDataList = bankCardDao.list(model);
        PageDataList<BankCardModel> pageDataList_ = new PageDataList<BankCardModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<BankCardModel> list = new ArrayList<BankCardModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (BankCard bankCard : pageDataList.getList()) {
                BankCardModel model_ = BankCardModel.instance(bankCard);
                model_.setUserModel(UserModel.instance(bankCard.getUser()));
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
    public Result add(BankCardModel model) {
        model.initBind();
        bankCardDao.save(model.prototype());
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
    public Result update(BankCardModel model) {

        return null;
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getById(BankCardModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        BankCard bankCard = bankCardDao.find(model.getId());
        BankCardModel model_ = BankCardModel.instance(bankCard);
        model_.setUserModel(UserModel.instance(bankCard.getUser()));
        // model_=UserModel.instance(UserInfoModel.instance(user.getUserInfo()).getUser());
        return Result.success().setData(model_);
    }

    /**
     * 绑卡请求
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result bindBCRequest(BankCardModel model) {
        //model.checkBindBC();//绑卡校验
        model.initBind();//初始化
        BankCard bankCard = model.prototype();
        bankCardDao.save(bankCard);
        model.setId(bankCard.getId());
        model.setUser(userDao.find(model.getUser()));
        this.doBankCardQueue(model);
        return Result.success("处理中...请稍后！");
    }

    private void doBankCardQueue(BankCardModel model) {
        //绑卡发送队列
        QueueAbstract.send(StringUtil.getSerialNumber(), OrderNid.ORDER_NID_USER_BINDBC.getNid(), QueueCode.QUEUE_CODE_OTHER.getCode(), model, model.getUser());
    }

    /**
     * 绑卡处理
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result bindBCDeal(BankCardModel model) {
        BankCard bankCard = null;
        if (model.getId() > 0) {
            bankCard = bankCardDao.find(model.getId());
        } else {
            model.initBind();// 初始化参数
        }
        bankCard.setState(BaseConstant.BUSINESS_STATE_YES);
        User user = bankCard.getUser();
        UserIdentify userIdentify = userIdentifyDao.findByUser(user.getId());
        userIdentify.setBindCardNum(userIdentify.getBindCardNum() + 1);
        userIdentifyDao.update(userIdentify);
        bankCard.setUser(user);
        bankCardDao.merge(bankCard);

        //订单处理
        OrderTaskModel.dealOrder(model.getOrderTask() != null ? model.getOrderTask().getOrderNo() : "", BaseConstant.BUSINESS_STATE_YES, "绑卡成功");

        //绑定银行卡任务
        Executer bindBCExecuter = BeanUtil.getBean(UserBindBCExecuter.class);
        bindBCExecuter.execute(model);
        return Result.success();
    }

    /**
     * 换绑卡请求
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result changeBankCardRequest(BankCardModel model) {
        BankCard targetBankCard = null;// 被更换银行卡
        model.checkChangeBankCardRequest(targetBankCard);// 换绑卡校验参数

//		model.checkBindBC();// 绑卡校验参数
//		model.initBind();// 初始化参数
//		BankCard bankCard = model.prototype();
//		bankCardDao.save(bankCard);

        Executer changeBankCardRequestExecuter = BeanUtil.getBean(ChangeBankCardRequestExecuter.class);
        changeBankCardRequestExecuter.execute(model);
        return Result.success().setData(model.getPayObj());
    }

    /**
     * 换绑卡处理
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result changeBankCardDeal(BankCardModel model) {
        List<BankCard> bankCardWaitingList = bankCardDao.getByBankCardNo(model.getUser().getId(), model.getBankCardNo(), BaseConstant.BUSINESS_STATE_YES);// 根据卡号查询已绑定的银行卡
        if (bankCardWaitingList.size() > 0) {
            throw new BusinessException("该银行卡已绑定");
        }
        List<BankCard> bankCardYesList = bankCardDao.getByBankCardNoYes(model.getUser().getId());// 根据用户查询已绑定的银行卡
        if (bankCardYesList.size() <= 0) {
            throw new BusinessException("信息有误，请联系管理员");
        }

        BankCard bankCardYes = bankCardYesList.get(0);
        //绑卡处理
        QueueAbstract.send(StringUtil.getSerialNumber(), OrderNid.ORDER_NID_USER_BINDBC.getNid(), QueueCode.QUEUE_CODE_OTHER.getCode(), model, bankCardYes.getUser());
        //解绑卡处理
        QueueAbstract.send(StringUtil.getSerialNumber(), OrderNid.ORDER_NID_USER_UNBINDBC.getNid(), QueueCode.QUEUE_CODE_OTHER.getCode(), BankCardModel.instance(bankCardYes), bankCardYes.getUser());
        return Result.success("换绑卡本地处理中...请稍后！");
    }

    /**
     * 解绑卡处理
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result unBindBCDeal(BankCardModel model) {
        BankCard bankCard = bankCardDao.find(model.getId());
        bankCard.setState(BaseConstant.INFO_STATE_NO);
        bankCard.setRemark("该银行卡已被更换");

        User user = bankCard.getUser();
        UserIdentify userIdentify = userIdentifyDao.findByUser(user.getId());
        userIdentify.setBindCardNum(userIdentify.getBindCardNum() - 1);
        userIdentifyDao.update(userIdentify);

        bankCard.setUser(user);
        bankCardDao.update(bankCard);

        //解绑银行卡任务
        Executer unBindBCExecuter = BeanUtil.getBean(UserUnBindBCExecuter.class);
        unBindBCExecuter.execute(model);

        //订单处理
        OrderTaskModel.dealOrder(model.getOrderTask() != null ? model.getOrderTask().getOrderNo() : "", BaseConstant.BUSINESS_STATE_YES, "解绑卡成功");
        return Result.success();
    }

    /**
     * 通过用户查询
     *
     * @param model mobile
     * @return
     */
    @Override
    public Result getByBankCardUserId(BankCardModel model) {
        BankCard bankCard = bankCardDao.findObjByProperty("user.id", model.getUserId());
        BankCardModel model_ = BankCardModel.instance(bankCard);
        if (bankCard != null) {
            model_.setUserModel(UserModel.instance(bankCard.getUser()));
        }
        return Result.success().setData(model_);
    }

}