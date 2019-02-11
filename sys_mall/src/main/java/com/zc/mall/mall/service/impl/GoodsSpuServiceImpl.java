package com.zc.mall.mall.service.impl;

import com.zc.mall.core.manage.dao.OperatorDao;
import com.zc.mall.core.manage.entity.Operator;
import com.zc.mall.core.manage.model.OperatorModel;
import com.zc.mall.mall.dao.*;
import com.zc.mall.mall.entity.*;
import com.zc.mall.mall.model.*;
import com.zc.mall.mall.service.GoodsSpuService;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.util.calculate.BigDecimalUtil;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品SPU
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@Service
public class GoodsSpuServiceImpl implements GoodsSpuService {

    @Resource
    private GoodsSpuDao goodsSpuDao;
    @Resource
    private GoodsCategoryDao goodsCategoryDao;
    @Resource
    private GoodsBrandDao goodsBrandDao;
    @Resource
    private OperatorDao operatorDao;
    @Resource
    private GoodsSkuDao goodsSkuDao;
    @Resource
    private GoodsPromotionRecordDao goodsPromotionRecordDao;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result list(GoodsSpuModel model) {
        PageDataList<GoodsSpu> pageDataList = goodsSpuDao.list(model);
        PageDataList<GoodsSpuModel> pageDataList_ = new PageDataList<>();
        pageDataList_.setPage(pageDataList.getPage());
        List<GoodsSpuModel> list = new ArrayList<>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (GoodsSpu goodsSpu : pageDataList.getList()) {
                GoodsSpuModel model_ = GoodsSpuModel.instance(goodsSpu);
                if (goodsSpu.getGoodsCategory() != null) {
                    model_.setGoodsCategoryModel(GoodsCategoryModel.instance(goodsSpu.getGoodsCategory()));
                }
                if (goodsSpu.getGoodsBrand() != null) {
                    model_.setGoodsBrandModel(GoodsBrandModel.instance(goodsSpu.getGoodsBrand()));
                }
                if (goodsSpu.getAddOperator() != null) {
                    model_.setAddOperatorModel(OperatorModel.instance(goodsSpu.getAddOperator()));
                }
                if (goodsSpu.getUpdateOperator() != null) {
                    model_.setUpdateOperatorModel(OperatorModel.instance(goodsSpu.getUpdateOperator()));
                }
                //获取一个展示sku
                setFirstSku(model_);
                list.add(model_);
            }
        }
        pageDataList_.setList(list);
        return Result.success().setData(pageDataList_);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result add(GoodsSpuModel model) {
        this.checkAdd(model);
        this.initAdd(model);
        goodsSpuDao.save(model.prototype());
        return Result.success();
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result update(GoodsSpuModel model) {
        GoodsSpu goodsSpu = this.checkUpdate(model);
        this.initUpdate(model, goodsSpu);
        goodsSpuDao.update(goodsSpu);
        return Result.success();
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getById(GoodsSpuModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        GoodsSpu goodsSpu = goodsSpuDao.find(model.getId());
        if (goodsSpu == null) {
            return Result.error("参数错误！");
        }
        return Result.success().setData(GoodsSpuModel.instance(goodsSpu));
    }


    /**
     * 校验
     *
     * @param model
     */
    private void checkAdd(GoodsSpuModel model) {
        checkCategory(model);
        checkGoodsBrand(model);
        checkOperator(model.getAddOperator());
        if (StringUtil.isBlank(model.getName())) {
            throw new BusinessException("请输入正确spu名称");
        }
    }

    /**
     * 校验管理员
     *
     * @param operator
     */
    private void checkOperator(Operator operator) {
        if (operator == null || operator.getId() <= 0) {
            throw new BusinessException("管理员信息有误");
        }
        Operator operator1 = operatorDao.find(operator.getId());
        if (operator1 == null) {
            throw new BusinessException("管理员信息有误");
        }
    }


    /**
     * 校验商品品牌
     *
     * @param model
     */
    private void checkGoodsBrand(GoodsSpuModel model) {
        if (model.getGoodsBrand() == null || model.getGoodsBrand().getId() <= 0) {
            throw new BusinessException("商品品牌信息有误，无法添加商品spu");
        }
        GoodsBrand goodsBrand = goodsBrandDao.find(model.getGoodsBrand().getId());
        if (goodsBrand == null) {
            throw new BusinessException("商品品牌信息不存在，无法添加商品spu");
        }
    }

    /**
     * 校验商品分类是否存在
     *
     * @param model
     */
    private void checkCategory(GoodsSpuModel model) {
        if (model == null) {
            throw new BusinessException("请您输入正确spu信息");
        }
        if (model.getGoodsCategory() == null || model.getGoodsCategory().getId() <= 0) {
            throw new BusinessException("产品分类信息有误，无法添加商品spu");
        }
        GoodsCategory goodsCategory = goodsCategoryDao.find(model.getGoodsCategory().getId());
        if (goodsCategory == null) {
            throw new BusinessException("产品分类信息不存在，无法添加商品spu");
        }

    }


    /**
     * 初始化
     *
     * @param model
     */
    private void initAdd(GoodsSpuModel model) {
        model.setAddTime(DateUtil.getNow());
        model.setNo(StringUtil.getSerialNumber());
    }

    /**
     * 校验
     *
     * @param model
     * @return
     */
    private GoodsSpu checkUpdate(GoodsSpuModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误！");
        }
        GoodsSpu goodsSpu = goodsSpuDao.find(model.getId());
        if (goodsSpu == null) {
            throw new BusinessException("参数错误！");
        }
        if (model.getGoodsCategory() != null) {
            checkCategory(model);
        }
        if (model.getGoodsBrand() != null) {
            checkGoodsBrand(model);
        }
        checkOperator(model.getUpdateOperator());
        return goodsSpu;
    }

    /**
     * 初始化
     *
     * @param model
     */
    private void initUpdate(GoodsSpuModel model, GoodsSpu goodsSpu) {
        goodsSpu.setUpdateTime(DateUtil.getNow());
        if (!StringUtil.isBlank(model.getName())) {
            goodsSpu.setName(model.getName());
        }
        goodsSpu.setUpdateOperator(model.getUpdateOperator());
    }

    /**
     * 添加一个商品
     *
     * @param spuModel
     */
    public void setFirstSku(GoodsSpuModel spuModel) {
        GoodsSkuModel goodsSkuModel=null;
        List<GoodsSku> goodsSkuList = goodsSkuDao.findBySpu(spuModel.getId());
        for (GoodsSku goodsSku : goodsSkuList) {
            if ((BigDecimalUtil.sub(goodsSku.getPrince(), goodsSku.getDiscount())) == spuModel.getLessAmount() && goodsSku.getOnSale() > 0) {
                goodsSkuModel=GoodsSkuModel.instance(goodsSku);
                //spuModel.setShowSkuModel(GoodsSkuModel.instance(goodsSku));
                break;
            }
        }
        //没有符合上述条件的商品，查询上架商品
        if (spuModel.getShowSkuModel() == null) {
            for (GoodsSku goodsSku : goodsSkuList) {
                if (goodsSku.getOnSale() > 0) {
                    goodsSkuModel=GoodsSkuModel.instance(goodsSku);
                    //spuModel.setShowSkuModel(GoodsSkuModel.instance(goodsSku));
                    break;
                }
            }
        }
        //添加活动
        if (goodsSkuModel != null){
            GoodsPromotionRecord goodsPromotionRecord= goodsPromotionRecordDao.findBySku(goodsSkuModel.getId());
            if (goodsPromotionRecord!=null){
                GoodsPromotionRecordModel goodsPromotionRecordModel = GoodsPromotionRecordModel.instance(goodsPromotionRecord);
                goodsPromotionRecordModel.setGoodsPromotionModel(GoodsPromotionModel.instance(goodsPromotionRecordModel.getGoodsPromotion()));
                goodsSkuModel.setGoodsPromotionRecordModel(goodsPromotionRecordModel);
            }
        }
        spuModel.setShowSkuModel(goodsSkuModel);
    }
}