package com.zc.mall.core.user.dao.impl;

import com.zc.mall.core.user.dao.UserRelationDao;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.entity.UserRelation;
import com.zc.mall.core.user.model.UserRelationModel;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.model.jpa.OrderFilter.OrderType;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import com.zc.sys.common.model.jpa.SearchFilter;
import com.zc.sys.common.model.jpa.SearchFilter.Operators;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户关系
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2018年03月15日
 */
@Repository
public class UserRelationDaoImpl extends BaseDaoImpl<UserRelation> implements UserRelationDao {

    @Override
    public PageDataList<UserRelation> list(UserRelationModel model) {
        QueryParam param = QueryParam.getInstance();
        if (StringUtil.isNotBlank(model.getSearchName())) {
            SearchFilter orFilter1 = new SearchFilter("user.userName", Operators.LIKE, model.getSearchName());
            SearchFilter orFilter2 = new SearchFilter("user.mobile", Operators.LIKE, model.getSearchName());
            param.addOrFilter(orFilter1, orFilter2);
        }
        if (model.getUserId() > 0) {
            param.addParam("user.id", model.getUserId());
        }
        if (model.getBeUserId() > 0) {
            param.addParam("beUser.id", model.getBeUserId());
        }
        if (model.getType() > 0) {
            param.addParam("type", model.getType());
        }
        if (model.getRealNameState() != 0) {
            param.addParam("user.userIdentify.realNameState", model.getRealNameState());
        }
        param.addOrder(OrderType.ASC, "id");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }

    @Override
    public UserRelation findUserRelation(UserRelationModel model) {
        QueryParam param = QueryParam.getInstance();
        param.addParam("user.id", model.getUserId());
        param.addParam("beUser.id", model.getBeUserId());
        return super.findByCriteriaForUnique(param);// 检测用户的唯一性


    }

    /**
     * 通过商家id和用户与商家之间的关系Type查询用户列表
     *
     * @param model
     * @return UserRelation
     */
    @Override
    public List<UserRelation> findUserByToUserIdAndType(UserRelationModel model) {
        QueryParam param = QueryParam.getInstance();
        param.addParam("type", model.getType());
        param.addParam("beUser.id", model.getBeUserId());
        return super.findByCriteria(param);
    }

    @Override
    public List<UserRelation> countUserNumber(UserRelationModel model) {
        QueryParam param = QueryParam.getInstance();
        param.addParam("beUser.id", model.getBeUserId());
        param.addParam("addTime", Operators.GT, DateUtil.valueOf(DateUtil.dateStr4(DateUtil.getDayStartTime(DateUtil.getTime(DateUtil.getNow())))));
        param.addParam("addTime", Operators.LTE, DateUtil.valueOf(DateUtil.dateStr4(DateUtil.getDayEndTime(DateUtil.getTime(DateUtil.getNow())))));
        return super.findByCriteria(param);
    }

    /**
     * 查询邀请的人数
     *
     * @param id
     * @return
     */
    @Override
    public PageDataList<UserRelation> findUserListByToUserId(long id) {
        QueryParam queryParam = QueryParam.getInstance();
        queryParam.addParam("beUser.id", id);
        return super.findPageList(queryParam);
    }

    /**
     * 查询邀请人
     *
     * @param id
     * @return
     */
    @Override
    public User findToUserByUserId(long id) {
        QueryParam queryParam = QueryParam.getInstance();
        queryParam.addParam("user.id", id);
        UserRelation userRelation = super.findByCriteriaForUnique(queryParam);
        return userRelation != null ? userRelation.getBeUser() : null;
    }

}