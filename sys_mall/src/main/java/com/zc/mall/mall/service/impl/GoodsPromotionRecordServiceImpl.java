package com.zc.mall.mall.service.impl;

import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.manage.dao.OperatorDao;
import com.zc.mall.core.manage.entity.Operator;
import com.zc.mall.mall.dao.GoodsPromotionRecordDao;
import com.zc.mall.mall.dao.GoodsSkuDao;
import com.zc.mall.mall.entity.GoodsPromotion;
import com.zc.mall.mall.entity.GoodsPromotionRecord;
import com.zc.mall.mall.entity.GoodsSku;
import com.zc.mall.mall.entity.OrderGoods;
import com.zc.mall.mall.model.GoodsPromotionModel;
import com.zc.mall.mall.model.GoodsPromotionRecordModel;
import com.zc.mall.mall.model.GoodsSkuModel;
import com.zc.mall.mall.service.GoodsPromotionRecordService;
import com.zc.mall.promotion.dao.PromotionDao;
import com.zc.mall.promotion.entity.Promotion;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.util.date.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * 商品活动记录
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年12月24日
 */
@Service
public class GoodsPromotionRecordServiceImpl implements GoodsPromotionRecordService {

    @Resource
    private GoodsPromotionRecordDao goodsPromotionRecordDao;
    @Resource
    private OperatorDao operatorDao;
    @Resource
    private PromotionDao promotionDao;
    @Resource
    private GoodsSkuDao goodsSkuDao;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result list(GoodsPromotionRecordModel model) {
        PageDataList<GoodsPromotionRecord> list=goodsPromotionRecordDao.list(model);
        PageDataList<GoodsPromotionRecordModel> modelList = new PageDataList<>();
        modelList.setPage(list.getPage());
        ArrayList<GoodsPromotionRecordModel> goodsPromotionRecordModels = new ArrayList<>();
        for (GoodsPromotionRecord goodsPromotionRecord : list.getList()) {
            GoodsPromotionRecordModel goodsPromotionRecordModel = GoodsPromotionRecordModel.instance(goodsPromotionRecord);
            goodsPromotionRecordModel.setGoodsSkuModel(GoodsSkuModel.instance(goodsPromotionRecordModel.getGoodsSku()));
            goodsPromotionRecordModel.setGoodsPromotionModel(GoodsPromotionModel.instance(goodsPromotionRecordModel.getGoodsPromotion()));
            goodsPromotionRecordModels.add(goodsPromotionRecordModel);
        }
        modelList.setList(goodsPromotionRecordModels);
        return Result.success().setData(modelList);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result add(GoodsPromotionRecordModel model) {
        this.checkAdd(model);
        this.initAdd(model);
        GoodsPromotionRecord goodsPromotionRecordDBDB = goodsPromotionRecordDao.findBySku(model.getGoodsSku().getId());
        if (goodsPromotionRecordDBDB!=null){
            delete(GoodsPromotionRecordModel.instance(goodsPromotionRecordDBDB));
        }
        goodsPromotionRecordDao.save(model.prototype());
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
    public Result update(GoodsPromotionRecordModel model) {
        GoodsPromotionRecord goodsPromotionRecord = this.checkUpdate(model);
        this.initUpdate(model, goodsPromotionRecord);
        goodsPromotionRecordDao.update(goodsPromotionRecord);
        return Result.success();
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getById(GoodsPromotionRecordModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        GoodsPromotionRecord goodsPromotionRecord = goodsPromotionRecordDao.find(model.getId());
        if (goodsPromotionRecord == null) {
            return Result.error("参数错误！");
        }
        return Result.success().setData(GoodsPromotionRecordModel.instance(goodsPromotionRecord));
    }

    /**
     * 删除活动记录
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Object delete(GoodsPromotionRecordModel model) {
        if (model==null||model.getId()<=0){
            throw new BusinessException("活动参数有误");
        }
        GoodsPromotionRecord goodsPromotionRecord = goodsPromotionRecordDao.find(model.getId());
        if (goodsPromotionRecord==null){
            throw new BusinessException("活动不存在");
        }
        goodsPromotionRecord.setEndTime(DateUtil.getNow());
        return goodsPromotionRecordDao.update(goodsPromotionRecord);
    }

    /**
     * 校验
     *
     * @param model
     */
    private void checkAdd(GoodsPromotionRecordModel model) {
        checkOperator(model.getAddOperator());
        if (model.getBeginTime()==null){
            throw new BusinessException("请传入活动开始时间");
        }
        if (model.getEndTime()==null){
            throw new BusinessException("请传入活动结束时间");
        }
        if (DateUtil.daysBetween(model.getBeginTime(),model.getEndTime())<0){
            throw new BusinessException("开始时间不能大于结束时间");
        }
        if (model.getGoodsPromotion()==null||model.getGoodsPromotion().getId()<=0){
            Promotion promotion = promotionDao.find(model.getGoodsPromotion().getId());
            if (promotion==null||promotion.getState()<=0){
                throw new BusinessException("活动不存在或不可使用");
            }
        }
        if (model.getGoodsSku()==null||model.getGoodsSku().getId()<=0){
            GoodsSku goodsSku = goodsSkuDao.find(model.getGoodsSku().getId());
            if (goodsSku==null||goodsSku.getOnSale()== BaseConstant.BUSINESS_STATE_NO){
                throw new BusinessException("商品已经下架，不能添加活动");
            }
        }

    }

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
    private void initAdd(GoodsPromotionRecordModel model) {
        model.setAddTime(DateUtil.getNow());
    }

    /**
     * 校验
     *
     * @param model
     * @return
     */
    private GoodsPromotionRecord checkUpdate(GoodsPromotionRecordModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误！");
        }
        GoodsPromotionRecord goodsPromotionRecord = goodsPromotionRecordDao.find(model.getId());
        if (goodsPromotionRecord == null) {
            throw new BusinessException("参数错误！");
        }
        return goodsPromotionRecord;
    }

    /**
     * 初始化
     *
     * @param model
     */
    private void initUpdate(GoodsPromotionRecordModel model, GoodsPromotionRecord goodsPromotionRecord) {
        goodsPromotionRecord.setBeginTime(model.getBeginTime());
        goodsPromotionRecord.setEndTime(model.getEndTime());
        goodsPromotionRecord.setGoodsPromotion(model.getGoodsPromotion());
    }

    /**
     * 初始化活动
     * @param orderGoods
     * @param promotionCount
     * @return
     */
    public int initGoodsPromotion(OrderGoods orderGoods,int promotionCount) {
        GoodsPromotionRecord goodsPromotionRecordDB = goodsPromotionRecordDao.findBySku(orderGoods.getGoodsSku().getId());
        if (goodsPromotionRecordDB!=null){
            orderGoods.setGoodsPromotionRecord(goodsPromotionRecordDB);
            GoodsPromotion goodsPromotion = goodsPromotionRecordDB.getGoodsPromotion();
            if (goodsPromotion != null) {
                switch (goodsPromotion.getType()) {
                    case 1:
                        if (goodsPromotion.getValue()<=orderGoods.getNumber()){
                            promotionCount = (int) goodsPromotion.getValueAppend();
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        return promotionCount;
    }
}