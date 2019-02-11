package com.zc.mall.core.common.interest;

import com.zc.sys.common.util.calculate.BigDecimalUtil;
import com.zc.sys.common.util.date.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 每期还息到期还本利息计算函数
 *
 * @author：zp
 */
public class PeriodInterestCalculator implements InterestCalculator {

    /**
     * 借款金额
     */
    private double account;
    /**
     * 年利率
     */
    private double yearApr;
    /**
     * 天利率
     */
    private double dayApr;
    /**
     * 利息管理费
     */
    private double manageFee;
    /**
     * 期数
     */
    private int periods;
    /**
     * 标记是否为满标复审
     */
    private boolean flag;
    /**
     * 还款金额
     */
    private double repayAccount;
    /**
     * 开始计息日
     */
    private Date interestTime;
    /**
     * 还款日
     */
    private Date repayTime;
    /**
     * 还款计划
     */
    private List<EachPlan> eachPlan;
    /**
     * 总利息
     */
    private double repayInterest;

    /**
     * 初始化每期还息到期还本构造函数
     *
     * @param account      借款金额
     * @param yearApr      年利率
     * @param interestTime 开始计息日
     * @param periods      期数
     * @param flag         满标复审：true
     * @param manageFee    利率管理费
     */
    public PeriodInterestCalculator(double account, double yearApr, Date interestTime, int periods, boolean flag, double manageFee) {
        this.account = account;
        this.yearApr = yearApr;
        this.periods = periods;
        this.manageFee = manageFee;
        this.interestTime = interestTime;
        this.flag = flag;
        eachPlan = new ArrayList<EachPlan>();
    }

    @Override
    public List<EachPlan> calculator() {
        /*
         * 如果是发标或复审或投标或网贷计算器则total = account; 否则total = BigDecimalUtil.mul(account, periods);
         */
        double total;
        if (flag) {
            total = account;
        } else {
            total = BigDecimalUtil.mul(account, periods);
        }
        double needRepay = total;
        double eInterest = 0;
        double netInterest = 0;
        double eCapital = 0;
        double eTotal = 0;
        double sum = 0;
        Date eInterestTime = null;
        Date eRepayTime = null;
        EachPlan e = null;
        for (int i = 0; i < periods; i++) {
            e = new EachPlan();
            // 计算每期需要支付的利息
            eInterest = BigDecimalUtil.round(BigDecimalUtil.div(BigDecimalUtil.mul(needRepay, yearApr), 12), 2);
            // 计算本金
            eCapital = 0;
            // 每期还款即是本期的还款总额
            if (i == periods - 1) { // 判断是否是最后一期
                eCapital = account;
                //总利息
//				double totalInterest=BigDecimalUtil.div(BigDecimalUtil.mul(BigDecimalUtil.mul(needRepay, yearApr), periods), 12);
                //最后一期利息
//				double lastInterest=BigDecimalUtil.round(BigDecimalUtil.mul(eInterest, BigDecimalUtil.sub(periods, 1)),2);
//				eInterest=BigDecimalUtil.round(BigDecimalUtil.sub(totalInterest,lastInterest), 2);
            }
            netInterest = BigDecimalUtil.mul(eInterest, BigDecimalUtil.sub(1, manageFee));
            eTotal = BigDecimalUtil.round(BigDecimalUtil.add(eCapital, eInterest), 2);
            // 本期开始计息日
            eInterestTime = interestTime;
            // 本期还款日
            eRepayTime = this.calculatorRepaytime(eInterestTime, i + 1);
            e.setCapital(eCapital);
            e.setInterest(eInterest);
            e.setNetInterest(BigDecimalUtil.round(netInterest, 2));
            e.setTotal(eTotal);
            double netTotal = BigDecimalUtil.round(BigDecimalUtil.add(eCapital, netInterest), 2);
            e.setNetTotal(netTotal);
            e.setInterestTime(eInterestTime);
            e.setRepayTime(eRepayTime);
            e.setNeedRepay(BigDecimalUtil.sub(BigDecimalUtil.add(total, BigDecimalUtil.mul(eInterest, periods)), BigDecimalUtil.mul(eInterest, BigDecimalUtil.add(i, 1))));
            repayTime = eRepayTime;
            eachPlan.add(e);
            sum = BigDecimalUtil.round(BigDecimalUtil.add(eInterest, sum), 2);
        }
        this.setRepayInterest(sum);
        this.repayAccount = BigDecimalUtil.round(BigDecimalUtil.add(account, sum), 2);
        e = eachPlan.get(periods - 1);
        e.setNeedRepay(0);
        e.setCapital(account);
        return eachPlan;
    }

