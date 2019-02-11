package com.zc.mall.core.account.model;

import com.zc.mall.core.account.dao.AccountDeductDao;
import com.zc.mall.core.account.entity.AccountDeduct;
import com.zc.mall.core.account.service.AccountDeductService;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.common.constant.BaseConstant.OrderNid;
import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.manage.dao.OperatorDao;
import com.zc.mall.core.manage.entity.OrderTask;
import com.zc.mall.core.manage.model.OperatorModel;
import com.zc.mall.core.user.dao.UserDao;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.model.jpa.Page;
import com.zc.sys.common.util.calculate.BigDecimalUtil;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.http.RequestUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.beans.BeanUtils;

/**
 * 线下扣款
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public class AccountDeductModel extends AccountDeduct {
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
     * 交易密码
     **/
    private String payPwd;
    /**
     * 管理员id
     **/
    private long operatorId;
    /**
     * 用户id
     **/
    private long userId;
    /**
     * 订单信息
     **/
    private OrderTask orderTask;

    /**
     * 实体转换model
     */
    public static AccountDeductModel instance(AccountDeduct accountDeduct) {
        if (accountDeduct == null || accountDeduct.getId() <= 0) {
            return null;
        }
        AccountDeductModel accountDeductModel = new AccountDeductModel();
        BeanUtils.copyProperties(accountDeduct, accountDeductModel);
        return accountDeductModel;
    }

    /**
     * model转换实体
     */
    public AccountDeduct prototype() {
        AccountDeduct accountDeduct = new AccountDeduct();
        BeanUtils.copyProperties(this, accountDeduct);
        return accountDeduct;
    }


    /**
     * 扣款请求校验
     */
    public void checkDeductRequest() {
        if (StringUtil.isBlank(this.getPayPwd())) {
            throw new BusinessException("参数错误");
        }
        if (!OperatorModel.checkPayPwd(this.getOperatorId(), this.getPayPwd())) {
            throw new BusinessException("管理员交易密码错误");
        }
        if (StringUtil.isBlank(this.getRemark())) {
            throw new BusinessException("备注不能为空");
        }
        switch (this.getType()) {
            case BaseConstant.PAYMENTS_TYPE_EXPENDITURES:// 支出
                if (this.getAmount() >= 0) {
                    throw new BusinessException("金额有误");
                }
                break;
            case BaseConstant.PAYMENTS_TYPE_INCOME:// 收入
                if (this.getAmount() <= 0) {
                    throw new BusinessException("金额有误");
                }
                break;

            default:
                throw new BusinessException("参数错误");
        }
        UserDao userDao = BeanUtil.getBean(UserDao.class);
        User user = userDao.find(this.getUserId());
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        if (user.getUserIdentify().getRealNameState() != BaseConstant.IDENTIFY_STATE_YES) {
            throw new BusinessException("用户未实名认证");
        }
        if (BigDecimalUtil.add(user.getAccount().getBalance(), this.getAmount()) < 0) {
            throw new BusinessException("操作金额有误");
        }
        this.setUser(user);
    }

    /**
     * 扣款请求初始化
     */
    public void initDeductRequest() {
        this.setOrderNo(StringUtil.getSerialNumber());
        this.setState(BaseConstant.BUSINESS_STATE_WAIT);
        this.setOperator(BeanUtil.getBean(OperatorDao.class).find(this.operatorId));
        this.setAddTime(DateUtil.getNewDate());
        this.setAddIp(RequestUtil.getClientIp());
    }

    /**
     * 扣款处理校验
     *
     * @return
     */
    public AccountDeduct checkDeductDeal() {
        AccountDeductDao accountDeductDao = BeanUtil.getBean(AccountDeductDao.class);
        AccountDeduct accountDeduct = accountDeductDao.find(this.getId());
        if (accountDeduct == null) {
            throw new BusinessException("参数错误");
        }
        if (accountDeduct.getState() != BaseConstant.BUSINESS_STATE_WAIT) {
            throw new BusinessException("状态有误");
        }
        return accountDeduct;
    }

    /**
     * 扣款处理初始化
     *
     * @param accountDeduct
     */
    public void initDeductDeal(AccountDeduct accountDeduct) {
        accountDeduct.setState(BaseConstant.BUSINESS_STATE_YES);
    }

    /**
     * 队列处理
     */
    public void doQueue() {
        AccountDeductService accountDeductService = BeanUtil.getBean(AccountDeductService.class);
        String type = this.getOrderTask().getType();
        if (OrderNid.ORDER_NID_ACCOUNT_DEDUCT.getNid().equals(type)) {
            accountDeductService.deductDeal(this);
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
     * 获取【交易密码】
     **/
    public String getPayPwd() {
        return payPwd;
    }

    /**
     * 设置【交易密码】
     **/
    public void setPayPwd(String payPwd) {
        this.payPwd = payPwd;
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
     * 获取【管理员id】
     **/
    public long getOperatorId() {
        return operatorId;
    }

    /**
     * 设置【管理员id】
     **/
    public void setOperatorId(long operatorId) {
        this.operatorId = operatorId;
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

}