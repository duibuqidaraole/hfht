package com.zc.mall.mall.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.manage.entity.Operator;
import com.zc.sys.common.entity.LongPKEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 商品SPU
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = BaseConstant.DB_PREFIX + BaseConstant.MODEL_G + "_goods_spu")
public class GoodsSpu extends LongPKEntity {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;

    /**
     * 商品唯一编号
     **/
    private String no;
    /**
     * 名称
     **/
    private String name;

    /**
     * 商品分类
     */
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_category_id")
    private GoodsCategory goodsCategory;

    /**
     * 商品品牌
     */
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_brand_id")
    private GoodsBrand goodsBrand;

    /**
     * 修改管理员id
     */
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "update_operator_id")
    private Operator updateOperator;

    /**
     * 添加管理员id
     */
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "add_operator_id")
    private Operator addOperator;

    /**
     * 修改时间
     **/
    private Date updateTime;
    /**
     * 添加时间
     **/
    private Date addTime;
    /**
     * 搜索标题
     **/
    private String showTab;
    /**
     * 最低售价
     **/
    private double lessAmount;
    /**
     * 最高售价
     **/
    private double mostAmount;
    /**
     * 最低售价(未减优惠)
     **/
    private double LessAmountWithOutDiscount;
    /**
     * 最高售价 (未减优惠)
     **/
    private double MostAmountWithOutDiscount;

    /**
     * 推荐位置
     **/
    private String recommendSite;
    /**
     * 推荐位置
     **/
    private int recommendRanking;
    /**
     * 推荐时间
     **/
    private Date recommendDate;
    /**
     * sku在售数目
     **/
    private int goodsSkuCount;
    /**
     * 可以使用vip
     **/
    private int isVipPrince;

    /**
     * 设置【商品唯一编号】
     **/
    public void setNo(String no) {
        this.no = no;
    }

    /**
     * 获取【商品唯一编号】
     **/
    public String getNo() {
        return no;
    }

    /**
     * 设置【名称】
     **/
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取【名称】
     **/
    public String getName() {
        return name;
    }

    /**
     * 设置【修改时间】
     **/
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取【修改时间】
     **/
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置【添加时间】
     **/
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取【添加时间】
     **/
    public Date getAddTime() {
        return addTime;
    }

    public GoodsCategory getGoodsCategory() {
        return goodsCategory;
    }

    public void setGoodsCategory(GoodsCategory goodsCategory) {
        this.goodsCategory = goodsCategory;
    }

    public GoodsBrand getGoodsBrand() {
        return goodsBrand;
    }

    public void setGoodsBrand(GoodsBrand goodsBrand) {
        this.goodsBrand = goodsBrand;
    }

    public Operator getUpdateOperator() {
        return updateOperator;
    }

    public void setUpdateOperator(Operator updateOperator) {
        this.updateOperator = updateOperator;
    }

    public Operator getAddOperator() {
        return addOperator;
    }

    public void setAddOperator(Operator addOperator) {
        this.addOperator = addOperator;
    }

    public double getLessAmount() {
        return lessAmount;
    }

    public void setLessAmount(double lessAmount) {
        this.lessAmount = lessAmount;
    }

    public String getShowTab() {
        return showTab;
    }

    public void setShowTab(String showTab) {
        this.showTab = showTab;
    }

    public String getRecommendSite() {
        return recommendSite;
    }

    public void setRecommendSite(String recommendSite) {
        this.recommendSite = recommendSite;
    }

    public Date getRecommendDate() {
        return recommendDate;
    }

    public void setRecommendDate(Date recommendDate) {
        this.recommendDate = recommendDate;
    }

    public double getMostAmount() {
        return mostAmount;
    }

    public void setMostAmount(double mostAmount) {
        this.mostAmount = mostAmount;
    }

    public int getGoodsSkuCount() {
        return goodsSkuCount;
    }

    public void setGoodsSkuCount(int goodsSkuCount) {
        this.goodsSkuCount = goodsSkuCount;
    }

    public double getLessAmountWithOutDiscount() {
        return LessAmountWithOutDiscount;
    }

    public void setLessAmountWithOutDiscount(double LessAmountWithOutDiscount) {
        this.LessAmountWithOutDiscount = LessAmountWithOutDiscount;
    }

    public double getMostAmountWithOutDiscount() {
        return MostAmountWithOutDiscount;
    }

    public void setMostAmountWithOutDiscount(double MostAmountWithOutDiscount) {
        this.MostAmountWithOutDiscount = MostAmountWithOutDiscount;
    }

    public int getRecommendRanking() {
        return recommendRanking;
    }

    public void setRecommendRanking(int recommendRanking) {
        this.recommendRanking = recommendRanking;
    }

    public int getIsVipPrince() {
        return isVipPrince;
    }

    public void setIsVipPrince(int isVipPrince) {
        this.isVipPrince = isVipPrince;
    }
}