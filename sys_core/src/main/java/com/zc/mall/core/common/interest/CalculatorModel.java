package com.zc.mall.core.common.interest;

import java.util.Date;

/**
 * 利息计算器Model
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月21日
 */
public class CalculatorModel {
    /**
     * 期数
     */
    private int period;
    /**
     * 本金
     */
    private double capital;
    /**
     * 利息
     */
    private double interest;
    /**
     * 本期本息总和
     */
    private double periodTotal;
    /**
     * 还款时间
     */
    private Date repayTime;
    /**
     * 剩余还款金额
     */
    private double needRepay;

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
     * 获取【本金】
     **/
    public double getCapital() {
        return capital;
    }

    /**
     * 设置【本金】
     **/
    public void setCapital(double capital) {
        this.capital = capital;
    }

    /**
     * 获取【利息】
     **/
    public double getInterest() {
        return interest;
    }

    /**
     * 设置【利息】
     **/
    public void setInterest(double interest) {
        this.interest = interest;
    }

    /**
     * 获取【本期本息总和】
     **/
    public double getPeriodTotal() {
        return periodTotal;
    }

    /**
     * 设置【本期本息总和】
     **/
    public void setPeriodTotal(double periodTotal) {
        this.periodTotal = periodTotal;
    }

    /**
     * 获取【还款时间】
     **/
    public Date getRepayTime() {
        return repayTime;
    }

    /**
     * 设置【还款时间】
     **/
    public void setRepayTime(Date repayTime) {
        this.repayTime = repayTime;
    }

    /**
     * 获取【剩余还款金额】
     **/
    public double getNeedRepay() {
        return needRepay;
    }

    /**
     * 设置【剩余还款金额】
     **/
    public void setNeedRepay(double needRepay) {
        this.needRepay = needRepay;
    }

}
