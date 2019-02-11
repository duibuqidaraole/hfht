package com.zc.mall.core.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zc.mall.core.account.dao.AccountDao;
import com.zc.mall.core.account.dao.BankCardDao;
import com.zc.mall.core.account.entity.Account;
import com.zc.mall.core.account.entity.BankCard;
import com.zc.mall.core.account.model.AccountModel;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.common.constant.BaseConstant.OrderNid;
import com.zc.mall.core.common.constant.BaseConstant.QueueCode;
import com.zc.mall.core.common.executer.Executer;
import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.common.model.CommonModel;
import com.zc.mall.core.common.queue.work.QueueAbstract;
import com.zc.mall.core.common.service.CommonService;
import com.zc.mall.core.credit.dao.CreditScoreDao;
import com.zc.mall.core.credit.entity.CreditScore;
import com.zc.mall.core.credit.model.CreditScoreModel;
import com.zc.mall.core.integral.dao.IntegralAccountDao;
import com.zc.mall.core.integral.entity.IntegralAccount;
import com.zc.mall.core.integral.model.IntegralAccountModel;
import com.zc.mall.core.manage.model.NoticeMessageModel;
import com.zc.mall.core.manage.model.OrderTaskModel;
import com.zc.mall.core.manage.service.NoticeMessageService;
import com.zc.mall.core.user.dao.UserDao;
import com.zc.mall.core.user.dao.UserIdentifyDao;
import com.zc.mall.core.user.dao.UserInfoDao;
import com.zc.mall.core.user.dao.UserRelationDao;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.entity.UserIdentify;
import com.zc.mall.core.user.entity.UserInfo;
import com.zc.mall.core.user.entity.UserRelation;
import com.zc.mall.core.user.executer.*;
import com.zc.mall.core.user.model.UserIdentifyModel;
import com.zc.mall.core.user.model.UserInfoModel;
import com.zc.mall.core.user.model.UserModel;
import com.zc.mall.core.user.service.UserAddressService;
import com.zc.mall.core.user.service.UserRelationService;
import com.zc.mall.core.user.service.UserService;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import com.zc.sys.common.model.jpa.SearchFilter;
import com.zc.sys.common.model.jpa.SearchFilter.Operators;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.encrypt.MD5;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Resource
    private UserInfoDao userInfoDao;
    @Resource
    private UserIdentifyDao userIdentifyDao;
    @Resource
    private UserAddressService userAddressService;
    @Resource
    private CommonService commonService;

    /**
     * 账户*
     */
    @Resource
    private AccountDao accountDao;
    @Resource
    private UserRelationDao userRelationDao;
    @Resource
    private NoticeMessageService noticeMessageService;

    /**
     * 积分
     */
    @Resource
    private IntegralAccountDao integralAccountDao;
    @Resource
    private CreditScoreDao creditScoreDao;
    /**
     * 银行卡
     */
    @Resource
    private BankCardDao bankCardDao;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result list(UserModel model) {
        PageDataList<User> pageDataList = userDao.list(model);
        PageDataList<UserModel> pageDataList_ = new PageDataList<UserModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<UserModel> list = new ArrayList<UserModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (User user : pageDataList.getList()) {
                UserModel model_ = UserModel.instance(user);
                UserInfoModel userInfoModel = UserInfoModel.instance(user.getUserInfo());
                if (userInfoModel != null) {
                    userInfoModel.setUserModel(UserModel.instance(userInfoModel.getUser()));
                }

                if (userInfoModel != null) {
                    userInfoModel.setInviteUserModel(UserModel.instance(userInfoModel.getInviteUser()));
                }
                model_.setInfoModel(userInfoModel);
                model_.setUserIdentifyModel(UserIdentifyModel.instance(user.getUserIdentify()));
                list.add(model_);
            }
        }
        pageDataList_.setList(list);
        return Result.success().setData(pageDataList_);
    }


    /**
     * 带等级用户列表
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public PageDataList<UserModel> listWithGrade(UserModel model) {
        PageDataList<User> pageDataList = userDao.list(model);
        PageDataList<UserModel> pageDataList_ = new PageDataList<UserModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<UserModel> list = new ArrayList<UserModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (User user : pageDataList.getList()) {
                UserModel model_ = UserModel.instance(user);
                UserInfoModel userInfoModel = UserInfoModel.instance(user.getUserInfo());
                if (userInfoModel != null) {
                    userInfoModel.setUserModel(UserModel.instance(userInfoModel.getUser()));
                }

                if (userInfoModel != null) {
                    userInfoModel.setInviteUserModel(UserModel.instance(userInfoModel.getInviteUser()));
                }
                model_.setInfoModel(userInfoModel);
                model_.setUserIdentifyModel(UserIdentifyModel.instance(user.getUserIdentify()));
                userAddressService.setUserAddress(model_);
                list.add(model_);
            }
        }
        pageDataList_.setList(list);
        return pageDataList_;
    }

    /**
     * 根据微信openId判断是否注册
     *
     * @param model
     * @return
     */
    @Override
    public Object checkByOpenId(UserModel model) {
        User user = userDao.findByOpenId(model.getOpenId());
        if (user != null) {
            return Result.success().setData(user);
        }
        return Result.error();
    }

    /**
     * 添加修改生日
     *
     * @param model
     * @return
     */
    @Override
    public Object addBirthday(UserModel model) {
        if (model.getUserInfo() == null || model.getUserInfo().getBirthday() == null) {
            throw new BusinessException("用户信息有误");
        }
        User user = userDao.find(model.getId());
        if (user == null) {
            throw new BusinessException("用户信息有误");
        }
        UserInfo userInfo = user.getUserInfo();
        if (userInfo != null) {
            userInfo.setBirthday(model.getUserInfo().getBirthday());
        }
        user.setUserInfo(userInfo);
        userInfoDao.update(userInfo);
        UserModel userModel = UserModel.instance(user);
        userModel.setInfoModel(UserInfoModel.instance(userInfo));
        return Result.success().setData(userModel);
    }

    /**
     * 添加地址
     *
     * @param model
     * @return
     */
    @Override
    public Object addAddress(UserModel model) {
        if (model.getUserInfo() == null || model.getUserInfo().getCity() == null || model.getUserInfo().getProvince() == null || model.getUserInfo().getArea() == null) {
            throw new BusinessException("用户信息有误");
        }
        User user = userDao.find(model.getId());
        if (user == null) {
            throw new BusinessException("用户信息有误");
        }
        UserInfo userInfo = user.getUserInfo();
        if (userInfo != null) {
            userInfo.setCity(model.getUserInfo().getCity());
            userInfo.setProvince(model.getUserInfo().getProvince());
            userInfo.setArea(model.getUserInfo().getArea());
        }
        user.setUserInfo(userInfo);
        userInfoDao.update(userInfo);
        UserModel userModel = UserModel.instance(user);
        userModel.setInfoModel(UserInfoModel.instance(userInfo));
        return Result.success().setData(userModel);
    }

    /**
     * 添加用户需求
     *
     * @param model
     * @return
     */
    @Override
    public Object addDemands(UserModel model) {
        if (model.getUserInfo() == null || model.getUserInfo().getDemands() == null) {
            throw new BusinessException("用户信息有误");
        }
        User user = userDao.find(model.getId());
        if (user == null) {
            throw new BusinessException("用户信息有误");
        }
        UserInfo userInfo = user.getUserInfo();
        if (userInfo != null) {
            userInfo.setDemands(model.getUserInfo().getDemands());
        }
        user.setUserInfo(userInfo);
        userInfoDao.update(userInfo);
        UserModel userModel = UserModel.instance(user);
        userModel.setInfoModel(UserInfoModel.instance(userInfo));
        return Result.success().setData(userModel);
    }

    /**
     * 绑定手机号
     *
     * @param model
     * @return
     */
    @Override
    public Object bindMobile(UserModel model) {
        if(model==null||model.getId()<=0){
            throw new BusinessException("绑定失败，用户信息有误");
        }
        User user = userDao.find(model.getId());
        if (user==null){
            throw new BusinessException("绑定失败，用户信息有误");
        }
        if (StringUtil.isBlank(model.getMobile())||!StringUtil.isPhone(model.getMobile())){
            throw new BusinessException("绑定失败，请输入正确手机号码");
        }
        commonService.checkMobileCode(model.getMobile(),model.getMobileCode(),1);
        user.setMobile(model.getMobile());
        return Result.success("绑定成功").setData(UserModel.instance(userDao.update(user)));
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result add(UserModel model) {
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
    public Result update(UserModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        User user = userDao.find(model.getId());
        userDao.update(model.setUpdateUser(user));
        return Result.success();
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public UserModel getById(UserModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误");
        }
        User user = userDao.find(model.getId());
        UserModel model_ = UserModel.instance(user);
        if (user != null) {
            UserInfoModel userInfoModel = UserInfoModel.instance(user.getUserInfo());
            IntegralAccount integralAccount = integralAccountDao.findByUserId(user.getId());
            double totalIntegral = integralAccount.getTotalIntegral();
            userInfoModel.setUserTotleIntegralAcount(totalIntegral);
            QueryParam qp = QueryParam.getInstance();
            qp.addParam("user.id", user.getId());
            BankCard bankCard = bankCardDao.findByCriteriaForUnique(qp);
            if (StringUtil.isBlank(bankCard)) {
                model_.setBankCardNo("");
                model_.setBankCardName("");
            } else {
                if (StringUtil.isBlank(bankCard.getBankName())) {
                    model_.setBankCardNo(bankCard.getBankName());
                }
                if (StringUtil.isBlank(bankCard.getBankCardNo())) {
                    model_.setBankCardName(bankCard.getBankCardNo());
                }
            }
            setinviteInfo(model_);
            model_.setInfoModel(userInfoModel);
            model_.setAccountModel(AccountModel.instance(accountDao.findByUser(user.getId())));
            model_.setIntegralAccountModel(IntegralAccountModel.instance(integralAccountDao.findByUserId(user.getId())));
            model_.setUserIdentifyModel(UserIdentifyModel.instance(user.getUserIdentify()));
        }
        return model_;
    }

    /**
     * 添加用户邀请人信息
     *
     * @param model_
     */
    private void setinviteInfo(UserModel model_) {
        PageDataList<UserRelation> userRelationPageDataListList = userRelationDao.findUserListByToUserId(model_.getId());
        PageDataList<UserModel> userPageDataList = new PageDataList<>();
        ArrayList<UserModel> userModels = new ArrayList<>();
        for (UserRelation userRelation : userRelationPageDataListList.getList()) {
            userModels.add(UserModel.instance(userRelation.getUser()));
        }
        userPageDataList.setList(userModels);
        userPageDataList.setPage(userRelationPageDataListList.getPage());
        model_.setInviteUserModelList(userPageDataList);
        model_.setInviteUser(UserModel.instance(userRelationDao.findToUserByUserId(model_.getId())));
    }

    /**
     * 注册
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result regRequest(UserModel model) {
        // 注册信息校验
        model.checkReg();
        // 初始化注册
        model.initReg();
        User user = model.prototype();
        // 用户基本信息
        UserInfoModel infoModel = new UserInfoModel();
        infoModel.setUser(user);
        infoModel.initReg(model);
        UserInfo userInfo = infoModel.prototype();
        userInfoDao.save(userInfo);
        model.setInfoModel(infoModel);
        // 邀请信息
        UserRelationService userRelationService = BeanUtil.getBean(UserRelationService.class);
        userRelationService.regInvite(user, model.getInviteUser());
        // 认证信息
        UserIdentifyModel identifyModel = new UserIdentifyModel();
        identifyModel.setUser(user);
        identifyModel.initReg(model);
        UserIdentify userIdentify = identifyModel.prototype();
        userIdentifyDao.save(userIdentify);
        // 账户信息
        AccountModel accountModel = new AccountModel();
        accountModel.setUser(user);
        accountModel.initReg(model);
        Account account = accountModel.prototype();
        accountDao.save(account);
        // 积分账户
        IntegralAccountModel integralAccountModel = new IntegralAccountModel();
        integralAccountModel.setUser(user);
        integralAccountModel.initReg(model);
        IntegralAccount integralAccount = integralAccountModel.prototype();
        integralAccountDao.save(integralAccount);
        // 信用账户
        CreditScoreModel creditScoreModel = new CreditScoreModel();
        creditScoreModel.setUser(user);
        creditScoreModel.initReg(model);
        CreditScore creditScore = creditScoreModel.prototype();
        creditScoreDao.save(creditScore);
        // 注册任务
        Executer regExecuter = BeanUtil.getBean(UserRegExecuter.class);
        regExecuter.execute(UserModel.instance(user));
        // 邀请任务
        Executer inviteExecuter = BeanUtil.getBean(UserInviteExecuter.class);
        inviteExecuter.execute(UserInfoModel.instance(userInfo));
        UserModel returnModel = UserModel.instance(user);
        returnModel.initReturn();
        return Result.success().setData(returnModel);
    }

    /**
     * 登录
     * loginType 1pwd 2mobileCode
     *
     * @param model
     * @return
     */
    @Override
    public Result login(UserModel model) {
        QueryParam param = QueryParam.getInstance();
        if (model.getLoginType() <= 0) {
            throw new BusinessException("登录方式未定义");
        } else if (model.getLoginType() == BaseConstant.LOGIN_BY_PWD) {
            String loginName = model.getLoginName();
            String pwd = "";
            if (!StringUtil.isBlank(model.getPwd())) {
                pwd = MD5.toMD5(StringUtil.isNull(model.getPwd()));
            }
            SearchFilter orFilter1 = new SearchFilter("userName", Operators.EQ, loginName);
            SearchFilter orFilter2 = new SearchFilter("email", Operators.EQ, loginName);
            SearchFilter orFilter3 = new SearchFilter("mobile", Operators.EQ, loginName);
            param.addOrFilter(orFilter1, orFilter2, orFilter3);
            if (StringUtil.isBlank(pwd)) {
                throw new BusinessException("登录密码不能为空");
            } else {
                param.addParam("pwd", pwd);
            }

        } else if (model.getLoginType() == BaseConstant.LOGIN_BY_MOBILECODE) {
            if (StringUtil.isBlank(model.getMobileCode())) {
                throw new BusinessException("验证码不能为空");
            } else {
                commonService.checkMobileCode(model.getLoginName(), model.getMobileCode(), BaseConstant.HANDLE_SMS_TYPE_PWD);
            }
            param.addParam("mobile", model.getLoginName());
        }
        // 检测用户的唯一性
        User user = userDao.findByCriteriaForUnique(param);
        if (null == user) {
            return Result.error("登录失败，用户名或密码有误");
        }
        model = UserModel.instance(user);

        // 登录任务
        Executer loginExecuter = BeanUtil.getBean(UserLoginExecuter.class);
        loginExecuter.execute(model);

        // 返回的数据
        UserModel returnModel = UserModel.instance(user);
        IntegralAccount integralAccoun = (IntegralAccount) integralAccountDao.findObjByProperty("user.id", user.getId());
        if (!StringUtil.isBlank(integralAccoun)) {
            returnModel.setIntegralAccountModel(IntegralAccountModel.instance(integralAccoun));
        }
        returnModel.setAccountModel(AccountModel.instance(user.getAccount()));
        returnModel.setUserIdentifyModel(UserIdentifyModel.instance(user.getUserIdentify()));
        returnModel.initReturn();
        return Result.success().setData(returnModel);
    }


    /**
     * 根据微信openId登录
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Object loginByOpenId(UserModel model) {
        if (StringUtil.isBlank(model.getOpenId())) {
            throw new BusinessException("请传入正确的openId");
        }
        User user = userDao.findByOpenId(model.getOpenId());
        if (user == null) {
            //注册
            return Result.success().setData(regByOpenId(model));
        }
        if (StringUtil.isBlank(user.getUserName())) {
            try {
                if (StringUtil.isNotBlank(model.getUserName())) {
                    user.setUserName(URLEncoder.encode(model.getUserName(), "utf-8"));
                }
            } catch (UnsupportedEncodingException e) {
                user.setUserName(model.getUserName());
            }
            UserInfo userInfo = user.getUserInfo();
            userInfo.setArea(model.getUserInfo().getArea());
            userInfo.setHeadImg(model.getUserInfo().getHeadImg());
            userInfo.setProvince(model.getUserInfo().getProvince());
            userInfo.setCity(model.getUserInfo().getCity());
            user.setUserInfo(userInfo);
            userDao.update(user);
        }
        UserModel userModel = UserModel.instance(user);
        userModel.setInfoModel(UserInfoModel.instance(userModel.getUserInfo()));
        return Result.success().setData(userModel);
    }

    /**
     * 获取所有用户信息
     *
     * @param userId
     * @return
     */
    @Override
    public Object getAllUserInfo(Long userId) {
        User user = userDao.find(userId);
        if (user == null) {
            throw new BusinessException("用户信息有误");
        }
        UserModel userModel = UserModel.instance(user);
        List<UserRelation> userRelationList = userRelationDao.findUserListByToUserId(user.getId()).getList();
        for (UserRelation userRelation : userRelationList) {

        }
        return null;
    }

    /**
     * 根据id获取用户
     *
     * @param userId
     * @return
     */
    @Override
    public User find(Long userId) {
        return userDao.find(userId);
    }


    /**
     * 微信用户注册
     *
     * @param model
     */
    private User regByOpenId(UserModel model) {
        User user = new User();
        initOpenIdUser(user, model);

        UserInfoModel infoModel = new UserInfoModel();
        infoModel.setUser(user);
        infoModel.initReg(model);
        infoModel.initOpenIdReg(model);
        if (model.getInviteUser() != null && model.getInviteUser().getId() > 0) {
            infoModel.setInviteUser(userDao.find(model.getInviteUser().getId()));
        }
        UserInfo userInfo = infoModel.prototype();
        userInfoDao.save(userInfo);
        model.setInfoModel(infoModel);
        // 认证信息
        UserIdentifyModel identifyModel = new UserIdentifyModel();
        identifyModel.setUser(user);
        identifyModel.initReg(model);
        UserIdentify userIdentify = identifyModel.prototype();
        userIdentifyDao.save(userIdentify);
        // 账户信息
        AccountModel accountModel = new AccountModel();
        accountModel.setUser(user);
        accountModel.initReg(model);
        Account account = accountModel.prototype();
        accountDao.save(account);
        // 积分账户
        IntegralAccountModel integralAccountModel = new IntegralAccountModel();
        integralAccountModel.setUser(user);
        integralAccountModel.initReg(model);
        IntegralAccount integralAccount = integralAccountModel.prototype();
        integralAccountDao.save(integralAccount);
        // 信用账户
        CreditScoreModel creditScoreModel = new CreditScoreModel();
        creditScoreModel.setUser(user);
        creditScoreModel.initReg(model);
        CreditScore creditScore = creditScoreModel.prototype();
        creditScoreDao.save(creditScore);
        // 邀请
        if (userInfo.getInviteUser() == null || userInfo.getInviteUser().getId() < 0 || userDao.find(userInfo.getInviteUser().getId()) == null) {
            //默认邀请人
            userInfo.setInviteUser(userDao.find(1L));
        }
        UserRelation userRelation = new UserRelation();
        userRelation.setBeUser(userInfo.getInviteUser());
        userRelation.setUser(user);
        userRelation.setAddTime(DateUtil.getNow());
        userRelation.setState(BaseConstant.INFO_STATE_YES);
        userRelation.setType(BaseConstant.USERRELATION_TYPE_INVITE);
        userRelationDao.save(userRelation);
        // 邀请任务
        Executer inviteExecuter = BeanUtil.getBean(UserInviteExecuter.class);
        inviteExecuter.execute(UserInfoModel.instance(userInfo));
        // 注册任务
        Executer regExecuter = BeanUtil.getBean(UserRegExecuter.class);
        regExecuter.execute(UserModel.instance(user));

        UserModel returnModel = UserModel.instance(user);
        returnModel.initReturn();
        if (user.getUserInfo() != null) {
            initOpenIdUserInfo(user, model);
        }
        return userDao.save(user);

    }

    private void initOpenIdUser(User user, UserModel model) {
        user.setOpenId(model.getOpenId());
        user.setUserName(model.getUserName());
        user.setAddTime(DateUtil.getNow());
        model.setRoute(1);
        model.setUserNature(BaseConstant.USER_NATURE_GENERAL);
    }

    private void initOpenIdUserInfo(User user, UserModel model) {
        UserInfo userInfo = new UserInfo();
        if (model.getUserInfo().getSex() > 0) {
            userInfo.setSex(model.getUserInfo().getSex());
        }
        if (StringUtil.isNotBlank(model.getUserInfo().getProvince())) {
            userInfo.setProvince(model.getUserInfo().getProvince());
        }
        if (StringUtil.isNotBlank(model.getUserInfo().getCity())) {
            userInfo.setCity(model.getUserInfo().getCity());
        }
        if (StringUtil.isNotBlank(model.getUserInfo().getArea())) {
            userInfo.setArea(model.getUserInfo().getArea());
        }
        user.setUserInfo(userInfo);
    }


    /**
     * 找回密码
     *
     * @param model
     * @return
     */
    @Override
    public Result backKeyCode(UserModel model) {
        // 找回密码信息校验
        model.backReg();
        User user = userDao.findObjByProperty("mobile", model.getMobile());
        // 通过手机号设置新密码,检测新密码是否为空
        if (!StringUtil.isBlank(model.getPwd())) {
            user.setPwd(MD5.toMD5(StringUtil.isNull(model.getPwd())));
        }
        userDao.update(user);
        return Result.success().setData(user);
    }

    /**
     * 修改交易密码
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result updatePayPwd(UserModel model) {
        // 校验修改交易密码
        User user = model.checkUpdatePayPwd();
        // 初始化信息
        model.initUpdatePayPwd(user);
        userDao.update(user);
        return Result.success();
    }

    /**
     * 修改登录密码
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result updatePwd(UserModel model) {
        // 校验修改登录密码
        User user = model.checkUpdatePwd();
        if (!StringUtil.isBlank(model.getMobileCode())) {
            commonService.checkMobileCode(user.getMobile(), model.getMobileCode(), BaseConstant.HANDLE_SMS_TYPE_PWD);
        }
        // 初始化信息
        model.initUpdatePwd(user);
        userDao.update(user);
        return Result.success();
    }

    /**
     * 通过手机号设置登录密码
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result backDatePwd(UserModel model) {
        // 通过手机号设置新密码,检测新密码是否为空
        String pwd = MD5.toMD5(StringUtil.isNull(model.getPwd()));
        User user = userDao.find(model.getId());
        user.setPwd(pwd);
        userDao.update(user);
        return Result.success().setData(user);
    }

    /**
     * 邮箱认证
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result emailState(UserModel model) {
        User user = userDao.find(model.getId());
        // 判断邮箱是否合法
        if (StringUtil.isEmail(model.getEmail())) {
            user.setEmail(model.getEmail());
        } else {
            return Result.error("该邮箱不合法？请输入合法邮箱");
        }
        userDao.update(user);
        UserIdentify userIdentify = userIdentifyDao.findObjByProperty("user.id", user.getId());
        if (userIdentify != null) {
            userIdentify.setEmailState(BaseConstant.IDENTIFY_STATE_YES);
        }
        return Result.success();
    }

    /**
     * 第三方登录请求
     *
     * @param model
     * @return
     */
    @Override
    public Object apiLogin(UserModel model) {
        // 第三方登录校验
        User user = model.checkApiLogin();
        model = UserModel.instance(user);

        Executer userApiLoginExecuter = BeanUtil.getBean(UserApiLoginExecuter.class);
        userApiLoginExecuter.execute(model);

        return Result.success().setData(model.getPayModel());
    }

    /**
     * 修改手机号请求
     *
     * @param model
     * @return
     */
    @Override
    public Object modifyMobileRequest(UserModel model) {
        // 修改手机号校验
        this.modifyMobileRequestCheck(model);

        Executer modifyMobileRequestExecuter = BeanUtil.getBean(ModifyMobileRequestExecuter.class);
        modifyMobileRequestExecuter.execute(model);

        QueueAbstract.send(StringUtil.getSerialNumber(), OrderNid.ORDER_NID_USER_MODIFY_MOBILE.getNid(),
                QueueCode.QUEUE_CODE_OTHER.getCode(), model, model.getUser());
        return Result.success("处理中...请稍后！");
    }

    /**
     * 修改手机号校验
     */
    private void modifyMobileRequestCheck(UserModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误");
        }
        User user = userDao.find(model.getId());
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        if (StringUtil.isBlank(model.getNewMobile())) {
            throw new BusinessException("新手机号不能为空");
        }
        // 短信验证码校验
        commonService.checkMobileCode(model.getNewMobile(), model.getMobileCode(),
                BaseConstant.HANDLE_SMS_TYPE_UPDATE_MOBILE);
        model.setUser(user);
    }

    /**
     * 修改手机号处理
     *
     * @param model
     * @return
     */
    @Override
    public Object modifyMobileDeal(UserModel model) {
        User user1 = userDao.find(model.getId());
        User user = userDao.findByMobile(model.getMobile());
        if (user == null || user.getMobile().equals(model.getNewMobile())) {
            throw new BusinessException("修改信息不存在或已处理");
        }
        user.setMobile(model.getNewMobile());
        userDao.update(user);
        OrderTaskModel.dealOrder(model.getOrderTask() != null ? model.getOrderTask().getOrderNo() : "",
                BaseConstant.BUSINESS_STATE_YES, "手机号修改处理成功");
        return Result.success("手机号修改处理成功");
    }

    @Override
    public long findUserNumber() {
        return userDao.findUserNumber();
    }

    @Override
    public long findUserNumberByToday() {
        return userDao.findUserNumber();
    }

    /**
     * 根据id删除
     *
     * @param model userId
     * @return
     */
    @Override
    @Transactional
    public Result deleteById(UserModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        userDao.delete(model.getId());
        return Result.success();

    }

    /**
     * 修改商家信息
     *
     * @param model userId
     * @return
     */
    @Override
    @Transactional
    public Result updateRelationByUserId(UserModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        // 初始化用户基本信息
        UserInfoModel infoModel = new UserInfoModel();
        UserInfo userInfo = infoModel.setUpdateParams(model);
        userInfoDao.update(userInfo);
        return Result.success();
    }

    /**
     * 通过手机号查询
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result findUserByMobile(UserModel model) {
        User user = userDao.findByMobile(model.getMobile());
        UserModel model_ = null;
        if (user != null) {
            model_ = UserModel.instance(user);
            model_.setInfoModel(UserInfoModel.instance(user.getUserInfo()));
            model_.setUserIdentifyModel(UserIdentifyModel.instance(user.getUserIdentify()));
        }
        return Result.success().setData(model_);
    }

    /**
     * 商家旗下借款人注册
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result regBorrowUserInRelation(UserModel model) {
        Result rs = this.regRequest(model);
        UserModel umodel = (UserModel) rs.getData();
        model.regUserRelation(umodel);
        return Result.success();
    }

    /**
     * 检查用户是否注册
     *
     * @param model
     * @return
     */
    @Override
    public Object userCheck(UserModel model) {
        if (model == null || StringUtil.isBlank(model.getMobile())) {
            throw new BusinessException("查询参数有误");
        }
        if (!model.checkMobileExist(model.getMobile())) {
            throw new BusinessException("用户不存在");
        }
        return Result.success("用户存在");
    }

    /**
     * 获取用户及相关信息统计
     *
     * @param userId
     * @return
     */
    @Override
    public Object getUserInfoCount(Long userId) {
        JSONObject jsonObject = new JSONObject();
        User user = userDao.find(userId);
        NoticeMessageModel noticeMessageModel = new NoticeMessageModel();
        noticeMessageModel.setReceiveUser(user);
        noticeMessageModel.setState(1);
        noticeMessageModel.setType(1);
        jsonObject.put("userNature", user.getUserInfo().getUserNature() == 1 ? "普通用户" : "商户");
        jsonObject.put("creditScore", creditScoreDao.findByUser(userId).getBalanceScore());
        jsonObject.put("noticeMessage", noticeMessageService.getByUserIdAndState(noticeMessageModel));
        jsonObject.put("mobile", user.getMobile());
        jsonObject.put("gradeIntegral", getUserGradeIntegral(userId));
        return Result.success().setData(jsonObject);
    }


    private String getUserGradeIntegral(long userId) {
        int gradeIntegral = integralAccountDao.findByUserId(userId).getGradeIntegral();
        String result;
        switch (gradeIntegral) {
            case BaseConstant.USER_GRADE_INTEGRAL_PRIMARY:
                result = "初级";
                break;
            case BaseConstant.USER_GRADE_INTEGRAL_MIDDLE_LEVEL:
                result = "中级";
                break;
            case BaseConstant.USER_GRADE_INTEGRAL_HIGH_RANKING:
                result = "高级";
                break;
            case BaseConstant.USER_GRADE_INTEGRAL_EXCLUSIVE:
                result = "尊享";
                break;
            default:
                result = "初级";
                break;
        }
        return result;
    }
}
