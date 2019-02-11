package com.zc.mall.core.account.constant;

import com.zc.sys.common.exception.BusinessException;

/**
 * 账户常量
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2018年03月23日
 */
public final class AccountConstant {

    /**
     * Account-Statistics-type=========================================================start
     **/
    public static enum AccountStatisticsType {
        ACCOUNT_STATISTICS_TENDER_TOTAL(1, "投资总额"),
        ACCOUNT_STATISTICS_TENDER_AWARD_TOTAL(3, "投资获得奖励总额"),
        ACCOUNT_STATISTICS_TENDER_COLLECTION_CAPITAL_TOTAL(6, "待收本金累计"),
        ACCOUNT_STATISTICS_TENDER_COLLECTION_RATE_TOTAL(7, "待收利息累计"),
        ACCOUNT_STATISTICS_RECHARGE_TOTAL(8, "充值总额累计"),
        ACCOUNT_STATISTICS_WITHDRAW_CASH_TOTAL(9, "提现总额累计"),
        ACCOUNT_STATISTICS_TENDER_AREADYCOLLECTION_CAPITAL_TOTAL(10, "已收本金累计"),
        ACCOUNT_STATISTICS_TENDER_AREADYCOLLECTION_RATE_TOTAL(11, "已收利息累计"),
        ACCOUNT_STATISTICS_TENDER_GCOLLECTION_CAPITAL_TOTAL(12, "归还本金累计"),
        ACCOUNT_STATISTICS_TENDER_GCOLLECTION_INTEREST_TOTAL(13, "归还利息累计"),
        ACCOUNT_STATISTICS_TENDER_REPAYMENT_TOTAL(14, "还款冻结累计"),
        ACCOUNT_STATISTICS__GCOLLECTION_VERDUEINTEREST_TOTAL(15, "归还逾期利息累计"),
        ACCOUNT_STATISTICS_AREADYCOLLECTION_OVERDUEINTEREST_TOTAL(2, "已收逾期利息累计"),
        ACCOUNT_STATISTICS_PROJECT_FEE(16, "借款管理费累计"),
        ACCOUNT_STATISTICS_INTEREST_FEE(17, "支付利息管理费累计"),
        USER_RESIGER(18, "用户注册累计"),
        ACCOUNT_STATISTICS_TENDER_INCREASE_AMOUNT(19, "待收加息券利息总额累计"),
        ACCOUNT_STATISTICS_TENDER_INCREASE_ALREADYAMOUNT(20, "已收加息券利息总额累计"),
        ACCOUNT_STATISTICS_TENDER_AREADYCOLLECTION_INTEREST_TOTAL(21, "已收加息利息累计"),
        ACCOUNT_STATISTICS_TENDER_COLLECTION_INTEREST_TOTAL(22, "待收加息利息累计"),
        ACCOUNT_STATISTICS_ORDER_INFO_TOTAL(23, "订单消费金额累计"),
        ACCOUNT_STATISTICS_ORDER_INFO_COUNT(24, "订单消费数目累计"),;
        /**
         * 标识
         **/
        private int type;
        /**
         * 名称
         **/
        private String name;

        private AccountStatisticsType(int type, String name) {
            this.type = type;
            this.name = name;
        }

        public static String getName(int type) {
            for (AccountStatisticsType s : AccountStatisticsType.values()) {
                if (type == s.getType()) {
                    return s.getName();
                }
            }
            throw new BusinessException("枚举不存在");
        }

        /**
         * 获取【标识】
         **/
        public int getType() {
            return type;
        }

        /**
         * 设置【标识】
         **/
        public void setType(int type) {
            this.type = type;
        }

        /**
         * 获取【名称】
         **/
        public String getName() {
            return name;
        }

        /**
         * 设置【名称】
         **/
        public void setName(String name) {
            this.name = name;
        }
    }
    /** Account-Statistics-type=========================================================end **/

    /** Account-recharge-way=========================================================start **/
    /**
     * 充值方式-网银
     **/
    public static final int ACCOUNT_RECHARGE_WAY_ONLINE_BANK = 1;
    /**
     * 充值方式-pc快捷
     **/
    public static final int ACCOUNT_RECHARGE_WAY_PC_QUICK = 2;
    /** Account-recharge-way=========================================================end **/

}
