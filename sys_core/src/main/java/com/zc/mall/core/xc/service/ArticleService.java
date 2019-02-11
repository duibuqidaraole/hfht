package com.zc.mall.core.xc.service;

import com.zc.mall.core.xc.model.ArticleModel;
import com.zc.sys.common.form.Result;

/**
 * 文章
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public interface ArticleService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(ArticleModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(ArticleModel model);

    /**
     * 添加商家团队成员
     *
     * @param model
     * @return
     */
    public Result addTeam(ArticleModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(ArticleModel model);

    /**
     * 修改商家团队成员
     *
     * @param model
     * @return
     */
    public Result updateTeam(ArticleModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(ArticleModel model);

    /**
     * 通过Id删除单个
     *
     * @param model
     * @return
     */
    public Result deleteById(ArticleModel model);

    /**
     * 查询
     *
     * @param model
     * @return List<Article>
     */
    Result findByModel(ArticleModel model);

}