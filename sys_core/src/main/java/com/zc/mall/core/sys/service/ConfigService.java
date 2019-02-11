package com.zc.mall.core.sys.service;

import com.zc.mall.core.sys.model.ConfigModel;
import com.zc.sys.common.form.Result;

/**
 * 系统参数配置
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月08日
 */
public interface ConfigService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(ConfigModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(ConfigModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(ConfigModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(ConfigModel model);

    /**
     * 通过Nid获取
     *
     * @param model
     * @return
     */
    public Result getConfigByNid(ConfigModel model);

    /**
     * 刷新缓存
     *
     * @return
     */
    Result refreshConfigCache();

}