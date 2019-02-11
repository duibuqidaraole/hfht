package com.zc.mall.core.credit.service.work;

import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.common.global.Global;
import com.zc.mall.core.credit.dao.CreditScoreDao;
import com.zc.mall.core.credit.entity.CreditScore;
import com.zc.mall.core.credit.model.CreditScoreLogModel;
import com.zc.mall.core.credit.model.CreditScoreModel;
import com.zc.mall.core.credit.service.CreditScoreLogService;
import com.zc.mall.core.credit.service.CreditScoreService;
import com.zc.mall.core.manage.entity.Operator;
import com.zc.mall.core.sys.model.TemplateModel;
import com.zc.mall.core.user.entity.User;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.util.log.LogUtil;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 信用分处理
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2018年9月28日
 */
@Component
public class CreditScoreWork {

    @Resource
    private CreditScoreService creditScoreService;
    @Resource
    private CreditScoreLogService creditScoreLogService;
    @Resource
    private CreditScoreDao creditScoreDao;

    /**
     * 标识
     **/
    private String nid;
    /**
     * 关联信息
     **/
    private String orderNo;
    /**
     * 操作信用分
     **/
    private double score;
    /**
     * 目标用户
     **/
    private User user;
    /**
     * 信用model
     **/
    private CreditScoreModel creditScoreModel;
    /**
     * 信用日志model
     **/
    private CreditScoreLogModel creditScoreLogModel;
    /**
     * 日志模板
     **/
    private TemplateModel templateModel;
    /**
     * 操作管理员
     **/
    private Operator operator;
    /**
     * 备注
     **/
    private String remark;

    /**
     * 信用分处理
     *
     * @param nid
     * @param user
     * @param orderNo
     * @param score
     * @param operator
     * @param remark
     * @param creditScoreModel
     */
    @Transactional
    public void doCreditScore(String nid, User user, String orderNo, double score, Operator operator, String remark, CreditScoreModel creditScoreModel) {
        this.nid = nid;
        this.user = user;
        this.orderNo = orderNo;
        this.score = score;
        this.remark = remark;
        this.operator = operator;
        this.creditScoreModel = creditScoreModel;
        try {
            // 初始化操作分
            this.score();
            // 初始化账户
            this.initCreditScoreModel();
            // 账户处理
            this.doCreditScoreModel();
            // 初始化日志
            this.initCreditScoreLogModel();
            // 日志处理
            this.doCreditScoreLog();
        } catch (Exception e) {
            LogUtil.info(e.getMessage());
        }
    }

    /**
     * 初始化操作分
     */
    private void score() {
        if (this.score == 0) {
            this.score = Global.getdouble(BaseConstant.MODEL_Cs + "_" + this.nid);
            if (this.score == 0) {
                throw new BusinessException("初始化信用分失败[信用分不存在或未开启]");
            }
        }
    }

    /**
     * 初始化账户
     */
    private void initCreditScoreModel() {
        if (creditScoreModel == null) {
            creditScoreModel = new CreditScoreModel(user, score);
        }
    }

    /**
     * 账户处理
     */
    private void doCreditScoreModel() {
        creditScoreService.updateCreditScore(creditScoreModel);
    }

    /**
     * 初始化日志
     */
    private void initCreditScoreLogModel() {
        // 初始化日志模板
        this.initTemplateModel();
        CreditScore creditScore = creditScoreDao.findByUser(this.user.getId());
        this.creditScoreLogModel = new CreditScoreLogModel(user, score, nid, templateModel.getName(), creditScore.getBalanceScore(),
                templateModel.getContent(), templateModel.getPaymentsType(), orderNo, operator, remark);
    }

    /**
     * 初始化日志模板
     */
    private void initTemplateModel() {
        this.templateModel = new TemplateModel().instance(this.nid, BaseConstant.TEMPLATE_TYPE_CREDIT_SCORE, 0);
    }

    /**
     * 日志处理
     */
    private void doCreditScoreLog() {
        creditScoreLogService.add(creditScoreLogModel);
    }

}
