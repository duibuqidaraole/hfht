package com.zc.mall.mall.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zc.mall.core.manage.model.OperatorModel;
import com.zc.mall.mall.entity.GoodsSpuSpec;
import com.zc.sys.common.model.jpa.Page;
import org.springframework.beans.BeanUtils;

/**
 * 商品SPU-规格
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
public class GoodsSpuSpecModel extends GoodsSpuSpec {
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
    private GoodsSpuModel goodsSpuModel;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private GoodsSpecModel goodsSpecModel;
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
     * 实体转换model
     */
    public static GoodsSpuSpecModel instance(GoodsSpuSpec goodsSpuSpec) {
        if (goodsSpuSpec == null || goodsSpuSpec.getId() <= 0) {
            return null;
        }
        GoodsSpuSpecModel goodsSpuSpecModel = new GoodsSpuSpecModel();
        BeanUtils.copyProperties(goodsSpuSpec, goodsSpuSpecModel);
        return goodsSpuSpecModel;
    }

    /**
     * model转换实体
     */
    public GoodsSpuSpec prototype() {
        GoodsSpuSpec goodsSpuSpec = new GoodsSpuSpec();
        BeanUtils.copyProperties(this, goodsSpuSpec);
        return goodsSpuSpec;
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

    public GoodsSpecModel getGoodsSpecModel() {
        return goodsSpecModel;
    }

    public void setGoodsSpecModel(GoodsSpecModel goodsSpecModel) {
        this.goodsSpecModel = goodsSpecModel;
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
}