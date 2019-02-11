package com.zc.mall.core.integral.service.work;

import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.common.global.Global;
import com.zc.mall.core.integral.dao.IntegralAccountDao;
import com.zc.mall.core.integral.entity.IntegralAccount;
import com.zc.mall.core.integral.model.IntegralAccountModel;
import com.zc.mall.core.integral.model.IntegralLogModel;
import com.zc.mall.core.integral.service.IntegralAccountService;
import com.zc.mall.core.integral.service.IntegralLogService;
import com.zc.mall.core.sys.model.TemplateModel;
import com.zc.mall.core.user.entity.User;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.util.log.LogUtil;
import org.springframework.transaction.annotation.Transactional;

/**
 * 积分处理
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public class IntegralWork {

    /**
     * 账户model
     **/
    private IntegralAccountModel integralAccountModel;
    /**
     * 日志model
     **/
    private IntegralLogModel integralLogModel;
    /**
     * 日志模板
     **/
    private TemplateModel templateModel;
    /**
     * 目标用户
     **/
    private User user;
    /**
     * 发放用户
     **/
    private User toUser;
    /**
     * 操作积分
     **/
    private double integral;
    /**
     * 标识
     **/
    private String nid;
    /**
     * 关联信息
     **/
    private String orderNo;

    /**
     * 初始化
     *
     * @return
     */
    public static IntegralWork instantce() {
        return new IntegralWork();
    }

    /**
     * 积分处理
     *
     * @param nid     标识
     * @param user    目标用户
     * @param orderNo 关联信息
     */
    public void doIntegral(String nid, User user, String orderNo) {
        doIntegral(nid, user, orderNo, 0, null);
    }

    /**
     * 积分处理
     *
     * @param nid      标识
     * @param user     目标用户
     * @param orderNo  关联信息
     * @param integral 操作积分
     */
    public void doIntegral(String nid, User user, String orderNo, double integral) {
        doIntegral(nid, user, orderNo, integral, null);
    }

    /**
     * 积分处理
     *
     * @param nid      标识
     * @param user     目标用户
     * @param orderNo  关联信息
     * @param integral 操作积分
     */
    @Transactional
    public void doIntegral(String nid, User user, String orderNo, double integral, IntegralAccountModel integralAccountModel) {
        this.nid = nid;
        this.user = user;
        this.orderNo = orderNo;
        this.integral = integral;
        this.integralAccountModel = integralAccountModel;
        try {
            this.initIntegral();//初始化积分
            this.initIntegralAccountModel();//初始化账户
            this.doIntegralAccount();//账户处理
            this.initIntegralLogModel();//初始化日志
            this.doIntegralLog();//日志处理
        } catch (Exception e) {
            LogUtil.info(e.getMessage());
        }
    }

    /**
     * 初始化日志
     */
    private void initIntegralLogModel() {
        this.initTemplateModel();//初始化日志模板
        IntegralAccountDao integralAccountDao = BeanUtil.getBean(IntegralAccountDao.class);
        IntegralAccount integralAccount = integralAccountDao.findByUserId(this.user.getId());
        this.integralLogModel = new IntegralLogModel(this.user, this.integral, this.nid, this.templateModel.getName(), integralAccount.getTotalIntegral(),
                integralAccount.getBalanceIntegral(), integralAccount.getExpenseIntegral(), integralAccount.getFreezeIntegral(), this.toUser,
                this.templateModel.getContent(), this.templateModel.getPaymentsType(), this.orderNo, "");
    }

    /**
     * 初始化日志模板
     */
    private void initTemplateModel() {
        this.templateModel = new TemplateModel().instance(this.nid, BaseConstant.TEMPLATE_TYPE_INTEGRAL, 0);
    }

    /**
     * 初始化账户
     *
     * @return
     */
    private void initIntegralAccountModel() {
        if (this.integralAccountModel == null) {
            this.integralAccountModel = new IntegralAccountModel(this.user, this.integral);
        }
    }

    /**
     * 初始化积分
     */
    private void initIntegral() {
        if (this.integral <= 0) {
            this.integral = Global.getdouble(BaseConstant.MODEL_Jf + "_" + this.nid);
            if (this.integral <= 0) {
                throw new BusinessException("初始化积分失败[积分不存在或未开启]");
            }
        }

    }

    /**
     * 处理积分账户
     */
    private void doIntegralAccount() {
        IntegralAccountService integralAccountService = BeanUtil.getBean(IntegralAccountService.class);
        integralAccountService.updateIntegral(this.integralAccountModel);
    }

    /**
     * 处理积分日志
     */
    private void doIntegralLog() {
        IntegralLogService integralLogService = BeanUtil.getBean(IntegralLogService.class);
        integralLogService.add(this.integralLogModel);
    }
}
