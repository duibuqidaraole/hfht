package com.zc.mall.mall.service.impl;

import com.zc.mall.core.manage.dao.OperatorDao;
import com.zc.mall.core.manage.entity.Operator;
import com.zc.mall.mall.dao.GoodsPromotionRecordDao;
import com.zc.mall.mall.dao.GoodsSkuDao;
import com.zc.mall.mall.dao.GoodsSkuSpecValueDao;
import com.zc.mall.mall.dao.GoodsSpuDao;
import com.zc.mall.mall.entity.GoodsPromotionRecord;
import com.zc.mall.mall.entity.GoodsSku;
import com.zc.mall.mall.entity.GoodsSpu;
import com.zc.mall.mall.model.*;
import com.zc.mall.mall.service.GoodsPromotionRecordService;
import com.zc.mall.mall.service.GoodsSkuService;
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
 * 商品SKU
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@Service
public class GoodsSkuServiceImpl implements GoodsSkuService {

    @Resource
    private GoodsSkuDao goodsSkuDao;
    @Resource
    private OperatorDao operatorDao;
    @Resource
    private GoodsSpuDao goodsSpuDao;
    @Resource
    private GoodsSkuSpecValueDao goodsSkuSpecValueDao;
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
    public Result list(GoodsSkuModel model) {
        PageDataList<GoodsSku> pageDataList = goodsSkuDao.list(model);
        PageDataList<GoodsSkuModel> pageDataList_ = new PageDataList<>();
        pageDataList_.setPage(pageDataList.getPage());
        List<GoodsSkuModel> list = new ArrayList<>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (GoodsSku goodsSku : pageDataList.getList()) {
                GoodsSkuModel model_ = GoodsSkuModel.instance(goodsSku);
                if (goodsSku.getGoodsSpu() != null) {
                    model_.setGoodsSpuModel(GoodsSpuModel.instance(goodsSku.getGoodsSpu()));
                }
                ArrayList<GoodsSpecValueModel> goodsSpecValueModels = new ArrayList<>();
                List<GoodsSkuSpecValueModel> goodsSkuSpecValueModelList = goodsSkuSpecValueDao.findBySku(goodsSku.getId());
                for (GoodsSkuSpecValueModel goodsSkuSpecValueModel : goodsSkuSpecValueModelList) {
                    GoodsSpecValueModel goodsSpecValueModel = GoodsSpecValueModel.instance(goodsSkuSpecValueModel.getGoodsSpecValue());
                    goodsSpecValueModel.setGoodsSpecModel(GoodsSpecModel.instance(goodsSpecValueModel.getGoodsSpec()));
                    goodsSpecValueModels.add(goodsSpecValueModel);
                }
                model_.setGoodsSpecValueModelList(goodsSpecValueModels);
                list.add(model_);
            }
        }
        pageDataList_.setList(list);
        return Result.success().setData(pageDataList_);
    }

    /**
     * 前台列表
     *
     * @param model
     * @return
     */
    @Override
    public Result listForReception(GoodsSkuModel model) {
        PageDataList<GoodsSku> pageDataList = goodsSkuDao.listForReception(model);
        PageDataList<GoodsSkuModel> pageDataList_ = new PageDataList<>();
        pageDataList_.setPage(pageDataList.getPage());
        List<GoodsSkuModel> list = new ArrayList<>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (GoodsSku goodsSku : pageDataList.getList()) {
                GoodsSkuModel model_ = GoodsSkuModel.instance(goodsSku);
                if (goodsSku.getGoodsSpu() != null) {
                    model_.setGoodsSpuModel(GoodsSpuModel.instance(goodsSku.getGoodsSpu()));
                }
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
    public Result add(GoodsSkuModel model) {
        this.checkAdd(model);
        this.initAdd(model);
        goodsSkuDao.save(model.prototype());
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
    public Result update(GoodsSkuModel model) {
        GoodsSku goodsSku = this.checkUpdate(model);
        this.initUpdate(model, goodsSku);
        goodsSkuDao.update(goodsSku);
        return Result.success();
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result getById(GoodsSkuModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        GoodsSku goodsSku = goodsSkuDao.find(model.getId());
        if (goodsSku == null) {
            return Result.error("参数错误！");
        }
        //添加规格
        GoodsSkuModel goodsSkuModel = GoodsSkuModel.instance(goodsSku);
        List<GoodsSkuSpecValueModel> GoodsSkuSpecValueModel = goodsSkuSpecValueDao.findBySku(goodsSkuModel.getId());
        goodsSkuModel.setGoodsSkuSpecValueModelList(GoodsSkuSpecValueModel);
        GoodsSpu goodsSpu = goodsSku.getGoodsSpu();
        GoodsSpuModel goodsSpuModel = GoodsSpuModel.instance(goodsSpu);
        goodsSpuModel.setGoodsBrandModel(GoodsBrandModel.instance(goodsSpu.getGoodsBrand()));
        goodsSpuModel.setGoodsCategoryModel(GoodsCategoryModel.instance(goodsSpu.getGoodsCategory()));
        goodsSkuModel.setGoodsSpuModel(goodsSpuModel);
        getPriceInterval(goodsSkuModel);
        //添加活动
        GoodsPromotionRecord goodsPromotionRecord = goodsPromotionRecordDao.findBySku(goodsSkuModel.getId());
        if (goodsPromotionRecord!=null){
            GoodsPromotionRecordModel goodsPromotionRecordModel = GoodsPromotionRecordModel.instance(goodsPromotionRecord);
            goodsPromotionRecordModel.setGoodsPromotionModel(GoodsPromotionModel.instance(goodsPromotionRecordModel.getGoodsPromotion()));
            goodsSkuModel.setGoodsPromotionRecordModel(goodsPromotionRecordModel);
        }
        return Result.success().setData(goodsSkuModel);
    }

    /**
     * 获取价格区间
     *
     * @param goodsSkuModel
     * @return
     */
    private void getPriceInterval(GoodsSkuModel goodsSkuModel) {
        //折扣后
        double l = 0D;
        double b = 0D;
        //原价
        double l1 = 0D;
        double b1 = 0D;
        GoodsSpu goodsSpu = goodsSkuModel.getGoodsSpu();
        List<GoodsSku> goodsSkuList = goodsSkuDao.findBySpu(goodsSpu.getId());
        for (GoodsSku goodsSku : goodsSkuList) {
            if (l == 0 || l > BigDecimalUtil.sub(goodsSku.getPrince(), goodsSku.getDiscount())) {
                l = BigDecimalUtil.sub(goodsSku.getPrince(), goodsSku.getDiscount());
            }
            if (b == 0 || b < BigDecimalUtil.sub(goodsSku.getPrince(), goodsSku.getDiscount())) {
                b = BigDecimalUtil.sub(goodsSku.getPrince(), goodsSku.getDiscount());
            }
            if (l == 0 || l1 > goodsSku.getPrince()) {
                l1 = goodsSku.getPrince();
            }
            if (b1 == 0 || b < goodsSku.getPrince()) {
                b1 = goodsSku.getPrince();
            }
        }
        goodsSkuModel.setPriceInterval(l1 == b1 ? l1 + "" : l1 + "" + "--" + b1 + "");
        goodsSkuModel.setPriceIntervalAfterDiscount(l == b ? l + "" : l + "" + "--" + b + "");
    }

    /**
     * 获取热门商品
     *
     * @return
     */
    @Override
    @Transactional
    public Object getHot() {
        List<GoodsSkuModel> goodsSkuModels = goodsSkuDao.getHot();
        return Result.success().setData(goodsSkuModels);
    }

    /**
     * 根据商品的规格值和spu获取sku
     *
     * @return
     */
    @Override
    @Transactional
    public Object getChooseSku(GoodsSkuModel model) {
        GoodsSpu goodsSpu = model.getGoodsSpu();
        if (goodsSpu == null || goodsSpu.getId() <= 0) {
            throw new BusinessException("spu有误");
        }
        int num = 0;
        GoodsSkuModel goodsSkuModel = null;
        List<Long> specValueList = model.getSpecValueList();
        List<Long> goodsSkuList = goodsSkuSpecValueDao.findBySpecValueList(specValueList);
        if (goodsSkuList == null) {
            throw new BusinessException("商品不存在");
        }
        for (Long goodsSkuId : goodsSkuList) {
            GoodsSku goodsSku = goodsSkuDao.find(goodsSkuId);
            if (goodsSku.getGoodsSpu().getId() == goodsSpu.getId()) {
                goodsSkuModel = GoodsSkuModel.instance(goodsSku);
                num++;
            }
        }
        if (num == 0) {
            throw new BusinessException("商品不存在");
        }
        if (num > 1) {
            throw new BusinessException("商品信息有误");
        }
        return getById(goodsSkuModel);
    }

    /**
     * 根据spuId获取商品列表
     *
     * @param goodsSpuId
     * @return
     */
    @Override
    public List<GoodsSku> findBySpuId(Long goodsSpuId) {
        return goodsSkuDao.findBySpu(goodsSpuId);
    }

    /**
     * 校验
     *
     * @param model
     */
    private void checkAdd(GoodsSkuModel model) {
        checkGoodsSpu(model);
        if (model.getPrince() <= 0) {
            throw new BusinessException("售价有误，sku表不能正确添加");
        }
        if (model.getAddOperator() == null || model.getAddOperator().getId() <= 0) {
            throw new BusinessException("管理员信息有误，无法添加商品分类");
        }
        Operator operator = operatorDao.find(model.getAddOperator().getId());
        if (operator == null) {
            throw new BusinessException("管理员不存在，无法添加商品分类");
        }
        if (StringUtil.isBlank(model.getName())) {
            throw new BusinessException("请输入正确sku名称");
        }
        if (model.getStock() <= 0) {
            throw new BusinessException("请输入正确sku库存");
        }
        if (StringUtil.isBlank(model.getImg())) {
            throw new BusinessException("请添加图片");
        }
    }

    /**
     * 校验spu
     *
     * @param model
     */
    private void checkGoodsSpu(GoodsSkuModel model) {
        if (model.getGoodsSpu() == null || model.getGoodsSpu().getId() <= 0) {
            throw new BusinessException("spu信息有误，请重新输入");
        }
        GoodsSpu goodsSpu = goodsSpuDao.find(model.getGoodsSpu().getId());
        if (goodsSpu == null) {
            throw new BusinessException("spu信息不存在，请重新输入");
        }
    }


    /**
     * 初始化
     *
     * @param model
     */
    private void initAdd(GoodsSkuModel model) {
        model.setAddTime(DateUtil.getNow());
        model.setSalesVolume(0);
        model.setNo(StringUtil.getSerialNumber());
        if (model.getIsRecommend() <= 0) {
            model.setIsRecommend(0);
        } else {
            model.setRecommendTime(DateUtil.getNow());
        }
    }

    /**
     * 校验
     *
     * @param model
     * @return
     */
    private GoodsSku checkUpdate(GoodsSkuModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误！");
        }
        GoodsSku goodsSku = goodsSkuDao.find(model.getId());
        if (goodsSku == null) {
            throw new BusinessException("参数错误！");
        }

        if (model.getUpdateOperator() == null || model.getUpdateOperator().getId() <= 0) {
            throw new BusinessException("管理员信息有误，无法添加商品分类");
        }

        Operator operator = operatorDao.find(model.getUpdateOperator().getId());
        if (operator == null) {
            throw new BusinessException("管理员不存在，无法添加商品分类");
        }
        return goodsSku;
    }

    /**
     * 初始化
     *
     * @param model
     */
    private void initUpdate(GoodsSkuModel model, GoodsSku goodsSku) {
        if (StringUtil.isBlank(model.getName())) {
            goodsSku.setName(model.getName());
        }
        if (model.getStock() >= 0&&model.getStock()!=goodsSku.getStock()) {
            goodsSku.setStock(model.getStock());
        }
        if (model.getPrince() >= 0) {
            goodsSku.setPrince(model.getPrince());
        }
        if (!StringUtil.isBlank(model.getImg())) {
            goodsSku.setImg(model.getImg());
        }
        if (model.getIsRecommend() != null) {
            if (model.getIsRecommend() > 0) {
                goodsSku.setRecommendTime(DateUtil.getNow());
            }
            if (model.getIsRecommend() < 0) {
                goodsSku.setRecommendTime(null);
            }
        }
        if (!StringUtil.isBlank(model.getSummary())) {
            goodsSku.setSummary(model.getSummary());
        }
        if (!StringUtil.isBlank(model.getDescription())) {
            goodsSku.setDescription(model.getDescription());
        }
    }

}