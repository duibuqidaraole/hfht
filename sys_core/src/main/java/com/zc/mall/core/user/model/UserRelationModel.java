package com.zc.mall.core.user.model;

import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.user.dao.UserDao;
import com.zc.mall.core.user.dao.UserRelationDao;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.entity.UserRelation;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.model.jpa.Page;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.beans.BeanUtils;

/**
 * 用户关系
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2018年03月15日
 */
public class UserRelationModel extends UserRelation {
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
     * 借款人Model
     **/
    private UserModel userModel;
    /**
     * 借款人信息Model
     **/
    private UserInfoModel userInfoModel;
    /**
     * 商家Model
     **/
    private UserModel beUserModel;
    /**
     * 借款人Id
     **/
    private long userId;
    /**
     * 商家Id
     **/
    private long beUserId;
    /**
     * 实名认证状态
     **/
    private int realNameState;
    /**
     * 用户信息类型
     **/
    private int userInfotype;

    /**
     * 实体转换model
     */
    public static UserRelationModel instance(UserRelation userRelation) {
        if (userRelation == null || userRelation.getId() <= 0) {
            return null;
        }
        UserRelationModel userRelationModel = new UserRelationModel();
        BeanUtils.copyProperties(userRelation, userRelationModel);
        return userRelationModel;
    }

    /**
     * model转换实体
     */
    public UserRelation prototype() {
        UserRelation userRelation = new UserRelation();
        BeanUtils.copyProperties(this, userRelation);
        return userRelation;
    }


    /**
     * 设置修改基本参数
     *
     * @param menu
     */
    public void setUpdateParam() {
        this.setState(this.getState());
        this.setType(this.getType());
        this.setUser(this.getUser());
        this.setBeUser(this.getBeUser());
        this.setAddTime(DateUtil.getNow());

    }

    /**
     * 添加初始化参数
     *
     * @param menu
     */
    public void addInit() {
        UserDao userDao = (UserDao) BeanUtil.getBean(UserDao.class);
        User user = userDao.findObjByProperty("id", this.getUserId());
        User beUser = userDao.findObjByProperty("id", this.getBeUserId());
        this.setType(BaseConstant.USERRELATION_TYPE_GENERAL);
        this.setUser(user);
        this.setBeUser(beUser);
        this.setState(BaseConstant.INFO_STATE_YES);
        this.setAddTime(DateUtil.getNow());
    }

    /**
     * 添加商家旗下借款人初始化参数
     *
     * @param menu
     */
    public void addUserInRelationInit() {
        UserDao userDao = (UserDao) BeanUtil.getBean(UserDao.class);
        User user = userDao.findObjByProperty("id", this.getUserId());
        User beUser = userDao.findObjByProperty("id", this.getBeUserId());
        this.setType(BaseConstant.USERRELATION_TYPE_ARTIF);
        this.setUser(user);
        this.setBeUser(beUser);
        this.setState(BaseConstant.INFO_STATE_YES);
        this.setAddTime(DateUtil.getNow());
    }

    /**
     * 参数检验
     *
     * @param menu
     */
    public void checkParam() {
        if (this.getBeUserId() < 0) {
            throw new BusinessException("该商家不存在");
        }
        if (this.getUserId() < 0) {
            throw new BusinessException("该用户不存在");
        }
        UserRelationDao userRelationDao = (UserRelationDao) BeanUtil.getBean(UserRelationDao.class);
        UserRelation userRelationEntiy = userRelationDao.findUserRelation(this);// 检测唯一性
        if (!StringUtil.isBlank(userRelationEntiy)) {
            throw new BusinessException("您已经关注过本商家了！");
        }
        if (this.getUserId() == this.getBeUserId()) {
            throw new BusinessException("不能自己关注自己！");
        }
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
     * 获取【借款人Model】
     **/
    public UserModel getUserModel() {
        return userModel;
    }


    /**
     * 设置【借款人Model】
     **/
    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }


    /**
     * 获取【商家Model】
     **/
    public UserModel getBeUserModel() {
        return beUserModel;
    }


    /**
     * 设置【商家Model】
     **/
    public void setBeUserModel(UserModel beUserModel) {
        this.beUserModel = beUserModel;
    }


    /**
     * 设置【商家Id】
     **/
    public void setBeUserId(int beUserId) {
        this.beUserId = beUserId;
    }

    /**
     * 获取【借款人信息Model】
     **/
    public UserInfoModel getUserInfoModel() {
        return userInfoModel;
    }


    /**
     * 设置【借款人信息Model】
     **/
    public void setUserInfoModel(UserInfoModel userInfoModel) {
        this.userInfoModel = userInfoModel;
    }

    /**
     * 获取【用户信息类型】
     **/
    public int getUserInfotype() {
        return userInfotype;
    }


    /**
     * 设置【用户信息类型】
     **/
    public void setUserInfotype(int userInfotype) {
        this.userInfotype = userInfotype;
    }

    /**
     * 获取【借款人Id】
     **/
    public long getUserId() {
        return userId;
    }


    /**
     * 设置【借款人Id】
     **/
    public void setUserId(long userId) {
        this.userId = userId;
    }


    /**
     * 获取【商家Id】
     **/
    public long getBeUserId() {
        return beUserId;
    }


    /**
     * 设置【商家Id】
     **/
    public void setBeUserId(long beUserId) {
        this.beUserId = beUserId;
    }

    /**
     * 获取【实名认证状态】
     **/
    public int getRealNameState() {
        return realNameState;
    }

    /**
     * 设置【实名认证状态】
     **/
    public void setRealNameState(int realNameState) {
        this.realNameState = realNameState;
    }


}