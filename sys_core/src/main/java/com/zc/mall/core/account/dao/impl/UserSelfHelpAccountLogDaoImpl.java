package com.zc.mall.core.account.dao.impl;

import com.zc.mall.core.account.dao.UserSelfHelpAccountLogDao;
import com.zc.mall.core.account.entity.UserSelfHelpAccountLog;
import com.zc.mall.core.account.model.UserSelfHelpAccountLogModel;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.model.jpa.OrderFilter.OrderType;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import com.zc.sys.common.model.jpa.SearchFilter;
import com.zc.sys.common.model.jpa.SearchFilter.Operators;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Repository;

/**
 * 用户自助记账
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2018年08月05日
 */
@Repository
public class UserSelfHelpAccountLogDaoImpl extends BaseDaoImpl<UserSelfHelpAccountLog> implements UserSelfHelpAccountLogDao {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    public PageDataList<UserSelfHelpAccountLog> list(UserSelfHelpAccountLogModel model) {
        QueryParam param = QueryParam.getInstance();
        if (!StringUtil.isBlank(model.getUserId()) && model.getUserId() > 0) {
            param.addParam("user.id", model.getUserId());
        }
        if (!StringUtil.isBlank(model.getType())) {
            param.addParam("type", Operators.LIKE, model.getType());
        }
        if (!StringUtil.isBlank(model.getSearchName())) {
            SearchFilter orFilter3 = new SearchFilter("user.realName", Operators.LIKE, model.getSearchName().trim());
            SearchFilter orFilter4 = new SearchFilter("user.mobile", Operators.LIKE, model.getSearchName().trim());
            param.addOrFilter(orFilter3, orFilter4);
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