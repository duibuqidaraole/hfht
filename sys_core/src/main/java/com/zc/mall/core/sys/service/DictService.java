package com.zc.mall.core.sys.service;

import com.zc.mall.core.sys.model.DictModel;
import com.zc.sys.common.form.Result;

/**
 * 字典
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年7月29日
 */

/**
 * 字典
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月7日
 */
public interface DictService {
    /**
     * 列表
     *
     * @param model
     * @return
     */
    Result list(DictModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    Result add(DictModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    Result update(DictModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    Result getById(DictModel model);

    /**
     * nid查询字典
     *
     * @param model
     * @return
     */
    Result getByNid(DictModel model);

    /**
     * 刷新字典缓存
     * @return
     */
    Result refreshDictCache();
}
