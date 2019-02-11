package com.zc.mall.api.zc.user;

import com.alibaba.fastjson.JSONArray;
import com.zc.mall.core.account.service.AccountService;
import com.zc.mall.core.account.service.AccountStatisticsService;
import com.zc.mall.core.common.web.BaseController;
import com.zc.mall.core.integral.service.IntegralAccountService;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.model.UserModel;
import com.zc.mall.core.user.service.UserAddressService;
import com.zc.mall.core.user.service.UserRelationService;
import com.zc.mall.core.user.service.UserService;
import com.zc.mall.mall.service.OrderInfoService;
import com.zc.mall.promotion.entity.UserVipCoupons;
import com.zc.mall.promotion.entity.VipCoupons;
import com.zc.mall.promotion.model.UserVipCouponsModel;
import com.zc.mall.promotion.model.VipCouponsModel;
import com.zc.mall.promotion.service.BonusCouponsRecordService;
import com.zc.mall.promotion.service.UserVipCouponsService;
import com.zc.mall.promotion.service.VipCouponsService;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@RestController
@RequestMapping("/u/user")
public class UserController extends BaseController<UserModel> {

    @Resource
    UserService userService;
    @Resource
    UserVipCouponsService userVipCouponsService;
    @Resource
    VipCouponsService vipCouponsService;
    @Resource
    BonusCouponsRecordService bonusCouponsRecordService;
    @Resource
    UserRelationService userRelationService;
    @Resource
    AccountStatisticsService accountStatisticsService;
    @Resource
    IntegralAccountService integralAccountService;
    @Resource
    AccountService accountService;
    @Resource
    UserAddressService userAddressService;
    @Resource
    OrderInfoService orderInfoService;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(UserModel model) throws BusinessException {
        return userService.list(model);
    }

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/listWithGrade", method = RequestMethod.POST)
    @ResponseBody
    public Object listWithGrade(UserModel model) throws BusinessException {
        PageDataList<UserModel> userModelPageDataList = userService.listWithGrade(model);
        for (UserModel userModel : userModelPageDataList.getList()) {
            VipCouponsModel vipCouponsModel = userVipCouponsService.giveByUserId(userModel.getId());
            userModel.setGrade(vipCouponsModel == null ? 0 : vipCouponsModel.getGrade());
        }
        return Result.success().setData(userModelPageDataList);
    }


    /**
     * 添加
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(UserModel model) throws BusinessException {
        return userService.add(model);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(UserModel model) throws BusinessException {
        return userService.update(model);
    }

    /**
     * 修改商家
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/upDateRelationByUserId", method = RequestMethod.POST)
    @ResponseBody
    public Object upDateRelationByUserId(UserModel model) throws BusinessException {
        return userService.updateRelationByUserId(model);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(UserModel model) throws BusinessException {
        return userService.getById(model);
    }

    /**
     * 通过Id删除
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteById(UserModel model) throws BusinessException {
        return userService.deleteById(model);
    }

    /**
     * 注册
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    @ResponseBody
    public Object reg(UserModel model) throws BusinessException {
        return userService.regRequest(model);
    }

    /**
     * 登录
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(UserModel model) throws Exception {
        return userService.login(model);
    }

    /**
     * 根据微信openId登录
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/loginByOpenId", method = RequestMethod.POST)
    @ResponseBody
    public Object loginByOpenId(UserModel model) throws Exception {
        return userService.loginByOpenId(model);
    }

    /**
     * 根据微信openId判断是否注册
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/checkByOpenId", method = RequestMethod.POST)
    @ResponseBody
    public Object checkByOpenId(UserModel model) throws Exception {
        return userService.checkByOpenId(model);
    }

    /**
     * 修改登录密码
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
    @ResponseBody
    public Object updatePwd(UserModel model) throws BusinessException {
        return userService.updatePwd(model);
    }

    /**
     * 修改设置交易密码
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/updatePayPwd", method = RequestMethod.POST)
    @ResponseBody
    public Object updatePayPwd(UserModel model) throws BusinessException {
        return userService.updatePayPwd(model);
    }

    /**
     * 找回密码
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/backPwd", method = RequestMethod.POST)
    @ResponseBody
    public Object backPwd(UserModel model) throws BusinessException {
        return userService.backKeyCode(model);
    }

    /**
     * 修改登录密码
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/byMobileUpdatePwd", method = RequestMethod.POST)
    @ResponseBody
    public Object backKeyPwd(UserModel model) throws BusinessException {
        return userService.backDatePwd(model);
    }

    /**
     * 邮箱认证
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/emailState", method = RequestMethod.POST)
    @ResponseBody
    public Object emailState(UserModel model) throws BusinessException {
        return userService.emailState(model);
    }

    /**
     * 第三方登录
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/apiLogin", method = RequestMethod.POST)
    @ResponseBody
    public Object apiLogin(UserModel model) throws BusinessException {
        return userService.apiLogin(model);
    }

    /**
     * 修改手机号
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/modifyMobileRequest", method = RequestMethod.POST)
    @ResponseBody
    public Object modifyMobileRequest(UserModel model) throws BusinessException {
        return userService.modifyMobileRequest(model);
    }

    /**
     * 查询所有注册人数
     *
     * @return
     */
    @RequestMapping(value = "/findUserNumber", method = RequestMethod.POST)
    @ResponseBody
    public Object findUserNumber() throws BusinessException {
        return userService.findUserNumber();
    }

