package com.zc.mall.core.xc.dao.impl;

import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.xc.dao.SiteDao;
import com.zc.mall.core.xc.entity.Site;
import com.zc.mall.core.xc.model.SiteModel;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.model.jpa.OrderFilter.OrderType;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import com.zc.sys.common.model.jpa.SearchFilter;
import com.zc.sys.common.model.jpa.SearchFilter.Operators;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Repository;

/**
 * 栏目
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Repository
public class SiteDaoImpl extends BaseDaoImpl<Site> implements SiteDao {
    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    public PageDataList<Site> list(SiteModel model) {
        QueryParam param = QueryParam.getInstance();
        if (StringUtil.isNotBlank(model.getSearchName())) {
            SearchFilter orFilter1 = new SearchFilter("name", Operators.LIKE, StringUtil.isNull(model.getSearchName()));
            SearchFilter orFilter2 = new SearchFilter("nid", Operators.LIKE, StringUtil.isNull(model.getSearchName()));
            param.addOrFilter(orFilter1, orFilter2);
        } else {
            if (StringUtil.isNotBlank(model.getName())) {
                param.addParam("name", Operators.LIKE, StringUtil.isNull(model.getName()));
            }
            if (model.getPid() > 0) {
                param.addParam("pid", model.getPid());
            }
            if (StringUtil.isNotBlank(model.getNid())) {
                param.addParam("nid", Operators.LIKE, StringUtil.isNull(model.getNid()));
            }
            if (model.getState() != 0) {
                param.addParam("state", model.getState());
            }
            // 通过添加管理员Id查找栏目表
            if (model.getOperator() != null && model.getOperator().getId() > 0) {
                param.addParam("operator.id", model.getOperator().getId());
            }
        }
        param.addOrder(OrderType.ASC, "id");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }

    /**
     * 列表无分页
     *
     * @param model
     * @return
     */
    @Override
    public PageDataList<Site> listNoPage(SiteModel model) {
        QueryParam param = QueryParam.getInstance();
        if (model.getState() != 0) {
            param.addParam("state", model.getState());
        }
        if (model.getPid() > 0) {
            param.addParam("pid", model.getPid());
        }
        param.addOrder(OrderType.ASC, "id");
        return super.findPageList(param);
    }

    /**
     * @param nid
     */
    @Override
    public Site findByNid(String nid) {
        QueryParam queryParam = QueryParam.getInstance();
        queryParam.addParam("nid", nid);
        queryParam.addParam("state", BaseConstant.BUSINESS_STATE_YES);
        return super.findByCriteriaForUnique(queryParam);
    }

}