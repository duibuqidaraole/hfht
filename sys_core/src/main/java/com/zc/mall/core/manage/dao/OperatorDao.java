package com.zc.mall.core.manage.dao;

import com.zc.mall.core.manage.entity.Operator;
import com.zc.mall.core.manage.model.OperatorModel;
import com.zc.sys.common.dao.BaseDao;
import com.zc.sys.common.model.jpa.PageDataList;

/**
 * 管理员
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public interface OperatorDao extends BaseDao<Operator> {
    /**
     * 列表
     *
     * @param model
     * @return
     */
    public PageDataList<Operator> list(OperatorModel model);

    /**
     * 校验管理员交易密码
     *
     * @param id
     * @param payPwd
     * @return
     */
    public boolean checkPayPwd(long id, String payPwd);

    /**
     * 根据id查询管理员
     *
     * @param id
     * @return
     */
    Operator findById(int id);
}