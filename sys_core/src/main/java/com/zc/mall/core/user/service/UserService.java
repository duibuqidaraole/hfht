package com.zc.mall.core.user.service;

import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;

/**
 * 用户
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public interface UserService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(UserModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(UserModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(UserModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public UserModel getById(UserModel model);

    /**
     * 注册请求
     *
     * @param model
     * @return
     */
    public Result regRequest(UserModel model);

    /**
     * 商家旗下借款人注册
     *
     * @param model
     * @return
     */
    public Result regBorrowUserInRelation(UserModel model);

    /**
     * 找回密码
     *
     * @param model
     * @return
     */
    public Result backKeyCode(UserModel model);

    /**
     * 登录
     *
     * @param model
     * @return
     */
    public Result login(UserModel model) throws Exception;

    /**
     * 修改交易密码
     *
     * @param model
     * @return
     */
    public Result updatePayPwd(UserModel model);

    /**
     * 修改登录密码
     *
     * @param model
     * @return
     */
    public Result updatePwd(UserModel model);

    /**
     * 修改找回的登录密码
     *
     * @param model
     * @return
     */
    public Result backDatePwd(UserModel model);

    /**
     * 邮箱认证
     *
     * @param model
     * @return
     */
    public Result emailState(UserModel model);

    /**
     * 第三方登录请求
     *
     * @param model
     * @return
     */
    public Object apiLogin(UserModel model);

    /**
     * 修改手机号请求
     *
     * @param model
     * @return
     */
    public Object modifyMobileRequest(UserModel model);

    /**
     * 修改手机号处理
     *
     * @param model
     * @return
     */
    public Object modifyMobileDeal(UserModel model);

    /**
     * 查询所有注册用户
     *
     * @param mobile
     * @return
     */
    long findUserNumber();

    /**
     * 查询今日所有注册用户
     *
     * @param mobile
     * @return
     */
    long findUserNumberByToday();

    /**
     * 通过手机号查询
     *
     * @param mobile
     * @return
     */
    Result findUserByMobile(UserModel model);

    /**
     * 根据id删除
     *
     * @param userId
     * @return
     */
    public Result deleteById(UserModel model);

    /**
     * 根据id删除
     *
     * @param model userId
     * @return
     */
    public Result updateRelationByUserId(UserModel model);


    /**
     * 检查用户是否注册
     *
     * @param model
     * @return
     */
    Object userCheck(UserModel model);

    /**
     * 获取用户及相关信息统计
     *
     * @param userId
     * @return
     */
    Object getUserInfoCount(Long userId);

    /**
     * 根据微信openId登录
     *
     * @param model
     * @return
     */
    Object loginByOpenId(UserModel model);

    /**
     * 获取所有用户信息
     *
     * @param userId
     * @return
     */
    Object getAllUserInfo(Long userId);

    /**
     * 根据id获取用户
     *
     * @param userId
     * @return
     */
    User find(Long userId);

    /**
     * 带等级用户列表
     *
     * @param model
     * @return
     */
    PageDataList<UserModel> listWithGrade(UserModel model);

    /**
     * 根据微信openId判断是否注册
     *
     * @param model
     * @return
     */
    Object checkByOpenId(UserModel model);

    /**
     * 添加修改生日
     * @param model
     * @return
     */
    Object addBirthday(UserModel model);

    /**
     * 添加地址
     * @param model
     * @return
     */
    Object addAddress(UserModel model);

    /**
     * 添加用户需求
     * @param model
     * @return
     */
    Object addDemands(UserModel model);

    /**
     * 绑定手机号
     * @param model
     * @return
     */
    Object bindMobile(UserModel model);
}

