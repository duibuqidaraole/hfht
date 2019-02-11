package com.zc.mall.core.common.executer;

import com.zc.mall.core.manage.model.NoticeMessageModel;
import com.zc.mall.core.manage.service.NoticeMessageService;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.util.log.LogUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 注册等通用消息任务
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月13日
 */
@Component
public class NoticeMessageExecuter extends BaseExecuter {

    @Resource
    private NoticeMessageService noticeMessageService;
    private NoticeMessageModel model;
    private String NOTICE_CHECK_MESSAGE = "check_message";// 验证消息

    @Override
    public void init() {
        if (!(this.obj instanceof NoticeMessageModel)) {
            throw new BusinessException("实例不存在");
        }
        this.model = (NoticeMessageModel) this.obj;
    }

    @Override
    public void sendSMS() {
        try {
            model.setNid("sms_" + NOTICE_CHECK_MESSAGE);
            noticeMessageService.sendRequest(model);
        } catch (Exception e) {
            LogUtil.info("SMS消息发送失败" + e.getMessage());
        }
    }

}
