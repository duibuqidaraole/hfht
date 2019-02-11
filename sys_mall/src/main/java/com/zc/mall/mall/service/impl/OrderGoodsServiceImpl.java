package com.zc.mall.mall.service.impl;

import com.zc.mall.mall.dao.GoodsSkuSpecValueDao;
import com.zc.mall.mall.dao.OrderGoodsCommentDao;
import com.zc.mall.mall.dao.OrderGoodsDao;
import com.zc.mall.mall.entity.OrderGoods;
import com.zc.mall.mall.entity.OrderGoodsComment;
import com.zc.mall.mall.model.*;
import com.zc.mall.mall.service.OrderGoodsService;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单商品
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@Service
public class OrderGoodsServiceImpl implements OrderGoodsService {

    @Resource
    private OrderGoodsDao orderGoodsDao;
    @Resource
    private OrderGoodsCommentDao orderGoodsCommentDao;
    @Resource
    private GoodsSkuSpecValueDao goodsSkuSpecValueDao;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result list(OrderGoodsModel model) {
        PageDataList<OrderGoods> pageDataList = orderGoodsDao.list(model);
        PageDataList<OrderGoodsModel> pageDataList_ = new PageDataList<>();
        pageDataList_.setPage(pageDataList.getPage());
        List<OrderGoodsModel> list = new ArrayList<>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (OrderGoods orderGoods : pageDataList.getList()) {
                OrderGoodsModel model_ = OrderGoodsModel.instance(orderGoods);
                model_.setGoodsSkuModel(GoodsSkuModel.instance(model_.getGoodsSku()));
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
    public Result add(OrderGoodsModel model) {
        this.checkAdd(model);
        this.initAdd(model);
        orderGoodsDao.save(model.prototype());
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
    public Result update(OrderGoodsModel model) {
        OrderGoods orderGoods = this.checkUpdate(model);
        this.initUpdate(model, orderGoods);
        orderGoodsDao.save(orderGoods);
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
    public Result getById(OrderGoodsModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        OrderGoods orderGoods = orderGoodsDao.find(model.getId());
        if (orderGoods == null) {
            return Result.error("参数错误！");
        }
        List<OrderGoodsComment> orderGoodsCommentList = orderGoodsCommentDao.findByOrderGoods(model.getId());
        GoodsBrandModel goodsBrandModel = GoodsBrandModel.instance(orderGoods.getGoodsSku().getGoodsSpu().getGoodsBrand());
        GoodsCategoryModel goodsCategoryModel = GoodsCategoryModel.instance(orderGoods.getGoodsSku().getGoodsSpu().getGoodsCategory());
        List<GoodsSkuSpecValueModel> goodsSkuSpecValueModelList = goodsSkuSpecValueDao.findBySku(orderGoods.getGoodsSku().getId());
        OrderGoodsModel orderGoodsModel = OrderGoodsModel.instance(orderGoods);
        orderGoodsModel.setOrderGoodsCommentList(orderGoodsCommentList);
        orderGoodsModel.setGoodsBrandModel(goodsBrandModel);
        orderGoodsModel.setGoodsCategoryModel(goodsCategoryModel);
        orderGoodsModel.setGoodsSkuSpecValueModelList(goodsSkuSpecValueModelList);
        orderGoodsModel.setOrderInfoModel(OrderInfoModel.instance(orderGoodsModel.getOrderInfo()));
        orderGoodsModel.setGoodsSkuModel(GoodsSkuModel.instance(orderGoodsModel.getGoodsSku()));
        return Result.success().setData(orderGoodsModel);
    }

    /**
     * 校验
     *
     * @param model
     */
    private void checkAdd(OrderGoodsModel model) {

    }

    /**
     * 初始化
     *
     * @param model
     */
    private void initAdd(OrderGoodsModel model) {

    }

    /**
     * 校验
     *
     * @param model
     * @return
     */
    private OrderGoods checkUpdate(OrderGoodsModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误！");
        }
        OrderGoods orderGoods = orderGoodsDao.find(model.getId());
        if (orderGoods == null) {
            throw new BusinessException("参数错误！");
        }
        return orderGoods;
    }

    /**
     * 初始化
     *
     * @param model
     */
    private void initUpdate(OrderGoodsModel model, OrderGoods orderGoods) {

    }

}