    /**
     * 查询今日所有注册人数
     *
     * @return
     */
    @RequestMapping(value = "/findUserNumberByToday", method = RequestMethod.POST)
    @ResponseBody
    public Object findUserNumberByToday() throws BusinessException {
        return userService.findUserNumberByToday();
    }

    /**
     * 通过手机号查询
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/findUserByMobile", method = RequestMethod.POST)
    @ResponseBody
    public Object findUserByMobile(UserModel model) throws BusinessException {
        return userService.findUserByMobile(model);
    }

    /**
     * 商家旗下借款人注册
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/regBorrowUserInRelation", method = RequestMethod.POST)
    @ResponseBody
    public Object regBorrowUserInRelation(UserModel model) throws BusinessException {
        return userService.regBorrowUserInRelation(model);
    }

    /**
     * 获取用户各项信息 总结信息
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getUserInfoSupplement", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public Object getUserInfoSupplement(UserModel model) throws BusinessException {
        //获取用户
        UserModel userModel = userService.getById(model);
        VipCouponsModel vipCouponsModel = userVipCouponsService.giveByUserId(model.getId());
        userModel.setGrade(vipCouponsModel == null ? 0 : vipCouponsModel.getGrade());
        //获取用户vip
        List<UserVipCoupons> list = userVipCouponsService.getByUserId(userModel.getId());
        ArrayList<UserVipCouponsModel> userVipCouponsModels = new ArrayList<>();
        for (UserVipCoupons userVipCoupon : list) {
            UserVipCouponsModel userVipCouponsModel = UserVipCouponsModel.instance(userVipCoupon);
            userVipCouponsModel.setUserModel(userModel);
            VipCoupons vipCoupons = new VipCoupons();
            vipCoupons.setId(userVipCoupon.getVipCoupons().getId());
            userVipCouponsModel.setVipCouponsModel(vipCouponsService.getById(VipCouponsModel.instance(vipCoupons)));
            userVipCouponsModels.add(userVipCouponsModel);
        }
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(userModel);
        //添加tab
        jsonArray.add(orderInfoService.getCountByState(userModel.getId()));
        jsonArray.add(userVipCouponsModels);
        //todo 顺序前台固定
        return Result.success().setData(jsonArray);
    }

    /**
     * 检查用户是否注册
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/userCheckByMobile", method = RequestMethod.POST)
    @ResponseBody
    public Object userCheckByMobile(UserModel model) throws BusinessException {
        return userService.userCheck(model);
    }

    /**
     * 获取用户及相关信息统计
     *
     * @return
     */
    @RequestMapping(value = "/getUserInfoCount", method = RequestMethod.POST)
    @ResponseBody
    public Object getUserInfoCount(Long userId) throws BusinessException {
        return userService.getUserInfoCount(userId);
    }

