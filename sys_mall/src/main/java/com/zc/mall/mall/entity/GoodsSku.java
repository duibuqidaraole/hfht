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
 * 商品SKU
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = BaseConstant.DB_PREFIX + BaseConstant.MODEL_G + "_goods_sku")
public class GoodsSku extends LongPKEntity {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;

    /**
     * sku编号，唯一
     **/
    private String no;
    /**
     * 名称
     **/
    private String name;
    /**
     * 状态
     **/
    private String state;
    /**
     * 描述
     **/
    private String description;
    /**
     * 售价
     **/
    private double prince;
    /**
     * 折扣
     **/
    private double discount;
    /**
     * 库存
     **/
    private int stock;
    /**
     * spu_id
     */
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_spu_id")
    private GoodsSpu goodsSpu;
    /**
     * 图片地址
     **/
    private String img;
    /**
     * pc图片
     **/
    private String pcImg;
    /**
     * 简介
     **/
    private String summary;
    /**
     * 销量
     **/
    private int salesVolume;
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
     * 推荐时间
     **/
    private Date recommendTime;

    /**
     * 添加时间
     **/
    private Date addTime;

    /**
     * 是否在售
     **/
    private int onSale;

    /**
     * 上架时间
     **/
    private Date onSaleDate;

    /**
     * 下架时间
     **/
    private Date offSaleDate;

    /**
     * 获取【sku编号，唯一】
     **/
    public String getNo() {
        return no;
    }

    /**
     * 设置【sku编号，唯一】
     **/
    public void setNo(String no) {
        this.no = no;
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

    /**
     * 获取【描述】
     **/
    public String getDescription() {
        return description;
    }

    /**
     * 设置【描述】
     **/
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取【售价】
     **/
    public double getPrince() {
        return prince;
    }

    /**
     * 设置【售价】
     **/
    public void setPrince(double prince) {
        this.prince = prince;
    }

    /**
     * 获取【折扣】
     **/
    public double getDiscount() {
        return discount;
    }

    /**
     * 设置【折扣】
     **/
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    /**
     * 获取【库存】
     **/
    public int getStock() {
        return stock;
    }

    /**
     * 设置【库存】
     **/
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * 获取【spu_id】
     **/
    public GoodsSpu getGoodsSpu() {
        return goodsSpu;
    }

    /**
     * 设置【spu_id】
     **/
    public void setGoodsSpu(GoodsSpu goodsSpu) {
        this.goodsSpu = goodsSpu;
    }

    /**
     * 获取【图片地址】
     **/
    public String getImg() {
        return img;
    }

    /**
     * 设置【图片地址】
     **/
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * 获取【简介】
     **/
    public String getSummary() {
        return summary;
    }

    /**
     * 设置【简介】
     **/
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * 获取【销量】
     **/
    public int getSalesVolume() {
        return salesVolume;
    }

    /**
     * 设置【销量】
     **/
    public void setSalesVolume(int salesVolume) {
        this.salesVolume = salesVolume;
    }

    /**
     * 获取【修改管理员id】
     **/
    public Operator getUpdateOperator() {
        return updateOperator;
    }

    /**
     * 设置【修改管理员id】
     **/
    public void setUpdateOperator(Operator updateOperator) {
        this.updateOperator = updateOperator;
    }

    /**
     * 获取【添加管理员id】
     **/
    public Operator getAddOperator() {
        return addOperator;
    }

    /**
     * 设置【添加管理员id】
     **/
    public void setAddOperator(Operator addOperator) {
        this.addOperator = addOperator;
    }

    /**
     * 获取【修改时间】
     **/
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置【修改时间】
     **/
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取【推荐时间】
     **/
    public Date getRecommendTime() {
        return recommendTime;
    }

    /**
     * 设置【推荐时间】
     **/
    public void setRecommendTime(Date recommendTime) {
        this.recommendTime = recommendTime;
    }

    /**
     * 获取【添加时间】
     **/
    public Date getAddTime() {
        return addTime;
    }

    /**
     * 设置【添加时间】
     **/
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getOnSale() {
        return onSale;
    }

    public void setOnSale(int onSale) {
        this.onSale = onSale;
    }

    public Date getOnSaleDate() {
        return onSaleDate;
    }

    public void setOnSaleDate(Date onSaleDate) {
        this.onSaleDate = onSaleDate;
    }

    public Date getOffSaleDate() {
        return offSaleDate;
    }

    public void setOffSaleDate(Date offSaleDate) {
        this.offSaleDate = offSaleDate;
    }

    public String getPcImg() {
        return pcImg;
    }

    public void setPcImg(String pcImg) {
        this.pcImg = pcImg;
    }
}