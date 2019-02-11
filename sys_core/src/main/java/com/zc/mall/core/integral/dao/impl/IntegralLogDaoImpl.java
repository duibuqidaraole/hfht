package com.zc.mall.core.integral.dao.impl;

import com.zc.mall.core.integral.dao.IntegralLogDao;
import com.zc.mall.core.integral.entity.IntegralLog;
import com.zc.mall.core.integral.model.IntegralLogModel;
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
 * 积分日志
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Repository
public class IntegralLogDaoImpl extends BaseDaoImpl<IntegralLog> implements IntegralLogDao {

    @Override
    public PageDataList<IntegralLog> list(IntegralLogModel model) {
        QueryParam param = QueryParam.getInstance();
        if (StringUtil.isNotBlank(model.getSearchName())) {
            SearchFilter orFilter2 = new SearchFilter("name", Operators.LIKE, model.getSearchName().trim());
            param.addOrFilter(orFilter2);
        } else {
            if (StringUtil.isNotBlank(model.getName())) {
                param.addParam("name", Operators.LIKE, model.getName().trim());
            }
        }
        if (model.getUserId() > 0) {
            param.addParam("user.id", model.getUserId());
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
        param.addOrder(OrderType.DESC, "addTime");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }

}