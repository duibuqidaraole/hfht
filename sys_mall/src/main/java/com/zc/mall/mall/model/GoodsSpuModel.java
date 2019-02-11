package com.zc.mall.mall.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zc.mall.core.manage.model.OperatorModel;
import com.zc.mall.mall.entity.GoodsSpu;
import com.zc.sys.common.model.jpa.Page;
import org.springframework.beans.BeanUtils;

/**
 * 商品SPU
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
public class GoodsSpuModel extends GoodsSpu {
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
     * 添加管理员
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private OperatorModel addOperatorModel;
    /**
     * 更新管理员
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private OperatorModel updateOperatorModel;
    /**
     * 分类
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private GoodsCategoryModel goodsCategoryModel;
    /**
     * 品牌
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private GoodsBrandModel goodsBrandModel;
    /**
     * 展示商品
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private GoodsSkuModel showSkuModel;
    /**
     * 多个推荐位置
     */
    private String recommendSiteList;

    /**
     * 实体转换model
     */
    public static GoodsSpuModel instance(GoodsSpu goodsSpu) {
        if (goodsSpu == null || goodsSpu.getId() <= 0) {
            return null;
        }
        GoodsSpuModel goodsSpuModel = new GoodsSpuModel();
        BeanUtils.copyProperties(goodsSpu, goodsSpuModel);
        return goodsSpuModel;
    }

    /**
     * model转换实体
     */
    public GoodsSpu prototype() {
        GoodsSpu goodsSpu = new GoodsSpu();
        BeanUtils.copyProperties(this, goodsSpu);
        return goodsSpu;
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

    public OperatorModel getAddOperatorModel() {
        return addOperatorModel;
    }

    public void setAddOperatorModel(OperatorModel addOperatorModel) {
        this.addOperatorModel = addOperatorModel;
    }

    public OperatorModel getUpdateOperatorModel() {
        return updateOperatorModel;
    }

    public void setUpdateOperatorModel(OperatorModel updateOperatorModel) {
        this.updateOperatorModel = updateOperatorModel;
    }

    public GoodsCategoryModel getGoodsCategoryModel() {
        return goodsCategoryModel;
    }

    public void setGoodsCategoryModel(GoodsCategoryModel goodsCategoryModel) {
        this.goodsCategoryModel = goodsCategoryModel;
    }

    public GoodsBrandModel getGoodsBrandModel() {
        return goodsBrandModel;
    }

    public void setGoodsBrandModel(GoodsBrandModel goodsBrandModel) {
        this.goodsBrandModel = goodsBrandModel;
    }

    public GoodsSkuModel getShowSkuModel() {
        return showSkuModel;
    }

    public void setShowSkuModel(GoodsSkuModel showSkuModel) {
        this.showSkuModel = showSkuModel;
    }

    public String getRecommendSiteList() {
        return recommendSiteList;
    }

    public void setRecommendSiteList(String recommendSiteList) {
        this.recommendSiteList = recommendSiteList;
    }
}