package com.zc.mall.core.common.model;

import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.common.constant.BaseConstant.OrderNid;
import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.common.interest.CalculatorModel;
import com.zc.mall.core.common.service.CommonService;
import com.zc.mall.core.manage.entity.OrderTask;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.validate.StringUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 公共接口
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月15日
 */
public class CommonModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 手机号
     **/
    private String mobile;
    /**
     * 期数单位
     **/
    private int periodUnit;
    /**
     * 期数
     **/
    private int period;
    /**
     * 借款金额
     **/
    private double amount;
    /**
     * 天利率
     **/
    private double rate;
    /**
     * 计息开始时间
     **/
    private Date startTime;
    /**
     * 还款方式
     **/
    private int repaymentType;
    /**
     * 还款利息总额
     **/
    private double interestTotal;
    /**
     * 还款总额（本息）
     **/
    private double total;
    /**
     * 还款计划model
     **/
    private List<CalculatorModel> CalculatorModelList;

    /**
     * 主动发送短信类型
     **/
    private int handleSmsType;
    /**
     * 合同id
     **/
    private String contractId;
    /**
     * 投资id
     **/
    private long tenderId;
    /**
     * 订单
     **/
    private OrderTask orderTask;

    public CommonModel() {
        super();
    }

    /**
     * @param periodUnit    期数单位
     * @param period        期数
     * @param amount        金额
     * @param rate          利率
     * @param startTime     计息开始时间
     * @param repaymentType 还款方式
     */
    public CommonModel(int periodUnit, int period, double amount, double rate, Date startTime, int repaymentType) {
        super();
        this.periodUnit = periodUnit;
        this.period = period;
        this.amount = amount;
        this.rate = rate;
        this.startTime = startTime;
        this.repaymentType = repaymentType;
    }

    /**
     * @param periodUnit    期数单位
     * @param period        期数
     * @param amount        金额
     * @param rate          利率
     * @param startTime     计息开始时间
     * @param repaymentType 还款方式
     */
    public static CommonModel getCalculatorInterest(int periodUnit, int period, double amount, double rate,
                                                    Date startTime, int repaymentType) {
        CommonService commonService = BeanUtil.getBean(CommonService.class);
        CommonModel commonModel = new CommonModel(periodUnit, period, amount, rate, startTime, repaymentType);
        return commonService.calculator(commonModel);
    }

    /**
     * 短信发送校验
     */
    public void checkSMS() {
        if (StringUtil.isBlank(mobile) || !StringUtil.isPhone(mobile)) {
            throw new BusinessException("手机号不能为空");
        }
        if (this.getHandleSmsType() <= 0) {
            throw new BusinessException("参数错误");
        }
    }

    /**
     * 利息计算器参数校验及初始化
     */
    public void checkAndInitCalculator() {
        if (this.getPeriodUnit() != BaseConstant.PERIODUNIT_DAY
                && this.getPeriodUnit() != BaseConstant.PERIODUNIT_MONTH) {
            throw new BusinessException("参数错误");
        }
        if (period <= 0) {
            throw new BusinessException("借款期限必须大于0");
        }
        if (amount <= 0) {
            throw new BusinessException("借款金额必须大于0");
        }
        if (rate <= 0) {
            throw new BusinessException("借款天利率必须大于0");
        }
        if (startTime == null) {
            startTime = DateUtil.getNow();
        }
    }

    /**
     * 任务分发
     */
    public void doQueue() {
        CommonService commonService = BeanUtil.getBean(CommonService.class);
        if (OrderNid.ORDER_NID_TENDER_CREATE_PROTOCOL.getNid().equals(this.orderTask.getType())) {
            commonService.createProtocol(this);
        }
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
     * 获取【期数单位】
     **/
    public int getPeriodUnit() {
        return periodUnit;
    }

    /**
     * 设置【期数单位】
     **/
    public void setPeriodUnit(int periodUnit) {
        this.periodUnit = periodUnit;
    }

    /**
     * 获取【期数】
     **/
    public int getPeriod() {
        return period;
    }

    /**
     * 设置【期数】
     **/
    public void setPeriod(int period) {
        this.period = period;
    }

    /**
     * 获取【借款金额】
     **/
    public double getAmount() {
        return amount;
    }

    /**
     * 设置【借款金额】
     **/
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * 获取【天利率】
     **/
    public double getRate() {
        return rate;
    }

    /**
     * 设置【天利率】
     **/
    public void setRate(double rate) {
        this.rate = rate;
    }

    /**
     * 获取【计息开始时间】
     **/
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置【计息开始时间】
     **/
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取【还款方式】
     **/
    public int getRepaymentType() {
        return repaymentType;
    }

    /**
     * 设置【还款方式】
     **/
    public void setRepaymentType(int repaymentType) {
        this.repaymentType = repaymentType;
    }

    /**
     * 获取【还款利息总额】
     **/
    public double getInterestTotal() {
        return interestTotal;
    }

    /**
     * 设置【还款利息总额】
     **/
    public void setInterestTotal(double interestTotal) {
        this.interestTotal = interestTotal;
    }

    /**
     * 获取【还款总额（本息）】
     **/
    public double getTotal() {
        return total;
    }

    /**
     * 设置【还款总额（本息）】
     **/
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * 获取【calculatorModelList】
     **/
    public List<CalculatorModel> getCalculatorModelList() {
        return CalculatorModelList;
    }

    /**
     * 设置【calculatorModelList】
     **/
    public void setCalculatorModelList(List<CalculatorModel> calculatorModelList) {
        CalculatorModelList = calculatorModelList;
    }

    /**
     * 获取【主动发送短信类型】
     **/
    public int getHandleSmsType() {
        return handleSmsType;
    }

    /**
     * 设置【主动发送短信类型】
     **/
    public void setHandleSmsType(int handleSmsType) {
        this.handleSmsType = handleSmsType;
    }

    /**
     * 获取【合同id】
     **/
    public String getContractId() {
        return contractId;
    }

    /**
     * 设置【合同id】
     **/
    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    /**
     * 获取【投资id】
     **/
    public long getTenderId() {
        return tenderId;
    }

    /**
     * 设置【投资id】
     **/
    public void setTenderId(long tenderId) {
        this.tenderId = tenderId;
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

}
