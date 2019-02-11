package com.zc.mall.core.xc.service.impl;

import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.manage.dao.OperatorDao;
import com.zc.mall.core.manage.entity.Operator;
import com.zc.mall.core.manage.model.OperatorModel;
import com.zc.mall.core.user.model.UserModel;
import com.zc.mall.core.xc.dao.ArticleDao;
import com.zc.mall.core.xc.dao.SiteDao;
import com.zc.mall.core.xc.entity.Article;
import com.zc.mall.core.xc.entity.Site;
import com.zc.mall.core.xc.model.ArticleModel;
import com.zc.mall.core.xc.model.SiteModel;
import com.zc.mall.core.xc.service.ArticleService;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.http.RequestUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 文章
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleDao articleDao;
    @Resource
    private SiteDao siteDao;
    @Resource
    private OperatorDao operatorDao;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result list(ArticleModel model) {
        PageDataList<Article> pageDataList = articleDao.list(model);
        PageDataList<ArticleModel> pageDataList_ = new PageDataList<ArticleModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<ArticleModel> list = new ArrayList<ArticleModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (Article article : pageDataList.getList()) {
                ArticleModel model_ = ArticleModel.instance(article);
                model_.setSiteModel(SiteModel.instance(article.getSite()));
                Operator operator = article.getOperator();
                if (operator != null) {
                    model_.setOperatorModel(OperatorModel.instance(operator));
                    if (operator.getUser() != null) {
                        model_.setRelationBusinessUserModel(UserModel.instance(operator.getUser()));
                    }
                }
                list.add(model_);
            }
        }
        pageDataList_.setList(list);
        return Result.success().setData(pageDataList_);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result add(ArticleModel model) {
        // 校验添加
        this.checkAdd(model);
        checkOperator(model.getOperator());
        // 初始化添加
        this.initAdd(model);
        articleDao.save(model.prototype());
        return Result.success();
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result update(ArticleModel model) {
        // 校验修改
        Article article = this.checkUpdate(model);
        // 初始化修改
        this.initUpdate(model, article);
        articleDao.update(article);
        return Result.success();

    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result getById(ArticleModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        Article article = articleDao.find(model.getId());
        ArticleModel model_ = ArticleModel.instance(article);
        if (article.getSite() != null) {
            model_.setSiteModel(SiteModel.instance(article.getSite()));
        }
        Operator operator = article.getOperator();
        if (operator != null) {
            model_.setOperatorModel(OperatorModel.instance(operator));
            if (operator.getUser() != null) {
                model_.setRelationBusinessUserModel(UserModel.instance(operator.getUser()));
            }
        }
        return Result.success().setData(model_);
    }

    /**
     * 通过Id删除单个
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result deleteById(ArticleModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        articleDao.delete(model.getId());
        return Result.success();
    }

    /**
     * 添加商家团队成员
     *
     * @param model
     * @return
     */
    @Override
    public Result addTeam(ArticleModel model) {
        this.checkAddTeam(model);
        this.initAddTeam(model);
        articleDao.save(model.prototype());
        return Result.success();
    }

    /**
     * 修改商家团队成员
     *
     * @param model
     * @return
     */
    @Override
    public Result updateTeam(ArticleModel model) {
        Article article = this.checkUpdateTeam(model);
        this.initUpdateTeam(model, article);
        articleDao.update(article);
        return Result.success();
    }

    /**
     * 查询文章
     *
     * @param model
     * @return List<Article>
     */
    @Override
    @Transactional
    public Result findByModel(ArticleModel model) {
        List<Article> list = articleDao.findByModel(model);
        List<ArticleModel> list_ = new ArrayList<ArticleModel>();
        for (Article article : list) {
            ArticleModel articleModel = ArticleModel.instance(article);
            articleModel.setSiteModel(SiteModel.instance(article.getSite()));
            Operator operator = article.getOperator();
            if (operator != null) {
                articleModel.setOperatorModel(OperatorModel.instance(operator));
                if (operator.getUser() != null) {
                    articleModel.setRelationBusinessUserModel(UserModel.instance(operator.getUser()));
                }
            }
            list_.add(articleModel);
        }
        return Result.success().setData(list_);
    }

    /**
     * 校验添加
     *
     * @param model
     */
    private void checkAdd(ArticleModel model) {
        if (StringUtil.isBlank(model.getTitle())) {
            throw new BusinessException("模版标题不能为空！");
        }
        if (model.getSite() == null || model.getSite().getId() <= 0) {
            throw new BusinessException("所属栏目不能为空！");
        }
        /*if (StringUtil.isBlank(model.getContent())) {
            throw new BusinessException("内容不能为空！");
        }*/
        /*if (StringUtil.isBlank(model.getIntroduction())) {
            throw new BusinessException("简介不能为空！");
        }*/
        if (StringUtil.isBlank(model.getState())) {
            throw new BusinessException("状态不能为空！");
        }
        /*if (StringUtil.isBlank(model.getIsHot())) {
            throw new BusinessException("热文章不能为空！");
        }*/
        /*if (StringUtil.isBlank(model.getClicks())) {
            throw new BusinessException("点击量不能为空！");
        }*/
        /*if (StringUtil.isBlank(model.getUrl())) {
            throw new BusinessException("链接地址不能为空！");
        }*/
        /*if (StringUtil.isBlank(model.getSort())) {
            throw new BusinessException("排序不能为空！");
        }*/
        //if (StringUtil.isBlank(model.getPicPath())) {
        //    throw new BusinessException("图片地址不能为空！");
        //}
        Site site = siteDao.find(model.getSite().getId());
        if (site == null) {
            throw new BusinessException("参数错误！");
        }
        model.setSite(site);
    }

    /**
     * 初始化添加
     *
     * @param model
     */
    private void initAdd(ArticleModel model) {
        model.setUpdateTime(DateUtil.getNow());
        model.setUpdateIp(RequestUtil.getClientIp());
    }


    /**
     * 校验修改
     *
     * @param model
     * @return
     */
    private Article checkUpdate(ArticleModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误！");
        }
        Article article = articleDao.find(model.getId());
        if (article == null) {
            throw new BusinessException("参数错误！");
        }
        this.checkAdd(model);
        checkOperator(model.getUpdateOperator());
        return article;
    }

    /**
     * 校验管理员
     *
     * @param operator
     */
    private void checkOperator(Operator operator) {
        if (operator == null || operator.getId() <= 0) {
            throw new BusinessException("管理员信息有误");
        }
        Operator operator1 = operatorDao.find(operator.getId());
        if (operator1 == null) {
            throw new BusinessException("管理员信息有误");
        }
    }

    /**
     * 初始化修改
     *
     * @param model
     */
    private void initUpdate(ArticleModel model, Article article) {
        article.setContent(model.getContent());
        article.setIntroduction(model.getIntroduction());
        article.setPicPath(model.getPicPath());
        article.setSort(model.getSort());
        article.setState(model.getState());
        article.setTitle(model.getTitle());
        article.setIsHot(model.getIsHot());
        article.setUrl(model.getUrl());
        article.setSite(model.getSite());
        article.setUpdateOperator(model.getUpdateOperator());
        article.setUpdateTime(DateUtil.getNow());
        article.setUpdateIp(RequestUtil.getClientIp());
    }


    /**
     * 校验
     *
     * @param model
     */
    private void checkAddTeam(ArticleModel model) {
        if (!model.getTitle().contains(",")) {
            throw new BusinessException("参数错误！");
        }
        if (StringUtil.isBlank(model.getTitle().split(",")[0])) {
            throw new BusinessException("姓名不能为空！");
        }
        if (StringUtil.isBlank(model.getTitle().split(",")[1])) {
            throw new BusinessException("职位不能为空！");
        }
        if (StringUtil.isBlank(model.getIntroduction())) {
            throw new BusinessException("简介不能为空！");
        }
        if (model.getSort() < 0) {
            throw new BusinessException("排序不能为空！");
        }
        if (StringUtil.isBlank(model.getPicPath())) {
            throw new BusinessException("图片地址不能为空！");
        }
        if (model.getOperator() == null || model.getOperator().getId() <= 0) {
            throw new BusinessException("参数错误！");
        }
        Operator operator = operatorDao.find(model.getOperator().getId());
        if (operator == null) {
            throw new BusinessException("参数错误！");
        }
        model.setOperator(operator);
    }

    /**
     * 初始化
     *
     * @param model
     */
    private void initAddTeam(ArticleModel model) {
        Site site = siteDao.find(Long.parseLong("5"));// 初始化栏目
        model.setSite(site);
        model.setState(BaseConstant.INFO_STATE_YES);
    }


    /**
     * 校验
     *
     * @param model
     * @return
     */
    private Article checkUpdateTeam(ArticleModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误！");
        }
        Article article = articleDao.find(model.getId());
        if (article == null) {
            throw new BusinessException("参数错误！");
        }
        this.checkAddTeam(model);
        return article;
    }

    /**
     * 初始化
     *
     * @param model
     * @param article
     */
    private void initUpdateTeam(ArticleModel model, Article article) {
        article.setIntroduction(model.getIntroduction());
        article.setPicPath(model.getPicPath());
        article.setSort(model.getSort());
        article.setTitle(model.getTitle());
        article.setOperator(model.getOperator());
        article.setUrl(model.getUrl());
        article.setUpdateTime(DateUtil.getNow());
        article.setUpdateIp(RequestUtil.getClientIp());
    }

}