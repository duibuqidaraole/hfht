package com.zc.mall.core.credit.service.impl;

import com.zc.mall.core.common.executer.Executer;
import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.common.global.Global;
import com.zc.mall.core.credit.dao.CreditScoreDao;
import com.zc.mall.core.credit.entity.CreditScore;
import com.zc.mall.core.credit.executer.AdminHandleCreditScore;
import com.zc.mall.core.credit.model.CreditScoreModel;
import com.zc.mall.core.credit.service.CreditScoreService;
import com.zc.mall.core.manage.dao.OperatorDao;
import com.zc.mall.core.manage.entity.Operator;
import com.zc.mall.core.manage.model.OperatorModel;
import com.zc.mall.core.sys.constant.ConfigParamConstant;
import com.zc.mall.core.user.dao.UserDao;
import com.zc.mall.core.user.entity.User;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.exception.VersionControlException;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.util.log.LogUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 信用分账户
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Service
public class CreditScoreServiceImpl implements CreditScoreService {

    @Resource
    private CreditScoreDao creditScoreDao;
    @Resource
    private OperatorDao operatorDao;
    @Resource
    private UserDao userDao;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    public Result list(CreditScoreModel model) {

        return null;
    }

    /**
     * 更新信用分
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public void updateCreditScore(CreditScoreModel model) {
        int currUpdateAccountTimes = Global.getTransfer("currUpdateAccountTimes", Integer.class);
        CreditScore creditScore = creditScoreDao.find(model.getId());
        if (creditScore == null) {
            throw new BusinessException("更新信息不存在");
        }
        currUpdateAccountTimes++;
        try {
            creditScoreDao.updateCreditScore(creditScore.getId(), model.getBalanceScore(), model.getSysScore(), creditScore.getVersion());
        } catch (VersionControlException e) {
            LogUtil.info("信用分更新失败信息:" + model.toString() + "------当前执行次数:" + currUpdateAccountTimes);
            int versionControlTimes = Global.getInt(ConfigParamConstant.SYS_PARAM_VERSION_CONTROL_TIMES);// 版本控制次数
            if (currUpdateAccountTimes < versionControlTimes) {
                Global.setTransfer("currUpdateAccountTimes", currUpdateAccountTimes);
                updateCreditScore(model);
            }
            throw new BusinessException("信用分更新异常");
        }
    }

    /**
     * 后台手动更新信用分
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result adminHandleCreditScore(CreditScoreModel model) {
        this.checkAdminHandleCreditScore(model);
        Executer adminHandleCreditScore = BeanUtil.getBean(AdminHandleCreditScore.class);
        adminHandleCreditScore.execute(model, model.getBalanceScore(), model.getUser());
        return Result.success();
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result add(CreditScoreModel model) {

        return null;
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result update(CreditScoreModel model) {

        return null;
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getById(CreditScoreModel model) {

        return null;
    }

    /**
     * 芝麻信用授权
     *
     * @param model
     * @return
     */
    @Override
    public Result zmxyAuthorize(CreditScoreModel model) {

        return null;
    }


    /**
     * 参数校验
     *
     * @param model
     */
    private void checkAdminHandleCreditScore(CreditScoreModel model) {
        if (model.getBalanceScore() == 0) {
            throw new BusinessException("参数错误！");
        }
        if (model.getOperatorModel() == null || model.getOperatorModel().getId() <= 0) {
            throw new BusinessException("参数错误！");
        }
        if (StringUtil.isBlank(model.getRemark())) {
            throw new BusinessException("备注不能为空！");
        }
        if (model.getUser() == null || model.getUser().getId() <= 0) {
            throw new BusinessException("参数错误！");
        }
        User user = userDao.find(model.getUser().getId());
        if (user == null) {
            throw new BusinessException("用户不存在！");
        }
        model.setUser(user);
        Operator operator = operatorDao.find(model.getOperatorModel().getId());
        if (operator == null) {
            throw new BusinessException("参数错误！");
        }
        model.setOperatorModel(OperatorModel.instance(operator));
    }

}