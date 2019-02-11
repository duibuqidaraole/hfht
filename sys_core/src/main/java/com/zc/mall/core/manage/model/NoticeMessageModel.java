package com.zc.mall.core.manage.model;

import com.zc.mall.core.manage.entity.NoticeMessage;
import com.zc.mall.core.sys.model.TemplateModel;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.model.jpa.Page;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.beans.BeanUtils;

import java.util.Map;

/**
 * 通知消息
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月15日
 */
public class NoticeMessageModel extends NoticeMessage {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;

    /**
     * 当前页码
     **/
    private int pageNo;
    /**
     * 每页数据条数
     **/
    private int pageSize = Page.ROWS;
    /**
     * 条件查询
     **/
    private String searchName;
    /**
     * 操作员Model
     **/
    private OperatorModel operatorModel;
    /**
     * 订单
     **/
    private OrderTaskModel orderTaskModel;
    /**
     * 发送用户 Model
     **/
    private UserModel sendUserModel;
    /**
     * 接收用户Model
     **/
    private UserModel receiveUserModel;
    /**
     * 开始时间
     **/
    private String startTime;
    /**
     * 结束时间
     **/
    private String endTime;
    /**
     * 用户名
     **/
    private String userName;
    /**
     * 发送参数
     **/
    private Map<String, Object> sendParam;

    public NoticeMessageModel() {

    }

    public NoticeMessageModel(String nid, int type, User sendUser, User receiveUser, String receiveAddr) {
        this.setNid(nid);
        this.setType(type);
        this.setSendUser(sendUser);
        this.setReceiveUser(receiveUser);
        this.setReceiveAddr(receiveAddr);
    }

    public NoticeMessageModel(int type, String receiveAddr) {
        this.setType(type);
        this.setReceiveAddr(receiveAddr);
    }

    public NoticeMessageModel(String nid, int type, String receiveAddr) {
        this.setType(type);
        this.setReceiveAddr(receiveAddr);
        this.setNid(nid);
    }

    /**
     * 实体转换model
     */
    public static NoticeMessageModel instance(NoticeMessage noticeMessage) {
        if (noticeMessage == null || noticeMessage.getId() <= 0) {
            return null;
        }
        NoticeMessageModel noticeMessageModel = new NoticeMessageModel();
        BeanUtils.copyProperties(noticeMessage, noticeMessageModel);
        return noticeMessageModel;
    }

    /**
     * model转换实体
     */
    public NoticeMessage prototype() {
        NoticeMessage noticeMessage = new NoticeMessage();
        BeanUtils.copyProperties(this, noticeMessage);
        return noticeMessage;
    }

    /**
     * 初始化站内信发送
     */
    public void initMessage(TemplateModel templateModel) {
        this.setAddTime(DateUtil.getNow());
        this.setState(2);
        this.setContent(StringUtil.fillTemplet(templateModel.getContent(), this));
        this.setTitle(StringUtil.fillTemplet(templateModel.getTitle(), this));
        this.setNid(templateModel.getNid());
        this.setSendParam(null);
    }

    /**
     * 获取【当前页码】
     **/
    public int getPageNo() {
        return pageNo;
    }

    /**
     * 设置【当前页码】
     **/
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * 获取【每页数据条数】
     **/
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置【每页数据条数】
     **/
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取【条件查询】
     **/
    public String getSearchName() {
        return searchName;
    }

    /**
     * 设置【条件查询】
     **/
    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    /**
     * 获取【operatorModel】
     **/
    public OperatorModel getOperatorModel() {
        return operatorModel;
    }

    /**
     * 设置【operatorModel】
     **/
    public void setOperatorModel(OperatorModel operatorModel) {
        this.operatorModel = operatorModel;
    }

    /**
     * 获取【订单】
     **/
    public OrderTaskModel getOrderTaskModel() {
        return orderTaskModel;
    }

    /**
     * 设置【订单】
     **/
    public void setOrderTaskModel(OrderTaskModel orderTaskModel) {
        this.orderTaskModel = orderTaskModel;
    }

    /**
     * 获取【发送用户Model】
     **/
    public UserModel getSendUserModel() {
        return sendUserModel;
    }

    /**
     * 设置【发送用户Model】
     **/
    public void setSendUserModel(UserModel sendUserModel) {
        this.sendUserModel = sendUserModel;
    }

    /**
     * 获取【接收用户Model】
     **/
    public UserModel getReceiveUserModel() {
        return receiveUserModel;
    }

    /**
     * 设置【接收用户Model】
     **/
    public void setReceiveUserModel(UserModel receiveUserModel) {
        this.receiveUserModel = receiveUserModel;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取【发送参数】
     **/
    public Map<String, Object> getSendParam() {
        return sendParam;
    }

    /**
     * 设置【发送参数】
     **/
    public void setSendParam(Map<String, Object> sendParam) {
        this.sendParam = sendParam;
    }

}