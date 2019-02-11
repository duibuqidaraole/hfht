package com.zc.mall.core.sys.dao;

import com.zc.mall.core.sys.entity.Config;
import com.zc.mall.core.sys.model.ConfigModel;
import com.zc.sys.common.dao.BaseDao;
import com.zc.sys.common.model.jpa.PageDataList;

/**
 * 系统参数配置
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月08日
 */
public interface ConfigDao extends BaseDao<Config> {

    /**
     * 系统配置列表
     *
     * @param model
     * @return
     */
    PageDataList<Config> list(ConfigModel model);

    /**
     * 获取系统配置
     *
     * @return
     */
    ConfigModel getConfig();

    Config getByNid(String nid);

}