package com.zc.mall.core.manage.dao.impl;

import com.zc.mall.core.manage.dao.OperatorDao;
import com.zc.mall.core.manage.entity.Operator;
import com.zc.mall.core.manage.model.OperatorModel;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.model.jpa.OrderFilter;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import com.zc.sys.common.model.jpa.SearchFilter;
import com.zc.sys.common.model.jpa.SearchFilter.Operators;
import com.zc.sys.common.util.encrypt.MD5;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 管理员
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Repository
public class OperatorDaoImpl extends BaseDaoImpl<Operator> implements OperatorDao {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    public PageDataList<Operator> list(OperatorModel model) {
        QueryParam param = QueryParam.getInstance();
        if (StringUtil.isNotBlank(model.getSearchName())) {
            SearchFilter orFilter1 = new SearchFilter("userName", SearchFilter.Operators.LIKE, model.getSearchName().trim());
            SearchFilter orFilter2 = new SearchFilter("name", SearchFilter.Operators.LIKE, model.getSearchName().trim());

            param.addOrFilter(orFilter1, orFilter2);
        } else {
            if (StringUtil.isNotBlank(model.getName())) {
                param.addParam("name", SearchFilter.Operators.LIKE, model
                        .getName().trim());
            }
            if (model.getState() != 0) {
                param.addParam("state", model.getState());
            }
        }
        // 通过关联用户Id查找管理员表
        if (model.getUserId() > 0) {
            param.addParam("user.id", model.getUserId());

        }
        if (model.getPid() > 0) {
            param.addParam("user.id", Operators.NOTEQ, SearchFilter.NULL);
            param.addParam("pid", Operators.EQ, 0);

        }
        // 通过角色Id查找管理员表
        if (model.getRoleId() > 0) {
            param.addParam("role.id", model.getRoleId());
        }
        param.addOrder(OrderFilter.OrderType.ASC, "id");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }

    /**
     * 校验管理员交易密码
     *
     * @param id
     * @param payPwd
     * @return
     */
    @Override
    public boolean checkPayPwd(long id, String payPwd) {
        QueryParam param = QueryParam.getInstance();
        param.addParam("id", id);
        param.addParam("payPwd", MD5.toMD5(StringUtil.isNull(payPwd)));
        List<Operator> operaterList = this.findByCriteria(param);
        if (operaterList.size() == 1) {
            return true;
        }
        return false;
    }

    /**
     * 根据id查询管理员
     *
     * @param id
     * @return
     */
    @Override
    public Operator findById(int id) {
        QueryParam queryParam = QueryParam.getInstance();
        queryParam.addParam("id", id);
        return super.findByCriteriaForUnique(queryParam);
    }

}