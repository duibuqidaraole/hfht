package com.zc.mall.core.user.service.impl;

import com.zc.mall.core.account.dao.BankCardDao;
import com.zc.mall.core.account.model.BankCardModel;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.common.constant.BaseConstant.OrderNid;
import com.zc.mall.core.common.constant.BaseConstant.QueueCode;
import com.zc.mall.core.common.executer.Executer;
import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.common.global.Global;
import com.zc.mall.core.common.queue.work.QueueAbstract;
import com.zc.mall.core.manage.dao.OrderTaskDao;
import com.zc.mall.core.manage.model.OrderTaskModel;
import com.zc.mall.core.sys.constant.ConfigParamConstant;
import com.zc.mall.core.user.dao.UserDao;
import com.zc.mall.core.user.dao.UserIdentifyDao;
import com.zc.mall.core.user.dao.UserInfoDao;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.entity.UserIdentify;
import com.zc.mall.core.user.executer.UserRealNameExecuter;
import com.zc.mall.core.user.executer.UserRealNameRequestArtifExecuter;
import com.zc.mall.core.user.model.UserIdentifyModel;
import com.zc.mall.core.user.model.UserModel;
import com.zc.mall.core.user.service.UserIdentifyService;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户认证
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Service
public class UserIdentifyServiceImpl implements UserIdentifyService {

