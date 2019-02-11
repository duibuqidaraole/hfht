package com.zc.mall.core.sys.constant;

/**
 * 字典nid枚举
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2018年10月9日
 */
public enum DictNidEnum {
    // 物流
    LOGISTICS_TYPE("logistics_type"),
    // 支付方式
    PAY_TYPE("pay_type"),
    // 退款理由
    REFUND_REASON("refund_reason"),
    // 热门搜索
    HOT_SEARCH("hot_search"),
    // tab搜索
    SHOW_IN_TAB("show_in_tab"),
    // 倒计时
    COUNT_DOWN("count_down"),
    // 热门位置
    RECOMMEND_SITE("recommend_site"),
    // 用户需求
    USER_DEMANDS("user_demands"),;
    // 字典nid
    private String nid;

    private DictNidEnum(String nid) {
        this.nid = nid;
    }

    /**
     * 获取【nid】
     **/
    public String getNid() {
        return nid;
    }

    /**
     * 设置【nid】
     **/
    public void setNid(String nid) {
        this.nid = nid;
    }

}
