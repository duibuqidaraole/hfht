package com.zc.mall.promotion.service.impl;

import com.zc.mall.core.manage.dao.OperatorDao;
import com.zc.mall.core.manage.entity.Operator;
import com.zc.mall.promotion.dao.VipCouponsDao;
import com.zc.mall.promotion.entity.VipCoupons;
import com.zc.mall.promotion.model.VipCouponsModel;
import com.zc.mall.promotion.service.VipCouponsService;
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
public class VipCouponsServiceImpl implements VipCouponsService {

    @Resource
    private VipCouponsDao vipCouponsDao;
    @Resource
    private OperatorDao operatorDao;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    public Result list(VipCouponsModel model) {
        PageDataList<VipCoupons> pageDataList = vipCouponsDao.list(model);
        PageDataList<VipCouponsModel> pageDataList_ = new PageDataList<VipCouponsModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<VipCouponsModel> list = new ArrayList<VipCouponsModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (VipCoupons vipCoupons : pageDataList.getList()) {
                VipCouponsModel model_ = VipCouponsModel.instance(vipCoupons);
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
    public Result add(VipCouponsModel model) {
        this.checkAdd(model);
        this.initAdd(model);
        vipCouponsDao.save(model.prototype());
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
    public Result update(VipCouponsModel model) {
        VipCoupons vipCoupons = this.checkUpdate(model);
        this.initUpdate(model, vipCoupons);
        vipCouponsDao.update(model.prototype());
        return Result.success();
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public VipCouponsModel getById(VipCouponsModel model) {
        if (model.getId() <= 0) {
            new BusinessException("参数错误！");
        }
        VipCoupons vipCoupons = vipCouponsDao.find(model.getId());
        if (vipCoupons == null) {
            new BusinessException("参数错误！");
        }
        return VipCouponsModel.instance(vipCoupons);
    }

    /**
     * 获取
     *
     * @param id
     * @return
     */
    @Override
    public VipCouponsModel getById(long id) {
        VipCouponsModel vipCouponsModel = new VipCouponsModel();
        vipCouponsModel.setId(id);
        return getById(vipCouponsModel);
    }

    /**
     * 校验
     *
     * @param model
     */
    private void checkAdd(VipCouponsModel model) {
        if (model.getGrade() <= 0) {
            throw new BusinessException("请传入正确会员等级");
        }
        if (StringUtil.isBlank(model.getName())) {
            throw new BusinessException("请输入正确名称");
        }
        if (StringUtil.isBlank(model.getContent())) {
            throw new BusinessException("请输入正确内容");
        }
        if (model.getValue() <= 0) {
            throw new BusinessException("请输入正确优惠值");
        }
        if (model.getPrince() < 0) {
            throw new BusinessException("请输入正确价值");
        }
        if (model.getValidityType() <= 0) {
            throw new BusinessException("请输入有效期类型");
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
    private void initAdd(VipCouponsModel model) {
        model.setAddTime(DateUtil.getNow());
        if (model.getBeginTime() == null) {
            model.setBeginTime(DateUtil.getNow());
        }
        model.setPrizeNo(StringUtil.getSerialNumber());
    }

    /**
     * 校验
     *
     * @param model
     * @return
     */
    private VipCoupons checkUpdate(VipCouponsModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误！");
        }
        VipCoupons vipCoupons = vipCouponsDao.find(model.getId());
        if (vipCoupons == null) {
            throw new BusinessException("参数错误！");
        }
        if (model.getGrade() <= 0) {
            throw new BusinessException("请传入正确会员等级");
        }
        if (StringUtil.isBlank(model.getName())) {
            throw new BusinessException("请输入正确名称");
        }
        if (StringUtil.isBlank(model.getContent())) {
            throw new BusinessException("请输入正确内容");
        }
        if (model.getValue() <= 0) {
            throw new BusinessException("请输入正确优惠值");
        }
        if (model.getPrince() < 0) {
            throw new BusinessException("请输入正确价值");
        }
        if (model.getValidityType() <= 0) {
            throw new BusinessException("请输入有效期类型");
        }
        checkOperator(model.getUpdateOperator());
        return vipCoupons;
    }

    /**
     * 初始化
     *
     * @param model
     */
    private void initUpdate(VipCouponsModel model, VipCoupons vipCoupons) {
        model.setAddOperator(vipCoupons.getAddOperator());
        model.setUpdateTime(DateUtil.getNow());
        model.setAddTime(vipCoupons.getAddTime());
    }

}