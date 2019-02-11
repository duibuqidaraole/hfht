package com.zc.mall.promotion.service.impl;

import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.common.constant.BaseConstant.OrderNid;
import com.zc.mall.core.common.constant.BaseConstant.QueueCode;
import com.zc.mall.core.common.queue.work.QueueAbstract;
import com.zc.mall.core.manage.model.OperatorModel;
import com.zc.mall.core.manage.model.OrderTaskModel;
import com.zc.mall.core.user.dao.UserDao;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.model.UserInfoModel;
import com.zc.mall.core.user.model.UserModel;
import com.zc.mall.promotion.dao.BonusCouponsDao;
import com.zc.mall.promotion.dao.BonusCouponsRecordDao;
import com.zc.mall.promotion.dao.UserVipCouponsDao;
import com.zc.mall.promotion.entity.BonusCoupons;
import com.zc.mall.promotion.entity.BonusCouponsRecord;
import com.zc.mall.promotion.model.BonusCouponsModel;
import com.zc.mall.promotion.model.BonusCouponsRecordModel;
import com.zc.mall.promotion.model.VipCouponsModel;
import com.zc.mall.promotion.service.BonusCouponsRecordService;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.util.log.LogUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 红包发放记录
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年12月20日
 */
@Service
public class BonusCouponsRecordServiceImpl implements BonusCouponsRecordService {

