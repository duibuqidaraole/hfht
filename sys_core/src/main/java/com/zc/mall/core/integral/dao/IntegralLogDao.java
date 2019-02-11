package com.zc.mall.core.integral.dao;

import com.zc.mall.core.integral.entity.IntegralLog;
import com.zc.mall.core.integral.model.IntegralLogModel;
import com.zc.sys.common.dao.BaseDao;
import com.zc.sys.common.model.jpa.PageDataList;

/**
 * 积分日志
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public interface IntegralLogDao extends BaseDao<IntegralLog> {
    /**
     * 积分日志列表
     *
     * @param model
     * @return
     */
    PageDataList<IntegralLog> list(IntegralLogModel model);
}