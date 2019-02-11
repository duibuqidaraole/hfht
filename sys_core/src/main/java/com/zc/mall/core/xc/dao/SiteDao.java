package com.zc.mall.core.xc.dao;

import com.zc.mall.core.xc.entity.Site;
import com.zc.mall.core.xc.model.SiteModel;
import com.zc.sys.common.dao.BaseDao;
import com.zc.sys.common.model.jpa.PageDataList;

/**
 * 栏目
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public interface SiteDao extends BaseDao<Site> {
    /**
     * 列表
     *
     * @param model
     * @return
     */
    PageDataList<Site> list(SiteModel model);

    /**
     * 列表无分页
     *
     * @param model
     * @return
     */
    PageDataList<Site> listNoPage(SiteModel model);

    /**
     * @param nid
     */
    Site findByNid(String nid);
}