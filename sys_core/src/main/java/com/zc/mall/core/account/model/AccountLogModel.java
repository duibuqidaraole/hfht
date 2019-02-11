package com.zc.mall.core.account.model;

import com.zc.mall.core.account.entity.Account;
import com.zc.mall.core.account.entity.AccountLog;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.sys.model.TemplateModel;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.model.jpa.Page;
import com.zc.sys.common.util.date.DateUtil;
import org.springframework.beans.BeanUtils;

/**
 * 资金日志
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public class AccountLogModel extends AccountLog {
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
     * 关联商家Id
     **/
    private long ralationUserId;
    /**
     * 用户 model
     **/
    private UserModel userModel;
    /**
     * 邀请用户 model*
     */
    private UserModel toUserModel;
    /**
     * 开始时间
     **/
    private String startTime;
    /**
     * 结束时间
     **/
    private String endTime;
    /**
     * 用户名
     **/
    private String userName;
    /**
     * 手机号
     **/
    private String mobile;

    /**
     * 资金日志
     *
     * @param account 账户
     * @param user    用户
     * @param nid     标识
     * @param toUser  目标用户
     * @param amount  金额
     * @param orderNo 订单号
     * @param remark  备注
     * @return
     */
    public AccountLog instanceAccountLog(Account account, User user, String nid, User toUser, double amount, String orderNo, String remark) {
        AccountLog log = new AccountLog();
        log.setAddTime(DateUtil.getNewDate());
        log.setAmount(amount);
        log.setBalance(account.getBalance());
        log.setFreezeAmount(account.getFreezeAmount());
        log.setOrderNo(orderNo);
        log.setRemark(remark);
        log.setTotal(account.getTotal());
        log.setToUser(toUser);
        log.setUser(user);
        log.setType(nid);
        TemplateModel templateModel = new TemplateModel().instance(nid, BaseConstant.TEMPLATE_TYPE_ACCOUNT, 0);
        log.setContent(templateModel.getContent());
        log.setName(templateModel.getName());
        log.setPaymentsType(templateModel.getPaymentsType());
        return log;
    }

    /**
     * 实体转换model
     */
    public static AccountLogModel instance(AccountLog accountLog) {
        if (accountLog == null || accountLog.getId() <= 0) {
            return null;
        }
        AccountLogModel accountLogModel = new AccountLogModel();
        BeanUtils.copyProperties(accountLog, accountLogModel);
        return accountLogModel;
    }

    /**
     * model转换实体
     */
    public AccountLog prototype() {
        AccountLog accountLog = new AccountLog();
        BeanUtils.copyProperties(this, accountLog);
        return accountLog;
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
     * 获取【邀请用户model】
     **/
    public UserModel getToUserModel() {
        return toUserModel;
    }


    /**
     * 设置【邀请用户model】
     **/
    public void setToUserModel(UserModel toUserModel) {
        this.toUserModel = toUserModel;
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
     * 获取【关联商家Id】
     **/
    public long getRalationUserId() {
        return ralationUserId;
    }

    /**
     * 设置【关联商家Id】
     **/
    public void setRalationUserId(long ralationUserId) {
        this.ralationUserId = ralationUserId;
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


}