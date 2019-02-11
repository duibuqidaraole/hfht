package com.zc.mall.core.account.model;

import com.zc.mall.core.account.entity.UserSelfHelpAccountLog;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.model.jpa.Page;
import org.springframework.beans.BeanUtils;

/**
 * 用户自助记账
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2018年08月05日
 */
public class UserSelfHelpAccountLogModel extends UserSelfHelpAccountLog {
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
     * 开始时间
     **/
    private String startTime;
    /**
     * 结束时间
     **/
    private String endTime;
    /**
     * 用户 model
     **/
    private UserModel userModel;
    /**
     * 用户自助记账实体
     **/
    private UserSelfHelpAccountLog userSelfHelpAccountLog;
    /**
     * 用户id
     **/
    private long userId;

    /**
     * 实体转换model
     */
    public static UserSelfHelpAccountLogModel instance(UserSelfHelpAccountLog userSelfHelpAccountLog) {
        if (userSelfHelpAccountLog == null || userSelfHelpAccountLog.getId() <= 0) {
            return null;
        }
        UserSelfHelpAccountLogModel userSelfHelpAccountLogModel = new UserSelfHelpAccountLogModel();
        BeanUtils.copyProperties(userSelfHelpAccountLog, userSelfHelpAccountLogModel);
        return userSelfHelpAccountLogModel;
    }

    /**
     * model转换实体
     */
    public UserSelfHelpAccountLog prototype() {
        UserSelfHelpAccountLog userSelfHelpAccountLog = new UserSelfHelpAccountLog();
        BeanUtils.copyProperties(this, userSelfHelpAccountLog);
        return userSelfHelpAccountLog;
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
     * 获取【用户model】
     **/
    public UserModel getUserModel() {
        return userModel;
    }

    /**
     * 设置【用户model】
     **/
    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    /**
     * 获取【开始时间】
     **/
    public String getStartTime() {
        return startTime;
    }


    /**
     * 设置【开始时间】
     **/
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取【结束时间】
     **/
    public String getEndTime() {
        return endTime;
    }


    /**
     * 设置【结束时间】
     **/
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取【用户自助记账实体】
     **/
    public UserSelfHelpAccountLog getUserSelfHelpAccountLog() {
        return userSelfHelpAccountLog;
    }

    /**
     * 设置【用户自助记账实体】
     **/
    public void setUserSelfHelpAccountLog(
            UserSelfHelpAccountLog userSelfHelpAccountLog) {
        this.userSelfHelpAccountLog = userSelfHelpAccountLog;
    }

    /**
     * 获取【用户id】
     **/
    public long getUserId() {
        return userId;
    }

    /**
     * 设置【用户id】
     **/
    public void setUserId(long userId) {
        this.userId = userId;
    }

}