    @Resource
    private UserIdentifyDao userIdentifyDao;
    @Resource
    private OrderTaskDao orderTaskDao;
    @Resource
    private UserDao userDao;
    @Resource
    private UserInfoDao userInfoDao;
    @Resource
    private BankCardDao bankCardDao;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    public Result list(UserIdentifyModel model) {
        PageDataList<UserIdentify> pageDataList = userIdentifyDao.list(model);
        PageDataList<UserIdentifyModel> pageDataList_ = new PageDataList<UserIdentifyModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<UserIdentifyModel> list = new ArrayList<UserIdentifyModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (UserIdentify userIdentify : pageDataList.getList()) {
                UserIdentifyModel model_ = UserIdentifyModel.instance(userIdentify);
                model_.setUserModel(UserModel.instance(userIdentify.getUser()));
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
    public Result add(UserIdentifyModel model) {

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
    public Result update(UserIdentifyModel model) {

        return null;
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getById(UserIdentifyModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        UserIdentify userIdentify = userIdentifyDao.find(model.getId());
        UserIdentifyModel model_ = UserIdentifyModel.instance(userIdentify);
        model_.setUserModel(UserModel.instance(userIdentify.getUser()));
        return Result.success().setData(model_);
    }

    /**
     * 实名请求
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Object realNameRequest(UserIdentifyModel model) {
        // 实名校验
        model.checkRealName(model);
        UserIdentify userIdentify = (UserIdentify) userIdentifyDao.findObjByProperty("user.id", model.getUserId());
        if (userIdentify.getRealNameCount() > Global.getInt(ConfigParamConstant.SYS_PARAM_REAL_NAME_COUNT)) {
            return Result.error("已达到实名认证次数上限，请联系平台处理");
        }

        model.setUser(userIdentify.getUser());
        model.setId(userIdentify.getId());
        // 实名请求任务
//		 Executer realNameRequestExecuter = BeanUtil.getBean(UserRealNameRequestExecuter.class);
//		 realNameRequestExecuter.execute(model);
        new BankCardModel().initAndSaveBCModel(userIdentify.getUser(), model.getBankCardNo(), model.getMobile(), "", "", "");
        // this.realNameAndBindCardRequest(model, bcModel);
        doRealNameDealQueue(model);
        return Result.success("实名处理中...请稍后！");
    }

    private void doRealNameDealQueue(UserIdentifyModel model) {
        // 实名发送队列
        QueueAbstract.send(StringUtil.getSerialNumber(), OrderNid.ORDER_NID_USER_REALNAME.getNid(),
                QueueCode.QUEUE_CODE_OTHER.getCode(), model, model.getUser());
    }

    /**
     * 法人实名请求
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Object realNameArtifRequest(UserIdentifyModel model) {
        model.checkRealNameArtif();// 实名校验
        UserIdentify userIdentify = (UserIdentify) userIdentifyDao.findObjByProperty("user.id", model.getUserId());
        if (userIdentify.getRealNameCount() > Global.getInt(ConfigParamConstant.SYS_PARAM_REAL_NAME_COUNT)) {
            return Result.error("已达到实名认证次数上限，请联系平台处理");
        }
        model.setUser(userIdentify.getUser());

        // 实名请求任务
        Executer realNameRequestArtifExecuter = BeanUtil.getBean(UserRealNameRequestArtifExecuter.class);
        realNameRequestArtifExecuter.execute(model);
        return Result.success().setData(model.getPayReg());
    }


    /**
     * 实名处理
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Object realNameDeal(UserIdentifyModel model) {
        UserIdentify userIdentify = userIdentifyDao.findByUser(model.getUserId());
        model.checkRealNameDeal(userIdentify);
        model.initRealName(userIdentify);
        userIdentifyDao.update(userIdentify);
        UserIdentifyModel iModel = UserIdentifyModel.instance(userIdentify);
        // 实名任务
        Executer realNameExecuter = BeanUtil.getBean(UserRealNameExecuter.class);
        realNameExecuter.execute(iModel);
        // 订单处理
        OrderTaskModel.dealOrder(model.getOrderTask() != null ? model.getOrderTask().getOrderNo() : "",
                iModel.getRealNameState(), iModel.getRemark());

        // 认证失败
        if (iModel.getRealNameState() != BaseConstant.BUSINESS_STATE_YES) {
            userIdentify.setRealNameState(model.getRealNameState());
            userIdentifyDao.update(userIdentify);
            return Result.error(iModel.getRemark());
        }

        return Result.success("实名绑卡成功了！");
    }

    /**
     * 数据魔盒-运营商-数据认证请求
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Object octopusRequest(UserIdentifyModel model) {

        return null;
    }

    /**
     * 数据魔盒-运营商-数据认证处理
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Object octopusDeal(UserIdentifyModel model) {

        return null;
    }

    /**
     * 实名+绑卡请求
     *
     * @param model
     * @return
     */
    @Override
    public Object realNameAndBindCardRequest(UserIdentifyModel model, BankCardModel bcModel) {
        // 实名发送队列
        QueueAbstract.send(StringUtil.getSerialNumber(), OrderNid.ORDER_NID_USER_REALNAME.getNid(),
                QueueCode.QUEUE_CODE_OTHER.getCode(), model, model.getUser());
        // 绑卡发送队列
        QueueAbstract.send(StringUtil.getSerialNumber(), OrderNid.ORDER_NID_USER_BINDBC.getNid(),
                QueueCode.QUEUE_CODE_OTHER.getCode(), bcModel, bcModel.getUser());
        return Result.success();
    }

    /**
     * 通过用户ID获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getByUserId(UserIdentifyModel model) {
        if (model.getUserId() <= 0) {
            return Result.error("参数错误！");
        }
        UserIdentify userIdentify = userIdentifyDao.findByUser(model.getUserId());
        UserIdentifyModel model_ = UserIdentifyModel.instance(userIdentify);
        if (userIdentify.getUser() != null)
            model_.setUserModel(UserModel.instance(userIdentify.getUser()));
        return Result.success().setData(model_);
    }

    /**
     * 实名测试
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Object realNameTest(UserIdentifyModel model) {
        // 实名校验
        model.checkRealName(model);
        UserIdentify userIdentify = (UserIdentify) userIdentifyDao.findObjByProperty("user.id", model.getUserId());
        if (userIdentify.getRealNameCount() > Global.getInt(ConfigParamConstant.SYS_PARAM_REAL_NAME_COUNT)) {
            return Result.error("已达到实名认证次数上限，请联系平台处理");
        } else {
            userIdentify.setRealNameCount(userIdentify.getRealNameCount() + 1);
        }
        User user = userIdentify.getUser();
        user.setRealName(model.getRealName());
        user.setCardNo(model.getCardNo());
        userIdentify.setUser(user);

        BankCardModel bankCardModel = new BankCardModel().initAndSaveBCModel(userIdentify.getUser(), model.getBankCardNo(), model.getMobile(), "", "", "");
        userIdentify.setBindCardNum(userIdentify.getBindCardNum() + 1);
        userIdentify.setRealNameState(1);
        userIdentifyDao.update(userIdentify);
        return Result.success("实名成功！");
    }
}