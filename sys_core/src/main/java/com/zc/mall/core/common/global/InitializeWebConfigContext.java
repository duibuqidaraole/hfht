package com.zc.mall.core.common.global;

import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.sys.constant.ConfigParamConstant;
import com.zc.mall.core.sys.dao.ConfigDao;
import com.zc.mall.core.sys.dao.DictDao;
import com.zc.mall.core.sys.dao.TemplateDao;
import com.zc.mall.core.sys.entity.Template;
import com.zc.mall.core.sys.model.ConfigModel;
import com.zc.sys.common.cache.RedisCacheUtil;
import com.zc.sys.common.util.log.LogUtil;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 初始化系统参数
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月6日
 */
@Component
public class InitializeWebConfigContext {

    private static RedisCacheUtil cache;
    private static TemplateDao templateDao;
    private static ConfigDao configDao;
    private static DictDao dictDao;

    static {
        cache = (RedisCacheUtil) BeanUtil.getBean(RedisCacheUtil.class);
        templateDao = (TemplateDao) BeanUtil.getBean(TemplateDao.class);
        configDao = (ConfigDao) BeanUtil.getBean(ConfigDao.class);
        dictDao = (DictDao) BeanUtil.getBean(DictDao.class);
    }

    /**
     * 初始化调用
     */
    @PostConstruct
    public void contextInitialized() {
        // 初始化系统模版
        initSysTempalte();
        // 初始化系统参数
        initSysConfig();
        // 初始化字典
        initDict();
        LogUtil.info("初始化系统参数SUCCESS====================================================");
    }

    // 初始化字典
    public static Map<String, Object> initDict() {
        Map<String, Object> dictMap = dictDao.initDict();
        cache.setCode(ConfigParamConstant.SYS_DICT, dictMap);
        return dictMap;
    }

    /**
     * 初始化系统模版
     */
    public static Map<String, Object> initSysTempalte() {
        List<Template> list = templateDao.findByProperty("state", BaseConstant.INFO_STATE_YES);
        Map<String, Object> sysTemplateMap = new HashMap<String, Object>();
        for (Template template : list) {
            sysTemplateMap.put(template.getNid() + "_" + template.getType() + "_" + template.getTypeSub(), template);
        }
        cache.setCode(ConfigParamConstant.SYS_TEMPLATE, sysTemplateMap);
        return sysTemplateMap;
    }

    /**
     * 初始化系统配置
     *
     * @return
     */
    public static ConfigModel initSysConfig() {
        ConfigModel config = configDao.getConfig();
        cache.setCode(ConfigParamConstant.SYS_CONFIG, config);
        return config;
    }

}
