package com.zc.mall.core.xc.dao.impl;

import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.xc.dao.ArticleDao;
import com.zc.mall.core.xc.entity.Article;
import com.zc.mall.core.xc.model.ArticleModel;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.model.jpa.OrderFilter.OrderType;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import com.zc.sys.common.model.jpa.SearchFilter;
import com.zc.sys.common.model.jpa.SearchFilter.Operators;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 文章
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Repository
public class ArticleDaoImpl extends BaseDaoImpl<Article> implements ArticleDao {

    @Override
    public PageDataList<Article> list(ArticleModel model) {
        QueryParam param = QueryParam.getInstance();
        if (StringUtil.isNotBlank(model.getSearchName())) {
            SearchFilter orFilter1 = new SearchFilter("title", Operators.LIKE, StringUtil.isNull(model.getSearchName()));
            SearchFilter orFilter2 = new SearchFilter("introduction", Operators.LIKE, StringUtil.isNull(model.getSearchName()));
            param.addOrFilter(orFilter1, orFilter2);
        } else {
            if (StringUtil.isNotBlank(model.getTitle())) {
                param.addParam("title", Operators.LIKE, model.getTitle());
            }
        }
        if (model.getSite() != null && model.getSite().getId() > 0) {
            param.addParam("site.id", model.getSite().getId());
        }
        if (model.getSite() != null && StringUtil.isNotBlank(model.getSite().getNid())) {
            param.addParam("site.nid", model.getSite().getNid());
        }
        if (model.getOperator() != null && model.getOperator().getId() > 0) {
            param.addParam("operator.id", model.getOperator().getId());
        }
        if (model.getState() != 0) {
            param.addParam("state", model.getState());
        }
        param.addOrder(OrderType.ASC,"sort");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }

    /**
     * 通过栏目标识（nid)查询文章
     *
     * @param model
     * @return List<Article>
     */
    @Override
    public List<Article> findByModel(ArticleModel model) {
        QueryParam param = QueryParam.getInstance();
        if (model.getSite() != null && StringUtil.isNotBlank(model.getSite().getNid())) {
            param.addParam("site.nid", Operators.LIKE, model.getSite().getNid());
        }
        if (model.getOperator() != null && model.getOperator().getId() > 0) {
            param.addParam("operator.id", model.getOperator().getId());
        }
        param.addParam("state", BaseConstant.INFO_STATE_YES);
        param.addParam("site.state", BaseConstant.INFO_STATE_YES);
        // 升排序
        param.addOrder(OrderType.ASC, "sort");
        param.addOrder(OrderType.DESC, "id");
        return super.findByCriteria(param);

    }
}