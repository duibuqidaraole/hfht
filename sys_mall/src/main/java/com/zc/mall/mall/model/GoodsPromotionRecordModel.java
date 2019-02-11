package com.zc.mall.mall.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zc.mall.mall.entity.GoodsPromotionRecord;
import com.zc.sys.common.model.jpa.Page;
import org.springframework.beans.BeanUtils;

/**
 * 商品活动记录
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年12月24日
 */
public class GoodsPromotionRecordModel extends GoodsPromotionRecord {
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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private GoodsSkuModel goodsSkuModel;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private GoodsPromotionModel goodsPromotionModel;


    /**
     * 实体转换model
     */
    public static GoodsPromotionRecordModel instance(GoodsPromotionRecord goodsPromotionRecord) {
        if (goodsPromotionRecord == null || goodsPromotionRecord.getId() <= 0) {
            return null;
        }
        GoodsPromotionRecordModel goodsPromotionRecordModel = new GoodsPromotionRecordModel();
        BeanUtils.copyProperties(goodsPromotionRecord, goodsPromotionRecordModel);
        return goodsPromotionRecordModel;
    }

    /**
     * model转换实体
     */
    public GoodsPromotionRecord prototype() {
        GoodsPromotionRecord goodsPromotionRecord = new GoodsPromotionRecord();
        BeanUtils.copyProperties(this, goodsPromotionRecord);
        return goodsPromotionRecord;
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

    public GoodsSkuModel getGoodsSkuModel() {
        return goodsSkuModel;
    }

    public void setGoodsSkuModel(GoodsSkuModel goodsSkuModel) {
        this.goodsSkuModel = goodsSkuModel;
    }

    public GoodsPromotionModel getGoodsPromotionModel() {
        return goodsPromotionModel;
    }

    public void setGoodsPromotionModel(GoodsPromotionModel goodsPromotionModel) {
        this.goodsPromotionModel = goodsPromotionModel;
    }
}