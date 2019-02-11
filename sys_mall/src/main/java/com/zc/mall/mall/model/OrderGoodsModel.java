package com.zc.mall.mall.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zc.mall.mall.entity.OrderGoods;
import com.zc.sys.common.model.jpa.Page;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单商品
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
public class OrderGoodsModel extends OrderGoods {
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
     * 评论
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<OrderGoodsCommentModel> orderGoodsCommentModelList;
    /**
     * 规则
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<GoodsSkuSpecValueModel> goodsSkuSpecValueModelList;
    /**
     * 品牌
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private GoodsBrandModel goodsBrandModel;
    /**
     * 活动记录
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private GoodsPromotionRecordModel goodsPromotionRecordModel;
    /**
     * 分类
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private GoodsCategoryModel goodsCategoryModel;
    /**
     * 订单
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private OrderInfoModel orderInfoModel;
    /**
     * 商品
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private GoodsSkuModel goodsSkuModel;

    /**
     * 商品可选规格值
     **/
    private String chooseSpecVale;

    /**
     * 实体转换model
     */
    public static List<OrderGoodsModel> instance(List<OrderGoods> orderGoodsList) {
        return instance(orderGoodsList, false);
    }

    /**
     * 实体转换model
     */
    public static List<OrderGoodsModel> instance(List<OrderGoods> orderGoodsList, boolean isComment) {
        if (orderGoodsList == null || orderGoodsList.size() <= 0) {
            return null;
        }
        List<OrderGoodsModel> orderGoodsModelList = new ArrayList<>();
        for (OrderGoods orderGoods : orderGoodsList) {
            OrderGoodsModel orderGoodsModel = instance(orderGoods);
            if (isComment) {
                orderGoodsModel.setOrderGoodsCommentModelList(OrderGoodsCommentModel.instance(orderGoods.getOrderGoodsCommentList()));
            }
            orderGoodsModel.setGoodsSkuModel(GoodsSkuModel.instance(orderGoodsModel.getGoodsSku()));
            orderGoodsModelList.add(orderGoodsModel);
        }
        return orderGoodsModelList;
    }

    /**
     * 实体转换model
     */
    public static OrderGoodsModel instance(OrderGoods orderGoods) {
        if (orderGoods == null || orderGoods.getId() <= 0) {
            return null;
        }
        OrderGoodsModel orderGoodsModel = new OrderGoodsModel();
        BeanUtils.copyProperties(orderGoods, orderGoodsModel);
        return orderGoodsModel;
    }

    /**
     * model转换实体
     */
    public OrderGoods prototype() {
        OrderGoods orderGoods = new OrderGoods();
        BeanUtils.copyProperties(this, orderGoods);
        return orderGoods;
    }

    public List<OrderGoodsCommentModel> getOrderGoodsCommentModelList() {
        return orderGoodsCommentModelList;
    }

    public void setOrderGoodsCommentModelList(List<OrderGoodsCommentModel> orderGoodsCommentModelList) {
        this.orderGoodsCommentModelList = orderGoodsCommentModelList;
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

    public GoodsBrandModel getGoodsBrandModel() {
        return goodsBrandModel;
    }

    public void setGoodsBrandModel(GoodsBrandModel goodsBrandModel) {
        this.goodsBrandModel = goodsBrandModel;
    }

    public GoodsCategoryModel getGoodsCategoryModel() {
        return goodsCategoryModel;
    }

    public void setGoodsCategoryModel(GoodsCategoryModel goodsCategoryModel) {
        this.goodsCategoryModel = goodsCategoryModel;
    }

    public List<GoodsSkuSpecValueModel> getGoodsSkuSpecValueModelList() {
        return goodsSkuSpecValueModelList;
    }

    public void setGoodsSkuSpecValueModelList(List<GoodsSkuSpecValueModel> goodsSkuSpecValueModelList) {
        this.goodsSkuSpecValueModelList = goodsSkuSpecValueModelList;
    }

    public OrderInfoModel getOrderInfoModel() {
        return orderInfoModel;
    }

    public void setOrderInfoModel(OrderInfoModel orderInfoModel) {
        this.orderInfoModel = orderInfoModel;
    }

    public GoodsSkuModel getGoodsSkuModel() {
        return goodsSkuModel;
    }

    public void setGoodsSkuModel(GoodsSkuModel goodsSkuModel) {
        this.goodsSkuModel = goodsSkuModel;
    }

    public String getChooseSpecVale() {
        return chooseSpecVale;
    }

    public void setChooseSpecVale(String chooseSpecVale) {
        this.chooseSpecVale = chooseSpecVale;
    }

    public GoodsPromotionRecordModel getGoodsPromotionRecordModel() {
        return goodsPromotionRecordModel;
    }

    public void setGoodsPromotionRecordModel(GoodsPromotionRecordModel goodsPromotionRecordModel) {
        this.goodsPromotionRecordModel = goodsPromotionRecordModel;
    }
}