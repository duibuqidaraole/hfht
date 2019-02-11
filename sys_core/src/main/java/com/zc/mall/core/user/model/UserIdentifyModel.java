package com.zc.mall.core.user.model;

import com.zc.mall.core.account.model.BankCardModel;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.common.constant.BaseConstant.OrderNid;
import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.manage.entity.OrderTask;
import com.zc.mall.core.user.dao.UserIdentifyDao;
import com.zc.mall.core.user.dao.UserInfoDao;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.entity.UserIdentify;
import com.zc.mall.core.user.entity.UserInfo;
import com.zc.mall.core.user.service.UserIdentifyService;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.model.jpa.Page;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.beans.BeanUtils;

/**
 * 用户认证
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public class UserIdentifyModel extends UserIdentify {
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
     * 姓名
     **/
    private String realName;
    /**
     * 企业名称
     **/
    private String companyName;
    /**
     * 证件号码
     **/
    private String cardNo;
    /**
     * 证件类型
     **/
    private int cardType;
    /**
     * 手机号
     **/
    private String mobile;
    /**
     * 订单
     **/
    private OrderTask orderTask;
    /**
     * 重复标识
     **/
    private String token;
    /**
     * 用户Model
     **/
    private UserModel userModel;
    /**
     * 用户Id
     **/
    private long userId;
    /**
     * 接口对象
     **/
    private Object payReg;
    /**
     * 银行卡号
     **/
    private String bankCardNo;
    /**
     * 银行卡号
     **/
    private String bankName;

    /**
     * 备注
     **/
    private String remark;

    public UserIdentifyModel() {
        super();
    }

    /**
     * 认证
     *
     * @param user     用户
     * @param realName 姓名
     * @param cardNo   身份证
     * @param cardType 证件类型
     */
    public UserIdentifyModel(User user, String realName, String cardNo, int cardType) {
        this.setUser(user);
        this.setRealName(realName);
        this.setCardNo(cardNo);
        this.setCardType(cardType);
    }


    public UserIdentifyModel(String mobile) {
        this.setMobile(mobile);
    }

    /**
     * 实体转换model
     */
    public static UserIdentifyModel instance(UserIdentify userIdentify) {
        if (userIdentify == null || userIdentify.getId() <= 0) {
            return null;
        }
        UserIdentifyModel userIdentifyModel = new UserIdentifyModel();
        BeanUtils.copyProperties(userIdentify, userIdentifyModel);
        return userIdentifyModel;
    }

    /**
     * model转换实体
     */
    public UserIdentify prototype() {
        UserIdentify userIdentify = new UserIdentify();
        BeanUtils.copyProperties(this, userIdentify);
        return userIdentify;
    }

    /**
     * 设置修改基本参数
     *
     * @param userIdentify
     */
    public void setUpdateParam(UserIdentify userIdentify) {
        userIdentify.setEmailState(this.getEmailState());
        userIdentify.setCardImgState(this.getCardImgState());
        userIdentify.seteSignState(this.geteSignState());
        userIdentify.setMobileState(this.getMobileState());
        userIdentify.setRealNameState(this.getRealNameState());
        this.setIsRecommend(BaseConstant.INFO_STATE_NO);
        userIdentify.setState(this.getState());
        userIdentify.setCloudMoulageState(this.getState());
        userIdentify.setLoginFailCount(this.getLoginFailCount());
        userIdentify.setOctopusState(this.getOctopusState());
        userIdentify.setRealNameCount(this.getRealNameCount());
        userIdentify.setBindCardNum(this.getBindCardNum());
        userIdentify.setUser(this.getUser());
    }

    /**
     * 初始化注册用户认证信息
     *
     * @param model
     */
    public void initReg(UserModel model) {
        this.setRealNameState(BaseConstant.IDENTIFY_STATE_NO);
        this.setEmailState(BaseConstant.IDENTIFY_STATE_NO);
        if (model.getMobileState() == 0) {
            model.setMobileState(BaseConstant.IDENTIFY_STATE_NO);
        }
        this.setMobileState(BaseConstant.IDENTIFY_STATE_YES);
        this.setBindCardNum(0);
        this.setRealNameCount(0);
        this.setCloudMoulageState(BaseConstant.IDENTIFY_STATE_NO);
        this.setCardImgState(BaseConstant.IDENTIFY_STATE_NO);
        this.setOctopusState(BaseConstant.IDENTIFY_STATE_NO);
        this.setIsRecommend(BaseConstant.INFO_STATE_NO);
        this.setState(BaseConstant.INFO_STATE_YES);//启用状态
        this.setLoginFailCount(0);
        this.seteSignState(BaseConstant.IDENTIFY_STATE_NO);
    }

    /**
     * 实名信息校验
     */
    public void checkRealName(UserIdentifyModel model) {
//		CommonService commonService = (CommonService)BeanUtil.getBean(CommonService.class);
//		commonService.checkToken(this.token);
        if (this.getUserId() <= 0) {
            throw new BusinessException("参数错误");
        }
        if (StringUtil.isBlank(this.realName)) {
            throw new BusinessException("实名信息不能为空");
        }
        if (this.cardType <= 0) {
            throw new BusinessException("参数错误");
        }
        //证件银行卡卡号校验
        new BankCardModel().checkBindBC(model);
        //证件格式校验
        this.checkCard(this.cardType, this.cardNo);
        //判断证件号是否存在
        if (checkCardNoExist(this.cardNo)) {
            throw new BusinessException("证件号已存在");
        }

    }

    /**
     * 实名信息校验-法人
     */
    public void checkRealNameArtif() {
//		CommonService commonService = (CommonService)BeanUtil.getBean(CommonService.class);
//		commonService.checkToken(this.token);
        UserInfoDao userInfoDao = (UserInfoDao) BeanUtil.getBean(UserInfoDao.class);
        UserInfo useInfor = userInfoDao.findObjByProperty("user.id", this.getUserId());
        if (this.getUserId() <= 0) {
            throw new BusinessException("参数错误");
        }
        if (StringUtil.isBlank(this.companyName)) {
            throw new BusinessException("企业名称不能为空");
        }
        if (StringUtil.isBlank(this.realName)) {
            throw new BusinessException("实名信息不能为空");
        }
        if (this.cardType <= 0) {
            throw new BusinessException("参数错误");
        }

        this.checkCard(this.cardType, this.cardNo);//证件格式校验
        if (checkCardNoExist(this.cardNo)) {//判断证件号是否存在
            throw new BusinessException("证件号已存在");
        }
    }

    /**
     * 判断证件号是否存在
     *
     * @param cardNo
     * @return
     */
    public boolean checkCardNoExist(String cardNo) {
        UserIdentifyDao userIdentifyDao = BeanUtil.getBean(UserIdentifyDao.class);
        UserIdentifyModel userIdentifyModel = new UserIdentifyModel();
        userIdentifyModel.setCardNo(cardNo);
        userIdentifyModel.setRealNameState(BaseConstant.IDENTIFY_STATE_YES);
        int count = userIdentifyDao.countByModel(userIdentifyModel);
        return count > 0 ? true : false;
    }


    /**
     * 证件格式校验
     */
    public void checkCard(int cardType, String cardNo) {
        switch (this.cardType) {
            case BaseConstant.CARD_TYPE_IDENTIFY_CARD://二代身份证
                if (!StringUtil.isCard(cardNo)) {
                    throw new BusinessException("证件格式错误");
                }
                break;
            default:
                throw new BusinessException("参数有误");
        }
    }

    /**
     * 初始化实名认证
     *
     * @param userIdentify
     */
    public void initRealName(UserIdentify userIdentify) {
        userIdentify.setRealNameCount(userIdentify.getRealNameCount() + 1);//认证次数+1
        userIdentify.setRealNameState(BaseConstant.BUSINESS_STATE_YES);
        userIdentify.setBindCardNum(userIdentify.getBindCardNum() + 1);
        User user = userIdentify.getUser();
        user.setRealName(this.getRealName());
        user.setCardNo(this.getCardNo());

        UserInfo userInfo = user.getUserInfo();
        userInfo.setCardType(this.getCardType());
        userInfo.setCompanyName(this.getCompanyName());
        user.setUserInfo(userInfo);

        userIdentify.setUser(user);
    }

    /**
     * 任务分发
     */
    public void doQueue() {
        UserIdentifyService userIdentifyService = BeanUtil.getBean(UserIdentifyService.class);
        if (OrderNid.ORDER_NID_USER_REALNAME.getNid().equals(this.orderTask.getType())) {
            userIdentifyService.realNameDeal(this);
        }
    }

    public void checkRealNameDeal(UserIdentify userIdentify) {
        if (userIdentify.getRealNameState() != BaseConstant.IDENTIFY_STATE_NO) {
            throw new BusinessException("实名认证状态有误！");
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
     * 获取【姓名】
     **/
    public String getRealName() {
        return realName;
    }

    /**
     * 设置【姓名】
     **/
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 获取【证件号码】
     **/
    public String getCardNo() {
        return cardNo;
    }

    /**
     * 设置【证件号码】
     **/
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    /**
     * 获取【证件类型】
     **/
    public int getCardType() {
        return cardType;
    }

    /**
     * 设置【证件类型】
     **/
    public void setCardType(int cardType) {
        this.cardType = cardType;
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
     * 获取【手机号】
     **/
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置【手机号】
     **/
    public void setMobile(String mobile) {
        this.mobile = mobile;
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
     * 获取【备注】
     **/
    public String getRemark() {
        return remark;
    }

    /**
     * 设置【备注】
     **/
    public void setRemark(String remark) {
        this.remark = remark;
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
     * 获取【接口对象】
     **/
    public Object getPayReg() {
        return payReg;
    }

    /**
     * 设置【接口对象】
     **/
    public void setPayReg(Object payReg) {
        this.payReg = payReg;
    }

    /**
     * 获取【企业名称】
     **/
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置【企业名称】
     **/
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 获取【银行卡号】
     **/
    public String getBankCardNo() {
        return bankCardNo;
    }

    /**
     * 设置【银行卡号】
     **/
    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}