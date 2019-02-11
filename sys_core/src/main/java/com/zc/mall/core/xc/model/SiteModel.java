package com.zc.mall.core.xc.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.zc.mall.core.manage.model.OperatorModel;
import com.zc.mall.core.user.model.UserModel;
import com.zc.mall.core.xc.entity.Site;
import com.zc.sys.common.model.jpa.Page;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 栏目
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public class SiteModel extends Site {
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
     * 文章Model
     **/
    @JsonInclude(Include.NON_NULL)
    private ArticleModel articleModel;
    /**
     * 平台添加管理员Model
     **/
    @JsonInclude(Include.NON_NULL)
    private OperatorModel operatorModel;
    /**
     * 商家添加管理员关联用户Model
     **/
    @JsonInclude(Include.NON_NULL)
    private UserModel relationBusinessUserModel;
    /**
     * 子栏目列表
     **/
    private List<SiteModel> siteModelList;
    /**
     * 文章列表
     **/
    private List<ArticleModel> articleModelList;

    /**
     * 实体转换model
     */
    public static SiteModel instance(Site site) {
        if (site == null || site.getId() <= 0) {
            return null;
        }
        SiteModel siteModel = new SiteModel();
        BeanUtils.copyProperties(site, siteModel);
        return siteModel;
    }

    /**
     * model转换实体
     */
    public Site prototype() {
        Site site = new Site();
        BeanUtils.copyProperties(this, site);
        return site;
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
     * 获取【文章Model】
     **/
    public ArticleModel getArticleModel() {
        return articleModel;
    }

    /**
     * 设置【文章Model】
     **/
    public void setArticleModel(ArticleModel articleModel) {
        this.articleModel = articleModel;
    }

    /**
     * 获取【平台添加管理员Model】
     **/
    public OperatorModel getOperatorModel() {
        return operatorModel;
    }

    /**
     * 设置【平台添加管理员Model】
     **/
    public void setOperatorModel(OperatorModel operatorModel) {
        this.operatorModel = operatorModel;
    }

    /**
     * 获取【商家添加管理员关联用户Model】
     **/
    public UserModel getRelationBusinessUserModel() {
        return relationBusinessUserModel;
    }

    /**
     * 设置【商家添加管理员关联用户Model】
     **/
    public void setRelationBusinessUserModel(UserModel relationBusinessUserModel) {
        this.relationBusinessUserModel = relationBusinessUserModel;
    }

    public List<SiteModel> getSiteModelList() {
        return siteModelList;
    }

    public void setSiteModelList(List<SiteModel> siteModelList) {
        this.siteModelList = siteModelList;
    }

    public List<ArticleModel> getArticleModelList() {
        return articleModelList;
    }

    public void setArticleModelList(List<ArticleModel> articleModelList) {
        this.articleModelList = articleModelList;
    }
}
