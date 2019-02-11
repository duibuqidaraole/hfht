package com.zc.mall.core.account.dao;

import com.zc.mall.core.account.entity.UserSelfHelpAccountLog;
import com.zc.mall.core.account.model.UserSelfHelpAccountLogModel;
import com.zc.sys.common.dao.BaseDao;
import com.zc.sys.common.model.jpa.PageDataList;

/**
 * 用户自助记账
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2018年08月05日
 */
public interface UserSelfHelpAccountLogDao extends BaseDao<UserSelfHelpAccountLog> {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    PageDataList<UserSelfHelpAccountLog> list(UserSelfHelpAccountLogModel model);

}