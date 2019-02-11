package com.zc.mall.core.integral.model;

import com.zc.mall.core.integral.entity.IntegralAccount;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.model.jpa.Page;
import com.zc.sys.common.util.calculate.BigDecimalUtil;
import org.springframework.beans.BeanUtils;

/**
 * 积分账户
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public class IntegralAccountModel extends IntegralAccount {
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
     * 用户Id
     **/
    private long userId;
    /**
     * UserModel
     **/
    private UserModel userModel;

    public IntegralAccountModel() {
        super();
    }

    /**
     * 更新积分
     *
     * @param id
     * @param totalIntegral   积分总额
     * @param balanceIntegral 积分余额
     * @param expenseIntegral 消费积分
     * @param freezeIntegral  冻结积分
     */
    public IntegralAccountModel(User user, double totalIntegral, double balanceIntegral,
                                double expenseIntegral, double freezeIntegral) {
        this.setUser(user);
        this.setTotalIntegral(totalIntegral);
        this.setBalanceIntegral(balanceIntegral);
        this.setExpenseIntegral(expenseIntegral);
        this.setFreezeIntegral(freezeIntegral);
    }

    /**
     * 更新积分
     *
     * @param id
     * @param balanceIntegral 积分余额
     */
    public IntegralAccountModel(User user, double balanceIntegral) {
        this.setUser(user);
        this.setBalanceIntegral(balanceIntegral);
        this.setTotalIntegral(balanceIntegral);
    }


    /**
     * 实体转换model
     */
    public static IntegralAccountModel instance(IntegralAccount integralAccount) {
        if (integralAccount == null || integralAccount.getId() <= 0) {
            return null;
        }
        IntegralAccountModel integralAccountModel = new IntegralAccountModel();
        BeanUtils.copyProperties(integralAccount, integralAccountModel);
        return integralAccountModel;
    }

    /**
     * model转换实体
     */
    public IntegralAccount prototype() {
        IntegralAccount integralAccount = new IntegralAccount();
        BeanUtils.copyProperties(this, integralAccount);
        return integralAccount;
    }

    /**
     * 注册初始化
     */
    public void initReg(UserModel model) {
        double doubleZero = BigDecimalUtil.round(0);
        this.setTotalIntegral(doubleZero);
        this.setBalanceIntegral(doubleZero);
        this.setExpenseIntegral(doubleZero);
        this.setFreezeIntegral(doubleZero);
        this.setGradeIntegral(0);
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
     * 获取User【model】
     **/
    public UserModel getUserModel() {
        return userModel;
    }

    /**
     * 设置User【model】
     **/
    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
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


}