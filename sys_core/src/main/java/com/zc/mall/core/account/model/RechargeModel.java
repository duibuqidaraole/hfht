package com.zc.mall.core.account.model;

import com.zc.mall.core.account.constant.AccountConstant;
import com.zc.mall.core.account.entity.Recharge;
import com.zc.mall.core.account.service.RechargeService;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.common.constant.BaseConstant.OrderNid;
import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.common.service.CommonService;
import com.zc.mall.core.manage.entity.OrderTask;
import com.zc.mall.core.user.dao.UserDao;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.model.jpa.Page;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.http.RequestUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.beans.BeanUtils;

/**
 * 充值
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public class RechargeModel extends Recharge {
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
     * 用户 model
     **/
    private UserModel userModel;
    /**
     * 用户id
     **/
    private long userId;
    /**
     * 重复标识
     **/
    private String token;
    /**
     * 订单信息
     **/
    private OrderTask orderTask;
    /**
     * 状态
     **/
    private int state;
    /**
     * 银行卡号
     **/
    private String bankCardNo;
    /**
     * 预留手机号
     **/
    private String mobile;
    /**
     * 预留姓名
     **/
    private String realName;
    /**
     * 接口对象
     **/
    private Object payRecharge;
    /**
     * 开始时间
     **/
    private String startTime;
    /**
     * 结束时间
     **/
    private String endTime;

    /**
     * 充值初始化
     */
    public void initRecharge() {
        UserDao userDao = BeanUtil.getBean(UserDao.class);
        this.setState(BaseConstant.BUSINESS_STATE_WAIT);//充值处理中的状态
        User user = userDao.findObjByProperty("id", this.getUserId());
        this.setOrderNo(StringUtil.getSerialNumber());
        this.setUser(user);
        this.setFee(0);
        this.setArrivalAmount(this.getAmount() - this.getFee());
        this.setAmount(this.getAmount());
        this.setAddIp(RequestUtil.getClientIp());
        this.setAddTime(DateUtil.getNow());
    }


    /**
     * 充值校验
     */
    public void checkRecharge() {
        CommonService commonService = (CommonService) BeanUtil.getBean(CommonService.class);
        commonService.checkToken(this.getToken());
//		RechargeDao rechargeDao = (RechargeDao)BeanUtil.getBean(RechargeDao.class);
//		BankCard bankCard=rechargeDao.findRechargeByBankCard(this);
        if (this.getUserId() <= 0) {
            throw new BusinessException("参数错误");
        }
        if (this.getAmount() < 0) {
            throw new BusinessException("参数错误");
        }
//		if(this.getArrivalAmount()<0){
//			throw new BusinessException("充值失败");
//		}
        if (this.getWay() <= 0) {
            throw new BusinessException("参数错误");
        }
        if (this.getWay() == AccountConstant.ACCOUNT_RECHARGE_WAY_ONLINE_BANK && StringUtil.isBlank(this.getBankCode())) {
            throw new BusinessException("银行卡编码不能为空");
        }
        if (this.getWay() == AccountConstant.ACCOUNT_RECHARGE_WAY_PC_QUICK && StringUtil.isBlank(this.getBankCardNo())) {
            throw new BusinessException("银行卡卡号不能为空");
        }
    }

    /**
     * 实体转换model
     */
    public static RechargeModel instance(Recharge recharge) {
        if (recharge == null || recharge.getId() <= 0) {
            return null;
        }
        RechargeModel rechargeModel = new RechargeModel();
        BeanUtils.copyProperties(recharge, rechargeModel);
        return rechargeModel;
    }

    /**
     * model转换实体
     */
    public Recharge prototype() {
        Recharge recharge = new Recharge();
        BeanUtils.copyProperties(this, recharge);
        return recharge;
    }

    /**
     * 处理
     *
     * @param recharge
     */
    public void initRechargeDeal(Recharge recharge) {
        recharge.setState(BaseConstant.BUSINESS_STATE_YES);
    }

    /**
     * 任务分发
     */
    public void doQueue() {
        RechargeService rechargeService = BeanUtil.getBean(RechargeService.class);
        if (OrderNid.ORDER_NID_ACCOUNT_RECHARGE.getNid().equals(this.orderTask.getType())) {
            rechargeService.rechargeDeal(this);
        }
    }

    /**
     * 处理校验
     *
     * @param recharge
     */
    public void checkRechargeDeal(Recharge recharge) {
        if (recharge == null) {
            throw new BusinessException("充值信息有误");
        }
        if (recharge.getState() != BaseConstant.BUSINESS_STATE_WAIT) {
            throw new BusinessException("充值信息有误");
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
     * 获取【用户model】
     **/
    public UserModel getUserModel() {
        return userModel;
    }

    /**
     * 设置【用户model】
     **/
    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
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
     * 获取【状态】
     **/
    public int getState() {
        return state;
    }

    /**
     * 设置【状态】
     **/
    public void setState(int state) {
        this.state = state;
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

    /**
     * 获取【预留手机号】
     **/
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置【预留手机号】
     **/
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取【预留姓名】
     **/
    public String getRealName() {
        return realName;
    }

    /**
     * 设置【预留姓名】
     **/
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 获取【接口对象】
     **/
    public Object getPayRecharge() {
        return payRecharge;
    }

    /**
     * 设置【接口对象】
     **/
    public void setPayRecharge(Object payRecharge) {
        this.payRecharge = payRecharge;
    }


    /**
     * 获取【开始时间】
     **/
    public String getStartTime() {
        return startTime;
    }


    /**
     * 设置【开始时间】
     **/
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }


    /**
     * 获取【结束时间】
     **/
    public String getEndTime() {
        return endTime;
    }


    /**
     * 设置【结束时间】
     **/
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


}