package com.zc.mall.promotion.model;

import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.promotion.dao.VipCouponsDao;
import com.zc.mall.promotion.entity.VipCoupons;
import com.zc.sys.common.model.jpa.Page;
import org.springframework.beans.BeanUtils;

/**
 * 商品分类
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
public class VipCouponsModel extends VipCoupons {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;

    /**
     * 当前页码
     **/
    private int pageNo;
    /**
     * 每页数据条数
     **/
    private int pageSize = Page.ROWS;
    /**
     * 条件查询
     **/
    private String searchName;

    /**
     * 实体转换model
     */
    public static VipCouponsModel instance(VipCoupons vipCoupons) {
        if (vipCoupons == null || vipCoupons.getId() <= 0) {
            return null;
        }
        VipCouponsModel vipCouponsModel = new VipCouponsModel();
        BeanUtils.copyProperties(vipCoupons, vipCouponsModel);
        return vipCouponsModel;
    }

    /**
     * model转换实体
     */
    public VipCoupons prototype() {
        VipCoupons vipCoupons = new VipCoupons();
        BeanUtils.copyProperties(this, vipCoupons);
        return vipCoupons;
    }

    /**
     * 获取【当前页码】
     **/
    public int getPageNo() {
        return pageNo;
    }

    /**
     * 设置【当前页码】
     **/
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * 获取【每页数据条数】
     **/
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置【每页数据条数】
     **/
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取【条件查询】
     **/
    public String getSearchName() {
        return searchName;
    }

    /**
     * 设置【条件查询】
     **/
    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    /**
     * 根据prizeNo查询
     *
     * @param prizeNo
     * @return
     */
    public static Object getByprizeNo(String prizeNo) {
        VipCouponsDao vipCouponsDao = BeanUtil.getBean(VipCouponsDao.class);
        return vipCouponsDao.findByPrizeNo(prizeNo);
    }
}