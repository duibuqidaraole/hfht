package com.zc.mall.core.account.dao;

import com.zc.mall.core.account.entity.AccountDeduct;
import com.zc.mall.core.account.model.AccountDeductModel;
import com.zc.sys.common.dao.BaseDao;
import com.zc.sys.common.model.jpa.PageDataList;

/**
 * 线下扣款
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public interface AccountDeductDao extends BaseDao<AccountDeduct> {
    /**
     * 列表
     *
     * @param model
     * @return
     */
    PageDataList<AccountDeduct> list(AccountDeductModel model);
}