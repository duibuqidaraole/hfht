package com.zc.mall.core.account.model;

import com.zc.mall.core.account.entity.AccountStatistics;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.model.jpa.Page;
import org.springframework.beans.BeanUtils;

/**
 * 账户统计
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年12月20日
 */
public class AccountStatisticsModel extends AccountStatistics {
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
     * 用户Model
     **/
    private UserModel userModel;
    /**
     * 用户Id
     **/
    private long userId;

    /**
     * 实体转换model
     */
    public static AccountStatisticsModel instance(AccountStatistics accountStatistics) {
        if (accountStatistics == null || accountStatistics.getId() <= 0) {
            return null;
        }
        AccountStatisticsModel accountStatisticsModel = new AccountStatisticsModel();
        BeanUtils.copyProperties(accountStatistics, accountStatisticsModel);
        return accountStatisticsModel;
    }

    /**
     * model转换实体
     */
    public AccountStatistics prototype() {
        AccountStatistics accountStatistics = new AccountStatistics();
        BeanUtils.copyProperties(this, accountStatistics);
        return accountStatistics;
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
     * 获取【用户Model】
     **/
    public UserModel getUserModel() {
        return userModel;
    }

    /**
     * 设置【用户Model】
     **/
    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    /**
     * 获取【用户Id】
     **/
    public long getUserId() {
        return userId;
    }


    /**
     * 设置【用户Id】
     **/
    public void setUserId(long userId) {
        this.userId = userId;
    }


}