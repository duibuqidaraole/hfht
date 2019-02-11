package com.zc.mall.core.user.service.impl;

import com.zc.mall.core.user.dao.UserBusinessApplyDao;
import com.zc.mall.core.user.entity.UserBusinessApply;
import com.zc.mall.core.user.model.UserBusinessApplyModel;
import com.zc.mall.core.user.service.UserBusinessApplyService;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 商家申请信息
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Service
public class UserBusinessApplyServiceImpl implements UserBusinessApplyService {

    @Resource
    private UserBusinessApplyDao userBusinessApplyDao;

    /**
     * 查询商家列表
     *
     * @param model
     * @return
     */
    @Override
    public Result list(UserBusinessApplyModel model) {
        PageDataList<UserBusinessApply> pageDataList = userBusinessApplyDao.list(model);
        PageDataList<UserBusinessApplyModel> pageDataList_ = new PageDataList<UserBusinessApplyModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<UserBusinessApplyModel> list = new ArrayList<UserBusinessApplyModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (UserBusinessApply userBusinessApply : pageDataList.getList()) {
                UserBusinessApplyModel model_ = UserBusinessApplyModel.instance(userBusinessApply);
                list.add(model_);
            }
        }
        pageDataList_.setList(list);
        return Result.success().setData(pageDataList_);
    }

    /**
     * 添加商家
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result add(UserBusinessApplyModel model) {
        model.addInitCheckParams(model);
        userBusinessApplyDao.save(model.prototype());
        return Result.success();
    }

    /**
     * 通过Id修改商家
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result update(UserBusinessApplyModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        model.setUpdateParams(model);
        userBusinessApplyDao.update(model.prototype());
        return Result.success();
    }

    /**
     * 通过Id查询商家
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result getById(UserBusinessApplyModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        UserBusinessApply userBusinessApply = userBusinessApplyDao.find(model.getId());
        return Result.success().setData(userBusinessApply);
    }

    /**
     * 商家申请
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result delRegRequest(UserBusinessApplyModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        UserBusinessApply userBusinessApply = userBusinessApplyDao.find(model.getId());
        //处理申请通过
        UserBusinessApply userBusinessApplyEnty = model.delSuccss(userBusinessApply);
        userBusinessApplyDao.update(model.delSuccss(userBusinessApplyEnty));
        return Result.success();
    }

}
