package com.zc.mall.core.common.global;

import com.zc.mall.core.sys.constant.ConfigParamConstant;
import com.zc.mall.core.sys.entity.Dict;
import com.zc.mall.core.sys.entity.Template;
import com.zc.mall.core.sys.model.ConfigModel;
import com.zc.sys.common.cache.RedisCacheUtil;
import com.zc.sys.common.util.json.JsonUtil;
import com.zc.sys.common.util.validate.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 全局信息
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月6日
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class Global {

    public static final ThreadLocal transferThreadLocal = new ThreadLocal();

    /**
     * 获取模版信息
     *
     * @param nid
     * @param type
     * @param typeSub
     * @return
     */
    public static Template getTemplate(String nid, int type, int typeSub) {
        RedisCacheUtil cache = (RedisCacheUtil) BeanUtil.getBean(RedisCacheUtil.class);
        Map<String, Object> sysTemplateMap = cache.getCache(ConfigParamConstant.SYS_TEMPLATE, Map.class);
        if (sysTemplateMap == null) {
            sysTemplateMap = InitializeWebConfigContext.initSysTempalte();
        }
        return JsonUtil.formatObject(JsonUtil.formatToStr(sysTemplateMap.get(nid + "_" + type + "_" + typeSub)), Template.class);
    }

    public static Map<String, Object> getTransfer() {
        Map<String, Object> map = (Map<String, Object>) transferThreadLocal.get();
        if (map == null) {
            map = new HashMap<String, Object>();
            transferThreadLocal.set(map);
        }
        return map;
    }

    /**
     * 获取值(只支持部分对象类型初始化)
     *
     * @param key
     * @param clazz
     * @return
     */
    public static <T> T getTransfer(String key, Class<T> clazz) {
        Map<String, Object> map = (Map<String, Object>) transferThreadLocal.get();
        if (map == null) {
            map = new HashMap<String, Object>();
            map.put(key, StringUtil.initValue(clazz));
            transferThreadLocal.set(map);
        }
        T o = (T) map.get(key);
        return o == null ? StringUtil.initValue(clazz) : o;
    }

    public static void setTransfer(String key, Object value) {
        Map<String, Object> map = getTransfer();
        map.put(key, value);
        transferThreadLocal.set(map);
    }

    public static String getValue(String key) {
        RedisCacheUtil cache = (RedisCacheUtil) BeanUtil.getBean(RedisCacheUtil.class);
        ConfigModel sysConfig = cache.getCache(ConfigParamConstant.SYS_CONFIG, ConfigModel.class);
        if (sysConfig == null) {
            sysConfig = InitializeWebConfigContext.initSysConfig();
        }
        String value = sysConfig.getValue(key);
        return StringUtil.isBlank(value) ? "" : value;
    }

    public static String getValueDefalutZero(String key) {
        String value = getValue(key);
        return StringUtil.isBlank(value) ? "0" : value;
    }

    public static int getInt(String key) {
        return StringUtil.toInt(getValueDefalutZero(key));
    }

    public static double getdouble(String key) {
        return Double.parseDouble(getValueDefalutZero(key));
    }

    /**
     * 根据nid获取字典信息
     *
     * @param nid
     * @return
     */
    public static List<Dict> getDictByNid(String nid) {
        RedisCacheUtil cache = (RedisCacheUtil) BeanUtil.getBean(RedisCacheUtil.class);
        Map<String, Object> dictMap = cache.getCache(ConfigParamConstant.SYS_DICT, Map.class);
        if (dictMap == null) {
            dictMap = InitializeWebConfigContext.initDict();
        }
        Object o = dictMap.get(nid);
        if (o == null) {
            return new ArrayList<Dict>();
        }
        List list = (List) dictMap.get(nid);
        List<Dict> listDict = new ArrayList<Dict>();
        for (Object obj : list) {
            listDict.add(JsonUtil.formatObject(JsonUtil.formatToStr(obj), Dict.class));
        }
        return listDict;
    }

    /**
     * 系统状态dev-测试环境;prod-生产环境
     *
     * @return
     */
    public static boolean sysState() {
        if (StringUtil.isNull(Global.getValue(ConfigParamConstant.SYS_PARAM_SYS_STATE)).equals("prod")) {
            return true;
        }
        return false;
    }
}
