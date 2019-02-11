package com.zc.mall.mall.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.manage.dao.OperatorDao;
import com.zc.mall.core.manage.entity.Operator;
import com.zc.mall.mall.dao.*;
import com.zc.mall.mall.entity.*;
import com.zc.mall.mall.model.*;
import com.zc.mall.mall.service.GoodsSkuSpecValueService;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.util.calculate.BigDecimalUtil;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 商品sku-规格值
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@Service
public class GoodsSkuSpecValueServiceImpl implements GoodsSkuSpecValueService {

    @Resource
    private GoodsSkuSpecValueDao goodsSkuSpecValueDao;
    @Resource
    private GoodsSkuDao goodsSkuDao;
    @Resource
    private GoodsSpuSpecDao goodsSpuSpecDao;
    @Resource
    private GoodsSpuDao goodsSpuDao;
    @Resource
    private GoodsSpecValueDao goodsSpecValueDao;
    @Resource
    private OperatorDao operatorDao;
    @Resource
    private GoodsBrandDao goodsBrandDao;
    @Resource
    private GoodsCategoryDao goodsCategoryDao;
    @Resource
    private GoodsSpecDao goodsSpecDao;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result list(GoodsSkuSpecValueModel model) {
        PageDataList<GoodsSkuSpecValue> pageDataList = goodsSkuSpecValueDao.list(model);
        PageDataList<GoodsSkuSpecValueModel> pageDataList_ = new PageDataList<>();
        pageDataList_.setPage(pageDataList.getPage());
        List<GoodsSkuSpecValueModel> list = new ArrayList<>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (GoodsSkuSpecValue goodsSkuSpecValue : pageDataList.getList()) {
                GoodsSkuSpecValueModel model_ = GoodsSkuSpecValueModel.instance(goodsSkuSpecValue);
                if (goodsSkuSpecValue.getGoodsSku() != null) {
                    GoodsSkuModel goodsSkuModel = GoodsSkuModel.instance(goodsSkuSpecValue.getGoodsSku());
                    goodsSkuModel.setShowSpec(goodsSkuSpecValueDao.getShowSpec(goodsSkuModel.getId()));
                    model_.setGoodsSkuModel(goodsSkuModel);
                }
                if (goodsSkuSpecValue.getGoodsSpecValue() != null) {
                    model_.setGoodsSpecValueModel(GoodsSpecValueModel.instance(goodsSkuSpecValue.getGoodsSpecValue()));
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
    public Result add(GoodsSkuSpecValueModel model) {
        this.checkAdd(model);
        this.initAdd(model);
        goodsSkuSpecValueDao.save(model.prototype());
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
    public Result update(GoodsSkuSpecValueModel model) {
        GoodsSkuSpecValue goodsSkuSpecValue = this.checkUpdate(model);
        this.initUpdate(model, goodsSkuSpecValue);
        goodsSkuSpecValueDao.update(goodsSkuSpecValue);
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
    public Result getById(GoodsSkuSpecValueModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        GoodsSkuSpecValue goodsSkuSpecValue = goodsSkuSpecValueDao.find(model.getId());
        if (goodsSkuSpecValue == null) {
            return Result.error("参数错误！");
        }
        GoodsSkuSpecValueModel goodsSkuSpecValueModel = GoodsSkuSpecValueModel.instance(goodsSkuSpecValue);
        GoodsSkuModel goodsSkuModel = GoodsSkuModel.instance(goodsSkuSpecValueModel.getGoodsSku());
        goodsSkuModel.setGoodsSpuModel(GoodsSpuModel.instance(goodsSkuModel.getGoodsSpu()));
        goodsSkuSpecValueModel.setGoodsSkuModel(goodsSkuModel);
        goodsSkuSpecValueModel.setGoodsSpecValueModel(GoodsSpecValueModel.instance(goodsSkuSpecValueModel.getGoodsSpecValue()));
        return Result.success().setData(goodsSkuSpecValueModel);
    }

    /**
     * 根据spu获取
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Object getBySpuId(GoodsSkuSpecValueModel model) {
        if (model.getGoodsSku() == null || model.getGoodsSku().getId() <= 0) {
            throw new BusinessException("请传入正确商品信息");
        }
        GoodsSku goodsSku = goodsSkuDao.find(model.getGoodsSku().getId());
        if (goodsSku == null) {
            throw new BusinessException("商品信息有误");
        }
        JSONObject result = new JSONObject();
        GoodsSpu goodsSpu = goodsSku.getGoodsSpu();
        GoodsSpuSpecModel goodsSpuSpecModel = new GoodsSpuSpecModel();
        goodsSpuSpecModel.setGoodsSpu(goodsSpu);
        //根据spu获取list
        List<GoodsSpuSpec> list = goodsSpuSpecDao.list(goodsSpuSpecModel).getList();
        HashSet<Long> longs = new HashSet<>();
        ArrayList<GoodsSpecModel> goodsSpecs = new ArrayList<>();
        for (GoodsSpuSpec goodsSpuSpec : list) {
            if (longs.add(goodsSpuSpec.getGoodsSpec().getId())) {
                goodsSpecs.add(GoodsSpecModel.instance(goodsSpuSpec.getGoodsSpec()));
            }
        }
        result.put("goodsSpuSpecList", goodsSpecs);

        for (GoodsSpec goodsSpec : goodsSpecs) {
            ArrayList<GoodsSpecValueModel> goodsSpecValueModels = new ArrayList<>();
            //获取规格值list表
            List<GoodsSpecValue> goodsSpecValueList = goodsSkuSpecValueDao.findBySpuId(goodsSpu.getId(), BaseConstant.BUSINESS_STATE_YES);
            for (GoodsSpecValue goodsSpecValue : goodsSpecValueList) {
                if (goodsSpecValue.getGoodsSpec().getNo().equals(goodsSpec.getNo())) {
                    goodsSpecValueModels.add(GoodsSpecValueModel.instance(goodsSpecValue));
                }
            }
            result.put(goodsSpec.getNo(), goodsSpecValueModels);
        }
        return Result.success().setData(result);
    }

    /**
     * 获取有库存的商品规格
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Object getOnSalesList(GoodsSkuSpecValueModel model) {
        //获取商品列表
        List<GoodsSku> goodsSkuList = goodsSkuDao.findBySpu(model.getGoodsSpuId());
        HashSet<Long> specIdResultList = new HashSet<>();
        for (GoodsSku goodsSku : goodsSkuList) {
            //根据商品获取商品规格值列表
            List<GoodsSkuSpecValueModel> goodsSkuSpecValueModelList = goodsSkuSpecValueDao.findBySku(goodsSku.getId(), BaseConstant.BUSINESS_STATE_YES);
            //获取商品规格值id列表
            ArrayList<Long> dbSpecValueIdList = new ArrayList<>();
            for (GoodsSkuSpecValueModel goodsSkuSpecValueModel : goodsSkuSpecValueModelList) {
                dbSpecValueIdList.add(goodsSkuSpecValueModel.getGoodsSpecValue().getId());
            }
            //校验是否满足条件
            if (StringUtil.isBlank(model.getCheckSpecValueList()) || dbSpecValueIdList.containsAll(Arrays.asList(model.getCheckSpecValueList().split(",")))) {
                //获取有库存的商品
                if (goodsSku.getStock() > 0 && goodsSku.getOnSale() > 0) {
                    for (GoodsSkuSpecValueModel goodsSkuSpecValueModel : goodsSkuSpecValueDao.findBySku(goodsSku.getId())) {
                        //筛选有库存的可选规格
                        if (goodsSkuSpecValueModel.getGoodsSpecValue().getGoodsSpec().getType() == 1) {
                            specIdResultList.add(goodsSkuSpecValueModel.getGoodsSpecValue().getId());
                        }
                    }
                }
            }
        }
        return Result.success().setData(specIdResultList);
    }

    /**
     * 一步添加商品
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Object addSkuAllIn(GoodsSkuSpecValueModel model) {
        long modelGoodsSkuId = model.getGoodsSku().getId();
        double spuPrince = BigDecimalUtil.sub(model.getGoodsSku().getPrince(), model.getGoodsSku().getDiscount());
        //校验
        checkParam(model);
        //初始化spu
        GoodsSpu goodsSpu = null;
        //如果是复制则更新最低售价否则创建
        if (model.getGoodsSku() != null && model.getGoodsSku().getId() > 0) {
            //更新spu
            goodsSpu = reAddSpu(goodsSkuDao.find(model.getGoodsSku().getId()).getGoodsSpu(), model, spuPrince);
        } else {
            //初始化创建spu
            goodsSpu = initSpu(model);
        }
        //初始化创建sku
        GoodsSku goodsSku = initSku(goodsSpu, model);
        //添加商品规格关联关系
        initSpuSpec(goodsSku, model, modelGoodsSkuId);
        return Result.success().setData(GoodsSkuModel.instance(goodsSku));
    }

    /**
     * 复制时更改spu
     *
     * @param goodsSpu
     * @param model
     */
    private GoodsSpu reAddSpu(GoodsSpu goodsSpu, GoodsSkuSpecValueModel model, double spuPrince) {
        if (model.getOnSale() == 1) {
            goodsSpu.setGoodsSkuCount(model.getGoodsSku().getGoodsSpu().getGoodsSkuCount() + 1);
            goodsSpu.setLessAmount(goodsSpu.getLessAmount() > spuPrince ? spuPrince : goodsSpu.getLessAmount());
            goodsSpu.setMostAmount(goodsSpu.getMostAmount() < spuPrince ? spuPrince : goodsSpu.getMostAmount());
            goodsSpu.setLessAmountWithOutDiscount(goodsSpu.getLessAmountWithOutDiscount() > model.getGoodsSku().getPrince() ? model.getGoodsSku().getPrince() : goodsSpu.getLessAmountWithOutDiscount());
            goodsSpu.setMostAmountWithOutDiscount(goodsSpu.getMostAmountWithOutDiscount() < model.getGoodsSku().getPrince() ? model.getGoodsSku().getPrince() : goodsSpu.getMostAmountWithOutDiscount());
        }
        return goodsSpu;
    }

    /**
     * 一步修改商品
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Object updateSkuAllIn(GoodsSkuSpecValueModel model) {
        //校验管理员
        checkOperator(model.getUpdateOperator());
        checkUpdateSkuAllIn(model);
        //获取数据库数据
        GoodsSku goodsSkuDB = goodsSkuDao.find(model.getGoodsSku().getId());
        GoodsSpu goodsSpu = goodsSkuDB.getGoodsSpu();
        //更新spu
        updateSpu(goodsSpu, model, goodsSkuDB);
        goodsSkuDB.setGoodsSpu(goodsSpu);
        //更新sku
        updateSku(goodsSkuDB, model);
        //根据spu获取sku列表
        List<GoodsSku> GoodsSkuList = goodsSkuDao.findBySpu(goodsSpu.getId());
        for (GoodsSku sku : GoodsSkuList) {
            //获取规格值列表 循环并更新公有参数
            List<GoodsSkuSpecValueModel> goodsSkuSpecValueModelList = goodsSkuSpecValueDao.findBySku(sku.getId());
            for (GoodsSkuSpecValueModel goodsSkuSpecValueModel : goodsSkuSpecValueModelList) {
                if (goodsSkuSpecValueModel.getGoodsSpecValue().getGoodsSpec().getType() != 1 || goodsSkuSpecValueModel.getGoodsSku().getId() == goodsSkuDB.getId()) {
                    GoodsSkuSpecValue goodsSkuSpecValue = goodsSkuSpecValueModel.prototype();
                    updateSpecValue(goodsSkuSpecValue, model);
                }
            }
        }
        return Result.success().setData(GoodsSkuModel.instance(goodsSkuDB));
    }

    /**
     * 校验参数
     *
     * @param model
     */
    private void checkUpdateSkuAllIn(GoodsSkuSpecValueModel model) {
        if (model.getGoodsSku().getPrince() < 0) {
            model.getGoodsSku().setPrince(0D);
        }
        if (model.getGoodsSku().getStock() < 0) {
            model.getGoodsSku().setStock(0);
        }
    }

    /**
     * 更新spu
     *
     * @param goodsSpu
     * @param model
     */
    private void updateSpu(GoodsSpu goodsSpu, GoodsSkuSpecValueModel model, GoodsSku goodsSkuDB) {
        goodsSpu.setName(StringUtil.isBlank(model.getSpuName()) ? goodsSpu.getName() : model.getSpuName());
        goodsSpu.setUpdateOperator(model.getUpdateOperator());
        goodsSpu.setShowTab(model.getSpuShow());
        goodsSpu.setUpdateTime(DateUtil.getNow());
        //更新价格范围
        updateSpuAmount(goodsSpu, model, goodsSkuDB);
        if (!Objects.equals(model.getRecommendSite(), goodsSpu.getRecommendSite())) {
            goodsSpu.setRecommendSite(model.getRecommendSite());
        }
        if (model.getRefreshRecomment() != null && model.getRefreshRecomment() > 0) {
            goodsSpu.setRecommendDate(DateUtil.getNow());
        }
        goodsSpu.setRecommendRanking(model.getRecommendRanking());
        goodsSpu.setIsVipPrince(model.getGoodsSkuModel().getGoodsSpuModel().getIsVipPrince());
        goodsSpuDao.update(goodsSpu);
        goodsSpuDao.flush();
    }

    /**
     * 更新商品时更新价格范围
     *
     * @param goodsSpu
     * @param model
     * @param goodsSkuDB
     */
    private void updateSpuAmount(GoodsSpu goodsSpu, GoodsSkuSpecValueModel model, GoodsSku goodsSkuDB) {
        double lessAmount = goodsSpu.getLessAmount();
        double mostAmount = goodsSpu.getMostAmount();
        double LessAmountWithOutDiscount = goodsSpu.getLessAmountWithOutDiscount();
        double MostAmountWithOutDiscount = goodsSpu.getMostAmountWithOutDiscount();
        double modelSub = BigDecimalUtil.sub(model.getGoodsSku().getPrince(), model.getGoodsSku().getDiscount());
        double modelPrince = model.getGoodsSku().getPrince();
        double goodsSkuDBSub = BigDecimalUtil.sub(goodsSkuDB.getPrince(), goodsSkuDB.getDiscount());
        double skuPrince = goodsSkuDB.getPrince();
        //1更改上下架
        if (goodsSkuDB.getOnSale() != model.getOnSale()) {
            //1.1上架直接判断添加值与最值的关系
            if (model.getOnSale() > 0) {
                goodsSpu.setLessAmount((lessAmount > modelSub || lessAmount == 0) ? modelSub : lessAmount);
                goodsSpu.setMostAmount(mostAmount < modelSub ? modelSub : mostAmount);
                goodsSpu.setLessAmountWithOutDiscount(LessAmountWithOutDiscount > modelPrince ? modelPrince : LessAmountWithOutDiscount);
                goodsSpu.setMostAmountWithOutDiscount(MostAmountWithOutDiscount < modelPrince ? modelPrince : MostAmountWithOutDiscount);
                //添加商品数目
                goodsSpu.setGoodsSkuCount(model.getGoodsSku().getGoodsSpu().getGoodsSkuCount() + 1);
                //1.2下架
            } else {
                //减少商品数目
                goodsSpu.setGoodsSkuCount(model.getGoodsSku().getGoodsSpu().getGoodsSkuCount() - 1);
                //1.2.1如果下架商品的值为最小值
                if (goodsSkuDBSub <= lessAmount || goodsSkuDBSub >= mostAmount) {
                    goodsSpu.setLessAmount(0D);
                    for (GoodsSku goodsSku : goodsSkuDao.findBySpu(goodsSpu.getId())) {
                        if (goodsSku.getId() != goodsSkuDB.getId() && goodsSku.getOnSale() > 0 && (goodsSpu.getLessAmount() == 0 || goodsSpu.getLessAmount() > BigDecimalUtil.sub(goodsSku.getPrince(), goodsSku.getDiscount()))) {
                            goodsSpu.setLessAmount(BigDecimalUtil.sub(goodsSku.getPrince(), goodsSku.getDiscount()));
                        }
                    }
                }
                if (skuPrince <= LessAmountWithOutDiscount || skuPrince >= MostAmountWithOutDiscount) {
                    goodsSpu.setLessAmountWithOutDiscount(0D);
                    for (GoodsSku goodsSku : goodsSkuDao.findBySpu(goodsSpu.getId())) {
                        if (goodsSku.getId() != goodsSkuDB.getId() && goodsSku.getOnSale() > 0 && (goodsSpu.getLessAmountWithOutDiscount() == 0 || goodsSpu.getLessAmountWithOutDiscount() > goodsSku.getPrince())) {
                            goodsSpu.setLessAmountWithOutDiscount(goodsSku.getPrince());
                        }
                    }
                }


                //1.2.2如果下架商品的值为最大值
                if (BigDecimalUtil.sub(goodsSkuDB.getPrince(), goodsSkuDB.getDiscount()) >= goodsSpu.getMostAmount()) {
                    goodsSpu.setMostAmount(0D);
                    for (GoodsSku goodsSku : goodsSkuDao.findBySpu(goodsSpu.getId())) {
                        if (goodsSku.getId() != goodsSkuDB.getId() && goodsSku.getOnSale() > 0 && (goodsSpu.getMostAmount() == 0 || goodsSpu.getMostAmount() < BigDecimalUtil.sub(goodsSku.getPrince(), goodsSku.getDiscount()))) {
                            goodsSpu.setMostAmount(BigDecimalUtil.sub(goodsSku.getPrince(), goodsSku.getDiscount()));
                        }
                    }
                }

                if (goodsSkuDB.getPrince() >= goodsSpu.getMostAmount()) {
                    goodsSpu.setMostAmountWithOutDiscount(0D);
                    for (GoodsSku goodsSku : goodsSkuDao.findBySpu(goodsSpu.getId())) {
                        if (goodsSku.getId() != goodsSkuDB.getId() && goodsSku.getOnSale() > 0 && (MostAmountWithOutDiscount == 0 || MostAmountWithOutDiscount < goodsSku.getPrince())) {
                            goodsSpu.setMostAmountWithOutDiscount(goodsSku.getPrince());
                        }
                    }
                }
            }
            //2如果是上架状态不变
        } else if (model.getOnSale() > 0) {
            //2.1如果商品值是最值 循环获得最值
            if (BigDecimalUtil.sub(goodsSkuDB.getPrince(), goodsSkuDB.getDiscount()) == goodsSpu.getMostAmount() || BigDecimalUtil.sub(goodsSkuDB.getPrince(), goodsSkuDB.getDiscount()) == goodsSpu.getLessAmount()
                    || LessAmountWithOutDiscount == skuPrince || MostAmountWithOutDiscount == skuPrince) {
                lessAmount = BigDecimalUtil.sub(model.getGoodsSku().getPrince(), model.getGoodsSku().getDiscount());
                mostAmount = BigDecimalUtil.sub(model.getGoodsSku().getPrince(), model.getGoodsSku().getDiscount());
                LessAmountWithOutDiscount = modelPrince;
                MostAmountWithOutDiscount = modelPrince;
                List<GoodsSku> goodsSkuList = goodsSkuDao.findBySpu(goodsSpu.getId());
                for (GoodsSku goodsSku : goodsSkuList) {
                    if (goodsSku.getOnSale() > 0 && goodsSku.getId() != model.getGoodsSku().getId()) {
                        double skuSubAmount = BigDecimalUtil.sub(goodsSku.getPrince(), goodsSku.getDiscount());
                        double goodsSkuPrince = goodsSku.getPrince();
                        if (lessAmount > skuSubAmount) {
                            lessAmount = skuSubAmount;
                        }
                        if (mostAmount < skuSubAmount) {
                            mostAmount = skuSubAmount;
                        }
                        if (LessAmountWithOutDiscount > goodsSkuPrince) {
                            LessAmountWithOutDiscount = goodsSkuPrince;
                        }
                        if (MostAmountWithOutDiscount < goodsSkuPrince) {
                            MostAmountWithOutDiscount = goodsSkuPrince;
                        }
                    }
                }
                //2.2如果商品值不是最值 直接比较
            } else {
                lessAmount = lessAmount > modelSub ? modelSub : lessAmount;
                LessAmountWithOutDiscount = LessAmountWithOutDiscount > modelPrince ? modelPrince : LessAmountWithOutDiscount;
                mostAmount = mostAmount < modelSub ? modelSub : mostAmount;
                MostAmountWithOutDiscount = MostAmountWithOutDiscount < modelPrince ? modelPrince : MostAmountWithOutDiscount;
            }
            goodsSpu.setMostAmount(mostAmount);
            goodsSpu.setLessAmount(lessAmount);
            goodsSpu.setMostAmountWithOutDiscount(MostAmountWithOutDiscount);
            goodsSpu.setLessAmountWithOutDiscount(LessAmountWithOutDiscount);
        }
    }

    /**
     * 更新商品
     *
     * @param goodsSku
     * @param model
     */
    private void updateSku(GoodsSku goodsSku, GoodsSkuSpecValueModel model) {
        //商品上下架更改
        if (model.getOnSale() != null && model.getOnSale() != goodsSku.getOnSale()) {
            if (model.getOnSale() == 1) {
                goodsSku.setOnSaleDate(DateUtil.getNow());
                goodsSku.getGoodsSpu().setGoodsSkuCount(model.getGoodsSku().getGoodsSpu().getGoodsSkuCount() + 1);
            } else {
                goodsSku.setOffSaleDate(DateUtil.getNewDate());
                goodsSku.getGoodsSpu().setGoodsSkuCount(model.getGoodsSku().getGoodsSpu().getGoodsSkuCount() - 1);
            }
            goodsSku.setOnSale(model.getOnSale());
        }
        goodsSku.setUpdateOperator(model.getUpdateOperator());
        goodsSku.setName(model.getSpuName());
        goodsSku.setSummary(model.getGoodsSku().getSummary());
        goodsSku.setImg(model.getGoodsSku().getImg());
        goodsSku.setPcImg(model.getGoodsSku().getPcImg());
        goodsSku.setPrince(model.getGoodsSku().getPrince());
        goodsSku.setStock(model.getGoodsSku().getStock());
        goodsSku.setDiscount(model.getGoodsSku().getDiscount());
        goodsSku.setDescription(model.getGoodsSku().getDescription());
        goodsSkuDao.update(goodsSku);
    }

    private void updateSpecValue(GoodsSkuSpecValue goodsSkuSpecValue, GoodsSkuSpecValueModel model) {
        GoodsSpecValue goodsSpecValue = goodsSkuSpecValue.getGoodsSpecValue();
        GoodsSpec goodsSpec = goodsSpecValue.getGoodsSpec();
        String no = goodsSpec.getNo();
        switch (no) {
            case "spec_1_capacity_1812010048954322":
                goodsSpecValue.setValue(model.getCapacity());
                break;
            case "spec_1_specification_1812010048954321":
                goodsSpecValue.setValue(model.getSpecification());
                break;
            case "spec_-1_texture_1812010048954312":
                goodsSpecValue.setValue(model.getTexture());
                break;
            case "spec_-1_efficacy_1812010048954312":
                goodsSpecValue.setValue(model.getEfficacy());
                break;
            case "spec_-1_validity_1812010113043098":
                goodsSpecValue.setValue(model.getValidity());
                break;
            case "spec_-1_package_type_1812014568954312":
                goodsSpecValue.setValue(model.getPackageType());
                break;
            case "spec_-1_suitable_skin_1812630113043098":
                goodsSpecValue.setValue(model.getSuitableSkin());
                break;
            case "spec_-1_suitable_people_1812010113049648":
                goodsSpecValue.setValue(model.getSuitablePeople());
                break;
            case "spec_-1_suitable_recommend_1812010048959647":
                goodsSpecValue.setValue(model.getSuitableRecommend());
                break;
            default:
                break;
        }
        goodsSpecValueDao.update(goodsSpecValue);
        goodsSpecValueDao.flush();
    }

    /**
     * 校验参数
     *
     * @param model
     */
    private void checkParam(GoodsSkuSpecValueModel model) {
        if (model == null) {
            throw new BusinessException("参数错误");
        }
        checkOperator(model.getAddOperator());
        if (StringUtil.isBlank(model.getSpuName())) {
            throw new BusinessException("请传入商品名称");
        }
        if (model.getGoodsSku().getPrince() <= 0) {
            throw new BusinessException("价格不正常");
        }
        if (model.getGoodsSku().getStock() <= 0) {
            throw new BusinessException("库存不正常");
        }

    }

    /**
     * 初始化商品spu
     *
     * @param model
     */
    private GoodsSpu initSpu(GoodsSkuSpecValueModel model) {
        GoodsSpu goodsSpu = new GoodsSpu();
        goodsSpu.setAddOperator(model.getAddOperator());
        goodsSpu.setName(model.getSpuName());
        goodsSpu.setAddTime(DateUtil.getNow());
        goodsSpu.setGoodsBrand(goodsBrandDao.find(1L));
        goodsSpu.setGoodsCategory(goodsCategoryDao.find(1L));
        goodsSpu.setNo(StringUtil.getSerialNumber());
        goodsSpu.setShowTab(model.getSpuShow());
        if (model.getGoodsSkuModel() != null && model.getGoodsSkuModel().getGoodsSpuModel() != null) {
            goodsSpu.setIsVipPrince(model.getGoodsSkuModel().getGoodsSpuModel().getIsVipPrince());
        }
        if (StringUtil.isNotBlank(model.getRecommendSite())) {
            goodsSpu.setRecommendSite(model.getRecommendSite());
            goodsSpu.setRecommendDate(DateUtil.getNow());
            goodsSpu.setRecommendRanking(model.getRecommendRanking());
        }
        if (model.getOnSale() == 1) {
            goodsSpu.setLessAmount(BigDecimalUtil.sub(model.getGoodsSku().getPrince(), model.getGoodsSku().getDiscount()));
            goodsSpu.setMostAmount(BigDecimalUtil.sub(model.getGoodsSku().getPrince(), model.getGoodsSku().getDiscount()));
            goodsSpu.setLessAmountWithOutDiscount(model.getGoodsSku().getPrince());
            goodsSpu.setMostAmountWithOutDiscount(model.getGoodsSku().getPrince());
            goodsSpu.setGoodsSkuCount(model.getGoodsSku().getGoodsSpu().getGoodsSkuCount() + 1);
        }
        goodsSpuDao.save(goodsSpu);
        goodsSpuDao.flush();
        return goodsSpu;
    }

    private void initSpuSpec(GoodsSku goodsSku, GoodsSkuSpecValueModel model, long modelGoodsSkuId) {
        initSpuSpec(goodsSku, "spec_1_capacity_1812010048954322", model.getCapacity());
        initSpuSpec(goodsSku, "spec_1_specification_1812010048954321", model.getSpecification());
        if (modelGoodsSkuId == 0) {
            initSpuSpec(goodsSku, "spec_-1_texture_1812010048954312", model.getTexture());
            initSpuSpec(goodsSku, "spec_-1_efficacy_1812010048954312", model.getEfficacy());
            initSpuSpec(goodsSku, "spec_-1_validity_1812010113043098", model.getValidity());
            initSpuSpec(goodsSku, "spec_-1_package_type_1812014568954312", model.getPackageType());
            initSpuSpec(goodsSku, "spec_-1_suitable_skin_1812630113043098", model.getSuitableSkin());
            initSpuSpec(goodsSku, "spec_-1_suitable_people_1812010113049648", model.getSuitablePeople());
            initSpuSpec(goodsSku, "spec_-1_suitable_recommend_1812010048959647", model.getSuitableRecommend());
        } else {
            copySpecValue(modelGoodsSkuId, goodsSku);
        }
    }

    /**
     * 拷贝已有参数
     *
     * @param id
     * @param goodsSku
     */
    private void copySpecValue(long id, GoodsSku goodsSku) {
        List<GoodsSkuSpecValueModel> goodsSkuSpecValueModelList = goodsSkuSpecValueDao.findBySku(id);
        for (GoodsSkuSpecValueModel goodsSkuSpecValueModel : goodsSkuSpecValueModelList) {
            if (goodsSkuSpecValueModel.getGoodsSpecValue().getGoodsSpec().getType() != BaseConstant.BUSINESS_STATE_YES) {
                GoodsSkuSpecValue goodsSkuSpecValue = new GoodsSkuSpecValue();
                goodsSkuSpecValue.setGoodsSku(goodsSku);
                goodsSkuSpecValue.setGoodsSpecValue(goodsSkuSpecValueModel.getGoodsSpecValue());
                goodsSkuSpecValue.setAddTime(DateUtil.getNow());
                goodsSkuSpecValue.setAddOperator(goodsSku.getAddOperator());
                goodsSkuSpecValueDao.save(goodsSkuSpecValue);
                goodsSkuSpecValueDao.flush();
            }
        }
    }

    /**
     * 给商品添加规格
     */
    private void initSpuSpec(GoodsSku goodsSku, String no, String value) {
        GoodsSpu goodsSpu = goodsSku.getGoodsSpu();
        GoodsSpec goodsSpec = goodsSpecDao.findByNo(no);
        GoodsSpuSpec goodsSpuSpec = new GoodsSpuSpec();
        goodsSpuSpec.setGoodsSpu(goodsSpu);
        goodsSpuSpec.setAddOperator(goodsSpu.getAddOperator());
        goodsSpuSpec.setGoodsSpec(goodsSpec);
        goodsSpuSpec.setAddTime(DateUtil.getNow());
        goodsSpuSpecDao.save(goodsSpuSpec);
        goodsSpuSpecDao.flush();
        GoodsSpecValue goodsSpecValue = new GoodsSpecValue();
        goodsSpecValue.setValue(value);
        goodsSpecValue.setGoodsSpec(goodsSpec);
        goodsSpecValue.setAddOperator(goodsSku.getAddOperator());
        goodsSpecValue.setAddTime(DateUtil.getNow());
        goodsSpecValueDao.save(goodsSpecValue);
        goodsSpecValueDao.flush();
        GoodsSkuSpecValue goodsSkuSpecValue = new GoodsSkuSpecValue();
        goodsSkuSpecValue.setGoodsSpecValue(goodsSpecValue);
        goodsSkuSpecValue.setGoodsSku(goodsSku);
        goodsSkuSpecValue.setAddOperator(goodsSku.getAddOperator());
        goodsSkuSpecValue.setAddTime(DateUtil.getNow());
        goodsSkuSpecValueDao.save(goodsSkuSpecValue);
    }

    /**
     * 初始化sku
     *
     * @param goodsSpu
     * @param model
     * @return
     */
    private GoodsSku initSku(GoodsSpu goodsSpu, GoodsSkuSpecValueModel model) {
        double lessAmount = goodsSpu.getLessAmount();
        GoodsSku goodsSku = model.getGoodsSku();
        goodsSku.setGoodsSpu(goodsSpu);
        goodsSku.setName(model.getSpuName());
        goodsSku.setAddTime(DateUtil.getNow());
        goodsSku.setAddOperator(model.getAddOperator());
        goodsSku.setNo(StringUtil.getSerialNumber());
        goodsSku.setOnSale(model.getOnSale());
        if (model.getOnSale() != null && model.getOnSale() == 1) {
            goodsSku.setOnSaleDate(DateUtil.getNow());
        } else {
            goodsSku.setOffSaleDate(DateUtil.getNewDate());
        }
        goodsSku.setGoodsSpu(goodsSpu);
        goodsSku.setId(0);
        goodsSkuDao.save(goodsSku);
        goodsSkuDao.flush();
        return goodsSku;
    }

    /**
     * 校验
     *
     * @param model
     */
    private void checkAdd(GoodsSkuSpecValueModel model) {
        checkOperator(model.getAddOperator());
        checkSku(model);
        checkSpecValue(model);
        checkSpuSpecValue(model);
    }

    /**
     * 校验此商品是否含有此规格值
     *
     * @param model
     */
    private void checkSpuSpecValue(GoodsSkuSpecValueModel model) {
        if (goodsSkuSpecValueDao.checkSkuAndSpecValue(model)) {
            throw new BusinessException("此商品已有此规格");
        }
    }

    /**
     * 校验规格
     *
     * @param model
     */
    private void checkSpecValue(GoodsSkuSpecValueModel model) {
        if (model.getGoodsSpecValue() == null || model.getGoodsSpecValue().getId() <= 0) {
            throw new BusinessException("商品规格有误");
        }
        GoodsSpecValue goodsSpecValue = goodsSpecValueDao.find(model.getGoodsSpecValue().getId());
        if (goodsSpecValue == null) {
            throw new BusinessException("商品规格有误");
        }
    }

    /**
     * 校验sku
     *
     * @param model
     */
    private void checkSku(GoodsSkuSpecValueModel model) {
        if (model.getGoodsSku() == null || model.getGoodsSku().getId() <= 0) {
            throw new BusinessException("sku有误");
        }
        GoodsSku goodsSku = goodsSkuDao.find(model.getGoodsSku().getId());
        if (goodsSku == null) {
            throw new BusinessException("sku有误");
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
     * 初始化
     *
     * @param model
     */
    private void initAdd(GoodsSkuSpecValueModel model) {
        model.setAddTime(DateUtil.getNow());
    }

    /**
     * 校验
     *
     * @param model
     * @return
     */
    private GoodsSkuSpecValue checkUpdate(GoodsSkuSpecValueModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误！");
        }
        GoodsSkuSpecValue goodsSkuSpecValue = goodsSkuSpecValueDao.find(model.getId());
        if (goodsSkuSpecValue == null) {
            throw new BusinessException("参数错误！");
        }
        checkOperator(model.getUpdateOperator());
        if (model.getGoodsSpecValue() != null) {
            checkSpecValue(model);
        }
        if (model.getGoodsSku() != null) {
            checkSku(model);
        }
        return goodsSkuSpecValue;
    }

    /**
     * 初始化
     *
     * @param model
     */
    private void initUpdate(GoodsSkuSpecValueModel model, GoodsSkuSpecValue goodsSkuSpecValue) {
        goodsSkuSpecValue.setUpdateTime(DateUtil.getNow());
        goodsSkuSpecValue.setUpdateOperator(model.getUpdateOperator());
        checkSkuAndSpecValue(GoodsSkuSpecValueModel.instance(goodsSkuSpecValue));
    }

    /**
     * 检查重复性
     *
     * @param model
     */
    private void checkSkuAndSpecValue(GoodsSkuSpecValueModel model) {
        if (goodsSkuSpecValueDao.checkSkuAndSpecValue(model)) {
            throw new BusinessException("该spu已含有此规格值");
        }
    }

}