package com.zc.mall.core.account.dao.impl;

import com.zc.mall.core.account.dao.BankCardDao;
import com.zc.mall.core.account.entity.BankCard;
import com.zc.mall.core.account.model.BankCardModel;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.model.jpa.OrderFilter.OrderType;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import com.zc.sys.common.model.jpa.SearchFilter;
import com.zc.sys.common.model.jpa.SearchFilter.Operators;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 银行卡
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Repository
public class BankCardDaoImpl extends BaseDaoImpl<BankCard> implements BankCardDao {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    public PageDataList<BankCard> list(BankCardModel model) {
        QueryParam param = QueryParam.getInstance();
        if (StringUtil.isNotBlank(model.getSearchName())) {
            SearchFilter orFilter1 = new SearchFilter("name", Operators.LIKE, model.getSearchName().trim());
            SearchFilter orFilter2 = new SearchFilter("nid", Operators.LIKE, model.getSearchName().trim());
            param.addOrFilter(orFilter1, orFilter2);
        } else {
            if (model.getState() != 0) {
                param.addParam("state", model.getState());
            }
        }
        if (model.getUserId() > 0) {
            param.addParam("user.id", model.getUserId());
        }
        if (StringUtil.isNotBlank(model.getMobile())) {
            param.addParam("user.mobile", model.getMobile());
        }
        param.addOrder(OrderType.DESC, "addTime");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }

    /**
     * 根据卡号查询绑定中的银行卡
     *
     * @param bankCardNo
     * @return
     */
    @Override
    public List<BankCard> getByBankCardNo(long userId, String bankCardNo, int state) {
        return getListByParam(new BankCardModel(state, bankCardNo, userId));
    }

    /**
     * 根据卡号查询已绑定的银行卡
     *
     * @param bankCardNo
     * @return
     */
    @Override
    public List<BankCard> getByBankCardNoYes(long userId) {
        return getListByParam(new BankCardModel(BaseConstant.BUSINESS_STATE_YES, userId));
    }

    /**
     * 根据条件查询全部
     *
     * @param model
     * @return
     */
    @Override
    public List<BankCard> getListByParam(BankCardModel model) {
        QueryParam param = QueryParam.getInstance();
        if (StringUtil.isBlank(model.getBankCardNo())) {
            param.addParam("bankCardNo", model.getBankCardNo());
        }
        if (model.getState() != 0) {
            param.addParam("state", model.getState());
        }
        if (model.getUser() != null && model.getUser().getId() > 0) {
            param.addParam("user.id", model.getUser().getId());
        }
        param.addOrder(OrderType.DESC, "id");
        return super.findByCriteria(param);
    }
}