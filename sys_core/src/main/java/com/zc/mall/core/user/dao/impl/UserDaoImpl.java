package com.zc.mall.core.user.dao.impl;

import com.zc.mall.core.user.dao.UserDao;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.model.jpa.OrderFilter.OrderType;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import com.zc.sys.common.model.jpa.SearchFilter;
import com.zc.sys.common.model.jpa.SearchFilter.Operators;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

/**
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Override
    public PageDataList<User> list(UserModel model) {
        QueryParam param = QueryParam.getInstance();
        if (StringUtil.isNotBlank(model.getSearchName())) {
            SearchFilter orFilter1 = new SearchFilter("userName", StringUtil.isNull(model.getSearchName()));
            SearchFilter orFilter2 = new SearchFilter("mobile", StringUtil.isNull(model.getSearchName()));
            param.addOrFilter(orFilter1, orFilter2);
        } else {
            if (StringUtil.isNotBlank(model.getLoginName())) {
                param.addParam("LoginName", Operators.LIKE, StringUtil.isNull(model.getLoginName()));
            }
            if (model.getMobileState() != 0) {
                param.addParam("mobileState", model.getMobileState());
            }

        }
        if (StringUtil.isNotBlank(model.getLegalName())) {
            param.addParam("userInfo.legalName", Operators.LIKE, model.getLegalName());
        }
        if (StringUtil.isNotBlank(model.getCompanyName())) {
            param.addParam("userInfo.companyName", Operators.LIKE, model.getCompanyName());
        }

        if (model.getType() > 0) {
            param.addParam("userInfo.type", model.getType());
        }
        /**userInfoState(商家排序)  1综合排序  2安全优先  3收益优先**/
        if (model.getUserInfoState() > 0) {
            if (model.getUserInfoState() == 1) {
                param.addOrder(OrderType.ASC, "userInfo.id");
                param.addParam("userIdentify.state", 1);
            }
            if (model.getUserInfoState() == 2) {
                param.addOrder(OrderType.DESC, "userInfo.companySafety");
                param.addParam("userIdentify.state", 1);
            }
            if (model.getUserInfoState() == 3) {
                param.addOrder(OrderType.DESC, "userInfo.companyRateMost");
                param.addParam("userIdentify.state", 1);
            }
        }

        if (model.getUserNature() > 0) {
            param.addParam("userInfo.userNature", model.getUserNature());
        }

        if (model.getInviteUserId() > 0) {
            param.addParam("userInfo.inviteUser.id", model.getInviteUserId());
        }
        if (model.getId() > 0) {
            param.addParam("id", model.getId());
        }
        // 查询今日注册人数
        if (model.getRegState() > 0) {
            param.addParam("addTime", Operators.GT, DateUtil.getDayStartTime(DateUtil.getTime(DateUtil.getNow())));
            param.addParam("addTime", Operators.LTE, DateUtil.getDayEndTime(DateUtil.getTime(DateUtil.getNow())));
        }
        if (model.getRealNameState() != 0) {
            param.addParam("userIdentify.realNameState", model.getRealNameState());
        }
        if (StringUtil.isNotBlank(model.getIsRecommend())) {
            int i = Integer.parseInt(StringUtil.isNull(model.getIsRecommend()));
            if (i != 0) {
                param.addParam("userIdentify.isRecommend", i);
            }
        }

        if (model.getLessBirthday()!=null){
            param.addParam("userInfo.birthday",Operators.GTE,model.getLessBirthday());
        }
        if (model.getMostBirthday()!=null){
            param.addParam("userInfo.birthday",Operators.LTE,model.getMostBirthday());
        }

        param.addOrder(OrderType.DESC, "addTime");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }

    /**
     * 根据手机号查询用户
     *
     * @param mobile
     * @return
     */
    @Override
    public User findByMobile(String mobile) {
        return super.findObjByProperty("mobile", mobile);
    }

    /**
     * 查询所有注册用户
     *
     * @return
     */
    @Override
    public Long findUserNumber() {
        String countSql = "select count(1)  from zc_u_user where 1=1 ";
        Query query = em.createNativeQuery(countSql);
        return StringUtil.toLong(query.getSingleResult().toString());
    }

    /**
     * 查询今日所有注册用户
     *
     * @return
     */
    @Override
    public Long findUserNumberByToday() {
        String countSql = "select count(1) from zc_u_user where date_format(add_time,'%Y-%m-%d')=(select curdate())";
        Query query = em.createQuery(countSql);
        return StringUtil.toLong(query.getSingleResult().toString());
    }

    /**
     * 根据openId查询用户
     *
     * @param openId
     * @return
     */
    @Override
    public User findByOpenId(String openId) {
        QueryParam queryParam = QueryParam.getInstance();
        queryParam.addParam("openId", openId);
        return super.findByCriteriaForUnique(queryParam);
    }

}