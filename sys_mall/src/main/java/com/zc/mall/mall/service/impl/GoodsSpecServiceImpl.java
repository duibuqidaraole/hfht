package com.zc.mall.mall.service.impl;

import com.zc.mall.core.manage.dao.OperatorDao;
import com.zc.mall.core.manage.entity.Operator;
import com.zc.mall.core.manage.model.OperatorModel;
import com.zc.mall.mall.dao.GoodsSpecDao;
import com.zc.mall.mall.entity.GoodsSpec;
import com.zc.mall.mall.model.GoodsSpecModel;
import com.zc.mall.mall.service.GoodsSpecService;
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
 * 商品规格
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@Service
public class GoodsSpecServiceImpl implements GoodsSpecService {

    @Resource
    private GoodsSpecDao goodsSpecDao;
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
    public Result list(GoodsSpecModel model) {
        PageDataList<GoodsSpec> pageDataList = goodsSpecDao.list(model);
        PageDataList<GoodsSpecModel> pageDataList_ = new PageDataList<>();
        pageDataList_.setPage(pageDataList.getPage());
        List<GoodsSpecModel> list = new ArrayList<>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (GoodsSpec goodsSpec : pageDataList.getList()) {
                GoodsSpecModel model_ = GoodsSpecModel.instance(goodsSpec);
                if (goodsSpec.getAddOperator() != null) {
                    model_.setAddOperatorModel(OperatorModel.instance(goodsSpec.getAddOperator()));
                }
                if (goodsSpec.getUpdateOperator() != null) {
                    model_.setUpdateOperatorModel(OperatorModel.instance(goodsSpec.getUpdateOperator()));
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
    public Result add(GoodsSpecModel model) {
        this.checkAdd(model);
        this.initAdd(model);
        goodsSpecDao.save(model.prototype());
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
    public Result update(GoodsSpecModel model) {
        GoodsSpec goodsSpec = this.checkUpdate(model);
        this.initUpdate(model, goodsSpec);
        goodsSpecDao.update(goodsSpec);
        return Result.success();
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getById(GoodsSpecModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        GoodsSpec goodsSpec = goodsSpecDao.find(model.getId());
        if (goodsSpec == null) {
            return Result.error("参数错误！");
        }
        return Result.success().setData(GoodsSpecModel.instance(goodsSpec));
    }

    /**
     * 校验
     *
     * @param model
     */
    private void checkAdd(GoodsSpecModel model) {
        checkOperator(model.getAddOperator());
        if (StringUtil.isBlank(model.getName())) {
            throw new BusinessException("请输入规格名称");
        }
        if (model.getType() == 0) {
            throw new BusinessException("请输入规格类型");
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
    private void initAdd(GoodsSpecModel model) {
        model.setAddTime(DateUtil.getNow());
        model.setNo(StringUtil.getSerialNumber());
    }

    /**
     * 校验
     *
     * @param model
     * @return
     */
    private GoodsSpec checkUpdate(GoodsSpecModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误！");
        }
        GoodsSpec goodsSpec = goodsSpecDao.find(model.getId());
        if (goodsSpec == null) {
            throw new BusinessException("参数错误！");
        }
        checkOperator(model.getUpdateOperator());
        return goodsSpec;
    }

    /**
     * 初始化
     *
     * @param model
     */
    private void initUpdate(GoodsSpecModel model, GoodsSpec goodsSpec) {
        goodsSpec.setUpdateTime(DateUtil.getNow());
        goodsSpec.setUpdateOperator(model.getUpdateOperator());
        if (!StringUtil.isBlank(model.getName())) {
            goodsSpec.setName(model.getName());
        }
    }

}