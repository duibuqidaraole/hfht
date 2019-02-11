package com.zc.mall.core.manage.dao.impl;

import com.zc.mall.core.manage.dao.NoticeMessageDao;
import com.zc.mall.core.manage.entity.NoticeMessage;
import com.zc.mall.core.manage.model.NoticeMessageModel;
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
 * 通知消息
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月15日
 */
@Repository
public class NoticeMessageDaoImpl extends BaseDaoImpl<NoticeMessage> implements
        NoticeMessageDao {

    @Override
    public PageDataList<NoticeMessage> list(NoticeMessageModel model) {
        QueryParam param = QueryParam.getInstance();
        if (StringUtil.isNotBlank(model.getSearchName())) {
            SearchFilter orFilter2 = new SearchFilter("receiveUser.realName",
                    Operators.LIKE, model.getSearchName().trim());
            SearchFilter orFilter3 = new SearchFilter("nid", Operators.LIKE,
                    model.getSearchName().trim());
            param.addOrFilter(orFilter2, orFilter3);
        }
        if (model.getState() != 0) {
            param.addParam("state", model.getState());
        }
        if (!StringUtil.isBlank(model.getStartTime()) && !StringUtil.isBlank(model.getEndTime())) {
            // 查询今日
            if (DateUtil.getDayStartTime(DateUtil.getTime(DateUtil.valueOf(model.getStartTime()))).equals(DateUtil.getDayStartTime(DateUtil.getTime(DateUtil.valueOf(model.getEndTime()))))) {
                param.addParam("addTime", Operators.GT, DateUtil.getDayStartTime(DateUtil.getTime(DateUtil.valueOf(model.getStartTime()))));
                param.addParam("addTime", Operators.LTE, DateUtil.getDayEndTime(DateUtil.getTime(DateUtil.valueOf(model.getEndTime()))));
            } else {
                param.addParam("addTime", Operators.GT, DateUtil.valueOf(model.getStartTime()));
                param.addParam("addTime", Operators.LTE, DateUtil.valueOf(model.getEndTime()));
            }
        }

        param.addOrder(OrderType.ASC, "id");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }

}