package com.zc.mall.core.common.executer;

import com.zc.mall.core.account.entity.Account;
import com.zc.mall.core.user.entity.User;

/**
 * 任务抽象类
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public abstract class AbstractExecuter implements Executer {

    // 处理实体
    protected Object obj;
    protected User user;// 交易用户
    protected User toUser;// 交易对方用户
    protected Account account;
    protected double money;

    @Override
    public void execute(Object obj, double money, User user) {
        execute(obj, money, user, toUser, null);
    }

    @Override
    public void execute(Object obj, double money, User user, User toUser) {
        execute(obj, money, user, toUser, null);
    }

    @Override
    public void execute(Object obj) {
        execute(obj, 0, null, null, null);
    }

    @Override
    public void execute(Object obj, double money, User user, User toUser, Account account) {
        this.obj = obj;
        this.money = money;
        this.user = user;
        this.toUser = toUser;
        this.account = account;
        // 初始化
        init();
        // 更新账户
        handleAccount();
        // 添加资金记录
        addAccountLog();
        // 积分处理
        handleIntegral();
        // 信用分处理
        handleCredit();
        // 接口处理
        handleInterface();
        // 处理通知
        handleNotice();
        // 新增操作记录
        addOperateLog();
        // 处理推广活动
        handlePromotion();
        // 队列发送
        queueSend();
        // 统计
        doAccountStatistics();
    }

    @Override
    public abstract void init();

    @Override
    public abstract void handleCredit();

    @Override
    public abstract void handleIntegral();

    @Override
    public abstract void addAccountLog();

    @Override
    public abstract void handleAccount();

    @Override
    public abstract void handleNotice();

    @Override
    public abstract void sendAppPush();

    @Override
    public abstract void sendMessage();

    @Override
    public abstract void sendEmail();

    @Override
    public abstract void sendSMS();

    @Override
    public abstract void addOperateLog();

    @Override
    public abstract void queueSend();

    @Override
    public abstract void handlePromotion();

    @Override
    public abstract void handleInterface();

    @Override
    public abstract void doAccountStatistics();
}
