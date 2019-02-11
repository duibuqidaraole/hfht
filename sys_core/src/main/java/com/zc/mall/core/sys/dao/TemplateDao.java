package com.zc.mall.core.sys.dao;

import com.zc.mall.core.sys.entity.Template;
import com.zc.mall.core.sys.model.TemplateModel;
import com.zc.sys.common.dao.BaseDao;
import com.zc.sys.common.model.jpa.PageDataList;

/**
 * 模版配置
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月08日
 */
public interface TemplateDao extends BaseDao<Template> {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    PageDataList<Template> list(TemplateModel model);

    /**
     * 根据nid查找使用中的模版
     *
     * @param model
     * @return
     */
    Template findByNid(String nid);

}