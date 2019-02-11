package com.zc.mall.promotion.service;

import com.zc.mall.core.user.model.UserModel;
import com.zc.mall.promotion.model.BonusCouponsRecordModel;
import com.zc.sys.common.form.Result;

import java.util.List;

/**
 * 红包发放记录
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年12月20日
 */
public interface BonusCouponsRecordService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(BonusCouponsRecordModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(BonusCouponsRecordModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(BonusCouponsRecordModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(BonusCouponsRecordModel model);

    /**
     * 奖励发放请求
     *
     * @param model
     */
    public Result giveOutRequest(BonusCouponsRecordModel model);

    /**
     * 奖励发放
     *
     * @param model
     */
    public Result giveOut(BonusCouponsRecordModel model);

    /**
     * 使用红包处理
     *
     * @param model
     * @return
     */
    public Result doUse(BonusCouponsRecordModel model);

    /**
     * 撤回红包使用
     *
     * @param model
     * @return
     */
    public Result doCancelUse(BonusCouponsRecordModel model);

    /**
     * 发放奖励组合接口
     *
     * @param model
     * @return
     */
    public Result getByUse(BonusCouponsRecordModel model);

    /**
     * 红包记录过期处理
     *
     * @return
     */
    public Result doBonusRecordOverdue();

    /**
     * 添加用户总佣金
     *
     * @param beUserModel
     */
    double setAllUserBonusToUserModel(UserModel beUserModel);

    void setAllUserBonusToUserModel(List<UserModel> userModelList);

    /**
     * 添加用户总佣金
     *
     * @param id
     */
    double getBonusRealValueByBonusRecordId(long id);

    /**
     * 发放新人奖励
     *
     * @param model
     * @return
     */
    Object giveOutNewcomerReward(BonusCouponsRecordModel model);

    /**
     * 校验新人奖励资格
     * @param model
     * @return
     */
    Object checkUserNewcomer(BonusCouponsRecordModel model);
}