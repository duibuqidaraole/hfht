
package com.zc.mall.core.account.model;

import com.zc.mall.core.account.dao.AccountDao;
import com.zc.mall.core.account.dao.BankCardDao;
import com.zc.mall.core.account.entity.Account;
import com.zc.mall.core.account.entity.BankCard;
import com.zc.mall.core.account.entity.WithdrawCash;
import com.zc.mall.core.account.service.WithdrawCashService;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.common.constant.BaseConstant.OrderNid;
import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.common.service.CommonService;
import com.zc.mall.core.manage.entity.OrderTask;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.model.jpa.Page;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.http.RequestUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.beans.BeanUtils;

/**
 * 提现
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public class WithdrawCashModel extends WithdrawCash {
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
     * 重复标识
     **/
    private String token;
    /**
     * 用户名
     **/
    private String userName;
    /**
     * 用户 model
     **/
    private UserModel userModel;
    /**
     * 订单信息
     **/
    private OrderTask orderTask;
    /**
     * 接口对象
     **/
    private Object payWithdrawCash;
    /**
     * 开始时间
     **/
    private String startTime;
    /**
     * 结束时间
     **/
    private String endTime;

    /**
     * 实体转换model
     */
    public static WithdrawCashModel instance(WithdrawCash withdrawCash) {
        WithdrawCashModel withdrawCashModel = new WithdrawCashModel();
        BeanUtils.copyProperties(withdrawCash, withdrawCashModel);
        return withdrawCashModel;
    }

    /**
     * 提现校验
     */
    public void checkWithdrawCash() {
        CommonService commonService = (CommonService) BeanUtil.getBean(CommonService.class);
        commonService.checkToken(this.getToken());
        AccountDao accountDao = (AccountDao) BeanUtil.getBean(AccountDao.class);
        Account account = accountDao.findObjByProperty("user.id", this.getUserId());
        if (this.getUserId() <= 0) {
            throw new BusinessException("参数错误");
        }
        if (account.getBalance() < this.getAmount()) {
            throw new BusinessException("提现金额不能大于可提现金额");
        }
        if (this.getAmount() < 0) {
            throw new BusinessException("参数错误");
        }

    }


    /**
     * 提现审核校验
     */
    public void checkAuditWithdraw() {
        if (this.getId() < 0) {
            throw new BusinessException("参数错误");
        }

    }

    /**
     * 提现审核初始化
     */
    public void initAuditWithdraw(WithdrawCash withdrawCash) {
        withdrawCash.setState(this.getState());
        withdrawCash.setRemark(this.getRemark());
        withdrawCash.setId(this.getId());
        this.setAmount(this.getAmount());

    }

    /**
     * model转换实体
     */
    public WithdrawCash prototype() {
        WithdrawCash withdrawCash = new WithdrawCash();
        BeanUtils.copyProperties(this, withdrawCash);
        return withdrawCash;
    }

    /**
     * 提现失败处理
     *
     * @param recharge
     */
    public void initWithdrawCashFailDeal(WithdrawCash withdrawCash) {
        withdrawCash.setState(this.getState());
        withdrawCash.setResultMsg("提现处理失败");
        withdrawCash.setRemark(this.getRemark());
    }

    /**
     * 提现成功处理
     *
     * @param recharge
     */
    public void initWithdrawCashSuccessDeal(WithdrawCash withdrawCash) {
        withdrawCash.setState(this.getState());
        withdrawCash.setResultMsg("提现处理成功");
        withdrawCash.setRemark(this.getRemark());
    }


    /**
     * 任务分发
     */
    public void doQueue() {
        WithdrawCashService withdrawCashService = BeanUtil.getBean(WithdrawCashService.class);
        if (OrderNid.ORDER_NID_ACCOUNT_WITHDRAW_CASH.getNid().equals(this.orderTask.getType())) {
            if (this.getState() == BaseConstant.BUSINESS_STATE_YES) {
                withdrawCashService.withdrawSuccessfulDeal(this);
            }
            if (this.getState() == BaseConstant.BUSINESS_STATE_FAIL) {
                withdrawCashService.withdrawFailDeal(this);
            }
        }
    }

    /**
     * 提现初始化
     */
    public void initWithdraw() {
        BankCardDao bankCardDao = (BankCardDao) BeanUtil.getBean(BankCardDao.class);
        BankCard bankCard = bankCardDao.findObjByProperty("user.id", this.getUserId());
        this.setUser(bankCard.getUser());
        this.setBankCardNo(bankCard.getBankCardNo());
        this.setBankCode(bankCard.getBankCode());
        this.setMobile(bankCard.getUser().getMobile());
        this.setFee(0);
        this.setArrivalAmount(this.getAmount() - this.getFee());
        this.setOrderNo(StringUtil.getSerialNumber());
        this.setState(BaseConstant.BUSINESS_STATE_NO);
        this.setAddIp(RequestUtil.getClientIp());
        this.setAddTime(DateUtil.getNow());
        this.setResultMsg("提现申请已提交---待处理！");
        this.setRealName(bankCard.getUser().getRealName());
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
     * 获取【接口对象】
     **/
    public Object getPayWithdrawCash() {
        return payWithdrawCash;
    }

    /**
     * 设置【接口对象】
     **/
    public void setPayWithdrawCash(Object payWithdrawCash) {
        this.payWithdrawCash = payWithdrawCash;
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

    /**
     * 获取【用户名】
     **/
    public String getUserName() {
        return userName;
    }

    /**
     * 设置【用户名】
     **/
    public void setUserName(String userName) {
        this.userName = userName;
    }

}