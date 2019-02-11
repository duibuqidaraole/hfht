package com.zc.mall.mall.service.impl;

import com.zc.mall.core.manage.dao.OperatorDao;
import com.zc.mall.core.manage.entity.Operator;
import com.zc.mall.core.manage.model.OperatorModel;
import com.zc.mall.mall.dao.GoodsBrandDao;
import com.zc.mall.mall.entity.GoodsBrand;
import com.zc.mall.mall.model.GoodsBrandModel;
import com.zc.mall.mall.service.GoodsBrandService;
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
 * 商品品牌表
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@Service
public class GoodsBrandServiceImpl implements GoodsBrandService {

    @Resource
    private GoodsBrandDao goodsBrandDao;
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
    public Result list(GoodsBrandModel model) {
        PageDataList<GoodsBrand> pageDataList = goodsBrandDao.list(model);
        PageDataList<GoodsBrandModel> pageDataList_ = new PageDataList<>();
        pageDataList_.setPage(pageDataList.getPage());
        List<GoodsBrandModel> list = new ArrayList<>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (GoodsBrand goodsBrand : pageDataList.getList()) {
                GoodsBrandModel model_ = GoodsBrandModel.instance(goodsBrand);
                if (goodsBrand.getAddOperator() != null) {
                    model_.setAddOperatorModel(OperatorModel.instance(goodsBrand.getAddOperator()));
                }
                if (goodsBrand.getUpdateOperator() != null) {
                    model_.setUpdateOperatorModel(OperatorModel.instance(goodsBrand.getUpdateOperator()));
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
    public Result add(GoodsBrandModel model) {
        this.checkAdd(model);
        this.initAdd(model);
        goodsBrandDao.save(model.prototype());
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
    public Result update(GoodsBrandModel model) {
        GoodsBrand goodsBrand = this.checkUpdate(model);
        this.initUpdate(model, goodsBrand);
        goodsBrandDao.update(goodsBrand);
        return Result.success();
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getById(GoodsBrandModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        GoodsBrand goodsBrand = goodsBrandDao.find(model.getId());
        if (goodsBrand == null) {
            return Result.error("参数错误！");
        }
        return Result.success().setData(GoodsBrandModel.instance(goodsBrand));
    }

    /**
     * 校验
     *
     * @param model
     */
    private void checkAdd(GoodsBrandModel model) {
        if (model.getAddOperator() == null || model.getAddOperator().getId() <= 0) {
            throw new BusinessException("管理员信息有误，无法添加商品分类");
        }
        Operator operator = operatorDao.find(model.getAddOperator().getId());
        if (operator == null) {
            throw new BusinessException("管理员不存在，无法添加商品分类");
        }
        if (StringUtil.isBlank(model.getName())) {
            throw new BusinessException("请输入商品品牌名称");
        }
    }

    /**
     * 初始化
     *
     * @param model
     */
    private void initAdd(GoodsBrandModel model) {
        model.setAddTime(DateUtil.getNow());
    }

    /**
     * 校验
     *
     * @param model
     * @return
     */
    private GoodsBrand checkUpdate(GoodsBrandModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误！");
        }
        GoodsBrand goodsBrand = goodsBrandDao.find(model.getId());
        if (goodsBrand == null) {
            throw new BusinessException("参数错误！");
        }

        if (model.getUpdateOperator() == null || model.getUpdateOperator().getId() <= 0) {
            throw new BusinessException("管理员信息有误，无法添加商品分类");
        }

        Operator operator = operatorDao.find(model.getUpdateOperator().getId());
        if (operator == null) {
            throw new BusinessException("管理员不存在，无法添加商品分类");
        }
        return goodsBrand;
    }

    /**
     * 初始化
     *
     * @param model
     */
    private void initUpdate(GoodsBrandModel model, GoodsBrand goodsBrand) {
        goodsBrand.setUpdateTime(DateUtil.getNow());
        goodsBrand.setAddOperator(model.getUpdateOperator());
    }

}