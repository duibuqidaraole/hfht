package com.zc.mall.core.account.model;

import com.zc.mall.core.account.dao.BankCardDao;
import com.zc.mall.core.account.entity.BankCard;
import com.zc.mall.core.account.service.BankCardService;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.common.constant.BaseConstant.OrderNid;
import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.manage.entity.OrderTask;
import com.zc.mall.core.user.dao.UserDao;
import com.zc.mall.core.user.dao.UserIdentifyDao;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.entity.UserIdentify;
import com.zc.mall.core.user.model.UserIdentifyModel;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.model.jpa.Page;
import com.zc.sys.common.util.calculate.BigDecimalUtil;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.http.RequestUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.beans.BeanUtils;

/**
 * 银行卡
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public class BankCardModel extends BankCard {
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
     * 用户id
     **/
    private long userId;
    /**
     * 订单信息
     **/
    private OrderTask orderTask;
    /**
     * 重复标识
     **/
    private String token;
    /**
     * User model实体
     **/
    private UserModel userModel;
    /**
     * 银行卡id
     **/
    private long targetBankCardId;
    /**
     * 支付实体
     **/
    private Object payObj;

    public BankCardModel() {
        super();
    }

    public BankCardModel(int state, String bankCardNo, long userId) {
        this.setState(state);
        this.setBankCardNo(bankCardNo);
        this.setUser(new User(userId));
    }

    public BankCardModel(int state, long userId) {
        this.setState(state);
        this.setUser(new User(userId));
    }

    /**
     * 初始化并保存银行卡
     *
     * @param user
     * @param bankCardNo
     * @param mobile
     * @param city
     * @param bankCode
     * @param bankName
     */
    public BankCardModel initAndSaveBCModel(User user, String bankCardNo, String mobile, String city, String bankCode, String bankName) {
        BankCard bankCard = this.prototype();
        bankCard.setUser(user);
        bankCard.setBankCardNo(bankCardNo);
        bankCard.setMobile(mobile);
        bankCard.setCity("杭州");
        bankCard.setBankCode("fuioupay");
        bankCard.setBankName("中国广大银行");
        bankCard.setRealName(user.getRealName());
        bankCard.setAutoDeduct(BaseConstant.INFO_STATE_NO);
        bankCard.setAddIp(RequestUtil.getClientIp());
        bankCard.setAddTime(DateUtil.getNow());
        bankCard.setState(BaseConstant.INFO_STATE_YES);
        bankCard.setAmount(0);
        bankCard.setType(BaseConstant.INFO_STATE_NO);
        BankCardDao bankCardDao = BeanUtil.getBean(BankCardDao.class);
        bankCardDao.save(bankCard);
        return BankCardModel.instance(bankCard);
    }


    /**
     * 实体转换model
     */
    public static BankCardModel instance(BankCard bankCard) {
        if (bankCard == null || bankCard.getId() <= 0) {
            return null;
        }
        BankCardModel bankCardModel = new BankCardModel();
        BeanUtils.copyProperties(bankCard, bankCardModel);
        return bankCardModel;
    }

    /**
     * model转换实体
     */
    public BankCard prototype() {
        BankCard bankCard = new BankCard();
        BeanUtils.copyProperties(this, bankCard);
        return bankCard;
    }

    /**
     * 绑卡校验
     */
    public void checkBindBC(UserIdentifyModel model) {
//		CommonService commonService = (CommonService)BeanUtil.getBean(CommonService.class);
//		commonService.checkToken(this.token);
//		UserIdentifyDao userIdentifyDao = (UserIdentifyDao)BeanUtil.getBean(UserIdentifyDao.class);
//		if(this.getUserId()<= 0){
//			throw new BusinessException("参数错误");
//		}
//		UserIdentify userIdentify = userIdentifyDao.findObjByProperty("user.id",this.getUserId());
//		this.setUser(userIdentify.getUser());
//		if(userIdentify.getState() != 1){
//			throw new BusinessException("用户状态异常");
//		}
//		if(userIdentify.getRealNameState() != 1){
//			throw new BusinessException("请先实名认证");
//		}
//		if(userIdentify.getMobileState() != 1){
//			throw new BusinessException("请先手机认证");
//		}
		/*if(userIdentify.getOctopusState() != 1){
			throw new BussinessException("请先认证手机号运行商信息");
		}*/
        if (StringUtil.isBlank(model.getBankCardNo())) {
            throw new BusinessException("请输入银行卡账号");
        }
        if (StringUtil.isBlank(model.getMobile()) || !StringUtil.isPhone(model.getMobile())) {
            throw new BusinessException("请输入正确的银行卡预留手机号");
        }
//		if(StringUtil.isBlank(this.getBankCode())){
//			throw new BusinessException("请输入所属银行卡编码");
//		}
    }

    /**
     * 初始化
     */
    public void initBind() {
        UserDao userDao = (UserDao) BeanUtil.getBean(UserDao.class);
        this.setState(BaseConstant.BUSINESS_STATE_WAIT);//绑卡处理中
        User user = (User) userDao.findObjByProperty("id", this.getUserId());
        this.setUser(user);
        this.setAddIp(RequestUtil.getClientIp());
        this.setAddTime(DateUtil.getNow());
        this.setAmount(BigDecimalUtil.round(0));
        this.setAutoDeduct(0);
    }

    /**
     * 换绑卡校验参数
     */
    public void checkChangeBankCardRequest(BankCard targetBankCard) {
        UserIdentifyDao userIdentifyDao = (UserIdentifyDao) BeanUtil.getBean(UserIdentifyDao.class);
        if (this.getUserId() <= 0) {
            throw new BusinessException("参数错误");
        }
        UserIdentify userIdentify = userIdentifyDao.findObjByProperty("user.id", this.getUserId());
        this.setUser(userIdentify.getUser());
        if (userIdentify.getState() != 1) {
            throw new BusinessException("用户状态异常");
        }
        if (userIdentify.getRealNameState() != 1) {
            throw new BusinessException("请先实名认证");
        }
        if (userIdentify.getMobileState() != 1) {
            throw new BusinessException("请先手机认证");
        }
        if (this.getTargetBankCardId() <= 0) {
            throw new BusinessException("参数错误");
        }
        BankCardDao bankCardDao = BeanUtil.getBean(BankCardDao.class);
        targetBankCard = bankCardDao.find(this.getTargetBankCardId());
        if (targetBankCard == null || targetBankCard.getState() != BaseConstant.BUSINESS_STATE_YES) {
            throw new BusinessException("参数错误");
        }
    }

    /**
     * 任务分发
     */
    public void doQueue() {
        BankCardService bankCardService = BeanUtil.getBean(BankCardService.class);
        if (OrderNid.ORDER_NID_USER_BINDBC.getNid().equals(this.orderTask.getType())) {
            bankCardService.bindBCDeal(this);
        } else if (OrderNid.ORDER_NID_USER_UNBINDBC.getNid().equals(this.orderTask.getType())) {
            bankCardService.unBindBCDeal(this);
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
     * 获取【用户id】
     **/
    public long getUserId() {
        return userId;
    }

    /**
     * 设置【用户id】
     **/
    public void setUserId(long userId) {
        this.userId = userId;
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
     * 获取【银行卡id】
     **/
    public long getTargetBankCardId() {
        return targetBankCardId;
    }

    /**
     * 设置【银行卡id】
     **/
    public void setTargetBankCardId(long targetBankCardId) {
        this.targetBankCardId = targetBankCardId;
    }

    /**
     * 获取【支付实体】
     **/
    public Object getPayObj() {
        return payObj;
    }

    /**
     * 设置【支付实体】
     **/
    public void setPayObj(Object payObj) {
        this.payObj = payObj;
    }

}