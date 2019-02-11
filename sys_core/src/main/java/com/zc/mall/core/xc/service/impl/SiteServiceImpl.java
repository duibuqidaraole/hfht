package com.zc.mall.core.xc.service.impl;

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
import com.zc.mall.core.xc.service.SiteService;
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
 * 栏目
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Service
public class SiteServiceImpl implements SiteService {

    @Resource
    private SiteDao siteDao;
    @Resource
    private ArticleDao articleDao;
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
    public Result list(SiteModel model) {
        PageDataList<Site> pageDataList = siteDao.list(model);
        PageDataList<SiteModel> pageDataList_ = new PageDataList<SiteModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<SiteModel> list = new ArrayList<SiteModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (Site site : pageDataList.getList()) {
                SiteModel model_ = SiteModel.instance(site);
                Operator operator = site.getOperator();
                if (site.getOperator() != null) {
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
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result listNoPage(SiteModel model) {
        PageDataList<Site> pageDataList = siteDao.listNoPage(model);
        PageDataList<SiteModel> pageDataList_ = new PageDataList<SiteModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<SiteModel> list = new ArrayList<SiteModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (Site site : pageDataList.getList()) {
                SiteModel model_ = SiteModel.instance(site);
                Operator operator = site.getOperator();
                if (site.getOperator() != null) {
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
     * 根据nid获取所有子栏目及其文章
     *
     * @param model
     * @return
     */
    @Override
    public Object getAllByNid(SiteModel model) {
        if (StringUtil.isBlank(model.getNid())) {
            throw new BusinessException("请传入nid");
        }
        Site site = siteDao.findByNid(model.getNid());
        if (site == null) {
            return null;
        }
        SiteModel siteModel = SiteModel.instance(site);
        SiteModel searchModel = new SiteModel();
        searchModel.setPid(site.getId());
        List<Site> list = siteDao.list(searchModel).getList();
        List<SiteModel> siteModels = new ArrayList<>();
        for (Site site2 : list) {
            SiteModel model1 = SiteModel.instance(site2);
            ArticleModel articleModel = new ArticleModel();
            articleModel.setSite(site2);
            List<Article> list1 = articleDao.list(articleModel).getList();
            ArrayList<ArticleModel> articleModels = new ArrayList<>();
            for (Article article : list1) {
                articleModels.add(ArticleModel.instance(article));
            }
            model1.setArticleModelList(articleModels);
            siteModels.add(model1);
        }
        siteModel.setSiteModelList(siteModels);
        return Result.success().setData(siteModel);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result add(SiteModel model) {
        // 校验参数
        this.checkAdd(model);
        // 初始化添加
        this.initAdd(model);
        siteDao.save(model.prototype());
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
    public Result update(SiteModel model) {
        // 校验修改
        Site site = this.checkUpdate(model);
        // 初始化修改
        this.initUpdate(model, site);
        siteDao.update(site);
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
    public Result getById(SiteModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        Site site = siteDao.find(model.getId());
        if (site == null) {
            return Result.error("参数错误！");
        }
        SiteModel model_ = SiteModel.instance(site);
        Operator operator = site.getOperator();
        if (site.getOperator() != null) {
            model_.setOperatorModel(OperatorModel.instance(operator));

            if (operator.getUser() != null) {
                model_.setRelationBusinessUserModel(UserModel.instance(operator.getUser()));
            }
        }
        return Result.success().setData(model_);

    }


    /**
     * 校验参数
     *
     * @param model
     */
    private void checkAdd(SiteModel model) {
        if (StringUtil.isBlank(model.getName())) {
            throw new BusinessException("栏目名称不能为空！");
        }
        if (StringUtil.isBlank(model.getNid())) {
            throw new BusinessException("标识不能为空！");
        }
        if (model.getType() <= 0) {
            throw new BusinessException("请选择类型！");
        }
        //if (StringUtil.isBlank(model.getContent())) {
        //    throw new BusinessException("模版内容不能为空！");
        //}
        //if (StringUtil.isBlank(model.getIntroduction())) {
        //    throw new BusinessException("介绍不能为空！");
        //}
        //if (StringUtil.isBlank(model.getPicPath())) {
        //    throw new BusinessException("图片地址不能为空！");
        //}
        /*if (StringUtil.isBlank(model.getSort())) {
            throw new BusinessException("排序不能为空！");
        }*/
        if (model.getState() == 0) {
            throw new BusinessException("状态不能为空！");
        }
        //if (StringUtil.isBlank(model.getUrl())) {
        //    throw new BusinessException("跳转链接不能为空！");
        //}
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
     * 初始化添加
     *
     * @param model
     */
    private void initAdd(SiteModel model) {
        model.setUpdateTime(DateUtil.getNow());
        model.setUpdateIp(RequestUtil.getClientIp());
    }


    /**
     * 校验修改
     *
     * @param model
     * @return
     */
    private Site checkUpdate(SiteModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误！");
        }
        Site site = siteDao.find(model.getId());
        if (site == null) {
            throw new BusinessException("参数错误！");
        }
        this.checkAdd(model);
        return site;
    }

    /**
     * 初始化修改
     *
     * @param model
     */
    private void initUpdate(SiteModel model, Site site) {
        site.setName(model.getName());
        site.setPid(model.getPid());
        site.setType(model.getType());
        site.setContent(model.getContent());
        site.setIntroduction(model.getIntroduction());
        site.setOperator(model.getOperator());
        site.setPicPath(model.getPicPath());
        site.setSort(model.getSort());
        site.setState(model.getState());
        site.setNid(model.getNid());
        site.setUpdateTime(DateUtil.getNow());
        site.setUpdateIp(RequestUtil.getClientIp());
    }
}