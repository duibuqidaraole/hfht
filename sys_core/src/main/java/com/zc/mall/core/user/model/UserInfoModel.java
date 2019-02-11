package com.zc.mall.core.user.model;

import com.zc.mall.core.account.model.BankCardModel;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.common.constant.BaseConstant.OrderNid;
import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.manage.entity.OrderTask;
import com.zc.mall.core.user.dao.UserDao;
import com.zc.mall.core.user.dao.UserIdentifyDao;
import com.zc.mall.core.user.dao.UserInfoDao;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.entity.UserIdentify;
import com.zc.mall.core.user.entity.UserInfo;
import com.zc.mall.core.user.service.UserInfoService;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.model.jpa.Page;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.beans.BeanUtils;

/**
 * 用户信息
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public class UserInfoModel extends UserInfo {
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
     * 用户Model
     **/
    private UserModel userModel;
    /**
     * 用户信息Model
     **/
    private UserIdentifyModel userIdentifyModel;
    /**
     * 用户Id
     **/
    private long userId;
    /**
     * 作为查询排序条件
     **/
    private int state;
    /**
     * 订单
     **/
    private OrderTask orderTask;
    /**
     * 用戶总积分
     **/
    private double userTotleIntegralAcount;

    private BankCardModel bankCardModel;
    /**
     * 邀请用户model实体 *
     */
    private UserModel inviteUserModel;

    public UserInfoModel() {
        super();
    }

    public UserInfoModel(User user) {
        super(user);
    }

    public UserIdentifyModel getUserIdentifyModel() {
        return userIdentifyModel;
    }

    public void setUserIdentifyModel(UserIdentifyModel userIdentifyModel) {
        this.userIdentifyModel = userIdentifyModel;
    }

    /**
     * 实体转换model
     */
    public static UserInfoModel instance(UserInfo userInfo) {
        if (userInfo == null || userInfo.getId() <= 0) {
            return null;
        }
        UserInfoModel userInfoModel = new UserInfoModel();
        BeanUtils.copyProperties(userInfo, userInfoModel);
        return userInfoModel;
    }

    /**
     * model转换实体
     */
    public UserInfo prototype() {
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(this, userInfo);
        return userInfo;
    }

    /**
     * 设置修改基本参数
     *
     * @param userInfo
     */
    public void setUpdateParam(UserInfo userInfo) {
        userInfo.setAddIp(this.getAddIp());
        userInfo.setAddress(this.getAddress());
        userInfo.setAdIdentifier(this.getAdIdentifier());
        userInfo.setArea(this.getAdIdentifier());
        userInfo.setCardBg(this.getCardBg());
        userInfo.setCardFg(this.getCardFg());
        userInfo.setCardType(this.getCardType());
        userInfo.setChannel(this.getChannel());
        userInfo.setCity(this.getCity());
        userInfo.setCompanyCardNo(this.getCompanyCardNo());
        userInfo.setCompanyDes(this.getCompanyDes());
        userInfo.setCompanyName(this.getCompanyName());
        userInfo.setCompanyType(this.getCompanyType());
        userInfo.setElecAcct(this.getElecAcct());
        userInfo.seteSignAccountId(this.geteSignAccountId());
        userInfo.setHeadImg(this.getHeadImg());
        userInfo.setZmxyOpenId(this.getZmxyOpenId());
        userInfo.setUserNature(this.getUserNature());
        userInfo.setUser(this.getUser());
        userInfo.setType(this.getType());
        userInfo.setSex(this.getSex());
        userInfo.setRoute(this.getRoute());
        userInfo.setProvince(this.getProvince());
        userInfo.setInviteCode(this.getInviteCode());
        userInfo.setInviteUser(this.getInviteUser());
        userInfo.setLoginLockRemark(this.getLoginLockRemark());
        userInfo.seteSignSealData(this.geteSignSealData());
        userInfo.setElecAcct(this.getElecAcct());
        userInfo.setRemark(this.getRemark());
        userInfo.setCompanyOtherPic(this.getCompanyOtherPic());
        userInfo.setCompanyRulesPic(this.getCompanyRulesPic());
        userInfo.setCompanyBusinessLicensePic(this.getCompanyBusinessLicensePic());
        userInfo.setCompanyRecommendDec(this.getCompanyRecommendDec());
        userInfo.setCompanyTelephone(this.getCompanyTelephone());
        userInfo.setCompanyBusinessState(this.getCompanyBusinessState());
        userInfo.setCompanyBusinessPeriod(this.getCompanyBusinessPeriod());
        userInfo.setCompanyBusinessAddress(this.getCompanyBusinessAddress());
        userInfo.setCompanyRealRegisteredCapital(this.getCompanyRealRegisteredCapital());
        userInfo.setCompanyRegisteredCapital(this.getCompanyRegisteredCapital());
        userInfo.setCompanyLogoPic(this.getCompanyLogoPic());
        userInfo.setCompanyProjectType(this.getCompanyProjectType());
        userInfo.setCompanyProjectDes(this.getCompanyProjectDes());
        userInfo.setLegalCardNo(this.getLegalCardNo());
        userInfo.setLegalName(this.getLegalName());
        userInfo.setCompanyCashRule(this.getCompanyCashRule());
        userInfo.setCompanySafety(this.getCompanySafety());
        userInfo.setCompanySafetyType(this.getCompanySafetyType());
        userInfo.setCompanyAddTime(this.getCompanyAddTime());
        userInfo.setCompanyRateMost(this.getCompanyRateMost());
        userInfo.setCompanyRateLowest(this.getCompanyRateLowest());
        userInfo.setCompanyPeriodMost(this.getCompanyPeriodMost());
        userInfo.setCompanyPeriodLowest(this.getCompanyPeriodLowest());
        userInfo.setRemark(this.getRemark());
    }


    public UserInfo setUpdateParams(UserModel model) {
        UserDao userDao = BeanUtil.getBean(UserDao.class);
        User user = userDao.find(model.getId());
        UserInfo userInfo = user.getUserInfo();
        userInfo.setAddress(model.getAddress());
        userInfo.setCompanyName(model.getCompanyName());
        userInfo.setLegalName(model.getLegalName());
        userInfo.setCompanyType(model.getCompanyType());
        userInfo.setCompanyCashRule(model.getCompanyCashRule());
        userInfo.setCompanySafety(model.getCompanySafety());
        userInfo.setCompanySafetyType(model.getCompanySafetyType());
        userInfo.setCompanyAddTime(model.getCompanyAddTime());
        userInfo.setCompanyRegisteredCapital(model.getCompanyRegisteredCapital());
        userInfo.setCompanyRealRegisteredCapital(model.getCompanyRealRegisteredCapital());
        userInfo.setCompanyRecommendDec(model.getCompanyRecommendDec());
        userInfo.setCompanyLogoPic(model.getCompanyLogoPic());
        userInfo.setCompanyBusinessPeriod(model.getCompanyBusinessPeriod());
        userInfo.setCompanyCardNo(model.getCompanyCardNo());
        userInfo.setCompanyBusinessAddress(model.getCompanyBusinessAddress());
        userInfo.setCompanyBusinessPeriod(model.getCompanyBusinessPeriod());
        userInfo.setCompanyDes(model.getCompanyDes());
        userInfo.setCompanyCashRule(model.getCompanyCashRule());
        userInfo.setCompanyProjectDes(model.getCompanyProjectDes());
        userInfo.setCompanyBusinessLicensePic(model.getCompanyBusinessLicensePic());
        userInfo.setCompanyBusinessState(model.getCompanyBusinessState());
        userInfo.setCompanyProjectType(model.getCompanyProjectType());
        userInfo.setCompanyRulesPic(model.getCompanyRulesPic());
        userInfo.setCompanyOtherPic(model.getCompanyOtherPic());
        userInfo.setCompanyTelephone(model.getCompanyTelephone());
        userInfo.setAddIp(userInfo.getAddIp());
        userInfo.setLegalCardNo(model.getLegalCardNo());
        userInfo.setCompanyRateMost(model.getCompanyRateMost());
        userInfo.setCompanyRateLowest(model.getCompanyRateLowest());
        userInfo.setCompanyPeriodMost(model.getCompanyPeriodMost());
        userInfo.setCompanyPeriodLowest(model.getCompanyPeriodLowest());
        if (model.getType() == BaseConstant.USER_TYPE_BUSINESS) {
            userInfo.setUserNature(userInfo.getUserNature());
            userInfo.setType(userInfo.getType());
        } else {
            userInfo.setUserNature(model.getUserNature());
            userInfo.setType(model.getType());
        }

        return userInfo;
    }

    /**
     * 设置修改商家用户信息基本参数
     *
     * @param userInfo
     */
    public void setRelationUserInfoParam(UserInfo userInfo) {
        UserIdentifyDao userIdentifyDao = BeanUtil.getBean(UserIdentifyDao.class);
        UserIdentify userIdentify = userIdentifyDao.findObjByProperty("user.id", this.getUserId());
        userIdentify.setState(this.getState());
        userIdentifyDao.update(userIdentify);
        userInfo.setCompanyName(this.getCompanyName());
        userInfo.setLegalName(this.getLegalName());
        userInfo.setLegalCardNo(this.getLegalCardNo());
        userInfo.setCompanyCardNo(this.getCompanyCardNo());


    }

    /**
     * 初始化注册用户基本信息
     *
     * @param model
     */
    public void initReg(UserModel model) {
        UserInfoDao userInfoDao = BeanUtil.getBean(UserInfoDao.class);
        UserDao userDao = BeanUtil.getBean(UserDao.class);
        //邀请用户
        if (!StringUtil.isBlank(model.getInviteCode())) {
            UserInfo userInfo = (UserInfo) userInfoDao.findObjByProperty("inviteCode", model.getInviteCode());
            User user = (User) userDao.findObjByProperty("mobile", model.getInviteCode());
            if (userInfo == null) {
                throw new BusinessException("邀请码不存在");
            }
            if (user == null) {
                throw new BusinessException("邀请人不存在");
            }
            this.setInviteUser(user);

        }
        if (model.getType() == 0) {
            model.setType(1);//普通用户
        }
        if (model.getUserNature() == 0) {
            model.setUserNature(1);//个人用户
        }
        if (model.getRoute() == 0) {
            model.setRoute(0);//PC渠道
        }
        if (model.getChannel() == null) {
            model.setChannel("0");//推广途径
        }
        if (model.getRelationUserId() > 0) {
            this.setType(model.getType());
            this.setUserNature(model.getUserNature());
        } else {
            if (!StringUtil.isBlank(model.getType()) && model.getType() == BaseConstant.USER_TYPE_BUSINESS) {
                this.setAddress(model.getAddress());
                this.setCompanyName(model.getCompanyName());
                this.setLegalName(model.getLegalName());
                this.setCompanyType(model.getCompanyType());
                this.setCompanyProjectType(model.getCompanyProjectType());
                this.setCompanyRegisteredCapital(model.getCompanyRegisteredCapital());
                this.setCompanyRealRegisteredCapital(model.getCompanyRealRegisteredCapital());
                this.setCompanyRecommendDec(model.getCompanyRecommendDec());
                this.setCompanyLogoPic(model.getCompanyLogoPic());
                this.setCompanyBusinessPeriod(model.getCompanyBusinessPeriod());
                this.setCompanyCardNo(model.getCompanyCardNo());
                this.setCompanyBusinessAddress(model.getCompanyBusinessAddress());
                this.setCompanyBusinessPeriod(model.getCompanyBusinessPeriod());
                this.setCompanyDes(model.getCompanyDes());
                this.setLegalCardNo(model.getLegalCardNo());
                this.setCompanyCashRule(model.getCompanyCashRule());
                this.setCompanySafety(model.getCompanySafety());
                this.setCompanySafetyType(model.getCompanySafetyType());
                this.setLegalName(model.getLegalName());
                this.setCompanyAddTime(model.getCompanyAddTime());
                this.setCompanyProjectDes(model.getCompanyProjectDes());
                this.setCompanyBusinessLicensePic(model.getCompanyBusinessLicensePic());
                this.setCompanyBusinessState(model.getCompanyBusinessState());
                this.setCompanyProjectType(model.getCompanyProjectType());
                this.setCompanyRulesPic(model.getCompanyRulesPic());
                this.setCompanyOtherPic(model.getCompanyOtherPic());
                this.setCompanyTelephone(model.getCompanyTelephone());
                this.setCompanyRateMost(model.getCompanyRateMost());
                this.setCompanyRateLowest(model.getCompanyRateLowest());
                this.setCompanyPeriodMost(model.getCompanyPeriodMost());
                this.setCompanyPeriodLowest(model.getCompanyPeriodLowest());
                this.setUserNature(BaseConstant.USER_NATURE_ARTIF);
                this.setAddIp(model.getAddIp());//获取ip
                this.setType(BaseConstant.USER_TYPE_BUSINESS);
            } else {
                this.setType(BaseConstant.USER_TYPE_GENERAL);
                this.setUserNature(model.getUserNature());
            }
        }
        this.setInviteCode(StringUtil.isNull(this.getUser().getId()));
        this.setRoute(model.getRoute());
        this.setChannel(model.getChannel());
        this.setAddIp(model.getAddIp());//获取ip
    }

    /**
     * 任务分发
     */
    public void doQueue() {
        UserInfoService userInfoService = BeanUtil.getBean(UserInfoService.class);
        if (OrderNid.ORDER_NID_USER_INFO_UPDATE.getNid().equals(this.orderTask.getType())) {
            userInfoService.updateInfo(this);
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
     * 获取【用户Model】
     **/
    public UserModel getUserModel() {
        return userModel;
    }


    /**
     * 设置【用户Model】
     **/
    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }


    /**
     * 获取【邀请用户model实体】
     **/
    public UserModel getInviteUserModel() {
        return inviteUserModel;
    }


    /**
     * 设置【邀请用户model实体】
     **/
    public void setInviteUserModel(UserModel inviteUserModel) {
        this.inviteUserModel = inviteUserModel;
    }

    /**
     * 获取【用户Id】
     **/
    public long getUserId() {
        return userId;
    }


    /**
     * 设置【用户Id】
     **/
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * 获取【订单】
     **/
    public OrderTask getOrderTask() {
        return orderTask;
    }

    /**
     * 设置【订单】
     **/
    public void setOrderTask(OrderTask orderTask) {
        this.orderTask = orderTask;
    }

    /**
     * 获取【查询用户条件】
     **/
    public int getState() {
        return state;
    }

    /**
     * 设置【查询用户条件】
     **/
    public void setState(int state) {
        this.state = state;
    }

    public double getUserTotleIntegralAcount() {
        return userTotleIntegralAcount;
    }

    public void setUserTotleIntegralAcount(double userTotleIntegralAcount) {
        this.userTotleIntegralAcount = userTotleIntegralAcount;
    }

    public BankCardModel getBankCardModel() {
        return bankCardModel;
    }

    public void setBankCardModel(BankCardModel bankCardModel) {
        this.bankCardModel = bankCardModel;
    }


    public void initOpenIdReg(UserModel model) {
        if (model.getUserInfo() != null) {
            if (StringUtil.isNotBlank(model.getUserInfo().getProvince())) {
                this.setProvince(model.getUserInfo().getProvince());
            }
            if (StringUtil.isNotBlank(model.getUserInfo().getCity())) {
                this.setCity(model.getUserInfo().getCity());
            }
            if (StringUtil.isNotBlank(model.getUserInfo().getArea())) {
                this.setArea(model.getUserInfo().getArea());
            }
        }

    }
}