package com.zc.mall.core.account.dao.impl;

import com.zc.mall.core.account.dao.BankCardDao;
import com.zc.mall.core.account.dao.WithdrawCashDao;
import com.zc.mall.core.account.entity.BankCard;
import com.zc.mall.core.account.entity.WithdrawCash;
import com.zc.mall.core.account.model.WithdrawCashModel;
import com.zc.mall.core.common.global.BeanUtil;
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
 * 提现
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Repository
public class WithdrawCashDaoImpl extends BaseDaoImpl<WithdrawCash> implements WithdrawCashDao {

    @Override
    public PageDataList<WithdrawCash> list(WithdrawCashModel model) {
        QueryParam param = QueryParam.getInstance();
        if (StringUtil.isNotBlank(model.getSearchName())) {
            SearchFilter orFilter1 = new SearchFilter("name", Operators.LIKE, model.getSearchName().trim());
            SearchFilter orFilter2 = new SearchFilter("nid", Operators.LIKE, model.getSearchName().trim());
            param.addOrFilter(orFilter1, orFilter2);
        }
        if (StringUtil.isNotBlank(model.getUserName())) {
            param.addParam("user.userName", model.getUserName());
        }
        if (model.getUserId() != 0) {
            param.addParam("user.id", model.getUserId());
        }
        if (model.getState() != 0) {
            param.addParam("state", model.getState());
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

    @Override
    public WithdrawCash findByOrderNo(String orderNo) {
        return findObjByProperty("orderNo", orderNo);
    }

    @Override
    public BankCard findRechargeByBankCard(WithdrawCashModel model) {
        BankCardDao bankCardDao = (BankCardDao) BeanUtil.getBean(BankCardDao.class);
        QueryParam param = QueryParam.getInstance();
        param.addParam("user.id", model.getUserId());
        param.addParam("state", model.getState());
        param.addParam("bankCardNo", model.getBankCardNo());
        param.addParam("mobile", model.getMobile());
        param.addParam("realName", model.getRealName());
        BankCard bankCard = bankCardDao.findByCriteriaForUnique(param);
        return bankCard;
    }

}