package com.zc.mall.mall.service.impl;

import com.zc.mall.core.manage.dao.OperatorDao;
import com.zc.mall.core.manage.entity.Operator;
import com.zc.mall.core.manage.model.OperatorModel;
import com.zc.mall.mall.dao.GoodsSpecDao;
import com.zc.mall.mall.dao.GoodsSpuDao;
import com.zc.mall.mall.dao.GoodsSpuSpecDao;
import com.zc.mall.mall.entity.GoodsSpec;
import com.zc.mall.mall.entity.GoodsSpu;
import com.zc.mall.mall.entity.GoodsSpuSpec;
import com.zc.mall.mall.model.GoodsSpecModel;
import com.zc.mall.mall.model.GoodsSpuModel;
import com.zc.mall.mall.model.GoodsSpuSpecModel;
import com.zc.mall.mall.service.GoodsSpuSpecService;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.util.date.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品SPU-规格
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@Service
public class GoodsSpuSpecServiceImpl implements GoodsSpuSpecService {

    @Resource
    private GoodsSpuSpecDao goodsSpuSpecDao;
    @Resource
    private OperatorDao operatorDao;
    @Resource
    private GoodsSpuDao goodsSpuDao;
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
    public Result list(GoodsSpuSpecModel model) {
        PageDataList<GoodsSpuSpec> pageDataList = goodsSpuSpecDao.list(model);
        PageDataList<GoodsSpuSpecModel> pageDataList_ = new PageDataList<>();
        pageDataList_.setPage(pageDataList.getPage());
        List<GoodsSpuSpecModel> list = new ArrayList<>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (GoodsSpuSpec goodsSpuSpec : pageDataList.getList()) {
                GoodsSpuSpecModel model_ = GoodsSpuSpecModel.instance(goodsSpuSpec);
                if (goodsSpuSpec.getAddOperator() != null) {
                    model_.setAddOperatorModel(OperatorModel.instance(goodsSpuSpec.getAddOperator()));
                }
                if (goodsSpuSpec.getUpdateOperator() != null) {
                    model_.setUpdateOperatorModel(OperatorModel.instance(goodsSpuSpec.getUpdateOperator()));
                }
                if (goodsSpuSpec.getGoodsSpec() != null) {
                    model_.setGoodsSpecModel(GoodsSpecModel.instance(goodsSpuSpec.getGoodsSpec()));
                }
                if (goodsSpuSpec.getGoodsSpu() != null) {
                    model_.setGoodsSpuModel(GoodsSpuModel.instance(goodsSpuSpec.getGoodsSpu()));
                }

                list.add(model_);
            }
        }
        pageDataList_.setList(list);
        return Result.success().setData(pageDataList_);
    }

    /**
     * 未选择列表
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Object unCheckList(GoodsSpuSpecModel model) {
        List<GoodsSpec> goodsSpecs = goodsSpuSpecDao.unCheckList(model);
        List<GoodsSpecModel> list = new ArrayList<>();
        if (goodsSpecs != null && goodsSpecs.size() > 0) {
            for (GoodsSpec goodsSpec : goodsSpecs) {
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
        return Result.success().setData(list);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result add(GoodsSpuSpecModel model) {
        this.checkAdd(model);
        this.initAdd(model);
        goodsSpuSpecDao.save(model.prototype());
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
    public Result update(GoodsSpuSpecModel model) {
        GoodsSpuSpec goodsSpuSpec = this.checkUpdate(model);
        this.initUpdate(model, goodsSpuSpec);
        goodsSpuSpecDao.update(goodsSpuSpec);
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
    public Result getById(GoodsSpuSpecModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        GoodsSpuSpec goodsSpuSpec = goodsSpuSpecDao.find(model.getId());
        if (goodsSpuSpec == null) {
            return Result.error("参数错误！");
        }
        GoodsSpuSpecModel goodsSpuSpecModel = GoodsSpuSpecModel.instance(goodsSpuSpec);
        goodsSpuSpecModel.setGoodsSpuModel(GoodsSpuModel.instance(goodsSpuSpecModel.getGoodsSpu()));
        goodsSpuSpecModel.setGoodsSpecModel(GoodsSpecModel.instance(goodsSpuSpecModel.getGoodsSpec()));
        return Result.success().setData(goodsSpuSpecModel);
    }


    /**
     * 校验
     *
     * @param model
     */
    private void checkAdd(GoodsSpuSpecModel model) {
        checkOperator(model.getAddOperator());
        checkSpu(model);
        checkSpec(model);
        checkSpecAndSku(model);
    }

    /**
     * 校验spec
     *
     * @param model
     */
    private void checkSpec(GoodsSpuSpecModel model) {
        if (model.getGoodsSpec() == null || model.getGoodsSpec().getId() <= 0) {
            throw new BusinessException("规格信息有误");
        }
        GoodsSpec goodsSpec = goodsSpecDao.find(model.getGoodsSpec().getId());
        if (goodsSpec == null) {
            throw new BusinessException("规格信息有误");
        }
    }

    /**
     * 校验spu
     *
     * @param model
     */
    private void checkSpu(GoodsSpuSpecModel model) {
        if (model.getGoodsSpu() == null || model.getGoodsSpu().getId() <= 0) {
            throw new BusinessException("spu信息有误");
        }
        GoodsSpu goodsSpu = goodsSpuDao.find(model.getGoodsSpu().getId());
        if (goodsSpu == null) {
            throw new BusinessException("spu信息有误");
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
    private void initAdd(GoodsSpuSpecModel model) {
        model.setAddTime(DateUtil.getNow());
    }

    /**
     * 校验
     *
     * @param model
     * @return
     */
    private GoodsSpuSpec checkUpdate(GoodsSpuSpecModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误！");
        }
        GoodsSpuSpec goodsSpuSpec = goodsSpuSpecDao.find(model.getId());
        if (goodsSpuSpec == null) {
            throw new BusinessException("参数错误！");
        }
        if (model.getGoodsSpu() != null) {
            checkSpu(model);
        }
        if (model.getGoodsSpec() != null) {
            checkSpec(model);
        }
        return goodsSpuSpec;
    }

    /**
     * 初始化
     *
     * @param model
     */
    private void initUpdate(GoodsSpuSpecModel model, GoodsSpuSpec goodsSpuSpec) {
        goodsSpuSpec.setUpdateTime(DateUtil.getNow());
        goodsSpuSpec.setUpdateOperator(model.getUpdateOperator());
        checkSpecAndSku(GoodsSpuSpecModel.instance(goodsSpuSpec));
    }

    /**
     * 查询是否有重复信息
     *
     * @param model
     */
    private void checkSpecAndSku(GoodsSpuSpecModel model) {
        if (goodsSpuSpecDao.checkSpecAndSpu(model)) {
            throw new BusinessException("该spu已有此规格");
        }
    }

}