    /**
     * 获取用户及相关信息统计
     *
     * @return
     */
    @RequestMapping(value = "/getAllUserInfo", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public Object getAllUserInfo(UserModel model) throws BusinessException {
        //获取当前用户 即邀请人
        User user = userService.find(model.getId());
        UserModel beUserModel = UserModel.instance(user);

        //获取用户邀请人
        User toUser = userRelationService.findToUserByUserId(model.getId());
        UserModel toUserModel = UserModel.instance(toUser);
        if (toUserModel == null) {
            throw new BusinessException("用户邀请人数据有误");
        }
        //添加邀请奖励--佣金
        bonusCouponsRecordService.setAllUserBonusToUserModel(beUserModel);
        bonusCouponsRecordService.setAllUserBonusToUserModel(toUserModel);
        //获取用户信息
        List<UserModel> list = userRelationService.findUserListByToUserId(user.getId());
        toUserModel.setInviteUserCount(userRelationService.findUserListByToUserId(toUserModel.getId()).size());
        userRelationService.setInviteUserCount(list);
        //添加邀请奖励--佣金
        if (list != null && list.size() > 0) {
            bonusCouponsRecordService.setAllUserBonusToUserModel(list);
        }
        //获取用户资金信息
        accountStatisticsService.setUserPayInfo(beUserModel);
        accountStatisticsService.setUserPayInfo(toUserModel);
        if (list != null && list.size() > 0) {
            accountStatisticsService.setUserPayInfo(list);
        }
        //账户余额
        accountService.setUserAccount(beUserModel);
        accountService.setUserAccount(toUserModel);
        if (list != null && list.size() > 0) {
            accountService.setUserAccount(list);
        }
        //用户地址
        userAddressService.setUserAddress(beUserModel);
        userAddressService.setUserAddress(toUserModel);
        if (list != null && list.size() > 0) {
            userAddressService.setUserAddress(list);
        }

        //获取用户积分信息
        integralAccountService.setUserIntegral(beUserModel);
        integralAccountService.setUserIntegral(toUserModel);
        if (list != null && list.size() > 0) {
            integralAccountService.setUserIntegral(list);
        }
        //获取用户vip等级
        userVipCouponsService.setUserGrade(beUserModel);
        userVipCouponsService.setUserGrade(toUserModel);
        if (list != null && list.size() > 0) {
            userVipCouponsService.setUserGrade(list);
        }
        PageDataList<UserModel> userModelPageDataList = new PageDataList<>();
        userModelPageDataList.setList(list);
        beUserModel.setInviteUserModelList(userModelPageDataList);
        beUserModel.setInviteUserModel(toUserModel);
        return Result.success().setData(beUserModel);
    }

    /**
     * 添加生日
     *
     * @return
     */
    @RequestMapping(value = "/addBirthday", method = RequestMethod.POST)
    @ResponseBody
    public Object addBirthday(UserModel model) throws BusinessException {
        return userService.addBirthday(model);
    }

    /**
     * 添加地址
     *
     * @return
     */
    @RequestMapping(value = "/addAddress", method = RequestMethod.POST)
    @ResponseBody
    public Object addAddress(UserModel model) throws BusinessException {
        return userService.addAddress(model);
    }

    /**
     * 添加需求
     *
     * @return
     */
    @RequestMapping(value = "/addDemands", method = RequestMethod.POST)
    @ResponseBody
    public Object addDemands(UserModel model) throws BusinessException {
        return userService.addDemands(model);
    }

    /**
     * 绑定手机号
     *
     * @return
     */
    @RequestMapping(value = "/bindMobile", method = RequestMethod.POST)
    @ResponseBody
    public Object bindMobile(UserModel model) throws BusinessException {
        return userService.bindMobile(model);
    }
}
