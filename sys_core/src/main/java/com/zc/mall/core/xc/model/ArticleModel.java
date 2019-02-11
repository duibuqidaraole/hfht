package com.zc.mall.core.xc.model;

import com.zc.mall.core.manage.model.OperatorModel;
import com.zc.mall.core.user.model.UserModel;
import com.zc.mall.core.xc.entity.Article;
import com.zc.sys.common.model.jpa.Page;
import org.springframework.beans.BeanUtils;

/**
 * 文章
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public class ArticleModel extends Article {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;

    /**
     * 当前页码
     **/
    private int pageNo;
    /**
     * 每页数据条数
     **/
    private int pageSize = Page.ROWS;
    /**
     * 条件查询
     **/
    private String searchName;
    /**
     * 栏目Model
     **/
    private SiteModel siteModel;
    /**
     * 平台添加管理员Model
     **/
    private OperatorModel operatorModel;
    /**
     * 商家添加管理员关联用户Model
     **/
    private UserModel relationBusinessUserModel;

    /**
     * 实体转换model
     */
    public static ArticleModel instance(Article article) {
        if (article == null || article.getId() <= 0) {
            return null;
        }
        ArticleModel articleModel = new ArticleModel();
        BeanUtils.copyProperties(article, articleModel);
        return articleModel;
    }

    /**
     * model转换实体
     */
    public Article prototype() {
        Article article = new Article();
        BeanUtils.copyProperties(this, article);
        return article;
    }

    /**
     * 获取【当前页码】
     **/
    public int getPageNo() {
        return pageNo;
    }

    /**
     * 设置【当前页码】
     **/
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * 获取【每页数据条数】
     **/
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置【每页数据条数】
     **/
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取【条件查询】
     **/
    public String getSearchName() {
        return searchName;
    }

    /**
     * 设置【条件查询】
     **/
    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    /**
     * 获取【栏目Model】
     **/
    public SiteModel getSiteModel() {
        return siteModel;
    }

    /**
     * 设置【栏目Model】
     **/
    public void setSiteModel(SiteModel siteModel) {
        this.siteModel = siteModel;
    }

    public OperatorModel getOperatorModel() {
        return operatorModel;
    }

    public void setOperatorModel(OperatorModel operatorModel) {
        this.operatorModel = operatorModel;
    }

    public UserModel getRelationBusinessUserModel() {
        return relationBusinessUserModel;
    }

    public void setRelationBusinessUserModel(UserModel relationBusinessUserModel) {
        this.relationBusinessUserModel = relationBusinessUserModel;
    }

}