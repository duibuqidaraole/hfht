package com.zc.mall.core.integral.service.impl;

import com.zc.mall.core.integral.dao.IntegralLogDao;
import com.zc.mall.core.integral.entity.IntegralLog;
import com.zc.mall.core.integral.model.IntegralLogModel;
import com.zc.mall.core.integral.service.IntegralLogService;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 积分日志
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Service
public class IntegralLogServiceImpl implements IntegralLogService {

    @Resource
    private IntegralLogDao integralLogDao;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result list(IntegralLogModel model) {
        PageDataList<IntegralLog> pageDataList = integralLogDao.list(model);
        PageDataList<IntegralLogModel> pageDataList_ = new PageDataList<IntegralLogModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<IntegralLogModel> list = new ArrayList<IntegralLogModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (IntegralLog integralLog : pageDataList.getList()) {
                IntegralLogModel model_ = IntegralLogModel.instance(integralLog);
                model_.setToUserModel(UserModel.instance(integralLog.getToUser()));
                model_.setUserModel(UserModel.instance(integralLog.getUser()));
                model_.setUser(null);
                model_.setToUser(null);
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
    public void add(IntegralLogModel model) {
        integralLogDao.save(model.prototype());
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result update(IntegralLogModel model) {

        return null;
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getById(IntegralLogModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        IntegralLog integralLog = integralLogDao.find(model.getId());
        IntegralLogModel model_ = IntegralLogModel.instance(integralLog);
        model_.setToUserModel(UserModel.instance(integralLog.getToUser()));
        model_.setUserModel(UserModel.instance(integralLog.getUser()));
        return Result.success().setData(model_);
    }

    @Override
    public Result getByUserId(IntegralLogModel model) {
        return null;
    }

}