package com.zc.mall.core.xc.dao;

import com.zc.mall.core.xc.entity.Article;
import com.zc.mall.core.xc.model.ArticleModel;
import com.zc.sys.common.dao.BaseDao;
import com.zc.sys.common.model.jpa.PageDataList;

import java.util.List;

/**
 * 文章
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public interface ArticleDao extends BaseDao<Article> {
    /**
     * 列表
     *
     * @param model
     * @return
     */
    PageDataList<Article> list(ArticleModel model);

    /**
     * 查询
     *
     * @param model
     * @return List<Article>
     */
    List<Article> findByModel(ArticleModel model);
}