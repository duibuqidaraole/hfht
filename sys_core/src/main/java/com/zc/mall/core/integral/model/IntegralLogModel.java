package com.zc.mall.core.integral.model;

import com.zc.mall.core.integral.entity.IntegralLog;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.model.jpa.Page;
import com.zc.sys.common.util.date.DateUtil;
import org.springframework.beans.BeanUtils;

/**
 * 积分日志
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public class IntegralLogModel extends IntegralLog {
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
     * user
     **/
    private UserModel userModel;
    /**
     * 用户id
     **/
    private long userId;
    /**
     * toUser
     **/
    private UserModel toUserModel;
    /**
     * 开始时间
     **/
    private String startTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * 结束时间
     **/
    private String endTime;

    public IntegralLogModel() {
        super();
    }

    /**
     * 积分日志封装
     *
     * @param user            用户
     * @param integral        操作积分
     * @param type            类型
     * @param name            名称
     * @param totalIntegral   总积分
     * @param balanceIntegral 积分余额
     * @param expenseIntegral 消费积分
     * @param freezeIntegral  冻结积分
     * @param toUser          发放用户
     * @param content         内容
     * @param paymentType     收支类型
     * @param orderNo         关联
     * @param remark          备注
     */
    public IntegralLogModel(User user, double integral, String type, String name, double totalIntegral,
                            double balanceIntegral, double expenseIntegral, double freezeIntegral, User toUser, String content,
                            int paymentsType, String orderNo, String remark) {
        this.setUser(user);
        this.setIntegral(integral);
        this.setType(type);
        this.setName(name);
        this.setTotalIntegral(totalIntegral);
        this.setBalanceIntegral(balanceIntegral);
        this.setExpenseIntegral(expenseIntegral);
        this.setFreezeIntegral(freezeIntegral);
        this.setToUser(toUser);
        this.setContent(content);
        this.setPaymentsType(paymentsType);
        this.setOrderNo(orderNo);
        this.setRemark(remark);
        this.setAddTime(DateUtil.getNow());
    }

    /**
     * 实体转换model
     */
    public static IntegralLogModel instance(IntegralLog integralLog) {
        if (integralLog == null || integralLog.getId() <= 0) {
            return null;
        }
        IntegralLogModel integralLogModel = new IntegralLogModel();
        BeanUtils.copyProperties(integralLog, integralLogModel);
        return integralLogModel;
    }

    /**
     * model转换实体
     */
    public IntegralLog prototype() {
        IntegralLog integralLog = new IntegralLog();
        BeanUtils.copyProperties(this, integralLog);
        return integralLog;
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
     * 获取【user】
     **/
    public UserModel getUserModel() {
        return userModel;
    }

    /**
     * 设置【user】
     **/
    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    /**
     * 获取【toUser】
     **/
    public UserModel getToUserModel() {
        return toUserModel;
    }

    /**
     * 设置【toUser】
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

}