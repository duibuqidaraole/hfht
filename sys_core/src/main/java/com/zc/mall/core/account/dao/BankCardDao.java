package com.zc.mall.core.account.dao;

import com.zc.mall.core.account.entity.BankCard;
import com.zc.mall.core.account.model.BankCardModel;
import com.zc.sys.common.dao.BaseDao;
import com.zc.sys.common.model.jpa.PageDataList;

import java.util.List;
/**
 * 银行卡
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */

/**
 * 银行卡
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public interface BankCardDao extends BaseDao<BankCard> {

    /**
     * 列表
     * @param model
     * @return
     */
    PageDataList<BankCard> list(BankCardModel model);

    /**
     * 根据卡号查询绑定中的银行卡
     * @param bankCardNo
     * @return
     */
    List<BankCard> getByBankCardNo(long userId, String bankCardNo, int state);

    /**
     * 根据用户查询已绑定的银行卡
     * @param bankCardNo
     * @return
     */
    List<BankCard> getByBankCardNoYes(long userId);

    /**
     * 根据条件查询全部
     * @param model
     * @return
     */
    List<BankCard> getListByParam(BankCardModel model);

}