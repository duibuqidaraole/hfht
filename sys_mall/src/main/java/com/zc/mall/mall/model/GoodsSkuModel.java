package com.zc.mall.mall.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zc.mall.mall.entity.GoodsSku;
import com.zc.sys.common.model.jpa.Page;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 商品SKU
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
public class GoodsSkuModel extends GoodsSku {
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
     * 推荐
     **/
    private Integer isRecommend;
    /**
     * 展示规格
     **/
    private String showSpec;
    /**
     * capacity规格值
     **/
    private String capacityValue;
    /**
     * 规格值
     **/
    private List<Long> specValueList;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private GoodsSpuModel goodsSpuModel;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private GoodsPromotionRecordModel goodsPromotionRecordModel;

    private List<GoodsSkuSpecValueModel> goodsSkuSpecValueModelList;
    /**
     * 规格值列表
     **/
    private List<GoodsSpecValueModel> goodsSpecValueModelList;
    /**
     * 评论list
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<OrderGoodsCommentModel> orderGoodsCommentModelList;

    /**
     * 价格区间
     **/
    private String priceInterval;

    /**
     * 价格区间
     **/
    private String priceIntervalAfterDiscount;

    /**
     * 实体转换model
     */
    public static GoodsSkuModel instance(GoodsSku goodsSku) {
        if (goodsSku == null || goodsSku.getId() <= 0) {
            return null;
        }
        GoodsSkuModel goodsSkuModel = new GoodsSkuModel();
        BeanUtils.copyProperties(goodsSku, goodsSkuModel);
        return goodsSkuModel;
    }

    /**
     * model转换实体
     */
    public GoodsSku prototype() {
        GoodsSku goodsSku = new GoodsSku();
        BeanUtils.copyProperties(this, goodsSku);
        return goodsSku;
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

    public GoodsSpuModel getGoodsSpuModel() {
        return goodsSpuModel;
    }

    public void setGoodsSpuModel(GoodsSpuModel goodsSpuModel) {
        this.goodsSpuModel = goodsSpuModel;
    }

    public List<GoodsSkuSpecValueModel> getGoodsSkuSpecValueModelList() {
        return goodsSkuSpecValueModelList;
    }

    public void setGoodsSkuSpecValueModelList(List<GoodsSkuSpecValueModel> goodsSkuSpecValueModelList) {
        this.goodsSkuSpecValueModelList = goodsSkuSpecValueModelList;
    }

    public List<OrderGoodsCommentModel> getOrderGoodsCommentModelList() {
        return orderGoodsCommentModelList;
    }

    public void setOrderGoodsCommentModelList(List<OrderGoodsCommentModel> orderGoodsCommentModelList) {
        this.orderGoodsCommentModelList = orderGoodsCommentModelList;
    }

    public Integer getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }

    public String getShowSpec() {
        return showSpec;
    }

    public void setShowSpec(String showSpec) {
        this.showSpec = showSpec;
    }

    public List<Long> getSpecValueList() {
        return specValueList;
    }

    public void setSpecValueList(List<Long> specValueList) {
        this.specValueList = specValueList;
    }

    public String getPriceInterval() {
        return priceInterval;
    }

    public void setPriceInterval(String priceInterval) {
        this.priceInterval = priceInterval;
    }

    public String getPriceIntervalAfterDiscount() {
        return priceIntervalAfterDiscount;
    }

    public void setPriceIntervalAfterDiscount(String priceIntervalAfterDiscount) {
        this.priceIntervalAfterDiscount = priceIntervalAfterDiscount;
    }

    public List<GoodsSpecValueModel> getGoodsSpecValueModelList() {
        return goodsSpecValueModelList;
    }

    public void setGoodsSpecValueModelList(List<GoodsSpecValueModel> goodsSpecValueModelList) {
        this.goodsSpecValueModelList = goodsSpecValueModelList;
    }

    public GoodsPromotionRecordModel getGoodsPromotionRecordModel() {
        return goodsPromotionRecordModel;
    }

    public void setGoodsPromotionRecordModel(GoodsPromotionRecordModel goodsPromotionRecordModel) {
        this.goodsPromotionRecordModel = goodsPromotionRecordModel;
    }

    public String getCapacityValue() {
        return capacityValue;
    }

    public void setCapacityValue(String capacityValue) {
        this.capacityValue = capacityValue;
    }
}