    @Override
    public List<EachPlan> calculator(int days) {
        this.dayApr = BigDecimalUtil.div(this.yearApr, 365);
        double eInterest = 0;
        double netInterest = 0;
        double eCapital = 0;
        double eTotal = 0;
        double sum = 0;//累计利息
        double lastInterest = 0;
        Date eInterestTime = null;
        Date eRepayTime = interestTime;
        EachPlan e = null;
        // 计算期数
        periods = 0;
        Date endPeriodTime = DateUtil.rollDay(interestTime, days);// 最后还款日
        double totalInterest = BigDecimalUtil.round(BigDecimalUtil.mul(dayApr, account, days), 2);// 总利息
        boolean isEndPeriod = false;//是否最后一期
        while (!isEndPeriod) {
            e = new EachPlan();
            // 本期开始计息日
            eInterestTime = eRepayTime;
            // 本期还款日
            eRepayTime = this.calculatorRepaytime(interestTime, periods + 1);
            // 计算本金
            eCapital = 0;
            double b = DateUtil.msBetween(endPeriodTime, eRepayTime);
            if (b <= 0) {// 判断最后一期
                eCapital = account;
                eRepayTime = endPeriodTime;
//				lastInterest = BigDecimalUtil.sub(totalInterest, sum);
                isEndPeriod = true;//最后一期
            }
            // 相隔天数
            int betweenDays = DateUtil.daysBetween(eInterestTime, eRepayTime);
            //计算当期需要支期的利息
            if (lastInterest > 0) {
                eInterest = lastInterest;
            } else {
                eInterest = BigDecimalUtil.round(BigDecimalUtil.mul(dayApr, account, betweenDays), 2);
            }
            sum = BigDecimalUtil.round(BigDecimalUtil.add(eInterest, sum), 2);
            netInterest = BigDecimalUtil.mul(eInterest, BigDecimalUtil.sub(1, manageFee));
            eTotal = BigDecimalUtil.round(BigDecimalUtil.add(eCapital, eInterest), 2);
            e.setCapital(eCapital);
            e.setInterest(eInterest);
            e.setNetInterest(BigDecimalUtil.round(netInterest, 2));
            e.setTotal(eTotal);
            double netTotal = BigDecimalUtil.round(BigDecimalUtil.add(eCapital, netInterest), 2);
            e.setNetTotal(netTotal);
            e.setInterestTime(eInterestTime);
            e.setRepayTime(eRepayTime);
            e.setNeedRepay(BigDecimalUtil.sub(BigDecimalUtil.add(totalInterest, account), BigDecimalUtil.add(sum, eCapital)));
            repayTime = eRepayTime;
            eachPlan.add(e);
            periods++;
        }
        return eachPlan;
    }

    @Override
    public List<EachPlan> getEachPlan() {
        return eachPlan;
    }

    @Override
    public double repayTotal() {
        return this.repayAccount;
    }

    @Override
    public Date repayTime() {
        return repayTime;
    }

    @Override
    public int repayPeriods() {
        return periods;
    }

    @Override
    public Date calculatorRepaytime(Date date, int i) {
        return DateUtil.rollMon(date, i);
    }

    /* (non-Javadoc)
     * @see com.rongdu.p2psys.borrow.model.interest.InterestCalculator#calculatorRepaytime(java.util.Date)
     */
    @Override
    public Date calculatorRepaytime(Date date) {
        return null;
    }

    public double getAccount() {
        return account;
    }

    public void setAccount(double account) {
        this.account = account;
    }

    public double getYearApr() {
        return yearApr;
    }

    public void setYearApr(double yearApr) {
        this.yearApr = yearApr;
    }

    public double getManageFee() {
        return manageFee;
    }

    public void setManageFee(double manageFee) {
        this.manageFee = manageFee;
    }

    public int getPeriods() {
        return periods;
    }

    public void setPeriods(int periods) {
        this.periods = periods;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public double getRepayAccount() {
        return repayAccount;
    }

    public void setRepayAccount(double repayAccount) {
        this.repayAccount = repayAccount;
    }

    public Date getInterestTime() {
        return interestTime;
    }

    public void setInterestTime(Date interestTime) {
        this.interestTime = interestTime;
    }

    public Date getRepayTime() {
        return repayTime;
    }

    public void setRepayTime(Date repayTime) {
        this.repayTime = repayTime;
    }

    public void setEachPlan(List<EachPlan> eachPlan) {
        this.eachPlan = eachPlan;
    }

    public double getRepayInterest() {
        return repayInterest;
    }

    public void setRepayInterest(double repayInterest) {
        this.repayInterest = repayInterest;
    }
}
