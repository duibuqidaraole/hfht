package com.zc.mall.mall.service.impl;

import com.zc.mall.core.manage.dao.OperatorDao;
import com.zc.mall.core.manage.entity.Operator;
import com.zc.mall.core.manage.model.OperatorModel;
import com.zc.mall.mall.dao.GoodsCategoryDao;
import com.zc.mall.mall.entity.GoodsCategory;
import com.zc.mall.mall.model.GoodsCategoryModel;
import com.zc.mall.mall.service.GoodsCategoryService;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@Service
public class GoodsCategoryServiceImpl implements GoodsCategoryService {

    @Resource
    private GoodsCategoryDao goodsCategoryDao;
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
    public Result list(GoodsCategoryModel model) {
        PageDataList<GoodsCategory> pageDataList = goodsCategoryDao.list(model);
        PageDataList<GoodsCategoryModel> pageDataList_ = new PageDataList<>();
        pageDataList_.setPage(pageDataList.getPage());
        List<GoodsCategoryModel> list = new ArrayList<>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (GoodsCategory goodsCategory : pageDataList.getList()) {
                GoodsCategoryModel model_ = GoodsCategoryModel.instance(goodsCategory);
                if (goodsCategory.getAddOperator() != null) {
                    model_.setAddOperatorModel(OperatorModel.instance(goodsCategory.getAddOperator()));
                }
                if (goodsCategory.getUpdateOperator() != null) {
                    model_.setUpdateOperatorModel(OperatorModel.instance(goodsCategory.getUpdateOperator()));
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
    public Result add(GoodsCategoryModel model) {
        this.checkAdd(model);
        this.initAdd(model);
        goodsCategoryDao.save(model.prototype());
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
    public Result update(GoodsCategoryModel model) {
        GoodsCategory goodsCategory = this.checkUpdate(model);
        this.initUpdate(model, goodsCategory);
        goodsCategoryDao.update(goodsCategory);
        return Result.success();
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getById(GoodsCategoryModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        GoodsCategory goodsCategory = goodsCategoryDao.find(model.getId());
        if (goodsCategory == null) {
            return Result.error("参数错误！");
        }
        return Result.success().setData(GoodsCategoryModel.instance(goodsCategory));
    }

    /**
     * 校验
     *
     * @param model
     */
    private void checkAdd(GoodsCategoryModel model) {
        if (model == null) {
            throw new BusinessException("添加商品分类失败");
        }
        if (model.getAddOperator() == null || model.getAddOperator().getId() <= 0) {
            throw new BusinessException("管理员信息有误，无法添加商品分类");
        }

        Operator operator = operatorDao.find(model.getAddOperator().getId());
        if (operator == null) {
            throw new BusinessException("管理员不存在，无法添加商品分类");
        }
        if (StringUtil.isBlank(model.getName())) {
            throw new BusinessException("请输入商品分类名称");
        }
    }

    /**
     * 初始化
     *
     * @param model
     */
    private void initAdd(GoodsCategoryModel model) {
        model.setAddTime(DateUtil.getNow());
    }

    /**
     * 校验
     *
     * @param model
     * @return
     */
    private GoodsCategory checkUpdate(GoodsCategoryModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误！");
        }
        GoodsCategory goodsCategory = goodsCategoryDao.find(model.getId());
        if (goodsCategory == null) {
            throw new BusinessException("参数错误！");
        }
        if (model.getUpdateOperator() == null || model.getUpdateOperator().getId() <= 0) {
            throw new BusinessException("更新管理员信息有误，无法更新商品分类");
        }
        Operator operator = operatorDao.find(model.getUpdateOperator().getId());
        if (operator == null) {
            throw new BusinessException("更新管理员未找到，无法更新商品分类");
        }

        return goodsCategory;
    }

    /**
     * 初始化
     *
     * @param model
     */
    private void initUpdate(GoodsCategoryModel model, GoodsCategory goodsCategory) {
        goodsCategory.setUpdateTime(DateUtil.getNow());
        goodsCategory.setAddOperator(goodsCategory.getUpdateOperator());
    }

}