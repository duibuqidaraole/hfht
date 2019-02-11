package com.zc.mall.core.common.executer;

import com.zc.mall.core.account.dao.AccountLogDao;
import com.zc.mall.core.account.service.AccountService;
import com.zc.mall.core.common.global.BeanUtil;

/**
 * 任务主类
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public class BaseExecuter extends AbstractExecuter {

    /**
     * 资金
     **/
    protected AccountService accountService;
    /**
     * 资金日志
     **/
    protected AccountLogDao accountLogDao;

    @Override
    public void init() {
        accountService = BeanUtil.getBean(AccountService.class);
        accountLogDao = BeanUtil.getBean(AccountLogDao.class);
    }

    @Override
    public void handleCredit() {

    }

    @Override
    public void handleIntegral() {

    }

    @Override
    public void addAccountLog() {

    }

    @Override
    public void handleAccount() {

    }

    @Override
    public void handleNotice() {
        // 短信
        sendSMS();
        // 邮件
        sendEmail();
        // 站内信
        sendMessage();
        // 推送
        sendAppPush();
    }

    @Override
    public void addOperateLog() {

    }

    @Override
    public void sendMessage() {

    }

    @Override
    public void sendEmail() {

    }

    @Override
    public void sendSMS() {

    }

    @Override
    public void sendAppPush() {

    }

    @Override
    public void queueSend() {

    }

    @Override
    public void handlePromotion() {

    }

    @Override
    public void handleInterface() {

    }

    @Override
    public void doAccountStatistics() {

    }

}
