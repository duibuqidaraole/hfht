package com.zc.mall.core.manage.service.impl;

import com.zc.mall.core.manage.dao.OperatorDao;
import com.zc.mall.core.manage.entity.Operator;
import com.zc.mall.core.manage.model.OperatorModel;
import com.zc.mall.core.manage.model.RoleModel;
import com.zc.mall.core.manage.service.OperatorService;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.encrypt.MD5;
import com.zc.sys.common.util.http.RequestUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理员
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Service
public class OperatorServiceImpl implements OperatorService {

    @Resource
    private OperatorDao operatorDao;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result list(OperatorModel model) {
        PageDataList<Operator> pageDataList = operatorDao.list(model);
        PageDataList<OperatorModel> pageDataList_ = new PageDataList<OperatorModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<OperatorModel> list = new ArrayList<OperatorModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (Operator operator : pageDataList.getList()) {
                OperatorModel model_ = OperatorModel.instance(operator);
                model_.setRoleModel(RoleModel.instance(operator.getRole()));
                if (null != operator.getUser())
                    model_.setUserModel(UserModel.instance(operator.getUser()));
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
    public Result add(OperatorModel model) {
        model.validParam();// 校验参数
        Operator operator = model.prototype();
        model.initParamadd(operator);// 注册初始化
        operatorDao.save(operator);
        operatorDao.clear();
        return Result.success().setData(operator);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result update(OperatorModel model) {
        model.validParam();// 校验参数
        Operator operator = operatorDao.find(model.getId());
        model.setUpdateParam(operator);// 设置修改基本参数
        operatorDao.update(operator);
        return Result.success();
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getById(OperatorModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        Operator operater = operatorDao.find(model.getId());
        OperatorModel model_ = OperatorModel.instance(operater);
        model_.setRoleModel(RoleModel.instance(operater.getRole()));
        model_.setUserModel(UserModel.instance(operater.getUser()));
        return Result.success().setData(model_);
    }

    /**
     * 登录
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result signIn(OperatorModel model) {
        model.checkLogin();// 登录校验参数
        String loginName = StringUtil.isNull(model.getName());
        String pwd = MD5.toMD5(StringUtil.isNull(model.getPwd()));
        QueryParam param = QueryParam.getInstance();
        param.addParam("name", loginName);
        param.addParam("pwd", pwd);
        List<Operator> operaterList = operatorDao.findByCriteria(param);
        if (operaterList.size() != 1) {
            return Result.error("登录失败，用户名或密码有误");
        }
        Operator operater = operaterList.get(0);
        OperatorModel model_ = OperatorModel.instance(operater);
        model_.setRoleModel(RoleModel.instance(operater.getRole()));
        model_.setUserModel(UserModel.instance(operater.getUser()));
        Operator operator = operatorDao.find(operater.getId());
        if (operator != null) {
            operator.setLoginIp(RequestUtil.getClientIp());
            operator.setLoginTime(DateUtil.getNow());
        }
        if (operator.getUser() == null)
            operator.setUser(null);
        operatorDao.update(operator);
        return Result.success().setData(model_);
    }

    /**
     * 通过用户Id查询管理员信息
     *
     * @param model
     * @return
     */
    @Override
    public Result getByUserId(OperatorModel model) {
        Operator operater = operatorDao.findObjByProperty("user.id",
                model.getUserId());
        OperatorModel model_ = OperatorModel.instance(operater);
        if (operater.getRole() != null) {
            model_.setRoleModel(RoleModel.instance(operater.getRole()));
        }
        if (operater.getUser() != null) {
            model_.setUserModel(UserModel.instance(operater.getUser()));
        }
        return Result.success().setData(model_);
    }

    /**
     * 添加后台商家
     *
     * @param model
     * @return
     */
    @Override
    public Result addRelation(OperatorModel model) {
        // 商家注册初始化
        Operator operator = model.initParamAddRelation();
        operatorDao.save(operator);
        return Result.success().setData(operator);
    }
}