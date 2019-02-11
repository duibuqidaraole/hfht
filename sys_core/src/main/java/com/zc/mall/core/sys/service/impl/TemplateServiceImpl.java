package com.zc.mall.core.sys.service.impl;

import com.zc.mall.core.common.global.InitializeWebConfigContext;
import com.zc.mall.core.sys.constant.ConfigParamConstant;
import com.zc.mall.core.sys.dao.TemplateDao;
import com.zc.mall.core.sys.entity.Template;
import com.zc.mall.core.sys.model.TemplateModel;
import com.zc.mall.core.sys.service.TemplateService;
import com.zc.sys.common.cache.RedisCacheUtil;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 模版配置
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月08日
 */
@Service
public class TemplateServiceImpl implements TemplateService {

    @Resource
    private TemplateDao templateDao;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    public Result list(TemplateModel model) {
        PageDataList<Template> pageDataList = templateDao.list(model);
        PageDataList<TemplateModel> pageDataList_ = new PageDataList<TemplateModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<TemplateModel> list = new ArrayList<TemplateModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (Template template : pageDataList.getList()) {
                TemplateModel model_ = TemplateModel.instance(template);
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
    public Result add(TemplateModel model) {
        model.validParam();// 校验参数
        Template template = model.prototype();
        templateDao.save(template);
        refreshTemplateCache();
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
    public Result update(TemplateModel model) {
        model.validParam();// 校验参数
        Template template = templateDao.find(model.getId());
        model.setUpdateParam(template);// 设置修改基本参数
        templateDao.update(template);
        refreshTemplateCache();
        return Result.success();
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getById(TemplateModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        Template template = templateDao.find(model.getId());
        return Result.success().setData(template);
    }

    /**
     * 刷新缓存
     *
     * @return
     */
    @Override
    public Result refreshTemplateCache() {
        RedisCacheUtil.instance().delCode(ConfigParamConstant.SYS_TEMPLATE);
        InitializeWebConfigContext.initDict();
        return Result.success();
    }

}