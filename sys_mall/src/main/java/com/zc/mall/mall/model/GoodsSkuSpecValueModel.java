package com.zc.mall.mall.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zc.mall.core.manage.model.OperatorModel;
import com.zc.mall.mall.entity.GoodsSkuSpecValue;
import com.zc.sys.common.model.jpa.Page;
import org.springframework.beans.BeanUtils;

/**
 * 商品sku-规格值
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
public class GoodsSkuSpecValueModel extends GoodsSkuSpecValue {
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
     * sku
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private GoodsSkuModel goodsSkuModel;
    /**
     * 规格
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private GoodsSpecValueModel goodsSpecValueModel;

    /**
     * 已选规格值
     **/
    private String checkSpecValueList;
    /**
     * 查询规格
     **/
    private String searchSpecList;
    /**
     * 解析规格值
     **/
    private String addSpecValueList;
    /**
     * 商品名称
     **/
    private String spuName;
    /**
     * 规格
     **/
    private String capacity;
    /**
     * 质感
     **/
    private String texture;
    /**
     * 功效
     **/
    private String efficacy;
    /**
     * 有效期
     **/
    private String validity;
    /**
     * 包装类型
     **/
    private String packageType;
    /**
     * 适用肤质
     **/
    private String suitableSkin;
    /**
     * 适用人群
     **/
    private String suitablePeople;
    /**
     * 适用推荐
     **/
    private String suitableRecommend;
    /**
     * spushow
     **/
    private String spuShow;
    /**
     * 推荐位置
     **/
    private String recommendSite;
    /**
     * 推荐位置
     **/
    private Integer refreshRecomment;
    /**
     * 上架
     **/
    private Integer onSale;
    /**
     * 规格2
     **/
    private String specification;
    /**
     * 查询用spuId
     **/
    private Long goodsSpuId;
    /**
     * 推荐位置
     */
    private int recommendRanking;


    /**
     * 实体转换model
     */
    public static GoodsSkuSpecValueModel instance(GoodsSkuSpecValue goodsSkuSpecValue) {
        if (goodsSkuSpecValue == null || goodsSkuSpecValue.getId() <= 0) {
            return null;
        }
        GoodsSkuSpecValueModel goodsSkuSpecValueModel = new GoodsSkuSpecValueModel();
        BeanUtils.copyProperties(goodsSkuSpecValue, goodsSkuSpecValueModel);
        return goodsSkuSpecValueModel;
    }

    /**
     * model转换实体
     */
    public GoodsSkuSpecValue prototype() {
        GoodsSkuSpecValue goodsSkuSpecValue = new GoodsSkuSpecValue();
        BeanUtils.copyProperties(this, goodsSkuSpecValue);
        return goodsSkuSpecValue;
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

    public GoodsSkuModel getGoodsSkuModel() {
        return goodsSkuModel;
    }

    public void setGoodsSkuModel(GoodsSkuModel goodsSkuModel) {
        this.goodsSkuModel = goodsSkuModel;
    }

    public GoodsSpecValueModel getGoodsSpecValueModel() {
        return goodsSpecValueModel;
    }

    public void setGoodsSpecValueModel(GoodsSpecValueModel goodsSpecValueModel) {
        this.goodsSpecValueModel = goodsSpecValueModel;
    }

    public String getCheckSpecValueList() {
        return checkSpecValueList;
    }

    public void setCheckSpecValueList(String checkSpecValueList) {
        this.checkSpecValueList = checkSpecValueList;
    }

    public String getSearchSpecList() {
        return searchSpecList;
    }

    public void setSearchSpecList(String searchSpecList) {
        this.searchSpecList = searchSpecList;
    }

    public String getAddSpecValueList() {
        return addSpecValueList;
    }

    public void setAddSpecValueList(String addSpecValueList) {
        this.addSpecValueList = addSpecValueList;
    }

    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public String getEfficacy() {
        return efficacy;
    }

    public void setEfficacy(String efficacy) {
        this.efficacy = efficacy;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getSuitableSkin() {
        return suitableSkin;
    }

    public void setSuitableSkin(String suitableSkin) {
        this.suitableSkin = suitableSkin;
    }

    public String getSuitablePeople() {
        return suitablePeople;
    }

    public void setSuitablePeople(String suitablePeople) {
        this.suitablePeople = suitablePeople;
    }

    public String getSuitableRecommend() {
        return suitableRecommend;
    }

    public void setSuitableRecommend(String suitableRecommend) {
        this.suitableRecommend = suitableRecommend;
    }

    public String getSpuShow() {
        return spuShow;
    }

    public void setSpuShow(String spuShow) {
        this.spuShow = spuShow;
    }

    public String getRecommendSite() {
        return recommendSite;
    }

    public void setRecommendSite(String recommendSite) {
        this.recommendSite = recommendSite;
    }

    public Integer getRefreshRecomment() {
        return refreshRecomment;
    }

    public void setRefreshRecomment(Integer refreshRecomment) {
        this.refreshRecomment = refreshRecomment;
    }

    public Integer getOnSale() {
        return onSale;
    }

    public void setOnSale(Integer onSale) {
        this.onSale = onSale;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Long getGoodsSpuId() {
        return goodsSpuId;
    }

    public void setGoodsSpuId(Long goodsSpuId) {
        this.goodsSpuId = goodsSpuId;
    }

    public int getRecommendRanking() {
        return recommendRanking;
    }

    public void setRecommendRanking(int recommendRanking) {
        this.recommendRanking = recommendRanking;
    }
}