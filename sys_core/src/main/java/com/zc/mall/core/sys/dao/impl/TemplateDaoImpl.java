package com.zc.mall.core.sys.dao.impl;

import com.zc.mall.core.sys.dao.TemplateDao;
import com.zc.mall.core.sys.entity.Template;
import com.zc.mall.core.sys.model.TemplateModel;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.model.jpa.OrderFilter.OrderType;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import com.zc.sys.common.model.jpa.SearchFilter;
import com.zc.sys.common.model.jpa.SearchFilter.Operators;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Repository;

/**
 * 模版配置
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月08日
 */
@Repository
public class TemplateDaoImpl extends BaseDaoImpl<Template> implements
        TemplateDao {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    public PageDataList<Template> list(TemplateModel model) {
        QueryParam param = QueryParam.getInstance();
        if (StringUtil.isNotBlank(model.getSearchName())) {
            SearchFilter orFilter1 = new SearchFilter("name", Operators.LIKE,
                    model.getSearchName().trim());
            SearchFilter orFilter2 = new SearchFilter("nid", Operators.LIKE,
                    model.getSearchName().trim());
            param.addOrFilter(orFilter1, orFilter2);
        }
        if (StringUtil.isNotBlank(model.getName())) {
            param.addParam("name", Operators.LIKE, model.getName().trim());
        }
        if (model.getState() != 0) {
            param.addParam("state", model.getState());
        }
        if (model.getType() != 0) {
            param.addParam("type", model.getType());
        }
        param.addOrder(OrderType.ASC, "id");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }

    /**
     * 根据nid查找使用中的模版
     *
     * @param model
     * @return
     */
    @Override
    public Template findByNid(String nid) {
        Template template = (Template) this.findObjByProperty("nid", nid);
        if (template != null && template.getState() == 1) {
            return template;
        }
        return null;
    }

}