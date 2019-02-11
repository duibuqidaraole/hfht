package com.zc.mall.core.account.dao;

import com.zc.mall.core.account.entity.BankCard;
import com.zc.mall.core.account.entity.Recharge;
import com.zc.mall.core.account.model.RechargeModel;
import com.zc.sys.common.dao.BaseDao;
import com.zc.sys.common.model.jpa.PageDataList;

/**
 * 充值
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public interface RechargeDao extends BaseDao<Recharge> {
    /**
     * 列表
     *
     * @param model
     * @return
     */
    PageDataList<Recharge> list(RechargeModel model);

    /**
     * 通过银行卡的状态，银行卡卡号,银行预留手机号，银行预留用户姓名
     *
     * @param model
     * @return
     */
    BankCard findRechargeByBankCard(RechargeModel model);

    /**
     * 根据订单号查询
     *
     * @param orderNo
     * @return
     */
    Recharge findByOrderNo(String orderNo);
}