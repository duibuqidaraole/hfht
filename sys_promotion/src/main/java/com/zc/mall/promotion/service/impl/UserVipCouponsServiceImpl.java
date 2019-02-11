package com.zc.mall.promotion.service.impl;

import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.common.pay.wx.WxPayClient;
import com.zc.mall.core.common.queue.work.QueueAbstract;
import com.zc.mall.core.manage.model.OrderTaskModel;
import com.zc.mall.core.user.dao.UserDao;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.model.UserModel;
import com.zc.mall.core.user.service.UserRelationService;
import com.zc.mall.promotion.dao.UserVipCouponsDao;
import com.zc.mall.promotion.dao.VipCouponsDao;
import com.zc.mall.promotion.entity.PromotionPrizeRecord;
import com.zc.mall.promotion.entity.UserVipCoupons;
import com.zc.mall.promotion.entity.VipCoupons;
import com.zc.mall.promotion.model.BonusCouponsRecordModel;
import com.zc.mall.promotion.model.UserVipCouponsModel;
import com.zc.mall.promotion.model.VipCouponsModel;
import com.zc.mall.promotion.service.BonusCouponsRecordService;
import com.zc.mall.promotion.service.UserVipCouponsService;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用户vip会员卡
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月15日
 */
@Service
public class UserVipCouponsServiceImpl implements UserVipCouponsService {

