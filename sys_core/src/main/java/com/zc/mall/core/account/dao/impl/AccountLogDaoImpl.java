package com.zc.mall.core.account.dao.impl;

import com.zc.mall.core.account.dao.AccountLogDao;
import com.zc.mall.core.account.entity.AccountLog;
import com.zc.mall.core.account.model.AccountLogModel;
import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.user.dao.UserRelationDao;
import com.zc.mall.core.user.entity.UserRelation;
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
 * 资金日志
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Repository
public class AccountLogDaoImpl extends BaseDaoImpl<AccountLog> implements AccountLogDao {
    @Override
    public PageDataList<AccountLog> list(AccountLogModel model) {
        QueryParam param = QueryParam.getInstance();
        if (StringUtil.isNotBlank(model.getSearchName())) {
            SearchFilter orFilter1 = new SearchFilter("user.realName", Operators.LIKE, model.getSearchName().trim());
            SearchFilter orFilter2 = new SearchFilter("user.mobile", Operators.LIKE, model.getSearchName().trim());
            param.addOrFilter(orFilter1, orFilter2);
        }
        if (model.getUser() != null && model.getUser().getId() > 0) {
            param.addParam("user.id", model.getUser().getId());
        }
        if (model.getRalationUserId() > 0) {
            UserRelationDao userRelationDao = BeanUtil.getBean(UserRelationDao.class);
            QueryParam params = QueryParam.getInstance();
            params.addParam("beUser.id", model.getRalationUserId());
            params.addParam("type", 2);
            List<UserRelation> list = userRelationDao.findByCriteria(params);
            String userIds = model.getRalationUserId() + ",";
            if (list.size() > 0) {
                for (UserRelation u : list) {
                    userIds += u.getUser().getId() + ",";
                }
            }
            if (!StringUtil.isBlank(userIds)) {
                param.addOrFilter("user.id", userIds.split(","));
            }

        }
        if (model.getPaymentsType() != 0) {
            param.addParam("paymentsType", model.getPaymentsType());
        }
        if (!StringUtil.isBlank(model.getStartTime()) && !StringUtil.isBlank(model.getEndTime())) {
            //查询今日
            if (DateUtil.getDayStartTime(DateUtil.getTime(DateUtil.valueOf(model.getStartTime()))).equals(DateUtil.getDayStartTime(DateUtil.getTime(DateUtil.valueOf(model.getEndTime()))))) {
                param.addParam("addTime", Operators.GT, DateUtil.getDayStartTime(DateUtil.getTime(DateUtil.valueOf(model.getStartTime()))));
                param.addParam("addTime", Operators.LTE, DateUtil.getDayEndTime(DateUtil.getTime(DateUtil.valueOf(model.getEndTime()))));
            } else {
                param.addParam("addTime", Operators.GT, DateUtil.valueOf(model.getStartTime()));
                param.addParam("addTime", Operators.LTE, DateUtil.valueOf(model.getEndTime()));
            }

        }
        param.addOrder(OrderType.DESC, "id");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }
}
