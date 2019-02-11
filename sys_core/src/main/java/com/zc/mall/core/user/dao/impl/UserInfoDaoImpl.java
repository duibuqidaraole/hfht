
package com.zc.mall.core.user.dao.impl;

import com.zc.mall.core.user.dao.UserInfoDao;
import com.zc.mall.core.user.entity.UserInfo;
import com.zc.mall.core.user.model.UserInfoModel;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import com.zc.sys.common.model.jpa.SearchFilter;
import com.zc.sys.common.model.jpa.SearchFilter.Operators;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Repository;

/**
 * 用户信息
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Repository
public class UserInfoDaoImpl extends BaseDaoImpl<UserInfo> implements UserInfoDao {
    /*
     * 用户信息列表
     *
     */
    @Override
    public PageDataList<UserInfo> list(UserInfoModel model) {
        QueryParam param = QueryParam.getInstance();
        if (StringUtil.isNotBlank(model.getSearchName())) {
            SearchFilter orFilter2 = new SearchFilter("name", Operators.LIKE, model.getSearchName().trim());
            param.addOrFilter(orFilter2);
        }
        if (model.getType() > 0) {
            param.addParam("type", model.getType());
        }
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }

    /*
     * 通过用户Id在用户信息表里面查询
     *
     */
    @Override
    public UserInfo getByUserId(long userId) {
        return super.findObjByProperty("user.id", userId);
    }

}