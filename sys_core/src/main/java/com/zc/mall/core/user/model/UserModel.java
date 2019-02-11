package com.zc.mall.core.user.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.zc.mall.core.account.model.AccountModel;
import com.zc.mall.core.account.model.BankCardModel;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.common.constant.BaseConstant.OrderNid;
import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.common.service.CommonService;
import com.zc.mall.core.integral.model.IntegralAccountModel;
import com.zc.mall.core.integral.model.IntegralLogModel;
import com.zc.mall.core.manage.entity.OrderTask;
import com.zc.mall.core.user.dao.UserDao;
import com.zc.mall.core.user.dao.UserIdentifyDao;
import com.zc.mall.core.user.dao.UserInfoDao;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.entity.UserInfo;
import com.zc.mall.core.user.service.UserRelationService;
import com.zc.mall.core.user.service.UserService;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.model.jpa.Page;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.encrypt.MD5;
import com.zc.sys.common.util.http.RequestUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.beans.BeanUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

/**
 * 用户
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public class UserModel extends User {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;
    /**
     * 当前页码
     **/
    private int pageNo;
    /**
     * 每页数据条数
     **/
    private int pageSize = Page.ROWS;
    /**
     * 条件查询
     **/
    private String searchName;

    /**
     * 用户
     **/
    @JsonInclude(Include.NON_NULL)
    private User user;
    /**
     * 登录名
     **/
    private String loginName;
    /**
     * 确认密码
     **/
    private String confirmPwd;
    /**
     * 原密码
     **/
    private String oldPwd;
    /**
     * 修改密码方式：1-修改密码，2-忘记密码
     **/
    private int updatePwdWay;
    /**
     * 用户基本信息
     **/
    @JsonInclude(Include.NON_NULL)
    private UserIdentifyModel userIdentifyModel;
    /**
     * 用户基本信息
     **/
    @JsonInclude(Include.NON_NULL)
    private UserInfoModel infoModel;
    /**
     * 邀请码
     **/
    private String inviteCode;
    /**
     * 邀请用户Id
     **/
    private long inviteUserId;
    /**
     * 邀请用户
     **/
    private User inviteUser;
    /**
     * 邀请用户
     **/
    private UserModel inviteUserModel;
    /**
     * 被此用户邀请的用户列表
     **/
    private PageDataList<UserModel> inviteUserModelList;
    /**
     * 商家Id
     **/
    private long relationUserId;
    /**
     * 用户类型
     **/
    private int type;
    /**
     * 用户信息Id
     **/
    private long userInfoId;
    /**
     * 用户类别
     **/
    private int userNature;
    /**
     * 注册渠道
     **/
    private int route;
    /**
     * 推广途径
     **/
    private String channel;
    /**
     * 手机号认证状态
     **/
    private int mobileState;
    /**
     * 是否是今日注册
     **/
    private int regState;
    /**
     * 实名认证状态
     **/
    private int realNameState;
    /**
     * 短信验证码
     **/
    private String mobileCode;
    /**
     * 新手机号
     **/
    private String newMobile;
    /**
     * 添加ip
     **/
    private String addIp;
    /**
     * 订单信息
     **/
    private OrderTask orderTask;
    /**
     * 重复标识
     **/
    private String token;
    /**
     * 账户Model
     **/
    @JsonInclude(Include.NON_NULL)
    private AccountModel accountModel;
    /**
     * 积分账户Model
     **/
    @JsonInclude(Include.NON_NULL)
    private IntegralAccountModel integralAccountModel;
    /**
     * 银行卡Model
     **/
    private List<BankCardModel> bankCardModel;
    /**
     * 积分日志Model
     **/
    private IntegralLogModel integralLogModel;
    /**
     * 支付接口对象
     **/
    private Object payModel;
    /**
     * 居住地址
     **/
    private String address;
    /**
     * 公司名称
     **/
    private String companyName;
    /**
     * 公司类型
     **/
    private int companyType;
    /**
     * 公司证件号(企业征信代码)
     **/
    private String companyCardNo;
    /**
     * 公司描述
     **/
    private String companyDes;
    /**
     * 企业法人姓名
     **/
    private String legalName;
    /**
     * 企业法人证件号
     **/
    private String legalCardNo;
    /**
     * 公司产品介绍
     **/
    private String companyProjectDes;
    /**
     * 公司产品类型
     **/
    private String companyProjectType;
    /**
     * logo图片地址
     **/
    private String companyLogoPic;
    /**
     * 注册资本
     **/
    private String companyRegisteredCapital;
    /**
     * 实缴注册资本
     **/
    private String companyRealRegisteredCapital;
    /**
     * 公司经营地址
     **/
    private String companyBusinessAddress;
    /**
     * 公司经营期限
     **/
    private String companyBusinessPeriod;
    /**
     * 公司经营状态
     **/
    private String companyBusinessState;
    /**
     * 公司电话
     **/
    private String companyTelephone;
    /**
     * 公司推荐信息
     **/
    private String companyRecommendDec;
    /**
     * 营业执照图片
     **/
    private String companyBusinessLicensePic;
    /**
     * 公司章程
     **/
    private String companyRulesPic;
    /**
     * 公司其他图片
     **/
    private String companyOtherPic;
    /**
     * 提现规则
     **/
    private String companyCashRule;
    /**
     * 公司安全保障
     **/
    private String companySafety;
    /**
     * 公司安全保障方式
     **/
    private String companySafetyType;
    /**
     * 公司成立时间
     **/
    private Date companyAddTime;
    /**
     * 最高年化
     **/
    private String companyRateMost;
    /**
     * 最低年化
     **/
    private String companyRateLowest;
    /**
     * 最大期限
     **/
    private String companyPeriodMost;
    /**
     * 最小期限
     **/
    private String companyPeriodLowest;

    private String bankCardName;

    private String bankCardNo;
    /**
     * userInfoState(商家排序) 1综合排序 2安全优先 3收益优先
     **/
    private int userInfoState;
    /**
     * 登录方式 1：密码 2：短信 默认为密码
     **/
    private int loginType;

    private String isRecommend;
    /**
     * 用户佣金
     **/
    private Double receiveAccount;
    /**
     * 邀请用户佣金
     **/
    private Double inviteAccount;
    /**
     * 用户消费金额
     **/
    private Double totalPay;
    /**
     * 用户消费数目
     **/
    private Double totalCount;
    /**
     * 邀请的用户消费金额
     **/
    private Double inviteUserTotalPay;
    /**
     * 用户等级
     **/
    private Integer Grade;
    /**
     * 邀请人数
     **/
    private Integer inviteUserCount;
    /**
     * 用户地址
     **/
    private String userAddressDefault;

    private Date lessBirthday;

    private Date mostBirthday;

    public UserModel() {
        super();
    }

    /**
     * 实体转换model
     */
    public static UserModel instance(User user) {
        if (user == null || user.getId() <= 0) {
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(user, userModel);
        try {
            if (StringUtil.isNotBlank(user.getUserName())){
                userModel.setUserName(URLDecoder.decode(user.getUserName(), "utf-8"));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return userModel;
    }

    /**
     * model转换实体
     */
    public User prototype() {
        User user = new User();
        BeanUtils.copyProperties(this, user);
        return user;
    }

    /**
     * 设置修改基本参数
     */
    public void setUpdateParam() {
        // this.setAccount(this.getAccount());
        this.setUserName(this.getUserName());
        // this.setCardNo(this.getCardNo());
        // this.setEmail(this.getEmail());
        this.setMobile(this.getMobile());
        // this.setPayPwd(this.getPayPwd());
        this.setPwd(this.getPwd());
        // this.setRealName(this.getRealName());
    }

    /**
     * 设置修改基本参数
     *
     * @param user
     */
    public User setUpdateUser(User user) {
        user.setMobile(user.getMobile());
        user.setUserName(user.getUserName());
        user.setCardNo(user.getCardNo());
        user.setRealName(user.getRealName());
        user.setEmail(user.getEmail());
        user.setAddTime(user.getAddTime());
        UserInfoDao userInfoDao = BeanUtil.getBean(UserInfoDao.class);
        user.getUserInfo().setUserNature(this.getUserNature());
        user.getUserInfo().setType(this.getType());
        userInfoDao.update(user.getUserInfo());
        return user;

    }

    /**
     * 设置修改基本参数
     *
     * @param user
     */
    public void setUpdateUserInfoParams(User user) {
        UserInfoDao userInfoDao = BeanUtil.getBean(UserInfoDao.class);
        UserInfo userInfo = userInfoDao.findObjByProperty("user.id",
                user.getId());
        userInfo.setType(this.getType());
        userInfo.setUserNature(this.getUserNature());
        userInfoDao.update(userInfo);
    }

    /**
     * 注册信息校验
     */
    public void checkReg() {
        CommonService commonService = (CommonService) BeanUtil
                .getBean(CommonService.class);
        commonService.checkToken(this.token);
        String mobile = this.getMobile();
        if (!StringUtil.isPhone(mobile)) {
            throw new BusinessException("请输入正确手机号");
        }
        if (this.checkMobileExist(mobile)) {
            throw new BusinessException("该手机号已存在");
        }

        if (!StringUtil.isBlank(this.getPwd())
                && !StringUtil.isBlank(this.getConfirmPwd())) {
            if (StringUtil.isBlank(this.getPwd())
                    || StringUtil.isBlank(this.getConfirmPwd())
                    || !this.getPwd().equals(this.getConfirmPwd())) {
                throw new BusinessException("登录密码输入有误");
            }
            // 短信验证码校验
            commonService.checkMobileCode(mobile, this.getMobileCode(),
                    BaseConstant.HANDLE_SMS_TYPE_REG);
        } else {
            throw new BusinessException("请输入密码");
        }

    }

    /**
     * 找回密码信息校验
     */
    public void backReg() {
        // CommonService commonService =
        // (CommonService)BeanUtil.getBean(CommonService.class);
        // commonService.checkToken(this.token);
        String mobile = this.getMobile();
        if (!StringUtil.isPhone(mobile)) {
            throw new BusinessException("请输入正确手机号");
        }
        if (!this.checkMobileExist(mobile)) {
            throw new BusinessException("该手机号尚未注册");
        }
        if (StringUtil.isBlank(this.getMobileCode())) {
            throw new BusinessException("验证码不能为空");
        }
        if (StringUtil.isBlank(this.getPwd())
                || StringUtil.isBlank(this.getConfirmPwd())
                || !this.getPwd().equals(this.getConfirmPwd())) {
            throw new BusinessException("两次密码输入不一致！请重新输入");
        }
        // 短信验证码校验
        // commonService.checkMobileCode(mobile,
        // this.getMobileCode(),BaseConstant.HANDLE_SMS_TYPE_REG);
    }

    /**
     * 判断手机号是否存在
     *
     * @param mobile
     * @return
     */
    public boolean checkMobileExist(String mobile) {
        UserIdentifyDao userIdentifyDao = BeanUtil
                .getBean(UserIdentifyDao.class);
        int count = userIdentifyDao.countByModel(new UserIdentifyModel(mobile));
        return count > 0 ? true : false;
    }

    /**
     * 登录信息校验
     */
    public void checkLogin() {
        CommonService commonService = (CommonService) BeanUtil
                .getBean(CommonService.class);
        commonService.checkToken(this.token);
        if (StringUtil.isBlank(this.getLoginName())
                || StringUtil.isBlank(this.getPwd())) {
            throw new BusinessException("登录信息不能为空");
        }
    }

    /**
     * 注册初始化
     */
    public void initReg() {
        if (!StringUtil.isBlank(this.getType())
                && this.getType() == BaseConstant.USER_TYPE_BUSINESS) {
            this.setPwd(MD5.toMD5("123456"));// 商家注册初始化密码
            this.setRealName(this.getCompanyName());
            this.setUserName(this.getLegalName());
        } else {
            this.setPwd(MD5.toMD5(this.getPwd()));
            this.setPayPwd(MD5.toMD5("123456"));
        }
        this.setUserName(this.getMobile());// 用户名默认手机号
        this.setAddTime(DateUtil.getNow());
        this.setAddIp(RequestUtil.getClientIp());// 获取ip
    }

    /**
     * 邮箱认证检查
     */
    public void emailCheck() {
        if (StringUtil.isEmail(this.getEmail())) { // 判断邮箱是否合法
            this.setEmail(this.getEmail());
        } else {
            throw new BusinessException("该邮箱不合法？请输入合法邮箱");
        }

    }

    /**
     * 注册返回数据
     */
    public void initReturn() {
        this.setPwd(null);
        this.setPayPwd(null);
    }

    /**
     * 校验修改登录密码
     */
    public User checkUpdatePwd() {
        // CommonService commonService =
        // (CommonService)BeanUtil.getBean(CommonService.class);
        // commonService.checkToken(this.token);
        UserDao userDao = BeanUtil.getBean(UserDao.class);
        if (this.getId() <= 0) {
            throw new BusinessException("参数错误");
        }
        if (StringUtil.isBlank(this.getPwd())
                || StringUtil.isBlank(this.getConfirmPwd())
                || !this.getPwd().equals(this.getConfirmPwd())) {
            throw new BusinessException("新密码输入有误");
        }
        User user = userDao.find(this.getId());
        if (user == null) {
            throw new BusinessException("参数错误");
        }
        switch (this.updatePwdWay) {
            case BaseConstant.UPDATE_PWD_WAY_UPDATE:// 正常修改
                if (StringUtil.isBlank(this.getOldPwd())
                        || StringUtil.isBlank(this.getConfirmPwd())
                        || !MD5.toMD5(this.oldPwd).equals(user.getPwd())) {
                    throw new BusinessException("原密码输入有误");
                }
                break;
            case BaseConstant.UPDATE_PWD_WAY_FORGET:// 忘记密码
                if (StringUtil.isBlank(this.mobileCode)) {
                    throw new BusinessException("短信验证码不能为空");
                }
                // 短信验证码校验
                // commonService.checkMobileCode(user.getMobile(),
                // this.getMobileCode(),BaseConstant.HANDLE_SMS_TYPE_PWD);
                break;
            default:
                throw new BusinessException("参数错误");
        }
        return user;
    }

    /**
     * 初始化信息
     *
     * @param user
     */
    public void initUpdatePwd(User user) {
        user.setPwd(MD5.toMD5(this.getPwd()));
    }

    /**
     * 调用添加商家的方法
     *
     * @param umodel
     */
    public void regUserRelation(UserModel umodel) {
        UserRelationService userRelationService = (UserRelationService) BeanUtil
                .getBean(UserRelationService.class);
        UserRelationModel userRelationModel = new UserRelationModel();
        if (this.getRelationUserId() > 0) {
            userRelationModel.setBeUserId(this.getRelationUserId());
        }
        userRelationModel.setUserId(umodel.getId());
        userRelationService.addBorrowUserInRelation(userRelationModel);
    }

    /**
     * 校验修改交易密码
     *
     * @return
     */
    public User checkUpdatePayPwd() {
        CommonService commonService = (CommonService) BeanUtil
                .getBean(CommonService.class);
        commonService.checkToken(this.token);
        UserDao userDao = BeanUtil.getBean(UserDao.class);
        if (this.getId() <= 0) {
            throw new BusinessException("参数错误");
        }
        if (StringUtil.isBlank(this.getPayPwd())) {
            throw new BusinessException("新密码输入有误");
        }
        User user = userDao.find(this.getId());
        if (user == null) {
            throw new BusinessException("参数错误");
        }
        if (MD5.toMD5(this.getPayPwd()).equals(user.getPwd())) {
            throw new BusinessException("交易密码不能和登录密码相同");
        }
        switch (this.updatePwdWay) {
            case BaseConstant.UPDATE_PWD_WAY_SET:// 设置
                // 设置密码判断
                break;
            case BaseConstant.UPDATE_PWD_WAY_UPDATE:// 修改
                if (StringUtil.isBlank(this.oldPwd)
                        || StringUtil.isBlank(this.getConfirmPwd())
                        || !MD5.toMD5(this.oldPwd).equals(this.getPayPwd())) {
                    throw new BusinessException("原交易密码输入有误");
                }
                break;
            case BaseConstant.UPDATE_PWD_WAY_FORGET:// 忘记密码
                if (StringUtil.isBlank(this.mobileCode)) {
                    throw new BusinessException("短信验证码不能为空");
                }
                // 短信验证码校验
                commonService.checkMobileCode(user.getMobile(),
                        this.getMobileCode(), BaseConstant.HANDLE_SMS_TYPE_PAYPWD);
                break;
            default:
                throw new BusinessException("参数错误");
        }
        return user;
    }

    /**
     * 初始化信息
     *
     * @param user
     */
    public void initUpdatePayPwd(User user) {
        user.setPayPwd(MD5.toMD5(this.getPayPwd()));
    }

    /**
     * 校验交易密码
     *
     * @param inputPayPwd 输入密码
     * @param payPwd      原密码
     */
    public static void checkPayPwd(String inputPayPwd, String payPwd) {
        if (StringUtil.isBlank(inputPayPwd) || StringUtil.isBlank(payPwd)) {
            throw new BusinessException("交易密码错误");
        }
        if (!MD5.toMD5(inputPayPwd).equals(payPwd)) {
            throw new BusinessException("交易密码错误");
        }
    }

    /**
     * 第三方登录校验
     */
    public User checkApiLogin() {
        if (this.getId() <= 0) {
            throw new BusinessException("参数错误");
        }
        UserDao userDao = BeanUtil.getBean(UserDao.class);
        User user = userDao.find(this.getId());
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        if (user.getUserIdentify().getRealNameState() != BaseConstant.BUSINESS_STATE_YES) {
            throw new BusinessException("请先开通实名支付账户");
        }
        return user;
    }

    /**
     * 任务分发
     */
    public void doQueue() {
        UserService userService = BeanUtil.getBean(UserService.class);
        if (OrderNid.ORDER_NID_USER_MODIFY_MOBILE.getNid().equals(
                this.orderTask.getType())) {
            userService.modifyMobileDeal(this);
        }
    }

    /**
     * 获取【当前页码】
     **/
    public int getPageNo() {
        return pageNo;
    }

    /**
     * 设置【当前页码】
     **/
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * 获取【每页数据条数】
     **/
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置【每页数据条数】
     **/
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取【条件查询】
     **/
    public String getSearchName() {
        return searchName;
    }

    /**
     * 设置【条件查询】
     **/
    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    /**
     * 获取【邀请码】
     **/
    public String getInviteCode() {
        return inviteCode;
    }

    /**
     * 设置【邀请码】
     **/
    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    /**
     * 获取【用户类型】
     **/
    public int getType() {
        return type;
    }

    /**
     * 设置【用户类型】
     **/
    public void setType(int type) {
        this.type = type;
    }

    /**
     * 获取【infoModel】
     **/
    public UserInfoModel getInfoModel() {
        return infoModel;
    }

    /**
     * 设置【infoModel】
     **/
    public void setInfoModel(UserInfoModel infoModel) {
        this.infoModel = infoModel;
    }

    /**
     * 获取【用户类别】
     **/
    public int getUserNature() {
        return userNature;
    }

    /**
     * 设置【用户类别】
     **/
    public void setUserNature(int userNature) {
        this.userNature = userNature;
    }

    /**
     * 获取【注册渠道】
     **/
    public int getRoute() {
        return route;
    }

    /**
     * 设置【注册渠道】
     **/
    public void setRoute(int route) {
        this.route = route;
    }

    /**
     * 获取【推广途径】
     **/
    public String getChannel() {
        return channel;
    }

    /**
     * 设置【推广途径】
     **/
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     * 获取【手机号认证状态】
     **/
    public int getMobileState() {
        return mobileState;
    }

    /**
     * 设置【手机号认证状态】
     **/
    public void setMobileState(int mobileState) {
        this.mobileState = mobileState;
    }

    /**
     * 获取【登录名】
     **/
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置【登录名】
     **/
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * 获取【实名认证状态】
     **/
    public int getRealNameState() {
        return realNameState;
    }

    /**
     * 设置【实名认证状态】
     **/
    public void setRealNameState(int realNameState) {
        this.realNameState = realNameState;
    }

    /**
     * 获取【短信验证码】
     **/
    public String getMobileCode() {
        return mobileCode;
    }

    /**
     * 设置【短信验证码】
     **/
    public void setMobileCode(String mobileCode) {
        this.mobileCode = mobileCode;
    }

    /**
     * 获取【订单信息】
     **/
    public OrderTask getOrderTask() {
        return orderTask;
    }

    /**
     * 设置【订单信息】
     **/
    public void setOrderTask(OrderTask orderTask) {
        this.orderTask = orderTask;
    }

    /**
     * 获取【添加ip】
     **/
    public String getAddIp() {
        return addIp;
    }

    /**
     * 设置【添加ip】
     **/
    public void setAddIp(String addIp) {
        this.addIp = addIp;
    }

    /**
     * 获取【确认密码】
     **/
    public String getConfirmPwd() {
        return confirmPwd;
    }

    /**
     * 设置【确认密码】
     **/
    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }

    /**
     * 获取【原密码】
     **/
    public String getOldPwd() {
        return oldPwd;
    }

    /**
     * 设置【原密码】
     **/
    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    /**
     * 获取【修改密码方式：1-修改密码，2-忘记密码】
     **/
    public int getUpdatePwdWay() {
        return updatePwdWay;
    }

    /**
     * 设置【修改密码方式：1-修改密码，2-忘记密码】
     **/
    public void setUpdatePwdWay(int updatePwdWay) {
        this.updatePwdWay = updatePwdWay;
    }

    /**
     * 获取【重复标识】
     **/
    public String getToken() {
        return token;
    }

    /**
     * 设置【重复标识】
     **/
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 获取【账户Model】
     **/
    public AccountModel getAccountModel() {
        return accountModel;
    }

    /**
     * 设置【账户Model】
     **/
    public void setAccountModel(AccountModel accountModel) {
        this.accountModel = accountModel;
    }

    /**
     * 获取【用户基本信息】
     **/
    public UserIdentifyModel getUserIdentifyModel() {
        return userIdentifyModel;
    }

    /**
     * 设置【用户基本信息】
     **/
    public void setUserIdentifyModel(UserIdentifyModel userIdentifyModel) {
        this.userIdentifyModel = userIdentifyModel;
    }

    /**
     * 获取【积分账户Model】
     **/
    public IntegralAccountModel getIntegralAccountModel() {
        return integralAccountModel;
    }

    /**
     * 设置【积分账户Model】
     **/
    public void setIntegralAccountModel(
            IntegralAccountModel integralAccountModel) {
        this.integralAccountModel = integralAccountModel;
    }

    /**
     * 获取【银行卡Model】
     **/
    public List<BankCardModel> getBankCardModel() {
        return bankCardModel;
    }

    /**
     * 设置【银行卡Model】
     **/
    public void setBankCardModel(List<BankCardModel> bankCardModel) {
        this.bankCardModel = bankCardModel;
    }

    /**
     * 获取【积分日志Model】
     **/
    public IntegralLogModel getIntegralLogModel() {
        return integralLogModel;
    }

    /**
     * 设置【积分日志Model】
     **/
    public void setIntegralLogModel(IntegralLogModel integralLogModel) {
        this.integralLogModel = integralLogModel;
    }

    /**
     * 获取【支付接口对象】
     **/
    public Object getPayModel() {
        return payModel;
    }

    /**
     * 设置【支付接口对象】
     **/
    public void setPayModel(Object payModel) {
        this.payModel = payModel;
    }

    /**
     * 获取【新手机号】
     **/
    public String getNewMobile() {
        return newMobile;
    }

    /**
     * 设置【新手机号】
     **/
    public void setNewMobile(String newMobile) {
        this.newMobile = newMobile;
    }

    /**
     * 获取【居住地址】
     **/
    public String getAddress() {
        return address;
    }

    /**
     * 设置【居住地址】
     **/
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取【公司名称】
     **/
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置【公司名称】
     **/
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 获取【公司类型】
     **/
    public int getCompanyType() {
        return companyType;
    }

    /**
     * 设置【公司类型】
     **/
    public void setCompanyType(int companyType) {
        this.companyType = companyType;
    }

    /**
     * 获取【公司证件号(企业征信代码)】
     **/
    public String getCompanyCardNo() {
        return companyCardNo;
    }

    /**
     * 设置【公司证件号(企业征信代码)】
     **/
    public void setCompanyCardNo(String companyCardNo) {
        this.companyCardNo = companyCardNo;
    }

    /**
     * 获取【公司描述】
     **/
    public String getCompanyDes() {
        return companyDes;
    }

    /**
     * 设置【公司描述】
     **/
    public void setCompanyDes(String companyDes) {
        this.companyDes = companyDes;
    }

    /**
     * 获取【企业法人姓名】
     **/
    public String getLegalName() {
        return legalName;
    }

    /**
     * 设置【企业法人姓名】
     **/
    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    /**
     * 获取【企业法人证件号】
     **/
    public String getLegalCardNo() {
        return legalCardNo;
    }

    /**
     * 设置【企业法人证件号】
     **/
    public void setLegalCardNo(String legalCardNo) {
        this.legalCardNo = legalCardNo;
    }

    /**
     * 获取【公司产品介绍】
     **/
    public String getCompanyProjectDes() {
        return companyProjectDes;
    }

    /**
     * 设置【公司产品介绍】
     **/
    public void setCompanyProjectDes(String companyProjectDes) {
        this.companyProjectDes = companyProjectDes;
    }

    /**
     * 获取【公司产品类型】
     **/
    public String getCompanyProjectType() {
        return companyProjectType;
    }

    /**
     * 设置【公司产品类型】
     **/
    public void setCompanyProjectType(String companyProjectType) {
        this.companyProjectType = companyProjectType;
    }

    /**
     * 获取【logo图片地址】
     **/
    public String getCompanyLogoPic() {
        return companyLogoPic;
    }

    /**
     * 设置【logo图片地址】
     **/
    public void setCompanyLogoPic(String companyLogoPic) {
        this.companyLogoPic = companyLogoPic;
    }

    /**
     * 获取【注册资本】
     **/
    public String getCompanyRegisteredCapital() {
        return companyRegisteredCapital;
    }

    /**
     * 设置【注册资本】
     **/
    public void setCompanyRegisteredCapital(String companyRegisteredCapital) {
        this.companyRegisteredCapital = companyRegisteredCapital;
    }

    /**
     * 获取【实缴注册资本】
     **/
    public String getCompanyRealRegisteredCapital() {
        return companyRealRegisteredCapital;
    }

    /**
     * 设置【实缴注册资本】
     **/
    public void setCompanyRealRegisteredCapital(
            String companyRealRegisteredCapital) {
        this.companyRealRegisteredCapital = companyRealRegisteredCapital;
    }

    /**
     * 获取【公司经营地址】
     **/
    public String getCompanyBusinessAddress() {
        return companyBusinessAddress;
    }

    /**
     * 设置【公司经营地址】
     **/
    public void setCompanyBusinessAddress(String companyBusinessAddress) {
        this.companyBusinessAddress = companyBusinessAddress;
    }

    /**
     * 获取【公司经营期限】
     **/
    public String getCompanyBusinessPeriod() {
        return companyBusinessPeriod;
    }

    /**
     * 设置【公司经营期限】
     **/
    public void setCompanyBusinessPeriod(String companyBusinessPeriod) {
        this.companyBusinessPeriod = companyBusinessPeriod;
    }

    /**
     * 获取【公司经营状态】
     **/
    public String getCompanyBusinessState() {
        return companyBusinessState;
    }

    /**
     * 设置【公司经营状态】
     **/
    public void setCompanyBusinessState(String companyBusinessState) {
        this.companyBusinessState = companyBusinessState;
    }

    /**
     * 获取【公司电话】
     **/
    public String getCompanyTelephone() {
        return companyTelephone;
    }

    /**
     * 设置【公司电话】
     **/
    public void setCompanyTelephone(String companyTelephone) {
        this.companyTelephone = companyTelephone;
    }

    /**
     * 获取【公司推荐信息】
     **/
    public String getCompanyRecommendDec() {
        return companyRecommendDec;
    }

    /**
     * 设置【公司推荐信息】
     **/
    public void setCompanyRecommendDec(String companyRecommendDec) {
        this.companyRecommendDec = companyRecommendDec;
    }

    /**
     * 获取【营业执照图片】
     **/
    public String getCompanyBusinessLicensePic() {
        return companyBusinessLicensePic;
    }

    /**
     * 设置【营业执照图片】
     **/
    public void setCompanyBusinessLicensePic(String companyBusinessLicensePic) {
        this.companyBusinessLicensePic = companyBusinessLicensePic;
    }

    /**
     * 获取【公司章程】
     **/
    public String getCompanyRulesPic() {
        return companyRulesPic;
    }

    /**
     * 设置【公司章程】
     **/
    public void setCompanyRulesPic(String companyRulesPic) {
        this.companyRulesPic = companyRulesPic;
    }

    /**
     * 获取【公司其他图片】
     **/
    public String getCompanyOtherPic() {
        return companyOtherPic;
    }

    /**
     * 设置【公司其他图片】
     **/
    public void setCompanyOtherPic(String companyOtherPic) {
        this.companyOtherPic = companyOtherPic;
    }

    /**
     * 获取【提现规则】
     **/
    public String getCompanyCashRule() {
        return companyCashRule;
    }

    /**
     * 设置【提现规则】
     **/
    public void setCompanyCashRule(String companyCashRule) {
        this.companyCashRule = companyCashRule;
    }

    /**
     * 获取【公司安全保障】
     **/
    public String getCompanySafety() {
        return companySafety;
    }

    /**
     * 设置【公司安全保障】
     **/
    public void setCompanySafety(String companySafety) {
        this.companySafety = companySafety;
    }

    /**
     * 获取【公司安全保障方式】
     **/
    public String getCompanySafetyType() {
        return companySafetyType;
    }

    /**
     * 设置【公司安全保障方式】
     **/
    public void setCompanySafetyType(String companySafetyType) {
        this.companySafetyType = companySafetyType;
    }

    /**
     * 获取【公司成立时间】
     **/
    public Date getCompanyAddTime() {
        return companyAddTime;
    }

    /**
     * 设置【公司成立时间】
     **/
    public void setCompanyAddTime(Date companyAddTime) {
        this.companyAddTime = companyAddTime;
    }

    /**
     * 获取【最高年化】
     **/
    public String getCompanyRateMost() {
        return companyRateMost;
    }

    /**
     * 设置【最高年化】
     **/
    public void setCompanyRateMost(String companyRateMost) {
        this.companyRateMost = companyRateMost;
    }

    /**
     * 获取【最低年化】
     **/
    public String getCompanyRateLowest() {
        return companyRateLowest;
    }

    /**
     * 设置【最低年化】
     **/
    public void setCompanyRateLowest(String companyRateLowest) {
        this.companyRateLowest = companyRateLowest;
    }

    /**
     * 获取【最大期限】
     **/
    public String getCompanyPeriodMost() {
        return companyPeriodMost;
    }

    /**
     * 设置【最大期限】
     **/
    public void setCompanyPeriodMost(String companyPeriodMost) {
        this.companyPeriodMost = companyPeriodMost;
    }

    /**
     * 获取【最小期限】
     **/
    public String getCompanyPeriodLowest() {
        return companyPeriodLowest;
    }

    /**
     * 设置【最小期限】
     **/
    public void setCompanyPeriodLowest(String companyPeriodLowest) {
        this.companyPeriodLowest = companyPeriodLowest;
    }

    /**
     * 获取【用户信息Id】
     **/
    public long getUserInfoId() {
        return userInfoId;
    }

    /**
     * 设置【用户信息Id】
     **/
    public void setUserInfoId(long userInfoId) {
        this.userInfoId = userInfoId;
    }

    /**
     * 获取【是否是今日注册】
     **/
    public int getRegState() {
        return regState;
    }

    /**
     * 设置【是否是今日注册】
     **/
    public void setRegState(int regState) {
        this.regState = regState;
    }

    /**
     * 获取【邀请用户Id】
     **/
    public long getInviteUserId() {
        return inviteUserId;
    }

    /**
     * 设置【邀请用户Id】
     **/
    public void setInviteUserId(long inviteUserId) {
        this.inviteUserId = inviteUserId;
    }

    /**
     * 获取【商家Id】
     **/
    public long getRelationUserId() {
        return relationUserId;
    }

    /**
     * 设置【商家Id】
     **/
    public void setRelationUserId(long relationUserId) {
        this.relationUserId = relationUserId;
    }

    /**
     * 获取【邀请用户】
     **/
    public User getInviteUser() {
        return inviteUser;
    }

    /**
     * 设置【邀请用户】
     **/
    public void setInviteUser(User inviteUser) {
        this.inviteUser = inviteUser;
    }

    /**
     * 获取【userInfoState(商家排序)1综合排序2安全优先3收益优先】
     **/
    public int getUserInfoState() {
        return userInfoState;
    }

    /**
     * 设置【userInfoState(商家排序)1综合排序2安全优先3收益优先】
     **/
    public void setUserInfoState(int userInfoState) {
        this.userInfoState = userInfoState;
    }

    /**
     * 获取【登录方式1：密码2：短信默认为密码】
     **/
    public int getLoginType() {
        return loginType;
    }

    /**
     * 设置【登录方式1：密码2：短信默认为密码】
     **/
    public void setLoginType(int loginType) {
        this.loginType = loginType;
    }

    public String getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(String isRecommend) {
        this.isRecommend = isRecommend;
    }

    public String getBankCardName() {
        return bankCardName;
    }

    public void setBankCardName(String bankCardName) {
        this.bankCardName = bankCardName;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    /**
     * 获取【用户】
     **/
    public User getUser() {
        return user;
    }

    /**
     * 设置【用户】
     **/
    public void setUser(User user) {
        this.user = user;
    }

    public PageDataList<UserModel> getInviteUserModelList() {
        return inviteUserModelList;
    }

    public void setInviteUserModelList(PageDataList<UserModel> inviteUserModelList) {
        this.inviteUserModelList = inviteUserModelList;
    }

    public Double getReceiveAccount() {
        return receiveAccount;
    }

    public void setReceiveAccount(Double receiveAccount) {
        this.receiveAccount = receiveAccount;
    }

    public Double getInviteAccount() {
        return inviteAccount;
    }

    public void setInviteAccount(Double inviteAccount) {
        this.inviteAccount = inviteAccount;
    }

    public Double getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(Double totalPay) {
        this.totalPay = totalPay;
    }

    public Double getInviteUserTotalPay() {
        return inviteUserTotalPay;
    }

    public void setInviteUserTotalPay(Double inviteUserTotalPay) {
        this.inviteUserTotalPay = inviteUserTotalPay;
    }

    public Integer getGrade() {
        return Grade;
    }

    public void setGrade(Integer grade) {
        Grade = grade;
    }

    public Double getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Double totalCount) {
        this.totalCount = totalCount;
    }

    public UserModel getInviteUserModel() {
        return inviteUserModel;
    }

    public void setInviteUserModel(UserModel inviteUserModel) {
        this.inviteUserModel = inviteUserModel;
    }

    public Integer getInviteUserCount() {
        return inviteUserCount;
    }

    public void setInviteUserCount(Integer inviteUserCount) {
        this.inviteUserCount = inviteUserCount;
    }

    public String getUserAddressDefault() {
        return userAddressDefault;
    }

    public void setUserAddressDefault(String userAddressDefault) {
        this.userAddressDefault = userAddressDefault;
    }

    public Date getLessBirthday() {
        return lessBirthday;
    }

    public void setLessBirthday(Date lessBirthday) {
        this.lessBirthday = lessBirthday;
    }

    public Date getMostBirthday() {
        return mostBirthday;
    }

    public void setMostBirthday(Date mostBirthday) {
        this.mostBirthday = mostBirthday;
    }
}
