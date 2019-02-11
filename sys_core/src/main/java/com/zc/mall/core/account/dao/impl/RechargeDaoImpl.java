package com.zc.mall.core.account.dao.impl;

import com.zc.mall.core.account.dao.BankCardDao;
import com.zc.mall.core.account.dao.RechargeDao;
import com.zc.mall.core.account.entity.BankCard;
import com.zc.mall.core.account.entity.Recharge;
import com.zc.mall.core.account.model.RechargeModel;
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
 * 充值
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Repository
public class RechargeDaoImpl extends BaseDaoImpl<Recharge> implements RechargeDao {
    @Override
    public PageDataList<Recharge> list(RechargeModel model) {
        QueryParam param = QueryParam.getInstance();
        if (StringUtil.isNotBlank(model.getSearchName())) {
            SearchFilter orFilter1 = new SearchFilter("user.realName", Operators.LIKE, model.getSearchName().trim());
            SearchFilter orFilter2 = new SearchFilter("user.mobile", Operators.LIKE, model.getSearchName().trim());
            param.addOrFilter(orFilter1, orFilter2);
        }
        if (model.getState() != 0) {
            param.addParam("state", model.getState());
        }
        if (model.getUserId() > 0) {
            param.addParam("user.id", model.getUserId());
        }
        if (StringUtil.isNotBlank(model.getMobile())) {
            param.addParam("mobile", model.getMobile());
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

    /**
     * 通过银行卡的状态，银行卡卡号,银行预留手机号，银行预留用户姓名
     *
     * @param model
     * @return
     */
    @Override
    public BankCard findRechargeByBankCard(RechargeModel model) {
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

    /**
     * 根据订单号查询
     *
     * @param orderNo
     * @return
     */
    @Override
    public Recharge findByOrderNo(String orderNo) {
        return findObjByProperty("orderNo", orderNo);
    }

}