    @Resource
    private BonusCouponsRecordDao bonusCouponsRecordDao;
    @Resource
    private UserVipCouponsDao userVipCouponsDao;
    @Resource
    private BonusCouponsDao bonusCouponsDao;
    @Resource
    private UserDao userDao;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result list(BonusCouponsRecordModel model) {
        PageDataList<BonusCouponsRecord> pageDataList = bonusCouponsRecordDao.list(model);
        PageDataList<BonusCouponsRecordModel> pageDataList_ = new PageDataList<BonusCouponsRecordModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<BonusCouponsRecordModel> list = new ArrayList<BonusCouponsRecordModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (BonusCouponsRecord bonusCouponsRecord : pageDataList.getList()) {
                BonusCouponsRecordModel model_ = BonusCouponsRecordModel.instance(bonusCouponsRecord);
                model_.setUserModel(UserModel.instance(bonusCouponsRecord.getUser()));
                model_.setOperatorModel(OperatorModel.instance(bonusCouponsRecord.getOperator()));
                model_.setBeUserModel(UserModel.instance(bonusCouponsRecord.getBeUser()));
                if (bonusCouponsRecord.getBeUser() != null) {
                    model_.setBeUserInfoModel(UserInfoModel.instance(bonusCouponsRecord.getBeUser().getUserInfo()));
                }
                model_.setBonusCouponsModel(BonusCouponsModel.instance(bonusCouponsRecord.getBonusCoupons()));
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
    public Result add(BonusCouponsRecordModel model) {

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
    public Result update(BonusCouponsRecordModel model) {

        return null;
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result getById(BonusCouponsRecordModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误");
        }
        BonusCouponsRecord bonusCouponsRecord = bonusCouponsRecordDao.find(model.getId());
        BonusCouponsRecordModel model_ = BonusCouponsRecordModel.instance(bonusCouponsRecord);
        model_.setUserModel(UserModel.instance(bonusCouponsRecord.getUser()));
        model_.setOperatorModel(OperatorModel.instance(bonusCouponsRecord.getOperator()));
        model_.setBeUserModel(UserModel.instance(bonusCouponsRecord.getBeUser()));
        if (bonusCouponsRecord.getBeUser() != null) {
            model_.setBeUserInfoModel(UserInfoModel.instance(bonusCouponsRecord.getBeUser().getUserInfo()));
        }
        model_.setBonusCouponsModel(BonusCouponsModel.instance(bonusCouponsRecord.getBonusCoupons()));
        return Result.success().setData(model_);
    }

    /**
     * 奖励发放请求
     *
     * @param model
     */
    @Override
    @Transactional(noRollbackFor = BusinessException.class)
    public Result giveOutRequest(BonusCouponsRecordModel model) {
        model.checkGiveOutRequest();// 校验发放请求
        model.initGiveOutRequest();// 初始化发放请求
        //发送队列
        QueueAbstract.send(StringUtil.getSerialNumber(), OrderNid.ORDER_NID_USER_PROMOTION_GIVE_OUT_BONUS_COUPONS.getNid(), QueueCode.QUEUE_CODE_OTHER.getCode(), model, model.getUser());
        return Result.success("发放请求处理中");
    }

    /**
     * 奖励发放
     *
     * @param model
     */
    @Override
    @Transactional(noRollbackFor = BusinessException.class)
    public synchronized Result giveOut(BonusCouponsRecordModel model) {
        BonusCouponsRecord bonusCouponsRecord = model.prototype();
        model.initGiveOut(bonusCouponsRecord);// 初始化发放
        bonusCouponsRecordDao.save(bonusCouponsRecord);
        //订单处理
        OrderTaskModel.dealOrder(model.getOrderTask() != null ? model.getOrderTask().getOrderNo() : "", BaseConstant.BUSINESS_STATE_YES, "推广-发放红包处理完成");
        return Result.success();
    }

    /**
     * 使用红包处理
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result doUse(BonusCouponsRecordModel model) {
        BonusCouponsRecord bonusCouponsRecord = bonusCouponsRecordDao.find(model.getId());
        if (bonusCouponsRecord == null) {
            return Result.error("红包不存在");
        }

        model.initUse(bonusCouponsRecord);// 使用初始化
        bonusCouponsRecordDao.update(bonusCouponsRecord);
        return Result.success();
    }

    /**
     * 撤回红包使用
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result doCancelUse(BonusCouponsRecordModel model) {
        List<BonusCouponsRecord> list = bonusCouponsRecordDao.findByCriteria(model);// 查找已使用的红包
        if (list.size() <= 0) {
            LogUtil.info("BonusCouponsRecordServiceImpl.doCancelUse.list.size() = " + list.size());
            return null;
        }
        for (BonusCouponsRecord bonusCouponsRecord : list) {
            model.initCancelUse(bonusCouponsRecord);// 初始化撤回信息
            bonusCouponsRecordDao.update(bonusCouponsRecord);
        }
        return Result.success();
    }

    /**
     * 查询使用的红包
     *
     * @param model useType 使用类型 useId 关联id
     * @return
     */
    @Override
    @Transactional
    public Result getByUse(BonusCouponsRecordModel model) {
        BonusCouponsRecord bonusCouponsRecord = bonusCouponsRecordDao.getByUse(model.getUseType(), model.getUseId(), model.getState());
        if (bonusCouponsRecord != null) {
            model.setBonusCouponsModel(BonusCouponsModel.instance(bonusCouponsRecord.getBonusCoupons()));
        }
        return Result.success().setData(model);
    }

    /**
     * 红包记录过期处理
     *
     * @return
     */
    @Override
    @Transactional
    public Result doBonusRecordOverdue() {
        bonusCouponsRecordDao.doBonusRecordOverdue();
        return Result.success();
    }

    /**
     * 添加用户总佣金
     *
     * @param beUserModel
     */
    @Override
    public double setAllUserBonusToUserModel(UserModel beUserModel) {
        BonusCouponsRecordModel bonusCouponsRecordModel = new BonusCouponsRecordModel();
        bonusCouponsRecordModel.setUser(beUserModel);
        bonusCouponsRecordModel.setBounsIds("2,3");
        List<BonusCouponsRecord> list = bonusCouponsRecordDao.list(bonusCouponsRecordModel).getList();
        beUserModel.setInviteAccount(0D);
        for (BonusCouponsRecord bonusCouponsRecord : list) {
            double realAmount = bonusCouponsRecord.getRealAmount();
            realAmount += beUserModel.getInviteAccount();
            beUserModel.setInviteAccount(realAmount);
        }
        return beUserModel.getInviteAccount();
    }

    @Override
    public void setAllUserBonusToUserModel(List<UserModel> userModelList) {
        for (UserModel userModel : userModelList) {
            setAllUserBonusToUserModel(userModel);
        }
    }

    /**
     * 添加用户总佣金
     *
     * @param id
     */
    @Override
    public double getBonusRealValueByBonusRecordId(long id) {
        return bonusCouponsRecordDao.find(id) == null ? 0 : bonusCouponsRecordDao.find(id).getRealAmount();
    }

    /**
     * 发放新人奖励
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Object giveOutNewcomerReward(BonusCouponsRecordModel model) {
        checkUserNewcomer(model);
        for (String no : model.getBounsIds().split(",")) {
            BonusCoupons bonusCoupons = bonusCouponsDao.findbyCouponNo(no);
            if (bonusCoupons == null) {
                throw new BusinessException("新人奖励红包有误,请联系管理员");
            }
            BonusCouponsRecordModel bonusCouponsRecordModel = new BonusCouponsRecordModel();
            bonusCouponsRecordModel.setUserId(model.getUserId());
            bonusCouponsRecordModel.setPrizeNo(no);
            giveOutRequest(bonusCouponsRecordModel);
        }
        return Result.success("新人奖励发放成功");
    }


    /**
     * 新人奖励资格检查
     */
    @Override
    @Transactional
    public Object checkUserNewcomer(BonusCouponsRecordModel model) {
        if (model == null || StringUtil.isBlank(model.getBounsIds())) {
            throw new BusinessException("新人奖励有误");
        }
        if (model.getUserId() <= 0) {
            throw new BusinessException("新人奖励发放用户有误");
        }
        User user = userDao.find(model.getUserId());
        if (user == null) {
            throw new BusinessException("新人奖励发放用户有误");
        }
        VipCouponsModel vipCouponsModel = userVipCouponsDao.giveByUserId(user.getId());
        if (vipCouponsModel != null && vipCouponsModel.getGrade() >= 1) {
            throw new BusinessException("vip用户不能领取新人奖励");
        }
        bonusCouponsRecordDao.checkUserNewcomer(user,model.getBounsIds());
        return Result.success();
    }


}