package com.zc.mall.core.common.service.impl;

import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.common.executer.Executer;
import com.zc.mall.core.common.executer.NoticeMessageExecuter;
import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.common.global.Global;
import com.zc.mall.core.common.interest.*;
import com.zc.mall.core.common.model.CommonModel;
import com.zc.mall.core.common.protocol.AbstractProtocolBean;
import com.zc.mall.core.common.protocol.ProtocolConstant;
import com.zc.mall.core.common.service.CommonService;
import com.zc.mall.core.credit.dao.CreditScoreDao;
import com.zc.mall.core.integral.dao.IntegralAccountDao;
import com.zc.mall.core.manage.dao.OrderTaskDao;
import com.zc.mall.core.manage.model.NoticeMessageModel;
import com.zc.mall.core.manage.model.OrderTaskModel;
import com.zc.mall.core.manage.service.NoticeMessageService;
import com.zc.mall.core.user.dao.UserDao;
import com.zc.sys.common.cache.RedisCacheUtil;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.util.calculate.BigDecimalUtil;
import com.zc.sys.common.util.validate.RandomUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 公共接口
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月15日
 */
@Service
public class CommonServiceImpl implements CommonService {

    @Resource
    private RedisCacheUtil redisCacheUtil;
    @Resource
    private OrderTaskDao orderTaskDao;
    @Resource
    private NoticeMessageService noticeMessageService;
    @Resource
    private UserDao userDao;
    @Resource
    private CreditScoreDao creditScoreDao;
    @Resource
    private IntegralAccountDao integralAccountDao;

    /**
     * 生成唯一请求标识
     * token
     *
     * @return
     */
    @Override
    public Result getToken() {
        String token = StringUtil.getSerialNumber();
        redisCacheUtil.setCode("token_" + token, "token_" + token, 60 * 30);
        return Result.success().setData(token);
    }

    /**
     * 校验token
     *
     * @param token
     * @return
     */
    @Override
    public void checkToken(String token) {
        String key = "token_" + token;
        String ckToken = redisCacheUtil.getCache(key, String.class);
        if (!StringUtil.isBlank(ckToken)) {
            redisCacheUtil.delCode(key);
        } else {
            throw new BusinessException("表单Token未设定，请刷新页面后重试。");
        }
    }

    /**
     * 发送短信验证码
     *
     * @param model
     * @return
     */
    @Override
    public Result getMobileCode(CommonModel model) {
        model.checkSMS();//短信发送校验

        String value = RandomUtil.code();
        String key = "SMS_" + model.getHandleSmsType() + "_" + model.getMobile();
        String cacheCode = redisCacheUtil.getCache(key, String.class);
        if (StringUtil.isNotBlank(cacheCode)) {
            //TODO
            redisCacheUtil.delCode(key);
            return Result.error("短信请求频繁，请稍后操作");
        }
        redisCacheUtil.setCode(key, value, 60 * 10);
        if (!Global.sysState()) {
            //测试环境不发送短信
            return Result.success("本次测短信试验证码为").setData(value);
        }
        NoticeMessageModel noticeModel = new NoticeMessageModel(2, model.getMobile());
        noticeModel.setCode(value);
        //短信任务
        Executer smsExecuter = BeanUtil.getBean(NoticeMessageExecuter.class);
        smsExecuter.execute(noticeModel);
        return Result.success("短信发送请求成功");
    }

    /**
     * 校验短信验证码
     *
     * @param mobile
     * @param mobileCode
     * @param handleSmsType
     * @return
     */
    @Override
    public void checkMobileCode(String mobile, String mobileCode, int handleSmsType) {
        String key = "SMS_" + handleSmsType + "_" + mobile;
        String cacheCode = redisCacheUtil.getCache(key, String.class);
        if (StringUtil.isBlank(cacheCode) || StringUtil.isBlank(mobileCode) || !mobileCode.equals(cacheCode)) {
            throw new BusinessException("短信验证码校验失败");
        }
        redisCacheUtil.delCode(key);
    }

    /**
     * 利息计算器
     *
     * @param model
     * @return
     */
    @Override
    public CommonModel calculator(CommonModel model) {
        model.checkAndInitCalculator();
        InterestCalculator ic = null;
        double rate = model.getRate();
        List<EachPlan> list = new ArrayList<EachPlan>();
        List<CalculatorModel> list_ = new ArrayList<CalculatorModel>();
        if (model.getRepaymentType() == BaseConstant.REPAYMENT_WAY_ONETIME) {// 一次性
            ic = new OnetimeRepaymentCalculator(model.getAmount(), rate, model.getStartTime(), model.getPeriod(), 0);
        } else if (model.getRepaymentType() == BaseConstant.REPAYMENT_WAY_INSTALLMENT) {// 等额本息
            ic = new InstallmentRepaymentCalculator(model.getAmount(), rate, model.getStartTime(), model.getPeriod(), 0);
        } else if (model.getRepaymentType() == BaseConstant.REPAYMENT_WAY_PERIOD_INTEREST) {// 每期付息到期还本
            ic = new PeriodInterestCalculator(model.getAmount(), rate, model.getStartTime(), model.getPeriod(), true, 0);
        }
        if (model.getPeriodUnit() == BaseConstant.PERIODUNIT_DAY) {
            list = ic.calculator(model.getPeriod());
        } else {
            list = ic.calculator();
        }
        int i = 0;
        for (EachPlan eachPlan : list) {
            CalculatorModel calculatorModel = new CalculatorModel();
            calculatorModel.setCapital(eachPlan.getCapital());
            calculatorModel.setInterest(eachPlan.getInterest());
            calculatorModel.setPeriod(i + 1);
            calculatorModel.setPeriodTotal(eachPlan.getNetTotal());
            calculatorModel.setRepayTime(eachPlan.getRepayTime());
            double needRepay = eachPlan.getNeedRepay();
            calculatorModel.setNeedRepay(needRepay);
            list_.add(calculatorModel);
            //还款利息总额
            model.setInterestTotal(BigDecimalUtil.add(eachPlan.getInterest(), model.getInterestTotal()));
            i++;
        }
        //还款总额
        model.setTotal(BigDecimalUtil.add(model.getInterestTotal(), model.getAmount()));
        model.setCalculatorModelList(list_);
        model.setInterestTotal(BigDecimalUtil.round(model.getInterestTotal(), 2));
        model.setTotal(BigDecimalUtil.round(model.getTotal(), 2));
        return model;
    }

    /**
     * 协议下载
     *
     * @param model
     * @return
     */
    @Override
    public Result downloadProtocol(CommonModel model) {
        if (StringUtil.isBlank(model.getContractId())) {
            throw new BusinessException("参数错误");
        }
        AbstractProtocolBean.instance(ProtocolConstant.PROTOCOL_SERVICE_TENDER).downloadContract(model.getContractId());
        return Result.success();
    }

    /**
     * 生成协议
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result createProtocol(CommonModel model) {
        if (model.getTenderId() <= 0) {
            throw new BusinessException("参数错误");
        }
        AbstractProtocolBean.instance(ProtocolConstant.PROTOCOL_SERVICE_TENDER).executer(ProtocolConstant.PROTOCOL_NID_TENDER, model.getTenderId());
        // 订单处理
        OrderTaskModel.dealOrder(model.getOrderTask() != null ? model.getOrderTask().getOrderNo() : "", BaseConstant.BUSINESS_STATE_YES, "生成协议处理成功");
        return Result.success();
    }


}
