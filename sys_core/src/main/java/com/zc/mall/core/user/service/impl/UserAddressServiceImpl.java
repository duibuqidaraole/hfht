package com.zc.mall.core.user.service.impl;

import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.user.dao.UserAddressDao;
import com.zc.mall.core.user.dao.UserDao;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.entity.UserAddress;
import com.zc.mall.core.user.model.UserAddressModel;
import com.zc.mall.core.user.model.UserModel;
import com.zc.mall.core.user.service.UserAddressService;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户地址
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@Service
public class UserAddressServiceImpl implements UserAddressService {

    @Resource
    private UserAddressDao userAddressDao;
    @Resource
    private UserDao userDao;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    public Result list(UserAddressModel model) {
        PageDataList<UserAddress> pageDataList = userAddressDao.list(model);
        PageDataList<UserAddressModel> pageDataList_ = new PageDataList<>();
        pageDataList_.setPage(pageDataList.getPage());
        List<UserAddressModel> list = new ArrayList<>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (UserAddress userAddress : pageDataList.getList()) {
                UserAddressModel model_ = UserAddressModel.instance(userAddress);
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
    public Result add(UserAddressModel model) {
        this.checkAdd(model);
        this.initAdd(model);
        userAddressDao.save(model.prototype());
        return Result.success();
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result update(UserAddressModel model) {
        UserAddress userAddress = this.checkUpdate(model);
        this.initUpdate(model, userAddress);
        userAddressDao.update(model.prototype());
        return Result.success();
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getById(UserAddressModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        UserAddress userAddress = userAddressDao.find(model.getId());
        if (userAddress == null) {
            return Result.error("参数错误！");
        }
        return Result.success().setData(UserAddressModel.instance(userAddress));
    }

    /**
     * 添加用户地址
     *
     * @param beUserModel
     */
    @Override
    public void setUserAddress(UserModel beUserModel) {
        UserAddressModel userAddressModel = new UserAddressModel();
        userAddressModel.setUser(beUserModel);
        List<UserAddress> list = userAddressDao.list(userAddressModel).getList();
        if (list != null && list.size() > 0) {
            UserAddress userAddress = list.get(0);
            String address = userAddress.getProvince() + userAddress.getCity() + userAddress.getArea() + userAddress.getAddress();
            beUserModel.setUserAddressDefault(address);
        }
    }

    /**
     * 添加用户地址
     *
     * @param userModelList
     */
    @Override
    public void setUserAddress(List<UserModel> userModelList) {
        for (UserModel userModel : userModelList) {
            setUserAddress(userModel);
        }
    }

    /**
     * 校验
     *
     * @param model
     */
    private void checkAdd(UserAddressModel model) {
        if (model.getUser() == null || model.getUser().getId() <= 0) {
            throw new BusinessException("用户信息有误，无法添加地址");
        }
        User user = userDao.find(model.getUser().getId());
        if (user == null) {
            throw new BusinessException("用户不存在，无法添加地址");
        }
        if (StringUtil.isBlank(model.getMobile()) || !StringUtil.isPhone(model.getMobile())) {
            throw new BusinessException("手机号码输出有误，无法添加地址");
        }
        if (StringUtil.isBlank(model.getName())) {
            throw new BusinessException("请输入用户名");
        }
//        if (StringUtil.isBlank(model.getPostCode())){
//            throw new BusinessException("请输入邮编");
//        }
        if (StringUtil.isBlank(model.getProvince()) || StringUtil.isBlank(model.getCity()) || StringUtil.isBlank(model.getArea()) || StringUtil.isBlank(model.getAddress())) {
            throw new BusinessException("请填写正确地址信息");
        }
        if (model.getState() > 0) {
            updateDefault();
        }
    }

    private void updateDefault() {
        userAddressDao.updateDefault();
    }

    /**
     * 初始化
     *
     * @param model
     */
    private void initAdd(UserAddressModel model) {
        model.setAddTime(DateUtil.getNow());
        if (model.getState() > 0) {
            model.setState(BaseConstant.BUSINESS_STATE_YES);
        }
    }

    /**
     * 校验
     *
     * @param model
     * @return
     */
    private UserAddress checkUpdate(UserAddressModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误！");
        }
        UserAddress userAddress = userAddressDao.find(model.getId());
        if (userAddress == null) {
            throw new BusinessException("参数错误！");
        }
        if (userAddress.getUser().getId() != model.getUser().getId()) {
            throw new BusinessException("参数错误！");
        }
        if (StringUtil.isBlank(model.getMobile()) || !StringUtil.isPhone(model.getMobile())) {
            throw new BusinessException("手机号码输出有误，无法添加地址");
        }
        if (StringUtil.isBlank(model.getName())) {
            throw new BusinessException("请输入用户名");
        }
        if (StringUtil.isBlank(model.getPostCode())) {
            throw new BusinessException("请输入邮编");
        }
        if (StringUtil.isBlank(model.getProvince()) || StringUtil.isBlank(model.getCity()) || StringUtil.isBlank(model.getArea()) || StringUtil.isBlank(model.getAddress())) {
            throw new BusinessException("请填写正确地址信息");
        }
        return userAddress;
    }

    /**
     * 初始化
     *
     * @param model
     */
    private void initUpdate(UserAddressModel model, UserAddress userAddress) {
        model.setAddTime(userAddress.getAddTime());
        if (model.getState() == 0) {
            model.setState(userAddress.getState());
        }
    }

}