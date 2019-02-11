package com.zc.mall.mall.service.impl;

import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.manage.dao.OperatorDao;
import com.zc.mall.core.manage.entity.Operator;
import com.zc.mall.mall.constant.GoodsPromotionTypeEnum;
import com.zc.mall.mall.dao.GoodsPromotionDao;
import com.zc.mall.mall.entity.GoodsPromotion;
import com.zc.mall.mall.model.GoodsPromotionModel;
import com.zc.mall.mall.service.GoodsPromotionService;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * 商品活动
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年12月24日
 */
@Service
public class GoodsPromotionServiceImpl implements GoodsPromotionService {

    @Resource
    private GoodsPromotionDao goodsPromotionDao;
    @Resource
    private OperatorDao operatorDao;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result list(GoodsPromotionModel model) {
        PageDataList<GoodsPromotion> list = goodsPromotionDao.list(model);
        PageDataList<GoodsPromotionModel> goodsPromotionModelPageDataList = new PageDataList<>();
        goodsPromotionModelPageDataList.setPage(list.getPage());
        ArrayList<GoodsPromotionModel> goodsPromotionModels = new ArrayList<>();
        for (GoodsPromotion goodsPromotion : list.getList()) {
            goodsPromotionModels.add(GoodsPromotionModel.instance(goodsPromotion));
        }
        goodsPromotionModelPageDataList.setList(goodsPromotionModels);
        return Result.success().setData(goodsPromotionModelPageDataList);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result add(GoodsPromotionModel model) {
        this.checkAdd(model);
        this.initAdd(model);
        goodsPromotionDao.save(model.prototype());
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
    public Result update(GoodsPromotionModel model) {
        GoodsPromotion goodsPromotion = this.checkUpdate(model);
        this.initUpdate(model, goodsPromotion);
        goodsPromotionDao.update(model.prototype());
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
    public Result getById(GoodsPromotionModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        GoodsPromotion goodsPromotion = goodsPromotionDao.find(model.getId());
        if (goodsPromotion == null) {
            return Result.error("参数错误！");
        }
        return Result.success().setData(GoodsPromotionModel.instance(goodsPromotion));
    }

    /**
     * 无分页列表
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Object checkList(GoodsPromotionModel model) {
        model.setPageSize(0);
        return goodsPromotionDao.list(model);
    }



    /**
     * 校验
     *
     * @param model
     */
    private void checkAdd(GoodsPromotionModel model) {
        if (model==null){
            throw new BusinessException("活动信息有误");
        }
        if (model.getType()<=0){
            throw new BusinessException("请传入正确活动类型");
        }
        if (StringUtil.isBlank(model.getValue())){
            throw new BusinessException("请传入正确活动内容");
        }
        if (model.getType()== GoodsPromotionTypeEnum.GOODS_PROMOTION_TYPE_BUY_SEND.getGoodsPromotionType()){
            if (StringUtil.isBlank(model.getValueAppend())){
                throw new BusinessException("请传入正确活动内容");
            }
        }
        checkOperator(model.getAddOperator());
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
    private void initAdd(GoodsPromotionModel model) {
        if (model.getType()==GoodsPromotionTypeEnum.GOODS_PROMOTION_TYPE_BUY_SEND.getGoodsPromotionType()){
            model.setName("买"+(int)model.getValue()+"赠"+(int)model.getValueAppend());
        }
        model.setAddTime(DateUtil.getNow());
    }

    /**
     * 校验
     *
     * @param model
     * @return
     */
    private GoodsPromotion checkUpdate(GoodsPromotionModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误！");
        }
        GoodsPromotion goodsPromotion = goodsPromotionDao.find(model.getId());
        if (goodsPromotion == null) {
            throw new BusinessException("参数错误！");
        }
        return goodsPromotion;
    }

    /**
     * 初始化
     *
     * @param model
     */
    private void initUpdate(GoodsPromotionModel model, GoodsPromotion goodsPromotion) {

    }

}