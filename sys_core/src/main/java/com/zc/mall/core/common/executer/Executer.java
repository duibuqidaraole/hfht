package com.zc.mall.core.common.executer;

import com.zc.mall.core.account.entity.Account;
import com.zc.mall.core.user.entity.User;

/**
 * 任务基类
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public interface Executer {

    /**
     * 初始化
     */
    void init();

    /**
     * 处理核心方法
     */
    void execute(Object obj, double money, User user);

    /**
     * 处理核心方法
     */
    void execute(Object obj);

    /**
     * 处理核心方法
     */
    void execute(Object obj, double money, User user, User toUser);

    /**
     * 处理核心方法
     */
    void execute(Object obj, double money, User user, User toUser,
                 Account account);

    /**
     * 处理通知
     */
    void handleNotice();

    /**
     * 新增操作记录
     */
    void addOperateLog();

    /**
     * 信用分处理
     */
    void handleCredit();

    /**
     * 积分处理
     */
    void handleIntegral();

    /**
     * 添加资金记录
     */
    void addAccountLog();

    /**
     * 更新账户
     */
    void handleAccount();

    /**
     * 处理推广活动
     */
    void handlePromotion();

    /**
     * 推送
     */
    void sendAppPush();

    /**
     * 站内信
     */
    void sendMessage();

    /**
     * 邮件
     */
    void sendEmail();

    /**
     * 短信
     */
    void sendSMS();

    /**
     * 队列发送
     */
    void queueSend();

    /**
     * 处理接口
     */
    void handleInterface();

    /**
     * 处理统计
     */
    void doAccountStatistics();
}
