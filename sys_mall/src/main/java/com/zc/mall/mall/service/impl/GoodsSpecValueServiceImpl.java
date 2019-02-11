package com.zc.mall.mall.service.impl;

import com.zc.mall.core.manage.dao.OperatorDao;
import com.zc.mall.core.manage.entity.Operator;
import com.zc.mall.core.manage.model.OperatorModel;
import com.zc.mall.mall.dao.GoodsSpecDao;
import com.zc.mall.mall.dao.GoodsSpecValueDao;
import com.zc.mall.mall.entity.GoodsSpec;
import com.zc.mall.mall.entity.GoodsSpecValue;
import com.zc.mall.mall.model.GoodsSpecModel;
import com.zc.mall.mall.model.GoodsSpecValueModel;
import com.zc.mall.mall.service.GoodsSpecValueService;
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
 * 商品规格值
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@Service
public class GoodsSpecValueServiceImpl implements GoodsSpecValueService {

    @Resource
    private GoodsSpecValueDao goodsSpecValueDao;
    @Resource
    private OperatorDao operatorDao;
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
    public Result list(GoodsSpecValueModel model) {
        PageDataList<GoodsSpecValue> pageDataList = goodsSpecValueDao.list(model);
        PageDataList<GoodsSpecValueModel> pageDataList_ = new PageDataList<>();
        pageDataList_.setPage(pageDataList.getPage());
        List<GoodsSpecValueModel> list = new ArrayList<>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (GoodsSpecValue goodsSpecValue : pageDataList.getList()) {
                GoodsSpecValueModel model_ = GoodsSpecValueModel.instance(goodsSpecValue);
                if (goodsSpecValue.getAddOperator() != null) {
                    model_.setAddOperatorModel(OperatorModel.instance(goodsSpecValue.getAddOperator()));
                }
                if (goodsSpecValue.getUpdateOperator() != null) {
                    model_.setUpdateOperatorModel(OperatorModel.instance(goodsSpecValue.getUpdateOperator()));
                }
                if (goodsSpecValue.getGoodsSpec() != null) {
                    model_.setGoodsSpecModel(GoodsSpecModel.instance(goodsSpecValue.getGoodsSpec()));
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
    public Result add(GoodsSpecValueModel model) {
        this.checkAdd(model);
        this.initAdd(model);
        goodsSpecValueDao.save(model.prototype());
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
    public Result update(GoodsSpecValueModel model) {
        GoodsSpecValue goodsSpecValue = this.checkUpdate(model);
        this.initUpdate(model, goodsSpecValue);
        goodsSpecValueDao.update(goodsSpecValue);
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
    public Result getById(GoodsSpecValueModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        GoodsSpecValue goodsSpecValue = goodsSpecValueDao.find(model.getId());
        if (goodsSpecValue == null) {
            return Result.error("参数错误！");
        }
        GoodsSpecValueModel goodsSpecValueModel = GoodsSpecValueModel.instance(goodsSpecValue);
        goodsSpecValueModel.setGoodsSpecModel(GoodsSpecModel.instance(goodsSpecValueModel.getGoodsSpec()));
        goodsSpecValueModel.setUpdateOperatorModel(OperatorModel.instance(goodsSpecValueModel.getUpdateOperator()));
        goodsSpecValueModel.setAddOperatorModel(OperatorModel.instance(goodsSpecValueModel.getAddOperator()));
        return Result.success().setData(goodsSpecValueModel);
    }

    /**
     * 校验
     *
     * @param model
     */
    private void checkAdd(GoodsSpecValueModel model) {
        checkOperator(model.getAddOperator());
        checkGoodsSpec(model);
        if (StringUtil.isBlank(model.getValue())) {
            throw new BusinessException("请输入规格值");
        }
    }

    /**
     * 校验规格
     *
     * @param model
     */
    private void checkGoodsSpec(GoodsSpecValueModel model) {
        if (model.getGoodsSpec() == null || model.getGoodsSpec().getId() <= 0) {
            throw new BusinessException("商品规格有误");
        }
        GoodsSpec goodsSpec = goodsSpecDao.find(model.getGoodsSpec().getId());
        if (goodsSpec == null) {
            throw new BusinessException("商品规格有误");
        }
    }

    /**
     * 校验更新时规格
     *
     * @param model
     */
    private void checkUpdateGoodsSpec(GoodsSpecValueModel model) {
        if (model.getGoodsSpec() == null || model.getGoodsSpec().getId() <= 0) {
            throw new BusinessException("商品规格有误");
        }
        GoodsSpec goodsSpec = goodsSpecDao.find(model.getGoodsSpec().getId());
        if (goodsSpec == null) {
            throw new BusinessException("商品规格不存在");
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
    private void initAdd(GoodsSpecValueModel model) {
        model.setAddTime(DateUtil.getNow());
    }

    /**
     * 校验
     *
     * @param model
     * @return
     */
    private GoodsSpecValue checkUpdate(GoodsSpecValueModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误！");
        }
        GoodsSpecValue goodsSpecValue = goodsSpecValueDao.find(model.getId());
        if (goodsSpecValue == null) {
            throw new BusinessException("参数错误！");
        }
        checkOperator(model.getUpdateOperator());
        if (model.getGoodsSpec() != null) {
            checkUpdateGoodsSpec(model);
        }
        return goodsSpecValue;
    }


    /**
     * 初始化
     *
     * @param model
     */
    private void initUpdate(GoodsSpecValueModel model, GoodsSpecValue goodsSpecValue) {
        goodsSpecValue.setUpdateTime(DateUtil.getNow());
        goodsSpecValue.setUpdateOperator(model.getUpdateOperator());
        if (!StringUtil.isBlank(model.getValue())) {
            goodsSpecValue.setValue(model.getValue());
        }


    }

}