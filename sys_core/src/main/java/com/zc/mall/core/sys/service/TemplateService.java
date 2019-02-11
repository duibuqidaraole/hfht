package com.zc.mall.core.sys.service;

import com.zc.mall.core.sys.model.TemplateModel;
import com.zc.sys.common.form.Result;

/**
 * 模版配置
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月08日
 */
public interface TemplateService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(TemplateModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(TemplateModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(TemplateModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(TemplateModel model);

    /**
     * 刷新缓存
     *
     * @return
     */
    Result refreshTemplateCache();

}