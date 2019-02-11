package com.zc.mall.core.account.dao.impl;

import com.zc.mall.core.account.dao.AccountStatisticsDao;
import com.zc.mall.core.account.entity.AccountStatistics;
import com.zc.mall.core.account.model.AccountStatisticsModel;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.model.jpa.OrderFilter.OrderType;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import com.zc.sys.common.model.jpa.SearchFilter;
import com.zc.sys.common.model.jpa.SearchFilter.Operators;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账户统计
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年12月20日
 */
@Repository
public class AccountStatisticsDaoImpl extends BaseDaoImpl<AccountStatistics> implements AccountStatisticsDao {

    @Override
    public PageDataList<AccountStatistics> list(AccountStatisticsModel model) {
        QueryParam param = QueryParam.getInstance();
        if (StringUtil.isNotBlank(model.getSearchName())) {
            SearchFilter orFilter1 = new SearchFilter("user.realName", Operators.EQ, model.getSearchName());
            SearchFilter orFilter2 = new SearchFilter("user.mobile", Operators.EQ, model.getSearchName());
            param.addOrFilter(orFilter1, orFilter2);
        }
        if (model.getUserId() > 0) {
            param.addParam("user.id", model.getUserId());
        }
        if (model.getType() > 0) {
            param.addParam("type", model.getType());
        }
        param.addOrder(OrderType.ASC, "id");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }

    /**
     * 查询账户
     *
     * @param user 用户
     * @param type 类型
     * @return
     */
    @Override
    public AccountStatistics getAccountStatistics(User user, int type) {
        if (type <= 0) {
            throw new BusinessException("参数错误");
        }
        QueryParam param = QueryParam.getInstance();
        if (user != null) {
            param.addParam("user.id", user.getId());
        }
        param.addParam("type", type);
        List<AccountStatistics> list = super.findByCriteria(param);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 根据用户获取消费总结
     *
     * @param userModel
     * @return
     */
    @Override
    public AccountStatistics findByUserId(UserModel userModel, int type) {
        QueryParam queryParam = QueryParam.getInstance();
        queryParam.addParam("user.id", userModel.getId());
        queryParam.addParam("type", type);
        return super.findByCriteriaForUnique(queryParam);
    }

}