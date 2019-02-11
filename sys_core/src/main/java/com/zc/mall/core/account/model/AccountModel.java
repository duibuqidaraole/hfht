package com.zc.mall.core.account.model;

import com.zc.mall.core.account.entity.Account;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.model.jpa.Page;
import com.zc.sys.common.util.calculate.BigDecimalUtil;
import org.springframework.beans.BeanUtils;

/**
 * 资金账户
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public class AccountModel extends Account {
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
     * 用户Id
     **/
    private long userId;
    /**
     * 用户手机号或姓名
     **/
    private String mobileOrRealName;

    public AccountModel() {
        super();
    }

    /**
     * @param id
     * @param total        总额
     * @param balance      余额
     * @param freezeAmount 冻结
     */
    public AccountModel(long id, double total, double balance, double freezeAmount) {
        this.setId(id);
        this.setTotal(total);
        this.setBalance(balance);
        this.setFreezeAmount(freezeAmount);
    }

    /**
     * 实体转换model
     */
    public static AccountModel instance(Account account) {
        if (account == null || account.getId() <= 0) {
            return null;
        }
        AccountModel accountModel = new AccountModel();
        BeanUtils.copyProperties(account, accountModel);
        return accountModel;
    }

    /**
     * model转换实体
     */
    public Account prototype() {
        Account account = new Account();
        BeanUtils.copyProperties(this, account);
        return account;
    }

    /**
     * 初始化注册用户账户信息
     *
     * @param model
     */
    public void initReg(UserModel model) {
        double doubleZero = BigDecimalUtil.round(0);
        this.setTotal(doubleZero);
        this.setBalance(doubleZero);
        this.setFreezeAmount(doubleZero);
        this.setVersion(0);
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


    @Override
    public String toString() {
        return "AccountModel [id=" + this.getId() +
                ", total=" + this.getTotal() +
                ", balance=" + this.getBalance() +
                ", freezeAmount=" + this.getFreezeAmount() +
                "]";
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
     * 获取【用户手机号或姓名】
     **/
    public String getMobileOrRealName() {
        return mobileOrRealName;
    }


    /**
     * 设置【用户手机号或姓名】
     **/
    public void setMobileOrRealName(String mobileOrRealName) {
        this.mobileOrRealName = mobileOrRealName;
    }


}