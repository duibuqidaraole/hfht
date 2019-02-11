package com.zc.mall.api.zc.user;

import com.zc.mall.core.account.service.AccountStatisticsService;
import com.zc.mall.core.common.web.BaseController;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.model.UserModel;
import com.zc.mall.core.user.model.UserRelationModel;
import com.zc.mall.core.user.service.UserRelationService;
import com.zc.mall.promotion.service.BonusCouponsRecordService;
import com.zc.mall.promotion.service.UserVipCouponsService;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户关系
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2018年03月15日
 */
@RestController
@RequestMapping("/u/userrelation")
public class UserRelationController extends BaseController<UserRelationModel> {

    @Resource
    UserRelationService userRelationService;
    @Resource
    UserVipCouponsService userVipCouponsService;
    @Resource
    BonusCouponsRecordService bonusCouponsRecordService;
    @Resource
    AccountStatisticsService accountStatisticsService;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Transactional
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(UserRelationModel model) throws BusinessException {
        return userRelationService.list(model);
    }

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Transactional
    @RequestMapping(value = "/simpleList", method = RequestMethod.POST)
    @ResponseBody
    public Object simpleList(UserRelationModel model) throws BusinessException {
        return userRelationService.simpleList(model);
    }


    /**
     * 添加
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(UserRelationModel model) throws BusinessException {
        return userRelationService.add(model);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(UserRelationModel model) throws BusinessException {
        return userRelationService.update(model);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(UserRelationModel model) throws BusinessException {
        return userRelationService.getById(model);
    }

    /**
     * 取消关注商家
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteById(UserRelationModel model) throws BusinessException {
        return userRelationService.deleteById(model);
    }


    /**
     * 通过用户和商家查询用户关系存不存在
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/findUserRelationByUserIdAndToBeUserId", method = RequestMethod.POST)
    @ResponseBody
    public Object findUserRelationByUserIdAndToBeUserId(UserRelationModel model) throws BusinessException {
        return userRelationService.findUserRelationByUserIdAndToBeUserId(model);
    }

    /**
     * 通过商家id和用户与商家之间的关系Type查询用户列表
     *
     * @param model
     * @return Object
     */
    @RequestMapping(value = "/findUserByToUserIdAndType", method = RequestMethod.POST)
    @ResponseBody
    public Object findUserByToUserIdAndType(UserRelationModel model) throws BusinessException {
        return userRelationService.findUserByToUserIdAndType(model);
    }

    /**
     * 统计商家旗下今日注册人数
     *
     * @param model
     * @return UserRelation
     */
    @RequestMapping(value = "/countUserNumber", method = RequestMethod.POST)
    @ResponseBody
    public Object countUserNumber(UserRelationModel model) {
        return userRelationService.countUserNumber(model);
    }

    /**
     * 统计用户及分销奖励
     *
     * @param model
     * @return UserRelation
     */
    @RequestMapping(value = "/userInfoAboutAccount", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public Object userInfoAboutAccount(UserRelationModel model) {
        //获取当前用户 即邀请人
        User beUser = model.getUser();
        UserModel beUserModel = UserModel.instance(beUser);
        //添加邀请奖励
        bonusCouponsRecordService.setAllUserBonusToUserModel(beUserModel);
        //获取用户信息
        List<UserModel> list = userRelationService.findUserListByToUserId(beUser.getId());
        //获取用户佣金信息
        accountStatisticsService.setUserPayInfo(list);
        //获取用户等级
        userVipCouponsService.setUserGrade(list);
        PageDataList<UserModel> userModelPageDataList = new PageDataList<>();
        userModelPageDataList.setList(list);
        beUserModel.setInviteUserModelList(userModelPageDataList);
        return Result.success().setData(beUserModel);
    }

}