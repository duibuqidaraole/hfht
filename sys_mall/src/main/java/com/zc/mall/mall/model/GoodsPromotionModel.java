package com.zc.mall.mall.model;

import com.zc.mall.mall.entity.GoodsPromotion;
import com.zc.sys.common.model.jpa.Page;
import org.springframework.beans.BeanUtils;

/**
 * 商品活动
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年12月24日
 */
public class GoodsPromotionModel extends GoodsPromotion {
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
    public static GoodsPromotionModel instance(GoodsPromotion goodsPromotion) {
        if (goodsPromotion == null || goodsPromotion.getId() <= 0) {
            return null;
        }
        GoodsPromotionModel goodsPromotionModel = new GoodsPromotionModel();
        BeanUtils.copyProperties(goodsPromotion, goodsPromotionModel);
        return goodsPromotionModel;
    }

    /**
     * model转换实体
     */
    public GoodsPromotion prototype() {
        GoodsPromotion goodsPromotion = new GoodsPromotion();
        BeanUtils.copyProperties(this, goodsPromotion);
        return goodsPromotion;
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

}