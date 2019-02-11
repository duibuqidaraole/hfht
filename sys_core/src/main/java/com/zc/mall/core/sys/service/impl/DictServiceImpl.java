package com.zc.mall.core.sys.service.impl;

import com.zc.mall.core.common.global.Global;
import com.zc.mall.core.common.global.InitializeWebConfigContext;
import com.zc.mall.core.sys.constant.ConfigParamConstant;
import com.zc.mall.core.sys.dao.DictDao;
import com.zc.mall.core.sys.entity.Dict;
import com.zc.mall.core.sys.model.DictModel;
import com.zc.mall.core.sys.service.DictService;
import com.zc.sys.common.cache.RedisCacheUtil;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 字典
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年7月29日
 */
@Service
public class DictServiceImpl implements DictService {

    @Resource
    private DictDao dictDao;
    @Resource
    private RedisCacheUtil redisCacheUtil;

    @Override
    public Result list(DictModel model) {
        PageDataList<Dict> pageDataList = dictDao.list(model);
        PageDataList<DictModel> pageDataList_ = new PageDataList<DictModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<DictModel> list = new ArrayList<DictModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (Dict dict : pageDataList.getList()) {
                DictModel model_ = DictModel.instance(dict);
                list.add(model_);
            }
        }
        pageDataList_.setList(list);
        return Result.success().setData(pageDataList_);
    }

    @Override
    @Transactional
    public Result add(DictModel model) {
        this.checkAdd(model);
        this.initAdd(model);
        dictDao.save(model.prototype());
        refreshDictCache();
        return Result.success();
    }

    @Override
    @Transactional
    public Result update(DictModel model) {
        Dict dict = this.checkUpdate(model);
        this.initUpdate(model, dict);
        dictDao.update(dict);
        refreshDictCache();
        return Result.success();
    }

    @Override
    public Result getById(DictModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        Dict dict = dictDao.find(model.getId());
        return Result.success().setData(dict);
    }

    /**
     * nid查询字典
     *
     * @param model
     * @return
     */
    @Override
    public Result getByNid(DictModel model) {
        if (StringUtil.isBlank(model.getNid())) {
            return Result.error("参数错误！");
        }
        List<Dict> list = Global.getDictByNid(model.getNid());
        return Result.success().setData(list);
    }

    /**
     * 刷新字典缓存
     *
     * @return
     */
    @Override
    public Result refreshDictCache() {
        redisCacheUtil.delCode(ConfigParamConstant.SYS_DICT);
        InitializeWebConfigContext.initDict();
        return Result.success();
    }

    /**
     * 校验
     *
     * @param model
     */
    private void checkAdd(DictModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("主键id不能为空！");
        }
        if (StringUtil.isBlank(model.getName())) {
            throw new BusinessException("名称不能为空！");
        }
        if (StringUtil.isBlank(model.getNid())) {
            throw new BusinessException("nid标识不能为空！");
        }
        if (StringUtil.isBlank(model.getNidName())) {
            throw new BusinessException("nid标识名称不能为空！");
        }
        if (StringUtil.isBlank(model.getValue())) {
            throw new BusinessException("值不能为空！");
        }
        if (model.getSort() == 0 || model.getSort() < 0) {
            throw new BusinessException("排序不能为空！");
        }
        if (model.getState() == 0) {
            throw new BusinessException("状态不能为空！");
        }
    }

    /**
     * 初始化
     *
     * @param model
     */
    private void initAdd(DictModel model) {

    }


    /**
     * 校验
     *
     * @param model
     * @return
     */
    private Dict checkUpdate(DictModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误！");
        }
        Dict dict = dictDao.find(model.getId());
        if (dict == null) {
            throw new BusinessException("参数错误！");
        }
        this.checkAdd(model);
        return dict;
    }

    /**
     * 初始化
     *
     * @param model
     * @param dict
     */
    private void initUpdate(DictModel model, Dict dict) {
        dict.setName(model.getName());
        dict.setNidName(model.getNidName());
        dict.setRemark(model.getRemark());
        dict.setSort(model.getSort());
        dict.setState(model.getState());
        dict.setValue(model.getValue());
    }
}
