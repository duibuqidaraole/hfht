package com.zc.mall.core.sys.service.impl;

import com.zc.mall.core.common.global.Global;
import com.zc.mall.core.common.global.InitializeWebConfigContext;
import com.zc.mall.core.sys.constant.ConfigParamConstant;
import com.zc.mall.core.sys.dao.ConfigDao;
import com.zc.mall.core.sys.entity.Config;
import com.zc.mall.core.sys.model.ConfigModel;
import com.zc.mall.core.sys.service.ConfigService;
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
 * 系统参数配置
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月08日
 */
@Service
public class ConfigServiceImpl implements ConfigService {

    @Resource
    private ConfigDao configDao;
    @Resource
    private RedisCacheUtil redisCacheUtil;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    public Result list(ConfigModel model) {
        PageDataList<Config> pageDataList = configDao.list(model);
        PageDataList<ConfigModel> pageDataList_ = new PageDataList<ConfigModel>();
        List<ConfigModel> list = new ArrayList<ConfigModel>();
        pageDataList_.setPage(pageDataList.getPage());
        if (pageDataList.getList().size() > 0) {
            for (Config config : pageDataList.getList()) {
                ConfigModel configModel = ConfigModel.instance(config);
                list.add(configModel);
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
    public Result add(ConfigModel model) {
        this.checkAdd(model);
        this.initAdd(model);
        configDao.save(model.prototype());
        // 重新加载缓存
        refreshConfigCache();
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
    public Result update(ConfigModel model) {
        Config config = this.checkUpdate(model);
        this.initUpdate(model, config);
        configDao.update(model.prototype());
        // 重新加载缓存
        refreshConfigCache();
        return Result.success();
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getById(ConfigModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        Config config = configDao.find(model.getId());
        return Result.success().setData(config);
    }

    /**
     * 刷新缓存
     *
     * @return
     */
    @Override
    public Result refreshConfigCache() {
        redisCacheUtil.delCode(ConfigParamConstant.SYS_CONFIG);
        InitializeWebConfigContext.initDict();
        return Result.success();
    }

    /**
     * 通过Nid获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getConfigByNid(ConfigModel model) {
        if (StringUtil.isBlank(model.getNid())) {
            return Result.error("参数错误！");
        }
        return Result.success().setData(Global.getValue(model.getNid()));
    }


    /**
     * 校验
     *
     * @param model
     */
    private void checkAdd(ConfigModel model) {
        if (StringUtil.isBlank(model.getNid())) {
            throw new BusinessException("标识不能为空！");
        }
        if (StringUtil.isBlank(model.getName())) {
            throw new BusinessException("名称不能为空！");
        }
        if (StringUtil.isBlank(model.getValue())) {
            throw new BusinessException("参数值不能为空！");
        }
        if (model.getType() <= 0) {
            throw new BusinessException("类型错误！");
        }
        Config nidEntity = configDao.getByNid(model.getNid());
        if (nidEntity != null) {
            throw new BusinessException("该标识已存在！");
        }
    }

    /**
     * 初始化
     *
     * @param model
     */
    private void initAdd(ConfigModel model) {

    }


    /**
     * 校验
     *
     * @param model
     * @return
     */
    private Config checkUpdate(ConfigModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误！");
        }
        Config config = configDao.find(model.getId());
        if (config == null) {
            throw new BusinessException("参数错误！");
        }
        if (StringUtil.isBlank(model.getNid())) {
            throw new BusinessException("标识不能为空！");
        }
        if (StringUtil.isBlank(model.getName())) {
            throw new BusinessException("名称不能为空！");
        }
        if (StringUtil.isBlank(model.getValue())) {
            throw new BusinessException("参数值不能为空！");
        }
        if (model.getType() <= 0) {
            throw new BusinessException("类型错误！");
        }
        Config nidEntity = configDao.getByNid(model.getNid());
        if (nidEntity != null && !nidEntity.getNid().equals(config.getNid())) {
            throw new BusinessException("该标识已存在！");
        }
        return config;
    }

    /**
     * 初始化
     *
     * @param model
     * @param config
     */
    private void initUpdate(ConfigModel model, Config config) {
        config.setName(model.getName());
        config.setNid(model.getNid());
        config.setRemark(model.getRemark());
        config.setState(model.getState());
        config.setType(model.getType());
        config.setValue(model.getValue());
    }
}