    @Resource
    private UserVipCouponsDao userVipCouponsDao;
    @Resource
    private UserDao userDao;
    @Resource
    private VipCouponsDao vipCouponsDao;
    @Resource
    private UserRelationService userRelationService;
    @Resource
    private  BonusCouponsRecordService bonusCouponsRecordService;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result list(UserVipCouponsModel model) {
        PageDataList<UserVipCoupons> pageDataList = userVipCouponsDao.list(model);
        PageDataList<UserVipCouponsModel> pageDataList_ = new PageDataList<>();
        pageDataList_.setPage(pageDataList.getPage());
        List<UserVipCouponsModel> list = new ArrayList<>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (UserVipCoupons userVipCoupons : pageDataList.getList()) {
                UserVipCouponsModel model_ = UserVipCouponsModel.instance(userVipCoupons);
                model_.setUserModel(UserModel.instance(userVipCoupons.getUser()));
                model_.setVipCouponsModel(VipCouponsModel.instance(userVipCoupons.getVipCoupons()));
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
    public Result add(UserVipCouponsModel model) {
        this.checkAdd(model);
        this.initAdd(model);
        userVipCouponsDao.save(model.prototype());
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
    public Result update(UserVipCouponsModel model) {
        UserVipCoupons userVipCoupons = this.checkUpdate(model);
        this.initUpdate(model, userVipCoupons);
        userVipCouponsDao.update(model.prototype());
        return Result.success();
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getById(UserVipCouponsModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        UserVipCoupons userVipCoupons = userVipCouponsDao.find(model.getId());
        if (userVipCoupons == null) {
            return Result.error("参数错误！");
        }
        return Result.success().setData(UserVipCouponsModel.instance(userVipCoupons));
    }

    /**
     * 通过用户id获取vip卡
     *
     * @param id
     * @return
     */
    @Override
    public List<UserVipCoupons> getByUserId(long id) {
        return userVipCouponsDao.getByUserId(id);


    }

    /**
     * 校验
     *
     * @param model
     */
    private void checkAdd(UserVipCouponsModel model) {
        checkUser(model);
        checkVipCoupon(model);
    }

    private void checkVipCoupon(UserVipCouponsModel model) {
        if (model.getVipCoupons() == null || model.getVipCoupons().getId() <= 0) {
            throw new BusinessException("vip信息有误");
        }
        VipCoupons vipCoupons = vipCouponsDao.find(model.getVipCoupons().getId());
        if (vipCoupons == null) {
            throw new BusinessException("vip信息不存在");
        }
    }

    private void checkUser(UserVipCouponsModel model) {
        if (model.getUser() == null || model.getUser().getId() <= 0) {
            throw new BusinessException("用户信息有误");
        }
        User user = userDao.find(model.getUser().getId());
        if (user == null) {
            throw new BusinessException("用户信息不存在");
        }
    }


    /**
     * 初始化
     *
     * @param model
     */
    private void initAdd(UserVipCouponsModel model) {
        model.setAddTime(DateUtil.getNow());
    }

    /**
     * 校验
     *
     * @param model
     * @return
     */
    private UserVipCoupons checkUpdate(UserVipCouponsModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误！");
        }
        UserVipCoupons userVipCoupons = userVipCouponsDao.find(model.getId());
        if (userVipCoupons == null) {
            throw new BusinessException("参数错误！");
        }
        return userVipCoupons;
    }

    /**
     * 初始化
     *
     * @param model
     */
    private void initUpdate(UserVipCouponsModel model, UserVipCoupons userVipCoupons) {
        if (model.getVipCoupons() == null) {
            model.setVipCoupons(userVipCoupons.getVipCoupons());
        }
        if (model.getUser() == null) {
            model.setUser(userVipCoupons.getUser());
        }
        model.setAddTime(userVipCoupons.getAddTime());
        if (model.getState() == 0) {
            model.setState(userVipCoupons.getState());
        }
        if (model.getBeginTime() == null) {
            model.setBeginTime(userVipCoupons.getBeginTime());
        }
        if (model.getEndTime() == null) {
            model.setEndTime(userVipCoupons.getEndTime());
        }
    }

    /**
     * 奖励发放请求
     *
     * @param model
     */
    @Override
    @Transactional
    public Result giveOutRequest(UserVipCouponsModel model) {
        model.checkGiveOutRequest();// 校验发放请求
        model.initGiveOutRequest();// 初始化发放请求
        sendVipReward(model);
        //发送队列
        QueueAbstract.send(StringUtil.getSerialNumber(), BaseConstant.OrderNid.ORDER_NID_USER_PROMOTION_GIVE_OUT_USER_VIP_COUPONS.getNid(), BaseConstant.QueueCode.QUEUE_CODE_OTHER.getCode(), model, model.getUser());
        //sendToInviteUser(model);
        return Result.success("发放请求处理中");
    }

    /**
     * 发放vip奖励
     * @param model
     */
    private void sendVipReward(UserVipCouponsModel model) {
        BonusCouponsRecordModel bonusCouponsRecordModel = new BonusCouponsRecordModel();
        bonusCouponsRecordModel.setUserId(model.getUser().getId());
        bonusCouponsRecordModel.setPrizeNo("1812030143748199");
        bonusCouponsRecordService.giveOutRequest(bonusCouponsRecordModel);
    }

    /**
     * 奖励发放请求
     *
     * @param model
     */
    @Transactional
    public Result giveOutRequestOnly(UserVipCouponsModel model) {
        model.checkGiveOutRequest();// 校验发放请求
        model.initGiveOutRequest();// 初始化发放请求
        //发送队列
        QueueAbstract.send(StringUtil.getSerialNumber(), BaseConstant.OrderNid.ORDER_NID_USER_PROMOTION_GIVE_OUT_USER_VIP_COUPONS.getNid(), BaseConstant.QueueCode.QUEUE_CODE_OTHER.getCode(), model, model.getUser());
        return Result.success("发放请求处理中");
    }

    /**
     * 给邀请人发放vip红包
     *
     * @param model
     */
    private void sendToInviteUser(UserVipCouponsModel model) {
        User inviteUser = userRelationService.findToUserByUserId(model.getUser().getId());
        if (inviteUser != null && inviteUser.getId() != 1) {
            VipCouponsModel vipCouponsModel = userVipCouponsDao.giveByUserId(inviteUser.getId());
            if (vipCouponsModel == null || vipCouponsModel.getGrade() <= 1) {
                UserVipCouponsModel userVipCouponsModel = new UserVipCouponsModel();
                userVipCouponsModel.setUser(inviteUser);
                userVipCouponsModel.setVipCoupons(vipCouponsDao.find(2L));
                giveOutRequestOnly(userVipCouponsModel);
            }
        }
    }

    /**
     * 发放vip
     *
     * @param prizeRecord
     * @return
     */
    @Override
    public Result giveOutRequest(PromotionPrizeRecord prizeRecord) {
        UserVipCouponsModel userVipCouponsModel = new UserVipCouponsModel(prizeRecord.getPromotionPrizeConfig().getPrizeNo(), prizeRecord.getUser());
        this.giveOutRequest(userVipCouponsModel);
        return Result.success();
    }

    /**
     * vip发放
     *
     * @param model
     */
    @Override
    @Transactional(noRollbackFor = BusinessException.class)
    public synchronized Result giveOut(UserVipCouponsModel model) {
        UserVipCoupons userVipCoupons = model.prototype();
        // 初始化发放
        model.initGiveOut(userVipCoupons);
        userVipCouponsDao.save(userVipCoupons);
        //订单处理
        OrderTaskModel.dealOrder(model.getOrderTask() != null ? model.getOrderTask().getOrderNo() : "", BaseConstant.BUSINESS_STATE_YES, "推广-发放vip红包处理完成");
        return Result.success();
    }

    /**
     * 根据用户获取最高的vip
     *
     * @param userId
     * @return
     */
    @Override
    public VipCouponsModel giveByUserId(long userId) {
        return userVipCouponsDao.giveByUserId(userId);
    }

    /**
     * 添加用户等级信息
     *
     * @param list
     */
    @Override
    public void setUserGrade(List<UserModel> list) {
        for (UserModel userModel : list) {
            setUserGrade(userModel);
        }
    }

    /**
     * 添加用户等级信息
     *
     * @param model
     */
    @Override
    public void setUserGrade(UserModel model) {
        if (userVipCouponsDao.giveByUserId(model.getId()) == null) {
            model.setGrade(0);
        } else {
            model.setGrade(userVipCouponsDao.giveByUserId(model.getId()).getGrade());
        }
    }

    /**
     * 发放vip付款请求
     *
     * @param model
     * @return
     */
    @Override
    public Object giveOutPay(UserVipCouponsModel model) {
        try {
            //微信请求
            checkGiveOutPay(model);
            Map<String, String> payData = WxPayClient.unifiedorder(model.getUser().getOpenId(), StringUtil.getSerialNumber(), "发放红包", model.getVipCoupons().getPrince());
            return payData;
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    private void checkGiveOutPay(UserVipCouponsModel model) {
        if (StringUtil.isBlank(model.getUser().getOpenId())) {
            throw new BusinessException("请传入用户信息");
        }
    }

    /**
     * 根据用户vip折扣值
     *
     * @param id
     * @return
     */
    public double getVipValueByUserId(long id) {
        return userVipCouponsDao.getVipValueByUserId(id);
    }

}