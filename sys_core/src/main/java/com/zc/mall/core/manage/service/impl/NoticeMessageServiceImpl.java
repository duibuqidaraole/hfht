package com.zc.mall.core.manage.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.common.constant.BaseConstant.OrderNid;
import com.zc.mall.core.common.constant.BaseConstant.QueueCode;
import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.common.global.Global;
import com.zc.mall.core.common.queue.service.QueueProducerService;
import com.zc.mall.core.common.queue.work.QueueAbstract;
import com.zc.mall.core.common.sms.SmsService;
import com.zc.mall.core.manage.dao.NoticeMessageDao;
import com.zc.mall.core.manage.entity.NoticeMessage;
import com.zc.mall.core.manage.model.NoticeMessageModel;
import com.zc.mall.core.manage.model.OperatorModel;
import com.zc.mall.core.manage.model.OrderTaskModel;
import com.zc.mall.core.manage.service.NoticeMessageService;
import com.zc.mall.core.sys.dao.TemplateDao;
import com.zc.mall.core.sys.entity.Template;
import com.zc.mall.core.sys.model.TemplateModel;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.util.log.LogUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 通知消息
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月15日
 */
@Service
public class NoticeMessageServiceImpl implements NoticeMessageService {

    @Resource
    private NoticeMessageDao noticeMessageDao;
    @Resource
    private TemplateDao templateDao;
    @Resource
    private QueueProducerService queueProducerService;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result list(NoticeMessageModel model) {
        PageDataList<NoticeMessage> pageDataList = noticeMessageDao.list(model);
        PageDataList<NoticeMessageModel> pageDataList_ = new PageDataList<NoticeMessageModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<NoticeMessageModel> list = new ArrayList<NoticeMessageModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (NoticeMessage noticeMessage : pageDataList.getList()) {
                NoticeMessageModel model_ = NoticeMessageModel
                        .instance(noticeMessage);
                model_.setSendUserModel(UserModel.instance(noticeMessage
                        .getSendUser()));
                model_.setReceiveUserModel(UserModel.instance(noticeMessage
                        .getReceiveUser()));
                model_.setOperatorModel(OperatorModel.instance(noticeMessage
                        .getOperator()));
                list.add(model_);
            }
        }
        pageDataList_.setList(list);
        return Result.success().setData(pageDataList_);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result add(NoticeMessageModel model) {

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
    public Result update(NoticeMessageModel model) {

        return null;
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getById(NoticeMessageModel model) {
        if (model.getId() < 0) {
            return Result.error("参数错误！");
        }
        NoticeMessage noticeMessage = noticeMessageDao.find(model.getId());
        NoticeMessageModel model_ = NoticeMessageModel.instance(noticeMessage);
        model_.setSendUserModel(UserModel.instance(noticeMessage.getSendUser()));
        model_.setReceiveUserModel(UserModel.instance(noticeMessage
                .getReceiveUser()));
        model_.setOperatorModel(OperatorModel.instance(noticeMessage
                .getOperator()));
        return Result.success().setData(model_);
    }

    /**
     * 消息发送请求
     *
     * @param model
     * @return
     */
    @Override
    public void sendRequest(NoticeMessageModel model) {
        String nid = "notice_" + model.getNid();
        Template template = templateDao.findByNid(nid);
        if (template == null) {
            LogUtil.info(nid + "配置未启用");
            return;
        }
        TemplateModel templateModel = TemplateModel.instance(template);
        model.initMessage(templateModel);

        // 发送队列
        QueueAbstract.send(StringUtil.getSerialNumber(),
                OrderNid.ORDER_NID_MESSAGE_SEND.getNid(),
                QueueCode.QUEUE_CODE_MESSAGE.getCode(), model,
                model.getReceiveUser());
    }

    /**
     * 消息发送
     *
     * @param model
     * @return
     */
    @Override
    public Result send(NoticeMessageModel model) {
        switch (model.getType()) {
            // 站内信
            case BaseConstant.NOTICEMESSAGE_TYPE_MESSAGE:
                model.setState(BaseConstant.BUSINESS_STATE_YES);
                model.setResult("成功");
                model.setResultCode("0000");
                break;
            // 短信
            case BaseConstant.NOTICEMESSAGE_TYPE_SMS:
                this.sendSms(model);
                // 邮件
            case BaseConstant.NOTICEMESSAGE_TYPE_EMAIL:
                break;
            // 推送
            case BaseConstant.NOTICEMESSAGE_TYPE_APPPUSH:
                break;
            default:
                return Result.error("参数错误");
        }
        NoticeMessage message = model.prototype();
        noticeMessageDao.save(message);
        // 订单处理
        OrderTaskModel.dealOrder(model.getOrderTaskModel() == null ? model
                        .getOrderTaskModel().getOrderNo() : "",
                BaseConstant.BUSINESS_STATE_YES, "消息发送成功");
        return Result.success();
    }

    /**
     * 通过用户id和状态查询统计
     *
     * @param model
     * @return
     */
    @Override
    public Object getByUserIdAndState(NoticeMessageModel model) {
        PageDataList<NoticeMessage> PageDataList = noticeMessageDao.list(model);
        return PageDataList.getPage().getTotal();
    }

    /**
     * 短信发送方法
     *
     * @param model
     */
    private void sendSms(NoticeMessageModel model) {
        String mobile = model.getReceiveAddr();
        String content = model.getContent();
        String smsRoute = Global.getValue(BaseConstant.SMS_ROUTE);
        if (StringUtil.isBlank(smsRoute) || !Global.sysState()) {
            LogUtil.info("短信通道未配置");
            return;
        }
        SmsService smsService = BeanUtil.getBean(smsRoute + "SmsService");// 短信渠道商
        int state = BaseConstant.BUSINESS_STATE_NO;
        String result = "";
        String resultCode = "";
        try {
            JSONObject resultJson = smsService.sendSms(mobile, content);
            resultCode = resultJson.getString("resultCode");
            if (StringUtil.isNull(resultCode).equals("0000")) {
                state = BaseConstant.BUSINESS_STATE_YES;
            } else {
                state = BaseConstant.BUSINESS_STATE_FAIL;
                result = resultJson.getString("result");
            }
        } catch (Exception e) {
            state = BaseConstant.BUSINESS_STATE_FAIL;
            result = e.getMessage();
        } finally {
            model.setResult(result);
            model.setState(state);
            model.setResultCode(resultCode);
        }
    }

}